package com.lfg.spring.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfg.spring.model.Friendship;
import com.lfg.spring.model.Message;
import com.lfg.spring.model.enums.WSEvent;
import com.lfg.spring.model.projections.UserId;
import com.lfg.spring.service.FriendshipService;
import com.lfg.spring.service.MessageService;
import com.lfg.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@FunctionalInterface
interface WSFunctionHandler {
    
    public void handle(Long fromUser, TextMessage textMessage);
}

@Slf4j
@Component
public class webSocketHandler extends TextWebSocketHandler {

    // having a websocket connections means the user is online
    private HashMap<Long, WebSocketSession> connections = new HashMap<Long, WebSocketSession>();

    // because number of events will increase in the near future this way we will avoid the long-ugly if or switch statements
    private final Map<String, WSFunctionHandler> handlers = Map.of(WSEvent.CHAT.name(), new webSocketHandler()::HandleChatEvent);

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private MessageService messageService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        Long userId = getUserId(session);

        sendOnlineEventToFriendsOf(userId);

        connections.put(userId, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){

        connections.remove(getUserId(session));
    }

    @Override
    public void handleTextMessage(WebSocketSession client, TextMessage textMessage) throws InterruptedException, IOException {
        JSONObject message = new JSONObject(textMessage.getPayload());

        String event = (String) message.get("event");
        WSFunctionHandler handler = handlers.get(event);

        if(handler == null)
            throw new IllegalArgumentException("Invalid handler of type: " + event);
    
        handler.handle(getUserId(client), textMessage);
    }

    private void sendOnlineEventToFriendsOf(Long userId){

        List<UserId> friends = friendshipService.getOnlineFriendsOf(userId);

        friends.forEach(friend -> {
            send(connections.get(friend.getUserId()), new TextMessage(new JSONObject()
            .put("event", "online")
            .put("user", userId)
            .toString()));
        });
    }

    private HandleChatEvent(Long fromUser, TextMessage textMessage){
        JSONObject message = new JSONObject(textMessage.getPayload());
        Long toUser = (Long) message.get("to");

        if(isOnline(toUser))
            send(connections.get(toUser), textMessage);

        messageService.save(Message.builder()
            .toUser(userService.getReference((Long) message.get("touser")))
            .fromUser(userService.getReference(fromUser))
            .content((String) message.get("message")).build());
    }

    private boolean isOnline(Long userId){
    
        return connections.containsKey(userId);
    }

    private void send(WebSocketSession client, TextMessage message){

        try { client.sendMessage(message); } 
            catch(IOException error){ log.error("Unable to send message to {} with error message {}", username, error); }
    }

    private Long getUserId(WebSocketSession session){

        return (Long) session.getAttributes().get("userId");
    }



}
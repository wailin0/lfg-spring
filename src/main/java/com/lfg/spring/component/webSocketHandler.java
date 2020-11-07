package com.lfg.spring.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.lfg.spring.model.enums.WSEvent;
import com.lfg.spring.service.FriendshipService;
import com.lfg.spring.service.MessageService;
import com.lfg.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
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

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private MessageService messageService;

    // holds userId and webscoket session
    private HashMap<Long, WebSocketSession> connections = new HashMap<Long, WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        Long userId = getUserId(session);

        sendIsOnlineEventToFriendsOf(userId, true);

        connections.put(userId, session);

        userService.setOnline(userId, true);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        Long userId = getUserId(session);

        sendIsOnlineEventToFriendsOf(userId, false);

        connections.remove(userId);

        userService.setOnline(userId, false);
    }

    @Override
    @SneakyThrows(JSONException.class)
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws InterruptedException, IOException {
        JSONObject message = new JSONObject(textMessage.getPayload());

        String event = (String) message.get("event");

        // TODO: make the function-reference-map works instead of if-else
        if(event.equals(WSEvent.EVENT_CHAT))
            HandleChatEvent(getUserId(session), textMessage);
    }

    private void sendIsOnlineEventToFriendsOf(Long userId, boolean isOnline){

        List<Long> friends = friendshipService.getOnlineFriendsOf(userId);

        friends.forEach(friend -> {

		    send(connections.get(friend), custructIsOnlineEventMessage(userId, isOnline));
        });
    }

    @SneakyThrows(JSONException.class)
    private TextMessage custructIsOnlineEventMessage(Long sneder, boolean isOnline){

        return new TextMessage(new JSONObject()
            .put("event", WSEvent.EVENT_CHAT)
            .put("online", isOnline)
            .put("user", sneder)
            .toString());
    }

    @SneakyThrows(JSONException.class)
    private void HandleChatEvent(Long fromUser, TextMessage textMessage){
        JSONObject message = new JSONObject(textMessage.getPayload());
        Long toUser = Long.valueOf((String) message.get("touser"));

        if(isOnline(toUser))
            send(connections.get(toUser), textMessage);
        
        messageService.create(toUser, fromUser, (String) message.get("message"));
    }

    private boolean isOnline(Long userId){
    
        return connections.containsKey(userId);
    }

    private void send(WebSocketSession session, TextMessage message){

        try { session.sendMessage(message); } 
            catch(IOException error){ log.error("Unable to send message {}", error); }
    }

    private Long getUserId(WebSocketSession session){

        // TODO: this casting is so eye burning please find a work around
        return Long.valueOf((String) session.getAttributes().get("userId"));
    }

}
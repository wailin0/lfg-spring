package com.lfg.spring.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.lfg.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.SneakyThrows;

@Component
public class webSocketHandler extends TextWebSocketHandler {

    @Autowired
    private UserService userService;

    // holds username and ws.session
    private HashMap<String, WebSocketSession> onlineUsers = new HashMap<String, WebSocketSession>();

    @SneakyThrows(Exception.class)
    public void handleTextMessage(WebSocketSession client, TextMessage textMessage) {
        JSONObject message = new JSONObject(textMessage.getPayload());
    
        String toUser = (String) message.get("message_to");

        if(isOnline(toUser));
            send(toUser, textMessage);

        // TODO: save the message in db
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession client){
        // TODO: if this client have friends notify them about being online
        System.out.println(client.getPrincipal().getName());
        //onlineUsers.put(client.getPrincipal().getName(), client);
    }

    public void broadcast(WebSocketSession sender, TextMessage message){
        for(WebSocketSession client : onlineUsers.values())
            send(client, message);
    }

    private List<WebSocketSession> getOnlineFriendsOf(String username){ 

        return null;
    }
    
    private boolean isOnline(String username){
    
        return onlineUsers.containsKey(username);
    }

    @SneakyThrows(IOException.class)
    public void send(String username, TextMessage message){

        onlineUsers.get(username).sendMessage(message);
    }

    @SneakyThrows(IOException.class)
    public void send(WebSocketSession session, TextMessage message){

        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession client, CloseStatus status){

        onlineUsers.remove(client.getPrincipal().toString());
    }

}
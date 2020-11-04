package com.lfg.spring.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfg.spring.model.enums.WSEvent;
import com.lfg.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.SneakyThrows;

@FunctionalInterface
interface WSFunctionHandler {
    
    public void handle(Long fromUser, TextMessage textMessage);
}

@Component
public class webSocketHandler extends TextWebSocketHandler {

    // having a websocket connections means the user is online
    private HashMap<String, WebSocketSession> connections = new HashMap<String, WebSocketSession>();

    // because number of events will increase in the near future this way we will avoid the long-ugly if or switch statements
    private final Map<String, WSFunctionHandler> handlers = Map.of(WSEvent.CHAT.name(), new webSocketHandler()::HandleChatEvent);

    @Autowired
    private UserService userService;

    private void HandleChatEvent(Long fromUser, TextMessage textMessage){ }

}
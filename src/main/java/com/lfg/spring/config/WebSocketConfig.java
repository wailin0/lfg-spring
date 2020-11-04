package com.lfg.spring.config;

import java.util.Map;

import com.lfg.spring.component.webSocketHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(new webSocketHandler(), "/userid/{id}").addInterceptors(authInterceptor());
    }

    // to add userId attribute in the ws session
    @Bean
    private HandshakeInterceptor authInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, 
                  WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                
                String path = request.getURI().getPath();
    
                // TODO: verify if current authenticated user is equal to this id and add is Authenticated attribute in the map
                attributes.put("userId", path.substring(path.indexOf("/") + 1));

                return true;
            }

			@Override
			public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Exception exception) { }

        };
    }
}
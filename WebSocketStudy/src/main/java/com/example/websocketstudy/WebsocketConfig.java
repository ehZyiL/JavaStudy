package com.example.websocketstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
    @Autowired
    private WebsocketHandler websocketHandler;
    @Autowired
    private WebsocketInterceptor websocketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(websocketHandler, "/ws").setAllowedOrigins("*").addInterceptors(websocketInterceptor);
    }
}
package com.autolink.webSocketDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by shileichao on 2018/5/15
 * 第一个addHandler是对正常连接的配置，第二个是如果浏览器不支持websocket，使用socketjs模拟websocket的连接。
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatMessageHandler(), "/webSocketServer").addInterceptors(new ChatHandshakeInterceptor());
        registry.addHandler(chatMessageHandler(), "/sockjs/webSocketServer").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();
    }
    @Bean
    public TextWebSocketHandler chatMessageHandler(){
        return new ChatMessageHandler();
    }

   @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

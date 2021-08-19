package com.test.cbback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("http://localhost:4200");
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic", "/registry");
    }
}

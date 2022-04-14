package com.cuppa.cuppa.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {
    
    @Value("${infra.rabbitmq.host}")
    private String RABBITMQ_HOST;
    
    @Value("${infra.rabbitmq.username}")
    private String RABBITMQ_USERNAME;
    
    @Value("${infra.rabbitmq.password}")
    private String RABBITMQ_PASSWORD;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/v1/stomp/chat") // handshake 담당 url
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        
        registry.setPathMatcher(new AntPathMatcher("."));
        registry.setApplicationDestinationPrefixes("/app"); // send 요청 처리
//        registry.enableSimpleBroker("/topic"); // 이 경로를 subscribe하는 client에게 메시지를 전달
        registry.enableStompBrokerRelay( "/queue", "/topic", "/exchange", "/amq/queue")
                .setRelayHost(RABBITMQ_HOST)
                .setRelayPort(61613)
                .setClientLogin(RABBITMQ_USERNAME)
                .setClientPasscode(RABBITMQ_PASSWORD)
                .setSystemLogin(RABBITMQ_USERNAME)
                .setSystemPasscode(RABBITMQ_PASSWORD);
    }
}

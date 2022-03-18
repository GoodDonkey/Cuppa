package com.cuppa.cuppa.main.controller;

import com.cuppa.cuppa.main.domain.MessageDTO;
import com.cuppa.cuppa.main.domain.MessageMapper;
import com.cuppa.cuppa.messaging.event.MessageSaveEvent;
import com.cuppa.cuppa.messaging.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompRabbitController {
    
    private final RabbitTemplate rabbitTemplate;
    private final static String CHAT_EXCHANGE_NAME = "chat.exchange";
    private final static String CHAT_QUEUE_NAME = "chat.queue";
    
    private final ApplicationEventPublisher publisher;
    private final MessageMapper messageMapper;
    
    
    @MessageMapping("chat.{to}")
    public void send(@DestinationVariable Long to, Message message){
        log.debug("to={}", to);
        publisher.publishEvent(new MessageSaveEvent(message));
        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME,"to." + to, messageMapper.map(message));
    }
    
    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void receive(MessageDTO message){
        // Todo: 메시지 받을 때 어떤 로직을?
        System.out.println("received : " + message.getMessage());
    }
}

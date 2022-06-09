package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.adapter.in.web.dto.MessageRequestDTO;
import com.cuppa.cuppa.adapter.in.web.dto.MessageRequestToMessageResponse;
import com.cuppa.cuppa.adapter.in.web.dto.MessageResponseDTO;
import com.cuppa.cuppa.adapter.in.web.dto.MessageMapper;
import com.cuppa.cuppa.adapter.out.persistence.MessageSaveEvent;
import com.cuppa.cuppa.domain.Message;
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
    private final MessageRequestToMessageResponse messageRequestToMessageResponse;
    
    
    @MessageMapping("chat.{to}")
    public void send(@DestinationVariable Long to, MessageRequestDTO messageRequestDTO){
        log.debug("to={}", to);
        log.debug("messageRequestDTO={}", messageRequestDTO);
        publisher.publishEvent(new MessageSaveEvent(messageRequestDTO));
        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME,"to." + to, messageRequestToMessageResponse.map(messageRequestDTO));
    }
    
    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void receive(MessageResponseDTO message){
        // Todo: 메시지 받을 때 어떤 로직을?
        System.out.println("received : " + message.getMessage());
    }
}

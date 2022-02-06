package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.app.messaging.event.MessageEvent;
import com.cuppa.cuppa.app.messaging.model.MessageModel;
import com.cuppa.cuppa.app.messaging.storage.MemberRepository;
import com.cuppa.cuppa.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class MessageController {

    private final ApplicationEventPublisher publisher;
    private final SimpMessagingTemplate simpMessagingTemplate; // 메시지를 특정 Broker로 전달한다.

    public MessageController(SimpMessagingTemplate simpMessagingTemplate, ApplicationEventPublisher publisher) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.publisher = publisher;
    }

    // 대상 유저에게 메시지를 보낸다.
    @MessageMapping("/chat/{to}") // url
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("handling send message: " + message + " to: " + to);
        boolean isExists = MemberRepository.getInstance().contains(to);
        System.out.println("isExists = " + isExists);
        if (isExists) {
            Member member = MemberRepository.getInstance().getMember(to);
            publisher.publishEvent(new MessageEvent(member, message));
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }
}

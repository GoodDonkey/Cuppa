package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.app.messaging.event.MessageEvent;
import com.cuppa.cuppa.app.messaging.model.Message;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.service.MemberService;
import com.cuppa.cuppa.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class MessageController {

    private final ApplicationEventPublisher publisher;
    private final SimpMessagingTemplate simpMessagingTemplate; // 메시지를 특정 Broker로 전달한다.
    private final MessageService messageService;
    private final MemberService memberService;

    public MessageController(SimpMessagingTemplate simpMessagingTemplate, ApplicationEventPublisher publisher,
                             MessageService messageService, MemberService memberService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.publisher = publisher;
        this.messageService = messageService;
        this.memberService = memberService;
    }

    // 대상 유저에게 메시지를 보낸다.
    @MessageMapping("/chat/{to}") // url
    public void sendMessage(@DestinationVariable String to, Message message) throws Exception {
        System.out.println("handling send message: " + message + " to: " + to);
        Member member = memberService.findMember(to);
        publisher.publishEvent(new MessageEvent(member, message));
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }
    
    @GetMapping("/chat/fetchAllMessages/{loginUser}/{destination}")
    public List<Message> fetchMessages(@PathVariable String loginUser, @PathVariable String destination) {
        List<Message> messages = messageService.fetchAllMessages(loginUser, destination);
        log.debug("messages={}", messages);
        return messages;
    
    }
}

package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.app.messaging.event.MessageSaveEvent;
import com.cuppa.cuppa.app.messaging.model.Message;
import com.cuppa.cuppa.argumentresolver.Login;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.service.MemberService;
import com.cuppa.cuppa.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ApplicationEventPublisher publisher;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final MemberService memberService;
    
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message) throws Exception {
        Member member = memberService.findMember(to);
        log.debug("to={}", to);
        publisher.publishEvent(new MessageSaveEvent(member, message));
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }
    
    @GetMapping("/chat/fetchAllMessages/{destination}")
    public List<Message> fetchMessages(@Login Member member,
                                       @PathVariable String destination) {
        List<Message> messages = messageService.fetchAllMessagesBetween(member.getUsername(), destination);
        log.debug("messages={}", messages);
        return messages;
    
    }
}

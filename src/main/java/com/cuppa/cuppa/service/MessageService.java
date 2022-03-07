package com.cuppa.cuppa.service;

import com.cuppa.cuppa.app.messaging.event.MessageEvent;
import com.cuppa.cuppa.app.messaging.model.Message;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.repository.MemberRepository;
import com.cuppa.cuppa.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    
    @EventListener
    @Transactional
    public void saveMessageEvent(MessageEvent messageEvent) {
        log.debug("messageEvent={}", messageEvent);
        Message message = messageEvent.getMessage();
        
        Message savedMessage = messageRepository.save(message);
        log.debug("savedMessage={}", savedMessage);
    }
    
    public List<Message> fetchAllMessagesBetween(String loginUser, String other) {
        List<Message> messagesReceived = messageRepository.findMessagesBySenderAndDestination(loginUser, other);
        List<Message> messagesSent = messageRepository.findMessagesBySenderAndDestination(other, loginUser);
        log.debug("messagesReceived={}", messagesReceived);
        log.debug("messagesSent={}", messagesSent);
        return Stream.concat(messagesReceived.stream(), messagesSent.stream())
                     .sorted(Comparator.comparing(Message::getId))
                     .collect(Collectors.toList());
    }
}

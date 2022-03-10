package com.cuppa.cuppa.app.messaging.service;

import com.cuppa.cuppa.app.messaging.event.MessageSaveEvent;
import com.cuppa.cuppa.app.messaging.model.Message;
import com.cuppa.cuppa.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void saveMessageEvent(MessageSaveEvent messageSaveEvent) {
        log.debug("messageEvent={}", messageSaveEvent);
        Message message = messageSaveEvent.getMessage();
        
        Message savedMessage = messageRepository.save(message);
        log.debug("savedMessage={}", savedMessage);
    }
    
    public List<Message> fetchAllMessagesBetween(Long loginUserId, Long otherId) {
        List<Message> messagesReceived = messageRepository.findMessagesById(loginUserId);
        List<Message> messagesSent = messageRepository.findMessagesById(otherId);
        log.debug("messagesReceived={}", messagesReceived);
        log.debug("messagesSent={}", messagesSent);
        return Stream.concat(messagesReceived.stream(), messagesSent.stream())
                     .sorted(Comparator.comparing(Message::getId))
                     .collect(Collectors.toList());
    }
    
    public List<Message> fetchAllMessages(String chatRoomId) {
        return messageRepository.findMessagesByDestination(chatRoomId);
    }
}

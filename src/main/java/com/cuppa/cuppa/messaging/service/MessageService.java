package com.cuppa.cuppa.messaging.service;

import com.cuppa.cuppa.messaging.event.MessageSaveEvent;
import com.cuppa.cuppa.messaging.model.Message;
import com.cuppa.cuppa.messaging.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    
    @EventListener
    @Async
    public void saveMessageEvent(MessageSaveEvent messageSaveEvent) {
        Message message = messageSaveEvent.getMessage();
        Message savedMessage = messageRepository.save(message);
        log.debug("savedMessage={}", savedMessage);
    }
    
    public List<Message> fetchAllMessagesBetween(Long loginUserId, Long otherId) {
        List<Message> messagesReceived = messageRepository.findAllBySenderIdAndReceiverId(loginUserId, otherId);
        List<Message> messagesSent = messageRepository.findAllBySenderIdAndReceiverId(otherId, loginUserId);
        log.debug("messagesReceived={}", messagesReceived);
        log.debug("messagesSent={}", messagesSent);
        return Stream.concat(messagesReceived.stream(), messagesSent.stream())
                     .sorted(Comparator.comparing(Message::getId))
                     .collect(Collectors.toList());
    }
}

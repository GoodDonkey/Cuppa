package com.cuppa.cuppa.adapter.out.persistence;

import com.cuppa.cuppa.application.port.MessageFetchPort;
import com.cuppa.cuppa.application.port.MessageSavePort;
import com.cuppa.cuppa.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MessagePersistenceAdapter implements MessageSavePort, MessageFetchPort {
    
    private final MessageRepository messageRepository;
    
    @Override
    public void save(Message message) {
        messageRepository.save(message);
        log.debug("message={}", message);
    }
    
    @Override
    public List<Message> fetchAll(Long userId, Long otherId) {
        List<Message> messagesReceived = messageRepository.findAllBySenderIdAndReceiverId(userId, otherId);
        List<Message> messagesSent = messageRepository.findAllBySenderIdAndReceiverId(otherId, userId);
        log.debug("messagesReceived={}", messagesReceived);
        log.debug("messagesSent={}", messagesSent);
        return Stream.concat(messagesReceived.stream(), messagesSent.stream())
                     .sorted(Comparator.comparing(Message::getId))
                .collect(Collectors.toList());
    }
}

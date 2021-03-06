package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.in.web.dto.*;
import com.cuppa.cuppa.adapter.out.persistence.MessageSaveEvent;
import com.cuppa.cuppa.application.port.in.MessageFetchUseCase;
import com.cuppa.cuppa.application.port.in.MessageSaveUseCase;
import com.cuppa.cuppa.application.port.out.MessageFetchPort;
import com.cuppa.cuppa.application.port.out.MessageSavePort;
import com.cuppa.cuppa.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MessageService implements MessageFetchUseCase, MessageSaveUseCase {
    
    private final MessageSavePort messageSavePort;
    private final MessageFetchPort messageFetchPort;
    private final MessageToMessageResponse messageToMessageResponse;
    private final MessageRequestToMessage messageRequestToMessage;
    
    @Override
    public List<MessageResponseDTO> fetchAll(Long userId, Long otherId) {
        List<Message> messages = messageFetchPort.fetchAll(userId, otherId);
        return messages.stream()
                .map(messageToMessageResponse::map)
                .collect(Collectors.toList());
    }
    
    @EventListener
    @Async
    @Override
    public void save(MessageSaveEvent messageSaveEvent) {
        Message message = messageRequestToMessage.map(messageSaveEvent.getMessage());
        messageSavePort.save(message);
    }
}

package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.in.web.dto.MessageDTO;
import com.cuppa.cuppa.adapter.in.web.dto.MessageMapper;
import com.cuppa.cuppa.adapter.out.persistence.MessageSaveEvent;
import com.cuppa.cuppa.application.port.out.MessageFetchPort;
import com.cuppa.cuppa.application.port.in.MessageFetchUseCase;
import com.cuppa.cuppa.application.port.out.MessageSavePort;
import com.cuppa.cuppa.application.port.in.MessageSaveUseCase;
import com.cuppa.cuppa.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    private final MessageMapper messageMapper;
    
    @Override
    public List<MessageDTO> fetchAll(Long userId, Long otherId) {
        List<Message> messages = messageFetchPort.fetchAll(userId, otherId);
        return messages.stream()
                .map(messageMapper::map)
                .collect(Collectors.toList());
    }
    
    @EventListener
    @Async
    @Override
    public void save(MessageSaveEvent messageSaveEvent) {
        Message message = messageSaveEvent.getMessage();
        messageSavePort.save(message);
    }
    
    
    @RabbitListener(queues = "chat.queue")
    public void receive(MessageDTO message){
        // Todo: 메시지 받을 때 어떤 로직을?
        System.out.println("received : " + message.getMessage());
    }
    
    @RabbitListener(queues = "chat-queue")
    public void receive2(MessageDTO message){
        // Todo: 메시지 받을 때 어떤 로직을?
        System.out.println("received : " + message.getMessage());
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(name = "chat.exchange", type = ExchangeTypes.TOPIC),
                    value = @Queue(name = "another-queue"))
    })
    public void bindingsExample(MessageDTO payload) {
        log.info("Received new message in example-bindings-queue: {}", payload.toString());
    }
}

package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.adapter.in.web.dto.MessageDTO;
import com.cuppa.cuppa.adapter.in.web.dto.MessageMapper;
import com.cuppa.cuppa.adapter.out.persistence.MessageSaveEvent;
import com.cuppa.cuppa.application.service.MessageService;
import com.cuppa.cuppa.common.argumentresolver.Login;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ApplicationEventPublisher publisher;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final MessageMapper messageMapper;
    
    /**
     * SimpMessagingTemplate 을 사용하는 메시지 엔드포인트.
     * @param to : 상대방 id
     * @param message : 메시지 내용
     */
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable Long to, Message message) {
        log.debug("to={}", to);
        publisher.publishEvent(new MessageSaveEvent(message));
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, messageMapper.map(message));
    }
    
    @GetMapping("/messages/{userId}")
    public List<MessageDTO> fetchMessages(@Login Member member, @PathVariable Long userId) {
        List<Message> messages = messageService.fetchAllMessagesBetween(member.getId(), userId);
        log.debug("messages={}", messages);
    
        return messages.stream()
                .map(messageMapper::map)
                .collect(Collectors.toList());
    }
}

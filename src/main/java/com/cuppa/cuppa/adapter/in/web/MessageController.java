package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.adapter.in.web.dto.MessageRequestDTO;
import com.cuppa.cuppa.adapter.in.web.dto.MessageRequestToMessageResponse;
import com.cuppa.cuppa.adapter.in.web.dto.MessageResponseDTO;
import com.cuppa.cuppa.adapter.in.web.dto.MessageMapper;
import com.cuppa.cuppa.adapter.out.persistence.MessageSaveEvent;
import com.cuppa.cuppa.application.port.in.MessageFetchUseCase;
import com.cuppa.cuppa.common.argumentresolver.SecurityLogin;
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

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ApplicationEventPublisher publisher;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageFetchUseCase messageFetchUseCase;
    private final MessageRequestToMessageResponse messageRequestToMessageResponse;
    
    /**
     * SimpMessagingTemplate 을 사용하는 메시지 엔드포인트.
     * @param to : 상대방 id
     * @param messageRequestDTO : 메시지 내용
     */
    @Deprecated
    @MessageMapping("/api/v1/chat/{to}")
    public void sendMessage(@DestinationVariable Long to, MessageRequestDTO messageRequestDTO) {
        log.debug("to={}", to);
        publisher.publishEvent(new MessageSaveEvent(messageRequestDTO));
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, messageRequestToMessageResponse.map(messageRequestDTO));
    }
    
    @GetMapping("/api/v1/messages/{userId}")
    public List<MessageResponseDTO> fetchMessages(@SecurityLogin Member member, @PathVariable Long userId) {
        List<MessageResponseDTO> messages = messageFetchUseCase.fetchAll(member.getId(), userId);
        log.debug("messages={}", messages);
    
        return messages;
    }
}

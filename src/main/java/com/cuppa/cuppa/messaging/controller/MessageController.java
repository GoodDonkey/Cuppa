package com.cuppa.cuppa.messaging.controller;

import com.cuppa.cuppa.messaging.event.MessageSaveEvent;
import com.cuppa.cuppa.messaging.model.Message;
import com.cuppa.cuppa.messaging.model.MessageTransferDTO;
import com.cuppa.cuppa.messaging.service.ChatRoomService;
import com.cuppa.cuppa.login.argumentresolver.Login;
import com.cuppa.cuppa.main.domain.Member;
import com.cuppa.cuppa.main.domain.TransferHelper;
import com.cuppa.cuppa.main.service.MemberService;
import com.cuppa.cuppa.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ApplicationEventPublisher publisher;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final MemberService memberService;
    private final ChatRoomService chatRoomService;
    private final TransferHelper transferHelper;
    
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable Long to, Message message) throws Exception {
        Member member = memberService.findMemberById(to);
        log.debug("to={}", to);
        publisher.publishEvent(new MessageSaveEvent(member, message));
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, transferHelper.getMessageTransferDTO(message));
    }
    
    @GetMapping("/chat/fetchAllMessages/{userId}")
    public List<MessageTransferDTO> fetchMessages(@Login Member member, @PathVariable Long userId) {
        List<Message> messages = messageService.fetchAllMessagesBetween(member.getId(), userId);
        log.debug("messages={}", messages);
    
        return messages.stream()
                .map(transferHelper::getMessageTransferDTO)
                .collect(Collectors.toList());
    }
}

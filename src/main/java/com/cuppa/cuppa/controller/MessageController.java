package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.app.messaging.event.MessageSaveEvent;
import com.cuppa.cuppa.app.messaging.model.Message;
import com.cuppa.cuppa.app.messaging.model.MessageTransferDTO;
import com.cuppa.cuppa.app.messaging.service.ChatRoomService;
import com.cuppa.cuppa.argumentresolver.Login;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.TransferHelper;
import com.cuppa.cuppa.service.MemberService;
import com.cuppa.cuppa.app.messaging.service.MessageService;
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
    
//    @GetMapping("/chat/fetchAllChatRooms")
//    public Set<ChatRoom> fetchChatRooms(@Login Member member) {
//        // member와 연결된 모든 ChatRoom을 가져온다.
//        Set<ChatRoom> myChatRooms = chatRoomService.getMyChatRooms(member);
//        log.debug("myChatRooms={}", myChatRooms);
//        return myChatRooms;
//    }
}

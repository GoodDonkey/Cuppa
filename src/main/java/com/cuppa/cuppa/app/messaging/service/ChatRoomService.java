package com.cuppa.cuppa.app.messaging.service;

import com.cuppa.cuppa.app.messaging.model.ChatRoom;
import com.cuppa.cuppa.app.messaging.model.ChatRoomMember;
import com.cuppa.cuppa.app.messaging.repository.ChatRoomMemberRepository;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    public Set<ChatRoom> getMyChatRooms(Member member) {
        Set<ChatRoomMember> chatRoomMembers = chatRoomMemberRepository.findAllByMember(member);
        log.debug("chatRoomMembers={}", chatRoomMembers);
    
        Set<ChatRoom> chatRooms = chatRoomMembers.stream()
                .map(ChatRoomMember::getChatRoom)
                .collect(Collectors.toSet());
        return chatRooms;
    }
}

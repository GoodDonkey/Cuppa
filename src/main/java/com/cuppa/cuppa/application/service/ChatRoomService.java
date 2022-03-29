package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.out.persistence.ChatRoomMemberRepository;
import com.cuppa.cuppa.adapter.out.persistence.ChatRoomRepository;
import com.cuppa.cuppa.domain.ChatRoom;
import com.cuppa.cuppa.domain.ChatRoomMember;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

package com.cuppa.cuppa;

import com.cuppa.cuppa.adapter.in.web.dto.MemberSignupRequestDTO;
import com.cuppa.cuppa.adapter.out.persistence.ChatRoomMemberRepository;
import com.cuppa.cuppa.adapter.out.persistence.ChatRoomRepository;
import com.cuppa.cuppa.application.port.in.SignupUseCase;
import com.cuppa.cuppa.application.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Profile({"dev", "test"})
@RequiredArgsConstructor
@Component
public class TestDataInit {
    
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomService chatRoomService;
    private final SignupUseCase signupUseCase;
    
    
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        
        MemberSignupRequestDTO memberSignupRequestDTO = new MemberSignupRequestDTO();
        memberSignupRequestDTO.setLoginId("asdf");
        memberSignupRequestDTO.setPassword("asdf");
        memberSignupRequestDTO.setUsername("asdf");
        signupUseCase.signUp(memberSignupRequestDTO);
    
        MemberSignupRequestDTO memberSignupRequestDTO2 = new MemberSignupRequestDTO();
        memberSignupRequestDTO2.setLoginId("qweqwe");
        memberSignupRequestDTO2.setPassword("qweqwe");
        memberSignupRequestDTO2.setUsername("qweqwe");
        signupUseCase.signUp(memberSignupRequestDTO2);
        
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setName("room1");
//        chatRoomRepository.save(chatRoom);
//
//        ChatRoom chatRoom2 = new ChatRoom();
//        chatRoom2.setName("room2");
//        chatRoomRepository.save(chatRoom2);
//
//        Message message = new Message(1L, "hh", member.getUsername(),  chatRoom.getName(), false);
//        Message message2 = new Message(2L, "JJ", member2.getUsername(),  chatRoom2.getName(), false);
//
//
//        ChatRoomMember chatRoomMember = new ChatRoomMember(1L, chatRoom, member);
//        ChatRoomMember chatRoomMember2 = new ChatRoomMember(2L, chatRoom, member2);
//
//        ChatRoomMember chatRoomMember3 = new ChatRoomMember(3L, chatRoom2, member2);
//        ChatRoomMember chatRoomMember4 = new ChatRoomMember(4L, chatRoom2, member3);
//
//        chatRoomMemberRepository.save(chatRoomMember);
//        chatRoomMemberRepository.save(chatRoomMember2);
//        chatRoomMemberRepository.save(chatRoomMember3);
//        chatRoomMemberRepository.save(chatRoomMember4);
//
//        Set<ChatRoom> myChatRooms = chatRoomService.getMyChatRooms(member);
//        log.info("myChatRooms={}", myChatRooms);
//        System.out.println("myChatRooms = " + myChatRooms);
    }
}
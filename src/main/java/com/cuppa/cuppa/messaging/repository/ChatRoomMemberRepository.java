package com.cuppa.cuppa.messaging.repository;

import com.cuppa.cuppa.messaging.model.ChatRoomMember;
import com.cuppa.cuppa.main.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, String> {
    Set<ChatRoomMember> findAllByMember(Member member);
}

package com.cuppa.cuppa.app.messaging.repository;

import com.cuppa.cuppa.app.messaging.model.ChatRoom;
import com.cuppa.cuppa.app.messaging.model.ChatRoomMember;
import com.cuppa.cuppa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, String> {
    Set<ChatRoomMember> findAllByMember(Member member);
}

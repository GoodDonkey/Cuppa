package com.cuppa.cuppa.adapter.out.persistence;

import com.cuppa.cuppa.domain.ChatRoomMember;
import com.cuppa.cuppa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, String> {
    Set<ChatRoomMember> findAllByMember(Member member);
}

package com.cuppa.cuppa.adapter.out.persistence;

import com.cuppa.cuppa.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    
//    Set<ChatRoom> findAllByChatRoomMembers(Set<ChatRoomMember> chatRoomMembers);
}

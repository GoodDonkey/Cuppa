package com.cuppa.cuppa.messaging.repository;

import com.cuppa.cuppa.messaging.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    
//    Set<ChatRoom> findAllByChatRoomMembers(Set<ChatRoomMember> chatRoomMembers);
}

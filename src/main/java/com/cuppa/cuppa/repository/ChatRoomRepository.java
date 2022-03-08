package com.cuppa.cuppa.repository;

import com.cuppa.cuppa.app.messaging.model.ChatRoom;
import com.cuppa.cuppa.app.messaging.model.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    
//    Set<ChatRoom> findAllByChatRoomMembers(Set<ChatRoomMember> chatRoomMembers);
}

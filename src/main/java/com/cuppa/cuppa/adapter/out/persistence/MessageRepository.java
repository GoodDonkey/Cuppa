package com.cuppa.cuppa.adapter.out.persistence;

import com.cuppa.cuppa.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findAllBySenderIdAndReceiverId(Long senderId, Long receiverId);
    
}

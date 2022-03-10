package com.cuppa.cuppa.repository;

import com.cuppa.cuppa.app.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findMessagesBySenderAndDestination(String sender, String destination);
    List<Message> findMessagesById(Long id);
    
    List<Message> findMessagesByDestination(String destination);
    
}

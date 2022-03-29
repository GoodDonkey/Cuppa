package com.cuppa.cuppa.application.port;

import com.cuppa.cuppa.domain.Message;

import java.util.List;

public interface MessageFetchPort {
    
    List<Message> fetchAll(Long userId, Long otherId);
}

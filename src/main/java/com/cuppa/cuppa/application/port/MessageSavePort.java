package com.cuppa.cuppa.application.port;

import com.cuppa.cuppa.domain.Message;

public interface MessageSavePort {
    
    void save(Message message);
}

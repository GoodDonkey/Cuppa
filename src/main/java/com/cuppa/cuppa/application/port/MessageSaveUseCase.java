package com.cuppa.cuppa.application.port;

import com.cuppa.cuppa.adapter.out.persistence.MessageSaveEvent;

public interface MessageSaveUseCase {
    
    void save(MessageSaveEvent messageSaveEvent);
}

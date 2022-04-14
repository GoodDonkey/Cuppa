package com.cuppa.cuppa.application.port.in;

import com.cuppa.cuppa.adapter.out.persistence.MessageSaveEvent;

public interface MessageSaveUseCase {
    
    void save(MessageSaveEvent messageSaveEvent);
}

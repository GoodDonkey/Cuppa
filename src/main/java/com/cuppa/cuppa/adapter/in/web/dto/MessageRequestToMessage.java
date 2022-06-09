package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.domain.Message;

public interface MessageRequestToMessage {
    
    Message map(MessageRequestDTO messageRequestDTO);
}

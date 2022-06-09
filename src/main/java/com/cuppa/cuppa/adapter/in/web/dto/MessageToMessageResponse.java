package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.domain.Message;

public interface MessageToMessageResponse {
    
    MessageResponseDTO map(Message message);
}

package com.cuppa.cuppa.application.port.in;

import com.cuppa.cuppa.adapter.in.web.dto.MessageResponseDTO;

import java.util.List;

public interface MessageFetchUseCase {
    
    List<MessageResponseDTO> fetchAll(Long UserId, Long otherId);
}

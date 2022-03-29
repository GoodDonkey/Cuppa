package com.cuppa.cuppa.application.port.in;

import com.cuppa.cuppa.adapter.in.web.dto.MessageDTO;

import java.util.List;

public interface MessageFetchUseCase {
    
    List<MessageDTO> fetchAll(Long UserId, Long otherId);
}

package com.cuppa.cuppa.adapter.out.persistence;

import com.cuppa.cuppa.adapter.in.web.dto.MessageRequestDTO;
import com.cuppa.cuppa.domain.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MessageSaveEvent {
    private final MessageRequestDTO message;
}

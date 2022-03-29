package com.cuppa.cuppa.adapter.out.persistence;

import com.cuppa.cuppa.domain.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MessageSaveEvent {
    private final Message message;
}

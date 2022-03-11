package com.cuppa.cuppa.messaging.event;

import com.cuppa.cuppa.messaging.model.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MessageSaveEvent {
    private final Message message;
}

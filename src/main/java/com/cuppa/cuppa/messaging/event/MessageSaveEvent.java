package com.cuppa.cuppa.messaging.event;

import com.cuppa.cuppa.messaging.model.Message;
import com.cuppa.cuppa.main.domain.Member;

public class MessageSaveEvent {

    private final Member member;
    private final Message message;

    public MessageSaveEvent(Member member, Message message) {
        this.member = member;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public Member getMember() {
        return member;
    }
}

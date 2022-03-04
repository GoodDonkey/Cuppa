package com.cuppa.cuppa.app.messaging.event;

import com.cuppa.cuppa.app.messaging.model.Message;
import com.cuppa.cuppa.domain.Member;

public class MessageEvent {

    private final Member member;
    private final Message message;

    public MessageEvent(Member member, Message message) {
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

package com.cuppa.cuppa.app.messaging.event;

import com.cuppa.cuppa.app.messaging.model.MessageModel;
import com.cuppa.cuppa.domain.Member;

public class MessageEvent {

    private final Member member;
    private final MessageModel messageModel;

    public MessageEvent(Member member, MessageModel messageModel) {
        this.member = member;
        this.messageModel = messageModel;
    }

    public MessageModel getMessageModel() {
        return messageModel;
    }

    public Member getMember() {
        return member;
    }
}

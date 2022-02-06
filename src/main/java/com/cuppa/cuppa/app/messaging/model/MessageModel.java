package com.cuppa.cuppa.app.messaging.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
public class MessageModel {

    private String message;
    private String fromLogin;
    private String to;

    @Override
    public String toString() {
        String theString = "MessageModel{" + "message='" + message + '\'' + ", fromLogin='" + fromLogin + '\'' + ", to='" + to + '\'' + '}';
        String replaced = theString.replace("\n", "\\n");
        return replaced;
    }
}

package com.cuppa.cuppa.app.messaging.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @Column
    private String sender;

    @Column
    private String destination;

    @Override
    public String toString() {
        String theString =
                "Message{" + "message='" + message + '\'' + ", sender='" + sender + '\'' + ", destination='" + destination + '\'' + '}';
        String replaced = theString.replace("\n", "\\n");
        return replaced;
    }
}

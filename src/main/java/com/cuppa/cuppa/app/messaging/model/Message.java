package com.cuppa.cuppa.app.messaging.model;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
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
    
    @Column
    private boolean checked;
}

package com.cuppa.cuppa.messaging.model;

import com.cuppa.cuppa.main.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @Column
    private Long senderId;

    @Column
    private Long receiverId;
    
    @Column
    private boolean checked;
}

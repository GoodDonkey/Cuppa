package com.cuppa.cuppa.app.messaging.model;

import com.cuppa.cuppa.domain.BaseEntity;
import com.cuppa.cuppa.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomMember extends BaseEntity {
    
    @Id
    @Getter
    @Column(name = "id", nullable = false)
    private Long id;
    
    @JoinColumn
    @ManyToOne
    @Getter
    private ChatRoom chatRoom;
    
    @JoinColumn
    @ManyToOne
    @Getter
    private Member member;
}

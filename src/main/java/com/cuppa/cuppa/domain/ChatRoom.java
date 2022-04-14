package com.cuppa.cuppa.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatRoom extends BaseEntity {
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @Setter
    private String name;
    
    @OneToMany(mappedBy = "chatRoom")
    @ToString.Exclude
    private Set<ChatRoomMember> ChatRoomMembers;
    
    public void setChatRoomMembers(Set<ChatRoomMember> chatRoomMembers) {
        ChatRoomMembers = chatRoomMembers;
    }
}

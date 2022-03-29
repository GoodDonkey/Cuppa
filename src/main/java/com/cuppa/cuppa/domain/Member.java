package com.cuppa.cuppa.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member extends LoginEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Column(name="member_id")
    private Long id;
    
    @Getter
    @Column(unique = true, nullable = false)
    private String username;
    
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private Set<ChatRoomMember> chatRoomMembers;
}


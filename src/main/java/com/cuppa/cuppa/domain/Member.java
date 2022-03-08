package com.cuppa.cuppa.domain;

import com.cuppa.cuppa.app.messaging.model.ChatRoomMember;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Column(name="member_id")
    private Long id;
    
    @Getter
    @Column(unique = true, nullable = false)
    private String loginId;
    
    @Getter
    @Column(unique = true, nullable = false)
    private String username;
    
    @Getter
    @Column(nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private Set<ChatRoomMember> chatRoomMembers;
}


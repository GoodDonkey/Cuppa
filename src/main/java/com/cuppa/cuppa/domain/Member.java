package com.cuppa.cuppa.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
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
    
    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private Set<ChatRoomMember> chatRoomMembers;
}


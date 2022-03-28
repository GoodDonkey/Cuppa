package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.login.domain.LoginEntity;
import com.cuppa.cuppa.main.dto.MemberDTO;
import com.cuppa.cuppa.messaging.model.ChatRoomMember;
import com.cuppa.cuppa.utils.dto.MappableEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member extends LoginEntity implements MappableEntity<MemberDTO> {
    
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
    
    @Override
    public MemberDTO toSimpleDTO() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setUsername(username);
        return memberDTO;
    }
}


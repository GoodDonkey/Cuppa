package com.cuppa.cuppa.main.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO implements TransferObject{
    
    private Long id;
    private String username;
    
    public MemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}

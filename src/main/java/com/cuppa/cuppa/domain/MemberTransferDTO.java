package com.cuppa.cuppa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberTransferDTO {
    
    private Long id;
    private String username;
    
    public MemberTransferDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}

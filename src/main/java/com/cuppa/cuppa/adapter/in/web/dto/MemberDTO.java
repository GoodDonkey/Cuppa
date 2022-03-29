package com.cuppa.cuppa.adapter.in.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO implements TransferObject {
    
    private Long id;
    private String username;
}

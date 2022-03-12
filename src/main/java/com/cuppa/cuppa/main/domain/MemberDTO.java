package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.utils.dto.TransferObject;
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

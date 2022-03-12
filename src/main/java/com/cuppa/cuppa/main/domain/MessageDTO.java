package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.utils.dto.TransferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageDTO implements TransferObject {
    
    private Long id;
    private String message;
    private MemberDTO sender;
    private MemberDTO receiver;
    private boolean checked;
}

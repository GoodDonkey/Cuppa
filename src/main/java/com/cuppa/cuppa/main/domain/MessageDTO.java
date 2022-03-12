package com.cuppa.cuppa.main.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageDTO implements TransferObject{
    
    private Long id;
    private String message;
    private MemberDTO sender;
    private MemberDTO receiver;
    private boolean checked;
}

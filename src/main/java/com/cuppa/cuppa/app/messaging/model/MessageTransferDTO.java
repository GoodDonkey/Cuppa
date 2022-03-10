package com.cuppa.cuppa.app.messaging.model;

import com.cuppa.cuppa.domain.MemberTransferDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageTransferDTO {
    
    private Long id;
    private String message;
    private MemberTransferDTO sender;
    private MemberTransferDTO receiver;
    private boolean checked;
}

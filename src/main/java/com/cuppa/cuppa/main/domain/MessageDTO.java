package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.utils.dto.TransferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageDTO implements TransferObject {
    
    private Long id;
    private String message;
    private MemberDTO sender;
    private MemberDTO receiver;
    private LocalDateTime createdAt;
    private boolean checked;
}

package com.cuppa.cuppa.adapter.in.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageResponseDTO{
    
    private Long id;
    private String message;
    private MemberDTO sender;
    private MemberDTO receiver;
    private LocalDateTime createdAt;
    private boolean checked;
}

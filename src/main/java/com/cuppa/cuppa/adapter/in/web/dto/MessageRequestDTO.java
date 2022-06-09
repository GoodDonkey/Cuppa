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
public class MessageRequestDTO {
    
    private String message;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime createdAt;
    private boolean checked;
}

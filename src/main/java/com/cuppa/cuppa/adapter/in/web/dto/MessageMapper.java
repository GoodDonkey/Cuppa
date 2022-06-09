package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.application.port.out.MemberFindByIdPort;
import com.cuppa.cuppa.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class MessageMapper implements MessageRequestToMessage {
    
    private final MemberFindByIdPort memberFindByIdPort;
    
    @Override
    public Message map(MessageRequestDTO messageRequestDTO) {
        return Message.builder()
                      .message(messageRequestDTO.getMessage())
                      .receiver(memberFindByIdPort.findById(messageRequestDTO.getReceiverId()))
                      .sender(memberFindByIdPort.findById(messageRequestDTO.getSenderId()))
                      .checked(messageRequestDTO.isChecked())
                      .build();
    }
}

package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.messaging.model.Message;
import com.cuppa.cuppa.utils.dto.DTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class MessageMapper implements DTOMapper<MessageDTO, Message> {
    
    private final MemberMapper memberMapper;
    
    @Override
    public MessageDTO map(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setSender(memberMapper.map(message.getSenderId()));
        messageDTO.setReceiver(memberMapper.map(message.getReceiverId()));
        messageDTO.setCreatedAt(message.getCreatedAt());
        messageDTO.setChecked(message.isChecked());
        log.debug("messageTransferDTO={}", messageDTO);
        return messageDTO;
    }
}

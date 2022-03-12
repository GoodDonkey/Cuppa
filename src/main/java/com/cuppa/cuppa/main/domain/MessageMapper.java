package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.messaging.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class MessageMapper implements DTOMapper<MessageDTO, Message> {
    
    private final MemberMapper memberMapper;
    
    @Override
    public MessageDTO toDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setSender(memberMapper.toDTOById(message.getSenderId()));
        messageDTO.setReceiver(memberMapper.toDTOById(message.getReceiverId()));
        messageDTO.setChecked(message.isChecked());
        log.debug("messageTransferDTO={}", messageDTO);
        return messageDTO;
    }
}

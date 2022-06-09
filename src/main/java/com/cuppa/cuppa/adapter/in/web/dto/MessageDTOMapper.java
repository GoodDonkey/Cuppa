package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.application.port.out.MemberFindByIdPort;
import com.cuppa.cuppa.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class MessageDTOMapper implements MessageRequestToMessageResponse, MessageToMessageResponse {
    
    private final MemberFindByIdPort memberFindByIdPort;
    private final MemberMapper memberMapper;
    
    @Override
    public MessageResponseDTO map(MessageRequestDTO messageRequestDTO) {
        // Todo: 회원정보를 조회해서 MessageResponseDTO 를 만든다.
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        
        messageResponseDTO.setMessage(messageRequestDTO.getMessage());
        messageResponseDTO.setSender(memberMapper.map(memberFindByIdPort.findById(messageRequestDTO.getSenderId())));
        messageResponseDTO.setReceiver(memberMapper.map(memberFindByIdPort.findById(messageRequestDTO.getReceiverId())));
        messageResponseDTO.setCreatedAt(messageResponseDTO.getCreatedAt());
        messageResponseDTO.setChecked(messageRequestDTO.isChecked());
        return messageResponseDTO;
    }
    
    @Override
    public MessageResponseDTO map(Message message) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setId(message.getId());
        messageResponseDTO.setMessage(message.getMessage());
        messageResponseDTO.setSender(memberMapper.map(message.getSender()));
        messageResponseDTO.setReceiver(memberMapper.map(message.getReceiver()));
        messageResponseDTO.setCreatedAt(message.getCreatedAt());
        messageResponseDTO.setChecked(message.isChecked());
        log.debug("messageTransferDTO={}", messageResponseDTO);
        return messageResponseDTO;
    }
}

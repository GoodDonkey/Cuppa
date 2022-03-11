package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.messaging.model.Message;
import com.cuppa.cuppa.messaging.model.MessageTransferDTO;
import com.cuppa.cuppa.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TransferHelper {
    
    private final MemberRepository memberRepository;
    
    public MemberTransferDTO getMemberTransferDTOById(Long id) {
        log.debug("id={}", id);
        return new MemberTransferDTO(memberRepository.findById(id).get());
    }
    
    public MessageTransferDTO getMessageTransferDTO(Message message) {
        MessageTransferDTO messageTransferDTO = new MessageTransferDTO();
        messageTransferDTO.setId(message.getId());
        messageTransferDTO.setMessage(message.getMessage());
        messageTransferDTO.setSender(getMemberTransferDTOById(message.getSenderId()));
        messageTransferDTO.setReceiver(getMemberTransferDTOById(message.getReceiverId()));
        messageTransferDTO.setChecked(message.isChecked());
        log.debug("messageTransferDTO={}", messageTransferDTO);
        return messageTransferDTO;
    }
}

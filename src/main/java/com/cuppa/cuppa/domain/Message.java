package com.cuppa.cuppa.domain;

import com.cuppa.cuppa.adapter.in.web.dto.MappableEntity;
import com.cuppa.cuppa.adapter.in.web.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message extends TimeEntity implements MappableEntity<MessageDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @Column
    private Long senderId;

    @Column
    private Long receiverId;
    
    @Column
    private boolean checked;
    
    @Override
    public MessageDTO toSimpleDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(id);
        messageDTO.setMessage(message);
        messageDTO.setChecked(checked);
        return messageDTO;
    }
}

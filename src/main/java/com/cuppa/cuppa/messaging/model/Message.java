package com.cuppa.cuppa.messaging.model;

import com.cuppa.cuppa.main.domain.BaseEntity;
import com.cuppa.cuppa.utils.dto.MappableEntity;
import com.cuppa.cuppa.main.domain.MessageDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message extends BaseEntity implements MappableEntity<MessageDTO> {

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

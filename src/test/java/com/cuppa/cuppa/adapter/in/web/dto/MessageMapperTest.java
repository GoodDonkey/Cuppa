package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: 스프링과 분리하여 테스트를 만들었는데, 스프링 위에서 돌리는 테스트도 필요할까?
class MessageMapperTest {
    
    private final MessageMapper messageMapper = new MessageMapper(new MemberMapper());
    
    @Test
    void mapTest() {
        
        // given
        String m = "hahaha";
        Member sender = Member.builder().username("senderName").id(1L).build();
        Member receiver = Member.builder().username("receiverName").id(2L).build();
        
        Message message = Message.builder().message(m).receiver(receiver).sender(sender).build();
        
        // when
        MessageDTO dto = messageMapper.map(message);
        
        //then
        assertThat(dto.getMessage()).isEqualTo(message.getMessage());
    }
    
    
    @Test
    @DisplayName("message dto에 member 엔티티 자체가 들어가면 안된다. 패스워드 포함하면 안됨.")
    void mapTest2() {
        // given
        String m = "hahaha";
        String password = "pass!";
        Member sender = Member.builder().username("senderName").id(1L).password(password).build();
        Member receiver = Member.builder().username("receiverName").id(2L).password(password).build();
        
        Message message = Message.builder().message(m).receiver(receiver).sender(sender).build();
        
        // when
        MessageDTO dto = messageMapper.map(message);
        
        //then
        assertThat(dto.getSender()).isInstanceOf(MemberDTO.class);
        assertThat(dto.getReceiver()).isInstanceOf(MemberDTO.class);
        
        assertThat(dto.getSender()).isNotInstanceOf(Member.class);
        assertThat(dto.getReceiver()).isNotInstanceOf(Member.class);
    }
}
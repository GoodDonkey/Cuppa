package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.out.persistence.MessagePersistenceAdapter;
import com.cuppa.cuppa.application.port.out.MessageFetchPort;
import com.cuppa.cuppa.application.port.out.MessageSavePort;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.profiles.active:dev")
@Slf4j
public class MessageServiceTest {
    
    @Autowired private ApplicationContext ac;
    @Autowired private MessageSavePort messageSavePort;
    @Autowired private MessageFetchPort messageFetchPort;
    
    @Test
    @DisplayName("MessageSavePort 의 빈 타입이 MessagePersistenceAdapter 인가?")
    void findPortBeanName() {
        MessageSavePort MessageSavePortBean = ac.getBean(MessageSavePort.class);
        MessageFetchPort MessageFetchPortBean = ac.getBean(MessageFetchPort.class);
        log.debug("MessageSavePortBean={}", MessageSavePortBean);
        log.debug("MessageFetchPortBean={}", MessageFetchPortBean);
        
        assertThat(MessageSavePortBean).isInstanceOf(MessagePersistenceAdapter.class);
        assertThat(MessageFetchPortBean).isInstanceOf(MessagePersistenceAdapter.class);
    }
    
    @Test
    @DisplayName("MessageSavePort, MessageFetchPort 에 MessagePersistenceAdapter 가 주입되었는가?")
    void checkPortBeanType() {
        assertThat(messageSavePort).isInstanceOf(MessagePersistenceAdapter.class);
        assertThat(messageFetchPort).isInstanceOf(MessagePersistenceAdapter.class);
    }
}
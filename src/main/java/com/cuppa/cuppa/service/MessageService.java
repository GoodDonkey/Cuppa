package com.cuppa.cuppa.service;

import com.cuppa.cuppa.app.messaging.event.MessageEvent;
import com.cuppa.cuppa.app.messaging.model.Message;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.repository.MemberRepository;
import com.cuppa.cuppa.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public MessageService(MessageRepository messageRepository, MemberRepository memberRepository) {
        this.messageRepository = messageRepository;
        this.memberRepository = memberRepository;
    }

    public void saveMessage(String to, Message message) throws Exception {
        boolean isExists = memberRepository.existsByUsername(to);
        System.out.println("isExists = " + isExists);
        if (isExists) {
            Member member = memberRepository.findByUsername(to).get();
            messageRepository.save(message);
        }
        if (!isExists) {
            throw new Exception("Member 가 존재하지 않습니다." + to);
        }
    }

    @EventListener
    @Transactional
    public void addMemberMessage(MessageEvent messageEvent) {
        log.info("Event Listened");
        log.debug("messageEvent={}", messageEvent);

        Message message = messageEvent.getMessage();

        Message savedMessage = messageRepository.save(message);
        log.debug("savedMessage={}", savedMessage);
    }
    
    public List<Message> fetchAllMessages(String loginUser, String other) {
        List<Message> messagesReceived = messageRepository.findMessagesBySenderAndDestination(loginUser, other);
        List<Message> messagesSent = messageRepository.findMessagesBySenderAndDestination(other, loginUser);
        log.debug("messagesReceived={}", messagesReceived);
        log.debug("messagesSent={}", messagesSent);
        messagesReceived.addAll(messagesSent);
        messagesReceived.sort(Comparator.comparing(Message::getId));
        return messagesReceived;
    }
}

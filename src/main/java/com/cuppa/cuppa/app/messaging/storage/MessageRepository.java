//package com.cuppa.cuppa.app.messaging.storage;
//
//import com.cuppa.cuppa.app.messaging.model.Message;
//import com.cuppa.cuppa.domain.Member;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//@Component
//public class MessageRepository {
//
//    private static MessageRepository instance;
//
//    @Getter
//    private Map<Member, Message> UserMessageStorage;
//
//    private MessageRepository() {
//        UserMessageStorage = new ConcurrentHashMap<>();
//    }
//
//    public static synchronized MessageRepository getInstance() {
//        if (instance == null) {
//            instance = new MessageRepository();
//        }
//        return instance;
//    }
//
////    @EventListener
////    public void addMemberMessage(MessageEvent messageEvent) {
////        System.out.println("Event listened");
////        log.debug("messageEvent={}", messageEvent);
////
////        MessageModel message = messageEvent.getMessageModel();
////        Member member = messageEvent.getMember();
////
////        UserMessageStorage.putIfAbsent(member, message); // 없으면 넣을 것.
////        log.info("UserMessageStorage={}", UserMessageStorage);
////    }
//}

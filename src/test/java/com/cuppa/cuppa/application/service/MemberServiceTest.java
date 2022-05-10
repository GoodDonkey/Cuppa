package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.in.web.dto.MemberMapper;
import com.cuppa.cuppa.adapter.out.persistence.MemberPersistenceAdapter;
import com.cuppa.cuppa.adapter.out.persistence.MemberRepository;
import com.cuppa.cuppa.application.port.out.MemberFetchPort;
import com.cuppa.cuppa.application.port.out.MemberSavePort;
import com.cuppa.cuppa.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active:test")
class MemberServiceTest {
    
    @Autowired private MemberFetchPort memberFetchPort;
    @Autowired private MemberSavePort memberSavePort;
    
    @Test
    void findAllMembersExceptMe() {
    }
    
    @Test
    void save() {
    }
    
    @Test
    @DisplayName("savePort 기능 테스트")
    void memberSaveTest() {
    
        //given
        Member member = new Member();
        String username = "forTest3";
        String loginId = "forTestLoginId3";
        String password = "password3";
        
        member.setUsername(username);
        member.setLoginId(loginId);
        member.setPassword(password);
    
        // when
        Member savedByPort = memberSavePort.save(member);
        
    
        // then
        assertThat(savedByPort.getUsername()).isEqualTo(username);
        assertThat(savedByPort.getPassword()).isEqualTo(password);
        assertThat(savedByPort.getLoginId()).isEqualTo(loginId);
    }
    
    @Test
    @DisplayName("멤버 정보를 가져오는 Port의 기능이 잘 작동하는지 테스트한다.")
    void memberFetchTest() {
        //given
        Member me = new Member();
        me.setUsername("forTest");
        me.setLoginId("forTestLoginId");
        me.setPassword("password");
        memberSavePort.save(me);
    
        Member member = new Member();
        member.setUsername("forTest2");
        member.setLoginId("forTestLoginId2");
        member.setPassword("password2");
        memberSavePort.save(member);
        
        // when
        List<Member> membersExceptMe = memberFetchPort.fetchAllExceptMe(me);
    
        // then
        assertThat(membersExceptMe).isNotNull();
        assertThat(me).isNotIn(membersExceptMe);
    }
}
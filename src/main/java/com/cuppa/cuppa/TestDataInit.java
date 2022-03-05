package com.cuppa.cuppa;

import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class TestDataInit {
    
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
    
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setUsername("testUser");
    
        Member member2 = new Member();
        member2.setLoginId("asd");
        member2.setPassword("asd");
        member2.setUsername("newUser");
    
        memberRepository.save(member);
        memberRepository.save(member2);
        
    }

}
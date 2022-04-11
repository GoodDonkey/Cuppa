package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.in.web.dto.MemberSignupRequestDTO;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.profiles.active:test")
@Slf4j
class AuthServiceTest {
    
    @Autowired private PasswordEncoder passwordEncoder;
    static MemberSignupRequestDTO memberDTO;
    static Member member;
    
    @BeforeEach
    void before() {
        memberDTO = new MemberSignupRequestDTO("wgwg1", "wgwg2", "wgwg3", Role.ROLE_MEMBER);
        member = Member.builder()
                       .loginId(memberDTO.getLoginId())
                       .password(passwordEncoder.encode(memberDTO.getPassword()))
                       .username(memberDTO.getUsername())
                       .role(Role.ROLE_MEMBER)
                       .build();
    }
    
    @Test
    @DisplayName("패스워드 들어가는 거 맞나? 로그가 안찍히는데.")
    void setPassword() {
        
        log.debug("member={}", member);
        log.debug("member.getPassword()={}", member.getPassword());
        
        assertThat(member.getPassword()).isNotNull();
    }
    
    @Test
    @DisplayName("비밀번호 이외에 멤버와 DTO가 같은 값이 들어가는가?")
    void sameAsDTO() {

        assertThat(member.getLoginId()).isEqualTo(memberDTO.getLoginId());
        assertThat(member.getRole()).isEqualTo(memberDTO.getRole());
        assertThat(member.getUsername()).isEqualTo(memberDTO.getUsername());
    }
    
    @Test
    @DisplayName("Member의 password 는 DTO 에서의 원본값이면 안된다.")
    void passwordCheck() {

        assertThat(member.getPassword()).isNotEqualTo(memberDTO.getPassword());
    }
    
}
package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.in.web.dto.MemberSignupRequestDTO;
import com.cuppa.cuppa.application.port.out.MemberFindByLoginIdPort;
import com.cuppa.cuppa.application.port.out.MemberSavePort;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active:test")
@Slf4j
class AuthServiceTest {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    @DisplayName("패스워드 들어가는 거 맞나? 로그가 안찍히는데.")
    void signuptest() {
        MemberSignupRequestDTO memberDTO = new MemberSignupRequestDTO("wgwg1",
                                                                      "wgwg2",
                                                                      "wgwg3",
                                                                      Role.ROLE_MEMBER);
        Member member = Member.builder().loginId(memberDTO.getLoginId())
                              .password(passwordEncoder.encode(memberDTO.getPassword()))
                              .username(memberDTO.getUsername())
                              .role(Role.ROLE_MEMBER).build();
        
        log.debug("member={}", member);
        log.debug("member.getPassword()={}", member.getPassword());
        
        assertThat(member.getLoginId()).isEqualTo(memberDTO.getLoginId());
        assertThat(member.getPassword()).isNotEqualTo(memberDTO.getPassword());
        assertThat(member.getPassword()).isNotNull();
        assertThat(member.getRole()).isEqualTo(memberDTO.getRole());
    }
    
}
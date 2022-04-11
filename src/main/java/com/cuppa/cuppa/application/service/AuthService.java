package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.in.web.dto.MemberSignupRequestDTO;
import com.cuppa.cuppa.application.port.in.SignupUseCase;
import com.cuppa.cuppa.application.port.out.MemberFindByLoginIdPort;
import com.cuppa.cuppa.application.port.out.MemberSavePort;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService implements SignupUseCase {
    
    private final MemberFindByLoginIdPort findByLoginIdPort;
    private final MemberSavePort memberSavePort;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public Member signUp(MemberSignupRequestDTO memberDTO) {
        
        log.debug("memberDTO={}", memberDTO);
        
        boolean exists = findByLoginIdPort.findByLoginId(memberDTO.getLoginId()).isPresent();
        
        if (exists) return null;
    
        Member member = Member.builder()
                              .loginId(memberDTO.getLoginId())
                              .password(passwordEncoder.encode(memberDTO.getPassword()))
                              .username(memberDTO.getUsername())
                              .role(Role.ROLE_MEMBER)
                              .build();
        
        log.debug("member={}", member);
        
        memberSavePort.save(member);
    
        return member;
    }
}

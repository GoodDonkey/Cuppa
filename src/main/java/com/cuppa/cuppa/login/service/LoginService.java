package com.cuppa.cuppa.login.service;

import com.cuppa.cuppa.main.domain.Member;
import com.cuppa.cuppa.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    
    public Member login(String loginId, String password) {
        
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}

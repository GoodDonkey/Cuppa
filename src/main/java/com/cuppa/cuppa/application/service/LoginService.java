package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.out.persistence.MemberRepository;
import com.cuppa.cuppa.application.port.ILoginService;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements ILoginService<Member> {
    
    private final MemberRepository memberRepository;
    
    public Member login(String loginId, String password) {
        
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}

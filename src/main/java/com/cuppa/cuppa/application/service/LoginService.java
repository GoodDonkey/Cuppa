package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.application.port.in.LoginUseCase;
import com.cuppa.cuppa.application.port.out.MemberFindByLoginIdPort;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase<Member> {
    
    private final MemberFindByLoginIdPort memberFindByLoginIdPort;
    
    @Override
    public Member login(String loginId, String password) {
        
        return memberFindByLoginIdPort.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
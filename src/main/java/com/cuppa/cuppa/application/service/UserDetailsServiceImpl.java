package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.application.port.out.MemberFindByLoginIdPort;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final MemberFindByLoginIdPort findByLoginIdPort;
    
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> member = findByLoginIdPort.findByLoginId(loginId);
        
        if (member.isEmpty()) throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        log.info("loginId={}", loginId);
        log.info("member={}", member);
        return new SecurityUser(member.get());
    }
}

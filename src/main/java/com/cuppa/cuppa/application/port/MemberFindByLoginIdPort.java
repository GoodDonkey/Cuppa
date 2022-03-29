package com.cuppa.cuppa.application.port;

import com.cuppa.cuppa.domain.Member;

import java.util.Optional;

public interface MemberFindByLoginIdPort {
    
    Optional<Member> findByLoginId(String loginId);
}

package com.cuppa.cuppa.application.port.out;

import com.cuppa.cuppa.domain.Member;

import java.util.Optional;

public interface MemberFindByIdPort {
    
    Optional<Member> findById(Long loginId);
    
}

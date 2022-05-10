package com.cuppa.cuppa.application.port.out;

import com.cuppa.cuppa.domain.Member;

public interface MemberSavePort {
    
    Member save(Member member);
    
}

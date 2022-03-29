package com.cuppa.cuppa.application.port;

import com.cuppa.cuppa.domain.Member;

public interface MemberSavePort {
    
    void save(Member member);
    
}

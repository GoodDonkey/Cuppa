package com.cuppa.cuppa.application.port.in;

import com.cuppa.cuppa.domain.Member;

public interface MemberSaveUseCase {
    
    void save(Member member);
}

package com.cuppa.cuppa.application.port.out;

import com.cuppa.cuppa.domain.Member;

import java.util.List;

public interface MemberFetchPort {
    
    List<Member> fetchAllExceptMe(Member member);
}

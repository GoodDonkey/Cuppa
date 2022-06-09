package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.domain.Member;

public interface MemberToMemberDTO {
    
    MemberDTO map(Member member);
}

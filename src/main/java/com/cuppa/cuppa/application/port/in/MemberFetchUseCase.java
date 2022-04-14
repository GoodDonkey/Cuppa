package com.cuppa.cuppa.application.port.in;

import com.cuppa.cuppa.adapter.in.web.dto.MemberDTO;
import com.cuppa.cuppa.domain.Member;

import java.util.List;

public interface MemberFetchUseCase {
    
    List<MemberDTO> findAllMembersExceptMe(Member member);
}

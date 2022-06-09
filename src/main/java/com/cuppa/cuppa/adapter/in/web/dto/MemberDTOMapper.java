package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberDTOMapper implements MemberToMemberDTO {
    
    @Override
    public MemberDTO map(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setUsername(member.getUsername());
        return memberDTO;
    }
}

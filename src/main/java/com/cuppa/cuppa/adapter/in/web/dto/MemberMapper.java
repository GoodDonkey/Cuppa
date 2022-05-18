package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.application.port.out.MemberFindByIdPort;
import com.cuppa.cuppa.domain.Member;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberMapper implements DTOMapper<MemberDTO, Member> {
    
    @Override
    public MemberDTO map(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setUsername(member.getUsername());
        return memberDTO;
    }
}

package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.application.port.MemberFindByIdPort;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberMapper implements DTOMapper<MemberDTO, Long> {
    
    private final MemberFindByIdPort memberFindByIdPort;
    
    @Override
    public MemberDTO map(Long id) {
        Member member = memberFindByIdPort.findById(id).get();
        return toSimpleDTO(member);
    }
    
    public MemberDTO toSimpleDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setUsername(member.getUsername());
        return memberDTO;
    }
}

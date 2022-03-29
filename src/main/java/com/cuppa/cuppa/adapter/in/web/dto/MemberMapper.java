package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.adapter.out.persistence.MemberRepository;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberMapper implements DTOMapper<MemberDTO, Long> {
    
    private final MemberRepository memberRepository;
    
    @Override
    public MemberDTO map(Long id) {
        Member member = memberRepository.findById(id).get();
        return member.toSimpleDTO();
    }
}

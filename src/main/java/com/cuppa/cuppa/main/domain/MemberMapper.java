package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.main.repository.MemberRepository;
import com.cuppa.cuppa.utils.dto.DTOMapper;
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

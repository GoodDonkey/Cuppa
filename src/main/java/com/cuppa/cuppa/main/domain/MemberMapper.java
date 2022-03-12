package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberMapper implements DTOMapper<MemberDTO, Member>{
    
    private final MemberRepository memberRepository;
    
    @Override
    public MemberDTO toDTO(Member member) {
        log.debug("member={}", member);
        return member.toDTO();
    }
    
    public MemberDTO toDTOById(Long id) {
        return new MemberDTO(memberRepository.findById(id).get());
    }
}

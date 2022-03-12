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
    public MemberDTO translate(Member member) {
        log.debug("member={}", member);
        return new MemberDTO(member);
    }
    
    public MemberDTO translateById(Long id) {
        return new MemberDTO(memberRepository.getById(id));
    }
}

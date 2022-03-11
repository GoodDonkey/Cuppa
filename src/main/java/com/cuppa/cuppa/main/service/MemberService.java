package com.cuppa.cuppa.main.service;

import com.cuppa.cuppa.main.domain.Member;
import com.cuppa.cuppa.main.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    public List<Member> findAllMembersExceptMe(Member member) {
        return memberRepository.findAll()
                .stream()
                .filter(m -> !(m.getId().equals(member.getId())))
                .collect(Collectors.toList());
    }
    
    public Member findMemberById(Long id) {
        Member member = memberRepository.findById(id).get();
        log.debug("member={}", member);
        return member;
    }
}

package com.cuppa.cuppa.service;

import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByUsername(member.getUsername()) // Optional 타입 반환
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public List<String> findAllUsernames() {
        List<String> members = memberRepository.findAll()
                                            .stream()
                                            .map(Member::getUsername)
                                            .collect(Collectors.toList());
        log.debug("members={}", members);
        return members;
    }

    public Member findMember(String username) {
        Member member = memberRepository.findByUsername(username).get();
        log.debug("member={}", member);
        return member;
    }
}

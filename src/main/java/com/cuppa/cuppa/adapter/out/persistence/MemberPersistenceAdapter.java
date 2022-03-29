package com.cuppa.cuppa.adapter.out.persistence;

import com.cuppa.cuppa.application.port.MemberFindByIdPort;
import com.cuppa.cuppa.application.port.MemberFindByLoginIdPort;
import com.cuppa.cuppa.application.port.MemberFetchPort;
import com.cuppa.cuppa.application.port.MemberSavePort;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemberPersistenceAdapter implements MemberFetchPort,
                                                 MemberSavePort,
                                                 MemberFindByLoginIdPort,
                                                 MemberFindByIdPort {
    
    private final MemberRepository memberRepository;
    
    @Override
    public List<Member> fetchAllExceptMe(Member member) {
        List<Member> members = memberRepository.findAll()
                .stream()
                .filter(m -> !(m.getId().equals(member.getId())))
                .collect(Collectors.toList());
        log.debug("members={}", members);
        return members;
    }
    
    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }
    
    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
    
    @Override
    public Optional<Member> findById(Long loginId) {
        return memberRepository.findById(loginId);
    }
}

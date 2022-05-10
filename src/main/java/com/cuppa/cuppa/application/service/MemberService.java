package com.cuppa.cuppa.application.service;

import com.cuppa.cuppa.adapter.in.web.dto.MemberDTO;
import com.cuppa.cuppa.adapter.in.web.dto.MemberMapper;
import com.cuppa.cuppa.application.port.out.MemberFetchPort;
import com.cuppa.cuppa.application.port.in.MemberFetchUseCase;
import com.cuppa.cuppa.application.port.out.MemberSavePort;
import com.cuppa.cuppa.application.port.in.MemberSaveUseCase;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements MemberFetchUseCase, MemberSaveUseCase {
    
    private final MemberFetchPort memberFetchPort;
    private final MemberSavePort memberSavePort;
    private final MemberMapper memberMapper;
    
    @Override
    public List<MemberDTO> findAllMembersExceptMe(Member member) {
        List<Member> members = memberFetchPort.fetchAllExceptMe(member);
        List<MemberDTO> memberDTOs = members.stream()
                                            .map(memberMapper::toSimpleDTO)
                                            .collect(Collectors.toList());
        log.debug("memberDTOs={}", memberDTOs);
        return memberDTOs;
    }
    
    @Override
    public Member save(Member member) {
        return memberSavePort.save(member);
    }
}

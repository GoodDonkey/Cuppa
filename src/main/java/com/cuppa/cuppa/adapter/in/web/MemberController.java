package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.adapter.in.web.dto.MemberDTO;
import com.cuppa.cuppa.application.port.in.MemberFetchUseCase;
import com.cuppa.cuppa.common.argumentresolver.SecurityLogin;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberFetchUseCase memberFetchUseCase;

    @GetMapping("/api/v1/members")
    public List<MemberDTO> fetchAll(@SecurityLogin Member loginMember) {
        return memberFetchUseCase.findAllMembersExceptMe(loginMember);
    }
    
    @GetMapping("/api/v1/members/username")
    public String getUsername(@SecurityLogin Member loginMember) {
        log.info("loginMember={}", loginMember);
        return loginMember.getUsername();
    }
    
    @GetMapping("/api/v1/members/userId")
    public Long getUserId(@SecurityLogin Member loginMember) {
        log.info("loginMember={}", loginMember);
        return loginMember.getId();
    }
}

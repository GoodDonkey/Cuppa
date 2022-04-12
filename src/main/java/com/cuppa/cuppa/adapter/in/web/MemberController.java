package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.adapter.in.web.dto.MemberDTO;
import com.cuppa.cuppa.application.port.in.MemberFetchUseCase;
import com.cuppa.cuppa.application.port.in.MemberSaveUseCase;
import com.cuppa.cuppa.common.argumentresolver.Login;
import com.cuppa.cuppa.common.argumentresolver.SecurityLogin;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberSaveUseCase memberSaveUseCase;
    private final MemberFetchUseCase memberFetchUseCase;

    @GetMapping("/api/v1/members")
    @ResponseBody
    public List<MemberDTO> fetchAll(@SecurityLogin Member member) {
        return memberFetchUseCase.findAllMembersExceptMe(member);
    }
    
    @GetMapping("/api/v1/members/username")
    @ResponseBody
    public String getUsername(@SecurityLogin Member loginMember) {
        log.info("loginMember={}", loginMember);
        return loginMember.getUsername();
    }
    
    @GetMapping("/api/v1/members/userId")
    @ResponseBody
    public Long getUserId(@SecurityLogin Member loginMember) {
        log.info("loginMember={}", loginMember);
        return loginMember.getId();
    }
    
    @GetMapping("/members/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }
    
    @PostMapping("/members/add")
    public String save(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberSaveUseCase.save(member);
        return "redirect:/";
    }
}

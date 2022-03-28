package com.cuppa.cuppa.main.controller;

import com.cuppa.cuppa.login.argumentresolver.Login;
import com.cuppa.cuppa.main.domain.Member;
import com.cuppa.cuppa.main.dto.MemberDTO;
import com.cuppa.cuppa.main.repository.MemberRepository;
import com.cuppa.cuppa.main.service.MemberService;
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
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/members")
    @ResponseBody
    public List<MemberDTO> fetchAll(@Login Member member) {
    
        List<Member> allMembers = memberService.findAllMembersExceptMe(member);
        log.info("allMembers={}", allMembers);
    
        return allMembers.stream()
                .map(Member::toSimpleDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/members/username")
    @ResponseBody
    public String getUsername(@Login Member loginMember) {
        log.info("loginMember={}", loginMember);
        return loginMember.getUsername();
    }
    
    @GetMapping("/members/userId")
    @ResponseBody
    public Long getUserId(@Login Member loginMember) {
        log.info("loginMember={}", loginMember);
        return loginMember.getId();
    }
    
    @GetMapping("/members/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }
    
    @PostMapping("/members/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}

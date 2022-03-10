package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.argumentresolver.Login;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.MemberTransferDTO;
import com.cuppa.cuppa.domain.TransferHelper;
import com.cuppa.cuppa.repository.MemberRepository;
import com.cuppa.cuppa.service.MemberService;
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
    private final TransferHelper transferHelper;

    @GetMapping("/fetchAllUsers")
    @ResponseBody
    public List<MemberTransferDTO> fetchAll(@Login Member member) {
    
        List<Member> allMembers = memberService.findAllMembersExceptMe(member);
        log.info("allMembers={}", allMembers);
    
        return allMembers.stream()
                .map(m -> transferHelper.getMemberTransferDTOById(m.getId()))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/members/getUsername")
    @ResponseBody
    public String getUsername(@Login Member loginMember) {
        log.info("loginMember={}", loginMember);
        return loginMember.getUsername();
    }
    
    @GetMapping("/members/getUserId")
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

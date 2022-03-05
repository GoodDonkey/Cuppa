package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.argumentresolver.Login;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.repository.MemberRepository;
import com.cuppa.cuppa.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    
    
    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/registration/{username}")
    @ResponseBody
    public ResponseEntity<Void> register(@PathVariable String username) {
        System.out.println("handling register user request: " + username);
        memberService.join(new Member(username));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    @ResponseBody
    public List<String> fetchAll() {
        List<String> allUsernames = memberService.findAllUsernames();
        log.info("response={}", allUsernames);
        return allUsernames;
    }
    
    @GetMapping("/members/getUsername")
    @ResponseBody
    public String getUsername(@Login Member loginMember) {
        return loginMember.getUsername();
        
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

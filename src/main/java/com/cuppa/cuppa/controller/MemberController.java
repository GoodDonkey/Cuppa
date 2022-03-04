package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/registration/{username}")
    public ResponseEntity<Void> register(@PathVariable String username) {
        System.out.println("handling register user request: " + username);
        memberService.join(new Member(username));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public List<String> fetchAll() {
        List<String> allUsernames = memberService.findAllUsernames();
        log.info("response={}", allUsernames);
        return allUsernames;
    }
}

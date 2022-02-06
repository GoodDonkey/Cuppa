package com.cuppa.cuppa.controller;

import com.cuppa.cuppa.app.messaging.storage.MemberRepository;
import com.cuppa.cuppa.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@CrossOrigin
public class MemberController {

    @GetMapping("/registration/{username}")
    public ResponseEntity<Void> register(@PathVariable String username) {
        System.out.println("handleing register user request: " + username);
        try {
            MemberRepository.getInstance().setMember(username);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        Set<String> response = MemberRepository.getInstance().getUsernames();
        log.info("response={}", response);
        return response;
    }
}

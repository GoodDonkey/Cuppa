package com.cuppa.cuppa.main.controller;

import com.cuppa.cuppa.login.argumentresolver.Login;
import com.cuppa.cuppa.main.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    
    @GetMapping("/")
    public String home(
            @Login Member loginMember,
            Model model) {
        
        if (loginMember == null) {
            return "home";
        }
        
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
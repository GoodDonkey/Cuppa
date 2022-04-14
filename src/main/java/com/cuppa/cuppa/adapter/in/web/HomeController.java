package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.common.argumentresolver.Login;
import com.cuppa.cuppa.common.argumentresolver.SecurityLogin;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    
    @GetMapping("/")
    public String home(
            @SecurityLogin Member loginMember,
            Model model) {
        
        if (loginMember == null) {
            return "home";
        }
        
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
package com.cuppa.cuppa;

import com.cuppa.cuppa.argumentresolver.Login;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.repository.MemberRepository;
import com.cuppa.cuppa.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;
    
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
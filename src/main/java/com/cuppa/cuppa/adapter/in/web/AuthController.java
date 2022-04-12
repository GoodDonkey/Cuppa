package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.adapter.in.form.LoginForm;
import com.cuppa.cuppa.adapter.in.web.dto.MemberSignupRequestDTO;
import com.cuppa.cuppa.application.port.in.LoginUseCase;
import com.cuppa.cuppa.application.port.in.SignupUseCase;
import com.cuppa.cuppa.common.session.SessionConst;
import com.cuppa.cuppa.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final SignupUseCase signupUseCase;
    
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }
    
    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("member") MemberSignupRequestDTO member) {
        return "members/addMemberForm";
    }
    
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("member") MemberSignupRequestDTO memberDTO,
                         BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
    
        Member member = signupUseCase.signUp(memberDTO);
        
        if (member == null) {
            bindingResult.reject("exists");
            return "members/addMemberForm";
        }
    
        return "redirect:/";
    }
}

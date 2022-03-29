package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.adapter.in.form.LoginForm;
import com.cuppa.cuppa.application.port.LoginUseCase;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.common.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginUseCase<Member> loginUseCase;
    
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form,
                          BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request) {
        
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        
        Member loginMember = loginUseCase.login(form.getLoginId(), form.getPassword());
        
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        
        // redirectURL 가 있으면 거기로, 없으면 defaultValue인 "/" 로 보낸다.
        return "redirect:" + redirectURL;
    }
    
    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}


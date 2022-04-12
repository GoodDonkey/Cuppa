package com.cuppa.cuppa.common.argumentresolver;

import com.cuppa.cuppa.common.session.SessionConst;
import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class SecurityLoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(SecurityLogin.class);
        log.debug("hasAnnotation={}", hasAnnotation);
        return hasAnnotation;
    }
    
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
    
        if (session == null) {
            return null;
        }
        
        try {
            SecurityContext securityContext = (SecurityContext) session.getAttribute(SessionConst.SPRING_SECURITY_CONTEXT);
            Authentication authentication = securityContext.getAuthentication();
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            Member member = securityUser.getMember();
            
            log.debug("username {} 이 로그인 중입니다.", member.getUsername());
            return member;
    
        } catch (Exception e) {
            log.info("로그인 정보 없는 요청: {}", e.getMessage());
            return null;
        }
    }
}

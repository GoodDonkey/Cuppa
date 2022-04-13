package com.cuppa.cuppa.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AjaxAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    
    public AjaxAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
    
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        
        Boolean isAjax = Boolean.parseBoolean(request.getHeader("AJAX"));
        log.debug("isAjax={}", isAjax);
        
        if (isAjax) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            super.commence(request,response,authException);
        }
    }
}


package com.cuppa.cuppa.interceptor;

import com.cuppa.cuppa.exceptions.UnauthorizedException;
import com.cuppa.cuppa.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Iterator;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
                                                                                                       Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
    
        HttpSession session = request.getSession();
        if (CorsUtils.isPreFlightRequest(request)) {
            // Cors 상세 설정은 WebConfig 에서 함
            return true;
        }
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            // TODO: json 반환일 때 더 나은 방법을 찾아야 함:
            //  1. 리다이렉트 할 수 있는 방법?
            //  2. json 반환인지 확인하는 더 나은 방법은?
            if (request.getHeader("accept").contains("application/json")) {
                log.debug("accept: {}", request.getHeader("accept"));
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}

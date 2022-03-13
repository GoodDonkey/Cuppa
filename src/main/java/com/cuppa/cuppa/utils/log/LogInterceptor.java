package com.cuppa.cuppa.utils.log;

import com.cuppa.cuppa.main.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    
    public static final String LOG_ID = "logId";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
                                                                                                       Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        
        request.setAttribute(LOG_ID, uuid);
        
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            MethodParameter[] methodParameters = hm.getMethodParameters();
            log.info("methodParameters={}", methodParameters);
        }
    
        if (handler instanceof ResourceHttpRequestHandler) {
            ResourceHttpRequestHandler hm = (ResourceHttpRequestHandler) handler;
            List<Resource> locations = hm.getLocations();
            List<ResourceResolver> resourceResolvers = hm.getResourceResolvers();
            log.info("locations={}", locations);
            log.info("resourceResolvers={}", resourceResolvers);
        }
        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        log.info("session={}", session);
        log.info("session.getAttribute(SessionConst.LOGIN_MEMBER)={}",
                 session.getAttribute(SessionConst.LOGIN_MEMBER));
        while (attributeNames.hasMoreElements()) {
            log.info("session={}", attributeNames.nextElement());
        }
        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);
    
        log.info("RESPONSE [{}][{}][{}]", logId, requestURI, handler);
        
        if (ex != null)
            log.error("afterCompletion error!", ex);
    }
}

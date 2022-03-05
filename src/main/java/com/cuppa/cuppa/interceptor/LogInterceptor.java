package com.cuppa.cuppa.interceptor;

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
    
        // @RequestMapping: HandlerMethod 사용함
        // 정적 리소스: ResourceHttpRequestHandler 사용
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler; // 호출할 컨트롤러 메서드의 모든 정보가 포함되어 있음.
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

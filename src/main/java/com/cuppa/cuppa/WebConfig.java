package com.cuppa.cuppa;

import com.cuppa.cuppa.argumentresolver.LoginMemberArgumentResolver;
import com.cuppa.cuppa.exceptions.UnauthorizedException;
import com.cuppa.cuppa.interceptor.LogInterceptor;
import com.cuppa.cuppa.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] logExcludePatterns = {"/css/**", "/*ico", "/registration/**","/error", "/chat/**", "/topic/**"};
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(logExcludePatterns);
    
        String[] loginExcludePatterns = {"/", "/members/add", "/login", "/logout", "/images/**", "/css/**", "/*.ico", "/chat/**", "/error", "/registration/**", "/topic/**"};
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(loginExcludePatterns);
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500/", "http://localhost:5500", "http://localhost:8080/")
                .allowedMethods("GET", "POST")
                .allowCredentials(true)
                .maxAge(3600);
    }
}

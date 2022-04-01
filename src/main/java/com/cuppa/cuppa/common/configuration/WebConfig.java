package com.cuppa.cuppa.common.configuration;

import com.cuppa.cuppa.common.argumentresolver.LoginMemberArgumentResolver;
import com.cuppa.cuppa.common.interceptor.LogInterceptor;
import com.cuppa.cuppa.common.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] CLASSPATH_PATH_PATTERNS = {"swagger-ui.html", "/webjars/**"};
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/"};
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] logExcludePatterns = {"/css/**", "/*ico", "/registration/**", "/error"};
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(logExcludePatterns);
    
        String[] loginExcludePatterns = {"/", "/members/add", "/login", "/logout", "/images/**", "/css/**", "/*.ico","/v3/api-docs/**", "/null/**",
                                         "/chat/**", "/error", "/registration/**", "/topic/**", "/stomp/**", "/app/**", "/swagger-ui/**", "/openapi.json", "/swagger-ui.*", "/swagger-ui.html/**","/webjars/**", "/swagger-resources/**", "/META-INF/**"};
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
                .allowedOrigins("http://127.0.0.1:5500/",
                                "http://localhost:5500",
                                "ws://127.0.0.1:5500/",
                                "http://localhost:8080/",
                                "http://127.0.0.1:63342/")
                .allowedMethods("GET", "POST")
                .allowCredentials(true).maxAge(3600);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Spring Boot 2.6 과 Swagger 의 호환 문제: Swagger ui 리소스를 찾지 못하는 이슈 떄문에 추가함.
        registry.addResourceHandler(CLASSPATH_PATH_PATTERNS).addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
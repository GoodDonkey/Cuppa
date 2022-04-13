package com.cuppa.cuppa.common.configuration;

import com.cuppa.cuppa.common.argumentresolver.LoginMemberArgumentResolver;
import com.cuppa.cuppa.common.argumentresolver.SecurityLoginMemberArgumentResolver;
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
        String[] logExcludePatterns = {"/css/**", "/*ico"};
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(logExcludePatterns);
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
        resolvers.add(new SecurityLoginMemberArgumentResolver());
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500/",
                                "http://localhost:5500",
                                "ws://127.0.0.1:5500/",
                                "http://localhost:8080/",
                                "http://127.0.0.1:63342/",
                                "http://127.0.0.1:63343/",
                                "http://localhost:3001/",
                                "http://127.0.0.1:3001/",
                                "https://cuppa-chat.vercel.app/",
                                "https://cuppa-chat.site/",
                                "https://chat.cuppa-chat.site/")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowCredentials(true).maxAge(3600);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Spring Boot 2.6 과 Swagger 의 호환 문제: Swagger ui 리소스를 찾지 못하는 이슈 떄문에 추가함.
        registry.addResourceHandler(CLASSPATH_PATH_PATTERNS).addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}

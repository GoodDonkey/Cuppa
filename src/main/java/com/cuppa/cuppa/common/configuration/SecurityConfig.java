package com.cuppa.cuppa.common.configuration;

import com.cuppa.cuppa.application.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final UserDetailsServiceImpl userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();
    
        http.cors(); // WebConfig 에서 설정하면 CorsConfiguration 빈이 등록된다. 그 빈을 자동으로 사용함.
    
        http.authorizeRequests().antMatchers("/",
                                             "/members/add",
                                             "/login",
                                             "/logout",
                                             "/*.ico",
                                             "/null/**",
                                             "/chat/**",
                                             "/error",
                                             "/topic/**",
                                             "/stomp/**",
                                             "/app/**",
                                             "/auth/**").permitAll()
            .antMatchers("/admin").hasRole("ADMIN").anyRequest()
            .authenticated();
    
        http.formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login").defaultSuccessUrl("/", true)
            .usernameParameter("loginId");
    
        // 인증에 실패할 경우 보낼 곳
        http.exceptionHandling()
            .authenticationEntryPoint(new AjaxAuthenticationEntryPoint("/auth/login"));
    
    
        http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
    
        http.userDetailsService(userDetailsService);
    }
    
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/v3/api-docs/**",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/swagger-ui.html",
                                   "/swagger-ui.*",
                                   "/swagger-ui/**",
                                   "/swagger-ui.html/**",
                                   "/swagger-resources/**",
                                   "/openapi.json",
                                   "/webjars/**",
                                   "/css/**",
                                   "/images/**",
                                   "/openapi.json",
                                   "/META-INF/**",
                                   "/js/**");
    }
}

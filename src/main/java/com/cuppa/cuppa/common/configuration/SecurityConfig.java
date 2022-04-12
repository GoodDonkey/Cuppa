package com.cuppa.cuppa.common.configuration;

import com.cuppa.cuppa.application.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
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
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/", "/members/add", "/login", "/logout", "/images/**", "/css/**", "/*.ico","/v3/api-docs/**", "/null/**",
                         "/chat/**", "/error", "/registration/**", "/topic/**", "/stomp/**", "/app/**", "/swagger-ui/**", "/openapi.json", "/swagger-ui.*",
                         "/swagger-ui.html/**","/webjars/**", "/swagger-resources/**", "/META-INF/**", "/auth/**").permitAll()
            .antMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated();

        http.formLogin()
            .loginPage("/auth/login")
            .loginProcessingUrl("/auth/login")
            .defaultSuccessUrl("/", true)
            .usernameParameter("loginId");
        
        http.exceptionHandling().accessDeniedPage("/forbidden");

        http.logout()
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/");
    
        http.userDetailsService(userDetailsService);
    }
}

package com.cuppa.cuppa.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/members/add", "/login", "/logout", "/images/**", "/css/**", "/*.ico","/v3/api-docs/**", "/null/**",
                         "/chat/**", "/error", "/registration/**", "/topic/**", "/stomp/**", "/app/**", "/swagger-ui/**", "/openapi.json", "/swagger-ui.*",
                         "/swagger-ui.html/**","/webjars/**", "/swagger-resources/**", "/META-INF/**", "/auth/**").permitAll()
            .antMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated();
    }
}

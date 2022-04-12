package com.cuppa.cuppa.common.configuration;

import com.cuppa.cuppa.application.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
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
        
        http.cors();
        
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
        
        // 인증에 실패할 경우 보낼 곳
        http.exceptionHandling();
        

        http.logout()
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/");
    
        http.userDetailsService(userDetailsService);
    }
    
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOriginPattern("*");
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
//        corsConfiguration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//
//        return urlBasedCorsConfigurationSource;
//    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**",
                                   "/h2-console/**",
                                   "/js/**");
    }
}

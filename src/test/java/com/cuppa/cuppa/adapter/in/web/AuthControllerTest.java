package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.application.port.in.SignupUseCase;
import com.cuppa.cuppa.application.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active:test")
class AuthControllerTest {
    
    @Autowired private SignupUseCase signupUseCase;
    
    @Test
    @DisplayName("beancheck")
    void beancheck() {
        assertThat(signupUseCase).isInstanceOf(AuthService.class);
    
    
    }
    
}
package com.cuppa.cuppa.adapter.in.web;

import com.cuppa.cuppa.application.port.in.MemberFetchUseCase;
import com.cuppa.cuppa.application.port.in.MemberSaveUseCase;
import com.cuppa.cuppa.application.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.profiles.active:test")
public class MemberControllerTest {
    @Autowired private MemberSaveUseCase memberSaveUseCase;
    @Autowired private MemberFetchUseCase memberFetchUseCase;
    
    @Test
    @DisplayName("Member UseCase 에 MemberService 가 주입되었는가?")
    void checkUseCaseType() {
        assertThat(memberSaveUseCase).isInstanceOf(MemberService.class);
        assertThat(memberFetchUseCase).isInstanceOf(MemberService.class);
    }
    
}
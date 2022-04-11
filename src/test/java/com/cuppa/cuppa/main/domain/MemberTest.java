package com.cuppa.cuppa.main.domain;

import com.cuppa.cuppa.domain.Member;
import com.cuppa.cuppa.domain.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    
    @Test
    @DisplayName("builder 되나?")
    void builderable() {
        
        Member member = Member.builder().loginId("qwe").password("qweqwe").role(Role.ROLE_MEMBER).build();
        
        Member member1 = new Member();
        member1.setLoginId("qwe");
        member1.setPassword("qweqwe");
        member1.setRole(Role.ROLE_MEMBER);
    
        assertThat(member.getLoginId()).isEqualTo(member1.getLoginId());
        assertThat(member.getPassword()).isEqualTo(member1.getPassword());
        assertThat(member.getRole()).isEqualTo(member1.getRole());
    }
}
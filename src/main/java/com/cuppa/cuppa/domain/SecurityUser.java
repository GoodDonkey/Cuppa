package com.cuppa.cuppa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Slf4j
@Getter
@Setter
public class SecurityUser extends User {
    
    private Member member;
    
    public SecurityUser(Member member) {
        super(member.getUsername(),
              member.getPassword(),
              AuthorityUtils.createAuthorityList(member.getRole().toString()));
    
        log.info("member={}", member);
        this.member = member;
    }
}

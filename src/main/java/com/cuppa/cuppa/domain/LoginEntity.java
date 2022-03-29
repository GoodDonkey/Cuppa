package com.cuppa.cuppa.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class LoginEntity {
    
    @Column(unique = true, nullable = false)
    private String loginId;
    
    @Column(nullable = false)
    private String password;
}

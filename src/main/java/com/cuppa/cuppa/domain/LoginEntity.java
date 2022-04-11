package com.cuppa.cuppa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@SuperBuilder
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class LoginEntity {
    
    @Column(unique = true, nullable = false)
    protected String loginId;
    
    @Column(nullable = false)
    protected String password;
}

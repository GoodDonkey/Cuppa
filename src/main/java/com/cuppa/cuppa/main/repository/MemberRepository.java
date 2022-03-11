package com.cuppa.cuppa.main.repository;

import com.cuppa.cuppa.main.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
    Optional<Member> findByUsername(String Username);
    Optional<Member> findByLoginId(String loginId);
    
    boolean existsByUsername(String Username);
}

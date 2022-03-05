package com.cuppa.cuppa.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;
    
    @Getter
    @Column(unique = true, nullable = false)
    private String loginId;

    @Getter
    @Column(unique = true, nullable = false)
    private String username;
    
    @Getter
    @Column(nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public Member(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "username= " + username;
    }
}

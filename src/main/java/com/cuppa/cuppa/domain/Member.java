package com.cuppa.cuppa.domain;

import com.cuppa.cuppa.app.messaging.model.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Getter
    @Column(unique = true, nullable = false)
    private String username;

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

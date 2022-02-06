package com.cuppa.cuppa.app.messaging.storage;

import com.cuppa.cuppa.domain.Member;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class MemberRepository {

    private static MemberRepository instance;

    @Getter
    private final Set<Member> users;

    private MemberRepository() {
        users = new HashSet<>();
    }

    public static synchronized MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    public void setMember(String username) throws Exception {
        Member member = new Member(username);
        if (users.contains(member)) {
            throw new Exception("이미 등록되어있는 유저입니다: " + username);
        }
        users.add(member);
    }

    public Set<Member> allMembers() {
        return users;
    }

    public Set<String> getUsernames(){
        return users.stream()
                    .map(Member::getUsername)
                    .collect(Collectors.toSet());
    }

    public boolean contains(String username) {
        return users.stream()
                    .map(Member::getUsername)
                    .anyMatch(eachUsername -> eachUsername.equals(username));
    }

    public Member getMember(String username) {
        return users.stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst()
                .get();
    }
}

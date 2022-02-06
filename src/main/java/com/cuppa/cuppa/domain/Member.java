package com.cuppa.cuppa.domain;

import lombok.*;

public class Member {

    private static long count = 0L;
    @Getter
    private final long id;
    @Getter
    private final String username;

    public Member(String username) {
        this.id = ++count;
        this.username = username;
    }

    @Override
    public String toString() {
        return "username= " + username;
    }
}

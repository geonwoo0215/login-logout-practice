package com.loginlogout.loginlogout.domain.member.model;

public class Member {

    private String id;

    private String email;

    private String password;

    public Member(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

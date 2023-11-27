package com.loginlogout.loginlogout.domain.member.model;

import java.util.Objects;

public class Member {

    private Long id;
    private String loginId;

    private String email;

    private String password;

    private String nickName;

    public Member() {
    }

    public Member(Long id, String loginId, String email, String password, String nickName) {
        this.id = id;
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(email, member.email) && Objects.equals(password, member.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}

package com.loginlogout.loginlogout.domain.member.model;

import com.loginlogout.loginlogout.domain.member.dto.MemberDto;

import java.util.Objects;

public class Member {

    private Long id;
    private String loginId;
    private String email;
    private String password;
    private String nickName;

    public Member() {
    }

    public Member(String loginId, String email, String password, String nickName) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public MemberDto toMemberDto () {
        return new MemberDto(this.id, this.nickName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(id, member.id) && Objects.equals(loginId, member.loginId) && Objects.equals(email, member.email) && Objects.equals(password, member.password) && Objects.equals(nickName, member.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginId, email, password, nickName);
    }
}

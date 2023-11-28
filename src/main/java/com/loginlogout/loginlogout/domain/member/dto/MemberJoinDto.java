package com.loginlogout.loginlogout.domain.member.dto;

import com.loginlogout.loginlogout.domain.member.model.Member;

public class MemberJoinDto {

    private String loginId;

    private String email;

    private String password;

    private String nickName;

    public MemberJoinDto(String loginId, String email, String password, String nickName) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public Member toMember(String encodePassword) {
        return new Member(this.loginId, this.email, encodePassword, this.nickName);
    }
}

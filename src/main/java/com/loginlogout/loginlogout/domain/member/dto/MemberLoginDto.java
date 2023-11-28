package com.loginlogout.loginlogout.domain.member.dto;

public class MemberLoginDto {

    private String loginId;

    private String password;

    public MemberLoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}

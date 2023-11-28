package com.loginlogout.loginlogout.domain.member.dto;

public class MemberDto {

    private Long id;

    private String nickName;

    public MemberDto(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }
}

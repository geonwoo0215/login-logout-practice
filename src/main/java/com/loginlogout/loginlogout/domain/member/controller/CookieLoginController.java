package com.loginlogout.loginlogout.domain.member.controller;

import com.loginlogout.loginlogout.domain.member.dto.MemberDto;
import com.loginlogout.loginlogout.domain.member.dto.MemberJoinDto;
import com.loginlogout.loginlogout.domain.member.dto.MemberLoginDto;
import com.loginlogout.loginlogout.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CookieLoginController {

    private final MemberService memberService;

    public CookieLoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> join(
            @RequestBody MemberJoinDto memberJoinDto,
            HttpServletRequest request
    ) {
        Long id = memberService.join(memberJoinDto);

        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + id)).build();

    }

    @PostMapping(value = "/members/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDto> login(
            @RequestBody MemberLoginDto memberLoginDto,
            HttpServletResponse response
    ) {
        MemberDto memberDto = memberService.login(memberLoginDto);
        Cookie cookie = new Cookie("id", memberDto.getId().toString());
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/members/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("id", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

}

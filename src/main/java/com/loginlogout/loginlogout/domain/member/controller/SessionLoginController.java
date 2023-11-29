package com.loginlogout.loginlogout.domain.member.controller;

import com.loginlogout.loginlogout.domain.member.dto.MemberDto;
import com.loginlogout.loginlogout.domain.member.dto.MemberJoinDto;
import com.loginlogout.loginlogout.domain.member.dto.MemberLoginDto;
import com.loginlogout.loginlogout.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/session")
public class SessionLoginController {

    private final MemberService memberService;

    public SessionLoginController(MemberService memberService) {
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
    public ResponseEntity<Void> login(
            @RequestBody MemberLoginDto memberLoginDto,
            HttpServletRequest httpServletRequest
    ) {
        MemberDto memberDto = memberService.login(memberLoginDto);
       
        HttpSession session = httpServletRequest.getSession(true);

        session.setAttribute("id", memberDto.getId());
        session.setMaxInactiveInterval(60);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/members/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}

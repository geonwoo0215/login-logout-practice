package com.loginlogout.loginlogout.domain.member.service;

import com.loginlogout.loginlogout.domain.member.dto.MemberJoinDto;
import com.loginlogout.loginlogout.domain.member.model.Member;
import com.loginlogout.loginlogout.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    MemberService memberService;

    @Test
    void join() {

        MemberJoinDto memberJoinDto = new MemberJoinDto("loginId", "email", "password", "nickName");
        Member member = memberJoinDto.toMember("encodePassword");

        Mockito.when(passwordEncoder.encode(memberJoinDto.getPassword())).thenReturn("encodePassword");
        Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(member);

        memberService.join(memberJoinDto);

        Mockito.verify(passwordEncoder).encode(memberJoinDto.getPassword());
        Mockito.verify(memberRepository).save(member);

    }





}
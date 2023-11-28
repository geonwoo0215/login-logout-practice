package com.loginlogout.loginlogout.domain.member.service;

import com.loginlogout.loginlogout.domain.member.dto.MemberJoinDto;
import com.loginlogout.loginlogout.domain.member.model.Member;
import com.loginlogout.loginlogout.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootTest
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    void join() {

        MemberJoinDto memberJoinDto = new MemberJoinDto("loginId", "email", "password", "nickName");
        Long id = memberService.join(memberJoinDto);

        Optional<Member> optionalMember = memberRepository.findById(id);

        Assertions.assertThat(optionalMember.isPresent()).isTrue();
        Member member = optionalMember.get();

        Assertions.assertThat(encoder.matches(memberJoinDto.getPassword(),member.getPassword())).isTrue();

        Assertions.assertThat(member)
                .hasFieldOrPropertyWithValue("loginId",memberJoinDto.getLoginId())
                .hasFieldOrPropertyWithValue("email",memberJoinDto.getEmail())
                .hasFieldOrPropertyWithValue("nickName",memberJoinDto.getNickName());



    }

}

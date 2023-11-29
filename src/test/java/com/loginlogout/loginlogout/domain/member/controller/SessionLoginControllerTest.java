package com.loginlogout.loginlogout.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loginlogout.loginlogout.domain.member.dto.MemberJoinDto;
import com.loginlogout.loginlogout.domain.member.dto.MemberLoginDto;
import com.loginlogout.loginlogout.domain.member.repository.MemberRepository;
import com.loginlogout.loginlogout.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SessionLoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void login() throws Exception {
        MemberJoinDto memberJoinDto = new MemberJoinDto("loginId", "email", "password", "nickName");
        Long id = memberService.join(memberJoinDto);

        MemberLoginDto memberLoginDto = new MemberLoginDto("loginId", "password");
        String json = objectMapper.writeValueAsString(memberLoginDto);

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(MockMvcRequestBuilders.post("/session/members/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThat(session.isInvalid()).isFalse();
        assertThat(session.getAttribute("id")).isEqualTo(id);
        assertThat(session.getMaxInactiveInterval()).isEqualTo(60);

    }

    @Test
    @Transactional
    void logout() throws Exception {
        MemberJoinDto memberJoinDto = new MemberJoinDto("loginId", "email", "password", "nickName");
        memberService.join(memberJoinDto);

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(MockMvcRequestBuilders.get("/session/members/logout")
                        .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThat(session.isInvalid()).isTrue();
    }

}
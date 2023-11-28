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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CookieLoginControllerTest {

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
    void join() throws Exception {

        MemberJoinDto memberJoinDto = new MemberJoinDto("loginId", "email", "password", "nickName");
        String json = objectMapper.writeValueAsString(memberJoinDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Transactional
    void login() throws Exception {

        MemberJoinDto memberJoinDto = new MemberJoinDto("loginId", "email", "password", "nickName");
        memberService.join(memberJoinDto);

        MemberLoginDto memberLoginDto = new MemberLoginDto("loginId", "password");
        String json = objectMapper.writeValueAsString(memberLoginDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/members/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.cookie().exists("id"))
                .andExpect(MockMvcResultMatchers.cookie().maxAge("id", 60))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    void logout() throws Exception {
        MemberJoinDto memberJoinDto = new MemberJoinDto("loginId", "email", "password", "nickName");
        memberService.join(memberJoinDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/members/logout"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.cookie().exists("id"))
                .andExpect(MockMvcResultMatchers.cookie().maxAge("id", 0))
                .andDo(MockMvcResultHandlers.print());
    }


}
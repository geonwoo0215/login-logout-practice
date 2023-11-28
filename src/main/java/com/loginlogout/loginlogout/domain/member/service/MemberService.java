package com.loginlogout.loginlogout.domain.member.service;

import com.loginlogout.loginlogout.domain.member.dto.MemberDto;
import com.loginlogout.loginlogout.domain.member.dto.MemberJoinDto;
import com.loginlogout.loginlogout.domain.member.dto.MemberLoginDto;
import com.loginlogout.loginlogout.domain.member.model.Member;
import com.loginlogout.loginlogout.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder encoder) {
        this.memberRepository = memberRepository;
        this.encoder = encoder;
    }

    @Transactional
    public Long join(MemberJoinDto memberJoinDto) {
        validateDuplicateEmail(memberJoinDto.getEmail());
        validateDuplicateLoginId(memberJoinDto.getLoginId());

        String encodePassword = encoder.encode(memberJoinDto.getPassword());
        Member member = memberJoinDto.toMember(encodePassword);
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    public MemberDto login(MemberLoginDto memberLoginDto) {

        return memberRepository.findByLoginId(memberLoginDto.getLoginId())
                .filter(member -> encoder.matches(memberLoginDto.getPassword(), member.getPassword()))
                .map(Member::toMemberDto)
                .orElseThrow(RuntimeException::new);
    }

    private void validateDuplicateLoginId(String loginId) {
        if (memberRepository.existsByLoginId(loginId)) {
            throw new RuntimeException();
        }
    }

    private void validateDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException();
        }
    }
}

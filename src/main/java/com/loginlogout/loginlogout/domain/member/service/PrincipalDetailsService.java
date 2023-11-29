package com.loginlogout.loginlogout.domain.member.service;

import com.loginlogout.loginlogout.config.PrincipalDetails;
import com.loginlogout.loginlogout.domain.member.model.Member;
import com.loginlogout.loginlogout.domain.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public PrincipalDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByLoginId(username)
                .orElseThrow(RuntimeException::new);

        return new PrincipalDetails(member);
    }
}

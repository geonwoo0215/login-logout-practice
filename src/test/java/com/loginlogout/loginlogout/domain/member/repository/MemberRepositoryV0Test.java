package com.loginlogout.loginlogout.domain.member.repository;

import com.loginlogout.loginlogout.domain.member.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() {
        Member member = new Member("id1", "email","password");
        repository.save(member);

        Member findMember = repository.findById(member.getId());
        Assertions.assertThat(member).isEqualTo(findMember);
    }

}
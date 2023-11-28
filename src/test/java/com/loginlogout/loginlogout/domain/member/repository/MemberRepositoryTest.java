package com.loginlogout.loginlogout.domain.member.repository;

import com.loginlogout.loginlogout.domain.member.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
class MemberRepositoryTest {

    MemberRepository repository;

    @Autowired
    public MemberRepositoryTest(DataSource dataSource) {
        this.repository = new MemberRepository(dataSource);
    }

    @Test
    @Transactional
    void save() {
        Member member = new Member("loginId", "email", "password", "nickName");
        Member save = repository.save(member);
        assertThat(member).isEqualTo(save);
    }

    @Test
    void findById() {
        Member member = new Member("loginId", "email", "password", "nickName");
        repository.save(member);
        Optional<Member> optionalMember = repository.findById(member.getId());

        assertThat(optionalMember.isPresent()).isTrue();
        Member findMember = optionalMember.get();
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void existsByLoginId_True() {
        Member member = new Member("loginId", "email", "password", "nickName");
        repository.save(member);

        boolean exists = repository.existsByLoginId("loginId");

        assertThat(exists).isTrue();
    }

    @Test
    void existsByLoginId_False() {
        Member member = new Member("loginId", "email", "password", "nickName");
        repository.save(member);

        boolean exists = repository.existsByLoginId("loginId1");

        assertThat(exists).isFalse();

    }

    @Test
    @Transactional
    void findByLoginId() {
        Member member = new Member("loginId", "email", "password", "nickName");
        repository.save(member);

        Optional<Member> optionalMember = repository.findByLoginId("loginId");
        assertThat(optionalMember.isPresent()).isTrue();
        Member findMember = optionalMember.get();
        assertThat(member).isEqualTo(findMember);
    }


}
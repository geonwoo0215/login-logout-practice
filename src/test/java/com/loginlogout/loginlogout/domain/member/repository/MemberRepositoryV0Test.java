package com.loginlogout.loginlogout.domain.member.repository;

import com.loginlogout.loginlogout.config.dbconst.ConnectionConst;
import com.loginlogout.loginlogout.domain.member.model.Member;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static com.loginlogout.loginlogout.config.dbconst.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;

class MemberRepositoryV0Test {

    MemberRepository repository;

    @BeforeEach
    void beforeEach() throws Exception{
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        repository = new MemberRepository(dataSource);
    }

    @Test
    void crud() {
        Member member = new Member("id1", "email","password");
        repository.save(member);

        Member findMember = repository.findById(member.getId());
        assertThat(member).isEqualTo(findMember);

        repository.update(member.getId(), "email2", "password2");
        Member updateMember = repository.findById(member.getId());
        assertThat(updateMember.getEmail()).isEqualTo("email2");
        assertThat(updateMember.getPassword()).isEqualTo("password2");

        repository.delete(member.getId());
        assertThatThrownBy(()->repository.findById(member.getId())).isInstanceOf(RuntimeException.class);
    }

}
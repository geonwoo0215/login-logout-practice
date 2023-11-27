package com.loginlogout.loginlogout.domain.member.repository;

import com.loginlogout.loginlogout.domain.member.model.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

public class MemberRepository {

    public final DataSource dataSource;
    public final NamedParameterJdbcTemplate template;

    public final SimpleJdbcInsert jdbcInsert;

    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }

    public Member save(Member member) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(member);
        Number key = jdbcInsert.executeAndReturnKey(param);
        member.setId(key.longValue());
        return member;
    }

    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = :id";

        try {
            Map<String, Long> param = Map.of("id", id);
            Member member = template.queryForObject(sql, param, memberRowMapper());
            return Optional.of(member);
        } catch (DataAccessException e) {
            return Optional.empty();
        }

    }

    public RowMapper<Member> memberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class);
    }


}

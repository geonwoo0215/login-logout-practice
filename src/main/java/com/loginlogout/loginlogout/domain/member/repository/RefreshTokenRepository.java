package com.loginlogout.loginlogout.domain.member.repository;

import com.loginlogout.loginlogout.config.RefreshToken;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

@Repository
public class RefreshTokenRepository {

    private final NamedParameterJdbcTemplate template;

    private final SimpleJdbcInsert jdbcInsert;

    public RefreshTokenRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("RefreshToken")
                .usingGeneratedKeyColumns("id");
    }

    public RefreshToken save(RefreshToken refreshToken) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(refreshToken);
        Number key = jdbcInsert.executeAndReturnKey(param);
        refreshToken.setId(key.longValue());
        return refreshToken;
    }

    public Optional<RefreshToken> findById(Long id) {
        String sql = "select * from refresh_token where id = :id";

        try {
            Map<String, Long> param = Map.of("id", id);
            RefreshToken refreshToken = template.queryForObject(sql, param, refreshTokenRowMapper());
            return Optional.of(refreshToken);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public RowMapper<RefreshToken> refreshTokenRowMapper() {
        return BeanPropertyRowMapper.newInstance(RefreshToken.class);
    }
}

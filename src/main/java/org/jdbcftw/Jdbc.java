package org.jdbcftw;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class Jdbc {

    private final Mappers context;

    private final JdbcTemplate jdbc;

    private final NamedParameterJdbcTemplate namedJdbc;

    public Jdbc(Mappers context, DataSource dataSource) {
        this.context = context;
        this.jdbc = new JdbcTemplate(dataSource);
        this.namedJdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> query(String sql, Class<T> objectType, Object... args) {
        return jdbc.query(sql, context.types.get(objectType), args);
    }

    @SuppressWarnings("unchecked")
    public <T> T queryForObject(String sql, Class<T> objectType, Object... args) {
        return (T) jdbc.queryForObject(sql, context.types.get(objectType), args);
    }

}

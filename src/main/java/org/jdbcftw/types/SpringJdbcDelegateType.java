package org.jdbcftw.types;

import org.jdbcftw.Mappers;
import org.jdbcftw.Type;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpringJdbcDelegateType implements Type {

    private final Class<?> requiredType;

    public SpringJdbcDelegateType() {
        this(null);
    }

    public SpringJdbcDelegateType(Class<?> requiredType) {
        this.requiredType = requiredType;
    }

    @Override
    public Object mapRow(Mappers context, ResultSet rs, int columnIndex) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, columnIndex, requiredType);
    }

}

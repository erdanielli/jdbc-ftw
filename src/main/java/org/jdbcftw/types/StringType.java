package org.jdbcftw.types;

import org.jdbcftw.Mappers;
import org.jdbcftw.Type;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringType implements Type {

    @Override
    public Object mapRow(Mappers context, ResultSet rs, int columnIndex) throws SQLException {
        try {
            return rs.getString(columnIndex);
        } catch (SQLException e) {
            Object value = rs.getObject(columnIndex);
            return value == null ? null : String.valueOf(value);
        }
    }

}

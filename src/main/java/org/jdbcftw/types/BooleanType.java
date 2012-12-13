package org.jdbcftw.types;


import org.jdbcftw.Mappers;
import org.jdbcftw.Type;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanType implements Type {

    @Override
    public Object mapRow(Mappers context, ResultSet rs, int columnIndex) throws SQLException {
        try {
            boolean value = rs.getBoolean(columnIndex);
            if (value) {
                return true;
            }
        } catch (SQLException ignored) {}

        String flag = context.getValue(rs, columnIndex, String.class);
        if (flag != null) {
            if ("S".equalsIgnoreCase(flag) || "Y".equalsIgnoreCase(flag)) {
                return true;
            }
            if ("N".equalsIgnoreCase(flag)) {
                return false;
            }
            throw new TypeMismatchException(Boolean.class, flag);
        }
        return null;
    }

}

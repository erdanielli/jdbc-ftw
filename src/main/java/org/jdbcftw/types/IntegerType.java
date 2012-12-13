package org.jdbcftw.types;

import lombok.extern.slf4j.Slf4j;
import org.jdbcftw.Mappers;
import org.jdbcftw.Type;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class IntegerType implements Type {

    @Override
    public Object mapRow(Mappers context, ResultSet rs, int columnIndex) throws SQLException {
        Object value = rs.getObject(columnIndex);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            int result = ((Number) value).intValue();
            if (log.isDebugEnabled()) {
                log.debug("Converting {} {} into (int) {}", value.getClass().getName(), value, result);
            }
            return result;
        }
        if (value instanceof String) {
            try {
                int i = Integer.parseInt((String) value);
                log.debug("Converting String '{}' into (int) {}", value, i);
                return i;
            } catch (NumberFormatException e) {
                throw new TypeMismatchException(Integer.class, value);
            }
        }
        throw new TypeMismatchException(Integer.class, value);
    }
}

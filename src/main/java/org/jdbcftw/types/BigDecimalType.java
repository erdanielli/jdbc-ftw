package org.jdbcftw.types;

import lombok.extern.slf4j.Slf4j;
import org.jdbcftw.Mappers;
import org.jdbcftw.Type;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class BigDecimalType implements Type {

    public Object mapRow(Mappers context, ResultSet rs, int columnIndex) throws SQLException {
        try {
            return rs.getBigDecimal(columnIndex);
        } catch (SQLException exc) {
            Object value = rs.getObject(columnIndex);
            if (value instanceof String) {
                BigDecimal result = new BigDecimal((String) value);
                log.debug("Converting '{}' into {}", value, result);
                return result;
            }
            throw new TypeMismatchException(BigDecimal.class, value);
        }
    }
}

package org.jdbcftw.types;

import java.sql.SQLException;

public class TypeMismatchException extends SQLException {

    public TypeMismatchException(Class<?> requiredType, Object value) {
        super(String.format("'%s' could not be converted to %s", value, requiredType.getName()));
    }
}

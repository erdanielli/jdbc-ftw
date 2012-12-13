package org.jdbcftw;

public class NoSuchFieldInTypeException extends RuntimeException {

    public NoSuchFieldInTypeException(String field, Class<?> mappedClass) {
        super(String.format("No such field '%s' in %s", field, mappedClass.getName()));
    }

}

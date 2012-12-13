package org.jdbcftw;

public class NoSuchTypeException extends RuntimeException {

    public <T> NoSuchTypeException(Class<T> requiredType) {
        super("Missing type converter for " + requiredType.getName());
    }

}

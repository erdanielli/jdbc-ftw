package org.jdbcftw;

import java.util.HashMap;
import java.util.Map;

public abstract class Mapper {

    final Map<Class, Type> types = new HashMap<Class, Type>();

    public abstract void configure();

    protected void bind(Class<?> mappedClass, Type type) {
        types.put(mappedClass, type);
    }

    protected ComplexType bind(Class<?> mappedClass) {
        ComplexType type = new ComplexType(mappedClass);
        types.put(mappedClass, type);
        return type;
    }

}

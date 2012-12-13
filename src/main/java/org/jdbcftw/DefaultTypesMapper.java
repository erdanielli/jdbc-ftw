package org.jdbcftw;

import org.jdbcftw.types.BigDecimalType;
import org.jdbcftw.types.BooleanType;
import org.jdbcftw.types.IntegerType;
import org.jdbcftw.types.StringType;

import java.math.BigDecimal;

class DefaultTypesMapper extends Mapper {

    @Override
    public void configure() {
        bind(BigDecimal.class, new BigDecimalType());
        bind(Boolean.class, new BooleanType());
        bind(Integer.class, new IntegerType());
        bind(String.class, new StringType());
    }

}

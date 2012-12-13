package org.jdbcftw.spring;

import org.jdbcftw.Mappers;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MappersBean extends AbstractFactoryBean<Mappers> {

    @Override
    protected Mappers createInstance() throws Exception {
        return new Mappers();
    }

    @Override
    public Class<?> getObjectType() {
        return Mappers.class;
    }

}

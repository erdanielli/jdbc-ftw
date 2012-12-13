package org.jdbcftw.spring;

import lombok.extern.slf4j.Slf4j;
import org.jdbcftw.Mapper;
import org.jdbcftw.Mappers;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class AddMapperAutomatically implements BeanPostProcessor {

    @Autowired
    private Mappers mappers;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Mapper) {
            mappers.add((Mapper) bean);
        }
        return bean;
    }
}

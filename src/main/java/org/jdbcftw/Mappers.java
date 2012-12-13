package org.jdbcftw;

import lombok.extern.slf4j.Slf4j;
import org.jdbcftw.types.SpringJdbcDelegateType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.ClassUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Mappers {

    final Map<Class, RowMapper> types = new HashMap<Class, RowMapper>();

    public Mappers() {
        add(new DefaultTypesMapper());
    }

    public void add(Mapper mapper) {
        log.debug("Configuring mapper {}", mapper.getClass().getName());
        mapper.configure();

        for (Map.Entry<Class, Type> entry : mapper.types.entrySet()) {
            Class mappedClass = entry.getKey();
            Type type = entry.getValue();
            log.info("Registering mapper for {}", mappedClass.getName());
            types.put(mappedClass, new TypeRowMapper(type));
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(ResultSet rs, int columnIndex, Class<T> requiredType) throws SQLException {
        RowMapper<T> rowMapper = getRowMapper(requiredType);
        if (rowMapper == null) {
            try {
                rowMapper = new TypeRowMapper<T>(new SpringJdbcDelegateType(requiredType));
                rowMapper.mapRow(rs, columnIndex);
                log.debug("Falling back to JdbcUtils.getResultSetValue for type {}", requiredType);
                types.put(requiredType, rowMapper);
            } catch (SQLException e) {
                throw new NoSuchTypeException(requiredType);
            }
        }
        return rowMapper.mapRow(rs, columnIndex);
    }

    @SuppressWarnings("unchecked")
    private <T> RowMapper<T> getRowMapper(Class<?> requiredType) {
        Class<?> notPrimitive = ClassUtils.resolvePrimitiveIfNecessary(requiredType);
        return types.get(notPrimitive);
    }

    private class TypeRowMapper<T> implements RowMapper<T> {

        private final Type type;

        private TypeRowMapper(Type type) {
            this.type = type;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            return (T) type.mapRow(Mappers.this, new FriendlyResultSet(rs), rowNum);
        }
    }

}

package org.jdbcftw;

import lombok.Getter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

class ComplexTypeMetadata {

    final Class<?> mappedClass;

    String tableName;

    @Getter
    private ColumnMetadata primaryKey;

    private final Map<String, ColumnMetadata> columnByField = new HashMap<String, ColumnMetadata>();

    ComplexTypeMetadata(Class<?> javaTargetType) {
        this.mappedClass = javaTargetType;
        applyDefaults();
    }

    ColumnMetadata getColumnByField(String field) {
        ColumnMetadata column = columnByField.get(field);
        if (column == null) {
            throw new NoSuchFieldInTypeException(field, mappedClass);
        }
        return column;
    }

    ColumnMetadata getColumnByLabel(String label) {
        for (Map.Entry<String, ColumnMetadata> entry : columnByField.entrySet()) {
            if (entry.getValue().hasColumn(label)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private void applyDefaults() {
        tableName = mappedClass.getSimpleName();

        ReflectionUtils.doWithFields(mappedClass, new ReflectionUtils.FieldCallback() {
                    @Override
                    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                        String name = field.getName();
                        columnByField.put(name, new ColumnMetadata(field, name));
                    }
                }, new ReflectionUtils.FieldFilter() {
                    @Override
                    public boolean matches(Field field) {
                        return !Modifier.isTransient(field.getModifiers()) &&
                                !Modifier.isStatic(field.getModifiers());
                    }
                }
        );
    }

    void setPrimaryKey(String field, String column) {
        primaryKey = getColumnByField(field);
        primaryKey.changeColumn(column);
    }
}

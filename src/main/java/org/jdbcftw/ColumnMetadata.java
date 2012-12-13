package org.jdbcftw;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashSet;

class ColumnMetadata {

    private final Field field;

    final Class<?> type;

    String column;

    private final LinkedHashSet<Object> aliases;

    ColumnMetadata(Field field, String column) {
        this.field = field;
        this.type = field.getType();
        this.column = column;
        aliases = new LinkedHashSet<Object>();
        ReflectionUtils.makeAccessible(field);
    }

    boolean hasColumn(String columnOrAlias) {
        if (column.equalsIgnoreCase(columnOrAlias)) {
            return true;
        }
        for (Object alias : aliases) {
            if (columnOrAlias.equalsIgnoreCase(String.valueOf(alias))) {
                return true;
            }
        }
        return false;
    }

    void changeColumn(String column) {
        if (!this.column.equalsIgnoreCase(column)) {
            aliases.add(this.column);
            this.column = column;
        }
    }

    void addAliases(Object[] aliases) {
        Collections.addAll(this.aliases, aliases);
    }

    void set(Object object, Object value) {
        ReflectionUtils.setField(field, object, value);
    }
}

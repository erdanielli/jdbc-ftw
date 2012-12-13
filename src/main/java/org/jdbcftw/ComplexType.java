package org.jdbcftw;

import org.objenesis.ObjenesisHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class ComplexType implements Type {

    private final ComplexTypeMetadata metadata;

    public ComplexType(Class<?> clazz) {
        metadata = new ComplexTypeMetadata(clazz);
    }

    public ComplexType primaryKey(String field) {
        return primaryKey(field, field);
    }

    public ComplexType primaryKey(String field, String column) {
        metadata.setPrimaryKey(field, column);
        return this;
    }

    public ComplexType table(String tableName) {
        metadata.tableName = tableName;
        return this;
    }

    public ComplexType aliasColumn(String field, String column, Object... aliases) {
        ColumnMetadata columnMetadata = metadata.getColumnByField(field);
        columnMetadata.changeColumn(column);
        columnMetadata.addAliases(aliases);
        return this;
    }

    @Override
    public Object mapRow(Mappers context, ResultSet resultSet, int ignored) throws SQLException {
        FriendlyResultSet rs = (FriendlyResultSet) resultSet;

        Object mappedObject = createObject(rs);
        if (mappedObject == null) {
            return null;
        }

        Set<Map.Entry<String, Integer>> entries = rs.columnLabelIndex.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String columnLabel = entry.getKey();
            int columnIndex = entry.getValue();
            ColumnMetadata columnMetadata = metadata.getColumnByLabel(columnLabel);
            if (columnMetadata != null) {
                Object fieldValue = context.getValue(rs, columnIndex, columnMetadata.type);
                columnMetadata.set(mappedObject, fieldValue);
            }
        }
        return mappedObject;
    }

    private Object createObject(FriendlyResultSet rs) {
        ColumnMetadata primaryKey = metadata.getPrimaryKey();
        if (primaryKey != null) {
            for (String c : rs.columnLabelIndex.keySet()) {
                if (primaryKey.hasColumn(c)) {
                    return ObjenesisHelper.newInstance(metadata.mappedClass);
                }
            }
            return null;
        } else {
            return ObjenesisHelper.newInstance(metadata.mappedClass);
        }
    }

}

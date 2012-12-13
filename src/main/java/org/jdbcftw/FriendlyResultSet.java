package org.jdbcftw;

import lombok.Delegate;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

class FriendlyResultSet implements ResultSet {

    @Delegate
    private final ResultSet wrapped;

    final Map<String, Integer> columnLabelIndex;

    FriendlyResultSet(ResultSet wrapped) throws SQLException {
        this.wrapped = wrapped;
        columnLabelIndex = indexColumns();
    }

    private HashMap<String, Integer> indexColumns() throws SQLException {
        ResultSetMetaData metaData = wrapped.getMetaData();
        int columns = metaData.getColumnCount();

        HashMap<String, Integer> index = new HashMap<String, Integer>(columns);
        for (int i = 1; i <= columns; i++) {
            String columnLabel = metaData.getColumnLabel(i);
            index.put(columnLabel, i);
        }
        return index;
    }

}

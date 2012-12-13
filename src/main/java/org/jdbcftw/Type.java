package org.jdbcftw;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Type {

    Object mapRow(Mappers context, ResultSet rs, int columnIndex) throws SQLException;

}

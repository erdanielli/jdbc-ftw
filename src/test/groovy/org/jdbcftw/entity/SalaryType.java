package org.jdbcftw.entity;

import org.jdbcftw.Mappers;
import org.jdbcftw.Type;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryType implements Type {

    @Override
    public Object mapRow(Mappers context, ResultSet rs, int columnIndex) throws SQLException {
        return new Salary(context.getValue(rs, columnIndex, BigDecimal.class));
    }

}

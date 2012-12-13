package org.jdbcftw.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Salary {

    @Getter
    private final BigDecimal value;

}

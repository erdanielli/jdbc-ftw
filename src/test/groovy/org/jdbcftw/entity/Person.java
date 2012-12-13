package org.jdbcftw.entity;

import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class Person implements Serializable {

    private final int id;

    private String name;

    private Date birthdate;

    private Salary salary;

    public Person(String id) {
        throw new AssertionError("not supposed to invoke constructor");
    }

    public void setName(String name) {
        throw new AssertionError("not supposed to invoke setter");
    }

    public void setBirthDate(Date birthDate) {
        throw new AssertionError("not supposed to invoke setter");
    }

    public void setSalary(Salary salary) {
        throw new AssertionError("not supposed to invoke setter");
    }
}

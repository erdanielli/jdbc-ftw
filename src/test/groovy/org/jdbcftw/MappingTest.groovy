package org.jdbcftw

import org.jdbcftw.entity.Person
import org.jdbcftw.entity.Salary
import org.jdbcftw.entity.SalaryType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import java.text.SimpleDateFormat

@ContextConfiguration("classpath:META-INF/spring/jdbcftw-test-context.xml")
class MappingTest extends Specification {

    @Autowired
    Jdbc jdbc

    @Autowired
    Mappers mappers

    @Unroll
    def "should map person from sql #hint"() {
        given:
        def mapper = new Mapper() {
            @Override
            void configure() {
                bind(Salary, new SalaryType())

                bind(Person).table('MYSCHEMA.S_PERSON')
                        .primaryKey('id', 'ID_PERSON')
                        .aliasColumn('name', 'DS_NAME')
            }
        }
        mappers.add(mapper)

        when:
        def people = jdbc.query('select * from MYSCHEMA.S_PERSON', Person)

        then:
        with people[0], {
            id == 1
            name == 'John Doe'
            salary.value == 1234.99
            !birthdate
        }
        with people[1], {
            id == 2
            name == 'Foo Bar'
            salary.value == 0.99
            birthdate == date('1980-01-01')
        }

        where:
        sql << ['select * from MYSCHEMA.S_PERSON',
                'select ID_PERSON as id,DS_NAME as name,SALARY,birthdate from MYSCHEMA.S_PERSON']
        hint << ['WITHOUT aliases', 'with aliases']
    }

    Date date(String yyyyMMdd) {
        new SimpleDateFormat('yyyy-MM-dd').parse(yyyyMMdd)
    }
}

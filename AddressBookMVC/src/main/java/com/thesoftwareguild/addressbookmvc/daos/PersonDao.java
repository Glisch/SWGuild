
package com.thesoftwareguild.addressbookmvc.daos;

import com.thesoftwareguild.addressbookmvc.models.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PersonDao {

    private JdbcTemplate template;

    private static final String SQL_GET_PERSON = "select * from address_book.person where id = ?";
    private static final String SQL_INSERT_PERSON = "INSERT INTO address_book.person (first_name, last_name) VALUES (?, ?)";
    private static final String SQL_REMOVE_PERSON = "delete from address_book.person where id = ?";
    private static final String SQL_UPDATE_PERSON = "update address_book.person set first_name = ?, last_name = ? where id = ?";
    private static final String SQL_LIST = "select * from address_book.person";
    private static final String SQL_CHECK_PERSON_ACTIVE = "select count(*) from address_book.address where person_id = ?";

    public PersonDao(JdbcTemplate template) {
        this.template = template;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Person createPerson(Person person) {
        template.update(SQL_INSERT_PERSON, person.getFirstName(), person.getLastName());
        int id = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        person.setId(id);
        return person;
    }

    public Person getPerson(Integer id) {
        Person person = template.queryForObject(SQL_GET_PERSON, new PersonMapper(), id);
        return person;
    }

    public void update(Person person) {
        template.update(SQL_UPDATE_PERSON,
                person.getFirstName(),
                person.getLastName(),
                person.getId()
        );
    }

    public void remove(Person person) {

        int personCount = template.queryForObject(SQL_CHECK_PERSON_ACTIVE, Integer.class, person.getId());

        if (personCount == 0) {
            template.update(SQL_REMOVE_PERSON, person.getId());
        }
    }

    public List<Person> list() {
        List<Person> list = template.query(SQL_LIST, new PersonMapper());
        return list;
    }

    private static class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person person = new Person(rs.getString("first_name"), rs.getString("last_name"));
            person.setId(rs.getInt("id"));
            return person;
        }
    }
}

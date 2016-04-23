package com.thesoftwareguild.addressbookmvc.daos;

import com.thesoftwareguild.addressbookmvc.models.Address;
import com.thesoftwareguild.addressbookmvc.models.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AddressDao {
    
    private static final String SQL_GET_ADDRESS = "select * from address_book.address where id = ?";
    private static final String SQL_INSERT_ADDRESS = "INSERT INTO address_book.address (street, city, state, zip, person_id) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_REMOVE_ADDRESS = "delete from address_book.address where id = ?";
    private static final String SQL_UPDATE_ADDRESS = "update address_book.address set street = ?, city = ?, state = ?, zip = ? where id = ?";
    private static final String SQL_LIST = "select * from address_book.address a join address_book.person p on a.person_id = p.id order by p.last_name;";

    private JdbcTemplate template;
    PersonDao personDao;
    
    public AddressDao (JdbcTemplate template) {
        this.template = template;
        this.personDao = new PersonDao(template);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Address add(Address address) {
        
        template.update(SQL_INSERT_ADDRESS, 
                address.getStreet(), 
                address.getCity(), 
                address.getState(), 
                address.getZip(), 
                address.getPerson().getId()
        );
        
        int id = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        address.setId(id);
        
        return address;
        
    }

    public void update(Address address) {
        
        personDao.update(address.getPerson());
        
        template.update(SQL_UPDATE_ADDRESS,
                address.getStreet(), 
                address.getCity(), 
                address.getState(), 
                address.getZip(), 
                address.getId()
        );
    }

    public Address get(Integer id) {
        Address address = template.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), id);
        return address;
    }

    public void remove(Integer id) {
        Person person = get(id).getPerson();
        template.update(SQL_REMOVE_ADDRESS, id);
        personDao.remove(person);
    }

    public List<Address> list() {
        List<Address> addresses = template.query(SQL_LIST, new AddressMapper());
        return addresses;
    }
    
    
    private class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            
            Address address = new Address();
            address.setPerson(personDao.getPerson(rs.getInt("person_id")));
            address.setId(rs.getInt("id"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZip(rs.getString("zip"));
            
            return address;
        }
    }
}


package com.thesoftwareguild.addressbookmvc.controllers;

import com.thesoftwareguild.addressbookmvc.daos.AddressDao;
import com.thesoftwareguild.addressbookmvc.daos.PersonDao;
import com.thesoftwareguild.addressbookmvc.models.Address;
import com.thesoftwareguild.addressbookmvc.models.CommandObject;
import com.thesoftwareguild.addressbookmvc.models.Person;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/person")
public class PersonController {
    
    private AddressDao addressDao;
    private PersonDao personDao;
    
    @Inject
    public PersonController(AddressDao addressDao, PersonDao personDao) {
        this.addressDao = addressDao;
        this.personDao = personDao;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Person edit(@Valid @RequestBody CommandObject co) {
        
        
        Person person = addressDao.get(co.getId()).getPerson();
        
        person.setFirstName(co.getFirstName());
        person.setLastName(co.getLastName());
        
        personDao.update(person);
        return person;
    }
    
}

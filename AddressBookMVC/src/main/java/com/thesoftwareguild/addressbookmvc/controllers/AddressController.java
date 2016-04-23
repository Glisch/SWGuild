package com.thesoftwareguild.addressbookmvc.controllers;

import com.thesoftwareguild.addressbookmvc.daos.AddressDao;
import com.thesoftwareguild.addressbookmvc.daos.PersonDao;
import com.thesoftwareguild.addressbookmvc.models.Address;
import com.thesoftwareguild.addressbookmvc.models.CommandObject;
import com.thesoftwareguild.addressbookmvc.models.Person;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/address")
public class AddressController {

    private AddressDao addressDao;
    private PersonDao personDao;

    @Inject
    public AddressController(AddressDao AddressDao, PersonDao personDao) {
        this.addressDao = AddressDao;
        this.personDao = personDao;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Address add(@Valid @RequestBody CommandObject co) {
        
        Address address = new Address();
        
        address.setZip(co.getZip());
        address.setState(co.getState());
        address.setCity(co.getCity());
        address.setStreet(co.getStreet());
        address.setPerson(personDao.createPerson(new Person(co.getFirstName(), co.getLastName())));
        
        addressDao.add(address);
        return address;
    }

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Address show(@PathVariable("id") Integer id) {
        return addressDao.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        addressDao.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Address edit(@Valid @RequestBody CommandObject co) {
        
        Address address = addressDao.get(co.getId());
        
        address.setZip(co.getZip());
        address.setState(co.getState());
        address.setCity(co.getCity());
        address.setStreet(co.getStreet());
        address.getPerson().setFirstName(co.getFirstName());
        address.getPerson().setLastName(co.getLastName());
        
        addressDao.update(address);
        return address;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Address addNew(@Valid @RequestBody CommandObject co) {
        
        Address address = new Address();
        
        address.setZip(co.getZip());
        address.setState(co.getState());
        address.setCity(co.getCity());
        address.setStreet(co.getStreet());
        address.setPerson(addressDao.get(co.getId()).getPerson());
        
        addressDao.add(address);
        return address;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public List search(@RequestBody String input) {
        
        String search = input.substring(7);

        List<Address> addresses = new ArrayList();
        
        addressDao.list().stream()
                .forEach((Address a) -> {
                    if(a.getPerson().getFirstName().contains(search) || a.getPerson().getLastName().contains(search) || a.getCity().contains(search) || a.getState().contains(search) || a.getZip().contains(search)){
                        addresses.add(a);
                    }
                });
        
        return addresses;
    }
}

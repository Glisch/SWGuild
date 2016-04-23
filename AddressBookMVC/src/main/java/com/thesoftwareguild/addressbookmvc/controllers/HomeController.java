
package com.thesoftwareguild.addressbookmvc.controllers;

import com.thesoftwareguild.addressbookmvc.daos.AddressDao;
import com.thesoftwareguild.addressbookmvc.models.Address;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private AddressDao addressDao;
    
    @Inject
    public HomeController(AddressDao addressDao){
        this.addressDao = addressDao;
    }
    
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home(Model model){
        List<Address> addresses = addressDao.list();
        model.addAttribute("addressList", addresses);
        return "index";
    }
}

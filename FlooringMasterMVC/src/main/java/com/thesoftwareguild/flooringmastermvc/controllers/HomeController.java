/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.controllers;

import com.thesoftwareguild.flooringmastermvc.daos.FlooringDao;
import com.thesoftwareguild.flooringmastermvc.daos.ProductDao;
import com.thesoftwareguild.flooringmastermvc.daos.TaxesDao;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private FlooringDao orderDao;
    private ProductDao productDao;
    private TaxesDao taxesDao;

    @Inject
    public HomeController(FlooringDao dao, ProductDao pdao, TaxesDao tdao) {
        this.orderDao = dao;
        this.productDao = pdao;
        this.taxesDao = tdao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        
        List<String> dates = orderDao.getDates();
        String[] products = productDao.getProducts();
        String[] states = taxesDao.getStates();
        

        model.addAttribute("dates", dates);
        model.addAttribute("products", products);
        model.addAttribute("states", states);
        
        return "index";
    }
    
    @RequestMapping(value="/dates")
    public List getDates(){
        return orderDao.getDates();
    }

}

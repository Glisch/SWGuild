/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrarymvc.controllers;

import com.thesoftwareguild.dvdlibrarymvc.dao.DvdDao;
import com.thesoftwareguild.dvdlibrarymvc.models.Dvd;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private DvdDao dvdDao;

    @Inject
    public HomeController(DvdDao dao) {
        this.dvdDao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        List<Dvd> dvds = dvdDao.listAll();

        model.addAttribute("dvdList", dvds);

        return "index";
    }

}

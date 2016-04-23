package com.thesoftwareguild.dvdlibrarymvc.controllers;

import com.thesoftwareguild.dvdlibrarymvc.dao.DvdDao;
import com.thesoftwareguild.dvdlibrarymvc.models.CommandObject;
import com.thesoftwareguild.dvdlibrarymvc.models.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/dvd")
public class DvdController {
    
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter sf = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private DvdDao dao;

    @Inject
    public DvdController(DvdDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Dvd add(@Valid @RequestBody CommandObject input) {
        
        Dvd dvd = new Dvd();
        
        dvd.setTitle(input.getTitle());
        dvd.setMpaaRating(input.getMpaaRating());
        dvd.setDirector(input.getDirector());
        dvd.setStudio(input.getStudio());
        dvd.setNote(input.getNote());
        dvd.setReleaseDate(LocalDate.parse(input.getReleaseDate()));
        
        dao.add(dvd);

        return dvd;
    }

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public CommandObject show(@PathVariable("id") Integer id) {

        Dvd dvd = dao.get(id);
        
        CommandObject input = new CommandObject();
        
        input.setId(dvd.getId());
        input.setTitle(dvd.getTitle());
        input.setReleaseDate(dvd.getReleaseDate().format(sf));
        input.setMpaaRating(dvd.getMpaaRating());
        input.setDirector(dvd.getDirector());
        input.setStudio(dvd.getStudio());
        input.setNote(dvd.getNote());

        return input;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {

        dao.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Dvd edit(@Valid @RequestBody CommandObject input, @PathVariable("id") Integer id) {
        
        Dvd dvd = dao.get(id);
        
        String date = input.getReleaseDate().substring(6) + "-" + input.getReleaseDate().substring(0, 2) + "-" + input.getReleaseDate().substring(3, 5);
        
        dvd.setTitle(input.getTitle());
        dvd.setMpaaRating(input.getMpaaRating());
        dvd.setDirector(input.getDirector());
        dvd.setStudio(input.getStudio());
        dvd.setNote(input.getNote());
        dvd.setReleaseDate(LocalDate.parse(date));
        
        dao.update(dvd);

        return dvd;
    }

    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam("search") String search, @RequestParam("optradio") String optradio) {

        List<Dvd> dvds = new ArrayList();
        
        switch (optradio) {
            case ("title"):
                dvds = dao.getByTitle(search.toLowerCase());
                break;
            case ("rating"):
                dvds = dao.getByRating(search.toLowerCase());
                break;
            case ("studio"):
                dvds = dao.getByStudio(search.toLowerCase());
                break;
        }
        
     model.addAttribute("dvds", dvds);

        return "showSearch";
    }

}

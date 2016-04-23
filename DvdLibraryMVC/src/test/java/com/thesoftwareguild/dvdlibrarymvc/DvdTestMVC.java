package com.thesoftwareguild.dvdlibrarymvc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.dvdlibrarymvc.dao.DvdDao;
import com.thesoftwareguild.dvdlibrarymvc.models.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DvdTestMVC {

//    DvdDao dao;
//
//    Dvd dvd1 = new Dvd();
//    Dvd dvd2 = new Dvd();
//    Dvd dvd3 = new Dvd();
//
//    public DvdTestMVC() {
//    }
//
//    @Before
//    public void setUp() {
//
//        dvd1.setTitle("Lock Stock and Two Smoking Barrels");
//        dvd1.setMpaaRating("R");
//        dvd1.setReleaseDate(LocalDate.parse("1997-02-01", DateTimeFormatter.ISO_DATE));
//        dvd1.setDirector("Guy Ritchie");
//        dvd1.setStudio("Bullshit Way");
//
//        dvd2.setTitle("Cheech And Chong Go To Paris");
//        dvd2.setMpaaRating("R");
//        dvd2.setReleaseDate(LocalDate.parse("1976-03-01", DateTimeFormatter.ISO_DATE));
//        dvd2.setDirector("Cheech Marin");
//        dvd2.setStudio("Doobs");
//
//        dvd3.setTitle("Beauty and the Beast");
//        dvd3.setMpaaRating("NC-17");
//        dvd3.setReleaseDate(LocalDate.parse("1992-05-01", DateTimeFormatter.ISO_DATE));
//        dvd3.setDirector("Guy Ritchie");
//        dvd3.setStudio("Disney");
//
//    }
//
//    @After
//    public void tearDown() {
//        dao.remove(dvd1.getId());
//        dao.remove(dvd2.getId());
//        dao.remove(dvd3.getId());
//    }
//
//    @Test
//    public void addDvdMultiple() {
//
//        dao.add(dvd1);
//        dao.add(dvd2);
//        dao.add(dvd3);
//
//        int size = dao.listAll().size();
//
//        Assert.assertTrue(dao.listAll().contains(dvd1));
//        Assert.assertTrue(dao.listAll().contains(dvd2));
//        Assert.assertTrue(dao.listAll().contains(dvd3));
//    }
//
//    @Test
//    public void addDvdSingle() {
//        dao.add(dvd1);
//
//        int size = dao.listAll().size();
//
//        Assert.assertTrue(dao.listAll().contains(dvd1));
//    }
//    
//    @Test
//    public void updateDVDMultiple() {
//        
//        dao.add(dvd1);
//        dao.add(dvd2);
//        dao.add(dvd3);
//        
//       
//        Dvd one = dvd1;
//        one.setMpaaRating("G");
//
//        Dvd two = dvd2;
//        two.setStudio("Inevitable Failure Studios");
//
//        Dvd three = dvd3;
//        three.setReleaseDate(LocalDate.parse("1981-04-01"));
//        
//        dao.update(one);
//        dao.update(two);
//        dao.update(three);
//        
//        Assert.assertEquals("G", dao.get(dvd1.getId()).getMpaaRating());
//        Assert.assertEquals("Inevitable Failure Studios", dao.get(dvd2.getId()).getStudio());
//        Assert.assertEquals("1981-04-01", dao.get(dvd3.getId()).getReleaseDate().toString());
//        
//    }
//
//    @Test
//    public void updateDVDSingle() {
//        
//        dao.add(dvd1);
//               
//        Dvd one = dvd1;
//        one.setMpaaRating("G");
//
//        dao.update(one);
//
//        Assert.assertEquals("G", dao.get(dvd1.getId()).getMpaaRating());
//        
//    }
//    
//    @Test
//    public void getByIdMultiple() {
//        dao.add(dvd1);
//        dao.add(dvd2);
//        dao.add(dvd3);
//
//        Dvd thisDvd = dao.get(dvd1.getId());
//        Dvd thatDvd = dao.get(dvd2.getId());
//        Dvd theOtherDvd = dao.get(dvd3.getId());
//
//        Assert.assertEquals(thisDvd, dvd1);
//        Assert.assertEquals(dvd2, thatDvd);
//        Assert.assertEquals(dvd3, theOtherDvd);
//
//    }
//
//    @Test
//    public void getByIdSingle() {
//        dao.add(dvd1);
//
//        Dvd thisDvd = dao.get(dvd1.getId());
//
//        Assert.assertEquals(thisDvd, dvd1);
//
//    }
//
//    @Test
//    public void getByIdNull() {
//            Dvd thisDvd = dao.get(dvd1.getId());
//            
//            Assert.assertEquals(null, thisDvd);
//    }
//
//    @Test
//    public void getByTitleMultiple() {
//        dao.add(dvd1);
//        dao.add(dvd2);
//        dao.add(dvd3);
//
//        List<Dvd> titleList1 = dao.getByTitle(dvd1.getTitle());
//        List<Dvd> titleList2 = dao.getByTitle(dvd2.getTitle());
//        List<Dvd> titleList3 = dao.getByTitle(dvd3.getTitle());
//
//        Assert.assertEquals("Lock Stock and Two Smoking Barrels", titleList1.get(0).getTitle());
//        Assert.assertEquals("Cheech And Chong Go To Paris", titleList2.get(0).getTitle());
//        Assert.assertEquals("Beauty and the Beast", titleList3.get(0).getTitle());
//    }
//
//    @Test
//    public void getByTitleSingle() {
//        dao.add(dvd1);
//
//        List<Dvd> titleList1 = dao.getByTitle(dvd1.getTitle());
//
//        Assert.assertEquals("Lock Stock and Two Smoking Barrels", titleList1.get(0).getTitle());
//    }
//
//    @Test
//    public void getByTitleEmpty() {
//
//        List<Dvd> thisDvd = dao.getByTitle("Bad Title");
//        Assert.assertTrue(thisDvd.isEmpty());
//
//    }
//
//    @Test
//    public void getByRatingMultiple() {
//        dao.add(dvd1);
//        dao.add(dvd2);
//        dao.add(dvd3);
//
//        List<Dvd> ratingList1 = dao.getByRating(dvd1.getMpaaRating());
//        List<Dvd> ratingList2 = dao.getByRating(dvd2.getMpaaRating());
//        List<Dvd> ratingList3 = dao.getByRating(dvd3.getMpaaRating());
//
//        Assert.assertEquals("R", ratingList1.get(0).getMpaaRating());
//        Assert.assertEquals("R", ratingList2.get(0).getMpaaRating());
//        Assert.assertEquals("NC-17", ratingList3.get(0).getMpaaRating());
//
//    }
//
//    @Test
//    public void getByRatingSingle() {
//        dao.add(dvd1);
//
//        List<Dvd> ratingList1 = dao.getByRating(dvd1.getMpaaRating());
//
//        Assert.assertEquals("R", ratingList1.get(0).getMpaaRating());
//    }
//    
// @Test
//    public void getByRatingEmpty() {
//
//        List<Dvd> thisDvd = dao.getByRating("G");
//        Assert.assertTrue(thisDvd.isEmpty());
//
//    }
//    
//    @Test
//    public void getByStudioMultiple() {
//        dao.add(dvd1);
//        dao.add(dvd2);
//        dao.add(dvd3);
//
//        List<Dvd> studioList1 = dao.getByStudio(dvd1.getStudio());
//        List<Dvd> studioList2 = dao.getByStudio(dvd2.getStudio());
//        List<Dvd> studioList3 = dao.getByStudio(dvd3.getStudio());
//
//        Assert.assertEquals("Bullshit Way", studioList1.get(0).getStudio());
//        Assert.assertEquals("Doobs", studioList2.get(0).getStudio());
//        Assert.assertEquals("Disney", studioList3.get(0).getStudio());
//
//    }
//
//    @Test
//    public void getByStudioSingle() {
//        dao.add(dvd1);
//
//        List<Dvd> studioList1 = dao.getByStudio(dvd1.getStudio());
//
//        Assert.assertEquals("Bullshit Way", studioList1.get(0).getStudio());
//    }
//    
// @Test
//    public void getByStudioEmpty() {
//
//        List<Dvd> thisDvd = dao.getByStudio("NonExistant Studio");
//        Assert.assertTrue(thisDvd.isEmpty());
//
//    }
}

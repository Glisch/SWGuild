/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrarymvc.dao;

import com.thesoftwareguild.dvdlibrarymvc.models.Dvd;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DvdDao {

    public Dvd add(Dvd dvd);

    public void remove(int id);

    public void update(Dvd dvd);

    public List<Dvd> listAll();

    public Dvd get(int id);

    public List<Dvd> getByTitle(String title);

    public List<Dvd> getByRating(String rating);

    public List<Dvd> getByStudio(String studio);

}

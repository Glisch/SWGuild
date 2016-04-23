/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrarymvc.dao;

import com.thesoftwareguild.dvdlibrarymvc.models.Dvd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdDaoImpl implements DvdDao {

    private static Integer nextId = 1;
    private Map<Integer, Dvd> dvds = new HashMap();
    private final String DELIMETER = "::";
    private File file = new File("dvds.txt");

    public DvdDaoImpl() {
        decode();
    }

    @Override
    public Dvd add(Dvd dvd) {

        dvd.setId(nextId);
        dvds.put(dvd.getId(), dvd);
        encode();

        return dvd;
    }

    @Override
    public void remove(int id) {
        dvds.remove(id);
        encode();
    }

    @Override
    public void update(Dvd dvd) {
        dvds.put(dvd.getId(), dvd);
        encode();
    }

    @Override
    public List<Dvd> listAll() {
        return dvds.values()
                .stream()
                .sorted((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public Dvd get (int id) {
        return dvds.get(id);
    }

    @Override
    public List<Dvd> getByTitle(String title) {
        return listAll()
                .stream()
                .filter(s -> s.getTitle().toLowerCase().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> getByRating(String rating) {
         return listAll()
                .stream()
                .filter(s -> s.getMpaaRating().toLowerCase().contains(rating))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
         return listAll()
                .stream()
                .filter(s -> s.getStudio().toLowerCase().contains(studio))
                .collect(Collectors.toList());
    }
    
    private void setNextId() {
        dvds.keySet().forEach(key -> {
            if (key >= nextId) {
                nextId = key + 1;
            }
        });
    }
    
    private void encode() {

        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));

            List<Dvd> dvdList = listAll();

            for (Dvd d : dvdList) {

                String line = d.getId() + DELIMETER
                        + d.getTitle() + DELIMETER
                        + d.getReleaseDate() + DELIMETER
                        + d.getMpaaRating() + DELIMETER
                        + d.getDirector() + DELIMETER
                        + d.getStudio() + DELIMETER
                        + d.getNote();             
                out.println(line);
                out.flush();
            }

        } catch (Exception ex) {

        }
        setNextId();

    }
    
    private void decode() {

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(file)));

            while (sc.hasNextLine()) {

                String thisLine = sc.nextLine();
                String[] values = thisLine.split(DELIMETER);

                Dvd film = new Dvd();

                film.setId(Integer.parseInt(values[0]));
                film.setTitle(values[1]);
                film.setReleaseDate(LocalDate.parse(values[2]));
                film.setMpaaRating(values[3]);
                film.setDirector(values[4]);
                film.setStudio(values[5]);
                film.setNote(values[6]);

                dvds.put(film.getId(), film);
            }

        } catch (FileNotFoundException fnf) {

        }
        setNextId();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.daos;

import com.thesoftwareguild.flooringmastermvc.dtos.Taxes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class TaxesDao {

    private List<Taxes> taxList = new ArrayList<>();

    public TaxesDao() {
        taxList.add(new Taxes("AL", 4.0));
        taxList.add(new Taxes("AK", 0.0));
        taxList.add(new Taxes("AZ", 5.6));
        taxList.add(new Taxes("AR", 6.5));
        taxList.add(new Taxes("CA", 7.5));
        taxList.add(new Taxes("CO", 2.9));
        taxList.add(new Taxes("CT", 6.35));
        taxList.add(new Taxes("DE", 0.0));
        taxList.add(new Taxes("FL", 6.0));
        taxList.add(new Taxes("GA", 4.0));
        taxList.add(new Taxes("HI", 4.0));
        taxList.add(new Taxes("ID", 6.0));
        taxList.add(new Taxes("IL", 6.25));
        taxList.add(new Taxes("IN", 7.0));
        taxList.add(new Taxes("IA", 6.0));
        taxList.add(new Taxes("KS", 6.15));
        taxList.add(new Taxes("KY", 6.0));
        taxList.add(new Taxes("LA", 4.0));
        taxList.add(new Taxes("ME", 5.5));
        taxList.add(new Taxes("MD", 6.0));
        taxList.add(new Taxes("MA", 6.25));
        taxList.add(new Taxes("MI", 6.0));
        taxList.add(new Taxes("MN", 6.875));
        taxList.add(new Taxes("MS", 7.0));
        taxList.add(new Taxes("MO", 4.225));
        taxList.add(new Taxes("MT", 0.0));
        taxList.add(new Taxes("NE", 5.5));
        taxList.add(new Taxes("NV", 6.85));
        taxList.add(new Taxes("NH", 0.0));
        taxList.add(new Taxes("NJ", 7.0));
        taxList.add(new Taxes("NM", 5.125));
        taxList.add(new Taxes("NY", 4.0));
        taxList.add(new Taxes("NC", 4.75));
        taxList.add(new Taxes("ND", 5.0));
        taxList.add(new Taxes("OH", 5.75));
        taxList.add(new Taxes("OK", 4.5));
        taxList.add(new Taxes("OR", 0.0));
        taxList.add(new Taxes("PA", 6.0));
        taxList.add(new Taxes("RI", 7.0));
        taxList.add(new Taxes("SC", 6.0));
        taxList.add(new Taxes("SD", 4.0));
        taxList.add(new Taxes("TN", 7.0));
        taxList.add(new Taxes("TX", 6.25));
        taxList.add(new Taxes("UT", 5.95));
        taxList.add(new Taxes("VT", 6.0));
        taxList.add(new Taxes("VA", 5.3));
        taxList.add(new Taxes("WA", 6.5));
        taxList.add(new Taxes("WV", 6.0));
        taxList.add(new Taxes("WI", 5.0));
        taxList.add(new Taxes("WY", 4.0));
    }

    public String[] getStates() {
        String[] states = new String[taxList.size()];
        int i = 0;
        for (Taxes x : taxList) {
            states[i] = x.getState();
            i++;
        }
        return states;
    }

    public double getTax(String stateName) {
        for (Taxes x : taxList) {
            if (x.getState().equalsIgnoreCase(stateName)) {
                return x.getTaxRate();
            }
        }
        return -1;
    }

}

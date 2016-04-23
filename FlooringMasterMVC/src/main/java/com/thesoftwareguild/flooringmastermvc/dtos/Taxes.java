/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.dtos;

/**
 *
 * @author apprentice
 */
public class Taxes {
    private String state;
    private double taxRate;

    public Taxes(String state, double taxRate){
        this.state = state;
        this.taxRate = taxRate;   
    }
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @return the taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
    
}

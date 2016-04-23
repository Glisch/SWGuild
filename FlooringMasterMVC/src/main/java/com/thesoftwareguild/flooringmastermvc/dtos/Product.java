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
public class Product {
    private String productType;
    private double costSqFt;
    private double laborCostSqFt;
    
    public Product(String productType, double costSqFt, double laborCostSqFt){
        this.productType = productType;
        this.costSqFt = Math.ceil(costSqFt*100)/100;
        this.laborCostSqFt = Math.ceil(laborCostSqFt*100)/100;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @return the costSqFt
     */
    public double getCostSqFt() {
        return costSqFt;
    }

    /**
     * @return the laborCostSqFt
     */
    public double getLaborCostSqFt() {
        return laborCostSqFt;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @param costSqFt the costSqFt to set
     */
    public void setCostSqFt(double costSqFt) {
        this.costSqFt = Math.ceil(costSqFt*100)/100;
    }

    /**
     * @param laborCostSqFt the laborCostSqFt to set
     */
    public void setLaborCostSqFt(double laborCostSqFt) {
        this.laborCostSqFt = Math.ceil(laborCostSqFt*100)/100;
    }
}

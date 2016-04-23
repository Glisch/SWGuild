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
public class Order {
    private Integer orderNum;
    private String name;
    private String state;
    private double taxRate;
    private String productType;
    private double area;
    private double costSqFt;
    private double laborCostSqFt;
    private double materialCost;
    private double laborCost;
    private double taxCost;
    private double totalCost;
    private String date;
    
 
    public Order(Integer orderNum, String name, String state, double taxRate, String productType, double area, double costSqFt, double laborCostSqFt, double materialCost, double laborCost, double taxCost, double totalCost, String date){
        this.orderNum = orderNum;
        this.name = name;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costSqFt = Math.ceil(costSqFt*100)/100;
        this.laborCostSqFt = Math.ceil(laborCostSqFt*100)/100;
        this.materialCost = Math.ceil(materialCost*100)/100;
        this.laborCost = Math.ceil(laborCost*100)/100;
        this.taxCost = Math.ceil(taxCost*100)/100;
        this.totalCost = Math.ceil(totalCost*100)/100;
        this.date = date;
    }
    
    /**
     * @return the orderNum
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum the orderNum to set
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the costSqFt
     */
    public double getCostSqFt() {
        return costSqFt;
    }

    /**
     * @param costSqFt the costSqFt to set
     */
    public void setCostSqFt(double costSqFt) {
        this.costSqFt = Math.ceil(costSqFt*100)/100;
    }

    /**
     * @return the laborCostSqFt
     */
    public double getLaborCostSqFt() {
        return laborCostSqFt;
    }

    /**
     * @param laborCostSqFt the laborCostSqFt to set
     */
    public void setLaborCostSqFt(double laborCostSqFt) {
        this.laborCostSqFt = Math.ceil(laborCostSqFt*100)/100;
    }

    /**
     * @return the materialCost
     */
    public double getMaterialCost() {
        return materialCost;
    }

    /**
     * @param materialCost the materialCost to set
     */
    public void setMaterialCost(double materialCost) {
        this.materialCost = Math.ceil(materialCost*100)/100;
    }

    /**
     * @return the laborCost
     */
    public double getLaborCost() {
        return laborCost;
    }

    /**
     * @param laborCost the laborCost to set
     */
    public void setLaborCost(double laborCost) {
        this.laborCost = Math.ceil(laborCost*100)/100;
    }

    /**
     * @return the taxCost
     */
    public double getTaxCost() {
        return taxCost;
    }

    /**
     * @param taxCost the taxCost to set
     */
    public void setTaxCost(double taxCost) {
        this.taxCost = Math.ceil(taxCost*100)/100;
    }

    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = Math.ceil(totalCost*100)/100; 
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getIsoDate(){
        
        String isoDate = this.date.substring(6, 10) +"-" +this.date.substring(0, 2) +"-" +this.date.substring(3, 5);
        
        return isoDate;
    }
    
}
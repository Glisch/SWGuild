/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.dtos;

import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Christopher Glisch
 */
public class CommandObject {
    @NotEmpty(message = "Must input a customer name")
    private String name;
    @NotEmpty(message = "Must select a product choice")
    private String product;
    @Min(value = 1, message = "Area must be more than 1 sq/ft")
    private Double area;
    @NotEmpty(message = "Must selct a state")
    private String state;
    @NotEmpty(message = "Must select a date")
    private String date;

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

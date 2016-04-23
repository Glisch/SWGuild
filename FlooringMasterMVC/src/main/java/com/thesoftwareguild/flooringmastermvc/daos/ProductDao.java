/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.daos;


import com.thesoftwareguild.flooringmastermvc.dtos.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ProductDao {


    private List<Product> productList = new ArrayList<>();
    

    public ProductDao() {
       productList.add(new Product("Carpet", 1.25, 2.50));
       productList.add(new Product("Wood", 3.75, 6.25));
       productList.add(new Product("Tile", 2.50, 5.00));
       
    }


    public String[] getProducts() {
        String[] products = new String[productList.size()];
        int i = 0;
        for (Product x : productList) {
            products[i] = x.getProductType();
            i++;
        }
        return products;
    }

    public String[] getProductsCosts() {
        String[] products = new String[productList.size()];
        int i = 0;
        for (Product x : productList) {
            products[i] = x.getProductType() + " Material: " + x.getCostSqFt() + " Labor: " + x.getLaborCostSqFt();
            i++;
        }
        return products;
    }

    public Product getProduct(String productName) {
        for (Product x : productList) {
            if (x.getProductType().equalsIgnoreCase(productName)) {
                return x;
            }
        }
        return (new Product("null", -1, -1));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.daos;

import com.thesoftwareguild.flooringmastermvc.dtos.Order;
import java.util.List;

/**
 *
 * @author Christopher Glisch
 */
public interface FlooringDao {

    void createOrder(Order currentOrder);

    void deleteOrder(Order currentOrder);

    String getFileName(Order currentOrder);

    List<Order> getOrdersForDate(String date);

    Order getOrder(Integer orderNum, String date);
    
    List<String> getDates();
    
    List<String> getActiveFiles();
    
    Integer getNextId(String date);

    void updateOrder(Order currentOrder, double laborCostSqFt, double costSqFt, double tax);
    
    List<Order> getOrderList();
    
}

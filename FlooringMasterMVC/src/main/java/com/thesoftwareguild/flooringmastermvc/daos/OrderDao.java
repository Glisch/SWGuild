/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.daos;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.thesoftwareguild.flooringmastermvc.dtos.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class OrderDao implements FlooringDao {

    public List<Order> orderList = new ArrayList<>();

    public OrderDao() {
        decode();
    }

    @Override
    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public List<Order> getOrdersForDate(String date) {

        return orderList
                .stream()
                .filter(order -> order.getDate().equals(date))
                .sorted((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()))
                .collect(Collectors.toList());

    }

    @Override
    public void createOrder(Order currentOrder) {

        currentOrder.setOrderNum(getNextId(currentOrder.getDate()));
        orderList.add(currentOrder);
        encode();

    }

    @Override
    public Integer getNextId(String date) {
        int fileNextId = 1;

        if (getOrdersForDate(date).isEmpty()) {
            return fileNextId;
        }

        for (Order current : getOrdersForDate(date)) {
            if (current.getOrderNum() >= fileNextId) {
                fileNextId = current.getOrderNum() + 1;
            }
        }
        return fileNextId;
    }

    @Override
    public void deleteOrder(Order currentOrder) {
        for (Order current : orderList) {
            if (current.getDate().equals(currentOrder.getDate()) && Objects.equals(current.getOrderNum(), currentOrder.getOrderNum())) {
                orderList.remove(current);
                break;
            }
        }
        encode();
        deleteFile();
    }

    @Override
    public void updateOrder(Order currentOrder, double laborCostSqFt, double costSqFt, double tax) {
        currentOrder.setLaborCostSqFt(laborCostSqFt);
        currentOrder.setCostSqFt(costSqFt);
        currentOrder.setTaxRate(tax);

        currentOrder.setMaterialCost(currentOrder.getCostSqFt() * currentOrder.getArea());
        currentOrder.setLaborCost(currentOrder.getLaborCostSqFt() * currentOrder.getArea());
        currentOrder.setTaxCost((currentOrder.getTaxRate() / 100.0) * (currentOrder.getLaborCost() + currentOrder.getMaterialCost()));
        currentOrder.setTotalCost(currentOrder.getLaborCost() + currentOrder.getMaterialCost() + currentOrder.getTaxCost());
        encode();
        deleteFile();

    }

    @Override
    public Order getOrder(Integer orderNum, String date) {
        for (Order current : orderList) {
            if (current.getDate().equals(date) && Objects.equals(current.getOrderNum(), orderNum)) {
                return current;
            }
        }
        return null;
    }

    @Override
    public String getFileName(Order currentOrder) {
        return "Orders_" + currentOrder.getDate().replaceAll("-", "") + ".txt";
    }

    public void deleteFile() {
        File dir = new File(System.getProperty("user.dir"));
        File[] foundFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("Orders_");
            }
        });
        for (File file : foundFiles) {
            String filename = file.toString().substring(file.toString().length() - 19);

            if (!getActiveFiles().contains(filename)) {
                try {
                    Files.delete(file.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    @Override
    public List<String> getDates() {
        
        List<String> dateList = new ArrayList();
        
        for (Order order : orderList) {
            if (!dateList.contains(order.getDate())) {
                dateList.add(order.getDate());
            }
        }
        
        return dateList
                .stream()
                .sorted((o1, o2) -> o2.substring(6).compareTo(o1.substring(6)))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getActiveFiles() {
        List<String> fileList = new ArrayList<>();
        for (Order x : orderList) {
            if (!fileList.contains(getFileName(x))) {
                fileList.add(getFileName(x));
            }
        }
        return fileList;
    }

    public void decode() {

        File dir = new File(System.getProperty("user.dir"));
        File[] foundFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("Orders_");
            }
        });

        for (File file : foundFiles) {
            try {
                CSVReader reader = new CSVReader(new FileReader(file), ',', '"', '\0');
                String[] values;
                reader.readNext();

                while ((values = reader.readNext()) != null) {
                    String date = file.toString().substring((file.toString().length() - 12), (file.toString().length() - 4));
                    date = date.substring(0, 2) +"-" +date.substring(2, 4) +"-" +date.substring(4);
                    this.orderList.add(new Order(Integer.parseInt(values[0]), values[1], values[2], Double.parseDouble(values[3]), values[4], Double.parseDouble(values[5]), Double.parseDouble(values[6]), Double.parseDouble(values[7]), Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]), date));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void encode() {
        try {
            for (Order current : orderList) {
                CSVWriter writer = new CSVWriter(new FileWriter(getFileName(current)), ',');
                writer.writeNext(new String[]{"OrderNumber", "CustomerName", "State", "TaxRate", "ProductType", "Area", "CostPerSquareFoot", "LaborCostPerSquareFoot", "MaterialCost", "LaborCost", "Tax", "Total"});

                for (Order currentOrder : getOrdersForDate(current.getDate())) {
                    String[] line = new String[]{
                        currentOrder.getOrderNum().toString(),
                        currentOrder.getName(),
                        currentOrder.getState(),
                        "" + currentOrder.getTaxRate(),
                        currentOrder.getProductType(),
                        "" + currentOrder.getArea(),
                        "" + currentOrder.getCostSqFt(),
                        "" + currentOrder.getLaborCostSqFt(),
                        "" + currentOrder.getMaterialCost(),
                        "" + currentOrder.getLaborCost(),
                        "" + currentOrder.getTaxCost(),
                        "" + currentOrder.getTotalCost()};
                    writer.writeNext(line);
                    writer.flush();
                }
                writer.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

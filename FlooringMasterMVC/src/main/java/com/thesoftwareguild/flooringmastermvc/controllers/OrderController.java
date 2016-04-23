package com.thesoftwareguild.flooringmastermvc.controllers;

import com.thesoftwareguild.flooringmastermvc.daos.FlooringDao;
import com.thesoftwareguild.flooringmastermvc.daos.ProductDao;
import com.thesoftwareguild.flooringmastermvc.daos.TaxesDao;
import com.thesoftwareguild.flooringmastermvc.dtos.CommandObject;
import com.thesoftwareguild.flooringmastermvc.dtos.Order;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMddyyyy");

    private FlooringDao orderDao;
    private ProductDao productDao;
    private TaxesDao taxesDao;

    @Inject
    public OrderController(FlooringDao dao, ProductDao pdao, TaxesDao tdao) {
        this.orderDao = dao;
        this.productDao = pdao;
        this.taxesDao = tdao;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Order add(@Valid @RequestBody CommandObject input) {

        double taxRate = taxesDao.getTax(input.getState());
        double costSqFt = productDao.getProduct(input.getProduct()).getCostSqFt();
        double laborCostSqFt = productDao.getProduct(input.getProduct()).getLaborCostSqFt();
        double materialCost = costSqFt * input.getArea();
        double laborCost = laborCostSqFt * input.getArea();
        double taxCost = (taxRate / 100.0) * (laborCost + materialCost);
        double totalCost = laborCost + materialCost + taxCost;

        String filename = "Orders_" + input.getDate().substring(5, 7) + input.getDate().substring(8, 10) + input.getDate().substring(0, 4) + ".txt";
        Integer orderNum = orderDao.getNextId(filename);

        String date = input.getDate().substring(5, 7) + "-" + input.getDate().substring(8, 10) + "-" + input.getDate().substring(0, 4);

        Order order = new Order(orderNum, input.getName(), input.getState(), taxRate, input.getProduct(), input.getArea(), costSqFt, laborCostSqFt, materialCost, laborCost, taxCost, totalCost, date);

        orderDao.createOrder(order);

        return order;
    }

    @RequestMapping(value = "/view/{date}")
    public String show(@PathVariable("date") String date, Model model) {

        List<Order> orderList = orderDao.getOrdersForDate(date);

        model.addAttribute("orders", orderList);

        return "orders";
    }

    @RequestMapping(value = "/{order}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("order") String order) {
        Integer id = Integer.parseInt(order.substring(11));
        String date = order.substring(0, 10);
        orderDao.deleteOrder(orderDao.getOrder(id, date));
    }

    @RequestMapping(value = "/{oldDate}/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Order edit(@Valid @RequestBody CommandObject input, @PathVariable("id") Integer id, @PathVariable("oldDate") String oldDate) {

        String date = input.getDate().substring(5, 7) + "-" + input.getDate().substring(8, 10) + "-" + input.getDate().substring(0, 4);

        Order order = orderDao.getOrder(id, oldDate);

        order.setName(input.getName());
        order.setState(input.getState());
        order.setProductType(input.getProduct());
        order.setCostSqFt(productDao.getProduct(order.getProductType()).getCostSqFt());
        order.setLaborCostSqFt(productDao.getProduct(order.getProductType()).getLaborCostSqFt());
        order.setArea(input.getArea());
        if (!date.equals(oldDate)) {
            order.setOrderNum(orderDao.getNextId(date));
            order.setDate(date);
        }

        orderDao.updateOrder(order, order.getLaborCostSqFt(), order.getCostSqFt(), order.getTaxRate());
        return order;
    }

    @RequestMapping(value = "/{date}/{id}")
    @ResponseBody
    public Order viewSingleOrder(@PathVariable("date") String date, @PathVariable("id") Integer id) {

        Order order = orderDao.getOrder(id, date);
        List<Object> stuff = new ArrayList();

        stuff.add(order);
        stuff.add(productDao.getProducts());
        stuff.add(taxesDao.getStates());

        return order;

    }

    @RequestMapping(value = "/prods")
    @ResponseBody
    public String[] getProds() {
        String[] prods = productDao.getProducts();
        return prods;
    }

    @RequestMapping(value = "/states")
    @ResponseBody
    public String[] getStates() {
        return taxesDao.getStates();
    }

    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam("search") String search) {

        List<Order> orders = new ArrayList();

        orderDao.getOrderList()
                .stream()
                .forEach((Order order) -> {
                    if (Integer.toString(order.getOrderNum()).equals(search) && !orders.contains(order)) {
                        orders.add(order);
                    }
                    if (order.getName().toLowerCase().contains(search.toLowerCase()) && !orders.contains(order)) {
                        orders.add(order);
                    }
                    if (order.getState().toLowerCase().contains(search.toLowerCase()) && !orders.contains(order)) {
                        orders.add(order);
                    }
                    if (order.getProductType().toLowerCase().contains(search.toLowerCase()) && !orders.contains(order)) {
                        orders.add(order);
                    }
                });

        model.addAttribute("orders", orders);

        return "orders";
    }

}


//
//<table class="table" id="dateTable"> 
//                    <c:forEach items="${dates}" var="date">
//                        <tr id="order-row-${date}">
//                            <td><a href="${pageContext.request.contextPath}/order/view/${date}">${date}</a></td>
//                        </tr>
//                    </c:forEach>
//                    </table>

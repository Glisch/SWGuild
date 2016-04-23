<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flooring Master</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>

    <body>
        <div class="container">
            <jsp:include page="navBar.jsp" ></jsp:include>

                <div class="row">
                    <div class="col-md-2">

                    </div>
                    <div class="col-md-8"> 

                        <h2>Results:</h2>

                        <table id="orderTable" class="table table-striped">

                            <tr>
                                <th>Order Date:</th><th>Order #:</th><th>Name:</th><th>Product:</th><th>State</th><th>Tax:</th><th>Total Cost:</th>
                            </tr>
                        <c:forEach items="${orders}" var="order">
                            <tr id="order-row-${order.date}-${order.orderNum}">
                                <td>${order.date}</td><td><a data-order-id="${order.orderNum}" data-order-date="${order.date}" data-toggle="modal" data-target="#showOrderModal">${order.orderNum}</a></td><td><a data-order-id="${order.orderNum}" data-order-date="${order.date}" data-toggle="modal" data-target="#showOrderModal">${order.name}</a></td><td>${order.productType}</td><td>${order.state}</td><td>${order.taxCost}</td><td>${order.totalCost}</td>
                                <td><a data-order-id="${order.orderNum}" data-order-date="${order.date}" data-toggle="modal" data-target="#editOrderModal">Edit</a></td>
                                <td><a data-order-row="${order.date}-${order.orderNum}" class="delete-link">Delete<td>
                            </tr>
                        </c:forEach>

                    </table>

                </div>
            </div>
        </div>


        <div id="showOrderModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Order Details</h4>
                    </div>

                    <div class="modal-body">

                        <center><h2>${order.date} #${order.orderNum} Details</h2></center>
                        <br />

                        <div class="row">
                            <div class="col-md-2">

                            </div>
                            <div class="col-md-4">
                                <table>
                                    <tr><th style="text-align: right;">Name:</th><th style="padding-left: 15px;" id="order-name"></th></tr>
                                    <tr><th style="text-align: right;">Taxes:</th><th style="padding-left: 15px;" id="order-taxCost"></th></tr>
                                    <tr><th style="text-align: right;">Total:</th><th style="padding-left: 15px;"id="order-totalCost"></th></tr>
                                </table>
                            </div> 

                            <div class="col-md-4">
                                <table>
                                    <tr><td style="text-align: right;">Area:</td><td style="padding-left: 15px;" id="order-area"></td></tr>
                                    <tr><td style="text-align: right;">Product Type:</td><td style="padding-left: 15px;" id="order-productType"></td></tr>
                                    <tr><td style="text-align: right;">Cost per sq/ft:</td><td style="padding-left: 15px;" id="order-costSqFt"></td></tr>
                                    <tr><td style="text-align: right;">Labor Cost per sq/ft:</td><td style="padding-left: 15px;" id="order-laborCostSqFt"></td></tr>
                                    <tr><td style="text-align: right;">State:</td><td style="padding-left: 15px;" id="order-state"></td></tr>
                                    <tr><td style="text-align: right;">Tax Rate:</td><td style="padding-left: 15px;" id="order-taxRate"></td></tr>
                                    <tr><td style="text-align: right;">Material Cost:</td><td style="padding-left: 15px;" id="order-materialCost"></td></tr>
                                    <tr><td style="text-align: right;">Labor Cost:</td><td style="padding-left: 15px;" id="order-laborCost"></td></tr>
                                </table>

                            </div>         
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <!-- Modal -->
        <div id="editOrderModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Contact</h4>
                    </div>

                    <div class="modal-body">
                        <h2>Edit Order:</h2>

                        <div class="form-group">
                            <div class="col-md-10">
                                <input type="text" name="name" class="form-control" id="edit-name" placeholder="Customer Name" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <select name="product" class="form-control" id="edit-productType">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <input type="number" min="1" name="area" class="form-control" id="edit-area" placeholder="Area sq/ft" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <select name="state" class="form-control" id="edit-state">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <input type="date" name="date" class="form-control" id="edit-date" />
                            </div>
                        </div>


                        <input type="hidden" id="order-id" />
                        <input type="hidden" id="order-date" />
                        
                        <div id="edit-order-validation-errors"></div>

                        <button id="edit-contact-button" class="btn btn-default">Edit Contact</button>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>



        <script type="text/javascript">
            var contextRoot = "${pageContext.request.contextPath}";
        </script>


        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
    </body>
</html>

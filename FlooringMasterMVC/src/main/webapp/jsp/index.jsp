<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
                <form:form class="form-horizontal" action="${pageContext.request.contextPath}/order/search" method="post">
                    <div class="form-group">
                        <label for="search" class="col-md-9 control-label"></label>
                        <div class="col-md-3">
                            <input type="text" name="search" class="form-control pull-right" id="search" placeholder="Search"/>


                        </div>
                    </div>
                </form:form>
            </div>


            <div class="row">
                <div class="col-md-6">

                    <center><h2>Add Order</h2></center>

                    <form id="create-form" class="form-horizontal" >

                        <div class="form-group">
                            <div class="col-md-10">
                                <input type="text" name="name" class="form-control" id="add-name" placeholder="Customer Name" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <select name="product" class="form-control" id="select-product">
                                    <option value="" disabled selected>Select Product</option>
                                    <c:forEach items="${products}" var="prod">
                                        <option value="${prod}">${prod}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <input type="number" min="1" name="area" class="form-control" id="add-area" placeholder="Area sq/ft" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <select name="state" class="form-control" id="select-state">
                                    <option value="" disabled selected>Select State</option>
                                    <c:forEach items="${states}" var="state">
                                        <option value="${state}">${state}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <input type="date" name="date" class="form-control" id="add-date" />
                            </div>
                        </div>

                        <div id="add-order-validation-errors"></div>
                        
                        <input type="submit" class="btn btn-default" value="Add Order" />

                    </form>
                </div>




                <div class="col-md-6"> 

                    <center><h2>Order Dates:</h2></center>

                    <table class="table" id="dateTable">
                        <c:forEach begin="0" end="${fn:length(dates)}" step="3" varStatus="loop">
                            <tr id="order-row-${date}">
                                <td style="padding: 25px;"><a href="${pageContext.request.contextPath}/order/view/${dates[loop.index]}">${dates[loop.index]}</a></td>
                                <td style="padding: 25px;"><a href="${pageContext.request.contextPath}/order/view/${dates[loop.index + 1]}">${dates[loop.index + 1]}</a></td>
                                <td style="padding: 25px;"><a href="${pageContext.request.contextPath}/order/view/${dates[loop.index + 2]}">${dates[loop.index + 2]}</a></td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </div>

        <script type="text/javascript">
            var contextRoot = "${pageContext.request.contextPath}";
        </script>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
    </body>
</html>


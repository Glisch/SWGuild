<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search DVD</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>

    <body>
        <div class="container">
            <div class="row">

                <jsp:include page="navBar.jsp" ></jsp:include>

                    <div class="col-md-6"> 

                        <h2>DVD Search</h2> 
                        

                        <table id="contactTable" class="table">

                            <tr>
                                <th width="100%">Title</th>                               
                            </tr>

                        <c:forEach items="${dvds}" var="dvd">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/dvd/view/${dvd.id}">${dvd.title}</a></td>
                            </tr>

                        </c:forEach>

                    </table>

                </div>
                
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>


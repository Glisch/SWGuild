<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit DVD</title>
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

                    
                <div class="col-md-6">

                    <center><h2>Edit DVD</h2></center>

                    <form:form class="form-horizontal" commandName="dvd" action="${pageContext.request.contextPath}/dvd/edit/${dvd.id}" method="post">

                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">Title:</label>
                            <div class="col-md-8">
                                <form:input path="title" type="text" name="title" class="form-control" id="add-title" placeholder="Title" />
                                <form:errors path="title" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-release-date" class="col-md-4 control-label">Release Date:</label>
                            <div class="col-md-8">
                                <form:input path="releaseDate" type="date" name="release" class="form-control" id="add-release-date" />
                                <form:errors path="releaseDate" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-mpaa-rating" class="col-md-4 control-label">MPAA Rating:</label>
                            <div class="col-md-8">
                                <form:input path="mpaaRating" type="text" name="mpaaRating" class="form-control" id="add-mpaa-rating" placeholder="MPAA Rating" />
                                <form:errors path="mpaaRating" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">Director</label>
                            <div class="col-md-8">
                                <form:input path="director" type="text" name="director" class="form-control" id="add-director" placeholder="Director" />
                                <form:errors path="director" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-studio" class="col-md-4 control-label">Studio: </label>
                            <div class="col-md-8">
                                <form:input path="studio" type="text" name="studio" class="form-control" id="add-studio" placeholder="Studio" />
                                <form:errors path="studio" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-note" class="col-md-4 control-label">Note: </label>
                            <div class="col-md-8">
                                <form:textarea path="note" type="text" name="note" class="form-control" id="add-note" placeholder="Note" />
                                <form:errors path="note" />
                            </div>
                        </div>

                        <input type="submit" class="btn btn-default pull-right" value="Edit DVD" />
                    </form:form>
                </div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>


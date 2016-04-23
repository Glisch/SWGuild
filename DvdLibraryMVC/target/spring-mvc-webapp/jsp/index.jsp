<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD List</title>
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

                        <center><h2>DVD List</h2></center> 

                        <table id="dvdTable" class="table">

                            <tr>
                                <th width="70%">Title</th>                               
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>

                        <c:forEach items="${dvdList}" var="dvd">
                            <tr id="dvd-row-${dvd.id}">
                                <td><a data-dvd-id="${dvd.id}" data-toggle="modal" data-target="#showDvdModal">${dvd.title}</a></td>
                                <td><a data-dvd-id="${dvd.id}" data-toggle="modal" data-target="#editDvdModal">Edit</a></td>
                                <td><a data-dvd-id="${dvd.id}" class="delete-link">Delete</a><td>
                            </tr>

                        </c:forEach>

                    </table>

                </div>

                <div class="col-md-6">
                    <div class="row">
                        <center><h2>Search DVDs</h2></center>

                        <form:form class="form-horizontal" action="${pageContext.request.contextPath}/dvd/search" method="post">
                            <div class="form-group">
                                <label for="search" class="col-md-4 control-label">Search: </label>
                                <div class="col-md-8">
                                    <input type="text" name="search" class="form-control" id="search" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="selectors" class="col-md-4 control-label">Search By: </label>
                                <div class="col-md-8">
                                    <label class="radio-inline"><input type="radio" name="optradio" value="title" checked>Title</label>
                                    <label class="radio-inline"><input type="radio" name="optradio" value="rating">Rating</label>
                                    <label class="radio-inline"><input type="radio" name="optradio" value="studio">Studio</label>
                                </div>
                            </div>

                            <input type="submit" class="btn btn-default pull-right" value="Search" />


                        </form:form>

                    </div>

                    <div class="row">
                        <center><h2>Add New DVD</h2></center>

                        <form id="create-form" class="form-horizontal">

                            <div class="form-group">
                                <label for="add-title" class="col-md-4 control-label">Title:</label>
                                <div class="col-md-8">
                                    <input type="text" name="title" class="form-control" id="add-title" placeholder="Title" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-release-date" class="col-md-4 control-label">Release Date:</label>
                                <div class="col-md-8">
                                    <input type="date" name="releaseDate" class="form-control" id="add-release-date" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-mpaa-rating" class="col-md-4 control-label">MPAA Rating:</label>
                                <div class="col-md-8">
                                    <input type="text" name="mpaaRating" class="form-control" id="add-mpaa-rating" placeholder="MPAA Rating" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-director" class="col-md-4 control-label">Director</label>
                                <div class="col-md-8">
                                    <input type="text" name="director" class="form-control" id="add-director" placeholder="Director" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-studio" class="col-md-4 control-label">Studio: </label>
                                <div class="col-md-8">
                                    <input type="text" name="studio" class="form-control" id="add-studio" placeholder="Studio" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-note" class="col-md-4 control-label">Note: </label>
                                <div class="col-md-8">
                                    <textarea name="note" class="form-control" id="add-note" placeholder="Note"></textarea>
                                </div>
                            </div>

                            <div id="add-contact-validation-errors"></div>
                            
                            <input type="submit" class="btn btn-default pull-right" value="Add DVD" />

                        </form>
                    </div>
                </div>
            </div>
        </div>



        <div id="showDvdModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">DVD Details</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered">

                            <tr>
                                <th>Title:</th>
                                <td id="dvd-title"></td>
                            </tr>
                            <tr>
                                <th>Release Date</th>
                                <td id="dvd-releaseDate"></td>
                            </tr>
                            <tr>
                                <th>MPAA Rating:</th>
                                <td id="dvd-mpaaRating"></td>
                            </tr>
                            <tr>
                                <th>Director:</th>
                                <td id="dvd-director"></td>
                            </tr>
                            <tr>
                                <th>Studio:</th>
                                <td id="dvd-studio"></td>
                            </tr>
                            <tr>
                                <th>Note:</th>
                                <td id="dvd-note"></td>
                            </tr>

                        </table>




                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>

        <div id="editDvdModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Dvd</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered">

                            <tr>
                                <th>Title:</th>
                                <td>
                                    <input type="text" id="edit-title" />
                                </td>
                            </tr>
                            <tr>
                                <th>Release Date:</th>
                                <td>
                                    <input type="text" id="edit-release-date" />
                                </td>
                            </tr>
                            <tr>
                                <th>MPAA Rating:</th>
                                <td>
                                    <input type="text" id="edit-mpaa-rating" />
                                </td>
                            </tr>
                            <tr>
                                <th>Director:</th>
                                <td>
                                    <input type="text" id="edit-director" />
                                </td>
                            </tr>
                            <tr>
                                <th>Studio:</th>
                                <td>
                                    <input type="text" id="edit-studio" />
                                </td>
                            </tr>
                            <tr>
                                <th>Note:</th>
                                <td>
                                    <input type="text" id="edit-note" />
                                </td>
                            </tr>

                        </table>
                        
                        <div id="edit-contact-validation-errors"></div>

                        <input type="hidden" id="edit-id" />
                        <button id="edit-dvd-button" class="btn btn-default" value="Edit DVD">Edit DVD</button>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>


        <script type="text/javascript">
            var contextRoot = '${pageContext.request.contextPath}';
        </script>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
    </body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    
    <body>
        <div class="container">
            <jsp:include page="navbar.jsp" />

            <div class="row">
                <div class="col-md-6">

                    <center><h2>Address Book</h2></center>

                    <table id="addressesTable" class="table">
                        <thead>
                            <tr>
                                <th width="30%">Name</th>
                                <th width="50%">Address</th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${addressList}" var="address">
                                <tr id="address-row-${address.person.id}-${address.id}">
                                    <td>${address.person.lastName}, ${address.person.firstName}</td>
                                    <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#showAddressModal">${address.street}, ${address.city}, ${address.state} ${address.zip}</a></td>
                                    <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#editAddressModal">Edit</a></td>
                                    <td><a data-address-id="${address.person.id}-${address.id}"  class="delete-link">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-6">
                    <div class="row">
                        <center><h2>Search Addresses</h2></center>

                        <form:form class="form-horizontal" action="${pageContext.request.contextPath}/address/search" method="post">
                            <div class="form-group">
                                <label for="search" class="col-md-4 control-label">Search: </label>
                                <div class="col-md-8">
                                    <input type="text" name="search" class="form-control" id="search" />
                                </div>
                            </div>

                            <input type="submit" class="btn btn-default pull-right" value="Search" />

                        </form:form>
                    </div>

                    <div class="row">
                        <center><h2>Create Address</h2></center>

                        <form id="create-form" class="form-horizontal">

                            <div class="form-group">
                                <label for="add-first-name" class="col-md-4 control-label">First Name</label>
                                <div class="col-md-8">
                                    <input type="text" name="firstName" class="form-control" id="add-first-name" placeholder="First Name" autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-last-name" class="col-md-4 control-label">Last Name</label>
                                <div class="col-md-8">
                                    <input type="text" name="lastName" class="form-control" id="add-last-name" placeholder="Last Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-street" class="col-md-4 control-label">Street Address</label>
                                <div class="col-md-8">
                                    <input type="text" name="street" class="form-control" id="add-street" placeholder="Street Address">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-city" class="col-md-4 control-label">City</label>
                                <div class="col-md-8">
                                    <input type="text" name="city" class="form-control" id="add-city" placeholder="City">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-state" class="col-md-4 control-label">State</label>
                                <div class="col-md-8">
                                    <input type="text" name="state" class="form-control" id="add-state" placeholder="State">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-zip" class="col-md-4 control-label">Zip-code</label>
                                <div class="col-md-8">
                                    <input type="text" name="zip" class="form-control" id="add-zip" placeholder="Zipcode">
                                </div>
                            </div>

                            <div id="add-address-validation-errors"></div>

                            <input type="submit" class="btn btn-default pull-right" value="Create Address" />

                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="showAddressModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Address Details</h4>
                    </div>

                    <div class="modal-body">
                        <table class="table table-bordered">
                            <tr>
                                <th>First Name:</th>
                                <td id="address-firstName"></td>
                            </tr>
                            <tr>
                                <th>Last Name:</th>
                                <td id="address-lastName"></td>
                            </tr>
                            <tr>
                                <th>Street Address:</th>
                                <td id="address-street"></td>
                            </tr>
                            <tr>
                                <th>City:</th>
                                <td id="address-city"></td>
                            </tr>
                            <tr>
                                <th>State:</th>
                                <td id="address-state"></td>
                            </tr>
                            <tr>
                                <th>Zip:</th>
                                <td id="address-zip"></td>
                            </tr>
                        </table>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>


        <div id="editAddressModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Address</h4>
                    </div>

                    <div class="modal-body">

                        <table class="table">
                            <tr>
                                <th><input onclick="javascript:radioCheck();" type="radio" name="type" id="radioEditName" checked> Edit name</th>
                                <th><input onclick="javascript:radioCheck();" type="radio" name="type" id="radioEditAddress"> Edit this address</th>
                                <th><input onclick="javascript:radioCheck();" type="radio" name="type" id="radioNew"> Add new address</th>
                            </tr>
                        </table>

                        <div id="modalEditName" style="display: block;">
                            <table class="table table-bordered">
                                <tr>
                                    <th>First Name:</th>
                                    <td><input type="text" id="edit-name-first-name" /></td>
                                </tr>
                                <tr>
                                    <th>Last Name:</th>
                                    <td><input type="text" id="edit-name-last-name" /></td>
                                </tr>
                            </table>

                            <input type="hidden" id="edit-name-id" />
                            <input type="hidden" id="edit-name-street" />
                            <input type="hidden" id="edit-name-city" />
                            <input type="hidden" id="edit-name-state" />
                            <input type="hidden" id="edit-name-zip" />

                            <div id="edit-name-validation-errors"></div>

                            <button id="edit-name-button" class="btn btn-default">Edit Name</button>

                        </div>

                        <div id="modalEditAddress" style="display: none;">
                            <table class="table table-bordered">
                                <tr>
                                    <th>Street Address:</th>
                                    <td><input type="text" id="edit-street" /></td>
                                </tr>
                                <tr>
                                    <th>City:</th>
                                    <td><input type="text" id="edit-city" /></td>
                                </tr>
                                <tr>
                                    <th>State:</th>
                                    <td><input type="text" id="edit-state" /></td>
                                </tr>
                                <tr>
                                    <th>Zip:</th>
                                    <td><input type="text" id="edit-zip" /></td>
                                </tr>
                            </table>

                            <input type="hidden" id="edit-id" />
                            <input type="hidden" id="edit-first-name" />
                            <input type="hidden" id="edit-last-name" />

                            <div id="edit-address-validation-errors"></div>

                            <button id="edit-address-button" class="btn btn-default">Edit Address</button>

                        </div>

                        <div id="modalNew" style="display: none;">
                            <table class="table table-bordered">
                                <tr>
                                    <th>Street Address:</th>
                                    <td><input type="text" id="new-street" /></td>
                                </tr>
                                <tr>
                                    <th>City:</th>
                                    <td><input type="text" id="new-city" /></td>
                                </tr>
                                <tr>
                                    <th>State:</th>
                                    <td><input type="text" id="new-state" /></td>
                                </tr>
                                <tr>
                                    <th>Zip:</th>
                                    <td><input type="text" id="new-zip" /></td>
                                </tr>
                            </table>

                            <input type="hidden" id="new-id" />
                            <input type="hidden" id="new-first-name" />
                            <input type="hidden" id="new-last-name" />

                            <div id="new-address-validation-errors"></div>

                            <button id="new-address-button" class="btn btn-default">New Address</button>

                        </div>

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

        <script type="text/javascript">
            function radioCheck() {
                if (document.getElementById('radioEditName').checked) {
                    document.getElementById('modalEditName').style.display = 'block';
                    document.getElementById('modalEditAddress').style.display = 'none';
                    document.getElementById('modalNew').style.display = 'none';
                } else if (document.getElementById('radioEditAddress').checked) {
                    document.getElementById('modalEditName').style.display = 'none';
                    document.getElementById('modalEditAddress').style.display = 'block';
                    document.getElementById('modalNew').style.display = 'none';
                } else {
                    document.getElementById('modalEditName').style.display = 'none';
                    document.getElementById('modalEditAddress').style.display = 'none';
                    document.getElementById('modalNew').style.display = 'block';
                }
            }
        </script>


        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/tinymce/tinymce.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#mytextarea',
                plugins: 'image',
                menubar: 'insert'
            });
        </script>

    </body>
</html>


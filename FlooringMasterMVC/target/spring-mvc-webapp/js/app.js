
$(document).ready(function () {


    $(document).on('submit', '#create-form', function (e) {

        e.preventDefault();
        
        $('#add-order-validation-errors').empty();

        var orderData = JSON.stringify({
            name: $('#add-name').val(),
            product: $('#select-product').val(),
            area: $('#add-area').val(),
            state: $('#select-state').val(),
            date: $('#add-date').val()
        });

        $.ajax({
            type: 'POST',
            url: contextRoot + "/order",
            data: orderData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {

            window.location.replace("http://localhost:8080/FlooringMasterMVC/order/view/" + data.date);

        }).error(function (data, status) {
            var errors = data.responseJSON.errors;

            $.each(errors, function (index, validationError) {
                $('#add-order-validation-errors').append(validationError.fieldName + ": " + validationError.message).append("<br/>");
            });

        });
    });

    $('#showOrderModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);
        var orderId = link.data('order-id');
        var orderDate = link.data('order-date');
        var modal = $(this);
        $.ajax({
            type: 'GET',
            url: contextRoot + "/order/" + orderDate + "/" + orderId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }

        }).success(function (order, status) {

            modal.find('#order-name').text(order.name);
            modal.find('#order-taxCost').text(order.taxCost);
            modal.find('#order-totalCost').text(order.totalCost);
            modal.find('#order-area').text(order.area);
            modal.find('#order-productType').text(order.productType);
            modal.find('#order-costSqFt').text(order.costSqFt);
            modal.find('#order-laborCostSqFt').text(order.laborCostSqFt);
            modal.find('#order-state').text(order.state);
            modal.find('#order-taxRate').text(order.taxRate);
            modal.find('#order-materialCost').text(order.materialCost);
            modal.find('#order-laborCost').text(order.laborCost);
        }).error(function (order, status) {
            console.log(order);
        });
    });


    $('#editOrderModal').on('show.bs.modal', function (e) {
        
        $('#edit-order-validation-errors').empty();

        var link = $(e.relatedTarget);
        var orderId = link.data('order-id');
        var orderDate = link.data('order-date');
        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: contextRoot + "/order/" + orderDate + "/" + orderId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (order, status) {

            $.ajax({
                type: 'GET',
                url: contextRoot + "/order/" + "prods",
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                }
            }).success(function (prods, status) {

                $.ajax({
                    type: 'GET',
                    url: contextRoot + "/order/" + "states",
                    dataType: 'json',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("Accept", "application/json");
                    }
                }).success(function (states, status) {
                    
                    modal.find('#edit-name').val(order.name);
                    modal.find('#edit-area').val(order.area);
                    

                    for (var i = 0; i < prods.length; i++) {
                        var opt = document.createElement('option');
                        opt.value = prods[i];
                        opt.innerHTML = prods[i];
                        document.getElementById('edit-productType').appendChild(opt);
                    }

                    modal.find('#edit-productType').val(order.productType);


                    for (var i = 0; i < states.length; i++) {
                        var opt = document.createElement('option');
                        opt.value = states[i];
                        opt.innerHTML = states[i];
                        document.getElementById('edit-state').appendChild(opt);
                    }

                    modal.find('#edit-state').val(order.state);
                    modal.find('#edit-date').val(order.isoDate);
                    $('#order-id').val(orderId);
                    $('#order-date').val(orderDate);

                }).error(function (order, status) {
                    console.log(order);
                });

            }).error(function (order, status) {
                console.log(order);
            });

        }).error(function (order, status) {
            console.log(order);
        });

    });

    $(document).on('click', '#edit-contact-button', function (e) {

        e.preventDefault();
        
        var orderId = $('#order-id').val();
        var orderDate = $('#order-date').val();

        $.ajax({
            type: 'PUT',
            url: contextRoot + "/order/" + orderDate + "/" + orderId,
            data: JSON.stringify({
                name: $('#edit-name').val(),
                product: $('#edit-productType').val(),
                area: $('#edit-area').val(),
                state: $('#edit-state').val(),
                date: $('#edit-date').val()
            }),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", 'application/json');
                xhr.setRequestHeader("Content-type", 'application/json');
            }
        }).success(function (data, status) {

            if (data.date !== orderDate) {

                window.location.replace("http://localhost:8080/FlooringMasterMVC/order/view/" + data.date);

            } else {

                $('#editOrderModal').modal('hide');
                var tableRow = buildOrderRow(data);
                var row = $('#order-row-' + data.orderDate + "-" + data.orderNum);
                $('#order-row-' + orderDate + "-" + data.orderNum).replaceWith($(tableRow));
            }

        }).error(function (data, status) {
            
            var errors = data.responseJSON.errors;

            $.each(errors, function (index, validationError) {
                $('#edit-order-validation-errors').append(validationError.fieldName + ": " + validationError.message).append("<br/>");
            });
            
        });
    });

    $(document).on('click', '.delete-link', function (e) {
        e.preventDefault();
        var orderRow = $(e.target).data('order-row');

        $.ajax({
            type: 'DELETE',
            url: contextRoot + '/order/' + orderRow

        }).success(function (data, status) {
            $('#order-row-' + orderRow).remove();
        });
    });


    function buildDateRow(data) {

        return "<tr id = 'order-row-'" + data.date + "'> \n\
                <td><a href='" + contextRoot + "/order/view/" + data.date + "'>" + data.date + "</a></td> \n\
                </tr>";
    }

    function buildOrderRow(data) {

        return "<tr id='order-row-" + data.date + "-" + data.orderNum + "'> \n\
                <td>" + data.date + "</td><td><a data-order-id='" + data.orderNum + "' data-order-date='" + data.date + "' data-toggle='modal' data-target='#showOrderModal'>" + data.orderNum + "</a></td> \n\
                <td><a data-order-id='" + data.orderNum + "' data-order-date='" + data.date + "' data-toggle='modal' data-target='#showOrderModal'>" + data.name + "</a></td> \n\
                <td>" + data.productType + "</td><td>" + data.state + "</td><td>" + data.taxCost + "</td><td>" + data.totalCost + "</td> \n\
                <td><a data-order-id='" + data.orderNum + "' data-order-date='" + data.date + "' data-toggle='modal' data-target='#editOrderModal'>Edit</a></td> \n\
                <td><a href='" + contextRoot + "/order/'" + data.date + "'/delete/" + data.orderNum + "'>Delete</a><td> \n\
                </tr>";
    }

//    function buildDateRow(data) {
//
//        return '< c:forEach begin = "0" end = "${fn:length(dates)}" step = "3" varStatus = "loop" > \n\
//                < tr > \n\
//                < td style = "padding: 25px;" > < a href = "${pageContext.request.contextPath}/order/view/${dates[loop.index]}" > ${dates[loop.index]} < /a></td > \n\
//                < td style = "padding: 25px;" > < a href = "${pageContext.request.contextPath}/order/view/${dates[loop.index + 1]}" > ${dates[loop.index + 1]} < /a></td > \n\
//                < td style = "padding: 25px;" > < a href = "${pageContext.request.contextPath}/order/view/${dates[loop.index + 2]}" > ${dates[loop.index + 2]} < /a></td > \n\
//                < /tr> \n\
//                < /c:forEach>';
//    }


});




$(document).ready(function () {

    $(document).on('submit', '#create-form', function (e) {

        e.preventDefault();

        $(".errorMsg").empty();

        var addressData = JSON.stringify({
            firstName: $('#add-first-name').val(),
            lastName: $('#add-last-name').val(),
            street: $('#add-street').val(),
            city: $('#add-city').val(),
            state: $('#add-state').val(),
            zip: $('#add-zip').val()
        });



        $.ajax({
            type: 'POST',
            url: contextRoot + "/address",
            data: addressData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {

            //Clear the form
            $('#add-first-name').val("");
            $('#add-last-name').val("");
            $('#add-street').val("");
            $('#add-city').val("");
            $('#add-state').val("");
            $('#add-zip').val("");


            var tableRow = buildAddressRow(data);

            $('#addressesTable').append($(tableRow));

            sortTable();


        }).error(function (data, status) {

            var errors = data.responseJSON.errors;

            $.each(errors, function (index, validationError) {
                        $('#create-entry-error-' + validationError.fieldName)
                                .append("* " + validationError.message)
                                .append("<br />");
                    });

        });


    });

    $('#showAddressModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var id = link.data('address-id');

        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: contextRoot + "/address/" + id,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (address, status) {

            modal.find('#address-firstName').text(address.person.firstName);
            modal.find('#address-lastName').text(address.person.lastName);
            modal.find('#address-street').text(address.street);
            modal.find('#address-city').text(address.city);
            modal.find('#address-state').text(address.state);
            modal.find('#address-zip').text(address.zip);

            $('#edit-id').val(id);
        });


    });

    $('#editAddressModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var id = link.data('address-id');

        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: contextRoot + "/address/" + id,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (address, status) {

            modal.find('#edit-name-first-name').val(address.person.firstName);
            modal.find('#edit-name-last-name').val(address.person.lastName);
            modal.find('#edit-name-street').val(address.street);
            modal.find('#edit-name-city').val(address.city);
            modal.find('#edit-name-state').val(address.state);
            modal.find('#edit-name-zip').val(address.zip);
            $('#edit-name-id').val(id);
            modal.find('#edit-first-name').val(address.person.firstName);
            modal.find('#edit-last-name').val(address.person.lastName);
            modal.find('#edit-street').val(address.street);
            modal.find('#edit-city').val(address.city);
            modal.find('#edit-state').val(address.state);
            modal.find('#edit-zip').val(address.zip);
            $('#edit-id').val(id);
            modal.find('#new-first-name').val(address.person.firstName);
            modal.find('#new-last-name').val(address.person.lastName);
            modal.find('#new-street').val(address.street);
            modal.find('#new-city').val(address.city);
            modal.find('#new-state').val(address.state);
            modal.find('#new-zip').val(address.zip);
            $('#new-id').val(id);

        });

    });

    $(document).on('click', '#edit-name-button', function (e) {

        e.preventDefault();
        
        $(".errorMsg").empty();

        var id = $('#edit-name-id').val();


        $.ajax({
            type: 'PUT',
            url: contextRoot + '/person/' + id,
            data: JSON.stringify({
                id: id,
                firstName: $('#edit-name-first-name').val(),
                lastName: $('#edit-name-last-name').val(),
                street: $('#edit-name-street').val(),
                city: $('#edit-name-city').val(),
                state: $('#edit-name-state').val(),
                zip: $('#edit-name-zip').val()

            }),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", 'application/json');
                xhr.setRequestHeader("Content-type", 'application/json');
            }
        }).success(function (data, status) {

            $('#editAddressModal').modal('hide');

            var rows = $('[id^=address-row-' + data.id + ']');

            $.each(rows, function (index, row) {
                $(row).children('td:first').text(data.lastName + ', ' + data.firstName);
            });

        }).error(function (data, status) {

            var errors = data.responseJSON.errors;

            $.each(errors, function (index, validationError) {
                        $('#edit-name-error-' + validationError.fieldName)
                                .append("* " + validationError.message)
                                .append("<br />");
                    });

        });
    });

    $(document).on('click', '#edit-address-button', function (e) {

        e.preventDefault();
        
        $(".errorMsg").empty();

        var id = $('#edit-id').val();

        $.ajax({
            type: 'PUT',
            url: contextRoot + '/address/' + id,
            data: JSON.stringify({
                id: id,
                firstName: $('#edit-first-name').val(),
                lastName: $('#edit-last-name').val(),
                street: $('#edit-street').val(),
                city: $('#edit-city').val(),
                state: $('#edit-state').val(),
                zip: $('#edit-zip').val()

            }),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", 'application/json');
                xhr.setRequestHeader("Content-type", 'application/json');
            }
        }).success(function (data, status) {

            $('#editAddressModal').modal('hide');

            var tableRow = buildAddressRow(data);

            $('#address-row-' + data.person.id + "-" + data.id).replaceWith($(tableRow));

        }).error(function (data, status) {

            var errors = data.responseJSON.errors;

            $.each(errors, function (index, validationError) {
                        $('#edit-entry-error-' + validationError.fieldName)
                                .append("* " + validationError.message)
                                .append("<br />");
                    });

        });
    });

    $(document).on('click', '#new-address-button', function (e) {

        e.preventDefault();
        
        $(".errorMsg").empty();

        var id = $('#edit-id').val();

        $.ajax({
            type: 'POST',
            url: contextRoot + '/address/' + id,
            data: JSON.stringify({
                id: id,
                firstName: $('#new-first-name').val(),
                lastName: $('#new-last-name').val(),
                street: $('#new-street').val(),
                city: $('#new-city').val(),
                state: $('#new-state').val(),
                zip: $('#new-zip').val()
            }),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", 'application/json');
                xhr.setRequestHeader("Content-type", 'application/json');
            }
        }).success(function (data, status) {

            $('#editAddressModal').modal('hide');

            var tableRow = buildAddressRow(data);

            $('#addressesTable').append($(tableRow));

            sortTable();

        }).error(function (data, status) {

            var errors = data.responseJSON.errors;

            $.each(errors, function (index, validationError) {
                        $('#new-address-error-' + validationError.fieldName)
                                .append("* " + validationError.message)
                                .append("<br />");
                    });

        });
    });

    $(document).on('click', '.delete-link', function (e) {
        e.preventDefault();

        var id = $(e.target).data('address-id');

        var addressId = id.split('-')[1];

        $.ajax({
            type: 'DELETE',
            url: contextRoot + '/address/' + addressId

        }).success(function (data, status) {

            $('#address-row-' + id).remove();

        });
    });

    function buildAddressRow(data) {

        return "<tr id='address-row-" + data.person.id + "-" + data.id + "'>  \n\
                <td>" + data.person.lastName + ", " + data.person.firstName + "</td>  \n\
                <td><a data-address-id='" + data.id + "' data-toggle='modal' data-target='#showAddressModal'>" + data.street + ", " + data.city + ", " + data.state + " " + data.zip + "</a></td>    \n\
                <td> <a data-address-id='" + data.id + "' data-toggle='modal' data-target='#editAddressModal'>Edit</a>  </td>   \n\
                <td> <a data-address-id='" + data.person.id + "-" + data.id + "' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";
    }

    function sortTable() {
        var rows = $('#addressesTable tbody tr').get();
        rows.sort(function (a, b) {
            var A = $(a).children('td').text().toUpperCase();
            var B = $(b).children('td').text().toUpperCase();

            if (A < B) {
                return -1;
            }
            if (A > B) {
                return 1;
            }
            return 0;
        });

        $('#addressesTable').children('tbody').empty();

        $.each(rows, function (index, row) {
            $('#addressesTable').children('tbody').append(row);
        });
    }

});



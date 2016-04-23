
$(document).ready(function () {


    $(document).on('submit', '#create-form', function (e) {

        e.preventDefault();
        
        $('#add-contact-validation-errors').empty();

        var dvdData = JSON.stringify({
            title: $('#add-title').val(),
            releaseDate: $('#add-release-date').val(),
            mpaaRating: $('#add-mpaa-rating').val(),
            director: $('#add-director').val(),
            studio: $('#add-studio').val(),
            note: $('#add-note').val()
        });



        $.ajax({
            type: 'POST',
            url: contextRoot + "/dvd",
            data: dvdData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {

            //Clear the form
            $('#add-title').val("");
            $('#add-release-date').val("");
            $('#add-mpaa-rating').val("");
            $('#add-director').val("");
            $('#add-studio').val("");
            $('#add-note').val("");


            var tableRow = buildDvdRow(data);

            $('#dvdTable').append($(tableRow));


        }).error(function (data, status) {
            var errors = data.responseJSON.errors;
            
            $.each(errors, function(index, validationError) {
                
            $('#add-contact-validation-errors').append(validationError.fieldName + ": " +validationError.message).append("<br />");
            
            });
        });


    });


    $('#showDvdModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var id = link.data('dvd-id');

        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: contextRoot + "/dvd/" + id,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (dvd, status) {

            modal.find('#dvd-title').text(dvd.title);
            modal.find('#dvd-releaseDate').text(dvd.releaseDate);
            modal.find('#dvd-mpaaRating').text(dvd.mpaaRating);
            modal.find('#dvd-director').text(dvd.director);
            modal.find('#dvd-studio').text(dvd.studio);
            modal.find('#dvd-note').text(dvd.note);

            $('#edit-id').val(id);
        });


    });


    $('#editDvdModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var id = link.data('dvd-id');

        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: contextRoot + "/dvd/" + id,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (dvd, status) {


            modal.find('#edit-title').val(dvd.title);
            modal.find('#edit-release-date').val(dvd.releaseDate);
            modal.find('#edit-mpaa-rating').val(dvd.mpaaRating);
            modal.find('#edit-director').val(dvd.director);
            modal.find('#edit-studio').val(dvd.studio);
            modal.find('#edit-note').val(dvd.note);

            $('#edit-id').val(id);

        });

    });


    $(document).on('click', '#edit-dvd-button', function (e) {

        e.preventDefault();

        $('#edit-contact-validation-errors').empty();

        var id = $('#edit-id').val();

        $.ajax({
            type: 'PUT',
            url: contextRoot + '/dvd/' + id,
            data: JSON.stringify({
                id: id,
                title: $('#edit-title').val(),
                releaseDate: $('#edit-release-date').val(),
                mpaaRating: $('#edit-mpaa-rating').val(),
                director: $('#edit-director').val(),
                studio: $('#edit-studio').val(),
                note: $('#edit-note').val()

            }),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", 'application/json');
                xhr.setRequestHeader("Content-type", 'application/json');
            }
        }).success(function (data, status) {

            
            $('#editDvdModal').modal('hide');

            var tableRow = buildDvdRow(data);

            $('#dvd-row-' + data.id).replaceWith($(tableRow));


            }).error(function (data, status) {
            var errors = data.responseJSON.errors;
            
            $.each(errors, function(index, validationError) {
                
            $('#edit-contact-validation-errors').append(validationError.fieldName + ": " +validationError.message).append("<br />");
            
            });
            
        });



    });


    $(document).on('click', '.delete-link', function (e) {
        e.preventDefault();
        var id = $(e.target).data('dvd-id');

        $.ajax({
            type: 'DELETE',
            url: contextRoot + '/dvd/' + id

        }).success(function (data, status) {

            $('#dvd-row-' + id).remove();

        });


    });




    function buildDvdRow(data) {

        return "<tr id='dvd-row-" + data.id + "'>  \n\
                <td><a data-dvd-id='" + data.id + "' data-toggle='modal' data-target='#showDvdModal'>" + data.title + "</a></td>  \n\
                <td> <a data-dvd-id='" + data.id + "' data-toggle='modal' data-target='#editDvdModal'>Edit</a>  </td>   \n\
                <td> <a data-dvd-id='" + data.id + "' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";

    }


});



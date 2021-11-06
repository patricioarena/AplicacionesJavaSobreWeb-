$(document).ready(function () {

    $(document).on('click', '#close', function () {
        $('#editModal').removeClass('open');
    })

    $('.table #editButton').on('click',function(event){
		event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            console.log(user);
            $('#editModal').addClass('open');
            // $('#editFirstname').empty();
            $('#editFirstname').val(user.name);
            // $('#editLastname').empty();
            $('#editLastname').val(user.lastName);
            // $('#editAddress').empty();
            // $('#editAddress').append(user.address);
        })
	});

    console.log("main.js is ready!")
});

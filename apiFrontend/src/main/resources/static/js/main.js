$(document).ready(function () {

    $(document).on('click', '#closeTopButton', function (event) {
        event.preventDefault();
        $('#editModal').removeClass('open');
    })

    $(document).on('click', '#btnCloseForm', function (event) {
        event.preventDefault();
        $('#editModal').removeClass('open');
    })


    $('.table #editButton').on('click',function(event){
		event.preventDefault();
        var href = $(this).attr('href');

        console.log(href)

        $.get(href, function (user, status) {
            console.log(user);
            $('#editModal').addClass('open');

            $('#editId').val(user._id);
            $('#editIdRole').val(user._idRole);
            $('#editFirstname').val(user.name);
            $('#editLastname').val(user.lastName);
            $('#editBirthDate').val(user.birthDate);
            $('#editTypeDoc').val(user.typeDoc);
            $('#editNumberDoc').val(user.numberDoc);
            $('#editGender').val(user.gender);
            $('#editEmail').val(user.email);
            $('#editTelephoneNumber').val(user.telephoneNumber);
            $('#editReputation').val(user.reputation);
            $('#editStreet').val(user.address.street);
            $('#editNumber').val(user.address.number);
            $('#editZipCode').val(user.address.zipCode);
            $('#editCity').val(user.address.city);
            $('#editProvState').val(user.address.provState);
            $('#editCountry').val(user.address.country);
            $('#editDeleted').val(user.deleted);

            // $('#editAddress').empty();
            // $('#editAddress').append(user.address);
        })
	});

    console.log("main.js is ready!")
});

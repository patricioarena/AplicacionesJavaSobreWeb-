$(document).ready(function () {

    $(document).on('click', '.closeTopButton', function (event) {
        event.preventDefault();
        var id = event.target.id.replace("closeTopButton","");
        $('#editModal'+id).removeClass('open');
    })

    $(document).on('click', '.btnCloseForm', function (event) {
        event.preventDefault();
        var id = event.target.id.replace("btnCloseForm","");
        $('#editModal'+id).removeClass('open');
    })

    $('.table #editButton').on('click',function(event) {
		event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (user, status) {

            var dateFormated = moment(user.birthDate).format('YYYY-MM-DD');

            $(`#editModal${user._id}`)
                .addClass('open')
                .keyup(function(event) {
                if (event.key === "Escape") {
                    $(`#editModal${user._id}`).removeClass('open');
                }
            });

            $(`#editId${user._id}`).val(user._id);
            $(`#editIdRole${user._id}`).val(user._idRole);
            $(`#editFirstname${user._id}`).val(user.name);
            $(`#editLastname${user._id}`).val(user.lastName);
            $(`#editBirthDate${user._id}`).val(dateFormated);
            $(`#editTypeDoc${user._id}`).val(user.typeDoc);
            $(`#editNumberDoc${user._id}`).val(user.numberDoc);
            $(`#editGender${user._id}`).val(user.gender);
            $(`#editEmail${user._id}`).val(user.email);
            $(`#editTelephoneNumber${user._id}`).val(user.telephoneNumber);
            $(`#editReputation${user._id}`).val(user.reputation);
            $(`#editStreet${user._id}`).val(user.address.street);
            $(`#editNumber${user._id}`).val(user.address.number);
            $(`#editZipCode${user._id}`).val(user.address.zipCode);
            $(`#editCity${user._id}`).val(user.address.city);
            $(`#editProvState${user._id}`).val(user.address.provState);
            $(`#editCountry${user._id}`).val(user.address.country);
            $(`#editDeleted${user._id}`).val(user.deleted);

        })
	});

    console.log("main.js is ready!")
});

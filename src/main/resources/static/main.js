$('#modal-container-deleteUser').on('show.bs.modal', function (event) {
    let valID = $(event.relatedTarget).data('id')
    let valName = $(event.relatedTarget).data('name')
    let valSurname = $(event.relatedTarget).data('surname')
    let valAge = $(event.relatedTarget).data('age')
    let valEmail = $(event.relatedTarget).data('email')
    let valRoles = $(event.relatedTarget).data('roles')

    $(this).find(".modal-body #inputTextControlD0").val(valID)
    $(this).find(".modal-body #inputTextControlD1").val(valName)
    $(this).find(".modal-body #inputTextControlD2").val(valSurname)
    $(this).find(".modal-body #inputTextControlD3").val(valAge)
    $(this).find(".modal-body #inputTextControlD4").val(valEmail)
    $(this).find(".modal-body #inputTextControlD5").val(valRoles)
});


$('#modal-container-editUser').on('show.bs.modal', function (event1) {
    let valID = $(event1.relatedTarget).data('id')
    let valName = $(event1.relatedTarget).data('name')
    let valSurname = $(event1.relatedTarget).data('surname')
    let valAge = $(event1.relatedTarget).data('age')
    let valEmail = $(event1.relatedTarget).data('email')
    let valRoles = $(event1.relatedTarget).data('roles')

    $(this).find(".modal-body #idEdit").val(valID)
    $(this).find(".modal-body #nameEdit").val(valName)
    $(this).find(".modal-body #surnameEdit").val(valSurname)
    $(this).find(".modal-body #ageEdit").val(valAge)
    $(this).find(".modal-body #emailEdit").val(valEmail)
    $(this).find(".modal-body #roleEdit").val(valRoles)
});
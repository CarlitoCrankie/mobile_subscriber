/**
 *
 * */
$('document').ready(function (){



});

$(document).on('click', '#Submit',function (event) {

    event.preventDefault();
    const id = +$('#id').val();
    const data1 =  {
        "id":      id,
        "customerIdOwner": +$('#customer_id_owner').val(),
        "customerIdUser": +$('#customer_id_user').val(),
        "msisdn":    $('#msisdn').val(),
        "serviceType":   $('#service_type').val(),
    }
    console.log("add data",data1)

    $.ajax({
        url:`/addMobile`,
        type:'PUT',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data1),
        success:function () {
            $("#closeAddModal").click();
            history.go();
        }
    });

    $.ajax({
        url:`/updateMobile/${id}`,
        type:'PUT',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data1),
        success:function () {
            $("#closeAddModal").click();
            history.go();
        },
        error:function (error) {
            console.log(error)
        }
    })

});

$(document).on('click', '#editButton',function (event) {
    let row = $(this).closest('tr')
    let id = row.find('td:eq(0)').text()
    console.log("the id",id)


    $('#customer_id_ownerEdit').val('');
    $('#customer_id_userEdit').val('');
    $('#msisdnEdit').val('');
    $('#service_start_dateEdit').val('');
    $('#service_typeEdit').val('');

    // event.preventDefault();

    $.ajax({
        url:`/mobiles/${id}`,
        type:'GET',
        success:function (result) {
            console.log("the get result",result)
            $('#idEdit').val(result.id);
            $('#customer_id_ownerEdit').val(result.customerIdOwner);
            $('#customer_id_userEdit').val(result.customerIdUser);
            $('#msisdnEdit').val(result.msisdn);
            $('#service_start_dateEdit').val(result.serviceStartDate);
            $('#service_typeEdit').val(result.serviceType);
        }
    })


});


$(document).on('click', '#saveButton',function (event) {
    // let row = $(this).closest('tr')
    // let id = row.find('td:eq(0)').text()
    // console.log("the id",id)
     event.preventDefault();
     const id = +$('#idEdit').val();
    console.log("the id",id)
    const data =  {
        "id":      id,
        "customerIdOwner": +$('#customer_id_ownerEdit').val(),
        "customerIdUser": +$('#customer_id_userEdit').val(),
        "msisdn":    $('#msisdnEdit').val(),
        "serviceType":   $('#service_typeEdit').val(),
    }
    console.log("update data",data)

    $.ajax({
        url:`/updateMobile/${id}`,
        type:'PUT',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success:function (result) {
            console.log("the get result",result);
            $("#closeEditModal").click();
            history.go();
        },
        error:function (error) {
            console.log(error)
        }
    })

});

// $(document).ready(function () {
//     $('#exampleTable').DataTable();
// });
//
// $(document).on('click', '#deleteButton', function(){
//     let row = $(this).closest('tr')
//     let id = row.find('td:eq(0)').text()
//     console.log("the id",id)
//
// $.ajax({
//     url: `/deleteMobile/${id}`,
//     type: 'DELETE',
//     contentType: "application/json; charset=utf-8",
//
//     success:function(){
//         $('#deleteButtons').click();
//         history.go();
//     },
//     error: function(error){
//         console.log(error)
//         }
//     })
// });





$(document).ready(function () {
    // let dataSet = [
    //     ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', '', 'Khong co', '22/7/2022', ""],
    // ];
    $.ajax({
        url: `http://localhost:8080${$path_base}/api-get-user`,
        type: 'GET',
        dataType: 'text',
        success(values) {
            // const json = JSON.parse(d);
            const usersData = JSON.parse(values).map((x) => {
                return [
                    x.userID,
                    x.firstName,
                    x.lastName,
                    x.phoneNumber,
                    x.email,
                    x.address === undefined ? "" : x.address,
                    x.userName,
                    x.role
                ]
            })
            // dataSet = data;
            console.log(usersData)
            $('#table-user').DataTable({
                data: usersData,
                columns: [
                    {title: 'Mã TK'},
                    {title: 'Họ'},
                    {title: 'Tên'},
                    {title: 'Số điện thoại'},
                    {title: 'Email'},
                    {title: 'Địa chỉ'},
                    {title: 'Tên tài khoản'},
                    {title: 'Vai trò'},
                    {
                        title: '',
                        render: function () {
                            return "<button onclick='edit(this)' name='Edit' value=''>edit</button>";
                        }
                    },
                    {
                        title: '',
                        render: function () {
                            // return "<button class ='my-btn' onclick = deleteAccount(this)>delete</button>";
                            return '<button type="button" class="my-btn" data-toggle="modal" onclick="setDeleteElement(this)" data-target="#confirm-delete-account">delete</button>'
                        }
                    },
                ],
                'pageLength': 5
            });
        },
    })

});

// Xử lí sự kiện xóa tài khoản
// function deleteAccount(thisElement) {
//     let table = $('#table-user').DataTable();
//     const data = table.row($(thisElement).parents('tr')).data();
//     const idAccount = data[0];
//
//     $.ajax({
//         type: 'GET',
//         url: `http://localhost:8080${$path_base}/api-delete-account`,
//         dataType: 'text',
//         data: {'id_account': idAccount},
//         success: function (data) {
//             $("#message_box").fadeTo(2000, 500).slideUp(500, function () {
//                 $("#message_box").slideUp(500);
//             });
//             $("#msg_box").html(data);
//             let myTable = $('#table-user').DataTable();
//             myTable.row($(thisElement).parents('tr')).remove().draw();
//         }
//     });
// }
let deleteElement = null;

function setDeleteElement(thisElement) {
    deleteElement = thisElement;
    console.log(deleteElement);
}

function deleteAccount() {
    let table = $('#table-user').DataTable();
    const data = table.row($(deleteElement).parents('tr')).data();
    const idAccount = data[0];
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080${$path_base}/api-delete-account`,
        dataType: 'text',
        data: {'id_account': idAccount},
        success: function (data) {
            $("#message_box").fadeTo(2000, 500).slideUp(500, function () {
                $("#message_box").slideUp(500);
            });
            $("#msg_box").html(data);
            let myTable = $('#table-user').DataTable();
            myTable.row($(deleteElement).parents('tr')).remove().draw();
        }
    });
    $("#confirm-delete-account").modal("hide");
}

function edit(thisElement) {
    let table = $('#table-user').DataTable();
    const data = table.row($(thisElement).parents('tr')).data();

    $('#idAccount').val(data[0]);
    $('#last_name_edit').val(data[1]);
    $('#first_name_edit').val(data[2]);
    $('#phone_edit').val(data[3]);
    $('#email_edit').val(data[4]);
    $('#address_edit').val(data[5]);
    $('#username_edit').val(data[6]);
    $('#role_edit').val(data[7]).change();
    $('#editUser').modal('show');
}


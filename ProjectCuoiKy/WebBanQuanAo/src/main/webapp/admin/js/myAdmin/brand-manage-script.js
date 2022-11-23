$(document).ready(function () {
    $.ajax({
        url: `http://localhost:8080${$path_base}/api-brand`,
        type: 'GET',
        dataType: 'text',
        success(values) {
            const json = JSON.parse(values);
            const brandData = json.map((x) => {
                return [
                    x.idBrand,
                    x.nameBrand
                ]
            })
            console.log(brandData)
            $('#table-brand').DataTable({
                data: brandData,
                columns: [
                    {title: "Mã nhãn hiệu"},
                    {title: "Tên nhãn hiệu"},
                    {
                        title: '',
                        render: function () {
                            return "<button onclick='edit(this)' name='Edit'>edit</button>";
                        }
                    },
                    {
                        title: '',
                        render: function () {
                            return '<button type="button" class="my-btn" data-toggle="modal" onclick="setDeleteElement(this)" data-target="#confirm-delete-brand">delete</button>'
                        }
                    },
                ],
                'pageLength': 5
            });
        },
    })
})

let deleteElement = null;

function setDeleteElement(thisElement) {
    deleteElement = thisElement;
    console.log(deleteElement);
}

function deleteBrand() {
    let table = $('#table-brand').DataTable();
    const data = table.row($(deleteElement).parents('tr')).data();
    const idBrand = data[0];
    $.ajax({
        type: 'POST',
        url: `http://localhost:8080${$path_base}/api-brand`,
        dataType: 'text',
        data: {'id_brand': idBrand, action: "delete"},
        success: function (data) {
            $("#message_box").fadeTo(2000, 500).slideUp(500, function () {
                $("#message_box").slideUp(500);
            });
            $("#msg_box").html(data);
            console.log(data)
            let myTable = $('#table-brand').DataTable();
            myTable.row($(deleteElement).parents('tr')).remove().draw();
        }
    });
    $("#confirm-delete-brand").modal("hide");
}

function edit(thisElement) {
    let table = $('#table-brand').DataTable();
    const data = table.row($(thisElement).parents('tr')).data();

    $('#idBrand').val(data[0]);
    $('#brandName').val(data[1]);
    $('#editBrand').modal('show');
}


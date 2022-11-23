$(document).ready(function () {
    $.ajax({
        url: `http://localhost:8080${$path_base}/api-color`,
        type: 'GET',
        dataType: 'text',
        success(values) {
            const json = JSON.parse(values);
            const colorsData = json.map((x) => {
                return [
                    x.idColor,
                    x.nameColor,
                    x.createDate,
                ]
            })
            console.log(colorsData)
            $('#table-color').DataTable({
                data: colorsData,
                columns: [
                    {title: "Mã màu" },
                    {title: "Tên màu"},
                    {title: "Ngày thêm"},
                    {
                        title: '',
                        render: function () {
                            return "<button onclick='edit(this)' name='Edit'>edit</button>";
                        }
                    },
                    {
                        title: '',
                        render: function () {
                            return '<button type="button" class="my-btn" data-toggle="modal" onclick="setDeleteElement(this)" data-target="#confirm-delete-color">delete</button>'
                        }
                    },
                ],
                'pageLength': 5
            });
        },
    })

});

let deleteElement = null;

function setDeleteElement(thisElement) {
    deleteElement = thisElement;
    console.log(deleteElement);
}

function deleteColor() {
    let table = $('#table-color').DataTable();
    const data = table.row($(deleteElement).parents('tr')).data();
    const idColor = data[0];
    $.ajax({
        type: 'POST',
        url: `http://localhost:8080${$path_base}/api-color`,
        dataType: 'text',
        data: {'id_color': idColor, action: "delete"},
        success: function (data) {
            $("#message_box").fadeTo(2000, 500).slideUp(500, function () {
                $("#message_box").slideUp(500);
            });
            $("#msg_box").html(data);
            console.log(data)
            let myTable = $('#table-color').DataTable();
            myTable.row($(deleteElement).parents('tr')).remove().draw();
        }
    });
    $("#confirm-delete-color").modal("hide");
}

function edit(thisElement) {
    let table = $('#table-color').DataTable();
    const data = table.row($(thisElement).parents('tr')).data();

    $('#idColor').val(data[0]);
    $('#colorName').val(data[1]);
    $('#editColor').modal('show');
}


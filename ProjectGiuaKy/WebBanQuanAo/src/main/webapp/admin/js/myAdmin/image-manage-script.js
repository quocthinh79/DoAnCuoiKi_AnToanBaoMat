$(document).ready(function () {
    $.ajax({
        url: `http://localhost:8080${$path_base}/api-image`,
        type: 'GET',
        dataType: 'text',
        success(values) {
            const json = JSON.parse(values);
            const colorsData = json.map((x) => {
                return [
                    x.idProduct,
                    x.colorName,
                    x.pathImg,
                ]
            })
            $('#table-image').DataTable({
                data: colorsData,
                columns: [
                    {title: "Mã sản phẩm"},
                    {title: "Tên màu"},
                    {
                        title: 'Ảnh',
                        render: function (src) {
                            return `<img style="width: 80px; height: 80px" src="${$path_base}${src}" alt=''>`;
                        }
                    },
                    {
                        title: '',
                        render: function () {
                            return "<button onclick='edit(this)' name='Edit'>edit</button>";
                        }
                    },
                    {
                        title: '',
                        render: function () {
                            return '<button type="button" class="my-btn" data-toggle="modal" onclick="setDeleteElement(this)" data-target="#confirm-delete">delete</button>'
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

function onDelete() {
    let table = $('#table-image').DataTable();
    const data = table.row($(deleteElement).parents('tr')).data();
    let idColor = "";
    $('#color-product option').each(function () {
        if (this.innerText === data[1]) {
            idColor = $(this).val();
        }
    })
    const id = data[0];
    const pathImg = data[2];
    $.ajax({
        type: 'POST',
        url: `http://localhost:8080${$path_base}/api-image`,
        dataType: 'text',
        data: {'id': id, 'pathImg': pathImg, 'idColor': idColor, action: "delete"},
        success: function (data) {
            $("#message_box").fadeTo(2000, 500).slideUp(500, function () {
                $("#message_box").slideUp(500);
            });
            $("#msg_box").html(data);
            console.log(data)
            let myTable = $('#table-image').DataTable();
            myTable.row($(deleteElement).parents('tr')).remove().draw();
        }
    });
    $("#confirm-delete").modal("hide");
}

function edit(thisElement) {
    let table = $('#table-image').DataTable();
    const data = table.row($(thisElement).parents('tr')).data();

    $('#idProduct').val(data[0]);
    $('#color-product option').each(function () {
        if (this.innerText === data[1]) {
            $('#color-product').val($(this).val());
        }
    })
    $("#imageDetailPreview").empty();
    const img = "<div class='img-preview'><img src='" + $path_base + data[2] + "' id= previewEditImage alt='image preview'/> " +
        "<i class='fa-solid fa-magnifying-glass-plus'></i> </div>"
    $('#imageDetailPreview').append(img);
    $('#editImage').modal('show');
}

function previewImage(input) {
    $("#imageDetailPreview").empty()
    for (let i = 0; i < input.files.length; i++) {
        readImage(input.files[i], "imageDetailPreview", "imagesDetail" + i)
    }

}

function readImage(input, frameImages, idImages) {
    let reader = new FileReader()
    const img = "<div class='img-preview'><img id=" + idImages + " alt='image preview'/> " +
        "<i class='fa-solid fa-magnifying-glass-plus'></i> </div>"
    $("#" + frameImages).append(img)
    reader.onload = function (e) {
        $("#" + idImages).attr("src", e.target.result)
    }
    reader.readAsDataURL(input)
}

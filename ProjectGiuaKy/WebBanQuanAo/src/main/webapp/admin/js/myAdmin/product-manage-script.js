let itemColorsSelected = []
let itemTagsSelected = []
let itemSizeSelected = []
let listColor = []
let listTag = []
let listSize = []
$("#color-product option").each(function () {
    listColor.push($(this).val())
})
$("#tag-product option").each(function () {
    listTag.push($(this).val())
})
$("#size-product option").each(function () {
    listSize.push($(this).val())
})
$(document).ready(function () {
    $.ajax({
        url: `http://localhost:8080${$path_base}/api-get-product`,
        type: 'GET',
        dataType: 'text',
        success(d) {
            const json = JSON.parse(d);
            const data = json.map((x) => {
                return [
                    x.thumbnail,
                    x.id,
                    x.name,
                    x.brand,
                    x.price,
                    x.description === undefined ? "Không mô tả" : x.description,
                    x.date,
                    x.numberOfRate,
                    x.rate,
                    x.number
                ]
            })
            dataSet = data;
            $('#table-product').DataTable({
                data: dataSet,
                columns: [
                    {
                        title: 'Ảnh',
                        render: function (src) {
                            return `<img style="width: 80px; height: 80px" src="${$path_base}${src}" alt=''>`;
                        }
                    },
                    {title: 'Mã SP'},
                    {title: 'Tên SP'},
                    {title: 'Nhãn hiệu'},
                    {title: 'Giá'},
                    {title: 'Mô tả'},
                    {title: 'Ngày thêm'},
                    {title: 'Tổng đánh giá'},
                    {title: 'Tổng sao'},
                    {title: 'Số lượng'},
                    {
                        title: '',
                        render: function () {
                            return '<button class="my-btn" onclick="editProduct(this)" >edit</button>';
                        }
                    },
                    {
                        title: '',
                        render: function () {
                            return '<button class ="my-btn" data-toggle="modal" onclick="choseRowProduct(this)" data-target="#confirm-delete-product" >delete</button>';
                        }
                    },
                ],
                'pageLength': 5
            });
        },
    })
    $("#btnCheck").click(function () {
        const codeProduct = $("#code-product").val()
        $.ajax({
            url: `http://localhost:8080${$path_base}/check-code-product?codeProduct=${codeProduct}`,
            type: 'GET',
            dataType: 'text',
            success(result) {
                $("#message-check").remove()
                if (JSON.parse(result) === true) {
                    $("#form-code-product").append("<p id='message-check' class='text-danger'>Mã sản phẩm đã bị trùng vui lòng nhập mã số khác</p>")
                } else {
                    $("#form-code-product").append("<p id='message-check' class='text-success'>Mã sản phẩm hợp lệ</p>")
                    setTimeout(() => {
                        $("#message-check").remove()
                    }, 3000)
                }
            }
        })
    })
    $("#btn-confirm-delete-product").click(function () {
        deleteProduct()
    })
    $("#btnAddProduct").click(function () {
        $("#frame-tags").css("display", "")
        $("#frame_colors_sizes").css("display", "")
        swicthForm(true)
    })
    $("#inputTag").click(function () {
        for (const index of itemTagsSelected) {
            disableTagSelected(index)
        }
    })
    $("#inputColor").click(function () {
        for (const index of itemColorsSelected) {
            disableColorSelected(index)
        }
    })
    $("#inputSize").click(function () {
        for (const index of itemSizeSelected) {
            disableSizeSelected(index)
        }
    })
});


//chose row product
let elementSelected = null

function choseRowProduct(thisElement) {
    elementSelected = thisElement;
}

//function delete product
function deleteProduct() {
    let table = $('#table-product').DataTable();
    const data = table.row($(elementSelected).parents('tr')).data();
    const idProduct = data[1];
    $.ajax({
        type: 'DELETE',
        url: `http://localhost:8080${$path_base}/admin-product-manage?codeProduct=${idProduct}`,
        dataType: 'text',
        success: function (data) {
            $("#message_box").fadeTo(2000, 500).slideUp(500, function () {
                $("#message_box").slideUp(500);
            });
            $("#msg_box").html(data);
            if (data.trim() === "Xóa sản phẩm thành công") {
                let myTable = $('#table-product').DataTable();
                myTable.row($(elementSelected).parents('tr')).remove().draw();
            }
        }
    });
    $("#confirm-delete-product").modal("hide");
}

//edit product
function editProduct(thiselement) {
    $("#frame-tags").css("display", "none")
    $("#frame_colors_sizes").css("display", "none")
    $("#imageThumbnailPreview").empty()
    let table = $('#table-product').DataTable();
    const data = table.row($(thiselement).parents('tr')).data();
    const idProduct = data[1];
    swicthForm(false)
    let temp = null
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080${$path_base}/get-product-edit?codeProduct=${idProduct}`,
        dataType: 'text',
        success: function (data) {
            const object = JSON.parse(data)
            temp = object
            $("#code-product").val(object.id)
            $("#name-product").val(object.name)
            $("#brand").val(object.brand)
            $("#price-product").val(parseFloat(object.price))
            $("#description").val(object.description)
            $("#thumbnail").val(null)
            $("#imageThumbnailPreview").append(`<div class='img-preview'><img src='http://localhost:8080/WebBanQuanAo${object.thumbnail}' alt='image preview'/> </div> <span onclick="clearThumbnail()"><img src='<c:url value="/assets/imgs/icon-img/close.svg"/>' alt=""/></span>`)
            // for (const element of object.colors) {
            //     $("#color_product_chosen .chosen-choices").prepend(`<li class='search-choice'>
            //     <span>${element.nameColor}</span>
            //     <a class='search-choice-close' data-option-array-index=${listColor.indexOf(element.idColor)}></a>
            //     </li>`)
            //     itemColorsSelected.push(listColor.indexOf(element.idColor))
            // }
            // for (const element of object.tags) {
            //     $("#tag_product_chosen .chosen-choices").prepend(`<li class='search-choice'><span>${element.nameTag}</span>
            //         <a class='search-choice-close' data-option-array-index=${listTag.indexOf(element.idTag)}></a></li>`)
            //     itemTagsSelected.push(listTag.indexOf(element.idTag))
            // }
            // for (const element of object.sizes) {
            //     $("#size_product_chosen .chosen-choices").prepend(`<li class='search-choice'><span>${element.nameSize}</span>
            //         <a class='search-choice-close' data-option-array-index=${listSize.indexOf(element.idSize)}></a></li>`)
            //     itemSizeSelected.push(listSize.indexOf(element.idSize))
            // }
            // for (const element of object.urlImages) {
            //     $("#imageDetailPreview").append(`<div class='img-preview'><img src='http://localhost:8080/WebBanQuanAo${element.pathImage}' alt='image preview'/> </div>`)
            // }
            // $("#imageDetailPreview").append(`<span onclick="clearImages()"><img src='<c:url value="/assets/imgs/icon-img/close.svg"/>' alt=""/></span>`)
        }
    });
    $("#submit-form").click(function () {
        const codeProduct = $("#code-product").val()
        const nameProduct = $("#name-product").val()
        const brand = $("#brand").val()
        const price = $("#price-product").val()
        const description = $("#description").val()
        let thumbnail = $("#thumbnail").val()
        let form = new FormData()
        form.append('thumbnail', $("#thumbnail").prop("files")[0])
        $.when(
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/WebBanQuanAo/admin-product-manage",
                data: JSON.stringify({
                    "codeProduct": codeProduct, "nameProduct": nameProduct, "brand": brand,
                    "price": price, "description": description, "thumbnail": thumbnail
                }),
                contentType: "application/json",
                success: function(response) {
                    $("#message_box").fadeTo(2000, 500).slideUp(500, function() {
                        $("#message_box").slideUp(500);
                    });
                    $("#msg_box").html(response);
                    $("#formProduct").modal("hide");
                    $("#submit-form").prop("onclick", null).off("click");

                }
            }),
            $.ajax({
                type: "POST",
                url: `http://localhost:8080/WebBanQuanAo/update-thumbnail?codeProduct=${temp.id}&oldPath=${temp.thumbnail}`,
                data: form,
                contentType: false,
                cache: false,
                processData:false,
            })
        )
    })
}

//the state passed must is boolean. The true is form add product activated
//and false is form edit product activated
function swicthForm(state) {
    //form add product chosen
    refreshInput()
    if (state) {
        $("#headerFormProduct").html('<h5 className="modal-title" id="headerFormProduct">Thêm sản phẩm</h5>')
        // $("#form-add-product").addClass("hiddenL")
        $("#submit-form").html("Thêm sản phẩm")
    } else {
        $("#formProduct").modal("show")
        $("#submit-form").html("Cập nhật")
        $("#headerFormProduct").html('<h5 className="modal-title" id="headerFormProduct">Chỉnh sửa sản phẩm</h5>')
    }
}

//disable tag selected (for from edit product)
function disableTagSelected(value) {
    const element = $("#tag_product_chosen .chosen-drop .chosen-results").find(`[data-option-array-index=${value}]`)
    element.removeClass("active-result")
    element.removeClass("highlighted ")
    element.addClass("result-selected")
    element.click(false)
}

//disable color selected (for from edit product)
function disableColorSelected(value) {
    const element = $("#color_product_chosen .chosen-drop .chosen-results").find(`[data-option-array-index=${value}]`)
    element.removeClass("active-result")
    element.removeClass("highlighted ")
    element.addClass("result-selected")
    element.click(false)
}

//disable size selected (for from edit product)
function disableSizeSelected(value) {
    const element = $("#size_product_chosen .chosen-drop .chosen-results").find(`[data-option-array-index=${value}]`)
    element.removeClass("active-result")
    element.removeClass("highlighted ")
    element.addClass("result-selected")
    element.click(false)
}

//refresh input form product
function refreshInput() {
    $("#code-product").val("")
    $("#name-product").val("")
    $("#brand").val("")
    $("#price-product").val("")
    $("#description").val("")
    $("#thumbnail").val("")
    $("#images").val("")
    $("#tag_product_chosen .chosen-choices .search-choice").remove()
    $("#color_product_chosen .chosen-choices .search-choice").remove()
    $("#size_product_chosen .chosen-choices .search-choice").remove()
    itemColorsSelected = []
    itemSizeSelected = []
    itemTagsSelected = []
    $(".form-error").empty()
}

//clear thumbnail
function clearThumbnail() {
    $("#thumbnail").val("")
    $("#imageThumbnailPreview").empty();
}

//clear images
function clearImages() {
    $("#images").val("")
    $("#imageDetailPreview").empty();
}



$(document).ready(function () {
    var dataSet = [
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0],
        ['/avatars/user.jpg', 'SP01', 'ÁO KHOÁT DA LỘN AK015', 'LocalBrand', 395000, 'Khong co', '22/7/2022', 0, 0, 0]
    ];

    $('#grid-table').DataTable({
        data: dataSet,
        columns: [
            {
                title: 'Ảnh',
                render: function (src) {
                    return `<img src="${$path_base}/admin${src}" alt=''>`;
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
                    return "<button onclick = edit()>edit</button>";
                }
            },
            {
                title: '',
                render: function () {
                    return "<button class ='my-btn' onclick = delete1(this)>delete</button>";
                }
            },
        ],
        'pageLength': 4
    });
});

function delete1(thisElement) {
    $(document).ready(function () {
        var myTable = $('#grid-table').DataTable();
        myTable.row($(thisElement).parents('tr')).remove().draw();
    })
}

async function getListProduct() {
    $.ajax({
        url: `http://localhost:8080${$path_base}/api-get-product`,
        type: 'GET',
        dataType: 'text',
        success(d) {
            const json = JSON.parse(d);
            const data = json.map((x) => {
                return {
                    thumbnail: x.thumbnail,
                    id: x.id,
                    name: x.name,
                    brand: x.brand,
                    price: x.price,
                    description: x.description,
                    date: x.date,
                    numberOfRate: x.numberOfRate,
                    rate: x.rate,
                    number: x.number
                }
            })
            dataSet = data;
            console.log(dataSet)
        },
        fail(error) {
            console.log(error);
        }
    })
}

$(document).ready(function () {
    $('#example').DataTable();
});

function edit() {
    var table = $('#grid-table').DataTable();

    $('#grid-table tbody').on( 'click', 'tr', function () {
        const product = table.row( this ).data();
        console.log(product[1]);
        console.log( table.row( this ).data());
    } );
}
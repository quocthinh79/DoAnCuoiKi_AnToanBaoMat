$(document).ready(function () {

})

function confirmPayment() {
    const idCart = $('#idCart').val();
    let ho = $('#lastname').val().trim();
    let ten = $('#firstname').val().trim();
    let nguoi_nhan = ho + " " + ten;
    let sdt = $('#phone-number').val().trim();
    let dia_chi = $('#address_customer').val().trim();
    if (idCart === null || idCart === undefined) {
        console.log("Loi roi, kiem tra lai di");
    } else {
        if (ho === "" || ten === "" || sdt === "" || dia_chi === "") {
            alert("Không được để trống chi tiết giao hàng");
        } else {
            $.ajax({
                type: 'POST',
                url: `checkout`,
                dataType: 'text',
                data: {'idCart': idCart, 'nguoi_nhan': nguoi_nhan, 'sdt': sdt, 'dia_chi': dia_chi},
                success: function (data) {
                    console.log("đã gửi dữ liệu")
                }
            });
            alert("Đã xác nhận đơn hàng, vui lòng đợi nhận hàng từ shipper, tiếp tục mua sắm");
            window.location.href = "http://localhost:8080/WebBanQuanAo/home";
        }
    }
}
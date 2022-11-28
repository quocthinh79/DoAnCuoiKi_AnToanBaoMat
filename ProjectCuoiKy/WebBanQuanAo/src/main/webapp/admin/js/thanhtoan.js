$(document).ready(function () {

})

function confirmPayment() {
    const privateKey = $("#txt-privateKey").val().trim();
    if (privateKey == null || privateKey === "") {
        alert("Khóa của bạn đang rỗng, vui lòng upload hoặc nhập khóa vào!")
    } else {
        const idCart = $('#idCart').val();
        const userAccount = $('#userAccount').text();
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
                    data: {
                        'idCart': idCart, 'nguoi_nhan': nguoi_nhan, 'sdt': sdt,
                        'dia_chi': dia_chi, 'privateKey': privateKey, 'userAccount': userAccount
                    },
                    success: function (data) {
                        if (Number.parseInt(data) === -1) {
                            $('#fail-checkout').text("Không thể xác thực");
                        } else {
                            $('#fail-checkout').text("Xác thực thành công. Vui lòng kiểm tra email của bạn!");
                            alert("Xác thực thành công. Vui lòng kiểm tra email của bạn!")
                        }
                    },
                    error: function (mess) {
                        $('#fail-checkout').text("Không thể xác thực khóa");
                    },
                    beforeSend:function(){
                        var popup = document.getElementById("alertPopup");
                        popup.classList.toggle("show");
                        var btsubmit = document.getElementById("btdialogsubmit");
                        btsubmit.style.pointerEvents =  "none";
                    },
                    complete:function(){
                        var popup = document.getElementById("alertPopup");
                        popup.classList.toggle("hide");
                        var dialog = document.getElementById("modal-dialog");
                        dialog.style.visibility = "hidden";
                    }
                });
                // alert("Đã xác nhận đơn hàng, vui lòng đợi nhận hàng từ shipper, tiếp tục mua sắm");
                // window.location.href = "http://localhost:8080/WebBanQuanAo/home";
            }
        }
    }
}
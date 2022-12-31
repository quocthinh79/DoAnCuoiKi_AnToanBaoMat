function verify() {
    const pdf = $("#txt-pdf").val().trim();
    const privateKey = $("#txt-privateKey").val().trim();
    const action = "verify";

    if (pdf == null || pdf === "" || privateKey == null || privateKey == "") {
        alert("Thiếu file pdf hoặc khoá, vui lòng upload đồng thời cả 2 file!")
    } else {
        $.ajax({
            type: 'POST',
            url: `my-account`,
            dataType: 'text',
            data: {
                'action': action, 'pdf': pdf, 'privateKey': privateKey
            },
            success: function (data) {
                alert("Xác thực thành công.!")
            },
            error: function (mess) {
                // $('#fail-checkout').text("Không thể xác thực khóa");
            },
            beforeSend:function(){
                // var popup = document.getElementById("alertPopup");
                // popup.classList.toggle("show");
                // var btsubmit = document.getElementById("btdialogsubmit");
                // btsubmit.style.pointerEvents =  "none";
            },
            complete:function(){
                // var popup = document.getElementById("alertPopup");
                // popup.classList.toggle("hide");
                // var dialog = document.getElementById("modal-dialog");
                // dialog.style.visibility = "hidden";
            }
        });
    }
}
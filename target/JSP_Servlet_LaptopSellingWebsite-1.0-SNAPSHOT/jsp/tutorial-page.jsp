<%--
  Created by IntelliJ IDEA.
  User: Thinh PC
  Date: 12/21/2021
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="root" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <title>Tutorial</title>
    <title>Metaverse Laptop</title>
    <link rel="icon" href="${root}image/logo-re.png">
    <link href="${root}fonts/themify-icons/themify-icons.css" rel="stylesheet">
    <link href="${root}fonts/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="${root}fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
          integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
          referrerpolicy="no-referrer" rel="stylesheet"/>
    <link href="${root}css/style-tutorial.css" rel="stylesheet">
    <link href="${root}css/responsiveIndex.css" rel="stylesheet">
    <link href="${root}css/reponsiveIndex.css" rel="stylesheet">
</head>

<body>

<div id="main-tutorial">
    <%@include file="../layout/header.jsp"%>
    <%@include file="../layout/cart-hover.jsp"%>
    <div id="container">
        <div class="title-tutorial">HƯỚNG DẪN MUA HÀNG</div>
        <div class="step">Bước 1: Đăng nhập</div>
        <div class="content-tutorial">Trước khi mua hàng, bạn cần đăng nhập vào tài khoản của mình</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial-login.png)"></div>
        <div class="content-tutorial">Trong trường hợp bạn quên mật khẩu, bạn có thể nhấp vào <strong>Quên mật khẩu</strong> và làm theo hướng dẫn sau đó</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial-fogot.png)"></div>
        <div class="content-tutorial">Nếu bạn chưa có tài khoản, bạn cũng có thể nhấp vào <strong>Tạo tài khoản mới</strong> và tiến hành làm theo hướng dẫn</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial-register.png)"></div>
        <div class="step">Bước 2: Tìm sản phẩm cần mua</div>
        <div class="content-tutorial">Sử dụng ô tìm kiếm phía trên, gõ tên sản phẩm muốn mua (có thể tìm đích danh 1 sản phẩm, tìm theo hãng...)</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial1.png)"></div>
        <div class="content-tutorial">Trang web luôn có sẵn những gợi ý sản phẩm hot nhất, có chương trình khuyến mãi hấp dẫn, bạn cũng có thể chọn xem ngay mà không cần tìm kiếm</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial2.png)"></div>
        <div class="content-tutorial">Hoặc bạn cũng có thể tham khảo các sản phẩm bán chạy nhất ở cuối trang</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial3.png)"></div>
        <div class="step">Bước 3: Đặt mua sản phẩm</div>
        <div class="content-tutorial">Sau khi chọn được sản phẩm ưng ý muốn mua, bạn tiến hành đặt hàng bằng cách:</div>
        <div class="content-tutorial">+ Chọn vào nút <strong>MUA NGAY</strong> nếu bạn muốn thanh toán luôn toàn bộ giá tiền sản phẩm hoặc nút <strong>THÊM VÀO GIỎ HÀNG</strong></div>
        <div class="content-tutorial">+ Hệ thống sẽ ghi nhận địa chỉ mà bạn đã đăng kí tài khoản trước đó</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial4.png)"></div>
        <div class="content-tutorial">Ngoài ra, nếu bạn đang sở hữu phiếu mua hàng, mã giảm giá... thì hãy nhập trong giỏ hàng đã đặt để được giảm giá</div>
        <div class="img-tutorial" style="background-image: url(${root}image/Tutorial/tutorial6.png)"></div>
        <div class="content-tutorial"><strong>Xin trân trọng cảm ơn!</strong></div>
    </div>
</div>
<%@include file="../layout/footer.jsp"%>
<%@include file="../layout/login.jsp"%>
<%@include file="../layout/fogot.jsp"%>
<%@include file="../layout/register.jsp"%>
<!--Back to top-->
<%@include file="../layout/back-to-top.jsp"%>
</body>
<script src="${root}js/register.js"></script>
<script crossorigin="anonymous" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script crossorigin="anonymous"
        integrity="sha512-XtmMtDEcNz2j7ekrtHvOVR4iwwaD6o/FUJe6+Zq+HgcCsk3kj4uSQQR8weQ2QVj1o0Pk6PwYLohm206ZzNfubg=="
        referrerpolicy="no-referrer"
        src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<script src="${root}js/nav-responsive.js"></script>
<script src="${root}js/BackToTop.js"></script>
<script src="${root}js/Scroll-Indicator.js"></script>
<script src="${root}js/visibleCart.js"></script>
<script src="${root}js/clickAddCartTutorial.js"></script>
<script>
    let pric2 = document.querySelectorAll('.price-cart-item');
    let pric3 = document.querySelectorAll('.total-price');
    for (let i = 0; i < pric2.length; i++) {
        pric2[i].innerHTML = parseInt(pric2[i].textContent).toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }
    for (let i = 0; i < pric3.length; i++) {
        pric3[i].innerHTML = parseInt(pric3[i].textContent).toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }
</script>
</html>


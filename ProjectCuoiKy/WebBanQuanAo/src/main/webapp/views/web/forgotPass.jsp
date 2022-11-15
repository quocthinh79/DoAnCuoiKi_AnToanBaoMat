<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- pages-title-start -->
<html>
<head>
    <title>${requestScope.pageName}</title>
</head>
<body>
<%@include file="/components/location.jsp" %>
<!-- pages-title-end -->
<!-- login content section start -->
<div class="login-area">
    <div class="container">
        <div class="row login-my-form">
            <div class="col-md-6 col-xs-12">
                <div class="tb-login-form ">
                    <h5 class="tb-title">Quên mật khẩu</h5>
                    <p>Vui lòng nhập email đăng ký</p>
                    <form action="/WebBanQuanAo/forgot-pass" method="post">
                        <p class="checkout-coupon top log a-an form-group">
                            <label class="l-contact">
                                Nhập email
                                <em>*</em>
                            </label>
                            <input type="email" name="email">
                            <span class="form-error text-danger mt-8"></span>
                        </p>
                        <p class="login-submit5">
                            <input class="button-primary" type="submit" value="Gửi">
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login content section end -->
</body>
</html>
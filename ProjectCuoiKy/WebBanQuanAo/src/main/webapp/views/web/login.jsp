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
                    <h5 class="tb-title">Đăng nhập</h5>
                    <form id="login-form" action="/WebBanQuanAo/login" method="post">
                        <p class="checkout-coupon top log a-an form-group">
                            <label class="l-contact" for="username">
                                Tài khoản
                                <em>*</em>
                            </label>
                            <input id="username" type="text" name="username" value="${requestScope.userName}"/>
                            <span class="form-error text-red mt-16"></span>
                        </p>
                        <p class="checkout-coupon top-down log a-an form-group">
                            <label class="l-contact" for="password">
                                Mật khẩu
                                <em>*</em>
                            </label>
                            <input id="password" type="password" name="password">
                            <span class="form-error text-red mt-16">${requestScope.error}</span>
                        </p>
                        <div class="forgot-password1">
                            <label class="inline2">
                                <input type="checkbox" name="rememberme">
                                Ghi nhớ đăng nhập! <em>*</em>
                            </label>
                            <a class="forgot-password" href="forgot-pass">Quên mật khẩu?</a>
                        </div>
                        <p class="login-submit5">
                            <input class="button-primary" type="submit" value="login">
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
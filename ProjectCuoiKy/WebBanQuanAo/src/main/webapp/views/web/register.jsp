<!-- pages-title-start -->
<%@ page pageEncoding="UTF-8" %>
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
            <div class="col-md-8 col-xs-12">
                <div class="tb-login-form res">
                    <h5 class="tb-title text-center">Tạo tài khoản mới</h5>
                    <p>Chào mừng bạn tạo tài khoản</p>
                    <form id="register_form" action="/WebBanQuanAo/register" method="POST">
                        <p class="checkout-coupon top log a-an form-group">
                            <label class="l-contact" for="last-name">
                                Họ
                                <em>*</em>
                            </label>
                            <input type="text" name="last-name" id="last-name" value="${requestScope.lastName}"/>
                            <span class="form-error text-red mt-16 form_message" id="last-name-error"></span>
                        </p>
                        <p class="checkout-coupon top log a-an form-group">
                            <label class="l-contact" for="first-name">
                                Tên
                                <em>*</em>
                            </label>
                            <input type="text" name="first-name" id="first-name" value="${requestScope.firstName}"/>
                            <span class="form-error text-red mt-16 form_message" id="first-name-error"></span>
                        </p>
                        <p class="checkout-coupon top log a-an form-group">
                            <label class="l-contact" for="phone">
                                Số điện thoại
                                <em>*</em>
                            </label>
                            <input type="text" name="phone" id="phone" value="${requestScope.phone}"/>
                            <span class="form-error text-red mt-16 form_message"></span>
                        </p>
                        <p class="checkout-coupon top log a-an form-group">
                            <label class="l-contact" for="email">
                                Email
                                <em>*</em>
                            </label>
                            <input type="email" name="email" id="email" value="${requestScope.email}"/>
                            <span class="form-error text-red mt-16 form_message"></span>
                        </p>
                        <p class="checkout-coupon top log a-an form-group">
                            <label class="l-contact" for="username">
                                Tài khoản
                                <em>*</em>
                            </label>
                            <input type="text" name="username" id="username" value="${requestScope.userName}"/>
                            <span class="form-error text-red mt-16 form_message">${requestScope.errorUser}</span>
                        </p>
                        <p class="checkout-coupon top-down log a-an form-group">
                            <label class="l-contact" for="password">
                                Mật khẩu
                                <em>*</em>
                            </label>
                            <input type="password" name="password" id="password"/>
                            <span class="form-error text-red mt-16 form_message"></span>
                        </p>
                        <p class="checkout-coupon top-down log a-an form-group">
                            <label class="l-contact" for="password-repeat">
                                Lặp lại mật khẩu
                                <em>*</em>
                            </label>
                            <input type="password" name="password-repeat " id="password-repeat"/>
                            <span class="form-error text-red mt-16 form_message">${requestScope.message}</span>
                        </p>
                        <p class="login-submit5 ress">
                            <input value="Đăng ký" class="button-primary" type="submit"/>
                        </p>
                    </form>
                    <div class="tb-info-login ">
                        <h5 class="tb-title4">Đăng ký hôm nay để nhận ưu đãi:</h5>
                        <ul>
                            <li>Thanh toán thuận tiện hơn.</li>
                            <li>Theo dõi đơn đặt hàng của bạn một cách dễ dàng.</li>
                            <li>Giữ một bản ghi về tất cả các giao dịch mua của bạn.</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login content section end -->
</body>
</html>
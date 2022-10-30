<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18/7/2022
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<div class="js-forgot ${requestScope.openOTP !=null?"open":""}" id="forgot-main" style="z-index: 1000">
    <div class="forgot js-forgot-container">
        <div class="title-forgot">
            <p class="slogan-forgot">Vui lòng nhập mã xác nhận đã được gửi về email</p>
        </div>
        <div class="border"></div>
        <form action="CheckOTP" class="forgot-form" method="get">
            <div class="input-forgot">
                <input class="input-items" name="otp" placeholder="Nhập mã OTP" required type="text">
                <p style="color: red">${requestScope.openOTP}</p>
            </div>
            <button class="btn-forgot">Xác nhận</button>
        </form>
        <div class="border"></div>
    </div>
</div>

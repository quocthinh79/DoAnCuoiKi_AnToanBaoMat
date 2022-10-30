<%--
  Created by IntelliJ IDEA.
  User: Thinh PC
  Date: 12/21/2021
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
%>
<div class="js-register ${requestScope.existsUsername !=null?"open":""}" id="register-main" style="z-index: 1000">
  <div class="register js-register-container">
    <div class="title-register">
      <a class="close-register js-close-icon" role="button"><i class="fas fa-times"></i></a>
      <h1 class="title-register-main" style="margin: 0; line-height: 1.4;">Đăng kí</h1>
      <p class="slogan-register">Nhanh chóng và dễ dàng</p>
    </div>
    <div class="border"></div>
    <form action="Register" method="post" class="register-form">
      <div class="input-register">
        <input class="input-items" name="hoTen" value="${requestScope.hoTen}" placeholder="Họ và tên" required type="text">
        <input class="input-items" name="username" value="${requestScope.username}" placeholder="Tên đăng nhập" required type="text">
        <input class="input-items" name="pass" value="${requestScope.pass}" placeholder="Mật khẩu mới" required type="password">
        <input class="input-items" name="diaChi" value="${requestScope.diaChi}" placeholder="Địa chỉ nhận hàng" required type="text">
        <input class="input-items" name="email" value="${requestScope.email}" placeholder="Email liên hệ" required type="email">
        <p style="color: red">${requestScope.existsUsername}</p>
      </div>
      <div class="register-div">
        <button class="btn-register" role="button">Đăng kí</button>
      </div>
    </form>
  </div>
</div>

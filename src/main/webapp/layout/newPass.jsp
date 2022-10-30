<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18/7/2022
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
%>
<div class="js-forgot ${requestScope.openNewPass !=null?"open":""}" id="forgot-main" style="z-index: 1000">
  <div class="forgot js-forgot-container">
    <div class="title-forgot">
      <p class="slogan-forgot">Vui lòng nhập mã xác nhận đã được gửi về email</p>
    </div>
    <div class="border"></div>
    <form action="UpdatePass" class="forgot-form" method="post">
      <div class="input-forgot">
        <input class="input-items" name="newPass1" placeholder="Nhập mật khẩu mới" required type="password">
        <input class="input-items" name="newPass2" placeholder="Nhập lại mật khẩu mới" required type="password">
        <p style="color: red">${requestScope.openNewPass}</p>
      </div>
      <button class="btn-forgot">Xác nhận</button>
    </form>
    <div class="border"></div>
  </div>
</div>

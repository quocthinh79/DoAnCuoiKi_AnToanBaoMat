<%--
  Created by IntelliJ IDEA.
  User: Thinh PC
  Date: 12/21/2021
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<div class="js-forgot ${requestScope.errorFindAccount !=null?"open":""}" id="forgot-main" style="z-index: 1000">
    <div class="forgot js-forgot-container">
        <div class="title-forgot">
            <a class="close-forgot" role="button"></a>
            <h1 class="title-forgot-main">Quên mật khẩu</h1>
            <p class="slogan-forgot">Chúng tôi sẽ hỗ trợ tận tình</p>
        </div>
        <div class="border"></div>
        <form action="FindAccount" class="forgot-form" method="get">
            <div class="input-forgot">
                <input class="input-items" name="email" placeholder="Email đăng kí" required type="email" value="${requestScope.email}">
                <p style="color: red">${requestScope.errorFindAccount}</p>
            </div>
            <button class="btn-forgot">Tìm tài khoản</button>
        </form>
        <div class="border"></div>
        <div class="come-back">
            <a class="btn-come-back js-come-back" role="button">Quay trở lại đăng nhập</a>
        </div>
    </div>
</div>

<%--
  Created by IntelliJ IDEA.
  User: Thinh PC
  Date: 12/21/2021
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="root"/>
<div id="header">
    <a href="${root}">
        <div class="header_logo">
            <!--                <img src="../image/img_1.png" alt="" class="logo">-->
        </div>
    </a>
    <form action="${pageContext.request.contextPath}/SearchClick" method="GET" class="header_search">
        <div class="search">
            <input name="txt" id="input_search" class="header_input-search" placeholder="Nhập từ khóa cần tìm kiếm"
                   required type="text">
            <button class="header_btn-search" type="submit">
                <i class="fas fa-search"></i>
            </button>
        </div>
    </form>
    <div class="header_list" id="header-list-id">
        <ul class="header_list-item">
            <li class="header_sub-list">
                <a class="font-14 hover-header" href="${root}AllProduct">
                    <%--                    <a class="font-14 hover-header" href="${param.productPage}">--%>
                    <i class="hover-header1 fas fa-laptop"></i>Sản phẩm
                </a>
            </li>
            <li class="header_sub-list">
                <c:choose>
                    <c:when test="${sessionScope.permission == null}">
                        <a class="font-14 hover-header" href="${root}Tutorial">
                            <i class="hover-header1 fas fa-clipboard-check"></i>Hướng dẫn
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="font-14 hover-header" href="${root}Admin">
                            <i class="hover-header1 fa-solid fa-unlock"></i>Admin
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <li class="header_sub-list">
                        <a class="font-14 hover-header" href="${root}Customer">
                            <i class="hover-header1 fa-solid fa-user-pen"></i>Tài khoản
                        </a>
                    </li>
                </c:when>
            </c:choose>
            <li class="header_sub-list">
                <a class="font-14 hover-header" href="${root}AboutUs">
                    <i class="hover-header1 fas fa-users"></i>Thông tin
                </a>
            </li>
            <li id="li-cart" class="header_sub-list">
                <a class="font-14 hover-header" href="${root}Cart">
                    <i class="hover-header1 fas fa-shopping-cart"></i>Giỏ hàng
                </a>
                <c:choose>
                    <c:when test="${listProduct.size() > 0}">
                        <div style="display: flex" id="icon-cart" class="icon-cart">${totalProductHover}</div>
                    </c:when>
                    <c:otherwise>
                        <div style="display: none" id="icon-cart" class="icon-cart">${totalProductHover}</div>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="header_sub-list">
                <c:choose>
                    <c:when test="${sessionScope.account == null}">
                        <a class="font-14 hover-header js-btn-login" role="button">
                            <i class="hover-header1 fas fa-user"></i>Đăng nhập
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="font-14 hover-header" href="${root}Logout">
                            <i class="hover-header1 fa-solid fa-right-from-bracket"></i>Đăng xuất
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>

    <div class="menu-mobile-class" id="mobile-menu">
        <i class="menu-mobile-btn ti-menu"></i>
    </div>

    <div class="progress-container">
        <div class="progress-bar" id="myBar"></div>
    </div>
</div>

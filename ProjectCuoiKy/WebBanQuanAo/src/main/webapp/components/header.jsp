<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page import="Beans.Account" %>
<!-- header section start -->
<%@page pageEncoding="UTF-8" %>
<header>
    <div class="header-top">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-4">
                    <div class="left-header clearfix">
                        <ul>
                            <li class="hd-none"><p><i class="fa fa-clock-o" aria-hidden="true"></i>${sessionScope.day}
                            </p></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9 col-sm-8">
                    <div class="right-header">
                        <ul>
                            <c:if var="accountIsExist" test="${sessionScope.account != null}"/>
                            <c:if test='${(accountIsExist && sessionScope.account.role.equals("ADMIN"))}'>
                                <li><a href="admin-dash-board"><i
                                        class="fa fa-user"></i>Quản lý</a></li>
                            </c:if>
                            <li><a href="my-account"><i
                                    class="fa fa-user"></i>${accountIsExist?account.getUserName():"Tài Khoản"}</a></li>
                            <c:if test="${sessionScope.account != null}">
                                <li><a href="shopping-cart"><i class="fa fa-shopping-cart"></i>Giỏ hàng</a></li>
                            </c:if>
                            <li><a href="checkout"><i class="fa fa-usd"></i>Thanh toán</a></li>
                            <li><a href="${accountIsExist?"logout":"login"}">
                                <i class="${accountIsExist?"fa fa-sign-out":"fa fa-sign-in"}"></i>${accountIsExist?"Đăng xuất": "Đăng nhập"}
                            </a>
                            </li>
                            <c:if test="${!accountIsExist}">
                                <li><a href='register'><i class='fa fa-user-plus'></i>Đăng ký</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="header-bottom header-bottom-one" id="sticky-menu">
        <div class="container relative">
            <div class="row">
                <div class="col-sm-2 col-md-2 col-xs-4">
                    <div class="logo">
                        <a href="home"><img src='<c:url value="/assets/imgs/logo.png"/>' alt=""/></a>
                    </div>
                </div>
                <div class="col-sm-10 col-md-10 col-xs-8 static">
                    <div class="all-manu-area">
                        <div class="mainmenu clearfix hidden-sm hidden-xs">
                            <nav>
                                <ul>
                                    <li><a href="home">Trang chủ</a></li>
                                    <li><a href="shop?type=all&brand=all&color=all&numOfProducts=9&page=1&sort=default">Cửa
                                        hàng</a>
                                    </li>
                                    <li><a href="about-us">Chúng tôi</a></li>
                                    <li><a href="contact">Liên hệ</a></li>
                                </ul>
                            </nav>
                        </div>
                        <!-- mobile menu start -->
                        <div class="mobile-menu-area hidden-lg hidden-md">
                            <div class="mobile-menu">
                                <nav id="dropdown">
                                    <ul>
                                        <li><a href="home">Trang chủ</a></li>
                                        <li>
                                            <a href="shop?type=all&brand=all&color=all&numOfProducts=9&page=1&sort=default">Cửa
                                                hàng</a>
                                            <ul>
                                                <li>
                                                    <a href="shop?type=all&brand=all&color=all&numOfProducts=9&page=1&sort=default">Tất
                                                        cả sản
                                                        phẩm</a>
                                                    <ul>
                                                        <li>
                                                            <span>Áo nam</span>
                                                            <a href="#">Áo sơ mi</a>
                                                            <a href="#">Áo thun</a>
                                                            <a href="#">Áo khoác</a>
                                                        </li>
                                                        <li>
                                                            <span>Quần nam</span>
                                                            <a href="#">Quần tây âu</a>
                                                            <a href="#">Quần jean</a>
                                                            <a href="#">Quần đùi</a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li><a href="#">Sản phẩm mới</a>
                                                    <ul>
                                                        <li>
                                                            <span>Áo nam</span>
                                                            <a href="#">Áo sơ mi</a>
                                                            <a href="#">Áo thun</a>
                                                            <a href="#">Áo khoác</a>
                                                        </li>
                                                        <li>
                                                            <span>Quần nam</span>
                                                            <a href="#">Quần tây âu</a>
                                                            <a href="#">Quần jean</a>
                                                            <a href="#">Quần đùi</a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li><a href="#">Giảm giá</a>
                                                    <ul>
                                                        <li>
                                                            <span>Áo nam</span>
                                                            <a href="#">Áo sơ mi</a>
                                                            <a href="#">Áo thun</a>
                                                            <a href="#">Áo khoác</a>
                                                        </li>
                                                        <li>
                                                            <span>Quần nam</span>
                                                            <a href="#">Quần tây âu</a>
                                                            <a href="#">Quần jean</a>
                                                            <a href="#">Quần đùi</a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li><a href="#">Sản phẩm đặc trưng</a>
                                                    <ul>
                                                        <li>
                                                            <span>Áo nam</span>
                                                            <a href="#">Áo sơ mi</a>
                                                            <a href="#">Áo thun</a>
                                                            <a href="#">Áo khoác</a>
                                                        </li>
                                                        <li>
                                                            <span>Quần nam</span>
                                                            <a href="#">Quần tây âu</a>
                                                            <a href="#">Quần jean</a>
                                                            <a href="#">Quần đùi</a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li><a href="#">Sản phẩm phổ biến</a>
                                                    <ul>
                                                        <li>
                                                            <span>Áo nam</span>
                                                            <a href="#">Áo sơ mi</a>
                                                            <a href="#">Áo thun</a>
                                                            <a href="#">Áo khoác</a>
                                                        </li>
                                                        <li>
                                                            <span>Quần nam</span>
                                                            <a href="#">Quần tây âu</a>
                                                            <a href="#">Quần jean</a>
                                                            <a href="#">Quần đùi</a>
                                                        </li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="about-us">Chúng tôi</a></li>
                                        <li><a href="contact">Liên hệ</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <!-- mobile menu end -->
                        <div class="right-header re-right-header">
                            <ul>
                                <li class="re-icon tnm"><i class="fa fa-search" aria-hidden="true"></i>
                                    <form method="get" id="searchform" action="#">
                                        <input type="text" value="" name="s" id="ws" placeholder="Search product..."/>
                                        <button type="submit"><i class="pe-7s-search"></i></button>
                                    </form>
                                </li>
                                <c:if test="${sessionScope.account != null}">
                                    <li><a href="shopping-cart"><i class="fa fa-shopping-cart"></i><span
                                            id="all-card-item"
                                            class="color1"></span></a>
                                            <%--                                    <ul class="drop-cart">--%>
                                            <%--                                        <li>--%>
                                            <%--                                            <a href="shopping-cart"><img src='<c:url value="/assets/imgs/cart/1.png"/>'--%>
                                            <%--                                                                         alt=""/></a>--%>
                                            <%--                                            <div class="add-cart-text">--%>
                                            <%--                                                <p><a href="#">White Shirt</a></p>--%>
                                            <%--                                                <p>$50.00</p>--%>
                                            <%--                                                <span>Color : Blue</span>--%>
                                            <%--                                                <span>Size   : SL</span>--%>
                                            <%--                                            </div>--%>
                                            <%--                                            <div class="pro-close">--%>
                                            <%--                                                <i class="pe-7s-close"></i>--%>
                                            <%--                                            </div>--%>
                                            <%--                                        </li>--%>
                                            <%--                                        <li>--%>
                                            <%--                                            <a href="shopping-cart"> <img src='<c:url value="/assets/imgs/cart/2.png"/>'--%>
                                            <%--                                                                          alt=""/></a>--%>
                                            <%--                                            <div class="add-cart-text">--%>
                                            <%--                                                <p><a href="#">White Shirt</a></p>--%>
                                            <%--                                                <p>$50.00 x 2</p>--%>
                                            <%--                                                <span>Color : Blue</span>--%>
                                            <%--                                                <span>Size   : SL</span>--%>
                                            <%--                                            </div>--%>
                                            <%--                                            <div class="pro-close">--%>
                                            <%--                                                <i class="pe-7s-close"></i>--%>
                                            <%--                                            </div>--%>
                                            <%--                                        </li>--%>
                                            <%--                                        <li class="total-amount clearfix">--%>
                                            <%--                                            <span class="floatleft">total</span>--%>
                                            <%--                                            <span class="floatright"><strong>= $150.00</strong></span>--%>
                                            <%--                                        </li>--%>
                                            <%--                                        <li>--%>
                                            <%--                                            <div class="goto text-center">--%>
                                            <%--                                                <a href="shopping-cart"><strong>go to cart &nbsp;<i--%>
                                            <%--                                                        class="pe-7s-angle-right"></i></strong></a>--%>
                                            <%--                                            </div>--%>
                                            <%--                                        </li>--%>
                                            <%--                                        <li class="checkout-btn text-center">--%>
                                            <%--                                            <a href="checkout">Check out</a>--%>
                                            <%--                                        </li>--%>
                                            <%--                                    </ul>--%>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- header section end -->
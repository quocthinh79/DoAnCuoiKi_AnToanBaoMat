<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.pageName}</title>
</head>
<body>
<!-- pages-title-start -->
<%@include file="/components/location.jsp" %>
<!-- pages-title-end -->
<!-- shopping-cart content section start -->
<div class="shopping-cart-area s-cart-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-xs-12">
                <div class="s-cart-all">
                    <div class="cart-form table-responsive">
                        <table id="shopping-cart-table" class="data-table cart-table">
                            <tbody>
                            <tr>
                                <th>Hình ảnh</th>
                                <th>Kích thước</th>
                                <th>Màu sắc</th>
                                <th>Số lượng</th>
                                <th class="low2">Tên sản phẩm</th>
                                <th>Giá</th>
                                <th class="low1">Xóa</th>
                            </tr>
                            <jsp:useBean id="cartItems" scope="request"
                                         type="java.util.List<Beans.CartItem>"/>
                            <c:forEach var="cartItem" items="${cartItems}">
                                <tr id="${cartItem.getIdProduct()}">
                                    <td class="sop-cart">
                                        <a href='<c:url value="/single-product?productId=${cartItem.getIdProduct()}"/>'>
                                            <img
                                                    class="primary-image"
                                                    alt=""
                                                    src='<c:url value="${cartItem.getThumbnail()}"/>'/>
                                        </a>
                                    </td>
                                    <td class="sop-cart">
                                        <span><c:out value="${cartItem.getSize()}"/></span>
                                    </td>
                                    <td class="sop-cart">
                                        <span><c:out value="${cartItem.getColor()}"/></span>
                                    </td>
                                    <td class="sop-cart">
                                        <div class="quantity ray">
                                            <input class="input-text qty text my-item-quantity"
                                                   idProduct = "${cartItem.getIdProduct()}"
                                                   initValue = "${cartItem.getQuantity()}"
                                                   value="${cartItem.getQuantity()}"
                                                   size="${cartItem.getSize()}"
                                                   color="${cartItem.getColor()}"
                                                   type="number"
                                                   min="1"
                                                   step="1">
                                        </div>
                                    </td>
                                    <td class="sop-cart">
                                        <div class="tb-beg">
                                            <a href="#"><c:out value="${cartItem.getNameProduct()}"/></a>
                                        </div>
                                    </td>
                                    <td class="sop-cart">
                                        <div class="tb-product-price font-noraure-3">
                                            <span class="amount price"><c:out value="${cartItem.getPrice()}"/> VNĐ</span>
                                        </div>
                                    </td>
                                    <td class="sop-icon1">
                                        <a class="deleteCard"
                                           idCard = "${requestScope.idCard}"
                                           idProduct = "${cartItem.getIdProduct()}"
                                           size = "${cartItem.getSize()}"
                                           color="${cartItem.getColor()}"
                                           href="/api/card">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="last-check1">
                        <div class="yith-wcwl-share yit">
                            <p class="checkout-coupon an-cop">
                                <input id="my-update-cart" type="submit" value="Update Cart"/>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="second-all-class">
                <div class="col-md-5 col-sm-12 col-xs-12">
                    <div class="sub-total">
                        <table>
                            <tbody>
                            <tr class="cart-subtotal">
                                <th>Tổng phụ :</th>
                                <td>
                                    <span class="amount total-price">297.00 VNĐ</span>
                                </td>
                            </tr>
                            <tr class="order-total">
                                <th>Tổng tất cả:</th>
                                <td>
                                    <strong>
                                        <span class="amount total-price">297.00 VNĐ</span>
                                    </strong>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="wc-proceed-to-checkout">
                        <p class="return-to-shop">
                            <a class="button wc-backward" href="<c:url value='/shop?page=1'/>">Tiếp tục mua sắm</a>
                        </p>
                        <p class="wc-proceed-to-checkout">
                            <a class="wc-forward" href="<c:url value='/checkout'/>">Xác nhận thanh toán</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- shopping-cart content section end -->
</body>
</html>
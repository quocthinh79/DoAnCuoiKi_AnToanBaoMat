<%--
  Created by IntelliJ IDEA.
  User: Thinh PC
  Date: 12/21/2021
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<div id="cart-hover" class="hover-cart">
    <div class="cover-cart-item">
        <c:forEach var="x" items="${listProduct}">
            <a href="${pageContext.request.contextPath}/Product?id=${x.maLaptop}" style="text-decoration: none;color: black;">
                <div class="cart-item">
                    <div class="img-cart-item" style="background-image: url('${root}${x.linkHinh}')">

                    </div>
                    <div class="details-cart-item">
                        <div class="infor-cart-item">${x.tenLaptop}</div>
                        <div class="num-cart-item">Số lượng: ${x.soluong}</div>
                        <div style="color: crimson" class="price-cart-item">${x.giaban}</div>
                    </div>
                </div>
            </a>
        </c:forEach>

    </div>
    <div class="total-cart">
        <div class="total-price-infor">
<%--            <div class="total-product"></div>--%>
<%--            <div class="total-price"></div>--%>
            <div class="total-product">Tổng tiền ${totalProductHover} sản phẩm</div>
            <div class="total-price">${cost} VND</div>
        </div>
        <div role="button" class="btn-view-cart"><a href="${root}Cart" class="btn-tt">Xem giỏ hàng</a></div>
    </div>
</div>

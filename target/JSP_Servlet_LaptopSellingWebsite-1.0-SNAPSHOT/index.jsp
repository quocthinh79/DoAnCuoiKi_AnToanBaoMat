<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="root"/>
<%--<%--%>
<%--    Locale loc = Locale.getDefault();--%>
<%--    NumberFormat nf = NumberFormat.getCurrencyInstance(loc);--%>
<%--%>--%>
<%--<% DecimalFormat formatter = new DecimalFormat("###.###.###"); %>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Metaverse Laptop</title>
    <link href="image/logo-re.png" rel="icon">
    <link href="css/style.css" rel="stylesheet">
    <link href="fonts/themify-icons/themify-icons.css" rel="stylesheet">
    <link href="fonts/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
          integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
          referrerpolicy="no-referrer" rel="stylesheet"/>
    <!--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css" integrity="sha512-17EgCFERpgZKcm0j0fEq1YCJuyAWdz9KUtv1EjVuaOz8pDnh/0nZxmU6BBXwaaxqoi9PQXnRWqlcDB027hgv9A==" crossorigin="anonymous" referrerpolicy="no-referrer" />-->
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css"
          integrity="sha512-yHknP1/AwR+yx26cB1y0cjvQUMvEa2PFzt1c9LlS4pRQ5NOTZFWbhBig+X9G9eYW/8m0/4OXNx8pxJ6z57x0dw=="
          referrerpolicy="no-referrer" rel="stylesheet"/>
    <link href="css/responsiveIndex.css" rel="stylesheet">
    <link href="css/loading.css" rel="stylesheet">
</head>
<body>
<div id="main">
    <%@include file="layout/header.jsp" %>
    <%@include file="layout/cart-hover.jsp" %>
    <div id="content1" class="search-product">
        <div id="cover-all-product" class="cover-all-product">
            <div id="product" class="all-product-cover">
            </div>
        </div>
    </div>
    <div id="slider">
        <div class="slider-image" style="background-image: url('image/slider/Slider-image1.jpg')"></div>
        <div class="slider-image" style="background-image: url('image/slider/Slider-image2.jpg')"></div>
        <div class="slider-image" style="background-image: url('image/slider/Slider-image3.jpg')"></div>
        <div class="slider-image" style="background-image: url('image/slider/Slider-image4.jpg')"></div>
        <div class="slider-image" style="background-image: url('image/slider/Slider-image5.jpg')"></div>
        <button class="btn-slider previous"><i class="fas fa-chevron-left"></i></button>
        <button class="btn-slider  next"><i class="fas fa-chevron-right"></i></button>
        <div class="slider-element">
            <div class="slider-items slider-left"></div>
            <div class="slider-items slider-center"></div>
            <div class="slider-items slider-right"></div>
        </div>
    </div>
    <div id="content">
        <div class="outstanding-brand">
            <div class="outstading-brand-main">
                <div class="title-outstanding">
                    <div class="border-outstanding"></div>
                    <div class="title-main">Thương hiệu nổi bật</div>
                </div>
                <div class="outstand-product">
                    <%--                    <jsp:useBean id="topProducer" scope="request" type="java.util.List"/>--%>
                    <c:forEach var="x" items="${topProducer}">
                        <div class="outstand-product-items">
                            <a class="click-css" href="AllProduct?idHang=${x.tenHang}">
                                <div class="img-outstand" style="background-image: url('${x.logoNgang}')">
                                </div>
                            </a>
                            <div class="title-outstand">${x.tenHang}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="product">
            <jsp:useBean id="productsTop4" scope="request" type="java.util.List"/>
            <c:forEach var="y" items="${productsTop4}">
                <div class="product-biggest">
                    <div class="product-item-big">
                        <div class="title-product">
                            <div class="border-product"></div>
                            <div class="title-main-product">${y.tenHang} (${y.slogan})</div>
                            <a class="view-all" href="AllProduct?idHang=${y.tenHang}">Xem tất cả <i
                                    class="fas fa-angle-right"></i></a>
                        </div>
                        <div class="product-cover">
                            <div id="poster" style="overflow: hidden; border-radius: 0 0 0 10px; flex: 1">
                                <a class="product-hover-selling" href="AllProduct?idHang=${y.tenHang}">
                                    <div class="product-selling"
                                         style="background-image: url('${y.poster}')"></div>
                                </a></div>
                            <div class="product-item-cover">
                                <jsp:useBean id="productsTop" scope="request" type="java.util.List"/>
                                <c:forEach var="z" items="${productsTop}">
                                    <c:if test="${z.hangSX == y.tenHang}">
                                        <a class="product-hover-items" href="Product?id=${z.maLapTop}"
                                           style="width: calc(100% / 2)">
                                            <div class="product-items  border-item-top1">
                                                <div class="title-product-item">
                                                        ${z.tenLaptop}
                                                </div>
                                                <div class="slogan-item">
                                                        ${z.giaBan}
                                                </div>
                                                <div class="img-product-item"
                                                     style="background-image: url('${z.linkHinh1}')">
                                                </div>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <%@include file="layout/product-best-seller.jsp" %>
    </div>

    <%@include file="layout/footer.jsp" %>
</div>
<%@include file="layout/login.jsp" %>
<%@include file="layout/fogot.jsp" %>
<%@include file="layout/register.jsp" %>
<%@include file="layout/otp.jsp" %>
<%@include file="layout/newPass.jsp" %>
<%--<!--Back to top-->--%>
<%@include file="layout/back-to-top.jsp" %>

<div id="loading">
    <div class="loadingio-spinner-eclipse-okswoys3or7">
        <div class="ldio-yzbzl1tp5rn">
            <div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    fetch('${pageContext.request.contextPath}/Index').then(res => document.getElementById('loading').remove());
</script>
<script>
    let search = document.getElementById('input_search');
    let div = document.getElementById('content1');
    search.addEventListener('input', function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/Search',
            type: 'GET',
            data: {
                txt: search.value
            },
            success: function (response) {
                $('#product').html(response)
                let product = document.querySelectorAll('.hover-all-product');
                let slide = document.getElementById('slider');
                if (search.value == '' || product.length <= 0) {
                    div.style.display = 'none';
                    slide.style.display = 'block';
                } else {
                    div.style.display = 'flex';
                    slide.style.display = 'none';
                }
            },
            error: function () {
            }
        });
    })
</script>
<script>
    let price = document.querySelectorAll('.slogan-item');
    let price1 = document.querySelectorAll('.product-price');
    for (let i = 0; i < price.length; i++) {
        price[i].innerHTML = parseInt(price[i].textContent).toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }
    for (let i = 0; i < price1.length; i++) {
        price1[i].innerHTML = parseInt(price1[i].textContent).toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }
    let pric2 = document.querySelectorAll('.price-cart-item');
    let pric3 = document.querySelectorAll('.total-price');
    for (let i = 0; i < pric2.length; i++) {
        pric2[i].innerHTML = parseInt(pric2[i].textContent).toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }
    for (let i = 0; i < pric3.length; i++) {
        pric3[i].innerHTML = parseInt(pric3[i].textContent).toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }
</script>
<script src="js/register.js"></script>
<script src="js/register.js"></script>
<script crossorigin="anonymous" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script crossorigin="anonymous"
        integrity="sha512-XtmMtDEcNz2j7ekrtHvOVR4iwwaD6o/FUJe6+Zq+HgcCsk3kj4uSQQR8weQ2QVj1o0Pk6PwYLohm206ZzNfubg=="
        referrerpolicy="no-referrer"
        src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<script src="js/slider.js"></script>
<script src="js/product-bestseller.js"></script>
<script src="js/nav-responsive.js"></script>
<script src="js/BackToTop.js"></script>
<script src="js/Scroll-Indicator.js"></script>
<script src="js/visibleCart.js"></script>
</html>
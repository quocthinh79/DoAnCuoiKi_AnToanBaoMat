<%--
  Created by IntelliJ IDEA.
  User: Thinh PC
  Date: 12/21/2021
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="root"/>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Metaverse Laptop</title>
    <link href="${root}image/logo-re.png" rel="icon">
    <link href="${root}css/style.css" rel="stylesheet">
    <link href="${root}css/loading.css" rel="stylesheet">
    <link href="${root}fonts/themify-icons/themify-icons.css" rel="stylesheet">
    <link href="${root}fonts/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="${root}fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
          integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
          referrerpolicy="no-referrer" rel="stylesheet"/>
    <!--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css" integrity="sha512-17EgCFERpgZKcm0j0fEq1YCJuyAWdz9KUtv1EjVuaOz8pDnh/0nZxmU6BBXwaaxqoi9PQXnRWqlcDB027hgv9A==" crossorigin="anonymous" referrerpolicy="no-referrer" />-->
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css"
          integrity="sha512-yHknP1/AwR+yx26cB1y0cjvQUMvEa2PFzt1c9LlS4pRQ5NOTZFWbhBig+X9G9eYW/8m0/4OXNx8pxJ6z57x0dw=="
          referrerpolicy="no-referrer" rel="stylesheet"/>
    <link href="${root}css/responsiveIndex.css" rel="stylesheet">
</head>
<body>
<div id="main">
    <%@include file="../layout/header.jsp" %>
    <%@include file="../layout/cart-hover.jsp" %>
    <div id="content1">
        <div id="title-pro-all" class="title-pro-all">
            <jsp:useBean id="allProducer" scope="request" type="java.util.List"/>
            <c:forEach var="x" items="${allProducer}">
                <div class="logo-lap" style="background-image: url('${root}${x.logoNgang}')"></div>
                <span>${x.slogan}</span>
            </c:forEach>
        </div>
        <div class="filter-product">
            <div class="title-filter-product">
                <div style="border-left: 3px solid #111111;padding-left: 8px">Bộ lọc</div>
            </div>
            <%--            <form id="sort1" action="Sort" method="post">--%>
            <div id="fiter-hangsx" class="filter">
                <div class="title-filter">
                    Thương hiệu
                </div>
                <div class="filter-items">
                    <%--                    <jsp:useBean id="allProducer" scope="request" type="java.util.List"/>--%>
                    <c:forEach var="x" items="${filter}">
                        <jsp:useBean id="listHangSX" class="java.util.ArrayList"/>
                        <c:if test="${!listHangSX.contains(x.hangSX)}">
                            <c:set var="noUse" value="${listHangSX.add(x.hangSX)}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="x" items="${listHangSX}">
                        <c:if test="${not empty requestScope.valuesMap}">
                            <c:if test="${fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="hang" value="${x}"
                                        class="filter-item fiter-hangsx  filter-class-add">${x}
                                    <div style="display:block;" class="tick"><i class="fas fa-check"
                                                                                style="position: absolute;  left: 7px"></i>
                                    </div>
                                </button>
                            </c:if>
                            <c:if test="${not fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="hang" value="${x}"
                                        class="filter-item fiter-hangsx">${x}
                                    <div class="tick"><i class="fas fa-check"
                                                         style="position: absolute;  left: 7px"></i></div>
                                </button>
                            </c:if>
                        </c:if>
                        <c:if test="${empty requestScope.valuesMap}">
                            <button
                                    name="hang" value="${x}"
                                    class="filter-item fiter-hangsx">${x}
                                <div class="tick"><i class="fas fa-check"
                                                     style="position: absolute;  left: 7px"></i></div>
                            </button>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="filter">
                <div class="title-filter">
                    Màu sắc
                </div>
                <div class="filter-items">
                    <c:forEach var="x" items="${filter}">
                        <jsp:useBean id="list" class="java.util.ArrayList"/>
                        <c:if test="${!list.contains(x.mau)}">
                            <c:set var="noUse" value="${list.add(x.mau)}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="y" items="${list}">
                        <c:if test="${not empty requestScope.valuesMap}">
                            <c:if test="${fn:contains(requestScope.valuesMap, y)}">
                                <button
                                        name="mau" value="${y}"
                                        class="filter-item  filter-class-add">${y}
                                    <div style="display:block;" class="tick"><i class="fas fa-check"
                                                                                style="position: absolute;  left: 7px"></i>
                                    </div>
                                </button>
                            </c:if>
                            <c:if test="${not fn:contains(requestScope.valuesMap, y)}">
                                <button
                                        name="mau" value="${y}"
                                        class="filter-item ">${y}
                                    <div class="tick"><i class="fas fa-check"
                                                         style="position: absolute;  left: 7px"></i></div>
                                </button>
                            </c:if>
                        </c:if>
                        <c:if test="${empty requestScope.valuesMap}">
                            <button
                                    name="mau" value="${y}"
                                    class="filter-item ">${y}
                                <div class="tick"><i class="fas fa-check"
                                                     style="position: absolute;  left: 7px"></i></div>
                            </button>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="filter">
                <div class="title-filter">
                    Series CPU
                </div>
                <div class="filter-items">
                    <c:forEach var="x" items="${filter}">
                        <c:set var="cpuParts" value="${fn:split(x.cpu, ' ')}"/>
                        <c:set var="string" value="${cpuParts[1]}${' '}${cpuParts[2]}"/>
                        <jsp:useBean id="listCPU" class="java.util.ArrayList"/>
                        <c:if test="${!listCPU.contains(string)}">
                            <c:set var="noUse" value="${listCPU.add(string)}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="x" items="${listCPU}">
                        <c:if test="${not empty requestScope.valuesMap}">
                            <c:if test="${fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="cpu" value="${x}"
                                        class="filter-item filter-class-add">${x}
                                    <div style="display:block;" class="tick"><i class="fas fa-check"
                                                                                style="position: absolute;  left: 7px"></i>
                                    </div>
                                </button>
                            </c:if>
                            <c:if test="${not fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="cpu" value="${x}"
                                        class="filter-item">${x}
                                    <div class="tick"><i class="fas fa-check"
                                                         style="position: absolute;  left: 7px"></i></div>
                                </button>
                            </c:if>
                        </c:if>
                        <c:if test="${empty requestScope.valuesMap}">
                            <button
                                    name="cpu" value="${x}"
                                    class="filter-item">${x}
                                <div class="tick"><i class="fas fa-check"
                                                     style="position: absolute;  left: 7px"></i></div>
                            </button>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="filter">
                <div class="title-filter">
                    Dung lượng RAM
                </div>
                <div class="filter-items">
                    <c:forEach var="x" items="${filter}">
                        <jsp:useBean id="listRAM" class="java.util.ArrayList"/>
                        <c:if test="${!listRAM.contains(x.ram)}">
                            <c:set var="noUse" value="${listRAM.add(x.ram)}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="x" items="${listRAM}">
                        <c:if test="${not empty requestScope.valuesMap}">
                            <c:if test="${fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="ram" value="${x}"
                                        class="filter-item filter-class-add">${x}
                                    <div style="display:block;" class="tick"><i class="fas fa-check"
                                                                                style="position: absolute;  left: 7px"></i>
                                    </div>
                                </button>
                            </c:if>
                            <c:if test="${not fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="ram" value="${x}"
                                        class="filter-item">${x}
                                    <div class="tick"><i class="fas fa-check"
                                                         style="position: absolute;  left: 7px"></i></div>
                                </button>
                            </c:if>
                        </c:if>
                        <c:if test="${empty requestScope.valuesMap}">
                            <button
                                    name="ram" value="${x}"
                                    class="filter-item">${x}
                                <div class="tick"><i class="fas fa-check"
                                                     style="position: absolute;  left: 7px"></i></div>
                            </button>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="filter">
                <div class="title-filter">
                    Series
                </div>
                <div class="filter-items">
                    <c:forEach var="x" items="${filter}">
                        <jsp:useBean id="listSeries" class="java.util.ArrayList"/>
                        <c:if test="${!listSeries.contains(x.series)}">
                            <c:set var="noUse" value="${listSeries.add(x.series)}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="x" items="${listSeries}">
                        <c:if test="${not empty requestScope.valuesMap}">
                            <c:if test="${fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="series" value="${x}"
                                        class="filter-item filter-class-add">${x}
                                    <div style="display:block;" class="tick"><i class="fas fa-check"
                                                                                style="position: absolute;  left: 7px"></i>
                                    </div>
                                </button>
                            </c:if>
                            <c:if test="${not fn:contains(requestScope.valuesMap, x)}">
                                <button
                                        name="series" value="${x}"
                                        class="filter-item">${x}
                                    <div class="tick"><i class="fas fa-check"
                                                         style="position: absolute;  left: 7px"></i></div>
                                </button>
                            </c:if>
                        </c:if>
                        <c:if test="${empty requestScope.valuesMap}">
                            <button
                                    name="series" value="${x}"
                                    class="filter-item">${x}
                                <div class="tick"><i class="fas fa-check"
                                                     style="position: absolute;  left: 7px"></i></div>
                            </button>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="filter-another">
            <div class="title-filter">
                Sắp xếp theo
            </div>
            <div class="filter-items">
                <%--                <button name="btnFilterPrice" value="bestSeller" class="filter-item-another">Bán chạy--%>
                <%--                    <div class="tick tick-another"><i class="fas fa-check" style="position: absolute;  left: 7px"></i>--%>
                <%--                    </div>--%>
                <%--                </button>--%>
                <c:if test="${orderBy == 'desc'}">
                    <button id="desc" name="desc" value="desc"
                            class="filter-item-another click-filter  filter-class-add">Giá
                        giảm dần
                        <div style="display: block" class="tick tick-another"><i class="fas fa-check"
                                                                                 style="position: absolute;  left: 7px"></i>
                        </div>
                    </button>
                    <button id="asc" name="asc" value="asc" class="filter-item-another click-filter">Giá tăng
                        dần
                        <div class="tick tick-another"><i class="fas fa-check"
                                                          style="position: absolute;  left: 7px"></i>
                        </div>
                    </button>
                </c:if>
                <c:if test="${orderBy == 'asc'}">
                    <button id="desc" name="desc" value="desc" class="filter-item-another click-filter">Giá
                        giảm dần
                        <div class="tick tick-another"><i class="fas fa-check"
                                                          style="position: absolute;  left: 7px"></i>
                        </div>
                    </button>
                    <button id="asc" name="asc" value="asc" class="filter-item-another click-filter  filter-class-add">
                        Giá tăng
                        dần
                        <div style="display: block" class="tick tick-another"><i class="fas fa-check"
                                                                                 style="position: absolute;  left: 7px"></i>
                        </div>
                    </button>
                </c:if>
                <c:if test="${orderBy == '' || orderBy == null}">
                    <button id="desc" name="desc" value="desc" class="filter-item-another click-filter">Giá
                        giảm dần
                        <div class="tick tick-another"><i class="fas fa-check"
                                                          style="position: absolute;  left: 7px"></i>
                        </div>
                    </button>
                    <button id="asc" name="asc" value="asc" class="filter-item-another click-filter">Giá tăng
                        dần
                        <div class="tick tick-another"><i class="fas fa-check"
                                                          style="position: absolute;  left: 7px"></i>
                        </div>
                    </button>
                </c:if>
                <div class="price-filter">
                    <div class="filter-item-another" style="cursor: auto">
                        <input value="${lowestPrice}" id="lowestPrice" name="lowestPrice" class="input-filter-product"
                               placeholder="Giá thấp nhất">
                    </div>
                    <span style="line-height: 2; text-align: center">-</span>
                    <div class="filter-item-another" style="cursor: auto">
                        <input value="${highPrice}" id="highPrice" name="highPrice" class="input-filter-product"
                               placeholder="Giá cao nhất">
                    </div>
                </div>
                <button style="display: ${display}" value="price" name="btn-filter-price" class="btn-search-filter"
                        type="submit">
                    Tìm
                </button>
            </div>
        </div>
        <div class="cover-all-product">
            <div id="product" class="all-product-cover">
                <c:forEach var="x" items="${allProduct}">
                    <div class="hover-all-product">
                        <a class="all-product-item" href="${pageContext.request.contextPath}/Product?id=${x.maLapTop}">
                            <div class="img-all-product-item"
                                 style="background-image: url('${root}${x.image}')">
                            </div>
                            <c:if test="${x.trangThai == 0}">
                                <div class="status">HẾT HÀNG</div>
                            </c:if>
                            <div class="infor-all-product-item">
                                    ${x.nameProduct}
                            </div>
                            <div class="price-all-product-item">
                                    ${x.giaBan}
                            </div>
<%--                            <div class="sale-all-product-item">
                                <span class="origin-price">33.999.000đ</span> <span>11%</span>
                            </div>--%>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div id="not-found" class="not-found">
                <img src="${root}image/not%20found/no-products-found.png">
                <br>
                <div>Không tìm thấy bất kì sản phẩm nào</div>
            </div>
        </div>
        <div id="pagination" class="pagination p1">
            <ul>
                <c:forEach var="i" begin="1" end="${totalPage}">
                    <c:if test="${i == page}">
                        <a value="${i}" class="paging is-active">
                            <li>${i}</li>
                        </a>
                    </c:if>
                    <c:if test="${i != page}">
                        <a value="${i}" class="paging">
                            <li>${i}</li>
                        </a>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <%@include file="../layout/footer.jsp" %>
</div>
<%@include file="../layout/login.jsp" %>
<%@include file="../layout/fogot.jsp" %>
<%@include file="../layout/register.jsp" %>
<!--Back to top-->
<%@include file="../layout/back-to-top.jsp" %>
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
                let slide = document.getElementById('pagination')
                if (search.value == '' || product.length <= 0) {
                    slide.style.display = 'flex';
                } else {
                    slide.style.display = 'none';
                }
            },
            error: function () {
            }
        });
    })
</script>
<script>
    let notFound = document.getElementById('not-found');
    let product = document.querySelectorAll('.hover-all-product');
    if (product.length <= 0) {
        notFound.style.display = 'flex';
    }
</script>
<script>
    var paging = document.querySelectorAll('.paging');
    var searchParams = new URLSearchParams(window.location.search);
    var param = searchParams.get('idHang');
    var filterAnother = document.querySelectorAll('.click-filter');
    let value;
    var boolean = 0;
    for (let i = 0; i < filterAnother.length; i++) {
        filterAnother[i].addEventListener('click', function () {
            value = $(this).val();
            $.ajax({
                url: '${pageContext.request.contextPath}/Pagination',
                type: 'POST',
                data: {
                    value: value
                },
                success: function (response) {
                    $('#product').html(response)
                },
                error: function () {
                }
            });
            for (var i = 0; i < paging.length; i++) {
                paging[i].classList.remove('is-active');
            }
            paging[0].classList.add('is-active');
        })
    }
    for (var i = 0; i < paging.length; i++) {
        paging[i].addEventListener('click', function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/Pagination',
                type: 'POST',
                data: {
                    page: this.getAttribute('value'),
                    idHang: param,
                    value: value
                },
                success: function (response) {
                    $('#product').html(response)
                },
                error: function () {
                }
            });
            for (var i = 0; i < paging.length; i++) {
                paging[i].classList.remove('is-active');
            }
            this.classList.add('is-active');
        });
    }
</script>
<script>
    var price = document.querySelectorAll('.price-all-product-item');
    for (var i = 0; i < price.length; i++) {
        price[i].innerHTML = parseInt(price[i].textContent).toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }
</script>
<script>
    var hangSX = document.querySelectorAll('.fiter-hangsx');
    var filterHangSX = document.getElementById('fiter-hangsx');
    var logoLap = document.querySelectorAll('.logo-lap');
    var allProductLogo = document.getElementById('title-pro-all');
    if (logoLap.length > 1) {
        allProductLogo.style.display = 'none';
    }
    if (hangSX.length <= 1) {
        filterHangSX.style.display = 'none';
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    filter()

    function filter() {
        var btnFilterAnother = document.querySelectorAll('.filter-item');
        for (var i = 0; i < btnFilterAnother.length; i++) {
            btnFilterAnother[i].addEventListener('click', function () {
                var value = $(this).val();
                var name = $(this).attr("name");
                location.href = '${pageContext.request.contextPath}/Sort?name=' + name + '&value=' + value + ''
            })
        }
        var btnFilterPrice = document.querySelector('.btn-search-filter');
        btnFilterPrice.addEventListener('click', function () {
            var name = $(this).attr("name");
            let low = $('#lowestPrice').val();
            let high = $('#highPrice').val();
            location.href = '${pageContext.request.contextPath}/Sort?name=' + name + '&highPrice=' + high + '&lowestPrice=' + low + ''
        })
    }
</script>
<script>
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
<script src="${root}js/register.js"></script>
<script src="${root}js/register.js"></script>
<script crossorigin="anonymous" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script crossorigin="anonymous"
        integrity="sha512-XtmMtDEcNz2j7ekrtHvOVR4iwwaD6o/FUJe6+Zq+HgcCsk3kj4uSQQR8weQ2QVj1o0Pk6PwYLohm206ZzNfubg=="
        referrerpolicy="no-referrer"
        src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<script src="${root}js/nav-responsive.js"></script>
<script src="${root}js/BackToTop.js"></script>
<script src="${root}js/Scroll-Indicator.js"></script>
<script src="${root}js/visibleCart.js"></script>
<script src="${root}js/click-filter.js"></script>
</html>

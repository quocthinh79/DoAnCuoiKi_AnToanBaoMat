<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>${requestScope.pageName}</title>
</head>
<body>
<!-- pages-title-start -->
<%@include file="/components/location.jsp" %>
<!-- pages-title-end -->
<!-- shop-style content section start -->
<section class="pages products-page section-padding-top">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-lg-3 col-sm-12">
                <div class="all-shop-sidebar">
                    <div class="top-shop-sidebar">
                        <h3 class="wg-title">Lựa chọn</h3>
                    </div>
                    <div class="shop-one">
                        <h3 class="wg-title2">Phân loại</h3>
                        <ul class="product-categories">
                            <li style="cursor: pointer"
                                class="cat-item ${requestScope.type.equals("all")?'current-cat':null}">
                                <a onclick="filterType('all')">Tất cả</a>
                            </li>
                            <c:forEach var="tag" items="${requestScope.tags}">
                                <li style="cursor: pointer"
                                    class="cat-item ${requestScope.type.equals(tag.idTag)?'current-cat':null}">
                                    <a onclick="filterType('${tag.idTag}')">${tag.nameTag}</a>
                                    <span class="count">(${tag.numOfProducts})</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="shop-one">
                        <h3 class="wg-title2">Nhãn hiệu</h3>
                        <ul class="product-categories">
                            <li style="cursor: pointer"
                                class="cat-item ${requestScope.brand.equals("all")?'current-cat':null}">
                                <a onclick="filterBrand('all')">Tất cả</a>
                            </li>
                            <c:forEach var="brand" items="${requestScope.brands}">
                                <li style="cursor: pointer"
                                    class="cat-item ${requestScope.brand.equals(brand.idBrand)?'current-cat':null}">
                                    <a onclick="filterBrand('${brand.idBrand}')">${brand.nameBrand}</a>
                                    <span class="count">(${brand.numOfProducts})</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <%--                    <div class="shop-one re-shop-one">--%>
                    <%--                        <h3 class="wg-title2">Chọn giá</h3>--%>
                    <%--                        <div class="widget shop-filter">--%>
                    <%--                            <div class="info_widget">--%>
                    <%--                                <div class="price_filter">--%>
                    <%--                                    <div id="slider-range"></div>--%>
                    <%--                                    <div id="amount">--%>
                    <%--                                        <input--%>
                    <%--                                                type="text"--%>
                    <%--                                                name="first_price"--%>
                    <%--                                                class="first_price"--%>
                    <%--                                        />--%>
                    <%--                                        <input--%>
                    <%--                                                type="text"--%>
                    <%--                                                name="last_price"--%>
                    <%--                                                class="last_price"--%>
                    <%--                                        />--%>
                    <%--                                        <button class="button-shop" type="submit">--%>
                    <%--                                            <i class="fa fa-search search-icon"></i>--%>
                    <%--                                        </button>--%>
                    <%--                                    </div>--%>
                    <%--                                </div>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <div class="shop-one re-shop-one">
                        <h3 class="wg-title2">Chọn màu</h3>
                        <ul class="product-categories">
                            <li style="cursor: pointer"
                                class="cat-item ${requestScope.color.equals("all")?'current-cat':null}">
                                <a onclick="filterColor('all')">Tất cả</a>
                            </li>
                            <c:forEach var="color" items="${requestScope.colors}">
                                <li style="cursor: pointer"
                                    class="cat-item cat-item-8 ${requestScope.color.equals(color.idColor)?'current-cat':null}">
                                    <a onclick="filterColor('${color.idColor}')">${color.nameColor}</a>
                                    <span class="count">(${color.numOfProducts})</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <%--                    <div class="top-shop-sidebar an-shop">--%>
                    <%--                        <h3 class="wg-title">Giảm giá</h3>--%>
                    <%--                        <ul>--%>
                    <%--                            <li class="b-none">--%>
                    <%--                                <div class="tb-recent-thumbb">--%>
                    <%--                                    <a href="#">--%>
                    <%--                                        <img--%>
                    <%--                                                class="attachment"--%>
                    <%--                                                src='<c:url value="/assets/imgs/products/1.jpg"/>'--%>
                    <%--                                                alt=""--%>
                    <%--                                        />--%>
                    <%--                                    </a>--%>
                    <%--                                </div>--%>
                    <%--                                <div class="tb-recentb">--%>
                    <%--                                    <div class="tb-beg">--%>
                    <%--                                        <a href="#">Lambskin Shoe</a>--%>
                    <%--                                    </div>--%>
                    <%--                                    <div class="tb-product-price font-noraure-3">--%>
                    <%--                                        <span class="amount">$180.00</span>--%>
                    <%--                                    </div>--%>
                    <%--                                </div>--%>
                    <%--                            </li>--%>
                    <%--                            <li class="b-none">--%>
                    <%--                                <div class="tb-recent-thumbb">--%>
                    <%--                                    <a href="#">--%>
                    <%--                                        <img--%>
                    <%--                                                class="attachment"--%>
                    <%--                                                src='<c:url value="/assets/imgs/products/2.jpg"/>'--%>
                    <%--                                                alt=""--%>
                    <%--                                        />--%>
                    <%--                                    </a>--%>
                    <%--                                </div>--%>
                    <%--                                <div class="tb-recentb">--%>
                    <%--                                    <div class="tb-beg">--%>
                    <%--                                        <a href="#">Luxury Leather Bag</a>--%>
                    <%--                                    </div>--%>
                    <%--                                    <div class="tb-product-price font-noraure-3">--%>
                    <%--                                        <span class="amount2 ana">$170.00</span>--%>
                    <%--                                    </div>--%>
                    <%--                                </div>--%>
                    <%--                            </li>--%>
                    <%--                            <li class="b-none agn">--%>
                    <%--                                <div class="tb-recent-thumbb">--%>
                    <%--                                    <a href="#">--%>
                    <%--                                        <img--%>
                    <%--                                                class="attachment"--%>
                    <%--                                                src='<c:url value="/assets/imgs/products/3.jpg"/>'--%>
                    <%--                                                alt=""--%>
                    <%--                                        />--%>
                    <%--                                    </a>--%>
                    <%--                                </div>--%>
                    <%--                                <div class="tb-recentb">--%>
                    <%--                                    <div class="tb-beg">--%>
                    <%--                                        <a href="#">Vintage Glasses</a>--%>
                    <%--                                    </div>--%>
                    <%--                                    <div class="tb-product-price font-noraure-3">--%>
                    <%--                                        <span class="amount2 ana">$170.00</span>--%>
                    <%--                                    </div>--%>
                    <%--                                </div>--%>
                    <%--                            </li>--%>
                    <%--                        </ul>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="ro-info-box-wrap tpl3 st">--%>
                    <%--                        <div class="tb-image">--%>
                    <%--                            <img src='<c:url value="/assets/imgs/products/4.jpg"/>' alt=""/>--%>
                    <%--                        </div>--%>
                    <%--                        <div class="tb-content">--%>
                    <%--                            <div class="tb-content-inner an-inner">--%>
                    <%--                                <h5>Quần giảm giá</h5>--%>
                    <%--                                <h6>--%>
                    <%--                                    <a href="#">Mua ngay</a>--%>
                    <%--                                </h6>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                </div>
            </div>
            <div class="col-md-8 col-lg-9 col-sm-12">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="features-tab">
                            <!-- Nav tabs -->
                            <div class="shop-all-tab">
                                <div class="re-shop">
                                    <div class="sort-by">
                                        <div class="shop6">
                                            <label>Sắp xếp:</label>
                                            <select onchange="selectSort(this)">
                                                <option value="default" ${requestScope.sort.equals("default") ? "selected": null}>
                                                    Mặc định
                                                </option>
                                                <%--                                                <option value="">Hàng mua nhiều</option>--%>
                                                <option value="asc" ${requestScope.sort.equals("asc") ? "selected": null}>
                                                    Giá tăng dần
                                                </option>
                                                <option value="desc" ${requestScope.sort.equals("desc") ? "selected": null}>
                                                    Giá giảm dần
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="shop5">
                                        <label>Số sản phẩm:</label>
                                        <select id="select-num-products" onchange="selectNumProducts(this)">
                                            <option value="9" ${requestScope.numOfProducts == 9 ? "selected": null}>9
                                            </option>
                                            <option value="12" ${requestScope.numOfProducts == 12 ? "selected": null}>12
                                            </option>
                                            <option value="24" ${requestScope.numOfProducts == 24 ? "selected": null}>24
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="home">
                                    <div class="row">
                                        <div class="shop-tab">
                                            <!-- single-product start -->
                                            <jsp:useBean id="products" scope="request"
                                                         type="java.util.List<Beans.Product>"/>
                                            <c:forEach var="product" items="${products}">
                                                <div class="col-md-4 col-lg-4 col-sm-6">
                                                    <div class="single-product s-top">
                                                        <div class="product-img">
                                                            <div class="pro-type">
                                                                <span>new</span>
                                                            </div>
                                                            <a href="single-product?productId=${product.getId()}">
                                                                <img
                                                                        src='<c:url value="${product.getThumbnail()}"/>'
                                                                        alt="Product Title"
                                                                />
                                                            </a>
                                                        </div>
                                                        <div class="product-dsc">
                                                            <h3>
                                                                <a href="#">${product.getName()}</a>
                                                            </h3>
                                                            <div class="star-price">
                                                                <span class="price-left">${product.getPrice()}VNĐ</span>
                                                                <span class="star-right">
                                                                    <c:if var="check" test="${product.getRate() != 0}">
                                                                        <c:forEach var="i" begin="0"
                                                                                   end="${product.getRate()}">
                                                                            <i class="fa fa-star"></i>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <!-- single-product end -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="shop-all-tab-cr shop-bottom">
                                <div class="two-part">
                                    <div class="shop5 page">
                                        <ul>
                                            <li>
                                                <c:if test="${currentPage != 1}">
                                                    <a href="shop?type=${requestScope.type}&brand=${requestScope.brand}&color=${requestScope.color}&numOfProducts=${requestScope.numOfProducts}&page=${requestScope.currentPage - 1}&sort=${requestScope.sort}"><i
                                                            class="fa fa-arrow-left"></i></a>
                                                </c:if>
                                                <c:forEach var="i" begin="1" end="${endPage}">
                                                    <a class="${(i == currentPage)?"active":""}"
                                                       href="?type=${requestScope.type}&brand=${requestScope.brand}&color=${requestScope.color}&numOfProducts=${requestScope.numOfProducts}&page=${i}&sort=${requestScope.sort}">${i}</a>
                                                </c:forEach>
                                                <c:if test="${currentPage!=endPage}">
                                                    <a href="shop?type=${requestScope.type}&brand=${requestScope.brand}&color=${requestScope.color}&numOfProducts=${requestScope.numOfProducts}&page=${requestScope.currentPage +1}&sort=${requestScope.sort}"><i
                                                            class="fa fa-arrow-right"></i></a>
                                                </c:if>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function selectNumProducts(thisElememt) {
        let value = thisElememt.value;
        let url = document.URL;
        let first = url.substring(0, url.indexOf("?"));
        let newUrl = first + "?type=${requestScope.type}&brand=${requestScope.brand}&color=${requestScope.color}&numOfProducts=" + value + "&page=1&sort=${requestScope.sort}";
        window.location.replace(newUrl);
    }

    function selectSort(thisElememt) {
        let value = thisElememt.value;
        let url = document.URL;
        let first = url.substring(0, url.indexOf("?"));
        let newUrl = first + "?type=${requestScope.type}&brand=${requestScope.brand}&color=${requestScope.color}&numOfProducts=${requestScope.numOfProducts}&page=1&sort=" + value;
        window.location.replace(newUrl);
    }

    function filterType(type) {
        let url = document.URL;
        let first = url.substring(0, url.indexOf("?"));
        <%--let newUrl = first + "?type=${requestScope.type}&brand=${requestScope.brand}&color=${requestScope.color}&numOfProducts=${requestScope.numOfProducts}&page=${requestScope.currentPage}&sort=${requestScope.sort}";--%>
        let newUrl = first + "?type=" + type + "&brand=${requestScope.brand}&color=${requestScope.color}&numOfProducts=${requestScope.numOfProducts}&page=1&sort=${requestScope.sort}";
        window.location.replace(newUrl);
    }

    function filterBrand(brand) {
        let url = document.URL;
        let first = url.substring(0, url.indexOf("?"));
        let newUrl = first + "?type=${requestScope.type}&brand=" + brand + "&color=${requestScope.color}&numOfProducts=${requestScope.numOfProducts}&page=1&sort=${requestScope.sort}";
        window.location.replace(newUrl);
    }

    function filterColor(color) {
        let url = document.URL;
        let first = url.substring(0, url.indexOf("?"));
        let newUrl = first + "?type=${requestScope.type}&brand=${requestScope.brand}&color=" + color + "&numOfProducts=${requestScope.numOfProducts}&page=1&sort=${requestScope.sort}";
        window.location.replace(newUrl);
    }
</script>
<!-- shop-style content section end -->
</body>
</html>
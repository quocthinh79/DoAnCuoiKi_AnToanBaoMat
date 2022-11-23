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
<!-- single peoduct content section start -->
<jsp:useBean id="product" scope="request" type="Beans.Product"/>
<section class="single-product-area sit">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-6 col-sm-6 none-si-pro">
                        <div class="pro-img-tab-content tab-content">
                            <c:forEach var="i" begin="0" end="${product.getUrlImages().size() - 1}">
                                <c:if var="check" test="${i == 0}"/>
                                <div class="tab-pane ${check?"active":""}" id="image-${i}">
                                    <div class="simpleLens-big-image-container">
                                        <a class="simpleLens-lens-image" data-lightbox="roadtrip"
                                           data-lens-image='<c:url value="${product.getUrlImages().get(i)}"/>'
                                           href='<c:url value="${product.getUrlImages().get(i)}"/>'>
                                            <img src='<c:url value="${product.getUrlImages().get(i)}"/>'
                                                 alt="${product.getUrlImages().get(i)}"
                                                 class="simpleLens-big-image">
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="pro-img-tab-slider indicator-style2">
                            <c:forEach var="i" begin="0" end="${product.getUrlImages().size() - 1}">
                                <div class="item"><a href="#image-${i}" data-toggle="tab"><img
                                        src='<c:url value="${product.getUrlImages().get(i)}"/>'
                                        alt="${product.getUrlImages().get(i)}"/></a></div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <div class="cras">
                            <div class="product-name">
                                <h2>${product.name}</h2>
                            </div>
                            <div class="pro-rating cendo-pro">
                                <span class="star-right">
                                    rate(${product.getNumberOfRate()}):
                                     <c:if var="check" test="${product.getRate() != 0}">
                                         <c:forEach var="i" begin="0" end="${product.getRate()}">
                                             <i class="fa fa-star"></i>
                                         </c:forEach>
                                     </c:if>
                                </span>
                            </div>
                            <p id="my-product-id" class="availability in-stock">
                                Product Code: ${product.id}
                            </p>
                            <p class="availability in-stock2">
                                Availability: ${product.number}
                            </p>
                            <p class="availability in-stock2">
                                Brand: ${product.brand}
                            </p>
                            <div class="pre-box">
                                <span class="special-price">${product.price} VNĐ</span>
                            </div>
                            <form action="/WebBanQuanAo/single-product" method="post">
                                <div class="add-to-box1">
                                    <div class="add-to-box add-to-box2">
                                        <ul>
                                            <li id="my-color" style="white-space: nowrap; display: flex">
                                                <c:forEach var="i" begin="0" end="${product.getColors().size() - 1}">
                                                    <span>${product.getColors().get(i)}</span><strong>:</strong>
                                                    <input type="radio" name="color"
                                                           value="${product.getColors().get(i)}"
                                                        <c:if var="check"
                                                              test="${i == 0}"/> ${check?'checked="true"':""}
                                                    >
                                                </c:forEach>
                                            </li>
                                            <li id="my-size" style="display: flex;">
                                                <c:forEach var="i" begin="0" end="${product.getSizes().size() - 1}">
                                                    <span>${product.getSizes().get(i)}</span><strong>:</strong>
                                                    <input type="radio" name="size" value="${product.getSizes().get(i)}"
                                                        <c:if var="check"
                                                              test="${i == 0}"/> ${check?'checked="true"':""}
                                                    >
                                                </c:forEach>
                                            </li>
                                            <li><span>Tag</span><strong>:</strong>
                                                <c:forEach var="tag" items="${product.getTags()}">
                                                    <a href="#">${tag}</a>
                                                </c:forEach>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="add-to-box1">
                                    <div class="add-to-box add-to-box2">
                                        <div class="add-to-cart">
                                            <div class="input-content">
                                                <label>Quantity:</label>
                                                <div class="quantity">
                                                    <div class="cart-plus-minus">
                                                        <input id="my-quantity" type="text" value="1" name="qtybutton"
                                                               class="cart-plus-minus-box">
                                                    </div>
                                                </div>
                                            </div>
                                            <div  class="product-icon">
                                                <a id="my-add-to-card" href="http://localhost:8080/WebBanQuanAo/login" title="Add to cart">
                                                    <i class="fa fa-shopping-cart"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-xs-12">
                        <div class="text">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active">
                                    <a href="#home" aria-controls="home" role="tab" data-toggle="tab">Mô tả sản phẩm</a>
                                </li>
                                <li role="presentation">
                                    <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Đánh
                                        giá(1)</a>
                                </li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content tab-con2">
                                <div role="tabpanel" class="tab-pane active" id="home">${product.description}
                                </div>
                                <div role="tabpanel" class="tab-pane" id="profile">
                                    <form class="form-horizontal">
                                        <div id="review">
                                            <table class="table table-striped table-bordered">
                                                <tr>
                                                    <td style="width: 50%;">
                                                        <strong>noname</strong>
                                                    </td>
                                                    <td class="text-right">25/01/2016</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <p class="text an-text">Hàng kém chất lượng quá shop ạ.</p>
                                                        <span class="fa fa-stack">
                                                                    <i class="fa fa-star fa-stack-2x"></i>
                                                                    <i class="fa fa-star-o fa-stack-2x"></i>
                                                                </span>
                                                        <span class="fa fa-stack">
                                                                    <i class="fa fa-star fa-stack-2x"></i>
                                                                    <i class="fa fa-star-o fa-stack-2x"></i>
                                                                </span>
                                                        <span class="fa fa-stack">
                                                                    <i class="fa fa-star-o fa-stack-2x"></i>
                                                                </span>
                                                        <span class="fa fa-stack">
                                                                    <i class="fa fa-star-o fa-stack-2x"></i>
                                                                </span>
                                                        <span class="fa fa-stack">
                                                                    <i class="fa fa-star-o fa-stack-2x"></i>
                                                                </span>
                                                    </td>
                                                </tr>
                                            </table>
                                            <div class="text-right"></div>
                                        </div>
                                        <h2 class="write">Viết bình luận</h2>
                                        <div class="form-group required">
                                            <div class="col-sm-12">
                                                <label class="control-label" for="input-name">Tên của bạn</label>
                                                <input id="input-name" class="form-control" type="text" value=""
                                                       name="name">
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <div class="col-sm-12">
                                                <label class="control-label" for="input-review">Đánh giá của bạn</label>
                                                <textarea id="input-review" class="form-control" rows="5"
                                                          name="text"></textarea>
                                                <div class="help-block">
                                                    <span class="text-danger">Ghi chú:</span>
                                                    Có thể có hoặc không!
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <div class="col-sm-12">
                                                <label class="control-label">Đánh giá</label>
                                                Tệ
                                                <input type="radio" value="1" name="rating">
                                                <input type="radio" value="2" name="rating">
                                                <input type="radio" value="3" name="rating">
                                                <input type="radio" value="4" name="rating">
                                                <input type="radio" value="5" name="rating">
                                                Tốt
                                            </div>
                                        </div>
                                        <div class="form-group required">
                                            <div class="col-sm-12">
                                                <label class="control-label" for="input-captcha">Nhập mã hộp phía
                                                    dưới</label>
                                                <input id="input-captcha" class="form-control" type="text" value=""
                                                       name="captcha">
                                            </div>
                                        </div>
                                        <div class="buttons si-button">
                                            <div class="pull-right">
                                                <button id="button-review" class="btn btn-primary"
                                                        data-loading-text="Loading..." type="button">Tiếp tục
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="single-sidebar">
                    <div class="single-sidebar an-shop">
                        <h3 class="wg-title">Siêu giảm giá</h3>
                        <ul>
                            <li class="b-none7">
                                <div class="tb-recent-thumbb">
                                    <a href="#">
                                        <img class="attachment" src='<c:url value="/assets/imgs/products/6.jpg"/>'
                                             alt="">
                                    </a>
                                </div>
                                <div class="tb-recentb7">
                                    <div class="tb-beg">
                                        <a href="#">Áo sơ mi xanh tay dài</a>
                                    </div>
                                    <div class="tb-product-price font-noraure-3">
                                        <span class="amount">250000.0 VNĐ</span>
                                    </div>
                                </div>
                            </li>
                            <li class="b-none7">
                                <div class="tb-recent-thumbb">
                                    <a href="#">
                                        <img class="attachment" src='<c:url value="/assets/imgs/products/10.jpg"/>'
                                             alt="">
                                    </a>
                                </div>
                                <div class="tb-recentb7">
                                    <div class="tb-beg">
                                        <a href="#">Áo kaki nam kiểu dáng tây âu</a>
                                    </div>
                                    <div class="tb-product-price font-noraure-3">
                                        <span class="amount">300000.0 VNĐ</span>
                                    </div>
                                </div>
                            </li>
                            <li class="b-none7">
                                <div class="tb-recent-thumbb">
                                    <a href="#">
                                        <img class="attachment" src='<c:url value="/assets/imgs/products/8.jpg"/>'
                                             alt="">
                                    </a>
                                </div>
                                <div class="tb-recentb7">
                                    <div class="tb-beg">
                                        <a href="#">Áo ghi lê thời thượng</a>
                                    </div>
                                    <div class="tb-product-price font-noraure-3">
                                        <span class="amount">250000.0 VNĐ</span>
                                    </div>
                                </div>
                            </li>
                            <li class="b-none7">
                                <div class="tb-recent-thumbb">
                                    <a href="#">
                                        <img class="attachment" src='<c:url value="/assets/imgs/products/9.jpg"/>'
                                             alt="">
                                    </a>
                                </div>
                                <div class="tb-recentb7">
                                    <div class="tb-beg">
                                        <a href="#">Áo thun sọc</a>
                                    </div>
                                    <div class="tb-product-price font-noraure-3">
                                        <span class="amount">275000.0 VNĐ</span>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="ro-info-box-wrap tpl3 st">
                        <div class="tb-image">
                            <img class="attachment" src='<c:url value="/assets/imgs/products/10.jpg"/>'
                                 alt="">
                        </div>
                        <div class="tb-content">
                            <div class="tb-content-inner an-inner">
                                <h5>THỜI TRANG NAM</h5>
                                <h3>GIẢM GIÁ GIỮA MÙA</h3>
                                <h6>
                                    <a href="#">MUA SẮM NGAY</a>
                                </h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
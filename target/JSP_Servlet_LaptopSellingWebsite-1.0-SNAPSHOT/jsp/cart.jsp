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
<c:url value="" var="root"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Metaverse Laptop</title>
    <link rel="icon" href="${root}image/logo-re.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
          integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/fontawesome.min.css"
          integrity="sha512-Rcr1oG0XvqZI1yv1HIg9LgZVDEhf2AHjv+9AuD1JXWGLzlkoKDVvE925qySLcEywpMAYA/rkg296MkvqBF07Yw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="${root}css/cart.css">
    <link href="${root}css/responsiveIndex.css" rel="stylesheet">
    <link href="${root}fonts/themify-icons/themify-icons.css" rel="stylesheet">
    <link href="${root}fonts/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="${root}fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${root}css/cartResponsive.css">
</head>

<body>
<c:set var = "status" value = "${currentStatus}"/>
<c:if test = "${status == 0}">
    <%--    <div id="return-homepage">--%>
    <%--    </div>--%>
    <script>alert("Vui lòng đăng nhập để tiếp tục")</script>
</c:if>

<div id="cart-main">
    <%@include file="../layout/header.jsp" %>
    <div class="cart" class="bg-gray">
        <div class="container padding padding-top-60">
            <div class="address-menu">
                <div class="sub-container">
                    <div class="home-btn sub-container-content">
                        <a role="button" class="btn-circle white-bg" href="${root}index.jsp">
                            <i class="fas fa-home"></i>
                        </a>
                    </div>
                    <div class="arrow">
                        <i class="fas fa-arrow-right"></i>
                    </div>
                    <div class="current-btn sub-container-content">
                        <span class="info-content black-text rounded-border">Giỏ hàng</span>
                    </div>
                </div>
            </div>
            <div class="cart-info">
                <div class="left-content">
                    <div id="your-cart">
                        <div class="container">
                            <div class="top-content">
                                <div class="top-content-container">
                                    <div class="title">
                                    <span class="content">
                                        <span class="big-text bolder">Giỏ hàng của bạn</span>
                                    </span>
                                    </div>
                                    <div class="btn-clear">
                                        <button class="btn-clear-all" style="background-color: #f8f8fc;color: blue;border: 1px solid #f8f8fc;cursor: pointer;">
                                            <span>Xóa tất cả</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cart-item">
                        <div class="cart-container">
                            <div class="cart-item-info">
                                <div class="container">
                                    <c:forEach items="${listProduct}" var="product">
                                        <div class="item">
                                            <div class="left-cart-item">
                                                <div class="item-thumbnail item-padding">
                                                    <img class="item-logo-thumbnail" src="${product.linkHinh}" ;/>
                                                </div>
                                                <div class="item-info-container item-padding">
                                                    <span class="item-info-name smaller-text">${product.tenLaptop}</span>
                                                    <span class="item-info-sku smaller-text light-gray-text">Mã Laptop: ${product.maLaptop}</span>
                                                </div>
                                            </div>
                                            <div class="right-cart-item">
                                                <div class="btn-add-subtract">
                                                    <button id="btn-subtract-id" class="btn-padding btn-subtract">
                                                        <i class="icon-btn fas fa-chevron-down"></i>
                                                    </button>
                                                    <div id="quantity" class="btn-padding number">${product.soluong}</div>
                                                    <button id="btn-add-id" class="btn-padding btn-add">
                                                        <i class="icon-btn fas fa-chevron-up"></i>
                                                    </button>
                                                </div>
                                                <div class="price">
                                                    <input type="hidden" value="${product.giaban}" class="origin-price">
                                                    <span class="bolder gray-text price-text">${product.giaban * product.soluong}</span>
                                                </div>
                                                <div class="">
                                                    <button style="margin-top:5px; padding:3px; background-color: #e00000;border: 1px solid #e00000; border-radius:7px ;color: #f8f8f8" class="btn-delete-product">Xoá sản phẩm</button>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="right-content">
                    <div class="container">
                        <div class="cash">
                            <div class="container">
                                <div class="cash-title">
                                    <span class="large-text bolder">Thanh toán</span>
                                </div>
                                <!-- <div class="provisional-price" class="padding-10">
                                    <span class="left gray-text">Tạm tính</span>
                                    <span class="right provisional-cash bolder">4300000</span>
                                </div> -->
                                <div class="final-price" class="padding-10 padding-bottom-20">
                                    <span class="left gray-text">Thành tiền</span>
                                    <span id="final-cash" class="right final-cash large-text red-text bolder">${totalCost}</span>
                                </div>
                                <!-- <div class="VAT" class="padding-10">
                                    <span class="gray-text">(Đã bao gồm VAT)</span>
                                </div> -->
                                <div class="buy">
                                    <button class="buy-btn">Mua hàng</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
    <%@include file="../layout/footer.jsp" %>
</div>
<%@include file="../layout/login.jsp" %>
<%@include file="../layout/fogot.jsp" %>
<%@include file="../layout/register.jsp" %>
<!--Back to top-->
<%@include file="../layout/back-to-top.jsp" %>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

    $('.confirm-btn').click(function () {
        let sale = $('.coupon-input');
        let code = document.querySelectorAll('.coupon-code');
        let final = parseInt($('.final-cash').text().match(/\d+/g).join(''));
        let saleByCode = document.querySelectorAll('.sale-with-code');
        console.log(final)
        for (let i = 0; i < code.length; i++) {
            if (sale.val() == (code[i].innerText)) {
                final = final - (final * (saleByCode[i].innerText.match(/\d+/g).join('') / 100));
            }
        }
        $('.final-cash').html(final.toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        }))
    })
    <%--</c:if>--%>

</script>
<script>
    listenCart();


    $('.final-cash').text().match(/\d+/g).join('')

    var price = document.getElementById("final-cash");
    var priceText = $('.final-cash').text();
    price.innerText = (Number(priceText).toLocaleString('it-IT', {
        style: 'currency',
        currency: 'VND'
    }));

    $('.btn-clear').click(function() {
        $.ajax({
            url:'clearAllProduct',
            type:'post',
            success: function(response) {
                console.log(response)
                console.log(typeof response)
                console.log(response == 1)
                var result = response;
                if(result == 1) {
                    $(".cart-item-info").remove()
                    $(".final-cash").html("0")
                    alert("Xoá giỏ hàng thành công")
                }
                if(result == "Cart Empty") {
                    alert("Giỏ hàng rỗng")
                }
            }
        })
    })

    function listenCart() {
        let priceHTML = document.querySelectorAll('.price-text');

        for (let i = 0; i < priceHTML.length; i++) {
            priceHTML[i].innerHTML = parseInt(priceHTML[i].innerHTML.match(/\d+/g).join('')).toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            })
        }

        let total = 0;
        $('.checkAllProduct').click(function (event) {
            total = 0;
            var destination = $(this).closest('.cart-item');
            if (this.checked) {
                // Iterate each checkbox
                $(destination).find("input[name='checkItem']").each(function () {
                    this.checked = true;
                    total += parseInt($(this).closest('.item').find('.price-text').text().match(/\d+/g).join(''));
                    $(destination).find(".total-pay").html(total.toLocaleString('it-IT', {
                        style: 'currency',
                        currency: 'VND'
                    }));
                });
            } else {
                $(destination).find("input[name='checkItem']").each(function () {
                    this.checked = false;
                    total = 0;
                    sum = 0;
                    $(destination).find(".total-pay").html(sum.toLocaleString('it-IT', {
                        style: 'currency',
                        currency: 'VND'
                    }));
                });
            }
            changePrice();
        });

        let sum = 0;
        let elementTotalPrice;
        $('.checkItem').click(function () {
            var destination = $(this).closest('.cart-item');
            elementTotalPrice = $(this).closest('.cart-item').find('.total-pay');
            let checkAll = $(this).closest('.cart-item').find("input[name='checkAllProduct']");
            if (!checkAll.checked)
                sum = 0;
            $(destination).find("input[name='checkItem']").each(function () {
                if (this.checked) {
                    sum += parseInt($(this).closest('.item').find('.price-text').text().match(/\d+/g).join(''));
                }
            });
            $(destination).find(".total-pay").html(sum.toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }));
            if ($(destination).find("input[name='checkItem']").not(':checked').length === 0) {
                checkAll.prop("checked", true);
            } else {
                total = 0;
                // sum = 0;
                checkAll.prop("checked", false);
            }
            changePrice();
        });

        $('.btn-subtract').click(function () {
            var parent = $(this).closest('.right-cart-item');
            var numberProduct = parent.find('.number');
            var valueNumProduct = parseInt(numberProduct.text().match(/\d+/g).join(''));
            let checkAll = $(this).closest('.cart-item').find("input[name='checkAllProduct']");
            var destination = $(this).closest('.cart-item');
            var originPrice = parseInt(parent.find('.origin-price').val().match(/\d+/g).join(''));
            var checkBoxItem = $(this).closest('.item').find("input[name='checkItem']");
            if (valueNumProduct > 1) {
                valueNumProduct--;
                if (checkAll.prop("checked")) {
                    total -= originPrice;
                    $(destination).find(".total-pay").html(total.toLocaleString('it-IT', {
                        style: 'currency',
                        currency: 'VND'
                    }));
                } else if (checkBoxItem.prop("checked")) {
                    sum -= originPrice;
                    $(destination).find(".total-pay").html(sum.toLocaleString('it-IT', {
                        style: 'currency',
                        currency: 'VND'
                    }));
                }
            }

            numberProduct.html(valueNumProduct);
            var price = parent.find('.price-text');
            var priceValue = originPrice * valueNumProduct
            price.html(priceValue.toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }));
            changePrice();

            var leftParent = $(this).closest('.item');
            var idElement = leftParent.find('.item-info-sku')[0].innerText.split(': ')
            var idForSubtract = idElement[1]

            $.ajax({
                url:"subtractQuantity",
                type:"post",
                data:{id: idForSubtract},

                success: function() {

                },
                error: function() {

                },
            })
        });

        $('.btn-add').click(function () {
            var parent = $(this).closest('.right-cart-item');
            var numberProduct = parent.find('.number');

            let valueNumProduct = parseInt(numberProduct.text().match(/\d+/g).join(''));
            let checkAll = $(this).closest('.cart-item').find("input[name='checkAllProduct']");
            var destination = $(this).closest('.cart-item');
            var originPrice = parseInt(parent.find('.origin-price').val().match(/\d+/g).join(''));
            var checkBoxItem = $(this).closest('.item').find("input[name='checkItem']");

            var leftParent = $(this).closest('.item');
            var idElement = leftParent.find('.item-info-sku')[0].innerText.split(': ');
            var idForAdd = idElement[1];
            if (checkAll.prop("checked")) {
                total += originPrice;
                $(destination).find(".total-pay").html(total.toLocaleString('it-IT', {
                    style: 'currency',
                    currency: 'VND'
                }));
            } else if (checkBoxItem.prop("checked")) {
                sum += originPrice;
                $(destination).find(".total-pay").html(sum.toLocaleString('it-IT', {
                    style: 'currency',
                    currency: 'VND'
                }));

            }
            $.ajax({
                url:"addQuantity",
                type:"post",
                data:{id: idForAdd},

                success: function(response) {
                    let isSuccess = response;
                    if (isSuccess == 0) {
                        alert("Không thể thêm sản phẩm do vượt quá số lượng cho phép");

                    } else {
                        valueNumProduct++;
                        numberProduct.html(valueNumProduct);
                        var price = parent.find('.price-text');
                        var priceValue = originPrice * valueNumProduct
                        price.html(priceValue.toLocaleString('it-IT', {
                            style: 'currency',
                            currency: 'VND'
                        }));
                        changePrice();
                    }
                },
                error: function() {

                },
            })

        });

        $('.btn-delete-product').click(function() {
            var leftParent = $(this).closest('.item');

            $(leftParent).remove();

            var idElement = leftParent.find('.item-info-sku')[0].innerText.split(': ');
            var idForDelete = idElement[1];
            console.log(idForDelete)
            $.ajax({
                url:'clearAProduct',
                type:'post',
                data:{idForDelete: idForDelete},
                success: function() {
                    alert("Xoá sản phẩm thành công")
                    changePrice();

                }
            })
        });

        $('.buy').click(function() {
            $.ajax({
                url:'buy',
                type:'post',
                success: function(response) {
                    console.log(response)
                    var result = response;
                    if(result == 1) {
                        $(".cart-item-info").remove()
                        $(".final-cash").html("0")
                        alert("Mua hàng thành công")
                    }  else if(result == 0 ) {
                        alert("Mua hàng thất bại - Giỏ hàng rỗng")
                    }
                }
            })
        })



    }



    function changePrice() {
        let finalCash = 0
        let totalPayPrice = document.querySelectorAll('.price-text');
        let getTotalPay = [];
        //
        // for (let i = 0; i < totalPayPrice.length; i++) {
        //     getTotalPay.push(totalPayPrice[i].innerText)
        // }
        // for (let i = 0; i < getTotalPay.length; i++) {
        //     console.log(getTotalPay[i].split(' VND'))
        //
        // }


        for (let i = 0; i < totalPayPrice.length; i++) {
            if (totalPayPrice[i].innerText != "") {
                finalCash += parseInt(totalPayPrice[i].innerText.match(/\d+/g).join(''));
            }
        }
        $('.final-cash').html(finalCash.toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        }));
    }


</script>

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
<script src="${root}js/cart.js"></script>

</html>
<%--
  Created by IntelliJ IDEA.
  User: Thinh PC
  Date: 12/21/2021
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="root"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Metaverse Laptop</title>
    <link rel="icon" href="${root}image/logo-re.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${root}css/admin.css" class="css">
    <link rel="stylesheet" href="${root}css/loading.css" class="css">
    <link href="fonts/themify-icons/themify-icons.css" rel="stylesheet">
    <link href="fonts/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
          integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
          referrerpolicy="no-referrer" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <c:choose>
        <c:when test="${sessionScope.permission == null}">
            <div style="
    font-size: 5rem;
    align-items: center;
    /* width: 100vw; */
    text-align: center;
    display: flex;
    margin: auto;
    height: 100vh;
">Đường dẫn không tồn tại
            </div>
        </c:when>
        <c:otherwise>
            <h1 class="title"><strong>DASHBOARD</strong></h1>
            <a href="${root}Logout" class="logout">LOGOUT <i class="fas fa-sign-out-alt"></i></a>
            <div class="container">
                <div class="tab">
                    <c:forEach var="x" items="${AllManufacturer}">
                        <button class="tablinks">${x.tenHang}</button>
                    </c:forEach>
                </div>
                <c:forEach var="x" items="${AllManufacturer}">
                    <div id="${x.tenHang}" class="details-laptop">
                        <a role="button" type="button" href="">
                            <div class="logo" style="background-image: url(${root}${x.image})"></div>
                        </a>
                        <div class="details bg-blue">
                            <div class="details-number">${x.nhap}</div>
                            <div class="details-title">Tổng số lượng nhập</div>
                        </div>
                        <div class="details bg-green">
                            <div class="details-number">${x.xuat}</div>
                            <div class="details-title">Tổng số lượng xuất</div>
                        </div>
                        <div class="details bg-red">
                            <div class="details-number">${x.ton}</div>
                            <div class="details-title">Tổng số lượng tồn kho</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="container">
                <div class="tab" style="display: flex; justify-content: space-around;">
                    <a href="#TTLT">
                        <button class="tablinks">Thông tin laptop</button>
                    </a>
                    <a href="#Account">
                        <button class="tablinks">Account</button>
                    </a>
                    <a href="#Permission">
                        <button class="tablinks">Phân quyền</button>
                    </a>
                    <a href="#Kho">
                        <button class="tablinks">Kho</button>
                    </a>
                    <a href="#HSX">
                        <button class="tablinks">Hãng sản xuất</button>
                    </a>
                    <a href="#Cart">
                        <button class="tablinks">Giỏ hàng</button>
                    </a>
                    <a href="#CartDetail">
                        <button class="tablinks">Chi tiết giỏ hàng</button>
                    </a>
                </div>
            </div>
            <div class="container" style="position: relative">
                <div class="table-responsive">
                    <div class="table-wrapper" style="width: auto">
                        <div class="table-title" style="position: absolute;width: 98%;">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h2>Quản lý <b>Thông tin Laptop</b></h2>
                                </div>
                                <div class="col-xs-6">
                                    <a id="addRowTTLT" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i>
                                        <span>Thêm hàng</span></a>
                                </div>
                            </div>
                        </div>
                        <table id="TTLT" class="table table-striped table-hover" style="margin-top: 45px;">
                            <thead>
                            <tr>
                                <th>MÃ LAPTOP</th>
                                <th>TÊN LAPTOP</th>
                                <th>HÃNG</th>
                                <th>GIÁ BÁN</th>
                                <th>SERIES</th>
                                <th>MÀU</th>
                                <th>CPU</th>
                                <th>VGA</th>
                                <th>RAM</th>
                                <th>KÍCH THƯỚC MÀN HÌNH</th>
                                <th>Ổ CỨNG</th>
                                <th>BÀN PHÍM</th>
                                <th>PIN</th>
                                <th>KHỐI LƯỢNG</th>
                                <th>LINK HÌNH 1</th>
                                <th>LINK HÌNH 2</th>
                                <th>LINK HÌNH 3</th>
                                <th>LINK HÌNH 4</th>
                                <th>LINK HÌNH 5</th>
                            </tr>
                            </thead>
                            <tbody id="tbodyTTLT">
                            <c:forEach var="x" items="${allProduct}">
                                <tr>
                                    <td>${x.maLapTop}</td>
                                    <td>${x.tenLaptop}</td>
                                    <td>${x.hangSX}</td>
                                    <td>${x.giaBan}</td>
                                    <td>${x.series}</td>
                                    <td>${x.mau}</td>
                                    <td>${x.cpu}</td>
                                    <td>${x.vga}</td>
                                    <td>${x.ram}</td>
                                    <td>${x.kichThuocManHinh}</td>
                                    <td>${x.oCung}</td>
                                    <td>${x.banPhim}</td>
                                    <td>${x.pin}</td>
                                    <td>${x.khoiLuong}</td>
                                    <td>${x.linkHinh1}</td>
                                    <td>${x.linkHinh2}</td>
                                    <td>${x.linkHinh3}</td>
                                    <td>${x.linkHinh4}</td>
                                    <td>${x.linkHinh5}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalPageTTLT}">
                                    <c:if test="${i == pageTTLT}">
                                        <li style="cursor: pointer" class="page-item active paging"><a
                                                href="Admin?pageTTLT=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i != pageTTLT}">
                                        <li style="cursor: pointer" class="page-item paging"><a
                                                href="Admin?pageTTLT=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container" style="position: relative">
                <div class="table-responsive">
                    <div class="table-wrapper" style="width: auto">
                        <div class="table-title" style="position: absolute;width: 98%;">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h2>Quản lý <b>Tài khoản</b></h2>
                                </div>
                                <div class="col-xs-6">
                                    <a id="addRowAccount" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i>
                                        <span>Thêm hàng</span></a>
                                </div>
                            </div>
                        </div>
                        <table id="Account" class="table table-striped table-hover" style="margin-top: 45px;">
                            <thead>
                            <tr>
                                <th>ID TÀI KHOẢN</th>
                                <th>HỌ TÊN</th>
                                <th>USERNAME</th>
                                <th>PASSWORD</th>
                                <th>ĐỊA CHỈ</th>
                                <th>EMAIL</th>
                            </tr>
                            </thead>
                            <tbody id="tbodyAccount">
                            <c:forEach var="x" items="${allAccount}">
                                <tr>
                                    <td>${x.id}</td>
                                    <td>${x.fullName}</td>
                                    <td>${x.userName}</td>
                                    <td>${x.pass}</td>
                                    <td>${x.address}</td>
                                    <td>${x.email}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalAccount}">
                                    <c:if test="${i == pageAccount}">
                                        <li style="cursor: pointer" class="page-item active paging"><a
                                                href="Admin?pageAccount=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i != pageAccount}">
                                        <li style="cursor: pointer" class="page-item paging"><a
                                                href="Admin?pageAccount=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container" style="position: relative">
                <div class="table-responsive">
                    <div class="table-wrapper" style="width: auto">
                        <div class="table-title" style="position: absolute;width: 98%;">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h2>Quản lý <b>Phân quyền</b></h2>
                                </div>
                                <div class="col-xs-6">
                                    <a id="addRowPermission" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i>
                                        <span>Thêm hàng</span></a>
                                </div>
                            </div>
                        </div>
                        <table id="Permission" class="table table-striped table-hover" style="margin-top: 45px;">
                            <thead>
                            <tr>
                                <th>ID TÀI KHOẢN</th>
                                <th>QUYỀN</th>
                            </tr>
                            </thead>
                            <tbody id="tbodyPermission">
                            <c:forEach var="x" items="${allPermission}">
                                <tr>
                                    <td>${x.id}</td>
                                    <td>${x.quyen}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalPermission}">
                                    <c:if test="${i == pagePermission}">
                                        <li style="cursor: pointer" class="page-item active paging"><a
                                                href="Admin?pagePermission=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i != pagePermission}">
                                        <li style="cursor: pointer" class="page-item paging"><a
                                                href="Admin?pagePermission=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container" style="position: relative">
                <div class="table-responsive">
                    <div class="table-wrapper" style="width: auto">
                        <div class="table-title" style="position: absolute;width: 98%;">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h2>Quản lý <b>Kho hàng</b></h2>
                                </div>
                                <div class="col-xs-6">
                                    <a id="addRowKho" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i>
                                        <span>Thêm hàng</span></a>
                                </div>
                            </div>
                        </div>
                        <table id="Kho" class="table table-striped table-hover" style="margin-top: 45px;">
                            <thead>
                            <tr>
                                <th>MÃ LAPTOP</th>
                                <th>SỐ LƯỢNG NHẬP</th>
                                <th>SỐ LƯỢNG XUẤT</th>
                                <th>SỐ LƯỢNG TỒN KHO</th>
                            </tr>
                            </thead>
                            <tbody id="tbodyKho">
                            <c:forEach var="x" items="${allKho}">
                                <tr>
                                    <td>${x.maLaptop}</td>
                                    <td>${x.slNhap}</td>
                                    <td>${x.slXuat}</td>
                                    <td>${x.slTon}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalPageKho}">
                                    <c:if test="${i == pageKho}">
                                        <li style="cursor: pointer" class="page-item active paging"><a
                                                href="Admin?pageKho=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i != pageKho}">
                                        <li style="cursor: pointer" class="page-item paging"><a
                                                href="Admin?pageKho=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container" style="position: relative">
                <div class="table-responsive">
                    <div class="table-wrapper" style="width: auto">
                        <div class="table-title" style="position: absolute;width: 98%;">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h2>Quản lý <b>Hãng sản xuất</b></h2>
                                </div>
                                <div class="col-xs-6">
                                    <a id="addRowHSX" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i>
                                        <span>Thêm hàng</span></a>
                                </div>
                            </div>
                        </div>
                        <table id="HSX" class="table table-striped table-hover" style="margin-top: 45px;">
                            <thead>
                            <tr>
                                <th>TÊN HÃNG</th>
                                <th>QUỐC GIA</th>
                                <th>POSTER</th>
                                <th>LOGO VUÔNG</th>
                                <th>LOGO NGANG</th>
                                <th>SLOGAN</th>
                            </tr>
                            </thead>
                            <tbody id="tbodyHSX">
                            <c:forEach var="x" items="${allProductHSX}">
                                <tr>
                                    <td>${x.tenHang}</td>
                                    <td>${x.quocGia}</td>
                                    <td>${x.poster}</td>
                                    <td>${x.logoVuong}</td>
                                    <td>${x.logoNgang}</td>
                                    <td>${x.slogan}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalPageHSX}">
                                    <c:if test="${i == pageHSX}">
                                        <li style="cursor: pointer" class="page-item active paging"><a
                                                href="Admin?pageHSX=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i != pageHSX}">
                                        <li style="cursor: pointer" class="page-item paging"><a
                                                href="Admin?pageHSX=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container" style="position: relative">
                <div class="table-responsive">
                    <div class="table-wrapper" style="width: auto">
                        <div class="table-title" style="position: absolute;width: 98%;">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h2>Quản lý <b>Giỏ hàng</b></h2>
                                </div>
                                <div class="col-xs-6">
                                    <a id="addRowCart" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i>
                                        <span>Thêm hàng</span></a>
                                </div>
                            </div>
                        </div>
                        <table id="Cart" class="table table-striped table-hover" style="margin-top: 45px;">
                            <thead>
                            <tr>
                                <th>MÃ GIỎ HÀNG</th>
                                <th>MÃ KHÁCH HÀNG</th>
                                <th>NGÀY XUẤT GIỎ HÀNG</th>
                                <th>TRỊ GIÁ</th>
                            </tr>
                            </thead>
                            <tbody id="tbodyCart">
                            <c:forEach var="x" items="${allCart}">
                                <tr>
                                    <td>${x.maGioHang}</td>
                                    <td>${x.makh}</td>
                                    <td>${x.ngayXuatGioHang}</td>
                                    <td>${x.triGia}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalCart}">
                                    <c:if test="${i == pageCart}">
                                        <li style="cursor: pointer" class="page-item active paging"><a
                                                href="Admin?pageCart=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i != pageCart}">
                                        <li style="cursor: pointer" class="page-item paging"><a
                                                href="Admin?pageCart=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container" style="position: relative">
                <div class="table-responsive">
                    <div class="table-wrapper" style="width: auto">
                        <div class="table-title" style="position: absolute;width: 98%;">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h2>Quản lý <b>Chi tiết giỏ hàng</b></h2>
                                </div>
                                <div class="col-xs-6">
                                    <a id="addRowCartDetail" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i>
                                        <span>Thêm hàng</span></a>
                                </div>
                            </div>
                        </div>
                        <table id="CartDetail" class="table table-striped table-hover" style="margin-top: 45px;">
                            <thead>
                            <tr>
                                <th>MÃ GIỎ HÀNG</th>
                                <th>MÃ LAPTOP</th>
                                <th>SỐ LƯỢNG LAPTOP</th>
                            </tr>
                            </thead>
                            <tbody id="tbodyCartDetail">
                            <c:forEach var="x" items="${allCartDetail}">
                                <tr>
                                    <td>${x.maGioHang}</td>
                                    <td>${x.maLaptop}</td>
                                    <td>${x.soLuong}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalCartDetail}">
                                    <c:if test="${i == pageCartDetail}">
                                        <li style="cursor: pointer" class="page-item active paging"><a
                                                href="Admin?pageCartDetail=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i != pageCartDetail}">
                                        <li style="cursor: pointer" class="page-item paging"><a
                                                href="Admin?pageCartDetail=${i}" style="cursor: pointer" value="${i}"
                                                class="page-link">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div id="thongbao" style="z-index: 1000">
    <div class="register js-register-container">
        <form class="register-form">
            <div class="input-register">
                Bạn chắc chứ?
            </div>
            <div class="register-div">
                <button type="button" id="yes" class="btn-register">Có</button>
                <button type="button" id="no" class="btn-register">Không</button>
            </div>
        </form>
    </div>
</div>
<div id="thongbaoIdEmpty" style="z-index: 1000">
    <div class="register js-register-container">
        <form class="register-form">
            <div class="input-register">
                Cột đầu tiên không thể thiếu dữ liệu!
            </div>
            <div class="register-div">
                <button type="button" id="ok" class="btn-register">OK</button>
            </div>
        </form>
    </div>
</div>
<div id="thongbaoError" style="z-index: 1000;">
    <div class="register js-register-container">
        <form class="register-form">
            <div id="message" class="input-register">
                Lỗi khi thực hiện
            </div>
            <div class="register-div">
                <button type="button" id="okError" class="btn-register">OK</button>
            </div>
        </form>
    </div>
</div>
<!--Back to top-->
<%@include file="../layout/back-to-top.jsp" %>
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
    fetch('${pageContext.request.contextPath}/Admin').then(res => document.getElementById('loading').remove());
</script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha384-vk5WoKIaW/vJyUAd9n/wmopsmNhiy+L2Z+SBxGYnUkunIxVxAv/UtMOhba/xskxh"
        crossorigin="anonymous"></script>
<script src="${root}js/bstable.js"></script>
<script>
    var example3 = new BSTable("TTLT", {
        $addButton: $('#addRowTTLT'),
        advanced: {
            columnLabel: ''
        }
    });
    example3.init();
    var example4 = new BSTable("HSX", {
        $addButton: $('#addRowHSX'),
        advanced: {
            columnLabel: ''
        }
    });
    example4.init();
    var example5 = new BSTable("Kho", {
        $addButton: $('#addRowKho'),
        advanced: {
            columnLabel: ''
        }
    });
    example5.init();
    var example6 = new BSTable("Account", {
        $addButton: $('#addRowAccount'),
        advanced: {
            columnLabel: ''
        }
    });
    example6.init();
    var example7 = new BSTable("Permission", {
        $addButton: $('#addRowPermission'),
        advanced: {
            columnLabel: ''
        }
    });
    example7.init();
    var example8 = new BSTable("Cart", {
        $addButton: $('#addRowCart'),
        advanced: {
            columnLabel: ''
        }
    });
    example8.init();
    var example9 = new BSTable("CartDetail", {
        $addButton: $('#addRowCartDetail'),
        advanced: {
            columnLabel: ''
        }
    });
    example9.init();
</script>
<script>
    function handleSubmit(id) {
        let element = document.getElementById($(id).parent().attr('id'));
        console.log(element)
        $.ajax({
            url: 'UploadImage',
            type: 'POST',
            data: new FormData(element),
            processData: false,
            contentType: false,
            success: function (response) {
                $(id).parent().parent().html('<input disabled class="form-control input-sm"  data-original-value="' + response + '" value="' + response + '">')
            },
            error: function (xhr) {
            }
        });
    }
</script>
<script src="${root}js/BackToTop.js"></script>
<script src="${root}js/tabs.js"></script>
<!--<script src="${root}js/tabs-table.js"></script>-->
</html>

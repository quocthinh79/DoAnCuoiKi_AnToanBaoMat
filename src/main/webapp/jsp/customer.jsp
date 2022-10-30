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
    <link rel="stylesheet" href="${root}css/cart.css">
    <link href="fonts/themify-icons/themify-icons.css" rel="stylesheet">
    <link href="fonts/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
          integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
          referrerpolicy="no-referrer" rel="stylesheet"/>
</head>
<body>
<%@include file="../layout/header.jsp"%>
<div id="main" style="margin-top: 88px;">
    <div class="container" style="position: relative">
        <div class="table-responsive">
            <div class="table-wrapper" style="width: auto">
                <div class="table-title" style="position: absolute;width: 98%;">
                    <div class="row">
                        <div class="col-xs-6">
                            <h2>Quản lý <b>Tài khoản</b></h2>
                        </div>
                        <div class="col-xs-6">
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
    fetch('${pageContext.request.contextPath}/Customer').then(res => document.getElementById('loading').remove());
</script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha384-vk5WoKIaW/vJyUAd9n/wmopsmNhiy+L2Z+SBxGYnUkunIxVxAv/UtMOhba/xskxh"
        crossorigin="anonymous"></script>
<script src="${root}js/bstableCustomer.js"></script>
<script>
    var example3 = new BSTable("Account", {
        advanced: {
            columnLabel: ''
        }
    });
    example3.init();
</script>
<script src="${root}js/BackToTop.js"></script>
<script src="${root}js/tabs.js"></script>
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
<script src="${root}js/CartAtHover.js"></script>
<!--<script src="${root}js/tabs-table.js"></script>-->
</html>

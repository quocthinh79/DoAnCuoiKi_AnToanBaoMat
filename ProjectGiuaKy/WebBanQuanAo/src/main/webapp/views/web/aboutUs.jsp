<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>${requestScope.pageName}</title>
</head>
<body>
<%@include file="/components/location.jsp" %>

<section class="main_shop_area">
    <div class="breadcrumbs">
        <div class="container">
            <div class="row">
                <div class="col-md-12 client-say">
                    <div class="about-sec-head">
                        <h2>
                            Đánh giá người dùng
                            <strong>Về chúng tôi</strong>
                        </h2>
                    </div>
                    <div class="what-client-say">
                        <div class="single-item-testi">
                            <div class="client-image">
                                <img alt="" src='<c:url value="/assets/imgs/about/testi1.jpg"/>'>
                            </div>
                            <div class="client-text">
                                <p>Shop hỗ trợ nhiệt tình, sản phẩm chất lượng như giới thiệu, tôi rất hài lòng khi mua
                                    sắm ở đây…</p>
                                <h2>ROSE</h2>
                                <p class="client-info">-- Khách hàng tiềm năng --</p>
                            </div>
                        </div>
                        <div class="single-item-testi">
                            <div class="client-image">
                                <img alt="" src='<c:url value="/assets/imgs/about/testi2.jpg"/>'>
                            </div>
                            <div class="client-text">
                                <p>Hàng chất lượng, thời gian giao hàng nhanh, tôi cực kỳ hài lòng và sẽ ủng hộ shop
                                    tiếp…</p>
                                <h2>TERESA</h2>
                                <p class="client-info">-- Khách hàng tiềm năng --</p>
                            </div>
                        </div>
                        <div class="single-item-testi">
                            <div class="client-image">
                                <img alt="" src='<c:url value="/assets/imgs/about/testi3.jpg"/>'>
                            </div>
                            <div class="client-text">
                                <p>Trên cả tuyệt vời, mỗi lần mua sắm ở đây mình đều rất ưng ý, mong shop nhanh ra các
                                    sản phẩm mới nữa…</p>
                                <h2>MONICA</h2>
                                <p class="client-info">-- Khách hàng tiềm năng --</p>
                            </div>
                        </div>
                        <div class="single-item-testi">
                            <div class="client-image">
                                <img alt="" src='<c:url value="/assets/imgs/about/testi4.jpg"/>'>
                            </div>
                            <div class="client-text">
                                <p>Sản phẩm chất lượng, giao diện trang web của shop rất đẹp, tôi khá thích phong cách
                                    này của các bạn…</p>
                                <h2>KATRINA</h2>
                                <p class="client-info">-- Khách hàng tiềm năng --</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row creative-member-area">
                <div class="about-sec-head">
                    <h2 class="creative-member">
                        Các thành viên
                        <strong>nhóm chúng tôi</strong>
                    </h2>
                    <p>Các kỹ năng đã được học giúp ích chúng tôi rất nhiều trong dự án này.</p>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="single-creative-member res2">
                        <div class="member-image">
                            <img alt="" src='<c:url value="/assets/imgs/about/member1.jpg"/>'>
                            <div class="member-title">
                                <h2>Nguyễn Công Phúc</h2>
                                <h3>Sinh viên</h3>
                            </div>
                        </div>
                        <div class="member-info">
                            <p>Lập trình là tiềm năng cần được khai phá</p>
                            <div class="member-social">
                                <a class="m-facebook" href="#">
                                    <i class="fa fa-facebook"></i>
                                </a>
                                <a class="m-twitter" href="#">
                                    <i class="fa fa-twitter"></i>
                                </a>
                                <a class="m-g-plus" href="#">
                                    <i class="fa fa-google-plus"></i>
                                </a>
                                <a class="m-linkedin" href="#">
                                    <i class="fa fa-linkedin"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="single-creative-member res2 res-mem">
                        <div class="member-image">
                            <img alt="" src='<c:url value="/assets/imgs/about/member2.jpg"/>'>
                            <div class="member-title">
                                <h2>Lương Hữu Luân</h2>
                                <h3>Sinh viên</h3>
                            </div>
                        </div>
                        <div class="member-info">
                            <p>Thích tìm tòi sự đổi mới công nghệ</p>
                            <div class="member-social">
                                <a class="m-facebook" href="#">
                                    <i class="fa fa-facebook"></i>
                                </a>
                                <a class="m-twitter" href="#">
                                    <i class="fa fa-twitter"></i>
                                </a>
                                <a class="m-g-plus" href="#">
                                    <i class="fa fa-google-plus"></i>
                                </a>
                                <a class="m-linkedin" href="#">
                                    <i class="fa fa-linkedin"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="single-creative-member res-mem2">
                        <div class="member-image">
                            <img alt="" src='<c:url value="/assets/imgs/about/member3.jpg"/>'>
                            <div class="member-title">
                                <h2>Nguyễn Dũy Long</h2>
                                <h3>Sinh viên</h3>
                            </div>
                        </div>
                        <div class="member-info">
                            <p>Đam mê công nghệ thông tin</p>
                            <div class="member-social">
                                <a class="m-facebook" href="#">
                                    <i class="fa fa-facebook"></i>
                                </a>
                                <a class="m-twitter" href="#">
                                    <i class="fa fa-twitter"></i>
                                </a>
                                <a class="m-g-plus" href="#">
                                    <i class="fa fa-google-plus"></i>
                                </a>
                                <a class="m-linkedin" href="#">
                                    <i class="fa fa-linkedin"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- about content section end -->
</body>
</html>
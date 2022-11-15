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
<!-- about content section start -->
<section class="main_shop_area">
    <div class="breadcrumbs">
        <div class="container">
            <div class="row">
                <div class="col-md-12 client-say">
                    <div class="about-sec-head">
                        <h2>
                            ${message}
                            <strong><a href="${href}">${hrefName}</a></strong>
                        </h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- about content section end -->
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/components/taglib.jsp" %>
<% String error = (String) request.getAttribute("error");%>
<!DOCTYPE html>
<html class="no-js" lang="vi">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><dec:title default="Trang chủ"/></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon -->
    <link rel="shortcut icon" type="image/x-icon" href='<c:url value= "/assets/imgs/favicon.ico" />'/>
    <!-- include css -->
    <jsp:include page="/components/css.jsp"/>
    <style>
        .testimonials {
            background-image: url('<c:url value="/assets/imgs/testimonial/1.jpg"/>');
        }
        .contact-img-area {
            background-image: url('<c:url value="/assets/imgs/bg-banner/bg-pagetitle.jpg"/>');
        }
    </style>
</head>
<body>
<!-- header section start -->
<jsp:include page="/components/header.jsp"/>
<!-- header section end -->
<dec:body/>
<!-- content end end -->
<!-- service section start -->
<%@include file="/components/customerCare.jsp" %>
<!-- service section end -->
<!-- footer section start -->
<jsp:include page="/components/footer.jsp"/>
<!-- footer section end -->
<!-- include js -->
<jsp:include page="/components/js.jsp"/>
</body>
</html>

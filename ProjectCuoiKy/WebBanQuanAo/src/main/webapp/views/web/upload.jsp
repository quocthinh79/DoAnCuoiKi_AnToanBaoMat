<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>${requestScope.pageName}</title>
</head>
<body>
<section class="main_shop_area">
    <div class="breadcrumbs">
        <div class="container">
            <div class="row">
                <div class="col-md-12 client-say">
                    <div class="about-sec-head">
                        <form action="<c:url value='/upload'/>" method="post" enctype="multipart/form-data">
                            <input type="text" name="name" placeholder="name">
                            <input type="file" name="img" placeholder="choose file">
                            <input type="submit" value="submit">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
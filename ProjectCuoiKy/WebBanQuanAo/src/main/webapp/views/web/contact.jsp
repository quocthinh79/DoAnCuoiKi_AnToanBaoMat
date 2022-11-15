<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- pages-title-start -->
<!-- pages-title-end -->
<!-- contact content section start -->
<html>
<head>
    <title>${requestScope.pageName}</title>
</head>
<body>
<%@include file="/components/location.jsp" %>
<section class="top-map-area">
    <div class="container">
        <%--        <div class="row">--%>
        <%--            <div class="col-md-12">--%>
        <%--                <div class="map-area">--%>
        <%--                    <div class="contact-map">--%>
        <%--                        <div id="hastech"></div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="page-title">
                    <h2>Liên hệ với chúng tôi</h2>
                    <h3>Địa chỉ</h3>
                </div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <strong>http://xxxxxxxxxxxxxxxxx.com/</strong>
                                <br>
                                <address> Address: só 100, đường 100, Thủ Đức</address>
                            </div>
                            <div class="col-sm-3">
                                <strong>Điện thoại</strong>
                                <br>
                                (+84) 184 4234 2134
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h3>Form liên hệ</h3>
                    </div>
                    <form class="cendo" action="/WebBanQuanAo/contact" method="post">
                        <div class="form-group required">
                            <label class="col-md-2 control-label">Họ tên</label>
                            <div class="col-md-10">
                                <input class="form-control" type="text" value="" name="name">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-md-2 control-label">Email</label>
                            <div class="col-md-10">
                                <input class="form-control" type="text" value="" name="email">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-md-2 control-label">Vấn đề của bạn</label>
                            <div class="col-md-10">
                                <textarea class="form-control" rows="10" name="message"></textarea>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="buttons">
                                <div class="pull-right">
                                    <input class="btn btn-primary" type="submit" value="Gửi">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- contact content section end -->
<%--<script src="http://maps.googleapis.com/maps/api/js"></script>--%>
<%--<script>--%>
<%--    var myCenter=new google.maps.LatLng(23.763523, 90.431098);--%>
<%--    function initialize()--%>
<%--    {--%>
<%--        var mapProp = {--%>
<%--            center:myCenter,--%>
<%--            scrollwheel: false,--%>
<%--            zoom:17,--%>
<%--            mapTypeId:google.maps.MapTypeId.ROADMAP--%>
<%--        };--%>
<%--        var map=new google.maps.Map(document.getElementById("hastech"),mapProp);--%>
<%--        var marker=new google.maps.Marker({--%>
<%--            position:myCenter,--%>
<%--            animation:google.maps.Animation.BOUNCE,--%>
<%--            icon:'img/map-marker.png',--%>
<%--            map: map,--%>
<%--        });--%>

<%--        marker.setMap(map);--%>
<%--    }--%>
<%--    google.maps.event.addDomListener(window, 'load', initialize);--%>
<%--</script>--%>
<!-- service section end -->
</body>
</html>
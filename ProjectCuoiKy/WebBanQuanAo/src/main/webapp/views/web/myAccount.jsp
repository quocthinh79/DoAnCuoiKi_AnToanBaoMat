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
<!-- my account content section start -->
<section class="collapse_area coll2">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <div class="check">
                    <h2>Tài khoản </h2>
                </div>
                <div class="faq-accordion">
                    <div class="panel-group pas7" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingOne">
                                <h4 class="panel-title">
                                    <a class="collapsed method" role="button" data-toggle="collapse"
                                       data-parent="#accordion" href="#collapseOne" aria-expanded="false"
                                       aria-controls="collapseOne">Thay đổi thông tin tài khoản <i
                                            class="fa fa-caret-down"></i></a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                                 aria-labelledby="headingOne" aria-expanded="false">
                                <div class="row">
                                    <%--start account information--%>
                                    <c:if var="MTAccount" test="${sessionScope.account != null}"/>
                                    <div class="easy2">
                                        <h2>Thông tin tài khoản</h2>
                                        <form class="form-horizontal" action="#">
                                            <fieldset>
                                                <legend>Thông tin cá nhân của bạn</legend>
                                                <div class="form-group required">
                                                    <label class="col-sm-2 control-label">Họ </label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control" type="text" placeholder="Họ"
                                                               value="${MTAccount?account.getFirstName():""}">
                                                    </div>
                                                </div>
                                                <div class="form-group required">
                                                    <label class="col-sm-2 control-label">Tên</label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control" type="text" placeholder="Tên"
                                                               value="${MTAccount?account.getLastName():""}">
                                                    </div>
                                                </div>
                                                <div class="form-group required">
                                                    <label class="col-sm-2 control-label">E-Mail</label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control" type="email" placeholder="E-Mail"
                                                               value="${MTAccount?account.getEmail():""}">
                                                    </div>
                                                </div>
                                                <div class="form-group required">
                                                    <label class="col-sm-2 control-label">SĐT</label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control" type="tel" placeholder="SĐT"
                                                               value="${MTAccount?account.getPhoneNumber():""}">
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <div class="buttons clearfix">
                                                <div class="pull-left">
                                                    <a class="btn btn-default ce5" href="#">Trở lại</a>
                                                </div>
                                                <div class="pull-right">
                                                    <input class="btn btn-primary ce5" type="submit" value="Tiếp tục">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <%--                                    end account infomation --%>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingTwo">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                       href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Đổi mật
                                        khẩu <i class="fa fa-caret-down"></i></a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
                                 aria-labelledby="headingTwo" aria-expanded="false" style="height: 0px;">
                                <div class="row">
                                    <div class="easy2">
                                        <h2>Đổi mật khẩu</h2>
                                        <form class="form-horizontal" action="#">
                                            <fieldset>
                                                <legend>Mật khẩu của bạn</legend>
                                                <div class="form-group required">
                                                    <label class="col-sm-2 control-label">Mật khẩu</label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control" type="password"
                                                               placeholder="Mật khẩu">
                                                    </div>
                                                </div>
                                                <div class="form-group required">
                                                    <label class="col-sm-2 control-label">Xác nhận mật khẩu</label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control" type="password"
                                                               placeholder="Xác nhận mật khẩu">
                                                    </div>
                                                </div>
                                                <div class="form-group required">
                                                    <label class="col-sm-2 control-label">Mật khẩu mới</label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control" type="password"
                                                               placeholder="Mật khẩu mới">
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <div class="buttons clearfix">
                                                <div class="pull-left">
                                                    <a class="btn btn-default ce5" href="#">Trở lại</a>
                                                </div>
                                                <div class="pull-right">
                                                    <input class="btn btn-primary ce5" type="submit" value="Tiếp tục">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingThree">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                       href="#collapseThree" aria-expanded="false" aria-controls="collapseTwo">Sửa đổi
                                        danh sách địa chỉ <i class="fa fa-caret-down"></i></a>
                                </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                                 aria-labelledby="headingThree" aria-expanded="false" style="height: 0px;">
                                <div class="easy2">
                                    <h2>Danh sách địa chỉ</h2>
                                    <table class="table table-bordered table-hover">
                                        <tr>
                                            <td class="text-left">
                                                Phường Linh Trung,
                                                <br>
                                                Thủ Đức,
                                                <br>
                                                Thành phố HCM.
                                            </td>
                                            <td class="text-right">
                                                <a class="btn btn-info g6" href="#">Sửa</a>
                                                <a class="btn btn-danger g6" href="#">Xóa</a>
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="buttons clearfix">
                                        <div class="pull-left">
                                            <a class="btn btn-default ce5" href="#">Trở lại</a>
                                        </div>
                                        <div class="pull-right">
                                            <input class="btn btn-primary ce5" type="submit" value="Tiếp tục">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingFour">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                       href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                        Tạo key mới
                                        <i class="fa fa-caret-down"></i></a>
                                </h4>
                            </div>
                            <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                                 aria-labelledby="headingFour" aria-expanded="false" style="height: 0px;">
                                <div class="easy2">
                                    <form class="form-horizontal" action="#">
                                        <fieldset>
                                            <legend>Mật khẩu của bạn</legend>
                                            <div class="form-group required">
                                                <label class="col-sm-2 control-label">Mật khẩu</label>
                                                <div class="col-sm-10">
                                                    <input class="form-control" type="password"
                                                           placeholder="Mật khẩu">
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="buttons clearfix">
                                            <div class="pull-right">
                                                <input class="btn btn-primary ce5" type="submit" value="Tạo key">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- my account content section end -->
</body>
</html>
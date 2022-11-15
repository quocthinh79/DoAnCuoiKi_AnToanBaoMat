<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách tài khoản</title>
</head>
<body>
<div class="main-content">
    <div style="height: 0px; display: flex; justify-content: flex-end;">
        <div class="message_box" style="position: fixed; z-index: 9999; padding: 20px;">
            <div class="alert alert-success" id="message_box" style="width: 25vw;">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <strong id="msg_box">${requestScope.message}</strong>

            </div>
        </div>
    </div>
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="nav-search" id="nav-search">
                <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input"
                                           id="nav-search-input" autocomplete="off"/>
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                </form>
            </div><!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div class="page-header clearfix">
                <h1 class="pull-left">
                    Tài khoản
                </h1>
                <button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#addUser">
                    Thêm tài khoản
                </button>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">

                    <table id="table-user"></table>

                    <script type="text/javascript">
                        var $path_base = "${requestScope.base}";//in Ace demo this will be used for editurl parameter
                    </script>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<%--popup đăng ký user--%>
<div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="addUserLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thêm tài khoản</h5>
                <button style="margin-top: -25px !important;" type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="form-add-user" method="post" action="admin-user-manage">
                    <div class="form-group">
                        <label for="last_name">Họ</label>
                        <input type="text" class="form-control" name="last-name" id="last_name"
                               placeholder="Nhập họ của bạn">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="first_name">Tên</label>
                        <input type="text" class="form-control" name="first-name" id="first_name"
                               placeholder="Nhập tên của bạn">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" class="form-control" name="phone" id="phone"
                               placeholder="Nhập số điện thoại">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="email">Địa chỉ email</label>
                        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp"
                               placeholder="Nhập email">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="username">Tên tài khoản</label>
                        <input type="text" class="form-control" name="username" id="username"
                               placeholder="Nhập tên tài khoản">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="Nhập mật khẩu">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>
                    <div class="form-group">
                        <label for="repeat_password">Nhập lại mật khẩu</label>
                        <input type="password" class="form-control" name="password-repeat" id="repeat_password"
                               placeholder="Nhập lại mật khẩu">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <input type="hidden" name="action" value="delete">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="submit" id="submit-form" form="form-add-user" class="btn btn-primary">Thêm</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="confirm-delete-account" tabindex="-1" role="dialog" aria-labelledby="confirm-delete-account"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Bạn chắc chắn muốn xóa?</h5>
                <button type="button" style="margin-top: -25px !important;"  class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Chọn Ok để xóa tài khoản
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-primary" onclick="deleteAccount()">OK</button>
            </div>
        </div>
    </div>
</div>

<%--popup chỉnh sửa user--%>
<div class="modal fade" id="editUser" tabindex="-1" role="dialog" aria-labelledby="editUserLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="title-model">Chỉnh sửa tài khoản</h5>
                <button style="margin-top: -25px !important;" type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="form-edit-user" method="post" action="admin-user-manage">
                    <input type="hidden" id="idAccount" name="idAccount">
                    <div class="form-group">
                        <label for="last_name_edit">Họ</label>
                        <input type="text" class="form-control" name="last-name" id="last_name_edit"
                               placeholder="Nhập họ của bạn">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="first_name_edit">Tên</label>
                        <input type="text" class="form-control" name="first-name" id="first_name_edit"
                               placeholder="Nhập tên của bạn">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="phone_edit">Số điện thoại</label>
                        <input type="text" class="form-control" name="phone" id="phone_edit"
                               placeholder="Nhập số điện thoại">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="address_edit">Địa chỉ</label>
                        <input type="text" class="form-control" name="address" id="address_edit"
                               placeholder="Nhập địa chỉ">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="email_edit">Địa chỉ email</label>
                        <input type="email" class="form-control" name="email" id="email_edit" aria-describedby="emailHelp"
                               placeholder="Nhập email">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="username_edit">Tên tài khoản</label>
                        <input type="text" disabled class="form-control" name="username" id="username_edit" value=""
                               placeholder="Nhập tên tài khoản">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group" id="role_edit_selected">
                        <label for="role_edit">Vai trò</label>
                        <select id="role_edit" name="role_edit" class="form-select" aria-label="Default select example">
                            <option value="VT1">Admin</option>
                            <option value="VT2">Người dùng</option>
                        </select>
                    </div>

                    <input type="hidden" name="action" value="update">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="submit" id="update-account" form="form-edit-user" class="btn btn-primary">Cập nhật</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
<script src='<c:url value="/admin/js/myAdmin/user-manage-script.js"/>'></script>
<script>
    $(document).ready(function () {
        let message = "${requestScope.message}";
        if (message === "") {
            $("#message_box").hide();
        } else {
            $("#message_box").fadeTo(2000, 500).slideUp(500, function () {
                $("#message_box").slideUp(500);
            });
        }
    })
</script>
<script src='<c:url value="/assets/js/validation.js"/>'></script>
</body>
</html>

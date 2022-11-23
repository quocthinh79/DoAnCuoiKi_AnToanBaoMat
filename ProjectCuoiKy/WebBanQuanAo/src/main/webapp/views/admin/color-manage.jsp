<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách màu sắc</title>
</head>
<body>
<div class="main-content">
    <div style="height: 0; display: flex; justify-content: flex-end;">
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
            </div>
        </div>

        <div class="page-content">
            <div class="page-header clearfix">
                <h1 class="pull-left">
                    Màu sắc
                </h1>
                <button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#addColor">
                    Thêm màu sắc
                </button>
            </div>

            <div class="row">
                <div class="col-xs-12">

                    <table id="table-color"></table>

                    <script type="text/javascript">
                        var $path_base = "${requestScope.base}";//in Ace demo this will be used for editurl parameter
                    </script>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="addColor" tabindex="-1" role="dialog" aria-labelledby="addColor"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm màu sắc</h5>
                <button style="margin-top: -25px !important;" type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="form-add-color" method="post" action="admin-color-manage">
                    <input type="hidden" name="action" value="add">
                    <div class="form-group">
                        <label for="id-color">Mã màu</label>
                        <input type="text" class="form-control" name="id-color" id="id-color"
                               placeholder="Nhập mã màu">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div class="form-group">
                        <label for="color-name">Tên màu</label>
                        <input type="text" class="form-control" name="color-name" id="color-name"
                               placeholder="Nhập tên màu">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="submit" id="submit-form" form="form-add-color" class="btn btn-primary">Thêm</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="confirm-delete-color" tabindex="-1" role="dialog" aria-labelledby="confirm-delete-color"
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
                Chỉ xóa được màu khi không còn sản phẩm có màu này, vui lòng xóa sản phẩm trước khi xóa màu
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-primary" onclick="deleteColor()">OK</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editColor" tabindex="-1" role="dialog" aria-labelledby="editColorLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="title-model">Chỉnh sửa màu sắc</h5>
                <button style="margin-top: -25px !important;" type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="form-edit-color" method="post" action="admin-color-manage">
                    <input type="hidden" id="idColor" name="id-color">
                    <input type="hidden" name="action" value="edit">
                    <div class="form-group">
                        <label for="color-name">Tên màu</label>
                        <input type="text" class="form-control" name="color-name" id="colorName"
                               placeholder="Nhập tên màu">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="submit" id="update-account" form="form-edit-color" class="btn btn-primary">Cập nhật</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
<script src='<c:url value="/admin/js/myAdmin/color-manage-script.js"/>'></script>
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

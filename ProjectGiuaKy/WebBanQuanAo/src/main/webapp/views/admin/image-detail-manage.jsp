<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết hình ảnh sản phẩm</title>
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
                    Chi tiết hình ảnh của sản phẩm
                </h1>
                <button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#addImage">
                    Thêm hình ảnh
                </button>
            </div>

            <div class="row">
                <div class="col-xs-12">

                    <table id="table-image"></table>

                    <script type="text/javascript">
                        var $path_base = "${requestScope.base}";//in Ace demo this will be used for editurl parameter
                    </script>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="addImage" tabindex="-1" role="dialog" aria-labelledby="addImage"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm hình ảnh chi tiết cho sản phẩm</h5>
                <button style="margin-top: -25px !important;" type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="form-add-image" method="post" action="admin-image-manage" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="add">
                    <div class="form-group">
                        <label for="id">Mã sản phẩm</label>
                        <input type="text" class="form-control" name="id" id="id"
                               placeholder="Nhập mã sản phẩm">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>

                    <div>
                        <label for="color-product-add">Chọn màu</label>
                        <select
                                class="form-control chosen-select"
                                id="color-product-add" name="color-product"
                        >
                            <option value=""></option>
                            <c:forEach var="i" begin="0" end="${requestScope.infoForm.getColorList().size()-1}">
                                <option value="${requestScope.infoForm.getColorList().get(i).getIdColor()}">${requestScope.infoForm.getColorList().get(i).getNameColor()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group" style="margin-top: 4px">
                        <label for="images">Chọn hình ảnh, có thể chọn nhiều</label>
                        <input type="file" multiple class="form-control" name="images" id="images"
                               placeholder="Chọn một hoặc nhiều ảnh">
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="submit" id="submit-form" form="form-add-image" class="btn btn-primary">Thêm</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="confirm-delete"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Bạn chắc chắn muốn xóa?</h5>
                <button type="button" style="margin-top: -25px !important;" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Chọn OK để xóa sản phẩm
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-primary" onclick="onDelete()">OK</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editImage" tabindex="-1" role="dialog" aria-labelledby="editImageLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="title-model">Chỉnh sửa hình ảnh cho sản phẩm</h5>
                <button style="margin-top: -25px !important;" type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="form-edit-image" method="post" action="admin-image-manage">
                    <input type="hidden" id="idProduct" name="id-Product">
                    <input type="hidden" name="action" value="edit">
                    <div class="form-group">
                        <label for="color-product">Màu</label>
                        <div>
                            <select
                                    class="form-control chosen-select"
                                    id="color-product" name="color-product"
                            >
                                <option value=""></option>
                                <c:forEach var="i" begin="0" end="${requestScope.infoForm.getColorList().size()-1}">
                                    <option value="${requestScope.infoForm.getColorList().get(i).getIdColor()}">${requestScope.infoForm.getColorList().get(i).getNameColor()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>
                    <div class="form-group">
                        <label for="image">Hình ảnh</label>
                        <input type="file" onchange="previewImage(this)" class="form-control" name="image" id="image"
                               placeholder="Chọn ảnh">
                        <style>
                            #imageDetailPreview .img-preview:before {
                                display: none;
                            }
                        </style>
                        <div class="frame-image" id="imageDetailPreview"></div>
                        <p class="form-text text-danger text-muted form-error"></p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="submit" id="update-image" form="form-edit-image" class="btn btn-primary">Cập nhật</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
<script src='<c:url value="/admin/js/myAdmin/image-manage-script.js"/>'></script>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Danh sách sản phẩm</title>
</head>
<body>
	<div class="main-content">
		<div style="height: 0px; display: flex; justify-content: flex-end;">
			<div class="message_box"
				style="position: fixed; z-index: 9999; padding: 20px;">
				<div class="alert alert-success" id="message_box"
					style="width: 25vw;">
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

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>

					<li><a href="#">Tables</a></li>
					<li class="active">Sản phẩm</li>
				</ul>
				<!-- /.breadcrumb -->

				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon"> <input type="text"
							placeholder="Search ..." class="nav-search-input"
							id="nav-search-input" autocomplete="off" /> <i
							class="ace-icon fa fa-search nav-search-icon"></i>
						</span>
					</form>
				</div>
				<!-- /.nav-search -->
			</div>

			<div class="page-content">
				<div class="page-header clearfix">
					<h1>Sản phẩm</h1>
					<button type="button" id="btnAddProduct"
						class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#formProduct">Thêm sản phẩm</button>
				</div>
				<!-- /.page-header -->

				<div class="row">
					<div class="col-xs-12">

						<table id="table-product"></table>

						<script type="text/javascript">
							var $path_base = "${requestScope.base}";//in Ace demo this will be used for editurl parameter
						</script>
						<!-- PAGE CONTENT ENDS -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
	<%--popup confirm delete product--%>
	<div class="modal fade" id="confirm-delete-product" tabindex="-1"
		role="dialog" aria-labelledby="confirm-delete-product"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Bạn chắc chắn muốn xóa?</h5>
					<button type="button" style="margin-top: -25px !important;"
						class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Chọn Ok để xóa sản phẩm</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Đóng</button>
					<button type="button" class="btn btn-primary"
						id="btn-confirm-delete-product">OK</button>
				</div>
			</div>
		</div>
	</div>


	<%--popup thêm sản phẩm--%>
	<div class="modal fade" id="formProduct" tabindex="-1" role="dialog"
		aria-labelledby="formProductLable" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="headerFormProduct">Thêm sản phẩm</h5>
					<button style="margin-top: -25px !important;" id="btnDismiss"
						type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="form-add-product" method="post"
						action="admin-product-manage" enctype='multipart/form-data'>
						<div class="form-group" id="form-code-product">
							<label for="code-product">Mã sản phẩm</label>
							<div class="input-group">
								<input id="code-product" name="code-product" type="text"
									class="form-control search-query"
									placeholder="Mã sản phẩm nên gồm chu và số" /> <span
									class="input-group-btn">
									<button id="btnCheck" type="button"
										class="btn btn-purple btn-sm">
										<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
										Kiểm tra
									</button>
								</span>
							</div>
							<p class="form-text text-danger text-muted form-error"></p>
						</div>
						<div class="form-group">
							<label for="name-product">Tên sản phẩm</label> <input type="text"
								class="form-control" name="name-product" id="name-product"
								placeholder="Nhập tên sản phẩm ">
							<p class="form-text text-danger text-muted form-error"></p>
						</div>

						<div class="width-100" style="display: flex">
							<div class="width-100 form-group">
								<label for="brand">Nhãn hiệu</label>
								<div>
									<select class="form-control" id="brand" name="brand">
										<%--                                    <option value=""></option>--%>
										<c:forEach var="i" begin="0"
											end="${requestScope.infoForm.getBrandList().size()-1}">
											<option
												value="${requestScope.infoForm.getBrandList().get(i).getIdBrand()}">${requestScope.infoForm.getBrandList().get(i).getNameBrand()}</option>
										</c:forEach>
									</select>
								</div>
								<p class="form-text text-danger text-muted form-error"></p>
							</div>
						</div>

						<div class="form-group">
							<label for="price-product">Gía sản phẩm</label> <input
								type="text" class="form-control" name="price-product"
								id="price-product" placeholder="Nhập giá sản phẩm">
							<p class="form-text text-danger text-muted form-error"></p>
						</div>

						<div class="form-group">
							<label for="description">Mô tả sản phẩm</label>
							<textarea class="form-control" name="description"
								id="description" aria-describedby="description product"
								placeholder="Nhập mô tả về sản phẩm"></textarea>
							<p class="form-text text-danger text-muted form-error"></p>
						</div>
						<div class="form-group">
							<label for="thumbnail">Hình ảnh mẫu</label> <input type="file"
								onchange="previewImage(this)" class="form-control"
								name="thumbnail" id="thumbnail" />
							<div class="frame-image" id="imageThumbnailPreview"></div>
							<p class="form-text text-danger text-muted form-error"></p>
						</div>
						<%--                    <div id="frame-detail-images" class="form-group">--%>
						<%--                        <label for="images">Hình ảnh chi tiết (Có thể chọn nhiều)</label>--%>
						<%--                        <input onchange="previewImage(this)" type="file" multiple class="form-control" name="images"--%>
						<%--                               id="images"--%>
						<%--                        >--%>
						<%--                        <div class="frame-image" id="imageDetailPreview"></div>--%>
						<%--                        <p class="form-text text-danger text-muted form-error"></p>--%>
						<%--                    </div>--%>
						<div class="form-group" id="frame-tags">
							<label for="tag-product"
								class="col-sm-3 control-label no-padding-right">Gắn thẻ
								sản phẩm</label>
							<div id="inputTag">
								<select id="tag-product" multiple="multiple"
									class="form-control chosen-select" name="tag-product"
									data-placeholder="Hãy gắn thẻ sản phẩm...">
									<c:forEach var="i" begin="0"
										end="${requestScope.infoForm.getTagList().size()-1}">
										<option
											value="${requestScope.infoForm.getTagList().get(i).getIdTag()}">${requestScope.infoForm.getTagList().get(i).getNameTag()}</option>
									</c:forEach>
								</select>
								<p class="form-text text-danger text-muted form-error"></p>
							</div>
						</div>
						<div class="width-100" style="display: flex"
							id="frame_colors_sizes">
							<div class="width-100 form-group">
								<label for="color-product">Màu</label>
								<div id="inputColor">
									<select multiple="multiple" class="form-control chosen-select"
										id="color-product" name="color-product">
										<%--                                    <option value=""></option>--%>
										<c:forEach var="i" begin="0"
											end="${requestScope.infoForm.getColorList().size()-1}">
											<option
												value="${requestScope.infoForm.getColorList().get(i).getIdColor()}">${requestScope.infoForm.getColorList().get(i).getNameColor()}</option>
										</c:forEach>
									</select>
								</div>
								<p class="form-text text-danger text-muted form-error"></p>
							</div>
							<div class="width-100 form-group">
								<label for="size-product">Kích cỡ</label>
								<div id="inputSize">
									<select class="form-control chosen-select" multiple="multiple"
										id="size-product" name="size-product">
										<%--                                    <option value=""></option>--%>
										<c:forEach var="i" begin="0"
											end="${requestScope.infoForm.getSizeList().size()-1}">
											<option
												value="${requestScope.infoForm.getSizeList().get(i).getIdSize()}">${requestScope.infoForm.getSizeList().get(i).getNameSize()}</option>
										</c:forEach>
									</select>
								</div>
								<p class="form-text text-danger text-muted form-error"></p>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Đóng</button>
					<button type="submit" id="submit-form" form="form-add-product"
						class="btn btn-primary">Thêm</button>
				</div>
			</div>
		</div>
	</div>


<%--	<div class="modal fade" id="formEditProduct" tabindex="-1" role="dialog"--%>
<%--		 aria-labelledby="formProductLable" aria-hidden="true">--%>
<%--		<div class="modal-dialog" role="document">--%>
<%--			<div class="modal-content">--%>
<%--				<div class="modal-header">--%>
<%--					<h5 class="modal-title" >Chỉnh sửa sản phẩm</h5>--%>
<%--					<button style="margin-top: -25px !important;"--%>
<%--							type="button" class="close" data-dismiss="modal"--%>
<%--							aria-label="Close">--%>
<%--						<span aria-hidden="true">&times;</span>--%>
<%--					</button>--%>
<%--				</div>--%>
<%--				<div class="modal-body">--%>
<%--					<form method="put"--%>
<%--						  action="admin-product-manage" enctype='multipart/form-data'>--%>
<%--						<div class="form-group">--%>
<%--							<label for="code-product">Mã sản phẩm</label>--%>
<%--							<div class="input-group">--%>
<%--								<input id="code-product-edit" name="code-product" type="text"--%>
<%--									   class="form-control search-query"--%>
<%--									   placeholder="Mã sản phẩm nên gồm chu và số" /> <span--%>
<%--									class="input-group-btn">--%>
<%--									<button id="btnCheck-edit" type="button"--%>
<%--											class="btn btn-purple btn-sm">--%>
<%--										<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>--%>
<%--										Kiểm tra--%>
<%--									</button>--%>
<%--								</span>--%>
<%--							</div>--%>
<%--							<p class="form-text text-danger text-muted form-error"></p>--%>
<%--						</div>--%>
<%--						<div class="form-group">--%>
<%--							<label for="name-product">Tên sản phẩm</label> <input type="text"--%>
<%--																				  class="form-control" name="name-product" id="name-product-edit"--%>
<%--																				  placeholder="Nhập tên sản phẩm ">--%>
<%--							<p class="form-text text-danger text-muted form-error"></p>--%>
<%--						</div>--%>

<%--						<div class="width-100" style="display: flex">--%>
<%--							<div class="width-100 form-group">--%>
<%--								<label for="brand">Nhãn hiệu</label>--%>
<%--								<div>--%>
<%--									<select class="form-control" id="brand-edit" name="brand">--%>
<%--										&lt;%&ndash;                                    <option value=""></option>&ndash;%&gt;--%>
<%--										<c:forEach var="i" begin="0"--%>
<%--												   end="${requestScope.infoForm.getBrandList().size()-1}">--%>
<%--											<option--%>
<%--													value="${requestScope.infoForm.getBrandList().get(i).getIdBrand()}">${requestScope.infoForm.getBrandList().get(i).getNameBrand()}</option>--%>
<%--										</c:forEach>--%>
<%--									</select>--%>
<%--								</div>--%>
<%--								<p class="form-text text-danger text-muted form-error"></p>--%>
<%--							</div>--%>
<%--						</div>--%>

<%--						<div class="form-group">--%>
<%--							<label for="price-product">Gía sản phẩm</label> <input--%>
<%--								type="text" class="form-control" name="price-product"--%>
<%--								id="price-product-edit" placeholder="Nhập giá sản phẩm">--%>
<%--							<p class="form-text text-danger text-muted form-error"></p>--%>
<%--						</div>--%>

<%--						<div class="form-group">--%>
<%--							<label for="description">Mô tả sản phẩm</label>--%>
<%--							<textarea class="form-control" name="description"--%>
<%--									  id="description-edit" aria-describedby="description product"--%>
<%--									  placeholder="Nhập mô tả về sản phẩm"></textarea>--%>
<%--							<p class="form-text text-danger text-muted form-error"></p>--%>
<%--						</div>--%>
<%--						<div class="form-group">--%>
<%--							<label for="thumbnail">Hình ảnh mẫu</label> <input type="file"--%>
<%--																			   onchange="previewImage(this)" class="form-control"--%>
<%--																			   name="thumbnail" id="thumbnail-edit" />--%>
<%--							<div class="frame-image" id="imageThumbnailPreview-edit"></div>--%>
<%--							<p class="form-text text-danger text-muted form-error"></p>--%>
<%--						</div>--%>
<%--					</form>--%>
<%--				</div>--%>
<%--				<div class="modal-footer">--%>
<%--					<button type="button" class="btn btn-secondary"--%>
<%--							data-dismiss="modal">Đóng</button>--%>
<%--					<button type="submit" id="submit-form-edit" form="formEditProduct"--%>
<%--							class="btn btn-primary">Chỉnh sửa</button>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--	</div>--%>

	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
	<script
		src='<c:url value="/admin/js/myAdmin/product-manage-script.js"/>'></script>
	<script>
		function previewImage(input) {
			if (input.files.length > 1) {
				$("#imageDetailPreview").empty()
				for (let i = 0; i < input.files.length; i++) {
					readImage(input.files[i], "imageDetailPreview",
							"imagesDetail" + i)
				}
				$("#imageDetailPreview")
						.append(
								"<span onclick='clearImages()'><i class='fa-solid fa-xmark'></i></span>")
			} else {
				$("#imageThumbnailPreview").empty()
				readImage(input.files[0], "imageThumbnailPreview",
						"thumbnailPreview")
				$("#imageThumbnailPreview")
						.append(
								"<span onclick='clearThumbnail()'><i class='fa-solid fa-xmark'></i></span>")
			}
		}
		function readImage(input, frameImages, idImages) {
			let reader = new FileReader()
			const img = "<div class=\'img-preview\'><img id=" + idImages + " alt='image preview'/> "
					+ "<i class='fa-solid fa-magnifying-glass-plus'></i> </div>"
			$("#" + frameImages).append(img)
			reader.onload = function(e) {
				$("#" + idImages).attr("src", e.target.result)
			}
			reader.readAsDataURL(input)
		}
	</script>
	<script>
		$(document).ready(function() {
			let message = "${requestScope.message}";
			if (message === "") {
				$("#message_box").hide();
			} else {
				$("#message_box").fadeTo(2000, 500).slideUp(500, function() {
					$("#message_box").slideUp(500);
				});
			}
		})
	</script>
	<script type="text/javascript">
		jQuery(function($) {
			$('#id-disable-check').on('click', function() {
				var inp = $('#form-input-readonly').get(0);
				if (inp.hasAttribute('disabled')) {
					inp.setAttribute('readonly', 'true');
					inp.removeAttribute('disabled');
					inp.value = "This text field is readonly!";
				} else {
					inp.setAttribute('disabled', 'disabled');
					inp.removeAttribute('readonly');
					inp.value = "This text field is disabled!";
				}
			});
			if (!ace.vars['touch']) {
				$('.chosen-select').chosen({
					allow_single_deselect : true
				});
				//resize the chosen on window resize
				$(window).off('resize.chosen').on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						var $this = $(this);
						$this.next().css({
							'width' : $this.parent().width()
						});
					})
				}).trigger('resize.chosen');
				//resize chosen on sidebar collapse/expand
				$(document).on('settings.ace.chosen',
						function(e, event_name, event_val) {
							if (event_name != 'sidebar_collapsed')
								return;
							$('.chosen-select').each(function() {
								var $this = $(this);
								$this.next().css({
									'width' : $this.parent().width()
								});
							})
						});
				$('#chosen-multiple-style .btn').on(
						'click',
						function(e) {
							var target = $(this).find('input[type=radio]');
							var which = parseInt(target.val());
							if (which == 2)
								$('#form-field-select-4').addClass(
										'tag-input-style');
							else
								$('#form-field-select-4').removeClass(
										'tag-input-style');
						});
			}
			$('[data-rel=tooltip]').tooltip({
				container : 'body'
			});
			$('[data-rel=popover]').popover({
				container : 'body'
			});
			$('textarea[class*=autosize]').autosize({
				append : "\n"
			});
			$('textarea.limited').inputlimiter({
				remText : '%n character%s remaining...',
				limitText : 'max allowed : %n.'
			});
			$.mask.definitions['~'] = '[+-]';
			$('.input-mask-date').mask('99/99/9999');
			$('.input-mask-phone').mask('(999) 999-9999');
			$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
			$(".input-mask-product").mask("a*-999-a999", {
				placeholder : " ",
				completed : function() {
					alert("You typed the following: " + this.val());
				}
			});
			$("#input-size-slider").css('width', '200px').slider(
					{
						value : 1,
						range : "min",
						min : 1,
						max : 8,
						step : 1,
						slide : function(event, ui) {
							var sizing = [ '', 'input-sm', 'input-lg',
									'input-mini', 'input-small',
									'input-medium', 'input-large',
									'input-xlarge', 'input-xxlarge' ];
							var val = parseInt(ui.value);
							$('#form-field-4').attr('class', sizing[val]).val(
									'.' + sizing[val]);
						}
					});
			$("#input-span-slider").slider(
					{
						value : 1,
						range : "min",
						min : 1,
						max : 12,
						step : 1,
						slide : function(event, ui) {
							var val = parseInt(ui.value);
							$('#form-field-5').attr('class', 'col-xs-' + val)
									.val('.col-xs-' + val);
						}
					});
			//"jQuery UI Slider"
			//range slider tooltip example
			$("#slider-range")
					.css('height', '200px')
					.slider(
							{
								orientation : "vertical",
								range : true,
								min : 0,
								max : 100,
								values : [ 17, 67 ],
								slide : function(event, ui) {
									var val = ui.values[$(ui.handle).index() - 1]
											+ "";
									if (!ui.handle.firstChild) {
										$(
												"<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
												.prependTo(ui.handle);
									}
									$(ui.handle.firstChild).show().children()
											.eq(1).text(val);
								}
							}).find('span.ui-slider-handle').on('blur',
							function() {
								$(this.firstChild).hide();
							});
			$("#slider-range-max").slider({
				range : "max",
				min : 1,
				max : 10,
				value : 2
			});
			$("#slider-eq > span").css({
				width : '90%',
				'float' : 'left',
				margin : '15px'
			}).each(function() {
				// read initial values from markup and remove that
				var value = parseInt($(this).text(), 10);
				$(this).empty().slider({
					value : value,
					range : "min",
					animate : true
				});
			});
			$("#slider-eq > span.ui-slider-purple").slider('disable');//disable third item
			$('#id-input-file-1 , #id-input-file-2').ace_file_input({
				no_file : 'No File ...',
				btn_choose : 'Choose',
				btn_change : 'Change',
				droppable : false,
				onchange : null,
				thumbnail : false
			//| true | large
			//whitelist:'gif|png|jpg|jpeg'
			//blacklist:'exe|php'
			//onchange:''
			//
			});
			//pre-show a file name, for example a previously selected file
			//$('#id-input-file-1').ace_file_input('show_file_list', ['myfile.txt'])
			$('#id-input-file-3').ace_file_input({
				style : 'well',
				btn_choose : 'Drop files here or click to choose',
				btn_change : null,
				no_icon : 'ace-icon fa fa-cloud-upload',
				droppable : true,
				thumbnail : 'small'//large | fit
				//,icon_remove:null//set null, to hide remove/reset button
				/**,before_change:function(files, dropped) {
							//Check an example below
							//or examples/file-upload.html
							return true;
						}*/
				/**,before_remove : function() {
							return true;
						}*/
				,
				preview_error : function(filename, error_code) {
					//name of the file that failed
					//error_code values
					//1 = 'FILE_LOAD_FAILED',
					//2 = 'IMAGE_LOAD_FAILED',
					//3 = 'THUMBNAIL_FAILED'
					//alert(error_code);
				}
			}).on('change', function() {
				//console.log($(this).data('ace_input_files'));
				//console.log($(this).data('ace_input_method'));
			});
			//$('#id-input-file-3')
			//.ace_file_input('show_file_list', [
			//{type: 'image', name: 'name of image', path: 'http://path/to/image/for/preview'},
			//{type: 'file', name: 'hello.txt'}
			//]);
			//dynamically change allowed formats by changing allowExt && allowMime function
			$('#id-file-format').removeAttr('checked').on(
					'change',
					function() {
						var whitelist_ext, whitelist_mime;
						var btn_choose
						var no_icon
						if (this.checked) {
							btn_choose = "Drop images here or click to choose";
							no_icon = "ace-icon fa fa-picture-o";
							whitelist_ext = [ "jpeg", "jpg", "png", "gif",
									"bmp" ];
							whitelist_mime = [ "image/jpg", "image/jpeg",
									"image/png", "image/gif", "image/bmp" ];
						} else {
							btn_choose = "Drop files here or click to choose";
							no_icon = "ace-icon fa fa-cloud-upload";
							whitelist_ext = null;//all extensions are acceptable
							whitelist_mime = null;//all mimes are acceptable
						}
						var file_input = $('#id-input-file-3');
						file_input.ace_file_input('update_settings', {
							'btn_choose' : btn_choose,
							'no_icon' : no_icon,
							'allowExt' : whitelist_ext,
							'allowMime' : whitelist_mime
						})
						file_input.ace_file_input('reset_input');
						file_input.off('file.error.ace').on('file.error.ace',
								function(e, info) {
									//console.log(info.file_count);//number of selected files
									//console.log(info.invalid_count);//number of invalid files
									//console.log(info.error_list);//a list of errors in the following format
									//info.error_count['ext']
									//info.error_count['mime']
									//info.error_count['size']
									//info.error_list['ext']  = [list of file names with invalid extension]
									//info.error_list['mime'] = [list of file names with invalid mimetype]
									//info.error_list['size'] = [list of file names with invalid size]
									/**
									 if( !info.dropped ) {
											//perhapse reset file field if files have been selected, and there are invalid files among them
											//when files are dropped, only valid files will be added to our file array
											e.preventDefault();//it will rest input
										}
									 */
									//if files have been selected (not dropped), you can choose to reset input
									//because browser keeps all selected files anyway and this cannot be changed
									//we can only reset file field to become empty again
									//on any case you still should check files with your server side script
									//because any arbitrary file can be uploaded by user and it's not safe to rely on browser-side measures
								});
					});
			$('#spinner1').ace_spinner({
				value : 0,
				min : 0,
				max : 200,
				step : 10,
				btn_up_class : 'btn-info',
				btn_down_class : 'btn-info'
			}).closest('.ace-spinner').on('changed.fu.spinbox', function() {
				//alert($('#spinner1').val())
			});
			$('#spinner2').ace_spinner({
				value : 0,
				min : 0,
				max : 10000,
				step : 100,
				touch_spinner : true,
				icon_up : 'ace-icon fa fa-caret-up bigger-110',
				icon_down : 'ace-icon fa fa-caret-down bigger-110'
			});
			$('#spinner3').ace_spinner({
				value : 0,
				min : -100,
				max : 100,
				step : 10,
				on_sides : true,
				icon_up : 'ace-icon fa fa-plus bigger-110',
				icon_down : 'ace-icon fa fa-minus bigger-110',
				btn_up_class : 'btn-success',
				btn_down_class : 'btn-danger'
			});
			$('#spinner4').ace_spinner({
				value : 0,
				min : -100,
				max : 100,
				step : 10,
				on_sides : true,
				icon_up : 'ace-icon fa fa-plus',
				icon_down : 'ace-icon fa fa-minus',
				btn_up_class : 'btn-purple',
				btn_down_class : 'btn-purple'
			});
			//$('#spinner1').ace_spinner('disable').ace_spinner('value', 11);
			//or
			//$('#spinner1').closest('.ace-spinner').spinner('disable').spinner('enable').spinner('value', 11);//disable, enable or change value
			//$('#spinner1').closest('.ace-spinner').spinner('value', 0);//reset to 0
			//datepicker plugin
			//link
			$('.date-picker').datepicker({
				autoclose : true,
				todayHighlight : true
			})
			//show datepicker when clicking on the icon
			.next().on(ace.click_event, function() {
				$(this).prev().focus();
			});
			//or change it into a date range picker
			$('.input-daterange').datepicker({
				autoclose : true
			});
			//to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
			$('input[name=date-range-picker]').daterangepicker({
				'applyClass' : 'btn-sm btn-success',
				'cancelClass' : 'btn-sm btn-default',
				locale : {
					applyLabel : 'Apply',
					cancelLabel : 'Cancel',
				}
			}).prev().on(ace.click_event, function() {
				$(this).next().focus();
			});
			$('#timepicker1').timepicker({
				minuteStep : 1,
				showSeconds : true,
				showMeridian : false
			}).next().on(ace.click_event, function() {
				$(this).prev().focus();
			});
			$('#date-timepicker1').datetimepicker().next().on(ace.click_event,
					function() {
						$(this).prev().focus();
					});
			$('#colorpicker1').colorpicker();
			$('#simple-colorpicker-1').ace_colorpicker();
			//$('#simple-colorpicker-1').ace_colorpicker('pick', 2);//select 2nd color
			//$('#simple-colorpicker-1').ace_colorpicker('pick', '#fbe983');//select #fbe983 color
			//var picker = $('#simple-colorpicker-1').data('ace_colorpicker')
			//picker.pick('red', true);//insert the color if it doesn't exist
			$(".knob").knob();
			var tag_input = $('#form-field-tags');
			try {
				tag_input.tag({
					placeholder : tag_input.attr('placeholder'),
					//enable typeahead by specifying the source array
					source : ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
				/**
				 //or fetch data from database, fetch those that match "query"
				 source: function(query, process) {
					  $.ajax({url: 'remote_source.php?q='+encodeURIComponent(query)})
					  .done(function(result_items){
						process(result_items);
					  });
					}
				 */
				})
				//programmatically add a new
				var $tag_obj = $('#form-field-tags').data('tag');
				$tag_obj.add('Programmatically Added');
			} catch (e) {
				//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
				tag_input.after(
						'<textarea id="' + tag_input.attr('id') + '" name="'
								+ tag_input.attr('name') + '" rows="3">'
								+ tag_input.val() + '</textarea>').remove();
				//$('#form-field-tags').autosize({append: "\n"});
			}
			/////////
			$('#modal-form input[type=file]').ace_file_input({
				style : 'well',
				btn_choose : 'Drop files here or click to choose',
				btn_change : null,
				no_icon : 'ace-icon fa fa-cloud-upload',
				droppable : true,
				thumbnail : 'large'
			})
			//chosen plugin inside a modal will have a zero width because the select element is originally hidden
			//and its width cannot be determined.
			//so we set the width after modal is show
			$('#modal-form').on(
					'shown.bs.modal',
					function() {
						if (!ace.vars['touch']) {
							$(this).find('.chosen-container').each(
									function() {
										$(this).find('a:first-child').css(
												'width', '210px');
										$(this).find('.chosen-drop').css(
												'width', '210px');
										$(this).find('.chosen-search input')
												.css('width', '200px');
									});
						}
					})
			/**
			 //or you can activate the chosen plugin after modal is shown
			 //this way select element becomes visible with dimensions and chosen works as expected
			 $('#modal-form').on('shown', function () {
						$(this).find('.modal-chosen').chosen();
					})
			 */
			$(document)
					.one(
							'ajaxloadstart.page',
							function(e) {
								$('textarea[class*=autosize]').trigger(
										'autosize.destroy');
								$('.limiterBox,.autosizejs').remove();
								$(
										'.daterangepicker.dropdown-menu,.colorpicker.dropdown-menu,.bootstrap-datetimepicker-widget.dropdown-menu')
										.remove();
							});
		});
	</script>
	<%--<script src="assets/js/bootstrap.min.js"></script>--%>
	<script src='<c:url value="/assets/js/validation.js"/>'></script>
</body>
</html>
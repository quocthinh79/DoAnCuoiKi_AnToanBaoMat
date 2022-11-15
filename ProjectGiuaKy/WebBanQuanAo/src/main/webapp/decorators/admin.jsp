<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title><dec:title/></title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value='/admin/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/admin/font-awesome/4.2.0/css/font-awesome.min.css'/>"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="<c:url value="/admin/css/chosen.min.css"/>"/>


    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value='/admin/fonts/fonts.googleapis.com.css'/>"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value='/admin/css/ace.min.css'/>" class="ace-main-stylesheet"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<c:url value='/admin/css/ace-part2.min.css'/>" class="ace-main-stylesheet" />
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<c:url value='/admin/css/ace-ie.min.css'/>" />
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">
    <!-- inline styles related to this page -->

    <%--    style customizr--%>
    <link rel="stylesheet" type="text/css" href='<c:url value="/admin/css/customize.css"/>'/>

    <!-- ace settings handler -->
    <script src="<c:url value='/admin/js/ace-extra.min.js'/>"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="<c:url value='/admin/js/html5shiv.min.js'/>"></script>
    <script src="<c:url value='/admin/js/respond.min.js'/>"></script>

    <![endif]-->
</head>

<body class="no-skin">
<%--Giong--%>
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="admin-dash-board" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Admin WBH
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="<c:url value='/admin/avatars/user.jpg'/>" alt="Jason's Photo"/>
                        <span class="user-info" style="margin-top: 10px">
                            ${sessionScope.account == null?"Tài Khoản":sessionScope.account.userName}
                        </span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="home">
                                <i class="ace-icon fa fa-cog"></i>
                                Home shop
                            </a>
                        </li>

                        <%--                        <li>--%>
                        <%--                            <a href="">--%>
                        <%--                                <i class="ace-icon fa fa-user"></i>--%>
                        <%--                                Profile--%>
                        <%--                            </a>--%>
                        <%--                        </li>--%>

                        <li class="divider"></li>

                        <li>
                            <a href="logout">
                                <i class="ace-icon fa fa-power-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>
<%----%>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <!--[if !IE]> -->
    <script src="<c:url value='/admin/js/jquery.2.1.1.min.js'/>"></script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script src="<c:url value='/admin/js/jquery.1.11.1.min.js'/>"></script>
<![endif]-->
    <%--  Giong  --%>
    <div id="sidebar" class="sidebar                  responsive">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div><!-- /.sidebar-shortcuts -->

        <ul class="nav nav-list">
            <li class='${requestScope.tabName.equals("dashboard") ? "active": ""}'>
                <a href="admin-dash-board">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> Dashboard </span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class='${requestScope.tabName.equals("manage") ? "active": ""}'>
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-list"></i>
                    <span class="menu-text"> Quản lý </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class='${requestScope.subTabName.equals("manageProduct") ? "active": ""}'>
                        <a href="admin-product-manage">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Sản phẩm
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class='${requestScope.subTabName.equals("manageImageDetail") ? "active": ""}'>
                        <a href="admin-image-manage">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Hình ảnh sản phẩm
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class='${requestScope.subTabName.equals("manageUser") ? "active": ""}'>
                        <a href="admin-user-manage">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Tài Khoản
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class='${requestScope.subTabName.equals("manageBrand") ? "active": ""}'>
                        <a href="admin-brand-manage">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Nhãn hiệu
                        </a>
                    <li class='${requestScope.subTabName.equals("manageColor") ? "active": ""}'>
                        <a href="admin-color-manage">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Màu sắc
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
        </ul>
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
        </script>
    </div>
    <%----%>
    <dec:body/>
    <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

                &nbsp; &nbsp;
                <span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='<c:url value='/admin/js/jquery.min.js'/>'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='<c:url value='/admin/js/jquery1x.min.js'/>'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='<c:url value='/admin/js/jquery.mobile.custom.min.js'/>'>" + "<" + "/script>");
</script>
<script src="<c:url value='/admin/js/bootstrap.min.js'/>"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="<c:url value='/admin/js/excanvas.min.js'/>"></script>
<![endif]-->
<script src="<c:url value='/admin/js/jquery-ui.custom.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.ui.touch-punch.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.easypiechart.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.sparkline.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.flot.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.flot.pie.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.flot.resize.min.js'/>"></script>
<script src="<c:url value='/admin/js/chosen.jquery.min.js'/>"></script>

<!-- ace scripts -->
<script src="<c:url value='/admin/js/ace-elements.min.js'/>"></script>
<script src="<c:url value='/admin/js/ace.min.js'/>"></script>


<!-- inline scripts related to this page -->
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>权限管理</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel="stylesheet"
          href="../static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="../static/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="../static/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="../static/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="../static/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="../static/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="../static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="../static/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="../static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="../static/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="../static/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="../static/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="../static/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="../static/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="../static/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="../static/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="../static/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="../static/css/style.css">
    <link rel="stylesheet"
          href="../static/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="../static/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="../static/plugins/bootstrap-slider/slider.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                权限管理
                <small>全部权限</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="../pages/main.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a
                        href="../permission/findAll?page=1&size=10">权限管理</a></li>

                <li class="active">全部权限</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">

                                    <button type="button" class="btn btn-default" title="刷新"
                                            onclick="location.href='../permission/findAll?page=1&size=10'">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
                                </div>
                            </div>
                        </div>

                        <!--数据列表-->
                        <table id="dataList"
                               class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="sorting_asc">ID</th>
                                <th class="sorting_desc">权限名称</th>
                                <th class="sorting_asc sorting_asc_disabled">URL</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${permission.list}" var="permission">
                                <tr>
                                    <td>${permission.id }</td>
                                    <td>${permission.permissionName }</td>
                                    <td>${permission.url }</td>

                                </tr>
                            </c:forEach>
                            </tbody>

                        </table>
                        <!--数据列表/-->

                    </div>
                    <!-- 数据表格 /-->

                </div>


            </div>

            <div style="height: 65px" class="box-footer">
                <div style="float: right">
                    <ul STYLE="margin:5px" class="pagination">
                        <li>
                            <a href="../permission/findAll?page=1&size=${permission.pageSize}"
                               aria-label="Previous">首页</a>
                        </li>
                        <li>
                            <a href="../permission/findAll?page=${permission.pageNum-1}&size=${permission.pageSize}">上一页</a>
                        </li>
                        <c:forEach begin="1" end="${permission.pages}" var="pppp">
                            <li>
                                <a href="../permission/findAll?page=${pppp}&size=${permission.pageSize}">${pppp}</a>
                            </li>
                        </c:forEach>
                        <li>
                            <a href="../permission/findAll?page=${permission.pageNum+1}&size=${permission.pageSize}">下一页</a>
                        </li>
                        <li>
                            <a href="../permission/findAll?page=${permission.pages}&size=${permission.pageSize}"
                               aria-label="Next">尾页</a>
                        </li>

                    </ul>
                </div>

            </div>

        </section>
        <!-- 正文区域 /-->

    </div>

</div>

<script src="../static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../static/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="../static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="../static/plugins/raphael/raphael-min.js"></script>
<script src="../static/plugins/morris/morris.min.js"></script>
<script src="../static/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="../static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="../static/plugins/knob/jquery.knob.js"></script>
<script src="../static/plugins/daterangepicker/moment.min.js"></script>
<script src="../static/plugins/daterangepicker/daterangepicker.js"></script>
<script src="../static/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="../static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="../static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="../static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="../static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="../static/plugins/fastclick/fastclick.js"></script>
<script src="../static/plugins/iCheck/icheck.min.js"></script>
<script src="../static/plugins/adminLTE/js/app.min.js"></script>
<script src="../static/plugins/treeTable/jquery.treetable.js"></script>
<script src="../static/plugins/select2/select2.full.min.js"></script>
<script src="../static/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="../static/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="../static/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="../static/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="../static/plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="../static/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="../static/plugins/ckeditor/ckeditor.js"></script>
<script src="../static/plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="../static/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../static/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="../static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="../static/plugins/chartjs/Chart.min.js"></script>
<script src="../static/plugins/flot/jquery.flot.min.js"></script>
<script src="../static/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="../static/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="../static/plugins/flot/jquery.flot.categories.min.js"></script>
<script src="../static/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="../static/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script>
    $(document).ready(function () {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale: 'zh-CN'
        });
    });

    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }

    $(document)
        .ready(
            function () {

                // 激活导航位置
                setSidebarActive("admin-datalist");

                // 列表按钮
                $("#dataList td input[type='checkbox']")
                    .iCheck(
                        {
                            checkboxClass: 'icheckbox_square-blue',
                            increaseArea: '20%'
                        });
                // 全选操作
                $("#selall")
                    .click(
                        function () {
                            var clicks = $(this).is(
                                ':checked');
                            if (!clicks) {
                                $(
                                    "#dataList td input[type='checkbox']")
                                    .iCheck(
                                        "uncheck");
                            } else {
                                $(
                                    "#dataList td input[type='checkbox']")
                                    .iCheck("check");
                            }
                            $(this).data("clicks",
                                !clicks);
                        });
            });
</script>
</body>

</html>
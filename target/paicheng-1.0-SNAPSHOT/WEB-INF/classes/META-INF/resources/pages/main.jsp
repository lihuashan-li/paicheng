<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>实验室排程系统首页</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


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

        <img src="../static/img/center.jpg"
             width="100%" height="100%"/>

        <script
                src="../static/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <script
                src="../static/plugins/jQueryUI/jquery-ui.min.js"></script>
        <script>
            $.widget.bridge('uibutton', $.ui.button);
        </script>
        <script
                src="../static/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script
                src="../static/plugins/raphael/raphael-min.js"></script>
        <script
                src="../static/plugins/morris/morris.min.js"></script>
        <script
                src="../static/plugins/sparkline/jquery.sparkline.min.js"></script>
        <script
                src="../static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script
                src="../static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <script
                src="../static/plugins/knob/jquery.knob.js"></script>
        <script
                src="../static/plugins/daterangepicker/moment.min.js"></script>
        <script
                src="../static/plugins/daterangepicker/daterangepicker.js"></script>
        <script
                src="../static/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
        <script
                src="../static/plugins/datepicker/bootstrap-datepicker.js"></script>
        <script
                src="../static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
        <script
                src="../static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
        <script
                src="../static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <script
                src="../static/plugins/fastclick/fastclick.js"></script>
        <script
                src="../static/plugins/iCheck/icheck.min.js"></script>
        <script
                src="../static/plugins/adminLTE/js/app.min.js"></script>
        <script
                src="../static/plugins/treeTable/jquery.treetable.js"></script>
        <script
                src="../static/plugins/select2/select2.full.min.js"></script>
        <script
                src="../static/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <script
                src="../static/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
        <script
                src="../static/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
        <script
                src="../static/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
        <script
                src="../static/plugins/bootstrap-markdown/js/markdown.js"></script>
        <script
                src="../static/plugins/bootstrap-markdown/js/to-markdown.js"></script>
        <script
                src="../static/plugins/ckeditor/ckeditor.js"></script>
        <script
                src="../static/plugins/input-mask/jquery.inputmask.js"></script>
        <script
                src="../static/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script
                src="../static/plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <script
                src="../static/plugins/datatables/jquery.dataTables.min.js"></script>
        <script
                src="../static/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script
                src="../static/plugins/chartjs/Chart.min.js"></script>
        <script
                src="../static/plugins/flot/jquery.flot.min.js"></script>
        <script
                src="../static/plugins/flot/jquery.flot.resize.min.js"></script>
        <script
                src="../static/plugins/flot/jquery.flot.pie.min.js"></script>
        <script
                src="../static/plugins/flot/jquery.flot.categories.min.js"></script>
        <script
                src="../static/plugins/ionslider/ion.rangeSlider.min.js"></script>
        <script
                src="../static/plugins/bootstrap-slider/bootstrap-slider.js"></script>
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

            $(document).ready(function () {
                // 激活导航位置
                setSidebarActive("admin-index");
            });
        </script>
</body>

</html>
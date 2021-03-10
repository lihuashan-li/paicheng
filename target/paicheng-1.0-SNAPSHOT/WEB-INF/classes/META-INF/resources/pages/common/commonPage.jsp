<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>品保数据管理</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="../../static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../static/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="../../static/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="../../static/plugins/morris/morris.css">
    <link rel="stylesheet" href="../../static/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="../../static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="../../static/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="../../static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="../../static/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="../../static/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet" href="../../static/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="../../static/plugins/select2/select2.css">
    <link rel="stylesheet" href="../../static/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="../../static/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" href="../../static/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="../../static/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="../../static/css/style.css">
    <link rel="stylesheet" href="../../static/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="../../static/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet" href="../../static/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet" href="../../static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <jsp:include page="../common/header.jsp"></jsp:include>

    <jsp:include page="../common/aside.jsp"></jsp:include>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                数据管理 <small>数据列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="../../pages/main.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">品保数据管理</a></li>
                <li class="active">数据列表</li>
            </ol>
        </section>
        <section class="content">
            <div class="box box-primary">
                <button type="button" class="btn btn-default" title="已排刷新">
                    <i class="fa fa-cube"></i> 已排刷新
                </button>
                <div class="box-body">
                    <div class="table-box">
                        <div class="ccc table table-bordered table-striped table-hover dataTable">
                            <table id="" class="table table-bordered table-striped table-hover dataTable">

                            </table>
                        </div>
                    </div>
                </div>
                <div style="height: 65px" class="box-footer">
                    <div style="float: right">
                        <ul STYLE="margin:5px" class="pagination">
                            <li><a>X条数据</a></li>
                            <li>
                                <a href="" aria-label="Previous">首页</a>
                            </li>
                            <li>
                                <a href="">上一页</a>
                            </li>
                            <li>
                                <a href="">X</a>
                            </li>
                            <li>
                                <a href="">下一页</a>
                            </li>
                            <li>
                                <a href="" aria-label="Next">尾页</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <script src="../../static/js/vue.min.js"></script>
    <script src="../../static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="../../static/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="../../static/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../static/plugins/raphael/raphael-min.js"></script>
    <script src="../../static/plugins/morris/morris.min.js"></script>
    <script src="../../static/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="../../static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="../../static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="../../static/plugins/knob/jquery.knob.js"></script>
    <script src="../../static/plugins/daterangepicker/moment.min.js"></script>
    <script src="../../static/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="../../static/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
    <script src="../../static/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="../../static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="../../static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script src="../../static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="../../static/plugins/fastclick/fastclick.js"></script>
    <script src="../../static/plugins/iCheck/icheck.min.js"></script>
    <script src="../../static/plugins/adminLTE/js/app.min.js"></script>
    <script src="../../static/plugins/treeTable/jquery.treetable.js"></script>
    <script src="../../static/plugins/select2/select2.full.min.js"></script>
    <script src="../../static/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    <script src="../../static/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
    <script src="../../static/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
    <script src="../../static/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
    <script src="../../static/plugins/bootstrap-markdown/js/markdown.js"></script>
    <script src="../../static/plugins/bootstrap-markdown/js/to-markdown.js"></script>
    <script src="../../static/plugins/ckeditor/ckeditor.js"></script>
    <script src="../../static/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="../../static/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="../../static/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="../../static/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="../../static/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="../../static/plugins/chartjs/Chart.min.js"></script>
    <script src="../../static/plugins/flot/jquery.flot.min.js"></script>
    <script src="../../static/plugins/flot/jquery.flot.resize.min.js"></script>
    <script src="../../static/plugins/flot/jquery.flot.pie.min.js"></script>
    <script src="../../static/plugins/flot/jquery.flot.categories.min.js"></script>
    <script src="../../static/plugins/ionslider/ion.rangeSlider.min.js"></script>
    <script src="../../static/plugins/bootstrap-slider/bootstrap-slider.js"></script>
    <script src="../../static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="../../static/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script>
        $(function () {

        })
    </script>
</body>

</html>
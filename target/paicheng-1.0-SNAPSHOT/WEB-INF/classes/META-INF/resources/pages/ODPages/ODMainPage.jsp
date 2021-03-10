<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>实车数据管理</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
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
<style>
    .btnDisplayFalse{
        display: none !important;
    }
    #table_unsorted tr td,#table_sorted tr td{
        text-align: center;
        font-size: 12px;
    }
    .trTitle{
        font-size: 14px;
        font-weight: bold;
    }
    .bgc-red{
        background-color: 	#FFFF00;!important;
    }
    .bgc-orange{
        background-color: #8EE5EE;!important;
    }
    .onDrag{
        background-color: #00EE76;
        font-size: 18px;
        /*border: 1px solid red;*/
    }
    #table_sorted_all{
        font-size: 12px;
        -moz-user-select:none; /*火狐*/
        -webkit-user-select:none; /*webkit浏览器*/
        -ms-user-select:none; /*IE10*/
        -khtml-user-select:none; /*早期浏览器*/
        user-select:none;
    }
</style>
</head>

<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <jsp:include page="../common/header.jsp"></jsp:include>

    <jsp:include page="../common/aside.jsp"></jsp:include>


    <div class="content-wrapper" id = "unsort-data-area">
        <section class="content-header">
            <h1>
                OD实车数据管理 <small>数据列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="../../pages/main.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">OD实车数据管理</a></li>
                <li class="active">数据列表</li>
            </ol>
        </section>
        <section class="content">
            <div class="box box-primary">
                <button type="button" class="btn btn-default" title="已排刷新" v-on:click="sortTableRefresh">
                    <i class="fa fa-cube"></i> 已排刷新
                </button>
                <button type="button" class="btn btn-default" title="工时维护" onclick="location.href='ODWorkTimePage.jsp'">
                    <i class="fa fa-cube"></i> 工时维护
                </button>
                <button type="button" class="btn btn-default" title="批量操作" v-on:click="openBatchModal4">
                    <i class="fa fa-cube"></i> 批量操作
                </button>
                <button type="button" class="btn btn-default" title="顺序调整" v-on:click="sortTableReSort">
                    <i class="fa fa-cube"></i> 顺序调整
                </button>
                <div class="box-body">
                    <div class="table-box">
                        <div class="ccc table table-bordered dataTable" style="overflow: scroll;overflow-y: hidden">
                            <table class="table table-bordered dataTable" id="table_sorted" style="width: 2200px">
                                <tr class="trTitle">
                                    <td></td>
                                    <td>ID</td>
                                    <td>Order<br/>顺位</td>
                                    <td>RequestNo<br/>需求编号</td>
                                    <td>RequestTime<br/>需求日期</td>
                                    <td>ProjectName<br/>项目名称</td>
                                    <td>ProjectEngineer<br/>项目工程师</td>
                                    <td>ContactExt<br/>电话</td>
                                    <td>PriorityDes<br/>紧急程度</td>
                                    <td>SampleID<br/>轮胎编号</td>
                                    <td>TestItem<br/>试验项目</td>
                                    <td>Standard<br/>法规</td>
                                    <td>Size<br/>轮胎规格</td>
                                    <td>Pt(kPa)<br/>试验气压</td>
                                    <td>Fz(N)<br/>试验载荷</td>
                                    <td>Usage Rim.luch<br/>试验轮辋</td>
                                    <td>Vr(km/h)<br/>试验速度</td>
                                    <td>Purpose<br/>委试目的</td>
                                    <td>State<br/>状态</td>
                                    <td>Operation<br/>操作</td>
                                </tr>
                                <tr v-for="info2 in infoListSort" :class="setbgcByRequestTimeSort(info2.requestTime)">
                                    <td><input type="checkbox" name="sortedCheckBox" :rowId = "info2.odtestInfoId" :rowOrder = "info2.testOrder" :checked="0"></td>
                                    <td>{{info2.odtestInfoId}}</td>
                                    <td>{{info2.testOrder}}</td>
                                    <td>{{info2.reqNo}}</td>
                                    <td>{{formateDate(info2.requestTime)}}</td>
                                    <td>{{info2.project}}</td>
                                    <td>{{info2.projectEngineer}}</td>
                                    <td>{{info2.pEContact}}</td>
                                    <td>{{info2.priorityDes}}</td>
                                    <td>{{info2.sampleId}}</td>
                                    <td>{{info2.testItem}}</td>
                                    <td>{{info2.standard}}</td>
                                    <td>{{info2.tireSize}}</td>
                                    <td>{{info2.pressure}}</td>
                                    <td>{{info2.testLoad}}</td>
                                    <td>{{info2.usageRim}}</td>
                                    <td>{{info2.speed}}</td>
                                    <td>{{info2.purpose}}</td>
                                    <td>{{info2.state}}</td>
                                    <td>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="startTest([info2.odtestInfoId])">开始</button>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="finishTest([info2.odtestInfoId],[info2.testOrder])">完成</button>
                                        <%--<button type="button" class="btn bg-olive btn-xs" v-on:click="sortedChangeOrder([info2.odtestInfoId],[info2.testOrder])">插位</button>--%>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="sortToUnsort([info2.odtestInfoId],[info2.testOrder])">取消已排</button>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="sortedCancelTest([info2.odtestInfoId],[info2.testOrder])">取消实验</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div style="height: 65px" class="box-footer">
                    <div style="float: right">
                        <ul STYLE="margin:5px" class="pagination">
                            <li><a>{{totalRowsSort}}条数据</a></li>
                            <li>
                                <a href="javascript:;" v-on:click="toFirstPageSort" aria-label="Previous">首页</a>
                            </li>
                            <li>
                                <a href="javascript:;" v-on:click="prePage('sorted')">上一页</a>
                            </li>
                            <li v-for="pagenum in pageCountSort":class="(((currentPageSort-pagenum)<=2)&&((currentPageSort-pagenum)>=-2))?'btnDisplayTrue':'btnDisplayFalse'">
                                <a href="javascript:;" v-on:click="toPageSort(pagenum)">{{pagenum}}</a>
                            </li>
                            <li>
                                <a href="javascript:;"  v-on:click="nextPage('sorted')">下一页</a>
                            </li>
                            <li>
                                <a href="javascript:;" v-on:click="toLastPageSort" aria-label="Next">尾页</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <section class="content">
            <div class="box box-primary">
                <button type="button" class="btn btn-default" title="未排刷新" v-on:click="unSortTableRefresh">
                    <i class="fa fa-cube"></i> 未排刷新
                </button>
                <button type="button" class="btn btn-default" title="一键排程" v-on:click="unSortToSortInBatch">
                    <i class="fa fa-cube"></i> 一键排程
                </button>
                <button type="button" class="btn btn-default" title="批量操作" v-on:click="openBatchModal">
                    <i class="fa fa-cube"></i> 批量操作
                </button>
                <div class="box-body">
                    <div class="table-box">
                        <div class="ccc table table-bordered table-striped table-hover dataTable" style="overflow: scroll;overflow-y: hidden">
                            <table class="table table-bordered table-striped table-hover dataTable" id="table_unsorted" style="width: 2200px">
                                <tr class="trTitle">
                                    <td></td>
                                    <td>ID</td>
                                    <td>Order<br/>顺位</td>
                                    <td>RequestNo<br/>需求编号</td>
                                    <td>RequestTime<br/>需求日期</td>
                                    <td>ProjectName<br/>项目名称</td>
                                    <td>ProjectEngineer<br/>项目工程师</td>
                                    <td>ContactExt<br/>电话</td>
                                    <td>PriorityDes<br/>紧急程度</td>
                                    <td>SampleID<br/>轮胎编号</td>
                                    <td>TestItem<br/>试验项目</td>
                                    <td>Standard<br/>法规</td>
                                    <td>Size<br/>轮胎规格</td>
                                    <td>Pt(kPa)<br/>试验气压</td>
                                    <td>Fz(N)<br/>试验载荷</td>
                                    <td>Usage Rim.luch<br/>试验轮辋</td>
                                    <td>Vr(km/h)<br/>试验速度</td>
                                    <td>Purpose<br/>委试目的</td>
                                    <td>State<br/>状态</td>
                                    <td>Operation<br/>操作</td>
                                </tr>
                                <tr v-for="info in infoList">
                                    <td><input type="checkbox" name="unsortCheckBox" :rowId = "info.odtestInfoId" :rowOrder = "info.testOrder" :checked="0"></td>
                                    <td>{{info.odtestInfoId}}</td>
                                    <td>{{info.testOrder}}</td>
                                    <td>{{info.reqNo}}</td>
                                    <td>{{formateDate(info.requestTime)}}</td>
                                    <td>{{info.project}}</td>
                                    <td>{{info.projectEngineer}}</td>
                                    <td>{{info.pEContact}}</td>
                                    <td>{{info.priorityDes}}</td>
                                    <td>{{info.sampleId}}</td>
                                    <td>{{info.testItem}}</td>
                                    <td>{{info.standard}}</td>
                                    <td>{{info.tireSize}}</td>
                                    <td>{{info.pressure}}</td>
                                    <td>{{info.testLoad}}</td>
                                    <td>{{info.usageRim}}</td>
                                    <td>{{info.speed}}</td>
                                    <td>{{info.purpose}}</td>
                                    <td>{{info.state}}</td>
                                    <td>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="unSortToSortByOriginalOrder([info.odtestInfoId])">顺位排序</button>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="unSortToSortByUser([info.odtestInfoId])">插位排序</button>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="unSortCancelTest([info.odtestInfoId])">取消实验</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div style="height: 65px" class="box-footer">
                    <div style="float: right">
                        <ul STYLE="margin:5px" class="pagination">
                            <li><a>{{totalRows}}条数据</a></li>
                            <li>
                                <a href="javascript:;" v-on:click="toFirstPage" aria-label="Previous">首页</a>
                            </li>
                            <li>
                                <a href="javascript:;" v-on:click="prePage('unsorted')">上一页</a>
                            </li>
                            <li v-for="pagenum in pageCount":class="(((currentPage-pagenum)<=2)&&((currentPage-pagenum)>=-2))?'btnDisplayTrue':'btnDisplayFalse'">
                                <a href="javascript:;" v-on:click="toPage(pagenum)">{{pagenum}}</a>
                            </li>
                            <li>
                                <a href="javascript:;"  v-on:click="nextPage('unsorted')">下一页</a>
                            </li>
                            <li>
                                <a href="javascript:;" v-on:click="toLastPage" aria-label="Next">尾页</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <div class="modal fadeA" id="modal001" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabelSPSP" aria-hidden="true">
            <div class="modal-dialog modal-sm ">
                <div class="modal-content" style=" width: 450px;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body" style="height: 150px; text-align: center; font-size: 16px;">
                        将选中的实验插入到第<input id="modal001_input001" style="width:50px;text-align: center">位<br/>(多笔数据顺延)
                    </div>
                    <div class="modal-footer">
                        <button v-on:click="unsortToSortWithOrderSubmit" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fadeA" id="modal002" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabelSPSP" aria-hidden="true">
            <div class="modal-dialog modal-sm " style="width: 80%;top:0">
                <div class="modal-content" style="">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body" style="height: 80%; text-align: center; font-size: 16px;overflow: scroll;overflow-y: hidden">
                        <table class="table table-bordered dataTable" id="table_sorted_all" style="width: 2200px;text-align:center">
                            <tr class="trTitle">
                                <td>ID</td>
                                <td>Order<br/>顺位</td>
                                <td>RequestNo<br/>需求编号</td>
                                <td>RequestTime<br/>需求日期</td>
                                <td>ProjectName<br/>项目名称</td>
                                <td>ProjectEngineer<br/>项目工程师</td>
                                <td>ContactExt<br/>电话</td>
                                <td>PriorityDes<br/>紧急程度</td>
                                <td>SampleID<br/>轮胎编号</td>
                                <td>TestItem<br/>试验项目</td>
                                <td>Standard<br/>法规</td>
                                <td>Size<br/>轮胎规格</td>
                                <td>Pt(kPa)<br/>试验气压</td>
                                <td>Fz(N)<br/>试验载荷</td>
                                <td>Usage Rim.luch<br/>试验轮辋</td>
                                <td>Vr(km/h)<br/>试验速度</td>
                                <td>Purpose<br/>委试目的</td>
                                <td>State<br/>状态</td>
                            </tr>
                            <tr v-for="info3 in infoListSortedAll" :class="setbgcByRequestTimeSort(info3.requestTime)">
                                <td>{{info3.odtestInfoId}}</td>
                                <td>{{info3.testOrder}}</td>
                                <td>{{info3.reqNo}}</td>
                                <td>{{formateDate(info3.requestTime)}}</td>
                                <td>{{info3.project}}</td>
                                <td>{{info3.projectEngineer}}</td>
                                <td>{{info3.pEContact}}</td>
                                <td>{{info3.priorityDes}}</td>
                                <td>{{info3.sampleId}}</td>
                                <td>{{info3.testItem}}</td>
                                <td>{{info3.standard}}</td>
                                <td>{{info3.tireSize}}</td>
                                <td>{{info3.pressure}}</td>
                                <td>{{info3.testLoad}}</td>
                                <td>{{info3.usageRim}}</td>
                                <td>{{info3.speed}}</td>
                                <td>{{info3.purpose}}</td>
                                <td>{{info3.state}}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button v-on:click="saveOrderByUser" class="btn btn-primary">保存顺序</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fadeA" id="modal003" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabelSPSP" aria-hidden="true">
            <div class="modal-dialog modal-sm ">
                <div class="modal-content" style=" width: 450px;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body" style="height: 150px; text-align: center; font-size: 16px;">
                        <div>
                            将选中的实验插入到第<input id="modal003_input001" style="width:50px;text-align: center">位<br/>(多笔数据顺延)
                            <br/>
                            <br/>
                            <button v-on:click="unSortOperateInBatch('1')" class="btn btn-primary" style="margin: 5px">插位排序</button>
                            <button v-on:click="unSortOperateInBatch('2')" class="btn btn-primary" style="margin: 5px">顺位排序</button>
                            <button v-on:click="unSortOperateInBatch('3')" class="btn btn-primary" style="margin: 5px">取消实验</button>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button v-on:click="closeModalWithID('modal003')" class="btn btn-primary">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fadeA" id="modal004" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabelSPSP" aria-hidden="true">
            <div class="modal-dialog modal-sm ">
                <div class="modal-content" style=" width: 450px;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body" style="height: 100px; text-align: center; font-size: 16px;">
                        <div>
                            <button v-on:click="sortsedOperateInBatch('1')" class="btn btn-primary" style="margin: 5px">取消已排</button>
                            <button v-on:click="sortsedOperateInBatch('2')" class="btn btn-primary" style="margin: 5px">取消实验</button>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button v-on:click="closeModalWithID('modal004')" class="btn btn-primary">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="../../static/js/html5shiv.min.js"></script>
    <script src="../../static/js/respond.min.js"></script>
    <%--<script src="../../static/js/vue.min.js"></script>--%>
    <script src="../../static/js/vue@2.5.16.js"></script>
    <script src="../../static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="../../static/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script src="../../static/js/jquery.tablednd.js"></script>
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
        var v1 = new Vue({
            el:"#unsort-data-area",
            data: {
                infoList: "",
                totalRows:0,
                currentPage:1,
                pageCount:[],
                infoListSort: "",
                totalRowsSort:0,
                currentPageSort:1,
                pageCountSort:[],
                operationList:[],
                purposeOrder:"",
                fromUnsortOrSort:"",
                orgOrderList:[],
                infoListSortedAll:""
            },
            created:function(){
                this.sortTableRefresh();
                this.unSortTableRefresh();
            },
            mounted:function(){
                // $(".bgc-red").css("background-color","red");
                // $(".bgc-orange").css("background-color","orange");
            },
            methods:{
                // 操作确认
                operationConfirm:function(){
                    var userConfirm = confirm("操作确认!");
                    return userConfirm;
                },
                // 关闭模态框
                closeModalWithID:function(modalId){
                    $("#"+modalId).modal("hide");
                },
                // 打开未排区域批量操作模态框
                openBatchModal:function(){
                    $("#modal003_input001").val("");
                    $("#modal003").modal("show");
                },
                // 打开已排区域批量操作模态框
                openBatchModal4:function(){
                    $("#modal004").modal("show");
                },
                // 已排区域批量操作（取消已排&取消实验）
                sortsedOperateInBatch:function(operationNo){
                    var _this = this;
                    var operationIdList = [];
                    var operationOrderList = [];
                    $("input[name='sortedCheckBox']:checked").each(function () {
                        operationIdList.push(this.getAttribute("rowid"));
                        operationOrderList.push(this.getAttribute("roworder"));
                    });
                    if(_this.isEmptyList(operationIdList)){
                        alert("请选择需要操作的列!");
                        return;
                    }
                    switch (operationNo) {
                        case "1":
                            _this.sortToUnsort(operationIdList,operationOrderList);
                            break;
                        case "2":
                            _this.sortedCancelTest(operationIdList,operationOrderList);
                            break;
                        default:
                            break;
                    }
                    $("#modal004").modal("hide");
                },
                // 已排区域用户手动调整顺序
                saveOrderByUser:function(){
                    var _this = this;
                    if(!(_this.operationConfirm())){
                        return;
                    };
                    var afterAdjustOrderList = [];
                    var afterAdjustInfoIdList = [];
                    var returnAdjustOrderList = [];
                    var returnAdjustIdList = [];
                    $("#table_sorted_all tr[class!='trTitle']").each(function(item){
                        afterAdjustOrderList.push($(this)[0].children[1].innerHTML);
                        afterAdjustInfoIdList.push($(this)[0].children[0].innerHTML);
                    })
                    for(var i = 0 ; i < afterAdjustOrderList.length ; i++){
                        if(afterAdjustOrderList[i]!=(i+1)){
                            returnAdjustOrderList.push(i+1);
                            returnAdjustIdList.push(afterAdjustInfoIdList[i]);
                        }
                    }
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/adjustOrderByUserOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            testInfoIdList:returnAdjustIdList,
                            purposeOrderList:returnAdjustOrderList
                        },
                        success:function(data){
                            _this.infoListSortedAll = [];
                            $('#modal002').modal('hide');
                            _this.reFreshAllPageWithCurrentPageNum();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });

                },
                // 打开顺序调整模态框 向table内赋值 使table可拖拽
                sortTableReSort:function(){
                    var _this = this;
                    // _this.openReSortModal().then(function (data) {
                    //     _this.emptyAllSortedInfoList().then(function(data){
                    //         // this.updateList3().then(val =>{
                    //         _this.getAllSortedInfo().then(function(data){
                    //             _this.setTableDarggable();
                    //         })
                    //     })
                    // })
                    _this.openReSortModal().then(function (data){_this.emptyAllSortedInfoList()}).then(function (data){_this.getAllSortedInfo()}).then(function (data){_this.setTableDarggable()});
                },
                // 打开顺序调整模态框
                openReSortModal:function(){
                    $('#modal002').modal('show');
                    return new Promise(function(resolve, reject){
                        resolve();
                    });
                },
                // 清空顺序调整模态框内table值（会保留上一次拖拽结果，需清空）
                emptyAllSortedInfoList:function(){
                    this.infoListSortedAll = [];
                    return new Promise(function(resolve, reject){
                        resolve();
                    });
                },
                // 将table加上可拖拽属性
                setTableDarggable:function(){
                    $("#table_sorted_all").tableDnD({
                        onDragClass:"onDrag",
                    });
                },
                // 获取所有已排序实验信息
                getAllSortedInfo:function(){
                    var _this = this;
                    this.infoListSortedAll = "";
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/getAllSortInfoOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{

                        },
                        success:function(data){
                            _this.infoListSortedAll = data;
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                    return new Promise(function(resolve, reject){
                            resolve();
                    });
                },
                // 判断字符串是否为空
                isEmptyString:function(checkStr){
                    return (checkStr==""||checkStr==false||checkStr==null||checkStr==undefined);
                },
                // 判断数组是否为空
                isEmptyList:function(checkList){
                    return (checkList.length<=0);
                },
                // 已排开始实验
                startTest:function(orgIdList){
                    var _this = this;
                    if(!(_this.operationConfirm())){
                        return;
                    };
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/sortedSatrtTestOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            testInfoIdList:orgIdList,
                        },
                        success:function(data){
                            _this.reFreshAllPageWithCurrentPageNum();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 完成实验
                finishTest:function(orgIdList,orgOrderList){
                    var _this = this;
                    if(!(_this.operationConfirm())){
                        return;
                    };
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/sortedFinishTestOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            testInfoIdList:orgIdList,
                            orgOrderList:orgOrderList
                        },
                        success:function(data){
                            _this.reFreshAllPageWithCurrentPageNum();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 已排取消实验
                sortedCancelTest:function(orgIdList,orgOrderList){
                    var _this = this;
                    if(!(_this.operationConfirm())){
                        return;
                    };
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/sortedTestCancelOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            testInfoIdList:orgIdList,
                            orgOrderList:orgOrderList
                        },
                        success:function(data){
                            _this.reFreshAllPageWithCurrentPageNum();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 已排插位排序
                sortedChangeOrder:function(orgIdList,orgOrderList){
                    this.orgOrderList = orgOrderList;
                    this.fromUnsortOrSort = "sort";
                    this.operationList = orgIdList;
                    $('#modal001').modal('show');
                },
                // 已排实验返回至未排区域
                sortToUnsort:function(orgIdList,orgOrderList){
                    var _this = this;
                    if(!(_this.operationConfirm())){
                        return;
                    };
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/sortedToUnSortOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            testInfoIdList:orgIdList,
                            orgOrderList:orgOrderList
                        },
                        success:function(data){
                            _this.reFreshAllPageWithCurrentPageNum();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 未排插位排序
                unsortToSortWithOrderSubmit:function(){
                    var _this = this;
                    if(this.isEmptyString($("#modal001_input001").val())){
                        alert("目标位置不能为空!");
                    }else if($("#modal001_input001").val()>(_this.totalRowsSort+1)){
                        alert("目标位置不能大于已排序最大顺位!");
                    }else{
                        if(!_this.operationConfirm()){
                            return;
                        };
                        this.purposeOrder = $("#modal001_input001").val();
                        if(_this.fromUnsortOrSort=="unSort"){
                            $.ajax({
                                url:"${pageContext.request.contextPath }/test/unSortToSortByPurposeOrderOD",
                                type:"POST",
                                async:false,
                                traditional:true,
                                data:{
                                    purposeOrder: _this.purposeOrder,
                                    operationList:_this.operationList
                                },
                                success:function(data){
                                    _this.reFreshAllPageWithCurrentPageNum();
                                    _this.operationList = [];
                                    _this.fromUnsortOrSort = "";
                                },
                                error:function(error){
                                    console.log(error);
                                }
                            });
                        }else if(_this.fromUnsortOrSort=="sort"){
                            $.ajax({
                                url:"${pageContext.request.contextPath }/test/sortedReSortByOrderOD",
                                type:"POST",
                                async:false,
                                traditional:true,
                                data:{
                                    purposeOrder: _this.purposeOrder,
                                    operationList:_this.operationList,
                                    orgOrderList:_this.orgOrderList
                                },
                                success:function(data){
                                    _this.reFreshAllPageWithCurrentPageNum();
                                    _this.operationList = [];
                                    _this.fromUnsortOrSort = "";
                                },
                                error:function(error){
                                    console.log(error);
                                }
                            });
                        }
                        $("#modal001_input001").val("");
                        $('#modal001').modal('hide');
                    }

                },
                // 时间格式化（从后台获取到的时间格式）
                formateDate:function(orgTime){
                    var requestTimeFormat = new Date(orgTime);
                    var returnDate = requestTimeFormat.getFullYear()+"-"+(requestTimeFormat.getMonth()+1)+"-"+requestTimeFormat.getDate();
                    return returnDate;
                },
                // 时间格式化（已是js时间格式）
                formateDateWithHMS:function(formateDate){
                    return this.formatStringWithZero(formateDate.getFullYear())+"-"+this.formatStringWithZero(formateDate.getMonth()+1)+"-"+this.formatStringWithZero(formateDate.getDate())+" "+this.formatStringWithZero(formateDate.getHours())+":"+this.formatStringWithZero(formateDate.getMinutes())+":"+this.formatStringWithZero(formateDate.getSeconds());
                },
                // 字符串格式化 以0填充
                formatStringWithZero:function(fromString){
                    return fromString.toString().length>=2?fromString.toString():"0"+fromString.toString();
                },
                // 刷新已排区域
                sortTableRefresh:function(){
                    var _this = this;
                    this.getCountByState(_this,["wait","testing","pause"],"sorted");
                    this.getOnePageData(_this,["wait","testing","pause"],1,"sorted");
                },
                // 刷新未排区域
                unSortTableRefresh:function(){
                    var _this = this;
                    this.getCountByState(_this,["unsorted"],"unsorted");
                    this.getOnePageData(_this,["unsorted"],1,"unsorted");
                },
                // 一键排程（暂未使用，仅获取可用时间）
                unSortToSortInBatch:function(){
                    this.getUseableWorkTime();
                },
                // 未排批量操作（插位&顺位&取消实验）
                unSortOperateInBatch:function(optionNo){
                    var _this = this;
                    var operationIdList = [];
                    var operationOrderList = [];
                    $("input[name='unsortCheckBox']:checked").each(function () {
                        operationIdList.push(this.getAttribute("rowid"));
                        operationOrderList.push(this.getAttribute("roworder"));
                    });
                    if(_this.isEmptyList(operationIdList)){
                        alert("请选择需要操作的列!");
                        return;
                    }
                    switch (optionNo) {
                        case "1" :
                            if(_this.isEmptyString($("#modal003_input001").val())){
                                alert("目标位置不能为空");
                            }else{
                                if(!(_this.operationConfirm())){
                                    return;
                                };
                                $.ajax({
                                    url:"${pageContext.request.contextPath }/test/unSortToSortByPurposeOrderOD",
                                    type:"POST",
                                    async:false,
                                    traditional:true,
                                    data:{
                                        purposeOrder: $("#modal003_input001").val(),
                                        operationList:operationIdList
                                    },
                                    success:function(data){
                                        _this.reFreshAllPageWithCurrentPageNum();
                                        $("#modal003").modal("hide");
                                    },
                                    error:function(error){
                                        console.log(error);
                                    }
                                });
                            }
                            break;
                        case "2" :
                            _this.unSortToSortByOriginalOrder(operationIdList);
                            $("#modal003").modal("hide");
                            break;
                        case "3" :
                            _this.unSortCancelTest(operationIdList);
                            $("#modal003").modal("hide");
                            break;
                        default:
                            break;
                    }
                },
                // 未排顺位排序
                unSortToSortByOriginalOrder:function(orgIdList){
                    var _this = this;
                    if(!(_this.operationConfirm())){
                        return;
                    };
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/unSortToSortByOriginalOrderOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            // testInfoIdList:orgIdList,
                            testInfoIdList:orgIdList,
                            // nowTime: _this.formateDateWithHMS(new Date()),
                        },
                        success:function(data){
                            _this.reFreshAllPageWithCurrentPageNum();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 未排插位排序打开模态框
                unSortToSortByUser:function(orgIdList){
                    this.fromUnsortOrSort = "unSort";
                    this.operationList = orgIdList;
                    $('#modal001').modal('show');
                },
                // 未排取消实验
                unSortCancelTest:function(orgIdList){
                    var _this = this;
                    if(!(_this.operationConfirm())){
                        return;
                    };
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/unSortTestCancelOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            // testInfoIdList:orgIdList,
                            testInfoIdList:orgIdList,
                        },
                        success:function(data){
                            console.log("success");
                            _this.reFreshAllPageWithCurrentPageNum();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 刷新所有区域数据
                reFreshAllPageWithCurrentPageNum:function(){
                    var _this = this;
                    this.getCountByState(_this,["wait","testing","pause"],"sorted");
                    this.getOnePageData(_this,["wait","testing","pause"],this.currentPageSort,"sorted");
                    this.getCountByState(_this,["unsorted"],"unsorted");
                    this.getOnePageData(_this,["unsorted"],this.currentPage,"unsorted");
                },
                // 未排首页
                toFirstPage:function(){
                    var _this = this;
                    this.getOnePageData(_this,["unsorted"],1,"unsorted");
                },
                // 未排尾页
                toLastPage:function(){
                    var _this = this;
                    this.getOnePageData(_this,["unsorted"],Math.ceil(this.totalRows/10),"unsorted");
                },
                // 已排首页
                toFirstPageSort:function(){
                    var _this = this;
                    this.getOnePageData(_this,["wait","testing","pause"],1,"sorted");
                },
                // 已排尾页
                toLastPageSort:function(){
                    var _this = this;
                    this.getOnePageData(_this,["wait","testing","pause"],Math.ceil(this.totalRowsSort/10),"sorted");
                },
                // 未排页面跳转
                toPage:function(pagenum){
                    var _this = this;
                    this.getOnePageData(_this,["unsorted"],pagenum,"unsorted");
                },
                // 已经排页面跳转
                toPageSort:function(pagenum){
                    var _this = this;
                    this.getOnePageData(_this,["wait","testing","pause"],pagenum,"sorted");
                },
                // 上一页
                prePage:function(stateFlag){
                    var nowPage = "";
                    var _this = this;
                    if(stateFlag=="unsorted"){
                        nowPage = this.currentPage;
                        if(nowPage > 1){
                            this.getOnePageData(_this,["unsorted"],parseInt(nowPage)-1,"unsorted");
                        }
                    }else{
                        nowPage = this.currentPageSort;
                        if(nowPage > 1){
                            this.getOnePageData(_this,["wait","testing","pause"],parseInt(nowPage)-1,"sorted");
                        }
                    }
                },
                // 下一页
                nextPage:function(stateFlag){
                    var totalPage = 0;
                    var nowPage = "";
                    var _this = this;
                    if(stateFlag=="unsorted"){
                        nowPage = this.currentPage;
                        totalPage = Math.ceil(this.totalRows/10);
                        if(nowPage < totalPage){
                            this.getOnePageData(_this,["unsorted"],parseInt(nowPage)+1,"unsorted");
                        }
                    }else{
                        nowPage = this.currentPageSort;
                        totalPage = Math.ceil(this.totalRowsSort/10);
                        if(nowPage < totalPage){
                            this.getOnePageData(_this,["wait","testing","pause"],parseInt(nowPage)+1,"sorted");
                        }
                    }

                },
                // 获取数据总数量（条数）
                getCountByState:function (_this,states,stateFlag) {
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/getCountByStateOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            stateslist:states
                        },
                        success:function(data){
                            if(stateFlag=="unsorted"){
                                _this.totalRows = data;
                                var pagenum = Math.ceil(data/10);
                                _this.pageCount = [];
                                for(var i = 0 ; i < pagenum ; i++){
                                    _this.pageCount[i] = i + 1;
                                }
                            }else{
                                _this.totalRowsSort = data;
                                var pagenum = Math.ceil(data/10);
                                _this.pageCountSort = [];
                                for(var i = 0 ; i < pagenum ; i++){
                                    _this.pageCountSort[i] = i + 1;
                                }
                            }

                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 获取一页的数据
                getOnePageData:function (_this,states,pageNum,stateFlag) {
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/getOnePageDataOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            statesList:states,
                            pageNum:pageNum
                        },
                        success:function(data){
                            if(stateFlag=="unsorted"){
                                _this.infoList = data;
                                _this.currentPage = pageNum
                            }else{
                                _this.infoListSort = data;
                                _this.currentPageSort = pageNum
                            }

                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                // 警示背景色设置
                setbgcByRequestTimeSort:function (requsetTime) {
                    // 2020-07-15T16:00:00.000+0000
                    var requestTimeFormat = new Date(requsetTime);
                    var today = new Date();
                    var distanceFrom = new Date(requestTimeFormat.getFullYear()+"-"+(requestTimeFormat.getMonth()+1)+"-"+requestTimeFormat.getDate());
                    var distanceTo = new Date(today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate());
                    var difValue = (distanceFrom-distanceTo)/(1000 * 60 * 60 * 24);
                    var returnReustlt = "";
                    if(0<=difValue&& difValue<=3){
                        returnReustlt = "bgc-red";
                    }else if(3<difValue&&difValue<=5){
                        returnReustlt = "bgc-orange";
                    }else if(difValue<0){
                        returnReustlt = "bgc-red";
                    }else{
                        returnReustlt = "bgc-null";
                    }
                    return returnReustlt;
                }
            }
        })

    </script>
</body>

</html>
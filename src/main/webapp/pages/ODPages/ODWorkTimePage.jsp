<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>实车工时维护</title>
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
        #unFinishedWorkTimeTable tr td,#unFinishedWorkTimeTable tr td{
            text-align: center;
            font-size: 12px;
        }
        .trTitle{
            font-size: 14px;
            font-weight: bold;
        }
        .dateTimeInput{
            width: 100%;
            height: 100%;
            padding: 5px;
        }
        .dateTimeInputInModal{
            width: 100%;
            height: 30%;
            padding: 5px;
        }
    </style>
</head>

<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <jsp:include page="../common/header.jsp"></jsp:include>

    <jsp:include page="../common/aside.jsp"></jsp:include>


    <div class="content-wrapper" id = "work-time-area">
        <section class="content-header">
            <h1>
                OD工时维护 <small>数据列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="../../pages/main.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">OD工时维护</a></li>
                <li class="active">数据列表</li>
            </ol>
        </section>
        <section class="content">
            <div>
                <div class="panel panel-default">
                    <div class="panel-heading">新增OD工时</div>
                    <div class="row data-type">
                        <div class="col-md-2 title">输入上班时间</div>
                        <div class="col-md-4 data">
                            <input type="text" class="layui-input dateTimeInput" id="insert-start-time" placeholder="yyyy-MM-dd HH:mm:ss" lay-key="1">
                            <%--<input id="insert-start-time" name="startDate"  type="datetime-local" class="form-control" style="cursor: pointer">--%>
                        </div>
                        <div class="col-md-2 title">输入下班时间</div>
                        <div class="col-md-4 data">
                            <%--<input id="insert-end-time" name="endDate" type="datetime-local" class="form-control" style="cursor: pointer;">--%>
                                <input type="text" class="layui-input dateTimeInput" id="insert-end-time" placeholder="yyyy-MM-dd HH:mm:ss" lay-key="2">
                        </div>
                    </div>
                </div>
                <div class="box-tools text-center">
                    <button type="submit" class="btn bg-maroon"  v-on:click="createWorkTime">新增</button>
                </div>
            </div>
        </section>
        <section class="content">
            <div class="box box-primary">
                <button type="button" class="btn btn-default" title="工时刷新" v-on:click="">
                    <i class="fa fa-cube"></i> 工时刷新
                </button>
                <div class="box-body">
                    <div class="table-box">
                        <div class="ccc table table-bordered table-striped table-hover dataTable">
                            <table class="table table-bordered table-striped table-hover dataTable" id="unFinishedWorkTimeTable">
                                <tr class="trTitle">
                                    <td>Start Time</td>
                                    <td>End Time</td>
                                    <td>Operation</td>
                                </tr>
                                <tr v-for="unFinishWorkTime in unFinishedWorkTimeList">
                                    <td>{{formateDate(new Date(unFinishWorkTime.startTime))}}</td>
                                    <td>{{formateDate(new Date(unFinishWorkTime.endTime))}}</td>
                                    <td>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="deleteUnFinishedWorkTime(unFinishWorkTime.odworkTimeId)">删除</button>
                                        <button type="button" class="btn bg-olive btn-xs" v-on:click="updateUnFinishedWorkTime(unFinishWorkTime.odworkTimeId,unFinishWorkTime.startTime,unFinishWorkTime.endTime)">修改</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="modal fadeA" id="modal001" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabelSPSP" aria-hidden="true">
            <div class="modal-dialog modal-sm ">
                <div class="modal-content" style=" width: 450px;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" v-on:click="closeModal">&times;</button>
                        <%--<input style="display: none;" @keyup.esc="closeModal">--%>
                    </div>
                    <div class="modal-body" style="height: 150px; text-align: center; font-size: 16px;">
                        <div>
                            <div class="col-md-4 title">输入上班时间</div>
                            <div class="col-md-8 data">
                                <%--<input id="update-start-time" name="startDate"  type="datetime-local" class="form-control" style="cursor: pointer">--%>
                                <input type="text" class="layui-input dateTimeInputInModal" id="update-start-time" placeholder="yyyy-MM-dd HH:mm:ss" lay-key="3">
                            </div>
                        </div>
                        <div style="height: 50px"></div>
                        <div>
                            <div class="col-md-4 title">输入下班时间</div>
                            <div class="col-md-8 data">
                                <%--<input id="update-end-time" name="endDate" type="datetime-local" class="form-control" style="cursor: pointer;">--%>
                                <input type="text" class="layui-input dateTimeInputInModal" id="update-end-time" placeholder="yyyy-MM-dd HH:mm:ss" lay-key="4">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button v-on:click="submitUpdateWorkTime" class="btn btn-primary">提交</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script src="../../static/laydate/laydate.js"></script>
    <script src="../../static/js/html5shiv.min.js"></script>
    <script src="../../static/js/respond.min.js"></script>
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
        $(document).ready(function () {
            $(".layui-input").each(function(){
                laydate.render({
                    elem: this
                    ,type:"datetime"
                    ,trigger:"click"
                });
            })
        })
        var v1 = new Vue({
            el:"#work-time-area",
            data: {
                operateStartTime:"",
                operateEndTime:"",
                allowOperate:false,
                unFinishedWorkTimeList:"",
                operateID:""
            },
            created:function(){
                this.getUnFinishedWorkTime();
            },
            mounted:function(){
                var _this = this;
                document.onkeydown = function(e) {
                    var key = window.event.keyCode;
                    if (key== 27) {
                        window.event.preventDefault() //关闭浏览器快捷键
                        _this.closeModal();
                    }
                };
            },
            methods:{
                submitUpdateWorkTime:function(){
                    var _this = this;
                    if(this.isEmptyString($("#update-start-time").val())||this.isEmptyString($("#update-end-time").val())){
                        alert("开始时间/结束时间不能为空!");
                    }else{
                        this.operateStartTime = $("#update-start-time").val();
                        this.operateEndTime = $("#update-end-time").val();
                        var startTimeFormat = new Date(this.operateStartTime);
                        var endTimeFormat = new Date(this.operateEndTime);
                        var now = new Date();
                        if(startTimeFormat-endTimeFormat >=0){
                            alert("开始时间不能大于结束时间!");
                        }else{
                            this.isTimeConflict(startTimeFormat,endTimeFormat);
                            if(this.allowOperate ==false){
                                alert("选取的工时与已维护的工时冲突!");
                                _this.operateStartTime = "";
                                _this.operateEndTime = "";
                            }else{
                                $.ajax({
                                    url:"${pageContext.request.contextPath }/test/updateWorkTimeOD",
                                    type:"POST",
                                    async:false,
                                    traditional:true,
                                    data:{
                                        startTime: _this.formateDate(startTimeFormat),
                                        endTime: _this.formateDate(endTimeFormat),
                                        workTimeID:_this.operateID
                                    },
                                    success:function(data){
                                        alert("update success!");
                                        _this.operateStartTime = "";
                                        _this.operateEndTime = "";
                                        _this.closeModal();
                                        _this.allowOperate = false;
                                        _this.getUnFinishedWorkTime();
                                    },
                                    error:function(error){
                                        console.log(error);
                                    }
                                });
                                $("#update-start-time").val("");
                                $("#update-end-time").val("");
                            }
                        }
                        _this.operateStartTime = "";
                        _this.operateEndTime = "";
                    }
                },
                closeModal:function(){
                    $("#modal001").hide();
                    this.operateID = "";
                },
                deleteUnFinishedWorkTime:function(workTimeID){
                    var _this = this;
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/deleteUnFinishedWorkTimeOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            workTimeID: workTimeID
                        },
                        success:function(data){
                            alert("delete success!");
                            _this.getUnFinishedWorkTime();
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                updateUnFinishedWorkTime:function(operateID,operateStartTime,operateEndTime){
                    var _this = this;
                    _this.operateID = operateID;
                    $("#modal001").show();
                    //check time
                    $("#update-start-time").val(_this.formateDate(new Date(operateStartTime)));
                    $("#update-end-time").val(_this.formateDate(new Date(operateEndTime)));

                },
                getUnFinishedWorkTime:function(){
                    var _this = this;
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/getUnFinishedWorkTimeOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            nowTime: _this.formateDate(new Date())
                        },
                        success:function(data){
                            _this.unFinishedWorkTimeList = data;
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                isTimeConflict:function(startTime,endTime){
                    var _this = this;
                    $.ajax({
                        url:"${pageContext.request.contextPath }/test/isTimeConflictOD",
                        type:"POST",
                        async:false,
                        traditional:true,
                        data:{
                            startTime: _this.formateDate(startTime),
                            endTime: _this.formateDate(endTime),
                            nowTime: _this.formateDate(new Date()),
                            operateID:_this.operateID
                        },
                        success:function(data){
                            _this.allowOperate = data;
                        },
                        error:function(error){
                            console.log(error);
                        }
                    });
                },
                createWorkTime:function () {
                    var _this = this;
                    if(this.isEmptyString($("#insert-start-time").val())||this.isEmptyString($("#insert-end-time").val())){
                        alert("开始时间/结束时间不能为空!");
                    }else{
                        // 2020-07-27T11:02
                        this.operateStartTime = $("#insert-start-time").val();
                        this.operateEndTime = $("#insert-end-time").val();
                        var startTimeFormat = new Date(this.operateStartTime);
                        var endTimeFormat = new Date(this.operateEndTime);
                        var now = new Date();
                        if(startTimeFormat-endTimeFormat >=0){
                            alert("开始时间不能大于结束时间!");
                        }else if(now >= startTimeFormat){
                            alert("不能维护小于当前时间的工时!");
                        }else{
                            this.isTimeConflict(startTimeFormat,endTimeFormat);
                            if(this.allowOperate ==false){
                                alert("选取的工时与已维护的工时冲突!");
                                _this.operateStartTime = "";
                                _this.operateEndTime = "";
                            }else{
                                $.ajax({
                                    url:"${pageContext.request.contextPath }/test/insertWorkTimeOD",
                                    type:"POST",
                                    async:false,
                                    traditional:true,
                                    data:{
                                        startTime: _this.formateDate(startTimeFormat),
                                        endTime: _this.formateDate(endTimeFormat)
                                    },
                                    success:function(data){
                                        alert("insert success!");
                                        _this.operateStartTime = "";
                                        _this.operateEndTime = "";
                                        _this.allowOperate = false;
                                        _this.getUnFinishedWorkTime();
                                    },
                                    error:function(error){
                                        console.log(error);
                                    }
                                });
                                $("#insert-start-time").val("");
                                $("#insert-end-time").val("");
                            }
                        }
                        _this.operateStartTime = "";
                        _this.operateEndTime = "";
                    }
                },
                formateDate:function(formateDate){
                    // return formateDate.getFullYear()+"-"+(formateDate.getMonth()+1)+"-"+formateDate.getDate()+" "+formateDate.getHours()+":"+(formateDate.getMinutes()==0?"00":formateDate.getMinutes())+":"+(formateDate.getSeconds()==0?"00":formateDate.getSeconds());
                    return this.formatStringWithZero(formateDate.getFullYear())+"-"+this.formatStringWithZero(formateDate.getMonth()+1)+"-"+this.formatStringWithZero(formateDate.getDate())+" "+this.formatStringWithZero(formateDate.getHours())+":"+this.formatStringWithZero(formateDate.getMinutes())+":"+this.formatStringWithZero(formateDate.getSeconds());
                },
                formatStringWithZero:function(fromString){
                    return fromString.toString().length>=2?fromString.toString():"0"+fromString.toString();
                },
                isEmptyString:function (checkString) {
                    return (checkString==""||checkString==false||checkString==null||checkString==undefined);
                }

            }
        })

    </script>
</body>

</html>
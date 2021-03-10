<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- 页面meta -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>工时设定</title>
	<meta name="description" content="AdminLTE2定制版">
	<meta name="keywords" content="AdminLTE2定制版">
	<%--<script src="../static/My97DatePicker/WdatePicker.js"></script>--%>

	<!-- Tell the browser to be responsive to screen width -->
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
	<link rel="stylesheet"
		  href="../static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">

	<!-- 页面头部 -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- 页面头部 /-->
	<!-- 导航侧栏 -->
	<jsp:include page="aside.jsp"></jsp:include>
	<!-- 导航侧栏 /-->

	<!-- 内容区域 -->
	<div class="content-wrapper">


		<form method="post">
			<!-- 正文区域 -->
			<section class="content"> <!--产品信息-->

				<div class="panel panel-default">
					<div class="panel-heading">RR实验室工时设定</div>
					<div class="row data-type">

						<div class="col-md-2 title">输入上班时间</div>
						<div class="col-md-4 data">
							<%--<input type="text" class="form-control" name="gotowork"--%>
								   <%--placeholder="2020-04-07 08:00:00" value="">--%>
								<input id="start-time" name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', el:'start-time', maxDate:'#F{$dp.$D(\'end-time\')}'})" type="text" readonly="readonly" class="form-control" style="cursor: pointer">

						</div>

						<div class="col-md-2 title">输入下班时间</div>
						<div class="col-md-4 data">
							<%--<input type="password" class="form-control" name="getoffwork"--%>
								   <%--placeholder="2020-04-07 17:00:00" value="">--%>
								<input id="end-time" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', el:'end-time', minDate:'#F{$dp.$D(\'start-time\')}'})" type="text" readonly="readonly" class="form-control" style="cursor: pointer;">
						</div>

					</div>
				</div>
				<!--订单信息/--> <!--工具栏-->
				<div class="box-tools text-center">
					<input type="submit" class="btn bg-maroon" value="保存" onclick="return baocun()">

				</div>
				<!--工具栏/--> </section>
			<!-- 正文区域 /-->
		</form>

		<div class="box box-primary">

			<div class="box-body">

				<!-- 数据表格 -->
				<div class="table-box">

					<!--工具栏-->
					<div>
						<%--class="pull-left"--%>
						<tr>

							<div>
								<button style="float: left" type="button" class="btn btn-default" title="刷新"
										onclick="location.href='../workTimeRR/findWorkTimeSPShuaXinRR'" id="B">
									<i class="fa fa-cube"></i> 刷新
								</button>
								<button style="float: left" type="button" class="btn bg-default"
										onclick="location.href='../test/findTestByTestItemRR'">返回
								</button>
								<a>今天向前2天的时间已经隐藏，如需查看，请搜索</a>
								<%--<input id="login" name="" onclick="login()" type="button" class="btn btn-default" value="待排计划">--%>
								<div style="width: 230px;height: 30px;float: right">
									<form action="../workTimeRR/findWorkTimeSPSouSuoRR" method="post">

										<input style="width: 175px;height: 30px; border-radius:3px" type="text"
											   name="gotowork"
											   placeholder="书写格式2020-05-07" value="">
										<button style="width: 55px;height: 30px;float: right;border-radius:3px"
												type="submit">搜索
										</button>

									</form>
								</div>
							</div>

						</tr>
					</div>

		<div class="ccc table table-bordered table-striped table-hover dataTable">
			<table id="dataListAAAA"
				   class="table table-bordered table-striped table-hover dataTable">

				<thead>
				<tr>
					<th class="text-center" >（上班）开始时间</th>
					<th class="text-center">（下班）结束时间</th>
					<th colspan ="2" class="text-center">操作</th>
				</tr>
				</thead>

				<tbody>



				<c:forEach items="${WorkTime}" var="Work" varStatus="cou">

					<tr>
							<%--<td><input name="ids" type="checkbox"></td>--%>
						<td class="text-center"> ${Work.gotoworkStr}</td>
						<td class="text-center">${Work.getoffworkStr}</td>
						<td id="qqq" class="text-center">
							<button type="button" class="btn bg-olive btn-xs"
									onclick="location.href='../workTimeRR/deleteWorkTimeIdRR?id=${Work.id}'">删除</button>
						</td>
						<td class="text-center">
							<button type="submit" class="btn bg-olive btn-xs"  data-toggle="modal" data-target="#myModalB"  onclick="ValuesS('${Work.id}')">修改</button>
							<div class="modal fadeA" id="myModalB" tabindex="-1" role="dialog" aria-labelledby="myModalLabelB" aria-hidden="true">
								<div class="modal-dialog modal-sm " style=" width: 600px;height:500px;" >
									<div class="modal-content" >
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h3 class="modal-title" id="myModalLabelB" style="color: #00a7d0">调整工时</h3>

										</div>
										<form id="formB" action="../workTimeRR/UpdateWorkTimeRR" method="post">
											<div class="row data-type">

												<div class="col-md-2 title">上班时间</div>
												<div class="col-md-4 data">

													<input id="start" name="start"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', el:'start', maxDate:'#F{$dp.$D(\'end\')}'})" type="hidden" readonly="readonly" class="form-control" style="cursor: pointer">
												</div>

												<div class="col-md-2 title">下班时间</div>
												<div class="col-md-4 data">
													<input id="end" name="end" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', el:'end', minDate:'#F{$dp.$D(\'start\')}'})" type="text" readonly="readonly" class="form-control" style="cursor: pointer;">
												</div>

											</div>

											<input name="idB" id="WorkTimeid" type="hidden"  value="">
											<div class="modal-footer">

												<input type="submit" class="btn btn-primary" value="提交更改" onclick="return tijiaogenggai()">

											</div>

										</form>
									</div><!-- /.modal-content -->


								</div><!-- /.modal -->
							</div>
						</td>
					</tr>
				</c:forEach>

				</tbody>


			</table>

			<!--数据列表/-->
		       </div>
				</div>
			</div>
		</div>


		<script
				src="../static/plugins/jQuery/jquery-2.2.3.min.js"></script>

		<script
				src="../static/My97DatePicker/WdatePicker.js"></script>
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
		<script
				src="../static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

		<script>
            $(document).ready(function() {
                // 选择框
                $(".select2").select2();

                // WYSIHTML5编辑器
                $(".textarea").wysihtml5({
                    locale : 'zh-CN'
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

            //跳转到工时维护页面  并自动点击刷新按钮
            window.onload = function() {
                var from = sessionStorage.getItem("from");
                if(from == 'pageA') {
                    $('#B').click()
                    sessionStorage.setItem("from",""); //销毁 from 防止在b页面刷新 依然触发$('#xxx').click()
                }
            }

            $("#myModalB").modal("hide");
            function ValuesS(id) {
                $("#WorkTimeid").val(id);
            }

            function baocun(){
                //  获取到用户输入信
                var startDate= document.getElementById("start-time").value;
                var endDate= document.getElementById("end-time").value;
                //正则表达式 不能为空
                var reg_username=/\S/;
                //判断
                var flag=reg_username.test(startDate);
                var flag=reg_username.test(endDate);
                if(flag==false){
                    alert("输入格式不正确！");
                }else {
                    location.href='../workTimeRR/setRR?startDate=' + startDate + '&endDate=' + endDate;
                }
            };

            function checkNumberABB(){
                //  获取到用户输入信
                var number= document.getElementById("end").value;
                //正则表达式 不能为空
                var reg_username=/\S/;
                //判断
                var flag=reg_username.test(number);
                if(flag==false){
                    alert("输入格式不正确！")
                }
                return flag;
            }

            function tijiaogenggai(){
                return checkNumberABB();
            };
		</script>


</body>

</html>
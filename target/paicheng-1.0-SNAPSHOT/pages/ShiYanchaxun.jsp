<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ page isELIgnored="false"%> <!--支持EL表达式，不设的话，EL表达式不会解析-->
<html>

<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">




<title>实验室排程</title>
    <style>
        .searchdiv{
            width: 216px;
            height: 45px;
            position: relative;
            border-radius:6px;
        }
        .search{
            width:148px;
            height: 25px;
            border-radius:4px;
        }
        .sub{
            position: absolute;
            top:-1px;
            left:153px;
            background-color: #0d6aad;
            color: #c7ddef;
            border-radius:4px;
        }

		.modal.fade.in{
			top:-150px;
		}
		.ccc{
			overflow:auto;
		}
		#shuju{
			width: 100%;
			max-height: 480px;
			overflow: scroll;
		}

    </style>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">




<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">

<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->


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

<div id="divall">
    <!-- loading...... -->
</div>

	<div class="wrapper" >

		<%--<!-- 页面头部 -->--%>
		<jsp:include page="header.jsp"></jsp:include>
		<%--<!-- 页面头部 /-->--%>
		<%--<!-- 导航侧栏 -->--%>
		<jsp:include page="aside.jsp"></jsp:include>
		<!-- 导航侧栏 /-->

		<!-- 内容区域 -->
		<!-- @@master = admin-layout.html-->
		<!-- @@block = content -->

		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
				<h1>
					SP实验室试验执行履历 <small>数据列表</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="../pages/main.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li><a href="#">数据管理</a></li>
					<li class="active">数据列表</li>
				</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 正文区域 -->
			<section class="content">

				<!-- .box-body -->
				<div class="box box-primary">

					<button type="submit" class="btn btn-default" data-toggle="modal" data-target="#myModalBBBBEEE"><i
					class="fa fa-cube"></i>试验完成查询
					</button>

                    <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#myModalQuXiao"><i
                            class="fa fa-cube"></i>试验取消查询
                    </button>


					<button type="button" class="btn btn-default" title="试验计划查询"
					onclick="location.href='../pages/main.jsp'">
					<i class="fa fa-cube"></i> 试验计划查询
					</button>

					<button type="button" class="btn btn-default" title="数据导出"
							onclick="shujudaochuSP()">
						<i class="fa fa-cube"></i> 数据导出
					</button>

					<button type="button" class="btn btn-default" title="返回"
							onclick="history.back(-1);">
						<i class="fa fa-cube"></i> 返回
					</button>

					<div class="box-body">

						<!-- 数据表格 -->
						<div class="table-box">

							<!--数据列表-->
							<div id="shuju" class="ccc table table-bordered table-striped table-hover dataTable">
							<table id="dataList"
								class="table table-bordered table-striped table-hover dataTable">
								<thead>
									<tr>

										<th class="text-center">Order<br>顺位</th>
										<th class="text-center">ExpectedDate<br>排测日期</th>
                                            <th class="text-center"><br>开始时间</th>
                                            <th class="text-center"><br>完成时间</th>
                                            <th class="text-center"><br>取消时间</th>
										<th class="text-center">RequestNo.<br>需求编号</th>
										<th class="text-center">ProjectName<br>项目名称</th>
										<th class="text-center">ProjectEngineer<br>项目工程师</th>
										<th class="text-center">ContactExt<br>电话</th>
										<th class="text-center">PriorityDes<br>紧急程度</th>
										<th class="text-center">SampleID<br>轮胎编号</th>
										<th class="text-center">TestItem<br>试验项目</th>
										<th class="text-center">Standard<br>法规</th>
										<th class="text-center">Size<br>轮胎规格</th>
										<th class="text-center">Pt(kPa)<br>试验气压</th>
										<th class="text-center">Fz(N)<br>试验载荷</th>
										<th class="text-center">Usage Rim.luch<br>试验轮辋</th>
										<th class="text-center">Vr(km/h)<br>试验速度</th>
										<th class="text-center">Camber Angledeg<br>倾斜角</th>
										<th class="text-center">Slip Angledeg<br>滑移角</th>
										<th class="text-center">Pitch Angledeg<br>前后仰角</th>
										<th class="text-center">F&R Angle Deg</th>
										<th class="text-center">Mileage(km)<br>里程</th>
										<th class="text-center">FFT Order</th>
										<th class="text-center">Flat Spot pt(kPa)</th>
										<th class="text-center">Flat Spot Fz(N)</th>
										<th class="text-center">Purpose<br>委试目的</th>
											<%--<th class="text-center">operation<br>操作</th>--%>
									</tr>
								</thead>
								<tbody>


									<c:forEach items="${testList}" var="test" varStatus="cou">

										<tr>
											<%--<td><input name="ids" type="checkbox"></td>--%>
											<td class="text-center">${test.number}</td>
											<td class="text-center">${test.expectedDateStr}</td>

                                                <td class="text-center">${test.expectedDateStr}</td>
                                                <td class="text-center">${test.endTimeStr}</td>
                                                <td class="text-center">${test.cancellationTimeStr}</td>

											<td class="text-center">${test.tyre.cliet.requestId}</td>
											<td class="text-center">${test.tyre.cliet.project}</td>
											<td class="text-center">${test.tyre.cliet.projectEngineer}</td>
												<td class="text-center">${test.tyre.cliet.contactExt}</td>
											<td class="text-center">${test.tyre.cliet.priorityDes}</td>
											<td class="text-center">${test.sampleID}</td>
											<td class="text-center">${test.testItem}</td>
											<td class="text-center">${test.standard}</td>
											<td class="text-center">${test.tyre.size}</td>
											<td class="text-center">${test.pt}</td>
											<td class="text-center">${test.fz}</td>
											<td class="text-center">${test.usageRim}</td>
											<td class="text-center">${test.vr}</td>
											<td class="text-center">${test.CA}</td>
											<td class="text-center">${test.SA}</td>
											<td class="text-center">${test.PA}</td>
											<td class="text-center">${test.FRAngleDeg}</td>
											<td class="text-center">${test.mileage}</td>
											<td class="text-center">${test.FFTOrder}</td>
											<td class="text-center">${test.flatSpotPt}</td>
											<td class="text-center">${test.flatSpotFz}</td>
											<td class="text-center">${test.tyre.cliet.purpose}</td>

												<td class="text-center">
                                                 <%--详情的模态框--%>
                                                <%--<div class="modal fade" id="myModalA" tabindex="-1" role="dialog" aria-labelledby="myModalLabelA" aria-hidden="true">--%>
                                                    <%--<div class="modal-dialog" style="width:972px;">--%>
                                                        <%--<div class="modal-content">--%>
															<%--<div class="modal-header">--%>
																<%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>--%>
																<%--<h3 class="modal-title" class="text-center" id="myModalLabelA" style="color: #0f74a8" >数据详情</h3>--%>
															<%--</div>--%>
															<%--<table id="dataListA"--%>
																   <%--class="table table-bordered table-striped table-hover dataTable">--%>
																<%--<thead>--%>


																	<%--<tr><td colspan="5"><h4 style="color: #0d6aad">实验项目详细数据</h4></td></tr>--%>
																<%--<tr>--%>
																	<%--<th class="text-center">序号<input type="text" class="text-center" placeholder="序号"--%>
																									  <%--id="admini1" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">ID<input type="text" class="text-center" placeholder="id"--%>
																									 <%--id="idAAA" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">试验项目<input type="text" class="text-center" placeholder="TestItem"--%>
																									   <%--id="TestItem" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">样品编号<input type="text" class="text-center" placeholder="sampleID"--%>
																									   <%--id="sampleID" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">试验法规<input type="text" class="text-center" placeholder="Standard"--%>
																									   <%--id="Standard" readonly="readonly" value=""></th>--%>
																<%--</tr>--%>
																<%--<tr>--%>
																	<%--<th class="text-center">试验轮辋<input type="text" class="text-center" placeholder="UsageRim"--%>
																									   <%--id="UsageRim" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">试验风压<input type="text" class="text-center" placeholder="pt"--%>
																									   <%--id="pt" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">试验荷重<input type="text" class="text-center" placeholder="Fz"--%>
																									   <%--id="Fz" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">试验速度<input type="text" class="text-center" placeholder="Vr"--%>
																									   <%--id="Vr" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">倾斜角<input type="text" class="text-center" placeholder="CA"--%>
																									  <%--id="CA" readonly="readonly" value=""></th>--%>
																<%--</tr>--%>
																<%--<tr>--%>
																	<%--<th class="text-center">滑斜角<input type="text" class="text-center" placeholder="SA"--%>
																									  <%--id="SA" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">前后仰角<input type="text" class="text-center" placeholder="PA"--%>
																									   <%--id="PA" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">里程<input type="text" class="text-center" placeholder="Mileage"--%>
																									 <%--id="Mileage" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">备注<input type="text" class="text-center" placeholder="Remark"--%>
																									 <%--id="Remark" readonly="readonly" value=""></th>--%>
																<%--</tr>--%>

																	<%--<tr><td colspan="5"><h4 style="color: #0d6aad">轮胎数据</h4></td></tr>--%>
																<%--<tr>--%>
																	<%--<th class="text-center">ID	<input type="text" class="text-center" placeholder="id"--%>
																										 <%--id="idB" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">轮胎尺寸<input type="text" class="text-center" placeholder="TyreSize"--%>
																									   <%--id="TyreSize" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">花纹<input type="text" class="text-center" placeholder="pattern"--%>
																									 <%--id="pattern" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">荷重指数<input type="text" class="text-center" placeholder="loadIndex"--%>
																									   <%--id="loadIndex" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">速度等级<input type="text" class="text-center" placeholder="speedSymbol"--%>
																									   <%--id="speedSymbol" readonly="readonly" value=""></th>--%>
																<%--</tr>--%>
																<%--<tr>--%>
																	<%--<th class="text-center">SPEC编号<input type="text" class="text-center" placeholder="specNO"--%>
																										 <%--id="specNO" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">Earcode<input type="text" class="text-center" placeholder="Barcode"--%>
																										  <%--id="Barcode" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">产地<input type="text" class="text-center" placeholder="ManufacturingPlace"--%>
																									 <%--id="ManufacturingPlace" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">DOT<input type="text" class="text-center" placeholder="DOT"--%>
																									  <%--id="DOT" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">轮胎重量<input type="text" class="text-center" placeholder="Weight"--%>
																									   <%--id="Weight" readonly="readonly" value=""></th>--%>
																<%--</tr>--%>


																	<%--<tr><td colspan="5"><h4 style="color: #0d6aad">委托人数据</h4></td></tr>--%>
																<%--<tr>--%>
																	<%--<th class="text-center">ID<input type="text" class="text-center" placeholder="id"--%>
																									 <%--id="idC" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">档案编号<input type="text" class="text-center" placeholder="filenumber"--%>
																									   <%--id="filenumber" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">类型<input type="text" class="text-center" placeholder="PriorityDescription"--%>
																									 <%--id="PriorityDescription" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">委试单位<input type="text" class="text-center" placeholder="department"--%>
																									   <%--id="department" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">委试日期<input type="text" class="text-center" placeholder="createddate"--%>
																									   <%--id="createddate" readonly="readonly" value=""></th>--%>
																<%--</tr>--%>
																<%--<tr>--%>
																	<%--<th class="text-center">需求日期<input type="text" class="text-center" placeholder="requireddate"--%>
																									   <%--id="requireddate" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">开发项目<input type="text" class="text-center" placeholder="Project"--%>
																									   <%--id="Project" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">实验目的<input type="text" class="text-center" placeholder="Purpose"--%>
																									   <%--id="Purpose" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">委托人<input type="text" class="text-center" placeholder="ProjectEngineer"--%>
																									  <%--id="ProjectEngineer" readonly="readonly" value=""></th>--%>
																	<%--<th class="text-center">备注	<input type="text" class="text-center" placeholder="Remark"--%>
																										 <%--id="RemarkA" readonly="readonly" value=""></th>--%>
																<%--</tr>--%>

																<%--</thead>--%>
															<%--</table>--%>

                                                        <%--</div><!-- /.modal-content -->--%>


                                                    <%--</div>--%>
                                                <%--</div>--%>

                                                 <%--完成查询模态框--%>

											</td>
										</tr>
									</c:forEach>
									<td class="text-center">
									<div class="modal fadeA" id="myModalBBBBEEE" tabindex="-1" role="dialog"
										 aria-labelledby="myModalLabelBA" aria-hidden="true">
										<div class="modal-dialog modal-sm " style=" width: 600px;height:500px;">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;
													</button>

												</div>
                                                <a>请选择搜索时间范围</a>
												<form id="formASD" action="../test/findByendTime"
													  method="post">
													<div class="row data-type">

														<div class="col-md-2 title">开始时间</div>
														<div class="col-md-4 data">

															<input id="start" name="start"
																   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', el:'start', maxDate:'#F{$dp.$D(\'end\')}'})"
																   type="text" readonly="readonly" class="form-control"
																   style="cursor: pointer">
														</div>

														<div class="col-md-2 title">结束时间</div>
														<div class="col-md-4 data">
															<input id="end" name="end"
																   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', el:'end', minDate:'#F{$dp.$D(\'start\')}'})"
																   type="text" readonly="readonly" class="form-control"
																   style="cursor: pointer;">
														</div>

													</div>

													<input name="idB" id="WorkTimeid" type="hidden" value="">
													<div class="modal-footer">

														<input type="submit" class="btn btn-primary" value="提交"
															   onclick="return tijiaogenggai()">

													</div>

												</form>
											</div><!-- /.modal-content -->

										</div><!-- /.modal -->

									</div>

                                        <%--取消查询模态框--%>
                                        <div class="modal fadeA" id="myModalQuXiao" tabindex="-1" role="dialog"
                                             aria-labelledby="myModalLabelQuXiao" aria-hidden="true">
                                            <div class="modal-dialog modal-sm " style=" width: 600px;height:500px;">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>

                                                    </div>
                                                    <a>请选择搜索时间范围</a>
                                                    <form id="formQuXiao" action="../test/findByCancellationTime"
                                                          method="post">
                                                        <div class="row data-type">

                                                            <div class="col-md-2 title">开始时间</div>
                                                            <div class="col-md-4 data">

                                                                <input id="startQuXiao" name="startQuXiao"
                                                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', el:'startQuXiao', maxDate:'#F{$dp.$D(\'endQuXiao\')}'})"
                                                                       type="text" readonly="readonly" class="form-control"
                                                                       style="cursor: pointer">
                                                            </div>

                                                            <div class="col-md-2 title">结束时间</div>
                                                            <div class="col-md-4 data">
                                                                <input id="endQuXiao" name="endQuXiao"
                                                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', el:'endQuXiao', minDate:'#F{$dp.$D(\'startQuXiao\')}'})"
                                                                       type="text" readonly="readonly" class="form-control"
                                                                       style="cursor: pointer;">
                                                            </div>

                                                        </div>

                                                        <input name="idB" id="QuXiao" type="hidden" value="">
                                                        <div class="modal-footer">

                                                            <input type="submit" class="btn btn-primary" value="提交"
                                                                   onclick="return tijiaogenggaiQuXiao()">

                                                        </div>

                                                    </form>
                                                </div><!-- /.modal-content -->

                                            </div>
                                        </div>
									</td>
								</tbody>
							</table>

								</div>
						</div>

						</div>
						<!-- 数据表格 /-->


					</div>
					<!-- /.box-body -->

					<!-- .box-footer-->
                <%--<div class="box-footer">--%>
                    <%--<div class="pull-left">--%>
                        <%--<div class="form-group form-inline">--%>
							<%--每页--%>
                            <%--<select class="form-control" onchange="changePageSize()" id="changePageSize">--%>
                                <%--<option>1</option>--%>
                                <%--<option>2</option>--%>
                                <%--<option>3</option>--%>
                                <%--<option>4</option>--%>
                                <%--<option>5</option>--%>
                            <%--</select> 条--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="box-tools pull-right">--%>
                        <%--<ul class="pagination">--%>
							<%--<li>--%>
								<%--<a href="../test/findTestByTestItemHSU?page=1&size=${testList.pageSize}" aria-label="Previous">首页</a>--%>
							<%--</li>--%>
							<%--<li><a href="../test/findTestByTestItemHSU?page=${testList.pageNum-1}&size=${testList.pageSize}">上一页</a></li>--%>
							<%--<c:forEach begin="1" end="${testList.pages}" var="pppp">--%>
								<%--<li><a href="../test/findTestByTestItemHSU?page=${pppp}&size=${testList.pageSize}">${pppp}</a></li>--%>
							<%--</c:forEach>--%>
							<%--<li><a href="../test/findTestByTestItemHSU?page=${testList.pageNum+1}&size=${testList.pageSize}">下一页</a></li>--%>
							<%--<li>--%>
								<%--<a href="../test/findTestByTestItemHSU?page=${testList.pages}&size=${testList.pageSize}" aria-label="Next">尾页</a>--%>
							<%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>

                <%--</div>--%>
                <!-- /.box-footer-->



			</section>
			<!-- 正文区域 /-->

	</div>

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
	<script
		src="../static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script
		src="../static/My97DatePicker/WdatePicker.js"></script>
	<script
		src="../static/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script>
		function changePageSize() {
			//获取下拉框的值
			var pageSize = $("#changePageSize").val();

			//向服务器发送请求，改变没页显示条数
			location.href = "../test/?page=1&size="
					+ pageSize;
		}
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

		$(document).ready(function() {

			// 激活导航位置
			setSidebarActive("admin-datalist");

			// 列表按钮 
			$("#dataList td input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				increaseArea : '20%'
			});
			// 全选操作 
			$("#selall").click(function() {
				var clicks = $(this).is(':checked');
				if (!clicks) {
					$("#dataList td input[type='checkbox']").iCheck("uncheck");
				} else {
					$("#dataList td input[type='checkbox']").iCheck("check");
				}
				$(this).data("clicks", !clicks);
			});
		});
		function cdddd(_this) {
            $("#divloading").css("display","inline");
            $("#divall").css("display","inline");
        }


        $("#myModal").modal("hide");
		function Values(ID,id) {
			$("#ids").val(ID);
            $("#id").val(id);
            $("#idss").val(ID);
        }


        function checkNumber(){
            //  获取到用户输入信
            var number= document.getElementById("number").value;
            //正则表达式 长度1到6位
            var reg_username=/^\w{1,6}$/;
            //判断
            var flag=reg_username.test(number);
            if(flag==false){
                alert("输入格式不正确！")
            }
            return flag;
        }
        function checkNumberA(){
            //  获取到用户输入信
            var number= document.getElementById("number").value;
            //正则表达式 不能为0
            var reg_username=/(?!0+$)^\d*$/;
            //判断
            var flag=reg_username.test(number);
            if(flag==false){
                alert("输入格式不正确！")
            }
            return flag;
        }

        function tijiao(){
            return checkNumber()&&checkNumberA();
        };

	//	处理详情请求的事件
        function query (id) {
            $.ajax({
                url:"/test/findById",
                async:true,
                type:"post",
                data:{"id":id},
                success : showQuery,
                dataType : "json"

            });
        }

        function showQuery(all) {
            //转换为json字符串
            var ALL= JSON.stringify(all);
            //转换为json对象
            var obj = JSON.parse(ALL);

            // 显示模态框
            $('#myModalA').modal('show');

             $("#admini1").val(obj.number)
            $("#idAAA").val(obj.id)
            $("#TestItem").val(obj.testItem)
            $("#sampleID").val(obj.sampleID)
            $("#Standard").val(obj.standard)
            $("#UsageRim").val(obj.usageRim)
            $("#pt").val(obj.pt)
            $("#Fz").val(obj.fz)
            $("#Vr").val(obj.vr)
            $("#CA").val(obj.ca)
            $("#SA").val(obj.sa)
            $("#PA").val(obj.pa)
            $("#Mileage").val(obj.mileage)
            $("#Remark").val(obj.remark)


            $("#idB").val(obj.tyre.id)
            $("#TyreSize").val(obj.tyre.tyreSize)
            $("#pattern").val(obj.tyre.pattern)
            $("#loadIndex").val(obj.tyre.loadIndex)
            $("#speedSymbol").val(obj.tyre.speedSymbol)
            $("#specNO").val(obj.tyre.specNO)
            $("#Barcode").val(obj.tyre.barcode)
            $("#ManufacturingPlace").val(obj.tyre.manufacturingPlace)
            $("#DOT").val(obj.tyre.dot)
            $("#Weight").val(obj.tyre.weight)



            $("#idC").val(obj.tyre.cliet.id)
            $("#filenumber").val(obj.tyre.cliet.filenumber)
            $("#PriorityDescription").val(obj.tyre.cliet.priorityDescription)
            $("#department").val(obj.tyre.cliet.department)
            $("#createddate").val(obj.tyre.cliet.createddate)
            $("#requireddate").val(obj.tyre.cliet.requireddate)
            $("#Project").val(obj.tyre.cliet.project)
            $("#Purpose").val(obj.tyre.cliet.purpose)
            $("#ProjectEngineer").val(obj.tyre.cliet.projectEngineer)
            $("#RemarkA").val(obj.tyre.cliet.remark)
        }
        //完成查询
        function checkNumberBB() {
            //  获取到用户输入信
            var number = document.getElementById("start").value;
            //正则表达式 不能为空
            var reg_username = /\S/;
            //判断
            var flag = reg_username.test(number);
            if (flag == false) {
                alert("输入格式不正确！")
            }
            return flag;
        }

        function checkNumberABB() {
            //  获取到用户输入信
            var number = document.getElementById("end").value;
            //正则表达式 不能为空
            var reg_username = /\S/;
            //判断
            var flag = reg_username.test(number);
            if (flag == false) {
                alert("输入格式不正确！")
            }
            return flag;
        }

        function tijiaogenggai() {
            return checkNumberBB() && checkNumberABB();
        };



        //取消查询
        function checkNumberQuXiao() {
            //  获取到用户输入信
            var number = document.getElementById("startQuXiao").value;
            //正则表达式 不能为空
            var reg_username = /\S/;
            //判断
            var flag = reg_username.test(number);
            if (flag == false) {
                alert("输入格式不正确！")
            }
            return flag;
        }

        function checkNumberAQuXiao() {
            //  获取到用户输入信
            var number = document.getElementById("endQuXiao").value;
            //正则表达式 不能为空
            var reg_username = /\S/;
            //判断
            var flag = reg_username.test(number);
            if (flag == false) {
                alert("输入格式不正确！")
            }
            return flag;
        }

        function tijiaogenggaiQuXiao() {
            return checkNumberQuXiao() && checkNumberAQuXiao();
        };




        function shujudaochuSP(){
            var A='${A}'
            if(A==1){
                var testList='${testListA}';
                var form = $("<form>");
                form.attr('style', 'display:none');
                form.attr('target', '');
                form.attr('method', 'post');
                form.attr('action', '${pageContext.request.contextPath}/test/shujudaochuSPWanCheng');

                var input1 = $('<input>');
                input1.attr('type', 'hidden');
                input1.attr('name', 'testList');
                input1.attr('value', testList);      /* JSON.stringify($.serializeObject($('#searchForm'))) */
                $('body').append(form);
                form.append(input1);
                form.submit();
                form.remove();
            }else if(A==2){
                var testList='${testListB}';
                var form = $("<form>");
                form.attr('style', 'display:none');
                form.attr('target', '');
                form.attr('method', 'post');
                form.attr('action', '${pageContext.request.contextPath}/test/shujudaochuSPWanCheng');

                var input1 = $('<input>');
                input1.attr('type', 'hidden');
                input1.attr('name', 'testList');
                input1.attr('value', testList);      /* JSON.stringify($.serializeObject($('#searchForm'))) */
                $('body').append(form);
                form.append(input1);
                form.submit();
                form.remove();
            }
        }

	</script>
</body>

</html>
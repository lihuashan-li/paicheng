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

    </style>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">




<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<!-- Font Awesome -->
<!-- Ionicons -->
<!-- iCheck -->
<!-- Morris chart -->
<!-- jvectormap -->
<!-- Date Picker -->
<!-- Daterange picker -->
<!-- Bootstrap time Picker -->
<!--<link rel="stylesheet" href="../../../plugins/timepicker/bootstrap-timepicker.min.css">-->
<!-- bootstrap wysihtml5 - text editor -->
<!--数据表格-->
<!-- 表格树 -->
<!-- select2 -->
<!-- Bootstrap Color Picker -->
<!-- bootstrap wysihtml5 - text editor -->
<!--bootstrap-markdown-->
<!-- Theme style -->
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<!-- Ion Slider -->
<!-- ion slider Nice -->
<!-- bootstrap slider -->
<!-- bootstrap-datetimepicker -->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->








<!-- jQuery 2.2.3 -->
<!-- jQuery UI 1.11.4 -->
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!-- Bootstrap 3.3.6 -->
<!-- Morris.js charts -->
<!-- Sparkline -->
<!-- jvectormap -->
<!-- jQuery Knob Chart -->
<!-- daterangepicker -->
<!-- datepicker -->
<!-- Bootstrap WYSIHTML5 -->
<!-- Slimscroll -->
<!-- FastClick -->
<!-- iCheck -->
<!-- AdminLTE App -->
<!-- 表格树 -->
<!-- select2 -->
<!-- bootstrap color picker -->
<!-- bootstrap time picker -->
<!--<script src="../../../plugins/timepicker/bootstrap-timepicker.min.js"></script>-->
<!-- Bootstrap WYSIHTML5 -->
<!--bootstrap-markdown-->
<!-- CK Editor -->
<!-- InputMask -->
<!-- DataTables -->
<!-- ChartJS 1.0.1 -->
<!-- FLOT CHARTS -->
<!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
<!-- FLOT PIE PLUGIN - also used to draw donut charts -->
<!-- FLOT CATEGORIES PLUGIN - Used to draw bar charts -->
<!-- jQuery Knob -->
<!-- Sparkline -->
<!-- Morris.js charts -->
<!-- Ion Slider -->
<!-- Bootstrap slider -->
<!-- bootstrap-datetimepicker -->
<!-- 页面meta /-->

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
					数据管理 <small>数据列表</small>
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
										<%--<button type="button" class="btn btn-default" title="新建"--%>
											<%--onclick="location.href='../pages/product-add.jsp'">--%>
											<%--<i class="fa fa-file-o"></i> 新建--%>
										<%--</button>--%>
										<%--<button type="button" class="btn btn-default" title="删除">--%>
											<%--<i class="fa fa-trash-o"></i> 删除--%>
										<%--</button>--%>
										<%--<button type="button" class="btn btn-default" title="开启">--%>
											<%--<i class="fa fa-check"></i> 开启--%>
										<%--</button>--%>
										<%--<button type="button" class="btn btn-default" title="屏蔽">--%>
											<%--<i class="fa fa-ban"></i> 屏蔽--%>
										<%--</button>--%>
										<%--<button type="button" class="btn btn-default" title="筛选">--%>
											<%--<i class="fa fa-refresh"></i> 筛选--%>
										<%--</button>--%>

											<%--<button type="button"  class="btn btn-default"  title="筛选">--%>
												<%--<i class="fa fa-refresh"></i> 筛选--%>
											<%--</button>--%>
											<button type="button" class="btn btn-default"  data-toggle="modal" data-target="#myModalA" onclick="query(${test.id})">筛选</button>
											<button type="button" class="btn btn-default" title="BC"
											 onclick="location.href='../test/findTestByTestItem?page=1&size=8'">
												<i class="fa fa-cube"></i> BC
											</button>
											<button type="button" class="btn btn-default" title="RR"
												onclick="location.href='../test/findTestByTestItemRR?page=1&size=8'">
											<i class="fa fa-cube"></i> RR
											</button>
											<button type="button" class="btn btn-default" title="HSU"
													onclick="location.href='../test/findTestByTestItemHSU?page=1&size=8'">
											<i class="fa fa-cube"></i> HSU
											</button>
											<button type="button" class="btn btn-default" title="3D"
													onclick="location.href='../test/findTestByTestItem3D?page=1&size=8'">
											<i class="fa fa-cube"></i> 3D
											</button>
											<button type="button" class="btn btn-default" title="SP"
													onclick="location.href='../test/findTestByTestItemSP?page=1&size=8'">
												<i class="fa fa-cube"></i> SP
											</button>
											<button type="button" class="btn btn-default" title="返回全部数据"
													onclick="location.href='../test/findAll?page=1&size=8'">
												<i class="fa fa-cube"></i> 返回全部数据
											</button>


									</div>
								</div>
							</div>



							<%--<div class="box-tools pull-right">--%>
								<%--<form action="../test/findByTestRemark" method="post">--%>
								<%--<div class="has-feedback">--%>
									<%--<input type="text" class="form-control input-sm" name="Remark"--%>
										   <%--placeholder="请输入备注关键字搜索" value="">--%>
									<%----%>
								<%--</div>--%>
                                    <%--<span class="glyphicon glyphicon-search form-control-feedback"></span>--%>
                                    <%--<button type="submit" class="btn btn-primary btn-block btn-flat">搜索</button>--%>
								<%--</form>--%>


                            <%--</div>--%>
                            <%--<div class="box-tools pull-right">--%>
                                <%--<form action="../cliet/findByclietEngineer" method="post">--%>
                                    <%--<div class="searchdiv">--%>
                                        <%--<input type="text" class="search" name="Engineer"--%>
                                               <%--placeholder="请输入项目工程师搜索" value="">--%>
                                        <%--&lt;%&ndash;<span class="glyphicon glyphicon-search form-control-feedback"></span>&ndash;%&gt;--%>
                                        <%--<button type="submit" class="sub">搜索</button>--%>
                                    <%--</div>--%>

                                <%--</form>--%>


                            <%--</div>--%>
                            <!--
								</form>
							</div>
							<!--工具栏/-->


							<!--数据列表-->
							<div class="ccc table table-bordered table-striped table-hover dataTable">
							<table id="dataList"
								class="table table-bordered table-striped table-hover dataTable">
								<thead>
									<tr>
										<%--<th class="" style="padding-right: 0px;"><input--%>
											<%--id="selall" type="checkbox" class="icheckbox_square-blue">--%>
										<%--</th>--%>
										<th class="text-center">Order<br>顺位</th>
										<th class="text-center">ExpectedDate<br>排测日期</th>
										<th class="text-center">RequestNo.<br>需求编号</th>
										<th class="text-center">ProjectName<br>项目名称</th>
										<th class="text-center">ProjectEngineer<br>项目工程师</th>
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
											<th class="text-center">operation<br>操作</th>
									</tr>
								</thead>
								<tbody>


									<c:forEach items="${testList}" var="test" varStatus="cou">

										<tr>
											<%--<td><input name="ids" type="checkbox"></td>--%>
											<td class="text-center">${test.number}</td>
											<td class="text-center">${test.expectedDateStr}</td>
											<td class="text-center">${test.tyre.cliet.requestId}</td>
											<td class="text-center">${test.tyre.cliet.project}</td>
											<td class="text-center">${test.tyre.cliet.projectEngineer}</td>
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
                                                <%--onclick="location.href='../tyre/findByTyreIdA?id=${test.tyre.id}'"--%>
												<%--<button type="button" class="btn bg-olive btn-xs"  data-toggle="modal" data-target="#myModalA" onclick="query(${test.id})">详情</button>--%>
												<button type="submit" class="btn bg-olive btn-xs"  data-toggle="modal" data-target="#myModal"  onclick="Values(${test.number},${test.id})">操作</button>
												<!-- 操作模态框（Modal） -->
												<div class="modal fadeA" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog modal-sm " style=" width: 350px;height:350px;" >
														<div class="modal-content" >
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
																<h3 class="modal-title" id="myModalLabel" style="color: #00a7d0">调整实验数据顺序</h3>

                                                            </div>
                                                            <form id="form" action="../test/TestNumber" method="post">
                                                                <h4>当前该实验是第<input name="ids" id="idss" type="button"  readonly="readonly" value="">位</h4>
                                                                <h4>请输入需要把该数据调整到第几位：</h4>
                                                            <input type="text" class="text-center" name="number" id="number"
                                                                   placeholder="输入序号数字" style="border-radius: 4px" value="">

                                                                <input name="id" id="id" type="hidden"  value="">
																<input name="ids" id="ids" type="hidden" value="">

                                                            <div class="modal-footer">
                                                                <%--<button  class="btn btn-primary" onclick="ale()">提交更改</button>--%>
                                                                <%--<button type="submit" class="btn btn-primary" >提交更改</button>--%>
                                                                    <input type="submit" class="btn btn-primary" value="提交更改" onclick="return tijiao()">

                                                            </div>

                                                        </form>
														</div><!-- /.modal-content -->


													</div><!-- /.modal -->
												</div>
                                                 <%--筛选模态框--%>
                                                <div class="modal fade" id="myModalA" tabindex="-1" role="dialog" aria-labelledby="myModalLabelA" aria-hidden="true">
                                                    <div class="modal-dialog" style="width:972px;">
                                                        <div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
																<h3 class="modal-title" class="text-center" id="myModalLabelA" style="color: #0f74a8" >数据筛选</h3>
															</div>
															<form id="formA" action="../test/findByXuan?page=1&size=1" method="post">
															<table id="dataListA"
																   class="table table-bordered table-striped table-hover dataTable">
																<thead>
                                                                     <%--<form id="formA" action="../test/findByXuan" method="post">--%>
																		 <tr>
																			 <td  rowspan="2" style="background-color: #B5D1D8">
																				 <h3>TestItem</h3>
																			 </td>
																			 <td>
																				 <input name="Fruit" type="checkbox" value="BC" /><label>BC</label>
																			 </td>
																			 <td>
																				 <input name="Fruit" type="checkbox" value="RR" /><label>RR</label>
																			 </td>
																			 <td>
																				 <input name="Fruit" type="checkbox" value="RPK" /><label>RPK</label>
																			 </td>
																			 <td><input name="Fruit" type="checkbox" value="CDRR" /><label>CDRR</label></td>
																			 <td><input name="Fruit" type="checkbox" value="HSU" /><label>HSU</label></td>
																			 <td><input name="Fruit" type="checkbox" value="LSU" /><label>LSU</label></td>
																			 <td><input name="Fruit" type="checkbox" value="Flatspot" /><label>Flatspot</label></td>
																		 </tr>
																		 <tr>
																			 <td><input name="Fruit" type="checkbox" value="3D(Loaded)" /><label>3D(Loaded)</label></td>
																			 <td><input name="Fruit" type="checkbox" value="3D(Unload)" /><label>3D(Unload)</label></td>
																			 <td><input name="Fruit" type="checkbox" value="3D(Wear)" /><label>3D(Wear)</label></td>
																			 <td><input name="Fruit" type="checkbox" value="3D(Rim)" /><label>3D(Rim)</label></td>
																			 <td><input name="Fruit" type="checkbox" value="3D(NonRim)" /><label>3D(NonRim)</label></td>
																			 <td><input name="Fruit" type="checkbox" value="Footprint" /><label>Footprint</label></td>
																		 </tr>

																	 <tr>
																		 <td colspan ="7"><input type="hidden"  class="btn btn-primary" value="提交更改"></td>

																		 <td><input type="submit"  class="btn btn-primary" value="提交更改"></td>
																	 </tr>
																	 <%--</form>--%>
																</thead>
															</table>
															</form>
                                                        </div><!-- /.modal-content -->


                                                    </div>
                                                </div>
											</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
								<!--
                            <tfoot>
                            <tr>
                            <th>Rendering engine</th>
                            <th>Browser</th>
                            <th>Platform(s)</th>
                            <th>Engine version</th>
                            <th>CSS grade</th>
                            </tr>
                            </tfoot>-->
							</table>
							<!--数据列表/-->
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
								<%--<a href="../test/findByXuan?page=1&size=${testList.pageSize}" aria-label="Previous">首页</a>--%>
							<%--</li>--%>
							<%--<li><a href="../test/findByXuan?page=${testList.pageNum-1}&size=${testList.pageSize}">上一页</a></li>--%>
							<%--<c:forEach begin="1" end="${testList.pages}" var="pppp">--%>
								<%--<li><a href="../test/findByXuan?page=${pppp}&size=${testList.pageSize}">${pppp}</a></li>--%>
							<%--</c:forEach>--%>
							<%--<li><a href="../test/findByXuan?page=${testList.pageNum+1}&size=${testList.pageSize}">下一页</a></li>--%>
							<%--<li>--%>
								<%--<a href="../test/findByXuan?page=${testList.pages}&size=${testList.pageSize}" aria-label="Next">尾页</a>--%>
							<%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>

                <%--</div>--%>
                <!-- /.box-footer-->



			</section>
			<!-- 正文区域 /-->

	</div>
		<%--<!-- @@close -->--%>
		<%--<!-- 内容区域 /-->--%>

		<%--<!-- 底部导航 -->--%>
		<%--<footer class="main-footer">--%>
			<%--<div class="pull-right hidden-xs">--%>
				<%--<b>Version</b> 1.0.8--%>
			<%--</div>--%>
			<%--<strong>Copyright &copy; 2014-2017 <a--%>
				<%--href="http://www.itcast.cn">研究院研发部</a>.--%>
			<%--</strong> All rights reserved.--%>
		<%--</footer>--%>
		<%--<!-- 底部导航 /-->--%>

	<%--</div>--%>


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
		src="../static/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script>
		function changePageSize() {
			//获取下拉框的值
			var pageSize = $("#changePageSize").val();

			//向服务器发送请求，改变没页显示条数
			location.href = "../test/findByXuan?page=1&size="
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

	// //	处理详情请求的事件
     //    function query (id) {
     //        $.ajax({
     //            url:"/test/findById",
     //            async:true,
     //            type:"post",
     //            data:{"id":id},
     //            success : showQuery,
     //            dataType : "json"
    //
     //        });
     //    }
    //
     //    function showQuery(all) {
     //        //转换为json字符串
     //        var ALL= JSON.stringify(all);
     //        //转换为json对象
     //        var obj = JSON.parse(ALL);
    //
     //        // 显示模态框
     //        $('#myModalA').modal('show');
    //
     //         $("#admini1").val(obj.number)
     //        $("#idAAA").val(obj.id)
     //        $("#TestItem").val(obj.testItem)
     //        $("#sampleID").val(obj.sampleID)
     //        $("#Standard").val(obj.standard)
     //        $("#UsageRim").val(obj.usageRim)
     //        $("#pt").val(obj.pt)
     //        $("#Fz").val(obj.fz)
     //        $("#Vr").val(obj.vr)
     //        $("#CA").val(obj.ca)
     //        $("#SA").val(obj.sa)
     //        $("#PA").val(obj.pa)
     //        $("#Mileage").val(obj.mileage)
     //        $("#Remark").val(obj.remark)
    //
    //
     //        $("#idB").val(obj.tyre.id)
     //        $("#TyreSize").val(obj.tyre.tyreSize)
     //        $("#pattern").val(obj.tyre.pattern)
     //        $("#loadIndex").val(obj.tyre.loadIndex)
     //        $("#speedSymbol").val(obj.tyre.speedSymbol)
     //        $("#specNO").val(obj.tyre.specNO)
     //        $("#Barcode").val(obj.tyre.barcode)
     //        $("#ManufacturingPlace").val(obj.tyre.manufacturingPlace)
     //        $("#DOT").val(obj.tyre.dot)
     //        $("#Weight").val(obj.tyre.weight)
    //
    //
    //
     //        $("#idC").val(obj.tyre.cliet.id)
     //        $("#filenumber").val(obj.tyre.cliet.filenumber)
     //        $("#PriorityDescription").val(obj.tyre.cliet.priorityDescription)
     //        $("#department").val(obj.tyre.cliet.department)
     //        $("#createddate").val(obj.tyre.cliet.createddate)
     //        $("#requireddate").val(obj.tyre.cliet.requireddate)
     //        $("#Project").val(obj.tyre.cliet.project)
     //        $("#Purpose").val(obj.tyre.cliet.purpose)
     //        $("#ProjectEngineer").val(obj.tyre.cliet.projectEngineer)
     //        $("#RemarkA").val(obj.tyre.cliet.remark)
     //    }


		function fun(){
			obj=document.getElementsByName("Fruit");
			check_val = [];
			for (k in obj){
			if (obj[k].checked)
			    check_val.push(obj[k].value);
			}

			alert(check_val);

        }




	</script>
</body>

</html>
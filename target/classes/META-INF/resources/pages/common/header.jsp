<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- 页面头部 -->
<header class="main-header">
	<!-- Logo -->
	<%--href="all-admin-index.html"--%>
	<a class="logo" style="background-color: #005686;font-size: 18px"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>信息</b></span> <!-- logo for regular state and mobile devices -->
		<span>
			<%--class="logo-lg"--%>
			<img
				src="../../static/img/PLM.png">
			<b>研发信息管理平台</b></span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" style="background-color: #005686">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<%--<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>--%>
					<%--<span>系统管理</span> <span class="pull-right-container"> <i--%>
							<%--class="fa fa-angle-left pull-right"></i>--%>
				<%--</span>--%>


				<%--</a>--%>
					<%--<ul class="treeview-menu">--%>

						<%--<li id="system-setting"><a--%>
								<%--href="../../user/findAll"> <i--%>
								<%--class="fa fa-circle-o"></i> 用户管理--%>
						<%--</a></li>--%>
						<%--<li id="system-setting"><a--%>
								<%--href="../../role/findAll"> <i--%>
								<%--class="fa fa-circle-o"></i> 角色管理--%>
						<%--</a></li>--%>
						<%--<li id="system-setting"><a--%>
								<%--href="../../permission/findAll">--%>
							<%--<i class="fa fa-circle-o"></i> 资源权限管理--%>
						<%--</a></li>--%>
					<%--</ul></li>--%>


				<li class="dropdown user user-menu"><a href="#"
													   class="dropdown-toggle" data-toggle="dropdown"> <span class="hidden-xs">
							系统管理
					</span>

				</a>
					<ul class="dropdown-menu"style="background-color: #005686">
						<!-- User image -->
						<%--<li class="user-header"><img--%>
								<%--src="../../static/img/user2-160x160.jpg"--%>
								<%--class="img-circle" alt="User Image"></li>--%>
						<li id="system-setting"><a
						href="../../user/findAll"> <i
						class="fa fa-circle-o"></i> 用户管理
						</a></li>
						<li id="system-setting"><a
						href="../../role/findAll"> <i
						class="fa fa-circle-o"></i> 角色管理
						</a></li>
							<li id="system-setting"><a
							href="../../permission/findAll">
							<i class="fa fa-circle-o"></i> 资源权限管理
							</a></li>


					</ul></li>





				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="../../static/img/user2-160x160.jpg"
						class="user-image" alt="User Image"> <span class="hidden-xs">
							你好！<shiro:principal property="username"/>
					</span>

				</a>
					<ul class="dropdown-menu" >
						<!-- User image -->
						<li class="user-header"><img
							src="../../static/img/user2-160x160.jpg"
							class="img-circle" alt="User Image"></li>

						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="#" class="btn btn-default btn-flat">修改密码</a>
							</div>
							<div class="pull-right">
								<a href="../../user/logout"
									class="btn btn-default btn-flat">注销</a>
							</div>
						</li>
					</ul></li>

			</ul>
		</div>
	</nav>
</header>
<!-- 页面头部 /-->
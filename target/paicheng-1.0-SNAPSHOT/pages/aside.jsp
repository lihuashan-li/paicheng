<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>

<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <%--<div class="user-panel">--%>
        <%--<div class="pull-left image">--%>
            <%--<img src="../static/img/MAXXIS lOGO.jpg" alt="User Image">--%>
        <%--</div>--%>
        <%--</div>--%>

        <div class="user-panel">
            <div class="pull-left image">
                <img src="../static/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>xxx</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <%--<li class="header">菜单</li>--%>
            <li id="admin-index"><a
                    href="../pages/main.jsp"><i
                    class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <%--<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>--%>
            <%--<span>系统管理</span> <span class="pull-right-container"> <i--%>
            <%--class="fa fa-angle-left pull-right"></i>--%>
            <%--</span>--%>


            <%--</a>--%>
            <%--<ul class="treeview-menu">--%>

            <%--<li id="system-setting"><a--%>
            <%--href="../user/findAll"> <i--%>
            <%--class="fa fa-circle-o"></i> 用户管理--%>
            <%--</a></li>--%>
            <%--<li id="system-setting"><a--%>
            <%--href="../role/findAll"> <i--%>
            <%--class="fa fa-circle-o"></i> 角色管理--%>
            <%--</a></li>--%>
            <%--<li id="system-setting"><a--%>
            <%--href="../permission/findAll">--%>
            <%--<i class="fa fa-circle-o"></i> 资源权限管理--%>
            <%--</a></li>--%>
            <%--</ul></li>--%>

            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>实验室管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="../pages/main.jsp"> <i
                            class="fa fa-circle-o"></i> 质控管理
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 实验室信息
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 委试排程
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 管理员信息
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 使用者信息
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 使用记录
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 使用统计
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 试验报告
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 试验记录
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 数据分析
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 标准/资质
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 安全巡检
                    </a></li>


                </ul>
            </li>


            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>委托管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">

                    <li id="system-setting"><a
                            href="../test/findTestByTestItemSP?page=1&size=8">
                        <i class="fa fa-circle-o"></i> Static Footprint Tread
                    </a></li>
                    <li id="system-setting"><a
                            href="../test/findTestByTestItemSStiffness?page=1&size=8">
                        <i class="fa fa-circle-o"></i> Static Stiffness
                    </a></li>

                    <li id="system-setting"><a
                            href="../test/findTestByTestItemRR?page=1&size=8">
                        <i class="fa fa-circle-o"></i> Rolling Resistance 1.7m
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> Rolling Resistance 2.0m
                    </a></li>
                    <li id="system-setting"><a
                            href="../test/findTestByTestItemHSU?page=1&size=8">
                        <i class="fa fa-circle-o"></i> High Speed Uniformity 1.7m
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> High Speed Uniformity 2.0m
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> Force and Moment SS
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> Force and Moment CT
                    </a></li>
                    <li id="system-setting"><a
                            href="../test/findTestByTestItem3D?page=1&size=8">
                        <i class="fa fa-circle-o"></i> Static Scan
                    </a></li>
                    <li id="system-setting"><a
                            href="../test/findTestByTestItemBC?page=1&size=8">
                        <i class="fa fa-circle-o"></i> Bead Compression
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> Static Footprint Bead
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> High Speed Linear Friction
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> Infrared Thermal Camera
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> Digital Image Correlation
                    </a></li>
                    <li id="system-setting">
                        <%--< a href=" ">--%>
                        <a href="../pages/ODPages/ODMainPage.jsp">
                            <i class="fa fa-circle-o"></i> Outdoor
                        </a>
                    </li>


                </ul>
            </li>


            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>委托查询</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="../pages/WeiTuoChaXun.jsp"> <i
                            class="fa fa-circle-o"></i> 实验室委托查询
                    </a></li>
                </ul>
            </li>


            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>耗材管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="../pages/main.jsp"> <i
                            class="fa fa-circle-o"></i> 耗材信息
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 耗材盘点
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 补货清单
                    </a></li>

                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 补货汇总
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 用量统计
                    </a></li>


                </ul>
            </li>


            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>设备管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="../pages/main.jsp"> <i
                            class="fa fa-circle-o"></i> 仪器登记
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 状态维护
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 保养记录
                    </a></li>

                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 校准记录
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 维修记录
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 报废记录
                    </a></li>

                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 维护提醒
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 统计查询
                    </a></li>


                </ul>
            </li>


            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>固定资产</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="../pages/main.jsp"> <i
                            class="fa fa-circle-o"></i> 新增登记
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 领用变更
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 借用归还
                    </a></li>

                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 维修保养
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 折旧报废
                    </a></li>


                </ul>
            </li>


            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>仓库管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="../pages/main.jsp"> <i
                            class="fa fa-circle-o"></i> 产品入库
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 产品出库
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 产品库存
                    </a></li>

                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 库存预警
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 库存盘点
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 进出库汇总
                    </a></li>

                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 入库统计
                    </a></li>
                    <li id="system-setting"><a
                            href="../pages/main.jsp">
                        <i class="fa fa-circle-o"></i> 出库统计
                    </a></li>


                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
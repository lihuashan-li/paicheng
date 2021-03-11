<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--输出,条件,迭代标签库-->
<%@ page isELIgnored="false" %>
<!--支持EL表达式，不设的话，EL表达式不会解析-->
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <title>实验室排程</title>
    <style>
        .searchdiv {
            width: 216px;
            height: 45px;
            position: relative;
            border-radius: 6px;
        }

        .search {
            width: 148px;
            height: 25px;
            border-radius: 4px;
        }

        .sub {
            position: absolute;
            top: -1px;
            left: 153px;
            background-color: #0d6aad;
            color: #c7ddef;
            border-radius: 4px;
        }

        .modal.fade.in {
            top: -150px;
        }

        .ccc {
            overflow: auto;
        }

        #triesred1 {
            background-color: #FF7A7A;

        }

        #wancheng0 {
            Visibility: hidden;
        }

        #wancheng1 {
            Visibility: visible;
        }

        #caozuo1 {
            Visibility: hidden;
        }

        #duoxuan10 {
            Visibility: hidden;
        }

        #duoxuan01 {
            Visibility: hidden;
        }

        #daipai {
            width: 100%;
            max-height: 330px;
            overflow: scroll;
        }

        #quxiaokaishi0 {
            Visibility: hidden;
        }

        #quxiaokaishi1 {
            Visibility: visible;
        }


    </style>
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
    <link rel="stylesheet"
          href="../static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">

<div id="divall">

</div>

<div class="wrapper">

    <%--<!-- 页面头部 -->--%>
    <jsp:include page="header.jsp"></jsp:include>
    <%--<!-- 页面头部 /-->--%>
    <%--<!-- 导航侧栏 -->--%>
    <jsp:include page="aside.jsp"></jsp:include>


    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                SP实验室数据管理
                <small>数据列表</small>
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
                <button type="button" class="btn btn-default" title="已排刷新"
                        onclick="location.href='../test/findTestByTestItemSP?page=1&size=8'">
                    <i class="fa fa-cube"></i> 已排刷新
                </button>

                <shiro:hasPermission name="SP工时维护">
                    <button type="button" class="btn btn-default" title="工时维护"
                            onclick="location.href='../pages/setSP.jsp'" id="SPA">
                        <i class="fa fa-cube"></i> 工时维护
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="SP排程更新">
                    <button type="button" class="btn btn-default" title="排程更新"
                            onclick="location.href='../test/renovateSP'">
                        <i class="fa fa-cube"></i> 排程更新
                    </button>
                </shiro:hasPermission>

                <button type="button" class="btn btn-default" title="试验查询"
                        onclick="location.href='../pages/ShiYanchaxun.jsp'">
                    <i class="fa fa-cube"></i> 试验查询
                </button>

                <shiro:hasPermission name="SP已排批量操作">
                    <button type="submit" id="submit" class="btn btn-default" title="批量操作" class="btn bg-olive btn-xs"
                            data-toggle="modal" data-target="#myModalSPPiLiang" onclick="pilianganniu()">
                        <i class="fa fa-cube"></i> 批量操作
                    </button>
                </shiro:hasPermission>

                <button type="button" class="btn btn-default" title="数据导出"
                        onclick="location.href='../test/ShuJuDaoChuSP'">
                    <i class="fa fa-cube"></i> 数据导出
                </button>

                <button type="button" class="btn btn-default" title="返回"
                        onclick="location.href='../pages/main.jsp'">
                    <i class="fa fa-cube"></i> 返回
                </button>


                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--数据列表-->
                        <div class="ccc table table-bordered table-striped table-hover dataTable">
                            <table id="dataList"
                                   class="table table-bordered table-striped table-hover dataTable">
                                <thead>
                                <tr>
                                    <th class="text-center"><br>选择</th>
                                    <th class="text-center">Order<br>顺位</th>
                                    <th class="text-center">ExpectedDate<br>排测日期</th>
                                    <th class="text-center">RequestNo.<br>需求编号</th>
                                    <th class="text-center">RequiredTime<br>需求日期</th>
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
                                    <%--<th class="text-center">Vr(km/h)<br>试验速度</th>--%>
                                    <th class="text-center">Camber Angledeg<br>倾斜角</th>
                                    <%--<th class="text-center">Slip Angledeg<br>滑移角</th>--%>
                                    <th class="text-center">Pitch Angledeg<br>前后仰角</th>
                                    <%--<th class="text-center">Mileage(km)<br>里程</th>--%>
                                    <%--<th class="text-center">FFT Order</th>--%>
                                    <%--<th class="text-center">Flat Spot pt(kPa)</th>--%>
                                    <%--<th class="text-center">Flat Spot Fz(N)</th>--%>
                                    <th class="text-center">Purpose<br>委试目的</th>
                                    <th class="text-center">sampleDisposal<br>样品处理</th>
                                    <th class="text-center">Target<br>目标</th>
                                    <th colspan="5" class="text-center">operation<br>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--<from  >--%>
                                <c:forEach items="${testList.list}" var="test" varStatus="cou">

                                    <tr id="triesred${test.red}" onclick="ValuesAAA('${test.id}')">

                                        <td><input id="duoxuan${test.spbh}${test.red}" name="idsClass" type="checkbox"
                                                   style="zoom:120%" idA="${test.id}" number="${test.number}"
                                                   caozuo="${test.caozuo}" onclick="aaaa(this)"></td>
                                        <td class="text-center">${test.number}</td>
                                        <td class="text-center">${test.expectedDateStr}</td>
                                        <td class="text-center">${test.tyre.cliet.requestId}</td>
                                        <td class="text-center">${test.tyre.cliet.requestTimeStr}</td>
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
                                        <%--<td class="text-center">${test.vr}</td>--%>
                                        <td class="text-center">${test.CA}</td>
                                        <%--<td class="text-center">${test.SA}</td>--%>
                                        <td class="text-center">${test.PA}</td>
                                        <%--<td class="text-center">${test.mileage}</td>--%>
                                        <%--<td class="text-center">${test.FFTOrder}</td>--%>
                                        <%--<td class="text-center">${test.flatSpotPt}</td>--%>
                                        <%--<td class="text-center">${test.flatSpotFz}</td>--%>
                                        <td class="text-center">${test.tyre.cliet.purpose}</td>
                                        <td class="text-center">${test.tyre.cliet.sampleDisposal}</td>
                                        <td class="text-center">${test.tyre.cliet.target}</td>
                                        <td class="text-center">
                                            <shiro:hasPermission name="SP开始">
                                                <button type="button" class="btn bg-olive btn-xs"
                                                        onclick="kaishi(this)">开始
                                                </button>
                                            </shiro:hasPermission>
                                        </td>

                                        <td class="text-center" id="wancheng${test.spbh}">
                                            <shiro:hasPermission name="SP完成">
                                                <button id="button" type="button" class="btn bg-olive btn-xs"
                                                        onclick="wancheng('${test.id}',${test.number})">完成
                                                </button>
                                            </shiro:hasPermission>
                                        </td>

                                        <td class="text-center">
                                            <shiro:hasPermission name="SP操作">
                                                <button type="submit" class="btn bg-olive btn-xs" data-toggle="modal"
                                                        data-target="#myModalSP"
                                                        onclick="Values(${test.number},'${test.id}',${test.spbh},${test.red},${test.caozuo})">
                                                    操作
                                                </button>
                                            </shiro:hasPermission>
                                        </td>
                                        <td class="text-center">
                                            <shiro:hasPermission name="SP取消">
                                                <button id="buttonA" type="button" class="btn bg-olive btn-xs"
                                                        <%--onclick="location.href='../test/updateCancelSP?id=${test.id}&number=${test.number}'">--%>
                                                        onclick="ValuesQuXiaoSP('${test.id}',${test.number})">
                                                    案件取消
                                                </button>
                                            </shiro:hasPermission>
                                        </td>
                                        <td class="text-center" id="quxiaokaishi${test.spbh}">
                                                <%--<shiro:hasPermission name="SP完成">--%>
                                            <button id="button" type="button" class="btn bg-olive btn-xs"
                                                    onclick="quxiaokaishiSP()">取消开始
                                            </button>
                                                <%--</shiro:hasPermission>--%>
                                        </td>

                                    </tr>
                                </c:forEach>

                                <!-- 操作模态框（Modal） -->
                                <div class="text-center">
                                    <div class="modal fadeA" id="myModalSP" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabelSP" aria-hidden="true">
                                        <div class="modal-dialog modal-sm " style=" width: 350px;height:350px;">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">&times;
                                                    </button>
                                                    <h3 class="modal-title" id="myModalLabel" style="color: #00a7d0">
                                                        调整实验数据顺序</h3>

                                                </div>
                                                <form id="form" action="../test/TestNumberSPP" method="post">
                                                    <h4>当前该实验是第<input name="ids" id="idss" type="button"
                                                                      readonly="readonly" value="">位</h4>
                                                    <h4>请输入需要把该数据调整到第几位：</h4>
                                                    <input type="text" class="text-center" name="number" id="number"
                                                           placeholder="输入序号数字" style="border-radius: 4px" value="">

                                                    <input name="id" id="id" type="hidden" value="">
                                                    <input name="ids" id="ids" type="hidden" value="">
                                                    <input name="spbh" id="spbh" type="hidden" value="">
                                                    <input name="red" id="red" type="hidden" value="">
                                                    <input name="caozuo1" id="caozuo1" type="hidden" value="">


                                                    <div class="modal-footer">

                                                        <input type="submit" class="btn btn-primary" value="提交更改"
                                                               onclick="return tijiao()">

                                                    </div>

                                                </form>
                                                <form id="formAAA" action="../abnormal/saveAbnormal" method="post">
                                                    <h4>请输入试验暂停原因：</h4>
                                                    <input type="text" class="text-center" name="remarks" id="numberAA"
                                                           placeholder="输入暂停原因" style="border-radius: 4px" value="">

                                                    <input name="id" id="idID" type="hidden" value="">
                                                    <input name="spbh" id="spbh01" type="hidden" value="">
                                                    <div class="modal-footer">
                                                        <input type="submit"

                                                               class="btn btn-primary" value="暂停"
                                                               onclick="return zanting()">
                                                    </div>

                                                </form>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal -->
                                    </div>
                                    <%--错误详细原因模态框--%>
                                    <div class="modal fadeA" id="myModalAbnormal" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabelSPSP" aria-hidden="true">
                                        <div class="modal-dialog modal-sm ">
                                            <div class="modal-content" style=" width: 450px;height:150px;">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">&times;
                                                    </button>
                                                    <h3 class="modal-title" id="myModal" style="color: #00a7d0">
                                                        当前试验暂停的原因是：</h3>

                                                    <input type="text" style="width: 400px" class="text-center"
                                                           placeholder="原因" id="admini1AS" readonly="readonly" value="">

                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal -->
                                    </div>


                                    <%--批量操作模态框--%>
                                    <div class="modal fadeA" id="myModalSPPiLiang" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabelSPPiLiang" aria-hidden="true">
                                        <div class="modal-dialog modal-sm " style=" width: 350px;height:350px;">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">&times;
                                                    </button>
                                                    <h3 class="modal-title" id="myModalLabelPiLiang"
                                                        style="color: #00a7d0">调整实验数据顺序</h3>

                                                </div>
                                                <form id="formPiLiang" action="../test/PiLiangTestNumberSP"
                                                      method="post">
                                                    <h4>当前选中实验是第<input id="PiLiangnumberSS" type="button"
                                                                       readonly="readonly" value="">位</h4>
                                                    <h4>请输入需要把选中的实验调整到第几位：</h4>
                                                    <input type="text" class="text-center" name="numberS"
                                                           id="piliangshuru"
                                                           placeholder="输入序号数字" style="border-radius: 4px" value="">

                                                    <input name="id" id="PiLiangid" type="hidden" value="">
                                                    <input name="number" id="PiLiangnumber" type="hidden" value="">
                                                    <input name="caozuo" id="caozuo" type="hidden" value="">

                                                    <div class="modal-footer">

                                                        <input type="submit" class="btn btn-primary" value="提交更改"
                                                               onclick="return piliangtijiao()">

                                                    </div>

                                                </form>

                                                <form action="../test/JinRuDaiPai" method="post">

                                                    <h4>把选中的实验取消已排，进入待排</h4>

                                                    <input name="id" id="PiLiangidQu" type="hidden" value="">
                                                    <input name="number" id="PiLiangnumberQu" type="hidden" value="">

                                                    <div class="modal-footer">

                                                        <input type="submit" class="btn btn-primary" value="提交更改"
                                                               onclick="return JinRuDaiPai()">

                                                    </div>
                                                </form>

                                                <form action="../test/PiLiangQuXiaoYiPai" method="post">

                                                    <h4>取消选中的实验</h4>

                                                    <input name="QuXiaoidYiPai" id="PiLiangidQuXiaoYiPai" type="hidden"
                                                           value="">
                                                    <input name="QuXiaonumberYiPai" id="PiLiangnumberQuXiaoYiPai"
                                                           type="hidden" value="">

                                                    <div class="modal-footer">

                                                        <input type="submit" class="btn btn-primary" value="案件取消"
                                                               onclick="return YiPaiPiLiangQuXiao()">

                                                    </div>
                                                </form>


                                            </div><!-- /.modal-content -->

                                        </div><!-- /.modal -->
                                    </div>

                                </tbody>
                            </table>

                            </table>
                            <!--数据列表/-->
                        </div>
                    </div>

                </div>
                <!-- 数据表格 /-->

            </div>

            <div style="height: 65px" class="box-footer">
                <div style="float: right">
                    <ul STYLE="margin:5px" class="pagination">
                        <li><a>已排共计${count}条数据</a></li>
                        <li>
                            <a href="../test/findTestByTestItemSP?page=1&size=${testList.pageSize}"
                               aria-label="Previous">首页</a>
                        </li>
                        <li><a href="../test/findTestByTestItemSP?page=${testList.pageNum-1}&size=${testList.pageSize}">上一页</a>
                        </li>
                        <c:forEach begin="1" end="${testList.pages}" var="pppp">
                            <li>
                                <a href="../test/findTestByTestItemSP?page=${pppp}&size=${testList.pageSize}">${pppp}</a>
                            </li>
                        </c:forEach>
                        <li><a href="../test/findTestByTestItemSP?page=${testList.pageNum+1}&size=${testList.pageSize}">下一页</a>
                        </li>
                        <li>
                            <a href="../test/findTestByTestItemSP?page=${testList.pages}&size=${testList.pageSize}"
                               aria-label="Next">尾页</a>
                        </li>

                    </ul>
                </div>

            </div>

        </section>
        <!-- 正文区域 /-->
        <section class="content">

            <!-- .box-body -->
            <div class="box box-primary">

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div>
                            <tr>
                                <div>
                                    <button type="button" class="btn btn-default" title="待排刷新" id="btn01"
                                            onclick="location.href='../test/findByStateSP?page=1&size=8'">
                                        <%--onclick="queryA()">--%>
                                        <i class="fa fa-cube"></i> 待排刷新
                                    </button>
                                    <shiro:hasPermission name="SP一键排程">
                                        <button type="button" class="btn btn-default" title="一键排程"
                                                onclick="location.href='../test/SPInstall'">
                                            <i class="fa fa-cube"></i> 一键排程
                                        </button>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="SP待排批量操作">
                                        <button type="submit" id="Dsubmit" class="btn btn-default" title="批量操作"
                                                class="btn bg-olive btn-xs" data-toggle="modal"
                                                data-target="#myModalSPPiLiangDaiPai" onclick="pilianganniuDaiPai()">
                                            <i class="fa fa-cube"></i>批量操作
                                        </button>
                                    </shiro:hasPermission>
                                    <button type="button" class="btn btn-default" title="待排数据导出"
                                            onclick="location.href='../test/ShuJuDaoChuDaiPaiSP'">
                                        <i class="fa fa-cube"></i> 待排数据导出
                                    </button>
                                    待排共计${countDai}条


                                    <div style="width: 230px;height: 30px;float: right">
                                        <form action="../test/findByTestRequestIdDaiPai" method="post">

                                            <input style="width: 175px;height: 30px; border-radius:3px" type="text"
                                                   name="RequestId"
                                                   placeholder="  请输入需求编号查询" value="">
                                            <button style="width: 55px;height: 30px;float: right;border-radius:3px"
                                                    type="submit">搜索
                                            </button>

                                        </form>
                                    </div>
                                    <input type="hidden" id="pickListModels" value='${S}'>


                                </div>

                            </tr>
                        </div>

                        <!--数据列表-->
                        <div id="daipai" class="ccc table table-bordered table-striped table-hover dataTable">
                            <table id="dataListAAAA"
                                   class="table table-bordered table-striped table-hover dataTable">
                                <thead>
                                <tr>
                                    <th class="text-center"><br>选择</th>
                                    <th class="text-center">Order<br>顺位</th>
                                    <th class="text-center">RequestNo.<br>需求编号</th>
                                    <th class="text-center">RequiredTime<br>需求日期</th>
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
                                    <%--<th class="text-center">Vr(km/h)<br>试验速度</th>--%>
                                    <th class="text-center">Camber Angledeg<br>倾斜角</th>
                                    <%--<th class="text-center">Slip Angledeg<br>滑移角</th>--%>
                                    <th class="text-center">Pitch Angledeg<br>前后仰角</th>
                                    <%--<th class="text-center">Mileage(km)<br>里程</th>--%>
                                    <%--<th class="text-center">FFT<br> Order</th>--%>
                                    <%--<th class="text-center">Flat Spot <br>pt(kPa)</th>--%>
                                    <%--<th class="text-center">Flat Spot <br>Fz(N)</th>--%>
                                    <th class="text-center">Purpose<br>委试目的</th>
                                    <th class="text-center">sampleDisposal<br>样品处理</th>
                                    <th class="text-center">Target<br>目标</th>
                                    <th colspan="3" class="text-center">operation<br>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${SP}" var="test" varStatus="cou">

                                    <tr>
                                            <%--<td><input name="ids" type="checkbox"></td>--%>
                                        <td><input name="idsClassDaiPai" type="checkbox" style="zoom:120%"
                                                   id="${test.idRctest}" number="${test.number}" caozuo="${test.caozuo}"
                                                   onclick="aaaa(this)"></td>
                                        <td class="text-center">${test.number}</td>
                                        <td class="text-center">${test.requestId}</td>
                                        <td class="text-center">${test.requestTimeStr}</td>
                                        <td class="text-center">${test.project}</td>
                                        <td class="text-center">${test.projectEngineer}</td>
                                        <td class="text-center">${test.contactExt}</td>
                                        <td class="text-center">${test.priorityDes}</td>
                                        <td class="text-center">${test.sampleID}</td>
                                        <td class="text-center">${test.testItem}</td>
                                        <td class="text-center">${test.standard}</td>
                                        <td class="text-center">${test.size}</td>
                                        <td class="text-center">${test.pt}</td>
                                        <td class="text-center">${test.fz}</td>
                                        <td class="text-center">${test.usageRim}</td>
                                        <%--<td class="text-center">${test.vr}</td>--%>
                                        <td class="text-center">${test.CA}</td>
                                        <%--<td class="text-center">${test.SA}</td>--%>
                                        <td class="text-center">${test.PA}</td>
                                        <%--<td class="text-center">${test.mileage}</td>--%>
                                        <%--<td class="text-center">${test.FFTOrder}</td>--%>
                                        <%--<td class="text-center">${test.flatSpotPt}</td>--%>
                                        <%--<td class="text-center">${test.flatSpotFz}</td>--%>
                                        <td class="text-center">${test.purpose}</td>
                                        <td class="text-center">${test.sampleDisposal}</td>
                                        <td class="text-center">${test.target}</td>

                                        <td class="text-center">
                                            <shiro:hasPermission name="SP顺位排程">
                                                <button type="button" class="btn bg-olive btn-xs"
                                                        onclick="location.href='../test/updateStateSP?id=${test.idRctest}'">
                                                    顺位排程
                                                </button>
                                            </shiro:hasPermission>
                                        </td>
                                        <td class="text-center">
                                            <shiro:hasPermission name="SP插位排程">
                                                <button type="submit" class="btn bg-olive btn-xs" data-toggle="modal"
                                                        data-target="#myModalB"
                                                        onclick="ValuesS('${test.idRctest}',${test.caozuo})">插位排程
                                                </button>
                                            </shiro:hasPermission>
                                            <div class="modal fadeA" id="myModalB" tabindex="-1" role="dialog"
                                                 aria-labelledby="myModalLabelB" aria-hidden="true">
                                                <div class="modal-dialog modal-sm " style=" width: 350px;height:350px;">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">&times;
                                                            </button>
                                                            <h3 class="modal-title" id="myModalLabelB"
                                                                style="color: #00a7d0">调整实验数据顺序</h3>

                                                        </div>
                                                        <form id="formB" action="../test/TestNumberSP" method="post">
                                                            <h4>请输入需要把该数据调整到第几位：</h4>
                                                            <input type="text" class="text-center" name="numberB"
                                                                   id="numberB"
                                                                   placeholder="输入序号数字" style="border-radius: 4px"
                                                                   value="">

                                                            <input name="idB" id="id3D" type="hidden" value="">
                                                            <input name="caozuo4" id="caozuo4" type="hidden" value="">
                                                            <div class="modal-footer">

                                                                <input type="submit" class="btn btn-primary"
                                                                       value="提交更改" onclick="return tijiaoA()">

                                                            </div>

                                                        </form>
                                                    </div><!-- /.modal-content -->


                                                </div><!-- /.modal -->
                                            </div>
                                        </td>
                                        <td class="text-center">
                                            <shiro:hasPermission name="SP待排取消">
                                                <button type="button" class="btn bg-olive btn-xs"
                                                        onclick="location.href='../test/TestCancelSP?id=${test.idRctest}'">
                                                    案件取消
                                                </button>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <%--批量操作模态框--%>
                                <div class="modal fadeA" id="myModalSPPiLiangDaiPai" tabindex="-1" role="dialog"
                                     aria-labelledby="myModalLabelSPPiLiangD" aria-hidden="true">
                                    <div style=" width: 350px;height:350px;text-align: center"
                                         class="modal-dialog modal-sm ">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">&times;
                                                </button>
                                                <h3 class="modal-title" id="myModalLabelPiLiangD"
                                                    style="color: #00a7d0">调整实验数据顺序</h3>

                                            </div>
                                            <form id="formPiLiangChaWei" action="../test/PiLiangChaWei" method="post">
                                                <h4>当前选中实验是第<input id="PiLiangnumberSSChaWei" type="button"
                                                                   readonly="readonly" value="">位</h4>
                                                <h4>请输入需要把选中的实验调整到第几位：</h4>
                                                <input type="text" class="text-center" name="numberS"
                                                       id="piliangShuRuChaWei"
                                                       placeholder="输入序号数字" style="border-radius: 4px" value="">

                                                <input name="id" id="PiLiangidChaWei" type="hidden" value="">
                                                <input name="number" id="PiLiangnumberChaWei" type="hidden" value="">
                                                <input name="caozuo2" id="caozuo2" type="hidden" value="">

                                                <div class="modal-footer">

                                                    <input type="submit" class="btn btn-primary" value="插位排程"
                                                           onclick="return chaweipaicheng()">

                                                </div>

                                            </form>

                                            <form action="../test/PiLiangShunWei" method="post">

                                                <h4>把选中的实验顺位排程</h4>

                                                <input name="id" id="PiLiangidShunWei" type="hidden" value="">
                                                <input name="number" id="PiLiangnumberShunWei" type="hidden" value="">

                                                <div class="modal-footer">

                                                    <input type="submit" class="btn btn-primary" value="顺位排程"
                                                           onclick="return shunweipaicheng()">

                                                </div>
                                            </form>

                                            <form action="../test/PiLiangQuXiao" method="post">

                                                <h4>取消选中的实验</h4>

                                                <input name="QuXiaoid" id="PiLiangidQuXiao" type="hidden" value="">
                                                <input name="QuXiaonumber" id="PiLiangnumberQuXiao" type="hidden"
                                                       value="">

                                                <div class="modal-footer">

                                                    <input type="submit" class="btn btn-primary" value="案件取消"
                                                           onclick="return PiLiangQuXiao()">

                                                </div>
                                            </form>


                                        </div><!-- /.modal-content -->

                                    </div><!-- /.modal -->
                                </div>

                                </tbody>
                            </table>
                            </table>
                            <!--数据列表/-->
                        </div>

                    </div>
                </div>
            </div>

        </section>

    </div>
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
        src="../static/My97DatePicker/WdatePicker.js"></script>
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
        setSidebarActive("admin-datalist");

    });

    function cdddd(_this) {
        $("#divloading").css("display", "inline");
        $("#divall").css("display", "inline");
    }

    //跳转到工时维护页面  并自动点击刷新按钮
    $('#SPA').on('click', function () {
        sessionStorage.setItem("from", "pageSP");
    })

    //已排计划单个操作 数据进入到模态框
    $("#myModalSP").modal("hide");
    function Values(ID, id, spbh, red, caozuo) {
        $("#ids").val(ID);
        $("#id").val(id);
        $("#idss").val(ID);
        $("#idID").val(id);
        $("#spbh01").val(spbh);
        $("#spbh").val(spbh);
        $("#red").val(red);
        $("#caozuo1").val(caozuo);

    }

    function ValuesAAA(id) {
        $("#triesred1").click(function () {
            $('#myModalAbnormal').modal('show');
            $.ajax({
                url: "../abnormal/remarks",
                async: true,
                type: "post",
                data: {"id": id},
                success: showremarks,
                dataType: "json"
            });
        })

    }

    function showremarks(all) {
        //转换为json字符串
        var ALL = JSON.stringify(all);
        //转换为json对象
        var obj = JSON.parse(ALL);

        $("#admini1AS").val(obj.remarks)
    }

    //待排计划单个操作 数据进入到模态框
    $("#myModalB").modal("hide");

    function ValuesS(id, caozuo) {
        $("#id3D").val(id);
        $("#caozuo4").val(caozuo);
    }

    //已排单个操作
    function checkNumberDan() {
        //  获取到用户输入信
        var number = document.getElementById("number").value;
        //正则表达式 长度1到6位
        var reg_username = /^\w{1,6}$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberABDan() {

        var caozuo1 = document.getElementById("caozuo1").value;
        var caozuoA = caozuo1;
        if (caozuoA != 0) {
            //  获取到用户输入信
            var number = document.getElementById("number").value;
            var flag = number > caozuoA;
            if (flag == false) {
                alert("您输入的实验正在测试中或者正在暂停中，不允许操作！")
            }
            return flag;

        } else {
            return flag;
        }


        return flag;
    }


    function checkNumberADan() {
        //  获取到用户输入信
        var number = document.getElementById("number").value;
        //正则表达式 不能为0
        var reg_username = /(?!0+$)^\d*$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberACDan() {
        //  获取到用户输入信
        var number = document.getElementById("number").value;
        var count =${count};
        var flag = number < count;
        if (flag == false) {
            alert("请输入小于等于已排总条数的数字")
        }
        return flag;
    }

    function checkNumberABCCDan() {
        //  获取到开始信息
        var spbh = document.getElementById("spbh").value;
        var flag = spbh < 1;
        if (flag == false) {
            alert("已经开始的实验不能进行操作")
        }
        return flag;
    }

    function checkNumberABCABCCDan() {
        //  获取到暂停信息
        var red = document.getElementById("red").value;
        var flag = red < 1;
        if (flag == false) {
            alert("暂停中的实验不能进行操作")
        }
        return flag;
    }

    function tijiao() {
        return checkNumberDan() && checkNumberABDan() && checkNumberADan() && checkNumberACDan() && checkNumberABCCDan() && checkNumberABCABCCDan();
    };


    function zantingA() {
        //  获取到用户输入信
        var number = document.getElementById("numberAA").value;
        //正则表达式 不能为0
        var reg_username = /\S/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }

        return flag;
    }


    function zantingB() {

        //  获取到用户输入信
        var spbh01 = document.getElementById("spbh01").value;
        //只能为1
        var reg_username = /^[1]*$/;
        //判断
        var flag = reg_username.test(spbh01);
        if (flag == false) {
            alert("该功能只适用于开始的试验")
        }

        return flag;

    }

    function zanting() {
        return zantingA() && zantingB();
    };

    function kaishi(_this) {
        var number = $($(_this).parent().parent().children()[1])[0].innerHTML

        if ($($(_this).parent().parent().children()[1])[0].innerHTML == 1) {
            location.href = '../test/BeginSP?number=' + number;
        } else {
            if ($($(_this).parent().parent().prev().children()[22])[0].id == "wancheng0") {
                alert("请先开始前一条试验")
            } else {
                location.href = '../test/BeginSP?number=' + number;
            }
        }

    }

    function wancheng(id, number) {
        location.href = '../test/updateCompleteSP?id=' + id + '&number=' + number;
    }

    function ValuesQuXiaoSP (id,number) {
        if (confirm("操作确认!")){
            location.href='../test/updateCancelSP?id=' + id + '&number=' + number;
        }
    }

    function quxiaokaishiSP() {
        if(confirm("确定取消开始吗？")){
            location.href='../test/quxiaokaishiSP';
        }

    }

    //已排计划批量操作
    function pilianganniu() {
        var number = "";
        var id = "";
        var caozuo = "";

        $("input[name='idsClass']").each(function () {
            var _this = this;
            if ($(_this).get(0).checked) {
                number += $($(_this)[0]).attr("number") + ',';
                id += $($(_this)[0]).attr("idA") + ',';
                caozuo += $($(_this)[0]).attr("caozuo") + ',';
            }
        })
        id = id.substring(0, id.length - 1);
        number = number.substring(0, number.length - 1);
        caozuo = caozuo.substring(0, caozuo.length - 1);
        $("#PiLiangnumberSS").val(number);
        $("#PiLiangid").val(id);
        $("#PiLiangnumber").val(number);
        $("#PiLiangidQu").val(id);
        $("#PiLiangnumberQu").val(number);
        $("#caozuo").val(caozuo);
        $("#PiLiangidQuXiaoYiPai").val(id);
        $("#PiLiangnumberQuXiaoYiPai").val(number);


    }

    //待排计划批量操作
    function pilianganniuDaiPai() {
        var number = "";
        var id = "";
        var caozuo = "";
        $("input[name='idsClassDaiPai']").each(function () {
            var _this = this;
            if ($(_this).get(0).checked) {
                number += $($(_this)[0]).attr("number") + ',';
                id += $($(_this)[0]).attr("id") + ',';
                caozuo += $($(_this)[0]).attr("caozuo") + ',';

            }
        })
        id = id.substring(0, id.length - 1);
        number = number.substring(0, number.length - 1);
        caozuo = caozuo.substring(0, caozuo.length - 1);
        $("#PiLiangnumberSSChaWei").val(number);
        $("#PiLiangidChaWei").val(id);
        $("#PiLiangnumberChaWei").val(number);
        $("#PiLiangidShunWei").val(id);
        $("#PiLiangnumberShunWei").val(number);
        $("#caozuo2").val(caozuo);
        $("#PiLiangidQuXiao").val(id);
        $("#PiLiangnumberQuXiao").val(number);
    }

    //已排计划调整排程顺序(多选)
    function checkNumber() {
        //  获取到用户输入信
        var number = document.getElementById("piliangshuru").value;
        //正则表达式 长度1到6位
        var reg_username = /^\w{1,6}$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberA() {
        //  获取到用户输入信
        var number = document.getElementById("piliangshuru").value;
        //正则表达式 不能为0
        var reg_username = /(?!0+$)^\d*$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberAB() {
        var caozuo = document.getElementById("caozuo").value;
        for (var i = 0; i < caozuo.length; i++) {
            var caozuoA = caozuo[i];
        }
        if (caozuoA != 0) {
            //  获取到用户输入信
            var number = document.getElementById("piliangshuru").value;
            var flag = number > caozuoA;
            if (flag == false) {
                alert("您输入的实验正在测试中或者正在暂停中，不允许操作！")
            }
            return flag;

        } else {
            return flag;
        }

        return flag;
    }

    function checkNumberB() {
        //  获取到用户选择
        var number = document.getElementById("PiLiangnumberSS").value;
        //正则表达式 不能为空
        var reg_username = /\S/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("请先选中至少一项")
        }
        return flag;
    }

    function checkNumberC() {
        //  获取到用户输入信
        var number = document.getElementById("piliangshuru").value;
        var count =${count};
        var flag = number <= count;
        if (flag == false) {
            alert("请输入小于等于总条数的数字")
        }
        return flag;
    }

    function piliangtijiao() {
        return checkNumber() && checkNumberA() && checkNumberB() && checkNumberC() && checkNumberAB();
    }

    //已排批量取消  验证是否选中
    function checkPiLiangidYiPaiQuXiao() {
        //  获取到用户输入信
        var number = document.getElementById("PiLiangnumberQuXiaoYiPai").value;
        //正则表达式 不能为空
        var reg_username = /\S/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("请先选中至少一项")
        }
        return flag;
    }

    function YiPaiPiLiangQuXiao() {
        return checkPiLiangidYiPaiQuXiao();
    }


    //已排批量进入待排  验证是否选中
    function checkPiLiangidYiPaiJinRuDaiPai() {
        //  获取到用户输入信
        var number = document.getElementById("PiLiangnumberQu").value;
        //正则表达式 不能为空
        var reg_username = /\S/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("请先选中至少一项")
        }
        return flag;
    }

    function JinRuDaiPai() {
        return checkPiLiangidYiPaiJinRuDaiPai();
    }


    //待排计划（批量）插位和顺位控制和批量取消
    function checkPiLiangidshunwei() {
        //  获取到用户输入信
        var number = document.getElementById("PiLiangidShunWei").value;
        //正则表达式 不能为空
        var reg_username = /\S/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("请先选中至少一项")
        }
        return flag;
    }

    function shunweipaicheng() {
        return checkPiLiangidshunwei();
    }


    function checkNumberChaWei() {
        //  获取到用户输入信
        var number = document.getElementById("piliangShuRuChaWei").value;
        //正则表达式 长度1到6位
        var reg_username = /^\w{1,6}$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberAChaWei() {
        //  获取到用户输入信
        var number = document.getElementById("piliangShuRuChaWei").value;
        //正则表达式 不能为0
        var reg_username = /(?!0+$)^\d*$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberABChaWei() {
        var caozuo2 = document.getElementById("caozuo2").value;
        for (var i = 0; i < caozuo2.length; i++) {
            var caozuoA = caozuo2[i];
        }
        if (caozuoA != 0) {
            //  获取到用户输入信
            var number = document.getElementById("piliangShuRuChaWei").value;
            var flag = number > caozuoA;
            if (flag == false) {
                alert("您输入的实验正在测试中或者正在暂停中，不允许操作！")
            }
            return flag;

        } else {
            return flag;
        }
        return flag;
    }

    function checkNumberBChaWei() {
        //  获取到用户选择
        var number = document.getElementById("PiLiangidChaWei").value;
        //正则表达式 不能为空
        var reg_username = /\S/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("请先选中至少一项")
        }
        return flag;
    }

    function checkNumberCChaWei() {
        //  获取到用户输入信
        var number = document.getElementById("piliangShuRuChaWei").value;
        var count =${count};
        var flag = number <= count;
        if (flag == false) {
            alert("请输入小于等于已排总条数的数字")
        }
        return flag;
    }

    function chaweipaicheng() {
        return checkNumberChaWei() && checkNumberAChaWei() && checkNumberBChaWei() && checkNumberCChaWei() && checkNumberABChaWei();
    }

    //待排单个插位
    function checkNumberDanChaWei() {
        //  获取到用户输入信
        var number = document.getElementById("numberB").value;
        //正则表达式 长度1到6位
        var reg_username = /^\w{1,6}$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberADanChaWei() {
        //  获取到用户输入信
        var number = document.getElementById("numberB").value;
        //正则表达式 不能为0
        var reg_username = /(?!0+$)^\d*$/;
        //判断
        var flag = reg_username.test(number);
        if (flag == false) {
            alert("输入格式不正确！")
        }
        return flag;
    }

    function checkNumberABDanChaWei() {
        var caozuo4 = document.getElementById("caozuo4").value;
        var caozuoA = caozuo4;

        if (caozuoA != 0) {
            //  获取到用户输入信
            var number = document.getElementById("numberB").value;

            var flag = number > caozuoA;
            if (flag == false) {
                alert("您输入的实验正在测试中或者正在暂停中，不允许操作！")
            }
            return flag;

        } else {
            return flag;
        }
        return flag;
    }

    function checkNumberCDanChaWei() {
        //  获取到用户输入信
        var number = document.getElementById("numberB").value;
        var count =${count};
        var flag = number <= count;
        if (flag == false) {
            alert("请输入小于等于已排总条数的数字")
        }
        return flag;
    }

    function tijiaoA() {
        return checkNumberDanChaWei() && checkNumberADanChaWei() && checkNumberCDanChaWei() && checkNumberABDanChaWei();
    }


    //控制多选选中时的颜色
    function aaaa(_this) {
        if ($(_this).get(0).checked) {
            $($(_this).parent().parent()).css("background-color", "yellow")
        } else {
            $($(_this).parent().parent()).css("background-color", "")
        }

    }

</script>
</body>

</html>
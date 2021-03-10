<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>登录页面</title>

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
          href="../static/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="../static/plugins/iCheck/square/blue.css">
</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <%--<a href="all-admin-index.html">实验室信息管理系统</a>--%>
        <a href="all-admin-index.html">研发信息管理平台</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">登录系统</p>

        <form action="../user/login" method="post">
            <div class="form-group has-feedback">
                <input type="text" name="username" class="form-control"
                       placeholder="用户名"> <span
                    class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control"
                       placeholder="密码"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label><input type="checkbox"> 记住密碼</label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <tr>
            <td><a href="#">忘记密码</a></td>
            <td style="color: #ff0000">${msg}</td>
        </tr>
    </div>

</div>


<script
        src="../static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="../static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="../static/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>

</html>
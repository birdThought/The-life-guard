<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>Sign In</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    
    <t:base type="jquery"></t:base>

    <link href="/static/adminX/css/style.css" rel="stylesheet">
    <link href="/static/adminX/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/static/adminX/js/html5shiv.js"></script>
    <script src="/static/adminX/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body" onkeyup="_keyDown()">

<div class="container">

    <form class="form-signin" action="">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">Sign In</h1>
            <img src="/static/adminX/images/login-logo.png" alt=""/>
        </div>
        <div class="login-wrap">
            <input type="text" id="username" class="form-control" placeholder="用户名" autofocus>
            <input type="password" id="password" class="form-control" placeholder="密码">

            <button class="btn btn-lg btn-login btn-block" type="button" id="sub_login">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                	不是一个成员吗?
                <a class="" href="registration.html">
                   	注册
                </a>
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记得我
                <span class="pull-right">
                	<a href="javascript:testBut();">Test Button</a>
                    <a data-toggle="modal" href="#myModal"> 忘记了密码?</a>
                </span>
            </label>

        </div>

        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">忘记了密码?</h4>
                    </div>
                    <div class="modal-body">
                        <p>输入您的电子邮件地址重新设置您的密码。</p>
                        <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                        <button class="btn btn-primary" type="button">提交</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal -->

    </form>

</div>


<!-- Placed js at the end of the document so the pages load faster
<script src="/static/adminX/js/jquery-1.10.2.min.js"></script> -->
<script src="/static/adminX/js/bootstrap.min.js"></script>
<script src="/static/adminX/js/modernizr.min.js"></script>
<script src="/static/login/js/login.js"></script>
<script type="text/javascript" src="/static/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/static/common/js/common.js"></script>
</body>
</html>

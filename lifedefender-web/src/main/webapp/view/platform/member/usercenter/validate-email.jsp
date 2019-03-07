<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/20
  Time: 19:16
  验证邮箱
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,vue"></t:base>
    <meta charset=UTF-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common-home.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/user-info.css">
    <title>邮箱验证</title>
   <%-- <script src="js/jquery-2.1.1.min.js"></script>--%>
</head>
<body>
<%@include file="../context/header.jsp"%>
<!-- /header -->
<article>
    <section class="main-top">
        <%@include file="../context/admin.jsp"%>
    </section>
    <section class="banxin container">
        <%@include file="../context/menu.jsp"%>
        <%--右边页面start--%>
        <div class="main-content" >
            <div class="main-content-top">
                <a href="#">首页></a><a href="testController.do?usercenter">个人主页</a>><span>邮箱验证</span>
            </div>
            <form class="email-form">
                <ul class="user-infor">
                    <li>
                        <h5>邮箱地址</h5>
                        <input type="text" disabled="disabled" value="${user.email}">
                    </li>
                    <li class="change-num">
                        <a onclick="modifyEmail()" class="cursor-pointer">更换邮箱</a>
                    </li>
                </ul>
            </form>
            <form class="modify-form none">
                <ul class="user-infor">
                    <li>
                        <h5>邮箱地址</h5>
                        <input type="text" class="m-email" placeholder="请输入邮箱地址" >
                    </li>
                    <li class="mail-code">
                        <h5>验证码</h5>
                        <div>
                            <input type="text" class="m-code">
                            <input type="button" value="获取验证码" class="send-email-code" onclick="sendEmailValidateCode()">
                        </div>
                    </li>
                    <li class="refer-to">
                        <a href="javascript:void(0)" class="btn btn-mail" onclick="submitEmail()">确认提交</a>
                    </li>
                </ul>
            </form>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/platform/v2.2.0/js/member/usercenter/user-info.js"></script>
<script src="/static/officialwebsite/js/sendCode.js"></script>
<script >
    $(".main-content").css('min-height',800);
    var height=$(".main-content").height();
    $(".main-nav").css({
        borderRight:'1px solid #ddd',
        height:height
    });
    $(".container").css({
        border:'1px solid #ddd',
        height:height
    });


    var user = JSON.parse('${user}');
    if (user.email == null || user.email == '') {
        $('.modify-form').css('display', 'block');
    }
    
    function modifyEmail() {
        $('.modify-form').css('display', 'block');
        $('.email-form').css('display', 'none');
    }
</script>

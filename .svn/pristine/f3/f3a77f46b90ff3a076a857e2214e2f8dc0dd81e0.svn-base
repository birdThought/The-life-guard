<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/21
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common-home.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/user-info.css">
    <title>修改密码</title>
    <%--<script src="js/jquery-2.1.1.min.js"></script>--%>

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
        <div class="main-content">
            <div class="main-content-top">
                <a href="#">首页></a><a href="testController.do?usercenter">个人主页</a>><span>修改密码</span>
            </div>
            <form class="modify-password-form">
                <ul class="user-infor">
                    <li>
                        <h5>原密码</h5>
                        <input type="password" class="old-password">
                        <div class="msg-lay">请输入正确的原密码</div>
                    </li>
                    <li>
                        <h5>新密码</h5>
                        <input type="password" class="new-password">
                        <div class="msg-lay">6-18位字符，只能包含英文字母、数字、下划线</div>
                    </li>
                    <li>
                        <h5>确认新密码</h5>
                        <input type="password" class="confirm-password">
                        <div class="msg-lay">请再次输入新密码</div>
                    </li>
                    <li class="refer-to">
                        <a href="javascript:void(0)" class="btn btn-green" onclick="submitPassword()">确认保存</a>
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
</script>

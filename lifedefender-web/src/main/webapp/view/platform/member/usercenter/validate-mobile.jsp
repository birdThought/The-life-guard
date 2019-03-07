<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/20
  Time: 19:15
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
    <title>手机验证</title>
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
                <a href="#">首页></a><a href="testController.do?usercenter">个人主页</a>><span>手机验证</span>
            </div>
            <form class="mobile-form">
                <ul class="user-infor">
                    <li>
                        <h5>手机号码</h5>
                        <input type="text" value="${user.mobile}" disabled="disabled">
                    </li>
                    <li class="change-num">
                        <a href="javascript:void(0)" onclick="modifyMobile()">更换手机</a>
                    </li>
                </ul>
            </form>

            <form class="mobile-modify-form none">
                <ul class="user-infor">
                    <li>
                        <h5>手机号码</h5>
                        <input type="text" class="m-mobile" placeholder="请输入手机号码" >
                    </li>
                    <li class="mail-code">
                        <h5>验证码</h5>
                        <div>
                            <input type="text" class="m-code">
                            <input type="button" value="获取验证码" class="send-mobile-code" onclick="sendMobileValidateCode()">
                        </div>
                    </li>
                    <li class="refer-to">
                        <a href="javascript:void(0)" class="btn btn-mail" onclick="submitMobile()">确认提交</a>
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

    var user = JSON.parse('${user}');
    if (user.mobile == null || user.mobile == '') {
        $('.modify-form').css('display', 'block');
    }

    function modifyMobile() {
        $('.mobile-modify-form').css('display', 'block');
        $('.mobile-form').css('display', 'none');
    }
</script>

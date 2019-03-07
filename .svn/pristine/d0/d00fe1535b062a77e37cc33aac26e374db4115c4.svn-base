<%--
  Created by IntelliJ IDEA.
  User: wenxian.cai
  Date: 2017/3/17
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type = "layui,jquery2.11"></t:base>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/adapter/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="/static/common/css/mainheader_1.css">--%>
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/findPwd.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <title>找回密码</title>
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@include file="/context/mainHeader_1.jsp"%>
<section  class="item-content">
    <div class="findPwd">
        <div class="findPwd_title">
            <h3>找回密码</h3>
        </div>
        <div class="liucheng">
            <div class="line"></div>
            <div class="buzhou">
                <img src="/static/images/user.png">
                <p>账号信息</p>
            </div>
            <div class="buzhou" style="width:35%">
                <img src="/static/images/resetPwd.png">
                <p>重置密码</p>
            </div>
            <div class="buzhou">
                <img src="/static/images/finish.png">
                <p>完成</p>
            </div>
        </div>
        <form class="findpwd_form" action = "releaseControl.do?checkValidCode" method="post"  onSubmit="return findPwdOne.checkCode();">
            <ul>
                <li class="infor">
                    <h5>手机号码/邮箱</h5>
                    <input class = "mobileOrEmail" type="text" placeholder="请输入手机或者有效邮箱" >
                    <input type="hidden" class = "userId" name="userId" value = "" />
                </li>
                <li class="code">
                    <h5>验证码</h5>
                    <input type="text" class = "codeContent" name="code" placeholder="请输入验证码">
                    <input type="button" class = "sendCode" value="获取验证码">
                </li>
                <li>
                    <input type="submit" class = "submitCode" value="下一步">
                </li>
            </ul>
        </form>
    </div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/officialwebsite/index/js/findPwd.js"></script>
<script src="/static/officialwebsite/index/js/sendCode.js"></script>
<script>
    $(function(){
        layui.use('layer', function(){
        });
        findPwdOne.init();
    });
</script>

</body>
</html>

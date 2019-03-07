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
                <img src="/static/images/resetPwd_2.png">
                <p>重置密码</p>
            </div>
            <div class="buzhou">
                <img src="/static/images/finish.png">
                <p>完成</p>
            </div>
        </div>
        <form class="findpwd_form findPsw">
            <ul>
                <li class="infor">
                    <h5>重置密码</h5>
                    <input id="password" type="password" name="password" placeholder="请输入密码"
                           onkeyup="findPwdTwo.pwStrength(this.value)" onblur="findPwdTwo.pwStrength(this.value)">
                    <div class="psw_strenth">
                        <div id="strength_L"></div>
                        <div id="strength_M"></div>
                        <div id="strength_H"></div>
                        <span id="strength_notice"></span>
                    </div>
                    <input type="hidden" name="validCode" value="${validCode}">
                    <input type="hidden" name="userId" value="${userId}">
                </li>
                <li class="infor">
                    <h5 >确认密码</h5>
                    <input type="password" id="conform_password" name="conform_password" placeholder="请确认密码">
                    <span id="passstrength"></span>
                </li>
                <li class="infor">
                    <input type="button" class = "submitPwd" value="确定">
                </li>
            </ul>
        </form>
    </div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
<script type="text/javascript" src="/static/common/js/common.js"></script>
<script src="/static/officialwebsite/index/js/findPwd.js"></script>
<script>
    $(function(){
        layui.use('layer', function(){
        });
        findPwdTwo.init();
    });
</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<html>
<head>
    <title>绑定新手机</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-ic
on" rel="shortcut icon" href="favicon.ico
"
          mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/QYcomCss.css">
    <link rel="stylesheet" type="text/css" href="/static/com/QYPart/css/sercurity.css">
    <script type="text/javascript" src="/static/com/QYPart/js/commonCheck.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/mobileAndEmailVerify.js"></script>
    <script>
        window.onload = function () {
            var child = 1;
            <shiro:hasPermission name="user:0 or user:2">
            child = 2;
            </shiro:hasPermission>
            menuSetting({
                parent: 1,
                child: child
            })
        }
    </script>
    <style>
        .step .progress {
            width: 30%
        }
    </style>
</head>
<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body" style="min-height: 822px">
            <div class="prompt">
                <p style="margin-bottom: 5px;">温馨提示：为了保障你的账号安全，请先进行安全验证</p>
            </div>
            <div class="step">
                <div class="progress" style="background:#fff url('static/images/validateBg.png') ;background-size: 100% 24px;">验证身份
                </div>
                <div class="progress" style="background:#fff url('static/images/validateBg2.png');background-size: 100% 24px;">绑定新手机
                </div>
                <div class="progress" style="background:#fff url('static/images/validateBg3.png');background-size: 100% 24px;color:#111;">绑定成功</div>
            </div>
            <div class="param_set" style="margin-top:30px">
                <label class="param">手机：</label>
                <input type="text" placeholder="请输入新手机" id="newMobile">
                <button class="btn" style="color: #3598dc;margin-left:20px;" onclick="VeriMobile1.getMobileVerify(this,true)">获取短信验证码</button>
            </div>
            <div class="param_set" style="margin-top:30px">
                <label class="param">短信验证码：</label>
                <input type="text" id="dx">
                <p style="padding: 10px 0 0 114px;">1分钟内没校验，请点击重新获取验证码，验证码半小时内有效</p>
            </div>
            <div class="param_set" style="margin-top:30px">
                <label class="param"></label>
                <button class="save" onclick="VeriMobile1.bindMobile()">下一步</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
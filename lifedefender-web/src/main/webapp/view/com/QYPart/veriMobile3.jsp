<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<html>
<head>
    <title>验证身份</title>
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
</head>
<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body" style="min-height: 822px">
            <div class="prompt">
                <p style="margin-bottom: 10px;">温馨提示：为了保障你的账号安全，请先进行安全验证</p>
            </div>
            <div class="step">
                <div class="progress" style="background:#fff url('static/images/validateBg.png') ;background-size: 100% 24px;">验证身份
                </div>
                <c:if test="${isBindNew}">
                    <style>
                        .step .progress {
                            width: 30%
                        }
                    </style>
                    <div class="progress" style="background:#fff url('static/images/validateBg2.png');background-size: 100% 24px;">绑定新手机
                    </div>
                </c:if>
                <div class="progress" style="background:#fff url('static/images/validateBg_3.png');background-size: 100% 24px;color:#fff;">绑定成功</div>
            </div>
            <div class="finish">
                <img src="/static/images/OK.png" alt="">
                <p style="margin-top: 15px;font-size: 18px">你已成功绑定新手机</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>

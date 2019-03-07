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
        .save {
            border: none;
            outline: none;
            border-radius: 3px;
            padding: 5px 20px;
            background: #3598dc;
            color: #fff;
            cursor: pointer;
        }

        .save:hover {
            background: #328ac4;
        }

        .back {
            border: none;
            outline: none;
            border-radius: 3px;
            padding: 5px 20px;
            border: 1px solid #ccc;
            background-color: #fff;
            margin-left: 30px;
            cursor: pointer;
        }

        .back:hover {
            background: #f1f1f1;
        }
    </style>
</head>
<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body" style="min-height: 822px">
            <div class="right_title" style="padding-left: 30px;">
                <label class="action">绑定邮箱</label>
            </div>
            <div class="base_content" style="padding-left: 20px;padding-right: 20px;">
                <div class="param_set">
                    <label class="param">邮箱：</label>
                    <div class="sentEmail" style="display: inline-block;">
                        <input id="email" type="text" placeholder="请输入常用邮箱" value="${email}">
                        <button class="btn" style="color: #3598dc;display: block;margin-top: 8px" onclick="EmailVerify.sendEmailCode(this)">发送验证邮件</button>
                    </div>
                </div>
                <div class="param_set">
                    <label class="param">验证码：</label>
                    <input id="code" type="text" placeholder="请输入验证码">
                </div>
                <div class="param_set" style="margin-top:30px">
                    <label class="param"></label>
                    <button class="save" onclick="EmailVerify.bindEmail()">提交</button>
                    <button class="back" onclick="history.go(-1)">返回</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

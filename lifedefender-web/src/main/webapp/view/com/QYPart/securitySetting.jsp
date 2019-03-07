<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<html>
<head>
    <title>安全设置</title>
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
    <script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
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
            var strong = $.cookie("pwdStrong");
            setStrong(strong);
        }
        function setStrong(mode) {
            switch (parseInt(mode)) {
                case 0:
                    $(".level div").css({"background": "#ff0000"});
                    $(".level div").animate({"width": "10%"});
                    $(".level_text").text("弱");
                    $(".level_text").css({"color": "#ff0000"});
                    break;
                case 1:
                    $(".level div").css({"background": "#ff0000"});
                    $(".level div").animate({"width": "30%", "background": "#ff0000"});
                    $(".level_text").text("弱");
                    $(".level_text").css({"color": "#ff0000"});
                    break;
                case 2:
                    $(".level div").css({"background": "#f6ac4b"});
                    $(".level div").animate({"width": "60%"});
                    $(".level_text").text("中");
                    $(".level_text").css({"color": "#f6ac4b"});
                    break;
                case 3:
                    $(".level div").css({"background": "#48c858"});
                    $(".level div").animate({"width": "90%"});
                    $(".level_text").text("强");
                    $(".level_text").css({"color": "#48c858"});
                    break;
                default:
                    $(".level div").css({"background": "#48c858"});
                    $(".level div").animate({"width": "100%"});
                    $(".level_text").text("强");
                    $(".level_text").css({"color": "#48c858"});
                    break;
            }
        }
    </script>
    <style>
        div.setSec a:hover{
        	border-bottom: 1px dashed #000;
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
                <label class="action">安全设置</label>
            </div>
            <div class="setSec">
                <ul class=setSec_content>
                    <li><strong>登录密码</strong></li>
                    <li>
                        <span>当前登陆密码安全程度</span>
                        <div class="level">
                            <div></div>
                        </div>
                        <span class="level_text"></span>
                    </li>
                    <li class="setting">
                        <a href="orgControl.do?orgBasePage" style="font-size: 15px">修改密码</a>
                    </li>
                </ul>
                <ul class="setSec_content">
                    <li><strong>手机绑定</strong></li>
                    <li>
                        <p>您的手机为：<span>${data.attr['mobile']}</span></p>
                    </li>
                    <li class="setting">
                        <c:choose>
                            <c:when test="${data.attr['mobileVerify']}">
                                <img src="/static/images/yes.png"><span style="color: #48c858;margin-left: 5px;vertical-align: middle">已绑定</span> &nbsp;&nbsp;<span style="color: #ddd">|</span>&nbsp;&nbsp; <a href="orgSetControl.do?veriMobile&bindNew=true">修改</a>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty data.attr['mobile']}">
                                        <a href="orgUserControl.do?userInfo">去设置手机</a>
                                    </c:when>
                                    <c:otherwise><img src="/static/images/no.png"><a href="orgSetControl.do?veriMobile" style="font-size:15px;margin-left: 5px;vertical-align: middle">未绑定</a></c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
                <ul class="setSec_content">
                    <li><strong>邮箱绑定</strong></li>
                    <li>
                        <p>找回登陆密码，最新活动及最新动态及时邮件通知您</p>
                    </li>
                    <li class="setting">
                        <c:choose>
                            <c:when test="${data.attr['mobileVerify']}">
                                <img src="/static/images/yes.png"><span style="color: #48c858;margin-left: 5px;vertical-align: middle">已绑定</span> &nbsp;&nbsp;<span style="color: #ddd">|</span>&nbsp;&nbsp; <a href="orgSetControl.do?bindEmailPage" style="color:#0ab9f2">修改</a>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty data.attr['email']}">
                                        <a href="orgUserControl.do?userInfo">去设置邮箱</a>
                                    </c:when>
                                    <c:otherwise>
                                      <img src="/static/images/no.png"><a href="orgSetControl.do?bindEmailPage" style="font-size:15px;margin-left: 5px;vertical-align: middle">未绑定</a></c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>

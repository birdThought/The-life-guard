<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery,layer"></t:base>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/adapter/reset.css">
    <!-- <link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css"> -->
    <%--<link rel="stylesheet" type="text/css" href="/static/common/css/mainheader_1.css">--%>
    <link rel="stylesheet" href="/static/officialwebsite/index/css/magic-check.css">
    <link rel="stylesheet" type="text/css" href="/static/login/css/login.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <title>注册账号</title>
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@include file="/context/mainHeader_1.jsp"%>
<section class="item-content item-content-register">
    <form id="item-register" class="form" novalidate="novalidate">
        <div class="register-title">
            <h3>注册账号</h3>
        </div>
        <ul class="regiterContent">
            <li>
                <h5>用户类型</h5>
                <ol>
                    <li>
                        <input type="radio" class="magic-radio userType" value="m" id="user_1" name="user" checked>
                        <label for="user_1">个人用户</label>
                    </li>
                    <li>
                        <input type="radio" class="magic-radio userType" value="o" id="user_2" name="user">
                        <label for="user_2">机构</label>
                    </li>
                </ol>
            </li>
            <li class="org_type">
                <h5>机构类型</h5>
                <ol>
                    <li>
                        <input type="radio" class="magic-radio" value="0" id="org_1" name="org" checked = checked>
                        <label for="org_1">多门店</label>
                    </li>
                    <li>
                        <input type="radio" class="magic-radio" value="1" id="org_2" name="org">
                        <label for="org_2">单门店</label>
                    </li>
                    <li>
                        <input type="radio" class="magic-radio" value="2" id="org_3" name="org">
                        <label for="org_3">个体店</label>
                    </li>
                </ol>
            </li>
            <li class="org_type">
                <h5>机构性质</h5>
                <select name="nature" id="orgXZ">
                    <option value="慢病康复">慢病康复</option>
                    <option value="健康养生">健康养生</option>
                    <option value="减肥塑体">减肥塑体</option>
                    <option value="居家养老">居家养老</option>
                    <option value="癌症康复">癌症康复</option>
                    <option value="妇婴">妇婴</option>
                    <option value="家政">家政</option>
                    <option value="生殖医学">生殖医学</option>
                </select>
            </li>
            <li class="org_type">
                <h5>机构名称</h5>
                <input type="text" id="businessName" name="business" placeholder="请输入机构名称">
            </li>
            <li>
                <h5>用户名</h5>
                <input id="userName" type="text" name="userName" placeholder="请输入用户名">
            </li>
            <li>
                <h5>密码</h5>
                <input type="password" name="password" id="password" placeholder="请输入密码"
                       onkeyup="pwStrength(this.value)" onblur="pwStrength(this.value)">
                <div class="psw_strenth">
                    <div id="strength_L"></div>
                    <div id="strength_M"></div>
                    <div id="strength_H"></div>
                    <span id="strength_notice"></span>
                </div>
            </li>
            <li>
                <h5>确认密码</h5>
                <input type="password" name="conform_password" placeholder="请再次输入密码">
            </li>
            <li>
                <input type="checkbox" class="magic-checkbox" id="agreement">
                <label for="agreement"  name="agreement" >我已阅读并接受<a href="javascript:void(0);" onclick="clickHere();">《生命守护用户服务协议》</a></label>
            </li>
            <li>
                <input type="submit" value="注册" class="regist_btn">
            </li>
            <li>
                <p>已经有生命守护平台账号? <a href="/login">立即登录</a></p>
            </li>
        </ul>
    </form>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
<!-- <script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="/static/login/js/icheck.js"></script>
<script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
<script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
<script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="/static/login/js/register.js"></script>
<script>
    $(".org_type").css("display","none");
    $("label[for='user_2']").click(function(){
        $(".org_type").show(400);
    })
    $("label[for='user_1']").click(function(){
        $(".org_type").hide(400);
    })
</script>
</body>
</html>

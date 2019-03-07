<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/27
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery,layer"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <title>专业人士注册</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-1"></div>
                <p class="on">1.准备申请</p>
            </li>
            <li>
                <div class="step-2"></div>
                <p>2.实名信息认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>3.资格认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.专业特长</p>
            </li><li>
            <div  class="step-2"></div>
            <p>5.等待审核</p>
        </li>
        </ul>
    </div>
    <form class="wrap register-form-1">
        <ul>
            <li>
                <h5>登录名</h5>
                <input type="text" name="username" class="username" placeholder="6-18位字符，只能包含英文字母、数字、下划线">
                <div class="msg-lay">
                    <span class="msg-lay-1"></span>
                </div>
            </li>
            <li>
                <h5>登录密码</h5>
                <input type="password" class="password" name="password" placeholder="请输入密码"
                       onkeyup="pwStrength(this.value)" onblur="pwStrength(this.value)">

                <div class="msg-lay psw_strenth">
                    <span>安全等级低</span>
                    <div id="strength_L"></div>
                    <div id="strength_M"></div>
                    <div id="strength_H"></div>
                </div>
            </li>
            <li>
                <h5>确认密码</h5>
                <input type="password" class="cf-pwd" placeholder="请确认密码">
                <div class="msg-lay"></div>
            </li>
            <li>
                <h5>手机</h5>
                <input type="text" name="mobile" placeholder="请输入手机号码 " class="mobile">
                <div class="msg-lay"></div>
            </li>
            <li>
                <h5>手机验证码</h5>
                <input type="text" name="code">
                <input type="button" class="get-code get-code" value="获取验证码" onclick="org.sendCode()">
            </li>
            <li>
                <h5>申请类型</h5>
                <select name="type">
                    <%--<option value="请选择你的专业背景">请选择你的专业背景</option>--%>
                    <option value="1">服务师</option>
                </select>
            </li>
            <li>
                <h5>推荐人编号</h5>
                <input type="text" name="parentId" placeholder="请输入推荐人编号 " class="parentId">
                <div class="msg-lay"></div>
            </li>
            <li class="argument">
                <p>点击【开始申请】按钮，表示您同意<a class="btn-yellow btn-agreement" onclick="services.agreement()">《专业人员注册协议》</a></p>
            </li>
            <li class="argument">
                <input type="button" class="submit" value="入驻" onclick="services.register()">
            </li>
        </ul>
    </form>
</section>
<%@include file="../../context/footer.jsp"%>
</body>
</html>
<script src="/static/platform/v2.2.0/js/register/org/orgregister.js"></script>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <title>机构注册</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div class="step-1"></div>
        <div class="step-1"></div>
        <div class="step-1"></div>
        <div class="step-2"></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-3"></div>
                <p class="pass">1.公司信息认证</p>
            </li>
            <li>
                <div class="step-3"></div>
                <p class="pass">2.银行信息认证</p>
            </li>
            <li>
                <div  class="step-3"></div>
                <p class="on">3.法人信息认证</p>
            </li>
            <li>
                <div  class="step-1"></div>
                <p>4.准备入驻</p>
            </li><li>
                <div  class="step-2"></div>
                <p>5.等待审核</p>
            </li>
        </ul>
    </div>
    <form class="wrap  register-form-4">
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
                <input type="password" name="password" class="password" placeholder="请输入密码"
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
                <input type="password" name="confirmPassword" class="cf-pwd" placeholder="请确认密码">
                <div class="msg-lay"></div>
            </li>
            <li>
                <h5>手机</h5>
                <input type="text" name="mobile" placeholder="请输入手机号码 " class="mobile">
                <div class="msg-lay"></div>
            </li>
            <li>
                <h5>手机验证码</h5>
                <input type="text" name="code" class="code">
                <input type="button" class="get-code send-code" value="获取验证码" onclick="org.sendCode()">
            </li>
            <li>
                <h5>推荐人编号</h5>
                <input type="text" name="parentId" placeholder="请输入推荐人编号 " class="parentId">
                <div class="msg-lay"></div>
            </li>
            <%--<li class="argument">
                <p>点击【开始申请】按钮，表示您同意<a class="btn-yellow btn-agreement" onclick="org.agreement()">《专业人员注册协议》</a></p>
            </li>--%>
            <li class="argument">
                <input type="button" class="submit" value="入驻" onclick="org.registerStepFour()">
            </li>
            <input type="hidden" name="orgId" class="orgId" value="${id}">
        </ul>
    </form>
</section>
<%@include file="/view/platform/context/footer.jsp"%>
<script src="/static/platform/v2.2.0/js/register/org/orgregister.js"></script>
</body>
</html>
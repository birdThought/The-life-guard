<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type="jquery,layer"></t:base>
    <meta charset="utf-8">
    <meta name=description content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register.css">
    <%--<script src="js/jquery-2.1.1.min.js"></script>--%>
    <title>生命守护</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
	<section class="register_content banxin">
		<p class="introduce">提示：以下注册信息是系统所必须的重要健康参数，请您认真、正确填写</p>
		<div class="step-line clearfix">
			<div></div>
			<div></div>
			<div></div>
			<ul class="clearfix">
				<li>
					<div class="step-1"></div>
					<p class="on">1.填写登陆信息</p>
				</li>
				<li>
					<div class="step-2"></div>
					<p>2.填写基本信息</p>
				</li>
				<li>
					<div  class="step-2"></div>
					<p>3.注册成功</p>
				</li>
			</ul>
		</div>
		<form class="wrap">
			<ul>
			    <li>
			    	<h5>登录账号</h5>
			    	<input type="text" class="username" placeholder="6-18位字符，只能包含英文字母、数字、下划线">
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
			    	<input type="password" class="cf-pwd" placeholder="请确认密码">
			    	<div class="msg-lay"></div>
			    </li>
			    <li>
			    	<h5>手机</h5>
			    	<input type="text" placeholder="请输入手机号码 " class="phone">
			    	<div class="msg-lay"></div>
			    </li>
			    <li>
			    	<h5>手机验证码</h5>
			    	<input type="text" class="code">
			    	<input type="button" class="get-code" value="获取验证码" onclick="sendCode();">
			    </li>
			    <li class="argument">
			    	<p>点击【注册】按钮，表示您同意<a class = "register-agreement" onclick="clickHere()">《用户注册协议》</a></p>
			    </li>
			    <li class="argument">
			    	<input type="button" class = "submit" value="注册" onclick="register();"><%--style="background-color: #a9a9a9; border-color:#a9a9a9"--%>
			    </li>
			</ul>
		</form>
	</section>
<%@include file="../../context/footer.jsp"%>
</body>
</html>
<script src="/static/platform/v2.2.0/js/register/member/validate.js"></script>
<%--<script src="/static/platform/v2.2.0/js/login/register.js"></script>--%>

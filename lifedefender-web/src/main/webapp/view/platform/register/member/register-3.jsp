<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type="jquery,layer"></t:base>
    <meta charset="utf-8">
    <meta name=description content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register.css">
   <%-- <script src="js/jquery-2.1.1.min.js"></script>--%>
    <title>生命守护</title>
</head>
<body>
	<section class="register_content banxin">
		<p class="introduce">提示：以下注册信息是系统所必须的重要健康参数，请您认真、正确填写</p>
		<div class="step-line clearfix">
			<div class="step-1"></div>
			<div class="step-1"></div>
			<div class="step-2 step-3"></div>
			<ul class="clearfix">
				<li>
					<div class="step-3"></div>
					<p class="pass">1.填写登陆信息</p>
				</li>
				<li>
					<div class="step-3"></div>
					<p class="pass">2.填写基本信息</p>
				</li>
				<li>
					<div class="step-1"></div>
					<p class="on">3.注册成功</p>
				</li>
			</ul>
		</div>
		<section >
			<div></div>
			<h3>恭喜您，注册成功</h3>
			<a href="/login" class="btn-3">立即开始我的健康之旅</a>
		</section>
	</section>
</body>
</html>
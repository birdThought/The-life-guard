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
	<link rel="stylesheet" type="text/css" href="/static/plugins/jeDate/css/jedate.css">
	<script type="text/javascript" src="/static/plugins/jeDate/jedate.min.js"></script>
	<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
    <%--<script src="js/jquery-2.1.1.min.js"></script>--%>
    <title>生命守护</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
	<section class="register_content banxin">
		<p class="introduce">提示：以下注册信息是系统所必须的重要健康参数，请您认真、正确填写</p>
		<div class="step-line clearfix">
			<div class="step-1"></div>
			<div class="step-2"></div>
			<div></div>
			<ul class="clearfix">
				<li>
					<div class="step-3"></div>
					<p class="pass">1.填写登陆信息</p>
				</li>
				<li>
					<div class="step-1"></div>
					<p class="on">2.填写基本信息</p>
				</li>
				<li>
					<div class="step-2"></div>
					<p>3.注册成功</p>
				</li>
			</ul>
		</div>
		<form class="wrap base-info">
			<ul>
			    <li>
			    	<h5>姓名</h5>
			    	<input type="text" name="username">
			    	<div class="msg-lay"></div>
			    </li>
			    <li>
			    	<h5>性别</h5>
			    	<dl>
			    		<dd>
			    			<input type="radio" class="magic-radio" id="user_1" name="sex" checked="" value="true">
			    			<label for="user_1">先生</label>
			    		</dd>
			    		<dd>
			    			<input type="radio" class="magic-radio" id="user_2" name="sex" checked="" value="false">
			    			<label for="user_2">女士</label>
			    		</dd>
			    	</dl>
			    </li>
			    <li>
			    	<h5>出生日期</h5>
			    	<%--<input type="text" name="birthday">--%>
					<input class="date" name="birthday" id="date"
						   style="width: 380px; height: 40px; border: 1px solid #cacaca;
						   text-indent: 16px; outline-color: #369239; float: left;
						   margin-right: 18px; font-size: 14px;" readonly>
			    	<div class="msg-lay"></div>
			    </li>
			    <li>
			    	<h5>所在省份</h5>
			    	<select name="province">
			    		<option value="广东">广东</option>
			    		<option value="广西">广西</option>
			    	</select>
			    	<div class="msg-lay"></div>
			    </li>
			    <li>
			    	<h5>所在城市</h5>
			    	<select name="city">
			    		<option value="广州">广州</option>
			    		<option value="深圳">深圳</option>
			    	</select>
			    	<div class="msg-lay"></div>
			    </li>
			    <li>
			    	<h5>所在区县</h5>
			    	<select name="country">
			    		<option value="天河">天河</option>
			    		<option value="海珠">海珠</option>
			    	</select>
			    	<div class="msg-lay"></div>
			    </li>
			    <li class="step-2-btn">
			    	<h5></h5>
					<center>
			    	<a href="javascript:void(0)" class="btn-1">返回修改</a>
			    	<a href="javascript:void(0)" class="btn-1 btn-2" onclick="submitUserInfo()">确认提交</a>
					</center>
			    </li>
				<input type="hidden" name="userId" value="${userId}">
			</ul>
		</form>
	</section>
<%@include file="../../context/footer.jsp"%>
<script src="/static/platform/v2.2.0/js/register/member/validate.js"></script>
<script>
    jeDate({
        dateCell : "#date",
        format:"YYYY-MM-DD",
        isinitVal:true,
        initAddVal:[0],
        minDate:"1900-01-01",
        maxDate: jeDate.now(0),
        startMin:"1900-01-01",
        startMax:jeDate.now(0),
        zindex: 999,
    });
</script>
</body>
</html>
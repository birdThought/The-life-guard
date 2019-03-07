<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
  <head>
    <title>找回密码</title>
    <meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="find your password">
	<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
	<link rel="stylesheet" href="/static/css/findPwd.css">
	<link rel="stylesheet" href="/static/common/css/mainHeader.css">
	<t:base type="jquery,layer"></t:base>
	<script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
	<script type="text/javascript" src="/static/login/js/findPwd.js"></script>
	<script type="text/javascript" src="/static/common/js/common.js"></script>
	<script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
</head>
 
 <body>
 <%@include file="/context/mainHeader.jsp"%>
	<div class="content">
	   <div class="web-width">
	    	<div class="smallTitle">
	    		<h3>找回密码</h3>
	    	</div>
	     	<div class="liucheng" style="height:80px;">
	     		<div class="line"></div>
	     		<div class="buzhou">
	     			<img src="/static/images/user.png"/>
	     			<p>账号信息</p>
	     		</div>
	     		<div class="buzhou" style="width:35%">
	     			<img src="/static/images/resetPwd.png"/>
	     			<p>重置密码</p>
	     		</div>
	     		<div class="buzhou">
	     			<img src="/static/images/finish.png"/>
	     			<p>完成</p>
	     		</div>
	     	</div>
	     	<form class="findPsw" action="releaseControl.do?checkValidCode" method="POST">
				<ul>
				   <li class="infor">
						<label for="phoneOrEmail">手机号码/邮箱</label><br/>
						<input style="color: #000;" type="text" class="mobileOrEmail" name="mobileOrEmail" placeholder="请输入手机或者邮箱" id="mobileOrEmail"/>
						<input type="hidden" name="userId" />
		  			</li>
		  			<li class="icode">
					    <label for="idcode">验证码</label><br>
						<input type="text" name="code" placeholder="请输入验证码" id="code">
						<input type="button" id="btnCode" value="获取验证码">
		  			</li>
		  			<li class="infor tijiao">
		  				<input type="submit" value="下一步" style="border:none">
		  			</li>
				</ul>
			</form>
	   </div><!--web-width/-->
  	</div><!--content/--> 	
  	<%@include file="/context/mainFooter.jsp"%>
</body>
<script src="/static/officialwebsite/js/sendCode.js"></script>
</html>

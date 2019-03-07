<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>个人信息</title>
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
	<link rel="stylesheet" href="/static/css/userInfor.css">
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<t:base type="jquery,layer"></t:base>
	
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap" >
			<div class="title fl">主页</div>
			<div class="container fr">
				<h3>修改密码</h3>
				<form name="userPwd">
					<ul class="setInfor">
						<li><label>旧密码</label> <input type="text"
							name="password_old" class="height" placeholder="请输入旧密码" onfocus="this.type='password'" autocomplete="off">
						</li>
						<li><label>新密码</label> <input type="text"
							name="password_new" id="password_new" placeholder="请输入新密码" onfocus="this.type='password'" autocomplete="off">
						</li>
						<li class="confirm"><label>确认密码</label> <input
							type="text" name="password_comf" placeholder="请确定密码" onfocus="this.type='password'" autocomplete="off">
						</li>
					</ul>
					<button type="submit" class="btn_1">确定</button>
				</form>
			</div>
				</div>
		</div>
	</div>
</body>
	<script>
	$(function(){
		menuSetting({
			parent : 5,
			child : 4
		});
	});
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/login/js/icheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/messages_zh.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/validate.expand.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/com/lifeshs/member/js/userPwd.js"></script>
</html>
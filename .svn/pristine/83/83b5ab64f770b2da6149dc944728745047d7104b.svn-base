<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>邮箱绑定</title>
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" type="text/css" href="/static/css/userBinfor.css">
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<t:base type="jquery,layer"></t:base>
	<script>
		window.onload=function () {
			menuSetting({
				parent : 5,
				child : 0
			});
		}
	</script>
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">主页</div>
			<div class="container fr">
				<h3>邮箱绑定</h3>
				<div class="web-width">
					<div class="for-liucheng">
						<div class="liulist for-cur"></div>
						<div class="liulist"></div>
						<div class="liulist"></div>
						<div class="liutextbox">
							<div class="liutext for-cur">
								<em>1</em> <br />
								<strong>号码验证</strong>
							</div>
							<div class="liutext">
								<em>2</em> <br />
								<strong>新邮箱验证</strong>
							</div>
							<div class="liutext">
								<em>3</em> <br />
								<strong>完成</strong>
							</div>
						</div>
					</div>
					<!--for-liucheng/-->
					<form class="findPsw" action="memberControl.do?checkModifyEmailCode" method="POST">
						<ul>
							<li>
								<label>邮箱</label>
								<input type="text" placeholder="邮箱" id="email" value="${email}" disabled />
								<input type="hidden" name="Bemail_verified" value="${verified}" />
							</li>
							<li>
								<label>验证码</label>
								<input type="text" name="idcode" placeholder="请输入验证码" class="idcode">
								<input type="button" id="btnCode"value="获取验证码"></li>
							<li>
								<input type="submit" value="下一步" class="tijiao">
							</li>
						</ul>
					</form>
				</div>
				<!--web-width/-->
			</div>
				</div>
			<!--content/-->
		</div>
	</div>
</body>
<script type="text/javascript" src="/static/com/lifeshs/member/js/bindEmail.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/sendCode.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/js/userBemail.js"></script>
</html>
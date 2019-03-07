<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>个人信息</title>
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
		<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" href="/static/css/userInfor.css">
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<t:base type="jquery,layer"></t:base>
	<script>
		window.onload=function () {
			menuSetting({
				parent : 5,
				child : 2
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
				<h3>预警&联系人</h3>
				<form action="">
					<ul class="setInfor">
						<li><label>预警号码</label> <input type="text" class="height"
							name="sosMobile" placeholder="请输入号码"></li>
						<li>
							<dl class="instruction">
								<dt>说明：</dt>
								<dd>
									<small>需要惊醒设备绑定，输入imei号并提交，系统判断imei号是否存在并未被绑定，则可绑定成功</small>
								</dd>
							</dl>
						</li>
						<li><label>亲情号码1</label> <input type="text"
							name="familyMobile1" value="" placeholder="请输入号码"></li>
						<li><label>亲情号码2</label> <input type="text"
							name="familyMobile2" value="" placeholder="请输入号码"></li>
						<li><label>亲情号码3</label> <input type="text"
							name="familyMobile3" value="" placeholder="请输入号码"></li>
					</ul>
					<dl class="instruction">
						<dt>说明：</dt>
						<dd>
							<small>需要惊醒设备绑定，输入imei号并提交，系统判断imei号是否存在并未被绑定，则可绑定成功</small>
						</dd>
					</dl>
					<button type="button" class="btn">确定</button>
				</form>
			</div>
				</div>
		</div>
	</div>
</body>
</html>
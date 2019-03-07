<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>修改手机</title>
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" type="text/css" href="/static/css/userBinfor.css">
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<t:base type="jquery,layer"></t:base>
	<script type="text/javascript" src="/static/officialwebsite/js/userInfo.js"></script>
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">主页</div>
			<div class="container fr">
				<h3>号码绑定</h3>
				<div class="web-width">
					<div class="for-liucheng">
						<div class="liulist for-cur"></div>
						<div class="liulist for-cur"></div>
						<div class="liulist for-cur"></div>
						<div class="liutextbox">
							<div class="liutext for-cur">
								<em>1</em> <br />
								<strong>旧号码验证</strong>
							</div>
							<div class="liutext for-cur">
								<em>2</em> <br />
								<strong>新号码验证</strong>
							</div>
							<div class="liutext for-cur">
								<em>3</em> <br />
								<strong>完成</strong>
							</div>
						</div>
					</div>
					<!--for-liucheng/-->
					<div class="success">
						<img src="/static/images/OK.png" alt="">
						<ul>
							<li><span>号码已修改完成！</span></li>
							<!-- <li><a href="javascript:_logout();">请重新登录</a></li> -->
						</ul>
					</div>
					<div style="clear: both"></div>
				</div>
				<!--web-width/-->
			</div>
				</div>
			<!--content/-->
		</div>
	</div>
	<input type="hidden" name="result" value="${result}" />
	<input type="hidden" name="msg" value="${msg}" />
</body>
<script type="text/javascript" src="/static/com/lifeshs/member/js/bindMobile.js"></script>
<script>
jQuery(function(){
	menuSetting({
		parent : 5,
		child : 0
	});
	var result = jQuery("input[name='result']").val();
	switch(result){
	case "0":
		// 失败(首验)
		var msg = jQuery("input[name='msg']").val();
		bindMobile.firstBindFail(msg);
		break;
	case "1":
		// 成功(首验)
		bindMobile.firstBindSuccess();
		break;
	case "2":
		// 成功
		break;
	}
});
</script>
</html>
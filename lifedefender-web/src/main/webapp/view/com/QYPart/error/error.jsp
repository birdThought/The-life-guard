<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>

<html>
<head>
<title>主页</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
	<link rel="stylesheet" type="text/css"
	href="/static/common/css/QYcomCss.css">
<link rel="stylesheet" href="/static/com/QYPart/css/data_check.css">
<style type="text/css">
.container h3{
	height:60px;
	line-height:60px;
	font-size:18px;
	font-weight:400;
	margin:0 30px 20px;
	border-bottom:1px dashed #ddd;
}
.container p{
	text-align:center;
	font-size:16px;
}
</style>
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script>
	window.onload = function() {
		menuSetting({
			parent : 0,//父级菜单的索引
			child : 0,
			home:true
		//子级菜单的索引
		});
		$(".level div").animate({"width":"70%"},800);
	}
	function jumpToUrl(target){
		switch(target){
			case 0:
			window.location.href="orgControl.do?orgManage";
			break;
		}
	}
</script>
</head>
<body>
<%@include file="/context/QYHeader.jsp"%>
<div class="container">
	<%@include file="/context/QYMenu.jsp"%>
	<div class="right_content">
		<div class="right_body">
			<h3>信息提示</h3>
			<p>${error}</p>
		</div>
	</div>
</div>
</body>
<script>
$(function(){
	
});
</script>
</html>
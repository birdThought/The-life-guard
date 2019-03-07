<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title></title>

		<style>
			body {
				margin: 0;
				padding: 0;
			}
			.tip_shopcar{
				color: #44c660;
				text-align: center;
				padding: 1rem 0 1rem 0;
			}
			.button_shopcar_null{
				color: #44c660;
				text-align: center;
				padding: 0.8rem 0 0.8rem 0;
				border:1px solid #44c660;
				border-radius: 0.1rem;
			}
		</style>
		<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent.css" />
		<!--自适应js-->
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/js/jq.js"></script>
		<script type="text/javascript">
			function returnPage(){
				window.history.go(-1);
			}
			
			function goIndex(){
				window.location = "/shop/index";
			}
		</script>
	</head>

	<body>
		<!--标题-->
		<div class="mainbg main_title">
			<div style="height: 3.5rem;line-height:3.5rem;outline: 1px solid #f4f4f4;">
				<div class="main_title_img"><img src="/static/images/shop/img/icon_return.png" style="width: 0.6rem;height: 0.8rem;"  onclick="returnPage()"/></div>
				<div class="main_title_text">购物车</div>
				<div style="text-align: right;">编辑</div>
			</div>
		</div>
		<!--end 标题-->
		
		<div style="position: absolute;text-align:center;top:50%;left:50%;width: 10rem;height: 10rem;margin-top: -5rem;margin-left: -5rem;">
			<img src="/static/images/shop/img/shopcar_null.png" style="width: 100%;height: 100%;"/>
			<div class="tip_shopcar">您的购物车是空的哦！</div>
			<div class="button_shopcar_null" onclick="goIndex()">去逛逛</div>
		</div>
	</body>

</html>
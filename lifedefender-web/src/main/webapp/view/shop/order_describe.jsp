<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title></title>
		<script type="text/javascript" src="/static/js/jq.js" ></script>
		<style>
			body {
				margin: 0;
				padding: 0;
				background-color: #f0f0f0;
			}
			/*头部*/
			
			.header_shop {
				position: fixed;
				top: 0;
				width: 100%;
				list-style-type: none;
				margin: 0;
				padding: 0;
				height: 1.5rem;
				padding: 0.8rem 0 0.8rem 0;
				border-bottom: 1px solid #f4f4f4;
				background-color: #44c05e;
			}
			
			.header_shop>li {
				float: left;
			}
			
			.header_shop .header_icon {
				width: 20%;
				text-align: left;
			}
			
			.header_shop .header_title {
				width: 60%;
				text-align: center;
			}
			/*商品条目*/
			
			.bar_div {
				/*position: relative;*/
				/*top:1.5rem;*/
				background-color: white;
				margin-top: 1rem;
				margin-bottom: 1rem;
			}
			
			.bar_header {
				border: 1px solid #f0f0f0;
				padding: 0.8rem 0.5rem 0.8rem 0.5rem;
			}
			
			.bar_list {
				padding: 0.5rem 0.5rem 0.5rem 0.5rem;
			}
			/*商品块*/
			
			.product_bar {
				background-color: white;
				overflow: hidden;
				padding: 0.5rem 0 0.5rem 0;
				margin-bottom: 1rem;
			}
			
			.product_bar_header {
				padding: 0.8rem 0.5rem 0.8rem 0.5rem;
				border-bottom: 1px solid #eeeeee;
			}
			
			.product_bar_img {
				float: left;
				margin: 0.5rem 0.5rem 0 0.5rem;
				padding: 0.5rem 0.5rem 0.5rem 0.5rem;
				border: 1px solid #eeeeee;
				background-color: white;
				border-radius: 0.5rem;
			}
			
			.product_bar_title_text {
				margin-top: 0.5rem;
				display: flex;
				flex-direction: column;
				padding-left: 1rem;
				padding-right: 1rem;
			}
			
			.product_bar_title {
				display: flex;
				flex-direction: row;
				justify-content: space-between;
			}
		</style>
		<style>
			.button_group {
				padding: 0.5rem 1rem 0.5rem 1rem;
				display: flex;
				justify-content: space-around;
			}
			
			.button_group>div {
				width: 50%;
				float: left;
				text-align: center;
				/*outline: 1px  solid red;*/
				padding: 1rem 0.5rem 1rem 0.5rem;
				margin-left: 0.3rem;
				margin-right: 0.3rem;
				background-color: #f8f8f8;
				border: 1px solid #e7e7e7;
				border-radius: 0.5rem;
			}
			.bar_list_between{
				display: flex;
				justify-content: space-between;
				padding-left: 1rem;
				padding-right: 1rem;
			}
		</style>

	</head>

	<body>
		<!--头选择项-->
		<ul class="header_shop">
			<li class="header_icon" onclick="backOff()"><img src="/static/images/shop/img/icon_return.png" style="width: 0.7rem;height: 1.2rem;margin-left: 1rem;" /></li>
			<li class="header_title" style="height: 1.2rem;">
				<label>订单详情</label>
				<label></label>
			</li>
			<li class="header_icon" style="text-align: right;"><img src="/static/images/shop/img/share.png" style="width: 1.2rem;height: 1.2rem;margin-right: 1rem;" /></li>
		</ul>
		
		<ul class="header_shop" style="position: static;"></ul>
		<!--end 头选择项-->
		<!--各内容块-->
		<div class="bar_div">
			<div class="bar_list">订单编号:&nbsp;&nbsp;${order[0].order.orderNo}</div>
			<div class="bar_list">下单时间:&nbsp;&nbsp;<fmt:formatDate value="${order[0].order.createTime}" pattern="yyyy-MM-dd hh:mm" /></div>
			<c:if test="${order[0].order.status  == 1}">
    			<div class="bar_list">交易状态:&nbsp;&nbsp;未支付</div>
			</c:if>
			<c:if test="${order[0].order.status  == 2}">
    			<div class="bar_list">交易状态:&nbsp;&nbsp;已支付</div>
			</c:if>
			<c:if test="${order[0].order.status == 3}">
    			<div class="bar_list">交易状态:&nbsp;&nbsp;已完成</div>
			</c:if>
		</div>
		<!--end 各内容块-->

		<!--各内容块-->
		<div class="bar_div">
			<div class="bar_header">收货地址</div>
			<div class="bar_list">收货人:&nbsp;&nbsp;${order[0].receiverName}</div>
			<div class="bar_list">联系方式:&nbsp;&nbsp;${order[0].contactNumber}</div>
			<div class="bar_list">收货地址:&nbsp;&nbsp;${order[0].address}</div>
		</div>
		<!--end 各内容块-->

		<!--信息-->
		<!-- <div class="product_bar">
			<div class="product_bar_header">生命守护健康商城</div>

			<div class="product_bar_img" style="border:0;">
				<img src="/static/images/shop/img/productimg.png" style="width: 5rem;" />
			</div>

			<div class="product_bar_title_text">
				<div style="">
					<label>宫偌 人参皂匠</label>
					<label></label>
				</div>
				<div style="padding:0.5rem 0 0.5rem 0;color:#b4b4b4;font-size: 0.8rem;">商品套餐名称</div>
				<div style="color: #333333;">￥199.00&nbsp;&nbsp;<label style="color: #868686;">X2</label></div>
			</div>

		</div> -->
		<!--end 信息-->

		<!--按钮组-->
		<div class="bar_div button_group"  >
			<div>联系客服</div>
			<div>申请售后</div>
		</div>
		<!--end 按钮组-->

		<!--各内容块-->
		<div class="bar_div">
			<div class="bar_header">支付信息</div>
			<div class="bar_list bar_list_between">
				<label>商品总额:</label>
				<label>￥${order[0].order.money}</label>
			</div>
			<div class="bar_list bar_list_between">
				<label>运费:</label>
				<label>+￥0</label>
			</div>
			<div class="bar_list bar_list_between">
				<label>优惠劵:</label>
				<label>-￥0</label>
			</div>
		</div>
		<!--end 各内容块-->
		
		<!--实付金额-->
		<div style="padding:1rem 1rem 1rem 1rem;background-color: white;text-align: right;color: red;border-top: 1px solid #f0f0f0;border-bottom: 1px solid #f0f0f0;">实付金额：￥49</div>
		<!--end 实付金额-->
		
		<style>
			.more_div{
				position: fixed;
				bottom: 0;
				width: 100%;
				text-align: center;
				background-color: white;
				border-top: 1px solid #f0f0f0;
			}
			.option_left{
				position: relative;
				float: left;
				width: 40%;
				padding:1rem 0 1rem 0;
				/*outline: 1px solid red;*/
			}
			.option_left_list{
				position: absolute;
				display: none;
				top: -2rem;
				left: 50%;
				margin: 0;
				padding: 0;
				list-style-type: none;
				
			}
			.option_left_list  div{
				color: white;
				font-size: 0.8rem;
				text-align: center;
				padding:0.2rem 0.4rem 0.2rem 0.4rem;
				background-color: black;
				border-top:1px solid white;
			}
			.option_right{
				display: flex;
				justify-content: space-around;
				padding:0.5rem 0 0.5rem 0;
				/*outline: 1px solid red;*/			
				
			}
			.option_right > div{
				float: left;
				width: 50%;
				padding:0.5rem 0 0.5rem 0;
				background-color: #44c05e;
				color: white;
				margin-left: 1rem;
				margin-right: 1rem;
				border-radius: 0.5rem;
				/*outline: 1px solid red;*/
			}
		</style>
		<!--更多选择-->
		<div class="more_div">
			<div class="option_left">
				<label id="more">更多</label>
				<div id="option_left_list" class="option_left_list">
					<div id="more_after">售后服务</div>
					<div id="more_bar">申请开票</div>
				</div>
			</div>
			<div class="option_right">
				<!-- <div>查看物流</div> -->
				<div>评论</div>
			</div>
		</div>
		<!--end 更多选择-->
	</body>
	<script>
		$('#more').click(function(){
			$('#option_left_list').css('display','block');
		});
		
		$('#more_after').click(function(){
			$('#option_left_list').css('display','none');
			//跳转售后服务
			
		});

		$('#more_bar').click(function(){
			$('#option_left_list').css('display','none');
//			跳转申请开票
		});
		
		function backOff(){
			history.go(-1);
		};
		
	</script>

</html>
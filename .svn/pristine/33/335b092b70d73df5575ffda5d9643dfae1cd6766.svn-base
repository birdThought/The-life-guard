<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>机构首页</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico
"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/QYcomCss.css">
<link rel="stylesheet" href="/static/com/QYPart/css/data_check.css">
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/dataCount.js"></script>
<script>
	window.onload = function() {
		document.cookie = "orgType=" + ${orgType};
		menuSetting({
			home:true
		});
		$(".level div").animate({
			"width" : "70%"
		}, 800);
	}
</script>
</head>

<body>
	<%@include file="/context/QYHeader.jsp"%>
	<div class="container">
		<%@include file="/context/QYMenu.jsp"%>
		<div class="right_content">
			<div class="right_body">
				<!--公司信息-->
				<div class="information_contain">
					<img class="avater" src="/static/images/head.png" />
					<div class="info">
						<h1 class="company">东圃门店</h1>
						<div class="other_info">
							诚信认证：
							<div class="custom_renzheng">
								<img src="/static/images/certi.png" /><span>企业认证</span>
							</div>
							<div class="custom_renzheng">
								<img src="/static/images/iphone.png" /><span>号码认证</span>
							</div>
							<div class="custom_renzheng" style="margin-left:45px">
								<img src="/static/images/email.png" style="top:6px;left:-30px;" /><span>邮箱认证</span>
							</div>
						</div>
						<div class="other_info" style="font-size:16px">
							账号类型：<span class="admin">管理员</span><img
								src="/static/images/msg_i.png" class="level_icon" /><span>安全等级：</span>
							<div class="level">
								<div></div>
							</div>
							<span class="level_text">中</span>
						</div>
					</div>
				</div>
				
				<!--本周订单数据-->
				<div class="column_contain transform_data">
					<div class="column_title">
						<div class="date_count">
							<button class="action" id="week"
								onclick="dataCountObject.selectTradeTime(this)">本周</button>
							<button id="month"
								onclick="dataCountObject.selectTradeTime(this)">本月</button>
						</div>
						<h2 style="border-left:none;padding-left:5px">本周订单数据</h2>
					</div>

					<div class="company_item_contain transform_msg_data"
						style="padding-top:0;padding-bottom:0">
						<div class="company_item transform_msg_data data"
							style="width:50%;border-right:1px solid #ddd;">
							<div class="item_logo data_analysis_img"
								style="background:#3598dc;">
								<img src="/static/images/order.png" />
							</div>
							<div class="msg" style="padding-top:20px">
								<h2 style="font-size:30px">0</h2>
								<p>订单量</p>
							</div>
						</div>

						<div class="company_item transform_msg_data data">
							<div class="item_logo data_analysis_img"
								style="background:#ffc333;">
								<img src="/static/images/money.png" />
							</div>
							<div class="msg" style="padding-top:20px">
								<h2 style="font-size:30px">0</h2>
								<p>交易额</p>
							</div>
						</div>
					</div>
				</div>
<!--流量统计-->
				<div class="column_contain transform_data">
					<div class="column_title">
						<div class="date_count">
							<button class="action" id="yestoday"
								onclick="dataCountObject.selectTrafficTime(this)">昨天</button>
							<button id="servenday"
								onclick="dataCountObject.selectTrafficTime(this)">最近7天</button>
						</div>
			
							<h2 style="border-left:none;padding-left:5px">流量统计
							</h2>
					</div>

					<div
						class="company_item_contain transform_msg_data liuliang_noresult"
						style="padding:0;">
						<h2>
							商铺访问量&nbsp;&nbsp;<span class="count">0</span>
						</h2>
						（较前日<span style="color:#5ed3a9">持平</span>）
					</div>
				</div>
				<!--个人会员信息-->
				<div class="column_contain transform_data">
					<div class="column_title">
						<div class="date_count">
							<span class="unit"> （单位：个） </span>
						</div>
						<h2 style="border-left:none;padding-left:5px">个人会员信息</h2>
					</div>

					<div class="company_item_contain transform_msg_data">
						<ul class="msg_data_list">
							<li>
								<p>今天新增</p> <span>0</span>
							</li>
							<li>
								<p>本周新增</p> <span>0</span>
							</li>
							<li>
								<p>本月新增</p> <span>6</span>
							</li>
							<li>
								<p>共有会员</p> <span>0</span>
							</li>
						</ul>
					</div>
				</div>
				<!--其他信息-->
				<div class="column_contain transform_data">
					<div class="column_title">
						<div class="date_count">
							<span class="unit"> （单位：个） </span>
						</div>
						<h2 style="border-left:none;padding-left:5px">会员访问信息</h2>
					</div>

					<div class="company_item_contain transform_msg_data">
						<ul class="msg_data_list">
							<li>
								<p>用户登录数</p> <span>99</span>
							</li>
							<li>
								<p>使用服务数</p> <span>0</span>
							</li>
							<li>
								<p>员工登录数</p> <span>6</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

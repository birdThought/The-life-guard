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
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script>
	window.onload = function() {
		document.cookie = "orgType=" + ${orgType};
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
				<!--公司信息-->
				<div class="information_contain">
					<img class="avater" src=""
						style="width:120px; height: 134px; margin:0" />
					<div class="info">
						<h1 class="company">企业名称</h1>
						<div class="other_info">
							诚信认证：
							<div class="custom_renzheng">
								<img src="/static/images/certi.png" />
								<span>企业认证</span>
							</div>
							<div class="custom_renzheng">
								<img src="/static/images/iphone.png" /><span>号码认证</span>
							</div>
							<div class="custom_renzheng" style="margin-left:45px">
								<img src="/static/images/email.png" style="top:6px;left:-30px;" /><span>邮箱认证</span>
							</div>
						</div>
						<div class="other_info" style="font-size:16px">
							账号类型：<span class="admin">服务师</span><img src="/static/images/msg_i.png"
								class="level_icon" /><span>安全等级：</span>
							<div class="level">
								<div></div>
							</div>
							<span class="level_text">中</span>
						</div>
					</div>
				</div>
				<!--机构信息-->
				<div class="column_contain">
					<div class="column_title">
						<a href="#" class="more">更多&nbsp;></a>
						<h2>机构信息</h2>
					</div>
					<div class="company_item_contain">
						<div class="company_item">
							<div style="cursor:pointer" onclick="jumpToUrl(0)" class="item_logo">
								<img src="/static/images/jigou_logo.png" />
							</div>
							<div class="msg">
								<h2 style="cursor:pointer" onclick="jumpToUrl(0)" onmouseover="this.style.textDecoration='underline'" onmouseleave="this.style.textDecoration='none'">子机构</h2>
								<p>子机构数：0</p>
							</div>
						</div>
						<div class="company_item">
							<div style="cursor:pointer;background:#48c858;" onclick="jumpToUrl(0)"  class="item_logo">
								<img src="/static/images/door.png" />
							</div>
							<div class="msg">
								<h2 style="cursor:pointer" onclick="jumpToUrl(0)" onmouseover="this.style.textDecoration='underline'" onmouseleave="this.style.textDecoration='none'">门店</h2>
								<p>门店数:0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会员数:0</p>
							</div>
						</div>
					</div>
				</div>

				<div class="column_contain" style="width:48%;float:left">
					<div class="column_title">
						<a href="#" class="more">更多&nbsp;></a>
						<h2>公告</h2>
					</div>
					<ul class="news_list">
						<li>-【公告】生命守护现全面招商，赶紧叫上伙伴...</li>
						<li>-【公告】生命守护现全面招商，赶紧叫上伙伴...</li>
						<li>-【公告】生命守护现全面招商，赶紧叫上伙伴...</li>
						<li>-【公告】生命守护现全面招商，赶紧叫上伙伴...</li>
						<li>-【公告】生命守护现全面招商，赶紧叫上伙伴...</li>
					</ul>
				</div>

				<div class="column_contain" style="width:48%;float:right">
					<div class="column_title">
						<a href="#" class="more">更多&nbsp;></a>
						<h2>推送服务</h2>
					</div>
					<ul class="news_list">
						<li>以下省略999个字..以下省略999个字以下省略999个字以下省略999个字以下省略999个字</li>
						<li>今日头条大揭秘！10w+推荐，算个Pi？</li>
						<li>微信运营人必备的四大装逼神器</li>
						<li>运营内行人才知道的“微信群搜索大法”！你...</li>
						<li>做活动，到底能不能吸来高质用户？</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
jQuery(function() {
	jQuery.ajax({
		type: 'GET',
		url: 'orgControl.do?getHomeData',
		success: function(result) {
			var data = result.attributes;
			jQuery("h1.company").text(data.orgName);	// 名称
			jQuery("div.other_info>span.admin").text(data.userType);	// 用户类型
			if(data.logo != null && data.logo != "") {
				jQuery("img.avater").attr("src", data.logo);
			}
			
			var $management = jQuery("div.company_item_contain>div.company_item:eq(0)");
			$management.find("div.msg>p").html("子机构数："+data.managementAmount);
			
			var $service = jQuery("div.company_item_contain>div.company_item:eq(1)");
			$service.find("div.msg>p").html('门店数：'+data.serviceAmount+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会员数：'+data.memberAmount);
			
			if (data.orgVerified) {
				jQuery("div.other_info>div.custom_renzheng:eq(0)>img").attr("src", "/static/images/certi_on.png");
			}
		}
	});
});
</script>
</html>

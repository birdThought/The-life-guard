<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="description" content="keywords">
<title>服务师</title>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" href="/static/css/serviceDoctor.css">
<t:base type="jquery,layer"></t:base>
</head>
<script type="text/javascript">
jQuery(function() {
	menuSetting({
		parent: 3,
		child: 0
	});

	var classifies = '${orgUser.classify}';
	if(classifies != "") {
		var $root = jQuery("ul.serviceDoctor>li").eq(1);
				
		var tags = "";
		var classifyArray = classifies.split(",");
		for(var i = 0; i < classifyArray.length; i++) {
			tags += '<span class="doctor-bg">'+classifyArray[i]+'</span>';
		}
		$root.find("p").eq(0).after($(tags));
	}
});
</script>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap" >
			<div class="title fl">
				<a href="/login" class="top_cite">主页</a> &nbsp;&nbsp;>&nbsp;&nbsp; <a
					href="serviceControl.do?healthConsultPage" class="top_cite">服务</a> &nbsp;&nbsp;>&nbsp;&nbsp; <a href="#" class="top_cite">服务师信息</a>
			</div>
			<div class="container fr">
				<ul class="serviceDoctor">
					<li><img src="${orgUser.photo}" alt=""></li>
					<li style="height:114px;">
						<p>
							<span>${orgUser.realName}</span><small>服务师</small>
						</p><!--  <span class="doctor-bg">糖尿病</span> <span class="doctor-bg">高血压</span> -->
						<dl>
							<dt>
								<label>所属机构：</label>${orgUser.orgName}
							</dt>
							<dd>
								<label>简介：</label>${orgUser.about}<!-- <a>更多</a> -->
							</dd>
						</dl>
					</li>
					<li>
						<div>
							<div>
								<img src="/static/images/star.png" alt="star"> <img
									src="/static/images/star.png" alt="star"> <img
									src="/static/images/star.png" alt="star"> <img
									src="/static/images/star.png" alt="star"> <img
									src="/static/images/star2.png" alt="star">
							</div>
							<p>
								问诊数：<span>2131</span>
							</p>
							<p>
								综合评分：<span>4.0</span>
							</p>
						</div>
					</li>
				</ul>
				<div class="service-title">
					<div>
						<h3>服务师简介</h3>
					</div>
					<div class="introduce">${orgUser.detail}</div>
				</div>
				<div class="service-title">
					<div>
						<h3>服务</h3>
					</div>
					<ul class="service-list">
					<c:forEach items="${orgUser.serves}" var="s">
					<li><img alt="" src="${s.image}">
						<p>${s.name}</p>
						<p>已开通功能</p></li>
					</c:forEach><!-- 
						<li><img src="/static/images/image-consult.png" alt="">
							<p>图文咨询</p>
							<p>已开通功能</p></li>
						<li><img src="/static/images/phone-consult.png" alt="">
							<p>电话咨询</p>
							<p>暂未开通功能</p></li>
						<li><img src="/static/images/other-consult.png" alt="">
							<p>其他咨询</p>
							<p>暂未开通功能</p></li> -->
					</ul>
				</div>
			</div>
				</div>
		</div>
	</div>
</body>
</html>
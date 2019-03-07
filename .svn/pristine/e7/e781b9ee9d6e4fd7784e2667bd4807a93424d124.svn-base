<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="description" content="keywords">
    <title>生命守护官网  健康管理系统</title>
	<t:base type="jquery,layer"></t:base>
    <link rel="stylesheet" href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/common/css/mainHeader.css">
    <link rel="stylesheet" href="/static/css/portal/userService.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <script type="text/javascript" src="/static/officialwebsite/index/js/HealthServiceOrgDetail.js"></script>
</head>
<body>
	<div class="container">
		<%@include file="/context/mainHeader_1.jsp"%>
		<div style="background: #48c858;height: 200px;width: 100%;position: absolute;top: 90px;z-index: -11;"></div>
		<div class="service_org hwrap">
			<c:if test="${!empty item.parent}">
			<ul class="item-introduce " style="margin-bottom:30px;">
        	    <li>
        	    	<img src="${item.parent.logo}" alt="" onerror="this.src='/static/images/index/nopic.jpg'">
        	    </li>
        	    <li>
        	    	<h3>${item.parent.name}</h3>
        	    	<p>${item.parent.about}</p>
        	    </li>
        	</ul>
			</c:if>
        	<h3 class="shop-infor">门店信息</h3>
        	<div class="service-organize item-content">
	        	<div class="item-introduce"><img src="${item.logo}" alt="" onerror="this.src='/static/images/index/nopic.jpg'"></div>
	        	<div class="serviceCenter">
	        	    <h3>${item.name}</h3>
	        	    <p>${item.about}</p>
	        	    <address>${item.address}</address>
	        	</div>
        	</div>
        </div>
        <div class="item-service hwrap">
            <div class="item-subTiletle">
                <h3>服务项目</h3>
            </div>
            <c:forEach items="${item.serves}" var="s" varStatus="status">
            <div class="healthy-consult" style="border: 1px solid #ddd; padding: 20px 10px; margin-bottom: 20px;">
            	<ul class="item-introduce">
            	    <li>
            	    	<img src="${s.image}" alt="" onerror="this.src='/static/images/index/nopic.jpg'">
            	    </li>
            	    <li>
            	    	<h3>${s.name}</h3>
            	    	<p>${s.about}</p>
            	    	<p>
            	    	<c:forEach items="${s.classify}" var="c">
            	    	<span>${c}</span>
            	    	</c:forEach>
            	    	</p>
            	    </li>
            	</ul>
            	<ul class="item-price">
            		<c:forEach items="${s.price}" var="p" varStatus="pStatus">
            		<li>
            	    	<input type="radio" name="price_${status.index}" value="${p.type}" <c:if test="${pStatus.index==0}">checked</c:if>>
            	    	<em>${p.value}</em> <small>${p.unit}</small>
            	    </li>
            		</c:forEach>
            		<c:if test="${!empty s.price}">
            		<li>
            	    	<button class="goBuy" data-id="${s.id}">预约购买</button>
            	    </li>
            		</c:if>
            	</ul>
            </div>
            </c:forEach>
        </div>
        <div class="item-service hwrap">
            <div class="item-subTiletle">
                <h3>详细介绍</h3>
            </div>
            <div style="text-indent: 2em;font-size:15px;color:#777;">${item.detail}</div>
        </div>
        <%@include file="/context/mainFooter_1.jsp"%>
    </div>
</body>
</html>

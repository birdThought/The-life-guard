<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>生命守护官网  健康管理系统</title>
    <t:base type="jquery,layer"></t:base>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keyword" content="">
    <link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
	<link rel="stylesheet" type="text/css"
	href="/static/common/css/mainHeader.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/userService.css">
    <link rel="stylesheet" href="/static/common/css/pageCss.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
	<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/banner.css">
    <style type="text/css">
        html{overflow-x:hidden; overflow-y:auto;}
    </style>
</head>
<body>
	<div class="container">
		<%@include file="/context/mainHeader_1.jsp"%>
		<div class="healthInfor hwrap">
			<h3>分类</h3>
			<div>
				<input type="search" id="searchCondition" autocomplete="off"
					placeholder="搜索">
				<button id="searchUser" onclick="searchControl.search()">搜索</button>
			</div>
		</div>
		<div class="sortClass hwrap">
			<ul>
				<li style="display: none;">
					<div id="search_action_group" class="search-filter-group">
                    <ul class="search-filter">
                    </ul>
                    <span class="result-text">找到<span>${count}</span>个相关项目
						</span>
                	</div>
				</li>
				<li>
					<ol id="pcName_filter" style="overflow: hidden;">
						<li style="width: 100px;">地区</li>
						<li>北京</li>
						<li>上海</li>
						<li>广州</li>
						<!-- <li style="float: right;">更多</li> -->
					</ol>
				</li>
				<li>
					<ol id="orgType_filter">
						<li style="width: 100px;">机构分类</li>
						<li>慢病康复</li>
						<li>健康养生</li>
						<li>减肥塑体</li>
						<li>居家养老</li>
						<li>癌症康复</li>
						<li>妇婴</li>
						<li>家政</li>
						<li>生殖医学</li>
					</ol>
				</li>
				<li>
					<ol id="service_filter">
						<li style="width: 100px;">服务分类</li>
						<c:forEach var="s" items="${serviceTag}">
						<li>${s.name}</li>
						</c:forEach>
					</ol>
				</li>
			</ul>
		</div>
		<div class="hwrap">
			<div class="sort_nav">
				<label>排序：</label> <span>默认排序</span> <!-- <span>评价</span> <span>恢复速度</span> -->
			</div>
			<ul class="item-content">
			<c:if test="${empty itemList}">
			<li>没有相关数据</li>
			</c:if>
			<c:forEach items="${itemList}" var="i">
			<li>
				<div class="item-img">
					<img src="${i.logo}" alt=""  onerror="this.src='/static/images/index/nopic.jpg'">
				</div>
				<div class="item-news">
					<h3>${i.orgName}</h3>
					<p>${i.about}</p>
					<address>地址 : ${i.address}</address>
				</div>
				<div>
					<span><a href="indexControl.do?healthserviceorgdetails&orgId=${i.orgId}">商家详情</a></span>
					<p>
						<strong>4.5</strong>分
					</p>
					<p>
						<img src="" alt="">
					</p>
					<p>
						(共有<strong>${i.count}</strong>人购买)
					</p>
				</div>
			</li>
			</c:forEach>
			</ul>
			
			<div id="pageManager" class="page_Container">
                <c:choose>
                    <c:when test="${pageCount==1}">
                        <span class="page page_action">1</span>
                    </c:when>
                    <c:when test="${pageCount>1}">
                        <span class="page page_action">1</span>
                        <c:choose>
                            <c:when test="${pageCount>5}">
                                <c:forEach begin="2" end="5" var="p">
                                    <span class="page">${p}</span>
                                </c:forEach>
                                <span class="page_dian">...</span>
                            </c:when>
                            <c:when test="${pageCount<=5}">
                                <c:forEach begin="2" end="${pageCount}" var="p">
                                    <span class="page">${p}</span>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                        <span class="page page_next" id="btn_next">下一页</span>
                        <span style="margin-left:10px">共${pageCount}页，到第</span>
                        <input type="text" class="page_input" id="p_input"/>
                        <span>页</span>
                        <button class="page_input_enter">确定</button>
                    </c:when>
                </c:choose>
            </div>
			
		</div>
		<%@include file="/context/mainFooter_1.jsp"%>
	</div>
</body>
<script type="text/javascript" src="/static/plugins/slider/js/slider.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<script type="text/javascript" src="/static/common/js/CookieUtil.js"></script>
<script type="text/javascript" src="/static/officialwebsite/index/js/HealthServiceOrg.js"></script>
<script type="text/javascript">
searchControl.init({
    fil1ter: {
        service:'${service}',
        orgType:'${orgType}',
        pcName:'${pcName}',
        sort:'${sort}'
    },pageCount:parseInt('${pageCount}')
});
var pageManager = new PageUtil();
pageManager.getPageControl().init({
    container: "pageManager",
    preBtn: "btn_pre",
    nextBtn: "btn_next",
    totalPage: parseInt('${pageCount}'),
    initPage: parseInt('${initPage}'),
    pageChange: function (page) {
        searchControl.pageChange(page);
    }
});
</script>
</html>
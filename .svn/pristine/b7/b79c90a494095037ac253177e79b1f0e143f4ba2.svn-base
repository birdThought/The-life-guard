<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<title>生命守护官网——健康管理/慢病管理云平台</title>
	<meta name="keyword" content="慢病管理,健康管理">
	<meta name="description" content="生命守护专注打造慢病管理、健康管理系统，功能包括健康数据测量与分析、疾病预警、家庭医生、健康问诊、健康课堂、健康档案等，同时为用户提供智能预警检测设备。">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/help.css">
    <t:base type="jquery,layer"></t:base>
	<link rel="stylesheet" href="/static/common/css/pageCss.css">
	<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
	<script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
    <style type="text/css">
        html{overflow-x:hidden; overflow-y:auto;}
        .class_name{font-size:18px;}
		.page:hover {
			background-color: #48c858;
			color: #fff;
		}

		.page_input {
			margin: 0 8px;
			width: 40px;
			border: 1px solid #ddd;
			outline: #48c858;
			padding: 3px 6px
		}

		.page_input_enter:hover {
			color: #48c858
		}
    </style>
	<c:set var="totalPage" value="${data.attr.informations.totalPage}"></c:set>
	<c:set var="nowPage" value="${data.attr.informations.nowPage}"></c:set>
	<style>
		.question_ul{
			padding:15px 25px;font-size:15px;list-style: disc;color:#ccc;min-height: 320px;line-height: 28px
		}
		.question_ul li a:hover{
			text-decoration: underline;
		}
	</style>
</head>
<body>
	<div class="container">
		<%@include file="/context/mainHeader_1.jsp"%>
		<div class="main help-wrap">
		 	<div class="search">
		 		<h3>帮助中心</h3>
		 		<%--<div class="import">--%>
		 			<%--<div style="overflow: hidden;margin-bottom: 10px">--%>
		 				<%--<input type="text" name="" value="" id="searchCondition" style="padding: 0 8px">--%>
		 				<%--<span id="search" style="color:#fff;text-align: center;padding-top: 7px;cursor: pointer;">搜索</span>--%>
		 			<%--</div>--%>
		 			<%--<p style="display: none;">--%>
		 				<%--<label>热键关键词：</label>--%>
		 				<%--<span>--%>
		 					<%--<a href="#">智能设备</a>--%>
	        				<%--<a href="#">健康服务</a>  --%>
	        				<%--<a href="#">找回密码</a>--%>
	        				<%--<a href="#">生命守护</a> --%>
		 				<%--</span>--%>
		 			<%--</p>--%>
		 		<%--</div>--%>
		 	</div>
		 	<div class="main_content">
				<ul class="main_content_nav">
					<c:forEach items="${data.attr.columns}" var="item">
						<li data-id="${item.id}"><a href="informationControl.do?helpCenterIndex&f=${item.id}">${item.name}</a></li>
					</c:forEach>
				</ul>
				<div style="display: inline-block;width: 75%;min-height: 320px;float: left">
					<label id="question" style="color:#48c858;font-size:16px"></label>
					<c:if test="${empty data.attr.informations.dataObject}">
						<div style="font-size: 17px;padding:15px 25px;">暂无结果</div>
					</c:if>
					<ul class="question_ul">
						<c:forEach items="${data.attr.informations.dataObject}" var="item">
							<li><a href="javascript:helpControl.lookNews(${item.id})">${item.title}</a></li>
						</c:forEach>
					</ul>
					<div id="pageManager" class="page_Container" style="float: left">
						<c:choose>
							<c:when test="${totalPage==1}">
								<span class="page page_action">1</span>
							</c:when>
							<c:when test="${totalPage>1}">
								<span class="page page_action">1</span>
								<c:choose>
									<c:when test="${totalPage>5}">
										<c:forEach begin="2" end="5" var="p">
											<span class="page">${p}</span>
										</c:forEach>
										<span class="page_dian">...</span>
									</c:when>
									<c:when test="${totalPage<=5}">
										<c:forEach begin="2" end="${totalPage}" var="p">
											<span class="page">${p}</span>
										</c:forEach>
									</c:when>
								</c:choose>
								<span class="page page_next" id="btn_next">下一页</span>
								<span style="margin-left:10px">共${totalPage}页，到第</span>
								<input type="text" class="page_input" id="p_input"/>
								<span>页</span>
								<button class="page_input_enter">确定</button>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
			<%--<script>
				$("#clickChange >li:gt(0)").css("display","none");
				$(".main_content_nav li").click(function(event) {
					$(this).addClass('class_name').siblings().removeClass('class_name');
					$("#clickChange >li").hide().eq($(this).index()).show();
				});
			</script>--%>
		 </div>
		<%@include file="/context/mainFooter_1.jsp"%>
	</div>
	<script>
		helpControl.init({
			f: '${f}',
			search: '${search}'
		})
		var pageManager = new PageUtil();
		pageManager.getPageControl().init({
			container: "pageManager",
			preBtn: "btn_pre",
			nextBtn: "btn_next",
			totalPage: ${totalPage},
			initPage: '${nowPage}',
			pageChange: function (page) {
				helpControl.nextPage(page)
			}
		});
	</script>
</body>
</html>
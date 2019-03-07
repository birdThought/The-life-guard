<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>用户管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/QYcomCss.css">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/pageCss.css">
<link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/userManage.js"></script>
<script>
	window.onload = function() {
		var orgType = _cookie("orgType");
		orgType==1?menuSetting({
			parent : 0,
			child : 3
		}):menuSetting({
			parent: 0,
			child: 2
		});
	}
</script>
</head>

<body>
	<%@include file="/context/QYHeader.jsp"%>
	<div class="container">
		<%@include file="/context/QYMenu.jsp"%>
		<div class="right_content">
			<div class="right_body">
				<div class="right_title">
					<label class="action"> 用户管理 </label> <a href="orgUserControl.do?addUserPage" class="search-btn">添加用户</a>
				</div>
				<div style="margin-top:20px">
					<div style="margin-bottom:15px;position:relative">
						<select id="serve_select" class="service_select">
							<option selected="selected">选择服务</option>
							<c:forEach items="${serList}" var="item">
								<option value="${item.id}">${item.serviceName}</option>
							</c:forEach>
						</select> <span class="outdate"> <label>用户名</label> <input
							id="user_inp" type="text" style="padding:0 8px" />
						</span>
						<button class="search-btn" onclick="userControl.filter()">搜索</button>
					</div>
					<table class="service_table" cellpadding="0" cellspacing="0">
						<tr>
							<td>用户名</td>
							<td>姓名</td>
							<td>手机号</td>
							<td>当前服务</td>
							<td>群组</td>
							<!-- <td>操作</td> -->
						</tr>
						<c:if test="${empty memberList}">
							<tr>
								<td colspan="5">没有相关数据</td>
							</tr>
						</c:if>
						<c:forEach items="${memberList}" var="item">
							<tr>
								<td>${item.userName}</td>
								<td>${item.realName}</td>
								<td>${item.mobile}</td>
								<td>${item.curService}</td>
								<td>${item.groupName}</td>
								<!--  <td><a href="#">操作</a></td>-->
							</tr>
						</c:forEach>

					</table>
					<div id="pageContain" class="page_Container">
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
								<span id="btn_next" class="page page_next">下一页</span>
								<span style="margin-left:10px">共${pageCount}页，到第</span>
								<input type="text" class="page_input" id="p_input" />
								<span>页</span>
								<button class="page_input_enter">确定</button>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var mPage = new PageUtil();
	mPage.getPageControl().init({
		container : "pageContain",
		preBtn : "btn_pre",
		nextBtn : "btn_next",
		totalPage : ${pageCount},
		pageChange : function(page) {
			userControl.getDatas(page);
		}
	});
</script>
</html>

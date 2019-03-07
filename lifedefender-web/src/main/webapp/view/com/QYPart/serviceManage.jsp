<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>服务管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer"></t:base>
<link type="image/x-ic
on" rel="shortcut icon" href="favicon.ico
"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/QYcomCss.css">
<link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var child=1;
		<shiro:hasPermission name="user:1">
			child=0;
		</shiro:hasPermission>
		menuSetting({
			parent: 0,
			child: child
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
					<label class="action"> 服务管理 </label>
				</div>
				<div class="main_contain">
					<table class="service_table" cellpadding="0" cellspacing="0">
						<tr>
							<td>序号</td>
							<td>服务名称</td>
							<td>开通时间</td>
							<td>用户数</td>
							<td>操作</td>
						</tr>
						<c:if test="${empty serList}">
							<td colspan="5">您还没有开通任何服务</td>
						</c:if>
						<c:forEach items="${serList}" var="item" >
							<tr>
								<td>${item.id}</td>
								<td>${item.serviceName}</td>
								<td><fmt:formatDate value="${item.createDate}"
										pattern="yyyy-MM-dd" /></td>
								<td>${item.memberCount}</td>
								<c:if test = "${item.code == '02'}">
									<td><a href="orgServeControl.do?healthLessons&orgServeId=${item.orgServeId}">管理</a></td>
								</c:if>
								<c:if test = "${item.code != '02'}">
									<td><a href="orgServeControl.do?healthAsk&server=${item.id}">管理</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

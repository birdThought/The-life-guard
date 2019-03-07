<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
  <head>
    <title>用户主页</title>
	<t:base type="jquery,layer"></t:base>
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" type="text/css" href="/static/css/familyInfor.css">
  </head>
  <body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
	<%@include file="/context/header.jsp"%>
	<div class="view-body wrap">
		<%@include file="/context/nav_left.jsp"%>
		<div class="right-wrap">
		<div class="title fl"><a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;家庭成员</div>
	<div class="container fr" >
		<div class="family_member">
			<h3>家庭成员</h3>
		</div>
		<table class="familyMem" style="word-break:break-all;table-layout:fixed">
			<thead>
				<tr>
					<th>姓名</th>
					<th></th>
					<th>年龄</th>
					<th>手机号码</th>
					<th colspan="2">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${userGroup}" var="user">
				<tr>
					<td>${user.realName}</td>
					<td><c:if test="${user.isCurrentUser}"><span class="current">当前用户</span></c:if></td>
					<td class="age">${user.age}</td>
					<td>${user.mobile}</td>
					<td><c:if test="${!user.isCurrentUser}"><a href="familyControl.do?switchUser&userId=${user.id}" class="switch">切换</a></c:if></td>
					<td><c:if test="${!user.isCurrentUser}"><span><a href="javascript:logOut(this,${user.id})">退出</a></span></c:if></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
			</div>
	</div>
	</div>
	<script type="text/javascript">
		$(function() {
			menuSetting({
				parent : 4,
				child : 0
			});
		});
		//表格里的退出事件
		function logOut(obj,id){
			layer.confirm("你确定使该成员退出吗？",function(index){
				window.location.href="familyControl.do?backGroup&id="+id;
				layer.close(index);
			});
		}
		//表格里的切换退出事件
		/* function userToggle(obj,id){
			layer.confirm("你确定切换到该成员吗？",function(index){
				window.location.href="familyControl.do?switchUser&userId="+id;
				layer.close(index);
			});
		} */
	</script>
	<script type="text/javascript" src="/static/officialwebsite/js/family.js"></script>
</body>
</html>
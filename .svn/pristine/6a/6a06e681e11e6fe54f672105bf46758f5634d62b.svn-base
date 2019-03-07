<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<title>浏览病历</title>
	<t:base type="jquery,layer"></t:base>
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" type="text/css" href="/static/css/healthFiles.css">
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
	<%@include file="/context/header.jsp"%>
	<div class="view-body wrap">
		<%@include file="/context/nav_left.jsp"%>
		<div class="right-wrap">
		<div class="title fl"><a href="/login" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a href="recordHealthFileControl.do?enter" class="top_cite">健康档案</a>&nbsp;&nbsp;>&nbsp;&nbsp;浏览病历</div>
	<div class="container fr" >
		<ul class="caseTitle">
		    <li><span>浏览病历</span></li>
		</ul>
		<ul class="nav_tab">
		    <li><span class="case_back">返回</span></li>
		    <li><span class="case_edit">编辑</span></li>
		    <li><span class="case_delete">删除</span></li>
		</ul>
		<table class="lookCase">
			<thead>
				<tr>
					<th colspan="4">${data.title}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>就诊医院</td>
					<td colspan="3">${data.hospital}</td>
				</tr>
				<tr>
					<td>科室</td>
					<td>${data.departmentName}</td>
					<td>就诊时间</td>
					<td>${data.date}</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>${data.name}</td>
					<td>性别</td>
					<td>${data.sex}</td>
				</tr>
				<tr>
					<td>年龄</td>
					<td>${data.age}岁</td>
					<td>出生日期</td>
					<td>${data.birthday}</td>
				</tr>
				<tr>
					<td>诊断</td>
					<td colspan="3">${data.diagnosis}</td>
				</tr>
				<tr>
					<td>基础病情</td>
					<td colspan="3" style="width:680px;word-break:break-all;">${data.basicCondition}</td>
				</tr>
			</tbody>
		</table>
			<div class="sort_Title">
				<span>排序</span>
				<div class="sortBtn asc">病程时间<span class="trangle"></span></div>
				<div style="float: right;">
					<span>病程类型</span>
					<select class="sort_button">
						<option value="0">所有</option>
						<option value="1">首诊</option>
						<option value="2">复诊</option>
						<option value="3">出院</option>
					</select>
				</div>
			</div>
			<div style="padding-left: 0px">
				<ul class="info_list">
					<c:forEach var="course" items="${data.courses}">
					<li>
						<div class="left_contain">
							<div class="left_wrap" style="font-size: 16px;">
								<div></div>
								<div class="time">${course.visitingDate}</div>
							</div>
						</div>
						<div class="right_contain" style="width: 91%; left: 92px">
							<div class="out_circle"></div>
							<div class="border_contain">
								<div class="remark">
									备注：
									<p style="font-size: 15px; word-break:break-all;">
										${course.remark}
									</p>
								</div>
								<div class="health_image_contain" style="padding: 15px">
									<ul>
										<li><c:if test="${!empty course.img1}"><img src="${course.img1}" /></c:if></li>
										<li><c:if test="${!empty course.img2}"><img src="${course.img2}" /></c:if></li>
										<li><c:if test="${!empty course.img3}"><img src="${course.img3}" /></c:if></li>
									</ul>
								</div>
							</div>
						</div>
						<input type="hidden" name="case_typeNumber" value="${course.typeNumber}" />
						<input type="hidden" name="case_orderNumber" value="${course.orderNumber}" />
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
		</div>
	</div>
	<input type="hidden" name="medicalId" value="${data.id}" />
</body>
<script src="/static/com/lifeshs/member/record/lookHealthCase.js"></script>
<script type="text/javascript" src="/static/plugins/image/jquey-bigic.js"></script>
<script type="text/javascript">
$(function(){
	menuSetting({
		parent : 2,
		child : 0
	});
	$('.health_image_contain img').bigic();
});
</script>
</html>
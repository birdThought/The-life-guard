<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>联系人</title>
<meta http-equiv="X-UA-Compatible" content="IE-edge">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/static/css/userInfor.css">
<link
	href="/static/login/css/green.css"
	rel="stylesheet" type="text/css">
<t:base type="jquery,layer"></t:base>
<script type="text/javascript"
	src="/static/login/js/icheck.js"></script>
<script type="text/javascript"
	src="/static/com/lifeshs/member/js/warnAndCount.js"></script>
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">主页</div>
			<div class="container fr">
				<ul class="wAndC">
					<li><span>联系人</span></li>
					<li id="addNumber"><span>添加号码</span></li>
				</ul>
				<%@include file="/context/contactAddDialog.jsp"%>
				<%--<ul class="addInfor" name="contact" style="display: none">
					<li><label>姓名</label><br> <input type="text"
						name="showName"></li>
					<li><label>手机号码</label><br> <input type="text"
						name="showContactNumber" placeholder="请输入手机号码"></li>
					<li><label>类型</label><br> <input name="radio1"
						id="showsos" value="1" type="radio" class="user" />预警号码 <input
						name="radio1" id="showfamily" value="0" type="radio" class="user1"/>亲情号码</li>
					<li><label>说明</label>
						<p>
							<small> 添加后，如用户测量时出现异常或跌倒，系统将发送短信或拨打所登记的号码 </small>
						</p></li>
				</ul>--%>
				<table style="word-break:break-all;table-layout:fixed">
					<thead>
						<tr>
							<th>姓名</th>
							<th>号码</th>

							<th style="position:relative;">类型 <img
								src="/static/images/up.png">
								<ul data-hidden="0" style="display:none">
									<li name="allContact">全部</li>
									<li name="SOS">预警号码</li>
									<li name="family">亲情号码</li>
								</ul>
							</th>
							<th>终端类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contacts}" var="c" varStatus="index">
							<tr>
								<td>${c.userContacts.name}</td>
								<td>${c.userContacts.contactNumber}</td>
								<td data-type="${c.userContacts.contactType}" data-family="${c.userContacts.family}" data-sos="${c.userContacts.sos}"></td>
										<%-- <c:choose>
											<c:when test="${c.userContacts.terminalName}">
												<td class="isSOS">预警号码</td>
											</c:when>
											<c:when test="${c.userContacts.terminalName}">
												<td class="isFamily">亲情号码</td>
											</c:when>
											<c:when test="${c.userContacts.isFamily == false && c.userContacts.isSOS == false}">
												<td class="isFamily">无</td>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test = "${c.terminalType == null}">
												<td class="none">无</td>
											</c:when>
											<c:otherwise>
												<td class = "terminalType">
												<c:forEach items="${c.terminalType}" var="t" varStatus="index">
													${t}
												</c:forEach>
												</td>
											</c:otherwise>
										</c:choose> --%>
								<td>
								<c:choose>
									<c:when test = "${c.userContacts.terminalName == ''}">
										无
									</c:when>
									<c:otherwise>
										${c.userContacts.terminalName}
									</c:otherwise>
								</c:choose>
									
								</td>
								<td><span name="updateContact"
									onclick="getContactDetail(${c.userContacts.id})">设置</span>
									<c:choose>
										<c:when test = "${c.userContacts.isC3SOS}">
											<span style = "color:gray;" onclick="deleteContact('disable')">删除</span>
										</c:when>
										<c:otherwise>
											<span onclick="deleteContact(${c.userContacts.id})">删除</span>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function () {
			menuSetting({
				parent : 5,
				child : 2
			});
		})
	</script>
</body>
</html>
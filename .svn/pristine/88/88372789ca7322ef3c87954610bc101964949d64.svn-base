<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>设备数据</title>
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common/css/common.css">	
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/device.css">
	<link href="${pageContext.request.contextPath}/static/login/css/green.css" rel="stylesheet" type="text/css">
	<t:base type="jquery,layer"></t:base>
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">主页</div>
			<div class="container fr">
				<h3>设备管理</h3>
				<dl class="instruction">
					<dt>说明：</dt>
					<dd>
						<small>请选择您所拥有的健康包设备，选择后才可测量、记录和查询数据</small>
					</dd>
				</dl>
				<div class="contentEq">
					<div class="tab_content" style="display: block;" id="tab1">
						<ul class="addImg">
							<c:forEach items="${devices}" var="device">
								<li><img name="showImg"
									src="${device.img}" width="120"
									height="120" alt="${device.name_cn}"> <img
									src="/static/images/check.png" alt="" class="check"
									style="display: none">
									<p>${device.name_cn}</p>
									<p class="clickBtn">选择</p>
									<input type="hidden" name="status" value="${device.status}" />
									<input type="hidden" name="nameEn" value="${device.name_en}" />
								</li>
							</c:forEach>
						</ul>
					</div>
					<%-- <div class="tab_content infor" style="display: none;" id="tab2">
						<form name="form_params">
							<ul>
								<li class="chose"><label>性别</label><br> <input
									type="radio" name="rd_1" value="true"
									<c:if test="${user.sex==true}">checked</c:if>> <span>男</span>
									<input type="radio" name="rd_1" value="false"
									<c:if test="${user.sex==false}">checked</c:if>> <span>女</span>
								</li>
								<li class="infor_2"><label>年龄</label><br> <input
									name="age" type="text" placeholder="请输入年龄" value="${age}">
								</li>
								<li class="infor_2"><label>身高(CM)</label><br> <input
									name="height" type="text" placeholder="请输入身高"
									value="${user.height}"></li>
								<li class="infor_2"><label>体重(KG)</label><br> <input
									name="weight" type="text" placeholder="请输入体重"
									value="${user.weight}"></li>
								<li class="infor_2"><label>腰围(CM)</label><br> <input
									name="waist" type="text" placeholder="请输入腰围"
									value="${user.waist}"></li>
								<li class="infor_2"><label>臀围(CM)</label><br> <input
									name="hip" type="text" placeholder="请输入臀围" value="${user.hip}">
								</li>
								<li class="infor_2"><label>胸围(CM)</label><br> <input
									name="bust" type="text" placeholder="请输入胸围"
									value="${user.bust}"></li>
								<li class="infor_2"><label>说明</label>
									<p>
										<small>填写参数后，系统才可根据参数分析您通过设备检测出的数值，判断您实时的身体状况</small>
									</p></li>
								<li>
									<button name="submit" class="btn_cursor btn_submit">确定</button>
									<button class="btn_cursor btn_cancel">取消</button>
								</li>
							</ul>
						</form>
					</div> --%>
				</div>
			</div>
				</div>
		</div>
	</div>
</body>
	<script>
	$(function(){
		menuSetting({
			parent : 0,
			child : 0
		});
		
		checkRecordData.complete(checkRecordData.HEALTH_PACKAGE);
	});
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/login/js/icheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/messages_zh.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/validate.expand.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/com/lifeshs/member/js/device.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/com/lifeshs/member/js/userHealthDataCompleteCheck.js"></script>
</html>
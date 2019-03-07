<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>添加成员</title>
<title>用户主页</title>
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css" href="/static/login/css/green.css">
<link rel="stylesheet" type="text/css" href="/static/css/familyInfor.css">
<link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap" >
			<div class="title fl">
				<a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;新增成员>
			</div>
			<div class="container fr">
				<h3>新增成员</h3>
				<form class="memberSet" name="member">
					<h3>新家庭成员添加</h3>
					<input type="hidden" id="id" name="id" value="${user.id }" />
					<ul>
						<li><img id="upload_img"
							src="<c:choose><c:when test='${empty user.photo}'>static/images/picture.png</c:when><c:otherwise>${user.photo}</c:otherwise></c:choose>"
							alt="" width="90" height="90">
							<div>
								<input type="button" value="上传" onclick="path.click()">
								<input type="file" name="path" id="path" style="display:none"
									onchange="upfile.value=this.value;">
							</div></li>
						<li class="newInfor"><label>姓名</label> <input type="text"
							name="userName" value="${user.userName }"></li>
						<li class="newInfor"><label>手机号码</label> <input type="text"
							name="mobile" placeholder="请输入您的手机号码" value="${user.mobile }">
						</li>
						<li class="newInfor"><label>生日</label> <input type="text"
							name="birthday" id="testDate" placeholder="请输入您的生日" value=""
							readonly="readonly"></li>
						<li><label>性别</label> <input type="radio" name="sex"
							value="1" checked>男 <input type="radio" name="sex"
							value="0">女</li>
						<li class="newInfor appentUnit"><label>身高</label> <input
							type="text" value="177" name="height" maxlength="3"></li>
						<li class="newInfor appentUnitKG"><label>体重</label> <input
							type="text" value="60.0" name="weight" maxlength="6"></li>
						<li><span class="moreParam">其他参数</span></li>
						<li class="newInfor appentUnit"><label>臀围</label> <input
							type="text" value="60" name="hip" maxlength="5"></li>
						<li class="newInfor appentUnit"><label>腰围</label> <input
							type="text" value="60" name="waist" maxlength="5"></li>
						<li class="newInfor appentUnit"><label>胸围</label> <input
							type="text" value="60" name="bust" maxlength="5"></li>
					</ul>
					<button type="submit" id="sure">确定</button>
				</form>
			</div>
				</div>
		</div>
	</div>
	<script type="text/javascript"
		src="/static/common/js/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="/static/common/js/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="/static/common/js/validate.expand.js"></script>
	<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
	<script type="text/javascript"
		src="/static/com/lifeshs/member/js/photoUpload.js"></script>
	<script type="text/javascript" src="/static/login/js/icheck.js"></script>
	<script type="text/javascript"
		src="/static/com/QYPart/js/customRadio.js"></script>
	<script type="text/javascript" src="/static/officialwebsite/js/family.js"></script>
	<script src="/static/plugins/jeDate/jedate.min.js"></script>
	<script type="text/javascript">
		$(function() {
			menuSetting({
				parent : 4,
				child : 2
			});
		});
		customRadio.init({
			name : 'sex',
		});
		$('.moreParam').click(function() {
			$('.moreParam').parent().nextAll('li').toggle();
		});
		jeDate({
			dateCell : "#testDate",
			format : "YYYY-MM-DD",
			isinitVal : true,
			initAddVal : [ 0 ],
			minDate : "1900-01-01",
			maxDate : jeDate.now(0),
			startMin : "1900-01-01",
			startMax : jeDate.now(0),
			zindex : 999,
			choosefun : function(elem, val) {
				//val为获取到的时间值
			}
		});
	</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加用户</title>
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
<link href="/static/login/css/blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/static/com/QYPart/css/paramSubmit.css">
<script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
<script type="text/javascript">
	seajs.config({
		base : './static/',
		alias : {
			"icheck" : "login/js/icheck",
			"QYcommon" : "com/QYPart/js/QYcommon",
			"commonCheck" : "com/QYPart/js/commonCheck",
			"customRadio" : "com/QYPart/js/customRadio",
		}
	});
	seajs.use([ "./static/com/QYPart/js/addUser" ], function() {
		var orgType = _cookie("orgType");
		orgType==1?menuSetting({
			parent : 0,
			child : 3
		}):menuSetting({
			parent: 0,
			child: 2
		});
		var json={};
		<c:forEach items="${serList}" var="item">
			json['${item.id}']=JSON.parse('${item}');
		</c:forEach>
		if(!$.isEmptyObject(json)){
			addUser.initService(json);
		}
		//alert(JSON.stringify(json));
	});

</script>
</head>

<body>
	<%@include file="/context/QYHeader.jsp"%>
	<div class="container">
		<%@include file="/context/QYMenu.jsp"%>
		<div class="right_content">
			<div class="right_body">
				<div class="item_title"
					style="background-color:#f1f1f2;border-bottom:none;padding-left:20px;font-size:18px">
					<h3>添加用户</h3>
				</div>
				<!--账户信息-->
				<div class="item_contain">
					<div class="item_title">
						<label class="title"> 账户信息 </label>
					</div>
					<form>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>登录账户：</label><input
								id="account" type="text" placeholder="输入账户" />
						</div>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>密码：</label><input
								id="psw" type="password" placeholder="输入密码" />
						</div>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>确认密码：</label><input
								id="confirm_psw" type="password" placeholder="确认一下密码" />
						</div>
					</form>
				</div>
				<!--基本信息-->
				<div class="item_contain">
					<div class="item_title">
						<label class="title"> 基本信息 </label>
					</div>
					<form>
						<div class="param_set" style="clear: both;">
							<label class="param"><span class="warn">*</span>姓名：</label><input
								id="name" type="text" placeholder="请输入姓名" />
						</div>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>性别：</label><input
								name="sexSelect" type="radio" value="1" checked />男&nbsp;&nbsp;&nbsp;<input
								name="sexSelect" type="radio" value="0" />女
						</div>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>手机：</label><input
								id="phone" type="text" placeholder="请输入手机号码" />
						</div>
					</form>
				</div>
				
				<div class="item_contain">
						<div class="item_title">
							<label class="title">分配服务与群组 </label>
						</div>
						<form>
							<c:choose>
								<c:when test="${empty serList}">
									<div style="padding:20px;font-size:17px;padding-top:0">此门店还没添加服务</div>
								</c:when>
								<c:otherwise>
									<div class="param_set">
										<label class="param">服务绑定： </label><select id="serverSelect"
											class="select-btn" style="width:250px;"
											onchange="addUser.getGroupList()">
										</select>
									</div>
									<div class="param_set">
										<label class="param">收费方式： </label><select
																				   class="select-btn" style="width:250px;"
																				   id="chargeMode">
									</select>
									</div>
									<div class="param_set">
										<label class="param">数量：</label><input id="countNumber"
											type="text" value="1" style="width: 100px;" maxlength="3"/>
									</div>
									<div class="param_set">
										<label class="param">选择群组： </label><select id="groupSelect"
											class="select-btn" style="width:250px;">
											<c:forEach items="${groupList}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>

								</c:otherwise>
							</c:choose>
						</form>
					</div>
				<div style="padding:30px 110px 20px;clear: both;">
					<input onclick="addUser.submitForm()" class="save" value="保存"
						type="button" /><input class="back" value="返回" type="button" onclick="history.go(-1)" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
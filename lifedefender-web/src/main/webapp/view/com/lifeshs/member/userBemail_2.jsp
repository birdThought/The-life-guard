<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>邮箱绑定</title>
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<link rel="stylesheet" type="text/css" href="/static/css/userBinfor.css">
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
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
				<h3>邮箱绑定</h3>
				<div class="web-width">
					<div class="for-liucheng">
						<div class="liulist for-cur"></div>
						<div class="liulist for-cur"></div>
						<div class="liulist"></div>
						<div class="liutextbox">
							<div class="liutext for-cur">
								<em>1</em> <br />
								<strong>号码验证</strong>
							</div>
							<div class="liutext for-cur">
								<em>2</em> <br />
								<strong>新邮箱验证</strong>
							</div>

							<div class="liutext">
								<em>3</em> <br />
								<strong>完成</strong>
							</div>
						</div>
					</div>
					<!--for-liucheng/-->
					<form class="findPsw">
						<ul>
							<li><label>邮箱</label> <input type="text" id="email" name="newEmail"
								placeholder="请输入邮箱"></li>
							<li>
								<label>验证码</label>
								<input type="text" name="idcode" placeholder="请输入验证码" class="idcode">
								<input type="button" id="btnCode" value="获取验证码">
								<input type="hidden" name="emailCode" value="${validCode}" /></li>
							<li>
								<input name="emailSubmit" type="button" value="下一步" class="tijiao">
							</li>
						</ul>
					</form>
				</div>
				<!--web-width/-->
			</div>
				</div>
			<!--content/-->
		</div>
	</div>
</body>
<script type="text/javascript" src="/static/com/lifeshs/member/js/bindEmail.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/sendCode.js"></script>
<script>
jQuery(function(){
	menuSetting({
		parent : 5,
		child : 0
	});
	// 发送验证码
	jQuery("form.findPsw").on("click", "#btnCode", function(){
		var email = jQuery("#email").val();
		if(email != ""){
			var $element = jQuery("#btnCode");
			sendCode.sendRandCodeToEmail(email, $element, "email", null);
		}
	});
	// 提交按钮事件绑定
	$("[name='emailSubmit']").click(function(){
		updateEmail();
	});
});

function updateEmail(){
	var newEmail = $("input[name='newEmail']").val();
	var newEmailCode = $("input[name='idcode']").val();
	var emailCode = $("input[name='emailCode']").val();
	var _data="newEmail="+newEmail+'&newEmailCode='+newEmailCode+'&emailCode='+emailCode;
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "memberControl.do?modifyEmail",
		data: _data,
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
		beforeSend:function(){
			layer.load();
		},
		complete:function(){
			layer.closeAll('loading');
		},
		success: function(result) {
			layer.msg(result.msg);
			if(result.success){
				window.location.href="memberControl.do?modifyEmailSuccess";
			}
		},error: function(e){
			console.log(e);
		}
	});
}
</script>
</html>

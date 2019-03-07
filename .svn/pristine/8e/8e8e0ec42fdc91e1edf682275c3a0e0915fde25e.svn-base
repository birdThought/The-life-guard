<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="zh-cmn">
  <head>
    <title>找回密码</title>
    <meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="find the password">
	<link rel="stylesheet" href="/static/common/css/mainHeader.css">
	<link rel="stylesheet" href="/static/css/findPwd.css">
	<t:base type="jquery,layer"></t:base>
	<script type="text/javascript" src="/static/login/js/icheck.js"></script>
	<script type="text/javascript" src="/static/common/js/common.js"></script>
	<script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
	<script type="text/javascript" src="/static/login/js/findPwd.js"></script>
	<script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
</head>
<body>
	<%@include file="/context/mainHeader.jsp"%>
	<div class="content">
		<div class="web-width">
			<div class="smallTitle">
				<h3>找回密码</h3>
			</div>
			<div class="liucheng">
				<div class="line"></div>
				<div class="buzhou">
					<img src="/static/images/user.png" />
					<p>账号信息</p>
				</div>
				<div class="buzhou" style="width: 35%">
					<img src="/static/images/resetPwd_2.png" />
					<p>重置密码</p>
				</div>
				<div class="buzhou">
					<img src="/static/images/finish.png" />
					<p>完成</p>
				</div>
			</div>
			<form class="findPsw">
				<ul>
					<li class="infor"><label for="password">密码</label><br> <input
						id="password" type="password" name="password" placeholder="密码"
						style="color: #000;" onkeyup="pwStrength(this.value)"
						onblur="pwStrength(this.value)">
						<div class="psw_strenth">
							<div id="strength_L"></div>
							<div id="strength_M"></div>
							<div id="strength_H"></div>
							<span id="strength_notice"></span>
						</div> <input type="hidden" name="validCode" value="${validCode}">
						<input type="hidden" name="userId" value="${userId}"></li>
					<li class="infor"><label for="conform_password">确认密码</label><br>
						<input style="color: #000;" type="password"
						name="conform_password" value="" placeholder="请确认密码"
						id="conform_password" /> <span id="passstrength"></span></li>
					<li class="infor tijiao"><input type="button" name="submit"
						value="确定" onclick="resetPassword()" style="border: none"></li>
				</ul>
			</form>
		</div>
		<!--web-width/-->
	</div>
	<!--content/-->
	<%@include file="/context/mainFooter.jsp"%>
</body>
<script>
	jQuery(function() {
		_validate();
	});

	function _validate() {
		$(".findPsw").validate({
			errorPlacement : function(error, element) {
				var div = $("<div />").append(error);
				div.appendTo(element.parent());
			},
			rules : {
				mobileOrEmail : {
					required : true
				},
				idcode : {
					required : true
				},
				password : {
					required : true,
					minlength : 6,
					maxlength : 16
				},
				conform_password : {
					equalTo : "#password"
				}
			},
			messages : {
				mobileOrEmail : {
					required : "请填写手机号／邮箱"
				},
				idcode : {
					required : "验证码不能为空"
				},
				password : {
					required : "请填写密码",
					rangelength : "密码应该在6-16位",
				},
				conform_password : {
					equalTo : "输入的密码的不一致"
				}
			}
		});
	}

	function resetPassword() {

		if ($("[name='conform_password']").hasClass("valid")) {
			var userId = $("input[name='userId']").val();
			var validCode = $("input[name='validCode']").val();
			var password = $("input[name='password']").val();
			$
					.ajax({
						async : true,
						cache : false,
						type : 'POST',
						url : "releaseControl.do?resetPassword",
						data : "userId=" + userId + "&validCode=" + validCode
								+ "&password=" + password,
						dataType : 'json',
						beforeSend : function() {

						},
						complete : function() {
							//方法执行完毕，效果自己可以关闭，或者隐藏效果
						},
						success : function(result) {
							layer.msg(result.msg);
							if (result.success) {
								window.location.href = "releaseControl.do?success&userName="
										+ result.obj;
							} else {
								//询问框
								layer
										.confirm(
												'重置密码失败，请重新操作',
												{
													btn : [ '是', '否' ]
												//按钮
												},
												function() {
													window.location.href = "loginControl.do?forgotPwd";
												});
							}
						},
						error : function(e) {
							console.log(e);
						}
					});
		} else {
			layer.msg("输入的密码的不一致");
		}
	}

	//密码强度验证
	function CharMode(iN) {
		if (iN >= 48 && iN <= 57) // 数字
			return 1;
		if (iN >= 65 && iN <= 90) // 大写字母
			return 2;
		if (iN >= 97 && iN <= 122) // 小写
			return 4;
		else
			return 8; // 特殊字符
	}
	// bitTotal函数
	// 计算出当前密码当中一共有多少种模式
	function bitTotal(num) {
		modes = 0;
		for (i = 0; i < 4; i++) {
			if (num & 1)
				modes++;
			num >>>= 1;
		}
		return modes;
	}
	// checkStrong函数
	// 返回密码的强度级别
	function checkStrong(sPW) {
		if (sPW.length <= 4)
			return 0; // 密码太短
		Modes = 0;
		for (i = 0; i < sPW.length; i++) {
			// 测试每一个字符的类别并统计一共有多少种模式.
			Modes |= CharMode(sPW.charCodeAt(i));
		}
		return bitTotal(Modes);
	}
	// pwStrength函数
	// 当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色
	function pwStrength(pwd) {
		O_color = "#DDDDDD";
		L_color = "#FF0000";
		M_color = "#FF9900";
		H_color = "#33CC00";
		if (pwd == null || pwd == '') {
			Lcolor = Mcolor = Hcolor = O_color;
			strength_notice.style.display = 'none';
		} else {
			S_level = checkStrong(pwd);
			console.log(S_level);
			switch (S_level) {
			case 0:
				Lcolor = Mcolor = Hcolor = O_color;
			case 1:
				Lcolor = L_color;
				Mcolor = Hcolor = O_color;
				strength_notice.style.display = 'inline-block';
				strength_notice.style.color = 'red';
				strength_notice.innerText = "弱";
				break;
			case 2:
				Lcolor = Mcolor = M_color;
				Hcolor = O_color;
				strength_notice.style.display = 'inline-block';
				strength_notice.style.color = '#FF9900';
				strength_notice.innerText = "中";
				break;
			case 3:
				Lcolor = Mcolor = Hcolor = H_color;
				strength_notice.innerText = "强";
				strength_notice.style.color = '#48c858';
				break;
			default:
				Lcolor = Mcolor = Hcolor = H_color;
				strength_notice.style.display = 'none';
			}
		}
		document.getElementById("strength_L").style.background = Lcolor;
		document.getElementById("strength_M").style.background = Mcolor;
		document.getElementById("strength_H").style.background = Hcolor;
		return;
	}
</script>
</html>

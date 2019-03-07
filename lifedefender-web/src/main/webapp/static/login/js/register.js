var validator;
$(document).ready(function() {
	_validate();
});
var isOrg = false;
function _validate() {
	$.validator.addMethod("checkOrgName", function(value, element, params) {
		var checkOrgName = /^([a-zA-Z0-9]|[\u4e00-\u9fa5]){1,20}$/;
		return this.optional(element)||(checkOrgName.test(value));
	}, "请输入英文、数字、中文组合的机构名称");
	
	
	$("#item-register").validate({
		errorPlacement : function(error, element) {
			var elementType = element.attr("type");
			if ("checkbox" == elementType) {
				$("#_checkbox").append(error);
			} else {
				var div = $("<div />").append(error);
				div.appendTo(element.parent());
				div.css("color", "red");
			}
		},
		submitHandler : function(form) { // 表单提交句柄,为一回调函数，带一个参数：form
			var url = "/member/register";
			// var mobile = $("#mobile").val();
			var userName = $.trim($("#userName").val());
			// var validCode = $("#validCode").val();
			var password = $("#password").val();
			// var data =
			// "mobile="+mobile+"&validCode="+validCode+"&password="+password;
			userName=encodeStr(userName);
			password=encodeStr(password);
			var data = "userName=" + userName + "&password=" + password;
            var userType=$('input:radio[name="user"]:checked').val();
			if ("o" == userType) {
				url = "orgControl.do?registerOrg";
				var business = $("#businessName").val();
				data = data + "&orgName=" + business;
                var orgType = $('input:radio[name="org"]:checked').val();
				var orgXz=$("#orgXZ option:selected").val();
				data+="&orgType="+orgType+"&orgXz="+orgXz;
			}
			$.ajax({
				async : true,
				cache : false,
				type : 'POST',
				url : url,
				data : data + '&temp=' + new Date(),
				dataType : 'json',
				beforeSend : function() {
					layer.load();
				},
				complete : function() {
					// 方法执行完毕，效果自己可以关闭，或者隐藏效果
				},
				success : function(result) {
					layer.closeAll();
					// layer.msg(result.msg);
					if (result.success) {
						// 询问框
						if("o" == userType){
							layer.confirm('注册成功，是否完善机构资料以便于更好的体验？', {
								btn : [ '是', '否' ]
							// 按钮
							}, function() {
								var _register = userName + "&" + password;
								var date = new Date();
								date.setTime(date.getTime() + (1 * 60 * 1000));
								$.cookie("_REGISTER", _register, {
									path : '/',
									expires : date
								});
								window.location.href = "orgControl.do?improveOrgPage&orgId="+result.obj;
							}, function() {
								window.location.href = "/login";
							});
							return;
						}
						
						layer.confirm('注册成功，是否现在登录？', {
							btn : [ '是', '否' ]
						// 按钮
						}, function() {
							window.location.href = "/login";
							var _register = userName + "&" + password;
							var date = new Date();
							date.setTime(date.getTime() + (1 * 60 * 1000));
							$.cookie("_REGISTER", _register, {
								path : '/',
								expires : date
							});
						}, function() {

						});
					} else {
						layer.msg(result.msg);
					}
				}
			});

		},
		rules : {
			business : {
				required : true,
				minlength : 1,
				maxlength : 20,
				checkOrgName : true,
				remote : { // 验证机构名是否存在
					type : "POST",
					url : "releaseControl.do?ogrIsExist",
					data : {
						orgName : function() {
							return $("#businessName").val();
						}
					}
				}
			},
			userName : {
				required : true,
				minlength : 6,
				maxlength: 16,
				checkUserName : true,
				remote : { // 验证用户名是否存在
					type : "POST",
					url : "releaseControl.do?userIsExist",
					data : {
						val : function() {
							return $("#userName").val();
						}// ,
					// column : "m",
					// type : "mo"
					}
				},
				success : function(label) {
					// console.log(label);
					// console.log($(label).hasClass("valid"));
					if ($(label).is('.valid')) {
						$("#signupBtnCode").removeAttr("disabled");// 启用按钮
					}
				}
			},
			idcode : {
				required : true
			},
			password : {
				required : true,
				minlength : 6,
				maxlength: 16,
				checkPassword : true
			},
			conform_password : {
				equalTo : "#password"
			},
            agreement : {
				required : true
			}
		},
		messages : {
			business : {
				required : "请填写企业或者机构名称",
				remote : "该机构名已经被注册",
				maxlength : "机构名称长度不能超过20个字符"
			},
			userName : {
				required : "请填写用户名",
				remote : "该用户名已经被注册"
			},
			idcode : {
				required : "验证码不能为空"
			},
			password : {
				required : "请填写密码",
				rangelength : "密码应该在6-16位"
			},
			conform_password : {
				equalTo : "输入的密码的不一致"
			},
			agreement : {
				required : "这是必选项"
			}
		}
	});
}

// 通过短信短信获取验证码
function sendRandCode() {
	var mobile = $("#mobile").val();// 手机号码
	if (mobile != "") {
		if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile)) {
			layer.msg("请输入正确的手机号码！");
			return;
		}
		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			url : "/register?sendValidCode",
			data : "mobile=" + mobile + '&temp=' + new Date()
					+ '&cache=registery',
			dataType : 'json',
			beforeSend : function() {

			},
			complete : function() {
				// 方法执行完毕，效果自己可以关闭，或者隐藏效果
			},
			success : function(result) {
				layer.msg(result.msg);
				if (result.success) {
					sendMessage();
				}
			}
		});
	} else {
		layer.msg("请输入手机号码！");
	}
}

var InterValObj; // timer变量，控制时间
var count = 60; // 间隔函数，1秒执行
var curCount;// 当前剩余秒数
var codeLength = 6;// 验证码长度
function sendMessage() {
	curCount = count;
	// 设置button效果，开始计时
	$("#signupBtnCode").attr("disabled", "true");
	$("#signupBtnCode").val("重新获取（" + curCount + "）秒");
	$("#signupBtnCode").css("background-color", "#ccc");
	InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
}
// timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);// 停止计时器
		$("#signupBtnCode").removeAttr("disabled");// 启用按钮
		$("#signupBtnCode").val("重新发送验证码");
		$("#signupBtnCode").css("background-color", "#48c859");
		code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
	} else {
		curCount--;
		$("#signupBtnCode").val("重新获取（" + curCount + "）秒");
	}
}

// 密码强度验证
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

function clickHere() {
	var href = "/releaseControl.do?showAgreement";
	if (isOrg) {
		href = "releaseControl.do?showOrgAgreement";
	}
	layer.open({
		type: 2,
		title: false,
		shadeClose: true,
		moveType: 1,
		area: ["1000px", "800px"],
		content: href
	});
}
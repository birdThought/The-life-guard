var remenberMe = false;
function _keyDown(){
    if (event.keyCode == 13){
        // login();
    }
}
jQuery(function() {
	showRandCode(false, "");
	
	jQuery("div.loginContent").on("blur", "#username", function() {
		var userName = jQuery(this).val();
		showRandCode(false, userName);
	});
	
});

// 登陆验证
var validator = $(function() {
	$.validator.setDefaults({
		debug : true
	});
	validator = $(".login_form").validate({
		errorPlacement : function(error, element) {
			// error.appendTo(element.parent());
			// 创建div
			var div = $("<div />").append(error);
			div.appendTo(element.parent());
			// error.appendTo($("#errorMsg"));
		},
		rules : {
			username : {
				required : true,
			},
			password : {
				required : true,
			},
			randCode : {
				required : true
			}
		},
		messages : {
			username : {
				required : "请填写用户名或手机号码",
				rangelength : "用户名应该在4-11位"
			},
			password : {
				required : "请填写密码",
				rangelength : "密码应该在6-16位",
				remote : "url"
			},
			randCode : {
				required : "请输入验证码"
			}
		},
		submitHandler : function(form) {
			login();
		}
	});

	//添加验证码
	$('#randCodeImage').click(function(){
	    reloadRandCodeImage();
	});
	
	getCookie();
	
    $('input[type="checkbox"]').on('click', function () {
		if (this.checked) {
            remenberMe = true;
		} else {
            remenberMe = false;
		}
    })
	
	
	$("#user span").click(function() {
		$(this).css("background", "#48c858").siblings().css("background", "#fff");
	});
});

function _selectType(type){
	$("#_userType").val(type);
	switch(type){
	case 'm':
		$(".userSp1").css("color","white");
		$(".userSp2").css("color","#48c858");
		break;
	case 'o':
		$(".userSp1").css("color","#48c858");
		$(".userSp2").css("color","white");
		break;
	}
}
/**
 * 刷新验证码
 */
function reloadRandCodeImage() {
    var date = new Date();
    var img = document.getElementById("randCodeImage");
    img.src='randCodeImage.do?a=' + date.getTime();
}

function login(){
	var username = $('#username').val();
	var password = $('#password').val();
	var randCode = $('#randCode').val();
	var _userType = $("#_userType").val();
	var redirectUrl = jQuery("input.btnD").attr("data-redirecturl");
	if(username == "" || password == ""){
		return;
	}
	username=encodeStr(username);
	password=encodeStr(password);
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "/login/checkuser",
		data: "userName="+username+'&pwd='+password+'&randCode='+randCode+'&lut='+_userType+'&rememberMe='+remenberMe+'&temp='+new Date(),
		dataType: 'json',
		beforeSend:function(){
//			//加载层-默认风格
			layer.load();
		},
		complete:function(){
			//方法执行完毕，效果自己可以关闭，或者隐藏效果
			layer.closeAll('loading');
		},
		success: function(result) {
			if(!result.success){
				layer.msg(result.msg, {icon: 2});
				reloadRandCodeImage();
				showRandCode(true, username);
			} else {
			    showRandCode(false, "");
			    $.cookie("RAND_CODE", null);
			    var attributes = result.attributes;
			    if (attributes.userMessage != undefined) {
			        layer.alert(attributes.userMessage, {icon:5});
                    return ;
			    }
				if (attributes.redirectUrl != undefined) {
				    window.location.href = attributes.redirectUrl;
                    return ;
				}
				
				var verifiedResult = attributes.verifiedResult;
				
				var dataComplete = 1;
				var msg = "";
				var rewrite = 0;
				var orgId = 0;
				var href = attributes.href;
				var showMsg = 0;
				
				if (verifiedResult != undefined) {
					dataComplete = parseInt(verifiedResult.dataComplete);
					msg = verifiedResult.msg;
					rewrite = parseInt(verifiedResult.rewrite);
					orgId = parseInt(verifiedResult.orgId);
					showMsg = parseInt(verifiedResult.showMsg);
				}
				
				if (dataComplete == 1 && rewrite == 0 && showMsg == 0) {
					setCookie(password);
					layer.msg(result.msg, {icon: 1});

					if (redirectUrl != undefined) {
						window.location.href = redirectUrl;
						return ;
					}
					
					window.location.href = result.attributes.href;
					return ;
				}
				if (dataComplete == 0) {
					/*layer.confirm('机构资料尚未完善，是否现在跳转页面完善信息', {btn:['是','否']}, function() {
						window.location.href = "orgControl.do?improveOrgPage&orgId=" + orgId;
					});*/
                    layer.msg(result.msg);
					return ;
				}
				if (rewrite == 1) {
					/*layer.confirm(msg + '<br/>是否现在跳转页面重新填写信息', {btn:['是','否'], icon: 5}, function() {
						window.location.href = "orgControl.do?improveOrgPage&orgId=" + orgId;
					});*/
                    layer.alert('审核不通过，可联系客服了解相关信息', {icon:5});
				} else {
					layer.alert("请等待审核结果", {icon:6});
				}
				//1 打勾 2 红叉 3 问号 4 锁头 5 哭脸 6 笑脸
				
			}
		}
	});
}

//显示验证码
function showRandCode(isLoginError, userName){
//	$("#randCode").rules("remove","required");
//	$("#business").rules("add",{required:true});
	
	// 设置RAND_CODE
	if(isLoginError){
		$.cookie("RAND_CODE", userName, "/",24);
	}
	var RAND_CODE = $.cookie("RAND_CODE");
	
	if(RAND_CODE == null || RAND_CODE != userName) {
		$("#p_randCode").css('display','none');
		return;
	}
	if(RAND_CODE == userName || isLoginError) {
		//登录错误显示验证码
		$("#p_randCode").css('display','block');
	}
}

//设置cookie
function setCookie(password){
	if (remenberMe) {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name, $("#"+this.name).val(), "/",24);
			$.cookie("COOKIE_NAME","true", "/",24);
		});
	} else {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name,null);
			$.cookie("COOKIE_NAME",null);
		});
	}
	$.cookie("RAND_CODE",null);
	$.cookie("_REGISTER",null);
	$.cookie("pwdStrong",$.checkPswStrong(password));
}

//读取cookie
function getCookie(){
	var _userType = $.cookie("_userType");
	var _userTypeClass = "userSp1";
	if(_userType == null ||_userType == ""){
		$.cookie("_userType", "m", "/",24);
	}else{
		_selectType(_userType);
		if(_userType == "o"){
			_userTypeClass = "userSp2";
		}
	}
	$("."+_userTypeClass).css("background", "#48c858").siblings().css(
			"background", "#fff");
	var COOKIE_NAME = $.cookie("COOKIE_NAME");
	var _REGISTER = $.cookie("_REGISTER");
	if(_REGISTER != null){
		var namePwd = _REGISTER.split("&");
		$("#username").val(namePwd[0]);
		$("#password").val(namePwd[1]);
	}else{
		if (COOKIE_NAME != null) {
			$("input[iscookie='true']").each(function() {
				$($("#"+this.name).val($.cookie(this.name)));
			});
			 $("#remenberMe").iCheck('check');//如果没选择，可以用iCheck美化选择 
			 remenberMe = true;
		}else{
			 $("#remenberMe").iCheck('uncheck');//如果已选择，可以用iCheck取消选择
		}
	}
}

var pcLogin = {};
pcLogin.times = null;
pcLogin.token = null;
/**
 * 生成二维码
 */
pcLogin.createQrcode = function() {
    $('.qrcode-img').empty();
    var dialogContainer = document.createElement("div");
    $(dialogContainer).addClass("dialog_contain");
    $(dialogContainer).attr("style", "text-align:center;line-height:35px;font-size:16px;width:350px;margin-left:21px;");
    var qrCodeDiv = document.createElement("div");
    qrCodeDiv.setAttribute('style', 'display:inline-block;margin-top:10px');
    qrCodeDiv.setAttribute('id', 'qrcode');
    dialogContainer.appendChild(qrCodeDiv);
    document.body.appendChild(dialogContainer);
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        width: 254,
        height: 254
    });
    pcLogin.token = random(false, 36);
    qrcode.makeCode('{"loginType":"PC", "loginToken":"'+ pcLogin.token +'"}');

    $('.qrcode-img').append(dialogContainer);
    //定时器（3秒间隔向后台发送请求）
    pcLogin.times = window.setInterval(pcLogin.checkToken, 3000);
    /*return dialogContainer;*/
}

/**
 * 认证token
 */
pcLogin.checkToken = function() {
	var data = {'token': pcLogin.token};
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/login/checktoken",
        data: data,
		contentType: "application/x-www-form-urlencoded",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {

			if (result.success == true) {
                layer.msg(result.msg);
                pcLogin.stopCheck();
                window.setTimeout(function () {
                    window.location.href = result.attributes.href;
                }, 1000);

			}
        }
    })
}

/**
 * 解除计时器
 */
pcLogin.stopCheck = function() {
    window.clearInterval(pcLogin.times);
}
// function login(){
//     var username = $('#username').val();
//     var password = $('#password').val();
//     if(username == ""){
//         layer.msg("请输入用户名");
//         return;
//     }
//     if(password == ""){
//         layer.msg("请输入密码");
//         return;
//     }
//     $.ajax({
//         async : true,
//         cache : false,
//         type : 'POST',
//         url: "loginControl.do?checkUser",
//         data: "userName="+username+'&pwd='+password+'&temp='+new Date(),
//         dataType: 'json',
//         beforeSend:function(){
//             //这里是开始执行方法，显示效果，效果自己写
//             console.log("这里是开始执行方法，显示效果，效果自己写");
// //			//加载层-默认风格
//             layer.load();
//         },
//         complete:function(){
//             //方法执行完毕，效果自己可以关闭，或者隐藏效果
//             console.log("方法执行完毕，效果自己可以关闭，或者隐藏效果");
//         },
//         success: function(result) {
//             console.log(result);
//             layer.closeAll('loading');
//             if(!result.success){
//                 layer.alert(result.msg,{skin:'layer-ext-seaning',icon: 11});
//             }else{
//                 layer.msg(result.msg);
//                 window.location.href = "/login";
//             }
//         }
//     });
// }
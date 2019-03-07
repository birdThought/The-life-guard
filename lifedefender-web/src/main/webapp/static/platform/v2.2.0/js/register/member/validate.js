/*
* @Author: Administrator
* @Date:   2017-04-12 14:08:42
* @Last Modified by:   Administrator
* @Last Modified time: 2017-04-12 17:26:21
*/

var isValid = true;
/*用户名规则：6-18位字符，只能包含英文字母、数字、下划线*/
var $reg= /^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,17}$/;
$(".username").blur(function() {
	var $username=$(".username").val();
	if($reg.test($username)){
		$(".username + .msg-lay").text("");
		if ($(".username + .msg-lay").children('span').length < 1) {
            $(".username + .msg-lay").append("<span class=msg-lay-1></span>");
		}
		$(".msg-lay-1").css("display","block");

	}else if($username==""){
        isValid = false;
		$(".username + .msg-lay").text("用户名不能为空！");
		$(".username + .msg-lay").css({
			'lineHeight':"40px",
			'color':"#f00"
		});
	}else{
        isValid = false;
		$(".username + .msg-lay").text("用户名输入不正确");
		$(".username + .msg-lay").css({
			'lineHeight':"40px",
			'color':"#f00"
		});
	}
});


/*密码强度验证*/
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
	var O_color = "#999";
	var L_color = "#f00";
	var M_color = "#fe7800";
	var H_color = "#369239";
	if (pwd == null || pwd == '') {
		Lcolor = Mcolor = Hcolor = O_color;
	} else {
		var S_level = checkStrong(pwd);
		console.log(S_level);
		switch (S_level) {
		case 0:
			Lcolor = Mcolor = Hcolor = O_color;
		case 1:
			Lcolor = L_color;
			Mcolor = Hcolor = O_color;
			$(".psw_strenth span").text("安全等级低").css("color","#999");
			break;
		case 2:
			Lcolor = Mcolor = M_color;
			Hcolor = O_color;
			$(".psw_strenth span").text("安全等级中").css("color","#fe7800");
			break;
		case 3:
			Lcolor = Mcolor = Hcolor = H_color;
			$(".psw_strenth span").text("安全等级高").css("color","#369239");
			break;
		default:
			Lcolor = Mcolor = Hcolor = H_color;
			
		}
	}
		$("#strength_L").css("backgroundColor",Lcolor);
		$("#strength_M").css("backgroundColor",Mcolor);
		$("#strength_H").css("backgroundColor",Hcolor);
	return;
}

/*confirm password*/
$(".cf-pwd").blur(function(){
	var $pwd=$(".password").val();
	var $cf_pwd=$(".cf-pwd").val();
	if($cf_pwd!=$pwd){
        isValid = false;
		$(".cf-pwd + .msg-lay").text("两次密码不一致！").css({
			'lineHeight':"40px",
			'color':"#f00"
		});
	} else {
        $(".phone + .msg-lay").text("");
    }
})
$(".phone").blur(function(){
	var $phone=$(".phone").val();
	if ($phone != "") {
		if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test($phone)) {
            isValid = false;
			$(".phone + .msg-lay").text("手机格式不对").css({
				'lineHeight':"40px",
				'color':"#f00"
			})
			return;
		} else {
            $(".phone + .msg-lay").text("");
		}
	}
})

/**
 * 注册会员
 */
function register() {
	/*if (!isValid) {
		layer.msg('请完善注册信息');
		return;
	}*/


    var url = "memberControl.do?registerMemberByMobile";
    var userName = $.trim($(".username").val());
    var password = $(".password").val();
    var mobile = $('.phone').val();
	var code = $('.code').val();
    var data = "userName=" + userName + "&password=" + password + "&code=" + code + "&mobile=" + mobile;
    var load;
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url : url,
        data : data + '&temp=' + new Date(),
        dataType : 'json',
        beforeSend : function() {
            load = layer.load();
        },
        complete : function() {
            // 方法执行完毕，效果自己可以关闭，或者隐藏效果
        },
        success : function(result) {
            layer.close(load);
            if (result.success) {
            	var userId = result.attributes.userId;
                window.location.href = "memberControl.do?registerMemberByMobileTwo&id=" + userId;
            } else {
                layer.msg(result.msg);
            }
        }
    });
}


// 通过短信短信获取验证码
function sendCode() {
    var mobile = $(".phone").val();// 手机号码
	if (mobile == '') {
		layer.msg('请输入手机号码');
		return;
	}
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
}

var InterValObj; // timer变量，控制时间
var count = 60; // 间隔函数，1秒执行
var curCount;// 当前剩余秒数
var codeLength = 6;// 验证码长度
function sendMessage() {
    curCount = count;
    // 设置button效果，开始计时
    $(".get-code").attr("disabled", "true");
    $(".get-code").val("重新获取（" + curCount + "）秒");
    $(".get-code").css("background-color", "#ccc");
    $(".get-code").css("border", "1px solid #ccc");
    InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
}

// timer处理函数
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);// 停止计时器
        $(".get-code").removeAttr("disabled");// 启用按钮
        $(".get-code").val("重新发送验证码");
        $(".get-code").css("background-color", "#48c859");
        $(".get-code").css("border", "1px solid #48c859");
        code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
    } else {
        curCount--;
        $(".get-code").val("重新获取（" + curCount + "）秒");
    }
}

/**
 * 注册协议
 */
function clickHere() {
    var href = "/register?showAgreement";
    layer.open({
        type: 2,
        title: false,
        shadeClose: true,
        moveType: 1,
        area: ["1000px", "800px"],
        content: href
    });
}

/**
 * 提交个人信息
 */
function submitUserInfo() {
	var json = $('.base-info').serializeArray();
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url : "memberControl.do?submitUserInfo",
        data : json,
        contentType : 'application/x-www-form-urlencoded',
        beforeSend : function() {

        },
        complete : function() {
            // 方法执行完毕，效果自己可以关闭，或者隐藏效果
        },
        success : function(result) {
            layer.msg(result.msg);
            if (result.success) {
            	window.location.href = "memberControl.do?registerMemberByMobileThree"
            }
        }
    });
}


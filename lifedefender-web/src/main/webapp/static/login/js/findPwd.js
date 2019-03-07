$(function(){
	// 发送验证码
	/*jQuery("form.findPsw").on("click", "#btnCode", function(){
		
		$("[name='userId']").val("");	// 清空隐藏域中的内容
	 	var mobileOrEmail = $("#mobileOrEmail").val();//手机号码 或者邮箱地址
	 	
	 	if(mobileOrEmail == "") {
	 		layer.msg("请输入手机号或邮箱");
	 		return ;
	 	}
	 	var $element = jQuery("#btnCode");
	 	/!* 判断是通过邮箱还是手机发出的找回密码请求 *!/
	 	var regex_checkEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;	// 邮箱正则表达式
	 	var regex_checkMobile = /^1[3|4|5|7|8]\d{9}$/;
	 	if(regex_checkEmail.test(mobileOrEmail)){
	 		sendCode.sendRandCodeToEmail(mobileOrEmail, $element, "reset", jQuery("[name='userId']"));
	 	} else {
	 		if(regex_checkMobile.test(mobileOrEmail)) {
	 			sendCode.sendRandCodeToMobile(mobileOrEmail, $element, "reset", jQuery("[name='userId']"));
	 		} else {
	 			layer.msg("请输入正确的手机");
	 		}
	 	}
	});*/
	findPwd.init();
});

var findPwd = {};
/** 组件初始化 */
findPwd.init = function () {
    // 发送验证码
    jQuery(".sendCode").on("click", function(){
        findPwd.sendValidCode();
	});
    jQuery('.submitCode').on('click', function () {
        findPwd.checkValidCode();
	});
}
/** 发送验证码 */
findPwd.sendValidCode = function () {
    $('.userId').val("");	// 清空隐藏域中的内容
    var mobileOrEmail = $(".mobileOrEmail").val();//手机号码 或者邮箱地址

    if(mobileOrEmail == "") {
        layer.msg("请输入手机号或邮箱");
        return ;
    }
    var $element = jQuery(".sendCode");
	/* 判断是通过邮箱还是手机发出的找回密码请求 */
    var regex_checkEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;	// 邮箱正则表达式
    var regex_checkMobile = /^1[3|4|5|7|8]\d{9}$/;
    if(regex_checkEmail.test(mobileOrEmail)){
        sendCode.sendRandCodeToEmail(mobileOrEmail, $element, "reset", jQuery("[name='userId']"));
    } else {
        if(regex_checkMobile.test(mobileOrEmail)) {
            sendCode.sendRandCodeToMobile(mobileOrEmail, $element, "reset", jQuery("[name='userId']"));
        } else {
            layer.msg("请输入正确的手机");
        }
    }
}
/** 验证验证码是否正确 */
findPwd.checkValidCode = function () {
	var userId = $('.userId').val();
	var code = $('.code').text();
	var data = {
		'userId': userId,
		'code': code
	}
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url : 'releaseControl.do?checkValidCode',
        data : data,
		contentType: 'application/x-www-form-urlencoded',
        dataType : 'json',
        beforeSend : function() {
            layer.load();
        },
        complete : function() {
        },
        success : function(result) {
            // layer.msg(result.msg);
            if (result.success) {

            } else {
                layer.msg(result.msg);
            }
        }
    });
}
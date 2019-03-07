

/** step one */
var findPwdOne = {};
/** 组件初始化 */
findPwdOne.init = function () {
    // 发送验证码
    jQuery(".sendCode").on("click", function(){
        findPwdOne.sendValidCode();
	});
}
/** 发送验证码 */
findPwdOne.sendValidCode = function () {
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
/** 验证码是否为空 */
findPwdOne.checkCode = function () {
    if ($('.codeContent').val() == '') {
        layer.msg('请输入验证码');
        return false;
    }
}

/** step two */
var findPwdTwo = {};
/** 组件初始化 */
findPwdTwo.init = function () {
    findPwdTwo.validatePwd();
    $('.submitPwd').on('click', function () {
        findPwdTwo.resetPassword();
    })
}
/** 验证两次密码是否一致 */
findPwdTwo.validatePwd = function () {
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
findPwdTwo.resetPassword = function () {
    if (!$("[name='conform_password']").hasClass("valid")) {
        return;
    }
    console.log('len', $("[name='conform_password']").val().length);
    if ($("[name='conform_password']").val().length < 6) {
        return;
    }
    var userId = $("input[name='userId']").val();
    var validCode = $("input[name='validCode']").val();
    var password = $("input[name='password']").val();
    var data = {
        'userId': userId,
        'validCode': validCode,
        'password': password
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url : "releaseControl.do?resetPassword",
        data : data,
        dataType : 'json',
        beforeSend : function() {
            layer.load();
        },
        complete : function() {
        },
        success : function(result) {
            layer.msg(result.msg);
            if (result.success) {
                window.location.href = "releaseControl.do?success&userName="
                    + result.obj;
            } else {
                layer.confirm( '重置密码失败，请重新操作', {btn : [ '是', '否' ]},
                    function() {
                        window.location.href = "loginControl.do?forgotPwd";
                    });
            }
        },
        error : function(e) {
            console.log(e);
        }
    });
}
/** 验证码强度 */
findPwdTwo.CharMode = function (iN) {
    if (iN >= 48 && iN <= 57) // 数字
        return 1;
    if (iN >= 65 && iN <= 90) // 大写字母
        return 2;
    if (iN >= 97 && iN <= 122) // 小写
        return 4;
    else
        return 8; // 特殊字符
}
/** 计算出当前密码当中一共有多少种模式 */
findPwdTwo.bitTotal = function (num) {
    modes = 0;
    for (i = 0; i < 4; i++) {
        if (num & 1)
            modes++;
        num >>>= 1;
    }
    return modes;
}
/** 返回密码的强度级别 */
findPwdTwo.checkStrong = function (sPW) {
    if (sPW.length <= 4)
        return 0; // 密码太短
    Modes = 0;
    for (i = 0; i < sPW.length; i++) {
        // 测试每一个字符的类别并统计一共有多少种模式.
        Modes |= findPwdTwo.CharMode(sPW.charCodeAt(i));
    }
    return findPwdTwo.bitTotal(Modes);
}
/** 当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色 */
findPwdTwo.pwStrength = function (pwd) {
    O_color = "#DDDDDD";
    L_color = "#FF0000";
    M_color = "#FF9900";
    H_color = "#33CC00";
    if (pwd == null || pwd == '') {
        Lcolor = Mcolor = Hcolor = O_color;
        strength_notice.style.display = 'none';
    } else {
        S_level = findPwdTwo.checkStrong(pwd);
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


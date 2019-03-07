(function ($) {
    $.fn.countStart = function (count) {
        var obj = $(this);
        obj[0].disabled = true;
        obj.css("background", "#eee");
        obj.text("重新获取（" + count + "）");
        var interval = setInterval(function () {
            --count;
            obj.text("重新获取（" + count + "）");
            if (count == 0) {
                clearInterval(interval);
                obj.text("重新获取");
                obj[0].disabled = false;
                obj.css("background", "#fff");
            }
        }, 1000);
    }
})($)

var EmailVerify={
    sendEmailCode:function (obj) {
        var email=$("#email").val();
        if(!checkUtils.checkEmail(email)){
            layer.msg("请输入正确的邮箱");
            return;
        }
        layer.load();
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: 'releaseControl.do?sendValidCodeToEmail&email='+email+'&cache=email',
            success: function (result) {
                if (!result.success) {
                    layer.msg("发送验证码失败！");
                    return;
                }
                layer.msg("发送验证码成功，请留意稍后的短信")
                $(obj).countStart(60);
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    },bindEmail:function () {
        var email=$("#email").val();
        if(!checkUtils.checkEmail(email)){
            layer.msg("请输入正确的邮箱");
            return;
        }
        var code=$("#code").val();
        if($.trim(code)==''){
            layer.msg("请输入邮箱验证码");
            return;
        }
        var json={
            email:email,
            code:code
        }
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: 'orgSetControl.do?bindEmail',
            data:JSON.stringify(json),
            success: function (result) {
                if (!result.success) {
                    layer.msg(result.msg);
                    return;
                }
                layer.msg("绑定成功");
                setTimeout(function () {
                    history.go(-1);
                },1000);
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    }
}

var VeriMobile1 = {
    checkMobile: function (isBindNew) {
        var code = $("#dx").val();
        if ($.trim(code) == '') {
            layer.msg("请输入短信验证码");
            return;
        }
        layer.load();
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: 'orgSetControl.do?verifyCode&code='+code,
            success: function (result) {
                if (!result.success) {
                    layer.msg(result.msg);
                    return;
                }
                if (isBindNew) {
                    window.location.href ="orgSetControl.do?bindNewMobile";
                    return;
                }
                window.location.href = "orgSetControl.do?verifySuccess";
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    },
    bindMobile: function () {
        var code = $("#dx").val();
        if ($.trim(code) == '') {
            layer.msg("请输入短信验证码");
            return;
        }
        layer.load();
        var mobile = $("#newMobile").val();
        if (!checkUtils.checkPhone(mobile)) {
            layer.msg("请输入正确的手机号码");
            return;
        }
        var data = "&code=" + code + "&newMobile=" + mobile;
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: 'orgSetControl.do?verifyCode' + data,
            success: function (result) {
                if (!result.success) {
                    layer.msg(result.msg);
                    return;
                }
                window.location.href = "orgSetControl.do?verifySuccess&bindNew=true";
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    }, getMobileVerify: function (obj, isBindNew) {
        var url = "";
        if (isBindNew) {
            var mobile = $("#newMobile").val();
            if (!checkUtils.checkPhone(mobile)) {
                layer.msg("请输入正确的手机号码");
                return;
            }
            url = "/register?sendValidCode&cache=mobile&mobile=" + mobile;
        } else {
            url = "orgSetControl.do?sendVerify";
        }
        layer.load();
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: url,
            success: function (result) {
                if (!result.success) {
                    layer.msg("发送验证码失败！");
                    return;
                }
                layer.msg("发送验证码成功，请留意稍后的短信")
                $(obj).countStart(60);
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    }
}
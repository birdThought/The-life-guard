/**
 * 登录
 * author: wenxian.cai
 * date: 2017/9/22 15:12
 */

var login = {};

login.init = function () {
    layui.use('layer');

};

login.keyLogin = function (evt){
    evt = (evt) ? evt : ((window.event) ? window.event : ""); //兼容IE和Firefox获得keyBoardEvent对象  
    var key = evt.keyCode?evt.keyCode:evt.which; //兼容IE和Firefox获得keyBoardEvent对象的键值
    if (key == 13) {
        document.getElementById("submit").click();
    }
};

/**登录*/
login.login = function () {
    if (!login.check()) {
        return;
    }
    var url = '/login/check-user';
    var data = {
        userName: $.trim($('.userName').val()),
        password: $.trim($('.password').val()),
    };
    http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function () {
        window.location.href = '/index';
    })

};

/**验证*/
login.check = function () {
	 if($.trim($('#verifyCode').val()).toUpperCase()<=0){
	        layer.msg('请输入验证码')
	        return false;
	    }else if($.trim($('#verifyCode').val()).toUpperCase()!=code){
	        layer.msg('对不起,验证码输入错误!')
	        return false;
	    }
    if ($.trim($('.userName').val()) == '') {
        layer.msg('请填写用户名');
        $('.userName').focus();
        return false;
    }
    if ($.trim($('.password').val()) == '') {
        layer.msg('请填写密码');
        $('.password').focus();
        return false;
    }
    //todo 长度、特殊字符等限制
    return true;
};

/**退出登录*/
login.logout = function () {
    var url = '/login/logout';
    http.ajax.post(true, true, url, null, http.ajax.CONTENT_TYPE_1, function () {
        window.location.href = '/login';
    })
};

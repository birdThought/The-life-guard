jQuery(document).ready(function ($) {
    // If firefox
    if (navigator.userAgent.toLowerCase().match(/firefox/)) {
        $('.browser-warning').removeClass('hidden');
        setTimeout(function () {
            $('.browser-warning').addClass('hidden');
        }, 6 * 1000);
    }
    $('#window').attr('style', '');
});

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

login.rememberMe = false; // 记住我
/**登录*/
login.login = function () {
    if (!login.check()) {
        return;
    }
    var username = $('#username').val();    // 账号
    var password = $('#password').val();    // 密码
    var randCode = $('#verifyCode').val();  // 验证码
    
    username = encodeStr(username);
    password = encodeStr(password);
    
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/login/checkuser",
        data: "userName="+username+'&pwd='+password+'&randCode='+randCode+'&lut=o&rememberMe='+login.rememberMe+'&temp='+new Date(),
        dataType: 'json',
        beforeSend:function(){
//          //加载层-默认风格
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
};

/**验证*/
login.check = function () {
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
    var needCode = !($("div[name = 'codeDiv']").hasClass('hide'));
    var blankCode = $.trim($('#verifyCode').val()) == '';
    if (needCode && blankCode) {
        layer.msg('请填写验证码');
        $("#verifyCode").focus();
        return false;
    }
    return true;
}

login.checkPwdStrong = function(content) { //密码强度：0：弱，1：弱：2：中，3：强，4：超强
    if (content.length <= 4)
        return 0; // 密码太短
    var mode = 0;
    for (var i = 0; i < content.length; i++) {
        // 测试每一个字符的类别并统计一共有多少种模式.
        var c=content.charCodeAt(i);
        var temp=0;
        if(c>=48&&c<=57){//数字
            temp=1;
        }else if(c>=65&&c<=90){//大写字母
            temp=2;
        }else if(c>=97&&c<=122){//小写
            temp=4;
        }else{
            temp=8;//特殊字符
        }
        mode |= temp;
    }
    var strength = 0;
    for (var j = 0; j < 4; j++) {
        if (mode & 1)
            strength++;
        mode >>>= 1;
    }
    return strength;
}

//显示验证码
function showRandCode(isLoginError, userName) {
  // 设置RAND_CODE
  if(isLoginError) {
      $.cookie("RAND_CODE", userName, "/", 24);
  }
  var RAND_CODE = $.cookie("RAND_CODE");
  
  if(RAND_CODE == null || RAND_CODE != userName) {
//      $("div[name = 'codeDiv']").animate({'display':'none'});
      $("div[name = 'codeDiv']").addClass('hide');
      // 修改高度
      $("#window").animate({'height':'330px'});
      return;
  }
  if (RAND_CODE == userName || isLoginError) {
      //登录错误显示验证码
//      $("div[name = 'codeDiv']").animate({'display':'block'});
      $("div[name = 'codeDiv']").removeClass('hide');
      // 修改高度
      $("#window").animate({'height':'415px'});
  }
}

//刷新验证码
function reloadRandCodeImage() {
  // 获取验证码
  var date = new Date();
  var img = document.getElementById("randCodeImage");
  img.src='/randCodeImage.do?a=' + date.getTime();
}

//设置cookie
function setCookie(password) {
  if (login.rememberMe) {
      $("input[iscookie='true']").each(function() {
          $.cookie(this.name, $("#" + this.name).val(), "/", 24);
          $.cookie("COOKIE_NAME","true", "/", 24);
      });
  } else {
      $("input[iscookie='true']").each(function() {
          $.cookie(this.name, null);
          $.cookie("COOKIE_NAME", null);
      });
  }
  $.cookie("RAND_CODE", null);
  $.cookie("_REGISTER", null);
  $.cookie("pwdStrong", login.checkPwdStrong(password), {expires: 7, path: '/'});
  $.cookie("loginTunnel", 'hongyuantang', {expires: 7, path: '/'});  // 在cookie中标识本次登录通道
}

//读取cookie
function getCookie() {
  var _userType = $.cookie("_userType");
  var _userTypeClass = "userSp2";
  $("."+_userTypeClass).css("background", "#48c858").siblings().css("background", "#fff");
  var COOKIE_NAME = $.cookie("COOKIE_NAME");
  var _REGISTER = $.cookie("_REGISTER");
  if (_REGISTER != null) {
      var namePwd = _REGISTER.split("&");
      $("#username").val(namePwd[0]);
      $("#password").val(namePwd[1]);
  } else {
      if (COOKIE_NAME != null) {
          $("input[iscookie='true']").each(function() {
              $($("#"+this.name).val($.cookie(this.name)));
          });
           $("#rememberMe").iCheck('check');//如果没选择，可以用iCheck美化选择 
           login.rememberMe = true;
      } else {
           $("#rememberMe").iCheck('uncheck');//如果已选择，可以用iCheck取消选择
      }
  }
}

// 对特殊字符进行转义
function encodeStr(str) {
    return str.replace(new RegExp(/(&)/g),'＆');
}

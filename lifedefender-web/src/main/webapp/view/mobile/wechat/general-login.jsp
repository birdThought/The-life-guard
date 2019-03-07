<%--实现一个普通登录页，供第三方使用，无需绑定openid，每次进入都需要在此登录--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <%--<meta name="flexible" content="initial-dpr=2" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <link rel="apple-touch-icon" href="favicon.png">
    <link rel="Shortcut Icon" href="favicon.png" type="image/x-icon">
    <title>用户登录</title>
    <style type="text/css">
        .username, .password {
            width: 80%;
            height: 1.2rem;
            border: 0rem;
            border-bottom: 0.01rem solid #9a9a9a;
            font-size: 16px;
            vertical-align: middle;
            margin-left: 0.1rem;
        }
        .bind {
            width: 80%;
            height: 1.5rem;
            border: 0rem;
            font-size: 16px;
            margin-top: 1.2rem;
            color: white;
            background-color: #64CC77;
            border-radius: 1rem;
        }
        .login img {
            width: 0.7rem;
            height: 1rem;
            vertical-align: middle;
        }

    </style>
</head>
<body >
<!-- 页面结构写在这里 -->
<div style="width: 100%;">
    <div style="height: 50%;">
        <img src="/static/mobile/wechat/images/we-logo.png" style="width: 100%; height: 100%;">
        <div style="text-align: center;margin-bottom: 1rem" v-cloak v-if = "org != null"><img src="/static/mobile/wechat/images/${org}.png" style="width: 50%; height: 100%;"></div>
    </div>
    <div class="login" style="text-align: center;">
        <img src="/static/mobile/wechat/images/login_icon_phone.png">
        <input class="username" type="text" placeholder="请输入账号/手机号"><br><br><br>
        <img src="/static/mobile/wechat/images/login_icon_password.png" style="width: 0.8rem;">
        <input class="password" type="password" placeholder="请输入密码">
        <button class="bind" type="button" value="登 录" onclick="loginAccount();">
            登 录
        </button>
    </div>
</div>
</body>
</html>

<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    var loginAccount = function () {
        var data = {
            userName: $('.username').val(),
            password: $('.password').val()
        }
        if (data.userName == '') {
            layer.msg('请输入账号');
            return;
        }
        if (data.password == '') {
            layer.msg('请输入密码');
            return;
        }
        $.ajax({
            async : true,
            cache : false,
            type: 'PATCH',
            contentType: 'application/json;charset=utf-8',
            url: '/wechat/general-login-do',
            data: JSON.stringify(data),
            beforeSend:function(){
                layer.load(2);
            },
            success: function(result){
                setTimeout(function () {
                    layer.closeAll('loading');
                    if (result.success) {
                        layer.msg('登录成功');
                        //跳转自动登录接口
                        window.location.href = '/wechat/health-record-page';
                        return;
                    }
                    layer.msg(result.msg);
                }, 500)
            }
        });
    }

</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="/static/plugin/login/css/style.css" media="screen" type="text/css"/>
    <link rel="stylesheet" href="/static/plugin/layui/v2.1.2/css/layui.css" media="screen" type="text/css"/>
</head>
<body onkeydown="login.keyLogin(event);" style="overflow: hidden;">
<div id="Logo"><img src="/static/images/Logo.png" alt=""></div>
<div id="container" style="width: 100%;height: 100%;position: absolute;">
    <div id="anitOut"></div>
</div>
<div id="window" style="display:none;">
    <div id="loginTitle" class="fadeIn" style="text-align:center;font-family:'Microsoft YaHei';font-size:44px;bottom:88px;position:relative;display:block">生命守护-渠道商后台</div>
    <div class="page page-front">
        <div id="login_form">
            <div class="page-content">
                <div class="input-row">
                    <!--<label class="label fadeIn"></label>-->
                    <i class="fadeIn iconfont icon-guanliyuan"></i>
                    <input id="username" name="username" placeholder="请输入管理员账号" type="text" data-fyll="pineapple" class="input fadeIn delay1 userName"/>
                </div>
                <div class="input-row">
                    <!--<label class="label fadeIn delay2"></label>-->
                    <i class="fadeIn iconfont icon-mima"></i>
                    <input id="password" name="password" type="password" placeholder="请输入您的密码" data-fyll="hackmeplease" class="input fadeIn delay3 password"/>
                </div>
                <div class="input-row">
                    <!--<label class="label fadeIn delay2"></label>-->
                    <i class="fadeIn iconfont icon-dunpai"></i>
                    <input id="verifyCode" name="verifyCode" type="text" placeholder="请输入右侧的验证码" data-fyll="hackmeplease" class="input fadeIn delay3 verifyCode"/>
                    <p class="verifyCode-show fadeIn">
                        <input type="button" id="checkCode" class="code" onclick="createCode()" />
                        <!--<a href="#" onclick="createCode()">看不清楚？</a>-->
                    </p>
                </div>
                </div>
                <div class="input-row perspective">
                    <button id="submit" class="button load-btn fadeIn delay4" onclick="login.login()">
                        <span class="default"><i class="ion-arrow-right-b"></i>立即登录</span>
                        <div class="load-state">
                            <div class="ball"></div>
                            <div class="ball"></div>
                            <div class="ball"></div>
                        </div>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/plugin/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/static/plugin/layui/v2.1.2/layui.js"></script>
<script type="text/javascript" src="/static/plugin/login/js/cav.js"></script>
<script type="text/javascript" src='/static/plugin/login/js/fyll.js'></script>
<script type="text/javascript" src="/static/plugin/login/js/index.js"></script>
<script type="text/javascript" src="/static/js/common/http.js?v=1.1.0"></script>
<script type="text/javascript" src="/static/js/login/login.js?v=1.1.0"></script>
<script>
    login.init();
</script>
</body>
</html>

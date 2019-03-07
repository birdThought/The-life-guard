<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,layui"></t:base>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/HYTLogin.css">
    <title>宏元堂登陆页面</title>
<style type="text/css">
.hide{
    display: none;
}
</style>
</head>
<body onkeydown="login.keyLogin(event);" style="overflow: hidden;">
<div id="Logo" ><img class="animated zoomInRight" src="/static/platform/v2.2.0/images/HYTLogo.png" alt=""></div>
<div id="container" style="width: 100%;height: 100%;position: absolute;">
    <div id="anitOut"></div>
</div>
<div id="window" style="display:none;">
    <img class="fadeIn" src="/static/platform/v2.2.0/images/manager-system.png" id="loginTitle" style="bottom:88px;position:relative;left: 103px;">
    <div class="translucent"></div>
    <div class="page page-front">
        <div id="login_form">
            <div class="page-content">
                <div class="input-row">
                    <i class="fadeIn iconfont icon-guanliyuan"></i>
                    <input id="username" name="username" type="text" placeholder="请输入管理员账号" data-fyll="pineapple" class="input fadeIn delay1 userName"/>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <input id="password" name="password" placeholder="请输入您的密码" type="password" data-fyll="hackmeplease" class="input fadeIn delay3 password"/>
                </div>
                <div name="codeDiv" class="input-row hide">
                    <i class="fadeIn iconfont icon-dunpai"></i>
                    <input id="verifyCode" name="verifyCode" placeholder="请输入右侧验证码" type="text" data-fyll="hackmeplease" class="input fadeIn delay3 verifyCode"/>
                    <p class="verifyCode-show fadeIn">
                        <img id="randCodeImage" src="/randCodeImage.do" style="display:block;height:60px;width:145px;" onclick="reloadRandCodeImage()" />
                    </p>
                </div>
                <div class="input-row perspective">
                    <button id="submit" class="button load-btn fadeIn delay4" onclick="login.login()">
                        <span class="default"><i class="ion-arrow-right-b"></i>立即登录</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="background-color:#EEDDAA;width:100%;min-height:35px;_height:35px;line-height: 35px;text-align:center;position:fixed;bottom:0;">
    Copyright ©2017 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 技术支持：生命守护健康平台
</div>
</body>
<script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
<script src="/static/platform/v2.2.0/js/login/hyt-login.js?v=1.0.6"></script>
</html>
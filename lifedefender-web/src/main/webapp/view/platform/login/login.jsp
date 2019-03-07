<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type="jquery2.11,layer,jquery"></t:base>

    <meta charset="utf-8">
    <meta name=description content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/login.css">
    <%--<script src="/static/platform/v2.2.0/js/jquery-2.1.1.min.js"></script>--%>
    <title>生命守护</title>
</head>
<body>
<%@include file="../context/header.jsp"%>
	<section class="login_content">
		<div class="g-right">
			<form action="" class="login_form">
				<!-- pc标准登录 -->
				<div class="login_header">
					<h3>生命守护服务平台</h3>
					<div class="login-switch" >
						<i class="login_icon quick"></i> <i class="login_icon static"></i>
					</div>
				</div>
				<div class="static-form">
					<ul style="padding-bottom: 40px;">
						<li><input type="text" id="username" name="username" placeholder="用户名 / IMEI"></li>
						<li><input type="password" id="password" name="password" placeholder="密码"></li>
						<li>
							<p style="display: none;margin-bottom:18px" id="p_randCode">
								<input type="text" id="randCode" name="randCode" placeholder="请输入验证码"/>
								<img id="randCodeImage" src="randCodeImage.do" style="margin:8px 0;display:block" />
							</p>
						</li>
						<li class="setFlex">
							<div>
								<input type="checkbox" class="magic-checkbox" id="rememberMe" iscookie="true">
								<label for="rememberMe">自动登录</label>
							</div>
							<div>
								<a href="javascript:_forgotPwd();" type="button" class="forget">忘记密码？</a>
							</div>
						</li>
						<li><input type="submit" value="登录" data-redirectUrl="${redirectUrl}"></li>
						<!-- <li>
							<p>
								还没有生命守护平台账号? <a href="register" class="register-now">立即注册</a>
							</p>
						</li> -->
					</ul>
					<ol class="thirdParty" style="display:none;">
						<li>使用第三方账户登录</li>
						<li class="thirdParty_list">
							<%--暂时将连接放在前台 TODO--%>
							<a href="https://graph.qq.com/oauth/show?which=Login&display=pc&response_type=code&client_id=101395798&redirect_uri=http://www.lifekeepers.cn/oauthLoginControl.do?qq&state=1&scope=get_user_info,get_info">
								<span></span>
							</a>
								<%--暂时将连接放在前台 TODO--%>
							<a href="https://open.weixin.qq.com/connect/qrconnect?appid=wx228d7d84c2feda83&redirect_uri=http://www.lifekeepers.cn/oauthLoginControl.do&response_type=code&scope=snsapi_login&state=STATE">
								<span></span>
							</a>

						</li>
					</ol>
				</div>
				<!-- 扫码快速登录 -->
				<div class="quick-form none">
					<ul class="qrcode-mod">
						<li class="qrcode-main">
							<div class="qrcode-img" id="" style="opacity: 1;">
								<img src="">
							</div>
							<div class="qrcode-help"></div>
							<%--<div class="msg-err none">
								<h6>二维码已失效</h6>
								<a href="javascript:;" class="refresh">请点击刷新</a>
							</div>--%> <!--  <div class="qrcode-msg">
								<div class="msg-ok">
									<div class="msg-icon">
										<i class="iconfont icon-ok"></i>
										<i class="iconfont icon-phone"></i>
									</div>
									<h6>扫描成功！</h6>
									<p>请在手机上确认登录</p>
									<div class="link"><a href="#" class="light-link J_QRCodeRefresh">返回二维码登录</a></div>
								</div>
							</div> -->
						</li>
						<li>
							<h3>请使用生命守护手机用户端扫码登录</h3>
						</li>
					</ul>
				</div>
			</form>
		</div>
	</section>
<%@include file="../context/footer.jsp"%>
</body>
<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/plugins/icheck/icheck-1.0.2.js"></script>
<script type="text/javascript" src="/static/platform/v2.2.0/js/login/login.js"></script>
<script type="text/javascript" src="/static/common/js/common.js"></script>
<script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
<script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
<script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="/static/plugins/qrcode/qrcode.min.js"></script>

<script>
    var quick_btn=document.getElementsByClassName("quick")[0];
    var static_btn=document.getElementsByClassName("static")[0];
    var quick_content=document.getElementsByClassName("quick-form")[0];
    var static_content=document.getElementsByClassName("static-form")[0];
    var msg_err=document.querySelector(".msg-err");
    var qrcode_img=document.querySelector(".qrcode-img");
    quick_content.style.display="none";
    quick_btn.onclick=function () {
        quick_content.style.display="block";
        static_content.style.display="none";
        this.style.display="none";
        static_btn.style.display="block";
        //生成二维码
        pcLogin.createQrcode();


    }
    static_btn.onclick=function () {
        static_content.style.display="block";
        quick_content.style.display="none";
        this.style.display="none";
        quick_btn.style.display="block";
        pcLogin.stopCheck();

    }

    /*function _block(){
        if(quick_content.style.display=="block"){
            msg_err.style.display="block";
            qrcode_img.style.opacity="0.4";
        }

    }
    var timer=setTimeout("_block()",5000);*/
</script>
</html>



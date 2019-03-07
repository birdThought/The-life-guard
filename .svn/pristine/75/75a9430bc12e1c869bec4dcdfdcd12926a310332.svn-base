
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type="jquery2.11,layer,jquery"></t:base>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
<!-- 	<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/adapter/reset.css"> -->
	<link rel="stylesheet" href="static/officialwebsite/index/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/common/css/mainheader_1.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/magic-check.css">
	<%--<link rel="stylesheet" type="text/css" href="/static/login/css/login.css">--%>

	<link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
	<link rel="stylesheet" type="text/css" href="/static/login/css/login_v2_0.css">
	<title>登录</title>
	<!--[if lt IE 9]>
	<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body onkeyup="_keyDown()">
<%@include file="/context/mainHeader_1.jsp"%>
<%--<header>
	<div class="banxin clearfix">
		<div class="logo fl">
			<a href="#">
				<img src="images/logo.png" alt="">
			</a>
		</div>
		<ul class="fr clearfix navbar-nav">
			<li><a href="#" class="on">首页</a></li>
			<li>
				<a href="#">APP介绍</a>
				<ul>
					<li><a href="#">机构端</a></li>
					<li><a href="#">用户端</a></li>
				</ul>
			</li>
			<li>
				<a href="#">产品与服务</a>
				<ul>
					<li><a href="#">机构服务</a></li>
					<li><a href="#">智能设备</a></li>
				</ul>
			</li>
			<li><a href="#">商家入驻</a></li>
			<li>
				<a href="#" class="scroll-link">关于我们</a>
				<ul>
					<li><a href="#">公司介绍</a></li>
					<li><a href="#">帮助中心</a></li>
					<li><a href="#">联系我们</a></li>
				</ul>
			</li>
			<li class="register"><a href="#">注册</a></li>
			<li class="login"><a href="#">登录</a></li>
		</ul>
	</div>
</header>--%>
<%--<section class="item-content">
	<form id="item-login" class="form" novalidate="novalidate">
		<div class="loginContent">
			<ul class="loginContent">
				<li>
					<h3>生命守护服务平台</h3>
				</li>
				<li>
					<input id="username" name="username" class="text" type="text"
						   required placeholder="用户名/手机号码" iscookie="true" value="">
				</li>
				<li>
					<input name="password" type="password" class="text required" value=""
						   id="password" placeholder="输入密码" iscookie="true">
				</li>
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
				<li>
					<input type="submit" class="btnD" value="登录" data-redirectUrl="${redirectUrl}">
				</li>
				<li>
					<p>还没有生命守护平台账号? <a href="javascript:_register();">立即注册</a></p>
				</li>
			</ul>
		</div>
	</form>
</section>--%>
<section class="login_content">
	<div class="g-right">
		<form action="" class="login_form">
			<!-- pc标准登录 -->
			<div class="login_header">
				<h3>生命守护服务平台</h3>
				<div class="login-switch">
					<i class="login_icon quick"></i>
					<i class="login_icon static"></i>
				</div>
			</div>
			<div class="static-form">
				<ul>
					<li>
						<input type="text" placeholder="请输入用户名/手机号码" class="username">
					</li>
					<li>
						<input type="password" placeholder="请输入密码" class="password">
					</li>
					<li class="setFlex">
						<div>
							<input type="checkbox" class="magic-checkbox" id="remember">
							<label for="remember">自动登录</label>
						</div>
						<div>
							<a href="javascript:_forgotPwd();" type="button" class="forget">忘记密码？</a>
						</div>
					</li>
					<li>
						<input type="submit" value="登录">
					</li>
					<li>
						<p>还没有生命守护平台账号? <a href="#" class="register-now">立即注册</a></p>
					</li>
				</ul>
				<ol class="thirdParty">
					<li>使用第三账号登录</li>
					<li class="thirdParty_list">
						<a href="">
							<span></span>
						</a>
						<a href="https://open.weixin.qq.com/connect/qrconnect?appid=wx228d7d84c2feda83&redirect_uri=http://test.lifeshs.com/oauthLoginControl.do&response_type=code&scope=snsapi_login&state=STATE">
							<span></span>
						</a>
					</li>
				</ol>
			</div>
			<!-- 扫码快速登录 -->
			<div class="quick-form">
				<ul class="qrcode-mod">
					<li class="qrcode-main">
						<div class="qrcode-img" id="" style="opacity: 1;">
							<img src="static/images/login-code.png">
						</div>
						<div class="qrcode-help"></div>
						<div class="msg-err none">
							<h6>二维码已失效</h6>
							<a href="javascript:;" class="refresh">请点击刷新</a>
						</div>
						<!--  <div class="qrcode-msg">
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
<%@include file="/context/mainFooter_1.jsp"%>
<%--<footer>
	<div class="banxin">
		<ul>
			<li>
				<h5>广东若普医疗科技有限公司&copy;版权所有</h5>
				<p><a href="#">粤ICP备13019866号-3</a></p>
				<p>400-026-1003</p>
			</li>
			<li>
				<dl>
					<dt>关于我们</dt>
					<dd class="offset-bottom"><a href="#">关于健康管理平台</a></dd>
					<dd><a href="#">官方网站</a></dd>
					<dd><a href="#">加入我们</a></dd>
					<dd><a href="#">联系我们</a></dd>
				</dl>
				<dl>
					<dt>免费下载APP</dt>
					<dd class="offset-bottom"><a>应用APP下载</a></dd>
					<dd><a>管理APP下载</a></dd>
				</dl>
			</li>
			<li>
				<a>
					<img src="images/qrcode.png" alt="">
					<h4>生命守护公众号</h4>
				</a>
			</li>
		</ul>
	</div>
</footer>--%>

<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/login/js/icheck.js"></script>
<script type="text/javascript" src="/static/login/js/login.js"></script>
<script type="text/javascript" src="/static/common/js/common.js"></script>
<script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
<script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
<script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
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
    }
    static_btn.onclick=function () {
        static_content.style.display="block";
        quick_content.style.display="none";
        this.style.display="none";
        quick_btn.style.display="block";

    }

    function _block(){
        if(quick_content.style.display=="block"){
            msg_err.style.display="block";
            qrcode_img.style.opacity="0.4";
        }

    }
    var timer=setTimeout("_block()",5000);
</script>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<header class="header">
	<div class="inner-header">
		<div style="float: left;">
			<a style="cursor:pointer;" href="indexControl.do?index"><img src="/static/images/logo.png" alt=""></a>
		</div>
		<ul class="nav-bar fr">
			<li><a href="indexControl.do?index" class="active">首页</a></li>
			<li><a href="indexControl.do?healthService">健康服务</a></li>
			<li><a href="indexControl.do?inteDevice">智能设备</a></li>
			<li><a href="informationControl.do?newsIndex">健康资讯</a></li>
			<li><a href="informationControl.do?helpCenterIndex">帮助中心</a></li>
			<li style="display:none;">
				<a href="/login/register">
					<span class="signUp">注册</span>
				</a>
			</li>
			<li style="display:none;">
				<a href="/login">
					<span class="signIn">登录</span>
				</a>
			</li>
			<li style="width:200px;position:relative;display:none;" class="showExit">
				<img src="" height="50" width="50" style="margin-right: 10px;">
				<a href="/login">
					<span>用户名</span>
				</a>
				<ol>
					<li style="display:none;" data-userType="1"><a href="serviceControl.do?myOrders">我的订单</a></li>
					<li style="display:none;" data-userType="3"><a href="javascript:quitLogin();">退出</a></li>
				</ol>
			</li>
		</ul>
	</div>
</header>
<script>
jQuery(function() {
	checkLoginStatus();
})
</script>
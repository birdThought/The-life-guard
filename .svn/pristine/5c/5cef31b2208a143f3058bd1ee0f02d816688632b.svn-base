<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<header id="header">
    <div class="wrapper clearfix">
        <div class="logo fl">
            <a href="/">
                <img src="/static/images/index/logo.png" alt="">
            </a>
        </div>
        <ul class="fr clearfix  tz-navbar-nav">
            <li><a href="/indexControl.do?index" class="on">首页</a></li>
            <li>
                <a href="/indexControl.do?appIntroduce">APP介绍</a>
                <ul>
                    <li><a href="/indexControl.do?appIntroduce#APP_1" id="nav-org">机构端</a></li>
                    <li><a href="/indexControl.do?appIntroduce#APP_2" id="nav-user">用户端</a></li>
                </ul>
            </li>
            <li>
                <a href="/indexControl.do?healthService">产品与服务</a>
                <ul>
                    <li><a href="/indexControl.do?healthService">机构服务</a></li>
                    <li><a href="/indexControl.do?inteDevice">智能设备</a></li>
                </ul>
            </li>
            <li><a href="/indexControl.do?merchantsSettled">商家入驻</a></li>
            <li>
                <a href="/indexControl.do?aboutUs" class="scroll-link">关于我们</a>
                <ul id="nav-about">
                    <li><a href="/indexControl.do?aboutUs#a">公司介绍</a></li>
                    <li><a href="/indexControl.do?aboutUs#b">帮助中心</a></li>
                    <li><a href="/indexControl.do?aboutUs#c">联系我们</a></li>
                </ul>
            </li>
            <li class="register none" id = "register">
                <a href="/register" class="scroll-link">注册</a>
            </li>
            <li class="login none" id = "login">
                <a href="/login">登录</a>
            </li>
            <li class="showExit none"><a href="/my/home">用户名</a>
                <ul>
                    <li data-userType="1" class="none">
                        <a href="/memberControl.do?myOrders">我的订单</a>
                    </li>
                    <li data-userType="3" class="none">
                        <a href="javascript:quitLogin();">退出</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</header>
<!-- /header -->
<script>
jQuery(function() {
    checkLoginStatus();
    /* 导航控制相应内容 */
    $("#nav-org").click(function(){
    	$(".tab-item .tab-nav li:eq(0)").addClass("current").siblings().removeClass("current");
    	$(".user").css("display","none");
    	$(".org").css("display","block");
    });
    $("#nav-user").click(function(){
    	$(".tab-item .tab-nav li:eq(1)").addClass("current").siblings().removeClass("current");
    	$(".org").css("display","none");
    	$(".user").css("display","block");
    });
    $("#nav-about li").click(function(){
    	$('.item-nav li').eq($(this).index()).trigger("click");
    	/* $('.item-nav li').eq($(this).index()).addClass("current").siblings("li").removeClass("current");
    	$('.item-nav li').eq($(this).index()).children("a").addClass('class_name').parent('li').siblings('li').children('a').removeClass('class_name');
    	$('.item-nav-content > li').hide().eq($(this).index()).show(); */
    });
    $("header ul > li:eq(3) a").addClass("on").parent("li").siblings("li").children("a").removeClass("on");
});


</script>
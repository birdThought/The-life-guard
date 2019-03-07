<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,vue,layui"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <%--<link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/user-infor.css?v=2.2.0">--%>
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-infor.css?v=2.2.0">
    <title>绑定手机</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>

<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white;">
            <div class="items-link">
                <a href="#">账户安全</a>
                <em>&gt;</em>
                <a href="#">更换号码</a>
            </div>
            <form class="change-infor layui-form">
                <div class="change-tip" v-show = "oldMobile != ''">
                    <p>
                        <label>已绑定手机号：</label>
                        <span>{{oldMobile}}</span>
                    </p>
                </div>
                <ul>
                    <li>
                        <h5>国家地区</h5>
                        <select name="area" lay-ignore>
                            <option value="1">中国大陆</option>
                            <option value="2">香港</option>
                            <option value="3">澳门</option>
                        </select>
                    </li>
                    <li>
                        <h5>登录密码</h5>
                        <input name="password" type="password" placeholder="请输入您的登录密码" class="ipt" lay-verify="required">
                    </li>
                    <li>
                        <h5>新绑定手机</h5>
                        <input name="mobile" type="text" placeholder="请输入您的手机" class="ipt" v-model = "mobile" lay-verify="required|phone">
                    </li>
                    <li>
                        <h5>验证码</h5>
                        <input name="code" type="text" class="fl" lay-verify="required">
                        <input type="button" value="获取验证码" class="fl" @click = "sendCode()">
                    </li>
                    <li class="refer-btn">
                        <a href="#" class="bind-btn" lay-submit lay-filter="*">提交修改</a>
                    </li>
                </ul>
            </form>
        </div>
    </section>
</article>
<!-- /article -->
</body>
<script type="text/javascript" src="/static/platform/v2.2.0/js/org/profile/bind-mobile.js?v=2.2.0"></script>
<script type="text/javascript">
    if ('${orgType}' == 1) {
        $('.main-nav li').eq(5).click();
    } else {
        $('.main-nav li').eq(10).click();
    }
    $(".main-content").css("minHeight",1000);
    var height=$(".main-content").height();
    $(".main-nav").css({
        borderRight:'1px solid #ddd',
        height:height
    });
    $(".container").css({
        borderLeft:'1px solid #ddd',
        borderRight:'1px solid #ddd',
        borderBottom:'1px solid #ddd',
        height:height
    });

    $(function() {
        BindMobile.vm.oldMobile = '${mobile}';
        BindMobile.init();
    });
</script>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/account-security.css?v=2.2.0">
    <title>账户安全</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white;">
            <form v-cloak>
                <ul class="account-security account-security-ul">
                    <li>
                        <label>当前登陆密码安全程度</label>
                        <div class="psd-progress">
                            <div></div>
                        </div>
                        <div class="strength">
                            <span>弱</span>
                            <a @click = "goModifyPassword" class="cursor-pointer">修改密码</a>
                        </div>
                    </li>
                    <li v-if = "(orgType == 1 && userType == 1) || orgType == 2">
                        <label>联系手机号码</label>
                        <input type="text" :value = "mobile" class="cursor-not-allowed">
                        <div>
                            <a class="cursor-pointer" v-if = "mobile != null" @click = "goModifyMobile">去更换</a>
                            <a class="cursor-pointer" v-else @click = "goBindMobile">去绑定</a>
                        </div>
                    </li>
                    <%--<li>
                        <label>邮箱</label>
                        <input type="text" :value = "email" class="cursor-not-allowed">
                        <div>
                            <a class="cursor-pointer" v-if = "email != null" @click = "goModifyEmail">去更换</a>
                            <a class="cursor-pointer" v-else @click = "goBindEmail">去绑定</a>
                        </div>
                    </li>--%>
                </ul>
            </form>
        </div>
    </section>
</article>
<!-- /article -->
</body>
<script type="text/javascript" src="/static/platform/v2.2.0/js/org/accountsecurity/account-security.js?v=2.2.0"></script>
<script type="text/javascript">
    layui.use('layer');
    if ('${orgType}' == 1) {
        if ('${userType}' == 0) {
            $('.main-nav li').eq(10).click();
        } else {
            $('.main-nav li').eq(5).click();
        }
    } else {
        $('.main-nav li').eq(10).click();
    }
    security.vm.user = '${user}' == '' ? null : ${user};
    common.addBorder();
    security.vm.orgType = '${orgType}';
    security.vm.userType = '${userType}';

</script>
</html>
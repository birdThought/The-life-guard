
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,jquery.serializejson,layer,vue,layui"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <%--<link rel="stylesheet" type="text/css" href="/static/plugins/layui/css/layui.css">--%>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/account-security.css?v=2.2.0">
    <title>修改密码</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white;">
            <div class="security-href">
                <a href="#">账户安全</a><em>></em><span>修改密码</span>
            </div>
            <form class="form-modify-password">
                <ul class="account-security">
                    <li class="remind">
                        <p>互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</p>
                    </li>
                    <li>
                        <label>原密码</label>
                        <input type="password" class="oldPassword" name="oldPassword" placeholder="请输入原密码">
                    </li>
                    <li>
                        <label>新密码</label>
                        <input type="password" class="newPassword" name="newPassword" oncopy="return false" onpaste="return false" oncut="return false" placeholder="密码由6-20位任意字符组成">

                    </li>
                    <li>
                        <label>确认新密码</label>
                        <input type="password" class="confirmPassword" name = "confirmPassword" oncopy="return false" onpaste="return false" oncut="return false" placeholder="请再次输入密码">
                    </li>
                    <li class="save">
                        <a class="cursor-pointer" @click = "modifyPassword">确定保存</a>
                    </li>
                </ul>
            </form>
        </div>
    </section>
</article>
<!-- /article -->
</body>
<script type="text/javascript" src="/static/platform/v2.2.0/js/org/accountsecurity/account-security.js?v=2.2.0"></script>
<script type="text/javascript">
    if ('${orgType}' == 1) {
        if ('${userType}' == 0) {
            $('.main-nav li').eq(10).click();
        } else {
            $('.main-nav li').eq(5).click();
        }
    } else {
        $('.main-nav li').eq(10).click();
    }
    common.addBorder();


</script>
</html>
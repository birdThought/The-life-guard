
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,jquery.serializejson,layui,vue"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <%--<link rel="stylesheet" href="/static/plugins/layui/css/layui.css">--%>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <title>个体门店注册</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div class="step-1"></div>
        <div class="step-1"></div>
        <div class="step-1"></div>
        <div class="step-2"></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-3"></div>
                <p class="pass">1.实名信息认证</p>
            </li>
            <li>
                <div class="step-3"></div>
                <p class="pass">2.个人资格认证</p>
            </li>
            <li>
                <div class="step-3"></div>
                <p class="pass">3.店铺简介</p>
            </li>
            <li>
                <div  class="step-1"></div>
                <p class="on">4.账号申请</p>
            </li><li>
            <div  class="step-2"></div>
            <p>5.等待审核</p>
        </li>
        </ul>
    </div>
    <form class="wrap vue-content form-four-step">
        <ul>
            <li>
                <h5>登录名</h5>
                <input type="text" class="username" name = "userName" placeholder="6-18位字符，只能包含英文字母、数字、下划线">
                <div class="msg-lay">
                    <span class="msg-lay-1"></span>
                </div>
            </li>
            <li>
                <h5>登录密码</h5>
                <input type="password" name="password" placeholder="请输入密码"
                       onkeyup="pwStrength(this.value)" onblur="pwStrength(this.value)">

                <div class="msg-lay psw_strenth">
                    <span>安全等级低</span>
                    <div id="strength_L"></div>
                    <div id="strength_M"></div>
                    <div id="strength_H"></div>
                </div>
            </li>
            <li>
                <h5>确认密码</h5>
                <input type="password" name = "confirmPassword" class="cf-pwd" placeholder="请确认密码">
                <div class="msg-lay"></div>
            </li>
            <li>
                <h5>手机</h5>
                <input type="text" name = "mobile" placeholder="请输入手机号码 " class="phone">
                <div class="msg-lay"></div>
            </li>
            <li>
                <h5>手机验证码</h5>
                <input type="text" name = "verifyCode">
                <input  type="button" class="get-code" value="获取验证码" @click = "getCode">
            </li>
            <li>
                <h5>申请类型</h5>
                <select name="userType">
                    <option value="请选择你的专业背景" selected disabled>请选择你的专业背景</option>
                    <option value="2">服务师&管理员</option>
                </select>
            </li>
            <li>
                <h5>推荐人编号</h5>
                <input type="text" name = "parentId" placeholder="请输入推荐人编号 " class="parentId">
                <div class="msg-lay"></div>
            </li>
            <li class="step-2-btn">
                <a href="javascript:void(0)" class="btn-1">返回修改</a>
                <a href="javascript:void(0)" class="btn-1 btn-2" @click = "submitFour">确认提交</a>
            </li>
        </ul>
    </form>
</section>
<%@include file="../../context/footer.jsp"%>
</body>
</html>
<script src="/static/platform/v2.2.0/js/register/individualstore/individual-store-register.js?v=2.2.0"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script src="/static/common/js/common.js"></script>
<script>
    store.init();
</script>


<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/26
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <title>机构注册</title>
</head>
<body>
<%@include file="/view/platform/context/header.jsp"%>
<section class="register_content banxin">
    <div class="step-two-skip" onclick="org.skipTwoStep()">跳过</div>
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div class="step-1"></div>
        <div class="step-2"></div>
        <div ></div>
        <div></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-3"></div>
                <p class="pass">1.公司信息认证</p>
            </li>
            <li>
                <div class="step-1"></div>
                <p class="on">2.银行信息认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>3.法人信息认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.准备入驻</p>
            </li><li>
                <div  class="step-2"></div>
                <p>5.等待审核</p>
            </li>
        </ul>
    </div>
    <form class="wrap register-form-2">
        <ul>
            <li>
                <h5>公司名称</h5>
                <input type="text" name="bankName" placeholder="请正确填写公司名称(如：广州通众电气实业有限公司)" value="${orgName}" disabled>
            </li>
            <li>
                <h5>公司对公账号</h5>
                <input type="text" name="bankAccount" placeholder="输入内容必须介于16到19之间">
            </li>
            <li>
                <h5>开户所在地区</h5>
                <input type="text" name="bankDistrict" placeholder="请正确填写开户所在地区">
            </li>
            <li>
                <h5>开户行支行名称</h5>
                <input type="text" name="bankBranch" placeholder="请正确填写开户支行名称(如：建设银行北京王府井支行)">
            </li>
            <%--<li>
                <h5>手机验证码</h5>
                <input type="text" placeholder="请输入验证码">
                <button class="get-code">获取验证码</button>
            </li>--%>
            <li class="step-2-btn">
                <a href="javascript:void(0)" class="btn-1">返回修改</a>
                <a href="javascript:void(0)" class="btn-1 btn-2" onclick="org.registerStepTwo()">确认提交</a>
            </li>
            <input type="hidden" name="id" class="orgId step-two-orgId" value="${id}">
            <input type="hidden" name="token" class="token step-two-token" value="${token}">
        </ul>
    </form>
</section>
<%@include file="/view/platform/context/footer.jsp"%>
<script src="/static/platform/v2.2.0/js/register/org/orgregister.js"></script>
</body>
</html>
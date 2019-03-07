
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
        <div class="step-2"></div>
        <div></div>
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
                <div  class="step-1"></div>
                <p class="on">3.店铺简介</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.账号申请</p>
            </li><li>
            <div  class="step-2"></div>
            <p>5.等待审核</p>
        </li>
        </ul>
    </div>
    <form class="wrap vue-content form-three-step">
        <ul>
            <li>
                <h5>店铺名称</h5>
                <input type="text" name = "orgName" placeholder="请输入店铺名称">
            </li>
            <li>
                <h5>店铺分类</h5>
                <select name="orgType" >
                    <option value="选择您的店铺分类" selected disabled>选择您的店铺分类</option>
                    <option value="体检机构">体检机构</option>
                    <option value="健康养生会所">健康养生会所</option>
                    <option value="瑜伽馆">瑜伽馆</option>
                    <option value="健身俱乐部">健身俱乐部</option>
                    <option value="国医馆">国医馆</option>
                    <option value="中医理疗馆">中医理疗馆</option>
                    <option value="中医馆">中医馆</option>
                    <option value="药店">药店</option>
                    <option value="足疗店">足疗店</option>
                    <option value="理疗护理中心">理疗护理中心</option>
                    <option value="家政服务机构">家政服务机构</option>
                    <option value="养老护老行业">养老护老行业</option>
                    <option value="教育/学校/培训机构">教育/学校/培训机构</option>
                    <option value="三甲医院">三甲医院</option>
                    <option value="社区医院">社区医院</option>
                    <option value="心理辅导机构">心理辅导机构</option>
                    <option value="私人诊所">私人诊所</option>
                </select>
            </li>
            <li>
                <h5>从事领域</h5>
                <select name="workField" >
                    <option value="选择您的店铺从事领域" selected disabled>选择您的店铺从事领域</option>
                    <option value="体检">体检</option>
                    <option value="健康管理">健康管理</option>
                    <option value="诊疗">诊疗</option>
                    <option value="养生理疗">养生理疗</option>
                    <option value="居家健康">居家健康</option>
                    <option value="锻炼健身">锻炼健身</option>
                    <option value="教育文化">教育文化</option>
                    <option value="生殖医学">生殖医学</option>
                </select>
            </li>
            <li>
                <h5>店铺简介</h5>
                <textarea name="about" v-model = "orgAbout">请输入店铺的介绍</textarea>
                <p class="count">最多200字，已输入<em style="color: orange">{{countOfOrgAbout}}</em>个字</p>
            </li>
            <li class="step-2-btn">
                <a href="javascript:void(0)" class="btn-1">返回修改</a>
                <a href="javascript:void(0)" class="btn-1 btn-2" @click = "submitThree">保存下一步</a>
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


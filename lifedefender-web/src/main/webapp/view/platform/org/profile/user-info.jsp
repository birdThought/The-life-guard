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
    <link rel="stylesheet" type="text/css" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css?v=2.2.0">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/user-infor.css?v=2.2.0">
    <title>个人资料</title>
    <style type="text/css">
        .layui-layer-btn{}
    </style>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-infor.css?v=2.2.0">
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white;">
            <form class="layui-form">
                <ul class="user-infor">
                    <li class="set-head">
                        <div>
                            <div class="photo"><img src="${user.photo}" alt=""></div>
                        </div>
                    </li>
                    <li>
                        <h5>真实姓名</h5>
                        <input type="text" class="layui-input cursor-not-allowed" value="${user.realName}" disabled>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>性别</h5>
                            <div class="layui-input-block">
                                <input type="radio" value="女士" title="女士"  v-bind:checked ="male" disabled>
                                <input type="radio" value="先生" title="先生"  v-bind:checked ="male" disabled>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>出生日期</h5>
                            <input type="text" class="layui-input cursor-not-allowed" placeholder="出生日期" v-model="birthday" disabled>
                            <div class="msg-lay"></div>
                        </div>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>身份证号码</h5>
                            <input type="text" class="layui-input cursor-not-allowed" placeholder="身份证号码" value="${user.idCard}" disabled>
                        </div>
                    </li>
                    <%--<li>
                        <h5>城市</h5>
                        <select disabled lay-ignore>
                            <option value="">选择城市</option>
                            <option v-for = "c in city" v-bind:value = "c.code" v-bind:selected = "c.code == cCode">{{c.name}}</option>
                        </select>
                    </li>
                    <li>
                        <h5>区县</h5>
                        <select disabled lay-ignore>
                            <option value="">选择区县</option>
                            <option v-for = "d in district" v-bind:value = "d.code" v-bind:selected = "d.code == dCode">{{d.name}}</option>
                        </select>
                    </li>--%>
                    <li>
                        <div class="layui-form-item">
                            <h5>工作单位</h5>
                            <input type="text" class="layui-input cursor-not-allowed" placeholder="示例：广州通众" value="${user.orgName}" disabled>
                        </div>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>通讯地址</h5>
                            <input type="text" name="address" class="layui-input" placeholder="示例：天河区" v-model = "address">
                            <span class="fl change">可修改</span>
                        </div>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>联系手机号码</h5>
                            <input type="text" class="layui-input cursor-not-allowed" placeholder="示例:15915708457" v-model = "mobile" disabled>
                            <span class="fl change cursor-pointer" v-if = "mobile == ''" @click = 'bindMobile()'>去填写</span>
                            <span class="fl change cursor-pointer" v-if = "mobile != ''" @click = 'bindMobile()'>去更换</span>
                        </div>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>邮箱</h5>
                            <input type="text" class="layui-input cursor-not-allowed" placeholder="示例：123@abc.com" v-model = "email" disabled>
                            <span class="fl change cursor-pointer" v-if = "email == ''">去填写</span>
                            <span class="fl change cursor-pointer" v-if = "email != ''">去更换</span>
                        </div>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>专业特长</h5>
                            <textarea name="expertise" class="layui-textarea" placeholder="请输入您的专业特长" v-model = "expertise" lay-verify="expertise"></textarea>
                            <p class="count">最多{{expertiseMaxLength}}字，已输入<span style="color: orange">{{expertiseLength}}</span>个字</p>
                        </div>
                    </li>
                    <li>
                        <div class="layui-form-item">
                            <h5>个人简介</h5>
                            <textarea name="about" class="layui-textarea" placeholder="请输入您的个人简介" v-model = "about" lay-verify="about"></textarea>
                            <p class="count">最多{{aboutMaxLength}}字，已输入<span style="color: orange">{{aboutLength}}</span>个字</p>
                        </div>
                    </li>
                    <li class="org-btn">
                        <!-- <button href="javascript:void(0)" class="btn-1 layui-btn layui-btn-primary" lay-submit lay-filter="*">保存/提交</button> -->
                        <a href="javascript:void(0)" class="btn-1 layui-btn" lay-submit lay-filter="*">保存/提交</a>
                        <a href="javascript:void(0)" class="btn-2">重新审核</a>
                    </li>
                </ul>
            </form>
        </div>
    </section>
</article>
<!-- /article -->
</body>
<script type="text/javascript" src="/static/common/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/platform/v2.2.0/js/org/profile/user-info.js?v=2.2.0"></script>
<script type="text/javascript">
    if ('${orgType}' == 1) {
        $('.main-nav li').eq(4).click();
    } else {
        $('.main-nav li').eq(8).click();
    }
    common.addBorder();

    $(function() {
        UserInfo.init();
        UserInfo.vm.mobile = '${user.mobile}';
        UserInfo.vm.email = '${user.email}';
        UserInfo.vm.expertise = '${user.expertise}';
        UserInfo.vm.about = '${user.about}';
        UserInfo.vm.address = '${user.address}';
        UserInfo.vm.gender = '${user.gender}';
        UserInfo.vm.birthday = '${user.birthday}' == '' ? '' : new Date('${user.birthday}').Format('yyyy-MM-dd');
        <%--UserInfo.vm.cCode = '${user.cCode}';--%>
        <%--UserInfo.vm.city = JSON.parse('${city}');--%>
        <%--UserInfo.vm.dCode = '${user.dCode}';--%>
        <%--UserInfo.vm.district = JSON.parse('${district}');--%>
    });

</script>
</html>
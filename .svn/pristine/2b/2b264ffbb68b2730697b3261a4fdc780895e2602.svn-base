
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jeDate/css/jedate.css?v=2.2.0">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-member.css?v=2.2.0">、
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <title>会员管理</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content">
            <div class="member-manage" style="background-color: #f5f5f5">
                <div class="manage-top clearfix">
                    <div class="fl">
                        <input type="text" placeholder="会员搜索" v-model = "search">
                        <span>+</span>
                    </div>
                    <%--<span class="fr data-export cursor-pointer">健康数据导出</span>--%>
                </div>
                <div class="member-content clearfix">

                    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief"
                    style="margin: -50px; width: 290px; margin-left: 0px; height: 700px;">
                        <ul class="layui-tab-title" style="width: 281px; margin-left: -30px;">
                            <li class="layui-this" style="width: 50%; background-color: #fff">会员用户</li>
                            <li style="width: 50%; background-color: #fff">历史用户</li>
                        </ul>
                        <div class="layui-tab-content" style="width: 300px; margin-left: -40px; height: 666px; overflow-y: auto;overflow-x: hidden;">
                            <div class="layui-tab-item layui-show">
                                <%--<div class="layui-collapse" lay-filter="test" style="border: none;">
                                    <div class="layui-colla-item">
                                        <h2 class="layui-colla-title">全部（0人）</h2>
                                        <div class="layui-colla-content member-list" style="background-color: #fff;">
                                            <p v-for = "(m, index) in tempMembers" @click = "clickMember($event,index)">
                                                <img v-if = "m.photo != '' && m.photo != null" :src = "m.photo">
                                                <img v-else src = "/static/images/index/nopic.jpg">
                                                <label>
                                                    <label style="color: #000;">{{getMemberName(m) | subStr(13)}}</label><br>
                                                    <label style="position: relative; left: 84px; top: -16px; color: #757575;">病种：无</label>
                                                </label>
                                            </p>

                                        </div>
                                    </div>
                                    <div class="layui-colla-item">
                                        <h2 class="layui-colla-title">健康课堂（0人）</h2>
                                        <div class="layui-colla-content">
                                            <p>用户名称</p>
                                        </div>
                                    </div>
                                    <div class="layui-colla-item">
                                        <h2 class="layui-colla-title">健康咨询（0人）</h2>
                                        <div class="layui-colla-content">
                                            <p>用户名称</p>
                                        </div>
                                    </div>
                                    <div class="layui-colla-item">
                                        <h2 class="layui-colla-title">居家养老（0人）</h2>
                                        <div class="layui-colla-content">
                                            <p>用户名称</p>
                                        </div>
                                    </div>
                                    <div class="layui-colla-item">
                                        <h2 class="layui-colla-title">健康养生（0人）</h2>
                                        <div class="layui-colla-content">
                                            <p>用户名称</p>
                                            <p>用户名称</p>
                                        </div>
                                    </div>
                                </div>--%>
                                    <div v-if = "v_2.members != null" v-cloak class="main-content-left-bottom m_c_l_bottom">

                                        <p class="user-list">
                                            <i></i>
                                            <span>全部
                                                <em>（2人）</em>
                                            </span>
                                            <u></u>
                                        </p>
                                        <ul class="main-list autocompleter-list none" >
                                            <li>
                                                <i></i>
                                                <font>美眉 </font>
                                                <span>病种 ：无</span>
                                            </li>
                                        </ul>
                                        <p class="user-list">
                                            <i></i>
                                            <span>健康课堂
                                                <em>（{{countGroupMember('健康课堂')}}人）</em>
                                            </span>
                                            <u></u>
                                        </p>
                                        <ul class="main-list autocompleter-list none" >
                                            <li v-for = "m in listMemberByGroup('健康课堂')" @click = "clickMember_v_2(event, m)">
                                                <img :src = "m.photo | imgIsNone(1)">
                                                <font>{{getMemberName(m)}} </font>
                                                <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                            </li>
                                        </ul>
                                        <p class="user-list">
                                            <i></i>
                                            <span>健康咨询
                                                <em>（{{countGroupMember('健康咨询')}}人）</em>
                                            </span>
                                            <u></u>
                                        </p>
                                        <ul class="main-list autocompleter-list none" >
                                            <li v-for = "m in listMemberByGroup('健康咨询')" @click = "clickMember_v_2(event, m)">
                                                <img :src = "m.photo | imgIsNone(1)">
                                                <font>{{getMemberName(m)}} </font>
                                                <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                            </li>
                                        </ul>
                                        <p class="user-list">
                                            <i></i>
                                            <span>居家养老
                                                <em>（{{countGroupMember('居家养老')}}人）</em>
                                            </span>
                                            <u></u>
                                        </p>
                                        <ul class="main-list autocompleter-list none" >
                                            <li v-for = "m in listMemberByGroup('居家养老')" @click = "clickMember_v_2(event, m)">
                                                <img :src = "m.photo | imgIsNone(1)">
                                                <font>{{getMemberName(m)}} </font>
                                                <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                            </li>
                                        </ul>
                                        <p class="spec user-list">
                                            <i></i>
                                            <span>健康养生
                                                <em>（{{countGroupMember('健康养生')}}人）</em>
                                            </span>
                                        </p>
                                        <ul class="main-list autocompleter-list none" >
                                            <li v-for = "m in listMemberByGroup('健康养生')" @click = "clickMember_v_2(event, m)">
                                                <img :src = "m.photo | imgIsNone(1)">
                                                <font>{{getMemberName(m)}} </font>
                                                <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                            </li>
                                        </ul>
                                        <ul id="list_user"></ul>
                                    </div>
                            </div>
                            <div class="layui-tab-item">
                                <ul class="member-content-lists fl" style="height: 566px; overflow: auto;">
                                    <li v-cloak v-for = "(m, index) in tempMembers" @click = "clickMember($event,index)" >
                                        <img v-if = "m.photo != '' && m.photo != null" :src = "m.photo">
                                        <img v-else src = "/static/images/index/nopic.jpg">
                                        <a href="#">
                                            <label>{{getMemberName(m) | subStr(10)}}</label>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <%--<ul class="member-content-lists fl" style="height: 566px; overflow: hidden; overflow-y: auto;">
                        <li v-cloak v-for = "(m, index) in tempMembers" @click = "clickMember($event,index)" >
                            <img v-if = "m.photo != '' && m.photo != null" :src = "m.photo">
                            <img v-else src = "/static/images/index/nopic.jpg">
                            <a href="#">
                                <label>{{getMemberName(m) | subStr(10)}}</label>
                            </a>
                        </li>
                    </ul>--%>
                    <div class="member-infor fl" style="float: right; margin-top: -550px; margin-right: 300px;">
                        <dl v-if = "currentMember != null">
                            <dt v-cloak>
                                <template v-if = "currentMember.realName != null && currentMember.realName != ''">
                                    {{currentMember.realName}}
                                </template>
                                <template v-else>
                                    {{currentMember.userName}}
                                </template>

                            </dt>
                            <dd v-cloak>
                                <label class="color_10bb71">备注</label>：
                                <input v-if = "currentMember.userRemark != null" type="text" class="border-none" style="background-color: whitesmoke" :value = "currentMember.userRemark" onchange = "Manage.vm.modifyUserRemark($(this).val())">
                                <input v-else type="text" class="border-none" style="background-color: whitesmoke" placeholder="点击添加备注" onchange = "Manage.vm.modifyUserRemark($(this).val())">
                            </dd>
                            <dd v-cloak>性别：{{currentMember.gender | gender}}</dd>
                            <dd v-cloak>三围：{{currentMember.waist | removeDecimalPoint(1)}}-{{currentMember.bust | removeDecimalPoint(1)}}-{{currentMember.hip | removeDecimalPoint(1)}}</dd>
                            <dd v-cloak>身高：{{currentMember.height | removeDecimalPoint(1)}}CM</dd>
                            <dd v-cloak>出生日期：{{currentMember.birthday | date('yyyy-MM-dd')}}</dd>
                            <dd v-cloak>联系方式：{{currentMember.mobile}}</dd>
                            <dd v-cloak>最近服务：{{currentMember.serveName}} <a :href="'/order/order/' + currentMember.userName">服务详情</a></dd>

                            <dd v-cloak v-if = "userType != null && (userType == 1 || userType == 2)"> <%--管理员没有权限--%>
                                <span class="cursor-pointer" @click = "popupChatDialog(currentMember.userId, currentMember.userCode)">
                                    发送信息
                                </span>
                            </dd>
                        </dl>
                    </div>
                    <ul  v-cloak class="member-nav fr" v-if = "currentMember != null" style="float: right; margin-top: -550px;">
                        <li @click = "popupMemberDialog(1)">
                            <i></i>
                            <p>健康数据</p>
                        </li>
                        <li @click = "popupMemberDialog(2)">
                            <i></i>
                            <p>体检报告</p>
                        </li>
                        <li @click = "popupMemberDialog(3)">
                            <i></i>
                            <p>病历记录</p>
                        </li>
                        <li @click = "popupMemberDialog(4)">
                            <i></i>
                            <p>饮食记录</p>
                        </li>
                    </ul>
                    <div v-cloak v-if = "currentMember == null"
                         style = "margin-top: 100px; text-align: center;">
                        <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                        <br/><br/>
                        <p style="font-size: 18px; color: gray;">暂无会员</p>
                    </div>
                </div>
            </div>
            <%--窗口start--%>
            <div class="member-dialog none">
                <div class="layui-tab">
                    <div class="layui-tab-title clearfix">
                        <ul class="fl">
                            <li class="on" @click = "clickTab($event, 1)">健康数据</li>
                            <li @click = "clickTab($event, 2)">体检报告</li>
                            <li @click = "clickTab($event, 3)">病历记录</li>
                            <li @click = "clickTab($event, 4)">饮食记录</li>
                        </ul>
                        <div class="date-time fr">
                            <input type="text" class="health-date" v-model = "date"
                                   style="background:url('/static/images/green_date_img.png') no-repeat right center; " readonly>
                        </div>
                    </div>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <div class="health-data">
                                <div class="test-date clearfix">
                                    <p>{{date}}</p>
                                </div>
                                <div v-if = "healthData != null && healthData.length != 1" class="data-head">
                                    <span>类型</span>
                                    <span>测量值</span>
                                    <span>检查时间</span>
                                </div>
                                <ul class="data-none" v-if = "healthData != null && healthData.length == 1">暂无数据</ul>
                                <ul class="date-details" v-if = "healthData != null && healthData.length != 1">
                                    <li v-if = "healthData.Glucometer != null">
                                        <dl>
                                            <dd>血糖仪</dd>
                                            <dd>
                                                <p>血糖值：{{healthData.Glucometer.dataType | measureType}} {{healthData.Glucometer.bloodSugar}} mmol/L</p>
                                            </dd>
                                            <dd>{{healthData.Glucometer.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Bloodpressure != null">
                                        <dl>
                                            <dd>血压计</dd>
                                            <dd>
                                                <p>收缩压：{{healthData.Bloodpressure.systolic}} mmHg</p>
                                                <p>舒张压：{{healthData.Bloodpressure.diastolic}} mmHg</p>
                                                <p>心率：{{healthData.Bloodpressure.heartRate}} 次/分</p>
                                            </dd>
                                            <dd>{{healthData.Bloodpressure.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Oxygen != null">
                                        <dl>
                                            <dd>血氧仪</dd>
                                            <dd>
                                                <p>血氧饱和度：{{healthData.Oxygen.saturation}} %</p>
                                                <p>心率：{{healthData.Oxygen.heartRate}} 次/分</p>
                                            </dd>
                                            <dd>{{healthData.Oxygen.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Lunginstrument != null">
                                        <dl>
                                            <dd>肺活仪</dd>
                                            <dd>
                                                <p>肺活仪：{{healthData.Lunginstrument.vitalCapacity}} ml</p>
                                            </dd>
                                            <dd>{{healthData.Lunginstrument.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Band != null || healthData.Heartrate != null">
                                        <dl >
                                            <template v-if = "healthData.Band != null">
                                                <dd>心率手环</dd>
                                                <dd >
                                                    <%--<template v-if = "healthData.Heartrate != null">
                                                        <p>心率：{{healthData.Heartrate[0].heartRate}} 次/分</p>
                                                    </template>--%>
                                                    <p>步数：{{healthData.Band.steps}} 步</p>
                                                    <p>卡路里：{{healthData.Band.kcal}} Kcal</p>
                                                    <p>深睡：{{healthData.Band.deepDuration | sleepTime}}</p>
                                                    <p>浅睡：{{healthData.Band.shallowDuration | sleepTime}}</p>
                                                    <template v-if = "healthData.Heartrate != null">
                                                        <p class="xinlv-dialog" v-if = "healthData.Heartrate != null">
                                                            心率：{{healthData.Heartrate[0].heartRate}}次/分
                                                            <i class="heart-item-btn cursor-pointer" @click = "showHreatRate"></i>
                                                        </p>
                                                    </template>
                                                </dd>
                                            </template>
                                            <template v-else>
                                                <dd>心率</dd>
                                                <dd style="margin-left: -100px; margin-right: -50px;">
                                                <template v-for = "(i, index) in healthData.Heartrate">

                                                        <span class="heart-item-time" style="margin-left: 20px">{{i.measureDate | date('hh:mm')}}</span>
                                                        <span class="heart-item-unit" style="color: #10bb71;">{{i.heartRate}} 次/分</span>
                                                        <template v-if = "(index + 1)%3 == 0">
                                                            <br>
                                                        </template>

                                                </template>
                                                </dd>
                                            </template>

                                            <dd v-if = "healthData.Band != null">{{healthData.Band.date | date('yyyy-MM-dd')}}</dd>
                                            <dd v-else>{{healthData.Heartrate[0].measureDate | date('yyyy-MM-dd')}}</dd>
                                        </dl>

                                        <div class="heart-item none"  v-if = "healthData.Heartrate != null"
                                             style="border: 1px solid #f5eeee; box-shadow: 1px 1px 3px #bdbcbc; padding: 5px;">
                                            <template v-for = "(i, index) in healthData.Heartrate">
                                                <span class="heart-item-time">{{i.measureDate | date('hh:mm')}}</span>
                                                <span class="heart-item-unit" style="color: #10bb71;">{{i.heartRate}} 次/分</span>
                                                <template v-if = "(index + 1)%5 == 0">
                                                    <br/><br/>
                                                </template>
                                            </template>
                                        </div>
                                    </li>
                                    <li v-if = "healthData.Bodyfatscale != null">
                                        <dl>
                                            <dd>体脂秤</dd>
                                            <dd>
                                                <p>体重：{{healthData.Bodyfatscale.weight}} kg</p>
                                                <%--<p>BMI：{{healthData.Bodyfatscale.deepDuration}} kg/m</p>--%>
                                            </dd>
                                            <dd>{{healthData.Bodyfatscale.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Bloodlipid != null">
                                        <dl>
                                            <dd>血脂仪</dd>
                                            <dd>
                                                <p>总胆固醇：{{healthData.Bloodlipid.TC}} mmol</p>
                                                <p>甘油三酯: {{healthData.Bloodlipid.TG}} mmHg</p>
                                                <p>高密度脂蛋白: {{healthData.Bloodlipid.HDL}} mmol</p>
                                                <p>低密度脂蛋白: {{healthData.Bloodlipid.LDL}} mmol</p>
                                            </dd>
                                            <dd>{{healthData.Bloodlipid.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Uran != null">
                                        <dl class="urine">
                                            <dd>尿液</dd>
                                            <dd style="text-align: left">
                                                <p>
                                                    <span>比重: {{healthData.Uran.SG}}  </span>
                                                    <span>PH值：{{healthData.Uran.pH}}</span>
                                                </p>
                                                <p>
                                                    <span <%--style="margin-left: -8px"--%>>白细胞:  {{healthData.Uran.LEU}}  </span>
                                                    <span>蛋白质:  {{healthData.Uran.PRO}}  </span>
                                                </p>
                                                <p>
                                                    <span <%--style="margin-left: -14px"--%>>尿胆原:  {{healthData.Uran.UBG}} </span>
                                                    <span>潜血:  {{healthData.Uran.BLD}} </span>
                                                </p>
                                                <p>
                                                    <span <%--style="margin-left: -10px"--%>>胆红素:  {{healthData.Uran.BIL}}  </span>
                                                    <span>葡萄糖:  {{healthData.Uran.GLU}} </span>
                                                </p>
                                                <p>
                                                    <span <%--style="margin-left: -11px"--%>>酮体:  {{healthData.Uran.KET}}  </span>
                                                    <span>亚硝酸盐:  {{healthData.Uran.NIT}} </span>
                                                </p>
                                                <p>
                                                    <span <%--style="margin-left: -174px"--%>>VC维生素C:  {{healthData.Uran.VC}} </span>
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Uran.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Ua != null">
                                        <dl>
                                            <dd>尿酸</dd>
                                            <dd>
                                                <p>尿酸：{{healthData.Ua.UA}} mmol</p>
                                            </dd>
                                            <dd>{{healthData.Ua.measureDate | date('yyyy-MM-dd hh:mm')}}</dd>
                                        </dl>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layui-tab-item">
                            <dl class="data-none" v-if = "physicalData != null && physicalData.length == 0">
                                暂无数据
                            </dl>
                            <dl class="health-case" v-for = "(p, index) in physicalData">
                                <dt>医院：{{p.physicalsOrg}}</dt>
                                <dd>
                                    <p>类别：{{p.title}}</p>
                                    <div>
                                        <img style="width: 250px; height: 220px;" :src = "p.photos[0]" alt=""
                                             @click = "popurImg(event, p.photos[0])"
                                             v-if = "p.photos[0] != undefined"
                                        >
                                        <img style="width: 250px; height: 220px;" :src = "p.photos[1]" alt=""
                                             @click = "popurImg(event, p.photos[1])"
                                             v-if = "p.photos[1] != undefined"
                                        >
                                        <img style="width: 250px; height: 220px;" :src = "p.photos[2]" alt=""
                                             @click = "popurImg(event, p.photos[2])"
                                             v-if = "p.photos[2] != undefined"
                                        >
                                    </div>
                                </dd>
                            </dl>

                        </div>
                        <div class="layui-tab-item">
                            <ul class="data-none" v-if = "medicalData != null && medicalData.length == 0">
                                暂无数据
                            </ul>
                            <ul>
                                <li v-for = "(m, index) in medicalData">
                                    <dl>
                                        <dt>医院：</dt>
                                        <dd class="case-type">{{m.title}}</dd>
                                        <dd>
                                            <span>{{m.name}}</span>
                                            <span>{{m.sex}}</span>
                                            <span>{{m.age}}岁</span>
                                        </dd>
                                        <dd>
                                            {{m.basicCondition}}
                                        </dd>
                                        <dd>
                                            <span class="timed">{{m.visitingDate}}就诊</span>
                                        </dd>
                                    </dl>
                                    <div class="state-ill" style="height: 200px;">
                                        <img :src = "m.photo" alt="" style="height: 100%;"
                                             @click = "popurImg(event, m.photo)"
                                             v-if = "m.photo != null && m.photo != ''"
                                        >
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="layui-tab-item">
                            <div class="dinner-situation">
                                <div class="test-date clearfix">
                                    <p>{{date}}</p>
                                </div>
                                <ul class="tab-1">
                                    <li class="on">早餐</li>
                                    <li>午餐</li>
                                    <li>晚餐</li>
                                </ul>
                                <div class="tab-content-1">
                                    <div>
                                        <dl v-for = "d in dietData" v-if = "d.dietType == '早餐' || d.dietType == '早餐加餐'">
                                            <dt>{{d.dietType}}</dt>
                                            <dd>
                                                <template v-for = "(detail, index) in d.dietDetails">{{detail}}
                                                    <%--<template v-if = "index < detail.length - 5">、{{detail.length}}</template>--%>
                                                </template>

                                            </dd>
                                            <dd class="clearfix">
                                                <span class="fl">消耗能量:{{d.energy}}</span>
                                                <span class="fr">09:00</span>
                                            </dd>
                                        </dl>

                                    </div>
                                    <div>
                                        <dl v-for = "d in dietData" v-if = "d.dietType == '午餐' || d.dietType == '午餐加餐'">
                                            <dt>{{d.dietType}}</dt>
                                            <dd>
                                                <template v-for = "(detail, index) in d.dietDetails">{{detail}}
                                                    <%--<template v-if = "index < detail.length - 5">、{{detail.length}}</template>--%>
                                                </template>

                                            </dd>
                                            <dd class="clearfix">
                                                <span class="fl">消耗能量:{{d.energy}}</span>
                                                <span class="fr">09:00</span>
                                            </dd>
                                        </dl>

                                    </div>
                                    <div>
                                        <dl v-for = "d in dietData" v-if = "d.dietType == '晚餐' || d.dietType == '晚餐加餐'">
                                            <dt>{{d.dietType}}</dt>
                                            <dd>
                                                <template v-for = "(detail, index) in d.dietDetails">{{detail}}
                                                    <%--<template v-if = "index < detail.length - 5">、{{detail.length}}</template>--%>
                                                </template>

                                            </dd>
                                            <dd class="clearfix">
                                                <span class="fl">消耗能量:{{d.energy}}</span>
                                                <span class="fr">09:00</span>
                                            </dd>
                                        </dl>

                                    </div>
                                </div>
                                <script>
                                    $(".tab-content-1 > div").not("div:first").css("display","none")
                                    $(".tab-1 li").on("click",function(){
                                        $(this).addClass("on").siblings().removeClass("on");
                                        $(".tab-content-1 > div").css("display","none").eq($(this).index()).css("display","block");
                                    })
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--窗口end--%>
        </div>
    </section>
</article>

<!-- /article -->
</body>
<%@include file="/view/platform/org/message/chat-dialog.jsp"%>
</html>

<%--<script type="text/javascript" src="/static/plugins/jeDate/jedate.min.js?v=2.2.0"></script>--%>
<script type="text/javascript" src = "/static/plugins/jeDate/jeDate.js?v=2.2.0"></script>
<script type="text/javascript" src="/static/common/js/dateFormat.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/membermanage/member-manage.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/message/chat-dialog.js?v=2.2.0"></script>
<script src="/static/common/js/dateFormat.js"></script>
<script>

    layui.use('element')

    Manage.vm.members = '${members}' == '' ? '' : ${members};
    Manage.vm.tempMembers = '${members}' == '' ? '' : ${members};
    Manage.vm.userType = '${userType}' == '' ? null : ${userType};
    Manage.vm.orgType = '${orgType}' == '' ? null : ${orgType};
    Manage.vm.orgUser = {
        userCode: $('.userCode').val(),
        userName: $('.name').text(),
        head: $('.photo').attr('src')
    }
    Manage.init();

    if (Manage.vm.orgType == 1) {
        $('.main-nav li').eq(3).click();
    } else {
        $('.main-nav li').eq(1).click();
    }
    common.addBorder();
</script>




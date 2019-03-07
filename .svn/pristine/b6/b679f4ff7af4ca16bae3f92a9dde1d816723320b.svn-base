
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
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-member.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/layui/v2.1.2/css/layui.css">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
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
                    <div class="fl member-search" style="position: relative">
                        <i onclick="$('.search').focus();" style="cursor: text;"></i>
                        <input class="search" type="text" v-model = "search" @click = "showSearch">
                        <span>+</span>
                    </div>
                    <%--<span class="fr data-export cursor-pointer">健康数据导出</span>--%>
                </div>
                <div class="member-content clearfix" v-cloak>
                    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief"
                         style="margin: -50px; width: 290px; margin-left: 0px; height: 700px;">
                        <ul class="layui-tab-title layui-tab-title-group" style="width: 281px; margin-left: -30px;">
                            <li class="layui-this" style="width: 50%; background-color: #fff">会员用户</li>
                            <li style="width: 50%; background-color: #fff">历史用户</li>
                        </ul>
                        <div class="layui-tab-content" style="width: 300px; margin-left: -40px; height: 666px; overflow-y: auto;overflow-x: hidden;">
                            <div class="layui-tab-item layui-tab-item-group-1  layui-show">

                                <div v-if = "v_2.members != null" v-cloak class="main-content-left-bottom m_c_l_bottom" style="width: 280px;">

                                    <p class="user-list">
                                        <i></i>
                                        <span>全部
                                                <em>（{{countGroupMember('全部')}}人）</em>
                                            </span>
                                        <u></u>
                                    </p>
                                    <ul class="main-list autocompleter-list none" >
                                        <li v-for = "m in listMemberByGroup('全部')" @click = "clickMember_v_2(event, m)">
                                            <img :src = "m.photo | imgIsNone(1)">
                                            <font>{{getMemberName(m)}} </font>
                                            <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                        </li>
                                    </ul>
                                    <p class="user-list">
                                        <i></i>
                                        <span>在线课堂
                                                <em>（{{countGroupMember('课堂服务')}}人）</em>
                                            </span>
                                        <u></u>
                                    </p>
                                    <ul class="main-list autocompleter-list none" >
                                        <li v-for = "m in listMemberByGroup('课堂服务')" @click = "clickMember_v_2(event, m)">
                                            <img :src = "m.photo | imgIsNone(1)">
                                            <font>{{getMemberName(m)}} </font>
                                            <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                        </li>
                                    </ul>
                                    <p class="user-list">
                                        <i></i>
                                        <span>在线咨询
                                                <em>（{{countGroupMember('咨询服务')}}人）</em>
                                            </span>
                                        <u></u>
                                    </p>
                                    <ul class="main-list autocompleter-list none" >
                                        <li v-for = "m in listMemberByGroup('咨询服务')" @click = "clickMember_v_2(event, m)">
                                            <img :src = "m.photo | imgIsNone(1)">
                                            <font>{{getMemberName(m)}} </font>
                                            <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                        </li>
                                    </ul>
                                    <p class="user-list">
                                        <i></i>
                                        <span>上门服务
                                                <em>（{{countGroupMember('上门服务')}}人）</em>
                                            </span>
                                        <u></u>
                                    </p>
                                    <ul class="main-list autocompleter-list none" >
                                        <li v-for = "m in listMemberByGroup('上门服务')" @click = "clickMember_v_2(event, m)">
                                            <img :src = "m.photo | imgIsNone(1)">
                                            <font>{{getMemberName(m)}} </font>
                                            <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                        </li>
                                    </ul>
                                    <p class="spec user-list">
                                        <i></i>
                                        <span>到店服务
                                                <em>（{{countGroupMember('到店服务')}}人）</em>
                                            </span>
                                    </p>
                                    <ul class="main-list autocompleter-list none" >
                                        <li v-for = "m in listMemberByGroup('到店服务')" @click = "clickMember_v_2(event, m)">
                                            <img :src = "m.photo | imgIsNone(1)">
                                            <font>{{getMemberName(m)}} </font>
                                            <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                        </li>
                                    </ul>
                                    <ul id="list_user"></ul>
                                </div>
                            </div>
                            <div class="layui-tab-item layui-tab-item-group-2  main-content-left-bottom">
                                <ul class="main-list autocompleter-list" v-if = 'v_2.members != null'>
                                    <li v-for = "m in listMemberByGroup('history')" @click = "clickMember_v_2(event, m, true)">
                                        <img :src = "m.photo | imgIsNone(1)">
                                        <font>{{getMemberName(m)}} </font>
                                        <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="search-content main-content-left-bottom none" v-if = 'v_2.members != null'>
                                <ul class="main-list autocompleter-list" >
                                    <li v-for = "m in listMemberByGroup('全部')" v-if = "m.display" @click = "clickMember_v_2(event, m)">
                                        <img :src = "m.photo | imgIsNone(1)">
                                        <font>{{getMemberName(m)}} </font>
                                        <span>病种 ：{{m.userDiseasesName | isNone(1)}}</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="member-infor fl" style="float: right; margin-top: -550px; margin-right: 300px;">
                        <dl v-if = "currentMember != null">
                            <dt>
                                <template v-if = "currentMember.realName != null && currentMember.realName != ''">
                                    {{currentMember.realName}}
                                </template>
                                <template v-else>
                                    {{currentMember.userName}}
                                </template>
                            </dt>
                            <dd>
                                <label>备注</label>：
                                <input v-if = "currentMember.userRemark != null" type="text" class="color_10bb71 border-none" style="background-color: whitesmoke;"
                                       :value = "currentMember.userRemark" onchange="Manage.vm.modifyUserRemark($(this).val())">
                                <input v-else type="text" class="color_10bb71 border-none" placeholder="点击添加备注"
                                       onchange="Manage.vm.modifyUserRemark($(this).val())" style="background-color: whitesmoke;">
                            </dd>
                            <dd>
                                病种：
                                <select class="user-diseases"  v-model = "userDiseases">
                                    <option value = "-1">无</option>
                                    <option v-for = "d in diseases" :value = "d.id">{{d.name}}</option>
                                </select>
                            </dd>
                            <dd>性别：{{currentMember.gender | gender}}</dd>
                            <dd >三围：
                                <template v-if = "currentMember.waist == null">
                                    无
                                </template>
                                <template v-else>
                                    {{currentMember.waist | removeDecimalPoint(1)}}-{{currentMember.bust | removeDecimalPoint(1)}}-{{currentMember.hip | removeDecimalPoint(1)}}
                                </template>
                            </dd>
                            <dd >身高：{{currentMember.height | removeDecimalPoint(1)}}CM</dd>
                            <dd>出生日期：{{currentMember.birthday | date('yyyy-MM-dd')}}</dd>
                            <dd>联系方式：{{currentMember.mobile}}</dd>
                            <dd v-if="!currentMember.isHistory">最近服务：{{currentMember.serveName}}
                                <a :href="(userType == 0 ? '/order/order/' : '/order/ordertodo/') + currentMember.userName">服务详情</a>

                            </dd>
                            <dd v-cloak v-if = "userType != null && (userType == 1 || userType == 2)">
                                <span class="cursor-pointer" @click = "popupChatDialog(currentMember.userId, currentMember.userCode)">发送信息</span>
                            </dd>
                        </dl>
                    </div>
                    <ul class="member-nav fr" v-if = "currentMember != null" style="float: right; margin-top: -550px;">
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
                </div>
            </div>
            <%--窗口start--%>
            <div class="member-dialog none" style="/*overflow: auto;*/">
                <div class="layui-tab layui-tab-brief"  lay-filter="docDemoTabBrief">
                    <div class="layui-tab-title  clearfix " >
                        <ul class="fl">
                            <li class="layui-this" @click = "clickTab($event, 1)">健康数据</li>
                            <li @click = "clickTab($event, 2)">体检报告</li>
                            <li @click = "clickTab($event, 3)">病历记录</li>
                            <li @click = "clickTab($event, 4)">饮食记录</li>
                        </ul>
                        <div class="date-time fr">
                            <input type="text" class="health-date" :placeholder="date" v-model = "date"
                                   style="background:url('/static/images/green_date_img.png') no-repeat right center; font-size: small;" readonly>
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
                                <%--<ul class="data-none" v-if = "healthData != null && healthData.length == 1">暂无数据</ul>--%>
                                <ul class="date-details" v-if = "healthData != null">
                                    <li v-if = "healthData.Glucometer != null">
                                        <dl>
                                            <dd>血糖仪</dd>
                                            <dd>
                                                <p>血糖值：{{healthData.Glucometer.dataType | measureType}}
                                                    <span :class="healthData.Glucometer.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Glucometer.bloodSugar}}</span> mmol/L
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Glucometer.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('xty')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>血糖仪</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('xty')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Bloodpressure != null">
                                        <dl>
                                            <dd>血压计</dd>
                                            <dd>
                                                <p>收缩压：
                                                    <span :class="healthData.Bloodpressure.systolicStatus != false ? 'color_10bb71' : 'red'">{{healthData.Bloodpressure.systolic}}</span> mmHg
                                                </p>
                                                <p>舒张压：
                                                    <span :class="healthData.Bloodpressure.diastolic != false ? 'color_10bb71' : 'red'">{{healthData.Bloodpressure.diastolic}}</span> mmHg
                                                </p>
                                                <p>心率：
                                                    <span :class="healthData.Bloodpressure.heartRateStatus != false ? 'color_10bb71' : 'red'">{{healthData.Bloodpressure.heartRate}}</span> 次/分
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Bloodpressure.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('xyj')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>血压计</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('xyj')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Oxygen != null">
                                        <dl>
                                            <dd>血氧仪</dd>
                                            <dd>
                                                <p>血氧饱和度：
                                                    <span :class="healthData.Oxygen.saturationStatus != false ? 'color_10bb71' : 'red'">{{healthData.Oxygen.saturation}}</span> %
                                                </p>
                                                <p>心率：
                                                    <span :class="healthData.Oxygen.heartRateStatus != false ? 'color_10bb71' : 'red'">{{healthData.Oxygen.heartRate}} </span>
                                                        次/分</p>
                                            </dd>
                                            <dd>{{healthData.Oxygen.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('xyy')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>血氧仪</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('xyy')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Lunginstrument != null">
                                        <dl>
                                            <dd>肺活仪</dd>
                                            <dd>
                                                <p>肺活仪：<span :class="healthData.Lunginstrument.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Lunginstrument.vitalCapacity}}</span> ml
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Lunginstrument.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('fhy')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>肺活仪</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('fhy')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Band != null || healthData.Heartrate != null">
                                        <dl >
                                            <template v-if = "healthData.Band != null">
                                                <dd>心率手环</dd>
                                                <dd >
                                                    <p>步数：<span class="color_10bb71">{{healthData.Band.steps}}</span> 步</p>
                                                    <p>卡路里：<span class="color_10bb71">{{healthData.Band.kcal}}</span> Kcal</p>
                                                    <p>深睡：<span class="color_10bb71">{{healthData.Band.deepDuration | sleepTime}}</span></p>
                                                    <p>浅睡：<span class="color_10bb71">{{healthData.Band.shallowDuration | sleepTime}}</span></p>
                                                    <template v-if = "healthData.Heartrate != null">
                                                        <p class="xinlv-dialog" v-if = "healthData.Heartrate != null">
                                                            心率：<span :class="healthData.Heartrate[0].status == 0 ? 'color_10bb71' : 'red'">{{healthData.Heartrate[0].heartRate}}</span>次/分
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
                                                        <span class="heart-item-unit"><span :class="i.status == 0 ? 'color_10bb71' : 'red'">{{i.heartRate}}</span> 次/分</span>
                                                        <template v-if = "(index + 1)%3 == 0">
                                                            <br>
                                                        </template>

                                                    </template>
                                                </dd>
                                            </template>

                                            <dd v-if = "healthData.Band != null">{{healthData.Band.date | date('yyyy-MM-dd')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('band')">历史详情</span>
                                            </dd>
                                            <dd v-else>{{healthData.Heartrate[0].measureDate | date('yyyy-MM-dd')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('band')">历史详情</span>
                                            </dd>
                                        </dl>value

                                        <div class="heart-item none"  v-if = "healthData.Heartrate != null"
                                             style="border: 1px solid #f5eeee; box-shadow: 1px 1px 3px #bdbcbc; padding: 5px;">
                                            <template v-for = "(i, index) in healthData.Heartrate">
                                                <span class="heart-item-time">{{i.measureDate | date('hh:mm')}}</span>
                                                <span class="heart-item-unit">
                                                    <span :class="i.status == 0 ? 'color_10bb71' : 'red'">{{i.heartRate}}</span> 次/分
                                                </span>
                                                <template v-if = "(index + 1)%5 == 0">
                                                    <br/><br/>
                                                </template>
                                            </template>
                                        </div>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>心率手环</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('band')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Bodyfatscale != null">
                                        <dl>
                                            <dd>体脂秤</dd>
                                            <dd>
                                                <p>体重：
                                                    <span :class="healthData.Bodyfatscale.weightStatus != false ? 'color_10bb71' : 'red'">{{healthData.Bodyfatscale.weight}}</span> kg
                                                </p>
                                                <%--<p>BMI：{{healthData.Bodyfatscale.deepDuration}} kg/m</p>--%>
                                            </dd>
                                            <dd>{{healthData.Bodyfatscale.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('tzc')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>体脂秤</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('tzc')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Temperature != null">
                                        <dl>
                                            <dd>体温计</dd>
                                            <dd>
                                                <p>体温：
                                                    <span :class="healthData.Temperature.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Temperature.temperature}}</span>℃
                                                </p>
                                                <%--<p>BMI：{{healthData.Bodyfatscale.deepDuration}} kg/m</p>--%>
                                            </dd>
                                            <dd>{{healthData.Temperature.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('twj')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>体温计</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('twj')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Bloodlipid != null">
                                        <dl>
                                            <dd>血脂仪</dd>
                                            <dd>
                                                <p>总胆固醇：
                                                    <span :class="healthData.Bloodlipid.tC != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.TC}}</span> mmol
                                                </p>
                                                <p>
                                                    甘油三酯: <span :class="healthData.Bloodlipid.tG != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.TG}}</span> mmHg
                                                </p>
                                                <p>
                                                    高密度脂蛋白: <span :class="healthData.Bloodlipid.hDL != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.HDL}}</span> mmol
                                                </p>
                                                <p>
                                                    低密度脂蛋白: <span :class="healthData.Bloodlipid.lDL != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.LDL}}</span> mmol
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Bloodlipid.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('xzy')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>血脂仪</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('xzy')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Uran != null">
                                        <dl class="urine">
                                            <dd>尿液分析仪</dd>
                                            <dd style="text-align: left">
                                                <p>
                                                    <span>比重: <label :class="healthData.Uran.sG != false ? 'color_10bb71' : 'red'">{{healthData.Uran.SG}} </label> </span>
                                                    <span>PH值：<label :class="healthData.Uran.pH != false ? 'color_10bb71' : 'red'">{{healthData.Uran.pH}}</label></span>
                                                </p>
                                                <p>
                                                    <span>白细胞:  <label :class="healthData.Uran.lEU != false ? 'color_10bb71' : 'red'">{{healthData.Uran.LEU}}</label>  </span>
                                                    <span>蛋白质:  <label :class="healthData.Uran.pRO != false ? 'color_10bb71' : 'red'">{{healthData.Uran.PRO}}</label>  </span>
                                                </p>
                                                <p>
                                                    <span >尿胆原:  <label :class="healthData.Uran.uBG != false ? 'color_10bb71' : 'red'">{{healthData.Uran.UBG}}</label> </span>
                                                    <span>潜血:  <label :class="healthData.Uran.bLD != false ? 'color_10bb71' : 'red'">{{healthData.Uran.BLD}}</label> </span>
                                                </p>
                                                <p>
                                                    <span>胆红素:  <label :class="healthData.Uran.bIL != false ? 'color_10bb71' : 'red'">{{healthData.Uran.BIL}}</label>  </span>
                                                    <span>葡萄糖:  <label :class="healthData.Uran.gLU != false ? 'color_10bb71' : 'red'">{{healthData.Uran.GLU}}</label> </span>
                                                </p>
                                                <p>
                                                    <span>酮体:  <label :class="healthData.Uran.kET != false ? 'color_10bb71' : 'red'">{{healthData.Uran.KET}}</label>  </span>
                                                    <span>亚硝酸盐:  <label :class="healthData.Uran.nIT != false ? 'color_10bb71' : 'red'">{{healthData.Uran.NIT}}</label> </span>
                                                </p>
                                                <p>
                                                    <span >VC维生素C:  <label :class="healthData.Uran.vC != false ? 'color_10bb71' : 'red'">{{healthData.Uran.VC}} </label></span>
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Uran.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('ny')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>尿液分析仪</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('ny')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-if = "healthData.Ua != null">
                                        <dl>
                                            <dd>尿酸分析仪</dd>
                                            <dd>
                                                <p>尿酸：
                                                    <span :class="healthData.Ua.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Ua.UA}}</span> mmol
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Ua.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('ns')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>尿酸分析仪</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('ns')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>

                                    <li v-if = "healthData.Ecg != null">
                                        <dl>
                                            <dd>心电仪</dd>
                                            <dd>
                                                <p>心率：
                                                    <span :class="healthData.Ecg.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Ecg.heartRate}}</span> bpm
                                                </p>
                                            </dd>
                                            <dd>{{healthData.Ecg.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                <br><span class="history-details" @click = "getDeviceHistory('ecg')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                    <li v-else>
                                        <dl>
                                            <dd>心电仪</dd>
                                            <dd>
                                                <p>暂无数据</p>
                                            </dd>
                                            <dd>
                                                <span class="history-details" @click = "getDeviceHistory('ecg')">历史详情</span>
                                            </dd>
                                        </dl>
                                    </li>
                                </ul>
                            </div>
                            <div class = "device-history-data" v-if="history.showDevice != null">
                                <br>
                                <p>
                                    <i class="return-health-icon" @click = "showHealthData">返回</i>
                                </p>
                                <br>
                                <p>
                                    <span class = "return-health-data cursor-pointer" @click = "showHealthData">健康数据</span>&gt;
                                    <span class = "color_10bb71">{{deviceName}}</span>
                                </p>
                                <template v-if = "history.showDevice == 'xyj'"><%--血压计--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'diastolic')" >舒张压</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, 'systolic')" >收缩压</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, 'heartRate')" >心率</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">mmHg</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>收缩压 &nbsp;:&nbsp;<span :class="d.systolicStatus == 'abnormal' ? 'red' : ''">{{d.systolic}}</span> mmHg</p>
                                                    <p>舒张压 &nbsp;:&nbsp;<span :class="d.diastolicStatus == 'abnormal' ? 'red' : ''">{{d.diastolic}}</span> mmHg</p>
                                                    <p>心率 : <span :class="d.heartRateStatus == 'abnormal' ? 'red' : ''">{{d.heartRate}}</span>次/分</p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'xty'"><%--血糖仪--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, '1')" style="margin-left: 5px">早餐前</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, '2')" style="margin-left: 5px">早餐后</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, '3')" style="margin-left: 5px">午餐前</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, '4')" style="margin-left: 5px">午餐后</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, '5')" style="margin-left: 5px">晚餐前</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, '6')" style="margin-left: 5px">晚餐后</button>
                                        </div>
                                        <template v-if="history.data.length > 0 && history.xtyHasData">
                                            <div class="bloodPressure">mmol</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="d in history.pageData">
                                                <template v-for="p in d.paramData">
                                                    <template v-for="(t, index) in p">
                                                        <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                            <p>
                                                                <label style="color: #11bb71; font-weight: bold;">|</label>
                                                                {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                                <template v-if="t.status != 0">
                                                                    <span class="health-data-abnormal">异常</span>
                                                                </template>
                                                                <template v-else>
                                                                    <span class="health-data-normal">正常</span>
                                                                </template>
                                                            </p>
                                                            <p>血糖浓度 &nbsp;:&nbsp;<span :class="t.status == 0 ? '' : 'red'">{{t.bloodSugar}}</span> mmol/L</p>
                                                            <p>类型：<span style="color: grey; font-size: 14px;">{{Number(index) | measureType}}</span></p>
                                                            <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                        </div>
                                                    </template>

                                                </template>

                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'xyy'"><%--血氧仪--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'saturation')" >血氧饱和度</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, 'heartRate')" >心率</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure"></div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>血氧饱和度 &nbsp;:&nbsp;<span :class="d.saturationStatus == 'abnormal' ? 'red' : ''">{{d.saturation}}</span> %</p>
                                                    <p>心率 &nbsp;:&nbsp;<span :class="d.heartRatestatus == 'abnormal' ? 'red' : ''">{{d.heartRate}}</span> bpm</p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'fhy'"><%--肺活仪--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'vitalCapacity')" >肺活量</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">ml</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>肺活量 &nbsp;:&nbsp;<span :class="d.status == 0 ? '' : 'red'">{{d.vitalCapacity}}</span> ml</p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'band'"><%--心率手环--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'steps')" >计步</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, 'heartRate')" >心率</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, 'sleepDuration')" >睡眠</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">%</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{(history.paramType == 'heartRate' ? d.measureDate : date) | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="history.paramType == 'heartRate'">
                                                            <template v-if="d.status != 0">
                                                                <span class="health-data-abnormal">异常</span>
                                                            </template>
                                                            <template v-else>
                                                                <span class="health-data-normal">正常</span>
                                                            </template>
                                                        </template>
                                                    </p>
                                                    <template v-if="history.paramType == 'steps'">
                                                        <p>步数 &nbsp;:&nbsp;<span>{{d.steps}}</span> 步</p>
                                                        <p>卡路里 &nbsp;:&nbsp;<span>{{d.kcal }}</span> Kcal</p>
                                                        <p>里程 &nbsp;:&nbsp;<span>{{d.mileage }}</span> 公里</p>
                                                    </template>
                                                    <template v-if="history.paramType == 'heartRate'">
                                                        <p>心率 &nbsp;:&nbsp;<span :class="d.status == 0 ? 'color_10bb71' : 'red'">{{d.heartRate}}</span> bpm</p>
                                                    </template>
                                                    <template v-if="history.paramType == 'sleepDuration'">
                                                        <p>睡眠时长 &nbsp;:&nbsp;<span>{{d.sleepDuration | sleepTime}}</span> </p>
                                                        <p>深睡 &nbsp;:&nbsp;<span>{{d.deepDuration | sleepTime}}</span> </p>
                                                        <p>浅睡 &nbsp;:&nbsp;<span>{{d.shallowDuration | sleepTime}}</span> </p>
                                                        <p>苏醒 &nbsp;:&nbsp;<span>{{d.wakeupDuration | sleepTime}}</span> </p>

                                                    </template>

                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'tzc'"><%--体脂秤--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'weight')" >体重</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">kg</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>
                                                        <div class="color_666 line-height_24">体重 &nbsp;:&nbsp;<span :class="d.weightStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.weight}}</span>kg</div>
                                                        <div class="color_666 line-height_24 history-health-bodyfatscale">体脂率 &nbsp;:&nbsp;<span :class="d.axungeRatioStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.axungeRatio}}</span>%</div>
                                                    </p>
                                                    <p>
                                                        <div class="color_666">BMI &nbsp;:&nbsp;<span :class="d.BMIStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.BMI}}</span></div>
                                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >内脏脂肪 &nbsp;:&nbsp;<span :class="d.visceralFatStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.visceralFat}}</span></div>
                                                    </p>
                                                    <p>
                                                        <div class="color_666">腰臀比 &nbsp;:&nbsp;<span :class="d.WHRStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.WHR}}</span></div>
                                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >体年龄 &nbsp;:&nbsp;<span :class="d.bodyageStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.bodyage}}</span>岁</div>
                                                    </p>
                                                    <p>
                                                        <div class="color_666">人体水分 &nbsp;:&nbsp;<span :class="d.moistureStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.moisture}}</span>%</div>
                                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >人体肌肉 &nbsp;:&nbsp;<span :class="d.muscleStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.muscle}}</span>%</div>
                                                    </p>
                                                    <p>
                                                        <div class="color_666">骨骼重量 &nbsp;:&nbsp;<span :class="d.boneWeightStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.boneWeight}}</span>kg</div>
                                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >基础代谢 &nbsp;:&nbsp;<span :class="d.baseMetabolismStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.baseMetabolism}}</span>kg</div>
                                                    </p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'twj'"><%--体温计--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'temperature')" >体温计</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">℃</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">体温 &nbsp;:&nbsp;<span :class="d.status != 0 ? 'red' : 'color_10bb71'">{{d.temperature}}</span>℃</div>
                                                    </p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'xzy'"><%--血脂仪--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'tC')" >总胆固醇</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, 'tG')" >甘油三酯</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">mmol</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">总胆固醇 &nbsp;:&nbsp;<span :class="d.tCStatus == false ? 'red' : 'color_10bb71'">{{d.tC}}</span>mmol/L</div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">甘油三酯 &nbsp;:&nbsp;<span :class="d.tGStatus == false ? 'red' : 'color_10bb71'">{{d.tG}}</span>mmol/L</div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">高密度脂蛋白胆固醇 &nbsp;:&nbsp;<span :class="d.hDLStatus == false ? 'red' : 'color_10bb71'">{{d.hDL}}</span>mmol/L</div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">低密度脂蛋白胆固醇 &nbsp;:&nbsp;<span :class="d.lDLStatus == false ? 'red' : 'color_10bb71'">{{d.lDL}}</span>mmol/L</div>
                                                    </p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'ny'"><%--尿液分析仪--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'sG')" >比重</button>
                                            <button type="button" class="cursor-pointer" @click="changeParamType($event, 'pH')" >PH值</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure"></div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">比重 &nbsp;:&nbsp;<span :class="d.sGStatus == false ? 'red' : 'color_10bb71'">{{d.sG}}</span></div>
                                                    <div class="color_666 line-height_24 history-health-bodyfatscale" >PH值 &nbsp;:&nbsp;<span :class="d.pHStatus == false ? 'red' : 'color_10bb71'">{{d.pH}}</span></div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">葡萄糖 &nbsp;:&nbsp;<span :class="d.gLUStatus == false ? 'red' : 'color_10bb71'">{{d.gLU}}</span></div>
                                                    <div class="color_666 line-height_24 history-health-bodyfatscale" >蛋白质 &nbsp;:&nbsp;<span :class="d.pROStatus == false ? 'red' : 'color_10bb71'">{{d.pRO}}</span></div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">尿胆原 &nbsp;:&nbsp;<span :class="d.uBGStatus == false ? 'red' : 'color_10bb71'">{{d.uBG}}</span></div>
                                                    <div class="color_666 line-height_24 history-health-bodyfatscale" >潜血 &nbsp;:&nbsp;<span :class="d.bLDStatus == false ? 'red' : 'color_10bb71'">{{d.bLD}}</span></div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">胆红素 &nbsp;:&nbsp;<span :class="d.bILStatus == false ? 'red' : 'color_10bb71'">{{d.bIL}}</span></div>
                                                    <div class="color_666 line-height_24 history-health-bodyfatscale" >亚硝酸盐 &nbsp;:&nbsp;<span :class="d.nITStatus == false ? 'red' : 'color_10bb71'">{{d.nIT}}</span></div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">白细胞 &nbsp;:&nbsp;<span :class="d.lEUStatus == false ? 'red' : 'color_10bb71'">{{d.lEU}}</span></div>
                                                    <div class="color_666 line-height_24 history-health-bodyfatscale" >酮体 &nbsp;:&nbsp;<span :class="d.kETStatus == false ? 'red' : 'color_10bb71'">{{d.kET}}</span></div>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">VC维生素C &nbsp;:&nbsp;<span :class="d.vCStatus == false ? 'red' : 'color_10bb71'">{{d.vC}}</span></div>
                                                    </p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'ns'"><%--尿酸分析仪--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'uA')" >尿酸</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">mmol</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">尿酸 &nbsp;:&nbsp;<span :class="d.status != 0 ? 'red' : 'color_10bb71'">{{d.uA}}</span>mmol/L</div>
                                                    </p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>

                                <template v-if = "history.showDevice == 'ecg'"><%--心电--%>
                                    <div class="popup-bottom clearfix">
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="on cursor-pointer" @click="changeParamType($event, 'heartRate')" >心率</button>
                                        </div>
                                        <template v-if="history.data.length > 0">
                                            <div class="bloodPressure">bpm</div>
                                            <div style="width: 600px;height:254px; overflow-x: auto;">
                                                <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                                    <p><s></s></p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <div style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                                <p>暂未有数据</p>
                                            </div>
                                        </template>
                                        <div class="popup-bottom-top list">
                                            <button type="button" class="cursor-pointer on" @click="changeDateType($event, 'WEEK')" >一周</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'MONTH')" >一月</button>
                                            <button type="button" class="cursor-pointer" @click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                        </div>
                                        <div class="popup-bottom-bottom">
                                            <template v-for="(d, index) in history.pageData">
                                                <div class="popup-bottom-bottom-left" :class="index/2 == 0 ? 'border' : ''">
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date('yyyy-MM-dd hh:mm')}}
                                                        <template v-if="d.status != 0">
                                                            <span class="health-data-abnormal">异常</span>
                                                        </template>
                                                        <template v-else>
                                                            <span class="health-data-normal">正常</span>
                                                        </template>
                                                    </p>
                                                    <p>
                                                    <div class="color_666 line-height_24">心率 &nbsp;:&nbsp;<span :class="d.status != 0 ? 'red' : 'color_10bb71'">{{d.heartRate}}</span>bpm</div>
                                                    </p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </template>
                                        </div>
                                        <div class="last"></div>
                                    </div>
                                </template>
                            </div>
                        </div>
                        <div class="layui-tab-item">
                            <div v-cloak class="data-none" v-if = "physical.totalSize == 0" style="text-align: center; padding-top: 100px;">
                                <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                                <p style="color: #acacac; font-size: 16px;">暂无数据</p>
                            </div>
                            <dl class="health-case" v-for = "(p, index) in physical.data">
                                <dt>医院：{{p.physicalsOrg}}</dt>
                                <dd>
                                    <p>类别：{{p.title}}</p>
                                    <div class="physical-img" style="display: initial;">
                                        <template v-for="i in 6" v-if = "p.imgList[i-1] != null">
                                            <img style="width: 250px; height: 220px; margin-right: 10px; margin-bottom: 10px;" :src = "p.imgList[i-1].img" alt=""
                                                 @click = "popurImg(event, p.imgList[i-1].img)"
                                            >
                                        </template>

                                        <%--<img style="width: 250px; height: 220px;" :src = "p.imgList[1].img" alt=""
                                             @click = "popurImg(event, p.imgList[1].img)"
                                             v-if = "p.imgList[1] != undefined"
                                        >
                                        <img style="width: 250px; height: 220px;" :src = "p.imgList[2].img" alt=""
                                             @click = "popurImg(event, p.imgList[2].img)"
                                             v-if = "p.imgList[2] != undefined"
                                        >--%>
                                    </div>
                                </dd>
                            </dl>
                            <div id="page-container" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
                        </div>
                        <div class="layui-tab-item">
                            <div v-cloak class="data-none" v-if = "medical.data != null && medical.data.length == 0" style="text-align: center; padding-top: 100px;">
                                <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                                <p style="color: #acacac; font-size: 16px;">暂无数据</p>
                            </div>
                            <div>
                            <ul>
                                <li v-for = "(m, index) in medical.data">
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
                                    <div class="state-ill" style="height:200px;">
                                        <img :src = "m.photo" alt="" style="height: 100%;"
                                             @click = "popurImg(event, m.photo)"
                                             v-if = "m.photo != null && m.photo != ''"
                                        >
                                    </div>
                                </li>
                            </ul>
                            </div>
                            <div id="page-container-medical" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
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
                                                <span class="fr">{{d.dietTime}}</span>
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
                                                <span class="fr">{{d.dietTime}}</span>
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
                                                <span class="fr">{{d.dietTime}}</span>
                                            </dd>
                                        </dl>

                                    </div>
                                </div>

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
<script src="/static/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="/static/plugins/jeDate/jeDate.js?v=2.2.0"></script>
<script type="text/javascript" src="/static/common/js/dateFormat.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/membermanage/member-manage.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/message/chat-dialog.js?v=2.2.0"></script>
<script src = "/static/common/js/pageUtils.js?v=2.2.0"></script>
<script src="/static/plugins/image/jquey-bigic.js"></script>

<script>
    layui.use(['element', 'layer']);

    /*Manage.vm.members = '${members}' == '' ? '' : ${members};
    Manage.vm.tempMembers = '${members}' == '' ? '' : ${members};*/
    Manage.vm.userType = '${userType}' == '' ? null : ${userType};
    Manage.vm.orgType = '${orgType}' == '' ? null : ${orgType};
    Manage.vm.orgUser = ${user};
    Manage.init();

    if ('${userType}' == 2) {
        $('.main-nav li').eq(1).click();
    } else if('${userType}' == 1) {
        $('.main-nav li').eq(2).click();
    } else {
        $('.main-nav li').eq(3).click();
    }
    common.addBorder();
</script>




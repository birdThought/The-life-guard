<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<style>

</style>
<div class="right_body" ng-controller="memberController" ng-init="init()">
    <div class="right_title" style="padding-top: 10px">
        <label class="action" style="color: #3a87fc;font-size: 19px">
            会员列表
        </label>
    </div>
    <div style="margin-top:20px">
        <div style="margin-bottom:15px;position:relative;border-bottom: 1px dashed #ddd;padding-bottom: 20px;background:#fff;">
            <span class="outdate">
                <label>用户名</label>
                <input type="text" placeholder="请输入用户名" value="" ng-model="search.userName"/>
            </span>
            <span class="outdate">
                <label>姓名</label>
                <input type="text" placeholder="请输入姓名" value="" ng-model="search.realName"/>
            </span>
            <span class="outdate">
                <label>门店</label>
                <input type="text" placeholder="请输入门店名" value="" ng-model="search.orgName"/>
            </span>
            <span class="outdate">
                <label>手机号码</label>
                <input type="text" placeholder="请输入手机号码" value="" ng-model="search.mobile"/>
            </span>
            <button class="search-btn" ng-click="searchdata()">
                搜索
            </button>
        </div>
        <table class="service_table" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户名</td>
                <td>姓名</td>
                <td width="60px">性别</td>
                <td>手机号码</td>
                <td>年龄</td>
                <td>注册时间</td>
                <td width="90">操作</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="7">
                    无任何记录
                </td>
            </tr>
            <tr ng-repeat="r in results">
                <td>{{r.userName}}</td>
                <td>{{r.realName}}</td>
                <td>{{r.gender | sex}}</td>
                <td>{{r.mobile}}</td>
                <td>{{r.age}}</td>
                <td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
                <td><a ng-click="openHealthDialog(r.userId)">健康数据</a></td>
            </tr>
        </table>
    </div>
    <div class="content-right-bottom">
        <div id="page" style="text-align: center; margin-top: 30px"></div>
    </div>
    <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px;padding-top: 0">
        <div class="date_contain"><a class="pre_v" ng-click="switchDate(false)">
            <img
                    src="/static/images/pre_v.png"></a><a class="next_d" ng-click="switchDate(true)"><img
                src="/static/images/pre_v.png"></a><span>{{nowDate}}</span>
        </div>
        <div class="date_contain">
            <a class="pre_v" ng-click=""/>
            <table class="healthTable" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="20%">类型</td>
                    <td width="50%">测量值</td>
                    <td width="30%">检测时间</td>
                </tr>
                <tr ng-if="obj==''">
                    <td colspan="3">该时间段暂无数据</td>
                </tr>
                <tr ng-if="obj!=''">
                    <td>血糖仪</td>
                    <td><p>血糖值：{{obj.gluCometer.measureType | measureType}}{{obj.gluCometer.bloodSugar}}mmol/L</p>
                    </td>
                    <td>{{obj.gluCometer.measureDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                </tr>
                <tr ng-if="obj!=''">
                    <td>血压计</td>
                    <td><p>收缩压：{{obj.blood.systolic}}mmHg</p>
                        <p>舒张压：{{obj.blood.diastolic}}mmHg</p>
                        <p>心率：{{obj.blood.heartRate}}次/分</p>
                    </td>
                    <td>{{obj.blood.measureDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                </tr>
                <tr ng-if="obj!=''">
                    <td>血氧仪</td>
                    <td><p>血氧饱和度：{{obj.oxygen.saturation}}%</p>
                        <p>心率：{{obj.oxygen.heartRate}}次/分</p></td>
                    <td>{{obj.oxygen.measureDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                </tr>
                <tr ng-if="obj!=''">
                    <td>肺活仪</td>
                    <td><p>肺活量：{{obj.lung.vitalCapacity}}</p></td>
                    <td>{{obj.lung.measureDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                </tr>
                <tr ng-if="obj!=''">
                    <td>心率手环</td>
                    <td><p>心率：{{obj.heartRate.heartRate}}次/分</p></td>
                    <td>{{obj.heartRate.measureDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                </tr>
                <tr ng-if="obj!=''">
                    <td>体脂秤</td>
                    <td><p>体重：{{obj.fatScale.weight}}kg</p>
                        <p>BMI：{{obj.fatScale.bMI}}kg/m</p></td>
                    <td>{{obj.fatScale.measureDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                </tr>
            </table>
        </div>
    </div>
</div>

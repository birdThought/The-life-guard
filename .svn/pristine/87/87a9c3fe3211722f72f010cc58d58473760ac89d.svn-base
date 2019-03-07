<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<div class="right_body" ng-controller="memberCountController" ng-init=init()>
    <div class="right_title">         
        <label class="action">
            用户统计
        </label>
    </div>
    <div style="margin-top:20px">
        <div class="titleShow" style="margin-bottom:15px;position:relative">
            <span class="outdate">
                <label>门店</label>
                <input type="text" placeholder="请输入门店名" ng-model="counts.orgName"/>
            </span>
            <select id="showPro" class="service_select" style="width: auto" ng-model="counts.province">
                <option selected="selected" value=>全部省份</option>
                    <option ng-repeat="p in provinces" ng-value="p.name">{{p.name}}</option>
            </select>
            <select class="service_select" style="width: auto" ng-model="counts.avgAge" ng-init="counts.avgAge='-1'">
                 <option value="-1" selected>全部年龄段</option>
                 <option value="0">0~20岁</option>
                 <option value="1">20~30岁</option>
                 <option value="2">30~40岁</option>
                 <option value="3">40~50岁</option>
                 <option value="4">50~60岁</option>
                 <option value="5">60岁以上</option>
             </select>
            <input type="radio" value="0" ng-model="counts.radioValue" ng-init="counts.radioValue='0'"/>只根据省份排序&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" value="1" ng-model="counts.radioValue" />只根据年龄段排序
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;男人总数:<b>{{sum.man}}</b>&nbsp;&nbsp;&nbsp;&nbsp;女人总数:<b>{{sum.woman}}</b>&nbsp;&nbsp;&nbsp;&nbsp;总用户数:<b>{{sum.memberCount}}</b>&nbsp;&nbsp;&nbsp;&nbsp;缺失数据量:<b>{{sum.missing}}</b>
             <button class="search-btn" ng-click="search()">
                搜索
            </button>
        </div>
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td data-province>省份</td>
                <td data-Age>年龄段</td>
                <td>男</td>
                <td>女</td>
                <td>用户总数</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="5"> 无记录</td>
            </tr>
            <tr ng-repeat="r in results">
               <td data-province>{{r.province}}</td>
                <td data-Age>{{r.ageAvg}}</td>
                <td>{{r.man}}</td>
                <td>{{r.woman}}</td>
                <td>{{r.memberCount}}</td>
            </tr>
        </table>
    </div>
    <div class="content-right-bottom">
        <div id="page" style="text-align: center; margin-top: 30px"></div>
    </div>
</div>

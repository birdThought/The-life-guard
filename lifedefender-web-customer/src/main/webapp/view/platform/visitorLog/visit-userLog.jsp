
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<div class="orderCenter" ng-controller="visit-userLogController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="titleShow">
        <div style="font-size: 18px;display: inline-block;vertical-align: top">登录记录:</div>
        <div class="orderCenterTop clearfix" style="display: inline-block;width: auto;">
            <ol>
                <li class="orderDate">
                    <span class="small-tip">显示</span>
                    <select class="Select-Options" ng-model="conditions.userType">
                        <option value="">全部</option>
                        <option value="1">显示会员</option>
                        <option value="2">显示机构用户</option>
                    </select>
                </li>
                <li class="orderStatus" style="margin-left:50px;">
                    <span class="small-tip">登录终端 </span>
                    <select class="Select-Options" name="" id="" ng-model="conditions.terminalType">
                        <option value>登录终端-</option>
                        <option value="ios">ios</option>
                        <option value="android">android</option>
                        <option value="browse">browse</option>
                    </select>
                </li>
                <p ng-repeat="(index,x) in userLog" style="float:left;margin-top: 6px;margin-left: 20px;">
                    <span ng-if="index ==1">会员登录总次数 :<b style="display: inline-block;margin-right: 10px;color: #f40">{{x.memberCount}}人</b></span>
                    <span ng-if="index ==1">机构登录总次数 :<b style="display: inline-block;margin-right: 10px;color: #f40">{{x.orderCount}}人</b></span>
                </p>
            </ol>
        </div>
    </div>

    <div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>用户类型</td>
                <td>用户名</td>
                <td>机构名称</td>
                <td>登录终端</td>
                <td>登录IP地址</td>
                <td>登录时间</td>
            </tr>
            </thead>
            <fbody>
                <tr ng-repeat="o in userLog">
                    <td>{{o.userType | userType}}</td>
                    <td>{{o.userName}}</td>
                    <td>{{o.orgPo.orgName}}</td>
                    <td>{{o.terminalType}}</td>
                    <td>{{o.ip}}</td>
                    <td>{{o.loginTime | date:'yyyy-MM-dd hh:mm'}}</td>
                </tr>
            </fbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
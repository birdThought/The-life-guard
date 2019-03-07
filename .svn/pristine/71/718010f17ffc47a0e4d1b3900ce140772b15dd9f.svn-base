<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
    .layui-layer-content{height:auto!important;padding-bottom:34px}#orderCenterPopup span.small-tip{height:38px;width:98px}#orderCenterPopup section span{display:inline-block;width:118px;text-align:center}#orderCenterPopup2 section span{display:inline-block;width:118px;text-align:right}.layui-layer-page{width:630px!important}#orderCenterPopup section:nth-of-type(2){margin-left:0}#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
</style>
 <div class="orderCenter" ng-controller="agentController" ng-init="init()" style="padding: 34px 0 0 20px;">
<div class="titleShow">
    <div class="titleShow">
        <shiro:hasPermission name="agent:add"><a href="/index#!/agent/add" class="search-btn"
           style="position: relative;padding-top: 3px;margin-bottom: 5px">添加代理商</a></shiro:hasPermission>
    </div>
</div>

<!-- <div style="margin-top:20px">
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
                <label>手机号码</label>
                <input type="text" placeholder="请输入手机号码" value="" ng-model="search.mobile"/>
            </span>
            <button class="search-btn" ng-click="searchdata()">
                搜索
            </button>
        </div> -->

 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>帐号</td>
                <td>编号</td>
                <td>代理商名称</td>
                <td>联系人</td>
                <td>联系电话</td>
                <td>邮箱</td>
                <td>所属省份</td>
                <td>所属市</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="a in agent">
                	<td>{{$index+1}}</td>
                	<td>{{a.userName}}</td>
                    <td>{{a.userNo}}</td>
                    <td>{{a.agentName}}</td>
                    <td>{{a.name}}</td>
                    <!-- <td>{{a.contactMan}}</td> -->
                    <td>{{a.phone}}</td>
                    <td>{{a.email}}</td>
                    <td>{{a.provinceName}}</td>
                    <td>{{a.cityName}}</td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
</div>
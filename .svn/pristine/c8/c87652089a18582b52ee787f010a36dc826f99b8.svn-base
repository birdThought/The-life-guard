<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div ng-controller="reportAnalysisOrderController" ng-init='init()'>
    <div class="c-r-t">
        <p> <i class="iconfont icon-cursor"></i>报告分析处理 <i class="iconfont icon-dayu"></i><span class="orderCenter">订单中心</span></p>
    </div>
    <div class="c-r-m orderCenter-r-m clearfix">
        <h5>订单状态：
            <select ng-model="conditions.orderStatus">
                <option value="" selected>全部</option>
                <option value="3">处理中</option>
                <option value="4">已处理</option>
                <option value="1">待付款</option>
            </select>
            <%--<i class="option"></i>--%>
        </h5>
        <p>
            <input type="text" placeholder="请输入手机号/用户名" ng-model="conditions.keyword" ng-keypress="enterSearch($event)">
            <button ng-click="search()"><i class="layui-icon">&#xe615;</i>搜索</button>
        </p>
    </div>
    <div class="c-r-b">
        <table class="orederCenterTable" id="informationList" border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>序号</th>
                <th>用户名</th>
                <th>手机号码</th>
                <th>订单号</th>
                <th>订单日期</th>
                <th>服务项目</th>
                <th class="test">订单状态</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="(index, o) in orders">
                <td>{{index + 1}}</td>
                <td>{{o.userPO.realName || o.userPO.userName}}</td>
                <td>{{o.userPO.mobile}}</td>
                <td>{{o.orderNumber}}</td>
                <td>{{o.createDate | date:'yyyy-MM-dd'}}</td>
                <td>{{o.reportAnalysisPO.healthProduct | orderProduct}}</td>
                <td ng-class="statusClass(o.status)">{{o.status | orderStatus}}</td>
            </tr>

            </tbody>
        </table>
        <div id="page" style="text-align: center"></div>
    </div>
</div>
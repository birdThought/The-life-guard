<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">流水统计</label>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md11">
            <div class="layui-form layui-form-pane">
                <div class="layui-inline">
                    <label class="layui-form-label">订单编号</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" ng-model="data.condition.orderNumber" placeholder="请输入订单编号">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户姓名</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" ng-model="data.condition.realName" placeholder="请输入用户姓名">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">门店</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" ng-model="data.condition.orgName" placeholder="请输入门店名称">
                    </div>
                </div>
                <div class="layui-inline">
                    <select lay-filter="serveList" ng-model="data.condition.serveName" ng-options="s.code as s.name for s in data.serveList">
                        <option value="">请选择服务</option>
                    </select>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">时间段</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="date" placeholder="请选择时间段">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md1" style="text-align:right;">
            <button class="layui-btn layui-btn-normal" ng-click="search()">搜索</button>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md-12">
            <table class="layui-table" lay-even border="0" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>订单号</th>
                        <th>用户姓名</th>
                        <th>门店名称</th>
                        <th>服务名称</th>
                        <th>总金额</th>
                        <th>支付类型</th>
                        <th>付款账号</th>
                        <th>交易流水</th>
                        <th>明细</th>
                    </tr>
                </thead>
                <fbody>
                    <tr ng-repeat="o in data.orderFlowList" ng-cloak>
                        <td>{{($index + 1) + ((page.pageIndex - 1) * page.pageSize)}}</td>
                        <td>{{o.orderNumber}}</td>
                        <td>{{o.realName}}</td>
                        <td>{{o.orgName}}</td>
                        <td>{{o.serveName}}</td>
                        <td>{{o.cost/100 | currency:"￥"}}</td>
                        <td>{{o.payType | payType}}</td>
                        <td>{{o.payAccount}}</td>
                        <td>{{o.tradeNo}}</td>
                        <td><a href="javascript:void(0)" ng-click="detailsView(o.id)">查看</a></td>
                    </tr>
                </fbody>
            </table>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md-12">
            <div id="page" style="text-align: center; margin-top: 20px"></div>
        </div>
    </div>
    <div class="orderCenterTop clearfix">
        
    </div>
    <div class="orderCenterBottom" ng-cloak>
        
    </div>
    <!-- 查看明细 -->
    <div id="details" style="width:680px;display: none;padding: 35px;padding-left: 85px">
        <table style="width: 100%;line-height: 30px;text-align: left" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户姓名：{{flowDetails.realName}}</td>
                <td>订单号：{{flowDetails.orderNumber}}</td>
            </tr>
            <tr>
                <td>门店名称：{{flowDetails.orgName}}</td>
                <td>服务名称：{{flowDetails.serveName}}</td>
            </tr>
            <tr>
                <td>流水类型：{{flowDetails.flowType | flowType}}</td>
                <td>支付终端：{{flowDetails.payDevice}}</td>
            </tr>
            <tr>
                <td>支付类型：{{flowDetails.payType | payType}}</td>
                <td>付款账号：{{flowDetails.payAccount}}</td>
            </tr>
            <tr>
                <td>总金额：{{flowDetails.cost/100 | currency:"￥"}}</td>
                <td>支付金额：{{flowDetails.payCost/100 | currency:"￥"}}</td>
            </tr>
            <tr>
                <td>积分金额：{{flowDetails.pointsCost/100 | currency:"￥"}}</td>
                <td>平台分成：{{flowDetails.profitShare}}%</td>
            </tr>
            <tr>
                <td>平台收入：{{flowDetails.sysIncome/100 | currency:"￥"}}</td>
                <td>门店收入：{{flowDetails.orgIncome/100 | currency:"￥"}}</td>
            </tr>
            <tr>
                <td>收款账号：{{flowDetails.sellerAccount}}</td>
                <td>创建时间：{{flowDetails.createTime | date:"yyyy-MM-dd"}}</td>
            </tr>
        </table>
    </div>
</div>
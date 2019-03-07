<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">交易流水</label>
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
                    <select lay-filter="serveList" ng-model="data.condition.serveName" ng-options="s.name as s.name for s in data.serveList">
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
    <div class="layer-row">
        <div class="layer-col-md12">
            <table class="layui-table" cellspacing="0" cellpadding="0" lay-size="lg" layer-even>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>支付订单号</th>
                        <th>用户姓名</th>
                        <th>门店名称</th>
                        <th>服务名称</th>
                        <th>支付金额</th>
                        <th>流水类型</th>
                        <th>支付账号</th>
                        <th>支付终端</th>
                        <th>支付方式</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="o in data.orderFlowList">
                        <td>{{($index + 1) + ((page.pageIndex - 1) * page.pageSize)}}</td>
                        <td>{{o.orderNumber}}</td>
                        <td>{{o.realName}}</td>
                        <td>{{o.orgName}}</td>
                        <td>{{o.serveName}}</td>
                        <td>{{o.cost/100 | currency:"￥"}}</td>
                        <td>{{o.flowType | flowType}}</td>
                        <td>{{o.payAccount}}</td>
                        <td>{{o.payDevice}}</td>
                        <td>{{o.payType | payType}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="layer-row">
        <div class="layer-col-md12">
            <div id="page" style="text-align: center; margin-top: 20px"></div>
        </div>
    </div>
</div>
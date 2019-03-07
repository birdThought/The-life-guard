<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="fn.init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">订单统计</label>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md11">
            <div class="layui-form layui-form-pane">
                <div class="layui-inline">
                    <label class="layui-form-label">门店</label>
                    <div class="layui-input-block">
                        <input ng-model="data.condition.orgName" type="text" class="layui-input" placeholder="请输入门店名称">
                    </div>
                </div>
                <div class="layui-inline">
                    <select lay-filter="serveList" ng-model="data.condition.serveCode" ng-options="s.code as s.name for s in data.serveList">
                        <option value="">请选择服务</option>
                    </select>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">时间段</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="date" style="width:245px;" placeholder="请选择时间段">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md1" style="text-align:right;">
            <button class="layui-btn layui-btn-normal" ng-click="fn.search()">搜索</button>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <table class="layui-table" cellspacing="0" cellpadding="0" lay-size="lg" lay-even>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>门店名称</th>
                        <th>服务名称</th>
                        <th>总数</th>
                        <th>已支付</th>
                        <th>未支付</th>
                        <th>已完成</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="d in data.orderCountList">
                        <td>{{($index + 1) + ((page.pageIndex - 1) * page.pageSize)}}</td>
                        <td>{{d.orgName}}</td>
                        <td>{{d.serveName}}</td>
                        <td>{{d.allCount}}</td>
                        <td>{{d.payCount}}</td>
                        <td>{{d.unPayCount}}</td>
                        <td>{{d.finishCount}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div id="page" style="text-align: center; margin-top: 30px;"></div>
        </div>
    </div>
</div>
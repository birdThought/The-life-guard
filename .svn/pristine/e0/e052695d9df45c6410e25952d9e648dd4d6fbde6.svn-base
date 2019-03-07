<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="init()">
    <div class="layui-row title">
        <label class="title">交易结算</label>
    </div>
    <!-- 表格 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <table class="layui-table" lay-even>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>机构名称</th>
                        <th>结算月份</th>
                        <th>订单数量</th>
                        <th>合计费用</th>
                        <th>实际费用</th>
                        <th>生成日期</th>
                        <th>结算状态</th>
                        <th>机构确认时间</th>
                        <th>转账操作人</th>
                        <th>转账时间</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-if="dataList.length == 0">
                        <td colspan="11" style="text-align:center;">暂未有数据</td>
                    </tr>
                    <tr ng-repeat="d in dataList">
                        <td>{{($index + 1) + (page.pageIndex - 1) * page.pageSize}}</td>
                        <td>{{d.orgName}}</td>
                        <td>{{d.yearMonth}}</td>
                        <td>{{d.orderNumber}}</td>
                        <td>{{d.orderCharge | fenToYuan}}</td>
                        <td>{{d.statementCharge | fenToYuan}}</td>
                        <td>{{d.createDate | date:'yyyy-MM-dd hh:mm:ss'}}</td>
                        <td>{{d.status | statementStatus}}</td>
                        <td>{{d.confirmTime | date:'yyyy-MM-dd hh:mm:ss'}}</td>
                        <td>{{d.transferUser}}</td>
                        <td>{{d.transferTime | date:'yyyy-MM-dd hh:mm:ss'}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 分页 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <div id="page" style="text-align: center; margin-top: 30px;"></div>
        </div>
    </div>
</div>
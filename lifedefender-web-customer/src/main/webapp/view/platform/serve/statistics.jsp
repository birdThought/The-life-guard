<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="fn.init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">服务查看</label>
        </div>
    </div>
    <!-- 表格 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <table class="layui-table" lay-even>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>一级服务类别</th>
                        <th>二级服务类别</th>
                        <th>门店数</th>
                        <th>用户人数</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-if="data.statisticsList.length == 0">
                        <td colspan="6" style="text-align:center;">暂未有数据</td>
                    </tr>
                    <tr ng-repeat="s in data.statisticsList">
                        <td>{{$index + 1}}</td>
                        <td>{{s.parentName}}</td>
                        <td>{{s.name}}</td>
                        <td>{{s.orgCount}}</td>
                        <td>{{s.memberCount}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
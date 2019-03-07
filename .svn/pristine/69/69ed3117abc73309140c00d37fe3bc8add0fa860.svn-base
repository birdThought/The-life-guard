<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="fn.init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">服务查看</label>
        </div>
    </div>
    <!-- 搜索栏 -->
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
                    <label class="layui-form-label">服务名称</label>
                    <div class="layui-input-block">
                        <input ng-model="data.condition.classifyName" type="text" class="layui-input" placeholder="请输入服务名称">
                    </div>
                </div>
                <div class="layui-inline">
                    <%--<select lay-filter="serveList" ng-model="data.condition.serveCode" ng-options="s.code as s.name for s in data.serveList">--%>
                    <select lay-filter="serveList" ng-model="data.condition.serveCode">
                        <option value="">选择服务类型</option>
                        <option value="1">健康咨询</option>
                        <option value="4">健康课堂</option>
                        <option value="2">到店服务</option>
                        <option value="3">上门服务</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-col-md1 layui-col-md-offset-11" style="text-align:right;">
            <button class="layui-btn layui-btn-normal" ng-click="fn.search()">搜索</button>
        </div>
    </div>
    <!-- 表格 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <table class="layui-table" lay-even>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>服务名称</th>
                        <th>服务类别</th>
                        <th>门店名称</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-if="data.storeProjectList.length == 0">
                        <td colspan="4" style="text-align:center;">暂未有数据</td>
                    </tr>
                    <tr ng-repeat="p in data.storeProjectList">
                        <td>{{($index + 1) + (page.pageIndex - 1) * page.pageSize}}</td>
                        <td>{{p.name}}</td>
                        <td>{{p.classifyName}}</td>
                        <td>{{p.orgName}}</td>
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
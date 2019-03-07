<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="fn.init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">服务管理</label>
        </div>
    </div>
    <!-- 功能栏 -->
    <div class="layui-row">
        <div class="layui-col-md-offset11 layui-col-md-1" style="text-align:right;">
            <button class="layui-btn layui-btn-normal" ng-click="fn.addNew()">添加服务</button>
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
                        <th>服务代码</th>
                        <th>服务类型</th>
                        <th>添加时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-if="data.serveList.length == 0">
                        <td colspan="6" style="text-align:center">暂未有数据</td>
                    </tr>
                    <tr ng-repeat="s in data.serveList">
                        <td>{{$index + 1}}</td>
                        <td>{{s.name}}</td>
                        <td>{{s.code}}</td>
                        <td>{{s.serveType | serveType}}</td>
                        <td>{{s.createDate | date:'yyyy-MM-dd hh:mm:ss'}}</td>
                        <td>
                            <a ng-click="fn.edit(s)"><i class="layui-icon" style="font-size: 25px;">&#xe642;</i></a>
                            <a ng-click="fn.delConfirm(s)"><i class="layui-icon" style="font-size: 25px;">&#xe640;</i></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div name="dialog">
    <div id="serveDialog" class="layui-form layui-form-pane" style="display:none;padding:20px;">
        <div class="layui-form-item">
            <label class="layui-form-label">服务名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" ng-model="data.serve.name" placeholder="请输入服务名称">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">服务代码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" ng-model="data.serve.code" placeholder="请输入服务代码">
            </div>
        </div>
        <!-- 服务类型 -->
        <div class="layui-form-item">
            <label class="layui-form-label">服务类型</label>
            <div class="layui-input-block">
                <select lay-filter="serveType" ng-model="data.serve.serveType" ng-options="s.v as s.n for s in data.serveTypeList">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">平台分成</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" ng-model="data.serve.profitShare" ng-keyup="clearNoDigit(data.serve, 'profitShare')" placeholder="请输入平台分成">
            </div>
            <div class="layui-form-mid layui-word-aux">%</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">下级分类</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" ng-model="data.serve.classify" placeholder="请输入下级分类">
            </div>
            <div class="layui-form-mid layui-word-aux">多个分类以半角逗号分隔开</div>
        </div>
        <!-- 收费方式 -->
        <div class="layui-form-item">
            <label class="layui-form-label">收费方式</label>
            <div class="layui-input-line">
                <input lay-filter="chargeMode" type="checkbox" title="免费" ng-model="data.serve.chargeMode" value="0">
                <input lay-filter="chargeMode" type="checkbox" title="按次收费" ng-model="data.serve.chargeMode" value="1">
                <input lay-filter="chargeMode" type="checkbox" title="按月收费" ng-model="data.serve.chargeMode" value="2">
                <input lay-filter="chargeMode" type="checkbox" title="按年收费" ng-model="data.serve.chargeMode" value="3">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目类型</label>
            <div class="layui-input-block">
                <input name="projectType" lay-filter="projectType" type="radio" value="1" title="线上">
                <input name="projectType" lay-filter="projectType" type="radio" value="2" title="线下">
                <input name="projectType" lay-filter="projectType" type="radio" value="3" title="上门">
            </div>
        </div>
        <!-- 服务介绍 -->
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">服务介绍</label>
            <div class="layui-input-block">
                <textarea placeholder="不能超过70字" class="layui-textarea" ng-model="data.serve.about"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" ng-click="fn.save()">保存</button>
            </div>
        </div>
    </div>
</div>
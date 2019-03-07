<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init='fn.init()'>
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">订单查看</label>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md2 layui-col-md-offset10">
            <div class="layui-btn-container" style="text-align:right;">
                <button class="layui-btn" ng-click="fn.addNew()">添加栏目</button>
                <button class="layui-btn layui-btn-normal" ng-click="fn.saveMenu()">保存修改</button>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="tips">温馨提示：编辑完成后需要点击右上角保存按钮才可以正常修改菜单</div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <table cellpadding="0" cellspacing="0" class="layui-table">
                <thead>
                    <tr>
                        <th>菜单名称</th>
                        <th>添加子菜单</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-if="menuBtn.length == 0">
                        <td colspan="3">暂时没有栏目</td>
                    </tr>
                    <tr ng-repeat-start='b in data.menuBtn' ng-init="outerIndex = $index">
                        <td style="text-align: left;text-indent: 2em">
                            <img class="toggleBtn" src="/static/images/open.png" alt="" style="margin-right:8px;cursor: pointer" ng-click="fn.showChild($event, $index)">{{b.name}}
                        </td>
                        <td><a ng-click="fn.addNew(outerIndex)">新增</a></td>
                        <td>
                            <a ng-click="fn.editDialog(outerIndex)">
                                <i class="layui-icon" style="font-size: 25px;">&#xe642;</i>
                            </a>
                            <a ng-click="fn.deletMenu(outerIndex)">
                                <i class="layui-icon" style="font-size: 25px;">&#xe640;</i>
                            </a>
                        </td>
                    </tr>
                    <tr ng-repeat-end ng-repeat = "c in b.sub_button track by $index" data-child="{{outerIndex}}" class="hidden">
                        <td ng-bind="c.name" style="padding-left:60px;"></td>
                        <td></td>
                        <td>
                            <a ng-click="fn.editDialog(outerIndex, $index)">
                                <i class="layui-icon" style="font-size: 25px;">&#xe642;</i>
                            </a>
                            <a ng-click="fn.deletMenu(outerIndex, c)">
                                <i class="layui-icon" style="font-size: 25px;">&#xe640;</i>
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div name="dialog-content" style="display:none;width:auto;padding:20px;min-height:450px;" class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <label class="layui-form-label">上级菜单</label>
            <div class="layui-input-block">
                <select name="parentMenu" lay-filter="pMenu" ng-model="data.parentOption" ng-options="o as o.name for o in data.menuBtn">
                    <option value="">无</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称</label>
            <div class="layui-input-block">
                <input class="layui-input" type="text" placeholder="请输入栏目名称" ng-model="data.menuName"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">设置动作</label>
            <div class="layui-input-block">
                <input type="checkbox" lay-filter="action" lay-skin="switch" lay-text="开启|关闭" name="includeAction" checked>
            </div>
        </div>
        <div class="layui-form-item actionSelect" ng-if="data.includeAction">
            <label class="layui-form-label">动作类型</label>
            <div class="layui-input-block">
                <select lay-filter="actionType" ng-model="data.actionType">
                    <option value="view">网页跳转</option>
                    <option value="click">点击事件</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item actionInput" ng-if="data.actionType == 'view'">
            <label class="layui-form-label">url</label>
            <div class="layui-input-block">
                <input class="layui-input" type="text" placeholder="请输入网页跳转进入的网络链接" ng-model="data.actionUrl"/>
            </div>
        </div>
        <div class="layui-form-item actionInput" ng-if="data.actionType == 'click'">
            <label class="layui-form-label">菜单KEY值</label>
            <div class="layui-input-block">
                <input class="layui-input" type="text" placeholder="请输入点击事件自定义的KEY值" ng-model="data.actionKey"/>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button id="saveBtn" class="layui-btn save" ng-click="fn.saveOrEditMenu()">保存</button>
            </div>
        </div>
        <input id="menuFormHidden" type="hidden" data-pindex="-1" data-cindex="-1">
    </div>
</div>
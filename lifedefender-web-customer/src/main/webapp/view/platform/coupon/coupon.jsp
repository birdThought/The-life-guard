<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="fn.init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">优惠券管理</label>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md1 layui-col-md-offset11 top">
            <shiro:hasPermission name="coupon:add"><button class="layui-btn layui-btn-normal" ng-click="fn.openDialog()">添加卡券</button></shiro:hasPermission>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <table id="table" class="layui-table" border="0" cellspacing="0" cellpadding="0" lay-size="lg" lay-even>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>卡券模板名称</th>
                        <th>指定机构名称</th>
                        <th>指定服务</th>
                        <th>卡券金额(元)</th>
                        <th>有效期</th>
                        <th>券类型</th>
                        <th>更新日期</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-if="data.couponList.length == 0">
                        <td colspan="9" style="text-align: center;">无相关记录</td>
                    </tr>
                    <tr ng-repeat="c in data.couponList">
                        <td>{{($index + 1) + ((page.pageIndex - 1) * page.pageSize)}}</td>
                        <td>{{c.name}}</td>
                        <td>{{c.orgName}}</td>
                        <td>{{c.serveName}}</td>
                        <td>{{c.price}}</td>
                        <td>{{c.termOfValidity}}</td>
                        <td>{{c.type}}</td>
                        <td>{{c.modifyDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                        <td><a ng-click="fn.delConfirm(c)"><i class="layui-icon" style="font-size: 25px;">&#xe640;</i></a></td>
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
<!-- 对话框 -->
<div name="dialog">
    <!-- 卡券 -->
    <div id="addCouponDialog" class="layui-form layui-form-pane" style="display: none; padding: 20px;">
        <div class="layui-form-item">
            <label class="layui-form-label">卡券名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" placeholder="请输入名称" ng-model="data.submit.name"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">卡券金额</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" placeholder="请输入金额" ng-model="data.submit.price" ng-keyup="clearNoNum(data.submit, 'price')"/>
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">使用范围</label>
            <div class="layui-input-block">
                <input type="radio" name="usingScope" value="store" title="门店" disabled="disabled" checked>
                <input type="radio" name="usingScope" value="platform" title="平台(暂时不可选)" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选择机构</label>
            <div class="layui-input-inline">
                <select name="storeSelect" lay-filter="storeIdSelect" lay-search ng-model="data.submit.storeId" ng-options="store.id as store.orgName for store in data.storeList">
                    <option value="">直接选择或搜索选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">享受服务</label>
            <div class="layui-input-block">
                <select name="serveSelect" lay-filter="serveIdSelect" ng-model="data.submit.serveId" ng-options="serve.projectCode as serve.name for serve in data.serveList">
                    <option value="">请选择服务</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">有效时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入天数" ng-model="data.submit.validDay" ng-keyup="clearNoDigit(data.submit, 'validDay')"/>
            </div>
            <div class="layui-form-mid layui-word-aux">卡券领取后有效天数</div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" ng-click="fn.addNewSubmit()">立即提交</button>
            </div>
        </div>
    </div>
</div>
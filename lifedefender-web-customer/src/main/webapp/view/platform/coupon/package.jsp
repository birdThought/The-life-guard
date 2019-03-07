<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-init="fn.init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">优惠券礼包</label>
        </div>
    </div>
    <!-- 添加按钮 -->
    <div class="layui-row">
        <div class="layui-col-md1 layui-col-md-offset11 top">
            <shiro:hasPermission name="coupon_pkg:add"><button class="layui-btn layui-btn-normal" ng-click="fn.openAddDialog()">添加礼包</button></shiro:hasPermission>
        </div>
    </div>
    <!-- 表单 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <table id="table" class="layui-table" cellspacing="0" cellpadding="0" lay-size="lg" lay-even>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>礼包名称</th>
                        <th>礼包二维码</th>
                        <th>已使用</th>
                        <th>含卡券数量</th>
                        <th>发放数量</th>
                        <th>生成日期</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-if="data.packageList.length == 0">
                        <td colspan="8" style="text-align: center;">无相关记录</td>
                    </tr>
                    <tr ng-repeat="p in data.packageList">
                        <td>{{($index + 1) + ((page.pageIndex - 1) * page.pageSize)}}</td>
                        <td>{{p.name}}</td>
                        <td><a ng-click="fn.showCode(p.name, p.code, p.businessId)">查看</a></td>
                        <td> - </td>
                        <td>{{p.templetList.length}}</td>
                        <td> - </td>
                        <td>{{p.createDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                        <td>
                            <a ng-click="fn.look(p)"><i class="layui-icon" style="font-size: 25px;">&#xe63c;</i></a>
                            <a ng-click="fn.delConfirm(p)"><i class="layui-icon" style="font-size: 25px;">&#xe640;</i></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="layui-row">
            <div class="layui-col-md12">
                <div id="page" style="text-align: center; margin-top: 30px;"></div>
            </div>
        </div>
    </div>
</div>
<div name="dialog">
    <!-- 二维码 -->
    <div id="qrCodeContent" class="layui-container" style="display:none;width: auto !important;">
        <div class="layui-row">
            <div class="layui-col-md8 layui-col-md-offset2">
                <div id="qrcode"></div>
            </div>
        </div>
    </div>
    <!-- 礼包详情 -->
    <div id="packageDetail" class="layui-container" style="display:none;min-height:480px;">
        <div class="layui-row">
            <div class="layui-col-md12">
                <table class="layui-table" cellspacing="0" cellpadding="0">
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
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="c in data.packageDetail.templetList">
                            <td>{{$index + 1}}</td>
                            <td>{{c.name}}</td>
                            <td>{{c.orgName}}</td>
                            <td>{{c.serveName}}</td>
                            <td>{{c.price}}</td>
                            <td>{{c.termOfValidity}}</td>
                            <td>{{c.type}}</td>
                            <td>{{c.modifyDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- 礼包 -->
    <div id="addPackageDialog" class="layui-form layui-form-pane" style="display:none;padding:20px;min-height:665px;">
        <div class="layui-form-item">
            <label class="layui-form-label">礼包名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" placeholder="请输入名称" ng-model="data.submit.name"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">使用说明</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" placeholder="请输入名称" ng-model="data.submit.instructions"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">渠道商</label>
            <div class="layui-input-inline">
                <select name="businessSelect" lay-filter="businessIdSelect" lay-search ng-model="data.submit.businessId" ng-options="business.id as business.name for business in data.businessList">
                    <option value="">直接选择或搜索选择</option>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux">用户只能领取同一个渠道商发放的礼包</div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">筛选机构</label>
            <div class="layui-input-inline">
                <select name="storeSelect" lay-filter="storeIdSelect" lay-search ng-model="data.temp.storeId" ng-options="store.id as store.orgName for store in data.storeList">
                    <option value="">直接选择或搜索选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">筛选服务</label>
            <div class="layui-input-block">
                <select name="serveSelect" lay-filter="serveIdSelect" ng-model="data.temp.serveId" ng-options="serve.projectCode as serve.name for serve in data.serveList">
                    <option value="">请选择服务</option>
                </select>
            </div>
        </div>
        <!-- 卡券列表 -->
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" style="background-color:#009688; color:#FFF;">卡券列表</label>
            <div class="layui-input-block">
                <table class="layui-table" cellspacing="0" cellpadding="0" lay-even>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>模板名称</th>
                            <th>指定机构</th>
                            <th>指定服务</th>
                            <th>卡券金额(元)</th>
                            <th>有效期</th>
                            <th>券类型</th>
                            <th>更新日期</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-if="data.couponList.length == 0">
                            <td colspan="8" style="text-align: center;">无相关记录</td>
                        </tr>
                        <tr ng-repeat="c in data.couponList">
                            <td><input type="checkbox" name="coupon" lay-skin="primary" lay-filter="couponCheckbox" value="{{c}}" ng-checked="{{fn.hasCheckedCoupon(c.id)}}"></td>
                            <td>{{c.name}}</td>
                            <td>{{c.orgName}}</td>
                            <td>{{c.serveName}}</td>
                            <td>{{c.price}}</td>
                            <td>{{c.termOfValidity}}</td>
                            <td>{{c.type}}</td>
                            <td>{{c.modifyDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 已选择卡券列表 -->
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" style="background-color: #5FB878; color: #FFF;">已添加卡券列表</label>
            <div class="layui-input-block">
                <table class="layui-table" cellspacing="0" cellpadding="0" lay-even>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>模板名称</th>
                            <th>指定机构</th>
                            <th>指定服务</th>
                            <th>卡券金额(元)</th>
                            <th>有效期</th>
                            <th>券类型</th>
                            <th>更新日期</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-if="data.temp.couponList.length == 0">
                            <td colspan="8" style="text-align: center;">无相关记录</td>
                        </tr>
                        <tr ng-repeat="c in data.temp.couponList">
                            <td><input type="checkbox" name="couponChecked" lay-skin="primary" lay-filter="couponCheckbox" value="{{c}}" checked></td>
                            <td>{{c.name}}</td>
                            <td>{{c.orgName}}</td>
                            <td>{{c.serveName}}</td>
                            <td>{{c.price}}</td>
                            <td>{{c.termOfValidity}}</td>
                            <td>{{c.type}}</td>
                            <td>{{c.modifyDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" ng-click="fn.addPackage()">立即提交</button>
            </div>
        </div>
    </div>
</div>
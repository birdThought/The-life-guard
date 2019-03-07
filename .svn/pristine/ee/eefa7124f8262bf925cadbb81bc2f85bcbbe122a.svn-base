<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="layui-container" ng-init="fn.init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">订单查看</label>
        </div>
    </div>
    <!-- 搜索栏 -->
    <div class="layui-row">
        <div class="layui-col-md11">
            <div class="layui-form layui-form-pane">
                <div class="layui-inline">
                    <select lay-filter="os" ng-model="data.temp.nr" ng-options="o.v as o.n for o in data.temp.os"></select>
                </div>
                <div class="layui-inline">
                    <input ng-model="data.temp.nrValue" type="text" class="layui-input" placeholder="">
                </div>
                <div class="layui-inline" style="margin-left:15px;">
                    <label class="layui-form-label">门店</label>
                    <div class="layui-input-block">
                        <input ng-model="data.condition.orgName" type="text" class="layui-input" placeholder="请输入门店名称">
                    </div>
                </div>
                
                <div class="layui-inline" style="margin-left:15px;">
                    <!-- <label class="layui-form-label">订单类型</label> -->
                    <select name="oType" id="oType" ng-model="data.condition.orderType" >
                        <option value="">选择订单类型</option>
                        <option value="Vip">Vip套餐</option>
                        <option value="Service">服务</option>
                    </select>
                </div>
               <!--  <div class="layui-inline" style="margin-left:15px;">
                    <%--<select lay-filter="serveList" ng-model="data.condition.serveCode" ng-options="s.code as s.name for s in data.serveList">--%>
                    <select lay-filter="serveList" ng-model="data.condition.serveCode">
                        <option value="">选择服务类型</option>
                        <option value="1">健康咨询</option>
                        <option value="4">健康课堂</option>
                        <option value="2">到店服务</option>
                        <option value="3">上门服务</option>
                    </select> 
                    <select lay-filter="serveList" ng-model="data.condition.serveCode" ng-options="s.id as s.l1 for s in data.findServeList1">
                        
                    </select>
                </div>-->
                <div class="layui-inline" style="margin-left:15px;">
                    <select lay-filter="orderStatusList" ng-model="data.condition.orderStatus" ng-options="s.status as s.name for s in data.orderStatusList">
                        <option value="">订单状态</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-col-md1 layui-col-md-offset-11" style="text-align:right;">
        <!-- <button class="layui-btn layui-btn-normal" ng-click="fn.scanVipCombo()">vip套餐</button>-->
        <!-- <button class="layui-btn layui-btn-normal" ng-click="fn.testAgentIncome()">测试</button>  -->
            <button class="layui-btn layui-btn-normal" ng-click="fn.search()">搜索</button>
        </div>
    </div>
    <!-- 表格 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <table class="layui-table" cellpadding="0" cellspacing="0" lay-even>
                <thead>
                    <tr>
                        <th>序列</th>
                        <th>订单编号</th>
                        <th>用户名</th>
                        <th>用户姓名</th>
                        <th>门店</th>
                        <th>服务类型</th>
                        <th>订单类型</th>
                        <th>状态</th>
                        <th>消费金额</th>
                        <th ng-if="data.orderFlag == 'A'">平台编号</th>
                        <th ng-if="data.orderFlag == 'A'">平台获利(元)</th>
                        <th ng-if="data.orderFlag == 'A' || data.orderFlag == 'D'">代理商编号</th>
                        <th ng-if="data.orderFlag == 'A' || data.orderFlag == 'D'">代理商获利(元)</th>
                        <th>业务员编号</th>
                        <th>业务员获利(元)</th> 
                        <th>引入机构编号</th>
                        <th>引入机构获利(元)</th>
                        <th>服务机构编号</th>
                        <th>服务机构获利(元)</th>
                        <th>下单时间</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="o in data.orderList">
                        <td>{{($index + 1) + ((page.pageIndex - 1) * page.pageSize)}}</td>
                        <td>{{o.orderNumber}}</td>
                        <td>{{o.userName}}</td>
                        <td>{{o.realName}}</td>
                        <td>{{o.orgName}}</td>
                        <td>{{o.serveName}}</td>
                        <td>{{o.orderType}}</td>
                        <td>{{o.status | orderStatus}}</td>
                        <td>{{o.charge}}</td>
                        
                        
                        <!-- <td ng-if="data.orderFlag == 'A' && data.userNo != o.sysUserNo">{{o.sysIncome}}</td>
                        <td ng-if="data.orderFlag == 'A' && data.userNo == o.sysUserNo"><span style="color: red">{{o.sysIncome}}</span></td> -->
                        <td ng-if="data.orderFlag == 'A'">{{o.sysUserNo}}</td>
                        <td ng-if="data.orderFlag == 'A'">
                            <div ng-if="data.userNo != o.sysUserNo">{{o.sysIncome}}</div>
                            <div ng-if="data.userNo == o.sysUserNo"><span style="color: red">{{o.sysIncome}}</span></div>
                        </td>
                        
                        <td ng-if="data.orderFlag == 'A' || data.orderFlag == 'D'">{{o.agentUserNo}}</td>
                        <!-- <td ng-if="(data.orderFlag == 'A' || data.orderFlag == 'D') && data.userNo == o.agentUserNo"><span style="color: red">{{o.agentIncome}}</span></td>
                        <td ng-if="(data.orderFlag == 'A' || data.orderFlag != 'D') && data.userNo != o.agentUserNo">{{o.agentIncome}}</td> -->
                         <td ng-if="data.orderFlag == 'A' || data.orderFlag == 'D'">
                           <div ng-if="data.userNo != o.agentUserNo">{{o.agentIncome}}</div>
                           <div ng-if="data.userNo == o.agentUserNo"><span style="color: red">{{o.agentIncome}}</span></div>
                        </td>
                        
                        <td>{{o.salesmanUserNo}}</td>
                        <td>
                            <div ng-if="data.userNo != o.salesmanUserNo">{{o.salesmanIncome}}</div>
                            <div ng-if="data.userNo == o.salesmanUserNo"><span style="color: red">{{o.salesmanIncome}}</span></div>
                        </td> 
                        
                        <td>{{o.introduceOrgUserNo}}</td>
                        <td>
                            <div ng-if="data.userNo != o.introduceOrgUserNo">{{o.introduceOrgIncome}}</div>
                            <div ng-if="data.userNo == o.introduceOrgUserNo"><span style="color: red">{{o.introduceOrgIncome}}</span></div>
                        </td>
                        
                        <td>{{o.serviceOrgUserNo}}</td>
                       <td>
                            <div ng-if="data.userNo != o.serviceOrgUserNo">{{o.serviceOrgIncome}}</div>
                            <div ng-if="data.userNo == o.serviceOrgUserNo"><span style="color: red">{{o.serviceOrgIncome}}</span></div>
                       </td>
                        <td>{{o.createDate | date:"yy-MM-dd hh:mm:ss"}}</td>
                    </tr>
                </tbody>
            </table>
            <div style="" align="right"><font style="margin-left: 30%">机构盈利:{{data.toalMoeny}} 元</font> <font style="margin-left: 10%">引入机构:{{data.introduceTotal}} 元</font> <font style="margin-left: 10%">服务机构：{{data.serviceTotal}} 元</font>  <font style="margin-left: 10%"><b>总盈利:</b>{{data.moeny}} 元</font></div>
        </div>
    </div>
    <!-- 分页 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <div id="page" style="text-align: center; margin-top: 30px;"></div>
        </div>
    </div>
</div>
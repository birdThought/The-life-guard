
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<div class="orderCenter" ng-controller="shopOrderController" ng-init="init()" style="padding: 34px 0 0 20px;">

    <div class="titleShow">
        <div class="orderCenterTop clearfix" style="display: inline-block;width: auto;padding-left:0;">
            <ol>
                <li class="orderDate">
                    <span class="outdate">
                       <label>订单编号</label>
                       <input type="text" placeholder="请输入订单编号" value="" name="orderNo" ng-model="conditions.orderNo"/>
                    </span>
                </li>
                <li class="orderout">
                    <span class="outdate">
                       <label>下单人名称</label>
                       <input type="text" placeholder="请输入下单人名称" value="" name="userName" ng-model="conditions.userName"/>
                    </span>
                </li>
                <li class="orderout">
                    <span class="outdate">
                       <label>下单人手机号</label>
                       <input type="text" placeholder="请输入下单人手机号" value="" name="mobile" ng-model="conditions.mobile"/>
                    </span>
                </li>
                <li class="orderout" ng-if="userInfo.agentId == 0">
                    <span class="outdate">
                       <label>商铺名称</label>
                       <input type="text" placeholder="请输入商铺名称" value="" name="shopName" ng-model="conditions.shopName"/>
                    </span>
                </li>
                <li class="orderStatus">
                    <span class="small-tip">状态</span>
                    <!-- 订单状态1未支付，2已支付，3已完成 -->
                    <select class="Select-Options" name="status" id="status" ng-model="conditions.status">
                        <option value>-全部-</option>
                        <option value="1">未支付</option>
                        <option value="2">已支付</option>
                        <option value="3">已完成</option>
                    </select>
                </li>
                <li>
                	<button class="button_blue" style="margin-left: 100px;" ng-click="searchOrderList()">
						<i class="layui-icon">&#xe615;</i> 查询
					</button>
					<button class="button_blue" style="margin-left: 20px;" ng-click="resetSearch()">
						<i class="layui-icon">&#xe639;</i>重置
					</button>
                </li>
            </ol>
        </div>
    </div>
    <div class="orderCenterBottom">
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>订单编号</td>
                <td>下单人名称</td>
                <td>下单人手机号</td>
                <td width="140">下单时间</td>
                <td width="180">商品名称</td>
                <td>商品总额</td>
                <td>实付总额</td>
                <td>商品数量</td>
                <td>商品价格</td>
                <td>商铺名称</td>
                <td width="100">状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <fbody>
                <tr ng-repeat="o in results">
                    <td>{{o.orderNo}}</td>
                    <td>{{o.userName}}</td>
                    <td>{{o.mobile}}</td>
                    <td width="140">{{o.createTime | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                    <td>{{o.goodsName}}</td>
                    <td>{{o.amount}}</td>
                    <td>{{o.discountAmount}}</td>
                    <td>{{o.num}}</td>
                    <td>{{o.price}}</td>
                    <td>{{o.shopName}}</td>
                    <td width="100">{{o.status == 1?'未支付':o.status == 2?'已支付':o.status == 3?'已完成':o.status == 4?'运送中':''}}</td>
                    <!-- <td ng-if="o.status == 1">未支付</td>
                    <td ng-if="o.status == 2">已支付</td>
                    <td ng-if="o.status == 3">已完成</td> -->
                    <td ng-if="o.status > 2">
                    	<a ng-click = "logistics(o.shippingNo,o.com)" href="" title="查看物流" style="color:blue;cursor:hand;">
                    		查看物流
                    	</a>
                   	</td>
                   	<td ng-if="o.status <= 1"></td>
                </tr>
              
            </fbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
        
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="createCard" ng-controller="accountListController" ng-init='init()'>
	<div class="createTop">
	    
	</div>
	<div class="createContent">
		<div class="cardContent">
			<div class="activated" style="display: block;">
				<table id="settle_accounts" border="0" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<td>月份</td>
							<td>消费次数</td>
							<td>消费金额</td>
							<td>实付金额</td>
							<td>分成比率</td>
							<td>分成金额</td>
							<td>状态</td>
							<td>结算时间</td>
							<td>操作</td>
						</tr>
					</thead>
					<tfooter>
					<tr ng-repeat="o in orders">
						<td>{{o.months | date:'yyyy-MM'}}</td>
						<td>{{o.number}}次</td>
						<td>￥{{o.price}}</td>
						<td>￥{{o.payMoney}}</td>
						<td>{{o.percentage}}%</td>
						<td>￥{{o.settleAccounts}}</td>
						<td>{{o.status | payStatus}}</td>
						<td>{{o.closeDate | date:'yyyy-MM-dd'}}</td>
						<td><a ng-click="goAccount(o.thisMonth)">查看</a></td>
					</tr>
					</tfooter>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>
		</div>
	</div>
</div>
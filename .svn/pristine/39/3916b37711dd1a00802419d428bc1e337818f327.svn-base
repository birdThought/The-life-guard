<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<style>thead tr td:first-child
			{
				background: white;
			}</style>
<div id="createCard" ng-controller="monthCostController" ng-init='init()'>
	<div class="createContent">
		<div class="cardContent">
			<div class="activated" style="display: block;">
				<table id="settle_accounts" border="0" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<td>消费次数</td>
							<td>{{data.number}}次</td>
						</tr>
						<tr>
							<td>消费金额</td>
							<td>￥{{data.price}}</td>
						</tr>
						<tr>
							<td>实付付款</td>
							<td>￥{{data.payMoney}}</td>
						</tr>
						<tr>
							<td>操作</td>
							<td><a ng-click="goDetail()">消费清单</a></td>
						</tr>
					</thead>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>
		</div>
	</div>
</div>
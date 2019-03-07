<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="createCard" ng-controller="detailListController" ng-init='init()'>
	<div class="createTop">
	    
	</div>
	<div class="createContent">
		<div class="cardContent">
			<div class="activated" style="display: block;">
				<table id="settle_accounts" border="0" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<td>消费日期</td>
							<td>消费用户</td>
							<td>消费项目</td>
							<td>消费金额</td>
							<td>实付金额</td>
						</tr>
					</thead>
					<tfooter>
					<tr ng-repeat="o in orders">
						<td>{{o.startDate | date:'yyyy-MM-dd'}}</td>
						<td>{{o.realName}}</td>
						<td>{{o.serveName}}</td>
						<td>￥{{o.price}}</td>
						<td>￥{{o.payMoney}}</td>
					</tr>
					</tfooter>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>
		</div>
	</div>
</div>
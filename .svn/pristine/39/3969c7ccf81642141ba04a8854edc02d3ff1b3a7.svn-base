<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<style>
#settle_accounts tbody a {
	cursor:pointer;
}
</style>
<div id="createCard" ng-controller="userManagerController" ng-init='init()'>
	<div class="createTop">
		<input type="text" style="width: 300px; height: 30px;border:1px solid #eee;padding-left:10px;"
			placeholder="请输入姓名" name="realName" ng-model="conditions.realName" />&nbsp;&nbsp;
		<p class="batchCreate" style="width:80px;height:32px;border-radius:2px;margin-left:10px" ng-click="listOrders()">查询</p>
	</div>
	<div class="createContent">
		<div class="cardContent">
			<div class="activated" style="display: block;">
				<table id="settle_accounts" border="0" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<td>序号</td>
							<td>姓名</td>
							<td>性别</td>
							<td>年龄</td>
							<td>注册日期</td>
							<td>引入日期</td>
							<td>操作</td>
						</tr>
					</thead>
					<tfooter>
					<tr ng-repeat="(index, o) in orders">
						<td>{{index + 1}}</td>
						<td>{{o.realName}}</td>
						<td>{{o.gender | gender}}</td>
						<td>{{o.age}}</td>
						<td>{{o.createDate | date:'yyyy-MM-dd'}}</td>
						<td>{{o.joinDate | date:'yyyy-MM-dd'}}</td>
						<td><a ng-click="goDetail(o.id)">查看</a></td>
					</tr>
					</tfooter>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>
		</div>
	</div>
</div>
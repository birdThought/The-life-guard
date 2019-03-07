<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="createCard" ng-controller="settleController" ng-init="init()">
	<div class="createTop">
		<input id="dateSelect" ng-model="temps.date" style="text-align:center;display:inline-block;line-height: 30px;border:1px solid #ddd;width: 100px;height:30px;"/></label>
		<form style="display: inline-block;width: 814px;" action="" class="layui-form">
			<button style="float:right;" class="layui-btn layui-btn-warm" ng-click="show()">搜索</button>
		</form>
	</div>
	<div class="createContent">
		<div class="cardContent">
			<div class="activated" style="display: block;">
				<table id="settle_accounts" border="0" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td>月份</td>
							<td>消费次数</td>
							<td>实付金额</td>
							<td>分成比率</td>
							<td>分成金额</td>
							<td>状态</td>
							<td>结算时间</td>
							<td>操作</td>
						</tr>
					</thead>
					<tfooter>
					<tr ng-repeat="(index, o) in recordList">
						<td>{{o.moon}}</td>
						<td>{{o.count}}</td>
						<td>{{o.amount | fenToYuan}}</td>
						<td>20%</td>
						<td>{{o.split | fenToYuan3}}</td>
						<td>{{o.status | Record}}</td>
						<td>{{o.createDate | date:"yyyy-MM-dd"}}</td>
						<td>
							<a class="red" ng-click="openRecord(o.createDate)"><i class="layui-icon" style="font-size: 25px;">&#xe63c;</i></a>
							<a class="red" ng-click="del(o.id)"><i class="layui-icon" style="font-size: 25px;">&#xe640;</i></a>
						</td>
					</tr>
					</tfooter>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>
		</div>
	</div>

	<div class="layui-row">
		<div id="settle_record" class="layui-table" style="display: none;">
			<table style="padding-left: 24px;">
				<thead>
				<tr>
					<td>序号</td>
					<td>购买用户</td>
					<td>购买套餐</td>
					<td>推荐人</td>
					<td>套餐价格</td>
					<td>状态</td>
					<td>购买时间</td>
				</tr>
				</thead>
				<tfooter>
					<tr ng-repeat="(index, r) in spreadList">
						<td>{{index + 1}}</td>
						<td>{{r.realName}}</td>
						<td>{{r.name}}</td>
						<td>{{r.buName}}</td>
						<td>{{r.price| fenToYuan}}</td>
						<td>{{r.status | orderStatus2}}</td>
						<td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
					</tr>
				</tfooter>
			</table>
		</div>
	</div>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="createCard" ng-controller="temporaryController" ng-init='init()'>
	<div class="createTop">
		<input id="dateSelect" ng-model="temps.date" style="text-align:center;display:inline-block;line-height: 30px;border:1px solid #ddd;width: 100px;height:30px;"/></label>
		<form style="display: inline-block;width: 814px;" action="" class="layui-form">
			<div style="display: inline-block;" class="layui-form-item" ng-if="userInfo.superior ==0?true:false">
				<label style="width: 108px;" class="layui-form-label">销售员名称</label>
				<div class="layui-input-block">
					<input style="width: 240px;display: inline-block" type="text" ng-model="temps.bname" lay-verify="title" autocomplete="off" placeholder="请输入销售员名" class="layui-input">
				</div>
			</div>
			<div style="display: inline-block;" class="layui-form-item">
				<label style="width: 108px;" class="layui-form-label">套餐名</label>
				<div class="layui-input-block">
					<input style="width: 240px;display: inline-block" type="text" ng-model="temps.name" lay-verify="title" autocomplete="off" placeholder="请输入套餐名" class="layui-input">
				</div>
			</div>
			<button style="float:right;" class="layui-btn layui-btn-warm" ng-click="show()">搜索</button>
		</form>

	</div>
	<div class="createContent" >
		<div class="cardContent" style="margin-top: 10px;">
			<div class="activated" style="display: block;">
				<table id="settle_accounts" border="0" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<td>序号</td>
							<td>用户名</td>
							<td>套餐名称</td>
							<td>价格</td>
							<td>推销员</td>
							<td>购买时间</td>
							<td>状态</td>
						</tr>
					</thead>
					<tfooter>
					<tr ng-repeat="r in results">
						<td>{{$index + 1}}</td>
						<td>{{r.userName}}</td>
						<td>{{r.name}}</td>
						<td>￥{{r.price | fenToYuan}}</td>
						<td>{{r.bname}}</td>
						<td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
						<td>{{r.status | orderStatus2}}</td>

					</tr>
					</tfooter>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>
		</div>
	</div>
</div>
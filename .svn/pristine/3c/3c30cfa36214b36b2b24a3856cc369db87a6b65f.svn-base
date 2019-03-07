<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
	.orderCenterBottom table td {padding:10px;}
	.itemDes {
		width: 800px;
		text-align: left;
	}
	.content-right-top {
		background: #fff;
		height: 76px;
		padding-bottom: 24px;
		border-bottom: 1px dashed #ddd;
		margin-bottom: 24px;
	}
	.orderCenterBottom thead tr:nth-child(1){
		background: #f5f5f5;
		font-weight: bold;
		color: #333;
	}
	.orderCenterBottom tr {
		border: 1px solid #ddd;
	}
	.orderCenterBottom tr:nth-child(2n){
		background: #f8f8f8;
	}
	.orderCenterBottom tr:hover {
		background: #f8f8f8;
	}
</style>
<link rel="stylesheet" href="/static/css/vipmember/vip-member.css">
<link rel="stylesheet" href="/static/css/vipmember/member-health-data.css">
<div ng-controller="smsRecordController" ng-init='init()'>
	<div class="Main-content">
		<div class="content-right-top">
			<p><span class="small-tip">发送者</span> <input class="Select-Options"  type="text" name="userName" placeholder="请输入发送者名称" ng-model="conditions.userName">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="small-tip">接收号码</span> <input  class="Select-Options" type="text" name="receiveMobile" placeholder="请输入接收号码" ng-model="conditions.receiveMobile">
				<button class="button_blue" style="margin-left: 88px" ng-click="smsRecordList()">
					<i class="layui-icon">&#xe615;</i> 查询
				</button>
			</p>
		</div>
		<div class="orderCenterBottom" >
			<table border="1" cellpadding="0" cellspacing="0" style="text-align: center;width: 100%;">
				<thead>
				<tr>
					<td>发送者</td>
					<td>发送者类型</td>
					<td>接收号码</td>
					<td style="width: 700px;">内容</td>
					<td>状态</td>
					<td>IP</td>
					<td>发送时间</td>
				</tr>
				</thead>
				<tbody>
				<tr ng-repeat="item in smsRecord" ng-cloak>
					<td>{{item.userName}}</td>
					<td>{{item.sendType}}</td>
					<td>{{item.receiveMobile}}</td>
					<td class="itemDes">{{item.content}}</td>
					<td>{{item.status | sendStatus}}</td>
					<td>{{item.ip}}</td>
					<td>{{item.createDate | date:"yyyy-MM-dd"}}</td>
				</tr>
				</tbody>
			</table>
			<div id="page" style="text-align: center; margin-top: 20px"></div>
		</div>
	</div>
</div>
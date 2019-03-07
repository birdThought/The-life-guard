<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">

 <div class="orderCenter" ng-controller="success-customerOrderController" ng-init="init()" style="padding: 34px 0 0 20px;">

    <div class="">
        <div class="c-r-t">
            <p> <i class="iconfont icon-cursor"></i>预约管理 &gt;
            <a href="#!/combo/member/worklist"class="orderCenter">预约记录</a></>
        </div>
    </div>
 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>用户名</td>
                <td>性别</td>
                <td>手机号码</td>
                <td>服务名称</td>
                <td>预约时间</td>
                <td>约定时间</td>
                <td>用户备注</td>
                <td>客服备注</td>
                <td>地点</td>
                <td>使用状态</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="o in customerorders">
                	<td>{{$index+1}}</td>
                    <td>{{o.userName}}</td>
                    <td>{{o.gender | gender}}</td>
                    <td>{{o.mobile}}</td>
                    <td>{{o.serveName}}</td>
                    <td>{{o.appoinDate | date:'yyyy-MM-dd'}}</td>
                    <td>{{o.sureDate   | date:'yyyy-MM-dd'}}</td>
                    <td>{{o.userRemark}}</td>
  					<td>{{o.customerRemark}}</td> 
  					<td>{{o.address}}</td>
  					<td><span class="ny">已完成</span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
        
    </div>
  
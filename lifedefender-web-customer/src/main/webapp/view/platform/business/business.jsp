<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
.layui-layer-content {
	height:auto!important;
	padding-bottom:34px;
}
	#orderCenterPopup section span,#orderCenterPopup2 section span {
		display:inline-block;
		width:100px;
		text-align:center;
        line-height: 36px;
	}
	.layui-layer-page{width:630px!important}
	#orderCenterPopup section:nth-of-type(2){margin-left:0}
	#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
</style>
 <div class="orderCenter" ng-controller="businessController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="titleShow">
        <shiro:hasPermission name="business:add"><a href="/index#!/business/add" class="search-btn"
           style="position: relative;padding-top: 3px;margin-bottom: 5px">添加渠道商</a></shiro:hasPermission>
    </div>
 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>渠道商名称</td>
                <td>联系人</td>
                <td>联系电话</td>
                <td>类型</td>
                <td>编辑</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="b in business">
                	<td>{{$index+1}}</td>
                    <td>{{b.name}}</td>
                    <td>{{b.contactMan}}</td>
                    <td>{{b.phone}}</td>
                    <td>{{b.type| businessType}}</td>
                    <td><span class="ny" ng-click="popupEditBusiness(b)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>

        <%--<div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
        <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                渠道商名称
            </span>
            <input id="accountsName" class="doctor-sign Select-Options" ng-model="edit.name" placeholder="请输入渠道商名称" >
        </section>

        <section class="clearfix" style="margin-top: 15px;">
        <span style="line-height: 38px" class="small-tip">
            渠道商状态
        </span>
            <select class="doctor-sign Select-Options" ng-model="edit.status" ng-init="edit.status='0'" class="xla_k">
                <option value="0">正常</option>
                <option value="1">停用</option>
            </select>
        </section>

        <section class="clearfix" style="margin-top: 15px;">
        <span class="small-tip">
            联系人
        </span>
            <input id='PersonName' class="doctor-sign Select-Options" ng-model="edit.contactMan" placeholder="请输入联系人名字" >
        </section>

        <section class="clearfix" style="margin-top: 15px;">
        <span class="small-tip">
            联系人电话
        </span>
            <input id="buiness_phone" type="" class="doctor-sign Select-Options" ng-model="edit.phone" placeholder="请输入联系电话" >
        </section>

        <section class="clearfix" style="margin-top: 15px;">
        <span class="small-tip">
            邮箱
        </span>
            <input type='email' ng-model="edit.email" class="doctor-sign Select-Options" placeholder="输入邮箱..." >
        </section>

        <section class="clearfix" style="margin-top: 15px;">
        <span class="small-tip">
            渠道商类型
        </span>
            <select style="line-height: 38px" class="doctor-sign Select-Options" class="xla_k" ng-model="edit.type">

                <option value="2">推广渠道</option>
                <option value="4">会员销售渠道</option>
                <option value="8">分析服务渠道</option>
            </select>
        </section>
        <section class="clearfix" style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="addBusiness()">
        </section>
    </div> --%>
    
	<!-- 弹出修改渠道商框 -->
	<div id="orderCenterPopup2" class="orderPopup ">
	<section class="clearfix">

        </section>
      <%--  <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                帐号名称
            </span>
            <input id="accountsName2" class="doctor-sign Select-Options" placeholder="修改帐号名称" ng-model="edit.userName">
        </section>--%>
        
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                渠道商名
            </span>
            <input id="channel_name2" class="doctor-sign Select-Options" placeholder="修改渠道商名" ng-model="edit.name">
        </section>
       
        <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                联系人
            </span>
            <input id="addresss2" class="doctor-sign Select-Options" placeholder="修改联系人" ng-model="edit.contactMan">
        </section>

        <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                联系人电话
            </span>
            <input id="buiness_phone2" type="" class="doctor-sign Select-Options"  placeholder="修改联系电话" ng-model="edit.phone">
        </section>

      <section class="clearfix" class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                邮箱
            </span>
            <input type='email' id="business_email2"  class="doctor-sign Select-Options" placeholder="修改邮箱..." ng-model=edit.email>
        </section>

        <section class="clearfix" style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="editBusiness(b)">
        </section>
    </div>
  
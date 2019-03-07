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
 <div class="orderCenter" ng-controller="sellManageController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="titleShow">
        <button style="margin: 14px 0 8px 20px;float: none;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none;" class="search search-btn cursor-pointer" ng-click="addsell(userInfo.type)">添加用户</button>
    </div>
 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>帐号</td>
                <td>用户名称</td>
                <td>创建时间</td>
                <td>类型</td>
                <td>编辑</td>
            </tr>
            </thead>
            <tbody>
               <tr ng-repeat="b in ManAgeMent">
                	<td>{{$index+1}}</td>
                    <td>{{b.userName}}</td>
                    <td>{{b.name}}</td>
                    <td>{{b.createDate | date:'yyyy-MM-dd'}}</td>
                    <td>{{b.status | ofTypes}}</td>
                    <td><span class="ny" ng-click="openManAgeMent(b)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>

     <div id="orderCenterPopup" class="orderPopup ">
         <section class="clearfix">

         </section>
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                帐号名称
            </span>
             <input id="accountsName" class="doctor-sign Select-Options" ng-model="Fields.userName" placeholder="请输入帐号名称" >
         </section>

         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                用户名
            </span>
             <input id="channel_name" class="doctor-sign Select-Options" ng-model="Fields.name" placeholder="请输入用户名名" >
         </section>

         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                地址
            </span>
             <input id="addresss" class="doctor-sign Select-Options" ng-model="Fields.address" placeholder="请输入地址">
         </section>

         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                联系人电话
            </span>
             <input id="buiness_phone" type="" class="doctor-sign Select-Options" ng-model="Fields.phone" placeholder="请输入联系电话" >
         </section>

         <section class="clearfix" style="margin-top: 15px;">
            <span style="line-height: 38px" class="small-tip">
                状态
            </span>
             <select class="doctor-sign Select-Options"   ng-model="Fields.status" ng-init="Fields.status='0'">
                 <option value="0">正常</option>
                 <option value="1">禁用</option>
             </select>
         </section>


         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                密码
            </span>
             <input type="password"  class="doctor-sign Select-Options" ng-model="Fields.password" placeholder="请输入密码..." >
         </section>
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                重复输入密码
            </span>
             <input type="password" class="doctor-sign Select-Options" ng-model="Fields.pwds" placeholder="重复输入密码..." >
         </section>
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                邮箱
            </span>
             <input type='email'  class="doctor-sign Select-Options" ng-model="Fields.fmailbox" placeholder="输入邮箱...">
         </section>

         <section class="clearfix" style="text-align: center; margin-top: 15px;">
             <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button"
                    value="提交数据" ng-click="addBusiness()">
         </section>
     </div>

     <!-- 弹出修改渠道商框 -->
	<div id="orderCenterPopup2" class="orderPopup ">
	<section class="clearfix">

        </section>
        <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                帐号名称
            </span>
            <input id="accountsName2" class="doctor-sign Select-Options" ng-model="modify.userName" value="{{b.userName}}">
        </section>
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                用户名
            </span>
            <input id="channel_name2" class="doctor-sign Select-Options" ng-model="modify.name" value="{{b.name}}">
        </section>

        <section class="clearfix" style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button"
                   value="提交数据" ng-click="modifyBusiness()">
        </section>
    </div>
 </div>
  
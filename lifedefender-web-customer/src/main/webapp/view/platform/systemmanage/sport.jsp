<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
.layui-layer-content {
	height:auto!important;
	padding-bottom:34px;
}
	#orderCenterPopup section span,#orderCenterPopup2 section span {
		display:inline-block;
		width:118px;
		text-align:center;
        line-height: 38px;
	}
	.layui-layer-page{width:630px!important}
	#orderCenterPopup section:nth-of-type(2){margin-left:0}
	#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
</style>
 <div class="orderCenter" ng-clock ng-controller="sportController" ng-init="init()" style="padding: 16px 0 0 20px;">
     <div class="titleShow clearfix">
         <label style="border-left:2px solid #0093ff;padding-left: 6px;margin-bottom: 10px;display: block;font-size: 18px;color: #0093ff;" class="action">
             运动管理
         </label>
         <p class="orderDate" style="display:inline-block;">

             <span class="small-tip"> 运动种类</span>
             <select class="Select-Options" style="margin-right: 60px; width: 140px;border: 1px solid #ddd;padding: 6px 10px;" ng-model="conditions.kind">
                 <option value="">运动种类</option>
                 <option  ng-repeat="s in sportKind" ng-value='s.id'>{{s.name}}</option>
             </select>
             <span class="small-tip">运动名称：</span>
           		<input  type="text" style="width: 240px;border: 1px solid #ddd;padding: 7px 5px;" placeholder="请输入运动名称" ng-model="conditions.name">
                 <!--           		 <button class="button_blue" style="margin-left: 50%" ng-click="search()"> -->
                 <!--                 <i class="layui-icon">&#xe615;</i> 查询 -->
                 <!--            		</button> -->

         </p>
         <div style="display: inline-block;">
             <button style="margin: 14px 0 8px 100px;float: none;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none;" class="search search-btn cursor-pointer" ng-click = "addDialog()">添加运动</button>
         </div>
     </div>
 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>运动名称</td>
                <td>运动种类</td>
                <td>卡路里/kcal</td>
                <td>编辑</td>
                <td>删除</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="s in sport">
                	<td>{{s.name}}</td>
                	<td>{{s.sportKind.name}}</td>
                	<td>{{s.kcal}}</td>
                    <td><span class="ny" ng-click="EditDialog(s)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span></td>
  					<td><span class="ny" ng-click="DeleteDialog(s)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
	<!-- 删除 -->
 	   <div id="deleteContent" style="display: none;text-align: center;">
            <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
            <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
                ng-click="deletesport()"
            >确认</button>
        </div>
 
	<!-- 添加运动 -->
     <div id="orderCenterPopup" class="orderPopup ">
         <section  class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                运动名称：
            </span>
            <input id="sport_name" class="doctor-sign Select-Options" placeholder="请输入运动名称" >
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                卡路里：
            </span>
            <input id="sport_kcal" class="doctor-sign Select-Options" placeholder="请输入卡路里" >
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                运动种类：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="sport_kind" class="xla_k" >
				<option ng-repeat="s in sportKind" ng-value='s.id' >{{s.name}}</option>
			</select>
        </section>
           
        <section class="clearfix"  style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="addsport()">
        </section>
    </div> 
    
	<!--编辑种类 -->
      <div id="orderCenterPopup2" class="orderPopup ">
        <section class="clearfix">

        </section>
        
         <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
               运动名称：
            </span>
            <input id="sport_name" class="doctor-sign Select-Options" placeholder="修改运动名称" ng-model="edit.name">
        </section>
       
       <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                卡路里：
            </span>
            <input id="sport_kcal" class="doctor-sign Select-Options" placeholder="修改卡路里" ng-model="edit.kcal">
        </section>
       
       <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                运动种类：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="sport_kind" class="xla_k" ng-model="edit.kind">
				<option ng-repeat="s in sportKind" ng-value='s.id' >{{s.name}}</option>
			</select>
        </section>
   
        <section class="clearfix" style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="editsport()">
        </section>
    </div>
    
  </div>
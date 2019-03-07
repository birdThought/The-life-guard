<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
.layui-layer-content {
	height:auto!important;
	padding-bottom:34px;
}
	#orderCenterPopup section span {
		display:inline-block;
		width:118px;
		text-align:right;
	}
	#orderCenterPopup2 section span {
		display:inline-block;
		width:118px;
		text-align:right;
	}
	.layui-layer-page{width:630px!important}
	#orderCenterPopup section:nth-of-type(2){margin-left:0}
	#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
/* 	#orderCenterPopup reason_description {width:350px;border:none; border-bottom:1px solid #cde;height:80px;vertical-align:top} */
</style>
 <div class="orderCenter" ng-controller="reasonController" ng-init="init()" style="padding: 16px 0 0 20px;">
     <div class="titleShow clearfix">
         <label style="border-left:2px solid #0093ff;padding-left: 10px;margin-bottom: 10px;display: block;font-size: 18px;color: #0093ff;" class="action">
             测量原因
         </label>
         <p class="orderDate">
             <span class="small-tip">健康参数</span>
             <select class="Select-Options" style=" width: 140px;border: 1px solid #ddd;padding: 6px 10px;" ng-model="conditions.healthPackageParamId">
                 <option value="">选择参数</option>
                 <option  ng-repeat="h in healthParam" ng-value='h.id'>{{h.name_cn}}</option>
             </select>

         </p>
     </div>
 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>健康参数</td>
                <td style="width: 640px;padding: 4px 6px;">形成原因</td>
                <td>状态</td>
                <td>是否为专业原因</td>
                <td>创建日期</td>
                <td>操作</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="r in reason">
                	<td>{{r.healthPackageParam.name_cn}}</td>
                	<td style="width:40%;height:65px;">{{r.reason}}</td>
                	<td>{{r.status| healthStatus2}}</td>
                	<td>{{r.professional | professional}}</td>
                	<td>{{r.createDate | date:'yyyy-MM-dd'}}</td>
                    <td><span class="ny" ng-click="EditDialog(r)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span>
  					<span class="ny" ng-click="DeleteDialog(r)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
	<!-- 删除 -->
 	   <div id="deleteContent" style="display: none;text-align: center;">
            <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
            <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
                ng-click="deletereason()"
            >确认</button>
        </div>
     
	<!--编辑种类 -->
      <div id="orderCenterPopup2" class="orderPopup ">
        <section class="clearfix">

        </section>
          
           <section style="margin-top: 15px;">
            <span class="#999">
               * 形成原因：
            </span>
            <textarea id="reason_description" cols="50" rows="5" placeholder="请输入描述文字" ng-model="edit.reason"></textarea>
        </section>
        
           <section style="margin-top: 15px;">
            <span class="#999">
                状态：
            </span>
            <select class="doctor-sign"   name="select" id="reason_status" class="xla_k"ng-model="edit.status" >
				<option value='1' >低</option>
				<option value='2' >偏低</option>
				<option value='3' >正常</option>
				<option value='4' >偏高</option>
				<option value='5' >高</option>
			</select>
        </section>
        
            <section style="margin-top: 15px;">
            <span class="#999">
                是否为专业原因：
            </span>
            <select class="doctor-sign"   name="select" id="reason_gender" class="xla_k" ng-model="edit.professional" >
				<option value='0' >否</option>
				<option value='1' >是</option>
			</select>
        </section>
  
   
        <section style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="editreason()">
        </section>
    </div>
    
  </div>
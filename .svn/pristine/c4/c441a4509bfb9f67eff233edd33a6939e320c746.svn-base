<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
.layui-layer-content {
	height:auto!important;
	padding-bottom:34px;
}
	#orderCenterPopup section span,#orderCenterPopup2 section span {
		display:inline-block;
		width:90px;
		text-align:center;
        line-height: 38px;
	}
    #suggestion_healthPackageParamId{
        width: 140px;
        padding: 10px;
        border: 1px solid #ddd;
    }
	.layui-layer-page{width:630px!important}
	#orderCenterPopup section:nth-of-type(2){margin-left:0}
	#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
/* 	#orderCenterPopup suggestion_description {width:350px;border:none; border-bottom:1px solid #cde;height:80px;vertical-align:top} */
</style>
 <div class="orderCenter" ng-clock ng-controller="suggestionController" ng-init="init()"  style="padding: 16px 0 0 20px;">
     <div class="titleShow">
         <label style="border-left:2px solid #0093ff;padding-left: 6px;margin-bottom: 10px;display: block;font-size: 18px;color: #0093ff;" class="action">
             测量建议
         </label>
         <p class="orderDate" style="display: inline-block;">
             <span class="small-tip">健康参数</span>
             <select class="Select-Options"  style=" width: 140px;border: 1px solid #ddd;padding: 6px 10px;" ng-model="conditions.healthPackageParamId">
                 <option value="" ">选择参数</option>
                 <option  ng-repeat="h in healthParam" ng-value='h.id'>{{h.name_cn}}</option>
             </select>

         </p>

         <div class="clearfix" style="display: inline-block">
             <button style="margin: 14px 0 6px 100px;float: left;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none;" class="search search-btn cursor-pointer" ng-click = "addDialog()">添加测量建议</button>
         </div>
     </div>

 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>测量参数</td>
                <td style="width: 570px;padding: 4px;">描述文字</td>
                <td>状态</td>
                <td>性别</td>
                <td>年龄区间</td>
                <td>显示方式</td>
                <td>创建者</td>
                <td>创建日期</td>
                <td>修改日期</td>
                <td>操作</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="s in suggestion">
                	<td>{{s.healthPackageParam.name_cn}}</td>
                	<td style="width:40%;height:65px;">
                	{{s.description}}</td>
                	<td>{{s.status | healthStatus}}</td>
                	<td>{{s.gender | gender}}</td>
                	<td>{{s.startAge}}-{{s.endAge}}</td>
                	<td>{{s.display |display}}</td>
                	<td>{{s.adminUserId}}</td>
                	<td>{{s.createDateTime | date:'yyyy-MM-dd'}}</td>
                	<td>{{s.modifyDateTime | date:'yyyy-MM-dd'}}</td> 	
                    <td><span class="ny" ng-click="EditDialog(s)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span>
  					<span class="ny" ng-click="DeleteDialog(s)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
	<!-- 删除 -->
 	   <div id="deleteContent" style="display: none;text-align: center;">
            <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
            <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
                ng-click="deletesuggestion()"
            >确认</button>
        </div>
 
	<!-- 添加测量建议 -->
     <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix" >

        </section>
          
         <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
               参数名称：
            </span>
             <select id="suggestion_healthPackageParamId" >
                <option  ng-repeat="h in healthParam" ng-value='h.id'>{{h.name_cn}}</option>
            </select>
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip" style="vertical-align: top">
                描述文字：
            </span>
            <textarea id="suggestion_description" cols="58" rows="5" placeholder="请输入描述文字" style="padding: 6px;resize: none;border: 1px solid #ddd;"></textarea>
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                状态：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="suggestion_status" class="xla_k" >
				<option value='1' >低</option>
				<option value='2' >偏低</option>
				<option value='3' >正常</option>
				<option value='4' >偏高</option>
				<option value='5' >高</option>
			</select>
        </section>
        
         <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                性别：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="suggestion_gender" class="xla_k" >
				<option value='0' >女</option>
				<option value='1' >男</option>
			</select>
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                开始年龄：
            </span>
            <input id="suggestion_startAge" class="doctor-sign Select-Options" placeholder="请输入开始年龄" >
        </section>
        
                   <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                结束年龄：
            </span>
            <input id="suggestion_endAge" class="doctor-sign Select-Options" placeholder="请输入结束年龄" >
        </section>
        
       <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                显示方式：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="suggestion_display" class="xla_k" >
				<option value='0' >隐藏</option>
				<option value='1' >显示</option>
			</select>
        </section>
       
   
        <section class="clearfix"  style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="addsuggestion()">
        </section>
    </div> 
    
	<!--编辑种类 -->
      <div id="orderCenterPopup2" class="orderPopup ">
        <section class="clearfix">

        </section>
          
         <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
               参数名称：
            </span>
             <select id="suggestion_healthPackageParamId" ng-model="edit.healthPackageParamId">
                <option  ng-repeat="h in healthParam" ng-value='h.id'>{{h.name_cn}}</option>
            </select>
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
               描述文字：
            </span>
            <textarea style="border: 1px solid #ddd;resize: none" id="suggestion_description" cols="58" rows="6" placeholder="请输入描述文字" ng-model="edit.description"></textarea>
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                状态：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="suggestion_status" class="xla_k"ng-model="edit.status" >
				<option value='1' >低</option>
				<option value='2' >偏低</option>
				<option value='3' >正常</option>
				<option value='4' >偏高</option>
				<option value='5' >高</option>
			</select>
        </section>
        
            <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                性别：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="suggestion_gender" class="xla_k" ng-model="edit.gender" >
				<option value='0' >女</option>
				<option value='1' >男</option>
			</select>
        </section>
        
           <section  class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                开始年龄：
            </span>
            <input id="suggestion_startAge" class="doctor-sign Select-Options" placeholder="请输入开始年龄" ng-model="edit.startAge">
        </section>
        
                   <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                结束年龄：
            </span>
            <input id="suggestion_endAge" class="doctor-sign Select-Options" placeholder="请输入结束年龄" ng-model="edit.endAge">
        </section>
        
       <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                显示方式：
            </span>
            <select class="doctor-sign Select-Options"   name="select" id="suggestion_display" class="xla_k" ng-model="edit.display">
				<option value='0' >隐藏</option>
				<option value='1' >显示</option>
			</select>
        </section>
   
        <section class="clearfix"  style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="editsuggestion()">
        </section>
    </div>
    
  </div>
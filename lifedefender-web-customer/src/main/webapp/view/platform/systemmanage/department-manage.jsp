<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
.layui-layer-content {
	height:auto!important;
	padding-bottom:34px;
}
	#orderCenterPopup section span,#orderCenterPopup2 section span {
		display:inline-block;
		width:80px;
		text-align:center;
        line-height: 38px;
	}
	.orderCenterBottom > ul {
		width:100%;
		border-bottom:1px solid #ddd;
	}
	.orderCenterBottom > ul:nth-child(1){
		background:#eee;
	}
	.orderCenterBottom > ul:nth-child(1) >li{
		border-top:1px solid #ddd;
	}
	.orderCenterBottom > ul >li{
		display:inline-block;
		float:left;
		width:25%;
		line-height:42px;
		text-align:center;
		border-left:1px solid #ccc;
		/* color:deepskyblue; */
	}
	.orderCenterBottom > ul >li >ul{
		width:400%;
	}
	.departmentName {
		cursor:pointer;
		color:deepskyblue;
	}
	.departmentName:hover{
		font-weight:bold;
	}
	.orderCenterBottom > ul >li > ul > li{
		float:left;
		width:25%;
		line-height:42px;
		text-align:center;
		border-left:1px solid #ccc;
		border-top:1px solid #ddd;
	}
	.orderCenterBottom > ul >li > ul > li:nth-child(4n+1){
		border-left:none;
	}
	.layui-layer-page{width:630px!important}
	#orderCenterPopup section:nth-of-type(2){margin-left:0}
	#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
</style>
 <div class="orderCenter" ng-clock ng-controller="departmentManageController" ng-init="init()" style="padding: 16px 0 0 20px;">
     <div class="titleShow">
         <label  style="border-left:2px solid #0093ff;padding-left: 6px;margin-bottom: 10px;display: inline-block;font-size: 18px;color: #0093ff;"  class="action">
             科室管理
         </label>
         <div style="display:inline-block;">
             <button style="margin: 14px 0 8px 100px;float: none;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none;" class="search search-btn cursor-pointer" ng-click = "addDialog()">添加科室</button>
         </div>
     </div>
 	<div class="orderCenterBottom " ng-cloak>
            <ul class="clearfix">
                <li>编号</li>
                <li>科室名称</li>
                <li>编辑</li>
                <li>删除</li>
            </ul>
            <ul  class="clearfix" ng-repeat="d in Department">
                <li class="First">
                	{{$index+1}}
                	<ul class="menuContent" style="display:none">
                		<li>1234</li>
                		<li>5678</li>
                		<li>
                			<span class="ny">
                				<i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i>
               			   </span>
                		</li>
                		<li>
                			<span class="ny">
  								<i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i>
  							</span>
                		</li>
                	</ul>	
                </li>
                <li class="departmentName" ng-click="iconToggle($event)">
                	{{d.name}}
                	
                </li>
                <li>
                <span class="ny" ng-click="EditDialog(d)">
                <i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i>
                </span>
               
                </li>
  				<li>
  				<span class="ny" ng-click="DeleteDialog(d)">
  				<i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i>
  				</span>
  			
  				</li>
            </ul>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
	<!-- 删除 -->
 	   <div id="deleteContent" style="display: none;text-align: center;">
            <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
            <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
                ng-click="deleteDepartment()"
            >确认</button>
        </div>
 
	<!-- 添加科室 -->
     <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
        
        <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                父科室
            </span>
             <select class="doctor-sign Select-Options"   name="select" id="department_parentId" class="xla_k">
             	<option value="0">无</option>
				<option ng-repeat="d in Department" ng-value='d.id' >{{d.name}}</option>
   		 </select>
        </section>
        
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip" style="line-height: 36px">
                科室名称
            </span>
            <input id="department_name" class="doctor-sign Select-Options" placeholder="请输入科室名称" >
        </section>
        <section class="clearfix" style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="addDepartment()">
        </section>
    </div> 
	<!--修改科室 -->
      <div id="orderCenterPopup2" class="orderPopup ">
        <section class="clearfix">

        </section>
        
        <section  class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                父科室
            </span>
             <select class="doctor-sign Select-Options"   name="select" id="department_parentId" class="xla_k" ng-model="edit.parentId">
             	<option  value='0' >无</option>
				<option ng-repeat="d in Department" ng-value='d.id' >{{d.name}}</option>
   		 </select>
        </section>
        
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip" style="line-height: 36px">
                科室名称
            </span>
            <input id="department_name" class="doctor-sign Select-Options" placeholder="修改科室名称" ng-model="edit.name">
        </section>
       
        <section class="clearfix" style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="editDepartment()">
        </section>
    </div>
    
  </div>
  <script>
  	var departName = $('.departmentName');
  	departName.click(function(){
  		alert(1)
  	})
  	console.log(departName)
  </script>
  
  
  
  
  
  
  
  
  
  
  
  
  
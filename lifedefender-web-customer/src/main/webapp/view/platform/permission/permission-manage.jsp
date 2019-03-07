<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="right_body" ng-controller="adminPermissionController" ng-init='init()' >
   <div class="right_title right_top">
        <label class="action">
          	权限管理
        </label>
        <!-- <span class="btnSpan" style="float:right"  ng-click="delPermission()">删除权限</span>  -->
        <!-- <a class="btnSpan"  ng-href="/permission/delPermiss" style="margin-right:400px;float:right">权限编辑</a> -->
        <span class="btnSpan" style="float:right;margin-right:25%"  ng-click="openEdit()">权限编辑</span>
        <!-- <span class="btnSpan" style="float:right"  ng-click="updatePermission()">修改权限</span> -->
        <span class="btnSpan" style="float:right"  ng-click="addNew()">添加权限</span> 
         
    </div>
    <div style="margin-top:20px" >
        <table cellpadding="0" cellspacing="0" class="service_table server-ctrl">
            <tr>
            	<!-- <td>选项</td>  -->
                <td>权限名称</td>
                <td>角色ID</td>
                <td>角色名称</td>
                <td>角色备注</td>
                <td colspan="2">操作项名称</td>
                <td colspan="4">操作项管理</td>
                
            </tr>
            <tr ng-repeat='(index,r) in results'>
            	<!-- <td>
            		<input type="checkbox" value="r.name" ng-checked="itemSelected1(r.id,r.item)" ng-click="itemSelected2($event,r.id,r.name,r.item)"> 
            	</td> -->            
                <td>{{r.permissions.name}}</td>
                <td>{{r.roleId}}</td>
                <td>{{r.roleName}}</td>
                <td>{{r.roleRemark}}</td>
                <td colspan="2">{{r.permissions.item}}</td>
                <td colspan="3">
                    <!-- <img ng-click="openEdit(r.id,r.name,r.item)" src="/static/images/edit.png" alt="" style="margin-right:10px;cursor:pointer;"> -->	
  				   	<span>  
  				   		<span ng-repeat = "g in r.operationList">
  				   			<input type="checkbox"  id="g.id" name="g.operation" ng-checked="isSelected(g.operation,r.roleId,r.permission)"  ng-click="updateOperation($event,r.permissions.item,g.operation,r.roleId)"/>{{g.name}}
  				   		<span/>
  				   	</span> 
                </td>
                <td> 
                     <img id="{{r.id}}" ng-click="customPermission(r.permissions.id)" src="/static/images/open.png" alt="" style="cursor: pointer;"><span>增加<span/>&nbsp;
                     <img id="{{r.id}}" ng-click="delOperationPage(r.roleId,r.permissions.item,r.permissions.id,r.operationList)" src="/static/images/close.png" alt="" style="cursor: pointer;"><span>删除<span/>
                </td>
                <!-- <td>
                	<img id="{{r.id}}" ng-click="delPermission(r.id)" src="/static/images/del.png" alt="" style="cursor: pointer;"><span>删除<span/>
                </td> -->
            </tr>
            
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
    
    	<!-- 添加权限打开页面 -->
    <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px">
        <form id="permissionForm" method="post">
            <div class="param_set">
            	<input name="id"  type="hidden"/>
                <label class="param"><span class="warn">*</span>权限名称：</label>
                &nbsp;<input type="text" ng-model="$scope.updates.name" placeholder="请输入权限名称" name="name"/></br>
                <label class="param"><span class="warn">*</span>操作项名称：</label>
                &nbsp;<input type="text" ng-model="$scope.updates.item" placeholder="请输入功能项名称" name="item"/>
               	<!-- <label class="param"><span class="warn">*</span>权限Agent：</label>&nbsp;&nbsp;&nbsp;&nbsp;
               	<input type="radio" name="agent" value="0" id="radio0">0</radio>&nbsp;&nbsp;&nbsp;&nbsp;
               	<input type="radio" name="agent" value="1" id="radio1">1</radio> -->
            </div>
           
            
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="保存" type="button" ng-click="saveOrEdit()"/>
            </div>
        </form>
        
    </div>
    
    	<!-- 自定义操作 -->
     <div class="custom-operation" style="padding-left:30px;display: none;width: auto;min-height: 250px">
        <form id="operationForm" method="post">
            <div class="param_set" style="width:450px;  padding-top: 30px;"> 
            	
                <label class="param"><span class="warn">*</span>名称：</label>
                <input type="text" ng-model="$scope.opration.oName"  name="oName" style="width:100px" /><label class="param">
                <span class="warn">*</span>操作项：</label>
                <input type="text" ng-model="$scope.opration.operation" name="operation" style="width:100px"/>    
            </div>
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="保存" type="button" ng-click="saveOperation()"/>
            </div>
        </form>
        
    </div>
    
    	<!-- 删除操作 -->
     <div class="del-operation" style="padding-left:30px;display: none;width: auto;min-height: 250px">
        <form id="delOperationForm" method="post" >
            <div class="param_set" style="width:450;   padding-top: 30px;"> 
                <li ng-repeat = "g in list">
                	<ul>
                		<input type="checkbox"  id="g.name" name="g.operation" ng-click="pushArray(g.operation,$event)"/>{{g.name}}
                	</ul>
  				<li/>  
            </div>
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="删除" type="button" ng-click="delOperation()"/>
            </div>
        </form>
        
    </div>
    
    <!-- ------------------------------------------------------------------------------------------------------  -->
    
      <div class="openPermiss-content" style="padding-left:10px;display: none;width: 1100px; height: 680px;margin-top: 10px;margin-left:40px">
     	 <span class="btnSpan" style="margin-right:25%;float:right;margin-bottom:10px"  ng-click="updatePermission2()">修改权限</span>
     	<span class="btnSpan" style="float:right ;margin-bottom:10px"  ng-click="delPermission()">删除权限</span>     
            <table cellpadding="0" cellspacing="0" class="service_table server-ctrl" >
           	<tr style="text-align:center; margin:auto">
	                <td style="width:120px;">选项</td>
	                <td>编号</td>
	                <td>权限</td>
	                <td>权限item</td>	                
	            </tr >                 
			        <tr ng-repeat='(index,r) in results1'>
		            	<td>	            		         		 		           			
		           			<div >
		           				<input type="checkbox"  id="r.id" name="r.operation" ng-checked="itemSelected1(r.id,r.name,r.item)"  
		           				ng-click="itemSelected2($event,r.id,r.name,r.item, r.hasAgent)"/>
		           			</div>			            	
			            </td>
			            <td>
			            	{{r.id}}
			            </td>
			            <td>
			            	{{r.name}}
			            </td>
			            <td>
			            	{{r.item}}
			            </td>
			     </tr>		           			 
           </table>
           
           <div id="page1" style="text-align: center; margin-top: 20px"></div>  
   </div>
    
  <div class="dialog-content2" style="padding-left:30px;display: none;width: auto;min-height: 250px">
        <form id="permissionForm2" method="post">
            <div class="param_set">
            	<input name="id1"  type="hidden"/>
                <label class="param"><span class="warn">*</span>权限名称：</label>
                <input type="text" ng-model="$scope.updates.name1" placeholder="请输入权限名称" name="name1"/></br>
                <label class="param"><span class="warn">*</span>操作项名称：</label>
                <input type="text" ng-model="$scope.updates.ite" placeholder="请输入功能项名称" name="item1"/> 
                <!-- <label class="param"><span class="warn">*</span>权限Agent：</label>&nbsp;&nbsp;&nbsp;&nbsp;
               	<input type="radio" name="agent1" value="0" id="radio3">0</radio>&nbsp;&nbsp;&nbsp;&nbsp;
               	<input type="radio" name="agent1" value="1" id="radio4">1</radio>  -->            
            </div>         
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="保存" type="button" ng-click="saveOrEdit2()"/>
            </div>
        </form>
        
    </div> 
    
</div>


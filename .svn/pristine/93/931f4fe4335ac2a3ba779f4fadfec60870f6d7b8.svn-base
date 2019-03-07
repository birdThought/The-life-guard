<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="right_body" ng-controller="characterController" ng-init='init()'>
    <div class="right_title right_top">
        <label class="action">
            角色管理
        </label>
        <span class="btnSpan" style="margin-right:400px;float:right"  ng-click="addNew()">添加角色</span>
    </div>
    <div style="margin-top:20px;width:645;height:1570;">
        <table cellpadding="0" cellspacing="0" class="service_table server-ctrl">
            <tr>
                <td>编号</td>
                <td>角色名称</td>
                <td>备注</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat='r in results'>
                <td>{{r.id}}</td>
                <td>{{r.name}}</td>
                <td>{{r.remark}}</td>
                <td>
                    <img ng-click="openEdit(r.id,r.name,r.remark)" src="/static/images/edit.png" alt="" style="margin-right:10px;cursor:pointer;">
                    <a ng-click='openMenu(r.id)' style="margin-right: 10px">菜单设置</a>
                   
                    <img id="{{r.id}}" ng-click="deleteRole(r.id)" src="/static/images/del.png" alt="" style="cursor: pointer;">

                </td>
            </tr>
           
        </table>
        
        	<div id="page" style="text-align: center; margin-top: 20px"></div>
        
         
    </div>
    
    <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px;>
        <form id="roleForm" method="post">
            <div class="param_set">
                <label class="param"><span class="warn">*</span>角色名称：</label>
                <input type="text" ng-model="$scope.updates.name" placeholder="请输入角色名称" name="name"/><br/>
                <label class="param"><span class="warn">*</span>角色备注：</label>
                <input type="text" ng-model="$scope.updates.remark" placeholder="请输入备注" name="remark"/>
                <input name="id" value="" hidden/>
            </div>
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="保存" type="button" ng-click="saveOrEdit()"/>
            </div>
        </form>
    </div>
    
    <div class="openMenu-content" style="padding-left:30px;display: none;width: auto;min-height: 250px;margin-top: 10px;margin-left:40px">
     
            <table cellpadding="0" cellspacing="0" class="service_table server-ctrl" >
           	<tr style="text-align:center; margin:auto">
	                <td style="width:120px;">选项</td>
	                <td>编号</td>
	                <td>权限</td>
	                <td>权限item</td>
	                
	            </tr > 
                
			        <tr ng-repeat='(index,r) in results1'>
		            	<td>	            		         		 
		           			<div ng-if = 'r.isChecked == true'>
		           				<input type="checkbox"  id="r.id" name="r.operation" ng-checked="true"  
		           				ng-click="updateOperation($event,r.item)"/>
		           			</div>
		           			<div ng-if = 'r.isChecked == false'>
		           				<input type="checkbox"  id="r.id" name="r.operation" ng-checked="false"  
		           				ng-click="updateOperation($event,r.item)"/>
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
</div>




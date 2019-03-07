<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="right_body" ng-controller="adminPermissionController" ng-init='init()'>
    <form id="roleForm" method="post">
        <div class="param_set">
            <label class="param"><span class="warn">*</span>权限名称：</label>
            <input type="text" ng-model="$scope.updates.name" placeholder="请输入权限名称" name="name"/>
            <input name="id" value="" hidden/>
        </div>
        <div class="param_set">
          {{operationList == null}}
          <input type="checkbox" style="vertical-align: middle;" class="magic-checkbox check-all" id="all" ng-click="checkOperation()" ng-model = "checkAll">
          <label for="all" style="font-weight: bold;">全选</label>
          
          <!-- <template v-for='checkb of operationList'>
             <input type='checkbox' name='checkboxinput' class='input-checkbox' v-model='checkboxModel' value='{{checkb.id}}'>{{checkb.name}}
          </template> -->
          
          <ul ng-app="myApp" ng-controller="adminPermissionController">
             <li ng-repeat="checkb in operationList" on-repeat-finished-render>
                <input type='checkbox' name='checkboxinput' class='input-checkbox' v-model='checkboxModel' value='{{checkb.id}}'>{{checkb.name}}
             </li>
         </ul>
          
        </div>
        <div style="padding:10px 110px 20px;clear: both;">
            <input class="save" value="保存" type="button" ng-click="saveOrEdit()"/>
        </div>
    </form>
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="/static/css/shop/category.css">
<div class="right_body" ng-controller="goodsClassifyController" ng-init='init()'>
    <div class="right_title right_top">
        <label style="border-left:2px solid #0093ff" class="action">
            商品分类
        </label>
        <span class="btnSpan" ng-click="addnew(root)"><i style="color:deepskyblue;cursor: pointer" class="layui-icon">&#xe654;</i> 添加栏目</span>
    </div>
    <div style="margin-top:20px;width: 80%;height:auto;">
        <div class="serviceTitle" style="border: 1px solid #ddd;">
            <ul class="t-title">
                <li>类目名称</li>
                <li>标签</li>
                <li>操作</li>
            </ul>
            
        </div>
        <tree-view class="serviceBox" tree-data="root.children" addnew="addnew(parent)" modify="modify(category,index)" del="del(category, index)">
            </tree-view>
    </div>
    
    <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px">
        <form id="columnForm" method="post">
            <div class="param_set">
                <label class="param">上级类目：</label>
                <input type="text" id="pname" value="{{newCategory.parent.cName}}" disabled/>
            </div>
            <div class="param_set">
            	<label class="param">标签:</label>
            	<select ng-if="newCategory.parent.id== 0" id="parentId" class="select-btn" ng-model="newCategory.labelId"
            			  ng-options="lb.id as lb.labelName for lb in labels" onchange="setLabelName(this)">
                    <!-- <option value>请选择</option> -->
                </select>
                <input ng-if="newCategory.parent.id!= 0" type="text" id="labelName" value="{{newCategory.labelName}}" disabled/>
            </div>
            <div class="param_set">
                <label class="param"><span class="warn">*</span>类目名称：</label>
                <input type="text" id="cName" ng-model="newCategory.cName" placeholder="请输入类目名称" />
            </div>
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="保存" type="button" ng-click="saveOrEdit()"/>
            </div>
            <input type="hidden" name="id" ng-model="newCategory.id" />
        </form>
    </div>
    
</div>
<script type="text/ng-template" id="treeView.html">
		<ul class="content-ul" ng-repeat="(k,child) in treeData" data-id="{{child.id}}" ng-include="'treeItem.html'"></ul>
</script>
<script type="text/ng-template" id="treeItem.html">
		<li style="text-align: left;">
        	<span class="span-lf" style="cursor: pointer;" ng-click="expland(child,$event)">
        		<img src="/static/images/open.png" alt=""  style="cursor: pointer" ng-if="!child.expland"/>
        		<img src="/static/images/close.png" alt=""  style="cursor: pointer" ng-if="child.expland"/>
				{{child.cName}}
        	</span>
                    
        	
        </li>
        <li>{{child.labelName}}</li>
        <li>
			<i title='添加子类目' ng-click="addnew({parent:child})" style="color:green;cursor: blue" class="layui-icon">&#xe654;</i>
			<i title='修改类目' ng-click="modify({category:child,index:k})" style="color:deepskyblue;cursor: pointer" class="layui-icon">&#xe642;</i>
			<i title='删除类目' ng-click="del({category:child,index:k})" style="cursor: pointer;color:deepskyblue;" class="layui-icon">&#xe640;</i>
        </li>
		<div class="serviceBox" ng-if="!isLeaf(child)" ng-show="child.expland">
        		<ul class="content-ul" ng-repeat="(k,child) in child.children" data-id="{{child.id}}" ng-include="'treeItem.html'"></ul>   
        </div>
</script>
<script>
		
	function setLabelName(op){
		var appElement = document.querySelector("[ng-controller=goodsClassifyController]");
		var $scope = angular.element(appElement).scope();
		$scope.newCategory.labelName = $(op).find("option:selected").text()
//		console.log($scope.newCategory.labelName);
	}
</script>
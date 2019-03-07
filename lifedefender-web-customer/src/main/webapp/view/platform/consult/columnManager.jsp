<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="/static/css/consultManger/consultManger.css/">
<div class="right_body" ng-controller="columnManagerController" ng-init='init()' style="height: auto;
    background: #fff;
    padding: 24px;margin-top:24px;border: 1px solid #ddd;">
    <div class="right_title right_top">
        <label style="border-left:2px solid #0093ff" class="action">
            栏目管理
        </label>
        <span class="btnSpan" ng-click="addNew()"><i ng-click="modify(r.id,r.name)" style="color:deepskyblue;cursor: pointer" class="layui-icon">&#xe654;</i> 添加栏目</span>
    </div>
    <div style="margin-top:20px">
        <div class="serviceBox" style="border: 1px solid #ddd;">
            <ul>
                <li>栏目名称</li>
                <li>添加子栏目</li>
                <li>操作</li>
            </ul>
            <ul ng-repeat="column in columnList" data-id="{{column.id}}" ng-cloak>
                <li  style="text-align: left;text-indent: 2em">
                    <span class="test123" style="cursor: pointer" ng-click="iconToggle($event)"><img src="/static/images/open.png" alt="" style="margin-right:8px;cursor: pointer">{{column.name}}</span>
                	<div class="dataContent" style="display:none">
                		<ul ng-repeat="item in column.itemList" ng-cloak>
                 			<li>{{item.name}}</li>
                 			<li></li>
                 			<li>
                     			<i ng-click="modify(r.id,r.name)" style="color:deepskyblue;cursor: pointer" class="layui-icon">&#xe642;</i>
                     			<i ng-click="delPopout(item.id)" style="cursor: pointer;color:deepskyblue;" class="layui-icon">&#xe640;</i>
                 			</li>
             			</ul>   
                	</div>
                </li>
                <li><shiro:hasPermission name="news_column:add"><a ng-click="addNew(column.id)">新增</a></li></shiro:hasPermission>
                <li>
                    <shiro:hasPermission name="news_column:edit"><i ng-click="modify(r.id,r.name)" style="color:deepskyblue;cursor: pointer" class="layui-icon">&#xe642;</i></shiro:hasPermission>
                    <shiro:hasPermission name="news_column:del"><i ng-click="delPopout(column.id)" style="cursor: pointer;color:deepskyblue;" class="layui-icon">&#xe640;</i></shiro:hasPermission>
                </li>
            </ul>
             
        </div>
    </div>
    
    <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px">
        <form id="columnForm" method="post">
            <div class="param_set">
                <label class="param">上级栏目：</label>
                <select id="parentId" class="select-btn" ng-model="column.parentId" ng-options="column.id as column.name for column in columnList">
                    <option value="">无</option>
                </select>
            </div>
            <div class="param_set">
                <label class="param"><span class="warn">*</span>栏目名称：</label>
                <input type="text" id="name" ng-model="column.name" placeholder="请输入栏目名称" />
            </div>
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="保存" type="button" ng-click="saveOrEdit()"/>
            </div>
            <!-- 是否编辑 -->
            <input type="hidden" name="id" ng-model="column.id" />
        </form>
    </div>
    
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<style>

</style>
<div class="right_body" ng-controller="memberOfflineController" ng-init="init()">
    <div class="right_title" style="padding-top: 10px">
        <label class="action" style="color: #3a87fc;font-size: 19px">
            用户线下列表
        </label>
    </div>
    <div style="margin-top:20px">
        <div style="margin-bottom:15px;position:relative;border-bottom: 1px dashed #ddd;padding-bottom: 20px;background:#fff;">
            <span class="outdate">
                <label>真实姓名</label>
                <input type="text" placeholder="请输入真实姓名" value="" ng-model="search.realName"/>
            </span>
            <!-- <span class="outdate">
                <label>手机号码</label>
                <input type="text" placeholder="请输入手机号码" value="" ng-model="search.mobile"/>
            </span> -->
            
            <button class="search-btn" ng-click="addOffline()" style="margin-right: 90px">
                新增业务员
            </button>
            
            <button  class="search-btn" ng-click="searchdata()">
                搜索
            </button>
        </div>
        <table class="service_table" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>序列</td>
                <td>编号</td>
                <td>登录帐号</td>
                <td>真实姓名</td>
                <!-- <td>手机号码</td> -->
                <td>省</td>
                <td>市</td>
                <td>部门名称</td>
                <!-- <td>类型</td> -->
                <td>注册时间</td>
                <!-- <td width="90">操作</td> -->
                <!-- <td width="60px">性别</td> -->
                <!-- <td>年龄</td> -->
            </tr>
            <tr ng-if="results==''">
                <td colspan="8">
                    无任何记录
                </td>
            </tr>
            <tr ng-repeat="r in results">
                <td>{{r.id}}</td>
                <td>{{r.userNo}}</td>
                <td>{{r.userName}}</td>
                <td>{{r.realName}}</td>
                <!-- <td>{{r.mobile}}</td> -->
                <td>{{r.proviceName}}</td>
                <td>{{r.cityName}}</td>
                <td>{{r.orgName}}</td>
                <!-- <td>{{r.userType}}</td> -->
                <td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
                <!-- <td><a>操作</a></td> -->
            </tr>
        </table>
    </div>
    
    <div class="content-right-bottom">
        <div id="page" style="text-align: center; margin-top: 30px"></div>
    </div>
</div>

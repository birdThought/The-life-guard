<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<div class="right_body" ng-controller="memberHxController" ng-init='init()'>
    <div class="right_title titleShow">
        <label class="action" style="width: 100%">
            环信未注册列表
        </label>
        <button class="search-btn" ng-click="registAll()" style="position: relative;padding-top: 3px;margin-bottom: 5px">
            一键注册
        </button>
    </div>
    <div style="margin-top:20px">
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户名</td>
                <td>密码</td>
                <td>注册失败时间</td>
                <td>错误代码</td>
                <td>操作</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="5">
                    暂时无任何记录
                </td>
            </tr>
            <tr ng-repeat="r in results">
                <td>{{r.username}}</td>
                <td>{{r.password}}</td>
                <td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
                <td>{{r.errorCode}}</td>
                <td><a ng-click="regist(r.username)">注册</a></td>
            </tr>
        </table>
        <div class="content-right-bottom">
            <div id="page" style="text-align: center; margin-top: 30px"></div>
        </div>
    </div>
</div>

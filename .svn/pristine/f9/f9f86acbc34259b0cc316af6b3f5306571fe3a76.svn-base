<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="right_body" ng-controller="backLogController" ng-init='init()'>
    <div class="right_title">
        <label class="action">
            管理用户登录记录
        </label>
    </div>
    <div style="margin-top:20px">
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户角色</td>
                <td>用户名</td>
                <td>姓名</td>
                <td>手机号码</td>
                <td>登录IP地址</td>
                <td>登录时间</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="6">无任何记录</td>
            </tr>
            <tr ng-repeat="r in results">
                <td>{{r.adminId | adminType}}</td>
                <td>{{r.userName}}</td>
                <td>{{r.realName}}</td>
                <td>{{r.mobile}}</td>
                <td>{{r.login_ip}}</td>
                <td>{{r.login_time | date:"yyyy-MM-dd hh:mm:ss"}}</td>
            </tr>
        </table>
        <div class="content-right-bottom">
            <div id="page" style="text-align: center; margin-top: 30px"></div>
        </div>
    </div>
</div>
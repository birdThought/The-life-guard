
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/accountsecurity/account-security.css">
<div ng-controller="accountSecurityController" ng-init='init()'>
    <div class="Main-content" style="padding-top: 0;margin-top: 30px;">
        <div class="">
            <div class="c-r-t">
                <p> <i class="iconfont icon-cursor"></i>账户安全 &gt;<span style="display: inline-block;margin:0 10px" class="orderCenter">密码修改</span></p>
            </div>
        </div>
        <div class="content-right-middle">
            <form class="form-modify-password">
                <ul class="account-security">
                    <li class="remind">
                        <p>互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</p>
                    </li>
                    <li>
                        <label>原密码</label>
                        <input type="password" name="oldPassword" placeholder="请输入原密码" class="oldPassword" ng-model="oldPassword">
                    </li>
                    <li>
                        <label>新密码</label>
                        <input type="password" name="newPassword" oncopy="return false" onpaste="return false"
                               oncut="return false" placeholder="密码由6-20位任意字符组成" class="newPassword" ng-model="newPassword">
                    </li>
                    <li>
                        <label>确认新密码</label>
                        <input type="password" name="confirmPassword" oncopy="return false" onpaste="return false"
                               oncut="return false" placeholder="请再次输入密码" class="confirmPassword" ng-model="confirmPassword">
                    </li>
                    <li class="Save">
                        <a class="cursor-pointer" ng-click="updatePassword()">确定保存</a>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
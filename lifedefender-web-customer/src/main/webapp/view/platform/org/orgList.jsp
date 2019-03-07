<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<div class="right_body" ng-controller="orgListController" ng-init='init()'>
    <div class="right_title">
        <label class="action">门店列表</label>
        <shiro:hasPermission name="org:add"><a href="/index#!/org/add" class="search-btn" style="position: relative;padding-top: 3px;margin-bottom: 5px">
        门店添加</a></shiro:hasPermission>
    </div>
    <div style="margin-top:20px">
        <div style="margin-bottom:15px;position:relative">

            <div class="param_set" style="margin-bottom: 0">
                <span class="outdate" style="border: none;">
                            <label>门店</label>
                            <input type="text" placeholder="请输入门店名称" ng-model="list.orgName"/>
                        </span>

                <label class="param">地区：</label>
                <select class="select-btn" id="province" name="province" ng-click="gerCity()" ng-model="list.p"
                        ng-init="list.p=''">
                    <option selected="selected"  value="">全部省份</option>
                    <option ng-repeat='r in province' value="{{r.code}}">{{r.name}}</option>
                </select>
                <select id="city" class="select-btn" ng-click="gerArea()" name="city"  ng-model="list.c"
                        ng-init="list.c=''">
                    <option selected="selected" value="">全部市</option>
                    <option ng-repeat='r in city' value="{{r.code}}">{{r.name}}</option>
                </select>
                <select class="select-btn" name="district" ng-model="list.d" ng-init="list.d=''">
                    <option selected="selected" value="">全部区县</option>
                    <option ng-repeat='r in dis' value="{{r.code}}">{{r.name}}</option>
                </select>

                <button class="search-btn" ng-click="search()">
                    搜索
                </button>
            </div>
        </div>
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td>门店</td>
                <td>类型</td>
                <td>所属地区</td>
                <td>注册时间
                    <button ng-click="sort()" class="time-sort"></button>
                </td>
                <td>联系人</td>
                <td>绑定手机号</td>
                <td width="160">操作</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="7">无相关记录</td>
            </tr>
            <tr ng-repeat="r in results">
                <td style="text-align: left;padding-left: 10px"><a ng-click="getOrgInfo(r.id)" style="color:#00b7ee">{{r.orgName}}</a>
                </td>
                <td>{{r.type | orgType}}</td>
                <td>{{r.area}}</td>
                <td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
                <td>{{r.contacts}}</td>
                <td>{{r.contactInformation}}</td>
                <td><a ng-if="r.type!='0'" ng-click="operateOrg(0,$event,r.id)"
                       ng-bind-html="r.isRecommend | recommend"></a><a ng-click="operateOrg(1,$event,r.id)"
                                                                       style="margin-left: 10px"
                                                                       ng-bind-html="r.status | orgStatus"></a>
                       <a ng-click="searchOrgUser(r.id)">查看员工</a>
                </td>
            </tr>
        </table>
        <div class="content-right-bottom">
            <div id="page" style="text-align: center; margin-top: 30px"></div>
        </div>
    </div>
    <div id="details-org" style="display: none;padding: 10px;padding-left: 20px">
        <ul style="font-size: large">
            <li>门店名称：{{orgInfo.orgName}}</li>
            <li>门店编码：{{orgInfo.orgCode}}</li>
            <li>门店服务电话: {{orgInfo.tel}}</li>
            <li>服务性质: {{orgInfo.orgType}}</li>
            <li>联系人: {{orgInfo.contacts}}</li>
            <li>联系电话：{{orgInfo.contactInformation}}</li>
            <li>结算账户类型: {{orgInfo.acountType}}</li>
            <li>详细地址: {{orgInfo.street}}</li>
            <li>法人代表: {{orgInfo.legalPerson}}</li>
            <li>法人性别：{{orgInfo.sex | sex}}</li>
            <li>法人身份证: {{orgInfo.legalPersonIdCard}}</li>
        </ul>
    </div>
    
    <div class="orgUserListDialog" style="padding-left:30px;padding-right:30px;display: none;width: auto;min-height: 550px;padding-top: 0">
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
               <td>服务师编号</td>
               <td>登录帐号</td>
               <td>姓名</td>
               <td>性别</td>
               <td>电话</td>
               <td>职位</td>
               <td>邮箱</td>
               <td>操作</td>
            </tr>
            <tr ng-if="orgUserInfo==null">
                <td colspan="5">该门店暂无服务师</td>
            </tr>
            <tr ng-repeat="u in orgUserInfo">
                <td >{{u.id}}</td>
                <td>{{u.userName}}</td>
                <td >{{u.realName}}</td>
                <td >
                    <span ng-if="u.sex == true">男</span>
                    <span ng-if="u.sex == false">女</span>
                </td>
                <td>{{u.mobile}}</td>
                <td>
                    <span ng-if="(u.openId == 1 || u.openId == 2) && u.userType == 0">管理员</span>
                    <span ng-if="(u.openId == 1 || u.openId == 2) && u.userType == 1">职员</span>
                    <span ng-if="(u.openId == 1 || u.openId == 2) && u.userType == 2">管理员&职员</span>
                    <span ng-if="u.openId == 0 && u.userType == 0 ">管理员</span>
                    <span ng-if="u.openId == 0 && u.userType == 1 ">职员</span>
                    <span ng-if="u.openId == 0 && u.userType == 2 ">管理员&职员</span>
                </td>
                <td>{{u.email}}</td>
                <td><a ng-click="restOrgUserPwd(u.id)">密码重置</a></td>
            </tr>
        </table>
    </div>
</div>

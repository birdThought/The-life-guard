<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<div class="right_body" ng-controller="memberReportController" ng-init='init()'>
    <div class="right_title titleShow">
        <label class="action">
            用户反馈
        </label>
    </div>
    <div style="margin-top:20px">
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户姓名</td>
                <td>时间</td>
                <td>反馈内容</td>
                <td>回复</td>
                <td>操作</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="4">
                    无任何记录
                </td>
            </tr>
            <tr ng-repeat="r in results" id="{{r.id}}">
                <td><a ng-click="getReportUser(r.userType,r.userId)">{{r.realName}}</a></td>
                <td>{{r.createDate | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                <td>{{r.message}}</td>
                <td>{{r.reply}}</td>
                <td>
                    <shiro:hasPermission name="user_report:edit"><img ng-click="replyReport(r.id,r.message,r.reply)" src="/static/images/edit.png" alt=""
                         style="margin-right:20px;cursor: pointer"></shiro:hasPermission>
                    <shiro:hasPermission name="user_report:del"><img  ng-click="delete(r.id)" src="/static/images/del.png" alt="" style="cursor: pointer"></td></shiro:hasPermission>
            </tr>
        </table>
        <div class="content-right-bottom">
            <div id="page" style="text-align: center; margin-top: 30px"></div>
        </div>
    </div>

    <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px;padding-top: 0">
        <form id="replyForm">
            <div class="param_set">
                <label class="param" style="vertical-align: top;">反馈信息：</label>
                <p style="display: inline-block">{{target.message}}</p>
            </div>
            <div class="param_set">
                <label class="param" style="vertical-align: top;">回复内容：</label>
                <textarea class="big-txt-area" name="reply" cols="30" rows="2" ng-model="target.reply"></textarea>
            </div>
            <div style="padding:10px 110px 20px;clear: both;">
                <input class="save" value="发送" type="button" ng-click="replyMsg()"/>
            </div>
            <input hidden name="id" value="" ng-model="target.id"/>
        </form>
    </div>
    <div id="details-member" style="width:680px;display: none;padding: 35px;padding-left: 85px">
        <table style="width: 100%;line-height: 30px;text-align: left" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户账号：{{details.userName}}</td>
                <td>用户姓名：{{details.realName}}</td>
            </tr>
            <tr>
                <td>用户类型：{{details.userType | Type}}</td>
                <td>手机号码：{{details.mobile}}</td>
            </tr>
            <tr>
                <td>性别：{{details.userRecord.gender | sex}}</td>
            </tr>
            <tr>
                <td>身高：{{details.userRecord.height}} cm</td>
            </tr>
            <tr>
                <td>生日：{{details.userRecord.birthday | date:"yyyy-MM-dd hh:mm:ss"}}</td>
            </tr>
            <tr>
                <td>腰围：{{details.userRecord.waist}} cm</td>
            </tr>
            <tr>
                <td>胸围：{{details.userRecord.bust}} cm</td>
            </tr>
            <tr>
                <td>臀围：{{details.userRecord.hip}} cm</td>
            </tr>
        </table>
    </div>
    <div id="details-orgUser" style="width:680px;display: none;padding: 35px;padding-left: 85px">
        <table style="width: 100%;line-height: 30px;text-align: left" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户账号：{{details.userName}}</td>
                <td>用户姓名：{{details.realName}}</td>
            </tr>
            <tr>
                <td>用户类型：{{details.userType | Type}}</td>
                <td>手机号码：{{details.mobile}}</td>
            </tr>
            <tr>
                <td>性别：{{details.sex | sex}}</td>
            </tr>
        </table>
    </div>
</div>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/pushManger/push-management.css">
<div ng-controller="customerPushController" ng-init='init()'>
        <div class="main-content vue-content" style="background-color: white">
            <div class="server-items clearfix">
                <p class="server-items_p clearfix">
                   <!--  <span class="small-tip">地址&nbsp; <i></i></span>
                    <select class="Select-Options" style="width: 130px" ng-change="gerCity()" id="index" ng-model="query.province" ng-options="x.name for x in provinces">
                       <option value>省份</option>
                    </select>
                    <select class="Select-Options"  style="width: 130px" ng-change="gerArea()" id="indexs"  ng-model="query.cityx" ng-options="x.name for x in citys">
                        <option value>城市</option>
                    </select>
                    <select class="Select-Options" style="width: 130px" ng-model="query.area" ng-options="x.name for x in reads">
                        <option value>地区</option>
                    </select> -->
                    
                    <span class="small-tip">性别&nbsp; <i></i></span>
                <select class='Select-Options' ng-model="query.gender">
                    <option value>不限</option>
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
                <span class="small-tip">年龄段&nbsp; <i></i></span>
                <select style="margin-right: 0;" class="Select-Options" ng-model="query.startAge">
                    <option value>不限</option>
                    <option ng-repeat="i in arr" ng-value="i">{{i}}</option>
                </select>
                <span CLASS="small-tip" style="font-weight: 500">&nbsp;至&nbsp;&nbsp;<i></i></span>
                <select class="Select-Options"  id="select_spec"  ng-model="query.endAge">
                    <option value>不限</option>
                    <option ng-repeat="i in arr" ng-value="i">{{i}}</option>
                </select>
                    <!-- <span class="small-tip">体重&nbsp; <i></i></span>
                    <select class="Select-Options" ng-model="query.weight">
                        <option value>不限</option>
                        <option ng-repeat="i in arr " ng-value = "i">{{i}}kg</option>
                    </select> -->
                    
                    <span class="small-tip">用户级别&nbsp; <i></i></span>
                    <select class="Select-Options" style="width: 98px" ng-model="query.users">
                        <option value>普通</option>
                        <option value="0">会员</option>
                    </select>
                    
                    <span class="small-tip">兴趣标签&nbsp; <i></i></span>
                    <select class="Select-Options" ng-model="query.label">
                        <option value="123">123</option>
                    </select>
                </p>
                <div style="height: 10px;"></div>
                <p class="server-items_p">
                
                    
                    <span class="small-tip">手机号码</span>
                    <input style="height: 31px" class="Select-Options"  type="text" name="userName" placeholder="搜索手机号码" ng-model="query.mobile">
                    
                    <button style="margin-left: 64px;float: none" class="search search-btn cursor-pointer" ng-click="searchStatistics(2)">查询</button>
                </p>
                <!-- <h5 class="search clearfix">
                    <i></i> -->
                    <!-- <input type="text" class="search-txt search-mobile" placeholder="搜索手机号码"
                           style="width:200px;height:30px;border: 1px solid #3ABBFF;" ng-model="query.mobile"> -->
                    <button type="button" class="excel fr send-msg cursor-pointer" ng-click="popupSendMsg()">发送消息</button>
                <!-- </h5> -->
            </div>
            <!--表格区域开始-->
            <table class="hovertable" id="table-id">
                <thead>
                <th style="width: 5">序号</th>
                <th style="width: 10">姓名</th>
                <th>性别</th>
                <th style="width: 10">年龄</th>
                <th>体重</th>
                <th>病种</th>
                <th style="width: 30">服务项目</th>
                <th>手机号码</th>
                <th>
                    <span>
                        <input type="checkbox" style="vertical-align: middle;" class="magic-checkbox check-all" id="all" ng-click="selectAll()" ng-model = "checkAll">
                        <label for="all" style="font-weight: bold;">全选</label>
                    </span>
                </th>
                </thead>
                <tfooter>
                <tr ng-repeat="(index,r) in results">
                    <td style="width: 5">{{index + 1}}</td>
                    <td style="width: 10">{{r.realName}}</td>
                    <td style="width: 10">{{r.gender | gender}}</td>
                    <td style="width: 10">{{r.age}}</td>
                    <td>{{r.weight}}</td>
                    <td>{{r.diseasesName}}</td>
                    <td style="width: 30">{{r.projectName}}</td>
                    <td>{{r.mobile}}</td>
                    <td>地址
                        <span>
                             <input type="checkbox" class="magic-checkbox userCode check-all" ng-click="clearUserCode()" ng-value="r.userId" id="r.userId" ng-model = "selectedUserId">
                            <label for="r.userId" style="font-weight: bold;"></label>
                        </span>
                    </td>
                </tr>
                </tfooter>
            </table>
            <div class="content-right-bottom">
                <div id="page" style="text-align: center; margin-top: 30px"></div>
            </div>
            <div class="alreadySend none">
                <h6 class="alreadySend-top">
                    <p>
                        <span>已发送<b>（共123封）</b></span>
                        <a href="javascript:void(0)"><i></i>删除</a>
                        <u>x</u>
                    </p>
                    <p>
                        <label>
                            <input type="checkbox" class="magic-checkbox check-all" id="message-all">
                            <label for="message-all" style="font-weight: bold;"></label>
                        </label>
                        <span>收件人</span>
                        <span>发送方式</span>
                        <span>时间</span>
                        <span>操作</span>
                    </p>
                </h6>
                <ul class="alreadySend-bottom sent-message-content">
                    <p  style="text-align: center; color: gray; font-size: 14px; margin-top: 5px;">暂无记录!</p>
                    <li><i class="leptonema"></i></li>
                    <li class="content cursor-pointer">
                        <ul>
                            <li>
                                <label style="margin-left: 12px; float: left;">
                                    <input type="checkbox" class="magic-checkbox check-all">
                                    <label style="font-weight: bold;" ></label>
                                </label>
                                <label style="max-width: 240px; overflow: hidden; text-overflow: ellipsis;
                                white-space: nowrap; display: inline-block;">
                                </label>
                            </li>
                            <li></li>
                            <li></li>
                            <li style="width: 8; padding-left: 0px; color: #10ba71;"><span class="sent-message-details-btn">查看详情</span></li>
                        </ul>
                        <i class="leptonema"></i>
                    </li>
                </ul>
            </div>
            <div id="page-container" class="page_Container" style = "background-color: white; width: 100; text-align: center;"></div>
            <div id="page-container-1" class="page_Container" style = "background-color: white; width: 100; text-align: center;"></div>
            <!--表格区域结束-->
            <!--推送方式-->
            <section class="propelling-type push-type none">
                <span class="fr">
                    <img ng-click="closeSendMsgDialog()" src="/static/images/close.png" alt="">
                </span>
                <p>选择推送方式</p>
                <div class="propelling-type-bottom">
                    <shiro:hasPermission name="push:sms"><div class="cursor-pointer" ng-click="popupSmsMsgDialog()">
                        <img src="/static/images/org-sms-push.png" alt="">
                        <h6>短信推送</h6>
                    </div></shiro:hasPermission>
                    <shiro:hasPermission name="push:app"><div class="cursor-pointer" ng-click="popupAppMsgDialog()">
                        <img src="/static/images/org-app-push.png" alt="">
                        <h6>app推送</h6>
                    </div></shiro:hasPermission>
                </div>
            </section>
            <!--/推送方式-->
            <section class="propelling-type push-app none" id="propelling_type">
                <span class="fr"><img ng-click="closeSendMsgDialog()" src="/static/images/close.png" alt=""></span>
                <p>APP推送</p>
                <div class="propelling-type-bottom" id="propelling_type_bottom">
                    <div class="recipient">
                        <font style="color: #000; font-size: 17px;">收件人 :</font>
                        <ul class="recipients">
                            <li ng-repeat="(index,i) in allUserData">
                                <span ng-repeat="(index,s) in selectedUserId" ng-if="i.userId == s ? true:false">
                                    {{i.realName}}
                                    &nbsp;;
                                </span>
                            </li>
                        </ul>
                    </div>
                    
                    <div class="msgTitle" style="padding:20px 20px 20px 2px;width:630px">
                        <span class="edit">标题： &nbsp; &nbsp; &nbsp; &nbsp; </span>
                        <input style="width: 500px" class="message-title" name="" type="text" placeholder="请输入标题" v-model = "appMsg.title" ng-model="appMsg.title">
                    </div>
                    
                    <div style="width: 270px">
                        <span class="edit">消息类型： &nbsp; </span>
                        <select class="openType" v-model = "appMsg.openType" ng-model="appMsg.openType" style="width: 140px" ng-change="openTypeMethod()">
                           <option value="1">纯文本</option>
                           <option value="2">打开指定app页面</option>
                           <option value="3">打开指定URL</option>
                        </select>
                    </div>
                    
                   <div class="msgUrl" style="padding-top: 15px;width:610px;">
                        <span class="edit">设置URL： &nbsp; </span>
                        <input style="width: 500px" type="text" placeholder="请输入链接" v-model = "appMsg.paramUrl" ng-model="appMsg.paramUrl">
                    </div>
                    <div class="service-project" style="padding-top: 15px;width: 630px;">
                        <span class="edit">页面类型： &nbsp; </span>
                        <select v-model = "appMsg.appType" ng-model="appMsg.appType">
                           <option value="RECOMMEND_EMPLOYEE">向用户推广服务师</option>
                        </select>

                       <div style="padding-top: 15px;padding-bottom: 15px">
                           <span class="edit">服务师： &nbsp; &nbsp; &nbsp; </span>
                           <input style="width: 80px;" type="text" placeholder="" v-model = "appMsg.serviceUser" ng-model="appMsg.serviceUser">
                           
                           <button style="width: 60px;" ng-click = "searchService()">查询</button>
                           
                           <span class="edit"></span>
                           <select class="serviceUserInfo" id="serviceUserInfo" v-model="appMsg.serviceUserInfo" ng-model="appMsg.serviceUserInfo" ng-options="x.realName for x in orgUserList">
                            <!-- <option ng-options = "o in orgUserList" :value = "o.id">{{o.realName-o.mobile}}</option> -->
                           </select>
                       </div>
                    </div> 
                    
                    <div class="clearfix" style="width: 600px;padding-top: 15px">
                        <span class="edit">消息编辑：</span>
                        <textarea class="message-content" spellcheck="false" name="" id="" cols="30" rows="10" ng-model="appMsg.message" placeholder="请输入消息内容..."></textarea>
                        <h6>最多<span>100</span>字,已输入<span>{{appMsg.message.length}}</span>字</h6>
                    </div>
                  <%--  <div style="width: 100;" class="clearfix">
                        <span class="edit">添加图片：</span>
                        <font class="sl-custom-file">
                            <input type="button" value="" class="btn-file"/>
                            <input type="file" class="ui-input-file"/>
                            <i class="upload-icon"></i>
                        </font>
                    </div>--%>
                    <div style="clear: both;"></div>
                    <div style="height:30px;width: 100;margin-top:3px;">
                       <%-- <span style="font-size: 17px;color: #333;">跳转页面：</span> <label id="lblSelect">
                        <select id="selectPointOfInterest" title="Select points of interest nearby">
                            <option>线下针灸服务</option>
                        </select>
                    </label>--%>
                        <button class="AppSend" ng-click="appSendMsg()">发送</button>
                    </div>
            </section>
            <section class="propelling-type push-sms none" id="propelling_type" style="height: 410px;">
                <span class="fr">
                    <img ng-click="closeSendMsgDialog()" src="/static/images/close.png" alt="">
                </span>
                <p>短信推送</p>
                <div class="propelling-type-bottom" id="propelling_type_bottom">
                    <div class="recipient" style="width: 600px;">
                        <font style="color: #000; font-size: 14px; margin-left: -9px;">收件人 :</font>
                        <ul class="recipients">
                            <li ng-repeat="(index,i) in allUserData">
                                <span ng-repeat="(index,s) in selectedUserId" ng-if="i.userId == s ? true:false ">
                                    <%--{{i.realName || i.userName}}--%>
                                    {{i.realName}}
                                    &nbsp;;
                                </span>
                            </li>
                        </ul>
                        <div style="float: right; text-align: right; margin-right: 31px; border: none;">
                            <label class="sms-template-btn" ng-click="popupSmsTemplateDialog()">选择短信模板</label>
                        </div>
                    </div>
                    <div class="clearfix" style="width: 600px;">
                        <span class="message-edit" style="color: #000">消息编辑：</span>
                        <textarea class="sms-message-content" spellcheck="false" cols="30" rows="10" ng-model="smsMsg.message" placeholder="请输入消息内容..."></textarea>
                       <%-- <div class="sms-message-content" style="resize: none;
                                    width: 500px;
                                    height: 110px;
                                    float: right;
                                    padding: 8px;
                                    border: 1px solid #9a9a9a;
                                    line-height: 24px;"
                             ng-html="smsMsg.message">
                        </div>--%>
                        <h6>最多<span>70</span>字,已输入<span>{{smsMsg.message.length}}</span>字</h6>
                    </div>
                    <input class="send AppSend" type="button" ng-click="sendSmsMsg()" value="发送">
                </div>
            </section>

            <div class="wicket sent-message-details none">
                <p class="wicket-top"></p>
                <div class="wicket-bottom"></div>
                <p class="SendTime">发送时间 </p>
                <div class="recipient" style="margin-top: 22px;">
                    <font>收件人 :</font>
                    <ul class="recipients">
                        <li>
                        
                        </li>
                    </ul>
                </div>
                <p class="Sendtype">发送方式 :
                    <span></span>
                </p>
                <p class="message">消息 ：
                    <textarea spellcheck="false" style="resize: none" id="message-area" cols="30" rows="10" readonly></textarea>
                </p>
            </div>
        </div>

    <%--短信模板--%>
   <section class="propelling-type push-sms-template none" id="propelling_type" style="height: 410px;">
                <span class="fr">
                    <img ng-click = "closeLay2()" src="/static/images/close.png" alt="">
                </span>
        <p>选择模板</p>
        <div class="" id="xzc" style="padding: 30px;">
            <form class="layui-form">
                <div class="layui-form-item">
                    <label style="font-size: 16px; color: #858585;">模板分类：</label>
                    <div class="layui-input-block" style="width: 150px; margin-top: -32px; margin-left: 88px;">
                        <select class="select-template" lay-filter="select-template">
                            <option value="" selected>请选择模板类型</option>
                            <option value="服务到期提醒">服务到期提醒</option>
                            <option value="优惠提醒">优惠提醒</option>
                            <option value="通知提醒">通知提醒</option>
                            <option value="咨询信息">咨询信息</option>
                            <option value="节日关怀信息">节日关怀信息</option>
                        </select>
                    </div>
                </div>
            </form>

            <table class="layui-table layui-table-template" lay-size="sm">
                <thead>
                <tr style="height: 31px;">
                    <th >内容</th>
                    <th style="width: 55px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="(d,index) in smsTemplate.currentData">
                  <td>{{index}}</td>
                    <td style="width: 55px;">
                        <input type="radio" ng-value="d" id="inde"  ng-model="smsTemplate.selectedId">
                        <label for="inde" style="font-weight: bold; margin-left: 10px; padding-left: 0"></label>
                    </td>
                </tr>
                </tbody>
            </table>
            <h3 style="position: absolute; top: 360px; left: 275px;">
                <button style="color: #fff;
                                text-align: center;
                                display: inline-block;
                                width: 82px;
                                height: 30px;
                                background-color: #10BA71;
                                line-height: 30px;
                                border-radius: 4px;
                                border: 1px solid #10bb71;
                                cursor: pointer;
                                margin-left: 30px;" ng-click="confirmSmsTemplate()">确定
                </button>
            </h3>

        </div>
    </section>
</div>

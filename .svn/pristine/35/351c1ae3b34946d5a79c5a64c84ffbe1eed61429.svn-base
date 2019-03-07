<%--
 * 推送管理
 * date: 2017/8/21 15:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui2.1.2,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-push-manage.css?v=2.4.0">
    <link rel="stylesheet" href="/static/plugins/layui/v2.1.2/css/layui.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/velocity/1.2.3/velocity.min.js"></script>
    <title>推送管理</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white">

            <div class="server-items">
                <p class="server-items_p">
                    <span>服务项目&nbsp; : &nbsp;<%--<i></i>--%></span>
                    <select style="width: 130px" v-model = "search.projectCode">
                        <option value="-1">全部</option>
                        <option v-for = "p in project" :value = "p.projectCode">{{p.name | subStr(1)}}</option>
                    </select>
                    <span>病种 &nbsp;:&nbsp; <%--<i></i>--%></span>
                    <select style="width: 98px" v-model = "search.diseasesId">
                        <option value="-1">不限</option>
                        <option v-for = "d in diseases" :value = "d.id">{{d.name}}</option>
                    </select>
                    <span>性别 &nbsp;:&nbsp; <%--<i></i>--%></span>
                    <select v-model = "search.gender">
                        <option value="-1">不限</option>
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                    <span>年龄段 &nbsp;:&nbsp;<%-- <i></i>--%></span>
                    <select class="select-spec" v-model = "search.startAge">
                        <option value="-1">不限</option>
                        <option v-for = "i in 100" :value = "i">{{i}}</option>
                    </select>
                    <span style="font-weight: 500">&nbsp;&nbsp;至&nbsp;&nbsp;<%--<i></i>--%></span>
                    <select  id="select_spec" class="select-spec" v-model = "search.endAge">
                        <option value="-1">不限</option>
                        <option v-for = "i in 100" :value = "i">{{i}}</option>
                    </select>
                    <button class="search search-btn cursor-pointer" @click = "searchStatistics(2)">查询</button>
                </p>
                <span class="sent-message-btn" @click="lookSentMsg($event)">已发送信箱</span>
                <h5 class="search clearfix">
                    <i></i>
                    <input type="text" class="search-txt search-mobile" placeholder="搜索手机号码"
                           style="width:200px;height:30px;border: 1px solid #10BA71;" v-model = "search.mobile">
                    <button type="button" class="excel fr send-msg cursor-pointer" @click = "popupSendMsgDialog()">发送消息</button>
                </h5>
            </div>
            <!--表格区域开始-->
            <table class="hovertable" id="table-id">
                <thead>
                <th style="width: 5%">序号</th>
                <th style="width: 10%">用户名</th>
                <th style="width: 10%">真实姓名</th>
                <th>性别</th>
                <th style="width: 10%">年龄</th>
                <th>病种</th>
                <th style="width: 30%">服务项目</th>
                <th>手机号码</th>
                <th>
                    <span>
                        <input type="checkbox" class="magic-checkbox check-all" id="all"  v-model = "checkAll"> <!-- @click ="selectAll()" -->
                        <label for="all" style="font-weight: bold;">全选</label>
                    </span>
                </th>
                </thead>
                <tbody is="transition-group" name="staggered-fade"
                       v-bind:css="false"
                       v-on:before-enter="beforeEnter"
                       v-on:enter="enter"
                       v-on:leave="leave"

                >
                <tr v-for = "(r, index) in results.data" v-bind:key = "index" v-bind:data-index="index" v-cloak>
                    <td style="width: 5%">{{index + (results.nowPage - 1) * pageSize}}</td>
                    <td style="width: 10%">{{r.userName}}</td>
                    <td style="width: 10%">{{r.realName | isNone(1)}}</td>
                    <td style="width: 10%">{{r.gender | gender(1)}}</td>
                    <td>{{r.age}}</td>
                    <td>{{r.diseasesName | isNone(1)}}</td>
                    <td style="width: 30%">{{r.projectName}}</td>
                    <td>{{r.mobile | isNone(1)}}</td>
                    <td>
                        <span>
                            <input type="checkbox" name="userCode" class="magic-checkbox check-all" :value="r.userId + '_' + r.projectCode" :id="r.userId + '_' + r.projectCode" v-model = "selectedUserId">
                            <label :for="r.userId + '_' + r.projectCode" style="font-weight: bold;"></label>
                        </span>
                    </td>
                </tr>
                </tbody>
            </table>
            <%--已发送信箱列表--%>
            <div class="alreadySend none">
                <h6 class="alreadySend-top">
                    <p>
                        <span>已发送<b>（共{{sentTotalSize}}封）</b></span>
                        <a href="javascript:void(0)" @click = "deleteSentMsg()"><i></i>删除</a>
                        <u @click="closeSentMsg($event)">x</u>
                    </p>
                    <p>
                        <label>
                            <input type="checkbox" class="magic-checkbox check-all" id="message-all" v-model = "sentCheckAll">
                            <label for="message-all" style="font-weight: bold;"></label>
                        </label>
                        <span>收件人</span>
                        <span>发送方式</span>
                        <span>时间</span>
                        <span>操作</span>
                    </p>
                </h6>
                <ul class="alreadySend-bottom sent-message-content">
                    <p v-if = "sent.data.totalSize == 0" style="text-align: center; color: gray; font-size: 14px; margin-top: 5px;">暂无记录!</p>
                    <li><i class="leptonema"></i></li>
                    <li class="content cursor-pointer" v-for = "d in sent.data.data" v-cloak>
                        <ul>
                            <li>
                                <label style="margin-left: 12px; float: left;">
                                    <input type="checkbox" class="magic-checkbox check-all" :value="d.id" :id="d.id" v-model = "sent.selectId"
                                           >
                                    <label :for="d.id" style="font-weight: bold;" ></label>
                                </label>
                                <label style="max-width: 240px; overflow: hidden; text-overflow: ellipsis;
                                white-space: nowrap; display: inline-block;">
                                    <template v-for="(i, index) in d.userName">
                                        {{i}}
                                        <template v-if="index < d.userName.length - 1">;</template>
                                    </template>
                                </label>
                            </li>
                            <li>{{d.sendType | sendType}}</li>
                            <li>{{d.createDate | date('yyyy-MM-dd mm:hh')}}</li>
                            <li style="width: 8%; padding-left: 0px; color: #10ba71;"><span class="sent-message-details-btn" @click = "popupMsgDetails(d)">查看详情</span></li>
                        </ul>
                        <i class="leptonema"></i>
                    </li>
                </ul>
            </div>
            <div id="page-container" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
            <%--<div id="page-container-1" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>--%>
            <!--表格区域结束-->
            <!--推送方式-->
            <section class="propelling-type push-type none">
                <span class="fr">
                    <img @click = "closeSendMsgDialog()" src="/static/platform/v2.2.0/images/close.png" alt="">
                </span>
                <p>选择推送方式</p>
                <div class="propelling-type-bottom">
                    <div class="cursor-pointer" @click = "popupSmsMsgDialog()">
                        <img src="/static/platform/v2.2.0/images/org/org-sms-push.png" alt="">
                        <h6>短信推送（收费）</h6>
                    </div>
                    <div class="cursor-pointer" @click = "popupAppMsgDialog()">
                        <img src="/static/platform/v2.2.0/images/org/org-app-push.png" alt="">
                        <h6>app推送（免费）</h6>
                    </div>
                </div>
            </section>
            <!--/推送方式-->
            <%--app推送--%>
            <section class="propelling-type push-app none" id="propelling_type">
                <span class="fr"><img @click = "closeSendMsgDialog()" src="/static/platform/v2.2.0/images/close.png" alt=""></span>
                <p>APP推送</p>
                <div class="propelling-type-bottom" id="propelling_type_bottom">
                    <div class="recipient">
                        <font style="color: #000; font-size: 17px;">收件人 :</font>
                        <ul class="recipients">
                            <li>
                                <template v-for = "(i, index) in selectedUser">
                                    {{i.realName || i.userName}}
                                    &nbsp;;
                                </template>
                            </li>
                        </ul>
                    </div>
                    <div class="msgTitle" style="padding:20px 20px 20px 2px;width:630px">
                        <span class="edit">标题： &nbsp; &nbsp; &nbsp; &nbsp; </span>
                        <input style="width: 500px" class="message-title" name="" type="text" placeholder="请输入标题" v-model = "appMsg.title">
                    </div>
                    
                    <div style="width: 270px">
                        <span class="edit">消息类型： &nbsp; </span>
                        <select class="openType" v-model = "appMsg.openType" style="width: 140px">
                           <option value="1">纯文本</option>
                           <option value="2">打开指定app页面</option>
                           <option value="3">打开指定URL</option>
                        </select>
                    </div>
                    
                   <div class="msgUrl" style="padding-top: 15px;width:610px;">
                        <span class="edit">设置URL： &nbsp; </span>
                        <input style="width: 500px" class="paramUrl" name="" type="text" placeholder="请输入链接" v-model = "appMsg.paramUrl">
                    </div>
                    
                    <div class="service-project" style="padding-top: 15px;width: 630px;">
                        <span class="edit">页面类型： &nbsp; </span>
                        <select class="appType" id="appType" v-model = "appMsg.appType">
                           <option value="RECOMMEND_EMPLOYEE">向用户推广服务师</option>
                        </select>

                       <div style="padding-top: 15px;padding-bottom: 15px">
                           <span class="edit">服务师： &nbsp; &nbsp; &nbsp; </span>
                           <input style="width: 80px;" type="text" placeholder="" v-model = "appMsg.serviceUser">
                           
                           <button style="width: 60px;" @click = "searchService">查询</button>
                           
                           <span class="edit"></span>
                           
                           <select class="serviceUserInfo" id="serviceUserInfo" v-model="serviceUserInfo">
                            <option v-for = "o in orgUserList" :value = "o.id">{{o.realName}}</option>
                           </select>
                       </div>
                    </div> 
                    
                    <div class="clearfix" style="width: 600px; padding-top: 15px">
                        <span class="edit">消息编辑：</span>
                        <textarea class="message-content" spellcheck="false" name="" id="" cols="30" rows="10" v-model = "appMsg.message" placeholder="请输入消息内容..."></textarea>
                        <h6>最多<span>100</span>字,已输入<span>{{appMsg.message.length}}</span>字</h6>
                    </div>
                    <%--<div style="width: 100%;" class="clearfix">
                        <span class="edit">添加图片：</span>
                        <font class="sl-custom-file">
                            <input type="button" value="" class="btn-file"/>
                            <input type="file" class="ui-input-file"/>
                            <i class="upload-icon"></i>
                        </font>
                    </div>--%>
                    <div style="clear: both;"></div>
                    <%--<div style="height:30px;width: 100%;margin-top:3px;">
                        <span style="font-size: 17px;color: #333;">跳转页面：</span> <label id="lblSelect">
                        <select id="selectPointOfInterest" title="Select points of interest nearby">
                            <option>线下针灸服务</option>
                        </select>
                    </label>--%>
                        <button class="AppSend" @click = "appSendMsg()">发送</button>
                    </div>
            </section>
            <%--app推送end--%>
            <%--sms推送--%>
            <section class="propelling-type push-sms none" id="propelling_type" style="height: 410px;">
                <span class="fr">
                    <img @click = "closeSendMsgDialog()" src="/static/platform/v2.2.0/images/close.png" alt="">
                </span>
                <p>短信推送</p>
                <div class="propelling-type-bottom" id="propelling_type_bottom">
                    <div class="recipient" style="width: 600px;">
                        <font style="color: #000; font-size: 14px; margin-left: -9px;">收件人 :</font>
                        <ul class="recipients" >
                            <li>
                                <template v-for = "(i, index) in selectedUser">
                                    {{i.realName != null ? i.realName : i.userName}}
                                    &nbsp;;
                                </template>
                            </li>
                        </ul>
                        <div style="float: right; text-align: right; margin-right: 31px; border: none;">
                            <label class="sms-template-btn" @click="popupSmsTemplateDialog">选择短信模板</label>
                        </div>
                    </div>
                    <div class="clearfix" style="width: 600px;">
                        <span class="message-edit" style="color: #000">消息编辑：</span>
                       <%-- <textarea class="sms-message-content" spellcheck="false" cols="30" rows="10" placeholder="请输入消息内容..."></textarea>--%>
                        <div class="sms-message-content" style="resize: none;
                                    width: 500px;
                                    height: 110px;
                                    float: right;
                                    padding: 8px;
                                    border: 1px solid #9a9a9a;
                                    line-height: 24px;"
                            v-html="smsMsg.message">
                        </div>
                        <%--<h6>最多<span>70</span>字,已输入<span>{{smsMsg.message.length}}</span>字</h6>--%>
                    </div>
                    <div class="cb"></div>
                    <p class="mechanism" style="float: left; background: none;">短信推送机制是需收费项目，每条短信<span>0.2元</span>/条</p>
                    <h5 style="float: left; font-weight: normal; font-size: 16px;">当前可用条数：
                        <span>{{smsMsg.remainCount}}</span> 条
                        <input type="button" class="pay" value="充值" @click = "popupPayOneDialog()">
                    </h5>
                    <input class="send AppSend" type="button" value="发送" @click = "sendSmsMsg()">
                </div>
            </section>
            <%--sms推送end--%>

            <%--消息详情--%>
            <div class="wicket sent-message-details none">
                <p class="wicket-top"></p>
                <div class="wicket-bottom"></div>
                <p class="SendTime">发送时间 : {{sent.currentMsg.createDate | date('yyyy-MM-dd hh:mm')}}</p>
                <div class="recipient" style="margin-top: 22px;">
                    <font>收件人 :</font>
                    <ul class="recipients">
                        <li>
                            <template v-for="(i, index) in sent.currentMsg.userName">{{i}}<template v-if="index < sent.currentMsg.userName.length - 1">;</template></template>
                        </li>
                    </ul>
                </div>
                <p class="Sendtype">发送方式 :
                    <span>{{sent.currentMsg.sendType | sendType}}</span>
                </p>
                <p class="message">消息 ：
                    <textarea spellcheck="false" style="resize: none" id="message-area" cols="30" rows="10" readonly>{{sent.currentMsg.content}}</textarea>
                </p>
            </div>
            <%--消息详情end--%>

            <%--短信充值--%>
            <section class="propelling-type propelling-type-cz sms-pay-one none" id="propelling_type">
                <span class="fr" style="margin-right:10px; margin-top:6px;">
                    <s class="close" @click = "closeSendMsgDialog()"></s>
                </span>
                <p><i class="return" @click = "popupSmsMsgDialog"></i>充值</p>
                <div class="propelling-type-bottom-cz" >
                    <div class="clearfix">
                        <span class="Cz pay-count" value = "100">100<b value = "10">售价&#65509;10元</b></span>
                        <span class="Cz pay-count" value = "200">200<b value = "20">售价&#65509;20元</b></span>
                        <span class="Cz pay-count" value = "500">500<b value = "50">售价&#65509;50元</b></span>
                        <span class="Cz pay-count" value = "800">800<b value = "80">售价&#65509;80元</b></span>
                        <span class="Cz pay-count" value = "1000">1000<b value = "100">售价&#65509;100元</b></span>
                        <span class="Cz pay-count" value = "2000">2000<b value = "200">售价&#65509;200元</b></span>
                    </div>
                    <div class="cb"></div>
                    <h5 class="mechanism" style="float: left;"/>
                        <span style="color: #A9A9A9">0.1元</span>/条,充值数量必须是100的倍数
                    </h5>
                    <input class="chongzhi" type="button" value="充值" @click = "createSmsOrder()">
                </div>
            </section>
            <%--end--%>
            <%--支付--%>
            <section class="propelling-type propelling-type-cz sms-pay-two none" id="propelling_type" style="width: 426px; height: 426px;;">
                <span class="fr" style="margin-right:10px; margin-top:6px;">
                    <s class="close" @click = "closeSendMsgDialog()"></s>
                </span>
                <p><i class="return" @click = "popupPayOneDialog"></i>充值</p>
                <div class="" style="margin-top: 80px;">
                    <div class="clearfix" style="text-align: center">
                        <label><span style="color: #10bb71">10元</span>(100条)</label><br><br>
                        <img src="/static/platform/v2.2.0/images/template-head.png"><br><br>
                        <label style="font-weight: 600;">扫一扫支付</label><br>
                        <label style="font-weight: 600;">支付宝</label>
                    </div>
                </div>
            </section>
            <%--end--%>

            <%--支付完成--%>
            <section class="propelling-type propelling-type-cz sms-pay-three none" id="propelling_type" style="width: 426px; height: 426px;;">
                <span class="fr" style="margin-right:10px; margin-top:6px;">
                    <s class="close" @click = "closeSendMsgDialog()"></s>
                </span>
                <p><%--<i class="return"></i>--%>充值</p>
                <div class="" style="margin-top: 80px;">
                    <div class="clearfix" style="text-align: center">
                        <b style="background: url(/static/platform/v2.2.0/images/org/push-sms-pay.png) no-repeat -162px -11px;
                            position: absolute; width: 70px; height: 70px; left: 41%"></b>
                        <br>
                        <h6 style="font-weight: 400; margin-top: 152px;">充值成功，<label style="color: #10bb71">3秒</label>后自动跳转到推送页面</h6>
                    </div>
                </div>
            </section>
            <%--end--%>

            <%--短信模板--%>
            <section class="propelling-type push-sms-template none" id="propelling_type" style="height: 410px;">
                <span class="fr">
                    <img @click = "closeLay2()" src="/static/platform/v2.2.0/images/close.png" alt="">
                </span>
                <p>选择模板</p>
                <div class="" id="" style="padding: 30px;">
                    <form class="layui-form">
                        <div class="layui-form-item">
                            <label style="font-size: 16px; color: #858585;">模板分类：</label>
                            <div class="layui-input-block" style="width: 150px; margin-top: -32px; margin-left: 88px;">
                                <select class="select-template" lay-filter="select-template" >
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
                            <tr v-for="(d, index) in smsTemplate.currentData">
                                <td>{{d}}</td>
                                <td style="width: 55px;">
                                    <span>
                                        <input type="checkbox" class="magic-checkbox check-all" :value="index" :id="index" v-model = "smsTemplate.selectedId">
                                        <label :for="index" style="font-weight: bold; margin-left: 10px; padding-left: 0px"></label>
                                    </span>
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
                                margin-left: 30px;" @click="confirmSmsTemplate">确定
                        </button>
                    </h3>

                </div>
            </section>
            <%--end--%>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>

<script src = "/static/common/js/pageUtils.js?v=2.2.0"></script>
<script src = "/static/common/js/dateFormat.js"></script>
<script src = "/static/plugins/layer/layer.js"></script>
<script src = "/static/platform/v2.2.0/js/org/push/push-manage.js?v=2.4.0"></script>
<script>

    layui.use(['layer', 'element', 'form', 'table'], function () {
        push.table = layui.table;
        push.form = layui.form;
        push.form.on('select(select-template)', function(data){
            push.vm.smsTemplate.currentData = push.vm.smsTemplate.data[data.value];
            /*push.table.render({
                elem: '.layui-table-template'
                ,data: push.vm.smsTemplate.currentData
                ,height: 272
                ,cols: [[ //标题栏
                    {checkbox: true, LAY_CHECKED: true} //默认全选
                    ,{field: 'username', title: '用户名', width: 120}
                ]]
                ,skin: 'row' //表格风格
                ,even: true
                ,page: true //是否显示分页
                ,limits: [5, 7, 10]
                ,limit: 5 //每页默认显示的数量
            });*/
        });
    });

    if (window.jQuery) {
        var Velocity = $.Velocity;
    }

    push.vm.project = '${project}' == '' ? [] : JSON.parse('${project}'.replace(/\\/g, '/'));
    push.vm.diseases = '${diseases}' == '' ? [] : JSON.parse('${diseases}');
    push.init();
    common.addBorder();

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(7).addClass('menu-current');
    } else {
        $('.main-nav li').eq(6).addClass('menu-current');
    }
</script>
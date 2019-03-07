<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <%--<link rel="stylesheet" href="/static/platform/v2.2.0/css/org/common-store.css">--%>
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/store-news.css">
    <link rel="stylesheet" href="/static/css/page.css">
    <title>消息中心</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" v-cloak>
            <div class="news-tab clearfix">
                <ul class="fl">
                    <li class="current cursor-pointer system-message" @click = "checked = false">系统消息
                        <i v-if = "systemUnreadCount > 0">{{systemUnreadCount > 99 ? '99+' : systemUnreadCount}}</i>
                    </li>
                    <li class="cursor-pointer serve-message" @click = "checked = false" v-if = "userType == 1 || userType == 2">服务消息
                        <i v-if = "serveUnreadCount > 0" class="serve-message-tip">{{serveUnreadCount > 99 ? '99+' : serveUnreadCount}}</i>
                    </li>
                    <li class="cursor-pointer refund-message" @click = "checked = false" v-if = "userType == 0 || userType == 2">退款消息
                        <i v-if = "refundUnreadCount > 0">{{refundUnreadCount > 99 ? '99+' : refundUnreadCount}}</i>
                    </li>

                </ul>

                <div class="fr">
                    <span class="del cursor-pointer" @click="deleteAll">删除</span>
                    <span>
                        <input type="checkbox" class="magic-checkbox check-all" id="all" v-model = "checked">
                        <label for="all">勾选全部</label>
                    </span>
                </div>
            </div>
            <div class="news-content">
                <ol>
                    <li class="clearfix" v-for="(m, index) in systemMsgData">
                        <a href="#" class="fl width-85-per" v-on:click="popupMsgDialog(index)">
                            <div class="news-img">
                                <img style="width:40px;height:40px;" src="/static/images/favicon.ico" alt="">
                            </div>
                            <div>
                                <h4 v-if="m.read" class="light message-title">{{m.title}}</h4>
                                <h4 v-if="!m.read" class="message-title">{{m.title}}</h4>
                                <p class="news-content-omit">{{m.content}}</p>
                                <span class="date">{{m.createDate | date('yyyy-MM-dd')}}</span>
                            </div>
                        </a>
                        <div class="fr replace-check">
                            <span>
                                <input type="checkbox" :value = "m.id" class="magic-checkbox system-check" :id = "m.id" v-model = "systemChecked">
                                <label :for="m.id"></label>
                            </span>
                        </div>
                    </li>
                    <li style = "margin-top: 100px; text-align: center; border: 0px"
                        v-if = "systemMsgData != null && systemMsgData.length < 1">
                        <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                        <br/><br/>
                        <p style="font-size: 18px; color: gray;">暂无消息</p>
                    </li>
                    <li>
                        <div id="systemPageManager" class="page_Container" style = "background-color: white; margin-top: 0px; margin-bottom: 0px;"></div>
                    </li>
                </ol>
                <ol class="serve-message none" v-if = "userType == 1 || userType == 2"> <%--服务师--%>
                    <li class="clearfix" v-for = "(m, index) in serveMessage" v-if = "m.length > 0" @click = "popupChatDialog(index)">
                        <a href="#" class="fl">
                            <div class="news-img">
                                <img :src = "getHead(m)" alt="">
                            </div>
                            <div>
                                <h4>{{getUserName(m)}}</h4>
                                <template v-if = "m[m.length - 1].contentType != undefined">
                                    <template v-if = "m[m.length - 1].contentType == 'img'">
                                        <p class="infor news-content-omit" >图片消息</p>
                                    </template>
                                    <template v-if = "m[m.length - 1].contentType == 'file'">
                                        <p class="infor news-content-omit">文件消息</p>
                                    </template>
                                </template>
                                <template v-else>
                                    <p class="news-content-omit" v-html = "getContent(m[m.length - 1].content)"></p>
                                </template>
                                <span class="date">{{m[m.length - 1].time | date('yyyy-MM-dd')}}</span>

                            </div>
                        </a>

                        <div class="fr replace-check">
							<span>
								<input type="checkbox" :value = "index" :id="index" class="magic-checkbox serve-check"  v-model = "serveChecked">
						    	<label :for = "index"></label>
							</span>
                            <%--<span class="del">删除</span>--%>
                        </div>
                    </li>
                    <li v-if = "isNoMessage(serveMessage)" class="serve-message-no"  style = "margin-top: 100px; text-align: center;">
                        <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                        <br/><br/>
                        <p style="font-size: 18px; color: gray;">暂无消息</p>
                    </li>
                </ol>
                <ol class="refund-message none" v-if = "userType == 0 || userType == 2">
                    <li class="clearfix" v-for="(m, index) in refundMsgData">
                        <a href="#" class="fl width-85-per">
                            <div>
                                <h2 v-if="m.read == 0" class="red refund-title">退款处理通知</h2>
                                <h4 v-else class="refund-title">退款处理通知</h4>
                                <span class="date">{{m.createDate | date('yyyy-MM-dd')}}</span>
                                <p class="refund-order-number">订单号：{{m.orderNumber}}</p>
                                <span>用户：{{m.realName}}申请退款</span><br><br>
                                <p class="refund-do" style="color: #10bb71; font-size: 14px; width: 113px;" v-if="m.read == 0" @click = "popupRefundDialog(m.orderNumber, m)">点击处理退款事项</p>
                                <p style="color: black; font-size: 14px;" v-else @click = "popupRefundDialog(m.orderNumber, m)">点击查看退款事项</p>
                            </div>
                        </a>
                        <div class="fr replace-check">
                            <span>
                                <input type="checkbox" :value = "m.id" class="magic-checkbox refund-check" :id = "m.id" v-model = "refundChecked">
                                <label :for="m.id"></label>
                            </span>
                        </div>
                    </li>
                    <li style = "margin-top: 100px; text-align: center; border: 0px"
                        v-if = "refundUnreadCount != null && refundUnreadCount.length < 1">
                        <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                        <br/><br/>
                        <p style="font-size: 18px; color: gray;">暂无消息</p>
                    </li>
                    <li>
                        <div id="refundPageManager" class="page_Container" style = "background-color: white; margin-top: 0px; margin-bottom: 0px;"></div>
                    </li>
                </ol>
            </div>

            <div class="dialog-refund none">
                <%--<div class="tit"><h3>退款处理中心</h3></div>--%>
                <ul v-if = "currentRefund != null">
                    <li>
                        <p class="small-tip">订单详情</p>
                    </li>
                    <li>
                        <p>服务名称：</p>
                        <div>{{currentRefund.subject}}</div>
                    </li>
                    <li>
                        <p>状态：</p>
                        <div>{{currentRefund.refundStatus | status}}</div>
                    </li>
                    <li>
                        <p>订单时间：</p>
                        <div>{{currentRefund.createDate | date('yyyy-MM-dd hh:mm')}}</div>
                    </li>
                    <li>
                        <p>订单编号：</p>
                        <div>{{currentRefund.orderNumber}}</div>
                    </li>
                    <li>
                        <p>用户：</p>
                        <div>{{currentRefund.realName}}</div>
                    </li>
                    <li>
                        <p>地址：</p>
                        <div>{{currentRefund.address}}</div>
                    </li>
                    <li>
                        <p>联系手机号：</p>
                        <div>{{currentRefund.mobile}}</div>
                    </li>
                    <li>
                        <p>数量：</p>
                        <div>{{currentRefund.number}}</div>
                    </li>
                    <li>
                        <p>总价：</p>
                        <div>{{currentRefund.charge}}</div>
                    </li>
                    <li>
                        <p class="set-color">退款理由：</p>
                        <div class="set-color">{{currentRefund.cause}}</div>
                    </li>
                    <li>
                        <p class="set-color">温馨提示：</p>
                        <div>请确认该用户未在本店消费，若商家超时48小时未处理退款，将自动默认确认退款。谢谢合作</div>
                    </li>
                    <li class="refer-btn">
                        <a v-if = "currentRefund.refundStatus == 1" href="#" @click = "confirmRefund()">确认退款</a>
                        <a v-else href="#" style="background-color: #ddd">{{currentRefund.refundStatus | status}}</a>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
<%@include file="/view/platform/org/message/chat-dialog.jsp"%>
</html>
<script type="text/javascript" src="/static/platform/v2.2.0/js/org/message/store-message.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/message/chat-dialog.js?v=2.2.0"></script>
<script src="/static/common/js/dateFormat.js"></script>
<script src = "/static/common/js/pageUtils.js"></script>
<script>
    layui.use('layer');
    $('.main-nav li').eq(8).click();
    if ('${userType}' == 1) {
        $('.main-nav li').eq(1).click();
    }
    if ('${userType}' == 2) {
        $('.main-nav li').eq(7).click();
    }

    $(".main-content").css("minHeight",800);
    $(function() {
        message.vm.orgUser = ${orgUser};
        message.vm.orgType = '${orgType}';
        message.vm.userType = '${userType}';
        try {
            message.vm.systemMsg = JSON.parse('${systemMsg}'.replace(/\\/g, '/'));
        }catch (e) {

        }

        message.vm.systemUnreadCount = ${sysCount};
        message.vm.refundUnreadCount = ${orderCount};
        <%--message.vm.storePushMsgData = JSON.parse('${storePushMsg}')--%>
        //获取缓存里的未读数据
//        message.vm.serveMessage = messageCache.getUnreadMessage(message.vm.orgUser.userCode);
        message.vm.serveMessage = messageCache.getRecentMessage(message.vm.orgUser.userCode, message.vm.recentCount);
        message.init();
        /*var log = messageCache.getRecentMessage(message.vm.orgUser.userCode, 5);
        console.log('log:', log);
        console.log('log:', log.length);*/
    });
</script>
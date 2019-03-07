<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="description" content="keywords">
    <t:base type="jquery,layer"></t:base>
    <link rel="stylesheet" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/com/lifeshs/css/messageCenter.css">
    <script type="text/javascript" src="/static/plugins/hx/strophe.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/websdk-1.1.2.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/websdk.shim.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/webim.config.js"></script>
    <script type="text/javascript" src="/static/com/lifeshs/member/js/messageCenter.js"></script>
    <title>信息中心</title>
    <script>
        window.onload = function () {
            /*  ContactControl.chatWithServer({
             id: '
            {server.id}',
             hxId: '
            {server.userCode}',
             head: '
            {server.photo}',
             nick: '
            {server.realName}',
             remain: '
            {remain}'
             });*/
            HuanxinControl.account='${hxCode}';
            ContactControl.chatWithMultiServer(${servers}, ${targetServer}, '${remain}');
            HuanxinControl.init({
                account: '${hxCode}',
                password: '123456',
                headUrl: '${headUrl}',
                nick: '${nick}',
                orgServeId: '${orgServeId}'
            });
            menuSetting({});
        }
    </script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important;background: #f1f1f2;">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap" style="padding-top: 30px">
            <div class="container fr" style="margin-top: 0;background: transparent;margin-left: 20px">
                <div style="box-sizing:content-box;position:relative;border: 1px solid #ddd;width:950px;">
                    <div class="message-left">
                        <div class="search-container">
                            <div class="search-bg">
                                <input type="text" placeholder="搜索" oninput="ContactControl.searchContact(this)"/>
                            </div>
                        </div>
                        <div class="message-contact-list">
                            <div class="search-contain-list">

                            </div>
                            <div class="contact-other-contain">
                                <button onclick="ContactControl.returnToBase()" style="display:none">&#10094;</button>
                                <span>服务信息</span>
                            </div>
                            <div id="baseMessage" style="display:none">
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-item="yj">
                                    <img src="/static/images/yujing.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">预警信息</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg">嘿咻你啊啊咻你好...</span><span class="contact-item-unread">12</span></p>
                                    </div>
                                </div>
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-item="tz">
                                    <img src="/static/images/voice.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">通知信息</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg">嘿咻你啊啊咻你好...</span><span class="contact-item-unread">12</span></p>
                                    </div>
                                </div>
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-item="fws">
                                    <img src="/static/images/servInfor.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">服务信息</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg"></span><span class="contact-item-unread">12</span></p>
                                    </div>
                                </div>
                            </div>

                            <div id="fwsList">
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="123456789011">
                                    <%--<img src="/static/images/yujing.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">测试信息</span><span class="contact-item-status">在线</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg">嘿咻你啊啊咻你好...</span><span class="contact-item-unread">12</span></p>
                                    </div>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="message-right">
                        <div class="message-title" data-id="123456789011">
                            <span></span>
                        </div>
                        <div class="base-message-contain" style="display: none;">
                            <div class="base-message-item">
                                <div class="base-message-item-head">
                                    <img src="/static/images/yujing.png"/>
                                </div>
                                <div class="base-message-item-notify">
                                    <div><span class="base-message-item-notify-name">预警信息</span><span class="base-message-item-notify-time">2016-10-10</span></div>
                                    <div class="base-message-item-notify-msg">
                                        预警信息预警信息预警信息预警信息
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="chat-message">
                            <div class="chat-msg-contain">
                                <div class="chat-tips" style="display: none;">
                                    您好，本次定制服务已接通，现在您可以开始于服务师 进行沟通了。<br/>剩余服务天数: 30 天
                                </div>
                            </div>

                            <div class="chat-right-bottom">
                                <textarea class="chat-text-inp" rows="7"></textarea>
                                <div class="send-btn-contain">
                                    <input id="chat_photo_inp" type="file" onchange="HuanxinControl.sendPicture()" hidden/>
                                    <button onclick="chat_photo_inp.click()"><img src="/static/images/select_img.png"/></button>
                                    <button class="send-btn" onclick=" HuanxinControl.sendMsg()">发送</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
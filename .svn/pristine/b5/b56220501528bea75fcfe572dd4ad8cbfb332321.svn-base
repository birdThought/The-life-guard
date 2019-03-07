<%--废弃

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-message.css">
    &lt;%&ndash;<link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-news.css">&ndash;%&gt;
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
                    <li class="current cursor-pointer system-message" @click = "changeTab">系统消息<i v-if = "systemCount != null && systemCount != 0">{{systemCount}}</i></li>
                    <li class="cursor-pointer serve-message" @click = "changeTab">服务消息<i v-if = "offLineCount != null && offLineCount != 0">{{offLineCount}}</i></li>
                </ul>
                <div class="fr">
                    <span class="del cursor-pointer" @click = "deleteAll">删除</span>
                    <span>
                        <input type="checkbox" class="magic-checkbox check-all" id="all" v-model = "checked">
                        <label for="all">勾选全部</label>
                    </span>
                </div>
            </div>
            <div class="news-content">
                <ol class="system-message" >    &lt;%&ndash;全部角色都有&ndash;%&gt;
                    <li class="clearfix" v-for = "(m, index) in systemMsgData">
                        <a href="#" class="fl">
                            <div class="news-img">
                                <img style="width:40px;height:40px;" src="/static/images/favicon.ico" alt="">
                            </div>
                            <div>
                                <h4 class = "message-title" @click="popupMsgDialog(index)">{{m.title}}</h4>
                                <p>{{m.content}}</p>
                            </div>
                        </a>
                        <div class="fr replace-check">
                            <span>
                                <input type="checkbox" :value = "m.id" class="magic-checkbox system-check" :id = "m.id" v-model = "systemChecked">
                                <label :for = "m.id"></label>
                            </span>
                            &lt;%&ndash;<span class="del">删除</span>&ndash;%&gt;
                        </div>
                    </li>
                    <li style = "margin-top: 100px; text-align: center; "
                        v-if = "systemMsgData != null && systemMsgData.length < 1">
                        <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                        <br/><br/>
                        <p style="font-size: 18px; color: gray;">暂无消息</p>
                    </li>
                    <li>
                        <div id="systemPageManager" class="page_Container" style = "background-color: white; margin-top: 0px; margin-bottom: 0px;"></div>
                    </li>
                </ol>
                <ol class="serve-message" v-if = "(orgType == 1 && userType == 1) || orgType == 2"> &lt;%&ndash;服务师&ndash;%&gt;
                    <li class="clearfix" v-for = "(m, index) in serveMessage" v-if = "m.length > 0" @click = "popupChatDialog(index)">
                        <a href="#" class="fl">
                            <div class="news-img">
                                <img :src = "m[m.length - 1].head" alt="">
                            </div>
                            <div>
                                <h4>{{m[m.length - 1].from}}</h4>
                                <template v-if = "m[m.length - 1].contentType != undefined">
                                    <template v-if = "m[m.length - 1].contentType == 'img'">
                                        <p class="infor" >图片消息</p>
                                    </template>
                                    <template v-if = "m[m.length - 1].contentType == 'file'">
                                        <p class="infor">文件消息</p>
                                    </template>
                                </template>
                                <template v-else>
                                    <p v-html = "m[m.length - 1].content"></p>
                                </template>

                            </div>
                        </a>

                        <div class="fr replace-check">
							<span>
								<input type="checkbox" :value = "index" :id="index" class="magic-checkbox serve-check"  v-model = "serveChecked">
						    	<label :for = "index"></label>
							</span>
                            &lt;%&ndash;<span class="del">删除</span>&ndash;%&gt;
                        </div>
                    </li>
                    <li v-if = "isNoMessage(serveMessage)" class="serve-message-no"  style = "margin-top: 100px; text-align: center;">
                        <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                        <br/><br/>
                        <p style="font-size: 18px; color: gray;">暂无消息</p>
                    </li>


                </ol>
            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>

<%@include file="/view/platform/org/message/chat-dialog.jsp"%>

<script src="/static/platform/v2.2.0/js/org/message/org-message.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/message/chat-dialog.js?v=2.2.0"></script>
<script src="/static/common/js/dateFormat.js"></script>
<script src = "/static/common/js/pageUtils.js"></script>
<script>
    $(function() {
        message.vm.systemMsg = JSON.parse('${systemMsg}');
        message.init();
        //获取缓存里的未读数据
        message.vm.serveMessage = messageCache.getUnreadMessage($('.userCode').val());
        message.vm.orgUser = ${orgUser};
        message.vm.orgType = '${orgType}';
        message.vm.userType = '${userType}';
    });

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(1).click();
    } else {
        $('.main-nav li').eq(5).click();
    }
</script>
</html>
--%>

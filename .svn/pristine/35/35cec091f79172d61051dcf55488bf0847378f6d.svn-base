<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:base type="jquery2.11,layui,vue"></t:base>
<!DOCTYPE html>
<html>
<head>
    <meta charset=UTF-8>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-chat.css?v=2.2.0">
</head>
<script>

</script>
<body>
<div class="chat-items none vue-dialog-content" >
    <div class="chat-tit"></div>
    <div class="chat-nav">
        <div class="chat-search">
            <input type="text" placeholder="搜索" ><%-- v-model = "search" --%>
        </div>
        <ul style="height: 566px; overflow: hidden; overflow-y: auto;">
            <template v-for = "(c, index) in chatLog" v-if = "c.length > 0">
                <li v-if = "c[c.length - 1].type == 'chat'" class="chat-member-list cursor-pointer" :user-code = "getUserCode(c, false)" @click = "changeCurrent($event)">
                    <div class="fl chat-img">
                        <img :src = "getHead(c, false)" alt="">
                    </div>
                    <div class="fl chat-tip">
                        <p>{{getUserName(c) | subStr(20, true)}}</p>
                        <span class="message-tip" v-if = "unreadCount[index] > 0">{{unreadCount[index]}}</span>
                        <template v-if = "c[c.length - 1].contentType != undefined">
                            <template v-if = "c[c.length - 1].contentType == 'img'">
                                <p class="infor" >图片消息</p>
                            </template>
                            <template v-if = "c[c.length - 1].contentType == 'file'">
                                <p class="infor">文件消息</p>
                            </template>
                        </template>
                        <template v-else>
                            <p class="infor" v-html = "getContent(c)"></p>
                        </template>
                        <span>{{c[c.length - 1].time|date('hh:mm')}}</span>
                    </div>
                    <%--<i class="chat-close-btn">x</i>--%>
                </li>
                <li v-if = "c[c.length - 1].type == 'groupchat'" class="chat-group-list cursor-pointer" :user-code = "getUserCode(c, false)" @click = "changeCurrent($event)">
                    <div class="fl chat-img">
                        <img :src = "getHead(c)" alt="">
                    </div>
                    <div class="fl chat-tip">
                        <p>{{getUserName(c) | subStr(10, true)}}</p>
                        <template v-if = "c[c.length - 1].contentType != undefined">
                            <template v-if = "c[c.length - 1].contentType == 'img'">
                                <p class="infor" >图片消息</p>
                            </template>
                            <template v-if = "c[c.length - 1].contentType == 'file'">
                                <p class="infor">文件消息</p>
                            </template>
                        </template>
                        <template v-else>
                            <p class="infor" v-html = "getContent(c)"></p>
                        </template>
                        <span>{{c[c.length - 1].time|date('hh:mm')}}</span>
                    </div>
                    <%--<i class="chat-close-btn">x</i>--%>
                </li>
            </template>
        </ul>
    </div>
    <div class="chat-box">
        <template v-for = "c in chatLog" v-if = "c.length > 0">
            <div class="chat-top chat-friend" v-if = "c[c.length - 1].type == 'chat'">
                <div class="chat-title">
                    <div class="chat-other">
                        <%--<img :src = "getHead(c)" alt="">--%>
                        <span>{{getUserName(c)}}</span>

                    </div>
                </div>
                <div class="chat-main chat-item" :class = "'chat-main-' + getUserCode(c)" id="chat-main">
                    <p class = "more-message more-message-on" @click = "listMoreMessage($event)">加载更多消息</p>
                    <ul>
                        <template v-for = "(log, index) in c" v-if = "!log.speak0">
                            <template v-if="isShowTimeTip(c[index - 1], c[index], c[index + 1])">
                                <p style=" text-align: center; color: #9b9b9b; padding: 10px;">{{log.time | date('MM-dd hh:mm', 1)}}</p>
                            </template>
                            <li class="chat-yourself" v-if = "log.mine"><%--己方消息--%>
                                <div class="chat-user">
                                    <img :src = "getHead(c, true)" alt="">
                                    <cite><i><%--2017-06-15 11:34:40--%></i>{{log.userName}}</cite>
                                </div>
                                <div class="chat-text" v-if = "log.contentType != 'file'">
                                    <img v-if = "log.isSuccess == 'loading'" class="loading" src = "/static/platform/v2.2.0/images/loading.gif">
                                    <img v-if = "log.isSuccess == 'false'" class="loading" src = "/static/platform/v2.2.0/images/chat-fail.png">
                                    <span style="display: inherit; word-break: break-all; max-width: 466px;" v-html = "log.content"></span>
                                </div>
                                <div class="chat-text" v-if = "log.contentType == 'file'">

                                    <div class="file-content">
                                        <div class="file-container">
                                            <div class="file-top">
                                                <dl>
                                                    <dt style="text-overflow: ellipsis; width: 140px; overflow: hidden;">{{log.fileName}}</dt>
                                                    <%--<dd>38k</dd>--%>
                                                </dl>
                                                <div>
                                                    <img src="/static/platform/v2.2.0/images/chat-file.png" alt="" width="46" height="46">
                                                </div>
                                            </div>
                                            <p> <a :href = "log.content" style="margin-right: 17px">下载</a></p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="chat-himself" v-if = "!log.mine"><%--对方消息--%>
                                <div class="chat-user">
                                    <img :src = "getHead(c)" alt="">
                                    <cite><i><%--2017-06-15 11:34:40--%></i>{{log.userName}}</cite>
                                </div>
                                <div class="chat-text" v-if = "log.contentType != 'file'">
                                    <div v-if="log.contentType === 'img'">
                                        <a :href="log.content" target="_blank" v-html = "log.content"></a>
                                    </div>
                                    <div v-else>
                                        <span style="display: inherit; word-break: break-all; max-width: 466px;" v-html = "log.content"></span>
                                    </div>
                                </div>
                                <div class="chat-text" v-if = "log.contentType == 'file'">
                                    <div class="file-content">
                                        <div class="file-container">
                                            <div class="file-top">
                                                <dl>
                                                    <dt style="text-overflow: ellipsis; width: 140px; overflow: hidden;">{{log.fileName}}</dt>
                                                    <%--<dd>38k</dd>--%>
                                                </dl>
                                                <div>
                                                    <img src="/static/platform/v2.2.0/images/chat-file.png" alt="" width="46" height="46">
                                                </div>
                                            </div>
                                            <p> <a :href = "log.content" style="margin-right: 17px">下载</a></p>
                                        </div>
                                    </div>
                                </div>
                            </li>

                        </template>
                    </ul>
                </div>
                <div class="chat-footer">
                    <div class="chat-tool">
                        <span @click = "popupEmoji($event)"></span>
                        <input onchange="chat.file(this)" id = "message_file_member" type="file" class="none">
                        <span @click="clickBtn('1')"></span>
                        <input onchange="chat.picture(this)" type="file" class="none" :id = "'message-picture-member-' + getUserCode(c)">
                        <span @click="clickBtn('3')"></span>
                        <span @click="clickBtn('5')"></span>
                    </div>
                    <div class="input-text">
                        <textarea :class = "'txt' + getUserCode(c)"></textarea>
                    </div>
                    <div class="enter-send">
                        <a href="#" @click = "sendMessage('txt')">发送</a>
                    </div>
                </div>
            </div>
            <div class="chat-top chat-group "  v-else>
                <div class="chat-title">
                    <div class="chat-other">
                        <%--<img :src = "getHead(c)" alt="">--%>
                        <span>{{getUserName(c)}}
                            <img src = "/static/platform/v2.2.0/images/arrows.png" class="show-member" @click = "showGroupMember(c, event)">
                        </span>

                        <%--<em>成员</em>--%>
                    </div>
                </div>
                <div class="chat-main chat-item" :class = "'chat-main-' + getUserCode(c)">
                    <p class = "more-message more-message-on" @click = "listMoreMessage($event)">加载更多消息</p>
                    <ul>
                        <template v-for = "(log, index) in c" v-if = "!log.speak0">
                            <template v-if="isShowTimeTip(c[index - 1], c[index], c[index + 1])">
                                <p style=" text-align: center; color: #9b9b9b; padding: 10px;">{{log.time | date('MM-dd hh:mm')}}</p>
                            </template>
                            <li class="chat-yourself" v-if = "log.mine && !log.speak0"><%--己方消息--%>
                                <div class="chat-user">
                                    <img :src = "getHead(c, true)" alt="">
                                    <cite><i><%--2017-06-15 11:34:40--%></i>{{log.userName}}</cite>
                                </div>
                                <div class="chat-text" v-if = "log.contentType != 'file'">
                                    <img v-if = "log.isSuccess == 'loading'" class="loading" src = "/static/platform/v2.2.0/images/loading.gif">
                                    <img v-if = "log.isSuccess == 'false'" class="loading" src = "/static/platform/v2.2.0/images/chat-fail.png">
                                    <span style="display: inherit; word-break: break-all; max-width: 466px;" v-html = "log.content"></span>
                                </div>
                                <div class="chat-text" v-if = "log.contentType == 'file'">
                                    <div class="file-content">
                                        <div class="file-container">
                                            <div class="file-top">
                                                <dl>
                                                    <dt style="text-overflow: ellipsis; width: 140px; overflow: hidden;">{{log.fileName}}</dt>
                                                    <%--<dd>38k</dd>--%>
                                                </dl>
                                                <div>
                                                    <img src="/static/platform/v2.2.0/images/chat-file.png" alt="" width="46" height="46">
                                                </div>
                                            </div>
                                            <p> <a :href = "log.content" style="margin-right: 17px">下载</a></p>
                                        </div>
                                    </div>
                                </div>

                            </li>
                            <li class="chat-himself" v-if = "!log.mine && !log.speak0"><%--对方消息--%>
                                <div class="chat-user">
                                    <img :src = "log.head" alt=""><%--getHead(c)--%>
                                    <cite><i><%--2017-06-15 11:34:40--%></i>{{log.userName}}</cite>
                                </div>
                                <div class="chat-text" v-if = "log.contentType != 'file'">
                                    <span style="display: inherit; word-break: break-all; max-width: 466px;" v-html = "log.content"></span>
                                </div>
                                <div class="chat-text" v-if = "log.contentType == 'file'">
                                    <div class="file-content">
                                        <div class="file-container">
                                            <div class="file-top">
                                                <dl>
                                                    <dt style="text-overflow: ellipsis; width: 140px; overflow: hidden;">{{log.fileName}}</dt>
                                                    <%--<dd>38k</dd>--%>
                                                </dl>
                                                <div>
                                                    <img src="/static/platform/v2.2.0/images/chat-file.png" alt="" width="46" height="46">
                                                </div>
                                            </div>
                                            <p> <a :href = "log.content" style="margin-right: 17px">下载</a></p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </template>
                    </ul>
                </div>
                <div class="chat-footer">
                    <div class="chat-tool">
                        <span @click = "popupEmoji($event)"></span>
                        <input onchange="chat.file(this)" :id = "'message-file-group-' + getUserCode(c)" type="file" class="none">
                        <span @click="clickBtn('2')"></span>
                        <input onchange="chat.picture(this)" type="file" class=" none" :id = "'message-picture-group-' + getUserCode(c)">
                        <span @click="clickBtn('4')"></span>
                        <span @click="clickBtn('5')"></span>
                    </div>
                    <div class="input-text">
                        <textarea :class = "'txt' + getUserCode(c)"></textarea>
                    </div>
                    <div class="enter-send">
                        <a href="#" @click = "sendMessage('txt')">发送</a>
                    </div>
                </div>
            </div>
        </template>
    </div>
    <%--群成员窗口--%>
    <div class="layer-chat-members none">
        <div class="chat-members-content">
            <ul class="chat-member-lists">
                <li v-for = "i in lessonMember">
                    <a href="#">
                        <img v-if = "i.photo != null && i.photo != ''" :src = "i.photo" >
                        <img v-else src="/static/platform/v2.2.0/images/template-head.png" alt="">
                        <p>{{i.userName}}</p>
                        <input type="hidden" :value = "i.userCode">
                    </a>
                </li>

            </ul>
        </div>
    </div>
    <div>
        <ul class="face">
            <li data-key="[):]" class="webim-emoji-item">
                <img src="/static/images/webImFace/ee_1.png">
            </li>
            <li data-key="[:D]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_2.png"></li>
            <li data-key="[;)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_3.png"></li>
            <li data-key="[:-o]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_4.png"></li>
            <li data-key="[:p]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_5.png"></li>
            <li data-key="[(H)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_6.png"></li>
            <li data-key="[:@]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_7.png"></li>
            <li data-key="[:s]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_8.png"></li>
            <li data-key="[:$]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_9.png"></li>
            <li data-key="[:(]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_10.png"></li>
            <li data-key="[:'(]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_11.png"></li>
            <li data-key="[:|]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_12.png"></li>
            <li data-key="[(a)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_13.png"></li>
            <li data-key="[8o|]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_14.png"></li>
            <li data-key="[8-|]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_15.png"></li>
            <li data-key="[+o(]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_16.png"></li>
            <li data-key="[<o)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_17.png"></li>
            <li data-key="[|-)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_18.png"></li>
            <li data-key="[*-)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_19.png"></li>
            <li data-key="[:-#]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_20.png"></li>
            <li data-key="[:-*]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_21.png"></li>
            <li data-key="[^o)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_22.png"></li>
            <li data-key="[8-)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_23.png"></li>
            <li data-key="[(|)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_24.png"></li>
            <li data-key="[(u)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_25.png"></li>
            <li data-key="[(S)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_26.png"></li>
            <li data-key="[(*)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_27.png"></li>
            <li data-key="[(#)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_28.png"></li>
            <li data-key="[(R)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_29.png"></li>
            <li data-key="[({)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_30.png"></li>
            <li data-key="[(})]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_31.png"></li>
            <li data-key="[(k)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_32.png"></li>
            <li data-key="[(F)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_33.png"></li>
            <li data-key="[(W)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_34.png"></li>
            <li data-key="[(D)]" class="webim-emoji-item"><img
                    src="/static/images/webImFace/ee_35.png"></li>
        </ul>
    </div>
</div>
</body>
</html>
<script>
    layui.use('layer', function(){
    });

    /*$('.input-text textarea').keydown(function(e) {
        console.log('intet point one')
        e = e || window.event;
        if(e.keyCode == 13) {
            console.log('inter 监听')
            return false;
        }
    })
    $(document).keypress(function(e) {
        console.log('intet point two')
        if (e.ctrlKey && e.keyCode == 13) {
            console.log('inter ctrl 监听')
        }
    })*/


</script>

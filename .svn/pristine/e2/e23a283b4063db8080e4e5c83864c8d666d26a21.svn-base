var ChatControl = {
    chatCache: {chat: {}, unread: {}, contacts: {}},
    domContain: null,
    isShowing: false,
    init: function () {
        var data = $.localGet(HuanxinControl.account);
        if (data != null) {
            this.chatCache = JSON.parse(data);
        }
        var nowChat = {};
        for (var key in this.chatCache.contacts) {
            nowChat.id = key;
            nowChat.nick = this.chatCache.contacts[key].nick;
            HuanxinControl.dataInitChange(key, nowChat.nick);
            break;
        }
        this.createChatContain(this.chatCache, nowChat);
        this.updateUnread(false,null);//更新未读数目
    }, openChat: function (data) {//打开当前会话
        if (data['to'] == null) {
            layer.msg("你目前没有收到任何消息");
            return;
        }
        ChatControl.isShowing = true;
        var to = data['to'];
        var nick = data['nick'];
        var findTarget = $('.chat-left-contain ul li[data-id="' + to + '"]');
        if (findTarget.length != 0) {
            findTarget.remove();
            delete this.chatCache.unread[to];
        }
        var newContact = $('<li class="chat-contacts-item" data-id="' + to + '" onclick="ChatControl.changeContact(this)" >' + nick + '</li>');
        $(".chat-left-contain ul").children().length == 0 ? $(newContact).appendTo($(".chat-left-contain ul")) : $(newContact).insertBefore($(".chat-left-contain ul li:first-child"));
        $(newContact).siblings("li").removeClass("chat-action");
        $(newContact).addClass("chat-action");

        $(".contact-title").html(nick + "");
        $(".contact-title").attr("data-id", to);
        $(".chat-msg-contain").html('');
        if (this.chatCache['chat'].hasOwnProperty(to)) {
            var chatData = this.chatCache['chat'][to];
            for (var index in chatData) {
                var item = chatData[index];
                this.createMsgItem(JSON.parse(item));
            }
            //$(newContact).find($("span")).remove();//移除掉未读消息数目
        }
        this.updateUnread(false, data);
        $.localSave(HuanxinControl.account, JSON.stringify(this.chatCache));
        layer.open({
            type: 1,
            title: [' ',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['900px'],
            offset: 100,
            moveType: 1,
            scrollbar: false,
            zIndex: 9999,
            content: $(ChatControl.domContain),
            success: function () {
                $(".chat-msg-contain").scrollTop($(".chat-msg-contain")[0].scrollHeight);

            }, end: function () {
                $(".chat-left-contain ul li").each(function () {
                    var id = $(this).attr("data-id");
                    if (!ChatControl.chatCache['chat'].hasOwnProperty(id))
                        $(this).remove();
                });
                ChatControl.isShowing = false;
            }
        });
    },
    createChatContain: function (data, now) {
        var chatContain = $('<div class="chat_container"></div>');
        var left = this.createLeftContact(data.contacts, now);
        var right = this.createRightContain(data.chat, now);
        $(left).appendTo($(chatContain));
        $(right).appendTo($(chatContain));
        document.body.appendChild(chatContain[0]);
        $(chatContain).css("display", "none");
        this.domContain = $(chatContain);
        /*layer.open({
         type: 1,
         title: [' ',
         'text-align:center;font-size:16px;background:#fff;'],
         area: ['900px'],
         offset: 100,
         moveType: 1,
         scrollbar: false,
         zIndex: 9999,
         content: $(chatContain),
         success: function () {
         $(".chat-msg-contain").scrollTop($(".chat-msg-contain")[0].scrollHeight);
         ChatControl.isShowing = true;
         }, end: function () {
         $(".chat-left-contain ul li").each(function () {
         var id = $(this).attr("data-id");
         if (!ChatControl.chatCache.hasOwnProperty(id))
         $(this).remove();
         });
         ChatControl.isShowing = false;
         }
         });*/
        //$(".chat-msg-contain").scrollTop($(".chat-msg-contain")[0].scrollHeight);
    }, createLeftContact: function (data, now) {
        var leftContain = $('<div class="chat-left-contain"></div>');
        var contactsList = document.createElement("ul");

        var contactListData = new Array();
        for (var key in data) {
            var item = this.chatCache.contacts[key];
            var json = {id: key, nick: item['nick']};
            contactListData.push(json);
        }
        $(contactsList).html(this.createContactsList(contactListData, now));
        $(contactsList).appendTo($(leftContain));
        return leftContain;
    }, createContactsList: function (data, now) {
        var str = "";
        if ($.isEmptyObject(now))
            return str;
        var now = now['id'];
        var nowNick = now['nick'];
        for (var index in data) {
            var item = data[index];
            var id = item['id'];
            var name = item['nick'];
            if (id != now) {
                // var count=this.chatCache['chat'][id].length;
                var unreadCount = '';
                if (this.chatCache.unread.hasOwnProperty(id)) {//如果存在未读消息
                    unreadCount += '<span>' + this.chatCache.unread[id] + '</span>';
                }
                str += '<li class="chat-contacts-item" data-id="' + id + '" onclick="ChatControl.changeContact(this)" >' + name + unreadCount + '</li>';
            } else {
                str += '<li class="chat-contacts-item chat-action" data-id="' + id + '" onclick="ChatControl.changeContact(this)" >' + nowNick + '</li>';
                /*if (this.chatCache.unread.hasOwnProperty(id)) {//如果存在未读消息
                    // delete this.chatCache.unread[id];
                   // this.updateUnread(false, id);
                }*/
            }
        }
        return str;
    }, createRightContain: function (data, now) {
        var nick = now['nick'];//对方昵称
        var sendBtn = 'HuanxinControl.sendMsg()';//发送点击后的按钮
        var to = now['id'];//对方id
        var imgSendBtn = 'HuanxinControl.sendPicture()';//点击选择图片后的按钮
        var imgInp = 'chat_photo_inp';//隐藏的选择图片的inp
        var rightContain = $('<div class="chat-right-contain"></div>');
        var contactInfo = $('<div class="contact-title" data-id="' + to + '"></div>');
        $(contactInfo).html(nick);
        var msgContain = $('<div class="chat-msg-contain"></div>');

        if (this.chatCache['chat'].hasOwnProperty(to)) {
            var itemArray = this.chatCache['chat'][to];
            for (var index in itemArray) {
                var item = JSON.parse(itemArray[index]);
                var itemContain = this.createMsgItem(item, true);
                $(itemContain).appendTo($(msgContain));
            }
        }
        $(msgContain).scroll(function () {
            if ($(this).scrollTop() <= 0) {
                //console.log('滑动到顶端了');
            }
        });
        var chatBottom = $('<div class="chat-right-bottom"></div>');
        var tools = $('<div class="chat-tools-contain"><img src="static/images/select_img.png" class="chat-tools-item" onclick="ChatControl.openFile()"/><input id="' + imgInp + '" type="file" hidden onchange="' + imgSendBtn + '"   /></div>');
        $(tools).appendTo($(chatBottom));
        var inpContain = $(' <div><textarea class="chat-text-inp" rows="6"></textarea> <div class="send-btn-contain"><button onclick="' + sendBtn + '">发送</button></div></div>');
        $(inpContain).appendTo($(chatBottom));
        $(contactInfo).appendTo($(rightContain));
        $(msgContain).appendTo($(rightContain));
        $(chatBottom).appendTo($(rightContain));
        return rightContain;
    },
    changeContact: function (obj) {//切换联系人
        $(obj).siblings("li").removeClass("chat-action");
        $(obj).addClass("chat-action");
        var id = $(obj).attr("data-id");
        if ($(obj).find($("span")).length != 0) {//清除未读消息提示
            $(obj).find($("span")).remove();
            delete this.chatCache.unread[id];
        }
        var name = $(obj).text();
        $(".contact-title").html(name);
        $(".contact-title").attr("data-id", id);
        $(".chat-msg-contain").html('');
        if (this.chatCache['chat'].hasOwnProperty(id)) {
            var data = this.chatCache['chat'][id];
            for (var index in data) {
                var item = data[index];
                this.createMsgItem(JSON.parse(item));
            }
        }
        this.updateUnread(false, id);
        $.localSave(HuanxinControl.account, JSON.stringify(this.chatCache));
    },
    createMsgItem: function (data, isInit) {
        isInit = isInit == undefined ? false : isInit;
        if (!isInit) {
            this.updateUnread(true, data);
        }
        if (!this.isShowing)
            return;

        var to = data['to'];
        var now=$(".contact-title").attr("data-id");
        if(to!=now)//不是当前聊天对象
            return;
        var nick = data['nick'];
        var type = data['type'];//聊天类型
        var isFrom = data['isFrom'];//是否来自发送方
        var time = this.getFormateDate(data['time']);//时间
        var msg = data['msg'];//信息内容
        var headUrl = data['headUrl'];
        var lastTime = $('.chat-msg-contain .chat-item:last').attr("data-time");
        var itemContain = isFrom ? $('<div class="chat-item chat-right-item" data-time="' + time + '"></div>') : $('<div class="chat-item chat-left-item" data-time="' + time + '"></div>');
        if (lastTime != undefined) {
            if (this.checkTimeInterval(lastTime, time) > 3) {
                var timeText = $('<div class="timeline"><span data-time="' + time + '">' + time + '</span></div>');
                $(timeText).appendTo($(itemContain));
            }
        } else if ($.trim($('.chat-msg-contain').html()) == '') {
            var timeText = $('<div class="timeline"><span>' + time + '</span></div>');
            $(timeText).appendTo($(itemContain));
        }
        if (isFrom) {
            var msgId = data['msgId'];
            switch (msgId) {
                case "1"://已经发送成功
                    break;
                case "2"://发送失败
                    break;
                default://正在发送
                    var status = $('<button class="chat-status" data-status="' + msgId + '" data-id="' + to + '"><img src="static/images/loading.gif"/></button>');
                    $(status).appendTo($(itemContain));
                    break;
            }
        }
        var msgBody = '';
        switch (type) {
            case 'txt'://文本消息
                msgBody = isFrom ? $('<div class="chat-item-right-arrow">' + msg + '</div><img src="' + headUrl + '" class="chat-head chat-right-head" />') : $('<div class="chat-item-left-arrow">' + msg + '</div><img src="' + headUrl + '" class="chat-head chat-left-head" />');
                break;
            case 'img':
                msgBody = isFrom ? $('<div class="chat-item-right-arrow"><img src="' + msg + '" style="max-width:540px" /></div><img src="' + headUrl + '" class="chat-head chat-right-head" />') : $('<div class="chat-item-left-arrow"><img src="' + msg + '"  style="max-width:540px" /></div><img src="' + headUrl + '" class="chat-head chat-left-head" />');
                break;
            case 'audio':
                msgBody = isFrom ? $('<div class="chat-item-right-arrow">您接收到了一条语音消息，暂时不支持播放</div><img src="' + headUrl + '" class="chat-head chat-right-head" />') : $('<div class="chat-item-left-arrow">您接收到了一条语音消息，暂时不支持播放</div><img src="' + headUrl + '" class="chat-head chat-left-head" />');
                break;
        }
        $(msgBody).appendTo($(itemContain));
        if (isInit)
            return itemContain;
        $(itemContain).appendTo($(".chat-msg-contain"));
        $(".chat-msg-contain").scrollTop($(".chat-msg-contain")[0].scrollHeight);
        //$(".chat-msg-contain").animate({ scrollTop: $(".chat-msg-contain")[0].scrollHeight },50);
    }, handleMsg: function (data) {
        var type = data['type'];
        switch (type) {
            case 'txt':
                this.createMsgItem(data);
                this.putInCache(data);

                break;
            case 'img':
                var isFrom = data['isFrom'];
                if (!isFrom) {
                    this.createMsgItem(data);
                    this.putInCache(data);
                    return;
                }
                var imgMsg = data['msg'];
                var reader = new FileReader();
                reader.readAsDataURL(imgMsg);
                reader.onload = function (e) {
                    data['msg'] = this.result;
                    ChatControl.createMsgItem(data);
                    data['msg'] = "[图片]";
                    ChatControl.putInCache(data);
                }
                break;
            case 'audio':
                this.createMsgItem(data);
                this.putInCache(data);
                break;
        }
    }, getFormateDate: function (date) {//转换日期new Date()字符串转 yyyy-mm-dd HH:MM格式
        date = new Date(date);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        var minutes = date.getMinutes();
        minutes = minutes < 10 ? ('0' + minutes) : minutes;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minutes;
    }, parseDate: function (date) {//解析时间
        var t = Date.parse(date);
        return !isNaN(t) ? new Date(Date.parse(date.replace(/-/g, "/"))) : new Date();
    },
    checkTimeInterval: function (startDate, endDate) {//计算时间相差多少分钟
        startDate = this.parseDate(startDate);
        endDate = this.parseDate(endDate);
        var diff = endDate.getTime() - startDate.getTime();
        return Math.abs(diff / 60000);
    }, openFile: function () {
        $("#chat_photo_inp").click();
    }, putInCache: function (data) {
        var to = data['to'];
        if (this.chatCache['chat'].hasOwnProperty(to)) {
            var itemArray = this.chatCache['chat'][to];
            itemArray.push(JSON.stringify(data));
            this.chatCache['chat'][to] = itemArray;
        } else {
            var array = [];
            array.push(JSON.stringify(data));
            this.chatCache['chat'][to] = array;
            var nick = null;
            if (data['isFrom']) {
                nick = $(".contact-title").text();
            } else {
                nick = data['nick'];
            }
            this.chatCache.contacts[to] = {nick: nick};
        }
        $.localSave(HuanxinControl.account, JSON.stringify(this.chatCache));
    }, changeSendStaus: function (msgId, status, filePath) {
        var targetStatus = $('.chat-msg-contain').find($('button[data-status="' + msgId + '"]'));
        switch (status) {
            case 0://发送中

                break;
            case 1://发送成功
                var id = targetStatus.attr("data-id");
                targetStatus.remove();
                var list = this.chatCache['chat'][id];
                for (var index in list) {
                    var item = JSON.parse(list[index]);
                    if (item['msgId'] == msgId) {
                        item['msgId'] = "1";
                        if (filePath != undefined)
                            item['msg'] = filePath;
                        this.chatCache['chat'][id][index] = JSON.stringify(item);
                        break;
                    }
                }
                break;
            case 2://发送失败
                targetStatus.css("background", "#ff0000");
                var id = targetStatus.attr("data-id");
                var list = this.chatCache['chat'][id];
                for (var index in list) {
                    var item = JSON.parse(list[index]);
                    if (item['msgId'] == msgId) {
                        item['msgId'] = "2";
                        if (filePath != undefined)
                            item['msg'] = filePath;
                        this.chatCache['chat'][id][index] = JSON.stringify(item);
                        break;
                    }
                }
                break;
        }
        $.localSave(HuanxinControl.account, JSON.stringify(this.chatCache));
    }, updateUnread: function (isIncrease, data) {
        var count = 0;
        if (isIncrease) {
            var to = data['to'];
            var nick = data['nick'];
            if (to == HuanxinControl.account)
                return;
            if (this.isShowing) {
                var now = $(".contact-title").attr("data-id");
                if (now == to) {
                    //  $(".chat-left-contain ul li[data-id=\"" + to + "\"]").find($("span")).remove();
                    if (this.chatCache.unread.hasOwnProperty(to)) {
                        delete this.chatCache.unread[to];
                    }
                    return;
                }
            }
            var tLi = $(".chat-left-contain ul li[data-id=\"" + to + "\"]");
            if (tLi.length != 0) {//该联系人存在在最近联系列表
                var unread = tLi.find($("span"));
                if (unread.length != 0) {
                    var oldUr = unread.text();
                    oldUr = parseInt(oldUr);
                    oldUr = oldUr + 1;
                    unread.html(oldUr);
                    count = oldUr;
                } else {
                    var ur = $('<span></span>');
                    ur.text(1);
                    $(ur).appendTo(tLi);
                    count++;
                }
            } else {
                var newContact = $('<li class="chat-contacts-item" data-id="' + to + '" onclick="ChatControl.changeContact(this)" >' + nick + '<span>' + 1 + '</span></li>');
                $(newContact).appendTo($(".chat-left-contain ul"));
                count++;
            }
            this.chatCache.unread[to] = count;
           /* var c = $("#recent_chat").html();
            if ($.trim(c) != '') {
                count += parseInt(c);
            }*/
        }
       var unreadData=this.chatCache['unread'];
        count=0;
        for(var key in unreadData){
            count+=parseInt(unreadData[key]);
            console.log(unreadData[key])
        }
        count == 0 ? $("#recent_chat").html('') : $("#recent_chat").html(count);
    }
}


var HuanxinControl = {
    conn: null,
    nick: null,
    headUrl: null,
    orgServeId: null,
    hxError: false,
    account: null,
    dataInit: {
        to: null,
        nick: null
    },
    dataInitChange: function (id, nick) {
        this.dataInit = {
            to: id,
            nick: nick
        };
    },
    init: function (data) {
        this.conn = new WebIM.connection({
            https: WebIM.config.https,
            url: WebIM.config.xmppURL,
            isAutoLogin: WebIM.config.isAutoLogin,
            isMultiLoginSessions: WebIM.config.isMultiLoginSessions
        });
        this.conn.listen({
            onOpened: function (message) { //连接成功回调
                HuanxinControl.conn.setPresence();
                console.log("登陆成功")
            },
            onClosed: function (message) {
                console.log("连接关闭：" + message);
            }, //连接关闭回调
            onTextMessage: function (message) {
                console.log(message);
                var type = message['type'];//类型
                var from = message['from'];//发送方
                var to = message['to'];//接收方
                var msgId = message['id'];
                var msgBody = message['data'];
                msgBody = WebIM.utils.parseEmoji(msgBody);
                var data = {
                    type: 'txt',
                    isFrom: false,
                    time: message['ext']['time'],
                    msg: msgBody,
                    to: from,
                    nick: message['ext']['nick'],
                    headUrl: message['ext']['headUrl']
                }
                HuanxinControl.dataInitChange(from, message['ext']['nick']);
                // ChatControl.updateUnread(true, from);
                ChatControl.handleMsg(data);
            }, //收到文本消息
            onEmojiMessage: function (message) {
            }, //收到表情消息
            onPictureMessage: function (message) {
                console.log(message);
                console.log("收到图片");
                var json = {
                    type: 'img',
                    isFrom: false,
                    to: message['from'],
                    msg: message['url'],
                    time: message['ext']['time'],
                    nick: message['ext']['nick'],
                    headUrl: message['ext']['headUrl']
                }
                HuanxinControl.dataInitChange(message['from'], message['ext']['nick']);
                //  ChatControl.updateUnread(true, message['from']);
                ChatControl.handleMsg(json);
            }, //收到图片消息
            onCmdMessage: function (message) {
            }, //收到命令消息
            onAudioMessage: function (message) {
                var json = {
                    type: 'audio',
                    isFrom: false,
                    to: message['from'],
                    msg: message['url'],
                    time: message['ext']['time'],
                    nick: message['ext']['nick'],
                    headUrl: message['ext']['headUrl']
                }
                HuanxinControl.dataInitChange(message['from'], message['ext']['nick']);
                //  ChatControl.updateUnread(true, message['from']);
                ChatControl.handleMsg(json);
            }, //收到音频消息
            onLocationMessage: function (message) {
            },//收到位置消息
            onFileMessage: function (message) {
            }, //收到文件消息
            onVideoMessage: function (message) {
            }, //收到视频消息
            onPresence: function (message) {
            }, //收到联系人订阅请求、处理群组、聊天室被踢解散等消息
            onRoster: function (message) {
            }, //处理好友申请
            onInviteMessage: function (message) {
            }, //处理群组邀请
            onOnline: function () {
            }, //本机网络连接成功
            onOffline: function () {
            }, //本机网络掉线
            onError: function (message) {
                if (!HuanxinControl.hxError) {
                    HuanxinControl.hxError = true;
                    console.log("连接超时！请刷新页面重试");
                }
                console.log(message);
            } //失败回调
        });
        this.nick = data['nick'];
        this.headUrl = data['headUrl'];
        this.orgServeId = data['orgServeId'];
        this.login(data['account'], data['password']);
    }, login: function (account, pwd) {
        this.account = account;
        var options = {
            apiUrl: WebIM.config.apiURL,
            user: account,
            pwd: pwd,
            appKey: WebIM.config.appkey
        };
        this.conn.open(options);
    },
    sendMsg: function () {
        var text = $(".chat-text-inp").val();
        if (text == '') {
            return;
        }
        $(".chat-text-inp").val('');
        var other = $(".contact-title").attr("data-id");
        var id = this.conn.getUniqueId();//生成本地消息id
        if ($.trim(other) == '') {
            return;
        }
        var data = {
            type: 'txt',
            isFrom: true,
            time: new Date(),
            msg: text,
            to: other,
            msgId: id,
            nick: this.nick,
            headUrl: this.headUrl
        }
        ChatControl.handleMsg(data);
        var msg = new WebIM.message('txt', id);//创建文本消息
        msg.set({
            msg: text,
            to: other,
            ext: {
                time: new Date(), nick: this.nick,
                headUrl: this.headUrl, orgServeId: this.orgServeId
            },
            success: function (id, serverMsgId) {
                ChatControl.changeSendStaus(id, 1);
            }, fail: function () {
                ChatControl.changeSendStaus(id, 2);
            }
        });
        this.dataInitChange(other, $(".contact-title").text());
        this.conn.send(msg.body);
    }, sendPicture: function () {
        var id = this.conn.getUniqueId();
        var other = $(".contact-title").attr("data-id");
        var msg = new WebIM.message('img', id);
        var input = document.getElementById('chat_photo_inp');//选择图片的input
        if (!this.checkIsImg(input.files[0].name)) {
            layer.msg("请选择正确的图片格式");
            return;
        }
        var file = WebIM.utils.getFileUrl(input);
        var allowType = {
            "jpg": true,
            "gif": true,
            "png": true,
            "bmp": true
        };
        var data = {
            type: 'img',
            isFrom: true,
            time: new Date(),
            msg: input.files[0],
            to: other,
            msgId: id,
            nick: this.nick,
            headUrl: this.headUrl
        }
        ChatControl.handleMsg(data);
        var imgPath = null;
        if (file.filetype.toLowerCase() in allowType) {
            msg.set({
                apiUrl: WebIM.config.apiURL,
                file: file,
                to: other,
                ext: {
                    time: new Date(), nick: this.nick,
                    headUrl: this.headUrl,
                    orgServeId: this.orgServeId
                },
                onFileUploadError: function (error) {
                    if (imgPath != null)
                        ChatControl.changeSendStaus(id, 2, imgPath);
                },//图片上传失败
                onFileUploadComplete: function (data) {
                    //图片地址：data.uri + '/' + data.entities[0].uuid;
                    imgPath = data.uri + '/' + data.entities[0].uuid;
                },
                success: function (id, serverMsgId) {
                    if (imgPath != null)
                        ChatControl.changeSendStaus(id, 1, imgPath);
                },
                flashUpload: WebIM.flashUpload
            });
        }
        this.dataInitChange(other, $(".contact-title").text());
        this.conn.send(msg.body);
    }, checkIsImg: function (name) {
        var fileSub = name.substr(name.lastIndexOf(".") + 1,
            name.length).toLowerCase();
        if (fileSub != 'jpg' && fileSub != 'bmp' && fileSub != 'png'
            && fileSub != 'jpeg' && fileSub != 'gif') {
            return false;
        }
        return true;
    }
}
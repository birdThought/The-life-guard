var ContactControl = {
    changeContact: function (obj) {
        var target = $(obj).attr("data-item");
        if (target != undefined) {
            switch (target) {
                case 'yj'://预警通知
                    ChatControl.isShowing = false;
                    $(".base-message-contain").show();
                    $("#chat-message").hide();
                    $(".message-title").html('<span>预警信息</span>');
                    $(".message-title").removeAttr("data-id");
                    $(obj).find($(".contact-item-unread")).remove();
                    break;
                case 'tz'://系统通知
                    ChatControl.isShowing = false;
                    $(".base-message-contain").show();
                    $("#chat-message").hide();
                    $(".message-title").html('<span>系统通知</span>');
                    $(".message-title").removeAttr("data-id");
                    $(obj).find($(".contact-item-unread")).remove();
                    break;
                case 'fws'://服务师
                    $("#baseMessage").hide();
                    $('.contact-other-contain').show();
                    $("#fwsList").show();
                    break;
            }
        } else {//服务师item
            var id = $(obj).attr("data-id");
            var nick = $(obj).find($(".contact-item-nick")).attr("data-nick");
            var isGroup = $(obj).attr("is-group");
            if ($(obj).find($(".contact-item-unread")).length != 0) {
                this.updateUnread(false, obj, id);
            }
            if (!ChatControl.isShowing) {
                $(".base-message-contain").hide();
                $("#chat-message").show();
                ChatControl.changeContact(id, nick, isGroup);
                ChatControl.isShowing = true;
            } else if (!$(obj).hasClass('contact-item-action')) {
                ChatControl.changeContact(id, nick, isGroup);
            }
        }
        $(obj).siblings("div").removeClass("contact-item-action");
        $(obj).addClass("contact-item-action");
    }, returnToBase: function () {
        $('.contact-other-contain').hide();
        $("#baseMessage").show();
        $("#fwsList").hide();
    }, updateUnread: function (isIncrease, data, id) {
        if (isIncrease) {//增加未读消息数目
            var now = $('.message-title').attr('data-id');
            var from = data['to'];//对方
            this.updateRecentMsg(data);
            if (now == from) //如果是当前聊天
                return;
            var target = $('#fwsList .contact-item[data-id="' + from + '"]');
            if (target.length != 0) {//显示未读消息数目
                var unread = target.find($('.contact-item-unread'));
                if (unread.length != 0) {
                    var count = parseInt(unread.text());
                    count++;
                }
            }
            var fwsTotal = $("#baseMessage .contact-item[data-item='fws']");//更新接收消息和时间
            var c = fwsTotal.find($(".contact-item-unread"));
            if (c.length != 0) {
                var count = parseInt(c.text());
                count++;
                c.text(count);
            } else {
                var content = fwsTotal.find($("p"));
                $('<span class="contact-item-unread">1</span>').appendTo(content);
            }
        } else {
            ChatControl.putUnreadCache(false, id, 0);
            $(data).find($(".contact-item-unread")).remove();//移除未读消息数目
            var count = 0;
            $('#fwsList .contact-item').each(function () {
                var c = $(this).find('.contact-item-unread');
                if (c.length != 0) {
                    count += parseInt(c.text());
                }
            });
            var t = $("#baseMessage .contact-item[data-item='fws']");
            if (count == 0) {
                t.find($('.contact-item-unread')).remove();
            } else {
                var b = t.find($('.contact-item-unread'));
                if (b.length != 0) {
                    b.text(count);
                } else {
                    $('<span class="contact-item-unread">' + count + '</span>').appendTo(t.find($("p")));
                }
            }
        }
    }, updateRecentMsg: function (data, isInit, initCount) {
        var from = data['to'];//对方
        var type = data['type'];
        var msgBody = '';
        switch (type) {
            case 'txt':
                var msg = data['msg'];
                msgBody = msg.length > 9 ? msg.substr(0, 9) + '...' : msg;
                break;
            case 'img':
                msgBody = '[图片]';
                break;
            case 'audio':
                msgBody = '[语音消息]'
                break;
        }
        var time = ChatControl.getFormateDate(data['time']);
        var timeText = '';
        var interval = ChatControl.checkTimeInterval(ChatControl.getFormateDate(new Date()), time);
        if (interval < 1) {//今天+时间
            timeText = '今天 ' + time.split(' ')[1];
        } else if (interval < 2) {
            timeText = '昨天 ' + time.split(' ')[1];
        } else if (interval > 7) {//显示日期
            timeText = time.split(' ')[0];
        } else {//显示星期+时间
            timeText = ChatControl.getZhouDay(time) + ' ' + time.split(' ')[1];
        }
        var fwsTotal = $("#baseMessage .contact-item[data-item='fws']");//更新接收消息和时间
        fwsTotal.find($('.contact-item-recent-msg')).text(msgBody);
        fwsTotal.find($('.contact-item-time')).text(timeText);
        var target = $('#fwsList .contact-item[data-id="' + from + '"]');
        if (target.length != 0) {
            target.find($('.contact-item-recent-msg')).text(msgBody);
            target.find($('.contact-item-time')).text(timeText);
            var now = $('.message-title').attr('data-id');
            if (now != from) {
                var unread = target.find($('.contact-item-unread'));
                if (unread.length != 0) {
                    var count = parseInt(unread.text());
                    count++;
                    unread.text(count);
                    ChatControl.putUnreadCache(true, from, count);
                } else {
                    var content = target.find($("p"));
                    $('<span class="contact-item-unread">1</span>').appendTo(content);
                    ChatControl.putUnreadCache(true, from, 1);
                }
            }
        } else {//不存在则创建新的
            var headUrl = data['headUrl'];//头像
            var nick = data['nick'];//昵称
            var count = initCount == undefined ? 1 : initCount;
            var recent = $('<div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="' + from + '"><img src="' + headUrl + '" class="contact-head"><div class="contact-item-right"><span class="contact-item-nick">' + nick + '</span><span class="contact-item-status">在线</span><span class="contact-item-time">' + timeText + '</span><p><span class="contact-item-recent-msg">' + msgBody + '</span><span class="contact-item-unread">' + count + '</span></p></div></div>');
            if (isInit == undefined)//不是初始化状态不用丢进缓存
                ChatControl.putNewUnreadPeople(from, count);//把冒出来的未读人缓存
            $(recent).appendTo($("#fwsList"));
        }
    }, searchContact: function (obj) {
        var v = $.trim($(obj).val());
        setTimeout(function () {
            if (v != '') {
                $(".search-contain-list").show();
                $(".search-contain-list").html('');
                $("#fwsList .contact-item").each(function () {
                    var n = $(this).find($(".contact-item-nick")).text();
                    if (n.indexOf(v) != -1) {
                        var head = $(this).find($("img")).attr("src");
                        var id = $(this).attr("data-id");
                        ContactControl.createSearchItem(id, n, head);
                    }
                });
            } else {
                $(".search-contain-list").hide();
            }
        }, 50);
    }, createSearchItem: function (id, nick, head) {
        var str = '<div class="contact-item" onclick="ContactControl.openSearchPosition(' + id + ')" data-id="' + id + '" style="background:#fff"><img src="' + head + '" class="contact-head" /><div class="contact-item-right" style="padding-top:20px"><span class="contact-item-nick" style="font-size:16px">' + nick + '</span></div></div>';
        $(str).appendTo($(".search-contain-list"));
    }, openSearchPosition: function (id) {
        $('.contact-other-contain').show();
        $("#baseMessage").hide();
        $("#fwsList").show();
        $('#fwsList .contact-item[data-id="' + id + '"]').click();
        $(".search-bg input[type='text']").val('');
        $(".search-contain-list").hide();
    }, chatWithServer: function (data) {
        var tip = data['remain'];
        ChatControl.addTips(data['hxId'], tip);
        this.createNewContact(data);
    }, chatWithMultiServer: function (data, targetId, tip) {
        $("#fwsList").html('');
        for (var index in data) {
            var item = data[index];
            ChatControl.addTipsAndInitHistory(item['huanxinUserName'], tip);
            this.createNewContact({
                hxId: item['huanxinUserName'],
                nick: item['realName'],
                head: item['photo'],
                isOnline: item['isOnline']
            }, parseInt(item['userId']) == targetId);
        }
        //测试start
       /* if (groupList != null) {
        	console.log('groupList:' + groupList + '  length:' + groupList.length)
            for (var i =0 ; i < groupList.length; i ++) {
                var item = groupList[i];
                ChatControl.addTipsAndInitHistory(item['id'], tip);
                this.createNewContact({
                    hxId: item['id'],
                    nick: item['groupName'],
                    head: 'photo',
                    isGroup: true
                }, true);
            }
        }*/
        
        //测试end
        ChatControl.initOtherUnread();//初始化其他未读历史记录
    },
    createNewContact: function (data, isClick) {
        var id = data['hxId'];
        var nick = data['nick'];
        /** 避免空值 */
        if (nick == undefined) {
            nick = "";
        }
        var head = data['head'];
        if (data['isOnline'] != null) {
        	var isOnline = parseInt(data['isOnline']);
            var onlineHtml = isOnline == 0 ? '<span class="contact-item-status" style="background: #ccc">离线</span>' : '<span class="contact-item-status">在线</span>';
            var contact = $('<div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="' + id + '"> <img src="' + head + '" class="contact-head" /> <div class="contact-item-right"> <span class="contact-item-nick" data-nick="' + nick + '">' + (nick.length > 5 ? nick.substr(0, 5) + '...' : nick) + '</span>' + onlineHtml + '<span class="contact-item-time"></span> <p><span class="contact-item-recent-msg"></span></p> </div> </div>');
            contact.appendTo($("#fwsList"));
        } else if (data['isGroup'] != null) {	//群组
        	var isGroup = data['isGroup'];
        	var groupId = data['hxId'];
        	var nick = data['nick'];
        	var contact = $('<div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="' + id + 
        			'" is-Group = '+ isGroup +'> <img src="' + head + '" class="contact-head" /> <div class="contact-item-right"> <span class="contact-item-nick" data-nick="' + nick + 
        			'">' + (nick.length > 5 ? nick.substr(0, 5) + '...' : nick) + '</span><span class="contact-item-time"></span> <p><span class="contact-item-recent-msg"></span></p> </div> </div>');
        	contact.appendTo($("#fwsList"));
        }
        
        //$(".contact-item[data-item=\"fws\"]").click();
        if (isClick)
            contact.click();
    }
}

var HuanxinControl = {
    conn: null,
    nick: null,
    headUrl: null,
    orgServeId: null,
    account: null,
    hxError: false,
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
                console.log("文本", message);
                var type = message['type'];//类型
                var from = message['from'];//发送方
                var to = message['to'];//接收方
                var msgId = message['id'];
                var msgBody = message['data'];
                var isGroup = message['type'] == 'groupchat' ? true : false;
                console.log(isGroup == true ? to : from);
                msgBody = WebIM.utils.parseEmoji(msgBody);
                var data = {
                    type: 'txt',
                    isFrom: false,
                    time: message['ext']['time'],
                    msg: msgBody,
                    to: isGroup == true ? to : from,
                    nick: message['ext']['nick'],
                    headUrl: message['ext']['headUrl'],
                    isGroup: isGroup
                }
                ChatControl.handleMsg(data); 
                ContactControl.updateUnread(true, data);
            }, //收到文本消息
            onEmojiMessage: function (message) {
            }, //收到表情消息
            onPictureMessage: function (message) {
                console.log("表情", message);
                var json = {
                    type: 'img',
                    isFrom: false,
                    to: message['from'],
                    msg: message['url'],
                    time: message['ext']['time'],
                    nick: message['ext']['nick'],
                    headUrl: message['ext']['headUrl']
                }
                ChatControl.handleMsg(json);
                ContactControl.updateUnread(true, json);
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

                ChatControl.handleMsg(json);
                ContactControl.updateUnread(true, json);
                console.log("语音", message);
            }, //收到音频消息
            onLocationMessage: function (message) {
            },//收到位置消息
            onFileMessage: function (message) {
                console.log('文件消息', message)
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
        var options = {
            apiUrl: WebIM.config.apiURL,
            user: account,
            pwd: pwd,
            appKey: WebIM.config.appkey
        };
        this.conn.open(options);
        this.account = account;
    },
    sendMsg: function () {
        var text = $(".chat-text-inp").val();
        if (text == '') {
            return;
        }
        $(".chat-text-inp").val('');
        var other = $(".message-title").attr("data-id");
        var isGroup = $(".message-title").attr("is-group");
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
            headUrl: this.headUrl,
            isGroup: isGroup == 'true' ? true : false,	//是否为群聊
        }
        ChatControl.handleMsg(data); 
        ContactControl.updateRecentMsg(data);
        var msg = new WebIM.message('txt', id);//创建文本消息
        if (isGroup == 'true') {	//为群聊
        	msg.set({
                msg: text,
                to: other,
                roomType: false,
                chatType: 'chatRoom',
                ext: {
                    time: new Date(), nick: this.nick,
                    headUrl: this.headUrl, orgServeId: this.orgServeId
                },
                success: function (id, serverMsgId) {
                	console.log('send room text success');
                    ChatControl.changeSendStaus(id, 1);
                }, fail: function () {
                    ChatControl.changeSendStaus(id, 2);
                }
            });
        	msg.setGroup('groupchat');
        } else {
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
        }
        
        this.conn.send(msg.body);
    }, sendPicture: function () {
        var id = this.conn.getUniqueId();
        var other = $(".message-title").attr("data-id");
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
        ContactControl.updateRecentMsg(data);
        var imgPath = null;
        if (file.filetype.toLowerCase() in allowType) {
            msg.set({
                apiUrl: WebIM.config.apiURL,
                file: file,
                to: other,
                ext: {
                    time: new Date(), nick: this.nick,
                    headUrl: this.headUrl, orgServeId: this.orgServeId
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

var ChatControl = {
    isShowing: false,//
    chatCache: {chat: {}, unread: {}, contacts: {}},
    init: function () {

    },
    putNewUnreadPeople: function (id, c) {//突然冒出来的未读信息进行缓存
        var json = {unread: []};
        json.unread.push(id);
        $.localSave(HuanxinControl.account, JSON.stringify(json));
        var newHistory = $.localGet(HuanxinControl.account + ":" + id);
        console.log("历史记录："+newHistory);
        if (newHistory != null) {
            var history = JSON.parse(newHistory).chat;
            //合并历史记录
            var nowRecord = this.chatCache.chat[id];
            if (history[history.length - 1] == nowRecord[0])
                nowRecord.splice(0, 1);
            this.chatCache.chat[id] = history.concat(nowRecord);
        }
        this.putUnreadCache(true, id, c);
    },
    putUnreadCache: function (isIncr, id, count) {//未读消息数目存进缓存
        if (isIncr) {
            if (this.chatCache.unread.hasOwnProperty(id)) {
                var old = parseInt(this.chatCache.unread[id]);
                old += count;
                this.chatCache.unread[id] = old;
            } else {
                this.chatCache.unread[id] = count;
            }
        } else {
            if (this.chatCache.unread.hasOwnProperty(id)) {
                delete this.chatCache.unread[id];
            }
            var arrayUnread = $.localGet(HuanxinControl.account);
            if (arrayUnread != null) {
                var array = JSON.parse(arrayUnread).unread;
                array.splice($.inArray(id, array), 1);//删除未读的
                $.localSave(HuanxinControl.account, JSON.stringify({unread: array}));
            }
        }
        this.saveRecord(id);
    },
    saveRecord: function (id) {
        if (this.chatCache.chat.hasOwnProperty(id)) {
            var json = {chat: this.chatCache.chat[id], unread: 0};
            if (this.chatCache.unread.hasOwnProperty(id))
                json.unread = this.chatCache.unread[id];
            var first = JSON.parse(json.chat[0]);
            if (first.type == 'tip') {
                json.chat.splice(0, 1);
            }
            $.localSave(HuanxinControl.account + ":" + id, JSON.stringify(json));
        }
    },
    getFormateDate: function (date) {//转换日期new Date()字符串转 yyyy-mm-dd HH:MM格式
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
    checkTimeInterval: function (startDate, endDate) {//计算时间相差多少天
        startDate = this.parseDate(startDate);
        endDate = this.parseDate(endDate);
        var diff = endDate.getTime() - startDate.getTime();
        return Math.abs(diff / (60000 * 60 * 24));
    }, getZhouDay: function (day) {
        var tday = new Date(day).getDay();
        tday = parseInt(tday);
        switch (tday) {
            case 1:
                tday = '星期一';
                break;
            case 2:
                tday = '星期二';
                break;
            case 3:
                tday = '星期三';
                break;
            case 4:
                tday = '星期四';
                break;
            case 5:
                tday = '星期五';
                break;
            case 6:
                tday = '星期六';
                break;
            case 7:
                tday = '星期天';
                break;
        }
        return tday;
    },
    handleMsg: function (data) {//控制消息的入口
        var type = data['type'];
        var now = $('.message-title').attr("data-id");
        var from = data['to'];
        switch (type) {
            case 'txt':
                if (now != undefined && now == from){
                	this.createMsgItem(data);
                }
                this.putInCache(data);
                break;
            case 'img':
                var isFrom = data['isFrom'];
                if (!isFrom) {
                    if (now != undefined && now == from)
                        this.createMsgItem(data);
                    this.putInCache(data);
                    return;
                }
                var imgMsg = data['msg'];
                var reader = new FileReader();
                reader.readAsDataURL(imgMsg);
                reader.onload = function (e) {
                    data['msg'] = this.result;
                    if (now != undefined && now == from)
                        ChatControl.createMsgItem(data);
                    data['msg'] = "[图片]";
                    ChatControl.putInCache(data);
                }
                break;
            case 'audio':
                if (now != undefined && now == from)
                    this.createMsgItem(data);
                this.putInCache(data);
                break;
        }
    }, putInCache: function (data) {
        var to = data['to'];
        if (this.chatCache.chat.hasOwnProperty(to)) {
            var itemArray = this.chatCache.chat[to];
            itemArray.push(JSON.stringify(data));
            this.chatCache.chat[to] = itemArray;
            if (data['type'] != 'tip')
                this.saveRecord(to);
        } else {
            var array = [];
            array.push(JSON.stringify(data));
            this.chatCache.chat[to] = array;
            if($('#fwsList .contact-item[data-id="' + to + '"]').length!=0){
                if (data['type'] != 'tip')
                    this.saveRecord(to);
            }
        }

    }, changeSendStaus: function (msgId, status, filePath) {
        var targetStatus = $('.chat-msg-contain').find($('div[data-status="' + msgId + '"]'));
        var id = targetStatus.attr("data-id");
        switch (status) {
            case 0://发送中

                break;
            case 1://发送成功
                targetStatus.remove();
                var list = this.chatCache.chat[id];
                for (var index in list) {
                    var item = JSON.parse(list[index]);
                    if (item['msgId'] == msgId) {
                        item['msgId'] = "1";
                        if (filePath != undefined)
                            item['msg'] = filePath;
                        this.chatCache.chat[id][index] = JSON.stringify(item);
                        break;
                    }
                }
                break;
            case 2://发送失败
                targetStatus.css("background", "#ff0000");
                var list = this.chatCache.chat[id];
                for (var index in list) {
                    var item = JSON.parse(list[index]);
                    if (item['msgId'] == msgId) {
                        item['msgId'] = "2";
                        if (filePath != undefined)
                            item['msg'] = filePath;
                        this.chatCache.chat[id][index] = JSON.stringify(item);
                        break;
                    }
                }
                break;
        }
        this.saveRecord(id);
    }, createMsgItem: function (data) {
        /*console.log(this.chatCache.chat)*/
        var type = data['type'];//聊天类型
        if (type == 'tip') {
            var remain = data['msg'];
            var tip = $('<div class="chat-tips">您好，本次定制服务已接通，现在您可以开始与服务师 进行沟通了。<br />剩余 <span style="color:#f50">' + remain + '</span></div>');
            tip.appendTo($(".chat-msg-contain"));
            return;
        }
        var to = data['to'];
        var isFrom = data['isFrom'];//是否来自发送方
        var time = this.getFormateDate(data['time']);//时间
        var msg = data['msg'];//信息内容
        var headUrl = data['headUrl'];
        var nick = data['nick'];
        var lastTime = $('.chat-msg-contain .chat-item:last').attr("data-time");
        var timeText = '';
        var interval = this.checkTimeInterval(lastTime, time);
        if (interval < 1) {//今天+时间
            timeText = '今天 ' + time.split(' ')[1];
        } else if (interval < 2) {
            timeText = '昨天 ' + time.split(' ')[1];
        } else if (interval > 7) {//显示日期
            timeText = time;
        } else {//显示星期+时间
            timeText = this.getZhouDay(time) + ' ' + time.split(' ')[1];
        }
        var itemContain = $('<div class="chat-item" data-time="' + time + '"></div>');
        var head = isFrom ? $('<div class="chat-right-head"><img src="' + headUrl + '" /></div>') : $('<div class="chat-left-head"><img src="' + headUrl + '" /></div>');
        $(head).appendTo($(itemContain));
        var msgContain = isFrom ? $('<div class="chat-right-msg"></div>') : $('<div class="chat-left-msg"></div>');
        var nameAndTime = $('<div class="chat-item-name-time">' + nick + '<span>' + timeText + '</span></div>');
        $(nameAndTime).appendTo($(msgContain));
        if (isFrom) {
            var msgId = data['msgId'];
            switch (msgId) {
                case "1"://已经发送成功
                    break;
                case "2"://发送失败
                    break;
                default://正在发送
                    var status = $('<div class="chat-right-send-status" data-status="' + msgId + '" data-id="' + to + '"><img src="static/images/loading.gif"/></div>');
                    $(status).appendTo($(msgContain));
                    break;
            }
        }
        var msgBody = '';
        switch (type) {
            case 'txt'://文本消息
                msgBody = isFrom ? $('<div class="chat-right-msg-arrow"></div>') : $('<div class="chat-left-msg-arrow"></div>');
                msgBody.html(msg);
                break;
            case 'img':
                msgBody = isFrom ? $('<div class="chat-right-msg-arrow"><img src="' + msg + '" style="max-width:100%" /></div>') : $('<div class="chat-left-msg-arrow"><img src="' + msg + '" style="max-width:100%" /></div>');
                break;
            case 'audio':
                msgBody = isFrom ? $('<div class="chat-right-msg-arrow">您接收到了一条语音消息，暂时不支持播放</div>') : $('<div class="chat-left-msg-arrow">您接收到了一条语音消息，暂时不支持播放</div>');
                //msgBody=isFrom ?$('<div class="chat-right-msg-arrow"><audio src="' + msg + '"/><button onclick="ChatControl.playAudio(this)">语音消息</button></div>') : $('<div class="chat-left-msg-arrow"><audio src="' + msg + '"/><button onclick="ChatControl.playAudio(this)">语音消息</button></div>');
                break;
        }
        $(msgBody).appendTo($(msgContain));
        $(msgContain).appendTo($(itemContain));
        $(itemContain).appendTo($(".chat-msg-contain"));
        $(".chat-msg-contain").scrollTop($(".chat-msg-contain")[0].scrollHeight);
        //$(".chat-msg-contain").animate({ scrollTop: $(".chat-msg-contain")[0].scrollHeight },50);
    }, playAudio: function (obj) {//播放语音
        var targetAudio = $(obj).parent().find($("audio"))[0];
        if (targetAudio.paused) {
            targetAudio.play();
        } else {
            targetAudio.pause();
            targetAudio.currentTime = 0;
        }
    },
    changeContact: function (id, nick, isGroup) {
        $('.message-title').html('<span>' + nick + '</span>');
        $('.message-title').attr("data-id", id);
        $('.message-title').attr("is-group", isGroup);
        $('.chat-msg-contain').html('');
        if (this.chatCache.chat.hasOwnProperty(id)) {
            var data = this.chatCache.chat[id];
            for (var index in data) {
                var item = data[index];
                this.createMsgItem(JSON.parse(item));
            }
        }
    }, addTipsAndInitHistory: function (id, msg) {//添加小提示
        var data = {
            to: id,
            msg: msg,
            type: 'tip'
        }
        data = JSON.stringify(data)
        var history = $.localGet(HuanxinControl.account + ":" + id);
        if (history != null) {
            history = JSON.parse(history).chat;
            var item = history[0];//获取第一个元素
            if (item.type == 'tip') {
                history.splice(0, 1);
            }
            history.splice(0, 0, data);//第一条数据增加tip
        } else {
            history = new Array();
            history.push(data);
        }
        this.chatCache.chat[id] = history;
        //this.putInCache(data);
    }, initOtherUnread: function () {//初始化其他人的未读消息展示
        var unreadPeople = $.localGet(HuanxinControl.account);
        if (unreadPeople != null) {
            var array = JSON.parse(unreadPeople).unread;
            for (var index in array) {
                var id = array[index];
                var history = $.localGet(HuanxinControl.account + ":" + id);
                if (history != null && !this.chatCache.chat.hasOwnProperty(id)) {
                    var json = JSON.parse(history);
                    this.chatCache.chat[id] = json.chat;
                    var data = json.chat[(json.chat).length - 1];
                    ContactControl.updateRecentMsg(JSON.parse(data), true, json.unread);
                }
            }
        }
    }
}

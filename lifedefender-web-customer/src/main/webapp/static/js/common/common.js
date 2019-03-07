/**
 * 公用方法调用
 * author: wenxian.cai
 * date: 2017/10/12 17:13
 */

var common = {};

/**退出登录*/
common.logout = function () {
    layer.confirm('是否退出登录', function () {
        var url = '/login/logout';
        http.ajax.post(true, false, url, null, http.ajax.CONTENT_TYPE_1, function () {
            window.location.href = '/login';
        })
    })
}


/**初始化环信账号*/
common.initHuanXin = function (user) {
    HuanxinControl.init({
        account: user.userCode,//测试用的账号和密码
        password: WebIM.config.password,
        headUrl: user.photo,
        nick: user.nick,
        // orgServeId: user.orgServeId
    });
    // messageCache.putMessage(user.userCode, '112', message);
    // var tem = messageCache.getMessage(user.userCode, '112');
    // messageCache.removeMessage(user.userCode, '112');
    // messageCache.listening();
    var count = messageCache.getUnreadCount(user.userCode);
    if (count > 0) {
        var element = $('body > header > section.nav-right > span.news');
        if (element != undefined) {
            element.removeClass('hidden');
            var temp = Number(element.attr('value')) + count;
            common.updateNewsTips(temp);
        }
    }
    messageCache.listening(user.userCode);  //缓存监听
}
    console.log($('.content-right').width());
/**更新消息数目tip*/
common.updateNewsTips = function(count) {
    var element = $('body > header > section.nav-right > span.news');
    element.attr('value', count);
    count = count > 99 ? '99+' : count;
    element.text(count);
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
            isMultiLoginSessions: WebIM.config.isMultiLoginSessions,
            autoReconnectNumMax: WebIM.config.autoReconnectNumMax,
            autoReconnectInterval: WebIM.config.autoReconnectInterval,
        });
        this.conn.listen({
            onOpened: function (message) { //连接成功回调
                HuanxinControl.conn.setPresence();
                // HuanxinControl.conn.getInstance().isLoggedIn();
                // console.log("登陆成功")
            },
            onClosed: function (message) {
                // console.log("连接关闭：" + message);
                if (message != null && message.type == 8) { //设备在别处登录，被挤下线
                    // console.log("设备在别处登录，被挤下线")
                }
            }, //连接关闭回调
            onTextMessage: function (message) {
                // console.log("文本", message);
                var type = message['type'];//类型
                var from = message['from'];//发送方
                var to = message['to'];//接收方
                var msgId = message['id'];
                var msgBody = message['data'];
                var isGroup = message['type'] == 'groupchat' ? true : false;
                var head = message['ext']['headUrl'];
                var time = message['ext']['time'];
                var userName = message['ext']['nick'];
                if (userName == null || userName == undefined) {
                    userName = '未知用户';
                }
                msgBody = WebIM.utils.parseEmoji(msgBody);

                /*var data = {
                 type: 'txt',
                 isFrom: false,
                 time: message['ext']['time'],
                 msg: msgBody,
                 to: isGroup == true ? to : from,
                 nick: message['ext']['nick'],
                 headUrl: message['ext']['headUrl'],
                 isGroup: isGroup
                 }*/
                var message = {
                    content: msgBody,
                    id: msgId,
                    from: from,
                    to: to,
                    type: type,
                    isRead: false,
                    time: time,
                    mine: false,
                    head: head,
                    userName: userName,
                    isSuccess: true
                }
                messageCache.putMessage(to, from, message);


                // ChatControl.handleMsg(data);
                // ContactControl.updateUnread(true, data);
            }, //收到文本消息
            onEmojiMessage: function (message) {
            }, //收到表情消息
            onPictureMessage: function (message) {
                /*console.log('收到图片')
                 // console.log("图片", message);
                 var isGroup = message['type'] == 'groupchat' ? true : false;
                 var from = message['from'];//发送方
                 var to = message['to'];//接收方
                 var json = {
                 type: 'img',
                 isFrom: false,
                 to: isGroup == true ? to : from,
                 msg: message['url'],
                 time: message['ext']['time'],
                 nick: message['ext']['nick'],
                 headUrl: message['ext']['headUrl'],
                 isGroup: isGroup
                 }*/
                var msgBody = '<img src = "'+ message["url"] +'">';
                var id = message['id'];
                var type = message['type'];
                var from = message['from'];
                var to = message['to'];
                var msgId = message['id'];
                var head = message['ext']['headUrl'];
                var time = message['ext']['time'];
                var userName = message['ext']['nick'];

                var message = {
                    contentType: 'img',
                    content: msgBody,
                    id: msgId,
                    from: from,
                    to: to,
                    type: type,
                    isRead: false,
                    time: time,
                    mine: false,
                    head: head,
                    userName: userName,
                    isSuccess: true,
                }
                messageCache.putMessage(to, from, message);
            }, //收到图片消息
            onCmdMessage: function (message) {
                if (message['from'] == 'admin') {
                    var json = {
                        action: message['action'],
                        to: message['to']
                    };
                    //判断是否为禁言状态		//管理端全部开放
                    if (message['action'] == 'gag') {
                        /*$(".chat-text-inp").attr("disabled", true);
                         $(".chat-text-inp").css('background-color', '#e9e9e9');
                         $('.silentTip').css('display', 'block');*/
                    } else if (message['action'] == 'gag_remove') {
                        /*$(".chat-text-inp").attr("disabled",false);
                         $(".chat-text-inp").css('background-color', 'white');
                         $('.silentTip').css('display', 'none');*/
                    }
                }


            }, //收到命令消息
            onAudioMessage: function (message) {
                var isGroup = message['type'] == 'groupchat' ? true : false;
                var from = message['from'];//发送方
                var to = message['to'];//接收方
                var json = {
                    type: 'audio',
                    isFrom: false,
                    to: isGroup == true ? to : from,
                    msg: message['url'],
                    time: message['ext']['time'],
                    nick: message['ext']['nick'],
                    headUrl: message['ext']['headUrl'],
                    isGroup: isGroup
                }

                // ChatControl.handleMsg(json);
                // ContactControl.updateUnread(true, json);
                // console.log("语音", message);
            }, //收到音频消息
            onLocationMessage: function (message) {
            },//收到位置消息
            onFileMessage: function (message) {
                // console.log('文件消息', message)
                var from = message['from'];//发送方
                var to = message['to'];//接收方
                var filename = message['filename'];
                var msgBody = message["url"];
                var id = message['id'];
                var type = message['type'];
                var from = message['from'];
                var to = message['to'];
                var msgId = message['id'];
                var head = message['ext']['headUrl'];
                var time = message['ext']['time'];
                var userName = message['ext']['nick'];

                var message = {
                    contentType: 'file',
                    content: msgBody,
                    id: msgId,
                    from: from,
                    to: to,
                    type: type,
                    isRead: false,
                    time: time,
                    mine: false,
                    head: head,
                    userName: userName,
                    isSuccess: true,
                    fileName: filename
                }
                messageCache.putMessage(to, from, message);
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
                    // console.log("连接超时！请刷新页面重试");
                }
                if (message.type == 16) {
                    HuanxinControl.login(data['account'], data['password'])
                }
                // console.log(message);
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
    sendMsg: function (type, message, callback) {

        var id = this.conn.getUniqueId();
        var msg = new WebIM.message('txt', id);//创建文本消息
        if (type == 'chat') {   //单聊
            msg.set({
                msg: message.content,
                to: message.to,
                ext: {
                    time: new Date().getTime(), nick: message.userName,
                    headUrl: message.head
                },
                success: function () {
                    if (typeof callback == 'function') {
                        return callback(true);
                    }
                }, fail: function () {
                    if (typeof callback == 'function') {
                        return callback(false);
                    }
                }
            });
        } else {
            msg.set({
                msg: message.content,
                to: message.to,
                roomType: false,
                chatType: 'chatRoom',
                ext: {
                    time: new Date().getTime(), nick: message.userName,
                    headUrl: message.head
                },
                success: function () {
                    if (typeof callback == 'function') {
                        // console.log('send group message success')
                        return callback(true);
                    }
                }, fail: function () {
                    if (typeof callback == 'function') {
                        return callback(false);
                    }
                }
            });
            msg.setGroup('groupchat');
        }
        this.conn.send(msg.body);
        /*message.content = WebIM.utils.parseEmoji(message.content);
         messageCache.putMessage(message.from, message.to, message);*/
    }, sendPicture: function (event, msgType, message, callback) {
        var lay = layer.load();
        var id = this.conn.getUniqueId();
        var msg = new WebIM.message('img', id);
        var input = event;//选择图片的input
        if (!this.checkIsImg(input.files[0].name)) {
            layer.msg("请选择正确的图片格式");
            layer.close(lay);
            return;
        }
        var file = WebIM.utils.getFileUrl(input);
        var allowType = {
            "jpg": true,
            "gif": true,
            "png": true,
            "bmp": true
        };
        var imgPath = null;
        if (file.filetype.toLowerCase() in allowType) {
            msg.set({
                apiUrl: WebIM.config.apiURL,
                file: file,
                to: message.to,
                roomType: false,
                chatType: message.type == 'chat' ? 'singleChat' : 'chatRoom',
                ext: {
                    time: new Date().getTime(), nick: message.userName,
                    headUrl: message.head
                },
                onFileUploadError: function (error) {
                    layer.close(lay);
                    if (typeof callback == 'function') {
                        return callback(false);
                    }
                },//图片上传失败
                onFileUploadComplete: function (data) {
                    // imgPath = data.uri + '/' + data.entities[0].uuid;
                    message.content = '<img src = "'+ data.uri + '/' + data.entities[0].uuid +'">';
                    messageCache.putMessage(message.from, message.to, message);
                    layer.close(lay);

                },
                success: function () {
                    if (typeof callback == 'function') {
                        return callback(true);
                    }
                },
                flashUpload: WebIM.flashUpload
            });
            if (message.type == 'groupchat') {
                msg.setGroup('groupchat');
            }

        }
        this.conn.send(msg.body);
    },
    /** 发送文件消息 */
    sendFile: function (source, msgType, message, callback) {
        var lay = layer.load();
        var id = this.conn.getUniqueId();                   // 生成本地消息id
        var msg = new WebIM.message('file', id);        // 创建文件消息
        var file = WebIM.utils.getFileUrl(source);      // 将文件转化为二进制文件
        var pos = $(source).val().lastIndexOf('\\');
        var filename = $(source).val().substring(pos+1);
        message.fileName = filename;
        /*var allowType = {
         'jpg': true,
         'gif': true,
         'png': true,
         'bmp': true,
         'zip': true,
         'txt': true,
         'doc': true,
         'pdf': true
         };*/

        var option = {
            apiUrl: WebIM.config.apiURL,
            file: file,
            to: message.to,                       // 接收消息对象
            roomType: false,
            chatType: msgType == 'chatgroup' ? 'chatRoom' : 'singleChat',
            ext: {
                time: new Date().getTime(), nick: message.userName,
                headUrl: message.head, orgServeId: null
            },
            onFileUploadError: function (error) {
                layer.close(lay);
                if (typeof callback == 'function') {
                    return callback(false);
                }
            },
            onFileUploadComplete: function (result) {
                message.content = result.uri + '/' + result.entities[0].uuid;
                messageCache.putMessage(message.from, message.to, message);
                layer.close(lay);

            },
            success: function () {
                if (typeof callback == 'function') {
                    return callback(true);
                }
                // console.log('send file success')
            },
            flashUpload: WebIM.flashUpload
        };
        msg.set(option);
        if (msgType == 'groupchat') {
            msg.setGroup('groupchat');
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


/**
 * 消息缓存管理
 * @type {{}}
 * 储存模式：(ownerKey:当前登录用户的userCode; otherKey:对方的userCode)
 * lifekeeper.im = {
 *      ownerKey:{
 *          otherKey:[{message}]
 *      }
 * }
 */
var messageCache = {};
messageCache.key = 'lifekeeper.im'; //浏览器cache的key
messageCache.pageSize = 20; //取数据时对单一用户的最大条数
/**将消息存进缓存
 * message: {
 *  conentType: "txt/file/img",
 *  content: "",   //内容
 *  id: "",     //消息id
 *  from: "",   //发送者
 *  to: "",     //接受者
 *  type: "chat/groupchat",    //类型（单聊/群聊）
 *  isRead: true/false,   //是否已读
 *  time: ""
 *  mine: true/false    //是否自己为发送方
 *  head: "" //头像
 *  userName: "" //当前消息发送者名称
 *  isSuccess: true/false/loading   //是否发送成功(true:成功； loading:发送中; false:发送失败)
 *  fileName: "" //文件名称
 *  speak0: true/false  //第一次对话
 * }
 * */
messageCache.putMessage = function (ownerKey, otherKey, message) {
    if (message.type == 'groupchat') {  //群聊特殊处理
        ownerKey = HuanxinControl.account;
        otherKey = message.to;
    }
    var json = JSON.parse(localStorage.getItem(messageCache.key));
    if (json == null) {
        json = {};
    }
    var ownerLog = null;
    ownerLog = json[ownerKey];
    if (ownerLog == null || ownerLog == undefined) {
        ownerLog = {};
        json[ownerKey] = ownerLog;
    }
    var otherLog = null;
    otherLog = ownerLog[otherKey];
    if (otherLog == null || ownerLog == undefined) {
        otherLog = new Array;
        ownerLog[otherKey] = otherLog;
    }
    if (otherLog.length == 0 && !message.speak0) { //添加speak0数据
        var speak0Msg = {
            "content": "服务已开通!",
            "id": null,
            "from": message.from,
            "to": message.to,
            "type": message.type,
            "isRead": true,
            "time": new Date().getTime(),
            "mine": false,
            "head": message.head,
            "userName": message.userName,
            "isSuccess": true,
            "speak0": true
        }
        otherLog.push(speak0Msg);
        localStorage.setItem(messageCache.key, JSON.stringify(json), true);
    }
    otherLog.push(message);
    localStorage.setItem(messageCache.key, JSON.stringify(json), true);
}

/**从缓存里取出消息
 * page: 获取最后几页数据
 * */
messageCache.getMessage = function (ownerKey, otherKey, page) {
    if (page <= 0) {
        page = 1;
    }
    var json = JSON.parse(localStorage.getItem(messageCache.key));
    if (json == null || json == undefined) {
        return null;
    }
    var ownerLog = json[ownerKey];
    if (ownerLog == null || ownerLog == undefined) {
        return null;
    }
    var otherLog = ownerLog[otherKey];
    if (otherLog == null || otherLog == undefined) {
        return null;
    }
    var len = otherLog.length;
    var log = new Array();
    if (len < messageCache.pageSize * page) {
        return otherLog;
    }
    for (var i = (len -(page * messageCache.pageSize)) ; i < len ; i ++) {
        log.push(otherLog[i]);
    }
    return log;
}

/**
 * 获取最近几位用户的最后一条消息
 * @param ownerKey
 * @param number 用户位数
 * @returns {*}
 */
messageCache.getRecentMessage = function (ownerKey, number) {
    var json = JSON.parse(localStorage.getItem(messageCache.key));
    if (json == null || json == undefined) {
        return null;
    }
    var ownerLog = json[ownerKey];
    if (ownerLog == null || ownerLog == undefined) {
        return null;
    }
    var arr = [];
    for (var i in ownerLog) {
        if (ownerLog[i].length > 1) {
            var time = ownerLog[i][ownerLog[i].length - 1].time;
            arr.push(time);
        }
    }
    var log = {};
    var len = Object.getOwnPropertyNames(ownerLog).length;
    if (len <= number) {
        for (var i in ownerLog) {
            var temp = ownerLog[i][ownerLog[i].length - 1];
            if (temp.mine) {
                for (var j = ownerLog[i].length - 1; j >= 0; j --) {
                    if (!ownerLog[i][j].mine) {
                        temp.head = ownerLog[i][j].head;
                        temp.userName = ownerLog[i][j].userName;
                        break;
                    }
                }
            }
            log[i] = [];
            log[i].push(temp);
        }
        return log;

    }
    arr = arr.sort();
    for (var i = arr.length - 1; i >= arr.length - number; i --) {
        for (var j in ownerLog) {
            if (arr[i] == ownerLog[j][ownerLog[j].length - 1].time) {
                var temp = ownerLog[j][ownerLog[j].length - 1];
                if (temp.mine) {
                    for (var k = ownerLog[j].length - 1; k >= 0; k --) {
                        if (!ownerLog[j][k].mine) {
                            temp.head = ownerLog[j][k].head;
                            temp.userName = ownerLog[j][k].userName;
                            break;
                        }
                    }
                }
                log[j] = [];
                log[j].push(temp);
                continue;
            }
        }
    }

    return log;
}

/**获取当前用户全部未读消息
 * done 加上最近100条条件
 * */
messageCache.getUnreadMessage = function (ownerKey) {
    var json = null;
    var ownerLog = null;
    var log = {};
    if (localStorage.hasOwnProperty(messageCache.key)) {
        json = JSON.parse(localStorage.getItem(messageCache.key));
        ownerLog = json[ownerKey];
    }
    if (ownerLog == null) {
        return null;
    }
    for (var key in ownerLog) {
        log[key] = [];
        var len = ownerLog[key].length;
        len = len > 100 ? 100 : len;
        for (var i = 0; i < len; i ++) {    //便利最近100条数据即可
            if (!ownerLog[key][i].isRead) {
                log[key].push(ownerLog[key][i]);
            }
        }
        if (log[key].length == 0) {
            delete log[key];
        }
    }

    return log; //ownerLog
};

/**获取未读数
 * otherKey: 可为null（null时查询全部用户消息数；反之查询指定用户的消息数）
 * done 加上最近100条条件
 * */
messageCache.getUnreadCount = function (ownerKey, otherKey) {
    var json = null;
    var ownerLog = null;
    var count = 0;
    if (localStorage.hasOwnProperty(messageCache.key)) {
        json = JSON.parse(localStorage.getItem(messageCache.key));
        ownerLog = json[ownerKey];
    }
    if (ownerLog == null) {
        return count;
    }
    if (otherKey == null || otherKey == undefined) {    //全部用户
        for (var key in ownerLog) {
            var len = ownerLog[key].length;
            len = len > 100 ? 100 : len;
            for (var i = 0; i < len; i ++) {
                if (!ownerLog[key][i].isRead) {
                    count ++;
                }
            }
        }
        return count;
    }
    var otherLog = ownerLog[otherKey];      //指定用户
    var len = otherLog.length;
    len = len > 100 ? 100 : len;
    for (var i = 0; i < len; i ++) {
        if (!otherLog[i].isRead) {
            count ++;
        }
    }
    return count;
}

/**修改当前用户和指定用户的消息状态（已读、未读状态） */
messageCache.modifyMessageStatus = function (ownerKey, otherKey, status) {
    var json = JSON.parse(localStorage.getItem(messageCache.key));
    var ownerLog = json[ownerKey];
    if (ownerLog == null) {
        return;
    }
    var otherLog = ownerLog[otherKey];
    for (var key in otherLog) {
        if (otherLog[key].isRead != status) {
            otherLog[key].isRead = status;
        }
    }
    localStorage.setItem(messageCache.key, JSON.stringify(json), true);
}

/**修改当前用户和指定用户的指定消息的状态(发送状态) */
messageCache.modifyMessageSuccessById = function (ownerKey, otherKey, msgId, status) {
    var json = JSON.parse(localStorage.getItem(messageCache.key));
    var ownerLog = json[ownerKey];
    if (ownerLog == null) {
        return;
    }
    var otherLog = ownerLog[otherKey];
    for (var key in otherLog) {
        if (otherLog[key].id == msgId) {
            otherLog[key].isSuccess = status;
            break;
        }
    }
    localStorage.setItem(messageCache.key, JSON.stringify(json), true);

}

/**清除缓存里的消息*/
messageCache.removeMessage = function (ownerKey, otherKey) {
    var json = JSON.parse(localStorage.getItem(messageCache.key));
    var ownerLog = json[ownerKey];
    if (ownerLog == null) {
        return;
    }
    var otherLog = ownerLog[otherKey];
    if (otherLog == null) {
        return;
    }
    var speak0 = {};
    for (var i in otherLog) {
        if (otherLog[i].speak0) {
            speak0 = otherLog[i];
            break;
        }
    }
    ownerLog[otherKey] = new Array();   //将指定用户的消息清除
    ownerLog[otherKey].push(speak0);
    localStorage.setItem(messageCache.key, JSON.stringify(json));
    // localStorage.removeItem(messageCache.key);
}

/**缓存监听*/
messageCache.listening = function (ownerKey) {
    var SetItem = localStorage.setItem;
    localStorage.setItem = function(key,value) {
        var setItemEvent = new Event("setItemEvent");
        setItemEvent.value = value;
        setItemEvent.key = key;
        window.dispatchEvent(setItemEvent);
        SetItem.apply(this,arguments);
    }
    messageCache.storage = function (e) {
        setTimeout(function () {
            if (e.key == "lifekeeper.im") {
                /*var json = JSON.parse(e.value);
                 var log = json[ownerKey];*/
                // console.log('消息+1');
                if ($('.news') != undefined) {
                    var count = messageCache.getUnreadCount(ownerKey);
                    if (count < 1) {
                        return;
                    }
                    $('.news').removeClass('hidden');
                    var temp = Number($('.news').attr('value')) + 1;
                    common.updateNewsTips(temp);
                }
            }
        }, 300);

    }
    window.addEventListener("setItemEvent", messageCache.storage);

}

var Editor = {
    uEditor: null,
    isSetUpload: false,
    init: function () {
        UE.delEditor('server_details');
        this.uEditor = UE.getEditor('server_details', {
            toolbars: [['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'strikethrough',
                'horizontal', 'removeformat', 'formatmatch', '|', 'forecolor',
                'backcolor', 'insertorderedlist', 'insertunorderedlist',
                'selectall', '|', 'rowspacingtop', 'rowspacingbottom',
                'lineheight', 'template', 'background', '|', 'customstyle', 'paragraph',
                'fontfamily', 'fontsize', '|', 'directionalityltr',
                'directionalityrtl', 'indent', '|', 'justifyleft',
                'justifycenter', 'justifyright', 'justifyjustify', '|',
                'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'simpleupload', 'insertvideo', 'link']],
            enableAutoSave: false,
            enableContextMenu: false,
            elementPathEnabled: false,
            maximumWords: 300,
            imageFieldName: 'uploadFile',
            imageActionName: 'uploadimage',
            videoActionName: "uploadvideo",
            videoFieldName: "uploadFile",
            videoAllowFiles: [
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"],
            initialFrameHeight: 200
        });
        if (!this.isSetUpload) {
            UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
            UE.Editor.prototype.getActionUrl = function (action) {
                if (action == 'uploadimage') {
                    return 'common/uploadFile/img';
                }
                /*else if(action == 'uploadvideo'){
                 return $.baseUrl + '/upload/video';
                 }*/
                else {
                    return this._bkGetActionUrl.call(this, action);
                }
            }
            this.isSetUpload = true;
        }
    },
    getContent: function () {
        return this.uEditor.getContent();
    },
    setInitContent: function (html) {
        this.uEditor.addListener("ready", function () {
            Editor.setContent(html);
        });
    },
    setContent: function (html) {
        setTimeout(function () {
            Editor.uEditor.setContent(html);
        }, 1000)
    }
}
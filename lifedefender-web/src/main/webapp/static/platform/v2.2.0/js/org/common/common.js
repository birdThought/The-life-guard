/**
 * org公用JS
 * Created by wenxian.cai on 2017/5/3.
 */

var common = {};

var btn = '<a href="/org/profile/services/beStore" class="tobe-store-opera shop" style=""></a>';
/*var btn = '<li style="height: 4rem;line-height: 4rem;"><a href="/org/profile/services/beStore" style="">成为店铺</a></li>';*/


common.getStoreInfo = function ($logo, $orgName, $orgAuthenticated, $telAuthenticated, $emailAuthenticated, $news, $userId) {
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'GET',
        url: '/org/profile?storeinfo',
        success: function(result){
        	console.log(result)
            if (result.success == true) {
                $logo.attr('src', result.obj.logo);
                $orgName.text(result.obj.orgName);
                if (result.obj.tel != null) {
                    $telAuthenticated.css('background-position', 'left -128px');
                }
                var newsCount = result.attributes.newsCount;
                // newsCount = newsCount > 99 ? '99+' : newsCount;
                // $news.text(newsCount);
                common.updateNewsTips(newsCount);
                if (newsCount > 0) {
                    $news.removeClass("hidden");
                }
                $userId.text('编号：' + result.attributes.userId);
                common.initQrCode(result.attributes.userId);
                if (result.obj.type == 2) {
                    //登录环信
                    var user = {
                        userCode: result.attributes.userCode,
                    }
                    common.initHuanXin(user);
                }
                // 成为店铺 /org/profile/services/beStore 
                console.log(result.attributes.user);
                if(result.attributes.user.lut == 'o' && (result.attributes.user.userType == 0 || result.attributes.user.userType == 2)
                		&& (result.attributes.user.agentId != 3 || result.attributes.user.agentNum == null)){
                	$(".btn.btn-3").parent().append(btn);
                }else{
                	btn = '<a class="tobe-store-opera shop" style=""></a>';
                	$(".btn.btn-3").parent().append(btn);
                	$('.tobe-store-opera').css('background-position','-13px -41px')
                }
            }
        }
    });

}

common.checkOrgUserStatus = function ($photo, $name, $orgName, $userType, $qualificated, $isMobile, $isEmail, $news, $userCode, $userId) {
        jQuery.ajax({
            async: true,
            cache: false,
            type: 'GET',
            url: '/org/profile?services-info',
            success: function(result){
                if(result.success){
                    var obj = result.attributes;
                    $photo.attr('src', obj.userHead);
                    $name.text(obj.userName);
                    $orgName.text(obj.orgName);
                    $userType.text(obj.professionalName);
                    if (obj.isQualificated) {
                        $qualificated.css('background-position','left top');
                    }
                    if (obj.isMobileVerified) {
                        $isMobile.css('background-position','left -124px');
                    }
                    if (obj.isEmailVerified) {
                        $isEmail.css('background-position','left -156px');
                    }
                   
                    var newsCount = obj.newsCount;
                    // newsCount = newsCount > 99 ? '99+' : newsCount;
                    // $news.text(newsCount);
                    common.updateNewsTips(newsCount);
                    if (newsCount > 0) {
                        $news.removeClass("hidden");
                    }
                    $userCode.val(obj.userCode);
                    $userId.text('编号：' + result.attributes.userId);
                    common.initQrCode(result.attributes.userId);

                    //登录环信
                    var user = {
                        userCode: obj.userCode,
                    }
                    common.initHuanXin(user);
                    // 成为店铺
                    console.log(1);
                    if(result.attributes.user.lut == 'o' && (result.attributes.user.userType == 0 || result.attributes.user.userType == 2)
                    		&& result.attributes.user.agentId != 3 || result.attributes.user.agentNum == null){
                    	$(".btn.btn-3").parent().append(btn);
                    }
                }
            }
        });
}
common.beAShop = function(){
	var data = {} // shopName: $(".form-inline input.shopName").val()
	var url = '/org/profile/services/add_shop';
	http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result){
		if(result.success){
			$('.tobe-store-opera').css('background-position','-13px -41px');
			window.location.href = '/store/home';
			layer.msg("成为店铺成功!");
		}
	});
}
common.show_btn = function(ele){
	var state = $(ele).prop("checked");
	console.log(state);
	if(state){
		$('.be-shop-btn').removeAttr('disabled');
	} else {
		$('.be-shop-btn').attr('disabled', true);
	}
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
        if ($('.news') != undefined) {
            $('.news').removeClass('hidden');
            var temp = Number( $('.news').attr('value')) + count;
            common.updateNewsTips(temp);
        }
    }
    messageCache.listening(user.userCode);  //缓存监听
}

/**初始化二维码*/
common.initQrCode = function (userId) {
    var obj = document.getElementById("qrcode");
    var qrcode = new QRCode(obj, {
        width : 96,//设置宽高
        height : 96
    });
    qrcode.makeCode('{userId:'+ userId +'}');
}
/** 弹出二维码 */
common.popupQrCode = function () {
    var obj =  $('#qrcode');
    if (obj.hasClass('none')) {
        $('#qrcode').show(300);
        obj.removeClass('none')
    } else {
        $('#qrcode').hide(300);
        obj.addClass('none')
    }

}

/**
 * 添加border
 */
common.addBorder = function (height) {
    if (height == null || typeof height == undefined) {
        height = 780;
    }
    $(".main-content").css("minHeight",height);
    var height=$(".main-content").height() + 20;
    $(".main-nav").css({
        borderRight:'1px solid #ddd',
        height:height
    });
    $(".container").css({
        borderLeft:'1px solid #ddd',
        borderRight:'1px solid #ddd',
        borderBottom:'1px solid #ddd',
        height:height
    })
}

/**更新消息数目tip*/
common.updateNewsTips = function(count) {
    $('.news').attr('value', count);
    count = count > 99 ? '99+' : count;
    $('.news').text(count);
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
                console.log("登陆成功")
            },
            onClosed: function (message) {
                console.log("连接关闭：" + message);
                if (message != null && message.type == 8) { //设备在别处登录，被挤下线
                    console.log("设备在别处登录，被挤下线")
                }
            }, //连接关闭回调
            onTextMessage: function (message) {
                console.log("文本", message);
                var type = message['type'];//类型
                var from = message['from'];//发送方
                var to = message['to'];//接收方
                var msgId = message['id'];
                var msgBody = message['data'];
                var isGroup = message['type'] == 'groupchat' ? true : false;
                var head = message['ext']['headUrl'];
                var time = message['ext']['time'];
                var userName = message['ext']['nick'];
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
                console.log("图片", message);
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
                console.log("语音", message);
            }, //收到音频消息
            onLocationMessage: function (message) {
            },//收到位置消息
            onFileMessage: function (message) {
                console.log('文件消息', message)
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
                    console.log("连接超时！请刷新页面重试");
                }
                if (message.type == 16) {
                    HuanxinControl.login(data['account'], data['password'])
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
                        console.log('send group message success')
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
                console.log('send file success')
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
                console.log('消息+1');
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



/**
 * 全局过滤器
 */

/**字符串截取*/
Vue.filter('subStr', function (value, type) {
    switch (type) {
        case 1: //服务名称截取
            var arr = value.split('[');
            arr.splice(arr.length - 1, 1);
            return arr.join('[');
        case 2: //套餐次数截取
            var arr = value.split('');
            if (arr.length > 1) {
                arr.splice(arr.length - 1, 1);
                return arr.join('');
            }
            return value;
    }
});

/**日期格式转换*/
Vue.filter('date', function (value, fmt) {
    return (new Date(value)).Format(fmt);
});

/**确认value是否为null*/
Vue.filter('isNone', function (value, type) {
    switch (type) {
        case 1:
            if (value == null || value == undefined) {
                return '无';
            }
            return value;
        case 2:
            if (value == null || value == undefined) {
                return 0;
            }
            return value;
    }

});

/**性别转换*/
Vue.filter('gender', function (value, type) {
    switch (type) {
        case 1:
            if (value == null) {
                return '无'
            }
            if (value == 1) {
                return '男';
            }
            return '女';
            break;
    }

});

/**移除小数点*/
Vue.filter('removeDecimalPoint', function (value, type) {
    switch (type) {
        case 1:
            if (value == null) {
                return null;
            }
            var arr = [];
            arr = value.split('.');
            return arr[0];
    }

});

Vue.filter('imgIsNone', function (value, type) {
    switch (type) {
        case 1: //头像模板
            if (value == null || value == undefined) {
                return '/static/platform/v2.2.0/images/template-head.png';
            }
            return value;
    }
});

/**
 * 网络请求
 * @type {{}}
 */
var http = {};

/**Ajax*/
http.ajax = {};
http.ajax.CONTENT_TYPE_1 = 'application/x-www-form-urlencoded;charset=utf-8';
http.ajax.CONTENT_TYPE_2 = 'application/json;charset=utf-8';

/**GET请求*/
http.ajax.get = function (async, cache, url, data, contentType, callback) {
    $.ajax({
        async : async,
        cache : cache,
        type: 'GET',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        beforeSend:function(){
            layer.load(2, {offset: ['55%', '50%']});
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
                    return;
                }
                if (result.msg != null && result != '') {
                    layer.msg(result.msg);
                    return;
                }
                layer.msg('服务器异常');
            }
        }
    });

};

http.ajax.get_no_loading = function (async, cache, url, data, contentType, callback) {
    $.ajax({
        async : async,
        cache : cache,
        type: 'GET',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
                    return;
                }
            }
        }
    });

};

/**POST请求*/
http.ajax.post = function (async, cache, url, data, contentType, callback) {

    $.ajax({
        async : async,
        cache : cache,
        type: 'POST',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        beforeSend:function(){
            layer.load(2, {offset: ['55%', '50%']});
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
                    return;
                }
                if (result.msg != null && result != '') {
                    layer.msg(result.msg);
                    return;
                }
                layer.msg('服务器异常');
            }
        }
    });

}
/**POST请求2*/
http.ajax.post_no_loading = function (async, cache, url, data, contentType, callback) {

    $.ajax({
        async : async,
        cache : cache,
        type: 'POST',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
                    return;
                }
            }
        }
    });

};


/**
 * 图表对象
 * @type {{}}
 */
var chart = {};

/**
 *
 * @param data 数据对象
 * @param $element 元素对象
 * @param conditions 图像规则对象
 */
chart.create_chart_spline = function (data, $element, conditions) {
    $element.highcharts({
        chart: {
            type: 'spline'
        },
        title:{
            text: conditions.titleText,
            align:'left',
            style:{
                color:'#55a9e9'
            }
        },
        xAxis: {
            categories: data.categories
        },
        yAxis: {
            title:{
                text:  conditions.yAxisText
            }
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        colors: conditions.color | ['#10bb71'],
        series: [{
            data: data.series
        }]
    });
}



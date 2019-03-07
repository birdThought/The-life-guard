/**
 * 消息对话框
 */

var chat = {};

chat.initChatDialog = function (messages, orgUser, currentMember, callback) {
    // 如果没有给currentMember用户, 创建一个默认的currentMember
    if (!currentMember) {
        // 取聊天记录最后一条的用户信息，如果没有就用默认的用户
        var msg = messageCache.getRecentMessage(orgUser.userCode, 10);
        if (msg == null || Object.keys(msg).length == 0) {
            layer.msg("暂时没有消息");
            return;
        }
        for (var c in msg) {
            currentMember = {
                userName: msg[c][0].userName,
                realName: null,
                userCode: msg[c][0].from,
                photo: msg[c][0].head,
                type: 'chat'
            }
            break;
        }
    }
    
    if (currentMember.type == 'groupchat') {
        chat.getLessonByHuanxinId(currentMember.userCode);
    }
    var count = messageCache.getUnreadCount(orgUser.userCode);
    if (count > 0) {
        if ($('.news') != undefined) {
            var temp = (Number($('.news').attr('value') - count));
            if (temp <= 0) {
                temp = 0;
                $('.news').addClass('hidden');
            }
            // $('.news').text(temp);
            common.updateNewsTips(temp);
        }
    }

    //移除common的缓存监听
    window.removeEventListener("setItemEvent", messageCache.storage);
    // messageCache.setItemEvent.stopPropagation();
    //添加内部缓存
    if (chat.vm.times == 0) {
        chat.cacheListening();
        chat.vm.times ++;
    }
    window.addEventListener("setItemEvent2", chat.storage);

    chat.vm.orgUser = orgUser;
    chat.vm.currentMember = currentMember;
    //解决当type为grouptype时projectCode可能为null的情况
    if (currentMember.type == 'groupchat' && (currentMember.projectCode == undefined || currentMember.projectCode == null)) {
        chat.getLessonByHuanxinId(currentMember.userCode);
        currentMember.projectCode = chat.vm.lesson[currentMember.userCode].code;
    }
    // chat.vm.chatLog = messages;
    chat.vm.members[currentMember.userCode] = currentMember;    //将聊天用户存储起来
    chat.listHistory(orgUser.userCode, currentMember.userCode, 1);

    messageCache.modifyMessageStatus(orgUser.userCode, currentMember.userCode, true);   //todo

    //表情点击事件
    $('.face').on('click', 'li', function () {
        $('.txt' + chat.vm.currentMember.userCode).val($('.txt' + chat.vm.currentMember.userCode).val() + $(this).attr('data-key'));
    })

    layer.open({
        type: 1,
        title: false,
        anim: 0,
        move: '.chat-tit',
        area: ['800px', '626px'],
        zIndex: 999,
        content: $('.chat-items'),
        cancel: function () {
            chat.vm.chatLog = [];
            $('.face').unbind('click');
            //重新添加common的缓存监听
            window.addEventListener("setItemEvent", messageCache.storage);
            //移除内部缓存监听
            window.removeEventListener("setItemEvent2", chat.storage);
            // chat.storage.stopPropagation();
            if (typeof callback == 'function') {
                callback();
            }
        }
    });


}

chat.vm = new Vue({
    el: '.vue-dialog-content',
    data: {
        chatLog: null, //存储各个会话框的聊天记录
        result: null,
        currentMember: null,
        members: {},   //聊天用户
        orgUser: null,
        file: null,
        search: null,
        searchMember: [],
        lessonMember: null, //群成员
        unreadCount: {},    //存放各个用户的未读消息数
        lesson: {},     //存放各个群组信息
        times: 0
    },
    methods: {
        /**弹出/隐藏表情*/
        popupEmoji: function (event) {
            if ($('.face').css('display') == 'none') {
                $('.face').css('display', 'block');
                return;
            }
            $('.face').css('display', 'none');
        },
        /**掉线执行操作*/
        afterLoginOut: function (type, event) {
            var lay = layer.confirm('账号已掉线，是否重新登录?', {icon: 3, title:'提示'}, function () {
                HuanxinControl.login(HuanxinControl.account, WebIM.config.password);
                setTimeout(function () {
                    chat.vm.sendMessage(type, event);
                }, 1000);
                layer.close(lay);
            }, function () {
                layer.close(lay);
            });
        },
        /**发送消息*/
        sendMessage: function (type, event) {
            if (HuanxinControl.conn.logOut) {
                this.afterLoginOut(type, event);
                return;
            }

            var userName = this.orgUser.realName;   //如果没有真实姓名，则采用账号昵称
            if (this.orgUser.realName == '' || this.orgUser.realName == null) {
                userName = this.orgUser.userName;
            }

            var message = {
                "content": null,
                "id": new Date().getTime(),
                "from": this.orgUser.userCode,
                "to": this.currentMember.userCode,
                "type": this.currentMember.type,
                "isRead": true,
                "time": new Date().getTime(),
                "mine": true,
                "head": this.orgUser.head,
                "userName": userName,
                "isSuccess": 'loading'
            }
            switch (type) {
                case 'txt':
                    var txt = $('.txt'+ this.currentMember.userCode +'').val();
                    if (txt == '' || txt == null) {
                        return;
                    }
                    $('.txt'+ this.currentMember.userCode +'').val('');
                    $('.face').css('display', 'none');

                    message.content = txt;
                    var msgType = this.currentMember.type;
                    //存储消息
                    var temp = JSON.parse(JSON.stringify(message));
                    message.content = WebIM.utils.parseEmoji(message.content);
                    messageCache.putMessage(message.from, message.to, message);
                    HuanxinControl.sendMsg(msgType, temp, function (isSuccess) {
                        if (isSuccess) { //发送成功
                            message.isSuccess = 'true';
                        } else {
                            message.isSuccess = 'false';
                        }
                        messageCache.modifyMessageSuccessById(message.from, message.to, message.id, message.isSuccess);

                        // var url = '/message/save-im-message';
                        // message.owner = chat.vm.currentMember.userCode;
                        // message.contentType = 'text';
                        // message.status = message.isSuccess;
                        // http.ajax.post_no_loading(true, true, url, JSON.stringify(message), http.ajax.CONTENT_TYPE_2, function () {
                        //
                        // })
                    });

                    break;
                case 'emoji':
                    /*if ($('.face').css('diaplay') == 'none') {
                        $('.face').css('display', 'block');
                    } else {
                        $('.face').css('display', 'none');
                    }*/

                    break;
                case 'picture':
                    var msgType = this.currentMember.type;
                    message.contentType = 'img';
                    HuanxinControl.sendPicture(event, msgType, message, function (isSuccess) {
                        if (isSuccess) { //发送成功
                            message.isSuccess = 'true';
                        } else {
                            message.isSuccess = 'false';
                        }
                        messageCache.modifyMessageSuccessById(message.from, message.to, message.id, message.isSuccess);
                    });
                    break;
                case 'file':
                    var msgType = this.currentMember.type;
                    message.contentType = 'file';
                    HuanxinControl.sendFile(event, msgType, message, function (isSuccess) {
                        if (isSuccess) { //发送成功
                            message.isSuccess = 'true';
                        } else {
                            message.isSuccess = 'false';
                        }
                        messageCache.modifyMessageSuccessById(message.from, message.to, message.id, message.isSuccess);
                    });
                    break;
            }
            //更新“加载更多消息状态”
            var $obj = $('.chat-main-' + this.currentMember.userCode).find('p').eq(0);
            if ($obj.hasClass('more-message-on')) {
                return;
            }
            $obj.addClass('more-message-on');
            $obj.css('color', '#48c858');
            $obj.text('加载更多消息');
        },
        /**获取head*/
        getHead: function (logs, mine) {
            var head = null;
            for (var i = logs.length - 1; i >= 0; i --) {
                if (mine) {
                    if (logs[i].mine) {
                        head = logs[i].head;
                        break;
                    }
                    continue;
                }
                if (!logs[i].mine) {

                    head = logs[i].head;
                    break;
                }
                if (logs[i].type == 'groupchat') {
                    if (this.lesson[logs[i].to] != undefined) {
                        head = this.lesson[logs[i].to].image;
                    }
                    continue;
                }
            }
            if (head == null || head === undefined) {
                if (mine) {
                    head = this.orgUser.head;
                } else {
                    //head = this.currentMember.photo;
                }
            }
            return head;
        },
        /**获取userCode*/
        getUserCode: function (logs, mine) {
            // console.log('inter getusercode')
            var userCode = null;
            for (var i = logs.length - 1; i >= 0; i --) {
                if (mine) {
                    if (logs[i].mine) {
                        userCode = logs[i].to;
                        break;
                    }
                    continue;
                }
                if (!logs[i].mine) {
                    userCode = logs[i].from;
                    break;
                }
                if (logs[i].type == 'groupchat') {
                    userCode = logs[i].to;
                    continue;
                }
            }
            if (userCode == null || userCode === undefined) {
                if (mine) {
                    userCode = this.orgUser.userCode;
                } else {
                    userCode = this.currentMember.userCode;
                }
               
            }
            return userCode;
        },
        /**获取userName*/
        getUserName: function (logs, mine) {
            var userName = null;
            for (var i = logs.length - 1; i >= 0; i --) {
                if (mine) {
                    if (logs[i].mine) {
                        return logs[i].userName;
                    }
                    continue;
                }
                if (!logs[i].mine) {

                    userName = logs[i].userName;
                    break;
                }
                if (logs[i].type == 'groupchat') {
                    if (this.lesson[logs[i].to] != undefined) {
                        userName = this.lesson[logs[i].to].name;
                    }
                    continue;
                }
            }
            if (userName == null) {
                if (mine) {
                    userName = this.orgUser.userName;
                } else {
                    userName = this.currentMember.realName == null ? this.currentMember.userName : this.currentMember.realName;
                    userName = userName == null ? '未知用户' : userName;
                }

            }
            return userName;
        },
        /**获取最后一条对话*/
        getContent: function (c) {
           return c[c.length - 1].content.substr(0 ,16);
        },
        //更换对话用户
        changeCurrent: function (event) {
            if($(event.currentTarget).hasClass('on')) {
                return;
            }
            var code = $(event.currentTarget).attr('user-code');
           /* console.log('code:', code)*/
            var current = this.members[$(event.currentTarget).attr('user-code')];

            this.currentMember = current;
            //将用户消息修改为已读
            messageCache.modifyMessageStatus(this.orgUser.userCode, this.currentMember.userCode, true);
            //将当前用户的未读消息数设置为0
            chat.vm.unreadCount[this.currentMember.userCode] = 0;
            //将群成员隐藏
            $('.layer-chat-members').hide(300)
        },
        clickBtn: function (type) {
            switch (type) {
                case '1':
                    $('#message_file_member').click();
                    break;
                case '2':
                    $('#message-file-group-' + this.currentMember.userCode).click();
                    break;
                case '3':
                    $('#message-picture-member-'+ this.currentMember.userCode).click();
                    break;
                case '4':
                    $('#message-picture-group-'+ this.currentMember.userCode).click();
                    break;
                case '5':
                    this.clearCacheCache(this.orgUser.userCode, this.currentMember.userCode);
                    break;
            }
        },
        /**加载更多消息*/
        listMoreMessage: function (event) {
            if (!$(event.currentTarget).hasClass('more-message-on')) {
                return;
            }
            //将每个用户加载的数据页数存在currentMember
            if (this.currentMember.page == null || this.currentMember.page == undefined) {
                this.currentMember.page = 1;
            }
            this.currentMember.page ++;
            chat.listHistory(this.orgUser.userCode, this.currentMember.userCode, this.currentMember.page);
            if (this.chatLog[this.currentMember.userCode].length <
                messageCache.pageSize * this.currentMember.page) {
                $(event.currentTarget).text('无更多消息');
                $(event.currentTarget).css('color', 'black');

                // (event.currentTarget).styleSheets[0].deleteRule(0);
                // $(event.currentTarget).unbind("mouseenter").unbind("mouseleave");
                $(event.currentTarget).removeClass('more-message-on');
            }
        },
        /**显示群成员*/
        showGroupMember: function (c, event) {
            var $obj = $(event.currentTarget);
            if ($obj.hasClass('on')) {
                $obj.css('transform', 'rotate(90deg)');
                $obj.removeClass('on');
                $('.layer-chat-members').hide(300)
                return;
            }
            $obj.addClass('on');
            $obj.css('transform', 'rotate(-90deg)');
            $('.layer-chat-members').show(300)
            this.listLessonMember(this.currentMember.projectCode);
        },
        /**获取群成员*/
        listLessonMember: function (code) {
            var data = {
                code: code,
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/orderManage?listlessonmember',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        console.log('result:', result)
                        chat.vm.lessonMember = result.obj;
                    }
                }
            });
        },
        /**清除聊天记录*/
        clearCacheCache: function (ownerKey, otherKey) {
            var lay = layer.confirm('确定清除该用户的聊天记录?', function () {
                messageCache.removeMessage(ownerKey, otherKey);
                layer.close(lay);
            });
        },
        /** 是否显示时间tip*/
        isShowTimeTip: function (pre, now, next) {
            if (next == null) {
                if (pre == null) {
                    return true;
                }
                if (now.time - pre.time > 30000) {  //5分钟间隔
                    return true;
                }
                return false;
            }
            if (next.time - now.time > 30000) {
                return true;
            }
            return false;
        },
        inSearchMemberList: function(data) {
            var dataUserName = data[0].userName;
            if (chat.vm.searchMember.length == 0) {
                return true;
            }
            for (var i = 0; i < chat.vm.searchMember.length; i++) {
                var curData = chat.vm.searchMember[i][0];
                if (dataUserName == curData.userName) {
                    return true;
                }
            }
            return false;
        }
    },
    computed: {

    },
    watch: {
        /**渲染完chatLog后绑定事件*/
        chatLog: function () {
          this.$nextTick(function () {
              $(".chat-top").not(".chat-top:first").css("display","none");
              $(".chat-nav").find('li').on("click",function(){
                  $(this).addClass("on").siblings().removeClass("on");
                  $(".chat-top").css("display","none").eq($(this).index()).css("display","block");
              });
              $(".chat-close-btn").on("click",function(){
                  $(this).parent("li").remove();
                  $(".chat-top").eq($(this).parent("li").index()).remove()
              })
              $(".chat-nav").find('li[user-code = '+ chat.vm.currentMember.userCode +']').click();  //点击选择current用户

              //绑定inter键
              $('.txt' + chat.vm.currentMember.userCode).unbind('keypress');
              $('.txt' + chat.vm.currentMember.userCode).keypress(function(event){
                  if(event.ctrlKey && event.which == 10) {
                      chat.vm.sendMessage('txt');
                      $('.txt' + chat.vm.currentMember.userCode).focus();
                  }
              });
              var divs = document.getElementsByClassName('chat-item');
              for (var div in divs) {
                  if (divs[div] != null || divs[div] != undefined) {
                      divs[div].scrollTop = divs[div].scrollHeight;
                  }
              }
          });
        },
        members: function () {

        },
        currentMember: function () {
            this.$nextTick(function () {
                /*var divs = document.getElementsByClassName('chat-item');
                for (var div in divs) {
                    if (divs[div] != null || divs[div] != undefined) {
                        divs[div].scrollTop = divs[div].scrollHeight;
                    }
                }*/
            })
        },
        search: function () {
            chat.vm.searchMember = [];
            var searchKeyWord = chat.vm.search;
            if (searchKeyWord == null || searchKeyWord == undefined || searchKeyWord == '') {
                return;
            }
            // 搜索userName里面含有关键字的用户
            var temp = [];
            for (var i in chat.vm.chatLog) {
                var iUserName = chat.vm.chatLog[i][chat.vm.chatLog[i].length - 1].userName;
                if (iUserName.indexOf(searchKeyWord) != -1) {
                    temp.push(chat.vm.chatLog[i]);
                }
            }
            chat.vm.searchMember = temp;
        }

    },
    filters: {
        date: function (value, format, type) {
            if (type != null) {
                if (type === 1) {
                    if (new Date(value).getDay() < new Date().getDay()) {
                        return new Date(value).Format(format);
                    }
                    return new Date(value).Format('hh:mm');
                }
            }
            return new Date(value).Format(format);
        },
        subStr: function (value, length, isShowTip) {
            if (value == null || value == undefined) {
                return null;
            }
            if (isShowTip) {
                if (value.length < length) {
                    return value.substr(0, length);
                }
                return value.substr(0, length) + '...';
            }
            return value.substr(0, length);
        }
    }
});

/**获取历史记录*/
chat.listHistory = function (ownerUserCode, otherUserCode, page) {
    console.log('inter list-history')
    chat.vm.unreadCount[otherUserCode] = 0; // 未读消息清零
    // chat.vm.chatLog[otherUserCode] = messageCache.getMessage(ownerUserCode, otherUserCode, page);
//    chat.vm.chatLog = messageCache.getUnreadMessage(ownerUserCode);
    chat.vm.chatLog = messageCache.getRecentMessage(ownerUserCode, 100);    // 获取最近的100条消息
    if (chat.vm.chatLog == null) {
        chat.vm.chatLog = [];
    } else {
        // TODO 可能后期需要改进
        var nwChatLog = {};
        // 过滤用户自己的聊天记录
        for (var code in chat.vm.chatLog) {
            if (code == ownerUserCode) {
                continue;
            }
            nwChatLog[code] = chat.vm.chatLog[code];
        }
        chat.vm.chatLog = nwChatLog;
    }
    //将有未读信息的用户加入members
    for (var i in chat.vm.chatLog) {
        var bool = false;
        for (var j in chat.vm.members) {
            if (i != j) {
                bool = true;
            }
            if (bool) {
                var member = {
                    userCode: i,
                    userName: chat.vm.chatLog[i][0].userName,
                    head: chat.vm.chatLog[i][0].head,
                    type: chat.vm.chatLog[i][0].type
                }
                if (member.type == 'groupchat') {
                    chat.getLessonByHuanxinId(member.userCode);
                    member.projectCode = chat.vm.lesson[member.userCode].code;
                }
                chat.vm.members[i] = member;

                var unreadCount = messageCache.getUnreadCount(ownerUserCode, i);
                chat.vm.unreadCount[i] = unreadCount;
            }
        }
    }
    for (var i in chat.vm.members) {
        chat.vm.chatLog[i] = messageCache.getMessage(ownerUserCode,i, page);
    }
    chat.vm.chatLog[otherUserCode] = messageCache.getMessage(ownerUserCode, otherUserCode, page);
    if (chat.vm.chatLog[otherUserCode] == null) {
        chat.vm.chatLog[otherUserCode] = [];
        var from = otherUserCode;
        var to = ownerUserCode;
        if (chat.vm.currentMember.type == 'groupchat') {
            to = otherUserCode;
            from = ownerUserCode;
        }
        var message = {
            "content": "用户已连通!",
            "id": null,
            "from": from,
            "to": to,
            "type": chat.vm.currentMember.type,
            "isRead": true,
            "time": new Date().getTime(),
            "mine": chat.vm.currentMember.type == 'groupchat' ? true : false,
            "head": chat.vm.currentMember.photo,
            "userName": chat.vm.currentMember.realName == null ? chat.vm.currentMember.userName : chat.vm.currentMember.realName,
            "isSuccess": 'true',
            "speak0": true
        }
        // chat.vm.chatLog[otherUserCode].push(message);
        var temp = {};
        temp[otherUserCode] = [];
        temp[otherUserCode].push(message);
        for (var i in chat.vm.chatLog) {
            if (i == otherUserCode) {
                continue;
            }
            temp[i] = chat.vm.chatLog[i];
        }
        chat.vm.chatLog = Object.assign({}, chat.vm.chatLog, temp);
        /*if (chat.vm.currentMember.type == 'groupchat') {
            messageCache.putMessage(otherUserCode,ownerUserCode , message);
            return;
        }*/
        messageCache.putMessage(ownerUserCode, otherUserCode, message);

    }
}

/**缓存监听*/
chat.cacheListening = function () {

    var SetItem = localStorage.setItem;
    localStorage.setItem = function(key,value,isListen) {
     var setItemEvent = new Event("setItemEvent2");
     setItemEvent.value = value;
     setItemEvent.key = key;
     if (!isListen) {
         return;
     }
     window.dispatchEvent(setItemEvent);
     SetItem.apply(this,arguments);

     }
    chat.storage = function(e) {
        setTimeout(function () {
            // 检测是否为需要监听的key值
            if (e.key == messageCache.key) {
               /* console.log('inter cache')*/
                var json = JSON.parse(e.value)[chat.vm.orgUser.userCode];

                for (var i in json) {
                    var bool = false;
                    for (var j in chat.vm.chatLog) {
                        if (i == j) {
                            bool = true;
                            continue;
                        }
                        if (!bool) {
                            var unread = messageCache.getUnreadCount(chat.vm.orgUser.userCode, i);
                            if (unread < 1) {
                                continue;
                            }
                            chat.vm.chatLog[i] = [];
                            var member = {
                                userCode: i,
                                userName: json[i][0].userName,
                                head: json[i][0].head,
                                type: json[i][0].type
                            }
                            if (member.type == 'groupchat') {
                                chat.getLessonByHuanxinId(member.userCode);
                                member.projectCode = chat.vm.lesson[member.userCode].code;
                            }
                            chat.vm.members[i] = member;
                        }
                        bool = false;
                        break;
                    }
                }
                //将用户消息修改为已读
                //messageCache.modifyMessageStatus(chat.vm.orgUser.userCode, chat.vm.currentMember.userCode, true);

                var temp = {};
                for (var i in chat.vm.chatLog) {
                    temp[i] = messageCache.getMessage(chat.vm.orgUser.userCode, i, 1);
                    if (i == chat.vm.currentMember.userCode) {
                        continue;
                    }
                    var unreadCount = messageCache.getUnreadCount(chat.vm.orgUser.userCode, i);
                    chat.vm.unreadCount[i] = unreadCount;
                }
                chat.vm.chatLog = Object.assign({}, chat.vm.chatLog, temp)
            }
        }, 300);

    }
    // window.addEventListener("setItemEvent2", chat.storage);
}


chat.file = function (selector) {
    chat.vm.sendMessage('file', selector);
}

chat.picture = function (selector) {
    chat.vm.sendMessage('picture', selector);
};

/**根据环信id获取课堂信息*/
chat.getLessonByHuanxinId = function (huanxinId) {
    for (var i in chat.vm.lesson) {
        if (i == huanxinId) {
            return;
        }
    }
    var data = {
        huanxinId: huanxinId
    }
    $.ajax({
        async : false,
        cache : false,
        type: 'POST',
        url: '/org/service/get-lesson-by-huanxinId',
        data: data,
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        dataType: 'json',
        beforeSend:function(){
            // layer.load();
        },
        success: function (result) {
            // layer.closeAll('loading');
            // layer.msg(result.msg);
            if(result.success){
                chat.vm.lesson[result.obj.huanxinId] = result.obj;
            }
        }
    });
}

//测试
chat.add = function () {
    var otherUserCode = 1001003;
    // chat.vm.chatLog[otherUserCode] = [];
    var message = {
        "content": "测试数据!",
        "id": null,
        "from": otherUserCode,
        "to": chat.vm.orgUser.userCode,
        "type": 'chat',
        "isRead": false,
        "time": new Date().getTime(),
        "mine": false,
        "head": 'head',
        "userName": '测试',
        "isSuccess": true,
    }
    /*var temp = {};
    temp[otherUserCode] = [];
    temp[otherUserCode].push(message);
    for (var i in chat.vm.chatLog) {
        if (i == otherUserCode) {
            continue;
        }
        temp[i] = chat.vm.chatLog[i];
    }
    chat.vm.chatLog = Object.assign({}, chat.vm.chatLog, temp);*/
    messageCache.putMessage(chat.vm.orgUser.userCode, otherUserCode, message);
}
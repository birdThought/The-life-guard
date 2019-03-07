var message = {};

//$('.news')
message.init = function () {
    pageUtil.getPageUtil();
    message.vm.listRefundOrder(1);

}
message.vm = new Vue({
    el: '.vue-content',
    data: {
        systemMsg: {},
        systemMsgData: null,
        systemCount: null,
        systemUnreadCount: 0,
        systemChecked: [],
        serveMessage: null,
        serveUnreadCount: 0,
        serveChecked: [],
        refundMsg: null,
        refundMsgData: null,
        refundUnreadCount: 0,
        refundChecked: [],
        currentRefund: null,
        storePushMsgData: null,
        storePushMsg: null,
        checked: false,
        orgUser: null,
        orgType: null,
        userType: null,
        currentMessage: null,
        recentCount: 5, //最近联系的几位用户

    },
    methods: {
        /**删除全部*/
        deleteAll: function () {
            var system = $('.system-message').hasClass('current');
            var serve = $('.serve-message').hasClass('current');
            var refund = $('.refund-message').hasClass('current');
            if (system) { //系统
                if (this.systemChecked.length == 0) {
                    layer.msg('请勾选删除消息')
                    return;
                }
                layer.confirm('确定删除这'+ message.vm.systemChecked.length +'条消息？', {icon: 2, title:'提示'}, function(index){
                    $.ajax({
                        async : true,
                        cache : false,
                        type: 'DELETE',
                        url: '/message/services',
                        contentType: 'application/json;charset=utf-8',
                        dataType: 'json',
                        data: JSON.stringify(message.vm.systemChecked),
                        beforeSend: function() {
                            layer.load();
                        },
                        success: function (result) {
                            layer.closeAll('loading');
                            if(result.success){
                                message.vm.systemMsg = result.obj;
                                layer.msg("删除成功");
                            }
                        }
                    });
                    layer.close(index);
                });
                return;
            }
            if (serve) {
                if (this.serveChecked.length == 0) {
                    layer.msg('请勾选删除消息')
                    return;
                }
                layer.confirm('确定删除消息？', {icon: 2, title:'提示'}, function(index){
                    layer.close(index);
                    for (var i in message.vm.serveChecked) {
                        //将消息修改为已读状态
                        messageCache.modifyMessageStatus(message.vm.orgUser.userCode, message.vm.serveChecked[i], true);
                    }
                    //重新获取登录用户的未读信息
                    message.vm.serveMessage = messageCache.getUnreadMessage(message.vm.orgUser.userCode);
                });
                return;
            }
            if (refund) { //退款
                if (this.refundChecked.length == 0) {
                    layer.msg('请勾选删除消息')
                    return;
                }
                layer.confirm('确定删除这'+ message.vm.refundChecked.length +'条消息？', {icon: 2, title:'提示'}, function(index){
                    /*$.ajax({
                        async : true,
                        cache : false,
                        type: 'DELETE',
                        url: '/message/services',
                        contentType: 'application/json;charset=utf-8',
                        dataType: 'json',
                        data: JSON.stringify(message.vm.systemChecked),
                        beforeSend: function() {
                            layer.load();
                        },
                        success: function (result) {
                            layer.closeAll('loading');
                            if(result.success){
                                message.vm.systemMsg = result.obj;
                                layer.msg("删除成功");
                            }
                        }
                    });*/
                    layer.close(index);
                });
                return;
            }


        },
        popupMsgDialog: function(index) {
            var msg = this.systemMsg.data[index];
            layer.open({
                type: 1,
                title: msg.title,
                skin: 'layui-layer-rim',
                area: ['726px'],
                zIndex: 999,
                content: "<div style='padding: 10px 20px;min-height: 500px;'><p>" + msg.content + "</p></div>",
                success: function() {
                    if (!msg.read) {
                        $.ajax({
                            type: 'PUT',
                            url: '/message/read/' + msg.id,
                            cache: false,
                            success: function(result) {
                                if (result.success) {
                                    msg.read = true;
                                    message.vm.systemUnreadCount--;
                                    message.vm.updateNewsBtn();
                                } else {
                                    layer.msg("失败了");
                                }
                            }
                        });
                    }
                }
            });
        },
        /**弹出消息框*/
        popupChatDialog: function (index) {
            /*if ($('.news') != undefined) {
                var count = this.systemUnreadCount + this.refundUnreadCount;
                $('.news').text(count);
            }*/
            var currentMember = {
                userCode: index,
                type: this.serveMessage[index][this.serveMessage[index].length - 1].type
            }
            chat.initChatDialog(this.serveMessage, this.orgUser, currentMember, function () {
                message.vm.serveMessage = messageCache.getRecentMessage(message.vm.orgUser.userCode, message.vm.recentCount);
            });
            // message.vm.serveMessage = null;
        },
        isNoMessage: function (obj) {
            if (obj == null) {
                return true;
            }
            if (obj.length < 1) {
                return true;
            }
            for (var i in obj) {
                if (obj[i].length > 0) {
                    return false;
                }
            }
            return true;
        },
        getContent: function (value) {
            return value;
        },
        /**获取退款信息*/
        listRefundOrder: function (page) {
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/message/list-order-message/' + page,
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    // layer.load();
                },
                success: function (result) {
                    // layer.closeAll('loading');
                    if(result.success){
                        message.vm.refundMsg = result.obj;
                    }
                }
            });
        },
        /**弹出退款处理信息框*/
        popupRefundDialog: function (orderNumber, m) {
            this.currentMessage = m;
            this.getRefundDetails(orderNumber);
            layer.open({
                type: 1,
                title: ['退款处理中心', 'text-align: center; font-size: 18px;'],
                // skin: 'layui-layer-rim',
                area: ['660', '800px'],
                offset: ['11%', '34%'],
                zIndex: 999,
                content: $('.dialog-refund'),
                success: function() {
                }
            });
        },
        /**确认退款*/
        confirmRefund: function () {
            var lay = layer.confirm('确认退款？', {offset: '50%'}, function () {
                var data = {
                    orderNumber: message.vm.currentRefund.orderNumber,
                    orderId: message.vm.currentRefund.id
                }
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'POST',
                    data: data,
                    url: '/order/confirm-refund-order',
                    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load();
                    },
                    success: function (result) {
                        layer.closeAll();
                        if(result.success){
                            layer.msg(result.msg, {icon: 1});
                            //message.vm.changeStatusToRead(message.vm.currentMessage.id);
                            message.vm.listRefundOrder(1);
                            message.vm.changeStatusToRead(message.vm.currentMessage.id);
                            return;
                        }

                        layer.msg('退款失败', {icon: 2, offset: '50%'});
                    }
                });
            })
        },
        /**获取退款信息详情*/
        getRefundDetails: function (orderNumber) {
                var data = {
                    orderNumber: orderNumber
                }
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'GET',
                    data: data,
                    url: '/order/refund/details',
                    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load();
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if(result.success){
                            message.vm.currentRefund = result.obj;
                            return;
                        }
                    }
                });
        },
        /**将状态设为已读*/
        changeStatusToRead: function (id) {
            $.ajax({
                type: 'PUT',
                url: '/message/read/' +id,
                cache: false,
                success: function(result) {

                }
            });
        },
        /**更新消息tip消息数目*/
        updateNewsBtn: function () {
            var count = this.systemUnreadCount + this.serveUnreadCount + this.refundUnreadCount;
            // count = count > 99 ? '99+' : count;
            // $('.news').text(count);
            common.updateNewsTips(count);
            if (count == 0) {
                $('.news').addClass("hidden");
                return;
            }
            $('.news').removeClass('hidden');

        },
        getHead: function (m) {
            if (m[m.length - 1].type == 'groupchat') {
                if (chat.vm.lesson[m[m.length - 1].to] == null) {
                    chat.getLessonByHuanxinId(m[m.length - 1].to);
                }
                return chat.vm.lesson[m[m.length - 1].to].image;
            }
            return m[m.length - 1].head
        },
        getUserName: function (m) {
            if (m[m.length - 1].type == 'groupchat') {
                if (chat.vm.lesson[m[m.length - 1].to] == null) {
                    chat.getLessonByHuanxinId(m[m.length - 1].to);
                }
                return chat.vm.lesson[m[m.length - 1].to].name;
            }
            return m[m.length - 1].userName
        }
    },
    computed: {
        serveUnreadCount: function () {
            if (this.serveMessage == null) {
                return 0;
            }
            var len = null;
            for (var key in this.serveMessage) {
                len += this.serveMessage[key].length;
            }
            return len;
        }
    },
    watch: {
        /**监听checked(全选)*/
        checked: function () {
            if (this.checked) {
                var system = $('.system-message').hasClass('current');
                var serve = $('.serve-message').hasClass('current');
                var refund = $('.refund-message').hasClass('current');
                if (system) {
                    var arr = new Array();
                    for (var i = 0; i < this.systemMsgData.length; i ++) {
                        arr.push(this.systemMsgData[i].id);
                    }
                    this.systemChecked = arr;
                    return;
                }
                if (serve) {
                    var arr = new Array();
                    for (var key in this.serveMessage) {
                        arr.push(key);
                    }
                    this.serveChecked = arr;
                    return;
                }
                if (refund) {
                    var arr = new Array();
                    for (var i = 0; i < this.refundMsgData.length; i ++) {
                        arr.push(this.refundMsgData[i].id);
                    }
                    this.refundChecked = arr;
                    return;
                }
            } else {
                var system = $('.system-message').hasClass('current');
                var serve = $('.serve-message').hasClass('current');
                var refund = $('.refund-message').hasClass('current');
                if (system) {
                    this.systemChecked = [];
                }
                if (serve) {
                    this.serveChecked = [];
                }
                if (refund) {
                    this.refundChecked = [];
                }
            }
        },
        systemMsg: function() {
            /** 初始化数据 */
            this.systemCount = this.systemMsg.total;
            this.systemMsgData = this.systemMsg.data;
            /*this.systemUnreadCount = 0;
            for (var i in this.systemMsgData) {
                if (this.systemMsgData[i].read == 0) {
                    this.systemUnreadCount ++;
                }
            }*/
            if (this.systemMsgData == undefined) {
                this.systemMsgData = [];
            }
            /** 全部勾选按钮取消 */
            this.checked = false;

            this.$nextTick(function () {
                $(".news-content ol").not("ol:first").css("display","none");
                $(".news-tab li").on("click",function(){
                    $(this).addClass("current").siblings().removeClass("current");
                    $(".news-content ol").css("display","none").eq($(this).index()).css("display","block");
                });
                // 更新菜单的高度
                common.addBorder();

            })

            if ( this.systemMsg.total == 0) {   //没数据时不显示分页
                return;
            }
            if (pageUtil.systemMsgPage != null) {
                pageUtil.systemMsgPage.getPageControl().totalPage = message.vm.systemMsg.totalPage;
                pageUtil.systemMsgPage.getPageControl().selectPage(message.vm.systemMsg.curPage, true);
            }

            message.vm.updateNewsBtn();
        },
        /**门店推送消息*/
       /* storePushMsgData: function () {
           console.log('this.storePushMsgData:', this.storePushMsgData);
        }*/
        refundMsg: function () {
            this.refundMsgData = this.refundMsg.data;
            // this.refundUnreadCount = 0;
            for (var i in this.refundMsgData) {
                /*if (this.refundMsgData[i].read == 0) {
                    this.refundUnreadCount ++;
                }*/
                var arr = this.refundMsgData[i].content.split(';');
                this.refundMsgData[i].orderNumber = arr[0];
                this.refundMsgData[i].realName = arr[1];
            }
            if ( this.refundMsg.totalPage == 0) {   //没数据时不显示分页
                return;
            }
            pageUtil.getRefundPage();
            if (pageUtil.refundMsgPage != null) {
                pageUtil.refundMsgPage.getPageControl().totalPage = message.vm.refundMsg.totalPage;
                pageUtil.refundMsgPage.getPageControl().selectPage(message.vm.refundMsg.nowPage, true);
            }
            message.vm.updateNewsBtn();
        },

    },
    created: function() {
        /*var count = $('.news').text();
        this.systemUnreadCount = count;*/
    },
    updated: function() {



    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        },
        subStr: function (value, length) {
            if (value.length < length) {
                return value;
            }
            return value.substr(0, length);
        },
        status: function (value) {
            var msg = '';
            switch (value) {    //0已完成,1待退款,2已审核,3退款失败'
                case 0:
                    msg = '已完成';
                    break;
                case 1:
                    msg = '待退款';
                    break;
                case 2:
                    msg = '已审核';
                    break;
                case 3:
                    msg = '退款失败';
                    break;
            }
            return msg;
        }
        
    }
});

/*
 * 分页工具
 */
var pageUtil = {
    systemMsgPage: null,
    refundMsgPage: null,
    getPageUtil: function () {
        if (this.systemMsgPage == null) {
            this.systemMsgPage = new PageUtil();
            this.systemMsgPage.getPageControl().init({
                container: 'systemPageManager',
                preBtn: 'pre_systemMsg',
                nextBtn: 'next_systemMsg',
                totalPage: message.vm.systemMsg.totalPage,
                pageChange: function (page) {
                    $.ajax({
                        async : true,
                        cache : false,
                        type: 'GET',
                        url: '/message/service/' + page,
                        contentType: 'application/json;charset=utf-8',
                        dataType: 'json',
                        beforeSend:function(){
                            layer.load();
                        },
                        success: function (result) {
                            layer.closeAll('loading');
                            if(result.success){
                                message.vm.systemMsg = result.obj;
                            }
                        }
                    });
                }
            });
        }
    },
    getRefundPage: function () {
        if (this.refundMsgPage == null) {
            this.refundMsgPage = new PageUtil();
            this.refundMsgPage.getPageControl().init({
                container: 'refundPageManager',
                preBtn: 'pre_systemMsg',
                nextBtn: 'next_systemMsg',
                totalPage: message.vm.refundMsg.totalPage,
                pageChange: function (page) {
                    message.vm.listRefundOrder(page);
                }
            });
        }
    }
}
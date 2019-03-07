/**
 * 推送管理
 * author: wenxian.cai
 * date: 2017/8/21 14:50
 */

var push = {};

push.init = function () {
    push.vm.listStatisticsDetails();

    //短信充值套餐选择
    $('.propelling-type-bottom-cz .Cz').on('click', function () {
        $(this).addClass('on').siblings().removeClass('on')
    });
    $('.propelling-type-bottom-cz .Cz').eq(0).click();
    $('.msgUrl').css('display', 'none');
    $('.service-project').css('display', 'none');
    
    $('.select-spec-orguser').find('input').removeAttr("readonly");

    //搜索框绑定inter键
    // $('.search-mobile').unbind('keypress');
    setTimeout(function () {
        $('.search-mobile').keydown(function(event){
            if(event.which == 13) {
                push.vm.listStatisticsDetails();
                push.vm.checkAll = false;
                push.vm.selectedUserId = [];
            }
        });
    }, 1000);



}

push.vm = new Vue({
    el: '.vue-content',
    data: {
        project: [],
        diseases: [],
        results: {},
        allUserData: {},
        selectedUserId: [],
        checkAll: null, //全选
        page: 1,
        pageSize: 15,
        pageObj: null, //分页对象
        ageSegment: null,
        orgUserList:null,
        serviceUserInfo:null,
        
        sentCheckAll: null,

        search: {
            projectCode: -1,
            diseasesId: -1,
            gender: -1,
            startAge: -1,
            endAge: -1,
            mobile: null
        },
        appMsg: {
            message: '',
            size: 100,
            openType:'',
            serviceUser:'',
//            serviceUserInfo:'',
        },
        smsMsg: {
            message: '',
            size: 70,
            remainCount: 0, //可用条数
            orderNumber: null,
            qrCode: null,
            payCount: 0,    //充值条数
            payPrice: 0,    //充值价格
        },

        sent: {
            page: 1,
            pageSize: 12,
            pageObj: null,
            data: {},
            selectId: [],
            currentMsg: {},
        },
        smsTemplate: {
            data: {
                '服务到期提醒': [
                    '尊敬的*，您的账户余额仅剩*，为了确保您的正常使用，请及时充值。',
                    '尊敬的*，您的订购的预警短信仅剩*条，为了确保您的正常使用，请及时充值。',
                    '尊敬的*，您的订购的**咨询服务仅剩*天，如需*医生持续为您服务请继续订购服务，服务链接****。',
                    '尊敬的*，您的订购的*医生的**课堂服务仅剩*天，如需**医生持续为您服务请继续订购其课堂服务，服务链接****。',
                    '尊敬的*，您的订购的**套餐服务仅剩*天，如需续购请点击链接****。'
                ],
                '优惠提醒': [
                    // '尊敬的*，现在我平台**套餐做特惠活动，活动从*年*月*日到*年*月*日，活动不限名额，期待您的参与。参与链接****。',
                    // '尊敬的*，现在我平台**套餐做特惠活动，活动从*年*月*日到*年*月*日，活动限*位幸运名额，先到先得，我们期待您的参与。参与链接****。',
                    // '尊敬的*，您有**未领取，仅限*天参与活动才可领取，逾时将无法再享受，请点击链接****。',
                    // '【优惠提醒】尊敬的*，从*年*月*日到*年*月*日，您可参与一次**优惠！活动期内在我平台消费满*元，即可赠送**，本次赠送活动不叠加参与其他优惠，短信转发无效。'
                ],
                '通知提醒': [
                    '尊敬的*，您订购的**套餐已经生效，可在生效后**使用。',
                    '尊敬的*，您已成功充值*元短信，现可用预警短信条数为*条，短信预警功能即刻生效。',
                    '尊敬的*，感谢您使用生命守护，您的账号已设置完毕，祝您使用愉快。',
                    '尊敬的*，截至* 年*月*日，您账户上还有*元。'
                ],
                '咨询信息': [
                    // '我们给您准备的**的资讯，欢迎阅读。'
                ],
                '节日关怀信息': [
                    '尊敬的*，今天是**节，祝您****。'
                ]
            },
            type: '',
            currentData: null,
            selectedId: [],
            template: null,
        }
    },
    methods: {
        /**获取详细数据统计列表*/
        listStatisticsDetails: function () {

            var url = '/org/data-statistics/list-data-statistics-details/' + this.page;
            http.ajax.get(true, true, url, this.search, http.ajax.CONTENT_TYPE_1, function (results) {
                push.vm.results = results.obj;
                for (var i in push.vm.results.data) {
                    push.vm.allUserData[push.vm.results.data[i].userId + '_' + push.vm.results.data[i].projectCode] = push.vm.results.data[i];
                }
                if (push.vm.search.startAge == -1 || push.vm.search.endAge == -1) {
                    push.vm.ageSegment = '不限';
                } else {
                    push.vm.ageSegment = push.vm.search.startAge + '-' + push.vm.search.endAge;
                }
                //初始化分页
                push.vm.initPage(2);
            })

        },
        /**获取全部数据*/
        listAllStatisticsDetails: function () {
            push.vm.allUserData = [];
            for (var i = 1; i <= this.results.totalPage; i ++) {
                var url = '/org/data-statistics/list-data-statistics-details/' + i;
                http.ajax.get(false, true, url, this.search, http.ajax.CONTENT_TYPE_1, function (result) {
                    var temp = result.obj.data;
                    for (var j in temp) {
                        push.vm.allUserData[temp[j].userId + '_' + temp[j].projectCode] = temp[j];
                    }
                });
            }

        },
        searchService : function(){
            var url = '/orgUserControl/getEmployListByRealName';
            var data = {
                    rName: this.appMsg.serviceUser,
            }
            
            http.ajax.get_no_loading(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if(push.vm.orgUserList !=null){
                    push.vm.orgUserList = null;
                    push.vm.serviceUserInfo = new Array();
                }
                push.vm.orgUserList = result.obj;
            });
        },
        
        
        /**搜索*/
        searchStatistics: function (type) {
            var search = this.search;
            if ((search.startAge == -1 && search.endAge != -1) ||
                (search.startAge != -1 && search.endAge == -1)) {
                layer.msg('请选择年龄段', {icon: 5});
                return;
            }
            switch (type) {
                case 1:
                    // this.listStatistics();
                    break;
                case 2:
                    this.listStatisticsDetails();
                    break;
            }
            this.checkAll = false;
            this.selectedUserId = [];
            // this.selectedUser = [];
        },
        /**初始化分对象*/
        initPage: function (type) {
            if (this.pageObj == null) {
                this.pageObj = new PageUtil();
                this.pageObj.getPageControl().init({
                    container: 'page-container',
                    preBtn: 'pre_rec',
                    nextBtn: 'next_rec',
                    totalPage: push.vm.results.totalPage,
                    pageChange: function (page) {
                        push.vm.page = page;
                        switch (type) {
                            case 1:
                                // push.vm.listStatistics();
                                break;
                            case 2:
                                push.vm.listStatisticsDetails();
                                break;
                        }
                    }
                });
            }
            this.pageObj.getPageControl().totalPage = this.results.totalPage;
            this.pageObj.getPageControl().selectPage(this.page, true);
            if (push.vm.results.totalSize == 0) {
                $('#page-container').css('display', 'none');
                return;
            }
            $('#page-container').css('display', 'block');

        },
        /*弹出推送方式选择框*/
        popupSendMsgDialog: function () {
            if (this.selectedUserId.length < 1) {
                layer.msg('至少选择一个用户', {icon: 5});
                return;
            }
            this.lay = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                // area: ["1000px", "800px"],
                offset: ['30%', '51%'],
                closeBtn: false,
                content: $('.push-type')
            });
        },
        /*关闭推送方式选择框*/
        closeSendMsgDialog: function () {
            layer.close(this.lay);
        },
        closeLay2: function () {
            layer.close(this.lay2);
        },
        /*弹出app推送方式框*/
        popupAppMsgDialog: function () {
            this.closeSendMsgDialog();
            this.lay = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                // area: ["1000px", "800px"],
                offset: ['30%', '51%'],
                closeBtn: false,
                content: $('.push-app')
            });
        },
        /*弹出sms推送方式框*/
        popupSmsMsgDialog: function () {
            this.getSmsRemain();
            this.closeSendMsgDialog();
            this.lay = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                // area: ["1000px", "800px"],
                offset: ['30%', '51%'],
                closeBtn: false,
                content: $('.push-sms')
            });
        },
        /*弹出sms模板选择框*/
        popupSmsTemplateDialog: function () {
            this.lay2 = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                // area: ["1000px", "800px"],
                offset: ['30%', '51%'],
                closeBtn: false,
                content: $('.push-sms-template')
            });
        },
        /**确认sms模板*/
        confirmSmsTemplate: function () {
            if (this.smsTemplate.selectedId.length < 1) {
                layer.msg('请选择模板', {icon: 5});
                return;
            }
            this.smsTemplate.template = this.smsTemplate.currentData[this.smsTemplate.selectedId];
            this.smsMsg.message = this.smsTemplate.template;

            this.smsMsg.message = this.smsMsg.message
                .replace(/\*\*\*\*/g, '<input type="text" ' +
                '\style="border: none; border-bottom: 1px solid #9a9a9a; width: 200px; text-align: center;">')
                .replace(/\*\*\*/g, '<input type="text" ' +
                '\style="border: none; border-bottom: 1px solid #9a9a9a; width: 150px; text-align: center;">')
                .replace(/\*\*/g, '<input type="text" ' +
                '\style="border: none; border-bottom: 1px solid #9a9a9a; width: 100px; text-align: center;">')
                .replace(/\*/g, '<input type="text" ' +
                '\style="border: none; border-bottom: 1px solid #9a9a9a; width: 50px; text-align: center;">');


            this.$nextTick(function () {
                var name = null;
                for (var i in push.vm.selectedUser) {
                    name = push.vm.selectedUser[i].realName || push.vm.selectedUser[i].userName;
                    break;
                }
                var len = Object.getOwnPropertyNames(push.vm.selectedUser).length;
                var text = len > 1 ? name + ';...' : name;
                $('.sms-message-content').find('input').eq(0).val(text);
            })
            layer.close(this.lay2);
        },
        /**发送app推送消息*/
        appSendMsg: function () {
            layer.load(2);
            if(typeof(this.appMsg.title)=="undefined" || this.appMsg.title == ""){
                layer.closeAll('loading');
                layer.msg('请填写标题!', {icon: 5});
                $('.message-title').focus();
                return;
            }
            
            var paramValue = "";
            var url = '/org/push/push-store-message';
            var arr = [];
            for (var i in this.selectedUser) {
                arr.push(this.selectedUser[i].userId);
            }
            
            if(typeof(this.appMsg.openType)=="undefined" || this.appMsg.openType==""){
                layer.closeAll('loading');
                layer.msg('请选择消息类型.', {icon: 5});
                $('.openType').focus();
                return;
            }
            var openTypeValue = parseInt(this.appMsg.openType);
            if(3 == openTypeValue){
                if(typeof(this.appMsg.paramUrl)=="undefined" || typeof(this.appMsg.paramUrl)==""){
                        layer.closeAll('loading');
                        layer.msg('请设置URL.', {icon: 5});
                        $('.paramUrl').focus();
                        return;
                } else{
                    paramValue = this.appMsg.paramUrl;
                }
            }else if(2 == openTypeValue){
                if(typeof(this.appMsg.appType)=="undefined" || typeof(this.appMsg.appType)==""){
                    layer.closeAll('loading');
                    layer.msg('请选择页面类型.', {icon: 5});
                    $('.appType').focus();
                    return;
                }else if (this.appMsg.serviceUser == "" || typeof(this.appMsg.serviceUser)=="undefined" 
                    || typeof(this.serviceUserInfo)=="undefined" || this.serviceUserInfo==""){
                    layer.closeAll('loading');
                    layer.msg('请选择服务师.', {icon: 5});
                    $('.serviceUserInfo').focus();
                    return;
                }else{
                    paramValue = this.appMsg.appType;
                }
            }
            
            if (this.appMsg.message.length < 1) {
                layer.closeAll('loading');
                layer.msg('请输入消息内容', {icon: 5});
                return;
            }
            if (this.appMsg.message.length > this.appMsg.size) {
                layer.closeAll('loading');
                layer.msg('消息字数超出限制', {icon: 5});
                $('.message-content').focus();
                return;
            }
            
            
            var data = {
                ids: arr,
                content: this.appMsg.message,
                openType: openTypeValue,
                paramUrl: paramValue,
                title:this.appMsg.title,
                params:this.serviceUserInfo,
                projectCode:this.search.projectCode,
                diseasesId:this.search.diseasesId,
                gender:this.search.gender,
                startAge:this.search.startAge,
                endAge:this.search.endAge,
                mobile:this.search.mobile,
                checkAll:this.checkAll //是否全选
            }
            http.ajax.post(true, true, url, data,http.ajax.CONTENT_TYPE_1,function (result) {
                layer.closeAll('loading');
                layer.msg(result.obj+'条信息'+result.msg, {icon: 1});
                layer.close(push.vm.lay);
            })
            layer.closeAll('loading');
        },
        /**发送sms推送消息*/
        sendSmsMsg: function () {
            /*if (this.smsMsg.message.length < 1) {
                layer.msg('请输入消息内容', {icon: 5});
                return;
            }
            if (this.smsMsg.message.length > this.smsMsg.size) {
                layer.msg('消息字数超出限制', {icon: 5});
                $('.sms-message-content').focus();
                return;
            }*/
            var msg = this.smsTemplate.template.replace(/\*\*\*\*/, '*').replace(/\*\*\*/, '*').replace(/\*\*/, '*');
            var childLen = $('.sms-message-content').children('input').length;
            var arr1 = msg.split('*');
            var temp = arr1[0] + '*';
            for (var i = 1; i <= childLen; i ++) {
                temp += arr1[i] + ($('.sms-message-content').children('input').eq(i).val() || '');
            }
            var arr = [];
            var nameArr = [];
            var idArr = [];
            for (var i in this.selectedUser) {
                arr.push(this.selectedUser[i].mobile);
                nameArr.push(this.selectedUser[i].realName || this.selectedUser[i].userName);
                idArr.push(this.selectedUser[i].userId);
            }
            var url = '/org/push/send-store-sms-message';
            var data = {
                mobiles: arr,
                content: temp,
                names: nameArr,
                ids: idArr
            }
            http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                layer.msg(result.msg, {icon: 1});
                layer.close(push.vm.lay);
            })
        },
        /**获取短信剩余条数*/
        getSmsRemain: function () {
            var url = '/org/push/get-store-sms-remain';
            http.ajax.get_no_loading(true, true, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
                push.vm.smsMsg.remainCount = result.obj;
            });
        },
        /**创建短信订单*/
        createSmsOrder: function () {
            this.smsMsg.payCount = $('.pay-count.on').attr('value');
            this.smsMsg.payPrice = $('.pay-count.on b').attr('value');
            var url = '/org/push/create-store-sms-order';
            var data = {
                charge: this.smsMsg.payPrice,
                count: this.smsMsg.payCount
            };
            http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {

                push.vm.orderNumber = result.obj.orderNumber;
                //跳转到支付页面
                // push.vm.popupPayTwoDialog();
                window.location.href = '/commonControl?alipay&order=' + push.vm.orderNumber +
                    '&cash=' + result.obj.charge + '&subject=短信充值&payType=1';
            });
        },
        /**判断短信充值订单状态是否已完成*/
        isSmsOrderComplete: function (orderNumber) {
            var url = '/org/push/is-store-sms-order-complete';
            var data = {
                orderNumber: push.vm.orderNumber
            };
            http.ajax.post_no_loading(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if (result.obj) {
                    //清除请求
                    window.clearInterval(push.vm.times);
                    //跳转到成功页面
                    push.vm.popupPayThreeDialog();
                }

            });
        },
        /**充值页面1*/
        popupPayOneDialog: function () {
            layer.close(this.lay);
            this.lay = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                // area: ["1000px", "800px"],
                offset: ['30%', '51%'],
                closeBtn: false,
                content: $('.sms-pay-one')
            });
        },
        /**充值页面2*/
        /*popupPayTwoDialog: function () {
            layer.close(this.lay);
            this.lay = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                // area: ["1000px", "800px"],
                offset: ['30%', '59%'],
                closeBtn: false,
                content: $('.sms-pay-two'),
                success: function () {
                    setTimeout(function () {
                        push.vm.times = window.setInterval(function() {
                            push.vm.isSmsOrderComplete()
                        }, 2000);
                    }, 3000);

                }
            });
        },*/
        /**充值页面3*/
        /*popupPayThreeDialog: function () {
            layer.close(this.lay);
            this.lay = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                // area: ["1000px", "800px"],
                offset: ['30%', '59%'],
                closeBtn: false,
                content: $('.sms-pay-three')
            });
            setTimeout(function () {
                // layer.close(push.vm.lay);
                push.vm.popupSmsMsgDialog();
            }, 3000);
        },*/
        /**查看已发送信息*/
        lookSentMsg: function (event) {
            if ($(event.currentTarget).hasClass('on')) {
                return;
            }
            $(event.currentTarget).addClass('on');
            this.listSentMessage();
            $('.search-btn').attr('disabled', true);
            $('.search-btn').css('cursor', 'no-drop');
            $('.send-msg').attr('disabled', true);
            $('.send-msg').css('cursor', 'no-drop');
            $('.hovertable').hide(300);
            $('.alreadySend').show(300);
        },
        /**关闭已发送消息列表*/
        closeSentMsg: function (event) {
            $('.sent-message-btn').removeClass('on');
            this.listStatisticsDetails();
            $('.search-btn').attr('disabled', false);
            $('.search-btn').css('cursor', 'pointer');
            $('.send-msg').attr('disabled', false);
            $('.send-msg').css('cursor', 'pointer');
            $('.alreadySend').hide(300);
            $('.hovertable').show(300);

        },
        /**获取已发送消息列表*/
        listSentMessage: function () {
            var url = '/org/push/list-store-push-message/' + this.sent.page;

            http.ajax.get(true, true,url, null,http.ajax.CONTENT_TYPE_1,function (result) {
                push.vm.sent.data = result.obj;
                push.vm.initSentPage();
            })

        },
        /**初始化分对象*/
        initSentPage: function () {
            if (this.sent.pageObj == null) {
                this.sent.pageObj = new PageUtil();
                this.sent.pageObj.getPageControl().init({
                    container: 'page-container',
                    preBtn: 'pre_rec',
                    nextBtn: 'next_rec',
                    totalPage: push.vm.results.totalPage,
                    pageChange: function (page) {
                        push.vm.sent.page = page;
                        push.vm.listSentMessage();
                    }
                });
            }
            this.sent.pageObj.getPageControl().totalPage = this.sent.data.totalPage;
            this.sent.pageObj.getPageControl().selectPage(this.sent.page, true);
            if (push.vm.sent.data.totalSize == 0) {
                $('#page-container').css('display', 'none');
                return;
            }
            $('#page-container').css('display', 'block');

        },
        /**删除消息*/
        deleteSentMsg: function () {
            var len = this.sent.selectId.length;
            if (len < 1) {
                layer.msg('至少选择一条消息!', {icon: 5});
                return;
            }
            layer.confirm('确定删除这' + len + '条消息', function () {
                var url = '/org/push/delete-store-push-message';
                var data = {
                    ids: push.vm.sent.selectId
                };

                http.ajax.post(true, true, url, data,http.ajax.CONTENT_TYPE_1,function (result) {
                    layer.msg(result.msg, {icon: 1});
                    push.vm.listSentMessage();
                })
            });
        },
        /**弹出消息详情*/
        popupMsgDetails: function (obj) {
            this.sent.currentMsg = obj;
            this.lay = layer.open({
                type: 1,
                title: false,
                shadeClose: false,
                moveType: 1,
                area: ["655px", "410px"],
                offset: ['30%', '36%'],
                closeBtn: true,
                content: $('.sent-message-details')
            });
        },
        changeTextLength: function ($this) {

            /*var len = $this.val().replace(/[\u0391-\uFFE5]/g,"aa").length;
            var width = $this.width();
            if (len > 6) {


                var temp = (len -6) * 6;
                $this.width(width + temp);
            }*/
        },
        beforeEnter: function (el) {
            el.style.opacity = 0
            el.style.height = 0
        },
        enter: function (el, done) {
            var delay = el.dataset.index * 50
            setTimeout(function () {
                Velocity(
                    el,
                    { opacity: 1, height: '1.6em' },
                    { complete: done }
                )
            }, delay)
        },
        leave: function (el, done) {
            var delay = el.dataset.index * 50;
            setTimeout(function () {
                Velocity(
                    el,
                    { opacity: 0, height: 0 },
                    { complete: done }
                )
            }, delay)
        }

    },
    computed: {
        selectedUser: function() {
            var arr = {};
            for (var i in this.selectedUserId) {
                if (this.allUserData[this.selectedUserId[i]] != null) {
                    /*if (this.allUserData[this.selectedUserId[i]].realName != null &&
                        this.allUserData[this.selectedUserId[i]].realName != '') {
                        arr.push(this.allUserData[this.selectedUserId[i]].realName);
                    } else {
                        arr.push(this.allUserData[this.selectedUserId[i]].userName);
                    }*/
                    arr[this.allUserData[this.selectedUserId[i]].userId] = this.allUserData[this.selectedUserId[i]];

                }
            }
            return arr;
        },
        sentTotalSize: function () {
            if (this.sent.data != null) {
                return this.sent.data.totalSize;
            }
            return 0;
        },
        sentData: function () {
            return this.sent.data;
        },
        smsTemplateType: function(){
            return this.smsTemplate.type;
        },
        smsTemplateSelectedId: function () {
            return this.smsTemplate.selectedId;
        },
        smsMessage: function () {
            return this.smsMsg.message;
        }
    },
    watch: {
        /*全选按钮监听*/
        checkAll: function () {
            if (this.checkAll) {
                /*this.listAllStatisticsDetails();*/
                for (var i in this.allUserData) {
                    this.selectedUserId.push(i);
                }
                return;
            }
            this.selectedUserId = [];
        },
        /**已读消息全选按钮监听*/
        sentCheckAll: function () {
            if (this.sentCheckAll) {
                for (var i in this.sent.data.data) {
                    this.sent.selectId.push(this.sent.data.data[i].id);
                }
                return;
            }
            this.sent.selectId = [];
        },

        /*sent: {
            handler: function (newVal, oldVal) {
                if (newVal.data.data != oldVal.data.data) {
                    console.log('inter')
                }
                console.log(newVal)
            },
            deep: true
        },*/
        sentData: function () {
            this.$nextTick(function () {
                console.log('dsds');
                $('.sent-message-content .content').on('click', function () {
                    $(this).css('background-color', '#EEE').siblings().css('background-color', 'white')
                })
            })
        },

        appMsg: function () {
            
        },
        'appMsg.openType': function () {
            if(this.appMsg.openType == 3){
                $('.service-project').val("");
                $('.service-project').css('display', 'none');
                $('.msgUrl').css('display', 'block');
            }else if(this.appMsg.openType == 2){
                $('.msgUrl').val("");
                $('.msgUrl').css('display', 'none');
                $('.service-project').css('display', 'block');
            }else{
                $('.msgUrl').val("");
                $('.msgUrl').css('display', 'none');
                $('.service-project').val("");
                $('.service-project').css('display', 'none');
            }
        },
        smsTemplateType: function () {
            this.smsTemplate.currentData = this.smsTemplate.data[this.smsTemplate.type]
        },
        smsTemplateSelectedId: function () {
            if (this.smsTemplate.selectedId.length > 1) {
                this.smsTemplate.selectedId.splice(0, 1);
            }
        },
        smsMessage: function () {
            this.$nextTick(function () {
                var name = null;
                for (var i in push.vm.selectedUser) {
                    name = push.vm.selectedUser[i].realName || push.vm.selectedUser[i].userName;
                    break;
                }
                var len = Object.getOwnPropertyNames(push.vm.selectedUser).length;
                var text = len > 1 ? name + ';...' : name;
                $('.sms-message-content').find('input').eq(0).text(text);
            })

        }
    },

    filters: {
        sendType: function (value) {
            if (value == null) {
                return null;
            }
            switch (value) {
                case 1:
                    return 'app推送';
                case 2:
                    return '短信推送';
            }
        }
    }
});


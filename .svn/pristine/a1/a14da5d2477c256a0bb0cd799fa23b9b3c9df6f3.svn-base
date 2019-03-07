/**
 * 线下用户管理
 * author: liaoguo
 * date: 2018/12/12 20:19
 */

var offline = {};

offline.init = function () {
    offline.vm.searchStatistics();

}

offline.vm = new Vue({
    el: '.vue-content',
    data: {
        results: {},
        page: 1,
        pageSize: 10,
        pageObj: null, //分页对象
        search: {
            userName:null,
            realName:null,
            mobile: null
        },
    },
    methods: {
        /**搜索*/
        searchStatistics: function () {
            var data = {
                  userName : this.search.userName,
                  realName : this.search.realName,
                  mobile:this.search.mobile
            }
            
//            var url = '/member/data/list/' + this.page;
            var url = '/org/offlineManage/list/' + this.page;
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (rs) {
                offline.vm.results = rs.obj;
                
                //初始化分页
                offline.vm.initPage();
            })
        },
        /**初始化分对象*/
        initPage: function () {
            if (this.pageObj == null) {
                this.pageObj = new PageUtil();
                this.pageObj.getPageControl().init({
                    container: 'page-container',
                    preBtn: 'pre_rec',
                    nextBtn: 'next_rec',
                    totalPage: offline.vm.results.totalPage,
                    pageChange: function (page) {
                        offline.vm.page = page;
                        offline.vm.searchStatistics();
                    }
                });
            }
            this.pageObj.getPageControl().totalPage = this.results.totalPage;
            this.pageObj.getPageControl().selectPage(this.page, true);
            if (offline.vm.results.totalSize == 0) {
                $('#page-container').css('display', 'none');
                return;
            }
            $('#page-container').css('display', 'block');

        },
//        changeTextLength: function ($this) {
//
//            /*var len = $this.val().replace(/[\u0391-\uFFE5]/g,"aa").length;
//            var width = $this.width();
//            if (len > 6) {
//
//
//                var temp = (len -6) * 6;
//                $this.width(width + temp);
//            }*/
//        },
        beforeEnter: function (el) {
            el.style.opacity = 0
            el.style.height = 0
        },
        enter: function (el, done) {
            var delay = el.dataset.index * 50
            /*setTimeout(function () {
                Velocity(
                    el,
                    { opacity: 1, height: '1.6em' },
                    { complete: done }
                )
            }, delay)*/
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
//        selectedUser: function() {
//            var arr = {};
//            for (var i in this.selectedUserId) {
//                if (this.allUserData[this.selectedUserId[i]] != null) {
//                    /*if (this.allUserData[this.selectedUserId[i]].realName != null &&
//                        this.allUserData[this.selectedUserId[i]].realName != '') {
//                        arr.push(this.allUserData[this.selectedUserId[i]].realName);
//                    } else {
//                        arr.push(this.allUserData[this.selectedUserId[i]].userName);
//                    }*/
//                    arr[this.allUserData[this.selectedUserId[i]].userId] = this.allUserData[this.selectedUserId[i]];
//
//                }
//            }
//            return arr;
//        },
//        sentTotalSize: function () {
//            if (this.sent.data != null) {
//                return this.sent.data.totalSize;
//            }
//            return 0;
//        },
//        sentData: function () {
//            return this.sent.data;
//        },
//        smsTemplateType: function(){
//            return this.smsTemplate.type;
//        },
//        smsTemplateSelectedId: function () {
//            return this.smsTemplate.selectedId;
//        },
//        smsMessage: function () {
//            return this.smsMsg.message;
//        }
    },
    watch: {

        /*sent: {
            handler: function (newVal, oldVal) {
                if (newVal.data.data != oldVal.data.data) {
                    console.log('inter')
                }
                console.log(newVal)
            },
            deep: true
        },*/
//        sentData: function () {
//            this.$nextTick(function () {
//                console.log('dsds');
//                $('.sent-message-content .content').on('click', function () {
//                    $(this).css('background-color', '#EEE').siblings().css('background-color', 'white')
//                })
//            })
//        },
//
//        appMsg: function () {
//            
//        },
//        'appMsg.openType': function () {
//            if(this.appMsg.openType == 3){
//                $('.service-project').val("");
//                $('.service-project').css('display', 'none');
//                $('.msgUrl').css('display', 'block');
//            }else if(this.appMsg.openType == 2){
//                $('.msgUrl').val("");
//                $('.msgUrl').css('display', 'none');
//                $('.service-project').css('display', 'block');
//            }else{
//                $('.msgUrl').val("");
//                $('.msgUrl').css('display', 'none');
//                $('.service-project').val("");
//                $('.service-project').css('display', 'none');
//            }
//        },
//        smsTemplateType: function () {
//            this.smsTemplate.currentData = this.smsTemplate.data[this.smsTemplate.type]
//        },
//        smsTemplateSelectedId: function () {
//            if (this.smsTemplate.selectedId.length > 1) {
//                this.smsTemplate.selectedId.splice(0, 1);
//            }
//        },
//        smsMessage: function () {
//            this.$nextTick(function () {
//                var name = null;
//                for (var i in push.vm.selectedUser) {
//                    name = push.vm.selectedUser[i].realName || push.vm.selectedUser[i].userName;
//                    break;
//                }
//                var len = Object.getOwnPropertyNames(push.vm.selectedUser).length;
//                var text = len > 1 ? name + ';...' : name;
//                $('.sms-message-content').find('input').eq(0).text(text);
//            })
//
//        }
    },

    filters: {
//        sendType: function (value) {
//            if (value == null) {
//                return null;
//            }
//            switch (value) {
//                case 1:
//                    return 'app推送';
//                case 2:
//                    return '短信推送';
//            }
//        }
        sex : function(value){
            if(value==null){
                return '未知';
            }
            switch(value){
                case 0:
                    return '女';
                case 1:
                    return '男';
            }
        }
    }
});


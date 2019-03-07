/**
 * 服务管理JS
 * Created by Administrator on 2017/5/8.
 */

var orderTodo = {};

orderTodo.vm = new Vue({
    el: '.vue-content',
    data: {
        results: null,
        orgUser: null,
        orderData: null,
        orderTotalPage: null,
        currentPage: null,
        search: null,
        projectType: 'all',
        orderType: null,
        orderDetail: null,
        currentOrder: null,
        message: null,
        searchLesson: null
    },
    methods: {
        /**弹出聊天窗*/
        popupChatDialog: function (serveId, userHuanxinId, lessonHuanxinId, userName, projectName, photo, projectImage, code) {
            var type = 'chat';
            var huanxinId = userHuanxinId;
            var userName = userName;
            var photo = photo;
            if (serveId == 4) { //课堂
                type = 'groupchat';
                huanxinId = lessonHuanxinId;
                userName = projectName;
                photo = projectImage;
            }

            var currentMember = {
                userCode: huanxinId,
                type: type,
                userName: userName,
                photo: photo,
                projectCode: code
            }
            if (this.message == null) {
                this.message = {};
                this.message[currentMember.userCode] = [];
            }
            chat.initChatDialog(this.message, this.orgUser, currentMember);
        },
        /**服务详情*/
        goServeDetails: function (orderId) {
            window.location.href = '/orderManage/orderdetail/' + orderId;
        },
        /**服务评论*/
        goServeComments: function (orderId) {
            window.location.href = '/orderManage/ordercomments/' + orderId;
        },
        /**搜索服务*/
        searchServe: function(page) {
            var data = {
                projectType: this.projectType,
                keyword: this.search,
            }
            var url = null;
            if (this.orderType == 'lesson') {
                url = '/order/list-lesson/' + page;
            } else {
                url = '/orderManage/listserveorder/' + orderTodo.orderType + '/' + page
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: url,
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        orderTodo.vm.orderData = result.obj.data;
                        orderTodo.vm.orderTotalPage = result.obj.totalPage;
                        orderTodo.vm.currentPage = result.obj.nowPage;
                        pageUtil.getPageUtil();

                    }
                }
            });
        },
        /**弹出验证订单验证码窗口*/
        popupCheckOrderDialog:function (obj) {
            this.currentOrder = obj;
            layer.open({
                type: 1,
                anim: 0,
                title: false,
                closeBtn: false,
                shadeClose: true,
                area: ['620px', '329px'],
                zIndex: 99999,
                content: $('.check-order'),
                cancel: function () {
                }
            });
        },
        /**验证*/
        checkOrder: function () {
            var code = $('.check-order .code').val();
            var data = {
                orderId: this.currentOrder.orderId,
                verifyCode: code,
                orderNumber: this.currentOrder.orderNumber
            }
            if (code == '') {
                layer.msg('请输入验证码');
                $('.check-order .code').focus();
                return false;
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'POST',
                url: '/orderManage?checkorder',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    layer.msg(result.msg);
                    if(result.success){
                        layer.closeAll('page');
                        this.currentOrder = null;
                    }
                }
            });
        },
        /**取消验证*/
        cancelCheckOrder: function () {
            this.currentOrder = null;
            layer.closeAll('page');
        }
    },
    computed: {

    },
    watch: {
        projectType: function () {
            this.searchServe(1);
        },
        search: function () {
            setTimeout(function () {
                orderTodo.vm.searchServe(1);
            }, 300);

        },
        orderData: function () {
            this.$nextTick(function () {
                common.addBorder();
            })
        },
        searchLesson: function () {
            setTimeout(function () {
                orderTodo.vm.searchServe(1);
            }, 300);
        }
    },
    filters: {
        date: function (value, format) {
            // var date = null;
            return new Date(value).Format(format);
        },
        orderStatus: function (value) {
            var msg = null;
            switch (value) {
                case '1':
                    msg = '待付款';
                    break;
                case '2':
                    msg = '付款失败';
                    break;
                case '3':
                    msg = '有效';
                    break;
                case '4':
                    msg = '已完成';
                    break;
                case '5':
                    msg = '退款失效';
                    break;
                case '6':
                    msg = '已取消';
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
    reportPage: null,
    getPageUtil: function () {
        if (this.reportPage == null) {
            this.reportPage = new PageUtil();
            this.reportPage.getPageControl().init({
                container: 'pageManager',
                preBtn: 'pre_rec',
                nextBtn: 'next_rec',
                totalPage: orderTodo.vm.orderTotalPage,
                pageChange: function (page) {
                    orderTodo.vm.searchServe(page);
                }
            });
        }
        this.reportPage.getPageControl().totalPage = orderTodo.vm.orderTotalPage;
        this.reportPage.getPageControl().selectPage(orderTodo.vm.currentPage, true);

        if (orderTodo.vm.orderData == undefined || orderTodo.vm.orderData.length == 0) {
            $('.page_Container').removeClass('block');
            $('.page_Container').addClass('none');
        } else {
            $('.page_Container').removeClass('none');
            $('.page_Container').addClass('block');
        }
    }
}



/**
 * 任务中心
 * @Author wenxian.cai
 * @Date 2017/6/8 10:03
 */

var task = {};

/**初始化*/
task.init = function () {
    $(".task-content li").not("li:first").css("display","none");
    $(".items-nav li").on("click",function(){
        $(this).addClass('current').siblings().removeClass('current');
        // $(".task-content li").css("display","none").eq($(this).index()).css("display","block");
    });
}

/**vm实例*/
task.vm = new Vue({
    el: '.vue-task',
    data: {
        results: null,
        taskData: null,
        taskTotalPage: null,
        taskCurrentPage: null,
        currentType: 'all',
        currentStatus: 3,
        search: null,
        taskDetails: null,
    },
    methods: {
        /**获取任务列表*/
        listOrder: function (page) {
            var data = {
                type: this.currentType == 'all' ? null : this.currentType,
                status: this.currentStatus,
                userName: this.search == '' ? null : this.search
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/orderManage/listorder/' + page,
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        task.vm.results = result.obj;

                    }
                }
            });
        },
        /**跳转订单详情页面*/
        goTaskDetails: function (orderId) {
            window.location.href = '/orderManage/orderdetails/' + orderId;
        },
        /**提醒*/
        popupRemindDialog: function (orderNumber, orgUserId) {
            layer.confirm('确认推送提醒消息?', {'offset': '50%'}, function () {
                var data = {
                    content: '订单'+ orderNumber +'未完成，请及时服务用户。',
                    receiverId: orgUserId
                }
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'POST',
                    url: '/message/add-remind',
                    data: data,
                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load();
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if (result.success) {
                            layer.msg(result.msg, {icon: 1});
                            return;
                        }
                        layer.msg(result.msg, {icon: 2});
                    }
                });
            })
        }
    },
    computed: {

    },
    watch: {
        results: function () {
            if (this.results != null) {
                this.taskData = this.results.data;
                this.taskTotalPage = this.results.totalPage;
                this.taskCurrentPage = this.results.nowPage;
                pageUtil.getPageUtil();
            }
            this.$nextTick(function () {
                $(".main-content").css("minHeight",800);
                var height=$(".main-content").height();
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
            })
        },
        /**监控任务状态*/
        currentStatus: function () {
            this.listOrder(1);
        },
        /**监控任务类型*/
        currentType: function () {
            this.listOrder(1);
        },
        /**监控搜索框*/
        search: function () {
            this.listOrder(1);
        }
    },
    filters: {
        date: function (value, format) {
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
                container: 'task-todo-page-manager',
                preBtn: 'pre_rec',
                nextBtn: 'next_rec',
                totalPage: task.vm.taskTotalPage,
                pageChange: function (page) {
                    task.vm.listOrder(page);
                }
            });
        }
        this.reportPage.getPageControl().totalPage = task.vm.taskTotalPage;
        this.reportPage.getPageControl().selectPage(task.vm.taskCurrentPage, true);
        if (task.vm.taskData.length == 0) {
            $('.page_Container').removeClass('block');
            $('.page_Container').addClass('none');
        } else {
            $('.page_Container').removeClass('none');
            $('.page_Container').addClass('block');
        }
    }
}
/**
 * @Author wenxian.cai
 * @Date 2017/5/18 20:07
*/

var service = {};

service.init = function () {
    /**选项卡切换操作*/
    $(".items-content table").not("table:first").css("display","none");
    // $(".items-content div").not("div:first").css("display","none");
    $(".items-nav li").on("click",function(){
        $(this).addClass('current').siblings().removeClass('current');
        $(".items-content table").css("display","none").eq($(this).index()).css("display","block");
        // $(".items-content div").css("display","none").eq($(this).index()).css("display","block");
    });
}

service.vm = new Vue({
    el: '.vue-content',
    data: {
        results: null,
        service: null,
        projectType: 0,
        projectStatus: null,
        serviceData: null,          //全部项目
        preOnlineService: null,     //待上线
        onlineService: null,        //以上线
        offlineService: null,       //已下线
        serviceTotalPage: null,     //页码
        preOnlineTotalPage: null,
        onlineTotalPage: null,
        offlineTotalPage: null,
        serviceCurrentPage: 1,
        preOnlineCurrentPage: 1,
        onlineCurrentPage: 1,
        offlineCurrentPage: 1,
        search: null,

    },
    methods: {
        /**
         * 获取服务列表
         * @param status
         */
        listService: function (page) {
            var data = {
                type: this.projectType,
                status: this.projectStatus,
                search: this.search
            };
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/org/service/listservice/' + page,
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        service.vm.service = result.obj;

                    }
                }
            });
        },
        /**返回操作信息*/
        getOperation: function (status, projectType, serveId, code) {
            code = code + '';
            var preview = '<p onclick = "service.vm.goModifyService(this)">修改</p>';
            if (projectType == 3 || projectType == 2) {
                preview += '<p onclick = "service.vm.goPreviewService(this)">预览</p>';
            }

            var msg = null;
            switch (status) {
                case 2:
                    if (serveId == 4) { //健康课堂
                        msg = preview + '<p style="color: gray">(课堂服务会在有效期失效后自动下架)</p>';
                        break;
                    }
                    msg = preview + '<p onclick="service.vm.changeProjectStatus(3, this)">申请下线</p>';

                    break;
                case 1:
                    msg = preview + '<p onclick="service.vm.changeProjectStatus(2, this)">确定上线</p>';
                    // <p onclick="service.vm.goUpdateService(this)">修改</p>
                    break;
                case 3:
                    msg = '<p onclick="service.vm.changeProjectStatus(2, this)">申请上线</p>';
                    // <p onclick="service.vm.goUpdateService(this)">修改</p>
                    break;
            }
            return msg;
        },
        /**发布项目*/
        publishProject: function () {
            layer.open({
                closeBtn: 0,
                shadeClose: true,
                anim: 5,
                type: 1,
                title: " ",
                zIndex: 999,
                offset: '50%',
                content: $('.chose-type'),
            });
        },
        /**跳转添加服务项目*/
        goPublishProject: function (type) {
            window.location.href = '/org/service/publishservice/' + type;
        },
        /**上线、下线项目*/
        changeProjectStatus: function (status, event) {
            var msg = status == 2 ? '确定上线该项目？' : '确定下线该项目？';
            layer.confirm(msg, function(index){
                var data = {
                    type: $(event).parent().parent().attr('projectType'),
                    code: $(event).parent().parent().attr('code'),
                    status: status
                }
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'POST',
                    url: '/org/service/updatestatus',
                    data: data,
                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load();
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if(result.success){
                            setTimeout(function () {
                                window.location.href = '/org/service';
                            }, 1000)
                        }
                    }
                });
                layer.close(index);
            });

        },
        /**跳转预览界面*/
        goPreviewService: function (obj) {
            window.open('/org/service/preview?code=' + $(obj).parent().parent().attr('code'));
        },
        /**跳转修改界面*/
        goModifyService: function (obj) {
            window.location.href = '/org/service/modify-service/' + $(obj).parent().parent().attr('projectType') + '?code=' + $(obj).parent().parent().attr('code');
        }
    },
    computed: {

    },
    watch: {
        /**选择服务类型*/
        projectType: function () {
            this.listService(1);
        },
        /**搜索框*/
        search: function () {
            this.listService(1);
        },
        /**选择服务状态*/
        projectStatus: function () {
            this.listService(1);
        },
        /**监听service变化*/
        service: function () {
            switch (this.projectStatus) {
                case null:

                    this.serviceData = this.service.data;
                    this.serviceTotalPage = this.service.totalPage;
                    this.serviceCurrentPage = this.service.nowPage;
                    pageUtil.getServicePage();
                    break;
                case 1:

                    this.preOnlineService = this.service.data;
                    this.preOnlineTotalPage = this.service.totalPage;
                    this.preOnlineCurrentPage = this.service.nowPage;
                    pageUtil.getPreOnlinePage();
                    break;
                case 2:

                    this.onlineService = this.service.data;
                    this.onlineTotalPage = this.service.totalPage;
                    this.onlineCurrentPage = this.service.nowPage;
                    pageUtil.getOnlinePage();
                    break;
                case 3:

                    this.offlineService = this.service.data;
                    this.offlineTotalPage = this.service.totalPage;
                    this.offlineCurrentPage = this.service.nowPage;
                    pageUtil.getOfflinePage();
                    break;
            }
            this.$nextTick(function () {
                common.addBorder();
            })
        }
    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        },
        /**项目状态转换*/
        projectStatus: function (value) {
            var status = null;
           switch (value) {
               case 1:
                   status = '待上线';
                   break;
               case 2:
                   status = '已上线';
                   break;
               case 3:
                   status = '已下线';
                   break;
           }
           return status;
        },
        /**项目状态转换*/
        projectType: function (value) {
            var status = null;
            switch (value) {
                case 1:
                    status = '咨询服务';
                    break;
                case 2:
                    status = '到店服务';
                    break;
                case 3:
                    status = '上门服务';
                    break;
                case 4:
                    status = '课堂服务';
                    break;
            }
            return status;
        }
    }

});

/*
 * 分页工具
 */
var pageUtil = {
    servicePage: null,
    preOnlinePage: null,
    onlinePage: null,
    offlinePage: null,
    getServicePage: function () {
        if (this.servicePage == null) {
            this.servicePage = new PageUtil();
            this.servicePage.getPageControl().init({
                container: 'servicePage',
                preBtn: 'pre_rec',
                nextBtn: 'next_rec',
                totalPage: service.vm.serviceTotalPage,
                pageChange: function (page) {
                    service.vm.listService(page);
                }
            });
        }
        this.servicePage.getPageControl().totalPage = service.vm.serviceTotalPage;
        this.servicePage.getPageControl().selectPage(service.vm.serviceCurrentPage, true);
        if (service.vm.serviceData.length == 0) {
            $('#servicePage').css('display', 'none');
        } else {
            $('#servicePage').css('display', 'block');
        }
    },
    getPreOnlinePage: function () {
        if (this.preOnlinePage == null) {
            this.preOnlinePage = new PageUtil();
            this.preOnlinePage.getPageControl().init({
                container: 'preOnlinePage',
                preBtn: 'pre_rec',
                nextBtn: 'next_rec',
                totalPage: service.vm.preOnlineTotalPage,
                pageChange: function (page) {
                    service.vm.listService(page);
                }
            });
        }
        this.preOnlinePage.getPageControl().totalPage = service.vm.preOnlineTotalPage;
        this.preOnlinePage.getPageControl().selectPage(service.vm.preOnlineCurrentPage, true);
        if (service.vm.preOnlineService.length == 0) {
            $('#preOnlinePage').css('display', 'none');
        } else {
            $('#preOnlinePage').css('display', 'block');
        }
    },
    getOnlinePage: function () {
        if (this.onlinePage == null) {
            this.onlinePage = new PageUtil();
            this.onlinePage.getPageControl().init({
                container: 'onlinePage',
                preBtn: 'pre_rec',
                nextBtn: 'next_rec',
                totalPage: service.vm.onlineTotalPage,
                pageChange: function (page) {
                    service.vm.listService(page);
                }
            });
        }
        this.onlinePage.getPageControl().totalPage = service.vm.onlineTotalPage;
        this.onlinePage.getPageControl().selectPage(service.vm.onlineCurrentPage, true);
        if (service.vm.onlineService.length == 0) {
            $('#onlinePage').css('display', 'none');
        } else {
            $('#onlinePage').css('display', 'block');
        }
    },
    getOfflinePage: function () {
        if (this.offlinePage == null) {
            this.offlinePage = new PageUtil();
            this.offlinePage.getPageControl().init({
                container: 'offlinePage',
                preBtn: 'pre_rec',
                nextBtn: 'next_rec',
                totalPage: service.vm.offlineTotalPage,
                pageChange: function (page) {
                    service.vm.listService(page);
                }
            });
        }
        this.offlinePage.getPageControl().totalPage = service.vm.offlineTotalPage;
        this.offlinePage.getPageControl().selectPage(service.vm.offlineCurrentPage, true);
        if (service.vm.offlineService.length == 0) {
            $('#offlinePage').css('display', 'none');
        } else {
            $('#offlinePage').css('display', 'block');
        }
    }
}
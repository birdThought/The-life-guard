/*废弃
/!**
 * 消息中心JS
 * Created by Administrator on 2017/5/3.
 *!/
var message = {};
/!**
 * 初始化
 *!/
message.init = function () {
    $(".news-content ol").not("ol:first").css("display","none");
    $(".news-tab li").on("click",function(){
        $(this).addClass("current").siblings().removeClass("current");
        $(".news-content ol").css("display","none").eq($(this).index()).css("display","block");
    });
    pageUtil.getPageUtil();
}
message.vm = new Vue({
    el: '.vue-content',
    data: {
        results: null,
        orgUser: null,
        offLineCount: null,
        systemCount: null,
        systemMsg: null,
        systemMsgData: [],
        serviceMsg: [],
        checked: false,
        systemChecked: [],
        serveChecked: [],
        serveMessage: null,
        orgType: null,
        userType: null,
    },
    methods: {
        /!**切换选项卡*!/
        changeTab: function (param) {
            this.checked = false;
        },
        /!**删除全部*!/
        deleteAll: function () {
            var bool = $('.system-message').hasClass('current');
            if (bool) { //系统
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
            } else {    //服务消息
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
            }

        },
        /!**弹出消息框*!/
        popupChatDialog: function (index) {
            var currentMember = {
                userCode: index,
                type: this.serveMessage[index][this.serveMessage[index].length - 1].type
            }
            /!*var temp = [];
            for(var i in this.serveMessage) {
                temp[i] = this.serveMessage[i];
            }*!/
            chat.initChatDialog(this.serveMessage, this.orgUser, currentMember);
            message.vm.serveMessage = null;
        },
        /!**系统消息框*!/
        popupMsgDialog: function(index) {
            var msg = this.systemMsg.data[index];
            var $content = $("<div><p>" + msg.content + "</p></div>");
            console.log(msg);
            layer.open({
                type: 1,
                title: msg.title,
                skin: 'layui-layer-rim',
                area: ['726px'],
                zIndex: 999,
                content: "<div style='padding: 10px 20px;min-height: 500px;'><p>" + msg.content + "</p></div>"
            });
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
        }
    },
    computed: {
        //获取离线服务消息数
        offLineCount: function () {
           if (this.serveMessage != null) {
               var len = null;
               for (var key in this.serveMessage) {
                   len += this.serveMessage[key].length;
               }
               return len;
           }
        }
    },
    watch: {
        /!**监听checked(全选)*!/
        checked: function () {
            if (this.checked) {
                var bool = $('.system-message').hasClass('current');
                if (bool) {
                    var arr = new Array();
                    for (var i = 0; i < this.systemMsgData.length; i ++) {
                        arr.push(this.systemMsgData[i].id);
                    }
                    this.systemChecked = arr;
                } else {
                    var arr = new Array();
                    for (var key in this.serveMessage) {
                        arr.push(key);
                    }
                    this.serveChecked = arr;
                }
            } else {
                var bool = $('.system-message').hasClass('current');
                if (bool) {
                    this.systemChecked = [];
                } else {
                    this.serveChecked = [];
                }
            }

        },
        systemMsg: function() {
            /!** 初始化数据 *!/
            this.systemCount = this.systemMsg.total;
            this.systemMsgData = this.systemMsg.data;
            /!** 全部勾选按钮取消 *!/
            this.checked = false;
            if ( this.systemMsg.data.length == 0) {   //没数据时不显示分页
                return;
            }
            if (pageUtil.systemMsgPage != null) {
                pageUtil.systemMsgPage.getPageControl().totalPage = message.vm.systemMsg.totalPage;
                pageUtil.systemMsgPage.getPageControl().selectPage(message.vm.systemMsg.curPage, true);

            }
        }
    },
    updated: function() {
        var height=$(".main-content").height() + 40;
        $(".main-nav").css({
            borderRight:'1px solid #ddd',
            height:height
        });
        $(".container").css({
            borderLeft:'1px solid #ddd',
            borderRight:'1px solid #ddd',
            borderBottom:'1px solid #ddd',
            height:height
        });
    }
});

/!*
 * 分页工具
 *!/
var pageUtil = {
    systemMsgPage: null,
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
        /!*this.systemMsgPage.getPageControl().totalPage = message.vm.systemMsg.totalPage;
        this.systemMsgPage.getPageControl().selectPage(message.vm.systemMsg.curPage, true);*!/

    }
}
*/

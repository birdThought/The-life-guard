<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%--<meta name="flexible" content="initial-dpr=1" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <link rel="apple-touch-icon" href="favicon.png">
    <link rel="Shortcut Icon" href="favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="/static/mobile/wechat/css/device.css">
    <title>心电</title>
    <style>
        * {
            margin:0;
            padding:0;
            list-style-type:none;
         }
        .lastest {
            margin-top:0.2rem;
        }
        .lastest div li {
            width:50%;
            float: left;
            font-size:0.26rem;
            color: #78909c;
           	line-height:0.4rem;
           	padding-top:0.1rem;
            text-align: center;
        }
        .lastest div li span {
            font-size:0.24rem;
            color: #555;
        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
	<header class="header">
		<p>心电测量结果记录</p>
	</header>
    <div class="chart" style="display:none">
        图表数据区域
    </div>
    <div class="lastest clearfix">
        <section class="newest-data">
            <p>{{latest.date | date('yyyy-MM-dd')}}最新数据</p>
        </section>
        <template>
            <div class="clearfix">
                <ul>
                    <li>自动标记次数：<span v-html="latest.autoSize"></span>次</li>
                    <li>手动标记次数：<span v-html="latest.activeSize"></span>次</li>
                </ul>
            </div>
        </template>
    </div>
    <div class="history">
        <div class="history-data-lists">
            <div class="item clearfix" v-for="h in history">
                <p class="measure-date" >{{h.date | date('yyyy-MM-dd hh:mm')}}
                  <!--  <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>-->
                   <!-- <span>{{}}</span>-->
                </p>
                <div class="content">
                    <p>
                        <span>自动标记次数：{{h.autoSize}}次</span>
                        <span>手动标记次数：{{h.activeSize}}次</span>
                    </p>
                </div>
            </div>
        </div>
        <p id="line"></p>
    </div>
</div>
</body>
<script src="/static/mobile/wechat/js/adaptive.js?v=1.10"></script>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/mobile/wechat/js/dateFormat.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/dropload.js"></script>
<script src="/static/mobile/wechat/js/device/device-common.js?v=1.10"></script>
<script>
    var device = {};
    device.vm = new Vue({
        el: '.vue-content',
        data: {
            history: [], //历史数据
            latest: {}, //最新一条数据
            page: 1,
            deviceType: null//设备类型
        },
        computed:{
            str:function(){
                setTimeout(function () {
                    return this.history[1].systolic;
                },400)

            }
        },
        methods: {
            listDevice: function (type, measureType) {
                var deviceType = null;
                switch (this.deviceType) {
                    case 'ecg':
                        deviceType = 'ecg';
                        break;
                }
                var url = '/wechat-record/' + deviceType;
                var data = {
                    type: type,
                    page: this.page,
                    measureType: measureType
                };
                http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                    if (result.success) {
                        if (type == '0') {
                            device.vm.history = result.obj;
                            device.vm.latest = device.vm.history[0];
                        }
                        else {
                            device.vm.chart = result.obj;
                        }
                    }

                });
            },
            abnormal: function (value, area) {
                try {
                    var arr = area.split('-');
                    if (value > arr[1] || value < arr[0]) {
                        return true;
                    }
                    return false;
                }catch(e) {
                }
            }
        },
        filters: {
            date: function (value, format) {
                return new Date(value).Format(format);
            }
        }
    });
    device.vm.$watch('latest',function () {
        finish();
       ecgDrop();
    },{deep:true});
    var ecgDrop = function(){
        setTimeout(function(){
            $(function () {
                var page = 1;
                $('.history').dropload({
                    scrollArea:window,
                    domDown:{
                        domClass:'dropload-down',
                        domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',
                        domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                        domNoData: '<div class="dropload-noData">暂无数据....</div>'
                    },
//           这是里下拉加载更多数据时用到的
                    loadDownFn:function(me){
                        page++;
                        var result = '';
                        $.ajax({
                            type:'GET',
                            url:"/wechat-record/ecg",
                            data:{
                                type:0,
                                page:page,
                                measureType:'',
                                '_':Math.random()*14
                            },
                            dataType:'json',
                            success:function(responsive){//如果没有出错就是满数据下就是8条数据 如果数据数据不足情况下就是小于8条
                                var objLen = responsive.obj.length;
                                if(objLen>0){
                                    for(var i = 0; i < responsive.obj.length; i++){
                                        var data = responsive.obj[i];
                                        result+='<div class="item clearfix">' +
                                            '<p class="measure-date">'+gettime(data.date)+
                                            '</p> <div class="content"><p><span>自动标记次数：'+data.autoSize+'次</span> <span>手动标记次数：'+data.activeSize+'次</span></p></div></div>'
                                }
                                }else {
                                    me.lock();
                                    me.noData();
                                }
                                $('.history-data-lists').append(result);
                                me.resetload();
                                measureResult();
                                CreateLine();
                            },
                            error:function(xhr,type){
                                alert('Ajax error');
                                me.resetload();
                            }
                        });
                    },
                    threshold:50
                })
            });

        },500);
    };
</script>
<script>
    //设置设备类型
    device.vm.deviceType = 'ecg';
    //历史数据
    device.vm.listDevice(0);
</script>
</html>

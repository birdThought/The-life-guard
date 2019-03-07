<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <link rel="stylesheet" href="/static/mobile/wechat/css/device.css">
    <title>血氧仪</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none
        }

        .lastest-content-right li {
            font-size: 0.24rem;
            color: #78909c;
            line-height: 1;
            margin-bottom: .14rem;
        }

        .lastest-content-left {
            height: auto;
        }

        .lastest-content-right {
            padding-top: 0.58rem;
            padding-bottom: 0.58rem;
            padding-left: 0.2rem;
        }

        .lastest-content-right li strong {
            width: 1.5rem;
            text-align: right;
            display: inline-block;
            font-weight: normal;
        }

        .lastest-content-right li span {
            font-size: 0.26rem;
            color: #78909c;
        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
    <header class="header">
        <p>血氧仪测量结果记录</p>
    </header>
    <div class="chart" style="display:none">
        图表数据区域
    </div>
    <div class="lastest clearfix">
        <div class="lastest-content-left">
            <div class="wrap">
                <div class="circle">
                    <div class="percent left"></div>
                    <div class="percent right wth0"></div>
                </div>
                <div class="num"><span id="num">80%</span></div>
            </div>
            <div class="measureResult">
                <p class="result-show"></p>
                <p></p>
                <p class="newestdata">最新数据</p>
            </div>
        </div>
        <div class="lastest-content-right">
            <template>
                <div>
                    <ul>
                        <li><strong>血氧饱和度：</strong><span
                                :class="abnormal(latest.saturation, latest.saturationArea) ? 'red' : ''">{{latest.saturation}}</span>%
                        </li>
                        <li><strong>心率：</strong><span
                                :class="abnormal(latest.heartRate, latest.heartRateArea) ? 'red' : ''">{{latest.heartRate}}</span>bpm
                        </li>
                    </ul>
                </div>
            </template>
        </div>
    </div>
    <div class="history history-data-lists">
        <div class="item clearfix" v-for="h in history">
            <p class="measure-date">{{h.measureDate | date('yyyy-MM-dd hh:mm')}}
                <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>
            </p>
            <div class="content">
                <p>
                    <span><strong>血氧饱和度：</strong><label :class="abnormal(h.saturation, h.saturationArea) ? 'red' : ''">{{h.saturation}}</label>%</span>
                    <span><strong>心率：</strong><label :class="abnormal(h.heartRate, h.heartRateArea) ? 'red' : ''">{{h.heartRate}}</label>bpm</span>
                </p>
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
<script src="/static/mobile/wechat/js/bloodfatcharts.js"></script>
</html>
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
        computed: {
            str: function () {
                setTimeout(function () {
                    return this.history[1].systolic;
                }, 400)
            }
        },
        methods: {
            listDevice: function (type, measureType) {
                var deviceType = null;
                switch (this.deviceType) {
                    case 'xyy':
                        deviceType = 'oxygen';
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
                } catch (e) {
                }
            }
        },
        filters: {
            date: function (value, format) {
                return new Date(value).Format(format);
            },
            measureType: function (value) {
                switch (value) {
                    case 1:
                        return '早餐前';
                    case 2:
                        return '早餐后';
                    case 3:
                        return '午餐前';
                    case 4:
                        return '午餐后';
                    case 5:
                        return '晚餐前';
                    case 6:
                        return '晚餐后';
                }
            }
        }
    });
    device.vm.$watch('latest', function () {
        finish();
        lunginstrument();
    }, {deep: true});
    var lunginstrument = function () {
        setTimeout(function () {
            $(function () {
                var page = 1;
                $('.vue-content').dropload({
                    scrollArea: window,
                    domDown: {
                        domClass: 'dropload-down',
                        domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
                        domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                        domNoData: '<div class="dropload-noData">暂无数据....</div>'
                    },
//           这是里下拉加载更多数据时用到的
                    loadDownFn: function (me) {
                        page++;
                        var result = '';
                        var str ="";
                        $.ajax({
                            type: 'GET',
                            url: "/wechat-record/oxygen",
                            data: {
                                type: 0,
                                page: page,
                                measureType: '',
                                '_': Math.random() * 14
                            },
                            dataType: 'json',
                            success: function (responsive) {//如果没有出错就是满数据下就是8条数据 如果数据数据不足情况下就是小于8条
                                var objLen = responsive.obj.length;
                                if (objLen > 0) {
                                    for (var i = 0; i < responsive.obj.length; i++) {
                                        var data = responsive.obj[i];
                                        str+="<div class='item clearfix'>";
                                        str+="<p class='measure-date'>"+gettime(data.measureDate);
                                        str+="<span class='result_data_finally'>"+panduan(data.status)+"</span></p>";
                                        str+="<div class='content content-spec'>";
                                        str+="<p><span>血氧饱和度：<label class=\"" + result_judge(data.saturation,data.saturationArea) + "\">" + data.saturation + '</label>%</span>';
                                        str+="<span>心率：<label class=\"" + result_judge(data.heartRate,data.heartRateArea) + "\">" + data.heartRate + '</label>bpm</span>';
                                        str+="</p></div></div>";
                                    }
                                } else {
                                    me.lock();
                                    me.noData();
                                }
                                 $('.history-data-lists').append(str);
                                me.resetload();
                                measureResult();
                                CreateLine();
                                test();
                            },
                            error: function (xhr, type) {
                                alert('Ajax error');
                                me.resetload();
                            }
                        });
                    },
                    threshold: 50
                })
            });

        }, 300);
    };
    //95-100 98
</script>
<script>
    function test() {
        var result_show = $('.result_data_finally');
        $.each(result_show, function (val, key) {
            if (key.innerHTML == '异常') {
                $(this).css({'background': 'red', 'color': '#fff'});
            } else {
                $(this).css({'background': '#44c660', 'color': '#fff'})
            }
        })
    }
    //设置设备类型
    device.vm.deviceType = 'xyy';
    //历史数据
    device.vm.listDevice(0);
</script>

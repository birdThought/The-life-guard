<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%--
    <meta name="flexible" content="initial-dpr=1"/>
    --%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <link rel="Shortcut Icon" href="favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="/static/mobile/wechat/css/device.css">
    <title>肺活仪</title>
    <style>
        .lastest-content-right > div ul li {
            list-style: none
        }

        .lastest-content-left {
            height: 1.8rem
        }

        .lastest-content-right div ul li {
            font-size: 0.21rem;
            line-height: 1;
            margin-top: 0.8rem;
            margin-left: 0.66rem;
            color: #78909c;
            letter-spacing: 0.01rem;
        }

        .lastest-content-right div ul li span {
            font-size: 0.26rem;
            color: #78909c;
            letter-spacing: 0.01rem;
            margin-right: 0.1rem;
        }

        .measure-date {
            color: #333;
        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
    <header class="header">
        <p>肺活仪测量结果记录1</p>
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
            <div>
                <ul>
                    <li>肺活量：<span v-html="latest.vitalCapacity"
                                  :class="abnormal(latest.vitalCapacity, latest.vitalCapacityArea) ? 'red' : ''"></span>ml
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="history history-data-lists">
        <div class="item clearfix" v-for="h in history">
            <p class="measure-date">{{h.measureDate | date('yyyy-MM-dd hh:mm')}}
                <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>
            </p>
            <div class="content">
                <p>
                    <span class='history-data'>肺活量：<label
                            :class="abnormal(h.vitalCapacity, h.vitalCapacityArea) ? 'red' : ''">{{h.vitalCapacity}}</label>ml</span>
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
                    case 'fhy':
                        deviceType = 'lunginstrument';
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
                        domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                        domNoData: '<div class="dropload-noData">暂无数据....</div>'
                    },
//           这是里下拉加载更多数据时用到的
                    loadDownFn: function (me) {
                        page++;
                        var result = '';
                        var str = '';
                        $.ajax({
                            type: 'GET',
                            url: "/wechat-record/lunginstrument",
                            data: {
                                type: 0,
                                page: page,
                                measureType: '',
                                '_': Math.random() * 14
                            },
                            dataType: 'json',
                            success: function (responsive) {
                                var objLen = responsive.obj.length;
                                var dataArea = [];
                                var dataArea1 = [];
                                if (objLen > 0) {
                                    for (var i = 0; i < responsive.obj.length; i++) {
                                        var data = responsive.obj[i];
                                        result += '<div class="item clearfix">' +
                                            '<p class="measure-date">' + gettime(data.measureDate) +
                                            '<span class="result_data_finally">' + panduan(data.status) + '</span></p>  ' +
                                            '<div class="content content-spec"><p><span class="history-data result-show-first">肺活量：<label class="">' + data.vitalCapacity + '</label>ml</span></p></div></div>';
                                    }
                                } else {
                                    me.lock();
                                    me.noData();
                                }
                                $('.history-data-lists').append(result);
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
</script>
<script>
    function test() {
        var result_show = $('.result_data_finally');
        $.each(result_show, function (val, key) {
            if (key.innerHTML == '异常') {
                $(this).css({'background': 'red', 'color': '#fff'});
                $(this).parent('.measure-date').siblings('div').find('label').css({'color': 'red'})

            } else {
                $(this).css({'background': '#44c660', 'color': '#fff'})
            }
        })
    }
    //设置设备类型
    device.vm.deviceType = 'fhy';
    //历史数据
    device.vm.listDevice(0);
</script>







































<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%--<meta name="flexible" content="initial-dpr=1" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <link rel="Shortcut Icon" href="favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="/static/mobile/wechat/css/device.css">
    <title>血压计</title>
    <style>
        .lastest-content-left {
            height:2rem;
        }
        .lastest-content-right {
            padding:0.8rem 0 0.8rem 0.8rem;
        }
        .lastest-content-right li {
            font-size:0.21rem;
            color: #78909c;
            margin-bottom:0.2rem;
            line-height:1;
        }
        .lastest-content-right li span {
            font-size:0.26rem;
            color: #78909c;
            margin-right: 0.14rem;
        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
    <header class="header">
        <p>血压计测量结果记录</p>
    </header>
    <div class="chart" style="display: none">
        <button>clickChange</button>
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
            <ul>
                <li>收缩压：<span v-html="latest.systolic" :class="abnormal(latest.systolic,latest.ststolicArea)"></span>mmHg</li>
                <li>舒张压：<span v-html="latest.diastolic" :class="abnormal(latest.diastolic,latest.diastolicArea)"></span>mmHg</li>
                <li>心率：<span v-html="latest.heartRate" :class="abnormal(latest.heartRate,latest.heartRateArea)"></span></li>
            </ul>
        </div>
    </div>
    <div class="history bloodPressure-history-data">
        <div class="lists">
            <template  v-for="(h, index) in history">
                <li class="layui-timeline-item">
                    <div class="layui-timeline-content layui-text">
                        <div class="item clearfix">
                            <p class="measure-date" >{{h.measureDate | date('yyyy-MM-dd hh:mm')}}
                                <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>
                            </p>
                            <div class="content">
                                <p>
                                   <span>收缩压：<label :class="abnormal(h.systolic, h.systolicArea) ? 'red' : ''">{{h.systolic}}</label>mmHg</span>
                                   <span>舒张压：<label :class="abnormal(h.diastolic, h.diastolicArea) ? 'red' : ''">{{h.diastolic}}</label>mmHg</span>
                                </p>
                                <p> 
                                    <span>心率：<label :class="abnormal(h.heartRate, h.heartRateArea) ? 'red' : ''">{{h.heartRate}}</label></span>
                                </p>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="layui-timeline-item"></li>
            </template>
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
<script src="/static/mobile/wechat/js/device/device-common.js"></script>
<script src="/static/mobile/wechat/js/device/device.js?v=1.10"></script>
<script src="/static/mobile/wechat/js/bloodfatcharts.js"></script>
<script src="/static/mobile/wechat/js/dropload.js"></script>
<script>
    //设置设备类型
    device.vm.deviceType = 'xyj';
    //历史数据
    device.vm.listDevice(0, null);
</script>
</html>
<script>
</script>

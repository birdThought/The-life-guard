
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
    <title>心率手环</title>
    <style>
        *{
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
        .history {
            padding-left: 0.5rem;
        }
        .lastest-content {
            width:100%;
            height:auto;
            background: #fff;
        }
        .lastest-content ul {
            padding-left:0;
            padding-top: 0.42rem;
            padding-bottom: 0.3rem;
        }
        .lastest-content li {
            list-style:none;
            width:33.33%;
            float: left;
            border-right:1px solid #ddd;
            padding-left:0.34rem;
            text-align: center;
            font-size:0.2rem;
            color: #78909c;
        }
        .lastest-content li:last-child {
            border:none;
        }
        .lastest-content li strong {
            display: block;
            font-size:0.2rem;
            width:100%;
            color: #78909c;
            text-align: center;
            letter-spacing: 0.01rem;
        }
        .lastest-content li span {
            font-size:0.48rem;
            color: #78909c;
            letter-spacing: 0.02rem;
            margin-right:0.1rem;

        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
	<header class="header">
		<p>心率手环运动结果记录</p>
	</header>
   
    <div class="chart" style="display:none">
        图表数据区域
    </div>
    <div class="lastest clearfix">
        <div class="lastest-content">
         <section class="newest-data">
        <p>最新数据：</p>
    	</section>
            <ul class="clearfix">
                <li><strong>步数</strong><span v-html="latest.steps"></span>步</li>
                <li><strong>卡路里</strong><span v-html="latest.kcal"></span>千卡</li>
                <li><strong>里程</strong><span v-html="latest.mileage"></span>公里</li>
            </ul>
        </div>
    </div>
    <div class="history band-data-history">
        <div class="item clearfix" v-if="paramType == 'step'" v-for="h in historyBand">
            <p class="measure-date" >{{h.date | date('yyyy-MM-dd')}}
                <%--<span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>--%>
            </p>
            <div class="content">
                <p>
                    <span>步数：{{h.steps}}步</span>
                </p>
                <p>
                    <span>卡路里：{{h.kcal}}千卡</span>
                </p>
                <p>
                    <span>里程：{{h.mileage}}公里</span>
                </p>
            </div>
        </div>
        <div class="item clearfix" v-if="paramType == 'heartRate'" v-for="h in historyHeartRate">
            <p class="measure-date" >{{h.measureDate | date('yyyy-MM-dd hh:mm')}}
                <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>
            </p>
            <div class="content">
                <p>
                    <span>心率：<label :class="abnormal(h.heartRate, h.heartRateArea) ? 'red' : ''">{{h.heartRate}}</label>bpm</span>
                </p>
            </div>
        </div>
        <div class="item" v-if="paramType == 'sleep'" v-for="h in historyBand">
            <p class="measure-date" >{{h.date | date('yyyy-MM-dd')}}
                <%--<span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>--%>
            </p>
            <div class="content">
                <p>
                    <span>睡眠时长：{{h.sleepDuration}}分钟</span>
                </p>
                <p>
                    <span>深睡：{{h.deepDuration}}分钟</span>
                </p>
                <p>
                    <span>浅睡：{{h.shallowDuration}}分钟</span>
                </p>
                <p>
                    <span>苏醒：{{h.wakeupDuration}}分钟</span>
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
<script src="/static/mobile/wechat/js/device/device-band.js?v=1.10"></script>

<script>
    //设置设备类型
//    device.vm.deviceType = 'heartRate';
//    //历史数据
//    device.vm.listDevice(0);

    //设置设备类型
    device.vm.deviceType = 'band';
    //历史数据
    device.vm.listDevice(0);
</script>
</html>
<script>
</script>

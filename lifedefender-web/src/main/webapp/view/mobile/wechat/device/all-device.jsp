
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%--<meta name="flexible" content="initial-dpr=1" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/plugins/jeDate/css/jedate.css?v=2.2.0">
    <link rel="apple-touch-icon" href="favicon.png">
    <link rel="Shortcut Icon" href="favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="/static/plugins/layui/v2.1.2/css/layui.css">
    <title>健康数据</title>
    <style>
        [v-cloak] {
            display: none;
        }
        .vue-content {
            background-color: #f5f5f5;
            min-height: 100%;
        }
        .vue-content>div {
            font-size: 14px;
            padding: 1.5rem 0.4rem 0.2rem 01rem;;
            background-color: #f5f5f5;
            min-height: 100%;
        }
        .health {
            color: #66CC77;
            margin-bottom: 0.2rem;
        }
        .item {
            background-color: white;
            /*padding: 0.1rem 0.2rem 0.3rem 0.3rem;*/
            border-radius: 0.1rem;
            color: #9a9a9a;
            margin-bottom: 0.3rem;
        }
        .item .title {
            border-bottom: 1px solid #eaecee;
            line-height: 26px;
            padding-left: 0.3rem;
            padding-right: 0.2rem;
        }
        .item .title span:nth-of-type(2) {
            float: right;
        }
        .item .content {
            padding: 0.3rem;
            padding-left: 0.2rem;
        }
        .item .content img{
            width: 1rem;
            height: 1rem;
            float: left;
            margin-right: 0.2rem;
        }
        .item .content p {
            display: flex;
            text-align: center;
        }
        .item .content p span {
            width: 2rem;
        }

        .date-icon {
            background: url(/static/mobile/wechat/images/we-date.png) no-repeat;
            width: 0.7rem;
            height: 0.7rem;
            display: inline-block;
            vertical-align: middle;
            margin-left: 0.5rem;
            margin-right: 0.2rem;
            background-size: contain;

        }
        .layui-timeline-axis {
            /*width: 15px !important;*/
            /*height: 17px !important;*/
        }
        #date {
            border: none;
            background-color: #66cc77;
            color: white;
        }
    </style>

</head>
<body>
<div class="vue-content" v-cloak>

    <p style="line-height: 40px; background-color: #66cc77; color: white; font-size: 16px">
        <span class="date-icon" style=""></span>
        <!-- <input type="text" readonly id="date" :placeholder="date" v-model="date"> -->
        <span class="date-time fr">
        <input type="text" class="health-date" :placeholder="date" v-model = "date"
             style="background:url('/static/images/green_date_img.png') no-repeat right center; font-size: small;" readonly>   
        </span>
    </p>
    <ul v-if="deviceNumber != 0" class="layui-timeline" style="margin-left: 0.6rem; background-color: #f5f5f5; margin-right: 0.3rem;">
        <li class="layui-timeline-item">
            <%--<i class="layui-icon layui-timeline-axis">&#xe63f;</i>--%>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title" style="padding-top: 1rem; color: #66cc77; font-size: 16px;">健康数据</h3>

            </div>
        </li>
        <template v-for="d in list">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                    <div v-if="d.deviceName == 'Band'" class="item" @click="goDetails('band')">
                        <p class="title">
                            <span>心率手环</span>
                            <%--<span>{{d.measureTime}}</span>--%>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-band.png">
                            <p style="color: #66cc77">计步</p>
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'steps'">{{p.paramValue}}步</span>
                                    <span v-if="p.paramName == 'mileage'">{{p.paramValue}}公里</span>
                                    <span v-if="p.paramName == 'kcal'">{{p.paramValue}}千卡</span>
                                </template>

                            </p>
                            <p style="margin-left: 1.2rem; color: #66cc77">睡眠</p>
                            <p style="margin-left: 1.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'shallowDuration'">{{p.paramValue}}分钟</span>
                                    <span v-if="p.paramName == 'deepDuration'">{{p.paramValue}}分钟</span>
                                    <span v-if="p.paramName == 'wakeupDuration'">{{p.paramValue}}分钟</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem;">
                                <span >浅睡</span>
                                <span >深睡</span>
                                <span >清醒</span>
                            </p>
                            <p style="margin-left: 1.2rem; color: #66cc77">心率</p>
                            <p style="margin-left: 1.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>
                                </template>
                            </p>
                        </div>
                    </div>
                    <div v-if="d.deviceName == 'BloodPressure'" class="item" @click="goDetails('xyj')">
                        <p class="title">
                            <span>血压计</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-bloodpressure.png">
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'diastolic'">{{p.paramValue}}mmHg</span>
                                    <span v-if="p.paramName == 'systolic'">{{p.paramValue}}mmHg</span>
                                    <span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>
                                </template>

                            </p>
                            <p>
                                <span>舒张压</span>
                                <span>收缩压</span>
                                <span>心率</span>
                            </p>
                        </div>
                    </div>
                    <div v-if="d.deviceName == 'Lunginstrument'" class="item" @click="goDetails('fhy')">
                        <p class="title">
                            <span>肺活仪</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-lunginstrument.png">
                            <p style="line-height: 30px;">
                                <template v-for="p in d.param">
                                    <span>{{p.paramValue}}ml</span>
                                </template>
                            </p>
                        </div>
                    </div>
                    <div v-if="d.deviceName == 'Oxygen'" class="item" @click="goDetails('xyy')">
                        <p class="title">
                            <span>血氧仪</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-oxygen.png">
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'saturation'">{{p.paramValue}}%</span>
                                    <span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>
                                </template>

                            </p>
                            <p>
                                <span>血氧</span>
                                <span>心率</span>
                            </p>
                        </div>
                    </div>
                    <div v-if="d.deviceName == 'Glucometer'" class="item" @click="goDetails('xty')">
                        <p class="title">
                            <span>血糖仪</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-bloodsugar.png" style="margin-right: 0.4rem;">
                            <p style="line-height: 30px;">
                                <template v-for="p in d.param">
                                    <span>{{p.paramValue}}mmol/L</span>
                                </template>
                            </p>
                        </div>
                    </div>
                    <div v-if="d.deviceName == 'BodyFatScale'" class="item" @click="goDetails('tzc')">
                        <p class="title">
                            <span>体脂秤</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-badyfatscale.png">
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'weight'">{{p.paramValue}}kg</span>
                                    <span v-if="p.paramName == 'BMI'">{{p.paramValue}}</span>
                                    <span v-if="p.paramName == 'axungeRatio'">{{p.paramValue}}%</span>
                                </template>

                            </p>
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'weight'">体重</span>
                                    <span v-if="p.paramName == 'BMI'">BMI</span>
                                    <span v-if="p.paramName == 'axungeRatio'">体脂率</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'bodyage'">{{p.paramValue}}岁</span>
                                    <span v-if="p.paramName == 'WHR'">{{p.paramValue}}</span>
                                    <span v-if="p.paramName == 'moisture'">{{p.paramValue}}%</span>
                                </template>

                            </p>
                            <p style="margin-left: 1.2rem; ">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'bodyage'">体年龄</span>
                                    <span v-if="p.paramName == 'WHR'">腰臀比</span>
                                    <span v-if="p.paramName == 'moisture'">人体水分</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'muscle'">{{p.paramValue}}%</span>
                                    <span v-if="p.paramName == 'boneWeight'">{{p.paramValue}}kg</span>
                                    <span v-if="p.paramName == 'baseMetabolism'">{{p.paramValue}}kcal</span>
                                </template>

                            </p>
                            <p style="margin-left: 1.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'muscle'">人体肌肉</span>
                                    <span v-if="p.paramName == 'boneWeight'">骨骼重量</span>
                                    <span v-if="p.paramName == 'baseMetabolism'">基础代谢</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'visceralFat'">{{p.paramValue}}</span>
                                </template>

                            </p>
                            <p style="margin-left: 1.2rem;">
                                <span>内脏脂肪</span>
                            </p>
                        </div>
                    </div>

                    <div v-if="d.deviceName == 'Temperature'" class="item" @click="goDetails('twj')">
                        <p class="title">
                            <span>体温计</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-temperature.png" style="margin-right: 0.4rem;">
                            <p style="line-height: 30px;">
                                <template v-for="p in d.param">
                                    <span>{{p.paramValue}}℃</span>
                                </template>
                            </p>
                        </div>
                    </div>

                    <div v-if="d.deviceName == 'BloodLipid'" class="item" @click="goDetails('xzy')">
                        <p class="title">
                            <span>血脂仪</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-bloodlipid.png">
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'TC'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                                    <span v-if="p.paramName == 'TG'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                                </template>

                            </p>
                            <p>
                                <span style="width: 3rem;">总胆固醇</span>
                                <span style="width: 3rem;">甘油三酯</span>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'HDL'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                                    <span v-if="p.paramName == 'LDL'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                                </template>

                            </p>
                            <p style="margin-left: 1.5rem;">
                                <span style="width: 2.5rem;">高密度脂蛋白胆固醇</span>
                                <span style="width: 2.5rem; margin-left: 0.5rem;">低密度脂蛋白胆固醇</span>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'BloodLipidRation'" style="width: 3rem;">{{p.paramValue}}</span>
                                </template>

                            </p>
                            <p style="margin-left: 1.2rem;">
                                <span style="width: 3rem;">比值</span>
                            </p>
                        </div>
                    </div>

                    <div v-if="d.deviceName == 'URAN'" class="item" @click="goDetails('uran')">
                        <p class="title">
                            <span>尿液</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-uran.png">
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'SG'" style="width: 3rem;">比重: {{p.paramValue}}</span>
                                    <span v-if="p.paramName == 'BIL'" style="width: 3rem;">胆红素: {{p.paramValue}}</span>
                                </template>

                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'pH'" style="width: 3rem;">PH值: {{p.paramValue}}</span>
                                    <span v-if="p.paramName == 'NIT'" style="width: 3rem;">亚硝酸盐: {{p.paramValue}}</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'GLU'" style="width: 3rem;">葡萄糖: {{p.paramValue}}</span>
                                    <span v-if="p.paramName == 'LEU'" style="width: 3rem;">白细胞: {{p.paramValue}}</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'PRO'" style="width: 3rem;">蛋白质: {{p.paramValue}}</span>
                                    <span v-if="p.paramName == 'KET'" style="width: 3rem;">酮体: {{p.paramValue}}</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'UBG'" style="width: 3rem;">尿胆原: {{p.paramValue}}</span>
                                    <span v-if="p.paramName == 'VC'" style="width: 3rem;">VC维生素C: {{p.paramValue}}</span>
                                </template>
                            </p>
                            <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'BLD'" style="width: 3rem;">潜血: {{p.paramValue}}</span>
                                </template>
                            </p>

                        </div>
                    </div>

                    <div v-if="d.deviceName == 'UA'" class="item" @click="goDetails('ua')">
                        <p class="title">
                            <span>尿酸</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-ua.png" style="margin-right: 0.4rem;">
                            <p style="line-height: 30px;">
                                <template v-for="p in d.param">
                                    <span>{{p.paramValue}}mmol/L</span>
                                </template>
                            </p>
                        </div>
                    </div>

                    <div v-if="d.deviceName == 'ECG'" class="item" @click="goDetails('ecg')">
                        <p class="title">
                            <span>心电</span>
                            <span>{{d.measureTime}}</span>
                        </p>
                        <div class="content">
                            <img src="/static/mobile/wechat/images/we-health-ecg.png">
                            <p>
                                <template v-for="p in d.param">
                                    <span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>
                                    <%--<span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>--%>
                                </template>

                            </p>
                            <p>
                                <%--<span>血氧</span>--%>
                                <span>心率</span>
                            </p>
                        </div>
                    </div>
            </div>
        </li>
        </template>
    </ul>
    <div v-if="deviceNumber == 0" style="padding: 0px; text-align: center; margin-top: 100%; min-height:0px">
        <%--<img src="/static/mobile/wechat/images/we-no-data.png">--%>
        <br>
        <p style="color: #9a9a9a">暂无数据</p>
    </div>
   <%-- <div>
        <p class="health">健康包数据</p>
        <template v-for="d in list">
            <div v-if="d.deviceName == 'BloodPressure'" class="item" @click="goDetails('xyj')">
                <p class="title">
                    <span>血压计</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-bloodpressure.png">
                    <p>
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'diastolic'">{{p.paramValue}}mmHg</span>
                            <span v-if="p.paramName == 'systolic'">{{p.paramValue}}mmHg</span>
                            <span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>
                        </template>

                    </p>
                    <p>
                        <span>舒张压</span>
                        <span>收缩压</span>
                        <span>心率</span>
                    </p>
                </div>
            </div>
            <div v-if="d.deviceName == 'Lunginstrument'" class="item" @click="goDetails('fhy')">
                <p class="title">
                    <span>肺活仪</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-lunginstrument.png">
                    <p style="line-height: 30px;">
                        <template v-for="p in d.param">
                            <span>{{p.paramValue}}ml</span>
                        </template>
                    </p>
                </div>
            </div>
            <div v-if="d.deviceName == 'Oxygen'" class="item" @click="goDetails('xyy')">
                <p class="title">
                    <span>血氧仪</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-oxygen.png">
                    <p>
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'saturation'">{{p.paramValue}}%</span>
                            <span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>
                        </template>

                    </p>
                    <p>
                        <span>血氧</span>
                        <span>心率</span>
                    </p>
                </div>
            </div>
            <div v-if="d.deviceName == 'Glucometer'" class="item" @click="goDetails('xty')">
                <p class="title">
                    <span>血糖仪</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-bloodsugar.png" style="margin-right: 0.4rem;">
                    <p style="line-height: 30px;">
                        <template v-for="p in d.param">
                            <span>{{p.paramValue}}mmol/L</span>
                        </template>
                    </p>
                </div>
            </div>
            <div v-if="d.deviceName == 'BodyFatScale'" class="item" @click="goDetails('tzc')">
                <p class="title">
                    <span>体脂秤</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-badyfatscale.png">
                    <p>
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'weight'">{{p.paramValue}}kg</span>
                            <span v-if="p.paramName == 'BMI'">{{p.paramValue}}</span>
                            <span v-if="p.paramName == 'axungeRatio'">{{p.paramValue}}%</span>
                        </template>

                    </p>
                    <p>
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'weight'">体重</span>
                            <span v-if="p.paramName == 'BMI'">BMI</span>
                            <span v-if="p.paramName == 'axungeRatio'">体脂率</span>
                        </template>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'bodyage'">{{p.paramValue}}岁</span>
                            <span v-if="p.paramName == 'WHR'">{{p.paramValue}}</span>
                            <span v-if="p.paramName == 'moisture'">{{p.paramValue}}%</span>
                        </template>

                    </p>
                    <p style="margin-left: 1.2rem; ">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'bodyage'">体年龄</span>
                            <span v-if="p.paramName == 'WHR'">腰臀比</span>
                            <span v-if="p.paramName == 'moisture'">人体水分</span>
                        </template>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'muscle'">{{p.paramValue}}%</span>
                            <span v-if="p.paramName == 'boneWeight'">{{p.paramValue}}kg</span>
                            <span v-if="p.paramName == 'baseMetabolism'">{{p.paramValue}}kcal</span>
                        </template>

                    </p>
                    <p style="margin-left: 1.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'muscle'">人体肌肉</span>
                            <span v-if="p.paramName == 'boneWeight'">骨骼重量</span>
                            <span v-if="p.paramName == 'baseMetabolism'">基础代谢</span>
                        </template>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'visceralFat'">{{p.paramValue}}</span>
                        </template>

                    </p>
                    <p style="margin-left: 1.2rem;">
                        <span>内脏脂肪</span>
                    </p>
                </div>
            </div>

            <div v-if="d.deviceName == 'Temperature'" class="item" @click="goDetails('twj')">
                <p class="title">
                    <span>体温计</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-temperature.png" style="margin-right: 0.4rem;">
                    <p style="line-height: 30px;">
                        <template v-for="p in d.param">
                            <span>{{p.paramValue}}℃</span>
                        </template>
                    </p>
                </div>
            </div>

            <div v-if="d.deviceName == 'BloodLipid'" class="item" @click="goDetails('xzy')">
                <p class="title">
                    <span>血脂仪</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-bloodlipid.png">
                    <p>
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'TC'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                            <span v-if="p.paramName == 'TG'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                        </template>

                    </p>
                    <p>
                        <span style="width: 3rem;">总胆固醇</span>
                        <span style="width: 3rem;">甘油三酯</span>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'HDL'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                            <span v-if="p.paramName == 'LDL'" style="width: 3rem;">{{p.paramValue}}mmol/L</span>
                        </template>

                    </p>
                    <p style="margin-left: 1.5rem;">
                        <span style="width: 2.5rem;">高密度脂蛋白胆固醇</span>
                        <span style="width: 2.5rem; margin-left: 0.5rem;">低密度脂蛋白胆固醇</span>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'BloodLipidRation'" style="width: 3rem;">{{p.paramValue}}</span>
                        </template>

                    </p>
                    <p style="margin-left: 1.2rem;">
                        <span style="width: 3rem;">比值</span>
                    </p>
                </div>
            </div>

            <div v-if="d.deviceName == 'URAN'" class="item" @click="goDetails('uran')">
                <p class="title">
                    <span>尿液</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-uran.png">
                    <p>
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'SG'" style="width: 3rem;">比重: {{p.paramValue}}</span>
                            <span v-if="p.paramName == 'BIL'" style="width: 3rem;">胆红素: {{p.paramValue}}</span>
                        </template>

                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'pH'" style="width: 3rem;">PH值: {{p.paramValue}}</span>
                            <span v-if="p.paramName == 'NIT'" style="width: 3rem;">亚硝酸盐: {{p.paramValue}}</span>
                        </template>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'GLU'" style="width: 3rem;">葡萄糖: {{p.paramValue}}</span>
                            <span v-if="p.paramName == 'LEU'" style="width: 3rem;">白细胞: {{p.paramValue}}</span>
                        </template>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'PRO'" style="width: 3rem;">蛋白质: {{p.paramValue}}</span>
                            <span v-if="p.paramName == 'KET'" style="width: 3rem;">酮体: {{p.paramValue}}</span>
                        </template>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'UBG'" style="width: 3rem;">尿胆原: {{p.paramValue}}</span>
                            <span v-if="p.paramName == 'VC'" style="width: 3rem;">VC维生素C: {{p.paramValue}}</span>
                        </template>
                    </p>
                    <p style="margin-left: 1.2rem; margin-top: 0.2rem;">
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'BLD'" style="width: 3rem;">潜血: {{p.paramValue}}</span>
                        </template>
                    </p>

                </div>
            </div>

            <div v-if="d.deviceName == 'UA'" class="item" @click="goDetails('ua')">
                <p class="title">
                    <span>尿酸</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-ua.png" style="margin-right: 0.4rem;">
                    <p style="line-height: 30px;">
                        <template v-for="p in d.param">
                            <span>{{p.paramValue}}mmol/L</span>
                        </template>
                    </p>
                </div>
            </div>

            <div v-if="d.deviceName == 'ECG'" class="item" @click="goDetails('ecg')">
                <p class="title">
                    <span>心电</span>
                    <span>{{d.measureTime}}</span>
                </p>
                <div class="content">
                    <img src="/static/mobile/wechat/images/we-health-ecg.png">
                    <p>
                        <template v-for="p in d.param">
                            <span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>
                            &lt;%&ndash;<span v-if="p.paramName == 'heartRate'">{{p.paramValue}}bpm</span>&ndash;%&gt;
                        </template>

                    </p>
                    <p>
                        &lt;%&ndash;<span>血氧</span>&ndash;%&gt;
                        <span>心率</span>
                    </p>
                </div>
            </div>
        </template>
    </div>--%>


</div>
</body>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/plugins/jeDate/jeDate.js?v=2.2.0"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<%--<script src="/static/plugins/layui/v2.1.2/layui.js"></script>--%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/laydate/laydate.js"></script>
<script src="/static/mobile/wechat/js/dateFormat.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/device/all-device.js?v=1.10"></script>
<script>
    <%--device.vm.medicalId = '${medicalId}';--%>
     device.init();
    laydate.render({
        elem: '#date'
        ,done: function(value, date){
            device.vm.date = value;
            device.vm.listDevice();
        }
    }); 
</script>
</html>

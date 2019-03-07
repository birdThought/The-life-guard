<%--
  Created by IntelliJ IDEA.
  User: wenxian.cai
  Date: 2017/4/18
  Time: 16:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/user-index.css">
    <title>用户主页</title>
    <%--<script src="js/jquery-2.1.1.min.js"></script>--%>

</head>
<body>
<%@include file="../context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="../context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="../context/menu.jsp"%>
        <%--右边页面start--%>
        <div class="main-content vue-content">
            <div class="main-content-top">
                <a href="#">首页></a><span v-cloak>个人主页</span>
            </div>
            <ul class="main-first-ul">
                <li class="healthy-pac">
                    <h3>健康包数据</h3>
                    <div class="clearfix">
                        <p class="fl">心率手环</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <dl class="clearfix">
                        <dd class="clearfix">
                            <div id="main" style="height:200px;width:200px" class="fl"></div>
                            <ul class="fl main-details">
                                <li>
                                    <div class="kll">卡路里</div>
                                    <div class="kll-progress"><span :style = "progressClass('HR_band_kcal')"></span></div>
                                    <p><em v-cloak v-if = "band != null">{{band.kcal|division(1000)}}</em>千卡</p>
                                </li>
                                <li>
                                    <div class="kll course">里程</div>
                                    <div class="kll-progress blue-prog"><span :style = "progressClass('HR_band_mileage')"></span></div>
                                    <p><em v-cloak v-if = "band != null">{{band.mileage|division(1000)}}</em>公里</p>
                                </li>
                            </ul>
                        </dd>
                        <dd>
                            <div><img src="/static/platform/v2.2.0/images/xinlv.png" alt=""></div>
                            <div class="xinlv"><em v-cloak v-if = "heartRate != null">{{heartRate.heartRate}}</em>bpm</div>
                        </dd>
                        <dd>
                            <ul class="sleep-progress">
                                <li>
                                    <div><span :style = "progressClass('HR_band_deep')"></span></div>
                                    <div><span :style = "progressClass('HR_band_shallow')"></span></div>
                                </li>
                                <li>
                                    <p class="xinlv-title">
                                        <span>深睡</span>
                                        <span>浅睡</span>
                                    </p>
                                    <p>
                                        <label>深睡：</label>
                                        <span v-cloak v-if = "band != null">{{band.deepDuration | dateFormat('C_N')}}</span>
                                    </p>
                                    <p>
                                        <label>浅睡：</label>
                                        <span v-cloak v-if = "band != null">{{band.shallowDuration | dateFormat('C_N')}}</span>
                                    </p>
                                </li>
                            </ul>
                        </dd>
                        <dd></dd>
                    </dl>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span v-cloak v-if = "heartRate != null" :class = "statusClass(heartRate.status)">{{heartRate.status | status}}</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time">
                        <%--<a class="pre"></a>--%>
                        <span v-cloak v-if = "band != null">{{band.date | dateFormat('yyyy-MM-dd hh:mm')}}</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
                <li class="healthy-pac">
                    <div class="clearfix">
                        <p class="fl">体脂秤</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <div class="test-list clearfix">
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.weight}}</em>kg</dt>
                            <dd>体重</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.axungeRatio}}</em>%</dt>
                            <dd>体脂脂肪</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.bodyage}}</em>岁</dt>
                            <dd>体年龄</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.WHR}}</em>%</dt>
                            <dd>腰臀比</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.BMI}}</em>kg/m</dt>
                            <dd>BMI</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.fatFreeWeight}}</em>kg</dt>
                            <dd>去脂体重</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.moisture}}</em>%</dt>
                            <dd>人体水分</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.muscle}}</em>%</dt>
                            <dd>人体肌肉</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.boneWeight}}</em>kg</dt>
                            <dd>骨骼重量</dd>
                        </dl>
                        <dl>
                            <dt><em v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.baseMetabolism}}</em>kcal</dt>
                            <dd>基础代谢</dd>
                        </dl>
                    </div>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span v-cloak v-if = "bodyFatScale != null" :class = "statusClass(bodyFatScale.status)">{{bodyFatScale.status | status}}</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time">
                        <%--<a class="pre"></a>--%>
                        <span v-cloak v-if = "bodyFatScale != null">{{bodyFatScale.measureDate | dateFormat("yyyy-MM-dd hh:mm")}}</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
            </ul>
            <ul class="equiv-lists clearfix">
                <li>
                    <div class="clearfix">
                        <p class="fl list-title">血压计</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <section>
                        <p class="status">
                            <span>偏低</span>
                            <span>正常</span>
                            <span>异常</span>
                        </p>
                        <dl>
                            <dd class="clearfix ">
                                <p class="fl">
                                    <label>收缩压</label>
                                    <template v-cloak v-if = "bloodPressure != null">{{bloodPressure.systolic}}</template>mmHg
                                </p>
                                <div class="list-progress fr"><span :style = "progressClass('BP_systolic')"></span></div>
                            </dd>
                            <dd class="clearfix">
                                <p class="fl">
                                    <label>舒张压</label>
                                    <template v-cloak v-if = "bloodPressure != null">{{bloodPressure.diastolic}}</template>mmHg
                                </p>
                                <div class="list-progress fr"><span :style = "progressClass('BP_diastolic')"></span></div>
                            </dd>
                            <dd class="clearfix">
                                <p class="fl">
                                    <label>心率</label>
                                    <template v-cloak v-if = "bloodPressure != null">{{bloodPressure.heartRate}}</template>次/分
                                </p>
                                <div class="list-progress fr"><span :style = "progressClass('BP_heartRate')"></span></div>
                            </dd>
                        </dl>
                    </section>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span v-cloak v-if = "bloodPressure != null" :class = "statusClass(bloodPressure.status)">{{bloodPressure.status | status}}</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time-special">
                        <%--<a class="pre"></a>--%>
                        <span v-cloak v-if = "bloodPressure != null">{{bloodPressure.measureDate | dateFormat("yyyy-MM-dd hh:mm")}}</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
                <li>
                    <div class="clearfix">
                        <p class="fl list-title">肺活仪</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <section class="circle">
                        <div id="main-lung" style="height:200px;width:200px"></div>
                        <div>
                            <div><span>肺活量</span></div>
                            <p>
                                <label>肺活仪:</label><template v-cloak v-if = "instrument != null">{{instrument.vitalCapacity}}</template>ml
                            </p>
                        </div>
                    </section>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span v-cloak v-if = "instrument != null" :class = "statusClass(instrument.status)">{{instrument.status | status}}</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time-special">
                        <%--<a class="pre"></a>--%>
                        <span v-cloak v-if = "instrument != null">{{instrument.measureDate | dateFormat("yyyy-MM-dd hh:mm")}}</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
                <li>
                    <div class="clearfix">
                        <p class="fl list-title">血糖仪</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <section class="circle">
                        <div id="main-sugar" style="height:200px;width:200px"></div>
                        <div>
                            <div><span>血糖含量</span></div>
                            <p>
                                <label>血糖含量:</label><template v-cloak v-if = "bloodSugar != null">{{bloodSugar.bloodSugar}}</template>mmol/L
                            </p>
                        </div>
                    </section>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span v-cloak v-if = "bloodSugar != null" :class = "statusClass(bloodSugar.status)">{{bloodSugar.status | status}}</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time-special">
                        <%--<a class="pre"></a>--%>
                        <span v-cloak v-if = "bloodSugar != null">{{bloodSugar.measureDate | dateFormat("yyyy-MM-dd hh:mm")}}</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
                <li>
                    <div class="clearfix">
                        <p class="fl list-title">血氧仪</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <section>
                        <p class="status">
                            <span>偏低</span>
                            <span>正常</span>
                            <span>异常</span>
                        </p>
                        <dl>
                            <dd class="clearfix ">
                                <p class="fl">
                                    <label>血氧饱和度</label>
                                    <template v-cloak v-if = "oximeter != null">{{oximeter.saturation}}</template>%
                                </p>
                                <div class="list-progress fr"><span :style = "progressClass('O_saturation')"></span></div>
                            </dd>
                            <dd class="clearfix">
                                <p class="fl">
                                    <label>心率</label>
                                    <template v-cloak v-if = "oximeter != null">{{oximeter.heartRate}}</template>次/分
                                </p>
                                <div class="list-progress fr"><span :style = "progressClass('O_heartRate')"></span></div>
                            </dd>
                        </dl>
                    </section>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span v-cloak v-if = "oximeter != null" :class = "statusClass(oximeter.status)">{{oximeter.status | status}}</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time-special">
                        <%--<a class="pre"></a>--%>
                            <span v-cloak v-if = "oximeter != null">{{oximeter.measureDate | dateFormat("yyyy-MM-dd hh:mm")}}</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
                <li>
                    <div class="clearfix">
                        <p class="fl list-title">体温计</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <section class="circle">
                        <div id="main-tp" style="height:200px;width:200px"></div>
                        <div>
                            <div><span>体温测量</span></div>
                            <p>
                                <label>温度:</label><template v-cloak v-if = "temperature != null">{{temperature.temperature}}</template>℃
                            </p>
                        </div>
                    </section>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span v-cloak v-if = "temperature != null" :class = "statusClass(temperature.status)">{{temperature.status | status}}</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time-special">
                        <%--<a class="pre"></a>--%>
                            <span v-cloak v-if = "temperature != null">{{temperature.measureDate | dateFormat("yyyy-MM-dd hh:mm")}}</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
                <li>
                    <div class="clearfix">
                        <p class="fl list-title">心电仪</p>
                        <a href="#" class="fr">详情>></a>
                    </div>
                    <section class="circle">
                        <div><img src="/static/platform/v2.2.0/images/xindian.png" alt=""></div>
                        <div>
                            <p>
                                心率异常 <a href="#">2次</a>
                            </p>
                            <p>
                                手动标记 <a href="#">1次</a>
                            </p>
                        </div>

                    </section>
                    <div class="test-consult clearfix">
                        <label>检测结果</label>
                        <span>正常</span>
                        <a href="#" class="fr">正常范围值</a>
                    </div>
                    <div class="time-special">
                        <%--<a class="pre"></a>--%>
                        <span>2017年4月17日 11:30</span>
                        <%--<a class="next"></a>--%>
                    </div>
                </li>
            </ul>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/plugins/echarts-2.2.7/echarts.js"></script>
<script src="/static/platform/v2.2.0/js/member/usercenter/user-index.js"></script>
<script>
    var height=$(".main-content").height();
    $(".main-nav").css({
        borderRight:'1px solid #ddd',
        height:height
    });
    $(".container").css({
        border:'1px solid #ddd',
        height:height
    })
</script>


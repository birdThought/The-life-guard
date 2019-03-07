<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>用户主页</title>
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" type="text/css" href="/static/css/userPage.css">
    <script>
        window.onload=function () {
            menuSetting({

            });
        }
    </script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl"><a href="/login">主页</a></div>
            <div id="temp" style="position: relative;">
            <div class="infor fr" id="contextIframe" style="position: absolute;">
                <div class="mainbar" id="mainbar">
                    <div class="healthy-package">
                        <h3>健康包数据</h3>
                        <span class="flesh-icon"></span>
                    </div>
                </div>
                <div class="xinhuan" id="xinhuan">
                    <section class="xinhuan_top">
                        <h3>心率手环</h3>
                        <a href="javascript:_xlshxq();">详情>></a>
                    </section>
                    <section class="content">
                        <ul class="level_1">
                            <li class="run">
                                <div id="main" style="height:200px;width:200px"></div>
                            </li>
                            <li class="percent_1">
                                <span>卡路里</span>
                                <div class="progressBar">
                                    <div class="finish_1" id="kcal"></div>
                                    <p class="ing" id="">0 Kcal</p>
                                </div>
                                <span>里程</span>
                                <div class="progressBar_2">
                                    <div class="finish_2" id="mileage"></div>
                                    <p class="ing_2">0公里</p>
                                </div>
                            </li>
                            <li class="percent_1">
                                <img src="/static/images/quxian.png" alt="">
                                <p id="heartrate_1">0 bpm</p>
                                <p id="heartrate_2">心率：0bpm</p>
                            </li>
                            <li class="percent_1 percent">
                                <div class="percentAnto">
                                    <div class="progressBar progressBarAnto_1">
                                        <div class="finish_1" id="deepDuration_1"></div>
                                    </div>
                                    <div class="progressBar_2 progressBarAnto_2">
                                        <div class="finish_2" id="shallowDuration_1"></div>
                                    </div>
                                </div>
                            </li>
                            <li class="percent_1 ">
                                <span>深睡</span>
                                <span>浅睡</span>
                                <div id="deepDuration">深睡 0 min</div>
                                <div id="shallowDuration">浅睡 0 min</div>
                            </li>
                        </ul>
                        <section class="testResult">
                            <p>检查结果
                                <span id="heartRateStatus">正常</span>
                            </p>
                            <a href="javascript:void(0);" id="show_heartRateArea">正常范围值</a>
                        </section>
                    </section>
                    <section class="date">
                        <p id="heartrate_date">今日没有测量数据,请检查你的手环</p>
                    </section>
                    <div style="display:none;text-align:center;" id="heartRateArea"></div>
                </div>
                <div class="tizhicheng" id="tizhicheng">
                    <section class="tizhicheng_top">
                        <h3>体脂秤</h3>
                        <a href="javascript:_xzcxq();">详情>></a>
                    </section>
                    <section class="content">
                        <ul>
                            <li>
                                <p><strong><span id="bodyfatscaleWeight">0</span></strong><i>kg</i></p>
                                <p>体重</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleAxungeRatio">0</span></strong><i>%</i></p>
                                <p>体脂肪率</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleBodyage">0</span></strong><i>岁</i></p>
                                <p>体年龄</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleWHR">0</span></strong><i></i></p>
                                <p>腰臀比</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleBMI">0</span></strong><i></i></p>
                                <p>BMI</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleFatFreeWeight">0</span></strong><i>kg</i></p>
                                <p>去脂体重</p>
                            </li>
                        </ul>
                        <ul>
                            <li>
                                <p><strong><span id="bodyfatscaleMoisture">0</span></strong><i>%</i></p>
                                <p>人体水分</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleMuscle">0</span></strong><i>%</i></p>
                                <p>人体肌肉</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleBoneWeight">0</span></strong><i>kg</i></p>
                                <p>骨骼重量</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleBaseMetabolism">0</span></strong><i>kcal</i></p>
                                <p>基础代谢</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleProteide">0</span></strong><i>%</i></p>
                                <p>蛋白质</p>
                            </li>
                            <li>
                                <p><strong><span id="bodyfatscaleVisceralFat">0</span></strong><i></i></p>
                                <p>内脏脂肪</p>
                            </li>
                        </ul>
                        <section class="testResult">
                            <p>测试结果
                                <span id="bodyfatscaleStatus">正常</span>
                            </p>
                            <a href="javascript:void(0)" id="show_bodyfatscaleArea">正常范围值</a>
                        </section>
                    </section>
                    <section class="date">
                        <p id="bodyfatscale_date">你今天没有测量体脂秤</p>
                    </section>
                    <div style="display:none;text-align:center;" id="bodyfatscaleArea"></div>
                </div>
                <ul class="someEquiv">
                    <li class="xueyaji" id="xueyaji">
                        <section class="xueyaji_top">
                            <h3>血压计</h3>
                            <a href="javascript:_xyjxq();">详情>></a>
                        </section>
                        <section class="content">
                            <ol class="unorder">
                                <li>偏低</li>
                                <li>正常</li>
                                <li>异常</li>
                            </ol>
                            <div class="contentMid">
                                <ul>
                                    <li id="systolic">收缩压 0mmHg</li>
                                    <li id="diastolic">舒张压 0mmHg</li>
                                    <li id="bloodPressure_heartRate">心率 0bpm</li>
                                </ul>
                                <div class="percent_1">
                                    <div class="progressBar progressBar_1">
                                        <div class="finish_1" id="systolic_1"></div>
                                    </div>
                                    <div class="progressBar_2 progressBar_2">
                                        <div class="finish_2" id="diastolic_1"></div>
                                    </div>
                                    <div class="progressBar_2 progressBar_2">
                                        <div class="finish_2" id="heartRate_1"></div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section class="testResult">
                            <p>
                                测试结果 <span id="bloodPressureStatus">正常</span>
                            </p>
                            <a href="javascript:void(0);" id="show_bloodPressureArea">正常范围值</a>
                        </section>
                        <section class="date">
                            <p id="bloodPressure_status">你今天没有测量血压</p>
                        </section>
                        <div style="display:none;text-align:center;" id="bloodPressureArea"></div>
                    </li>
                    <li class="feihuoyi" id="feihuoyi">
                        <section class="feihuoyi_top">
                            <h3>肺活仪</h3>
                            <a href="javascript:_fhyxq();">详情>></a>
                        </section>
                        <section class="content">
                            <ul class="contentLs">
                                <li>
                                    <div id="mainLung" style="height: 150px; width: 150px"></div>
                                </li>
                                <li class="ctSummarize">
                                    <p>肺活量</p>
                                    <p id="vitalCapacity">肺活仪：0ml</p>
                                </li>
                            </ul>
                        </section>
                        <section class="testResult">
                            <p>
                                测试结果 <span id="lunginstrumentStatus">正常</span>
                            </p>
                            <a href="javascript:void(0)" id="show_lunginstrumentArea">正常范围值</a>
                        </section>
                        <section class="date">
                            <p id="lunginstrument_date">你今天没有测量肺活量</p>
                        </section>
                        <div style="display:none;text-align:center;" id="lunginstrumentArea"></div>
                    </li>
                    <li class="xuetangyi" id="xuetangyi">
                        <section class="xuetangyi_top">
                            <h3>血糖仪</h3>
                            <a href="javascript:_xtyxq();">详情>></a>
                        </section>
                        <section class="content">
                            <ul class="contentLs">
                                <li>
                                    <div id="mainSugar" style="height: 150px; width: 150px"></div>
                                </li>
                                <li class="ctSummarize">
                                    <p>血糖含量</p>
                                    <p id="bloodSugar">血糖含量：0mmol/L</p>
                                </li>
                            </ul>
                        </section>
                        <section class="testResult">
                            <p>
                                测试结果 <span id="glucometerStatus">正常</span>
                            </p>
                            <a href="javascript:void(0);" id="show_bloodSugarArea">正常范围值</a>
                        </section>
                        <section class="date">
                            <p id="glucometer_date">你今天没有测量血糖</p>
                        </section>
                        <div style="display:none;text-align:center;" id="bloodSugarArea"></div>
                    </li>
                    <li class="temperature" id="temperature">
                        <section class="temperature_top">
                            <h3>体温计</h3>
                            <a href="#">详情>></a>
                        </section>
                        <section class="content">
                            <ul class="contentLs">
                                <li>
                                    <div id="mainHt" style="height: 150px; width: 150px"></div>
                                </li>
                                <li class="ctSummarize">
                                    <p>体温测量</p>
                                    <p id="temperature_p">温度：0°C</p>
                                </li>
                            </ul>
                        </section>
                        <section class="testResult">
                            <p>
                                测试结果 <span id="temperatureStatus">正常</span>
                            </p>
                            <a href="javascript:void(0);" id="show_temperatureArea">正常范围值</a>
                        </section>
                        <section class="date">
                            <p id="temperature_date">你今天没有测量体温</p>
                        </section>
                        <div style="display:none;text-align:center;" id="temperatureArea"></div>
                    </li>
                    <li class="xueyangyi" id="xueyangyi">
                        <section class="xueyangyi_top">
                            <h3>血氧仪</h3>
                            <a href="javascript:_xyyxq();">详情>></a>
                        </section>
                        <section class="content">
                            <ol class="unorder">
                                <li>偏低</li>
                                <li>正常</li>
                                <li>异常</li>
                            </ol>
                            <div class="contentMid">
                                <ul>
                                    <li id="saturation">血氧饱和度&nbsp;&nbsp;0%</li>
                                    <li id="oxygen_heartRate">心率 0bpm</li>
                                </ul>
                                <div class="percent_1">
                                    <div class="progressBar progressBar_1">
                                        <div class="finish_1" id="saturation_1"></div>
                                    </div>
                                    <div class="progressBar_2 progressBar_2">
                                        <div class="finish_2" id="oxygen_heartRate_1"></div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section class="testResult">
                            <p>
                                测试结果 <span id="oxygen_status">正常</span>
                            </p>
                            <a href="javascript:void(0)" id="show_oxygenArea">正常范围值</a>
                        </section>
                        <section class="date">
                            <p id="oxygen_date">你今天没有测量血氧</p>
                        </section>
                        <div style="display:none;text-align:center;" id="oxygenArea"></div>
                    </li>
                </ul>
                <div class="mainbar_2" id="mainbar_2">
                    <div class="healthy-function">
                        <h3>HL系列数据</h3>
                        <span class="flesh-icon"></span>
                    </div>
                    <div class="small-nav">
                        <ul>
                            <li><a href="#">健康功能</a></li>
                            <li><a href="#">近期定位</a></li>
                        </ul>
                        <div><a href="javascript:_hlMore();">更多功能</a></div>
                    </div>
                    <ul class="sort">
                        <li class="heartRate">
                            <section class="title_top">
                                <h3>心率 <a href="javascript:void(0);">详情</a></h3>
                            </section>
                            <section class="content">
                                <img src="/static/images/quxian.png" alt="">
                                <p id="_heartRate1">0 bpm</p>
                                <p id="_heartRate2">心率：0bpm</p>
                            </section>
                            <section class="testResult">
                                <p>测试结果
                                    <span id="_heartRateStatus">正常</span>
                                </p>
                                <a href="javascript:void(0);" id="show_hlHeartRate">正常范围值</a>
                            </section>
                            <div style="display:none;text-align:center;" id="hlHeartRate"></div>
                            <section class="date">
                                <p id="_heartRate_date">你今天没有测量心率</p>
                            </section>
                        </li>
                        <li class="blood">
                            <section class="title_top">
                                <h3>血压 <a href="javascript:void(0);">详情</a></h3>
                            </section>
                            <section class="content">
                                <ol>
                                    <li>收缩压</li>
                                    <li>舒张压</li>
                                </ol>
                                <div class="pgBars">
                                    <span id="_systolic">收缩压&nbsp;&nbsp;0mmHg</span>
                                    <div class="pgBar pg_1 ">
                                        <div class="ok_1" id="_systolic_1"></div>
                                    </div>
                                    <span id="_diastolic">舒张压&nbsp;&nbsp;0mmHg</span>
                                    <div class="pgBar pg_2">
                                        <div class="ok_2" id="_diastolic_1"></div>
                                    </div>
                                </div>
                            </section>
                            <section class="testResult">
                                <p>测试结果
                                    <span id="_bloodPressureStatus">正常</span>
                                </p>
                                <a href="javascript:void(0);" id="show_hlBloodPressureArea">正常范围值</a>
                            </section>
                            <div style="display:none;text-align:center;" id="hlBloodPressureArea"></div>
                            <section class="date">
                                <p id="blood_date">你今天没有测量血压</p>
                            </section>
                        </li>
                        <li class="elect">
                            <section class="title_top">
                                <h3>心电 <a href="javascript:void(0);">详情</a></h3>
                            </section>
                            <section class="content">
                                <img src="/static/images/elect.png" alt="">
                                <p id="_eCG">0bpm</p>
                            </section>
                            <section class="testResult">
                                <p>测试结果
                                    <span id="_ecgStatus">正常</span>
                                </p>
                                <a href="javascript:void(0);" id="show_hlEcgArea">正常范围值</a>
                            </section>
                            <div style="display:none;text-align:center;" id="hlEcgArea"></div>
                            <section class="date">
                                <p id="ecg_date">你今天没有测量心电</p>
                            </section>
                        </li>
                        <li class="stepCount">
                            <section class="title_top">
                                <h3>计步器　<a href="javascript:void(0);">详情</a></h3>
                            </section>
                            <section class="content">
                                <div id="mainSc" style="height:150px;width:242px"></div>
                            </section>
                            <section class="testResult">
                                <ol>
                                    <li>
                                        <p>卡路里</p>
                                        <p id="_kcal">0Kcal</p>
                                    </li>
                                    <li>
                                        <p>里程</p>
                                        <p id="_mileage">0公里</p>
                                    </li>
                                </ol>
                            </section>
                            <section class="date">
                                <p id="step_date">你今天没有测量计步</p>
                            </section>
                        </li>
                        <li class="sleep">
                            <section class="title_top">
                                <h3>睡眠　<a href="javascript:void(0);">详情</a></h3>
                            </section>
                            <section class="content">
                                <ul>

                                    <li class="sleepy">
                                        <div class="repgBars">
                                            <div class="repgBar_1">
                                                <div class="reok_1" id="_deepDuration_1"></div>
                                            </div>
                                            <div class="repgBar_2 ">
                                                <div class="reok_2" id="_shallowDuration_1"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="slep">
                                        <span>深睡</span>
                                        <span>浅睡</span>
                                        <div id="_deepDuration">深睡 0 min</div>
                                        <div id="_shallowDuration">浅睡 0 min</div>
                                    </li>
                                </ul>
                            </section>
                            <section class="testResult">    <!-- <!-- style="margin-bottom: 40px;" -->
                                <p>测试结果
                                    <span>正常</span>
                                </p>
                            </section>
                            <section class="date">
                                <p id="sleep_date">你今天没有测量睡眠</p>
                            </section>
                        </li>
                        <li class="temper">
                            <section class="title_top">
                                <h3>体温<a href="javascript:void(0);">详情</a></h3>
                            </section>
                            <section class="content">
                                <div id="mainTp" style="height:150px;width:242px"></div>
                            </section>
                            <section class="testResult">
                                <p>测试结果
                                    <span id="_temperatureStatus">正常</span>
                                </p>
                                <a href="javascript:void(0);" id="show_hlTemperature">正常范围值</a>
                            </section>
                            <div style="display:none;text-align:center;" id="hlTemperature"></div>
                            <section class="date">
                                <p id="temper_date">你今天没有测量体温</p>
                            </section>
                        </li>
                    </ul>
                </div>
                <div class="mainbar_3" id="mainbar_3">
                    <div class="healthy-function">
                        <h3>C系列数据 </h3>
                        <span class="flesh-icon"></span>
                    </div>
                    <div class="latelyLocate">
                        <ul>
                            <li><a href="#">近期定位</a> <span id="c3_mobile"> 123423424</span></li>
                            <li class="func"><a href="javascript:_cMore();">更多功能</a></li>
                        </ul>
                    </div>
                    <div class="last-nav">
                        <ul class="ulArray">
                            <li>时间</li>
                            <li>位置</li>
                            <!-- <li>电话号码</li> -->
                            <li>操作</li>
                        </ul>
                        <ul class="ulAdd">

                        </ul>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#temp").css("height",$("#contextIframe").height());
    });
</script>
<script type="text/javascript" src="/static/plugins/echarts-2.2.7/echarts.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/userPage.js"></script>
</body>
</html>
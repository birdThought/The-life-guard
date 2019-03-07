<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>C系列手环</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui">
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link rel="stylesheet" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/css/Cseries.css">
    <link rel="stylesheet" href="/static/plugins/Jrange/css/jquery.range.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jeDate/css/jedate.css">
    <link rel="stylesheet" href="/static/css/noticeLayer.css">
    <link href="/static/login/css/green.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj"></script><!--U2glmFNDRvYgft0vED17VpNj-->
    <t:base type="jquery,layer"></t:base>

    <script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
    <script type="text/javascript" src="/static/plugins/XzsTimePicker/TimerPicker.js"></script>

    <script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>

</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%-- <%@include file="/context/nav_left.jsp" %> --%>
        <!-- <div class="right-wrap"> -->
            <!-- <div class="title fl">
                <a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;C3手环
            </div> -->
            <div class="container fr" style="width:auto">
                <section class="introduce">
                    <h3>C系列手环
                        <span class="imeiInfo" style="margin-left: 400px; color: gray;">
						<span>Imei号:<span style="font-size: 14px;margin-left: 5px;"><!-- 15521286083 --></span></span>
						<span style="margin-left: 20px;">状态:<span style="margin-left: 5px;"><!-- 在线 --></span></span>
						<span style="margin-left: 20px;"><input type="button" value="关机" style="width: 60px;"></span>
					</span>
                    </h3>
                </section>
                <ul class="Cseries_nav fl">
                    <li class="CsTitle">位置功能</li>
                    <li data-id="locationRecord" class="action" onclick="_clickNav(this)">定位记录</li>
                    <li data-id="currentLocate" onclick="_clickNav(this)">当前定位
                    </li>
                    <li data-id="electricFence" onclick="_clickNav(this)">电子围栏
                    </li>
                    <li data-id="orbitRun" onclick="_clickNav(this)">轨迹回放</li>
                    <li data-id="monitor" onclick="_clickNav(this)">实时监听</li>
                    <li class="CsTitle"><a href="javascript:void(0);">设备设置</a></li>
                    <li data-id="changeNumber" onclick="_clickNav(this)">设备号码修改
                    </li>
                    <li data-id="monitorRate" onclick="_clickNav(this)">监控频率</li>
                    <li data-id="remind" onclick="_clickNav(this)">提醒设置</li>
                    <!-- <li data-id="blackList" onclick="_clickNav(this)">黑名单</li> -->
                    <li data-id="familyList" onclick="_clickNav(this)">亲情号码</li>
                    <li data-id="runMode" onclick="_clickNav(this)">运行模式</li>
                </ul>
                <div class="CsContent fr">
                    <%@include file="/context/locationRecord.jsp" %>
                    <%@include file="/context/currentLocate.jsp" %>
                    <%@include file="/context/electricFence.jsp" %>
                    <%@include file="/context/orbitRun.jsp" %>
                    <%@include file="/context/monitor.jsp" %>
                    <%@include file="/context/changeNumber.jsp" %>
                    <div class="monitorRate nav_none" id="monitorRate">
                        <dl>
                            <dt>监控频率</dt>
                            <dd>“轨迹回放”或“电子围栏”的试用状态下，手环发送位置信息的频率，须知位置信息发送频率越高，手环的耗电量就好相对的增加(单位：秒)</dd>
                        </dl>
                        <ul>
                            <li><span>手表默认信息同步上传频率</span> 
                            <select name="heartFrequency" id="heartFrequency">
                                <option value="30">30</option>
                                <option value="60">60</option>
                                <option value="90">90</option>
                                <option value="120">120</option>
                            </select> 
                            <img src="/static/images/question.png" alt="help">
                            </li>
                            <li>
                            <span>手表默认上传位置信息频率</span> 
                            <select name="locationFrequency">
                                <option value="60">60</option>
                                <option value="90">90</option>
                                <option value="120">120</option>
                                <option value="150">150</option>
                                <option value="180">180</option>
                            </select> 
                            <img src="/static/images/question.png" alt="help">
                            </li>
                            <!-- <li><span>当电量低于70%时，降频至</span> <select
                                name="autoFrequency70">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                            </select> <img src="/static/images/question.png" alt="help"></li>
                            <li><span>当电量低于50%时，降频至</span> <select
                                name="autoFrequency50">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                            </select> <img src="/static/images/question.png" alt="help"></li>
                            <li><span>当电量低于30%时，降频至</span> <select
                                name="autoFrequency30">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                            </select> <img src="/static/images/question.png" alt="help"></li> -->
                            <li><a href="javascript:_clickNav('resetMonitorRate');">
                                <button
                                        type="reset">恢复默认
                                </button>
                            </a>
                                <button type="submit" onclick="setMonitorRate()">提交</button>
                            </li>
                        </ul>
                    </div>
                    <%@include file="/context/remind.jsp" %>
                    <%@include file="/context/blackList.jsp" %>
                    <%@include file="/context/fimalyList.jsp" %>
                    <div class="runMode nav_none" id="runMode">
                        <dl>
                            <dt>运行模式</dt>
                            <dd>在不同的运行模式下，根据系统设置项对手环用户的使用环境和人群惊醒模式切换</dd>
                        </dl>
                        <ul>
                            <li><input type="radio" name="radMode" id="runMode1"
                                       value="1">
                                <dl>
                                    <dt>默认模式</dt>
                                    <dd>可以一键拨通设置的监护人电话，来电铃声，有语音提示。</dd>
                                </dl>
                            </li>
                            <!-- <li><input type="radio" name="radMode" id="runMode1"
                                value="1" placeholder="">
                                <dl>
                                    <dt>家长模式</dt>
                                    <dd>可以一键拨通设置的监护人电话/可以接听黑名单之外的任意来电，来电铃声，有语音提示</dd>
                                </dl></li> -->
                            <li><input type="radio" name="radMode" id="runMode2"
                                       value="2" placeholder="">
                                <dl>
                                    <dt>飞行模式(GPS模式)</dt>
                                    <dd>不能拨打/接听任意电话，无语音提示，无振动，GPS可被动开启</dd>
                                </dl>
                            </li>
                            <!-- <li style = "height:80px;"><input type="radio" name="radMode" id="runMode3"
                                value="3" placeholder="">
                                <dl>
                                    <dt>儿童模式</dt>
                                    <dd>可以一键拨通设置的监护人电话/接听设置的监护人来电/接听限制接听列表里的白名单电话，来电铃声加震动，有语音提示。该模式下不作任何响铃三声后自动接听。</dd>
                                </dl></li> -->
                            <li><input type="radio" name="radMode" id="runMode4"
                                       value="4" placeholder="">
                                <dl>
                                    <dt>校园模式</dt>
                                    <dd>来电只有震动，无语音提示，GPS可被动开启。</dd>
                                </dl>
                            </li>
                            <!-- <li><input type="radio" name="radMode" id="runMode6"
                                value="6" class="goToClass">
                                <dl>
                                    <dt>上课隐身</dt>
                                    <dd>不能拨打/接听任意电话，无语音提示，无振动，自能定位与监听</dd>
                                </dl></li> -->
                        </ul>
                        <div class="remindDate">
                            <h4>选择日期</h4>
                            <ul class="remindFirst">
                                <li><input type="checkbox" name="week" value="一">
                                    星期一
                                </li>
                                <li><input type="checkbox" name="week" value="二">
                                    星期二
                                </li>
                                <li><input type="checkbox" name="week" value="三">
                                    星期三
                                </li>
                                <li><input type="checkbox" name="week" value="四">
                                    星期四
                                </li>
                                <li><input type="checkbox" name="week" value="五">
                                    星期五
                                </li>
                                <li><input type="checkbox" name="week" value="六">
                                    星期六
                                </li>
                                <li><input type="checkbox" name="week" value="日">
                                    星期日
                                </li>
                            </ul>
                            <ul class="remindSecond">
                                <!-- <li><span>提醒时间</span> <select name="remindHour1" size="1">
                                        <option value="">--</option>
                                </select> <span>时</span> <select name="remindMin1">
                                        <option value="">--</option>
                                </select> <span>分</span></li>
                                <li><span>提醒时间</span> <select name="remindHour2">
                                        <option value="">--</option>
                                </select> <span>时</span> <select name="remindMin2">
                                        <option value="">--</option>
                                </select> <span>分</span></li>
                                <li><span>提醒时间</span> <select name="remindHour3">
                                        <option value="">--</option>
                                </select> <span>时</span> <select name="remindMin3">
                                        <option value="">--</option>
                                </select> <span>分</span></li> -->
                                <li><input type="checkbox" id="time1" name="time1" value="time1">
                                    时间段一
                                    <br>
                                    <input id="start_one2" name="start_one" type="text" value="00:00" onclick="TimePicker.showTimePicker(this)" readonly/>
                                    <em>至</em>
                                    <input id="end_one2" name="end_one" type="text" value="00:00" onclick="TimePicker.showTimePicker(this)" readonly/>
                                </li>
                                <li><input type="checkbox" id="time2" name="time2" value="time2">
                                    时间段二
                                    <br>
                                    <input id="start_two2" name="start_two" type="text" value="00:00" onclick="TimePicker.showTimePicker(this)" readonly/>
                                    <em>至</em>
                                    <input id="end_two2" name="end_two" type="text" value="00:00" onclick="TimePicker.showTimePicker(this)" readonly/>
                                </li>
                                <li><input type="checkbox" id="time3" name="time3" value="time3">
                                    时间段三
                                    <br>
                                    <input id="start_three2" name="start_three" type="text" value="00:00" onclick="TimePicker.showTimePicker(this)" readonly/>
                                    <em>至</em>
                                    <input id="end_three2" name="end_three" type="text" value="00:00" onclick="TimePicker.showTimePicker(this)" readonly/>
                                </li>
                            </ul>
                            <dl>
                                <dt>上课隐身</dt>
                                <dd>1、请在时段设置栏内修改时间</dd>
                                <dd>2、如果要关闭某一时段，请把时段相应的启用取消钩选</dd>
                                <dd>3、在时间段内终端处于GPS模式</dd>
                                <dd>4、在时间段外终端处于校园模式</dd>
                            </dl>
                        </div>
                        <button type="submit" onclick="setRunMode()">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    seajs.config({
        alias: {
            "icheck": "/static/login/js/icheck.js",
            "dateFormat": "/static/officialwebsite/js/dateFormat.js",
            "location": "/static/com/lifeshs/member/js/location.js",
            "remind": "/static/com/lifeshs/member/js/remind.js",
            "changeNumber": "/static/com/lifeshs/member/js/changeNumber.js",
            "blackList": "/static/com/lifeshs/member/js/blackList.js",
            "fimalyList": "/static/com/lifeshs/member/js/fimalyList.js",
            "monitor": "/static/com/lifeshs/member/js/monitor.js",
            "jqueryRange": "/static/plugins/Jrange/js/jquery.range-min.js",
            "jeDate": "/static/plugins/jeDate/jedate.min.js",
            "timePicker": "/static/plugins/XzsTimePicker/TimerPicker.js",
        }
    });

    seajs.use(['/static/officialwebsite/js/Cseries.js'], function () {
        jeDate({
            dateCell: "#orbit_date",
            format: "YYYY-MM-DD",
            isinitVal: true,
            initAddVal: [0],
            minDate: "1900-01-01",
            maxDate: jeDate.now(0),
            startMin: "1900-01-01",
            startMax: jeDate.now(0),
            zindex: 999,
            choosefun: function (elem, val) {
                //val为获取到的时间值
            }
        });
    });
    /* $(function() {
        menuSetting({
            parent : 1,
            child : 2
        });
    }); */
</script>
</body>
</html>
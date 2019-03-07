<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>便携式健康包</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css"/>
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/css/health_package.css">
    <link rel="stylesheet" href="/static/plugins/Jrange/css/jquery.range.css">
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
    <link rel="stylesheet" href="/static/plugins/poshytip-master/src/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <t:base type="jquery,layer"></t:base>
    <style>
        .jedatebox{width:300px}
        .changeNormalColor {
            color: red;
        }
        [v-cloak] {
        	display: none;
        }
    </style>
    <script type="text/javascript" src="/static/plugins/tableExport/jquery.base64.js"></script>
    <script type="text/javascript" src="/static/plugins/tableExport/tableExport.js"></script>
    <script type="text/javascript" src="/static/com/lifeshs/member/js/healthDataUtil.js"></script>
    <script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
    <script type="text/javascript" src="/static/plugins/jeDate/jedate.min.js"></script>
    
</head>
<body onload="wol();">
    <div class="webPage wrap" style="border: 1px solid #ddd !important">
        <%@include file="/context/header.jsp"%>
        <div class="view-body wrap">
            <%@include file="/context/nav_left.jsp"%>
            <div class="right-wrap">
            <div class="title fl">主页</div>
            <div class="container fr" style="display:block;">
                <section class="introduce">
                    <h3 style="float: left">便携式健康包</h3>
                    <h3 style="display:none;" class="addData" >手动添加数据</h3>
                </section>
                
                <h3 class="tishi" align="center" style="display:none;">暂未绑定任何设备</h3>
                <ul class="setParam" style="display: none;"></ul>
                <ul class="nav_shebei">
                    <li data-name="healthData" class="healthyTitle" style="display:none;">健康数据</li>
                    <li data-id="xl" class="healthyXl action" style="display:none;">心率手环</li>
                    <li data-id="fhy" class="healthyFhy" style="display:none;">肺活仪</li>
                    <li data-id="xty"  class="healthyXty" style="display:none;">血糖仪</li>
                    <li data-id="xyy"  class="healthyXyy" style="display:none;">血氧仪</li>
                    <li data-id="xyj"  class="healthyXyj" style="display:none;">血压计</li>
                    <li data-id="tzc"  class="healthyTzc" style="display:none;">体脂秤</li>
                    <li data-id="temperature"  class="healthyTwj" style="display:none;">体温计</li>
                    <li data-name="paramSetting" class="healthyTitle" style="display:none;">参数设置</li>
                    <li data-id="remind" class="healthySz" style="display:none;">手环提醒</li>
                    <!-- <li data-id="sms" class="healthySms" style="display:none;">短信预警</li> -->
                </ul>
                 <div class="content nav_cont">
                    <%@include file="/context/heartRateBracelet.jsp"%>
                    <%@include file="/context/lungInstrument.jsp"%>
                    <%@include file="/context/bloodGlucoseMeter.jsp"%>
                    <%@include file="/context/oxiMeter.jsp"%>
                    <%@include file="/context/bloodPressureMeter.jsp"%>
                    <%@include file="/context/bodyFatScale.jsp"%>   
                    <%@include file="/context/temperature.jsp"%>    
                    <%@include file="/context/remind.jsp"%>
                    <%-- <%@include file="/context/smsWarning.jsp"%> --%>
                </div> 
            </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
var version = "0.01";
seajs.config({
    base : './static/',
    alias : {
        'highstock': 'officialwebsite/js/highstock.js',
        'icheck': 'login/js/icheck-1.0.2.js',
        'calculate': 'officialwebsite/js/calculate.js',
        'dateFormat': 'officialwebsite/js/dateFormat.js',
        'lungInstrument': 'officialwebsite/js/lungInstrument.js?v=0.001',
        'bloodPressureMeter': 'officialwebsite/js/bloodPressureMeter.js?v=0.001',
        'bloodGlucoseMeter': 'officialwebsite/js/bloodGlucoseMeter.js?v=0.001',
        'oxiMeter': 'officialwebsite/js/oxiMeter.js?v=0.001',
        'heartRateBracelet': 'officialwebsite/js/heartRateBracelet.js?v=0.001',
        'bodyFatScale': 'officialwebsite/js/bodyFatScale.js?v=0.001',
        'temperature': 'officialwebsite/js/temperature.js?v=0.001',
        'remind': 'com/lifeshs/member/js/remind.js',
        'healthDataUtil': 'com/lifeshs/member/js/healthDataUtil.js',
        'timePicker': 'plugins/XzsTimePicker/TimerPicker.js',
        'jqueryRange': 'plugins/Jrange/js/jquery.range-min.js',
        'jQueryPoshytip': 'plugins/poshytip-master/src/jquery.poshytip.min.js'
    }
});
seajs.use(['officialwebsite/js/health_package.js'], function() {
    $(function(){
        menuSetting({
            parent : 0,
            child : 1
        });
        
    });
});

function wol() {
    var mydateInput = document.getElementsByName("date");
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var dateString = date.getFullYear() + "-" + month + "-" + day;
    for (i = 0; i < mydateInput.length; i++) {
        mydateInput[i].value = dateString;
    }
}
</script>
</html>
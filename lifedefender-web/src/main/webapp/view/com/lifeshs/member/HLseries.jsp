<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>HL系列手环</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico" />
<link rel="stylesheet" href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" href="/static/css/noticeLayer.css">
<link rel="stylesheet" href="/static/css/Cseries.css">
<link rel="stylesheet" href="/static/plugins/Jrange/css/jquery.range.css">
<link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
<link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
<t:base type="jquery,layer"></t:base>

<script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=U2glmFNDRvYgft0vED17VpNj"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>

</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl"><a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;HL-03手环</div>
			<div class="container fr">
				<section class="introduce">
					<h3>HL系列手环</h3>
				</section>
				<ul class="HLseries_nav fl">
					<li class="HLTitle"><a href="javascript:void(0);">健康数据</a></li>
					<li data-id="bloodPressure" class="action" onclick="_clickNav(this)">血压</li>
					<li data-id="temperature" onclick="_clickNav(this)">体温</li>
					<li data-id="heartRate" onclick="_clickNav(this)">心率</li>
					<li data-id="stepCounter" onclick="_clickNav(this)">计步</li>
					<li data-id="sleep" onclick="_clickNav(this)">睡眠</li>
					<!-- <li data-id="heartElect" onclick="_clickNav(this)">心电</li>  -->
					<li class="HLTitle">位置功能</li>
					<li data-id="currentLocate" onclick="_clickNav(this)">当前定位</li>
					<li data-id="electricFence" onclick="_clickNav(this)">电子围栏</li>
					<li data-id="orbitRun" onclick="_clickNav(this)">轨迹回放</li>
					<li data-id="monitor" onclick="_clickNav(this)">实时监听</li>
					<li class="HLTitle">设备设置</li>
					<li data-id="changeNumber" onclick="_clickNav(this)">设备号码修改</li>
					<li data-id="remind" onclick="_clickNav(this)">提醒设置</li>
					<li data-id="blackList" onclick="_clickNav(this)">黑名单</li>
				</ul>
				<div class="HLContent fr">
					<%@include file="/context/bloodPressure.jsp"%>
					<%@include file="/context/temperature.jsp"%>
					<%@include file="/context/heartRate.jsp"%>
					<%@include file="/context/stepCounter.jsp"%>
					<%@include file="/context/sleep.jsp"%>
					<%@include file="/context/heartElect.jsp"%>
					<%@include file="/context/currentLocate.jsp"%>	
					<%@include file="/context/electricFence.jsp"%>
					<%@include file="/context/orbitRun.jsp"%>
					<%@include file="/context/monitor.jsp"%>
					<%@include file="/context/changeNumber.jsp"%>
					<%@include file="/context/remind.jsp"%>
					<%@include file="/context/blackList.jsp"%>
				</div>
			</div>
				</div>
		</div>
	</div>
</body>

<script type="text/javascript">
seajs.config({
	base : './static/',
	alias : {
		"icheck" : "login/js/icheck-1.0.2.js",
		"highcharts" : "officialwebsite/js/highcharts.js",
		"calculate" : "officialwebsite/js/calculate.js",
		"dateFormat" : "officialwebsite/js/dateFormat.js",
		"heartRate" : "officialwebsite/js/heartRate.js",
		"bloodPressure" : "officialwebsite/js/bloodPressure.js",
		"sleep" : "officialwebsite/js/sleep.js",
		"stepCounter" : "officialwebsite/js/stepCounter.js",
		"temperature" : "officialwebsite/js/temperature.js",
		"healthDataUtil" : "com/lifeshs/member/js/healthDataUtil.js",
		
		"location" : "com/lifeshs/member/js/location.js",
		"remind" : "com/lifeshs/member/js/remind.js",
		"changeNumber" : "com/lifeshs/member/js/changeNumber.js",
		"blackList" : "com/lifeshs/member/js/blackList.js",
		"monitor" : "com/lifeshs/member/js/monitor.js",
		"jqueryRange" : "plugins/Jrange/js/jquery.range-min.js",
		"jqueryBase64" : "plugins/tableExport/jquery.base64.js",
		"tableExport" : "plugins/tableExport/tableExport.js",
		"jeDate" : "plugins/jeDate/jedate.min",
		"timePicker" : "plugins/XzsTimePicker/TimerPicker.js"
	}
});

seajs.use(['com/lifeshs/member/js/HLseries.js'], function() {
	window.onload =function () {
		menuSetting({
			parent : 1,
			child : 1
		});
	}
	function selectDate(val) {
		WdatePicker({
			mixDate : '#F{$dp.$D(\'rqq\')}',
			isShowClear : true,
			readOnly : true,
			dateFmt : 'HH:mm',
			onpicked : function(dp) {
				onpicked(dp, val)
			},
			oncleared : function() {
				clearedFunc(val)
			}
		});
	}
	function onpicked(dp, val) {
		//	alert('你选择的日期是:'+dp.cal.getDateStr()+",val="+val);
		if (val == 1) {
			must("start_one", true);
			must("end_one", true);
		} else if (val == 2) {
			must("start_two", true);
			must("end_two", true);
		} else if (val == 3) {
			must("start_three", true);
			must("end_three", true);
		}
	}
	//当日期被清空
	function clearedFunc(val) {
		if (val == 1) {
			var start_one = $("#start_one").val();
			var end_one = $("#end_one").val();
			if (start_one == "" && end_one == "") {
				must("start_one", false);
				must("end_one", false);
			} else {
				must("start_one", true);
				must("end_one", true);
			}
		} else if (val == 2) {
			var start_two = $("#start_two").val();
			var end_two = $("#end_two").val();
			if (start_two == "" && end_two == "") {
				must("start_two", false);
				must("end_two", false);
			} else {
				must("start_two", true);
				must("end_two", true);
			}
		} else if (val == 3) {
			var start_three = $("#start_three").val();
			var end_three = $("#end_three").val();
			if (start_three == "" && end_three == "") {
				must("start_one", false);
				must("end_three", false);
			} else {
				must("start_one", true);
				must("end_three", true);
			}
		}
	}
	function must(elementId) {
		$('#' + elementId).val();
	}
});
</script>
</html>
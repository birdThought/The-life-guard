<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>饮食记录</title>
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css"
	href="/static/plugins/jeDate/css/jedate.css">
<link rel="stylesheet" type="text/css" href="/static/css/healthFiles.css">
<script src="/static/com/lifeshs/member/record/recordDietList.js"></script>
<script type="text/javascript" src="/static/plugins/jeDate/jedate.min.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<!-- <script type="text/javascript" src="/static/officialwebsite/js/highcharts.js"></script> -->
</head>
<body onload="wol();">
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">
				<a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a
					href="#" class="top_cite">健康档案</a>&nbsp;&nbsp;>&nbsp;&nbsp; <a
					href="recordDietControl.do?RecordDietEnter">健康日记</a>&nbsp;&nbsp;>&nbsp;&nbsp;
				<a href="recordDietControl.do?RecordDietFoodEnter">饮食记录</a>
			</div>
			<div class="container fr">
				<ul class="recordTitle">
					<li>饮食记录</li>

					<li><input class="date" name="date" id="date"
						style="width:130px;cursor:pointer;background:url('/static/images/green_date_img.png') no-repeat right center;background-size:27px 18px;"
						readonly></li>


				</ul>
				<!-- <div class="backup" style="text-align:right">
					<span style="display:none"><a href="recordDietControl.do?RecordDietEnter">返回</a></span>
					<div style="float:right;margin-right:60px;letter-spacing: 50px;display:none;">
						<a href="#"><img src="/static/images/previous.png" height="18" width="8"/></a>
						<a href="#"><img src="/static/images/previous.png" style="transform:rotate(-180deg)" height="18" width="8"/></a>
					</div>
					
					
				</div> -->
				<div style="margin-top:20px;">
					<div style="float:right;margin-right:60px;letter-spacing: 70px;">
						<a href="javascript:void(0);" name="prePage"><img
							src="/static/images/previous.png" height="18" width="8" /></a> <a
							href="javascript:void(0);" name="nextPage"><img
							src="/static/images/previous.png"
							style="transform:rotate(-180deg)" height="18" width="8" /></a>
					</div>
					<!--时间段的一大块列表-->
					<ul class="item_container" style="padding-left:5px;clear: both;">
						<li>
							<div class="item_time">
								<span class="recordTime"></span>
							</div> <!--早餐、中餐、晚餐的列表-->
							<ul class="info_list" style="list-style-type: none">
								<!-- 在js文件动加载数据 -->
							</ul>
						</li>

						<li>
							<div class="item_time">
								<span class="recordTime"></span>
							</div> <!--  早餐、中餐、晚餐的列表 -->
							<ul class="info_list">

							</ul>
						</li>
					</ul>
				</div>
			</div>
				</div>
		</div>
	</div>
</body>
<!-- <li>
<div class = "outside">
<div class = "left_contain">
<div class = "left_wrap">
<div>运动</div>
<div class = "time">09:55</div>
</div>
</div>
<div class = "right_contain">
<div class = "out_circle">
<div></div>
</div>
<div>
<span class = "energy">能量</span>
<span class = "energy_value">1500kcal</span>
</div>
<div class = "health_msg">慢跑</div>
</div>
</div>
</li> -->
<script type="text/javascript">
	$(function(){
		menuSetting({
			parent : 2,
			child : 2
		});
	});
	function wol() {
		var mydateInput = document.getElementById("date");
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
		mydateInput.value = dateString;
		
	}
	jeDate({
		dateCell : "#date",
		format:"YYYY-MM-DD",
	    isinitVal:true,
	    initAddVal:[0],
	    minDate:"1900-01-01",
	    maxDate: jeDate.now(0),
	    startMin:"1900-01-01",
	    startMax:jeDate.now(0),
	    zindex: 999,
	    choosefun:function(elem, val) {
	    	//val为获取到的时间值
	    	/* 选择时间后更新饮食记录 */
	    	selectDeviceTime();
	    }
	});
</script>
</html>
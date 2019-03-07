<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>服务管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer,vue"></t:base>
<link type="image/x-ic
on" rel="shortcut icon" href="favicon.ico
"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/QYcomCss.css">
<link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
<link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/serveManage/healthLesson.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/js/photoUpload.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/plugins/XzsTimePicker/TimerPicker.js"></script>
<link rel="stylesheet" href="/static/css/healthLesson.css">
<script type="text/javascript">
	window.onload = function() {
		var child=1;
		<shiro:hasPermission name="user:1">
			child=0;
		</shiro:hasPermission>
		menuSetting({
			parent: 0,
			child: child
		});
	}
	$(function(){
		//日期过滤器
		Vue.filter('date', function (value, format) {
			 return new Date(value).Format(format);
		});
		//星期转换器
		Vue.filter('switchWeeks', function (value) {
			var week = '';
			var arr = value.split('');
			for (var i = 0; i < arr.length; i ++) {
				if (arr[i] == 1) {
					switch (i) {
						case 0:
							week = week + '星期一';
							break;
						case 1:
							week = week + '星期二';
							break;
						case 2:
							week = week + '星期三';
							break;
						case 3:
							week = week + '星期四';
							break;
						case 4:
							week = week + '星期五';
							break;
						case 5:
							week = week + '星期六';
							break;
						case 6:
							week = week + '星期日';
							break;
					}
				}
			}
			return week;
		});
		
		ModelList.getLessonModel().results = ${lessons};
		ModelList.getLessonModel().userId = ${userId};
		// 上传头像
		$(".addMyself").on('change', '#path', function(){
			var $this = $(this);
			photoUpload.showPreview($this[0].files[0],"commonControl/uploadFile/img.do", $("#upload_img"),"#upload_img","[name='img_hidden']");
		});
		
	});
</script>
</head>

<body>
	<%@include file="/context/QYHeader.jsp"%>
	<div class="container">
		<%@include file="/context/QYMenu.jsp"%>
		<div class="right_content" id = "lessonModel">
			<div class="right_body ">
				<div class="service-team">
                    <div class="teams-title">
                        <h3>选择健康课堂
                        	<button class = "add-btn" @click = "addLesson()">创建课堂</button>
                        </h3>
                    </div>
                        <div v-if = "results == ''" style="text-align: center;padding: 15px">
                         	还未开设健康课堂
                        </div>
                        <ul class="teams" v-for = "(l, index) in results">
                            <li> 
                            	<img v-if = "l.photo != null" :src = "l.photo" height="74"/>
                            	<img v-else = "l.photo = null" src = "/static/images/photo.png" height="74"/>
                            </li>
                            <li style="width: 70%">
                                <p><span v-cloak>{{l.name}}</span></p>
                                <dl>
                                    <dd v-cloak>简介：{{l.description}}</dd>
                                </dl>
                                <p></p>
                                <span v-for = "(t, index) in l.courseTime" v-cloak>
                               		{{t.weeks | switchWeeks}} {{t.startTime}}
                               		<template v-cloak v-if = "index < l.courseTime.length - 1">、</template>
                                </span>
                            </li>
                            <li>
                                <button @click = "interLesson(l.id, index)" class="" :style = "getBtnCss(index)">进入课堂</button>
                                <button @click = "deleteLesson(l.id)" class="" style = "margin-left: 95px; background-color: #d1372b;">删除课堂</button>
                            </li>
                        </ul>
                </div>
			</div>
			<!-- 添加课堂窗口 -->
			<div class = "add_lesson_dialog_container" style = "display: none; padding: 30px;">
				
					<label for="">开课日期</label> 
				
					<div class="checkbox_line" >
						<input type="checkbox" id = "1" value="1" >星期一&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id = "2" value="2" >星期二&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id = "3" value="3" >星期三
					</div>
					<div class="checkbox_line" style="margin-top:8px; margin-left: 80px;">
						<input type="checkbox" id = "4" value="4" >星期四&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id = "5" value="5" >星期五&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id = "6" value="6" >星期六
					</div>
					<div class="checkbox_line"  style="margin-top:8px; margin-left: 80px;">
						<input type="checkbox" id = "7" value="7" >星期日
					</div>
					<p style = "width: 240px; height: 45px;">
					<label for="">开课时间</label>  
					<input type="text" value="" class = "lessonDate" id="lessonDate" name = "lessonDate" placeholder="00:00" onclick="TimePicker.showTimePicker(this)" style = "width: 170px;" readonly> 
					<input @click = "addLessonTime()" type = "button" value = "确定" style = "width: 60px; margin-left: 240px; transform: translateY(-160%);">
					</p>
				<p></p>
					<label>课堂时间：</label>
					<div style = "word-break:break-all; width:230px; margin-top: -2px; margin-left: 75px; border: 0px;">
						{{checkedTime}}
						<%-- <template v-if = "checkedTime != null" v-for = "(h, index) in checkedTime">
						{{checkedTime}}
							<template v-if = "index < checkedTime.length - 1">、</template>
						</template> --%>
					</div>
					
					<!-- <input type = "button" value = "添加" style = "width: 60px;"> -->
				<p></p>
				<p></p>
					<label>课堂名称</label> 
					<input v-model = "checkedLessonName" class = "lessonName" style = "width: 170px;" type="text">
					
				<div class = "addMyself" style = "float: right;margin-top: -125px; margin-right: 30px;">
					<div class="imgId">
	                        <img id="upload_img" width="110" height="110" src="/static/images/photo.png">
	                        <input type="hidden" name="img_hidden"/>
	                </div>
	                <div class="changeUrl">
	                        <!-- <input type="text" size="20" name="upfile" id="upfile">   -->
	                        <input type="button" value="浏览" onclick="path.click()" style="margin-left:10px; width:70px; outline:none;font-family:'Microsoft Yahei';cursor:pointer"/>
	                        <input type="file" name="path" id="path" style="display:none"/>
	                </div>
                </div>
				<div class="description">
					<textarea v-model = "checkedLessonDescrepion" placeholder="课堂描述(限250字数)" id="description"></textarea>
				</div>
				
				<!-- <div class="upload">
					<a href="javascript:void(0)" class="file a-upload" style = "background-color: #218bde;"> 上传群头像 <input
						type="file" class="file" name="pictures" id="pictures"
						multiple="multiple" />
					</a>
					<a href="javascript:void(0)" class="file">清空</a>
				</div> -->
			</div>
		</div>
	</div>
</body>
</html>

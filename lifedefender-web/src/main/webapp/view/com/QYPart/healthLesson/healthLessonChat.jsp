<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>服务管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer,vue,layui"></t:base>
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
<link rel="stylesheet" href="/static/plugins/layui/css/layui.css"  media="all">
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script type="text/javascript" src="/static/plugins/hx/strophe.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/websdk-1.1.2.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/websdk.shim.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/webim.config.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/serveManage/healthLessonChat.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/js/photoUpload.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/qrcode.min.js"></script>
<link rel="stylesheet" href="/static/css/healthLesson.css">
<script type="text/javascript" src="/static/plugins/XzsTimePicker/TimerPicker.js"></script>

<style type="text/css">
	body,ul,li{margin: 0;padding: 0;font: 12px normal "宋体", Arial, Helvetica, sans-serif;list-style: none;}
	a{text-decoration: none;color: #000;font-size: 14px;}
	
	#tabbox{ width:600px; overflow:hidden; margin:0 auto;}
	.tab_conbox{border: 1px solid #999;border-top: none;}
	.tab_con{ display:none;}
	
	.tabs{height: 32px;border-bottom:1px solid #999;border-left: 1px solid #999;width: 100%;}
	.tabs li{height:31px;line-height:31px;float:left;border:1px solid #999;border-left:none;margin-bottom: -1px;background: #e0e0e0;overflow: hidden;position: relative;}
	.tabs li a {display: block;padding: 0 20px;border: 1px solid #fff;outline: none;}
	.tabs li a:hover {background: #ccc;}	
	.tabs .thistab,.tabs .thistab a:hover{background: #fff;border-bottom: 1px solid #fff;}
	
	.tab_con {padding:12px;font-size: 14px; line-height:175%;}
</style>

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
		
		if(window.localStorage)
		{
		var localStrage= 1024 * 1024 * 5 - unescape(encodeURIComponent(JSON.stringify(localStorage))).length;
		console.log('localStrage:' + localStrage)
		}
		
		//初始化vue
		if ('${lesson}' == '[]') {
		    vueModel.getLesson().results = '';
		} else {
		    var myJSONString = JSON.stringify('${lesson}');
		    var myEscapedJSONString = myJSONString.replace(/\\n/g, "\\n")  
            .replace(/\\'/g, "\\'")  
            .replace(/\\"/g, "\\\"")  
            .replace(/\\&/g, "\\&")  
            .replace(/\\r/g, "\\r")  
            .replace(/\\t/g, "\\t")  
            .replace(/\\b/g, "\\b")  
            .replace(/\\f/g, "\\f");
		    vueModel.getLesson().results = JSON.parse(myEscapedJSONString);
		}
		
		//vueModel.getLesson().results = '${lesson}' == '[]' ? '' : JSON.parse('${lesson}');
		vueModel.getLesson().onlineUser = '${hxCode}';
		vueModel.getLesson().checkedGroupId = '${targetServer}';
		//初始化聊天界面
		/* HuanxinControl.account=vueModel.getLesson().results.huanxinId; */
        ContactControl.chatWithMultiServer(vueModel.getLesson().results, '${targetServer}', '${remain}', '${hxCode}', '${userId}');
        
		HuanxinControl.init({
        	account: '${hxCode}',
            password: WebIM.config.password,
            headUrl: '${headUrl}',
            nick: '${nick}',
            orgServeId: '${orgServeId}'
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
		
		vueModel.getLesson().initialPopupInfo();
		
		// 上传头像
		$(".addMyself").on('change', '#path', function(){
			var $this = $(this);
			photoUpload.showPreview($this[0].files[0],"commonControl/uploadFile/img.do", $("#upload_img"),"#upload_img","[name='img_hidden']");
		});
		//发送表情
		$('.webim-emoji-item').click(function () {
			console.log('one')
    		$(".chat-text-inp").val($(".chat-text-inp").val() + $(this).attr('data-key'));
    	});
		
		layui.use('element', function(){
		  var element = layui.element();
		  //一些事件监听
		  element.on('tab(demo)', function(data){
		    console.log(data);
		  });
		});
		
	});
</script>
</head>

<body>
	<%@include file="/context/QYHeader.jsp"%>
	<div class="view-body wrap">
		<%@include file="/context/QYMenu.jsp"%>
			<div class = "right_content lessonModel" style = "background-color: #ddd;">
                <div style="box-sizing:content-box;position:relative;border: 1px solid #ddd;width:950px;">
                    <div class="message-left">
                        <div class="search-container">
                            <div class="search-bg">
                                <input type="text" placeholder="搜索" oninput="ContactControl.searchContact(this)"/>
                            </div>
                        </div>
                        <div class="message-contact-list">
                            <div class="search-contain-list">

                            </div>
                            <div class="contact-other-contain">
                                <button onclick="ContactControl.returnToBase()" style="display:none">&#10094;</button>
                                <span>服务信息</span>
                            </div>
                            <div id="baseMessage" style="display:none">
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-item="yj">
                                    <img src="/static/images/yujing.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">预警信息</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg">嘿咻你啊啊咻你好...</span><span class="contact-item-unread">12</span></p>
                                    </div>
                                </div>
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-item="tz">
                                    <img src="/static/images/voice.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">通知信息</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg">嘿咻你啊啊咻你好...</span><span class="contact-item-unread">12</span></p>
                                    </div>
                                </div>
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-item="fws">
                                    <img src="/static/images/servInfor.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">服务信息</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg"></span><span class="contact-item-unread">12</span></p>
                                    </div>
                                </div>
                            </div>

                            <div id="fwsList">
                                <div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="123456789011">
                                    <%--<img src="/static/images/yujing.png" class="contact-head"/>
                                    <div class="contact-item-right">
                                        <span class="contact-item-nick">测试信息</span><span class="contact-item-status">在线</span><span class="contact-item-time">2016-10-15</span>
                                        <p><span class="contact-item-recent-msg">嘿咻你啊啊咻你好...</span><span class="contact-item-unread">12</span></p>
                                    </div>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="message-right">
                        <div class="message-title" data-id="123456789011" :data-group-id = "checkedGroupId">
                            <span></span>
                            <button class = "setting" @click = "updateLesson()">课堂设置</button>
                        </div>
                        <div class="base-message-contain" style="display: none;">
                            <div class="base-message-item">
                                <div class="base-message-item-head">
                                    <img src="/static/images/yujing.png"/>
                                </div>
                                <div class="base-message-item-notify">
                                    <div><span class="base-message-item-notify-name">预警信息</span><span class="base-message-item-notify-time">2016-10-10</span></div>
                                    <div class="base-message-item-notify-msg">
                                        预警信息预警信息预警信息预警信息
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="chat-message">
                            <div class="chat-msg-contain">
                            </div>

                            <div class="chat-right-bottom">
                                <textarea class="chat-text-inp" rows="7"></textarea>
                                <div class = "silentTip">全员禁言中,仅群主可发言!</div>
                                <div class="send-btn-contain">
                                	<input id="chat_photo_inp" type="file" onchange="HuanxinControl.sendPicture()" hidden/>
                               		<button class = "openPicture" onclick="chat_photo_inp.click()" style = "margin-right: 15px;"><img src="/static/images/org/chat_pic.png" width = "30px;"/></button>
                                 	<img class = "openFace" @click = "popupFace()" src = "/static/images/face.png" style = "cursor:pointer; margin-right: 15px;">
                                  <input id="chat_file_inp" type="file" onchange="HuanxinControl.sendFile()" hidden/>
                                    <button class = "openFile" onclick="chat_file_inp.click()"><img src="/static/images/select_img.png"/></button>
                                    <button class="send-btn" onclick=" HuanxinControl.sendMsg()">发送</button>
                                </div>
                            </div>
							<ul class="face">
								<li data-key="[):]" class="webim-emoji-item">
									<img src="/static/images/webImFace/ee_1.png">
								</li>
								<li data-key="[:D]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_2.png"></li>
								<li data-key="[;)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_3.png"></li>
								<li data-key="[:-o]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_4.png"></li>
								<li data-key="[:p]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_5.png"></li>
								<li data-key="[(H)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_6.png"></li>
								<li data-key="[:@]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_7.png"></li>
								<li data-key="[:s]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_8.png"></li>
								<li data-key="[:$]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_9.png"></li>
								<li data-key="[:(]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_10.png"></li>
								<li data-key="[:'(]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_11.png"></li>
								<li data-key="[:|]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_12.png"></li>
								<li data-key="[(a)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_13.png"></li>
								<li data-key="[8o|]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_14.png"></li>
								<li data-key="[8-|]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_15.png"></li>
								<li data-key="[+o(]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_16.png"></li>
								<li data-key="[<o)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_17.png"></li>
								<li data-key="[|-)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_18.png"></li>
								<li data-key="[*-)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_19.png"></li>
								<li data-key="[:-#]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_20.png"></li>
								<li data-key="[:-*]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_21.png"></li>
								<li data-key="[^o)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_22.png"></li>
								<li data-key="[8-)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_23.png"></li>
								<li data-key="[(|)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_24.png"></li>
								<li data-key="[(u)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_25.png"></li>
								<li data-key="[(S)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_26.png"></li>
								<li data-key="[(*)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_27.png"></li>
								<li data-key="[(#)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_28.png"></li>
								<li data-key="[(R)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_29.png"></li>
								<li data-key="[({)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_30.png"></li>
								<li data-key="[(})]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_31.png"></li>
								<li data-key="[(k)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_32.png"></li>
								<li data-key="[(F)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_33.png"></li>
								<li data-key="[(W)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_34.png"></li>
								<li data-key="[(D)]" class="webim-emoji-item"><img
									src="/static/images/webImFace/ee_35.png"></li>
							</ul>
						</div>
                    </div>
                </div>
                <!-- 设置窗口 -->
                <div class = "setting_lesson_dialog_container" style = "display: none; padding: 0px 30px;">
					<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					  <ul class="layui-tab-title">
					    <li class="layui-this" style = "font-size: 16px;">设&nbsp;&nbsp;置</li>
					    <li style = "font-size: 16px;">群成员</li>
					  </ul>	
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show">
					    	<div>
								<label>群头像</label>
								<div class = "addMyself" style = "margin-top: -20px; margin-right: 30px; margin-left: 71px;">
									<div class="imgId">
					                        <img v-if = "results.photo == null" id="upload_img" width="110" height="110" src="/static/images/photo.png">
					                        <img v-if = "results.photo != null" id="upload_img" width="110" height="110" :src = "results.photo">
					                        <input type="hidden" name="img_hidden"/>
					                </div>
					                <div class="changeUrl">
					                        <input type="button" value="更换" onclick="path.click()" style="margin-left: 126px;
						                        width:70px; outline:none;font-family:'Microsoft Yahei';cursor:pointer;
						                        transform: translateY(-104%); border: 1px solid #48c858; border-radius: 5px; padding: 4px;
						                        background-color: #48c858; color: white;"/>
					                        <input type="file" name="path" id="path" style="display:none"/>
					                </div>
				                </div>
								<label>课堂名称</label>
								<input class = "name" :value = "results.name" type = "text" style = "width: 250px; margin-left: 10px;height: 30px; border: 1px solid #ccc; border-radius: 5px;"><br><br>
								<label>课堂二维码:<img @click = "openGroupQrCode(results.id, results.name)" src = "/static/images/qrcode.png" style = "width: 30px; margin-left: 10px;"></label><br><br>
								<label>课堂简介:
								</label>
								<textArea class = "description" style = "margin-left: 10px; width: 443px; height: 120px; padding: 8px; border-radius: 5px;" >{{results.description}}</textArea>
								<br><br>
								<label>开课时间:</label>
								<label v-if = "checkedTime == null" style = "margin-left: 10px;">
									<template v-for = "(c, index) in results.courseTime">
										{{c.weeks | switchWeeks}} {{c.startTime}}
										<span v-if = "index < results.courseTime.length - 1">、</span>
									</template>
								</label>
								<button class = "editLessonTime" @click = "editLessonTimeBtn()">修改</button>
								<div v-if = "checkedTime != null" style = "word-break: break-all; width: 350px; margin-top: -2px; margin-left: 75px; border: 0px;">{{checkedTime}}</div>
								
								<div class = "editLessonDiv" style = "display: none"><br>
								<label for="">开课日期</label> 
								<div class="checkbox_line" >
									<input type="checkbox" id = "1" value="1" >星期一&nbsp;&nbsp;&nbsp;
									<input type="checkbox" id = "2" value="2" >星期二&nbsp;&nbsp;&nbsp;
									<input type="checkbox" id = "3" value="3" >星期三
								</div>
								<div class="checkbox_line" style="margin-top:8px; margin-left: 78px;">
									<input type="checkbox" id = "4" value="4" >星期四&nbsp;&nbsp;&nbsp;
									<input type="checkbox" id = "5" value="5" >星期五&nbsp;&nbsp;&nbsp;
									<input type="checkbox" id = "6" value="6" >星期六
								</div>
								<div class="checkbox_line"  style="margin-top:8px; margin-left: 78px;">
									<input type="checkbox" id = "7" value="7" >星期日
								</div>
								<p style = "width: 240px; height: 45px;">
								<label for="">开课时间</label>  
								<input type="text" value="" class = "lessonDate" id="lessonDate" name = "lessonDate" placeholder="00:00" onclick="TimePicker.showTimePicker(this)" style = "width: 170px;" readonly> 
								<input @click = "addLessonTime()" type = "button" value = "确定" style = "width: 60px; margin-left: 255px; transform: translateY(-103%); border-radius: 5px; height: 29px; border: 1px solid #ccc;">
								</div>
								<br><br>
								<label>禁言</label>
								<div class="testswitch">
						            <input v-if = "results.silence == true" @click = "isSilence()" class="testswitch-checkbox" id="onoffswitch" type="checkbox" checked = true>  
						            <input v-else = "results.silence == false" @click = "isSilence()" class="testswitch-checkbox" id="onoffswitch" type="checkbox">
						            <label class="testswitch-label" for="onoffswitch">  
						                <span class="testswitch-inner" data-on="ON" data-off="OFF"></span>  
						                <span class="testswitch-switch"></span>  
						            </label>  
						        </div><br>
						        <label>其他:
						        	<button @click = "clearChatRecord()" style = "margin-left: 10px; border-radius: 3px; background-color: rgb(238, 238, 238); padding: 6px 5px; border: none; font-size: 14px;">清空聊天记录</button>
						        </label>
							</div>
					    </div>
					    <div class="layui-tab-item">
					    <div>
							<div>
								<label>群主: 
									<template v-for = "o in results.orgUsers">
										<span v-if = "o.id == results.creatorId">{{o.realName}}</span>
									</template>
								</label>
								<br><br>
								<label>服务师: 
									<template v-for = "(o, index) in results.orgUsers">
										<span>{{o.realName}}</span>
										<span v-if = "index < results.orgUsers.length -1">、</span>
									</template>
								</label>
								<button class = "addOrgUser" @click = "addOrgUser()">添加服务师</button>
							</div>
							<br>
							<div class = "memberList" style = "overflow-y: auto; overflow-x: hidden;">
								<table class = "memberTable" cellspacing = "0" >
									<thead>
										<tr>
											<td>姓名</td>
											<td>剩余</td>
											<td>到期时间</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<tr v-for = "(u, index) in results.userOrders">
											<td>{{u.userRealName}}</td>
											<td>0天</td>
											<td>2015-12-12</td>
											<td>
												<a onclick = "dialogUtils.showDialog(4,1424,0)">
													<img style = "border: none; vertical-align: middle;" src = "/static/images/person_info.png">
												</a>
												<a href = "">
													<img style = "border: none; vertical-align: middle;" src = "/static/images/health_data.png">
												</a>
												<a href = "">
													<img style = "border: none; vertical-align: middle;" src = "/static/images/conversation.png">
												</a>
											</td>
										</tr>
										<tr v-if = "results.userOrders == ''">
											<td colspan="4">暂无成员</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					    </div>
					  </div>
					</div>
				</div>
				<!-- 选择服务师窗口 -->
				<div class = "add_orgUser_dialog_container" style = "display: none;">
					<table class = "memberTable" cellspacing = "0" >
						<thead>
							<tr>
								<td>选择</td>
								<td>服务师</td>
							</tr>
						</thead>
						<tbody>
							<tr v-for = "(u, index) in orgUsers" v-if = "u.id != results.creatorId">
								<td><input type = "checkbox" :value = "index" :data-id = "u.id" :checked = "isGroupUser(u.id)"></td>
								<td>{{u.realName}}</td>
							</tr>
						</tbody>
					</table>
				</div>
            </div>
	</div>
</body>
</html>

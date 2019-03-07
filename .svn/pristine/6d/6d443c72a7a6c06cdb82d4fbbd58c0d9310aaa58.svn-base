/**
 * 课堂群聊
 * 
 */

/** Model */
var vueModel = {
		/** 课堂 */
		lesson: null,
		
		getLesson: function () {
			if (this.lesson == null) {
				this.lesson = new Vue({
					el: ".lessonModel",
					data: {
						results: '',  //课堂信息集合
						onlineUser: null,  //在线用户
						orgUsers: null, //机构服务师列表
						groupUsers: null,  //课堂服务师
						lessonTimeDTO: null,  //课堂时间
						checkedLessonweek: null,  //已选定星期
						checkedLessonTime: null,  //已选定具体时间
						checkedTime: null,  //已选定课堂时间
						checkedGroupId: null, //已选择的课堂Id
					},
					methods: {
						/** 获取课堂信息 */
						getLesson: function () {
							var data = {
									'groupId': ($('.message-title').attr('data-group-id'))
							}
							$.ajax({
								async : true,
								cache : false,
								type: 'POST',
								contentType: 'application/x-www-form-urlencoded',
								url: 'orgServeControl.do?getHealthLesson',
								data: data,
								before: function(){
									layer.load(1);
								},
								success: function(result){
									if (result.success == true) {
										vueModel.getLesson().results = result.obj;
									}
								}
							});
						},
						/** 课堂设置 */
						updateLesson: function () {
							this.getLesson();
//							this.tabControl();
							var beforeRelativePath = $("#upload_img").attr('src');
							layer.open({
								type: 1,
								title:['设置','text-align:center;font-size:16px;background:#fff;'],
								area:['720px', '720px'],
								btn:['确定','取消'],
								zIndex: 9999,
								scrolling:'no',
								scrollbar:false,
								success : function() {
								},
					  			content: $('.setting_lesson_dialog_container'),
					  			yes: function(){
					  				var id = vueModel.getLesson().results.id;
					  				var name = $('.setting_lesson_dialog_container .name').val();
					  				var description = $('.setting_lesson_dialog_container .description').val();
					  				description = description.replace(/(\r\n|\n|\r)/gm, '');
					  				var lessonTimeDTO = vueModel.getLesson().lessonTimeDTO;
					  				var silence = $("#onoffswitch").is(':checked');
					  				var relativePath = $("#upload_img").attr('src');
					  				if (relativePath == beforeRelativePath) {
					  					relativePath = '';
					  				}
					  				var data = {
					  						id: id,
					  						name: name,
					  						description: description,
					  						lessonTimeDTO: lessonTimeDTO,
					  						silence: silence,
					  						relativePath: relativePath
					  				}
									$.ajax({
										async : true,
										cache : false,
										type: 'POST',
										contentType: 'application/json; charset=utf-8',
										url: 'orgServeControl.do?updateHealthLesson',
										data: JSON.stringify(data),
										before: function(){
											layer.load(1);
										},
										success: function(result){
											vueModel.getLesson().checkedTime = null;
											vueModel.getLesson().getLesson();
											$('.editLessonDiv').css('display', 'none');
											layer.closeAll();
											layer.msg('设置成功！');
										}
									});
					  			}
							});
						},
						/** 选项卡 */
						tabControl: function () {
						},
						/** 清空聊天记录 */
						clearChatRecord: function () {
							layer.confirm('确定清楚聊天记录?', {icon: 2, title:'提示'}, function(index){
								var groupId = $('.message-title').attr('data-id');
								if (groupId != null) {
									var key = vueModel.getLesson().onlineUser + ':' + groupId;
									$.localRemove(key);
									$('.chat-msg-contain').empty();
									layer.msg('清除聊天记录成功');
									layer.close(index);
								}
							});
						},
						/** 监听禁言按钮 */
						isSilence: function () {
					        if ($("#onoffswitch").is(':checked')) { 
					        	var data = {
					        			"groupId": vueModel.getLesson().results.id
					        	}
					        	$.ajax({
									async : true,
									cache : false,
									type: 'POST',
									contentType: 'application/x-www-form-urlencoded',
									url: 'orgServeControl.do?bindSilented',
									data: data,
									before: function(){
										layer.load(1);
									},
									success: function(result){
										if (result.success) {
											layer.msg('开启禁言成功!');  
										} else {
											$("#onoffswitch").click();
										}
									}
								});
					        } else {  
					        	var data = {
					        			"groupId": vueModel.getLesson().results.id
					        	}
					        	$.ajax({
									async : true,
									cache : false,
									type: 'POST',
									contentType: 'application/x-www-form-urlencoded',
									url: 'orgServeControl.do?unbindSilented',
									data: data,
									before: function(){
										layer.load(1);
									},
									success: function(result){
										if (result.success) {
											layer.msg('关闭禁言成功！');  
										} else {
											$("#onoffswitch").click();
										}
									}
								});
					            
					        }  
						},
						/** 打开二维码 */
						openGroupQrCode: function (url, name) {
							var dialog = this.createGroupQrcode(url, name);
			                layer.open({
			                    type: 1,
			                    title: ['二维码',
			                        'text-align:center;font-size:16px;background:#fff;'],
			                    area: '350px',
			                    moveType: 1,
			                    scrollbar: false,
			                    zIndex: 9999,
			                    scrolling: 'no',
			                    content: $(dialog),
			                    end: function () {// 销毁该弹出层
			                        $(dialog).remove();
			                    }
			                });
						},
						/** 创建二维码 */
						 createGroupQrcode: function (url, data) {// 创建群组二维码对话框,url为扫描二维码后的链接方向
						        var dialogContainer = document.createElement("div");
						        $(dialogContainer).addClass("dialog_contain");
						        $(dialogContainer).attr("style", "text-align:center;line-height:35px;font-size:16px;width:350px");
						        var qrCodeDiv = document.createElement("div");
						      /*  $(qrCodeDiv).attr({
						            "style": "display:inline-block;margin-top:10px",
						            "id": "qrcode"
						        });*/
						        qrCodeDiv.setAttribute('style', 'display:inline-block;margin-top:10px');
						        qrCodeDiv.setAttribute('id', 'qrcode');
						        dialogContainer.appendChild(qrCodeDiv);
						        $(dialogContainer)
						            .html(
						                $(dialogContainer).html()
						                + '<p style="font-weight:bold;font-size:20px;margin-top:15px">'
						                + data + '</p><p>扫一扫加入该群组</p>');
						        document.body.appendChild(dialogContainer);
						        var qrcode = new QRCode(document.getElementById("qrcode"), {
						            width: 150,
						            height: 150
						        });
						        qrcode.makeCode('{"serveGroupId":"'+url+'"}');
						        return dialogContainer;
						    },
						    /** 弹出表情--点击表情添加到输入框 */
						    popupFace: function () {
						    	$('.face').css('display', 'block');
						    },
						    /** 查看个人信息 */
						    initialPopupInfo: function () {
						    	var time = null;
						        $(".conversation").hover(function () {
						            $(this).attr("src", "static/images/conversation_h.png");
						            var obj = $(this);
						            var tip = new TipControl();
						            time = setTimeout(function () {
						                tip.showTips("健康会话", obj);
						            }, 1000);
						        }, function () {
						            $(this).attr("src", "static/images/conversation.png");
						            clearTimeout(time);
						        });
						        $(".person_info").hover(function () {
						        	console.log('point one')
						            $(this).attr("src", "static/images/person_info_h.png");
						            var tip = new TipControl();
						            var obj = $(this);
						            var tip = new TipControl();
						            time = setTimeout(function () {
						                tip.showTips("个人信息", obj);
						            }, 1000);
						        }, function () {
						            $(this).attr("src", "static/images/person_info.png");
						            clearTimeout(time);
						        });
						        $(".health_data").hover(function () {
						            $(this).attr("src", "static/images/health_data_h.png");
						            var tip = new TipControl();
						            var obj = $(this);
						            var tip = new TipControl();
						            time = setTimeout(function () {
						                tip.showTips("健康数据", obj);
						            }, 1000);
						        }, function () {
						            $(this).attr("src", "static/images/health_data.png");
						            clearTimeout(time);
						        });
						        $(".relation").hover(function () {
						            $(this).attr("src", "static/images/relation_h.png");
						            var tip = new TipControl();
						            var obj = $(this);
						            var tip = new TipControl();
						            time = setTimeout(function () {
						                tip.showTips("移动群组", obj);
						            }, 1000);
						        }, function () {
						            $(this).attr("src", "static/images/relation.png");
						            clearTimeout(time);
						        });
						    },
						    /** 修改开课时间按钮*/
						    editLessonTimeBtn: function () {
						    	if ($('.editLessonDiv').css('display') == 'none') {
						    		$('.editLessonDiv').slideDown();
						    		return;
						    	}
						    	$('.editLessonDiv').slideUp();
						    },
						    /** 修改开课时间 */
						    addLessonTime: function () {
								
								var weeks = '';
								for(var i=1; i<= 7; i++){
									if($(".editLessonDiv input[type='checkbox'][value='" + i + "']").is(":checked")){
										weeks += '1';
									}else{
										weeks += '0';
									}
								}
								this.checkedLessonweek = weeks;
								this.checkedLessonTime = $('.lessonDate').val();
								if (weeks == '0000000' || $('.lessonDate').val() == '') {
									layer.msg('请选择开课时间');
									return;
								} 
								if (this.checkedTime == null){
									this.checkedTime = new Array();
								}
								this.checkedTime.push(this.getWeeks(weeks) + $('.lessonDate').val());
								if (this.lessonTimeDTO == null) {
									this.lessonTimeDTO = new Array();
								}
								
								if (this.lessonTimeDTO == null) {
									this.lessonTimeDTO = new Array();
								}
								var lessonTime = {
										weeks: this.checkedLessonweek,
										startTime: this.checkedLessonTime
								};
								this.lessonTimeDTO.push(lessonTime);
							},
							/** 转换星期 */
							getWeeks: function (value) {
								var week = '';
								if (value != null) {
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
								}
							},
							/** 添加服务师 */
							addOrgUser: function () {
								this.getOrgUser();
								var vue = vueModel.getLesson();
								layer.open({
									type: 1,
									title:['添加服务师','text-align:center;font-size:16px;background:#fff;'],
									area:'720px',
									btn:['确定','取消'],
									zIndex: 9999,
									scrolling:'no',
									scrollbar:false,
									success : function() {
									},
						  			content: $('.add_orgUser_dialog_container'),
						  			yes: function(index){
						  				var checkedArr = new Array();
						  				for (var i = 0; i <= vue.orgUsers.length; i ++) {
						  					var obj = $(".add_orgUser_dialog_container input[type='checkbox'][value='" + i + "']");
											if(obj.is(":checked")){
												checkedArr.push(obj.attr('data-id'));
											}
										}
						  				//判断服务师是增加、减少、不变
						  				var bool = true;
						  				var addArr = new Array();
						  				var delArr = new Array();
						  				for (var i = 0; i < checkedArr.length; i ++) {
						  					for (var j = 0; j < vue.groupUsers.length; j ++) {
						  						if (checkedArr[i] == vue.groupUsers[j].id) {
						  							bool = false;
						  						}
						  					}
						  					if (bool) {
						  						addArr.push(checkedArr[i]);
						  					}
						  					bool = true;
						  				}
						  				bool = true;
						  				for (var i = 0; i < vue.groupUsers.length; i ++) {
						  					if (vue.groupUsers[i].id != vue.results.creatorId) {	//群主不可移除
						  						for (var j = 0; j < checkedArr.length; j ++) {
							  						if (vue.groupUsers[i].id == checkedArr[j]) {
							  							bool = false;
							  						}
							  					}
						  					} else {
						  						bool = false;
						  					}
						  					
						  					if (bool) {
						  						delArr.push(vue.groupUsers[i].id);
						  					}
						  					bool = true;
						  					
						  				}
						  				var groupId = $('.message-title').attr('data-group-id');
						  				if (addArr.length > 0 || delArr.length > 0) {
						  					var data = {
						  							groupId: groupId,
						  							addOrgUserIds: addArr,
						  							delOrgUserIds: delArr
						  					}
						  					$.ajax({
												async : true,
												cache : false,
												type: 'POST',
												contentType: 'application/x-www-form-urlencoded',
												url: 'orgServeControl.do?updateOrgUser',
												data: (data),
												before: function(){
													layer.load(1);
												},
												success: function(result){
														vue.getLesson();
														layer.msg(result.msg);
												        layer.close(index);
												}
											});
						  				} else {
						  					layer.close(index);
						  				}
						  			}
								});
							},
							/** 获取机构全部服务师 */
							getOrgUser: function () {
								var groupId = $('.message-title').attr('data-group-id');
								var data = {
										groupId: groupId,
										noServerCache: true
								}
								$.ajax({
									async : true,
									cache : false,
									type: 'POST',
									contentType: 'application/json; charset=utf-8',
									url: 'orgServeControl.do?getEditGroupCondition',
									data: JSON.stringify(data),
									before: function(){
										layer.load(1);
									},
									success: function(result){
										if (result.success) {
											vueModel.getLesson().orgUsers = result.attributes.emList;
											vueModel.getLesson().groupUsers = result.attributes.servers;
										}
									}
								});
							},
							/** 判断是否为课堂服务师 */
							isGroupUser: function (id) {
								var bool = false;
								for (var i = 0; i < vueModel.getLesson().groupUsers.length; i ++) {
									if (vueModel.getLesson().groupUsers[i].id == id) {
										bool = true;
										break;
									}
									bool = false;
								}
								return bool;
					    	}
					},
					destroyed: function () {
				        console.log('lesson 销毁');
				    },
				    computed: {
				    	
				    }
				});
			}
			return this.lesson;
		}
}

var ContactControl = {
	    changeContact: function (obj) {
	        var target = $(obj).attr("data-item");
	        if (target != undefined) {
	            switch (target) {
	                case 'yj'://预警通知
	                    ChatControl.isShowing = false;
	                    $(".base-message-contain").show();
	                    $("#chat-message").hide();
	                    $(".message-title").html('<span>预警信息</span>');
	                    $(".message-title").removeAttr("data-id");
	                    $(obj).find($(".contact-item-unread")).remove();
	                    break;
	                case 'tz'://系统通知
	                    ChatControl.isShowing = false;
	                    $(".base-message-contain").show();
	                    $("#chat-message").hide();
	                    $(".message-title").html('<span>系统通知</span>');
	                    $(".message-title").removeAttr("data-id");
	                    $(obj).find($(".contact-item-unread")).remove();
	                    break;
	                case 'fws'://服务师
	                    $("#baseMessage").hide();
	                    $('.contact-other-contain').show();
	                    $("#fwsList").show();
	                    break;
	            }
	        } else {//服务师item
	            var id = $(obj).attr("data-id");
	            var nick = $(obj).find($(".contact-item-nick")).attr("data-nick");
	            var isGroup = $(obj).attr("is-group");
	            var groupId = $(obj).attr('data-group-id');
	            if ($(obj).find($(".contact-item-unread")).length != 0) {
	                this.updateUnread(false, obj, id);
	            }
	            if (!ChatControl.isShowing) {
	                $(".base-message-contain").hide();
	                $("#chat-message").show();
	                ChatControl.changeContact(id, nick, isGroup, groupId);
	                ChatControl.isShowing = true;
	            } else if (!$(obj).hasClass('contact-item-action')) {
	                ChatControl.changeContact(id, nick, isGroup, groupId);
	            }
	        }
	        $(obj).siblings("div").removeClass("contact-item-action");
	        $(obj).addClass("contact-item-action");
	    }, returnToBase: function () {
	        $('.contact-other-contain').hide();
	        $("#baseMessage").show();
	        $("#fwsList").hide();
	    }, updateUnread: function (isIncrease, data, id) {
	        if (isIncrease) {//增加未读消息数目
	            var now = $('.message-title').attr('data-id');
	            var from = data['to'];//对方
	            this.updateRecentMsg(data);
	            if (now == from){//如果是当前聊天
	            	return;
	            } 
	            var target = $('#fwsList .contact-item[data-id="' + from + '"]');
	            if (target.length != 0) {//显示未读消息数目
	                var unread = target.find($('.contact-item-unread'));
	                if (unread.length != 0) {
	                    var count = parseInt(unread.text());
	                    count++;
	                }
	            }
	            var fwsTotal = $("#baseMessage .contact-item[data-item='fws']");//更新接收消息和时间
	            var c = fwsTotal.find($(".contact-item-unread"));
	            if (c.length != 0) {
	                var count = parseInt(c.text());
	                count++;
	                c.text(count);
	            } else {
	                var content = fwsTotal.find($("p"));
	                $('<span class="contact-item-unread">1</span>').appendTo(content);
	            }
	        } else {
	            ChatControl.putUnreadCache(false, id, 0);
	            $(data).find($(".contact-item-unread")).remove();//移除未读消息数目
	            var count = 0;
	            $('#fwsList .contact-item').each(function () {
	                var c = $(this).find('.contact-item-unread');
	                if (c.length != 0) {
	                    count += parseInt(c.text());
	                }
	            });
	            var t = $("#baseMessage .contact-item[data-item='fws']");
	            if (count == 0) {
	                t.find($('.contact-item-unread')).remove();
	            } else {
	                var b = t.find($('.contact-item-unread'));
	                if (b.length != 0) {
	                    b.text(count);
	                } else {
	                    $('<span class="contact-item-unread">' + count + '</span>').appendTo(t.find($("p")));
	                }
	            }
	        }
	    }, updateRecentMsg: function (data, isInit, initCount) {
	        var from = data['to'];//对方
	        var type = data['type'];
	        var msgBody = '';
	        switch (type) {
	            case 'txt':
	                var msg = data['msg'];
	                msg = WebIM.utils.parseEmoji(msg);
	                msgBody = msg.length > 9 ? msg.substr(0, 9) + '...' : msg;
	                break;
	            case 'img':
	                msgBody = '[图片]';
	                break;
	            case 'file':
	                msgBody = '[文件]';
	                break;
	            case 'audio':
	                msgBody = '[语音消息]'
	                break;
	        }
	        var time = ChatControl.getFormateDate(data['time']);
	        var timeText = '';
	        var interval = ChatControl.checkTimeInterval(ChatControl.getFormateDate(new Date()), time);
	        if (interval < 1) {//今天+时间
	            timeText = '今天 ' + time.split(' ')[1];
	        } else if (interval < 2) {
	            timeText = '昨天 ' + time.split(' ')[1];
	        } else if (interval > 7) {//显示日期
	            timeText = time.split(' ')[0];
	        } else {//显示星期+时间
	            timeText = ChatControl.getZhouDay(time) + ' ' + time.split(' ')[1];
	        }
	        var fwsTotal = $("#baseMessage .contact-item[data-item='fws']");//更新接收消息和时间
	        fwsTotal.find($('.contact-item-recent-msg')).text(msgBody);
	        fwsTotal.find($('.contact-item-time')).text(timeText);
	        var target = $('#fwsList .contact-item[data-id="' + from + '"]');
	        if (target.length != 0) {
	            target.find($('.contact-item-recent-msg')).text(msgBody);
	            target.find($('.contact-item-time')).text(timeText);
	            var now = $('.message-title').attr('data-id');
	            if (now != from) {
	                var unread = target.find($('.contact-item-unread'));
	                if (unread.length != 0) {
	                    var count = parseInt(unread.text());
	                    count++;
	                    unread.text(count);
	                    ChatControl.putUnreadCache(true, from, count);
	                } else {
	                    var content = target.find($("p"));
	                    $('<span class="contact-item-unread">1</span>').appendTo(content);
	                    ChatControl.putUnreadCache(true, from, 1);
	                }
	            }
	        } else {//不存在则创建新的
	            var headUrl = data['headUrl'];//头像
	            var nick = data['nick'];//昵称
	            var count = initCount == undefined ? 1 : initCount;
	            var recent = $('<div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="' + from + '"><img src="' + headUrl + '" class="contact-head"><div class="contact-item-right"><span class="contact-item-nick">' + nick + '</span><span class="contact-item-status">在线</span><span class="contact-item-time">' + timeText + '</span><p><span class="contact-item-recent-msg">' + msgBody + '</span><span class="contact-item-unread">' + count + '</span></p></div></div>');
	            if (isInit == undefined)//不是初始化状态不用丢进缓存
	                ChatControl.putNewUnreadPeople(from, count);//把冒出来的未读人缓存
	            $(recent).appendTo($("#fwsList"));
	            
	            /*var isGroup = data['isGroup'];
	        	var groupId = data['hxId'];
	        	var nick = data['nick'];
	        	var contact = $('<div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="' + id + 
	        			'" is-Group = '+ isGroup +'> <img src="' + head + '" class="contact-head" /> <div class="contact-item-right"> <span class="contact-item-nick" data-nick="' + nick + 
	        			'">' + (nick.length > 5 ? nick.substr(0, 5) + '...' : nick) + '</span><span class="contact-item-time"></span> <p><span class="contact-item-recent-msg"></span></p> </div> </div>');
	        	contact.appendTo($("#fwsList"));*/
	        	
	        }
	    }, searchContact: function (obj) {
	        var v = $.trim($(obj).val());
	        setTimeout(function () {
	            if (v != '') {
	                $(".search-contain-list").show();
	                $(".search-contain-list").html('');
	                $("#fwsList .contact-item").each(function () {
	                    var n = $(this).find($(".contact-item-nick")).text();
	                    if (n.indexOf(v) != -1) {
	                        var head = $(this).find($("img")).attr("src");
	                        var id = $(this).attr("data-id");
	                        ContactControl.createSearchItem(id, n, head);
	                    }
	                });
	            } else {
	                $(".search-contain-list").hide();
	            }
	        }, 50);
	    }, createSearchItem: function (id, nick, head) {
	        var str = '<div class="contact-item" onclick="ContactControl.openSearchPosition(' + id + ')" data-id="' + id + '" style="background:#fff"><img src="' + head + '" class="contact-head" /><div class="contact-item-right" style="padding-top:20px"><span class="contact-item-nick" style="font-size:16px">' + nick + '</span></div></div>';
	        $(str).appendTo($(".search-contain-list"));
	    }, openSearchPosition: function (id) {
	        $('.contact-other-contain').show();
	        $("#baseMessage").hide();
	        $("#fwsList").show();
	        $('#fwsList .contact-item[data-id="' + id + '"]').click();
	        $(".search-bg input[type='text']").val('');
	        $(".search-contain-list").hide();
	    }, chatWithServer: function (data) {
	        var tip = data['remain'];
	        ChatControl.addTips(data['hxId'], tip);
	        this.createNewContact(data);
	    }, chatWithMultiServer: function (lessons, targetId, tip, orgUerCode, orgUserId) {
	        $("#fwsList").html('');
	        for (var index in lessons) {
	            var item = lessons[index];
	            ChatControl.addTipsAndInitHistory(orgUerCode, tip, true, item['huanxinId']);
	            this.createNewContact({
	                hxId: item['huanxinId'],
	                nick: item['name'],
	                head: item['photo'],
	                /*isOnline: item['isOnline']*/
	                isGroup: true,
	                groupId: item['id']
	            }, parseInt(item['id']) == targetId);
	        }
	        ChatControl.initOtherUnread(true);//初始化其他未读历史记录
	        //判断是否为禁言状态(管理端全部开放)
	       /* if (item['silence']) {
	        	if (orgUserId != item['creatorId']) {	//除去群主
	        		//文字输入框
	        		$(".chat-text-inp").attr("disabled", true); 
					$(".chat-text-inp").css('background-color', '#e9e9e9');
					$('.silentTip').css('display', 'block');
					//移除表情事件
					$(".webim-emoji-item").attr("disabled", true);
					$(".webim-emoji-item").unbind('click');
					//移除图片事件
					$(".openPicture").attr("disabled", true);
	        	}
	        }*/
	    },
	    createNewContact: function (data, isClick) {
	        var id = data['hxId'];
	        var nick = data['nick'];
	        /** 避免空值 */
	        if (nick == undefined) {
	            nick = "";
	        }
	        var head = data['head'];
	        if (data['isOnline'] != null) {
	        	var isOnline = parseInt(data['isOnline']);
	            var onlineHtml = isOnline == 0 ? '<span class="contact-item-status" style="background: #ccc">离线</span>' : '<span class="contact-item-status">在线</span>';
	            var contact = $('<div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="' + id + '"> <img src="' + head + '" class="contact-head" /> <div class="contact-item-right"> <span class="contact-item-nick" data-nick="' + nick + '">' + (nick.length > 5 ? nick.substr(0, 5) + '...' : nick) + '</span>' + onlineHtml + '<span class="contact-item-time"></span> <p><span class="contact-item-recent-msg"></span></p> </div> </div>');
	            contact.appendTo($("#fwsList"));
	        } else if (data['isGroup'] != null) {	//群组
	        	var isGroup = data['isGroup'];
	        	var groupId = data['hxId'];
	        	var nick = data['nick'];
	        	var groupId = data['groupId'];
	        	var contact = $('<div class="contact-item" onclick="ContactControl.changeContact(this)" data-id="' + id + 
	        			'" is-Group = '+ isGroup +' data-group-id = "'+ groupId +'"> <img src="' + head + '" class="contact-head" /> <div class="contact-item-right"> <span class="contact-item-nick" data-nick="' + nick + 
	        			'">' + (nick.length > 5 ? nick.substr(0, 5) + '...' : nick) + '</span><span class="contact-item-time"></span> <p><span class="contact-item-recent-msg"></span></p> </div> </div>');
	        	contact.appendTo($("#fwsList"));
	        }
	        
	        //$(".contact-item[data-item=\"fws\"]").click();
	        if (isClick)
	            contact.click();
	    }
	}

	var HuanxinControl = {
	    conn: null,
	    nick: null,
	    headUrl: null,
	    orgServeId: null,
	    account: null,
	    hxError: false,
	    init: function (data) {
	        this.conn = new WebIM.connection({
	            https: WebIM.config.https,
	            url: WebIM.config.xmppURL,
	            isAutoLogin: WebIM.config.isAutoLogin,
	            isMultiLoginSessions: WebIM.config.isMultiLoginSessions
	        });
	        this.conn.listen({
	            onOpened: function (message) { //连接成功回调
	                HuanxinControl.conn.setPresence();
	                console.log("登陆成功")
	            },
	            onClosed: function (message) {
	                console.log("连接关闭：" + message);
	            }, //连接关闭回调
	            onTextMessage: function (message) {
	                console.log("文本", message);
	                var type = message['type'];//类型
	                var from = message['from'];//发送方
	                var to = message['to'];//接收方
	                var msgId = message['id'];
	                var msgBody = message['data'];
	                var isGroup = message['type'] == 'groupchat' ? true : false;
	                msgBody = WebIM.utils.parseEmoji(msgBody);
	                var data = {
	                    type: 'txt',
	                    isFrom: false,
	                    time: message['ext']['time'],
	                    msg: msgBody,
	                    to: isGroup == true ? to : from,
	                    nick: message['ext']['nick'],
	                    headUrl: message['ext']['headUrl'],
	                    isGroup: isGroup
	                }
	                ChatControl.handleMsg(data); 
	                ContactControl.updateUnread(true, data);
	            }, //收到文本消息
	            onEmojiMessage: function (message) {
	            }, //收到表情消息
	            onPictureMessage: function (message) {
	            	console.log('收到图片')
	                console.log("图片", message);
	            	var isGroup = message['type'] == 'groupchat' ? true : false;
	            	var from = message['from'];//发送方
	                var to = message['to'];//接收方
	                var json = {
	                    type: 'img',
	                    isFrom: false,
	                    to: isGroup == true ? to : from,
	                    msg: message['url'],
	                    time: message['ext']['time'],
	                    nick: message['ext']['nick'],
	                    headUrl: message['ext']['headUrl'],
	                    isGroup: isGroup
	                }
	                ChatControl.handleMsg(json);
	                ContactControl.updateUnread(true, json);
	            }, //收到图片消息
	            onCmdMessage: function (message) {
	            	console.log('禁言:', message);
	            	if (message['from'] == 'admin') {
	            		var json = {
	            				action: message['action'],
	            				to: message['to']
	            		};
	            		//判断是否为禁言状态		//管理端全部开放
	            		if (message['action'] == 'gag') {
	            			/*$(".chat-text-inp").attr("disabled", true); 
	            			$(".chat-text-inp").css('background-color', '#e9e9e9');
	            			$('.silentTip').css('display', 'block');*/
	            		} else if (message['action'] == 'gag_remove') {
	            			/*$(".chat-text-inp").attr("disabled",false); 
	            			$(".chat-text-inp").css('background-color', 'white');
	            			$('.silentTip').css('display', 'none');*/
	            		}
	            	}
	            	
	            	
	            }, //收到命令消息
	            onAudioMessage: function (message) {
	            	var isGroup = message['type'] == 'groupchat' ? true : false;
	            	var from = message['from'];//发送方
	                var to = message['to'];//接收方
	                var json = {
	                    type: 'audio',
	                    isFrom: false,
	                    to: isGroup == true ? to : from,
	                    msg: message['url'],
	                    time: message['ext']['time'],
	                    nick: message['ext']['nick'],
	                    headUrl: message['ext']['headUrl'],
	                    isGroup: isGroup
	                }

	                ChatControl.handleMsg(json);
	                ContactControl.updateUnread(true, json);
	                console.log("语音", message);
	            }, //收到音频消息
	            onLocationMessage: function (message) {
	            },//收到位置消息
	            onFileMessage: function (message) {
	            	console.log('文件消息', message)
	            	var isGroup = message['type'] == 'groupchat' ? true : false;
	            	var from = message['from'];//发送方
	                var to = message['to'];//接收方
	                var filename = message['filename'];
	                var json = {
	                    type: 'file',
	                    isFrom: false,
	                    to: isGroup == true ? to : from,
	                    msg: message['url'],
	                    time: message['ext']['time'],
	                    nick: message['ext']['nick'],
	                    headUrl: message['ext']['headUrl'],
	                    filename: filename,
	                    isGroup: isGroup
	                }
	                ChatControl.handleMsg(json);
	                ContactControl.updateUnread(true, json);
	            }, //收到文件消息
	            onVideoMessage: function (message) {
	            }, //收到视频消息
	            onPresence: function (message) {
	            }, //收到联系人订阅请求、处理群组、聊天室被踢解散等消息
	            onRoster: function (message) {
	            }, //处理好友申请
	            onInviteMessage: function (message) {
	            }, //处理群组邀请
	            onOnline: function () {
	            }, //本机网络连接成功
	            onOffline: function () {
	            }, //本机网络掉线
	            onError: function (message) {
	                if (!HuanxinControl.hxError) {
	                    HuanxinControl.hxError = true;
	                    console.log("连接超时！请刷新页面重试");
	                }
	                console.log(message);
	            } //失败回调
	        });
	        this.nick = data['nick'];
	        this.headUrl = data['headUrl'];
	        this.orgServeId = data['orgServeId'];
	        this.login(data['account'], data['password']);
	    }, login: function (account, pwd) {
	        var options = {
	            apiUrl: WebIM.config.apiURL,
	            user: account,
	            pwd: pwd,
	            appKey: WebIM.config.appkey
	        };
	        this.conn.open(options);
	        this.account = account;
	    },
	    sendMsg: function () {
	    	$('.face').css('display', 'none');
	        var text = $(".chat-text-inp").val();
	        if (text == '') {
	        	text = $(".chat-text-inp").text();
	        	if (text == '') {
	        		return;
	        	}
	        }
	        $(".chat-text-inp").val('');
	        var other = $(".message-title").attr("data-id");
	        var isGroup = $(".message-title").attr("is-group");
	        var id = this.conn.getUniqueId();//生成本地消息id
	        if ($.trim(other) == '') {
	            return;
	        }
	       
	        var data = {
	            type: 'txt',
	            isFrom: true,
	            time: new Date(),
	            msg: WebIM.utils.parseEmoji(text),
	            to: other,
	            msgId: id,
	            nick: this.nick,
	            headUrl: this.headUrl,
	            isGroup: isGroup == 'true' ? true : false,	//是否为群聊
	        }
	        ChatControl.handleMsg(data); 
	        ContactControl.updateRecentMsg(data);
	        
	        var msg = new WebIM.message('txt', id);//创建文本消息
	        if (isGroup == 'true') {	//为群聊
	        	msg.set({
	                msg: text,
	                to: other,
	                roomType: false,
	                chatType: 'chatRoom',
	                ext: {
	                    time: new Date(), nick: this.nick,
	                    headUrl: this.headUrl, orgServeId: this.orgServeId
	                },
	                success: function (id, serverMsgId) {
	                	console.log('send room text success');
	                    ChatControl.changeSendStaus(id, 1);
	                }, fail: function () {
	                    ChatControl.changeSendStaus(id, 2);
	                }
	            });
	        	msg.setGroup('groupchat');
	        } else {
	        	msg.set({
	                msg: text,
	                to: other,
	                ext: {
	                    time: new Date(), nick: this.nick,
	                    headUrl: this.headUrl, orgServeId: this.orgServeId
	                },
	                success: function (id, serverMsgId) {
	                    ChatControl.changeSendStaus(id, 1);
	                }, fail: function () {
	                    ChatControl.changeSendStaus(id, 2);
	                }
	            });
	        }
	        
	        this.conn.send(msg.body);
	    }, sendPicture: function () {
	    	var isGroup = $(".message-title").attr("is-group");
	        var id = this.conn.getUniqueId();
	        var other = $(".message-title").attr("data-id");
	        var msg = new WebIM.message('img', id);
	        var input = document.getElementById('chat_photo_inp');//选择图片的input
	        if (!this.checkIsImg(input.files[0].name)) {
	            layer.msg("请选择正确的图片格式");
	            return;
	        }
	        var file = WebIM.utils.getFileUrl(input);
	        var allowType = {
	            "jpg": true,
	            "gif": true,
	            "png": true,
	            "bmp": true
	        };
	        var data = {
	            type: 'img',
	            isFrom: true,
	            time: new Date(),
	            msg: input.files[0],
	            to: other,
	            msgId: id,
	            nick: this.nick,
	            headUrl: this.headUrl,
	            isGroup: isGroup == 'true' ? true : false,	//是否为群聊
	        }
	        ChatControl.handleMsg(data);
	        ContactControl.updateRecentMsg(data);
	        var imgPath = null;
	        if (file.filetype.toLowerCase() in allowType) {
	            msg.set({
	                apiUrl: WebIM.config.apiURL,
	                file: file,
	                to: other,
	                roomType: false,
	                chatType: isGroup == 'true' ? 'chatRoom' : 'singleChat',
	                ext: {
	                    time: new Date(), nick: this.nick,
	                    headUrl: this.headUrl, orgServeId: this.orgServeId
	                },
	                onFileUploadError: function (error) {
	                    if (imgPath != null)
	                        ChatControl.changeSendStaus(id, 2, imgPath);
	                },//图片上传失败
	                onFileUploadComplete: function (data) {
	                    //图片地址：data.uri + '/' + data.entities[0].uuid;
	                    imgPath = data.uri + '/' + data.entities[0].uuid;
	                },
	                success: function (id, serverMsgId) {
	                    if (imgPath != null)
	                        ChatControl.changeSendStaus(id, 1, imgPath);
	                },
	                flashUpload: WebIM.flashUpload
	            });
	            if (isGroup == 'true') {
	            	msg.setGroup('groupchat');
	            }
	        }
	        this.conn.send(msg.body);
	    }, 
	    /** 发送文件消息 */
	    sendFile: function () {
	    	var isGroup = $(".message-title").attr("is-group");
	    	var other = $(".message-title").attr("data-id");
	    	var id = this.conn.getUniqueId();                   // 生成本地消息id
	        var msg = new WebIM.message('file', id);        // 创建文件消息
	        var input = document.getElementById('chat_file_inp');  // 选择文件的input
	        var file = WebIM.utils.getFileUrl(input);      // 将文件转化为二进制文件
	        var pos = $(input).val().lastIndexOf('\\');
	        var filename = $(input).val().substring(pos+1);
	        var allowType = {
	            'jpg': true,
	            'gif': true,
	            'png': true,
	            'bmp': true,
	            'zip': true,
	            'txt': true,
	            'doc': true,
	            'pdf': true
	        };
	        var data = {
		            type: 'file',
		            isFrom: true,
		            time: new Date(),
		            msg: input.files[0],
		            to: other,
		            msgId: id,
		            nick: this.nick,
		            headUrl: this.headUrl,
		            filename: filename,
		            isGroup: isGroup == 'true' ? true : false,	//是否为群聊
		        }
		        /*ChatControl.handleMsg(data);
		        ContactControl.updateRecentMsg(data);*/
	        var filePath = null;
//	        if (file.filetype.toLowerCase() in allowType) {
	            var option = {
	                apiUrl: WebIM.config.apiURL,
	                file: file,
	                to: other,                       // 接收消息对象
	                roomType: false,
	                chatType: isGroup == 'true' ? 'chatRoom' : 'singleChat',
            		ext: {
	                    time: new Date(), nick: this.nick,
	                    headUrl: this.headUrl, orgServeId: this.orgServeId
	                },
	                onFileUploadError: function (error) {
	                    if (filePath != null)
	                        ChatControl.changeSendStaus(id, 2, filePath);
	                },
	                onFileUploadComplete: function (result) {
	                    //图片地址：data.uri + '/' + data.entities[0].uuid;
	                    filePath = result.uri + '/' + result.entities[0].uuid;
	                    data.msg = filePath;
	                    /*$('.sendStatus').text('点击下载');
	                    $('.sendStatus').attr('href', filePath);*/
	                    ChatControl.handleMsg(data);
	    		        ContactControl.updateRecentMsg(data);
	                },
	                success: function (id, serverMsgId) {
	                    if (filePath != null)
	                        ChatControl.changeSendStaus(id, 1, filePath);
	                },
	                flashUpload: WebIM.flashUpload
	            };
	            msg.set(option);
	            if (isGroup == 'true') {
	            	msg.setGroup('groupchat');
	            }
	            this.conn.send(msg.body);
//	        }
	    }, checkIsImg: function (name) {
	        var fileSub = name.substr(name.lastIndexOf(".") + 1,
	            name.length).toLowerCase();
	        if (fileSub != 'jpg' && fileSub != 'bmp' && fileSub != 'png'
	            && fileSub != 'jpeg' && fileSub != 'gif') {
	            return false;
	        }
	        return true;
	    }
	}

	var ChatControl = {
	    isShowing: false,//
	    chatCache: {chat: {}, unread: {}, contacts: {}},
	    init: function () {

	    },
	    putNewUnreadPeople: function (id, c) {//突然冒出来的未读信息进行缓存
	        var json = {unread: []};
	        json.unread.push(id);
	        $.localSave(HuanxinControl.account, JSON.stringify(json));
	        var newHistory = $.localGet(HuanxinControl.account + ":" + id);
	        console.log("历史记录："+newHistory);
	        if (newHistory != null) {
	            var history = JSON.parse(newHistory).chat;
	            //合并历史记录
	            var nowRecord = this.chatCache.chat[id];
	            if (history[history.length - 1] == nowRecord[0])
	                nowRecord.splice(0, 1);
	            this.chatCache.chat[id] = history.concat(nowRecord);
	        }
	        this.putUnreadCache(true, id, c);
	    },
	    putUnreadCache: function (isIncr, id, count) {//未读消息数目存进缓存
	        if (isIncr) {
	            if (this.chatCache.unread.hasOwnProperty(id)) {
	                var old = parseInt(this.chatCache.unread[id]);
	                old += count;
	                this.chatCache.unread[id] = old;
	            } else {
	                this.chatCache.unread[id] = count;
	            }
	        } else {
	            if (this.chatCache.unread.hasOwnProperty(id)) {
	                delete this.chatCache.unread[id];
	            }
	            var arrayUnread = $.localGet(HuanxinControl.account);
	            if (arrayUnread != null) {
	                var array = JSON.parse(arrayUnread).unread;
	                array.splice($.inArray(id, array), 1);//删除未读的
	                $.localSave(HuanxinControl.account, JSON.stringify({unread: array}));
	            }
	        }
	        this.saveRecord(id);
	    },
	    saveRecord: function (id) {
	        if (this.chatCache.chat.hasOwnProperty(id)) {
	            var json = {chat: this.chatCache.chat[id], unread: 0};
	            if (this.chatCache.unread.hasOwnProperty(id))
	                json.unread = this.chatCache.unread[id];
	            var first = JSON.parse(json.chat[0]);
	            if (first.type == 'tip') {
	                json.chat.splice(0, 1);
	            }
	            $.localSave(HuanxinControl.account + ":" + id, JSON.stringify(json));
	        }
	    },
	    getFormateDate: function (date) {//转换日期new Date()字符串转 yyyy-mm-dd HH:MM格式
	        date = new Date(date);
	        var y = date.getFullYear();
	        var m = date.getMonth() + 1;
	        m = m < 10 ? ('0' + m) : m;
	        var d = date.getDate();
	        d = d < 10 ? ('0' + d) : d;
	        var h = date.getHours();
	        var minutes = date.getMinutes();
	        minutes = minutes < 10 ? ('0' + minutes) : minutes;
	        return y + '-' + m + '-' + d + ' ' + h + ':' + minutes;
	    }, parseDate: function (date) {//解析时间
	        var t = Date.parse(date);
	        return !isNaN(t) ? new Date(Date.parse(date.replace(/-/g, "/"))) : new Date();
	    },
	    checkTimeInterval: function (startDate, endDate) {//计算时间相差多少天
	        startDate = this.parseDate(startDate);
	        endDate = this.parseDate(endDate);
	        var diff = endDate.getTime() - startDate.getTime();
	        return Math.abs(diff / (60000 * 60 * 24));
	    }, getZhouDay: function (day) {
	        var tday = new Date(day).getDay();
	        tday = parseInt(tday);
	        switch (tday) {
	            case 1:
	                tday = '星期一';
	                break;
	            case 2:
	                tday = '星期二';
	                break;
	            case 3:
	                tday = '星期三';
	                break;
	            case 4:
	                tday = '星期四';
	                break;
	            case 5:
	                tday = '星期五';
	                break;
	            case 6:
	                tday = '星期六';
	                break;
	            case 7:
	                tday = '星期天';
	                break;
	        }
	        return tday;
	    },
	    handleMsg: function (data) {//控制消息的入口
	        var type = data['type'];
	        var now = $('.message-title').attr("data-id");
	        var from = data['to'];
	        switch (type) {
	            case 'txt':
	                if (now != undefined && now == from){
	                	this.createMsgItem(data);
	                }
	                this.putInCache(data);
	                break;
	            case 'img':
	                var isFrom = data['isFrom'];
	                if (!isFrom) {
	                    if (now != undefined && now == from)
	                        this.createMsgItem(data);
	                    this.putInCache(data);
	                    return;
	                }
	                var imgMsg = data['msg'];
	                var reader = new FileReader();
	                reader.readAsDataURL(imgMsg);
	                reader.onload = function (e) {
	                    data['msg'] = this.result;
	                    if (now != undefined && now == from)
	                        ChatControl.createMsgItem(data);
	                    data['msg'] = "[图片]";
	                    ChatControl.putInCache(data);
	                }
	                break;
	            case 'file':
	                var isFrom = data['isFrom'];
//	                if (!isFrom) {
	                    if (now != undefined && now == from)
	                        this.createMsgItem(data);
	                    this.putInCache(data);
	                    return;
//	                }
	                /*var imgMsg = data['msg'];
	                var reader = new FileReader();
	                reader.readAsDataURL(imgMsg);
	                reader.onload = function (e) {
	                    data['msg'] = this.result;
	                    if (now != undefined && now == from)
	                        ChatControl.createMsgItem(data);
	                    data['msg'] = "[文件]";
	                    ChatControl.putInCache(data);
	                }*/
	                
	                break;
	            case 'audio':
	                if (now != undefined && now == from)
	                    this.createMsgItem(data);
	                this.putInCache(data);
	                break;
	        }
	    }, putInCache: function (data) {
	        var to = data['to'];
	        if (this.chatCache.chat.hasOwnProperty(to)) {
	            var itemArray = this.chatCache.chat[to];
	            itemArray.push(JSON.stringify(data));
	            this.chatCache.chat[to] = itemArray;
	            if (data['type'] != 'tip')
	                this.saveRecord(to);
	            var array = [];
	            array.push(JSON.stringify(data));
	            this.chatCache.chat[to] = array;
	            if($('#fwsList .contact-item[data-id="' + to + '"]').length!=0){
	                if (data['type'] != 'tip')
	                    this.saveRecord(to);
	            }
	        }

	    }, changeSendStaus: function (msgId, status, filePath) {
	        var targetStatus = $('.chat-msg-contain').find($('div[data-status="' + msgId + '"]'));
	        var id = targetStatus.attr("data-id");
	        switch (status) {
	            case 0://发送中

	                break;
	            case 1://发送成功
	                targetStatus.remove();
	                var list = this.chatCache.chat[id];
	                for (var index in list) {
	                    var item = JSON.parse(list[index]);
	                    if (item['msgId'] == msgId) {
	                        item['msgId'] = "1";
	                        if (filePath != undefined)
	                            item['msg'] = filePath;
	                        this.chatCache.chat[id][index] = JSON.stringify(item);
	                        break;
	                    }
	                }
	                break;
	            case 2://发送失败
	                targetStatus.css("background", "#ff0000");
	                var list = this.chatCache.chat[id];
	                for (var index in list) {
	                    var item = JSON.parse(list[index]);
	                    if (item['msgId'] == msgId) {
	                        item['msgId'] = "2";
	                        if (filePath != undefined)
	                            item['msg'] = filePath;
	                        this.chatCache.chat[id][index] = JSON.stringify(item);
	                        break;
	                    }
	                }
	                break;
	        }
	        this.saveRecord(id);
	    }, createMsgItem: function (data, operationType) {
	    	
	        /*console.log(this.chatCache.chat)*/
	        var type = data['type'];//聊天类型
	        if (type == 'tip') {
	            var remain = data['msg'];
	            var tip = $('<div class="chat-tips">您好，本次定制服务已接通，现在您可以开始与用户进行沟通了</div>');
	            tip.appendTo($(".chat-msg-contain"));
	            return;
	        }
	        var filename = data['filename'];
	        var to = data['to'];
	        var isFrom = data['isFrom'];//是否来自发送方
	        var time = this.getFormateDate(data['time']);//时间
	        var msg = data['msg'];//信息内容
	        var headUrl = data['headUrl'];
	        var nick = data['nick'];
	        var lastTime = $('.chat-msg-contain .chat-item:last').attr("data-time");
	        var timeText = '';
	        var interval = this.checkTimeInterval(lastTime, time);
	        if (interval < 1) {//今天+时间
	            timeText = '今天 ' + time.split(' ')[1];
	        } else if (interval < 2) {
	            timeText = '昨天 ' + time.split(' ')[1];
	        } else if (interval > 7) {//显示日期
	            timeText = time;
	        } else {//显示星期+时间
	            timeText = this.getZhouDay(time) + ' ' + time.split(' ')[1];
	        }
	        var itemContain = $('<div class="chat-item" data-time="' + time + '"></div>');
	        var head = isFrom ? $('<div class="chat-right-head"><img src="' + headUrl + '" /></div>') : $('<div class="chat-left-head"><img src="' + headUrl + '" /></div>');
	        $(head).appendTo($(itemContain));
	        var msgContain = isFrom ? $('<div class="chat-right-msg"></div>') : $('<div class="chat-left-msg"></div>');
	        var nameAndTime = $('<div class="chat-item-name-time">' + nick + '<span>' + timeText + '</span></div>');
	        $(nameAndTime).appendTo($(msgContain));
	        if (isFrom) {
	            var msgId = data['msgId'];
	            switch (msgId) {
	                case "1"://已经发送成功
	                    break;
	                case "2"://发送失败
	                    break;
	                default://正在发送
	                    var status = $('<div class="chat-right-send-status" data-status="' + msgId + '" data-id="' + to + '"><img src="static/images/loading.gif"/></div>');
	                    $(status).appendTo($(msgContain));
	                    break;
	            }
	        }
	        var msgBody = '';
	        switch (type) {
	            case 'txt'://文本消息
	                msgBody = isFrom ? $('<div class="chat-right-msg-arrow"></div>') : $('<div class="chat-left-msg-arrow"></div>');
	                msgBody.html(msg);
	                break;
	            case 'img':
	                msgBody = isFrom ? $('<div class="chat-right-msg-arrow"><img src="' + msg + '" style="max-width:100%" /></div>') : $('<div class="chat-left-msg-arrow"><img src="' + msg + '" style="max-width:100%" /></div>');
	                break;
	            case 'file':
	            	filename = filename.length > 10 ? filename.substr(0, 10) + '...' : filename;
	            	var sendMsg = '点击下载';
	                msgBody = isFrom ? $('<div class="chat-right-msg-arrow">\
	                			<img src="static/images/select_img.png" style = "margin-right: 10px; margin-top: 10px;"/>\
		                		<span style = "color: black;">'+ filename +'</span>\
		                		<a class = "sendStatus" target = "_blank" href = "'+ msg +'" style = "float: right; margin-left: -55px; margin-top: 20px; color: gray; font-size: 10px;">'+ sendMsg +'</a>\
	                		</div>') : $('<div class="chat-left-msg-arrow">\
	                			<img src="static/images/select_img.png" style = "margin-right: 10px; margin-top: 10px;"/>\
	                			<span style = "color: black;">'+ filename +'</span>\
	                			<a class = "sendStatus" target = "_blank" href = "'+ msg +'" style = "float: right; margin-left: -55px; margin-top: 20px; color: gray; font-size: 10px;">' + sendMsg+ '</a>\
	                		</div>');
	                break;
	            case 'audio':
	                msgBody = isFrom ? $('<div class="chat-right-msg-arrow">您接收到了一条语音消息，暂时不支持播放</div>') : $('<div class="chat-left-msg-arrow">您接收到了一条语音消息，暂时不支持播放</div>');
//	                msgBody=isFrom ?$('<div class="chat-right-msg-arrow"><audio src="' + msg + '"/><button onclick="ChatControl.playAudio(this)">语音消息</button></div>') : $('<div class="chat-left-msg-arrow"><audio src="' + msg + '"/><button onclick="ChatControl.playAudio(this)">语音消息</button></div>');
	                break;
	        }
	        if (operationType == 'more') {
	        	$(msgBody).appendTo($(msgContain));
		        $(msgContain).appendTo($(itemContain));
		        $('.chat-msg-contain').children('center').eq(0).after(itemContain);
	        } else {
	        	$(msgBody).appendTo($(msgContain));
		        $(msgContain).appendTo($(itemContain));
		        $(itemContain).appendTo($(".chat-msg-contain"));
	        }
	        $(".chat-msg-contain").scrollTop($(".chat-msg-contain")[0].scrollHeight);
	        //$(".chat-msg-contain").animate({ scrollTop: $(".chat-msg-contain")[0].scrollHeight },50);
	    }, playAudio: function (obj) {//播放语音
	        var targetAudio = $(obj).parent().find($("audio"))[0];
	        if (targetAudio.paused) {
	            targetAudio.play();
	        } else {
	            targetAudio.pause();
	            targetAudio.currentTime = 0;
	        }
	    },
	    changeContact: function (id, nick, isGroup, groupId) {
	    	
	        $('.message-title span').text(nick);
	        $('.message-title').attr("data-id", id);
	        $('.message-title').attr("is-group", isGroup);
	        vueModel.getLesson().checkedGroupId = groupId;
	        $('.chat-msg-contain').html('');
	        if (this.chatCache.chat.hasOwnProperty(id)) {
	        	$('.chat-msg-contain').append('<center><span><a href = "#" style = "color: #218bde;" \
	        			onclick="ChatControl.getMoreMsg(' + id + ', 2)">查看更多消息</a></span></center>');
	            var data = this.chatCache.chat[id];
	            var len = data.length;
	            var i = len > 30 ? len - 30 : 0;		//显示最近30条数据
	            for (i ; i < len; i ++) {
	            	var item = data[i];
	                this.createMsgItem(JSON.parse(item));
	            }
	        }
	    },
	    /** 获取更多消息 */
	    getMoreMsg: function (id, times) {
	        $('.message-title').attr("data-id", id);
	        if (this.chatCache.chat.hasOwnProperty(id)) {
	        	var data = this.chatCache.chat[id];
	        	var len = data.length;
	            if (30 * (times - 1) >= len) {
	            	layer.msg('没有更多消息了');
	            	return;
	            }
	            
	            var i = len > 30 * times ? len - 30 * times : 0;	//显示最近30条数据
	            for (i ; i < len - 30; i ++) {
	            	var item = data[i];
	                this.createMsgItem(JSON.parse(item), 'more');
	            }
	            times = times + 1;
	            $('.chat-msg-contain').find('a').eq(0).attr('onclick', 'ChatControl.getMoreMsg(' + id + ', '+ times +')');
	        }
	    }
	    ,addTipsAndInitHistory: function (id, msg, isGroup, group_huanxinId) {//添加小提示
	        var data = {
	            to: id,
	            msg: msg,
	            type: 'tip'
	        }
	        data = JSON.stringify(data)
	        var history = $.localGet(HuanxinControl.account + ":" + id);
	        if (isGroup) {
	        	history = $.localGet(id + ":" + group_huanxinId);
	        }
	        if (history != null) {
	            history = JSON.parse(history).chat;
	            var item = history[0];//获取第一个元素
	            if (item.type == 'tip') {
	                history.splice(0, 1);
	            }
	            
//	            history.splice(0, 0, data);//第一条数据增加tip
	        } else {
	            history = new Array();
//	            history.push(data);
	        }
	        if (isGroup) {
	        	this.chatCache.chat[group_huanxinId] = history;
	        } else {
	        	this.chatCache.chat[id] = history;
	        }
	        
	        //this.putInCache(data);
	    }, initOtherUnread: function (isGroup) {//初始化其他人的未读消息展示
	        var unreadPeople = $.localGet(HuanxinControl.account);
	        if (unreadPeople != null) {
	            var array = JSON.parse(unreadPeople).unread;
	            for (var index in array) {
	                var id = array[index];
	                var history = $.localGet(HuanxinControl.account + ":" + id);
	                if (isGroup) {
	    	        	history = $.localGet(id + ":" + HuanxinControl.account);
	    	        }
	                if (history != null && !this.chatCache.chat.hasOwnProperty(id)) {
	                    var json = JSON.parse(history);
	                    this.chatCache.chat[id] = json.chat;
	                    var data = json.chat[(json.chat).length - 1];
	                    ContactControl.updateRecentMsg(JSON.parse(data), true, json.unread);
	                }
	            }
	        }
	    }
	}

/* 对话框创建的工具类 */
var dialogUtils = {
    /** 健康数据缓存 */
    healthDataCache: {},
    showDialog: function (target, data, d) {
        switch (target) {
            case 0:// 服务师信息的对话框
                if (data == null)
                    return;
                if (data==undefined|| data==null) {
                    layer.msg("该群组还没分配服务师，无法查看");
                    return;
                }
                var dialog = $('<div style="padding: 15px;">'+data+'</div>');
                document.body.appendChild($(dialog)[0]);
                layer
                    .open({
                        type: 1,
                        title: ['服务师信息',
                            'text-align:center;font-size:16px;background:#fff;'],
                        area: '650px',
                        offset: 100,
                        moveType: 1,
                        scrollbar: false,
                        zIndex: 9999,
                        scrolling: 'no',
                        content: $(dialog),
                        end: function () {// 销毁该弹出层
                            $(dialog).remove();
                        }
                    });
                /*var result = ajaxSend(
                    {
                        id: data
                    },
                    'orgServeControl.do?getEmployInfo',
                    false,
                    function (result) {
                        layer.closeAll();
                        var dialog = dialogUtils.createServicerInfo(result);
                        layer
                            .open({
                                type: 1,
                                title: ['服务师信息',
                                    'text-align:center;font-size:16px;background:#fff;'],
                                area: '650px',
                                offset: 100,
                                moveType: 1,
                                scrollbar: false,
                                zIndex: 9999,
                                scrolling: 'no',
                                content: $(dialog),
                                end: function () {// 销毁该弹出层
                                    $(dialog).remove();
                                }
                            });
                    });*/
                break;
            case 1:// 健康数据对话框
                if (data == null) {
                    return;
                }
                var userId = parseInt(data);
                var date = DateUtils.formatDate(new Date());

                var key = userId + "," + date;

                if (!this.healthDataCache.hasOwnProperty(key)) {
                    jQuery
                        .ajax({
                            type: 'GET',
                            url: 'orgServeControl.do?getMemberHealthDataByMeasureDate&userId='
                            + userId + '&measureDate=' + date,
                            beforeSend: function () {
                                layer.load(1);
                            },
                            success: function (result) {
                                openHealthDialog(result.attributes);
                                // 保存数据到缓存
                                dialogUtils.healthDataCache[key] = result.attributes;
                            },
                            complete: function (data) {
                                layer.closeAll("loading");
                            }
                        });
                } else {
                    openHealthDialog(this.healthDataCache[key]);
                }

            function openHealthDialog(data) {
                var dialog = dialogUtils.createHealthData(userId, date, data);
                layer
                    .open({
                        type: 1,
                        title: ['健康数据',
                            'text-align:center;font-size:16px;background:#fff;'],
                        area: '650px',
                        offset: 100,
                        moveType: 1,
                        scrollbar: false,
                        zIndex: 9999,
                        scrolling: 'no',
                        content: $(dialog),
                        end: function () {// 销毁该弹出层
                            jQuery("div.layui-layer-content").remove();
                        }
                    });
            }

                break;
            case 2:// 添加或编辑群组的对话框
                if (data != undefined && data == 1) {// 编辑
                    if ($(".group_list").children().length == 0) {
                        layer.msg("您目前没有群组可进行编辑");
                        return;
                    }
                    layer.load();
                    var groupId = $('#groupNumber').text();
                    var json = {
                        groupId: groupId
                    };
                    if (groupManagerCache == null)
                        json.noServerCache = true;
                    ajaxSend(json, 'orgServeControl.do?getEditGroupCondition',
                        true, function (result) {
                            layer.closeAll();
                            if (groupManagerCache == null)
                                groupManagerCache = result.attributes.emList;
                            var groupName = $.trim($(".group_name h2").text());
                            var oldData = {
                                selectServers: result.attributes.servers,
                                groupId: groupId,
                                groupName: groupName
                            };
                            dialogUtils.showAddGroupDiaglog(dialogUtils
                                    .createAddGroup(groupManagerCache, true,
                                        result.attributes.servers), true,
                                oldData);
                        });
                    return;
                }
                // 添加
                if (groupManagerCache != null) {
                    dialogUtils.showAddGroupDiaglog(dialogUtils
                        .createAddGroup(groupManagerCache));
                    return;
                }
                layer.load();
                ajaxSend(null, 'orgServeControl.do?getAddGroupCondition', false,
                    function (result) {
                        groupManagerCache = result.obj;
                        dialogUtils.showAddGroupDiaglog(dialogUtils
                            .createAddGroup(groupManagerCache));
                    });
                break;
            case 3:// 群组二维码对话框
                var dialog = this.createGroupQrcode(data, d);
                layer.open({
                    type: 1,
                    title: ['二维码',
                        'text-align:center;font-size:16px;background:#fff;'],
                    area: '350px',
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    scrolling: 'no',
                    content: $(dialog),
                    end: function () {// 销毁该弹出层
                        $(dialog).remove();
                    }
                });
                break;
            case 4:// 个人信息的对话框
                var dialog = this.peopleInfo.createPersonInfo(data,d);
                layer.open({
                    type: 1,
                    title: ['个人信息',
                        'text-align:center;font-size:16px;background:#fff;'],
                    area: ['670px', '600px'],
                    offset: 100,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    content: $(dialog),
                    success: function () {
                        var obj = jQuery(
                            "div.person_dialog_contain>div.right_title>label")
                            .eq(0).click();
                    },
                    end: function () {
                        $(dialog).remove();
                    }
                });
                break;
            case 5://健康会话
                ChatControl.openChat({
                    to: d,
                    nick: data
                });
                break;
            case 6://移动群组
                if($(".group_list").children().length==1){
                    layer.msg("当前没有可以移动到的群组");
                    return;
                }
                var dialog = this.createMoveGroupContainer($("#groupNumber").text());
                layer.open({
                    type: 1,
                    area: ['250px'],
                    title: ['移动群组',
                        'text-align:center;font-size:16px;background:#fff;'],
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    content: $(dialog),
                    success: function () {

                    },
                    end: function () {
                        $(dialog).remove();
                    }, btn: ['确定', '取消'],
                    yes: function (index) {
                        var v = $(dialog).find($("select option:selected")).val();
                        var oldGroup = $("#groupNumber").text();
                        ajaxSend({
                                newGroup: v, userId: data, serveId: serveTarget, oldGroup: oldGroup
                            }, 'orgServeControl.do?moveGroup', true,
                            function (result) {
                                layer.closeAll();
                                window.location.reload();
                            });
                    }
                });
                break;
        }
    },
    createMoveGroupContainer: function (escape) {
        var str = '<div style="padding:10px 15px;background:#fff;text-align: center"><select style="padding: 0 8px;font-size: 15px">';
        $(".group_list li").each(function () {
            var name = $(this).text();
            var dataId = $(this).attr("data-id");
            if (dataId != escape)//防止将要删除的群组重名
                str += '<option value="' + dataId + '">' + name + '</option>';
        });
        str += '</select></div>';
        var dialog = $(str);
        document.body.appendChild($(dialog)[0]);
        return dialog;
    },
    showAddGroupDiaglog: function (dialog, isEdit, oldData) {
        var title = isEdit == undefined ? "添加群组" : "编辑群组";
        layer.open({
            type: 1,
            title: [title,
                'text-align:center;font-size:16px;background:#fff;'],
            area: '550px',
            moveType: 1,
            scrollbar: false,
            zIndex: 9999,
            btn: ['确定', '取消'],
            scrolling: 'no',
            content: $(dialog),
            end: function () {// 销毁该弹出层
                $(dialog).remove();
            },
            yes: function (index) {
                isEdit != undefined && isEdit ? groupControl.editGroup(oldData)
                    : groupControl.addGroup($.trim(groupName_input.value));
            }
        });
    },
    createServicerInfo: function (data) {// 创建服务师显示信息的对话框
        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        var head = document.createElement("img");
        $(head).css({
            "position": "absolute",
            "width": "110px",
            "height": "110px",
            "margin": "15px"
        });
        head.src = data.photo == undefined ? "static/images/head.png"
            : data.photo;
        var msgContain = document.createElement("ul");
        $(msgContain)
            .attr("style",
                "margin-left:140px;font-size:16px;line-height:28px;padding:15px 10px");
        data.sex == true ? data.sex = '男' : data.sex = '女';
        $(msgContain).html(
            '<li><h2 style="font-size:20px;">' + data.realName
            + '（服务师）</h2></li><li>用户名：' + data.userName
            + '</li><li>性别：' + data.sex + '</li> <li>所属机构：'
            + data.orgName + '</li><li>服务的群组：' + data.serveGroup
            + '</li>');
        dialogContainer.appendChild(head);
        dialogContainer.appendChild(msgContain);
        document.body.appendChild(dialogContainer);
        return dialogContainer;
    },
    createHealthData: function (userId, date, data) {// 创建健康数据对话框

        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        $(dialogContainer).css("padding-top", "0");
        var dateContain = document.createElement("div");
        $(dateContain).addClass("date_contain");
        $(dateContain)
            .html(
                '<a href="javascript:dialogUtils.switchHealthData('
                + userId
                + ',\''
                + String(date)
                + '\',-1)" class="pre_v"><img src="static/images/pre_v.png">'
                + '</a><a href="javascript:dialogUtils.switchHealthData('
                + userId
                + ',\''
                + date
                + '\',1)" class="next_d"><img src="static/images/pre_v.png"></a><span>'
                + String(date) + '</span>');
        var healthTable = document.createElement("table");
        $(healthTable).attr({
            "cellpadding": "0",
            "cellspacing": "0",
            "class": "healthTable"
        });

        var $tbody = this.createHealthDataBody(data);
        $(healthTable).append($tbody);

        dialogContainer.appendChild(dateContain);
        dialogContainer.appendChild(healthTable);
        document.body.appendChild(dialogContainer);
        return dialogContainer;
    },
    createHealthDataBody: function (data) {

        var $tbody = jQuery("<tbody></tbody>");

        if (data.Glucometer != 'undefined' && data.Glucometer != null) {
            var $xueTang = jQuery('<tr><td>血糖仪</td><td><p>血糖值：'
                + glucometerMeasureTypeToString(data.Glucometer.measureType)
                + ' '
                + data.Glucometer.bloodSugar
                + ' mmol/L</p></td><td>'
                + (new Date(data.Glucometer.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 血糖仪
            $tbody.append($xueTang);

            function glucometerMeasureTypeToString(measureType) {
                var string = "";
                switch (measureType) {
                    case 1:
                        string = "早餐前";
                        break;
                    case 2:
                        string = "早餐后";
                        break;
                    case 3:
                        string = "午餐前";
                        break;
                    case 4:
                        string = "午餐后";
                        break;
                    case 5:
                        string = "晚餐前";
                        break;
                    case 6:
                        string = "晚餐后";
                        break;
                }
                return string;
            }
        }
        if (data.BloodPressure != 'undefined' && data.BloodPressure != null) {
            var $xueYa = jQuery('<tr> <td>血压计</td><td><p>收缩压：'
                + data.BloodPressure.diastolic
                + ' mmHg</p><p>舒张压：'
                + data.BloodPressure.systolic
                + ' mmHg</p><p>心率：'
                + data.BloodPressure.heartRate
                + ' bpm</p></td><td>'
                + (new Date(data.BloodPressure.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 血压计
            $tbody.append($xueYa);
        }
        if (data.Oxygen != 'undefined' && data.Oxygen != null) {
            var $xueYangYi = jQuery('<tr><td>血氧仪</td><td><p>血氧饱和度：'
                + data.Oxygen.saturation
                + ' %</p><p>心率：'
                + data.Oxygen.heartRate
                + ' bpm</p></td><td>'
                + (new Date(data.Oxygen.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 血氧仪
            $tbody.append($xueYangYi);
        }
        if (data.Lunginstrument != 'undefined' && data.Lunginstrument != null) {
            var $feiHuoYi = jQuery('<tr><td>肺活仪</td><td><p>肺活仪：'
                + data.Lunginstrument.vitalCapacity
                + ' ml</p></td><td>'
                + (new Date(data.Lunginstrument.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 肺活仪
            $tbody.append($feiHuoYi);
        }
        if (data.Band != 'undefined' && data.Band != null) {

            var heartRateData = "暂时没有该项数据";
            if (data.Heartrate != 'undefined' && data.Heartrate != null) {
                heartRateData = data.Heartrate.heartRate + " bpm";
            }

            var $xinLvSH = jQuery('<tr><td>心率手环</td><td><p>卡路里：'
                + data.Band.kcal + ' Kcal</p><p>里程：' + data.Band.mileage
                + ' 公里</p>' + '<p>心率：' + heartRateData + '</p><p>深睡：'
                + data.Band.deepDuration + ' 小时</p><p>浅睡：'
                + data.Band.shallowDuration + ' 小时</p></td><td>'
                + (new Date(data.Band.date).Format("yyyy-MM-dd"))
                + '</td></tr>');// 心率手环

            $tbody.append($xinLvSH);
        }
        if (data.Bodyfatscale != 'undefined' && data.Bodyfatscale != null) {
            var $tiZhiCheng = jQuery('<tr><td>体脂秤</td><td><p>体重：'
                + data.Bodyfatscale.weight
                + ' Kg</p><p>体脂肪：'
                + data.Bodyfatscale.axungeRatio
                + ' %</p>'
                + '<p>体年龄：'
                + data.Bodyfatscale.bodyage
                + ' 岁</p>'
                + '<p>腰臀比：'
                + data.Bodyfatscale.WHR
                + '</p>'
                + '<p>BMI：'
                + data.Bodyfatscale.BMI
                + '</p>'
                + '<p>去脂体重：'
                + data.Bodyfatscale.fatFreeWeight
                + ' Kg</p>'
                + '<p>人体水分：'
                + data.Bodyfatscale.moisture
                + ' %</p>'
                + '<p>人体肌肉：'
                + data.Bodyfatscale.muscle
                + ' %</p>'
                + '<p>骨骼重量：'
                + data.Bodyfatscale.boneWeight
                + ' Kg</p>'
                + '<p>基础代谢：'
                + data.Bodyfatscale.baseMetabolism
                + ' Kcal</p>'
                + '<p>内脏脂肪：'
                + data.Bodyfatscale.visceralFat
                + '</p>'
                + '</td><td>'
                + (new Date(data.Bodyfatscale.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 体脂秤
            $tbody.append($tiZhiCheng);
        }

        // 判断数据是否为空
        if ($tbody.children().length == 0) {
            var $noData = jQuery("<tr><td colspan='3'>暂时没有数据</td></tr>");
            $tbody.append($noData);
        } else {
            var $title = jQuery('<tr><td width="20%">类型</td><td width="50%">测量值</td><td width="30%">检测时间</td></tr>');
            $tbody.prepend($title);
        }

        return $tbody;
    },
    switchHealthData: function (userId, date, variationValue) {
        // 切换日期
        var newDate = new Date(String(date));
        newDate.setDate(newDate.getDate() + variationValue);

        if (newDate.isAfter(new Date())) {
            layer.msg("很抱歉，无法提供未来的数据");
            return;
        }

        var newDateStr = newDate.Format("yyyy-MM-dd");

        var key = userId + "," + newDateStr;

        // 移除
        var $root = jQuery("div.layui-layer-content");
        $root.empty();

        if (!this.healthDataCache.hasOwnProperty(key)) {
            jQuery
                .ajax({
                    type: 'GET',
                    url: 'orgServeControl.do?getMemberHealthDataByMeasureDate&userId='
                    + userId + '&measureDate=' + newDateStr,
                    beforeSend: function () {
                        layer.load(1);
                    },
                    success: function (result) {
                        // 获取新的dialog
                        var $dialog = dialogUtils.createHealthData(userId,
                            newDateStr, result.attributes);

                        $root.append($dialog);

                        // 将数据存放到缓存中
                        dialogUtils.healthDataCache[key] = result.attributes;
                    },
                    complete: function () {
                        layer.closeAll("loading");
                    }
                });
        } else {
            // 获取新的dialog
            var $dialog = dialogUtils.createHealthData(userId, newDateStr,
                dialogUtils.healthDataCache[key]);

            $root.append($dialog);
        }
    },
    createAddGroup: function (data, isEdit, servers) {// 创建添加或编辑群组的对话框
        console.log(servers);
        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        var groupNameSet = document.createElement("div");
        $(groupNameSet).addClass("groupName_set");
        isEdit != undefined && isEdit ? $(groupNameSet).html(
            '<label>群组名称：</label><input id="groupName_input" type="text" value="'
            + $(".group_name h2").text()
            + '" placeholder="请输入群组名称"/><a href="javascript:groupControl.showMoveDialog('+$("#groupNumber").text()+')" style="position: absolute;right: 50px">删除群组</a>')
            : $(groupNameSet)
            .html(
                '<label>群组名称：</label><input id="groupName_input" type="text" placeholder="请输入群组名称"/>');

        var addTable = document.createElement("table");
        var tableDiv = document.createElement("div");
        $(tableDiv).attr("style", "max-height: 300px;overflow-y:auto");
        $(addTable).attr({
            "class": "service_table",
            "cellpadding": "0",
            "cellspacing": "0",
        });
        var groupStr = '<tr><td>选择框</td><td>服务师</td><td>管理群数</td></tr>';// 服务师拼接字符串
        if (data.length == 0) {
            groupStr += '<tr><td colspan="3">该门店还没有服务师</td></tr>'
        } else {
            for (var index in data) {
                var item = data[index];
                groupStr += '<tr><td style="position:relative">';
                if (servers != undefined) {
                    var has = false;
                    for (var i in servers) {
                        var s = servers[i];
                        if (s.id == item.id) {
                            has = true;
                            break;
                        }
                    }
                    has ? groupStr += '<input type="checkbox" value=' + item.id
                        + ' name="groupSelect" checked/>'
                        : groupStr += '<input type="checkbox" value='
                        + item.id + ' name="groupSelect"/>';
                } else {
                    groupStr += '<input type="checkbox" value=' + item.id
                        + ' name="groupSelect"/>';
                }
                groupStr += '</td><td>' + item.realName + '</td><td>'
                    + item.groupCount + '</td></tr>';
            }
        }
        $(addTable).html($(addTable).html() + groupStr);
        $(addTable).appendTo($(tableDiv));
        var notice = document.createElement("div");
        $(notice).attr("style",
            "padding:15px 15px 0;font-size:16px;color:#6c6c6c");
        $(notice).html('创建后方便管理用户，可调动服务师管理不同用户群');
        dialogContainer.appendChild(groupNameSet);
        dialogContainer.appendChild(tableDiv);
        dialogContainer.appendChild(notice);
        $(dialogContainer).width(550);
        document.body.appendChild(dialogContainer);
        customRadio.init({
            name: 'groupSelect',
            color: 'blue'
        });
        return dialogContainer;
    },
    createGroupQrcode: function (url, data) {// 创建群组二维码对话框,url为扫描二维码后的链接方向
        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        $(dialogContainer)
            .attr("style",
                "text-align:center;line-height:35px;font-size:16px;width:350px");
        var qrCodeDiv = document.createElement("div");
        $(qrCodeDiv).attr({
            "style": "display:inline-block;margin-top:10px",
            "id": "qrcode"
        });
        dialogContainer.appendChild(qrCodeDiv);
        $(dialogContainer)
            .html(
                $(dialogContainer).html()
                + '<p style="font-weight:bold;font-size:20px;margin-top:15px">'
                + data + '</p><p>扫一扫加入该群组</p>');
        document.body.appendChild(dialogContainer);
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width: 150,
            height: 150
        });
        qrcode.makeCode('{"serveGroupId":"'+url+'"}');
        return dialogContainer;
    },
    peopleInfo: {// 创建个人信息对话框
        basicMessageCache: {}, // 用户基本信息缓存
        sickRecordCache: {}, // 病历缓存
        testReportCache: {}, // 体检缓存
        dietRecordCache: {}, // 饮食缓存
        switchDialog: function (obj,chargeMode) {// 切换对应位置的内容
            $(obj).addClass("action");
            $(obj).siblings("label").removeClass("action");
            var target = $.trim($(obj).text());
            $(obj).parent().siblings("div").remove();

            var userId = jQuery("[name='labelUserId']").val();
            var date = new Date().Format("yyyy-MM-dd");

            switch (target) {
                case "基本信息":
                    var groupId = jQuery("#groupNumber").text();
                    groupId = jQuery('.message-title').attr('data-group-id');
                    this.createBaseInfo(userId, groupId,chargeMode);
                    break;
                case "体检报告":
                    this.createTestReport(userId, date);
                    break;
                case "病历记录":
                    this.createSickRecord(userId, date);
                    break;
                case "饮食记录":
                    this.createDietRecord(userId, date);
                    break;
            }
        },
        createPersonInfo: function (userId,mode) {
            var outContain = this.createNav(userId,mode);
            // var container = this.createBaseInfo();
            // outContain.appendChild(container);
            document.body.appendChild(outContain);
            return outContain;
        },
        createNav: function (userId,mode) {
            var outContain = document.createElement("div");
            $(outContain).addClass("person_dialog_contain");
            var title = document.createElement("div");
            $(title).addClass("right_title");
            $(title)
                .html(
                    '<label class="action"onclick="dialogUtils.peopleInfo.switchDialog(this,'+mode+')"> 基本信息 </label> <label onclick="dialogUtils.peopleInfo.switchDialog(this)"> 体检报告 </label> <label onclick="dialogUtils.peopleInfo.switchDialog(this)"> 病历记录 </label> <label onclick="dialogUtils.peopleInfo.switchDialog(this)"> 饮食记录 </label>');
            var $userId = jQuery("<input type='hidden' name='labelUserId' value='"
                + userId + "'>");
            $(title).append($userId);
            outContain.appendChild(title);
            return outContain;
        },
        createDietRecord: function (userId, recordDate) {// 创建饮食记录内容的菜单栏

            var key = userId + "," + recordDate;
            var $root = jQuery("div.person_dialog_contain");

            if (!this.dietRecordCache.hasOwnProperty(key)) {
                jQuery
                    .ajax({
                        type: 'GET',
                        url: 'orgServeControl.do?getMemberDietDataByRecordDate&userId='
                        + userId + '&recordDate=' + recordDate,
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function (result) {

                            var body = dialogUtils.peopleInfo
                                .createDietRecordElement(result.obj,
                                    recordDate, userId);
                            $(body).appendTo($root);

                            dialogUtils.peopleInfo.dietRecordCache[key] = result.obj;
                        },
                        complete: function () {
                            layer.closeAll("loading");
                        }
                    });
            } else {
                var body = dialogUtils.peopleInfo.createDietRecordElement(
                    this.dietRecordCache[key], recordDate, userId);
                $(body).appendTo($root);
            }
        },
        createDietRecordElement: function (datas, recordDate, userId) {
            jQuery("div.diet_container").remove();
            var container = document.createElement("div");
            $(container).addClass("diet_container");
            var dateContain = document.createElement("div");
            $(dateContain).addClass("date_contain");
            var pre = document.createElement("a");
            pre.href = "javascript:dialogUtils.peopleInfo.switchDietRecordDate("
                + userId + ", \'" + recordDate + "\', -1)";
            $(pre).addClass("pre_v");
            $(pre).html('<img src="static/images/pre_v.png" />');
            var next = document.createElement("a");
            next.href = "javascript:dialogUtils.peopleInfo.switchDietRecordDate("
                + userId + ", \'" + recordDate + "\', 1)";
            $(next).addClass("next_d");
            $(next).html('<img src="static/images/pre_v.png" />');
            var dateTime = document.createElement("span");
            $(dateTime).text(recordDate);
            dateContain.appendChild(pre);
            dateContain.appendChild(next);
            dateContain.appendChild(dateTime);
            var dietList = document.createElement("ul");
            $(dietList).addClass("diet_record_list");
            var li = document.createElement("li");
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {// json数据添加在这里
                    var listr = '<p class="timeDiet"><span class="spot"></span>'
                        + datas[i].dietType
                        + '&nbsp;&nbsp;&nbsp;&nbsp;能量：'
                        + datas[i].energy + '</p><p class="food">';
                    if (datas[i].dietDetails.length > 0) {
                        for (var j = 0; j < datas[i].dietDetails.length; j++) {
                            listr += datas[i].dietDetails[j] + "、";
                        }
                        listr = listr.substring(0, listr.length - 1);
                    }
                    listr += "</p>";
                    $(li).html(listr);
                }
            } else {
                $(li)
                    .html(
                        '<p class="timeDiet" style="text-align:center;">暂时没有数据</p>');
            }
            dietList.appendChild(li);
            container.appendChild(dateContain);
            container.appendChild(dietList);
            return container;
        },
        switchDietRecordDate: function (userId, date, variationValue) {
            // 切换日期
            var newDate = new Date(String(date));
            newDate.setDate(newDate.getDate() + variationValue);
            if (newDate.isAfter(new Date())) {
                layer.msg("很抱歉，无法提供未来的数据");
                return;
            }
            var newDateStr = newDate.Format("yyyy-MM-dd");
            this.createDietRecord(userId, newDateStr);
        },
        createTestReport: function (userId, physicalsDate) {// 创建体检报告的菜单栏
            var key = userId + "," + physicalsDate;
            var $root = jQuery("div.person_dialog_contain");
            if (!this.testReportCache.hasOwnProperty(key)) {
                jQuery
                    .ajax({
                        type: 'GET',
                        url: 'orgServeControl.do?getMemberPhysicalDataByPhysicalsDate&userId='
                        + userId
                        + '&physicalsDate='
                        + physicalsDate,
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function (result) {
                            var body = dialogUtils.peopleInfo
                                .createTestReportElemet(result.obj,
                                    physicalsDate, userId);
                            $(body).appendTo($root);
                            // 保存到缓存中
                            dialogUtils.peopleInfo.testReportCache[key] = result.obj;
                        },
                        complete: function () {
                            layer.closeAll("loading");
                        }
                    });
            } else {
                var body = this.createTestReportElemet(
                    this.testReportCache[key], physicalsDate, userId);
                $(body).appendTo($root);
            }
        },
        createTestReportElemet: function (datas, physicalsDate, userId) {
            jQuery("div.testReport_container").remove();
            var dateContain = document.createElement("div");
            $(dateContain).addClass("date_contain");
            var pre = document.createElement("a");
            pre.href = "javascript:dialogUtils.peopleInfo.switchTestReportPhysicalsDate("
                + userId + ", \'" + physicalsDate + "\', -1)";
            $(pre).addClass("pre_v");
            $(pre).html('<img src="static/images/pre_v.png" />');
            var next = document.createElement("a");
            next.href = "javascript:dialogUtils.peopleInfo.switchTestReportPhysicalsDate("
                + userId + ", \'" + physicalsDate + "\', 1)";
            $(next).addClass("next_d");
            $(next).html('<img src="static/images/pre_v.png" />');
            var dateTime = document.createElement("span");
            $(dateTime).text(physicalsDate);
            dateContain.appendChild(pre);
            dateContain.appendChild(next);
            dateContain.appendChild(dateTime);
            var container = document.createElement("div");
            $(container).addClass("testReport_container");
            var reportList = document.createElement("ul");
            $(reportList).addClass("test_report_list");

            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var item = document.createElement("li");
                    $(item).addClass("report_item");
                    $(item).html(
                        '<span class="report_date">' + datas[i].title
                        + '</span>' + datas[i].physicalsOrg
                        + '<p class="remark">备注：'
                        + datas[i].description + '</p>');
                    if (datas[i].photos.length > 0) {// 如果有图片
                        var imgSet = document.createElement("ul");
                        $(imgSet).addClass("img_set");
                        var imgStr = "";// 图片html拼接
                        for (var j = 0; j < datas[i].photos.length; j++) {// 如果有图片在这里进行添加
                            imgStr += '<li><img src="' + datas[i].photos[j]
                                + '" /></li>';
                        }
                        $(imgSet).html(imgStr);
                        item.appendChild(imgSet);
                    }
                    reportList.appendChild(item);
                }
            } else {
                var item = document.createElement("li");
                $(item).addClass("report_item");
                $(item).css({
                    "text-align": "center"
                });
                $(item).html('<span>暂时没有数据</span>');
                reportList.appendChild(item);
            }
            container.appendChild(dateContain);
            container.appendChild(reportList);
            return container;
        },
        switchTestReportPhysicalsDate: function (userId, date, variationValue) {
            // 切换日期
            var newDate = new Date(String(date));
            newDate.setDate(newDate.getDate() + variationValue);
            if (newDate.isAfter(new Date())) {
                layer.msg("很抱歉，无法提供未来的数据");
                return;
            }
            var newDateStr = newDate.Format("yyyy-MM-dd");
            this.createTestReport(userId, newDateStr);
        },
        createBaseInfo: function (userId, groupId,mode) {// 创建基本信息的内容

            var key = userId + "," + groupId;

            var $root = jQuery("div.person_dialog_contain");

            if (!this.basicMessageCache.hasOwnProperty(key+"-"+mode)) {
                jQuery.ajax({
                    type: 'GET',
                    url: 'orgServeControl.do?getMemberDetailMessage&userId='
                    + userId + "&groupId=" + groupId+"&mode="+mode,
                    beforeSend: function () {
                        layer.load(2);
                    },
                    success: function (result) {
                        var data = result.obj;
                        var body = dialogUtils.peopleInfo.createbaseMsg(data);
                        $(body).appendTo($root);
                        // 保存到缓存
                        dialogUtils.peopleInfo.basicMessageCache[key+"-"+mode] = data;
                    },
                    complete: function () {
                        layer.closeAll("loading");
                    }
                });
            } else {
                var data = this.basicMessageCache[key+"-"+mode];
                var body = this.createbaseMsg(data);
                $(body).appendTo($root);
            }

        },
        createbaseMsg: function (data) {

            jQuery("div.baseMsg").remove();

            var container = document.createElement("div");
            $(container).addClass("baseMsg");
            var imgDiv = document.createElement("div");
            $(imgDiv).addClass("userImg");
            $(imgDiv)
                .html(
                    '<img width="110" height="110" src="' + data.photo
                    + '" />');
            container.appendChild(imgDiv);
            var baseContain = document.createElement("div");
            $(baseContain).addClass("baseContain");
            var remainingDay = null;
            switch (parseInt(data.chargeMode)) {
                case 0:
                    if (data.endDate == undefined)
                        remainingDay = "剩余天数：无限制";
                    break;
                case 1:
                    remainingDay = "剩余次数：" + data.timesRemaining + "次";
                    break;
            }
            if (remainingDay == null) {
                remainingDay = -DateUtils.getInterval(data.endDate);
                remainingDay = remainingDay < 0 ? "剩余天数：<span style='color:#d14242'>已过期</span>"
                    : "剩余天数：<span style='color:#48c858;'>" + remainingDay
                + "天</span>";
            }
            var realName = data.realName == undefined ? "未填写" : data.realName;
            var birthday = data.birthday;
            birthday = birthday == undefined ? "未填写" : new Date(data.birthday)
                .Format("yyyy-MM-dd");
            var end=data.endDate==undefined?"无":new Date(data.endDate).Format("yyyy-MM-dd");
            $(baseContain).html(
                ' <h2>' + realName
                + '</h2><table class="msg_table"><tr><td>性别：'
                + (data.sex ? '男' : '女') + '</td><td>当前服务：'
                + data.currentServeName + '</td></tr><tr><td>出生日期：'
                + birthday + '</td><td>服务费用：' + data.consumeMode
                + '</td></tr><tr><td>分组：' + data.groupName
                + '</td><td>' + remainingDay
                + '</td></tr><tr><td>到期时间：'
                + end
                + '</td></tr></table>');

            container.appendChild(imgDiv);
            container.appendChild(baseContain);
            return container;
        },
        createSickRecord: function (userId, visitingDate) {// 创建病历记录内容的对话框
            var key = userId + "," + visitingDate;
            var $root = jQuery("div.person_dialog_contain");
            if (!this.sickRecordCache.hasOwnProperty(key)) {
                jQuery
                    .ajax({
                        type: 'GET',
                        url: 'orgServeControl.do?getMemberMedicalDataByVisitingDate&userId='
                        + userId + "&visitingDate=" + visitingDate,
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function (result) {
                            var body = dialogUtils.peopleInfo
                                .createSickRecordElement(result.obj,
                                    visitingDate, userId);
                            $(body).appendTo($root);

                            dialogUtils.peopleInfo.sickRecordCache[key] = result.obj;
                        },
                        complete: function () {
                            layer.closeAll("loading");
                        }
                    });
            } else {
                var body = dialogUtils.peopleInfo.createSickRecordElement(
                    this.sickRecordCache[key], visitingDate, userId);
                $(body).appendTo($root);
            }
        },
        createSickRecordElement: function (datas, visitingDate, userId) {
            jQuery("div.sick_container").remove();
            var dateContain = document.createElement("div");
            $(dateContain).addClass("date_contain");
            var pre = document.createElement("a");
            pre.href = "javascript:dialogUtils.peopleInfo.switchSickRecordVisitingDate("
                + userId + ", \'" + visitingDate + "\', -1)";
            $(pre).addClass("pre_v");
            $(pre).html('<img src="static/images/pre_v.png" />');
            var next = document.createElement("a");
            next.href = "javascript:dialogUtils.peopleInfo.switchSickRecordVisitingDate("
                + userId + ", \'" + visitingDate + "\', 1)";
            $(next).addClass("next_d");
            $(next).html('<img src="static/images/pre_v.png" />');
            var dateTime = document.createElement("span");
            $(dateTime).text(visitingDate);
            dateContain.appendChild(pre);
            dateContain.appendChild(next);
            dateContain.appendChild(dateTime);
            var container = document.createElement("div");
            $(container).addClass("sick_container");
            var sickList = document.createElement("ul");
            $(sickList).addClass("sick_record");
            var liStr = "";// 拼接html字符串用
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {// json数据添加在这里
                    // 添加一个图片是否为空的判断
                    liStr += '<li><img src="' + datas[i].photo
                        + '"/><div class="sick_data"><p>' + datas[i].title
                        + '</p><p>' + datas[i].name + '&nbsp;&nbsp;'
                        + datas[i].sex + '&nbsp;&nbsp;' + datas[i].age
                        + '岁</p><p>' + datas[i].basicCondition + '</p><p>'
                        + datas[i].visitingDate + '&nbsp;就诊</p></div></li>';
                }
            } else {
                liStr = '<li style="text-align:center;min-height:0px;">暂时没有数据</li>';
            }
            $(sickList).html(liStr);
            container.appendChild(dateContain);
            container.appendChild(sickList);
            return container;
        },
        switchSickRecordVisitingDate: function (userId, date, variationValue) {
            // 切换日期
            var newDate = new Date(String(date));
            newDate.setDate(newDate.getDate() + variationValue);
            if (newDate.isAfter(new Date())) {
                layer.msg("很抱歉，无法提供未来的数据");
                return;
            }
            var newDateStr = newDate.Format("yyyy-MM-dd");
            this.createSickRecord(userId, newDateStr);
        }
    }
}


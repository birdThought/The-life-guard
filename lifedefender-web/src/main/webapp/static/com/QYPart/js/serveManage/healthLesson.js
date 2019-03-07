/**
 * 健康课堂列表界面
 * 功能：显示健康课堂列表
 *     添加健康课堂
 *     删除健康课堂
 */

/** Model集合 */
var ModelList = {
		/** 课堂Model */
		lessonModel: null,
		/** 获取课堂Model */
		getLessonModel: function () {
			if (this.lessonModel == null) {
				this.lessonModel = new Vue({
					el: '#lessonModel',
					data: {
						results: null,
						checkedLessonweek: null,
						checkedLessonTime: null,
						checkedTime: null,
						checkedLessonName: null,
						checkedLessonDescrepion: null,
						lessonTimeDTO: null,
						userId: null
					},
					methods: {
						/** 添加课堂-- 弹出添加框，提交添加信息到后台 */
						addLesson: function () {
							var vue = this;
							layer.open({
								type: 1,
								title:['创建课堂','text-align:center;font-size:16px;background:#fff;'],
								area:'520px',
								btn:['确定','取消'],
								zIndex: 9999,
								scrolling:'no',
								scrollbar:false,
								success : function() {
								},
					  			content: $('.add_lesson_dialog_container'),
					  			yes: function(){
					  				var name = vue.checkedLessonName;
									var description = vue.checkedLessonDescrepion;
									var relativePath = $("[name='img_hidden']").val();
									/*var lessonTimeDTO = new Array();
									lessonTimeDTO.push(lessonTime);*/
									if (vue.lessonTimeDTO == null) {
										layer.msg('请确定开课时间');
										return;
									}
									if (name == null) {
										layer.msg('请确定课堂名称');
										return;
									}
									if (description.length > 250) {
										layer.msg('课堂描述字数超出限制');
										return;
									}
									var data = {
											"name": name,
											"description": description,
											"lessonTimeDTO": vue.lessonTimeDTO,
											"relativePath": relativePath
									}
									$.ajax({
										async : true,
										cache : false,
										type: 'POST',
										contentType: 'application/json; charset=utf-8',
										url: 'orgServeControl.do?addHealthLesson',
										data: JSON.stringify(data),
										before: function(){
											layer.load(1);
										},
										success: function(result){
											layer.closeAll();
											window.location.reload();	//暂时这样实现，之后采用将新lesson加到vue的results里 --3/1
										}
									});
					  			}
							});
							$('.layui-layer-content').attr("style","min-height:300px;");//使弹出层里的按钮增加节点不会显示滚动条
						},
						/** 进入课堂 */
						interLesson: function (groupId, index) {
						    var bool = false;
							var len = this.results[index].orgUsers.length;
							for (var i = 0; i < len; i ++) {
								if (this.userId == this.results[index].orgUsers[i].id) {
									bool = true;
									break;
								}
							}
							if (!bool) {
							 layer.msg('你不在此群，无法进入!');
							 return;
							}
							window.open("orgServeControl.do?healthLessonChat&groupId="+groupId);
						},
						/** 添加课堂时间 */
						addLessonTime: function () {
							
							var weeks = '';
							for(var i=1; i<= 7; i++){
								if($(".add_lesson_dialog_container input[type='checkbox'][value='" + i + "']").is(":checked")){
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
							var lessonTime = {
									weeks: this.checkedLessonweek,
									startTime: this.checkedLessonTime
							};
							this.lessonTimeDTO.push(lessonTime);
						},
						/** 删除课堂 */
						deleteLesson: function (id) {
							layer.confirm('确定删除课堂?', {icon: 2, title:'提示'}, function(index){
								var data = {"id" : id};
								$.ajax({
									async : true,
									cache : false,
									type: 'POST',
									contentType: 'application/x-www-form-urlencoded',
									url: 'orgServeControl.do?deleteHealthLesson',
									data: data,
									before: function(){
										layer.load(1);
									},
									success: function(result){
										layer.close(index);
										window.location.reload();	//暂时这样实现，之后采用将新lesson加到vue的results里 --3/1
									}
								});
							});
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
						/** 获取课堂列表“进入课堂”按钮样式 */
						getBtnCss: function (index) {
							var bool = false;
							var len = this.results[index].orgUsers.length;
							for (var i = 0; i < len; i ++) {
								if (this.userId == this.results[index].orgUsers[i].id) {
									bool = true;
									break;
								}
							}
							return bool == true ? '' : "background-color: #908e8b;";
						}
					},
					computed: {
						
					}
				});
			}
			return this.lessonModel;
		}
}

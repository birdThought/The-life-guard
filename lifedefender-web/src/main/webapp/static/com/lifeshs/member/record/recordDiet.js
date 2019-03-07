/*
 * 
 * 饮食
 * 
 * DateObject:时间操作类
 * 饮食页面、运动页面重构已完成 2017-2-24
 * */

'use strict';
/*全局变量定义*/
var nowDate = new Date();  //当前系统时间
var listData;
Vue.filter('formatDate', function (value, format) {
	return new Date(value).Format(format);
})
/**页面模型*/
var vueModel = {
		/**饮食列表Model*/
		dietModel: null,
		/**运动列表Model*/
		sportsModel: null,
		/** 获取饮食Model实例*/
		getDietModel: function () {
			if (this.dietModel == null) {
				this.dietModel = new Vue({
					el: '.dietListModel',
					data: {
						results: null,
						details: null,	//存放饮食的详细食物列表
						dietTime: null,	//饮食时间
						dietType: null,	//饮食类型
						dietId: null,	//饮食ID
						foodKindList: null, //食物种类列表
						foodList: null, //根据种类获取的食物集合--存有ID,image,KCAL,NAME等属性
						selectedFoodKind: null, //已选择食物种类名称
						selectedFoodName: null,
						selectedFood: null,	//已选择的食物（含有id和image属性）
						selectedWeight: 100,
						selectedDietTime: '09:00:00',
						selectedDietType: '早餐',
					},
					methods: {
						/**初始化添加饮食(弹出添加框,并将数据传输到后台)*/
						addDiet: function () {
							this.details = null;
							//获取食物种类
							this.getFoodKind();
							
							layer.open({
								type: 1,
								title:['添加饮食','text-align:center;font-size:16px;background:#fff;height:50px;line-height:50px'],
								area:['770px','auto'],
								btn:['确定','取消'],
								moveType: 1,
								offset:'100px',
								scrollbar: false,
					  			content: $('.dialog_container'),
					  			yes: function(){
//					  				FoodAddObject.getAddDietList();       //等待修改
					  				var energy = 0;
									for (var i = 0; i < vueModel.getDietModel().details.length; i ++) {
										energy += vueModel.getDietModel().details[i].kcal;
									}
									var date = $('.selectDate').text();
									var _data = {
											recordDate: date,
											dietTime: vueModel.getDietModel().selectedDietTime,
											dietType: vueModel.getDietModel().selectedDietType,
											energy: energy,
											details: vueModel.getDietModel().details
									};
									$.ajax({
										async : true,
										cache : false,
										type: 'POST',
										contentType: 'application/json; charset=utf-8',
										url: 'recordDietControl.do?addDietByUserIdWithDate',
										data: JSON.stringify(_data),
										before: function(){
											layer.load(1);
										},
										success: function(result){
											layer.msg(result.msg);
											setTimeout(function(){
								            layer.closeAll();
											}, 1000);
											getDietList();   //添加成功后调用获取饮食列表函数，刷新列表
											//加载能量图表
					 						getDietEnergy(function(energy, date){
					 							getDietEnergySheet(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray);
					 							//加载圆环
					 							loadEnergyChart(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray, new Date($('.selectDate').text()).Format('MM/dd'));
					 						});
					 						
					 						//能量分析
					 						recordSport.energyAnalyze();
										}
									});
					  			}
							});
							$('.layui-layer-content').attr("style","min-height:300px;");//使弹出层里的按钮增加节点不会显示滚动条
						},
						/**编辑饮食(弹出编辑框,并将数据传输到后台)*/
						editDiet: function (details, dietTime, dietType, dietId) {
							this.dietId = dietId;
							this.details = details;
							this.dietTime = new Date(dietTime).Format('hh:mm:ss');
							this.dietType = dietType;
							this.getFoodKind();
//							var _data = {"dietId": dietId ,"details": this.details, "dietTime": dietTime, "dietType": dietType};
							
							layer.open({
								type: 1,
								title:['修改饮食','text-align:center;font-size:16px;background:#fff;height:50px;line-height:50px'],
								area:['770px','auto'],
								btn:['确定','取消'],
								offset:'100px',
								moveType: 1,
								scrollbar: false,
					  			content: $('.dialog_container_modify'),
					  			yes: function(){
					  				var energy = 0;
									for (var i = 0; i < vueModel.getDietModel().details.length; i ++) {
										energy += vueModel.getDietModel().details[i].kcal;
									}
									var diet = {
										id: dietId,
										dietTime: dietTime,
										dietType: dietType,
										energy: energy
									}
									var _data = {};
									_data.recordDiet = diet;
									_data.recordDietFoods = vueModel.getDietModel().details;
									
//					  				ajax请求更新饮食
					  				$.ajax({
					  					async : true,
					  					cache : false,
					  					type: 'POST',
					  					contentType: 'application/json; charset=utf-8',
					  					url: 'recordDietControl.do?modifyDiet',
					  					data:JSON.stringify(_data),
					  					before: function(){
					  						layer.load(1);
					  					},
					  					success: function(result){
					  						layer.msg(result.msg);
					  						setTimeout(function(){
					  			            layer.closeAll();
					  						}, 500);
					  						
					  						getDietList();   //添加成功后调用获取饮食列表函数，刷新列表
											//加载能量图表
					 						getDietEnergy(function(energy, date){
					 							getDietEnergySheet(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray);
					 							//加载圆环
						 						loadEnergyChart(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray, new Date($('.selectDate').text()).Format('MM/dd'));
					 						});
					 						
					 						//能量分析
					 						recordSport.energyAnalyze();
					  					}
					  				});
					  			},
					  			cancel:function(){
					  				layer.msg('饮食记录未保存！');
					  				getDietList();   //添加成功后调用获取饮食列表函数，刷新列表
								/* while(foodList.hasChildNodes()) //当div下还存在子节点时 循环继续
								    {
								    	foodList.removeChild(foodList.firstChild);
								    }*/
					  			}
							});
							$('.layui-layer-content').attr("style","min-height:300px;");//使弹出层里的按钮增加节点不会显示滚动条
						
						},
						/** 获取所有食物种类 */
						getFoodKind: function () {
							$.ajax({
								async : true,
								cache : false,
								type: 'POST',
								contentType: 'application/json; charset=utf-8',
								url : "recordDietControl.do?selectAllFoodKind",
								data :'{"kind":""}',
								success : function(data) {
									vueModel.getDietModel().foodKindList = data.obj;
									vueModel.getDietModel().selectedFoodKind = data.obj[0].name;
								}
							});
						},
						/** 编辑--添加食物按钮 */
						editToAddFood: function () {
							var foodWeight,
								foodId,
								image,
								kcal,
								kind,
								name;
							var vue = vueModel.getDietModel();
							for (var i = 0; i < vue.foodList.length; i ++) {
								if (vue.foodList[i].NAME == vue.selectedFoodName) {
									foodWeight = vue.selectedWeight,
									foodId = vue.foodList[i].ID;
									image = vue.foodList[i].image;
									kcal = vue.foodList[i].KCAL * vue.selectedWeight;
									kind = vue.selectedFoodKind;
									name = vue.foodList[i].NAME;
								}
							}
							var obj = {
									foodWeight: foodWeight,
									foodId: foodId,
									image: image,
									kcal: kcal,
									kind: kind,
									name: name
							}
							//判断是否是同一样食物
							var bool = true;
							for (var i = 0; i < vue.details.length; i ++) {
								if (obj.foodId == vue.details[i].foodId) {
									vue.details[i].foodWeight += obj.foodWeight;
									vue.details[i].kcal += obj.kcal;
									bool = false;
									break;
								}
							}
							if (bool) {
								vue.details.push(obj);
							}
						}
						,
						/** 添加--添加食物按钮 */
						addToAddFood: function () {
							var foodWeight,
								foodId,
								image,
								kcal,
								kind,
								name;
							var vue = vueModel.getDietModel();
							for (var i = 0; i < vue.foodList.length; i ++) {
								if (vue.foodList[i].NAME == vue.selectedFoodName) {
									foodWeight = vue.selectedWeight,
									foodId = vue.foodList[i].ID;
									image = vue.foodList[i].image;
									kcal = vue.foodList[i].KCAL * vue.selectedWeight;
									kind = vue.selectedFoodKind;
									name = vue.foodList[i].NAME;
								}
							}
							var obj = {
									foodWeight: foodWeight,
									foodId: foodId,
									image: image,
									kcal: kcal,
									kind: kind,
									name: name
							}
							if (this.details == null) {
								this.details = new Array();
							}
							//判断是否是同一样食物
							var bool = true;
							for (var i = 0; i < this.details.length; i ++) {
								if (obj.foodId == this.details[i].foodId) {
									this.details[i].foodWeight += obj.foodWeight;
									this.details[i].kcal += obj.kcal;
									bool = false;
									break;
								}
							}
							if (bool) {
								this.details.push(obj);
							}
						},
						/** 添加--删除食物按钮 */
						deleteFoodItem: function (index) {
							if (this.details != null) {
								this.details.splice(index, 1);
							}
						} 
					},
					computed: {
					},
					watch: {
						/** 监控selectedFoodKind属性，渲染完成时执行方法 */
						selectedFoodKind: function () {
							var name = this.selectedFoodKind;
							$.ajax({
								async : true,
								cache : false,
								type: 'POST',
								contentType: 'application/json; charset=utf-8',
								url : "recordDietControl.do?selectFoodByKind",
								data :'{"kind":"' + name + '"}',
								success : function(data) {
									vueModel.getDietModel().foodList = data.obj;
									
									vueModel.getDietModel().selectedFoodName = data.obj[0].NAME;
								}
							});
						}
					}
				});
			}
			return this.dietModel;
		},
		/**获取运动Model实例*/
		getSportsModel: function () {
			if (this.sportsModel == null) {
				this.sportsModel = new Vue({
					el: ".sportsModel",
					data: {
						results: null,
						details: null,
						sportKindList: null,
						sportNameList: null,
						sportList: null,
						selectedSportKind: null,
						selectedSportName: null,
						selectedStartTime: '09:00:00',
						selectedDurationTime: 100,
					},
					methods: {
						/** 添加运动--弹出添加运动窗口，并将数据传输到后台 */
						addSports: function () {
							this.details = null;
							this.getSportKindAndName();
							layer.open({
								type: 1,
								moveType: 1,
								title:['添加运动','text-align:center;font-size:16px;background:#fff;height:50px;line-height:50px'],
								area:['770px','auto'],
								btn:['确定','取消'],
								offset:'100px',
								scrollbar: false,
					  			content: $('.dialog_container_sport'),
					  			yes: function(){
					  				if (vueModel.getSportsModel().details == null) {
					  					layer.msg('至少添加一项运动!');
					  					return;
					  				} 
					  				if (vueModel.getSportsModel().details.length == 0) {
					  					layer.msg('至少添加一项运动!');
					  					return;
					  				}
				  					var recordDate = $('.selectDate').text();
				  					var sportTime = vueModel.getSportsModel().selectedStartTime;
				  					var details = vueModel.getSportsModel().details;
				  					var _data = '{"recordDate":"'+ recordDate +'", "sportTime":"'+ sportTime+'", "sportItem":'+ JSON.stringify(details) +'}';
				  					$.ajax({
				  						async : true,
				  						cache : false,
				  						type: 'POST',
				  						contentType: 'application/json; charset=utf-8',
				  						url: 'recordDietControl.do?addSport',
				  						data: _data,
				  						before: function(){
				  							layer.load(1);
				  						},
				  						success: function(result){
				  							layer.msg(result.msg);
				  							setTimeout(function(){
				  				            layer.closeAll();
				  							}, 1000);
				  							recordSport.getSportWithDate();   //添加成功后调用获取运动列表函数，刷新列表*/
				  							//获取运动能量
				  							recordSport.getSportEnergy();
				  							//加载能量图表
				  							getDietEnergy(function(energy, date){
				  								getDietEnergySheet(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray);
				  							});
				  							//加载圆环
				  							loadEnergyChart(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray, new Date($('.selectDate').text()).Format('MM/dd'));
				  							//能量分析
				  							recordSport.energyAnalyze();
				  						}
				  					});
			  					}
							});
							$('.layui-layer-content').attr("style","min-height:300px;");//使弹出层里的按钮增加节点不会显示滚动条
						},
						/** 编辑运动--弹出编辑运动窗口，并将数据传输到后台 */
						editSport: function (index) {
							this.getSportKindAndName();
							this.details = this.results[index].details;
							let vue = vueModel.getSportsModel();
							layer.open({
								type: 1,
								moveType: 1,
								title:['修改运动','text-align:center;font-size:16px;background:#fff;height:50px;line-height:50px'],
								area:['770px','auto'],
								btn:['确定','取消'],
								offset:'100px',
								scrollbar: false,
					  			content: $('.dialog_container_sport'),
					  			yes: function(){
					  				if (vueModel.getSportsModel().details == null) {
					  					layer.msg('至少添加一项运动!');
					  					return;
					  				}
					  				if (vueModel.getSportsModel().details.length == 0) {
					  					layer.msg('至少添加一项运动!');
					  					return;
					  				}
					  				var startTime = vue.selectedStartTime;
					  				startTime = 3600000;
					  				var energy = 0;
					  				var id = vue.results[index].id;
					  				//计算能量
					  				for (var i = 0; i < vue.details.length; i ++) {
					  					for (var j = 0; j < vue.sportList.length; j ++) {
				  							if (vue.sportList[j].id == vue.details[i].sportId) {
				  								energy += vue.details[i].duration * vue.sportList[j].kcal;
				  								break;
				  							}
				  						}
									} 
					  				var recordSport = {
					  						id: id,
					  						energy: energy,
					  						startTime: startTime
					  						
					  				}
					  				var details = vue.details;
					  				for (var i = 0; i < details.length; i ++) {
					  					if (details[i].detailId != null) {
					  						details[i].id = details[i].detailId;
					  					}
					  				}
					  				var data = {};
					  				data.recordSport = recordSport;
					  				data.details = details;
									$.ajax({
										async : true,
										cache : false,
										type: 'POST',
										contentType: 'application/json; charset=utf-8',
										url: 'recordDietControl.do?modifySport',
										data: JSON.stringify(data),
										before: function(){
											layer.load(1);
										},
										success: function(result){
											layer.msg(result.msg);
											setTimeout(function(){
								            layer.closeAll();
											}, 1000);
											recordSport.getSportWithDate();   //添加成功后调用获取运动列表函数，刷新列表*/
											//获取运动能量
											recordSport.getSportEnergy();
											//加载能量图表
											getDietEnergy(function(energy, date){
												getDietEnergySheet(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray);
											});
											//加载圆环
											loadEnergyChart(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray, new Date($('.selectDate').text()).Format('MM/dd'));
											//能量分析
											recordSport.energyAnalyze();
										}
									});
					  			},
					  			cancel: function () {
				  					recordSport.getSportWithDate();
				  					layer.msg('运动记录未保存!');
				  				}
							});
							$('.layui-layer-content').attr("style","min-height:300px;");//使弹出层里的按钮增加节点不会显示滚动条
						},
						/** 获取全部运动类型和名称 */
						getSportKindAndName: function () {
							$.ajax({
								async : true,
								cache : false,
								type: 'POST',
								contentType: 'application/json; charset=utf-8',
								url: 'recordDietControl.do?getAllSports',
								data:'{"extra":"extra"}',
								before: function(){	
									layer.load(1);
								},
								success: function(result){
									if (vueModel.getSportsModel().sportList == null) {
										vueModel.getSportsModel().sportList = new Array();
									}
									if (vueModel.getSportsModel().sportKindList == null) {
										vueModel.getSportsModel().sportKindList = new Array();
									}
									vueModel.getSportsModel().sportKindList = result.obj;
									vueModel.getSportsModel().selectedSportKind = result.obj[0].name;
								}
							});
						},
						/** 编辑、添加--添加运动按钮 */
						editToAddSport: function () {
							var 
								sportId,
								sportName,
								duration;
							var vue = vueModel.getSportsModel();
							for (var i = 0; i < vue.sportList.length; i ++) {
								if (vue.sportList[i].name == vue.selectedSportName) {
									sportId = vue.sportList[i].id;
								}
							}
							sportName = vue.selectedSportName;
							duration = vue.selectedDurationTime
							var obj = {
									sportId: sportId,
									sportName: sportName,
									duration: duration
							}
							if (vue.details == null) {
								vue.details = new Array();
								vue.details.push(obj);
							} else {
								//判断是否是同一样运动
								var bool = true;
								for (var i = 0; i < vue.details.length; i ++) {
									if (obj.sportId == vue.details[i].sportId) {
										vue.details[i].duration += obj.duration;
										bool = false;
										break;
									}
								}
								if (bool) {
									vue.details.push(obj);
								}
							}
						},
						deleteSport: function (index) {
							var recordSportId = this.results[index].id;
							layer.confirm("你确定要删除吗？", function(index) {
								recordSport.delSport(recordSportId);
								layer.close(index);
							});	
						},
						/** 添加、编辑--删除运动按钮 */
						deleteSportItem: function (index) {
							if (this.details != null) {
								this.details.splice(index, 1);
							}
						} 
					},
					computed: {
					},
					watch: {
						selectedSportKind: function () {
							for (var i = 0; i < this.sportKindList.length; i ++) {
								if (this.sportKindList[i].name == this.selectedSportKind) {
									this.sportList = this.sportKindList[i].sports;
								}
							}	
							this.selectedSportName = this.sportList[0].name;
						}
					}
				});
			}
			return this.sportsModel;
		}
}

//饮食添加类
var FoodAddObject={
		foodInitial: function() {
			//获取饮食能量
			getDietEnergy();
			//加载饮食列表
			getDietList();
		},
//		选择进餐时间后对应不同的时间段列表
		selectTime : function () {
			$('[id="selectType"]').empty();
			var time = $('[id="selectTime"]').val();
			var dietTypeFirst = null;
			var dietTypeLater = null;
			switch(time){
				case '09:00:00' :
					dietTypeFirst = '早餐';
					dietTypeLater = '早餐加餐';
					break;
				case '12:00:00' :
					dietTypeFirst = '午餐';
					dietTypeLater = '午餐加餐';
					break;
				case '18:00:00' :
					dietTypeFirst = '晚餐';
					dietTypeLater = '晚餐加餐';
					break;
			}
			$('[id="selectType"]').append("<option >"+ dietTypeFirst +"</option>");
			$('[id="selectType"]').append("<option >"+ dietTypeLater +"</option>");
			vueModel.getDietModel().selectedDietType = dietTypeFirst;
		}
}
/** 获取饮食列表 */
function getDietList(){
	var date = $('.selectDate').text();
	$.ajax({
			async : true,
			cache : false,
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded',
			url: 'recordDietControl.do?selectDietByUserIdWithDate',
			data: {"date": date},
			before: function(){
				layer.load(1);
			},
			success: function(result){
				if (result.success) {
					vueModel.getDietModel().results = result.obj;
				} else {
					vueModel.getDietModel().results = '';
				}
			}
		});
}
/**删除饮食*/
function removeDiet(obj) {
	layer.confirm("你确定要删除吗？", function(index) {
		var _data='{"id":"'+$(obj).parent().attr("value")+'"}'
		$.ajax({
			async : false,
			cache : false,
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			url: 'recordDietControl.do?delDietById',
			data:_data,
			before: function(){
				layer.load(1);
			},
			success: function(result){
				layer.msg(result.msg);
				setTimeout(function(){
	            layer.closeAll();
				}, 1000);
				getDietList();   //删除成功后调用获取饮食列表函数，刷新列表
				getDietEnergy(function(energy, date){
					getDietEnergySheet(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray);
					//加载圆环
					loadEnergyChart(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray, new Date($('.selectDate').text()).Format('MM/dd'));
				});
				//能量分析
				recordSport.energyAnalyze();
			}
		});
		layer.close(index)
	});
}

//饮食能量
var dietEnergyArray=new Array();
var dietDateArray=new Array();
//获取饮食能量表数据
function getDietEnergy(callback) {
	var date = $('.selectDate').text();
	$.ajax({
		async : true,
		cache : false,
		type: 'POST',
		contentType: 'application/json; charset=utf-8',
		url: 'recordDietControl.do?selectDietEnergyByUserId',
		data:'{"date":"'+ date +'"}',
		before: function(){	
			layer.load(1);
		},
		success: function(result){
			dietEnergyArray = [];
			dietDateArray = [];
			for (var i = 0; i <result.obj.length; i++) {
				dietEnergyArray.push(result.obj[i].energy);
				dietDateArray.push(new Date(result.obj[i].recordDate).Format("MM/dd"));
			}
			if(typeof callback == "function"){
				callback(dietEnergyArray, dietDateArray);
			}
			
		}
	});
}

/** 运动类 */
var recordSport = {  
	sportEnergyArray: new Array(),
	sportDateArray: new Array(),
	allSportArray: new Array(),	//二维数组    格式:[sportKind][sportName]
	sportRecord: new Array(),	//存放全部运动记录	格式:[{"details":[{"duration":100,"sportName":"快跑"}],"energy":1000,"id":123,"startTime":3600000}
	/*运动初始化*/
	initalSport: function(){
		//获取运动能量
		recordSport.getSportEnergy();
		//获取运动列表
		recordSport.getSportWithDate();
		//能量分析
		recordSport.energyAnalyze();
	},
	/*获取运动能量*/
	getSportEnergy: function(){
		var date = $('.selectDate').text();
		$.ajax({
			async : false,
			cache : false,
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			url: 'recordDietControl.do?selectSportEnergyByUserIdWithDate',
			data:'{"date":"'+ date +'"}',
			before: function(){	
				layer.load(1);
			},
			success: function(result){
				recordSport.sportEnergyArray = [];
				recordSport.sportDateArray = [];
				for (var i = 0; i <result.obj.length; i++) {
					recordSport.sportEnergyArray.push(result.obj[i].energy);
					recordSport.sportDateArray.push(new Date(result.obj[i].recordDate).Format("MM/dd"));
				}
//				getDietEnergySheet(recordSport.sportEnergyArray, recordSport.sportDateArray);
			}
		});
	},
	/** 获取运动 */
	getSportWithDate: function(){
		var date = $('.selectDate').text();
		$.ajax({
				async : true,
				cache : false,
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				url: 'recordDietControl.do?getSportWithDate',
				data:'{"recordDate":"'+date+'"}',
				before: function(){
					layer.load(1);
				},
				success: function(result){
					vueModel.getSportsModel().results = result.obj;
					if (result.success == false) {
						vueModel.getSportsModel().results = '';
					}
				}
			});
	},
	/*
	 * 能量分析
	 */
	energyAnalyze: function(){
		var recordDate = $('.selectDate').text();
		$.ajax({
			async : false,
			cache : false,
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded',
			url: 'recordDietControl.do?energyAnalyze',
			data: {recordDate : recordDate},
			before: function(){
				layer.load(1);
			},
			success: function(result){
				if(result.success){
					$('.introduce').css('display', 'none');
					$('.energyAnalyze ul').css('display', 'block');
					var basicMetabolism = result.obj.basicMetabolism;
					var requiredEnergy = result.obj.requiredEnergy;
					var basicMetabolismDeviation = result.obj.basicMetabolismDeviation;
					var requiredEnergyDeviation = result.obj.requiredEnergyDeviation;
					var basicMetabolismSuggetion = result.obj.basicMetabolismSuggetion;
					var requiredEnergySuggetion = result.obj.requiredEnergySuggetion;
					$('.energyAnalyze').find('div').eq(0).find('span').eq(1).text(basicMetabolism.toFixed(1) + 'kcal');
					$('.energyAnalyze').find('div').eq(0).find('span').eq(3).text(basicMetabolismDeviation.toFixed(1) + 'kcal');
					$('.energyAnalyze').find('div').eq(0).find('span').eq(5).text(basicMetabolismSuggetion);
					$('.energyAnalyze').find('div').eq(1).find('span').eq(1).text(requiredEnergy.toFixed(1) + 'kcal');
					$('.energyAnalyze').find('div').eq(1).find('span').eq(3).text(requiredEnergyDeviation.toFixed(1) + 'kcal');
					$('.energyAnalyze').find('div').eq(1).find('span').eq(5).text(requiredEnergySuggetion);
				}else{
					$('.energyAnalyze ul').css('display', 'none');
					if (result.obj.errorMessage != '') {
						$('.introduce').text(result.obj.errorMessage);
					}
					$('.introduce').css('display', 'block');
				}
			}
		});
	},
	
}
/*
 * 公用类(日期切换、能量柱状图、能量环形图)
 */
var commonObject = {
	/*
	 * 公用组件初始化
	 */
	commonInitial: function(){
		/*初始化日期*/
		$('.selectDate').text(new Date().Format('yyyy-MM-dd'));
		/*日期切换*/
		$('.prePage').on('click', function(){	//上一页
			var preDate = commonObject.switchDate($('.selectDate').text(), -1);
			$('.selectDate').text(preDate);
			preDate = new Date(preDate).Format('MM/dd');
			//获取运动能量
			recordSport.getSportEnergy();
			//加载能量图表
			getDietEnergy(function(energy, date){
				getDietEnergySheet(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray);
			});
			//加载圆环图
			loadEnergyChart(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray, preDate);
			//加载运动记录
			recordSport.getSportWithDate();
			//加载饮食记录
			getDietList();
			//能量分析
			recordSport.energyAnalyze();
			
		});
		$('.nextPage').on('click', function(){	//下一页
			var nextDate = commonObject.switchDate($('.selectDate').text(), 1);
			$('.selectDate').text(nextDate);
			nextDate = new Date(nextDate).Format('MM/dd');
			//获取运动能量
			recordSport.getSportEnergy();
			//加载能量图表
			getDietEnergy(function(energy, date){
				getDietEnergySheet(energy, date, recordSport.sportEnergyArray, recordSport.sportDateArray);
			});
			//加载圆环图
			loadEnergyChart(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray, nextDate);
			//加载运动记录
			recordSport.getSportWithDate();
			//加载饮食记录
			getDietList();
			//能量分析
			recordSport.energyAnalyze();
		});
	},
	/*
	 * 日期加减
	 * @param: dateStr:当前时间(2015-15-15) dayNumber:加减天数
	 */
	switchDate: function(dateStr,dayNumber){
		var date_new = new Date(dateStr);
		date_new.setDate(date_new.getDate()+dayNumber);
		var day=date_new.getDate();
		var month=date_new.getMonth()+1;
		if(day<10){
			day="0"+day;
		}
		if(month<10){
			month="0"+month;
		}
		return (date_new.getFullYear()+'-'+month+'-'+day);
	}
}
//显示能量柱状图
function getDietEnergySheet(dietEnergy, dietDate ,sportEnergy, sportDate){
	Array.prototype.contains = function ( needle ) {
		  for (i in this) {
		    if (this[i] === needle) return true;
		  }
		  return false;
		}
	/* 饮食和运动能量进行合并*/
	for(var i = 0; i < dietDate.length; i ++){
		if($.inArray(dietDate[i], sportDate) == -1){
			sportEnergy.splice(i, 0, 0); 
			sportDate.splice(i, 0, dietDate[i]);
		}
	}
	for(var j = 0; j < sportDate.length; j ++){
		if($.inArray(sportDate[j], dietDate) == -1){
			dietEnergy.splice(j, 0, 0); 
			dietDate.splice(j, 0, sportDate[j]);
		}
	}
	$('#dietData').highcharts({
        title: {
            text: null,
        },
        legend:{
       	 layout: 'horizontal',
           align: 'right',
           verticalAlign: 'top',
           x: -10,
           y: 30,
           fontSize:14
       },
       exporting: {
           enabled:false
       },
       chart: {
           type: 'column'
       },
        xAxis: {
            categories: dietDate
        },
        yAxis: {
            title: {
                text:null
            }
        },
         credits: {
            enabled: false
        },
        plotOptions: {
            spline: {
                marker: {
                    radius:4,
                    lineColor: '#666',
                    lineWidth: 1
                }
            }
        },
        colors: ['gold', '#48c858'],
        series: [{	
        			name: "饮食摄入",
        			data: dietEnergy,
        		},{
        			 name: "运动消耗",
                     data: sportEnergy,
                 }],
    });
	
}
/** 加载圆环图以及两边的卡路里数据 */
function loadEnergyChart(dietEnergyArray, dietDate, sportEnergyArray, sportDate, recordDate){
	var surplusEnergy = 0;
	var dietEnergyAll = 10;
	var dietEnergy = 0;
	var sportEnergy = 0;
	var now = new Date().Format('MM/dd');
	for(var i = 0; i < dietDate.length; i ++){
		if(dietDate[i] == recordDate || sportDate[i] == recordDate){
			
			if(sportDate[i] == recordDate){
				sportEnergy = sportEnergyArray[i];
			}
			if(dietDate[i] == recordDate){
				dietEnergy = dietEnergyArray[i];
			}
			dietEnergyAll = dietEnergyArray[i];
//			break;
		}
	}
	surplusEnergy = parseInt(dietEnergy) - parseInt(sportEnergy);
	$('.dietEnergy').text(dietEnergy);
	$('.sportEnergy').text(sportEnergy);
	
	require.config({
	    paths: {
	    	echarts: 'static/plugins/echarts-2.2.7'
	    }
	});
	require(
		    [
		        'echarts',
		        'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		    ],
		function (ec, obj) {
		    var myChart = ec.init(document.getElementById('energyChart'));
		    myChart.setOption(initEnergyChart(getParam("剩余能量", "#f00", "", dietEnergyAll ,"" , surplusEnergy))); 
		}
	);
}
function getParam(dw,color,wname,wvalue,name,value){
	var param = new Object();
	param.dw = dw;
	param.color = color;
	param.wname = wname;//
	param.wvalue = wvalue;
	param.name = name;
	param.value = value;
	return param;
}
/** 画圆环图 */
function initEnergyChart(_param){
	var labelTop = {
		    normal : {
		        label : {
		            show : true,
		            position : 'center',
		            formatter : '{b}',
		            textStyle: {
		                baseline : 'bottom'
		            }
		        },
		        labelLine : {
		            show : false
		        }
		    }
		};

	var labelFromatter = {
	    normal : {
	        label : {
	            formatter : function (params){
	                return _param.value + _param.dw /*100000 - params.value + _param.dw*/
	            },
	            textStyle: {
	                baseline : 'top'
	            }
	        },
	    color: _param.color
	    },
	}

	var labelBottom = {
	    normal : {
	        color: '#ccc',
	        label : {
	            show : true,
	            position : 'center'
	        },
	        labelLine : {
	            show : false
	        }
	    },
	    emphasis: {
	        color: 'rgba(0,0,0,0)'
	    }
	};

	var radius = [65, 50];
	var option = {
	    legend: {
	        x : 'center',
	        y : 'center',
	        data:[
	            ''
	        ]
	    },
	    series : [
	        {
	            type : 'pie',
	            center: ['50%', '50%'],
	            radius : radius,
	            x:'0%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:_param.wname, value:_param.wvalue, itemStyle : labelBottom},
	                {name:_param.wname, value:_param.value,itemStyle : labelTop}
	            ]
	        }
	    ]
	};
	return option
}


$(function(){
	
//	初始化公用组件
	commonObject.commonInitial();
//	绑定添加饮食
	var foodAddobject = Object.create(FoodAddObject);
	foodAddobject.foodInitial();
	//运动初始化
	recordSport.initalSport();
//	加载能量图表
	getDietEnergySheet(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray);
//	加载圆环图
	loadEnergyChart(dietEnergyArray, dietDateArray, recordSport.sportEnergyArray, recordSport.sportDateArray, new Date().Format('MM/dd'));

});


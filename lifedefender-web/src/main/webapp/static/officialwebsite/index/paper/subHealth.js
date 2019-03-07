/*
 * 亚健康问卷
 */
'use strict';
var vueModel = {
		/** 亚健康vue */
		subHealth: null,
		/** 亚健康分析vue */
		subHealthAnalyze: null,
		/** 获取亚健康vue */
		getSubHealth: function () {
			if (this.subHealth == null) {
				this.subHealth = new Vue({
					el: ".subHealthModel",
					data: {
						questionnaire: null,	//题目集合
						questionnaireOptions: null,		//选项集合
						currentTopicNumber: 1,		//当前题码
						totalTopic: null,		//总题码
						currentTopicName: null,		//当前题目名称
						questionnaireOptionScore: null,     //题目所得分数集合--[{'number': 1, 'score': 2}]
						questionnaireScore: null,    //所得分数
					},
					methods: {
						/** 下一道题 */
						nextTopic: function (score, type) {
							var vue = vueModel.getSubHealth();
							var scoreObj = {
									number: vue.currentTopicNumber,
									score: score
							}
							if (vue.questionnaireOptionScore == null) {
								vue.questionnaireOptionScore = new Array();
							}
							var bool = true;
							for (var i = 0; i < vue.questionnaireOptionScore.length; i ++) {
								//该题分数以存在
								if (vue.questionnaireOptionScore[i].number == vue.currentTopicNumber) {
									vue.questionnaireOptionScore[i].score = score;
									bool = false;
									break;
								}
							}
							if (bool) {
								vue.questionnaireOptionScore.push(scoreObj);
							}
							if (vue.currentTopicNumber == vue.totalTopic) {	//全部题目做完
								for (var i = 0; i < vue.questionnaireOptionScore.length; i ++) {
									vue.questionnaireScore += vue.questionnaireOptionScore[i].score;
								}
								setTimeout(function () {
									vue.submitResult(vue.questionnaireScore);
									vue.questionnaireScore = null;
								}, 1000);
							} else {
								setTimeout(function () {
									vue.currentTopicNumber ++;
									bool = true;
									//判断此题是否已经答过
									/*for (var i = 0; i < vue.questionnaireOptionScore.length; i ++) {
										if (vue.questionnaireOptionScore[i].number == vue.currentTopicNumber) {
											bool = false;
											var temp = 0;
											switch (vue.questionnaireOptionScore[i].score) {
											case 0 :
												temp = 0;
												break;
											case 3 :
												temp = 1;
												break;
											case 5 :
												temp = 2;
												break;
											default :
												temp = 0;
												break;
											}
											$('.options').find('.magic-radio').eq(temp).attr('checked', 'true');
										}
									}*/
									if (bool) {
										$('.magic-radio').removeAttr("checked");
									}
								}, 300);
							}
						},
						/** 上一道题 */
						preTopic: function () {
							var vue = vueModel.getSubHealth();
							setTimeout(function () {
								vue.currentTopicNumber --;
								//判断此题是否已经答过
								for (var i = 0; i < vue.questionnaireOptionScore.length; i ++) {
									if (vue.questionnaireOptionScore[i].number == vue.currentTopicNumber) {
										var temp = 0;
										switch (vue.questionnaireOptionScore[i].score) {
										case 0 :
											temp = 0;
											break;
										case 3 :
											temp = 1;
											break;
										case 5 :
											temp = 2;
											break;
										default :
											temp = 0;
											break;
										}
										$('.options').find('.magic-radio').eq(temp).attr('checked', 'true');
									}
								}
							}, 300);
						},
						/** 返回选项样式 */
						checkedCss: function (index) {
							var msg = null;
							switch (index) {
							case 0:
								msg = 'none';
								break;
							case 1:
								msg = 'sometimes';
								break;
							case 2:
								msg = 'always';
								break;
							}
							return msg;
						},
						/** 提交测试结果 */
						submitResult: function (score) {
							var paperTypeName = "体质";
							var topicTypeName = "亚健康";
							var paperResult = {
								'paperTypeName': paperTypeName,
								'topicTypeName': topicTypeName,
								'result': score
							}
							$.ajax({
								async : true,
								cache : false,
								type: 'POST',
								contentType: 'application/x-www-form-urlencoded',
								url: 'indexControl.do?addSubHealthResult',
								data: (paperResult),
								before: function(){
									layer.load(1);
								},
								success: function(result){
									if (result.success == true) {
										var data = {
												score: score
										}
										window.location.href = "indexControl.do?subHealthAnalyze&score=" + score;
									}
								}
							});
						}
					},
					computed: {
						/** 计算总页数 */
						totalTopic: function () {
							if (this.questionnaire != null) {
								return this.questionnaire.length;
							}
						},
						/** 获取当前题目名称 */
						currentTopicName: function () {
							if (this.questionnaire != null) {
								return this.questionnaire[this.currentTopicNumber - 1].name;
							}
						},
						currentOption: function () {
							if (this.questionnaire != null) {
								return this.questionnaire[this.currentTopicNumber - 1].options;
							}
                        }
					}
				});
			}
			return this.subHealth;
		},
		/** 获取亚健康分析实例 */
		getSubHealthAnalyze: function () {
			if (this.subHealthAnalyze == null) {
				this.subHealthAnalyze = new Vue({
					el: '.subHealthAnalyzeModel',
					data: {
						results: null,
						score: null,
						basicDes: null,
						suggestion: null,
					},
					methods: {
						again: function () {
							window.location.href = "indexControl.do?subHealth";
						}
					},
					computed: {
						basicDes: function () {
							if (this.results != null) {
								return this.results.basicDescription;
							}
						},
						suggestion: function () {
							if (this.results != null) {
								if (this.results.suggestion == null || this.results.suggestion == '') {
									return null;
								}
								var temp = this.results.suggestion.split('&');
								return temp;
							}
						}
						
					}
				});
			}
			return this.subHealthAnalyze;
		}
}
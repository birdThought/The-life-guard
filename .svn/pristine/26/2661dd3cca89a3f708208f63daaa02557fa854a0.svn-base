/*
* @Author: Administrator
* @Date:   2017-04-13 14:18:27
* @Last Modified by:   Administrator
* @Last Modified time: 2017-04-17 17:31:11
*/

var CIRCLE_MAX = {
    step: 50000,
    lung: 10000,
    sugar: 10,
    temp: 40
}

/*圆环设置*/
// 路径配置
require.config({
    paths: {
        echarts: '/static/plugins/echarts-2.2.7'
    }
});

// 'use strict';
$(function(){
	$(".subnav").css("display","none");
	$(".main-nav li").on("click",function(){
		$(".main-nav li .subnav").not($(this).children(".subnav")).css("display","none");
		$(this).children(".subnav").slideToggle(400);
		/*$(this).children(".subnav").slideToggle(400).parent("li").siblings('li').children(".subnav").slideUp();*/

		$(this).children("a").css("backgroundColor","#dedede").parent("li").siblings('li').children("a").css("backgroundColor","transparent");
	});


    model.getVm().init();
   /* Vue.filter('division', function(value) {
        return value/1000 ;
    });*/

})

/**
 * 实例化vue
 */
var model = {
	vm: null,
	getVm: function () {
		if (this.vm == null) {
            this.vm = new Vue({
                el: '.vue-content',
                data: {
                    results: null,
                    heartRate: null,		//心率手环-心率
					band: null,				//心率手环-计步、睡眠
					bodyFatScale: null,		//体脂秤
					bloodPressure: null,	//血压计
                    instrument: null,		//肺活仪
					bloodSugar: null,		//血糖仪
                    oximeter: null,			//血氧仪
					temperature: null,		//体温计
					ecg: null,				//心电仪



                },
                methods: {
                    //初始化
                    init: function () {
                        $.ajax({
                            async : true,
                            cache : false,
                            type: 'GET',
                            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                            url: 'userHomeControl.do?getMeasureLastedData',
                            success: function(result) {
                                console.log('result', result.attributes.APP.Band);
                                //各个设备赋值
                                model.getVm().results = result;
                                model.getVm().heartRate = result.attributes.APP.Heartrate;
                                model.getVm().band = result.attributes.APP.Band;
                                model.getVm().bodyFatScale = result.attributes.APP.Bodyfatscale;
                                model.getVm().bloodPressure = result.attributes.APP.Bloodpressure;
                                model.getVm().instrument = result.attributes.APP.Lunginstrument;
                                model.getVm().bloodSugar = result.attributes.APP.Glucometer;
                                model.getVm().oximeter = result.attributes.APP.Oxygen;
                                model.getVm().temperature = result.attributes.APP.Temperature
                                // model.getVm().ecg = result.attributes.APP.ECG;
                                // console.log(model.getVm().heartRate.deviceType);
								//圆环赋值
								model.getVm().initEchart();
                            }
                        });
                    },
                    /**
					 * 初始化圆环组件
                     */
                    initEchart: function () {
                        require(
                            [
                                'echarts',
                                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
                            ],
                            function (ec) {
                                // 基于准备好的dom，初始化echarts图表
                                var myChart = ec.init(document.getElementById('main'));
                                var myChartLung =ec.init(document.getElementById('main-lung'));
                                var myChartSugar=ec.init(document.getElementById('main-sugar'));
                                var myChartTp=ec.init(document.getElementById('main-tp'));
                                var vm = model.getVm();
                                if (vm.band != null) {
                                    var steps = vm.band.steps || 0;
                                }
                                if (vm.instrument != null) {
                                    var vitalCapacity = vm.instrument.vitalCapacity || 0;
                                }

                                if (vm.bloodSugar != null) {
                                    var bloodSugar = vm.bloodSugar.bloodSugar || 0;
                                }

                                if (vm.temperature != null) {
                                    var temperature = vm.temperature.temperature || 0;
                                }


                                myChart.setOption(initOption(getParams(" 步","#ddd","#81cf84","",steps,"跑步",CIRCLE_MAX.step)));
                                myChartLung.setOption(initOption(getParams(" ml","#ddd","#81cf84","",vitalCapacity,"肺活量",CIRCLE_MAX.lung)));
                                myChartSugar.setOption(initOption(getParams(" mmol/L","#ddd","#81cf84","",bloodSugar,"血糖含量",CIRCLE_MAX.sugar)));

                                myChartTp.setOption(initOption(getParams(" ℃","#ddd","#81cf84","",temperature,"体温",CIRCLE_MAX.temp)));



                            });
                    },
                    /**
					 * 转换"检测结果"样式
                     * @param status
                     * @returns {{error: boolean}}
                     */
                    statusClass: function (status) {
                        var isError = status == 0 ? false : true;
                        return {
                            error: isError
                        }
                    },
                    /**
					 * 转换进度条样式
                     * @param type
                     * @returns {string}
                     */
                    progressClass: function (type) {
						if (type == 'HR_band_kcal' && this.band != null) {
                            return 'width:' + this.band.kcal/1000 + '%';
						}
                        if (type == 'HR_band_mileage' && this.band != null) {
                            return 'width:' + this.band.mileage/1000 + '%';
                        }
                        if (type == 'HR_band_deep' && this.band != null) {
                            return 'height:' + this.band.deepDuration/40*10 + '%';
                        }
                        if (type == 'HR_band_shallow' && this.band != null) {
                            return 'height:' + this.band.shallowDuration/40*10 + '%';
                        }
                        if (type == 'BP_systolic' && this.bloodPressure != null) {
                            return 'width:' + this.bloodPressure.systolic * 0.8 + '%';
                        }
                        if (type == 'BP_diastolic' && this.bloodPressure != null) {
                            return 'width:' + this.bloodPressure.diastolic + '%';
                        }
                        if (type == 'BP_heartRate' && this.bloodPressure != null) {
                            return 'width:' + this.bloodPressure.heartRate * 0.8 + '%';
                        }
                        if (type == 'O_saturation' && this.oximeter != null) {
                            return 'width:' + this.oximeter.saturation + '%';
                        }
                        if (type == 'O_heartRate' && this.oximeter != null) {
                            return 'width:' + this.oximeter.heartRate * 0.8 + '%';
                        }

                    }

                },
                computed: {
                    /*heartRate: function () {
                        return model.getVm().results.attributes.APP.Heartrate;
                    }*/
                    classObject: function () {
                        return {
                            active: true,
                            'text-danger': 'ddsd',
                        }
                    },
                    // widthClass: function () {
                    //
                    //     return 'width:' + 158 + '%';
                    // }

                },
                /**
				 * 过滤器
                 */
                filters: {
                    /**
					 * 按指定倍数缩小
                     * @param value
                     * @param multiple	倍数
                     * @returns {number}
                     */
                    division: function (value, multiple) {
						return value/multiple;
                    },
                    /**
					 * 时间转换
                     * @param value
                     * @param multiple
                     * @returns {number}
                     */
                    dateFormat: function (value, fmt) {
                    	switch (fmt) {
							case 'C_N':
								if (value <= 60) {
									return (value + '分钟');
								}
								var hour = parseInt(value/60) + '小时';
								var minu = value%60 == 0 ? "" :  value%60 + '分钟';
								return hour + minu;
							case 'yyyy-MM-dd hh:mm':
								return new Date(value).Format('yyyy-MM-dd hh:mm');
						}
                    },
                    /**
					 * 状态转换
                     * @param value
                     */
                    status: function (value) {
						return value == '0' ? '正常' : '异常';
                    }
                }
            });
		}
		return this.vm;
    }
}


function getParams(dw,color,_textColor,wname,wvalue,name,value){
	var param=new Object();
	param.dw = dw;
	param.color = color;
	param._textColor=_textColor;
	param.wname = wname;
	param.wvalue = wvalue;
	param.name = name;
	param.value = value;
	return param;
}

function initOption(_params){
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

					formatter : /*function (params){
						return 1000 - params.value + _params.dw*/
						function(params){
							return params.value+_params.dw

					},
					textStyle: {
						baseline : 'top'
					}
				},
				color:_params.color
			},
		}
		var labelBottom = {
			normal : {
				/*设置圆环的颜色*/
				color: '#81cf84',
				label : {
					show : true,
					position : 'center',
					/*设置圆环内的字体颜色*/
					textStyle:{color:_params._textColor}
				},
				labelLine : {
					show : false
				}
			},
			emphasis: {
				color: 'rgba(0,0,0,0)'
			}
		};
		var radius = [50, 60];
		var option = {
				 legend: {
					x : 'center',
					y : 'center',
					data:[
						''
					]
				},
				title : {
					show:false
				},
				series : [

					{
						type : 'pie',
						center : ['30%', '30%'],
						radius : radius,
						x:'20%', // for funnel
						itemStyle : labelFromatter,
						data : [
							{name:_params.wname, value:_params.wvalue, itemStyle : labelBottom},
							{name:_params.name, value:_params.value,itemStyle : labelTop}
						]
					}
				]
			};

			/*// 为echarts对象加载数据
			myChart.setOption(option); */
			return option;
}
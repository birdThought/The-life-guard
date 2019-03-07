/**
* 血压计（收缩压、舒张压）
*/
'use strict';
var measureDateArray=new Array();
var healthStandardValueMin;   //最低健康标准值
var healthStandardValueMax;   //最高健康标准值
var isScrollbar = false;
var xAxisMax = 0;	// x轴显示个数

var valArray = [];

var bloodPressureAmount; //存放总记录数

//var deviceType_bloodPressure = "HL-031";
//
//function setDeviceType_bloodPressure(type){
//	deviceType_bloodPressure = type;
//}
function _hlHealthDataChart(id,title,Ytitle,valueSuffix,name,date){
	if (date.length > 0) {
		$('#' + id).highcharts({
			chart : {
				type : 'line'
			},
			title : {
				text : title
			},
			legend : {
				enabled : false,
				layout : 'vertical',
				align : 'left',
				verticalAlign : 'top',
				x : 150,
				y : 100,
				floating : true,
				borderWidth : 1,
				backgroundColor : '#FFFFFF'
			},
			xAxis : {
				categories : measureDateArray,
				max: xAxisMax,
				min: 0
			},
			yAxis : {
				tickPositions : valArray,
				title : {
					text : ""
				},
				plotLines : [ {
					color : '#99CC99', // 线的颜色，定义为红色
					dashStyle : 'solid', // 默认值，这里定义为实线
					value : healthStandardValueMin, // 定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
					width : 2 // 标示线的宽度，2px
					,
					label : {
						text : '最低值:' + healthStandardValueMin, // 标签的内容
						align : 'left', // 标签的水平位置，水平居左,默认是水平居中center
						x : 10
					// 标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
					}
				}, {
					color : 'orange', // 线的颜色，定义为红色
					dashStyle : 'solid', // 默认值，这里定义为实线
					value : healthStandardValueMax, // 定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
					width : 2 // 标示线的宽度，2px
					,
					label : {
						text : '最高值:' + healthStandardValueMax, // 标签的内容
						align : 'left', // 标签的水平位置，水平居左,默认是水平居中center
						x : 10
					// 标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
					}
				} ]
			},
			tooltip : {

				shared : true,
				valueSuffix : valueSuffix
			},
			credits : {
				enabled : false
			},
			plotOptions : {
				areaspline : {
					fillOpacity : 0.5
				}
			},
			series : [ {
				name : name,
				data : date
			} ]
		});
	} else {
		$('#' + id).highcharts({
			chart : {
				type : 'line'
			},
			title : {
				text : title
			},
			legend : {
				enabled : false,
				layout : 'vertical',
				align : 'left',
				verticalAlign : 'top',
				x : 150,
				y : 100,
				floating : true,
				borderWidth : 1,
				backgroundColor : '#FFFFFF'
			},
			xAxis : {
				categories:measureDateArray,
	            plotBands: [{ // visualize the weekend
	                from: 4.5,
	                to: 6.5,
	                color: 'rgba(255, 255, 255, .2)'
	            }]
			},
			yAxis : {
				tickPositions:valArray,
				title : {
					text : ""
				}
			},
			tooltip : {

				shared : true,
				valueSuffix : valueSuffix
			},
			credits : {
				enabled : false
			},
			plotOptions : {
				areaspline : {
					fillOpacity : 0.5
				}
			},
			series : [ {
				name : name,
				data : date
			} ]
		});
	}
}

function _clickBloodPressure() {
	/** 默认点击周这个选项 */
	jQuery("#bloodPressure").find("ul.period>li").eq(0).click();
	
	/** 查询出第一页显示的数据，并显示在下方*/
	getBloodPressureData(8, 1);
}

// 时间选择器
var bloodPressureDataControl = {
	weekArray: [],
	monthArray: [],
	threeMonthArray: [],
	drawChartByTime: function(time) {
		switch(time) {
		case "week":
			if(this.weekArray.length == 0) {
				this.weekData();
			} else {
				this.dataToChart(this.weekArray);
			}
			break;
		case "month":
			if(this.monthArray.length == 0) {
				this.monthData();
			} else {
				this.dataToChart(this.monthArray);
			}
			break;
		case "threeMonth":
			if(this.threeMonthArray.length == 0) {
				this.threeMonthData();
			} else {
				this.dataToChart(this.threeMonthArray);
			}
			break;
		default:
			break;
		}
	},
	weekData: function() {
		var url = 'healthDataControl.do?getHealthDataByWeek&type=xyj&deviceType='+generalFunction.deviceType;
		this.ajaxGetData(url, "week");
	},
	monthData: function() {
		var url = 'healthDataControl.do?getHealthDataByMonth&type=xyj&deviceType='+generalFunction.deviceType;
		this.ajaxGetData(url, "month");
	},
	threeMonthData: function() {
		var url = 'healthDataControl.do?getHealthDataByThreeMonth&type=xyj&deviceType='+generalFunction.deviceType;
		this.ajaxGetData(url, "threeMonth");
	},
	ajaxGetData: function(url, time) {
		var noDataTipMessage = "";
		var thisArray = null;
		switch(time) {
		case "week":
			noDataTipMessage = "最近一周暂无数据";
			break;
		case "month":
			noDataTipMessage = "最近一月暂无数据";
			break;
		case "threeMonth":
			noDataTipMessage = "最近三月暂无数据";
			break;
		}
		jQuery.ajax({
			type: 'GET',
			url: url,
			beforeSend: function(){
				layer.load(2);
			},
			success: function(result) {
				// 将获取的数据添加到页面中
				var data = result.obj;
				
	    		if(data.length == 0){
	    			layer.msg(noDataTipMessage);
	    			valArray = [10, 20, 30, 40, 50, 60, 70, 80, 90];
	    			_hlHealthDataChart('bloodPressure_systolic','','收缩压','mmHg','收缩压', []);
	    			valArray = [50, 60, 70, 80, 90, 100, 110, 120, 130];
	    			_hlHealthDataChart('bloodPressure_diastolic','','舒张压','mmHg','舒张压', []);
	    		} else {
	    			/** 将数据转换为图表 */
	    			bloodPressureDataControl.dataToChart(data);
		    		/** 将数据保存在缓存中 */
	    			switch(time) {
	    			case "week":
	    				bloodPressureDataControl.weekArray = data;
	    				break;
	    			case "month":
	    				bloodPressureDataControl.monthArray = data;
	    				break;
	    			case "threeMonth":
	    				bloodPressureDataControl.threeMonthArray = data;
	    				break;
	    			}
	    		}
			},
			complete: function() {
				layer.closeAll("loading");
			}
		});
	},
	dataToChart: function(data) {
		var systolicArray = [];
		var diastolicArray = [];
		
		measureDateArray=[];
		for(var i=0; i<data.length; i++) {
			systolicArray.push(data[i].systolic);
			diastolicArray.push(data[i].diastolic);
			measureDateArray.push(new Date(data[i].measureDate).Format("MM-dd"));
		}
		
		if(data.length > 10) {
			xAxisMax = 9;
			isScrollbar = true;
		} else {
			xAxisMax = data.length - 1;
			isScrollbar = false;
		}
		
		/** 将数据展示到highchart中 */
		var area = generalFunction.getHealthAreaData();
		var systolicArea = area.systolicArea;	// 取最后一条
		generalFunction.getHealthStandardValue(systolicArea);
		generalFunction.getStandarArray(1);
		_hlHealthDataChart('bloodPressure_systolic', '', '收缩压', 'mmHg', '收缩压', systolicArray);
		
		var diastolicArea = area.diastolicArea;	// 取最后一条
		generalFunction.getHealthStandardValue(diastolicArea);
		generalFunction.getStandarArray(1);
		_hlHealthDataChart('bloodPressure_diastolic','','舒张压','mmHg','舒张压',diastolicArray);
	}
}

/*生成分页*/
function fenye_bloodPressure(){
	var pageCount=Math.ceil(bloodPressureAmount/8);   //总页数
	$(".bloodPressurePageCode").eq(0).createPage_bloodPressure({
	    pageCount:pageCount,
	    current:1,
	    backFn:function(p){
	        console.log(p);
	    }
	});
}

/*分页处理*/
/*一页8条数据*/

(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		//填充html
		fillHtml:function(obj,args){
			return (function(){
				obj.empty();
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
				}
				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				var start = args.current -2,end = args.current+2;
				if((start > 1 && args.current < 4)||args.current == 1){
					end++;
				}
				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= args.pageCount && start >= 1){
						if(start != args.current){
							obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
						}else{
							obj.append('<span class="current">'+ start +'</span>');
						}
					}
				}
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
				}
			})();
		},
		//绑定事件 
		bindEvent:function(obj,args){
			return (function(){
				obj.off("click", "a.tcdNumber");
				obj.on("click","a.tcdNumber",function(){
					var current = parseInt($(this).text());
					var start_page=(current-1)*7;  //从第几条数据开始显示
					var end_page=current*7;
					
					getBloodPressureData(8, current);
					
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
			})();
		}
	}
	$.fn.createPage_bloodPressure = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);

var getBloodPressureData = function(pageSize, page) {
	$.ajax({
		async: false,
		type: 'GET',
		dataType : 'json', //去除类型限制
		url: 'healthDataControl.do?getHealthDataByPage&type=bloodPressure&deviceType='+generalFunction.deviceType,
		data: '&pageSize='+pageSize+'&page='+page,
		before:function() {
			layer.load(2);
		},
		complete:function(data) {
			layer.closeAll("loading");
			generalFunction.getAbnormalColor();
		},
		success:function(result) {
			/*将获取的数据添加到页面中*/
    		var txt_body = "";
    		var txt_head = "<tr><th>测量时间</th><th>收缩压(mmHg)</th><th>舒张压(mmHg)</th><th>状态</th></tr>";
    		if(!result.success) {
    			txt_body = "<tr><td colspan='4'>暂时没有数据</td></tr>";
    		} else {
    			var data = result.obj.data;
    			for(var i=0; i<(data.length); i++) { 
    				var systolic = data[i].systolic;
    				systolic = generalFunction.getAbnormalData(data[i].systolicArea, systolic);
    				var	diastolic = data[i].diastolic;
    				diastolic = generalFunction.getAbnormalData(data[i].diastolicArea, diastolic);
    				
    				var advice = data[i].status > 0 ? "异常" : "正常";
    				txt_body+="<tr><td>"+new Date(data[i].measureDate).Format("yyyy-MM-dd hh:mm:ss")+"</td><td>"+systolic+"</td><td>"+diastolic+"</td><td class='tdAdvice'>"+advice+"</td></tr>";
    			}
    			bloodPressureAmount = result.obj.totalPage;   //获取总页数
//    	    	调用分页，生成分页
        		fenye_bloodPressure();
    		}
    		$('#detail_bloodPressure').find("tbody").html(txt_body);
    		$('#detail_bloodPressure').find("thead").html(txt_head);
		}
	});
}
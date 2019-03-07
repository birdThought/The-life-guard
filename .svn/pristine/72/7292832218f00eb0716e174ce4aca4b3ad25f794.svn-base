/**
* 计步页面(单独)
*/
'use strict';
var measureDateArray=new Array();
var valArray=new Array();  //默认Y轴数值
var isScrollbar = false;	//是否带有滚动条
var xAxisMax = 0; 	//y轴显示个数
var stepCounterAmount; //存放总记录数

function _hlHealthDataChartNoPlotLines(id,title,Ytitle,valueSuffix,name,date) {
	if(date==""){
	   $('#'+id).highcharts({
	        chart: {
	            type: 'areaspline'
	        },
	        title: {
	            text: title
	        },
	        legend: {
	        	enabled:false,
	            layout: 'vertical',
	            align: 'left',
	            verticalAlign: 'top',
	            x: 150,
	            y: 100,
	            floating: true,
	            borderWidth: 1,
	            backgroundColor: '#FFFFFF'
	        },
	        xAxis: {
	            categories:measureDateArray,
	            plotBands: [{ // visualize the weekend
	                from: 4.5,
	                to: 6.5,
	                color: 'rgba(255, 255, 255, .2)'
	            }]
	        },
	        yAxis: {
	        	tickPositions:valArray,
	            title: {
	                text: ""
	            }
	        },
	        tooltip: {

	            shared: true,
	            valueSuffix: valueSuffix
	        },
	        credits: {
	            enabled: false
	        },
	        plotOptions: {
	            areaspline: {
	                fillOpacity: 0.5
	            }
	        },
	        series: [{
	            name: name,
	            data: date
	        }]
	    });
   }else{
	   if(valArray.length>0){
		   $('#'+id).highcharts({
		        chart: {
		            type: 'spline'
		        },
		        title: {
		            text: title
		        },
		        legend: {
		        	enabled:false,
		            layout: 'vertical',
		            align: 'left',
		            verticalAlign: 'top',
		            x: 150,
		            y: 100,
		            floating: true,
		            borderWidth: 1,
		            backgroundColor: '#FFFFFF'
		        },
		        xAxis: {
		            categories:measureDateArray,
		            max: xAxisMax,
		            min: 0,
		            plotBands: [{ // visualize the weekend
		                from: 4.5,
		                to: 6.5,
		                color: 'rgba(255, 255, 255, .2)'
		            }]
		        },
		        yAxis: {
		        	tickPositions:valArray,
		            title: {
		                text: ""
		            }
		        },
		        tooltip: {

		            shared: true,
		            valueSuffix: valueSuffix
		        },
		        credits: {
		            enabled: false
		        },
		        plotOptions: {
		            areaspline: {
		                fillOpacity: 0.5
		            }
		        },
		        scrollbar: {
		            enabled: isScrollbar
		        },
		        series: [{
		            name: name,
		            data: date
		        }
		        ]
		    });
	   }else{
		   $('#'+id).highcharts({
		        chart: {
		            type: 'spline'
		        },
		        title: {
		            text: title
		        },
		        legend: {
		        	enabled:false,
		            layout: 'vertical',
		            align: 'left',
		            verticalAlign: 'top',
		            x: 150,
		            y: 100,
		            floating: true,
		            borderWidth: 1,
		            backgroundColor: '#FFFFFF'
		        },
		        xAxis: {
		            categories:measureDateArray,
		            max: xAxisMax,
		            min: 0,
		            plotBands: [{ // visualize the weekend
		                from: 4.5,
		                to: 6.5,
		                color: 'rgba(255, 255, 255, .2)'
		            }]
		        },
		        yAxis: {
		        	tickPositions:valArray,
		            title: {
		                text: ""
		            }
		        },
		        tooltip: {

		            shared: true,
		            valueSuffix: valueSuffix
		        },
		        credits: {
		            enabled: false
		        },
		        plotOptions: {
		            areaspline: {
		                fillOpacity: 0.5
		            }
		        },
		        scrollbar: {
		            enabled: isScrollbar
		        },
		        series: [{
		            name: name,
		            data: date
		        }
		        ]
		    });
	   }
   }
}

function _clickStepCounter(){
	/** 默认点击周这个选项 */
	jQuery("#stepCounter").find("ul.period>li").eq(0).click();
	
	/*查询出第一页显示的数据，并显示在下方*/
	showStepCounterData(8, 1);
}

var stepCounterDataControl = {
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
		var url = 'healthDataControl.do?getHealthDataByWeek&type=xl&xlType=step&deviceType='+generalFunction.deviceType;
		this.ajaxGetData(url, "week");
	},
	monthData: function() {
		var url = 'healthDataControl.do?getHealthDataByMonth&type=xl&xlType=step&deviceType='+generalFunction.deviceType;
		this.ajaxGetData(url, "month");
	},
	threeMonthData: function() {
		var url = 'healthDataControl.do?getHealthDataByThreeMonth&type=xl&xlType=step&deviceType='+generalFunction.deviceType;
		this.ajaxGetData(url, "threeMonth");
	},
	ajaxGetData: function(url, time) {
		var noDataTipMessage = "";
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
	    			valArray = [0, 500, 1000, 1500, 2000, 2500, 3000, 3500];
	    			_hlHealthDataChartNoPlotLines('stepCount', '', '计步', '步', '计步', []);
	    		} else {
	    			/** 将数据转换为图表 */
	    			stepCounterDataControl.dataToChart(data);
		    		/** 将数据保存在缓存中 */
	    			switch(time) {
	    			case "week":
	    				stepCounterDataControl.weekArray = data;
	    				break;
	    			case "month":
	    				stepCounterDataControl.monthArray = data;
	    				break;
	    			case "threeMonth":
	    				stepCounterDataControl.threeMonthArray = data;
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
		var stepCounterArray=[];
		measureDateArray=[];
		for(var i=0; i < data.length; i++) {
			stepCounterArray.push(parseInt(data[i].steps));
			measureDateArray.push(new Date(data[i].date).Format("MM-dd"));
		}
		if(data.length > 10) {
			xAxisMax = 9;
			isScrollbar = true;
		} else {
			xAxisMax = data.length - 1;
			isScrollbar = false;
		}
		/*对数组进行判断，如果数据与之前的不一样，才会执行*/
		valArray = [0, 500, 1000, 1500, 2000, 2500, 3000, 3500];
		_hlHealthDataChartNoPlotLines('stepCount','','计步','步','计步',stepCounterArray);
	}
}

/*生成分页*/
function fenye_stepCounter(){
	var pageCount=Math.ceil(stepCounterAmount/8);   //总页数
	$(".stepCounterPageCode").eq(0).createPage_stepCounter({
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
					
					showStepCounterData(8, current);
					
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
			})();
		}
	}
	$.fn.createPage_stepCounter = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);

var showStepCounterData = function(pageSize, page) {
	$.ajax({
		async: false,
		type: 'GET',
		dataType : 'json', //去除类型限制
		url: 'healthDataControl.do?getHealthDataByPage&type=stepCounter&deviceType='+generalFunction.deviceType,
		data: '&pageSize='+pageSize+'&page='+page,
		beforeSend: function(){
			layer.load(2);
		},
		complete:function(data){
			layer.closeAll("loading");
		},
		success:function(result){
			/*将获取的数据添加到页面中*/
    		var txt_body = "";
    		var txt_head = "<tr><th>测量时间</th><th>步数(步)</th><th>里程(公里)</th><th>卡路里(大卡)</th></tr>";
    		if(!result.success) {
    			txt_body = "<tr><td colspan='4'>暂时没有数据</td></tr>";
    		} else {
    			var data = result.obj.data;
    			for(var i=0;i<(data.length);i++){ //list结果的最后一个数据是总记录数，去除最后一个
    				txt_body += "<tr><td>"+new Date(data[i].date).Format("yyyy-MM-dd")+"</td><td>"+data[i].steps+"</td><td>"+data[i].mileage+"</td><td>"+data[i].kcal+"</td></tr>";
    			}
    			stepCounterAmount = result.obj.totalPage;   //获取总页数
    			// 调用分页，生成分页
        		fenye_stepCounter();
    		}
    		$('#detail_stepCounter').find("tbody").html(txt_body);
    		$('#detail_stepCounter').find("thead").html(txt_head);
		}
	});
}
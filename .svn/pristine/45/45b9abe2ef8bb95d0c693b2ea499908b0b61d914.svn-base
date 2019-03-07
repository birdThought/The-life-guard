/**
* 心率页面(单独)
*/
'use strict';
var measureDateArray=new Array();

var valArray=new Array();  //默认Y轴数值
var valArray= [];
var sleepAmount;	// 存放总记录数

function _clickSleep(){
	/** 默认点击周这个选项 */
	jQuery("#sleep").find("ul.period>li").eq(0).click();
	
	/*查询出第一页显示的数据，并显示在下方*/
	showSleepData(8, 1);
}

var sleepDataControl = {
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
		}
	},
	weekData: function() {
		var url = "healthDataControl.do?getHealthDataByWeek&type=xl&xlType=sleep&deviceType="+generalFunction.deviceType;
		this.ajaxGetData(url, "week");
	},
	monthData: function() {
		var url = "healthDataControl.do?getHealthDataByMonth&type=xl&xlType=sleep&deviceType="+generalFunction.deviceType;
		this.ajaxGetData(url, "month");
	},
	threeMonthData: function() {
		var url = "healthDataControl.do?getHealthDataByThreeMonth&type=xl&xlType=sleep&deviceType="+generalFunction.deviceType;
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
		
		$.ajax({
			type: 'GET',
			dataType: "json",
			url: url,
			before:function(){
				layer.load(2);
			},
			success:function(result){
				var data = result.obj;
				/*将获取的数据添加到页面中*/
	    		if(data.length == 0){
	    			layer.msg(noDataTipMessage);
	    			valArray = [0, 2.5, 5, 7.5, 10, 12.5, 15, 17.5, 20];
	    			_hlHealthDataChartNoPlotLines('slep','','睡眠','时','总睡眠', []);
	    		}else{
	    			sleepDataControl.dataToChart(data);
	    			/** 将数据保存在缓存中 */
	    			switch(time) {
	    			case "week":
	    				sleepDataControl.weekArray = data;
	    				break;
	    			case "month":
	    				sleepDataControl.monthArray = data;
	    				break;
	    			case "threeMonth":
	    				sleepDataControl.threeMonthArray = data;
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
		var sleepArray = [];
		measureDateArray = [];
		for(var i=0; i < data.length; i++) {
			sleepArray.push(parseFloat(data[i].sleepDuration));
			measureDateArray.push(new Date(data[i].date).Format("MM-dd"));
		}
		
		if(data.length > 10) {
			xAxisMax = 9;
			isScrollbar = true;
		} else {
			xAxisMax = data.length - 1;
			isScrollbar = false;
		}
		
		valArray= [0, 2.5, 5, 7.5, 10, 12.5, 15, 17.5, 20];
		_hlHealthDataChartNoPlotLines('slep','','睡眠','时','总睡眠',sleepArray);
	}
}


/*生成分页*/
function fenye_stepCounter(){
	var pageCount=Math.ceil(sleepAmount/8);   //总页数
	$(".sleepPageCode").eq(0).createPage_sleep({
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
					
					showSleepData(8, current);
					
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
			})();
		}
	}
	$.fn.createPage_sleep = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);

var showSleepData = function(pageSize, page) {
	$.ajax({
		async: false,
		type: 'GET',
		dataType : 'json', //去除类型限制
		url: 'healthDataControl.do?getHealthDataByPage&type=sleep&deviceType='+generalFunction.deviceType,
		data: 'pageSize='+pageSize+'&page='+page,
		beforeSend: function(){
			layer.load(2);
		},
		complete:function(data){
			layer.closeAll("loading");
		},
		success:function(result){
			/*将获取的数据添加到页面中*/
			var txt_head = "<tr><th style='width:200px;'>测量时间</th><th>总睡眠(小时)</th><th>深睡眠(分钟)</th><th>浅睡眠(分钟)</th><th>苏醒(分钟)</th></tr>";
    		var txt_body = "";
    		if(!result.success) {
    			txt_body = "<tr><td colspan='5'>暂时没有数据</td></tr>";
    		} else {
    			var data = result.obj.data;
    			for(var i=0;i<(data.length);i++){ //list结果的最后一个数据是总记录数，去除最后一个
    				txt_body +=
    					"<tr><td style='width:150px;'>"+new Date(data[i].date).Format("yyyy-MM-dd hh:mm:ss")+"</td><td>"+data[i].sleepDuration+"</td><td>"
    					+ data[i].deepDuration+"</td><td>"+data[i].shallowDuration+"</td><td>"+data[i].wakeupDuration+"</td></tr>";
    			}
    			sleepAmount = result.obj.totalPage;   //获取总页数
    			// 调用分页，生成分页
        		fenye_stepCounter();
    		}
    		$('#detail_sleep').find("tbody").html(txt_body);
    		$('#detail_sleep').find("thead").html(txt_head);
		}
	});
}
/**
* 心率页面(单独)
*/
'use strict';
var measureDateArray = new Array();
var healthStandardValueMin;   //最低健康标准值
var healthStandardValueMax;   //最高健康标准值

var valArray=new Array();  //默认Y轴数值
var valArray= [];

var heartRateArray=new Array();	//心率数组

var heartRateAmount; //存放总记录数

function _clickHeartRate(){
	
	/** 默认点击周这个选项 */
	jQuery("#heartRate").find("ul.period>li").eq(0).click();
	
	/*查询出第一页显示的数据，并显示在下方*/
	showHeartRateData(8, 1);
}

var heartRateDataControl = {
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
		var url = 'healthDataControl.do?getHealthDataByWeek&type=xl&xlType=heartRate&deviceType='+generalFunction.deviceType
		this.ajaxGetData(url, "week");
	},
	monthData: function() {
		var url = 'healthDataControl.do?getHealthDataByMonth&type=xl&xlType=heartRate&deviceType='+generalFunction.deviceType
		this.ajaxGetData(url, "month");
	},
	threeMonthData: function() {
		var url = 'healthDataControl.do?getHealthDataByThreeMonth&type=xl&xlType=heartRate&deviceType='+generalFunction.deviceType
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
				layer('loading');
			},
			success:function(result){
				/*将获取的数据添加到页面中*/
				var data = result.obj;
	    		if(data.length == 0){
	    			layer.msg(noDataTipMessage);
	    			_hlHealthDataChart('heartRat','','心率','bpm','心率', []);
	    		} else {
	    			/** 将数据转换为图表 */
	    			heartRateDataControl.dataToChart(data);
	    			/** 将数据保存在缓存中 */
	    			switch(time) {
	    			case "week":
	    				heartRateDataControl.weekArray = data;
	    				break;
	    			case "month":
	    				heartRateDataControl.monthArray = data;
	    				break;
	    			case "threeMonth":
	    				heartRateDataControl.threeMonthArray = data;
	    				break;
	    			}
	    		}
			}
		});
	},
	dataToChart: function(data) {
		var heartRateArray = [];
		measureDateArray = [];
		for(var i=0; i < data.length; i++){
			heartRateArray.push(data[i].heartRate);
			measureDateArray.push(new Date(data[i].measureDate).Format("MM-dd"));
		}
		/** 将数据展示到highchart中 */
		var area = generalFunction.getHealthAreaData();
		var heartRateArea = area.heartRateArea;
		
		if (data.length > 10) {
			xAxisMax = 9;
			isScrollbar = true;
		}else {
			xAxisMax = data.length - 1;
			isScrollbar = false;
		}
		
		generalFunction.getHealthStandardValue(heartRateArea);
		generalFunction.getStandarArray(0.5);
		
		_hlHealthDataChart('heartRat','','心率','bpm','心率', heartRateArray);
	}
}


/*生成分页*/
function fenye_heartRate(){
	var pageCount=Math.ceil(heartRateAmount/8);   //总页数
	$(".heartRatePageCode").eq(0).createPage_heartRate({
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
					
					showHeartRateData(8, current);
				});
			})();
		}
	}
	$.fn.createPage_heartRate = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);

var showHeartRateData = function(pageSize, page) {
	$.ajax({
		async: false,
		type: 'GET',
		dataType : 'json', //去除类型限制
		url: 'healthDataControl.do?getHealthDataByPage&type=heartRate&deviceType='+generalFunction.deviceType,
		data: '&pageSize='+pageSize+'&page='+page,
		before:function(){
			layer.load(2);
		},
		complete:function(data){
			layer.closeAll("loading");
			generalFunction.getAbnormalColor();
		},
		success:function(result){
			/*将获取的数据添加到页面中*/
    		var txt_body = "";
    		var txt_head = "<tr><th>测量时间</th><th>心率(bpm)</th><th>状态</th></tr>";
    		if(!result.success) {
    			txt_body = "<tr><td colspan='3'>暂时没有数据</td></tr>";
    		} else {
    			var data = result.obj.data;
    			for(var i=0;i<(data.length);i++){ //list结果的最后一个数据是总记录数，去除最后一个
    				var heartRate = parseInt(data[i].heartRate);
    				heartRate = generalFunction.getAbnormalData(data[i].heartRateArea, heartRate);
        			var advice = data[i].status > 0 ? "异常" : "正常";
        			txt_body+="<tr><td>"+new Date(data[i].measureDate).Format("yyyy-MM-dd hh:mm:ss")+"</td><td>"+heartRate+"</td><td>"+advice+"</td></tr>";
        		}
    			heartRateAmount = result.obj.totalPage;   //获取总页数
    			// 调用分页，生成分页
        		fenye_heartRate();
    		}
    		$('#detail_heartRate').find("tbody").html(txt_body);
    		$('#detail_heartRate').find("thead").html(txt_head);
		}
	});
}
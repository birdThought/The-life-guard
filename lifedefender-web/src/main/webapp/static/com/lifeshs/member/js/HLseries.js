/*
 * @Author: Administrator
 * @Date:   2016-06-29 16:40:28
 * @Last Modified by:   Administrator
 * @Last Modified time: 2016-07-09 16:45:15
 */
'use strict';
var old_divId = "bloodPressure";

define(function(require, exports, module) {
	
	require.async([
 	  'heartRate',
	  'bloodPressure',
	  'sleep',
	  'stepCounter',
	  'temperature',
	  'healthDataUtil',
	  
	  'icheck',
	  'highcharts',
	  'calculate',
	  'dateFormat',
	  
	  'location',
	  'remind',
	  'changeNumber',
	  'blackList',
	  'monitor',
	  'jqueryRange',
	  'jqueryBase64',
	  'tableExport',
	  'jeDate',
	  'timePicker'
	], function() {
		_clickNav($(".HLseries_nav li.action"));
		
	    ic('input');
	    
	    _clickTab("bloodPressure");
	    
	    // 电子围栏
	    _location.fence.eventBind("allmap2", "hl031");
	    // 轨迹回放
	    _location.orbit.eventBind("allmap3", "hl031");
	    // 提醒设置
		remind.eventBind("hl031");
		// 黑名单
		blackList.eventBind("hl031");
		// 实时监听
		monitor.eventBind("hl031");
		// 设备号码修改
		changeNumber.eventBind("hl031");
		
		// 周-月-三月 数据选择
		$('.period li').click(function() {
			$(this).addClass('current').siblings('li').removeClass('current');
		});
		
		_clickTab("bloodPressure");
		
		jeDate({
		    dateCell:"#orbit_date",
		    format:"YYYY-MM-DD",
		    isinitVal:true,
		    initAddVal:[0],
		    minDate:"1900-01-01",
		    maxDate: jeDate.now(0),
		    startMin:"1900-01-01",
		    startMax:jeDate.now(0),
		    zindex: 999,
		    choosefun:function(elem, val) {
		    	//val为获取到的时间值
		    }
		});
		
		// TimePicker
		jQuery('ul.setNotice').on('click', '#timer', function() {
			var $this = this;
			require.async(['timePicker'], function() {
				TimePicker.showTimePicker($this);
			});
		});
	});
});

function _clickTab(id) {
	$('#' + id + ' .nav_tab li').click(function() {
		$(this).children('a').css('color', '#fff').parent().siblings().children('a').css('color', '');
		$(this).addClass('current').siblings('li').removeClass('current');
		$('#' + id + ' .nav_content > div').eq($(this).index()).show().siblings('div').hide();
	});
}

function ic(att){
	jQuery(att).iCheck({
		checkboxClass : 'icheckbox_minimal-green',
		radioClass : 'iradio_minimal-green',
		increaseArea : '20%' // optional
	});
}
$('input').on('ifClicked', function() {
	$('.remindDate').hide();
});
$('.goToClass').on('ifClicked', function() {
	$('.remindDate').show();
});

function _clickNav(obj) {
	var divId=$(obj).attr("data-id");
	if (old_divId != divId) {
		$("#" + old_divId).addClass("nav_none");
		old_divId = divId;
		$("#" + divId).removeClass("nav_none");
	}
	$(obj).addClass("action");
	$(obj).siblings("li").removeClass("action");
	generalFunction.deviceType = "HL-031";
	switch (divId) {
	case "currentLocate":
		_location.current.drawMap("allmap", "hl031");
		break;
	case "orbitRun":
		
		// 首次点击获取现在的时间作为条件
		// 1.修改页面上的date输入栏为现在的日期
		// 2.drawOrbitMap()方法date参数设置为空
		var date = new Date();
		jQuery("#orbit_date").val(date.Format("yyyy-MM-dd"));
		
		_location.orbit.drawToolBar("hl03");
		_location.orbit.drawMap("allmap3", 1, "", "hl031");
		break;
	case "electricFence":
		_location.fence.drawMap("allmap2", "hl031");
		break;
	case "changeNumber":
		changeNumber.getData("hl031");
		break;
	case "remind":
		remind.getData("hl031");
		break;
	case "blackList":
		blackList.getData("hl031");
		break;
	case "monitor":
		monitor.getData("hl031");
		break;
	case "bloodPressure":
		_clickBloodPressure();
		break;
	case "temperature":
		_clickTwj_1();
		break;
	case "heartRate":
		_clickHeartRate();
		break;
	case "stepCounter":
		_clickStepCounter();
		break;
	case "sleep":
		_clickSleep();
		break;
	case "heartElect":
		
		break;
	}
}

function _clickTab(id){
	 $('#'+id+' .nav_tab li').click(function(){
     $(this).children('a').css('color','#fff').parent().siblings().children('a').css('color',''); 
     $(this).addClass('current').siblings('li').removeClass('current');
     $('#'+id+' .nav_content > div').eq($(this).index()).show().siblings('div').hide();
  });	
}

function selectDate(val) {
	WdatePicker({
		mixDate : '#F{$dp.$D(\'rqq\')}',
		isShowClear : true,
		readOnly : true,
		dateFmt : 'HH:mm',
		onpicked : function(dp) {
			onpicked(dp, val)
		},
		oncleared : function() {
			clearedFunc(val)
		}
	});
}
function onpicked(dp, val) {
	// alert('你选择的日期是:'+dp.cal.getDateStr()+",val="+val);
	if (val == 1) {
		must("start_one", true);
		must("end_one", true);
	} else if (val == 2) {
		must("start_two", true);
		must("end_two", true);
	} else if (val == 3) {
		must("start_three", true);
		must("end_three", true);
	}
}
function _healthDate(id,title,Ytitle,valueSuffix,name,date){
    $('#'+id).highcharts({
        chart: {
            type: 'areaspline'
        },
        title: {
            text: title
        },
        legend: {
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
            categories: [
                '星期一',
                '星期二',
                '星期三',
                '星期四',
                '星期五',
                '星期六',
                '星期日'
            ],
            plotBands: [{ // visualize the weekend
                from: 4.5,
                to: 6.5,
                color: 'rgba(255, 255, 255, .2)'
            }]
        },
        yAxis: {
            title: {
                text: Ytitle
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
}

//table导出为excel
function tableToExcel(tableId) {
	$('#' + tableId).tableExport({
		type : 'excel',
		escape : 'false'
	});
}
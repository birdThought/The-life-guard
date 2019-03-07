/*
 * @Author: Administrator
 * @Date:   2016-06-29 16:40:28
 * @Last Modified by:   Administrator
 * @Last Modified time: 2016-06-29 17:04:29
 */

'use strict';
var old_divId = "currentLocate";

define(function (require, exports, module) {
    require('location');
    require('jeDate');
    require('icheck');
    require('remind');
    require('dateFormat');
    require('blackList');
    require('fimalyList');
    require('monitor');
    require('changeNumber');
    require('timePicker');
    jQuery("#locationRecord").removeClass("nav_none");	// c3首选栏为currentLocate
    _clickNav($(".Cseries_nav li.action"));	// 初始化选中的栏目为"当前定位"，需要获取定位信息
    getImeiInfo();	//获取C3设备信息
    ic('input');

    // 电子围栏
    _location.fence.eventBind("allmap2", "c3");
    // 轨迹回放
    _location.orbit.eventBind("allmap3", "c3");
    // 提醒设置
    remind.eventBind("c3");
    // 黑名单
    blackList.eventBind("c3");
    // 亲情号码
    familyList.eventBind("c3");
    // 实时监听
    monitor.eventBind("c3");
    // 设备号码修改
    changeNumber.eventBind("c3");
    //关机绑定
    poweroffBind();
    

    var $rH1 = jQuery("select[name='remindHour1']");
    var $rH2 = jQuery("select[name='remindHour2']");
    var $rH3 = jQuery("select[name='remindHour3']");
    var $rM1 = jQuery("select[name='remindMin1']");
    var $rM2 = jQuery("select[name='remindMin2']");
    var $rM3 = jQuery("select[name='remindMin3']");
    for (var i = 0; i < 24; i++) {
        var option = "<option value='" + i + "'>" + i + "</option>";
        $rH1.append(option);
        $rH2.append(option);
        $rH3.append(option);
    }
    for (var i = 0; i < 60; i++) {
        var min = i;
        if (min < 10)
            i = '0' + i;
        var option = "<option value='" + i + "'>" + i + "</option>";
        $rM1.append(option);
        $rM2.append(option);
        $rM3.append(option);
    }
});

function ic(att) {
    jQuery(att).iCheck({
        checkboxClass: 'icheckbox_minimal-green',
        radioClass: 'iradio_minimal-green',
        increaseArea: '20%' // optional
    });
}

$("input[name='radMode']").on('ifClicked', function () {
    $('.remindDate').hide();
});
$('.goToClass').on('ifClicked', function () {
    $('.remindDate').show();
});

function _clickNav(obj) {
    var divId = $(obj).attr("data-id");
    if (old_divId != divId && divId != "resetMonitorRate") {
        $("#" + old_divId).addClass("nav_none");
        old_divId = divId;
        $("#" + divId).removeClass("nav_none");
    }
    $(obj).addClass("action");
    $(obj).siblings("li").removeClass("action");
    switch (divId) {
        case "currentLocate":
            _location.current.drawMap("allmap", "c3");
            break;
        case "electricFence":
            _location.fence.drawMap("allmap2", "c3");
            break;
        case "orbitRun":
            // 首次点击获取现在的时间作为条件
            // 1.修改页面上的date输入栏为现在的日期
            // 2.drawOrbitMap()方法date参数设置为空
            var date = new Date();
            jQuery("#orbit_date").val(date.Format("yyyy-MM-dd"));

            _location.orbit.drawToolBar("c3");
            _location.orbit.drawMap("allmap3", 1, "", "c3");
            break;
        case "changeNumber":
            changeNumber.getData("c3");
            break;
        case "blackList":
            blackList.getData("c3");
            break;
        case "familyList":
        	familyList.getData("c3");
        	console.log("familyList");
            break;
        case "remind":
            remind.getData("c3");
            break;
        case "monitorRate":
            jQuery.ajax({
                async: true,
                cache: false,
                type: 'POST',
                url: "terminalWebControl.do?getMonitorRate",
                data: "",
                dataType: 'json',
                beforeSend: function () {

                },
                success: function (result) {
                    if (result.success) {
                        jQuery("select[name='heartFrequency']").val(result.attributes.heartFrequency);
                        jQuery("select[name='locationFrequency']").val(result.attributes.locationFrequency);
                        /*jQuery("select[name='autoFrequency70']").val(result.attributes.autoFrequency70);
                        jQuery("select[name='autoFrequency50']").val(result.attributes.autoFrequency50);
                        jQuery("select[name='autoFrequency30']").val(result.attributes.autoFrequency30);*/
                    }
                },
                complete: function () {
                    // 方法执行完毕，效果自己可以关闭，或者隐藏效果
                }
            });
            break;
        case "runMode":
            jQuery.ajax({
                async: true,
                cache: false,
                type: 'POST',
                url: "terminalWebControl.do?getRunMode",
                data: "",
                dataType: 'json',
                beforeSend: function () {

                },
                success: function (result) {
                    $('.remindDate').hide();
                    if (result.success) {
                        jQuery("#runMode" + result.attributes.mode.mode).iCheck('check');
                        if (result.attributes.mode.mode == 6) {
                            $('.remindDate').show();
                            var detail = result.attributes.mode.detail;
                            var weeks = detail.weeks.split("");	
                            jQuery.each(weeks, function (n, value) {
                                switch (n) {
                                    case 0:
                                        if (value == 1)
                                            jQuery(":checkbox[value='一']").iCheck('check');
                                        break;
                                    case 1:
                                        if (value == 1)
                                            jQuery(":checkbox[value='二']").iCheck('check');
                                        break;
                                    case 2:
                                        if (value == 1)
                                            jQuery(":checkbox[value='三']").iCheck('check');
                                        break;
                                    case 3:
                                        if (value == 1)
                                            jQuery(":checkbox[value='四']").iCheck('check');
                                        break;
                                    case 4:
                                        if (value == 1)
                                            jQuery(":checkbox[value='五']").iCheck('check');
                                        break;
                                    case 5:
                                        if (value == 1)
                                            jQuery(":checkbox[value='六']").iCheck('check');
                                        break;
                                    case 6:
                                        if (value == 1)
                                            jQuery(":checkbox[value='日']").iCheck('check');
                                        break;
                                }
                            });
                            var enable = detail.enable.split("");
                            jQuery.each(weeks, function (n, value) {
                                switch (n) {
                                	case 0:
                                		if (value == 1)
                                            jQuery(":checkbox[value='time1']").iCheck('check');
                                        break;
                                	case 1:
                                		if (value == 1)
                                            jQuery(":checkbox[value='time2']").iCheck('check');
                                        break;
                                	case 0:
                                		if (value == 2)
                                            jQuery(":checkbox[value='time3']").iCheck('check');
                                        break;
                                }
                                });
                            var start1 = detail.startTime1;
                            var start2 = detail.startTime2;
                            var start3 = detail.startTime3;
                            var end1 = detail.endTime1;
                            var end2 = detail.endTime2;
                            var end3 = detail.endTime3;
                            $('#start_one2').attr("value", start1);
                            $('#start_two2').attr("value", start2);
                            $('#start_three2').attr("value", start3);
                            jQuery('#end_one2').attr("value", end1);
                            jQuery('#end_two2').attr("value", end2);
                            jQuery('#end_three2').attr("value", end3);
                        }
                    }
                },
                complete: function () {
                    // 方法执行完毕，效果自己可以关闭，或者隐藏效果
                }
            });
            break;
        case "resetMonitorRate":
            jQuery("select[name='heartFrequency']").val(100);
            jQuery("select[name='locationFrequency']").val(100);
            jQuery("select[name='autoFrequency70']").val(100);
            jQuery("select[name='autoFrequency50']").val(100);
            jQuery("select[name='autoFrequency30']").val(100);

            break;
        case "monitor":
            monitor.getData("c3");
            break;
        case "locationRecord"://定位记录
            showLocationRecord(1);
            break;
    }
}

function showLocationRecord(page) {
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'POST',
        url: "terminalWebControl.do?getSportRecord&next="+page,
        beforeSend: function () {

        },
        success: function (result) {
            $("#sportLocation tbody").empty();
            var str="";
            var data=result.obj.dataObject;
            if($.isEmptyObject(data)){
                $('<tr><td colspan="4" style="line-height: 35px;font-size: 15px">当前没有定位记录数据</td></tr>').appendTo($("#sportLocation tbody"));
                return;
            }
            for(var index in data){
                var item=data[index];
                switch (parseInt(item.locationMode)){
                    case 1:
                        item.locationMode='GPS';
                        break;
                    case 2:
                        item.locationMode='WIFI';
                        break;
                    case 3:
                        item.locationMode='SIM';
                        break;
                }
                str+='<tr><td>'+item.productModel+'</td><td>'+item.address+'</td><td>'+item.locationMode+'</td><td>'+new Date(item.measureDate).Format("yyyy-MM-dd hh:mm:ss")+'</td></tr>';
            }
            $(str).appendTo($("#sportLocation tbody"));
            var pageCount=result.obj.totalPage;
            var cur=result.obj.nowPage;
            $(".stepCounterPageCode").eq(0).createPage({
                pageCount:pageCount,
                current:cur,
                backFn:function(p){
                    showLocationRecord(p);
                }
            });
        },
        complete: function () {
            // 方法执行完毕，效果自己可以关闭，或者隐藏效果
        }
    });
}

function setMonitorRate() {
    var heartFrequency = jQuery("select[name='heartFrequency'] option:selected").val();
    var locationFrequency = jQuery("select[name='locationFrequency'] option:selected").val();
    /*var autoFrequency70 = jQuery("select[name='autoFrequency70'] option:selected").val();
    var autoFrequency50 = jQuery("select[name='autoFrequency50'] option:selected").val();
    var autoFrequency30 = jQuery("select[name='autoFrequency30'] option:selected").val();*/
    var autoFrequency70 = 0;
    var autoFrequency50 = 0;
    var autoFrequency30 = 0;
    if (heartFrequency == "" || locationFrequency == "" || (typeof(heartFrequency) == 'undefined') || (typeof(locationFrequency) == 'undefined') ) {
        layer.msg("请先选择监控频率");
        return;
    }
    var data = "heartFrequency=" + heartFrequency + "&locationFrequency=" + locationFrequency
	    + "&autoFrequency70=" + autoFrequency70 + "&autoFrequency50=" + autoFrequency50
	    + "&autoFrequency30=" + autoFrequency30;
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'POST',
        url: "terminalWebControl.do?setMonitorRate",
        data: data,
        dataType: 'json',
        beforeSend: function () {

        },
        complete: function () {
            // 方法执行完毕，效果自己可以关闭，或者隐藏效果
        },
        success: function (result) {
            layer.msg(result.msg);
            if (result.success) {
//                _clickNav('monitorRate');
            }
        }
    });
}

function setRunMode() {
    var radMode = jQuery("input[name='radMode']:checked").val();
    var _data = "radMode=" + radMode;
    if (radMode == 6) {
        var obj = document.getElementsByName('week');
        var week = '';
        for (var i = 0; i < obj.length; i++) {
            if (obj[i].checked) week += obj[i].value + ','; //如果选中，将value添加到变量week中
        }
        //判断是否勾选时间段
        var enable = "";
        var time1 = document.getElementById("time1");
        var time2 = document.getElementById("time2");
        var time3 = document.getElementById("time3");
        enable = enable + (time1.checked == true ? "1" : "0");
        enable = enable + (time2.checked == true ? "1" : "0");
        enable = enable + (time3.checked == true ? "1" : "0");
        var start1 = $('#start_one2').val();
        var end1 = $('#end_one2').val();
        var start2 = $('#start_two2').val();
        var end2 = $('#end_two2').val();
        var start3 = $('#start_three2').val();
        var end3 = $('#end_three2').val();
        _data = _data + "&start1=" + start1 + "&end1=" + end1 + "&start2=" + start2 +
        "&end2=" + end2 + "&start3=" + start3 + "&end3=" + end3 + "&week=" + week + "&enable=" + enable;

        /*if (remindHour1 == "" || remindHour2 == "" || remindHour3 == "" || remindMin1 == "" || remindMin2 == "" || remindMin3 == "") {
            layer.msg("请选择提醒时间");
            return;
        }*/

    }
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'POST',
        url: "terminalWebControl.do?setRunMode",
        data: _data,
        dataType: 'json',
        beforeSend: function () {
        },
        success: function (result) {
            layer.msg(result.msg);
            if (result.success) {
//                _clickNav('runMode');
            }
        },
        complete: function () {
        }
    });
}

function getImeiInfo(){
	
	jQuery.ajax({
        async: true,
        cache: false,
        type: 'POST',
        url: "terminalWebControl.do?getDeviceInfo",
        data: "terminalType=C3",
        dataType: 'json',
        beforeSend: function () {

        },
        success: function (result) {
//            layer.msg(result.msg);
            if (result.success) {
            	$('.imeiInfo').find('span').eq(1).text(result.attributes.imei);
            	$('.imeiInfo').find('span').eq(3).text(result.attributes.status == 0 ? "离线" : "在线");
            	$('.imeiInfo').find('span').eq(3).attr("id", result.attributes.status);
            	var textColor = result.attributes.status == 0 ? "red" : "green";
            	$('.imeiInfo').find('span').eq(3).attr("style","color: " + textColor +"");
            }
        },
        complete: function () {
            // 方法执行完毕，效果自己可以关闭，或者隐藏效果
        }
    });
}

function poweroffBind(){
	$(".imeiInfo").find("input").eq(0).on("click", function(){
		var bool = $('.imeiInfo').find('span').eq(3).attr("id");
		if(bool == 0){
			layer.msg("终端设处于离线状态,不可执行关机操作!");
		}else{
			layer.confirm('确定关机?', {icon: 3, title:'提示'}, function(index){
				jQuery.ajax({
			        async: true,
			        cache: false,
			        type: 'POST',
			        url: "terminalWebControl.do?poweroff",
			        data: "terminalType=C3",
			        dataType: 'json',
			        beforeSend: function () {

			        },
			        success: function (result) {
			        	layer.msg(result.msg);
			        },
			        complete: function () {
			            // 方法执行完毕，效果自己可以关闭，或者隐藏效果
			        }
			    });
				layer.close(index);
				});
		}
		
	});
}


//分页（通用型）
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

                    

                    ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current);
                    }
                });
            })();
        }
    }
    $.fn.createPage = function(options){
        var args = $.extend({
            pageCount : 10,
            current : 1,
            backFn : function(){}
        },options);
        ms.init(this,args);
    }
})(jQuery);
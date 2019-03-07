/*
 * 
 *心率页面
 * 
 */
'use strict';
var measureDateArray=new Array();
var measureDateHeartRateArray = new Array();
var healthStandardValueMin;   //最低健康标准值
var healthStandardValueMax;   //最高健康标准值
 
var valArray=new Array();  //默认Y轴数值
var valArray= [];

var areaArray=new Array();

var isScrollbar = false;    //是否带有滚动条

var xlStepArray=new Array();        //心率手环计步数组
var xlSleepArray=new Array();       //心率手环睡眠数组
var xlHeartRateArray=new Array();   //心率手环心率数组

var check5="";
var check5_2="";
var check5_3=""; //判断是否为第一次点击

var xlStepSleepData; //存放json返回睡眠计步的数据
var xlHeartRateData; //存放json返回心率的数据

var xlHeartRateAmount; //存放总记录数
var xlStepAmount;
var xlSleepAmount;  

var type;

var xAxisMax = 0;   //y轴显示个数

vueList.getXl().results = ''; //初始化vue模板的值

function _healthData_xl(id,title,Ytitle,valueSuffix,name,date){   
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
                    },
                    plotLines:[{
                        color:'#99CC99',           //线的颜色，定义为红色
                        dashStyle:'solid',     //默认值，这里定义为实线
                        value:healthStandardValueMin,               //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                        width:2                //标示线的宽度，2px
                        ,
                        label:{
                            text:'最低值:'+healthStandardValueMin,     //标签的内容
                            align:'left',                //标签的水平位置，水平居左,默认是水平居中center
                            x:10                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
                        }
                    },
                    {
                        color:'orange',           //线的颜色，定义为红色
                        dashStyle:'solid',     //默认值，这里定义为实线
                        value:healthStandardValueMax,               //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                        width:2                //标示线的宽度，2px
                        ,
                        label:{
                            text:'最高值:'+healthStandardValueMax,     //标签的内容
                            align:'left',                //标签的水平位置，水平居左,默认是水平居中center
                            x:10                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
                        }
                    }
                    ]
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
//                  tickPositions:valArray,
                    title: {
                        text: ""
                    },
                    plotLines:[{
                        color:'#99CC99',           //线的颜色，定义为红色
                        dashStyle:'solid',     //默认值，这里定义为实线
                        value:healthStandardValueMin,               //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                        width:2                //标示线的宽度，2px
                    },
                    {
                        color:'orange',           //线的颜色，定义为红色
                        dashStyle:'solid',     //默认值，这里定义为实线
                        value:healthStandardValueMax,               //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                        width:2                //标示线的宽度，2px
                    }
                    ]
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
function _clickXl_1(){
    jQuery("#xl").find("ul.period>li").eq(0).click();
    
    /*查询出第一页显示的数据，并显示在下方*/
    jQuery("#xl").find('.nav_tab>li').eq(0).click();    //会导致重复请求
//  showXlHeartRateData(8, 1);
}

//时间选择控制类
var xlSelectDateControl = {
    weekArray: [],
    monthArray: [],
    threeMonthArray: [],
    selectDataByTime: function(object) {
        var tabId = jQuery("#xl>ul.nav_tab>li.current").attr("id");
        
        switch ($(object).attr('id')) {
        case "week":
            switch(tabId) {
            case "xlStep":
            case "xlSleep":
                if(this.weekArray.stepSleepData == undefined || this.weekArray.stepSleepData.length == 0) {
                    this.weekData(false);
                } else {
                    this.stepSleepDataToChart(this.weekArray.stepSleepData);
                }
                break;
            case "xlheartRate":
                if(this.weekArray.heartRateDate == undefined || this.weekArray.heartRateDate.length == 0) {
                    this.weekData(true);
                } else {
                    this.heartRateDataToChart(this.weekArray.heartRateDate);
                }
                break;
            }
            break;
        case "month":
            switch(tabId) {
            case "xlStep":
            case "xlSleep":
                if(this.monthArray.stepSleepData == undefined || this.monthArray.stepSleepData.length == 0) {
                    this.monthData(false);
                } else {
                    this.stepSleepDataToChart(this.monthArray.stepSleepData);
                }
                break;
            case "xlheartRate":
                if(this.monthArray.heartRateDate == undefined || this.monthArray.heartRateDate.length == 0) {
                    this.monthData(true);
                } else {
                    this.heartRateDataToChart(this.monthArray.heartRateDate);
                }
                break;
            }
            break;
        case "threeMonth":
            switch(tabId) {
            case "xlStep":
            case "xlSleep":
                if(this.threeMonthArray.stepSleepData == undefined || this.threeMonthArray.stepSleepData.length == 0) {
                    this.threeMonthData(false);
                } else {
                    this.stepSleepDataToChart(this.threeMonthArray.stepSleepData);
                }
                break;
            case "xlheartRate":
                if(this.threeMonthArray.heartRateDate == undefined || this.threeMonthArray.heartRateDate.length == 0) {
                    this.threeMonthData(true);
                } else {
                    this.heartRateDataToChart(this.threeMonthArray.heartRateDate);
                }
                break;
            }
            break;
        default:
            break;
        }
    },
    weekData: function(isHeartRate) {
        var url1 = "healthDataControl.do?getHealthDataByWeek&type=xl&xlType=heartRate&deviceType="+generalFunction.deviceType;
        var url2 = "healthDataControl.do?getHealthDataByWeek&type=xl&xlType=step&deviceType="+generalFunction.deviceType;
        this.ajaxGetData(url1, url2, "week", isHeartRate);
    },
    monthData: function(isHeartRate) {
        var url1 = "healthDataControl.do?getHealthDataByMonth&type=xl&xlType=heartRate&deviceType="+generalFunction.deviceType;
        var url2 = "healthDataControl.do?getHealthDataByMonth&type=xl&xlType=step&deviceType="+generalFunction.deviceType;
        this.ajaxGetData(url1, url2, "month", isHeartRate);
    },
    threeMonthData: function(isHeartRate) {
        var url1 = "healthDataControl.do?getHealthDataByThreeMonth&type=xl&xlType=heartRate&deviceType="+generalFunction.deviceType;
        var url2 = "healthDataControl.do?getHealthDataByThreeMonth&type=xl&xlType=step&deviceType="+generalFunction.deviceType;
        this.ajaxGetData(url1, url2, "threeMonth", isHeartRate);
    },
    ajaxGetData: function(url1, url2, time, isHeartRate) {
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
        
        if (isHeartRate) {
            // 心率
            $.ajax({
                type: 'GET',
                dataType : 'json',
                url: url1,
                beforeSend: function() {
                    layer.load(2);
                },
                complete:function(data) {
                    layer.closeAll("loading");
                },
                success:function(result) {
                    var data = result.obj;
                    /*将获取的数据添加到页面中*/
                    if (data.length == 0) {
                        layer.msg(noDataTipMessage);
                        valArray= [80,90,100,110,120,130,140,150,160];
                        _healthData_xl('heartStep_3','','心率','bpm','心率', []);
                    } else {
                        switch(time) {
                        case "week":
                            xlSelectDateControl.weekArray.heartRateDate = data;
                            break;
                        case "month":
                            xlSelectDateControl.monthArray.heartRateDate = data;
                            break;
                        case "threeMonth":
                            xlSelectDateControl.threeMonthArray.heartRateDate = data;
                            break;
                        }
                        xlSelectDateControl.heartRateDataToChart(data);
                    }
                }
            });
        } else {
            // 计步睡眠
            $.ajax({
                type: 'GET',
                dataType : 'json',
                url: url2,
                beforeSend: function() {
                    layer.load(2);
                },
                complete:function(data) {
                    layer.closeAll("loading");
                },
                success:function(result) {
                    var data = result.obj;
                    /*将获取的数据添加到页面中*/
                    if (data.length == 0) {
                        layer.msg(noDataTipMessage);
                        valArray= [0,2.5,5,7.5,10,12.5,15,17.5,20];
                        _healthData_xl('slep','','睡眠','时','总睡眠', []);
                        valArray= [0,100,200,300,400,500,600,700];
                        _healthData_xl('container5','','','步','步数', []);
                    } else {
                        switch(time) {
                        case "week":
                            xlSelectDateControl.weekArray.stepSleepData = data;
                            break;
                        case "month":
                            xlSelectDateControl.monthArray.stepSleepData = data;
                            break;
                        case "threeMonth":
                            xlSelectDateControl.threeMonthArray.stepSleepData = data;
                            break;
                        }
                        xlSelectDateControl.stepSleepDataToChart(data);
                    }
                }
            });
        }
    },
    heartRateDataToChart: function(data) {
        var xlHeartRateArray=[];
        measureDateArray=[];
        for(var i = 0; i < data.length; i++) {
            measureDateArray.push(new Date(data[i].measureDate).Format("MM-dd"));
            xlHeartRateArray.push(parseFloat(data[i].heartRate));
        }
        if (data.length > 10) {
            xAxisMax = 9;
            isScrollbar = true;
        }else {
            xAxisMax = data.length - 1;
            isScrollbar = false;
        }
        var area = generalFunction.getHealthAreaData();
        var heartRateArea = area.heartRateArea;
        generalFunction.getHealthStandardValue(heartRateArea);
        generalFunction.getStandarArray(0.5);
        _healthData_xl('heartStep_3','','心率','bpm','心率', xlHeartRateArray);
    },
    stepSleepDataToChart: function(data) {
        var xlStepArray = [];
        var xlSleepArray = [];
        measureDateArray = [];
        for(var i = 0; i < data.length; i++) {
            measureDateArray.push(new Date(data[i].date).Format("MM-dd"));
            xlStepArray.push(parseFloat(data[i].steps));
            xlSleepArray.push(parseFloat(data[i].sleepDuration));
        }
        if (data.length > 10) {
            xAxisMax = 9;
            isScrollbar = true;
        }else {
            xAxisMax = data.length - 1;
            isScrollbar = false;
        }
        valArray = [];
        healthStandardValueMin = -1000;  
        healthStandardValueMax = -1000;
        valArray= [0, 500, 1000, 1500, 2000, 2500, 3000, 3500];
        _healthData_xl('container5','','','步','步数', xlStepArray);
        valArray= [0,2.5,5,7.5,10,12.5,15,17.5,20];
        _healthData_xl('slep','','睡眠','分钟','总睡眠', xlSleepArray);
    }
}

/*预加载start*/
$(function () {
    
    /** 曲线图切换 */
    $("#xl>ul.nav_tab>li").eq(0).click(function() {
        
        $(this).children('a').css('color', '#fff').parent().siblings().children('a').css('color', '');
        $(this).addClass('current').siblings('li').removeClass('current');
        $('#xl .nav_content > div').eq($(this).index()).show().siblings('div').hide();
        
        var $tab = jQuery("#xl>ul.period>li.current");
        xlSelectDateControl.selectDataByTime($tab);
    });
    $("#xl>ul.nav_tab>li").eq(1).click(function() {
        
        $(this).children('a').css('color', '#fff').parent().siblings().children('a').css('color', '');
        $(this).addClass('current').siblings('li').removeClass('current');
        $('#xl .nav_content > div').eq($(this).index()).show().siblings('div').hide();
        
        var $tab = jQuery("#xl>ul.period>li.current");
        xlSelectDateControl.selectDataByTime($tab);
    });
    $("#xl>ul.nav_tab>li").eq(2).click(function() {
        
        $(this).children('a').css('color', '#fff').parent().siblings().children('a').css('color', '');
        $(this).addClass('current').siblings('li').removeClass('current');
        $('#xl .nav_content > div').eq($(this).index()).show().siblings('div').hide();
        
        var $tab = jQuery("#xl>ul.period>li.current");
        xlSelectDateControl.selectDataByTime($tab);
    });
    
    /*心率手环的计步、心率、睡眠（不同表）点击切换*/
    $('.nav_tab').eq(0).children().eq(0).click(function(){
        showXlHeartRateData(8, 1);
    });
    $('.nav_tab').eq(0).children().eq(1).click(function(){  
        showXlStepData(8, 1);
    });
    $('.nav_tab').eq(0).children().eq(2).click(function(){
        showXlSleepData(8, 1);
    });
    
    

});

/*分页处理*/
var showXlStepData = function(pageSize, curPage) {
    $.ajax({
        async: false,
        cache : false,
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage',
        data: 'type=xl&xlType=step&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+curPage,
        before:function(){
            layer.load(2);
        },
        complete:function(data){
            layer.closeAll("loading");
        },
        success:function(result){
            if(result.success == false){
                vueList.getXl().results = '';
            }else{
                vueList.getXl().results = result.obj.data;
                var pageManager1 = new PageUtil();
                pageManager1.getPageControl().init({
                    container: "xlStepPageManager",
                    preBtn: "btn_pre",
                    nextBtn: "btn_next",
                    totalPage: parseInt(result.obj.totalPage),
                    pageChange: function (page) {
                        
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json; charset=utf-8',
                            url: 'healthDataControl.do?getHealthDataByPage&type=xl&xlType=step&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                            success: function (result) {
                                vueList.getXl().results = result.obj.data;
                            },
                            complete: function () {
                                layer.closeAll("loading");
                            }
                        });
                    }
                });
                pageManager1.getPageControl().totalPage = parseInt(result.obj.totalPage);
                pageManager1.getPageControl().selectPage(1, true);
            }
            vueList.getXl().type = 'step';
        }
    });
}

var showXlHeartRateData = function(pageSize, curPage) {
    $.ajax({
        async: false,
        cache : false,
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage',
        data: 'type=xl&xlType=heartRate&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+curPage,
        before:function(){
            layer('loading');
        },
        complete:function(data){
            layer.closeAll("loading");
        },
        success:function(result){
            if(result.success == false){
                vueList.getXl().results = '';
            }else{
                vueList.getXl().results = result.obj.data;
                vueList.getXl().addTipsTool();
                var pageManager2 = new PageUtil();
                pageManager2.getPageControl().init({
                    container: "xlHeartRatePageManager",
                    preBtn: "btn_pre",
                    nextBtn: "btn_next",
                    totalPage: parseInt(result.obj.totalPage),
                    pageChange: function (page) {
                        
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json; charset=utf-8',
                            url: 'healthDataControl.do?getHealthDataByPage&type=xl&xlType=heartRate&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                            success: function (result) {
                                vueList.getXl().results = result.obj.data;
                            },
                            complete: function () {
                                layer.closeAll("loading");
                                /*Vue.nextTick(function () {
                                    generalFunction.getAbnormalColor();
                                });*/
                            }
                        });
                    }
                });
                pageManager2.getPageControl().totalPage = parseInt(result.obj.totalPage);
                pageManager2.getPageControl().selectPage(1, true);
            }
            vueList.getXl().type = 'heartRate';
            
        }
    });
}

var showXlSleepData = function(pageSize, curPage) {
    $.ajax({
        type: 'GET',
        async: false,
        contentType : 'application/json; charset=utf-8',
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage&type=xl&xlType=sleep&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+curPage,
        beforeSend:function(){
            layer.load(2);
        },
        complete:function(data){
            //方法执行完毕，效果自己可以关闭，或者隐藏效果
            layer.closeAll("loading");
        },
        success:function(result){
            if(result.success == false){
                vueList.getXlHeartRate().results = '';
            }else{
                vueList.getXl().results = result.obj.data;
                var pageManager3 = new PageUtil();
                pageManager3.getPageControl().init({
                    container: "xlSleepPageManager",
                    preBtn: "btn_pre",
                    nextBtn: "btn_next",
                    totalPage: parseInt(result.obj.totalPage),
                    pageChange: function (page) {
                        
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json; charset=utf-8',
                            url: 'healthDataControl.do?getHealthDataByPage&type=xl&xlType=sleep&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                            success: function (result) {
                                vueList.getXl().results = result.obj.data;
                            },
                            complete: function () {
                                layer.closeAll("loading");
                            }
                        });
                    }
                });
                pageManager3.getPageControl().totalPage = parseInt(result.obj.totalPage);
                pageManager3.getPageControl().selectPage(1, true);
            }
            vueList.getXl().type = 'sleep';
        }
    });
}

/*
 * 
 *血压计页面
 * 
 */
'use strict';
var measureDateArray=new Array();
var healthStandardValueMin;   //最低健康标准值
var healthStandardValueMax;   //最高健康标准值
var valArray=new Array();
var valArray= [];
var areaArray=new Array();
var isScrollbar = false;    //是否带有滚动条
var xAxisMax = 0;   //y轴显示个数
var healthStatus = 0;

var xyjDiastolicArray=new Array();  //血压计收缩压数组
var xyjSystolicArray=new Array();   //血压计舒张压数组
var xyjHeartRateyArray=new Array(); //血压计心率数组

var check1=""; //判断是否为第一次点击

var xyjData; //存放json返回的数据

var xyjAmount;  //存放总记录数

vueList.getXyj().results = ''; //初始化vue模板的值

function _healthData_xyj(id,title,Ytitle,valueSuffix,name,date){   
   if(date==""){
       valArray= [80,90,100,110,120,130,140,150,160];
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
                enabled: false,
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
//              tickInterval: 5, //设置间隔
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
            }]
        });
   }
}

function _clickXyj_1(){
    jQuery("#xyj").find("ul.period>li").eq(0).click();
    getXyjData(8, 1);
}

//时间选择控制类
var xyjSelectDateControl = {
    weekArray: [],
    monthArray: [],
    threeMonthArray: [],
    selectDataByTime: function(object) {
        switch ($(object).attr('id')) {
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
        
        $.ajax({
            async: false,
            type: 'GET',
            dataType : 'json', //去除类型限制
            url: url,
            before:function(){
                layer.load(2);
            },
            complete:function(data){
                layer.closeAll("loading");
            },
            success:function(result){
                var data = result.obj;
                xyjData=data;
                /*将获取的数据添加到页面中*/
                if(result.obj.length == 0){
                    layer.msg(noDataTipMessage);
                    _healthData_xyj('container1','','收缩压','mmHg','收缩压', []);
                    _healthData_xyj('relax','','舒张压','mmHg','舒张压', []);
                    _healthData_xyj('heartStep','','舒张压','bpm','心率', []);
                }else{
                    xyjSelectDateControl.dataToChart(data);
                    switch(time) {
                    case "week":
                        xyjSelectDateControl.weekArray = data;
                        break;
                    case "month":
                        xyjSelectDateControl.monthArray = data;
                        break;
                    case "threeMonth":
                        xyjSelectDateControl.threeMonthArray = data;
                        break;
                    }
                }
            }
        });
        
    },
    dataToChart: function(data) {
        var xyjDiastolicArray = [];
        var xyjSystolicArray = [];
        var xyjHeartRateyArray = [];
        measureDateArray = [];
        for (var i = 0; i < data.length; i++) {
            measureDateArray.push(new Date(data[i].measureDate).Format("MM-dd"));
            // 将最近一周数据添加进曲线图
            xyjDiastolicArray.push(data[i].diastolic);
            xyjSystolicArray.push(data[i].systolic);
            xyjHeartRateyArray.push(data[i].heartRate);
        }
        
        if(data.length > 10) {
            xAxisMax = 9;
            isScrollbar = true;
        } else {
            xAxisMax = data.length - 1;
            isScrollbar = false;
        }
        
        var area = generalFunction.getHealthAreaData();
        
        var diastolicArea = area.diastolicArea;
        generalFunction.getHealthStandardValue(diastolicArea);
        generalFunction.getStandarArray(1);
        _healthData_xyj('relax','','舒张压','mmHg','舒张压', xyjDiastolicArray);
        
        var systolicArea = area.systolicArea;
        generalFunction.getHealthStandardValue(systolicArea);
        generalFunction.getStandarArray(1);
        _healthData_xyj('container1','','收缩压','mmHg','收缩压',xyjSystolicArray);
        
        var heartRateArea = area.heartRateArea;
        generalFunction.getHealthStandardValue(heartRateArea);
        generalFunction.getStandarArray(0.5);
        _healthData_xyj('heartStep','','舒张压','bpm','心率',xyjHeartRateyArray);
    }
}

/*分页处理*/
/*一页8条数据*/
var getXyjData = function(pageSize, page) {
    /*查询出第一页显示的数据，并显示在下方*/
    $.ajax({
        async: false,
        type: 'GET',
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage&type=xyj&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
        beforeSend:function(){
            layer.load(2);
        },
        complete:function(data){
            layer.closeAll("loading");
        },
        success:function(result){
            if(result.success == false){
                vueList.getXyj().results = '';
            }else{
                vueList.getXyj().results = result.obj.data;
                vueList.getXyj().addTipsTool();
                var pageManager1 = new PageUtil();
                pageManager1.getPageControl().init({
                    container: "xyjPageManager",
                    preBtn: "btn_pre",
                    nextBtn: "btn_next",
                    totalPage: parseInt(result.obj.totalPage),
                    pageChange: function (page) {
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json; charset=utf-8',
                            url: 'healthDataControl.do?getHealthDataByPage&type=xyj&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                            success: function (result) {
                                vueList.getXyj().results = result.obj.data;
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
        }
    });
}

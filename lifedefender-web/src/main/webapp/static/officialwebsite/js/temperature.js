/*
 * 
 *体温页面
 * 
 */
'use strict';
var measureDateArray=new Array();

var healthStandardValueMin;   //最低健康标准值

var healthStandardValueMax;   //最高健康标准值

var valArray=new Array();

var twjWeightArray=new Array();

var valArray;

var areaArray=new Array();

var isScrollbar = false;    //是否带有滚动条

var xAxisMax = 0;   //y轴显示个数

var temperatureArray=new Array();   //体脂秤体重数组

var check7=""; //判断是否为第一次点击

var twjData; //存放json返回的数据

var twjAmount;  //存放总记录数

var type;

vueList.getTwj().results = ''; //初始化vue模板的值

function _healthData_twj(id,title,Ytitle,valueSuffix,name,date){   
    valArray=[35.0,35.5,36.0,36.5,37.0,37.5,38.0,38.5]; //温度是通用的，不用进行计算
    if(date.length == 0) {
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
       $('#'+id).highcharts({
            chart: {
                type: 'spline',
                shadow:false,   //外框阴影 
                plotShadow: false
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
                min: 0
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
                },
            column:{
                borderColor: "",//去边框
                shadow: false       //去阴影
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

function _clickTwj_1(){
    /** 默认点击周这个选项 */
    jQuery("#temperature").find("ul.period>li").eq(0).click();
    
    /*查询出第一页显示的数据，并显示在下方*/
    getTemperature(8, 1);
}

//时间选择控制类
var twjSelectDateControl = {
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
        var url = 'healthDataControl.do?getHealthDataByWeek&type=twj&deviceType='+generalFunction.deviceType;
        this.ajaxGetData(url, "week");
    },
    monthData: function() {
        var url = 'healthDataControl.do?getHealthDataByMonth&type=twj&deviceType='+generalFunction.deviceType;
        this.ajaxGetData(url, "month");
    },
    threeMonthData: function() {
        var url = 'healthDataControl.do?getHealthDataByThreeMonth&type=twj&deviceType='+generalFunction.deviceType;
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
            dataType : 'json',
            url: url,
            beforeSend:function(){
                layer.load(2);
            },
            complete:function(data){
                layer.closeAll("loading");
            },
            success:function(result){
                var data = result.obj;
                /*将获取的数据添加到页面中*/
                if(data.length == 0) {
                    layer.msg(noDataTipMessage);
                    _healthData_twj('temper','','体温','°C','体温', []);
                } else {
                    twjSelectDateControl.dataToChart(data);
                    switch(time) {
                    case "week":
                        twjSelectDateControl.weekArray = data;
                        break;
                    case "month":
                        twjSelectDateControl.monthArray = data;
                        break;
                    case "threeMonth":
                        twjSelectDateControl.threeMonthArray = data;
                        break;
                    }
                }
            }
        });
    },
    dataToChart: function(data) {
        var temperatureArray = [];
        measureDateArray = [];
        for(var i=0;i<data.length;i++){
            temperatureArray.push(data[i].temperature);
            measureDateArray.push(new Date(data[i].measureDate).Format("MM-dd"));
        }
        /*对数组进行判断，如果数据与之前的不一样，才会执行*/
        if (data.length > 10) {
            xAxisMax = 9;
            isScrollbar = true;
        }else {
            xAxisMax = data.length - 1;
            isScrollbar = false;
        }
        
        var area = generalFunction.getHealthAreaData();
        var temperatureArea = area.temperatureArea;
        generalFunction.getHealthStandardValue(temperatureArea);
        generalFunction.getStandarArray(1);
        _healthData_twj('temper','','体温','°C','体温',temperatureArray);
    }
}

/*分页处理*/
/*一页8条数据*/
var getTemperature = function(pageSize, page, callback) {
    $.ajax({
        async : false,
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage&type=twj&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
        beforeSend: function(){
            layer.load(2);
        },
        complete: function(){
            layer.closeAll("loading");
        },
        success:function(result){
            if(result.success == false){
                vueList.getTwj().results = '';
            }else{
                vueList.getTwj().results = result.obj.data;
                vueList.getTwj().addTipsTool();
                var pageManager = new PageUtil();
                pageManager.getPageControl().init({
                    container: "twjPageManager",
                    preBtn: "btn_pre",
                    nextBtn: "btn_next",
                    totalPage: parseInt(result.obj.totalPage),
                    pageChange: function (page) {
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json; charset=utf-8',
                            url: 'healthDataControl.do?getHealthDataByPage&type=twj&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                            success: function (result) {
                                vueList.getTwj().results = result.obj.data;
                            },
                            complete: function () {
                                layer.closeAll("loading");
                            }
                        });
                    }
                });
                pageManager.getPageControl().totalPage = parseInt(result.obj.totalPage);
                pageManager.getPageControl().selectPage(1, true);
            }
        }
    });
}

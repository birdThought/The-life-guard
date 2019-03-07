/*
 * 
 *体脂秤页面
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

var tzcWeightArray=new Array();     //体脂秤体重数组

var check6=""; //判断是否为第一次点击

var tzcData; //存放json返回的数据

var tzcAmount;  //存放总记录数

var type;

vueList.getTzc().results = ''; //初始化vue模板的值

function _healthData_tzc(id,title,Ytitle,valueSuffix,name,date){   
   if(date==""){
       valArray= [0,20,40,60,80,100];
       $('#'+id).highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: title
            },
            legend: {
                enabled:false
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
                type: 'spline'
            },
            title: {
                text: title
            },
            legend: {
                enabled:false
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
            }]
        });
   }
    
}

function _clickTzc_1(){

    /** 默认点击周这个选项 */
    jQuery("#tzc").find("ul.period>li").eq(0).click();
    tzcSelectDateControl.weekData();
    
    /*查询出第一页显示的数据，并显示在下方*/
    getBodyFatScale(8, 1);
}

//时间选择控制类
var tzcSelectDateControl = {
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
        var url = 'healthDataControl.do?getHealthDataByWeek&type=tzc&deviceType='+generalFunction.deviceType;
        this.ajaxGetData(url, "week");
    },
    monthData: function() {
        var url = 'healthDataControl.do?getHealthDataByMonth&type=tzc&deviceType='+generalFunction.deviceType;
        this.ajaxGetData(url, "month");
    },
    threeMonthData: function() {
        var url = 'healthDataControl.do?getHealthDataByThreeMonth&type=tzc&deviceType='+generalFunction.deviceType;
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
            type: 'GET',
            dataType : 'json',
            url: url,
            beforeSend: function() {
                layer.load(2);
            },
            complete:function(data) {
                layer.closeAll("loading");
            },
            success:function(result) {
                var data = result.obj;
                /*将获取的数据添加到页面中*/
                if(data.length == 0) {
                    layer.msg(noDataTipMessage);
                    _healthData_tzc('container6','','','kg','体重', []);
                }else{
                    tzcSelectDateControl.dataToChart(data);
                    switch(time) {
                    case "week":
                        tzcSelectDateControl.weekArray = data;
                        break;
                    case "month":
                        tzcSelectDateControl.monthArray = data;
                        break;
                    case "threeMonth":
                        tzcSelectDateControl.threeMonthArray = data;
                        break;
                    }
                }
            }
        });
        
    },
    dataToChart: function(data) {
        var tzcWeightArray = [];
        measureDateArray = [];
        for(var i = 0; i < data.length; i++) {
            tzcWeightArray.push(data[i].weight);
            measureDateArray.push(new Date(data[i].measureDate).Format("MM-dd"));
        }
        if (data.length > 10) {
            xAxisMax = 9;
            isScrollbar = true;
        }else {
            xAxisMax = data.length - 1;
            isScrollbar = false;
        }
        
        var area = generalFunction.getHealthAreaData();
        var weightArea = area.weightArea;
        if (weightArea == undefined) {
            // 如果获取到的健康范围，体重的数值不存在，就取最后一条数据的健康标准值
            weightArea = data[data.length-1].weightArea;
        }
        generalFunction.getHealthStandardValue(weightArea);
        generalFunction.getStandarArray(1);
        _healthData_tzc('container6','','','kg','体重',tzcWeightArray);
    }
}

/*分页处理*/
/*一页8条数据*/
var getBodyFatScale = function(pageSize, page) {
    $.ajax({
        async: false,
        type: 'GET',
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage&type=tzc&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
        beforeSend:function(){
            layer.load(2);
        },
        complete:function(data){
            layer.closeAll("loading");
        },
        success:function(result){
            /* 初始化vue模板数据 */
            if(result.success == false){
                vueList.getTzc().results = '';
            }else{
                vueList.getTzc().results = result.obj.data;
                vueList.getTzc().addTipsTool();
                var pageManager = new PageUtil();
                pageManager.getPageControl().init({
                    container: "tzcPageManager",
                    preBtn: "btn_pre",
                    nextBtn: "btn_next",
                    totalPage: parseInt(result.obj.totalPage),
                    pageChange: function (page) {
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json; charset=utf-8',
                            url: 'healthDataControl.do?getHealthDataByPage&type=tzc&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                            success: function (result) {
                                v.results = result.obj.data;
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
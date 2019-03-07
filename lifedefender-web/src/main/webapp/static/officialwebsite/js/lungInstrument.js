/**
 * 肺活仪页面
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
/*定义曲线数值数组*/
var fhyDiastolicArray=new Array();  //肺活仪收缩压数组

var check2="";  //验证是否第一次点击

var fhyData; //存放json返回的数据

var fhyAmount;  //存放总记录数

var type;

vueList.getFhy().results = ''; //初始化vue模板的值

function _healthData_fhy(id,title,Ytitle,valueSuffix,name,date){   
   if(date==""){
       valArray= [0,1000,2000,3000,4000,5000,6000,7000,8000];
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
       valArray= [0,1000,2000,3000,4000,5000,6000,7000,8000];
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
                }]
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

function _clickFhy_1(){
    jQuery("#fhy").find("ul.period>li").eq(0).click();
    /*查询出第一页显示的数据，并显示在下方*/
    showFhyData(8,1);
}
//时间选择控制类
var fhySelectDateControl = {
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
        var url = "healthDataControl.do?getHealthDataByWeek&type=fhy&deviceType="+generalFunction.deviceType;
        this.ajaxGetData(url, "week");
    },
    monthData: function() {
        var url = "healthDataControl.do?getHealthDataByMonth&type=fhy&deviceType="+generalFunction.deviceType;
        this.ajaxGetData(url, "month");
    },
    threeMonthData: function() {
        var url = "healthDataControl.do?getHealthDataByThreeMonth&type=fhy&deviceType="+generalFunction.deviceType;
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
            async: false,
            type: 'GET',
            dataType : 'json',
            url: url,
            before:function(){
                layer.load(2);
            },
            complete:function(data){
                layer.closeAll("loading");
            },
            success:function(result){
                var data = result.obj;
                /*将获取的数据添加到页面中*/
                if(data.length == 0){
                    layer.msg(noDataTipMessage);
                    _healthData_fhy('container2','','收缩压','ml','肺活量', []);
                }else{
                    fhySelectDateControl.dataToChart(data);
                    switch(time) {
                    case "week":
                        fhySelectDateControl.weekArray = data;
                        break;
                    case "month":
                        fhySelectDateControl.monthArray = data;
                        break;
                    case "threeMonth":
                        fhySelectDateControl.threeMonthArray = data;
                        break;
                    }
                }
            }
        });
    },
    dataToChart: function(data) {
        fhyDiastolicArray=[];
        measureDateArray=[];
        for(var i=0;i<data.length; i++){
             measureDateArray.push(generalFunction.renderTime_1(data[i].measureDate));  
             fhyDiastolicArray.push(data[i].vitalCapacity);
        }
        
        if(data.length > 10) {
            xAxisMax = 9;
            isScrollbar = true;
        } else {
            xAxisMax = data.length - 1;
            isScrollbar = false;
        }
        
        var area = generalFunction.getHealthAreaData();
        var vitalCapacityArea = area.vitalCapacityArea;
        generalFunction.getHealthStandardValue(vitalCapacityArea);
        _healthData_fhy('container2','','收缩压','ml','肺活量',fhyDiastolicArray);
    }
}

/*分页处理*/
/*一页8条数据*/
function showFhyData(pageSize,page) {
    
    $.ajax({
        type: 'GET',
        async: false,
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage&type=fhy&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
        beforeSend:function(){
            layer.load(2);
        },
        complete:function(data){
            //方法执行完毕，效果自己可以关闭，或者隐藏效果
            layer.closeAll("loading");
        },
        success:function(result){
            if(result.success == false){
                vueList.getFhy().results = '';
            }else{
                vueList.getFhy().results = result.obj.data;
                vueList.getFhy().addTipsTool();
                var pageManager2 = new PageUtil();
                pageManager2.getPageControl().init({
                   container: "fhyPageManager",
                   preBtn: "btn_pre",
                   nextBtn: "btn_next",
                   totalPage: parseInt(result.obj.totalPage),
                   pageChange: function (page) {
                       $.ajax({
                           type: 'GET',
                           contentType: 'application/json; charset=utf-8',
                           url: 'healthDataControl.do?getHealthDataByPage&type=fhy&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                           success: function (result) {
                               vueList.getFhy().results = result.obj.data;
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
        }
    });
}
 
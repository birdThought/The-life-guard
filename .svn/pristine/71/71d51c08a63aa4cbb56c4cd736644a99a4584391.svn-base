/*
 * 
 *血糖仪页面
 * 
 */
'use strict';
var measureDateArray=new Array();

var healthStandardValueMin;   //最低健康标准值

var healthStandardValueMax;   //最高健康标准值

var valArray=new Array();

var valArray=[];

var areaArray=new Array();

var isScrollbar = false;    //是否带有滚动条

var xAxisMax = 0;   //y轴显示个数

var xtyBloodSugarArray = new Array();  //血糖仪数组

var measureTypeArray = new Array();     //测量类型数组（早餐前_1,早餐后_2,午餐前_3,午餐后_4,晚餐前_5,晚餐后_6）

var paramDataArray = new Array();

var xtyBloodSugarAreaArray = new Array();   //范围值数组

var xtyDataWeek; //存放json返回一周的数据

var xtyDataMonth; //存放json返回一月的数据

var xtyDataThreeMonth; //存放json返回三月的数据

var showData;   // 用于展示的data

var weekNoData = "最近一周暂无数据";
var monthNoData = "最近一月暂无数据";
var threeMonthNoData = "最近三月暂无数据";
var showNoDataTips = "";

var xtyAmount;  //存放总记录数

var type;

var measureType_1 = "1";

var measureType_2 = "2";

var measureType_3 = "3";

var measureType_4 = "4";

var measureType_5 = "5";

var measureType_6 = "6";

vueList.getXty().results = ''; //初始化vue模板的值

/* vue过滤器--》转换血糖测量类型*/
Vue.filter('formatMeasureType', function (value) {
    return getMeasureType(String(value));
});

function _healthData_xty(id,title,Ytitle,valueSuffix,name,date){   //_healthData_xty('container3','','收缩压','mmHg','收缩压',xtyBloodSugarArray);
   if(date==""){
       valArray= [3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0];
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
       valArray= [3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0];  //血糖唯一，不做计算
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
            }]
        });
   }
    
}
function _clickXty_1(){
    $.ajax({
        async : true,
        cache : false,
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        url: 'healthDataControl.do?getHealthDataByWeek',
        data: 'type=xty&deviceType='+generalFunction.deviceType+'',
        before: function(){
            layer('loading');
        },
        success: function(data) {
            
            if (data.obj.length<1) {
                switchShowData(data);
                showNoDataTips = weekNoData;
                layer.msg(weekNoData);
                xtyBloodSugarArray=[];
                _healthData_xty('container3','','血糖','mmHg','血糖',xtyBloodSugarArray);
            } else {
                // 保存数据到weekData
                xtyDataWeek = data;
                switchShowData(xtyDataWeek);
                showNoDataTips = weekNoData;
                
                xtyBloodSugarArray=[];
                xtyBloodSugarAreaArray = [];
                measureDateArray=[];
                var length = 0;
                for(var i=0;i<data.obj.length;i++){
                    for(var key in data.obj[i].paramData)  {
                        for(var k in data.obj[i].paramData[key]){
                            if (k == "1") {
                                length = length + 1;
                                xtyBloodSugarArray.push(Number(data.obj[i].paramData[key][k].bloodSugar));
                                xtyBloodSugarAreaArray.push(data.obj[i].paramData[key][k].bloodSugarArea);
                                measureDateArray.push(generalFunction.renderTime_1(data.obj[i].measureDate));
                            }
                        }
                    }
                }
                /*对数组进行判断，如果数据与之前的不一样，才会执行*/
                xAxisMax = length - 1;
                isScrollbar = false;
                var area = xtyBloodSugarAreaArray[0];
                if(area != undefined) {
                    generalFunction.getHealthStandardValue(area);
                }
                _healthData_xty('container3','','血糖','mmol/L','血糖', xtyBloodSugarArray);
            }
        }
    });
    
    /*查询出第一页显示的数据，并显示在下方*/
    showXtyData(8,1);
    
    // 绑定按钮事件
    $("#measureType").find('li').eq(0).click(function(){
        selectMeasureType.selectDataByMeasureType("1", showData);
    });
    $("#measureType").find('li').eq(1).click(function(){
        selectMeasureType.selectDataByMeasureType("2", showData);
    });
    $("#measureType").find('li').eq(2).click(function(){
        selectMeasureType.selectDataByMeasureType("3", showData);
    });
    $("#measureType").find('li').eq(3).click(function(){
        selectMeasureType.selectDataByMeasureType("4", showData);
    });
    $("#measureType").find('li').eq(4).click(function(){
        selectMeasureType.selectDataByMeasureType("5", showData);
    });
    $("#measureType").find('li').eq(5).click(function(){
        selectMeasureType.selectDataByMeasureType("6", showData);
    });
}

//时间选择控制类
var xtySelectDateControl = {
    selectDataByTime: function(object) {
        switch ($(object).attr('id')) {
        case "week":
            if (xtyDataWeek != null) {
                switchShowData(xtyDataWeek);
                showNoDataTips = weekNoData;
                $("#measureType").find("li").each(function () {
                    if ($(this).hasClass('current')) {
                        selectMeasureType.selectDataByMeasureType(getMeasureType($(this).text()),xtyDataWeek); 
                    }
                });
            } else {
                $.ajax({
                    type: 'GET',
                    dataType : 'json',
                    url: 'healthDataControl.do?getHealthDataByWeek',
                    data: 'type=xty&deviceType='+generalFunction.deviceType,
                    before: function(){
                        layer('loading');
                    },
                    success: function(data){
                        xtyDataWeek = data;
                        switchShowData(xtyDataWeek);
                        showNoDataTips = weekNoData;
                        $("#measureType").find("li").each(function () {
                            if ($(this).hasClass('current')) {
                                selectMeasureType.selectDataByMeasureType(getMeasureType($(this).text()),data); 
                            }
                        });
                    }
                });
            }
            break;
            
        case "month":
            if (xtyDataMonth != null) {
                switchShowData(xtyDataMonth);
                showNoDataTips = monthNoData;
                $("#measureType").find("li").each(function() {
                    if ($(this).hasClass('current')) {
                        selectMeasureType.selectDataByMeasureType(getMeasureType($(this).text()),xtyDataMonth);
                    }
                });
            } else {
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'GET',
                    contentType : 'application/json; charset=utf-8',
                    dataType : 'json',
                    url: 'healthDataControl.do?getHealthDataByMonth',
                    data: 'type=xty&deviceType='+generalFunction.deviceType+'',
                    before: function(){
                        layer('loading');
                    },
                    success: function(data){
                        xtyDataMonth = data;
                        switchShowData(xtyDataMonth);
                        showNoDataTips = monthNoData;
                        $("#measureType").find("li").each(function () {
                            if ($(this).hasClass('current')) {
                                selectMeasureType.selectDataByMeasureType(getMeasureType($(this).text()),data); 
                            }
                        });
                    }
                });
            }
            
            break;
        case "threeMonth":
            if (xtyDataThreeMonth != null) {
                switchShowData(xtyDataThreeMonth);
                showNoDataTips = threeMonthNoData;
                $("#measureType").find("li").each(function () {
                    if($(this).hasClass('current')) {
                        selectMeasureType.selectDataByMeasureType(getMeasureType($(this).text()),xtyDataThreeMonth); 
                    }
                });
            } else {
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'GET',
                    contentType : 'application/json; charset=utf-8',
                    dataType : 'json',
                    url: 'healthDataControl.do?getHealthDataByThreeMonth',
                    data: 'type=xty&deviceType='+generalFunction.deviceType+'',
                    before: function(){
                        layer('loading');
                    },
                    success: function(data){
                        xtyDataThreeMonth = data;
                        showNoDataTips = threeMonthNoData;
                        switchShowData(xtyDataThreeMonth);
                        $("#measureType").find("li").each(function () {
                            if ($(this).hasClass('current')) {
                                selectMeasureType.selectDataByMeasureType(getMeasureType($(this).text()),data); 
                            }
                        });
                    }
                });
            }
            break;

        default:
            break;
        }
    }
}

//测量类型类
var selectMeasureType = {
    selectDataByMeasureType: function(measureType,data) {
        switch (measureType) {
        case "1":
            this.getData(measureType,data);
            break;
        case "2":
            this.getData(measureType,data);
            break;
        case "3":
            this.getData(measureType,data);
            break;
        case "4":
            this.getData(measureType,data);
            break;
        case "5":
            this.getData(measureType,data);
            break;
        case "6":
            this.getData(measureType,data);
            break;

        default:
            break;
        }
    },
    getData: function(type,data) {
        if(data.obj.length<1){
            layer.msg(showNoDataTips);
            xtyBloodSugarArray=[];
            _healthData_xty('container3','','血糖','mmHg','血糖',xtyBloodSugarArray);
        }else{
            xtyBloodSugarArray=[];
            xtyBloodSugarAreaArray = [];
            measureDateArray=[];
            var length = 0;
            var number = 0;
            for(var i=0;i<data.obj.length;i++){
                for(var key in data.obj[i].paramData)  {
                    for(var k in data.obj[i].paramData[key]){
                        if (k == type) {
                            number ++;
                            length = length + 1;
                            xtyBloodSugarArray.push(Number(data.obj[i].paramData[key][k].bloodSugar));
                            xtyBloodSugarAreaArray.push(data.obj[i].paramData[key][k].bloodSugarArea);
                            measureDateArray.push(generalFunction.renderTime_1(data.obj[i].measureDate));
                        }
                    }
                }
            }
            /*对数组进行判断，如果数据与之前的不一样，才会执行*/
            if (number > 10) {
                xAxisMax = 9;
                isScrollbar = true;
            }else {
                xAxisMax = number - 1;
                isScrollbar = false;
            }
            
            var area = generalFunction.getHealthAreaData();
            var bloodSugarArea = area.bloodSugarArea;
            var afterMeal = bloodSugarArea.afterMeal;
            var beforeMeal = bloodSugarArea.beforeMeal;
            
            if (type%2 == 0) {
                generalFunction.getHealthStandardValue(afterMeal);
            } else {
                generalFunction.getHealthStandardValue(beforeMeal);
            }
            
            _healthData_xty('container3','','血糖','mmol/L','血糖', xtyBloodSugarArray);
        }
    }
}


//获取测量类型
function getMeasureType(type) {
    var returnString = "";
    switch (type) {
    case "1":
        returnString = "早餐前";
        break;
    case "2":
        returnString = "早餐后";
        break;
    case "3":
        returnString = "午餐前";
        break;
    case "4":
        returnString = "午餐后";
        break;
    case "5":
        returnString = "晚餐前";
        break;
    case "6":
        returnString = "晚餐后";
        break;
    case "早餐前":
        returnString = "1";
        break;
    case "早餐后":
        returnString = "2";
        break;
    case "午餐前":
        returnString = "3";
        break;
    case "午餐后":
        returnString = "4";
        break;
    case "晚餐前":
        returnString = "5";
        break;
    case "晚餐后":
        returnString = "6";
        break;
    case "周":
        returnString = "week";
        break;
    case "一月":
        returnString = "month";
        break;
    case "三月":
        returnString = "threeMonth";
        break;
        
    default:
        returnString = "无";
        break;
    }
    return returnString;
}

function switchShowData(data) {
    showData = data;
}

/*分页处理*/
function showXtyData(pageSize,page) {
    $.ajax({
        async: false,
        type: 'GET',
        dataType : 'json', //去除类型限制
        url: 'healthDataControl.do?getHealthDataByPage&type=xty&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
        beforeSend:function(){
            layer.load(2);
        },
        complete:function(data){
            //方法执行完毕，效果自己可以关闭，或者隐藏效果
            layer.closeAll("loading");
        },
        success:function(result){
            if(result.success == false){
                vueList.getXty().results = '';
            }else{
                /* 初始化vue模板数据 */
                vueList.getXty().results = result.obj.data;
                vueList.getXty().addTipsTool();
                var pageManager = new PageUtil();
                pageManager.getPageControl().init({
                    container: "xtyPageManager",
                    preBtn: "btn_pre",
                    nextBtn: "btn_next",
                    totalPage: parseInt(result.obj.totalPage),
                    pageChange: function (page) {
                        $.ajax({
                            type: 'GET',
                            contentType: 'application/json; charset=utf-8',
                            url: 'healthDataControl.do?getHealthDataByPage&type=xty&deviceType='+generalFunction.deviceType+'&pageSize='+pageSize+'&page='+page,
                            success: function (result) {
                                vueList.getXty().results = result.obj.data;
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

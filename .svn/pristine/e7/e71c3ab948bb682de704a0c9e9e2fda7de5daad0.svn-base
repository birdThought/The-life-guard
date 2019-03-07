/**
 * 服务师主页
 * @Author wenxian.cai
 * @Date 2017/6/2 17:00
 */ 

'use strict';
$(function () {
    org.getHomeData();

});

var org = {};

org.visitorArray = null;
/**
 * 获取机构主页数据
 */
org.getHomeData = function () {
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'GET',
        url: '/org/services/home?gethomedata',
        success: function(result){
            var obj = result.obj;
            if(result.success){
                $('.member-count').text(obj.memberCount);
                $('.task-count').text(obj.taskCount);
                $('.week-earning').text(obj.earning);
                $('.week-order').text(obj.orderCount);
                $('.week-visitor').text(obj.visitorCount);
                org.visitorArray = obj.visitor;
                org.initChart();
            }
        }
    });
}
/**跳转到今日收益*/
org.goTodayProfit = function () {
    window.location.href = '/org/services/home/todayprofit';
}
/**跳转到今日订单*/
org.goTodayOrder = function () {
    window.location.href = '/org/services/home/todayorder';
},
/**
 * 初始化曲线图
 */
org.initChart = function () {
    $('#container').highcharts({
        chart: {
            type: 'spline'
        },
        title:{
            text: '本周流量',
            align:'left',
            style:{
                color:'#55a9e9'
            }

        },
        xAxis: {
            categories: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            title: {
                text:'人数(个)'
            }

        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        colors: ['#10bb71'],
        series: [{
            name: '人数',
            data: org.visitorArray
        }]
    });
}


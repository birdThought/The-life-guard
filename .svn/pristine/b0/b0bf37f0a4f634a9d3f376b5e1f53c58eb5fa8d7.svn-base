

'use strict';
var home = {};

home.init = function() {}


home.vm = new Vue({
    el: '.vue-content',
    data: {
        announcement: [],
        pushService: [],
        flowRate: [],

    },
    methods: {
        /**跳转到今日收益*/
        goTodayProfit: function () {
          window.location.href = '/store/home/todayprofit';
        },
        /**跳转到今日订单*/
        goTodayOrder: function () {
            window.location.href = '/store/home/todayorder';
        },

    },
    watch: {

    },
    updated: function() {
        // 菜单栏高度
        common.addBorder();
        
        // 图表数据
        $('#container').highcharts({
            chart: {
                type: 'spline'
            },
            title:{
                text: '本周流量',
                align:'left',
                style:{
                    color:'#55a9e9',
                    fontSize:'14px',
                    margin:'10px'
                }
            },
            xAxis: {
                categories: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            },
            yAxis: {
                title: {
                    text: '人数(个)'
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
                data: home.vm.flowRate
            }]
        });
    }
});
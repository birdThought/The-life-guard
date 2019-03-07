/**
 * 今日收益、订单JS
 * @Author wenxian.cai
 * @Date 2017/6/29 10:01
 */

var home = {};

home.init = function () {
    var $ul = $("div.data-show>ul");
    $ul.on('click', 'li', function() {
        $(this).addClass('on');
        $(this).siblings().removeClass('on');
    });
}

home.vm = new Vue({
    el: '.vue-content',
    data: {
        profit: null,
        todayProfit: null,
        weekProfit: null,
        orders: null,
        startDate: null,
        endDate: null,
        order: null,
        todayOrder: null,
        weekOrder: null,
        type: null,  //今日订单：order； 今日收益profit
        userType: null, //用户类型 服务师：1 管理员：0|2

    },
    methods: {
        /**分别获取最近7天、14天、30天的收益数据*/
        listProfit: function(type) {
            var startDate = null;
            switch (type) {
                case 1:
                    startDate = new Date(new Date().getTime() - 7 * 24 * 3600 * 1000).Format("yyyy-MM-dd");
                    break;
                case 2:
                    startDate = new Date(new Date().getTime() - 14 * 24 * 3600 * 1000).Format("yyyy-MM-dd");
                    break;
                case 3:
                    startDate = new Date(new Date().getTime() - 30 * 24 * 3600 * 1000).Format("yyyy-MM-dd");
                    break;
                default:
                    break;
            }
            //获取订单数据
            home.vm.listOrders(startDate ,new Date().Format("yyyy-MM-dd"));

            $.ajax({
                cache: false,
                type: 'GET',
                url: '/store/finance/data/' + type,
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend: function() {
                    // layer.load();
                },
                success: function(result) {
                    // layer.closeAll('loading');
                    if (!result.success) {
                        layer.msg(result.msg);
                        return;
                    }
                    var categories = new Array();
                    var series = new Array();
                    for (var i in result.attributes.data) {
                        categories.push(new Date(result.attributes.data[i].date).Format('yyyy-MM-dd'));
                        series.push(result.attributes.data[i].earning);
                    }
                    var data = {
                        categories: categories,
                        series: series
                    };
                    var conditions = {
                        titleText: '收益',
                        yAxisText: '金额(元)'
                    }
                    home.chart.createChart(data, $('.profit-chart'), conditions);
                }
            });
        },
        /**按时间段查询*/
        customSelectData: function() {
            //获取订单数据
            home.vm.listOrders(home.vm.startDate ,home.vm.endDate);

            $("div.data-show>ul").find("li").removeClass("on");
            $.ajax({
                cache: false,
                type: 'GET',
                url: '/store/finance/data/' + home.vm.startDate + '/' + home.vm.endDate,
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend: function() {
                    layer.load();
                },
                success: function(result) {
                    layer.closeAll('loading');
                    if (!result.success) {
                        layer.msg(result.msg);
                        return;
                    }
                    var categories = new Array();
                    var series = new Array();
                    var conditions = null;
                    switch (home.vm.type) {
                        case 'profit':
                            for (var i in result.attributes.data) {
                                categories.push(new Date(result.attributes.data[i].date).Format('yyyy-MM-dd'));
                                series.push(result.attributes.data[i].earning);
                            }
                            conditions = {
                                titleText: '收益',
                                yAxisText: '金额(元)'
                            }
                            break;
                        case 'order':
                            for (var i in result.attributes.data) {
                                categories.push(new Date(result.attributes.data[i].date).Format('yyyy-MM-dd'));
                                series.push(result.attributes.data[i].orderCount);
                            }
                            conditions = {
                                titleText: '订单',
                                yAxisText: '数量(单)'
                            }
                            break;
                        default:
                            break;
                    }
                    var data = {
                        categories: categories,
                        series: series
                    };
                    home.chart.createChart(data, $('.profit-chart'), conditions);
                }
            });
        },
        /**获取订单列表*/
        listOrders: function(startDate, endDate) {
            var data = {
                startDate: startDate,
                endDate: endDate,
                type: this.userType
            };
            $.ajax({
                cache: false,
                type: 'GET',
                url: '/orderManage?listorder',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                dataType: 'json',
                data: data,
                beforeSend: function() {
                    // layer.load();
                },
                success: function(result) {
                    // layer.closeAll('loading');
                    if (!result.success) {
                        layer.msg(result.msg);
                        return;
                    }
                    home.vm.orders = result.obj;
                }
            });
        },
        /**分别获取最近7天、14天、30天的收益数据
         * dateType: profit:今日收益   order:今日订单
         * */
        listProfit: function(type, dataType) {
            var startDate = null;
            switch (type) {
                case 1:
                    startDate = new Date(new Date().getTime() - 7 * 24 * 3600 * 1000).Format("yyyy-MM-dd");
                    break;
                case 2:
                    startDate = new Date(new Date().getTime() - 14 * 24 * 3600 * 1000).Format("yyyy-MM-dd");
                    break;
                case 3:
                    startDate = new Date(new Date().getTime() - 30 * 24 * 3600 * 1000).Format("yyyy-MM-dd");
                    break;
                default:
                    break;
            }
            //获取订单数据
            home.vm.listOrders(startDate ,new Date().Format("yyyy-MM-dd"));

            $.ajax({
                cache: false,
                type: 'GET',
                url: '/store/finance/data/' + type,
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend: function() {
                    // layer.load();
                },
                success: function(result) {
                    // layer.closeAll('loading');
                    if (!result.success) {
                        layer.msg(result.msg);
                        return;
                    }
                    var categories = new Array();
                    var series = new Array();
                    var conditions = null;
                    switch (dataType) {
                        case 'profit':
                            for (var i in result.attributes.data) {
                                categories.push(new Date(result.attributes.data[i].date).Format('yyyy-MM-dd'));
                                series.push(result.attributes.data[i].earning);
                            }
                            conditions = {
                                titleText: '收益',
                                yAxisText: '金额(元)'
                            }
                            break;
                        case 'order':
                            for (var i in result.attributes.data) {
                                categories.push(new Date(result.attributes.data[i].date).Format('yyyy-MM-dd'));
                                series.push(result.attributes.data[i].orderCount);
                            }
                            conditions = {
                                titleText: '订单',
                                yAxisText: '数量(单)'
                            }
                            break;
                        default:
                            break;
                    }

                    var data = {
                        categories: categories,
                        series: series
                    };

                    home.chart.createChart(data, $('.profit-chart'), conditions);
                }
            });
        },
    },
    watch: {
        profit: function () {
            if (this.profit != null) {
                this.todayProfit = this.profit.dayProfitSum;
                this.weekProfit = this.profit.weekProfitSum;
            }
        },
        order: function () {
            if (this.order != null) {
                this.todayOrder = this.order.dayOrderSum;
                this.weekOrder = this.order.monthOrderSum;
            }
        },
        startDate: function(val, oldVal) {
            if (oldVal == null || val == oldVal) {
                return;
            }
            home.vm.customSelectData();
        },
        endDate: function(val, oldVal) {
            if (oldVal == null || val == oldVal) {
                return;
            }
            home.vm.customSelectData();
        }
    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        }
    }
});

/**图表对象*/
home.chart = {
    categories: null,
    series: null,
    titleText: null,
    yAxisText: null,
    createChart: function (data, $element, conditions) {
        //todo conditions做条件判断，之后可作为公用模块
        home.chart.categories = data.categories;
        home.chart.series = data.series;
        home.chart.titleText = conditions.titleText;
        home.chart.yAxisText = conditions.yAxisText;
        $element.highcharts({
            chart: {
                type: 'spline'
            },
            title:{
                text: home.chart.titleText,
                align:'left',
                style:{
                    color:'#55a9e9'
                }
            },
            xAxis: {
                categories: home.chart.categories
            },
            yAxis: {
                title:{
                    text:  home.chart.yAxisText
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
                data: home.chart.series
            }]
        });
    }
}
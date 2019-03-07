var manager = {};

manager.init = function() {}

manager.vm = new Vue({
    el: '.vue-content',
    data: {
        weekProfitSum: null,
        showData: [],
        categories: [],
        monthIncome: null,
        bankAccount: null,
        bankName: null,
        startDate: null,
        endDate: null,
        statementCharge: [],
        orders: null,
        billDate: null, //账单日期
        billDateList: null,
        currentBill: [],  //当前账单
        date: null,
    },
    methods: {
        
        switchData: function(type) {
            var $ul = $("div.data-show>ul");
            
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
                    
                    manager.vm.createChart(result.attributes.data);
                }
            });
        },
        customSelectData: function() {
            //获取订单数据
            manager.vm.listOrders(manager.vm.startDate ,manager.vm.endDate);

            $("div.data-show>ul").find("li").removeClass("on");
            $.ajax({
                cache: false,
                type: 'GET',
                url: '/store/finance/data/month/' + new Date(manager.vm.startDate).Format('yyyy-MM-dd')
                    + '/' +new Date(manager.vm.endDate).Format('yyyy-MM-dd'),
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
                    manager.vm.createChart(result.attributes.data);
                }
            });
        },

        createChart: function(data) {
            // 清空数据
            manager.vm.categories = [];
            manager.vm.showData = [];
            manager.vm.statementCharge = [];

            
            $.each(data, function(index, d) {
                manager.vm.categories.push(new Date(d.yearMonth).Format("yyyy-MM"));
                manager.vm.showData.push(d.orderCharge);
                manager.vm.statementCharge.push(d.statementCharge);
            });
            
            // 图表数据
            $('#container').highcharts({
                chart: {
                    type: 'spline'
                },
                title:{
                    text: '',
                },
                xAxis: {
                    categories: manager.vm.categories
                },
                yAxis: {
                    title:{
                        text: "金额(元)"
                    }
                },
                legend: {
                     // enabled: false
                    },
                credits: {
                    // enabled: false
                },
                colors: ['#10bb71'],
                series: [{
                    name: '收益',
                    data: manager.vm.showData,
                    color:'#55a9e9'
                },{
                    name: '收入',
                    data: manager.vm.statementCharge
                }]
            });
        },
        bindBankInfo: function() {
            location.href = '/store/finance/bankInfo';
        },
        /**获取订单列表*/
        /*listOrders: function(startDate, endDate) {
            var data = {
                startDate: startDate + '-01',
                endDate: endDate + '-31',
                type: 'org'
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
                    manager.vm.orders = result.obj;
                }
            });
        },*/
        listOrders: function (startDate, endDate) {
            var data = {
                startDate: startDate + '-01',
                endDate: endDate + '-31',
                type: 'org'
            };
            var url = '/orderManage?listorder';
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                manager.vm.orders = null;
                manager.vm.orders = result.obj;
                // manager.vm.orders = Object.assign([], manager.vm.orders, result.obj)
            })
        },
        /**获取机构账单*/
        getOrgStatement: function (date) {
            // date = $('#yearDate').val() + '-' + (Number(date) + 1);
            // date = $('.layui-tab-title li.layui-this').attr('value');
            var data = null;
            var url = '/store/finance/data/month/' + new Date(date).Format('yyyy-MM-dd');
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_2, function (result) {
                manager.vm.currentBill = result.attributes.data;
            })
            $('.bill-details').css('display', 'none');
            $('.bill-details').removeClass('block')
            $('.bill-details').addClass('none')
        },
        getbillDateList: function (year) {
            var temp = [];
            for (var i = 1; i <= 12; i ++) {
                var obj = {
                    date: year + '年' + i + '月',
                    value: year + '/' + i + '/1'
                };
                temp.push(obj);
            }
            this.billDateList = temp;
        },
        billStatus: function (value) {
            switch (value) {
                case 0:
                    return '结算完成';
                case 1:
                    return '<span style="color: #009688; cursor: pointer" onclick = "manager.vm.confirmStatement()">未确认</span>';
                case 2:
                    return '未转账';
            }
        },
        /**确认账单*/
        confirmStatement: function () {
            var lay = layer.confirm('确认该月份的账单?', function () {
                var url = '/store/finance/confirm-bill';
                var data = {
                    id: manager.vm.currentBill[0].id
                }
                http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                    layer.msg(result.msg, {icon: 1});
                    layer.close(lay);
                    setTimeout(function () {
                        var date = $('.layui-tab-title li.layui-this').attr('value');
                        manager.vm.getOrgStatement(date);
                    }, 1000);
                });
            })
        },
        /**显示账单流水*/
        showBillDetails: function () {
            if ($('.bill-details').hasClass('none')) {
                $('.bill-details').show(300);
                $('.bill-details').removeClass('none');
                var date = $('.layui-tab-title li.layui-this').attr('value');
                this.listOrders(date, date);
                return;
            }
            $('.bill-details').hide(300);
            $('.bill-details').addClass('none');
        }
    },
    watch: {
        startDate: function(val, oldVal) {
            if (oldVal == null || val == oldVal) {
                return;
            }
            manager.vm.customSelectData();
        },
        endDate: function(val, oldVal) {
            if (oldVal == null || val == oldVal) {
                return;
            }
            manager.vm.customSelectData();
        },
       /* billDate: function () {
            this.getOrgStatement(this.billDate)
        },*/
        /*date: function () {
            this.getbillDateList(date);
        }*/
        billDateList: function () {
            this.$nextTick(function () {
                var month = new Date().getMonth();

                $('.layui-tab-title').find('li').eq(month).addClass('layui-this');
                $('.layui-tab-title').find('li').eq(month).click();
            })
        }
    },
    filters: {

    },
    beforeCreate: function() {
        
    },
    updated: function() {
        // 菜单栏高度
        var height=$(".main-content").height() + 500;
        $(".main-nav").css({
            borderRight:'1px solid #ddd',
            height:height
        });
        $(".container").css({
            borderLeft:'1px solid #ddd',
            borderRight:'1px solid #ddd',
            borderBottom:'1px solid #ddd',
            height:height
        });

    }
});

$(function() {
    var $ul = $("div.data-show>ul");
    $ul.on('click', 'li', function() {
        $(this).addClass('on');
        $(this).siblings().removeClass('on');
    });
});
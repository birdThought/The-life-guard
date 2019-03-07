/**
 * 健康数据：体脂秤
 * author: wenxian.cai
 * date: 2017/11/14 19:49
 */

var device = {};

device.init = function () {

};
device.vm = new Vue({
    el: '.vue-content',
    data: {
        history: [], //历史数据
        chart: [], //图表数据
        latest: {}, //最新一条数据
        page: 1,
        deviceType: null, //设备类型

    },
    computed: {
        str: function () {
            //业务逻辑代码
            setTimeout(function () {
                return this.history[1].systolic;
            }, 400)

        }
    },
    methods: {
        /**获取设备数据
         * type 日期类型 0：不限（分页） 1：按日，2：按周，3：按月，4：按三月
         * measureType 测量类型（血糖仪专有: 早餐前_1,早餐后_2,午餐前_3,午餐后_4,晚餐前_5,晚餐后_6）
         * */
        listDevice: function (type, measureType) {
            var deviceType = null;
            switch (this.deviceType) {
                case 'xyj':
                    deviceType = 'bloodpressure';
                    break;
            
            }
            var url = '/wechat-record/' + deviceType;
            var data = {
                type: type,
                page: this.page,
                measureType: measureType
            };
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if (result.success) {
                    if (type == '0') {
                        device.vm.history = result.obj;
                        device.vm.latest = device.vm.history[0];
                    }
                    else {
                        device.vm.chart = result.obj;
                    }
                }

            });
        },
        /**判断是否异常*/
        abnormal: function (value, area) {
            try {
                var arr = area.split('-');
                if (value > arr[1] || value < arr[0]) {
                    return true;
                }
                return false;
            } catch (e) {
            }
        }
    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        }
    },
});
device.vm.$watch('latest', function () {
    finish();
    dropDown();
}, {deep: true});
var initChart = function () {

};
var finish = function () {
    setTimeout(function () {
        obj.measureResult();
    }, 350);
    var obj = {
        measureResult: function () {
            var spans = $('.lastest-content-right li > span ');
            if (spans.hasClass('red')) {
                $('.result-show').html('异常').css({'color': '#f40'});
                $('.left').css({'border': '0.1rem solid #f40'});
                $('.right').css({'border': '0.1rem solid #f40'});
            } else {
                $('.result-show').html('正常').css({'color': '#44c660'});
                $('.left').css({'border': '0.1rem solid #44c660'});
                $('.right').css({'border': '0.1rem solid #44c660'});
            }
        }
    };
};

var dropDown = function () {
    setTimeout(function () {
        $(function () {
            var page = 1;
            $('.vue-content').dropload({
                scrollArea: window,
                domUp: {
                    domClass: 'dropload-up',
                    domRefresh: '<div class="dropload-refresh">↓下拉刷新</div>',
                    domUpdate: '<div class="dropload-update">↑释放更新</div>',
                    domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
                },
                domDown: {
                    domClass: 'dropload-down',
                    domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
                    domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                    domNoData: '<div class="dropload-noData">暂无数据</div>'
                },
//           这是里下拉加载更多数据时用到的
                loadDownFn: function (me) {
                    page++;
                    var str = "";
                    var result = "";
                    $.ajax({
                        type: 'GET',
                        url: "/wechat-record/bloodpressure",
                        data: {
                            type: 0,
                            page: page,
                            measureType: '',
                            '_': Math.random() * 14
                        },
                        dataType: 'json',
                        success: function (responsive) {//如果没有出错就是满数据下就是8条数据 如果数据数据不足情况下就是小于8条
                            var objLen = responsive.obj.length;
                            if (objLen > 0) {
                                for (var i = 0; i < responsive.obj.length; i++) {
                                    var data = responsive.obj[i];
                                    str+="<li class='layui-timeline-item drop-item'>";
                                     str+="<div class='layui-timeline-content layui-text'>";
                                     str+="<div class='item clearfix'>";
                                     str+="<p class='measure-date'>"+gettime(data.measureDate);
                                     str+="<span class='result_data_finally'>"+panduan(data.status)+"</span></p>";
                                     str+="<div class='content content-spec'><p>";
                                     str+="<span>收缩压：<label class=\"" + result_judge(data.systolic,data.systolicArea) + "\">" + data.systolic + '</label>mmHg</span>';
                                     str+="<span>舒张压：<label class=\"" + result_judge(data.diastolic,data.diastolicArea) + "\">" + data.diastolic + '</label>mmHg</span></p> <p>';
                                     str+="<span>心率：<label class=\""+result_judge(data.heartRate,data.heartRateArea)+"\">" + data.heartRate + "</label></span>";
                                     str+="</p></div></div></div></li>";
                                }
                            } else {
                                me.lock();
                                me.noData();
                            }
                            $('.bloodPressure-history-data').append(str);
                            me.resetload();
                            measureResult();
                            CreateLine();
                            test();
                        },
                        error: function (xhr, type) {
                            alert('Ajax error');
                            me.resetload();
                        }
                    });
                },
                threshold: 50
            })
        });

    }, 500);
};
function test() {
    var result_show = $('.result_data_finally');
    $.each(result_show, function (val, key) {
        if (key.innerHTML == '异常') {
            $(this).css({'background': 'red', 'color': '#fff'})
            $(this).parent('.measure-date').siblings('div').find('label').css({'color': 'red'})

        } else {
            $(this).css({'background': '#44c660', 'color': '#fff'})
        }
    })

}



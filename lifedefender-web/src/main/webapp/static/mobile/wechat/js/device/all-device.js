/**
 * 健康数据：全部设备
 * author: wenxian.cai
 * date: 2017/11/14 19:49
 */

var device = {};

device.init = function () {
    device.vm.listDevice();
}


device.vm = new Vue({
    el: '.vue-content',
    data: {
        list: [],
        page: 1,
        details: {},
        deviceId: null,
        user: {},
        validDate: {},    //有效的日期
        healthData: null,
        date: new Date().Format('yyyy-MM-dd')

    },
    watch: {
        /**监听date变化*/
        date: function () {
            if (this.date != null) {    //TODO
                this.getHealthData();
            }
                     
        }
    },
    /*created: function () {
     this.listPhysical();
     },*/
    methods: {
        /**获取体检报告列表*/
        listDevice: function () {
//            var url = '/wechat-record/all-device';
//            var data = {
//                date: this.date  //测试数据
//            };
//            $('.layui-tab-title ul').find('li').eq(type - 1).click();
            this.listValidDate(new Date().Format('yyyy-MM-dd'));
            device.vm.initDate();
            
//          http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
//              device.vm.list = result.obj;
//          });
            
        },
        /**跳转体检详细页面*/
        goDetails: function (type) {
            var url = null;
            switch (type) {
                case 'tzc':
                    url = '/bodyfatscale/page';
                    break;
                case 'xyj':
                    url = '/bloodpressure/page';
                    break;
                case 'fhy':
                    url = '/lunginstrument/page';
                    break;
                case 'xty':
                    url = '/glucometer/page';
                    break;
                case 'ecg':
                    url = '/ecg/page';
                    break;
                case 'xyy':
                    url = '/oxygen/page';
                    break;
                case 'twj':
                    url = '/temperature/page';
                    break;
                case 'xzy':
                    url = '/bloodlipid/page';
                    break;
                case 'uran':
                    url = '/uran/page';
                    break;
                case 'ua':
                    url = '/ua/page';
                    break;
                case 'band':
                    url = '/band/page';
                    break;
                default:
                    url = '/all-device'
                    break;
            }
            window.location.href = '/wechat-record' + url;
        },
        /**获取体检报告详细*/
        /*getDevice: function () {
            var url = '/wechat-record/device-details';
            var data = {
                deviceId: this.deviceId
            };
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                device.vm.details = result.obj;
                device.vm.user = result.attributes;
            });
        },*/
    listValidDate: function (date, callback) {
            var data = {
                date: date
            }
            $.ajax({
                async : false,
                cache : false,
                type: 'GET',
                url: '/wechat-record/list-valid-date',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        // device.vm.dietData = result.obj;

                        var temp = result.attributes.date;
                        for (var i in temp) {
                            device.vm.validDate[i] = temp[i];
                        }
                        var arr = [];
                        for (var i in device.vm.validDate) {
                            arr.push(i);
                        }
                        arr = arr.sort();
                        var timeArr = [];
                        var date = null;
                        for (var i = arr.length - 1; i >= 0; i --) {
                            if (device.vm.validDate[arr[i]].length > 0) {
                                timeArr = device.vm.validDate[arr[i]];
                                date = arr[i];
                                break;
                            }
                        }
                        timeArr = timeArr.sort();
                        var time = date + (timeArr[timeArr.length - 1] > 9 ? '-' + timeArr[timeArr.length - 1] : '-0' + timeArr[timeArr.length - 1]);
                        /*if (typeof callback == 'function') {
                            callback(time);
                        }*/
                        if (timeArr.length > 0) {
                            device.vm.date = time;
                        }

                        // device.vm.initDate();
                    }
                }
            });
        },
        /**初始化日期*/
        initDate: function () {
            var now = (new Date()).Format('yyyy-MM-dd');
            $(".health-date").jeDate({
                isvalid:[device.vm.validDate, true],  //设置有效日期（已重写方法）
                minDate:'1900-01-01',
                maxDate:now,
                format:"YYYY-MM-DD",
                zIndex:199999,
                choosefun:function(elem, val) {
                    //val为获取到的时间值
                    device.vm.date = val;
                },
                success: function () {
                    $('.monthprev').on('click', function () {
                        var year =  $('.jedateyear').attr('year');
                        var month = $('.jedatemonth').attr('month');
                        var now = new Date();
                        if (Number(year) > Number(now.getFullYear()) ||
                            (Number(year) >= Number(now.getFullYear()) && Number(month) > Number(now.getMonth() + 1))) {
                            return;
                        }
                        month = month <= 9 ? '0' + month : month;
                        var date = year + '-' + month;

                        var bool = false;
                        var arr = [];
                        for (var i in device.vm.validDate) {
                            arr.push(i);
                        }
                        arr.sort();
                        for (var i in device.vm.validDate) {
                            if (arr[0] == date) {
                                break;
                            }
                            if (i == date) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            device.vm.listValidDate(date + '-01');
                        }
                    });
                    $('.monthnext').on('click', function () {
                        var year =  $('.jedateyear').attr('year');
                        var month = $('.jedatemonth').attr('month');
                        var now = new Date();
                        if (Number(year) > Number(now.getFullYear()) ||
                            (Number(year) >= Number(now.getFullYear()) && Number(month) > Number(now.getMonth() + 1))) {
                            return;
                        }
                        month = month <= 9 ? '0' + month : month;
                        var date = year + '-' + month;
                        var bool = false;
                        for (var i in device.vm.validDate) {
                            if (i == date) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            device.vm.listValidDate(date + '-01');
                        }
                    });
                    $('.ymdropul').on('click', 'li', function () {
                        var year =  $('.jedateyear').attr('year');
                        var month = $('.jedatemonth').attr('month');
                        var now = new Date();
                        if (Number(year) > Number(now.getFullYear()) ||
                            (Number(year) >= Number(now.getFullYear()) && Number(month) > Number(now.getMonth() + 1))) {
                            return;
                        }
                        month = month <= 9 ? '0' + month : month;
                        var date = year + '-' + month;
                        var bool = false;
                        for (var i in device.vm.validDate) {
                            if (i == date) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            device.vm.listValidDate(date + '-01');
                        }
                    });

                }
            })
        },
        /**获取会员健康数据*/
        getHealthData: function (date) {
            var data = {
                'date': date == null ? this.date : date
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/wechat-record/all-device',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        device.vm.list = result.obj;

                    }
                }
            });
        }
    },
    computed: {
        deviceNumber: function () {
            if (this.list.length == 0) {
                return null;
            }
            return this.list[this.list.length - 1].device;
        }
    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        }
    }
});


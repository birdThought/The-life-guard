/**
 * 心率手环独立js
 * author: wenxian.cai
 * date: 2017/11/16 15:26
 */


var device = {};

device.init = function () {
}


device.vm = new Vue({
    el: '.vue-content',
    data: {
        historyBand: [], //历史数据
        historyHeartRate: [], //历史数据
        chartBand: [], //图表数据
        chartHeartRate: [], //图表数据
        latest: {}, //最新一条数据
        page: 1,
        deviceType: null, //设备类型
        paramType: 'step',
    },

    methods: {
        /**获取设备数据
         * type 日期类型 0：不限（分页） 1：按日，2：按周，3：按月，4：按三月
         * */
        listDevice: function (type) {
            var deviceType = null;
            switch (this.deviceType) {
                case 'heartRate':
                    deviceType = 'heartrate';
                    break;
                case 'band':
                    deviceType = 'band';
                    break;
                default:
                    return;
            }
            var url = '/wechat-record/' + deviceType;
            var data = {
                type: type,
                page: this.page,
            };
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if (result.success) {
                    if (type == '0') {
                        if (device.vm.deviceType == 'band') {
                            device.vm.historyBand = result.obj;
                            device.vm.latest = device.vm.historyBand[0];
                            console.log(device.vm.latest);
                            return;
                        }
                        device.vm.historyHeartRate = result.obj;

                    } else {
                        if (device.vm.deviceType == 'band') {
                            device.vm.chartBand = result.obj;
                            return;
                        }
                        device.vm.chartHeartRate = result.obj;
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
            }catch(e) {
            }
        }
    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        }
    }
});
device.vm.$watch('latest',function(){
    bandDropdown();
},{deep:true});
var bandDropdown=function(){
    setTimeout(function(){
        $(function () {
            var page = 1;
            $('.vue-content').dropload({
                scrollArea:window,
                domUp:{
                    domClass:'dropload-up',
                    domRefresh:'<div class="dropload-refresh">↓下拉刷新</div>',
                    domUpdate:'<div class="dropload-update">↑释放更新</div>',
                    domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
                },
                domDown:{
                    domClass:'dropload-down',
                    domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',
                    domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                    domNoData: '<div class="dropload-noData">暂无数据....</div>'
                },
//	           这是里下拉加载更多数据时用到的
                loadDownFn:function(me){
                    page++;
                    var result = '';
                    $.ajax({
                        type:'GET',
                        url:"/wechat-record/band",
                        data:{
                            type:0,
                            page:page,
                            measureType:'',
                            '_':Math.random()*14
                        },
                        dataType:'json',
                        success:function(responsive){//如果没有出错就是满数据下就是8条数据 如果数据数据不足情况下就是小于8条
                            var objLen = responsive.obj.length;
                            //console.log(objLen)
                            if(objLen>0){
                                for(var i = 0; i < responsive.obj.length; i++){
                                    var stepCount= responsive.obj[i].steps;
                                    var kCal = responsive.obj[i].kcal;
                                    var mileage = responsive.obj[i].mileage;
                                    result+='<div class="item clearfix">' +
                                        '<p class="measure-date">'+gettime(responsive.obj[i].date)+'</p>' +
                                        '<div class="content">' +
                                        '<p><span>步数：'+stepCount+'步</span></p>' +
                                        '<p><span>卡路里：'+kCal+'千卡</span></p>' +
                                        '<p><span>里程：'+mileage+'公里</span></p>' +
                                        '</div>' +
                                        '</div>'
                                }
                            }else {
                                me.lock();
                                me.noData();
                            }
                            $('.band-data-history').append(result);
                            me.resetload();
                            CreateLine();
                        },
                        error:function(xhr,type){
                            alert('Ajax error');
                            me.resetload();
                        }
                    });
                },
                threshold:50
            })
        });
        function gettime(t){
            var _time = new Date(t);
            var year = _time.getFullYear();
            var month = _time.getMonth();
            var date = _time.getDate();
            var hour = _time.getHours();
            var minute = _time.getMinutes();
            var second = _time.getSeconds();
            return   year+"-"+getzf(month)+"-"+getzf(date)+" ";
        }
        function getzf(num){
            if(parseInt(num<10)){
                num = '0'+num;
            }
            return num;
        }

    },500);
};
var CreateLine = function(){
    var line = $('#line');
    var $height =$('.history').height();
    var HT = $('.measure-date');
    line.css({'position':'absolute','height':$height-50+'px','width':'0.02rem','background':'#ddd','left':'0.4rem','top':'0.3rem'});
    var round = $("<s></s>");
    round.css({'position':'absolute','width':'0.1rem','height':"0.1rem",'border-radius':'50%','border':'0.01rem solid #ccc','left':'-0.35rem','background':'#fff','z-index':'9999','top':'6px'});
    HT.append(round);
};
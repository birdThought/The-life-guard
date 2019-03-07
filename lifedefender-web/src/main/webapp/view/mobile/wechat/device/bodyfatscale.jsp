<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <link rel="stylesheet" href="/static/mobile/wechat/css/device.css">
    <link rel="stylesheet" href="/static/plugins/layui/v2.1.2/css/layui.css">
    <script src="/static/mobile/wechat/js/adaptive.js?v=1.10"></script>
    <title>体脂秤</title>
    <style>
        .lastest-content-left {
            height:2rem;
        }
        .lastest-content-right {
            padding:0.8rem 0 0.8rem 0.8rem;
        }
        .lastest-content-right li {
            font-size:0.21rem;
            color: #78909c;
            margin-bottom:0.2rem;
            line-height:1;
        }
        .lastest-content-right li span {
            font-size:0.26rem;
            color: #78909c;
            margin-right: 0.14rem;
        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
    <header class="header">
        <p>体脂秤测量结果记录</p>
    </header>
    <div class="chart" style="display: none">
        图表数据区域
    </div>
    <div class="lastest clearfix">
        <div class="lastest-content-left">
            <div class="wrap">
                <div class="circle">
                    <div class="percent left"></div>
                    <div class="percent right wth0"></div>
                </div>
                <div class="num"><span id="num">80%</span></div>
            </div>
            <div class="measureResult">
                <p class="result-show"></p>
                <p></p>
                <p class="newestdata">最新数据</p>
            </div>
        </div>
        <div class="lastest-content-right">
            <ul>
                <li>体重：<span v-html="latest.weight" :class="abnormal(latest.weight,latest.weightArea)? 'red' : ' '"></span>kg</li>
                <li>体脂肪率：<span v-html="latest.axungeRatio" :class="abnormal(latest.axungeRatio,latest.axungeRatioArea)? 'red' : ' '"></span>%</li>
                <li>BMI：<span v-html="latest.BMI" :class="abnormal(latest.BMI,latest.BMIArea)? 'red' : ' '"></span></li>
                <li>内脏脂肪：<span v-html="latest.visceralFat" :class="abnormal(latest.visceralFat,latest.visceralFatArea)? 'red' : ' '"></span></li>
                <li>腰臀比：<span v-html="latest.WHR" :class="abnormal(latest.WHR,latest.WHRArea)? 'red' : ' '"></span></li>
                <li>体年龄：<span v-html="latest.bodyage" :class="abnormal(latest.bodyage,latest.bodyageArea)? 'red' : ' '"></span>岁</li>
                <li>人体水分：<span v-html="latest.moisture" :class="abnormal(latest.moisture,latest.moistureArea)? 'red' : ' '"></span>%</li>
                <li>基础代谢：<span v-html="latest.baseMetabolism" :class="abnormal(latest.baseMetabolism,latest.baseMetabolismArea)? 'red' : ' '"></span>kcal</li>
                <li>骨骼重量：<span v-html="latest.boneWeight" :class="abnormal(latest.boneWeight,latest.boneWeightArea)? 'red' : ' '"></span>kg</li>
                <li>人体结构：<span v-html="latest.muscle" :class="abnormal(latest.muscle,latest.muscleArea)? 'red' : ' '"></span>%</li> <!-- -->
            </ul> 
        </div>
    </div>
    <div class="history">
        <div class="history-data-lists">
            <div class="item clearfix" v-for="h in history">
                <p class="measure-date">{{h.measureDate | date('yyyy-MM-dd hh:mm')}}
                    <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>
                </p>
                <div class="content">
                    <p>
                        <span>体重：<label :class="abnormal(h.weight, h.weightArea) ? 'red' : ''">{{h.weight}}</label>kg</span>
                        <span>体脂肪率：<label :class="abnormal(h.axungeRatio, h.axungeRatioArea) ? 'red' : ''">{{h.axungeRatio}}</label>%</span>
                    </p>
                    <p>
                        <span>BMI：<label :class="abnormal(h.BMI, h.BMIArea) ? 'red' : ''">{{h.BMI}}</label></span>
                        <span>内脏脂肪：<label :class="abnormal(h.visceralFat, h.visceralFatArea) ? 'red' : ''">{{h.visceralFat}}</label></span>
                    </p>
                    <p>
                        <span>腰臀比：<label :class="abnormal(h.WHR, h.WHRArea) ? 'red' : ''">{{h.WHR}}</label></span>
                        <span>体年龄：<label :class="abnormal(h.bodyage, h.bodyageArea) ? 'red' : ''">{{h.bodyage}}</label>岁</span>
                    </p>
                    <p>
                        <span>人体水分：<label :class="abnormal(h.moisture, h.moistureArea) ? 'red' : ''">{{h.moisture}}</label>%</span>
                        <span>基础代谢：<label :class="abnormal(h.baseMetabolism, h.baseMetabolismArea) ? 'red' : ''">{{h.baseMetabolism}}</label>kcal</span>
                    </p>
                    <p>
                        <span>骨骼重量：<label :class="abnormal(h.boneWeight, h.boneWeightArea) ? 'red' : ''">{{h.boneWeight}}</label>kg</span>
                        <span>人体肌肉：<label :class="abnormal(h.muscle, h.muscleArea) ? 'red' : ''">{{h.muscle}}</label>%</span>
                    </p>
                </div>
            </div>
        </div>
        <p id="line"><p>
    </div>
</div>
</body>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/mobile/wechat/js/dateFormat.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/dropload.js"></script>
<script src="/static/mobile/wechat/js/bloodfatcharts.js"></script>
<script src="/static/mobile/wechat/js/device/device-common.js"></script>
<script>
    var device = {};
    device.vm = new Vue({
        el: '.vue-content',
        data: {
            history: [], //历史数据
            latest: {}, //最新一条数据
            page: 1,
            deviceType: null//设备类型
        },
        computed:{
            str:function(){
                setTimeout(function () {
                    return this.history[1].systolic;
                },400)

            }
        },
        methods: {
            listDevice: function (type, measureType) {
                var deviceType = null;
                switch (this.deviceType) {
                    case 'tzc':
                        deviceType = 'bodyfatscale';
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
                            console.log(device.vm.latest)
                        }
                        else {
                            device.vm.chart = result.obj;
                        }
                    }

                });
            },
            abnormal: function (value, area) {
                try {
                    var arr = area.split('-');
                    if (value > arr[1] || value < arr[0]) {
                        return true;
                    }
                    return false;
                }catch(e) {
                }
            },
            loadMore:function (type,measureType) {
                this.page+=1
                this.listDevice(type,measureType)
            }
        },
        filters: {
            date: function (value, format) {
                return new Date(value).Format(format);
            }
        }
    });
    device.vm.$watch('latest',function () {
        finish();
        bodyFatScaleDrop();

    },{deep:true});
    var bodyFatScaleDrop = function(){
        setTimeout(function(){
            $(function () {
                var page = 1;
                $('.history').dropload({
                    scrollArea:window,
                    domDown:{
                        domClass:'dropload-down',
                        domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',
                        domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                        domNoData: '<div class="dropload-noData">暂无数据</div>'
                    },
//           这是里下拉加载更多数据时用到的
                    loadDownFn:function(me){
                        page++;
                        var result = '';var str = "";
                        $.ajax({
                            type:'GET',
                            url:"/wechat-record/bodyfatscale",
                            data:{
                                type:0,
                                page:page,
                                measureType:'',
                                '_':Math.random()*14
                            },
                            dataType:'json',
                            success:function(responsive){
                                var objLen = responsive.obj.length;
                                if(objLen>0){
                                    for(var i = 0; i < responsive.obj.length; i++){
                                        var data = responsive.obj[i];
                                        str+="<div class='item clearfix'>";
                                        str+="<p class='measure-date'>"+gettime(data.measureDate);
                                        str+="<span class='result_data_finally'>"+panduan(data.status)+"</span></p>";
                                        str+="<div class='content content-spec'><p>";
                                        str+="<span>体重：<label class=\"" + result_judge(data.weight,data.weightArea) +"\">" + data.weight + '</label>Kg</span></p>';
                                        str+="<p><span>体脂肪率：<label class=\"" + result_judge(data.axungeRatio,data.axungeRatioArea) + "\">" + data.axungeRatio + '</label>%</span></p>';
                                        str+="<p><span>BMI：<label class=\""+result_judge(data.BMI,data.BMIArea)+"\">" + data.BMI + "</label></span></p>";
                                        str+="<p><span>内脏脂肪：<label class=\""+result_judge(data.visceralFat,data.visceralFatArea)+"\">" + data.visceralFat + "</label></span></p>";
                                        str+="<p><span>腰臀比：<label class=\""+result_judge(data.WHR,data.WHRArea)+"\">" + data.WHR + "</label></span>";
                                        str+="<p><span>体年龄：<label class=\""+result_judge(data.bodyage,data.bodyageArea)+"\">" + data.bodyage + "</label>岁</span></p>";
                                        str+="<p><span>人体水分：<label class=\""+result_judge(data.moisture,data.moistureArea)+"\">" + data.moisture + "</label>Kg</span></p>";
                                        str+="<p><span>基础代谢：<label class=\""+result_judge(data.baseMetabolism,data.baseMetabolismArea)+"\">" + data.baseMetabolism + "</label>Kcal</span>";
                                        str+="<p><span>骨骼重量：<label class=\""+result_judge(data.boneWeight,data.boneWeightArea)+"\">" + data.boneWeight + "</label>kg</span></p>";
                                        str+="<p><span>人体肌肉：<label class=\""+result_judge(data.muscle,data.muscleArea)+"\">" + data.muscle + "</label>%</span>";
                                        str+="</p></div></div>";
                                    }
                                }else {
                                    me.lock();
                                    me.noData();
                                }
                                $('.history-data-lists').append(str);
                                me.resetload();
                                measureResult();
                                CreateLine();
                                test();
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

        },500);
    };
    /*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
    //设置设备类型

</script>
<script>
function test() {
    var result_show = $('.result_data_finally');
    $.each(result_show,function(val,key){
        if(key.innerHTML=='异常') {
            $(this).css({'background':'red','color':'#fff'})
        }else {
        	 $(this).css({'background':'#44c660','color':'#fff'})
        }
    })
}
    device.vm.deviceType = 'tzc';
    //历史数据
    device.vm.listDevice(0);
</script>
</html>

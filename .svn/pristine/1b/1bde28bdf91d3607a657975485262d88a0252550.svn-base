<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%--<meta name="flexible" content="initial-dpr=1" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <link rel="stylesheet" href="/static/mobile/wechat/css/device.css">
    <title>血脂仪</title>
    <style>
        *{margin:0;padding:0;box-sizing:border-box;list-style:none;}
        .lastest-content-right {
            padding-top: 0.3rem;
            padding-left: 0.66rem;
            height:auto;
        }
        .lastest-content-right div li {
            color: #999;
            font-size:0.21rem;
            line-height:1;
            margin-bottom:0.24rem;
            letter-spacing:0.013rem;
        }
        .lastest-content-right div li span {
            margin-right: 0.1rem;
        }
        .history .item .content p span {
            width:100%;
        }
        .red {
            color: red;
        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
	<header class="header">
		<p>血脂仪测量结果记录</p>
	</header>
    <div class="chart" style="display:none">
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
            <template>
                <div class="clearfix">
                    <ul>
                        <li>卒中风险：<span v-html="latest.lapidRatio"></span></li>
                        <li>总胆固醇：<span v-html="latest.TC" :class="abnormal(latest.TC, latest.TCArea) ? 'red' : ''"></span>mmol</li>
                        <li>甘油三酯：<span v-html="latest.TG" :class="abnormal(latest.TG, latest.TGArea) ? 'red' : ''"></span>mmHg</li>
                        <li>高密度脂蛋白：<span v-html="latest.HDL" :class="abnormal(latest.HDL, latest.HDLArea) ? 'red' : ''"></span>mmol</li>
                        <li>低密度脂蛋白：<span v-html="latest.LDL" :class="abnormal(latest.LDL, latest.LDLArea) ? 'red' : ''"></span>mmol</li>
                    </ul>
                </div>
            </template>
        </div>
    </div>
    <div class="history bloodLipid-history-data">
        <div class="history-data-lists">
            <div class="item clearfix" v-for="h in history">
                <p class="measure-date" >{{h.measureDate | date('yyyy-MM-dd hh:mm')}}
                    <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>
                </p>
                <div class="content">
                    <p>
                        <span style="width: 100%; display: inline;">卒中风险：<label>{{h.lapidRatio}}</label></span>
                    </p>
                    <p>
                        <span style="width: 100%; display: inline;">总胆固醇：<label :class="abnormal(h.TC, h.TCArea) ? 'red' : ''">{{h.TC}}</label>mmol/L</span>
                    </p>
                    <p>
                        <span style="width: 100%; display: inline;">甘油三酯：<label :class="abnormal(h.TG, h.TGArea) ? 'red' : ''">{{h.TG}}</label>mmol/L</span>
                    </p>
                    <p>
                        <span style="width: 100%; display: inline;">高密度脂蛋白胆固醇：<label :class="abnormal(h.HDL, h.HDLArea) ? 'red' : ''">{{h.HDL}}</label>mmol/L</span>
                    </p>
                    <p>
                        <span style="width: 100%; display: inline;">低密度脂蛋白胆固醇：<label :class="abnormal(h.LDL, h.LDLArea) ? 'red' : ''">{{h.LDL}}</label>mmol/L</span>
                    </p>
                </div>
            </div>
            <p id="line"></p>
        </div>

    </div>
</div>
</body>
<script src="/static/mobile/wechat/js/adaptive.js?v=1.10"></script>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/mobile/wechat/js/dateFormat.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/dropload.js"></script>
<script src="/static/mobile/wechat/js/device/device-common.js"></script>
<script src="/static/mobile/wechat/js/bloodfatcharts.js"></script>
</html>
<script>
var device = {};
device.vm = new Vue({
    el: '.vue-content',
    data: {
        history: [],
        latest: {},
        page: 1,
        deviceType: null,
        height:$(".history").height()
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
                case 'xzy':
                    deviceType = 'bloodlipid';
                    break;
                default:
                    return;
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
device.vm.$watch('latest',function () {
    finish();
    bloodipidDrop();
},{deep:true});
device.vm.$watch('height',function () {
  alert(1)
},{deep:true});
var bloodipidDrop = function(){
    setTimeout(function(){
        $(function () {
            var page = 1;
            $('.bloodLipid-history-data').dropload({
                scrollArea:window,
                domDown:{
                    domClass:'dropload-down',
                    domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',
                    domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                    domNoData: '<div class="dropload-noData">暂无数据</div>'
                },
                loadDownFn:function(me){
                    page++;
                    var result = '';
                    var str = '';
                    $.ajax({
                        type:'GET',
                        url:"/wechat-record/bloodlipid",
                        data:{
                            type:0,
                            page:page,
                            measureType:'',
                            '_':Math.random()*14
                        },
                        dataType:'json',
                        success:function(responsive){//如果没有出错就是满数据下就是8条数据 如果数据数据不足情况下就是小于8条
                            var objLen = responsive.obj.length;
                            var dataArea = [];var dataArea1=[];var dataArea2=[];var dataArea3=[];var dataArea4=[];
                            var dataObj = responsive.obj[0];
                            if(objLen>0){
                                for(var i = 0; i < responsive.obj.length; i++){
                                    var data = responsive.obj[i];
                                    /*--------------------------*/
                                    str+="<div class='item clearfix'>";
                                    str+="<p class='measure-date'>"+gettime(data.measureDate);
                                    str+="<span class='result_data_finally'>"+panduan(data.status)+"</span></p>";
                                    str+="<div class='content content-spec'><p>";
                                    str+="<span>卒中风险：<label class=\"" + result_judge(data.lapidRatio,data.lapidRatioArea) +"\">" + data.lapidRatio + '</label></span></p>';
                                    str+="<p><span>总胆固醇：<label class=\"" + result_judge(data.TC,data.TCArea) + "\">" + data.TC + '</label>mmol/L</span></p>';
                                    str+="<p><span>甘油三酯：<label class=\""+result_judge(data.TG,data.TGArea)+"\">" + data.TG + "</label>mmol/L</span></p>";
                                    str+="<p><span>高密度脂蛋白胆固醇：<label class=\""+result_judge(data.HDL,data.HDLArea)+"\">" + data.HDL + "</label>mmol/L</span></p>";
                                    str+="<p><span>低密度脂蛋白胆固醇：<label class=\""+result_judge(data.LDL,data.LDLArea)+"\">" + data.LDL + "</label>mmol/L</span>";
                                    str+="</p></div></div>";
                                }
                            }else {
                                me.lock();
                                me.noData()
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


    //设置设备类型
    device.vm.deviceType = 'xzy';
    //历史数据
    device.vm.listDevice(0);
</script>


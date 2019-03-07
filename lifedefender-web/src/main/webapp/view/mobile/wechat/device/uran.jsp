
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
    <title>尿液分析仪</title>
    <style>
    *{margin:0;padding:0;list-style:none;}
        .lastest-content-left {
            height:2rem;
        }
        .lastest-content-right {
 			padding-top:0.8rem;
            padding-left:0.8rem;
            padding-bottom:0.8rem;
        }
        .lastest-content-right li {
            font-size:0.21rem;
            color: #78909c;
            margin-bottom:0.2rem;
            line-height:1
        }
        .lastest-content-right li span {
            font-size: 0.26rem;
            color: #78909c;
            margin-right:0.14rem;
        }
    </style>
</head>
<body>
<div class="vue-content" v-cloak>
	<header class="header">
		<p>尿液分析仪测量结果记录</p>
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
            <ul>
                <li>比重: <span v-html="latest.SG" :class="abnormal(latest.SG,latest.SGArea) ? 'red' : ''"></span></li>
                <li>PH值: <span v-html="latest.pH" :class="abnormal(latest.pH,latest.pHArea) ? 'red' : ''"></span></li>
                <li>白细胞: <span v-html="latest.LEU" :class="abnormal(latest.LEU,latest.LEUArea) ? 'red' : ''"></span></li>
                <li>亚硝酸盐: <span v-html="latest.NIT" :class="abnormal(latest.NIT,latest.NITArea) ? 'red' : ''"></span></li>
                <li>尿胆原: <span v-html="latest.UBG" :class="abnormal(latest.UBG,latest.UBGArea) ? 'red' : ''"></span></li>
                <li>蛋白质: <span v-html="latest.PRO" :class="abnormal(latest.PRO,latest.PROArea) ? 'red' : ''"></span></li>
                <li>潜血: <span v-html="latest.BLD" :class="abnormal(latest.BLD,latest.BLDArea) ? 'red' : ''"></span></li>
                <li>酮体: <span v-html="latest.KET" :class="abnormal(latest.KET,latest.KETArea) ? 'red' : ''"></span></li>
                <li>胆红素: <span v-html="latest.BIL" :class="abnormal(latest.BIL,latest.BILArea) ? 'red' : ''"></span></li>
                <li>葡萄糖: <span v-html="latest.GLU" :class="abnormal(latest.GLU,latest.GLUArea) ? 'red' : ''"></span></li>
                <li>维生素C：<span v-html="latest.VC" :class="abnormal(latest.VC,latest.VCArea) ? 'red' : ''"></span></li>
            </ul>
        </div>
    </div>
    <div class="history history-data-lists">
        <div class="item clearfix" v-for="h in history">
            <p class="measure-date" >{{h.measureDate | date('yyyy-MM-dd hh:mm')}}
                <span :class="h.status == 0 ? 'bg_green' : 'bg_red'">{{h.status == 0 ? '正常' : '异常'}}</span>
            </p>
            <div class="content special">
                <p>
                    <span>比重：<label :class="abnormal(h.SG, h.SGArea) ? 'red' : ''">{{h.SG}}</label></span>
                    <span>PH值：<label :class="abnormal(h.pH, h.pHArea) ? 'red' : ''">{{h.pH}}</label></span>
                </p>
                <p>
                    <span>白细胞：<label :class="abnormal(h.LEU, h.LEUArea) ? 'red' : ''">{{h.LEU}}</label></span>
                    <span>亚硝酸盐：<label :class="abnormal(h.NIT, h.NITArea) ? 'red' : ''">{{h.NIT}}</label></span>
                </p>
                <p>
                    <span>尿胆原：<label :class="abnormal(h.UBG, h.UBGArea) ? 'red' : ''">{{h.UBG}}</label></span>
                    <span>蛋白质：<label :class="abnormal(h.PRO, h.PROArea) ? 'red' : ''">{{h.PRO}}</label></span>
                </p>
                <p>
                    <span>潜血：<label :class="abnormal(h.BLD, h.BLDArea) ? 'red' : ''">{{h.BLD}}</label></span>
                    <span>酮体：<label :class="abnormal(h.KET, h.KETArea) ? 'red' : ''">{{h.KET}}</label></span>
                </p>
                <p>
                    <span>胆红素：<label :class="abnormal(h.BIL, h.BILArea) ? 'red' : ''">{{h.BIL}}</label></span>
                    <span>葡萄糖：<label :class="abnormal(h.GLU, h.GLUArea) ? 'red' : ''">{{h.GLU}}</label></span>
                </p>
                <p>
                    <span>维生素C：<label :class="abnormal(h.VC, h.VCArea) ? 'red' : ''">{{h.VC}}</label></span>
                </p>
            </div>
        </div>
		<p id="line"></p>
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
<script src="/static/mobile/wechat/js/device/device-common.js?v=1.10"></script>
<script src="/static/mobile/wechat/js/bloodfatcharts.js"></script>
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
                    case 'uran':
                        deviceType = 'uran';
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
            },
            measureType: function (value) {
                switch (value) {
                    case 1:
                        return '早餐前';
                    case 2:
                        return '早餐后';
                    case 3:
                        return '午餐前';
                    case 4:
                        return '午餐后';
                    case 5:
                        return '晚餐前';
                    case 6:
                        return '晚餐后';
                }
            }
        }
    });
    device.vm.$watch('latest',function () {
        finish();
        URANDropLoad();
    },{deep:true});
    var URANDropLoad = function(){
        setTimeout(function(){
            $(function () {
                var page = 1;
                $('.vue-content').dropload({
                    scrollArea:window,
                    domDown:{
                        domClass:'dropload-down',
                        domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',
                        domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                        domNoData: '<div class="dropload-noData">暂无数据....</div>'
                    },
//           这是里下拉加载更多数据时用到的
                    loadDownFn:function(me){
                        page++;
                        var str = '';
                        $.ajax({
                            type:'GET',
                            url:"/wechat-record/uran",
                            data:{
                                type:0,
                                page:page,
                                measureType:'',
                                '_':Math.random()*14
                            },
                            dataType:'json',
                            success:function(responsive){//如果没有出错就是满数据下就是8条数据 如果数据数据不足情况下就是小于8条
                                var objLen = responsive.obj.length;
                                if(objLen>0){
                                    for(var i = 0; i < responsive.obj.length; i++){
                                        var data = responsive.obj[i];
                                        str+="<div class='item clearfix'>";
                                        str+="<p class='measure-date'>"+gettime(data.measureDate);
                                        str+="<span class='result_data_finally'>"+panduan(data.status)+"</span></p>";
                                        str+="<div class='content content-spec'>";
                                        str+="<p><span>比重：<label class=\"" + result_judge(data.SG,data.SGArea) + "\">" + data.SG + '</label></span>';
                                        str+="<span>PH：<label class=\"" + result_judge(data.pH,data.pHArea) + "\">" + data.pH + '</label></span></p>';
                                        str+="<p><span>白细胞：<label>" + data.LEU + "</label></span>";
                                         str+="<span>亚硝酸盐：<label>" + data.NIT + '</label></span></p>';
                                        str+="<p><span>尿胆原：<label >" + data.UBG + '</label></span>';
                                        str+="<span>蛋白质：<label>" + data.PRO + '</label></span></p>';
                                        str+="<p><span>潜血：<label>" + data.BLD + '</label></span>';
                                        str+="<span>酮体：<label>" + data.KET + '</label></span></p>';
                                        str+="<p><span>胆红素：<label>" + data.BIL + '</label></span>';
                                        str+="<span>葡萄糖：<label>" + data.GLU + '</label></span></p>';
                                        str+="<p><span>维生素C：<label>" + data.VC + '</label></span></p>';
                                        str+="</div></div>";
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

        },300);
    };
</script>
<script>
   function isNormal (str) {
        return str.split('-')
    };
    function test() {
        var result_show = $('.result_data_finally');
        $.each(result_show,function(val,key){
            if(key.innerHTML=='异常') {
                $(this).css({'background':'red','color':'#fff'});
            }else {
                $(this).css({'background':'#44c660','color':'#fff'})
            }
        })
    }
    //设置设备类型
    device.vm.deviceType = 'uran';
    //历史数据
    device.vm.listDevice(0);
</script>
</html>

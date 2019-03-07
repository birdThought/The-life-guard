/*
 * generalFunction:健康包设备js通用类
 */
'use strict';

// 选项栏切换内容
/* 健康包设备绑定情况 */
/** 初始化为心率 */
var old_divId = "xl";
/*各个设备的分页静态初始vue*/
var vueList={
        xl: null,
        fhy:null,
        xty: null,
        xyy: null,
        xyj: null,
        tzc: null,
        twj: null,
        getXl:function(){
            if(this.xl==null){
                this.xl=new Vue({
                    el: '#xl_table',
                    data: {
                        results: null,
                        type: null,
                    }, 
                    methods: {
                       addTipsTool: function() {
                           this.$nextTick(function(){
                               $(".customTip").poshytip({
                                   className: 'tip-yellowsimple',
                                   bgImageFrameSize: 9,
                                   followCursor: false,
                                   alignTo: 'target',
                                   alignX: 'center',
                               });
                           })
                       } 
                    },
                    filters: {
                        reply: function (value) {
                            if (value == undefined)
                                return "无"
                            return value;
                        }
                    }, ready: function() {
                        console.log($(".customTip"));
                        
                    }
                });
            }
            return this.xl;
        },
        getFhy:function(){
            if(this.fhy==null){
                this.fhy=new Vue({
                    el: '#fhy_table',
                    data: {
                        results: null,
                    }, 
                    methods: {
                        addTipsTool: function() {
                            this.$nextTick(function(){
                                $(".customTip").poshytip({
                                    className: 'tip-yellowsimple',
                                    bgImageFrameSize: 9,
                                    followCursor: false,
                                    alignTo: 'target',
                                    alignX: 'center',
                                });
                            })
                        } 
                     }, filters: {
                        reply: function (value) {
                            if (value == undefined)
                                return "无"
                            return value;
                        }
                    }
                });
            }
            return this.fhy;
        },
        getXty:function(){
            if(this.xty==null){
                this.xty=new Vue({
                    el: '#xty_table',
                    data: {
                        results: null,
                    }, 
                    methods: {
                        addTipsTool: function() {
                            this.$nextTick(function(){
                                $(".customTip").poshytip({
                                    className: 'tip-yellowsimple',
                                    bgImageFrameSize: 9,
                                    followCursor: false,
                                    alignTo: 'target',
                                    alignX: 'center',
                                });
                            })
                        } 
                     },
                    filters: {
                        reply: function (value) {
                            if (value == undefined)
                                return "无"
                            return value;
                        }
                    },
                });
            }
            return this.xty;
        },
        getXyy:function(){
            if(this.xyy==null){
                this.xyy=new Vue({
                    el: '#xyy_table',
                    data: {
                        results: null,
                    }, 
                    methods: {
                        addTipsTool: function() {
                            this.$nextTick(function(){
                                $(".customTip").poshytip({
                                    className: 'tip-yellowsimple',
                                    bgImageFrameSize: 9,
                                    followCursor: false,
                                    alignTo: 'target',
                                    alignX: 'center',
                                });
                            })
                        } 
                     },
                    filters: {
                        reply: function (value) {
                            if (value == undefined)
                                return "无"
                            return value;
                        }
                    },
                });
            }
            return this.xyy;
        },
        getXyj:function(){
            if(this.xyj==null){
                this.xyj=new Vue({
                    el: '#xyj_table',
                    data: {
                        results: null,
                    }, 
                    methods: {
                        addTipsTool: function() {
                            this.$nextTick(function(){
                                $(".customTip").poshytip({
                                    className: 'tip-yellowsimple',
                                    bgImageFrameSize: 9,
                                    followCursor: false,
                                    alignTo: 'target',
                                    alignX: 'center',
                                });
                            })
                        } 
                     }, filters: {
                        reply: function (value) {
                            if (value == undefined)
                                return "无"
                            return value;
                        }
                    }
                });
            }
            return this.xyj;
        },
        getTzc:function(){
            if(this.tzc==null){
                this.tzc=new Vue({
                    el: '#tzc_table',
                    data: {
                        results: null,
                    }, 
                    methods: {
                        addTipsTool: function() {
                            this.$nextTick(function(){
                                $(".customTip").poshytip({
                                    className: 'tip-yellowsimple',
                                    bgImageFrameSize: 9,
                                    followCursor: false,
                                    alignTo: 'target',
                                    alignX: 'center',
                                });
                            })
                        } 
                     }, filters: {
                        reply: function (value) {
                            if (value == undefined)
                                return "无"
                            return value;
                        }
                    }
                });
            }
            return this.tzc;
        },
        getTwj:function(){
            if(this.twj==null){
                this.twj=new Vue({
                    el: '#twj_table',
                    data: {
                        results: null,
                    }, 
                    methods: {
                        addTipsTool: function() {
                            this.$nextTick(function(){
                                $(".customTip").poshytip({
                                    className: 'tip-yellowsimple',
                                    bgImageFrameSize: 9,
                                    followCursor: false,
                                    alignTo: 'target',
                                    alignX: 'center',
                                });
                            })
                        } 
                     }, filters: {
                        reply: function (value) {
                            if (value == undefined)
                                return "无"
                            return value;
                        }
                    }
                });
            }
            return this.twj;
        }
}
/* 全局date格式转换*/
Vue.filter('date', function (value, fmt) {
    return new Date(value).Format(fmt);
});
/* 异常数据过滤 */
Vue.filter('formatData', function (paramData,area) {
    var array = new Array();
    array = area.toString().split("-");
    if (paramData < parseFloat(array[0]) || paramData > parseFloat(array[1])) {
        paramData = paramData + "(" + area + ")";
    }
    return paramData;
});
    
define(function(require) {
    require('heartRateBracelet');
    require('healthDataUtil');
    require('highstock');
    require('dateFormat');
    require('icheck');
    require('jQueryPoshytip');
//  require('jqueryBase64');
    
    // 预加载处理是否勾选健康包
    var healthTypes = [
        "BloodPressure",
        "Lunginstrument",
        "Glucometer",
        "Oxygen",
        "BodyFatScale",
        "Band",
        "Temperature"
    ];
    generalFunction.deviceType = "APP";
    jQuery.ajax({
        type : 'GET',
        url : 'healthDataControl.do?isHealthDataBinded',
        data : 'healthType=' + healthTypes,
        before : function() {
            layer.load(1);
        },
        success : function(result) {
            var deviceData_xl = result.attributes.Band;
            var deviceData_fhy = result.attributes.Lunginstrument;
            var deviceData_xty = result.attributes.Glucometer;
            var deviceData_xyy = result.attributes.Oxygen;
            var deviceData_xyj = result.attributes.BloodPressure;
            var deviceData_tzc = result.attributes.BodyFatScale;
            var deviceData_twj = result.attributes.Temperature;

            var mapLength = Object
                    .getOwnPropertyNames(result.attributes).length;
            var map = result.attributes;
            /* 显示默认页面 */
            if (deviceData_xl == "false") {
                if (deviceData_fhy == "true") {
                    $("#fhy").removeClass('content2 nav_none');
                    $("#fhy").addClass('content1');
                    showFirstView("fhy");
                    old_divId = "fhy";
                } else {
                    if (deviceData_xty == "true") {
                        $("#xty").removeClass('content3 nav_none');
                        $("#xty").addClass('content1');
                        showFirstView("xty");
                        old_divId = "xty";
                    } else {
                        if (deviceData_xyy == "true") {
                            $("#xyy").removeClass('content4 nav_none');
                            $("#xyy").addClass('content1');
                            $("#xyy .nav_tab").css('width', '215px');
                            showFirstView("xyy");
                            old_divId = "xyy";
                        } else {
                            if (deviceData_xyj == "true") {
                                $("#xyj").removeClass(
                                        'content5 nav_none');
                                $("#xyj").addClass('content1');
                                showFirstView("xyj");
                                old_divId = "xyj";
                            } else {
                                if (deviceData_tzc == "true") {
                                    $("#tzc").removeClass(
                                            'content6 nav_none');
                                    $("#tzc").addClass('content1');
                                    showFirstView("tzc");
                                    old_divId = "tzc";
                                } else {
                                    if (deviceData_twj == "true") {
                                        $("#temperature").removeClass(
                                                'content7 nav_none');
                                        $("#temperature").addClass(
                                                'content1');
                                        showFirstView("twj");
                                        old_divId = "twj";
                                    }
                                }
                            }
                        }
                    }
                }

            }
            /* 判断是否加载页面 */
            var viewBlock = "false";
            for ( var key in map) {
                if (map.hasOwnProperty(key)) {
                    if (map[key] == "true") { // 有任一设备为True，则显示Title
                        viewBlock = map[key];
                        jQuery("ul.nav_shebei>li.healthyTitle").eq(0).css('display', 'block');
                        jQuery("ul.nav_shebei>li.healthySms").eq(0).css('display', 'block');
                        
//                      $(".healthyTitle").css('display', 'block');
                    }
                    if (key == "Band") {
                        if (map[key] == "false") {
                            $(".healthyXl").css('display', 'none');
                            $(".healthySz").css('display', 'none');
                            $("ul.nav_shebei>li.healthyTitle").eq(1).css({'display':'none'});
                        } else {
                            $(".healthyXl").css('display', 'block');
                            $("#xl").css('display', '');
                            $(".healthySz").css('display', 'block');
                            showFirstView("xl");
                            $("ul.nav_shebei>li.healthyTitle").eq(1).css({'display':'block'});
                            require.async(['remind'], function() {
                                // 提醒设置
                                remind.eventBind("band");
                            })
                        }
                    } else if (key == "Lunginstrument") {
                        if (map[key] == "false") {
                            $(".healthyFhy").css('display', 'none');
                        } else {
                            $(".healthyFhy").css('display', 'block');
                            $("#fhy").css('display', '');
                            $(".addData").css('display', '');
                        }
                    } else if (key == "Glucometer") {
                        if (map[key] == "false") {
                            $(".healthyXty").css('display', 'none');
                        } else {
                            $(".healthyXty").css('display', 'block');
                            $("#xty").css('display', '');
                            $(".addData").css('display', '');
                        }
                    } else if (key == "Oxygen") {
                        if (map[key] == "false") {
                            $(".healthyXyy").css('display', 'none');
                        } else {
                            $(".healthyXyy").css('display', 'block');
                            $("#xyy").css('display', '');
                            $(".addData").css('display', '');
                        }
                    } else if (key == "BodyFatScale") {
                        if (map[key] == "false") {
                            $(".healthyTzc").css('display', 'none');
                        } else {
                            $(".healthyTzc").css('display', 'block');
                            $("#tzc").css('display', '');
                            $(".addData").css('display', '');
                        }
                    } else if (key == "BloodPressure") {
                        if (map[key] == "false") {
                            $(".healthyXyj").css('display', 'none');
                            // $("#xyj").css('display','none');
                        } else {
                            $(".healthyXyj").css('display', 'block');
                            $("#xyj").css('display', '');
                            $(".addData").css('display', '');
                        }
                    } else if (key == "Temperature") {
                        if (map[key] == "false") {
                            $(".healthyTwj").css('display', 'none');
                            // $("#xyj").css('display','none');
                        } else {
                            $(".healthyTwj").css('display', 'block');
                            $("#temperature").css('display', '');
                            $(".addData").css('display', '');
                        }
                    }
                }
            }
            if (deviceData_xl == "true")
                $(".addData").css('display', 'none');
            if (viewBlock == "false") { // 如果6个设备都为false，则显示提示语句
                $(".tishi").css('display', 'block');
            }
        }
    });

    /** 设置选项栏中的勾选按钮样式 */
    $('input').iCheck({
        checkboxClass : 'icheckbox_minimal-green'
    });
    
    _clickTab("xyj");
    _clickTab("xyy");
    _clickTab("xl");
    _clickTab("tzc");
    
    jQuery('ul.nav_shebei').on('click', 'li', function() {
        var $this = jQuery(this);
        var dataId = $this.attr('data-id');
        if(dataId != null) {
            _clickNav(this);
        }
    });
    
    /** 通过点击事件切换样式显示内容 */
    function _clickNav(obj) {
        var divId = $(obj).attr('data-id');
        /* 根据传入的divId获取不同的数据 */
        if ("xyj" == divId) {
            require.async(['bloodPressureMeter'], function() {
                _clickXyj_1();
            });
        }
        if ("fhy" == divId) {
            require.async(['lungInstrument'], function() {
                _clickFhy_1();
            });
        }
        if ("xty" == divId) {
            require.async(['bloodGlucoseMeter'], function() {
                _clickXty_1();
            });
        }
        if ("xyy" == divId) {
            require.async(['oxiMeter'], function() {
                _clickXyy_1();
            });
        }
        if ("xl" == divId) {
            require.async(['heartRateBracelet'], function() {
                $("#xl").addClass('content1');
                _clickXl_1();
            });
        }
        if ("tzc" == divId) {
            require.async(['bodyFatScale'], function() {
                _clickTzc_1();
            });
        }
        if ("temperature" == divId) {
            require.async(['temperature'], function() {
                _clickTwj_1();
            });
        }
        if ("remind" == divId) {
            require.async(['remind'], function() {
                // 提醒设置
                remind.getData("band");
            })
        }
        /*if ("sms" == divId) {
            require.async(['smsWarning'], function() {
                // 短信预警
                smsWarning.bindEvent();
                smsWarning.getWarningReceive();
                smsWarning.getWarningDevice();
            })
            
        }*/
        if (old_divId != divId) {
            $("#" + old_divId).addClass("nav_none");
            old_divId = divId;
            $("#" + divId).removeClass("nav_none");
            /** 如果点击的是设置按钮或者心率手环，需要把.addData隐藏 */
            if ((divId == 'remind') || (divId == 'xl')) {
                $(".addData").hide();
            } else {
                $(".addData").show();
            }
        }
        $(obj).addClass("action");
        $(obj).siblings("li").removeClass("action");
    }
    
    // TimePicker
    jQuery('ul.setNotice').on('click', '#timer', function() {
        var $this = this;
        require.async(['timePicker'], function() {
            TimePicker.showTimePicker($this);
        });
    });
    
    /* 默认显示数据 */
    function showFirstView(type) {
        switch (type) {
        case "xl":
            require.async(['heartRateBracelet'], function() {
                _clickXl_1();
            });
            break;
        case "fhy":
            require.async(['lungInstrument'], function() {
                _clickFhy_1();
            });
            break;
        case "xty":
            require.async(['bloodGlucoseMeter'], function() {
                _clickXty_1();
            });
            break;
        case "xyy":
            require.async(['oxiMeter'], function() {
                _clickXyy_1();
            });
            break;
        case "xyj":
            require.async(['bloodPressureMeter'], function() {
                _clickXyj_1();
            });
            break;
        case "tzc":
            require.async(['bodyFatScale'], function() {
                _clickTzc_1();
            });
            break;
        case "twj":
            require.async(['temperature'], function() {
                _clickTwj_1();
            });
            break;
        }
    }
});



$('.addData').click(function() {
    // 通过选择的健康包类型来构建表单
    $("#xl").removeClass('content1');
    var timeStr = new Date().Format("yyyy-MM-dd hh:mm:ss");
    var _id = $("div.nav_cont").find("div[class^=content]")
            .not(".nav_none").attr("id");
    var li_2 = $("<li><label>测量时间</label><input  name='dateTime' id='localDate' value='"
            + timeStr
            + "' onclick='showDate()' readonly/></li>");

    $(".setParam").empty();
    switch (_id) {
    case "xyj":
        var li_1 = $("<li><label>设备类型</label><input type='text' value='血压计' disabled /></li>");
        var li_3 = $("<li><label>收缩压</label><input name='ssy' type='text' /></li>");
        var li_4 = $("<li><label>舒张压</label><input name='szy' type='text' /></li>");
        var li_5 = $("<li><label>心率</label><input name='xl' type='text' /></li>");
        $(".setParam").append(li_1);
        $(".setParam").append(li_2);
        $(".setParam").append(li_3);
        $(".setParam").append(li_4);
        $(".setParam").append(li_5);
        break;
    case "fhy":
        var li_1 = $("<li><label>设备类型</label><input type='text' value='肺活仪' disabled /></li>");
        var li_3 = $("<li><label>肺活量</label><input name='fhl' type='text' /></li>");
        $(".setParam").append(li_1);
        $(".setParam").append(li_2);
        $(".setParam").append(li_3);
        break;
    case "xty":
        var li_1 = $("<li><label>设备类型</label><input type='text' value='血糖仪' disabled /></li>");
        var li_3 = $("<li><label>血糖含量</label><input name='xthl' type='text' /></li>");
        var li_4 = $("<li><label>测量类型</label><select name = 'xtyType'>" +
                    "<option value = '1'>早餐前</option><option value = '2'>早餐后</option>" +
                    "<option value = '3'>午餐前</option><option value = '4'>午餐后</option>" +
                    "<option value = '5'>晚餐前</option><option value = '6'>晚餐后</option>" +
                    "</select></li>");
        $(".setParam").append(li_1);
        $(".setParam").append(li_2);
        $(".setParam").append(li_3);
        $(".setParam").append(li_4);
        break;
    case "xyy":
        var li_1 = $("<li><label>设备类型</label><input type='text' value='血氧仪' disabled /></li>");
        var li_3 = $("<li><label>血氧</label><input name='xy' type='text' /></li>");
        var li_4 = $("<li><label>心率</label><input name='xl' type='text' /></li>");
        $(".setParam").append(li_1);
        $(".setParam").append(li_2);
        $(".setParam").append(li_3);
        $(".setParam").append(li_4);
        break;
    case "xl":
        var li_1 = $("<li><label>设备类型</label><input type='text' value='手环' disabled /></li>");
        var li_3 = $("<li><label>计步</label><input name='jb' type='text' /></li>");
        var li_4 = $("<li><label>心率</label><input name='xl' type='text' /></li>");
        var li_5 = $("<li><label>睡眠</label><input name='sm' type='text' /></li>");
        $(".setParam").append(li_1);
        $(".setParam").append(li_2);
        $(".setParam").append(li_3);
        $(".setParam").append(li_4);
        $(".setParam").append(li_5);
        break;
    case "tzc":
        var li_1 = $("<li><label>设备类型</label><input type='text' value='体脂秤' disabled /></li>");
        var li_3 = $("<li><label>体重</label><input name='tz' type='text' /></li>");
        var li_4 = $("<li><label>体脂肪率</label><input name='tzfl' type='text' /></li>");
        var li_5 = $("<li><label>腰臀比</label><input name='ytb' type='text' /></li>");
        var li_6 = $("<li><label>BMI</label><input name='bmi' type='text' /></li>");
        var li_7 = $("<li><label>去脂体重</label><input name='qztz' type='text' /></li>");
        var li_8 = $("<li><label>人体水分</label><input name='rtsf' type='text' /></li>");
        var li_9 = $("<li><label>人体肌肉</label><input name='rtjr' type='text' /></li>");
        var li_10 = $("<li><label>骨骼重量</label><input name='ggzl' type='text' /></li>");
        var li_11 = $("<li><label>基础代谢</label><input name='jcdx' type='text' /></li>");
        var li_12 = $("<li><label>体年龄</label><input name='tnl' type='text' /></li>");
        var li_13 = $("<li><label>内脏脂肪</label><input name='nzzf' type='text' /></li>");
        $(".setParam").append(li_1);
        $(".setParam").append(li_2);
        $(".setParam").append(li_3);
        $(".setParam").append(li_4);
        $(".setParam").append(li_5);
        $(".setParam").append(li_6);
        $(".setParam").append(li_7);
        $(".setParam").append(li_8);
        $(".setParam").append(li_9);
        $(".setParam").append(li_10);
        $(".setParam").append(li_11);
        $(".setParam").append(li_12);
        $(".setParam").append(li_13);
        break;
    case "temperature":
        var li_1 = $("<li><label>设备类型</label><input type='text' value='体温计' disabled /></li>");
        var li_3 = $("<li><label>体温</label><input name='tw' type='text' /></li>");
        $(".setParam").append(li_1);
        $(".setParam").append(li_2);
        $(".setParam").append(li_3);
        break;
    }

    var li_last = $("<li><label>说明</label><p><small>填写参数后，系统才可根据参数分析您通过设备检测出的数值，判断您实时的身体状况</small></p></li>");
    var _hidden = $("<input name='healthTypeCode' type='hidden' value='"
            + _id + "'>");
    $(".setParam").append(_hidden);
    $(".setParam").append(li_last);
    /** 打开模态框 */
    layer.open({
        type : 1,
        content : $('.setParam'), // 这里content是一个DOM
        title : [ '手动添加数据',
                'text-align:center;font-size:16px' ],
        btn : [ '确定', '取消' ],
        moveType : 1,
        closeBtn : 1, // 打开关闭按钮
        area : '470px',
        scrollbar : false,
        success : function() {
            jeDate({
                dateCell : "#localDate",
                format : "YYYY-MM-DD hh:mm:ss",
                isinitVal : true,
                initAddVal : [ 0 ],
                minDate : "1900-01-01 00:00:00",
                maxDate : jeDate.now(0),
                startMin : "1900-01-01 00:00:00",
                startMax : jeDate.now(0),
                zIndex : 99999999999999,
                choosefun : function(elem, val) {
                    // val为获取到的时间值
                }
            });
        },
        yes : function(index) {
            var _healthTypeCode = $(
                    "input[name='healthTypeCode']")
                    .val();
            var data = {};
            switch (_healthTypeCode) {
            case "xyj":// 血压
                var szy = parseFloat($.trim($(
                        "[name='szy']").val()));
                var ssy = parseFloat($.trim($(
                        "[name='ssy']").val()));
                var xl = parseFloat($.trim($(
                        "[name='xl']").val()));
                if (szy == '' || ssy == '' || xl == ''
                        || isNaN(szy) || isNaN(ssy)
                        || isNaN(xl)) {
                    layer.msg("请输入正确的数量值！");
                    return;
                }
                if (szy < 0 || ssy < 0 || xl < 0) {
                    layer.msg("数量值不能为负数");
                    return;
                }

                var bloodPressure = {
                    diastolic : szy,
                    heartRate : xl,
                    systolic : ssy
                }

                data.healthType = "BloodPressure";
                data.bloodPressure = bloodPressure;

                break;
            case "fhy":// 肺活仪
                var fhl = parseFloat($.trim($(
                        "[name='fhl']").val()));
                if (fhl == "" || isNaN(fhl)) {
                    layer.msg("请输入正确的数量值！");
                    return;
                }
                if (fhl < 0) {
                    layer.msg("数量值不能为负数");
                    return;
                }

                var Lunginstrument = {
                    vitalCapacity : fhl
                }

                data.healthType = "Lunginstrument";
                data.lunginstrument = Lunginstrument;

                // _data =
                // '{"healthType":"Lunginstrument","vitalCapacity":'
                // + fhl + ',';
                break;
            case "xty":
                var xty = $.trim($("[name='xthl']")
                        .val());
                if (xty == '' || isNaN(xty)) {
                    layer.msg("请输入正确的数量值！");
                    return;
                }
                if (xty < 0) {
                    layer.msg("数量值不能为负数");
                    return;
                }
                var measureType = $.trim($("select[name='xtyType']  option:selected").attr("value"));
                console.log("measureType:"+$.trim($("select[name='xtyType']  option:selected").attr("value")));
                var Glucometer = {
                    bloodSugar : xty,
                    measureType : measureType
                }

                data.healthType = "Glucometer";
                data.glucometer = Glucometer;

                // _data =
                // '{"healthType":"Glucometer","bloodSugar":'
                // + xty + ',';
                break;
            case "xyy":
                var xl = parseFloat($.trim($(
                        "[name='xl']").val()));
                var xy = parseFloat($.trim($(
                        "[name='xy']").val()));
                if (xl == '' || xy == '' || isNaN(xl)
                        || isNaN(xy)) {
                    layer.msg("请输入正确的数量值！");
                    return;
                }
                if (xl < 0 || xy < 0) {
                    layer.msg("数量值不能为负数");
                    return;
                }

                var Oxygen = {
                    heartRate : xl,
                    saturation : xy
                }

                data.healthType = "Oxygen";
                data.oxygen = Oxygen;
                break;
            case "xl":
                return; // 暂未提供方法
            case "tzc":
                var tzfl = parseFloat($.trim($(
                        "[name='tzfl']").val()));
                var jcdx = ($.trim($("[name='jcdx']")
                        .val()));
                var bmi = parseFloat($.trim($(
                        "[name='bmi']").val()));
                var tnl = ($.trim($("[name='tnl']")
                        .val()));
                var ggzl = parseFloat($.trim($(
                        "[name='ggzl']").val()));
                var qztz = parseFloat($.trim($(
                        "[name='qztz']").val()));
                var rtsf = parseFloat($.trim($(
                        "[name='rtsf']").val()));
                var rtjr = parseFloat($.trim($(
                        "[name='rtjr']").val()));
                var tz = parseFloat($.trim($(
                        "[name='tz']").val()));
                var ytb = parseFloat($.trim($(
                        "[name='ytb']").val()));
                var nzzf = parseFloat($.trim($("[name='nzzf']").val()));
                if (tzfl == '' || jcdx == ''
                        || bmi == '' || tnl == ''
                        || ggzl == '' || qztz == ''
                        || rtsf == '' || rtjr == ''
                        || tz == '' || tz == ''
                        || ytb == '' || nzzf == ''
                        || isNaN(tzfl) || isNaN(jcdx)
                        || isNaN(bmi) || isNaN(tnl)
                        || isNaN(ggzl) || isNaN(qztz)
                        || isNaN(rtsf) || isNaN(rtjr)
                        || isNaN(tz) || isNaN(ytb)
                        || isNaN(nzzf)) {
                    layer.msg("请输入正确的数量值！");
                    return;
                }
                if (tzfl < 0 || jcdx < 0 || bmi < 0
                        || tnl < 0 || ggzl < 0
                        || qztz < 0 || rtsf < 0
                        || rtjr < 0 || tz < 0 || tz < 0
                        || ytb < 0 || nzzf < 0) {
                    layer.msg("数量值不能为负数");
                    return;
                }

                var BodyFatScale = {
                    axungeRatio : tzfl,
                    baseMetabolism : jcdx,
                    BMI : bmi,
                    bodyage : tnl,
                    boneWeight : ggzl,
                    fatFreeWeight : qztz,
                    moisture : rtsf,
                    muscle : rtjr,
                    weight : tz,
                    WHR : ytb,
                    visceralFat : nzzf
                }

                data.healthType = "BodyFatScale";
                data.bodyFatScale = BodyFatScale;
                break;
            case "temperature":
                var tx = parseFloat($.trim($(
                        "[name='tw']").val()));
                if (tx == '' || isNaN(tx)) {
                    layer.msg("请输入正确的数量值！");
                    return;
                }
                if (tx < 0) {
                    layer.msg("数量值不能为负数");
                    return;
                }

                var Temperature = {
                    temperature : tx
                }

                data.healthType = "Temperature",
                        data.temperature = Temperature;
            }
            if ($("[name='dateTime']").val() == "") {
                layer.msg("请完善测量时间");
                return;
            }

            data.measureDate = $("[name='dateTime']").val();
            
            $.ajax({
                type : 'POST',
                contentType : 'application/json; charset=utf-8',
                url : 'terminalCommandControl.do?addHealthData',
                data : JSON.stringify(data),
                before : function() {
                    layer.load(1);
                },
                success : function(result) {
                    layer.msg(result.msg);
                    setTimeout(
                            function() {
                                layer
                                        .closeAll("loading");
                                layer
                                        .close(index);
                            }, 1000);
                    switch (_healthTypeCode) {
                    case "xyj":
                        _clickXyj_1();
                        break;
                    case "xyy":
                        _clickXyy_1();
                        break;
                    case "xty":
                        _clickXty_1();
                        break;
                    case "fhy":
                        _clickFhy_1();
                        break;
                    case "tzc":
                        _clickTzc_1();
                        break;
                    case "temperature":
                        _clickTwj_1();
                        break;

                    default:
                        break;
                    }
                }
            });
        }
    });

    
});
function _clickTab(id) {
    $('#'+id+' .nav_tab li').click(function() {
        $(this).children('a').css('color', '#fff').parent().siblings().children('a').css('color', '');
        $(this).addClass('current').siblings('li').removeClass('current');
        $('#'+id+' .nav_content > div').eq($(this).index()).show().siblings('div').hide();
    });
}

$('.period li').click(function() {
    $(this).addClass('current').siblings('li').removeClass('current');
});

// table导出为excel
function tableToExcel(tableId) {
    jQuery('#' + tableId).tableExport({
        type : 'excel',
        escape : 'false'
    });
}

/* 展示日期控件 */
function showDate() {
    
}

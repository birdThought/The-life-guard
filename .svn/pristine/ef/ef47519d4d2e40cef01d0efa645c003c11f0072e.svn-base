
registerController.controller('vipMemberController',['$scope', '$rootScope', function ($scope, $rootScope) {
    /** 声明参数*/
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0,
        totalPage: 0
    }
    $scope.conditions = {
        gender: null,
        startAge: null,
        endAge: null,
        vipComboId: null,
        status: "0",
        keyword: null,
        todayAbnormal: null,
        isEndTime: null,
        todayNotMeasure: null,
        monthNotMeasure: null
    }
    $scope.userList = null;
    $scope.ageArr = [];
    $scope.vipCombo = null;

    $scope.healthData = null;
    $scope.physicalData = null;
    $scope.medicalData = null;
    $scope.dietData = null;
    $scope.date = new Date().Format('yyyy-MM-dd') + '';
    $scope.currentMember = {};
    $scope.currentType = 1;
    $scope.validDate = {};
    $scope.isChange = {};
    $scope.history = {
        showDevice: null,
        paramType: null,
        dateType: null,
        data: [],
        weekData: null,
        monthData: null,
        threeMonthData: null,
        xtyHasData: true,   //血糖图表是否有数据
        page: 1,
        pageData: [],
        finished: true
    };
    $scope.medical = {
        pageIndex: 1,
        totalPage: 1,
        totalSize: 0,
        pageObj: null,
        data: null,
    };
    $scope.physical = {
        pageIndex: 1,
        totalPage: 1,
        totalSize: 0,
        pageSize: 6,
        data: null,
    };
    $scope.analysis = {
        content: null,
        doctorSign: null,
    }
    $scope.analysisList = null;
    $scope.serveRecord = {
        serveDate: new Date().Format('yyyy-MM-dd'),
        serveContent: null,
        remark: null,
        page: 1,
        list: null,
    }

    /** 声明函数 */
    /*初始化*/
    $scope.init = function () {
        $scope.listUser();
        $scope.listVipCombo();
        setTimeout(function () {
            $scope.initPage();
        }, 300);

        for (var i = 1; i < 100; i ++) {
            $scope.ageArr.push(i);
        }

        //饮食记录
        $(".tab-content-1 > div").not("div:first").css("display","none")
        $(".tab-1 li").on("click",function(){
            $(this).addClass("on").siblings().removeClass("on");
            $(".tab-content-1 > div").css("display","none").eq($(this).index()).css("display","block");
        })

        $scope.initServeRecordDate($('.serve-record-date'));
     }

    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                console.log('wait for laypage...');
                laypage.render({
                    elem: 'page'
                    ,count: $scope.page.totalSize
                    ,limit: $scope.page.pageSize
                    ,theme: '#00bfff'
                    ,layout: ['count', 'prev', 'page', 'next', 'skip']
                    ,jump: function(obj, first){
                        if(!first){
                            $scope.page.pageIndex = obj.curr;
                            $scope.listUser();
                        }

                    }
                });
            }, 300);
        } else {
            laypage.render({
                elem: 'page'
                ,count: $scope.page.totalSize
                ,limit: $scope.page.pageSize
                ,theme: '#00bfff'
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,jump: function(obj, first){
                    if(!first){
                        $scope.page.pageIndex = obj.curr;
                        $scope.listUser();
                    }
                }
            });
        }

    }
    /*获取用户列表*/
    $scope.listUser = function () {
        var url = '/combo/member/list/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        data.todayAbnormal = data.todayAbnormal == false ? null : data.todayAbnormal;
        data.isEndTime = data.isEndTime == false ? null : data.isEndTime;
        data.todayNotMeasure = data.todayNotMeasure == false ? null : data.todayNotMeasure;
        data.monthNotMeasure = data.monthNotMeasure == false ? null : data.monthNotMeasure;
        http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.userList = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            });
        });
    }
    /*查询*/
    $scope.search = function () {
        $scope.listUser();
    }
    /*获取套餐列表*/
    $scope.listVipCombo = function () {
        var url = '/combo/list/id';
        http.ajax.get_no_loading(true, true, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.vipCombo = result.obj;
            $scope.$apply();
        });
    }


    /*弹出会员信息窗口*/
    $scope.popupMemberDialog = function (user) {
        try {
            setTimeout(function () {
                $(".health-data-health").click();
            }, 200)
            $scope.clickTab()
        } catch (error) {
            console.log(error)
        }

       //
        $scope.currentMember = user;
         $scope.currentType = 1;
        $scope.listValidDate((new Date()).Format('yyyy-MM-dd'));
        $scope.initDate();
        $scope.isChange = {
            1: true,
            2: true,
            3: true,
            4: true
        }
        layer.open({
            id: 'member-dialog-content',
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['880px', '700px'], //宽高
            maxmin: true, //开启最大化最小化按钮
            zIndex: 999,
            content: $('.member-dialog'),
            scrollbar: false,
            cancel: function () {
                $scope.healthData = null;
                // $scope.physicalData = null;
                // $scope.medicalData = null;
                $scope.dietData = null;
                $scope.date = null;
                $scope.physical.data = null;
                $scope.physical.totalSize = 0;
                $scope.medical.data = null;
                $scope.medical.totalSize = 0;
                $scope.currentType = 1;
                $scope.history.showDevice = null;
                $('.health-data').removeClass('none');
                $scope.$apply();

            }
        });
    }
    $scope.clickTab = function (event, type) {
        // event undefined
        if (!event) {
            return;
        }
        $scope.currentType = type;
        if ($(event.target).hasClass('on')) {
            return;
        }
        // $scope.validDate = {};
        var temp = $('.health-date').val();

        if ($('.health-date').val() == temp) {
            switch (type) {
                case 1:
                    $('.health-date').removeClass('none');
                    this.listValidDate(new Date().Format('yyyy-MM-dd'));
                    $scope.initDate();
                    if ($scope.isChange[1]) {
                        $scope.getHealthData();
                        $scope.isChange[1] = false;
                    }
                    break;
                case 2:
                    $('.health-date').addClass('none');
                    if ($scope.isChange[2]) {
                        $scope.listPhysicalPageData();
                        $scope.isChange[2] = false;
                    }
                    break;
                case 3:
                    $('.health-date').addClass('none');
                    if ($scope.isChange[3]) {
                        // this.getMedicalData();

                        $scope.listMedicalPageData();
                        $scope.isChange[3] = false;
                    }
                    break;
                case 4:
                    $('.health-date').removeClass('none');
                    $scope.listValidDate(new Date().Format('yyyy-MM-dd'));
                    $scope.initDate();
                    if ($scope.isChange[4]) {
                        $scope.getDietData();
                        $scope.isChange[4] = false;
                    }
                    break;
            }
        }
    }
    /*获取有效日期*/
    $scope.listValidDate = function (date, callback) {
        var data = {
            userId: $scope.currentMember.userId,
            type: $scope.currentType,
            date: date
        }
        var url = '/combo/member/list-valid-date';
        http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if(result.success){
                var temp = result.attributes.date;
                for (var i in temp) {
                    $scope.validDate[i] = temp[i];
                }
                var arr = [];
                for (var i in $scope.validDate) {
                    arr.push(i);
                }
                arr = arr.sort();
                var timeArr = [];
                var date = null;
                for (var i = arr.length - 1; i >= 0; i --) {
                    if ($scope.validDate[arr[i]].length > 0) {
                        timeArr =$scope.validDate[arr[i]];
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
                    $scope.date = time;
                    $scope.$apply();
                }

                // $scope.initDate();
            }
        })
    }
    /**初始化日期*/
    $scope.initDate = function () {
        var now = (new Date()).Format('yyyy-MM-dd');
        $(".health-date").jeDate({
            isvalid:[$scope.validDate, true],  //设置有效日期（已重写方法）
            minDate:'1900-01-01',
            maxDate:now,
            format:"YYYY-MM-DD",
            zIndex:199999,
            choosefun:function(elem, val) {
                //val为获取到的时间值
                $scope.date = val;
                $scope.$apply();
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
                    for (var i in $scope.validDate) {
                        arr.push(i);
                    }
                    arr.sort();
                    for (var i in $scope.validDate) {
                        if (arr[0] == date) {
                            break;
                        }
                        if (i == date) {
                            bool = true;
                            break;
                        }
                    }
                    if (!bool) {
                        $scope.listValidDate(date + '-01');
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
                    for (var i in $scope.validDate) {
                        if (i == date) {
                            bool = true;
                            break;
                        }
                    }
                    if (!bool) {
                        $scope.listValidDate(date + '-01');
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
                    for (var i in $scope.validDate) {
                        if (i == date) {
                            bool = true;
                            break;
                        }
                    }
                    if (!bool) {
                        $scope.listValidDate(date + '-01');
                    }
                });

            }
        })
    }
    /*获取会员健康数据*/
    $scope.getHealthData = function (date) {
        var data = {
            'userId': $scope.currentMember.userId,
            'measureDate': date == null ? $scope.date : date
        }
        var url = '/combo/member/health-data';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if(result.success){
                $scope.healthData = result.attributes.data;
                $scope.healthData.length = Object.getOwnPropertyNames($scope.healthData).length;
                $scope.$apply();
            }
        })
        $scope.listHealthAnalysis();
    }
    /*获取设备历史数据*/
    $scope.getDeviceHistory = function (type) {
        $('.health-data').addClass('none');
        $('.health-date').addClass('none');
        switch (type) {
            case 'xty':
                $scope.history.showDevice = 'xty';
                $scope.history.paramType = '1'
                break;
            case 'xyj':
                $scope.history.showDevice = 'xyj';
                $scope.history.paramType = 'diastolic';
                break;
            case 'xyy':
                $scope.history.showDevice = 'xyy';
                $scope.history.paramType = 'saturation'
                break;
            case 'fhy':
                $scope.history.showDevice = 'fhy';
                $scope.history.paramType = 'vitalCapacity';
                break;
            case 'band':
                $scope.history.showDevice = 'band';
                $scope.history.paramType = 'steps';
                break;
            case 'tzc':
                $scope.history.showDevice = 'tzc';
                $scope.history.paramType = 'weight';
                break;
            case 'twj':
                $scope.history.showDevice = 'twj';
                $scope.history.paramType = 'temperature';
                break;
            case 'xzy':
                $scope.history.showDevice = 'xzy';
                $scope.history.paramType = 'tC';
                break;
            case 'ny':
                $scope.history.showDevice = 'ny';
                $scope.history.paramType = 'sG';
                break;
            case 'ns':
                $scope.history.showDevice = 'ns';
                $scope.history.paramType = 'uA';
                break;
            case 'ecg':
                $scope.history.showDevice = 'ecg';
                $scope.history.paramType = 'heartRate';
                break;
            default:
                $('.health-data').removeClass('none');
                $('.health-date').removeClass('none');
                break
        }
        $scope.history.dateType = 'WEEK';
        $scope.history.pageData = null;

        $scope.listHistoryPageData();
        setTimeout(function() {
             $scope.createChart();
        }, 1000)

    }
    /*获取设备历史分页数据*/
    $scope.listHistoryPageData = function () {
        var url = '/combo/member/list-health-data/paging/' + $scope.history.page;
        var data = {
            type: $scope.history.showDevice,
            userId: $scope.currentMember.userId,
        }
        if ($scope.history.showDevice == 'band') {
            var xlType = $scope.history.paramType;
            xlType = xlType == 'steps' ? 'step' : xlType;
            xlType = xlType == 'sleepDuration' ? 'sleep' : xlType;
            data.xlType = xlType;
        }
        http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if ($scope.history.pageData == null) {
                $scope.history.pageData = [];
            }
            if (result.obj == null || result.success && result.obj.data.length === 0) {
                layer.msg('无更多数据')
                return;
            }
            var resultData = [];
            if ($scope.history.showDevice == "ecg") {
                for (var i = 0; i < result.obj.data.length; i++) {
                    resultData = resultData.concat(result.obj.data[i].datas);
                }
            } else {
                resultData = result.obj.data;
            }
            $scope.history.pageData.push.apply($scope.history.pageData, resultData);
            $scope.$apply();
        });
    }
    /*显示总的健康数据*/
    $scope.showHealthData = function () {
        $scope.history.showDevice = null;
        $scope.history.weekData = null;
        $scope.history.monthData = null;
        $scope.history.threeMonthData = null;
        $scope.history.data = [];
        $scope.history.dateType = null;
        $scope.history.page = 1;
        $('.health-data').removeClass('none');
        $('.health-date').removeClass('none');
    }
    /*显示心率完整数据*/
    $scope.showHreatRate = function (event) {
        if ($('.heart-item').css('display') == 'none') {
            $('.heart-item').show(300);
            $(event.target).css("border-color", "#10bb71 transparent transparent transparent");
            $(event.target).css("margin-top", "12px");
        } else {
            $('.heart-item').hide(300);
            $(event.target).css("border-color", "transparent transparent #10bb71 transparent");
            $(event.target).css("margin-top", "0px");
        }
    }
    /*切换日期类型*/
    $scope.changeDateType = function (event, type) {
        $(event.target).addClass('on').siblings().removeClass('on');
        $scope.history.dateType = type;
    }
    /*切换参数类型*/
    $scope.changeParamType = function (event, type) {
        $(event.target).addClass('on').siblings().removeClass('on');
        $scope.history.paramType = type;
        if ($scope.history.showDevice == 'band') {    //心率手环
            $scope.history.weekData = null;
            $scope.history.monthData = null;
            $scope.history.threeMonthData = null;
            $scope.listHistoryChartData();
            setTimeout(function () {
                $scope.createChart();
            }, 300);
            this.history.page = 1;
            this.history.pageData = null;
            this.listHistoryPageData();
            return;
        }
        $scope.createChart();
    }
    /*获取设备历史图表数据*/
    $scope.listHistoryChartData = function () {
        var url = '/combo/member/list-health-data/type/' + $scope.history.dateType;

        var data = {
            type: $scope.history.showDevice,
            userId: $scope.currentMember.userId,
        }
        if ($scope.history.showDevice == 'band') {
            var xlType = $scope.history.paramType;
            xlType = xlType == 'steps' ? 'step' : xlType;
            xlType = xlType == 'sleepDuration' ? 'sleep' : xlType;
            data.xlType = xlType;
            $scope.history.weekData = null;
            $scope.history.monthData = null;
            $scope.history.threeMonthData = null;

        }
        if ($scope.history.weekData != null && $scope.history.dateType == 'WEEK') {
            $scope.history.data = $scope.history.weekData;
            $scope.createChart();
            return;
        }
        if ($scope.history.monthData != null && $scope.history.dateType == 'MONTH') {
            $scope.history.data = $scope.history.monthData;
            $scope.createChart();
            return;
        }
        if ($scope.history.threeMonthData != null && $scope.history.dateType == 'THREEMONTH') {
            $scope.history.data = $scope.history.threeMonthData;
            $scope.createChart();
            return;
        }
        http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            switch ($scope.history.dateType) {
                case 'WEEK':
                    $scope.history.weekData = result.obj;
                    break;
                case 'MONTH':
                    $scope.history.monthData = result.obj;
                    break;
                case 'THREEMONTH':
                    $scope.history.threeMonthData = result.obj;
                    break;
            }
            $scope.history.data = result.obj;
            $scope.$apply();
            $scope.createChart();
        });

    }
    /*创建图表*/
    $scope.createChart = function () {
        if ($scope.history.data.length < 1) {
            return;
        }
        var series = [];
        var categories = [];
        var tickPositions = [];
        var plotLines = [];
        var param = $scope.history.paramType;
        if ($scope.history.showDevice == 'xty') {
            for (var i in $scope.history.data) {
                for (var j in $scope.history.data[i].paramData) {
                    if ($scope.history.data[i].paramData[j][param] != null) {
                        series.push(Number($scope.history.data[i].paramData[j][param].bloodSugar));
                        categories.push(new Date($scope.history.data[i].measureDate).Format('MM-dd'))
                        if (plotLines.length < 1) {
                            var arr = $scope.history.data[i].paramData[j][param]['bloodSugarArea'].split('-');
                            var number = ((Number(arr[1]) -  Number(arr[0])) / 4.5);
                            for (var i = -1; i <= 5; i ++) {
                                tickPositions.push((Number(arr[0]) + number * i).toFixed(1));
                            }
                            plotLines = arr;
                        }
                        break;
                    }
                }
            }
            $scope.history.xtyHasData = true;
            if (series.length < 1) {
                $scope.history.xtyHasData = false;
            }
        } else {
            for (var i in $scope.history.data) {
                series.push($scope.history.data[i][param]);
                $scope.history.data[i].measureDate = $scope.history.data[i].measureDate == null ? $scope.history.data[i].date : $scope.history.data[i].measureDate;
                categories.push(new Date($scope.history.data[i].measureDate).Format('MM-dd'))
            }
            if ($scope.history.showDevice != 'band' && $scope.history.showDevice != 'ecg') {
                var arr = $scope.history.data[0][param + 'Area'].split('-');
                if ($scope.history.showDevice == 'fhy') {
                    arr[1] = 10000;
                }
                var number = ((Number(arr[1]) -  Number(arr[0])) / 4.5);
                var fix = 1;
                if ($scope.history.paramType == 'sG') {   //比重保留三位小数
                    fix = 3;
                }
                if ($scope.history.paramType == 'uA') {   //尿酸保留2位小数
                    fix = 2;
                }
                for (var i = -1; i <= 5; i ++) {
                    tickPositions.push((Number(arr[0]) + number * i).toFixed(fix))
                }
                plotLines = arr;
            }

            if ($scope.history.showDevice == 'fhy') {
                plotLines.splice(1, 1);
            }
            if ($scope.history.showDevice == 'band' && $scope.history.showDevice != 'ecg') {
                tickPositions = null;
            }
        }

        var data = {
            series: series,
            categories: categories,
            tickPositions: tickPositions,
            plotLines: plotLines,
            YText: ''

        }
        setTimeout(function () {
            $scope.$apply();
            $scope.initChart(data);
        }, 300);

    }
    /*初始化图表*/
    $scope.initChart = function (data) {
        var series = [];
        for (var i in data.series) {
            var temp = {
                y: data.series[i],
                marker: {//值为26.5参数
                    fillColor: '#fff',//点填充色
                    lineColor: '#10bb71',//点边框色
                    minWidth:'12px',
                    lineWidth:2,
                    radius:6,
                    states:{//鼠标经过
                        hover:{
                            fillColor: '#fff',//点填充色
                            lineColor: '#10bb71'//点边框色
                        }
                    }
                }
            }
            series.push(temp);
        }
        $('#chart_container').highcharts({
            chart:{
                type:'line'
            },
            title: {
                text: null
            },
            legend: {
                enabled:false,
            },
            xAxis: {
                categories: data.categories
            },
            plotOption:{
                row :{
                    pointPadding:0.6
                }
            },
            plotOptions:{
                spline:{
                    dataLabels:{
                        enabled:true,
                        color:'#666'
                    }
                }
            },
            yAxis: {
                title: {
                    text: data.YText
                },
                tickPositions: data.tickPositions,
                // staggerLines:6,
                // tickPixelInterval:1000,
                // min:0,
                // max:160,
                // gridLineColor:'#ccc',
                girdLinePadding:50,
                plotLines:[ //临界线
                    {
                        color:'#f40',
                        dashStyle:'dash',
                        value: data.plotLines[0],
                        width:1,
                        zIndex: 999,
                        label:{
                            text:'最低值:' + data.plotLines[0],     //标签的内容
                            align:'left',                //标签的水平位置，水平居左,默认是水平居中center
                            x:10                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
                        }
                    },
                    {
                        color:'#f40',
                        dashStyle:'dash',
                        value: data.plotLines[1],
                        width:1,
                        zIndex: 999,
                        label:{
                            text:'最高值:' + data.plotLines[1],     //标签的内容
                            align:'left',                //标签的水平位置，水平居左,默认是水平居中center
                            x:10                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
                        }
                    }
                ]
            },
            series: [
                {
                    type: 'spline',
                    data: series,
                }
            ]
        });
    }
    /*分页获取体检数据*/
    $scope.listPhysicalPageData = function () {
        var url = '/combo/member/list-member-physical-data-paging/' + $scope.physical.pageIndex;
        var data = {
            userId: $scope.currentMember.userId
        }
        http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.physical.data = result.obj.data;
            $scope.physical.pageIndex = result.obj.nowPage;
            $scope.physical.totalPage = result.obj.totalPage;
            $scope.physical.totalSize = result.obj.totalSize;
            $scope.$apply();
            if ($scope.physical.totalSize == 0) {
                $('#page-physical').addClass('none');
                return;
            }
            $('#page-physical').removeClass('none');
            $scope.initPhysicalPage();
        });
    }
    /*分页获取病历数据*/
    $scope.listMedicalPageData = function () {
        var url = '/combo/member/list-member-medical-data-paging/' + $scope.medical.pageIndex;
        var data = {
            userId: $scope.currentMember.userId
        }
        http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.medical.data = result.obj.data;
            $scope.medical.page = result.obj.nowPage;
            $scope.medical.totalPage = result.obj.totalPage;
            $scope.medical.totalSize = result.obj.totalSize;
            $scope.$apply();
            if ($scope.medical.totalSize == 0 || result.obj.data.length == 0) {
                $('#page-medical').addClass('none');
                return;
            }
            $('#page-medical').removeClass('none');

            $scope.initMedicalPage();
        });
    },
    /*初始化medical分对象*/
    $scope.initMedicalPage = function () {
        laypage.render({
            elem: 'page-medical'
            ,count: $scope.medical.totalSize
            ,limit: $scope.medical.pageSize
            ,theme: '#00bfff'
            ,layout: ['count', 'prev', 'page', 'next', 'skip']
            ,jump: function(obj){
                $scope.medical.pageIndex = obj.curr;
                // $scope.listPhysicalPageData();
            }
        });
    }
    /*初始化physical分页对象*/
    $scope.initPhysicalPage = function () {
        laypage.render({
            elem: 'page-physical'
            ,count: $scope.physical.totalSize
            ,limit: $scope.physical.pageSize
            ,theme: '#00bfff'
            ,layout: ['count', 'prev', 'page', 'next', 'skip']
            ,jump: function(obj){
                $scope.physical.pageIndex = obj.curr;
                // $scope.listPhysicalPageData();
                console.log('inter page')
            }
        });
    }
    /*获取会员饮食数据*/
    $scope.getDietData = function () {
        var data = {
            'userId': this.currentMember.userId,
            'recordDate': this.date
        }
        var url = '/combo/member/member-diet-data';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if(result.success){

                $scope.$apply(function () {
                    $scope.dietData = result.obj;
                })
            }
        })
    }

    /*聊天*/
    $scope.popupChatDialog = function (userName, realName, userCode, photo) {
        var currentMember = {
            userName: userName,
            realName: realName,
            userCode: userCode,
            photo: photo,
            type: 'chat'
        }
        chat.initChatDialog([], $rootScope.userInfo, currentMember, function () {
            // message.vm.serveMessage = messageCache.getRecentMessage(message.vm.orgUser.userCode, message.vm.recentCount);
        });
    }

    /*弹出批注框*/
    $scope.popupHealthAnalysisDialog = function () {
        $scope.lay2 = layer.open({
            type: 1,
            title: '添加批注',
            shadeClose: false,
            shade: false,
            moveType: 1,
            area: ["550px", "350px"],
            // offset: ['50%', '51%'],
            closeBtn: 1,
            btn: ['确定', '取消'],
            content: $('.health-analysis-dialog'),
            yes: function () {
                $scope.addHealthAnalysis();
            },
            btn2: function(){

            }
        });
    }

    /*添加批注*/
    $scope.addHealthAnalysis = function () {
        if ($scope.analysis.content == null || $scope.analysis.content.length < 1) {
            layer.msg('请填写批注内容');
            return;
        }
        if ($scope.analysis.doctorSign == null || $scope.analysis.doctorSign.length < 1) {
            layer.msg('请填写医生署名');
            return;
        }
        var url = '/combo/member/health-analysis';
        var data = $scope.analysis;
        data.measureDate = $scope.date;
        data.userId = $scope.currentMember.userId;
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('添加批注成功');
                $scope.listHealthAnalysis();
                layer.close($scope.lay2);

            }
        })
    }

    /*获取健康数据批注*/
    $scope.listHealthAnalysis = function () {
        $scope.analysisList = null;
        var url = '/combo/member/health-analysis';
        var data = {};
        data.measureDate = $scope.date;
        data.userId = $scope.currentMember.userId;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success && result.obj.length > 0) {
                $scope.$apply(function () {
                    $scope.analysisList = result.obj;
                    $scope.analysis.content = null;
                    $scope.analysis.doctorSign = null;
                })
            }
        })
    }

    /*弹出用户服务记录窗口*/
    $scope.popupServeRecord = function (userId) {
        $scope.serveRecord.userId = userId;
        layer.open({
            type:1,
            title:"<p class='layer-service'>会员<i>陈美眉</i> > <span class='ServiceRecord'>服务过程记录</span></p>",
            content:$('#ServeRecordDialog'),
            area:['760px','630px'],
            success: function () {
                $scope.listServeRecord();
            }
        })
    }
    /*弹出添加用户服务记录窗口*/
    $scope.popupAddServeRecord = function () {
        $scope.lay2 = layer.open({
            type:1,
            area: ["550px", "370px"],
            closeBtn: 1,
            btn: ['确定', '取消'],
            title:"<p class='layer-service'>添加服务过程记录</p>",
            content:$('.add-serve-record-dialog'),
            yes: function () {
                $scope.addServeRecord();
            }
        })
    }
    /*初始化日期插件*/
    $scope.initServeRecordDate = function ($element) {
        $element.jeDate({
            minDate: '1900-01-01',
            maxDate: new Date().Format('yyyy-MM-dd'),
            format: "YYYY-MM-DD",
            zIndex: 20891016,
            choosefun: function (elem, val) {
                //val为获取到的时间值
                $scope.serveRecord.serveDate = val;
            },
        });
    }
    /*获取用户服务记录*/
    $scope.listServeRecord = function () {

        var url = '/combo/member/serve-record/' + $scope.serveRecord.page;
        var data = {
            userId: $scope.serveRecord.userId
        };
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.serveRecord.list = result.obj
            });
        })
    }
    /*添加用户服务记录*/
    $scope.addServeRecord = function () {
        var url = '/combo/member/serve-record';
        var data = Object.assign({}, data, $scope.serveRecord);
        // data.userId = $scope.currentMember.userId;
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('添加成功!');
                $scope.listServeRecord();
                layer.close($scope.lay2);
                $scope.serveRecord.remark = null;
                $scope.serveRecord.serveContent = null;
                return;
            }
            layer.msg('添加失败!');
        })
    }

    /** 监听模块 */
    $scope.$watch('conditions.todayAbnormal',function(){
        if ($scope.conditions.todayAbnormal) {
            $scope.conditions.isEndTime = false;
            $scope.conditions.todayNotMeasure = false;
            $scope.conditions.monthNotMeasure = false;
        }
    },true);
    $scope.$watch('conditions.isEndTime',function(){
        if ($scope.conditions.isEndTime) {
            $scope.conditions.todayAbnormal = false;
            $scope.conditions.todayNotMeasure = false;
            $scope.conditions.monthNotMeasure = false;
        }
    },true);
    $scope.$watch('conditions.todayNotMeasure',function(){
        if ($scope.conditions.todayNotMeasure) {
            $scope.conditions.isEndTime = false;
            $scope.conditions.todayAbnormal = false;
            $scope.conditions.monthNotMeasure = false;
        }
    },true);
    $scope.$watch('conditions.monthNotMeasure',function(){
        if ($scope.conditions.monthNotMeasure) {
            $scope.conditions.isEndTime = false;
            $scope.conditions.todayNotMeasure = false;
            $scope.conditions.todayAbnormal = false;
        }
    },true);
    $scope.$watch('date', function (newValue, oldValue, scope) {
        if (newValue == oldValue) {
            return;
        }
        if ($scope.date != null) {    //TODO
            $scope.isChange = {
                1: true,
                2: true,
                3: true,
                4: true
            }
            $scope.isChange[$scope.currentType] = false;
            switch ($scope.currentType) {
                case 1:
                    $scope.analysisList = null;
                    $scope.getHealthData();
                    break;
                case 2:
                    $scope.listPhysicalPageData();
                    break;
                case 3:
                    // this.getMedicalData();
                    $scope.listMedicalPageData();
                    break;
                case 4:
                    $scope.getDietData();
                    break;
                default:
                    break;
            }
        }
    }, true)
    $scope.$watch('history.dateType', function (newValue, oldValue, scope) {
        if ($scope.history.dateType == null) {
            return;
        }
        $scope.listHistoryChartData();
    }, true)
    $scope.$watch('history.showDevice', function (newValue, oldValue, scope) {
        if ($scope.history.showDevice == null) {
            return;
        }
        $('#member-dialog-content').scroll(function(){
            var srollPos = $('#member-dialog-content').scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
            var totalheight = parseFloat($('#member-dialog-content').height()) + parseFloat(srollPos);
            if($scope.history.finished && ($('.member-dialog').height()-50) <= totalheight) {
                $scope.history.finished = false;
                $scope.history.page ++;
                setTimeout(function () {
                    $scope.listHistoryPageData();
                }, 500);
            }
        });
    }, true)
    $scope.$watch('history.pageData', function () {
        $scope.history.finished = true;
    }, true)
}])


var tip;
var showTip = function(element, content) {
    tip = layer.tips(content, element, {
        tips: [1, '#3595CC'],
        time: 0,
        close: function () {
            console.log('dsds')
        }
    });
}

var closeTip = function() {
    layer.close(tip);
}
/**
 * 会员管理JS
 */

var Manage = {};

Manage.init = function () {

    $(".tab-content-1 > div").not("div:first").css("display","none")
    $(".tab-1 li").on("click",function(){
        $(this).addClass("on").siblings().removeClass("on");
        $(".tab-content-1 > div").css("display","none").eq($(this).index()).css("display","block");
    })




    //获取病种数据
    Manage.vm.listDiseases();

    Manage.vm.listMemer();

}
Manage.vm = new Vue({
    el: '.vue-content',
    data: {
        results: null,
        members: null,
        tempMembers: null,
        // currentMember: null,
        currentId: 0,
        date: new Date().Format('yyyy-MM-dd') + ' ',
        healthData: null,
        physicalData: null,
        medicalData: null,
        dietData: null,
        search: null,
        chatMember: null, //加入聊天的用户
        orgUser: null,
        userType: null,
        orgType: null,
        validDate: {},    //有效的日期
        currentType: 1,
        isChange: {},

        diseases: [],   //病种数据集合
        userDiseases: -1,
        currentMember: null,
        isSearch: false,
        v_2: {
            members: null,
            group: [],
        },
        history: {
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
        },
        medical: {
            page: 1,
            totalPage: 1,
            totalSize: 0,
            pageObj: null,
            data: null,
        },
        physical: {
            page: 1,
            totalPage: 1,
            totalSize: 0,
            pageObj: null,
            data: null,
        }
    },
    methods: {
        /**点击用户*/
        clickMember: function (event, index) {
            if ($(event.currentTarget).hasClass('on')) {
                return;
            }
            $(event.currentTarget).addClass('on').siblings().removeClass('on')
            this.currentId = index;
        },
        clickMember_v_2: function (event, m, isHistory) {
            if ($(event.currentTarget).hasClass('on')) {
                return;
            }
            var $obj = $('.main-content-left-bottom ul li.on');
            $(event.currentTarget).addClass('on');
            $obj.removeClass('on');
            if (isHistory) {
                m.isHistory = true;
            }
            this.currentMember = m;
        },
        clickTab: function (event, type) {
            this.currentType = type;
            if ($(event.currentTarget).hasClass('on')) {
                return;
            }
            // Manage.vm.validDate = {};
            var temp = $('.health-date').val();

            if ($('.health-date').val() == temp) {
                switch (type) {
                    case 1:
                        $('.health-date').removeClass('none');
                        this.listValidDate(new Date().Format('yyyy-MM-dd'));
                        Manage.vm.initDate();
                        if (this.isChange[1]) {
                            this.getHealthData();
                            this.isChange[1] = false;
                        }

                        break;
                    case 2:
                        $('.health-date').addClass('none');
                        if (this.isChange[2]) {
                            this.listPhysicalPageData();
                            this.isChange[2] = false;
                        }
                        break;
                    case 3:
                        $('.health-date').addClass('none');
                        if (this.isChange[3]) {
                            // this.getMedicalData();

                            this.listMedicalPageData();
                            this.isChange[3] = false;
                        }
                        break;
                    case 4:
                        $('.health-date').removeClass('none');
                        this.listValidDate(new Date().Format('yyyy-MM-dd'));
                        Manage.vm.initDate();
                        if (this.isChange[4]) {
                            this.getDietData();
                            this.isChange[4] = false;
                        }
                        break;
                }
            }

        },
        /**弹出用户信息框*/
        popupMemberDialog: function (type) {
            this.currentType = type;

           /* switch (type) {

            }*/
            $('.layui-tab-title ul').find('li').eq(type - 1).click();
            this.listValidDate((new Date()).Format('yyyy-MM-dd'));

            Manage.vm.initDate();

            this.isChange = {
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
                zIndex: 99,
                content: $('.member-dialog'),
                cancel: function () {
                    Manage.vm.healthData = null;
                    Manage.vm.physicalData = null;
                    Manage.vm.medicalData = null;
                    Manage.vm.dietData = null;
                    Manage.vm.date = null;
                }
            });
        },
        /**获取会员健康数据*/
        getHealthData: function (date) {
            var data = {
                'userId': this.currentMember.userId,
                'measureDate': date == null ? this.date : date
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/org/memberManage?getMemberHealthData',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        Manage.vm.healthData = result.attributes.data;
                        Manage.vm.healthData.length = Object.getOwnPropertyNames(Manage.vm.healthData).length;
                        // Manage.vm.validDate = result.attributes.date;
                        // Manage.vm.validDate = Manage.vm.validDate.join(',');

                    }
                }
            });
        },
        /**获取会员体检报告数据*/
        /*getPhysicalData: function () {
            var data = {
                'userId': this.currentMember.userId,
                'physicalsDate': this.date
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/org/memberManage?getMemberPhysicalData',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        Manage.vm.physicalData = result.obj;
                    }
                }
            });
        },*/
        /**分页获取体检数据*/
        listPhysicalPageData: function () {
            var url = '/org/member/list-member-physical-data-paging/' + this.physical.page;
            var data = {
                userId: this.currentMember.userId
            }
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                Manage.vm.physical.data = result.obj.data;
                Manage.vm.physical.page = result.obj.nowPage;
                Manage.vm.physical.totalPage = result.obj.totalPage;
                Manage.vm.physical.totalSize = result.obj.totalSize;
                if (Manage.vm.physical.totalSize == 0) {
                    return;
                }
                Manage.vm.initPhysicalPage();
            });
        },
        /**获取会员病历数据*/
       /* getMedicalData: function () {
            var data = {
                'userId': this.currentMember.userId,
                'visitingDate': this.date
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/org/memberManage?getMemberMedicalData',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        Manage.vm.medicalData = result.obj;
                    }
                }
            });
        },*/
        /**分页获取病历数据*/
        listMedicalPageData: function () {
            var url = '/org/member/list-member-medical-data-paging/' + this.medical.page;
            var data = {
                userId: this.currentMember.userId
            }
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                Manage.vm.medical.data = result.obj.data;
                Manage.vm.medical.page = result.obj.nowPage;
                Manage.vm.medical.totalPage = result.obj.totalPage;
                Manage.vm.medical.totalSize = result.obj.totalSize;
                if (Manage.vm.medical.totalSize == 0) {
                    return;
                }
                Manage.vm.initMedicalPage();
            });
        },
        /**初始化medical分对象*/
        initMedicalPage: function () {
            if (this.medical.pageObj == null) {
                this.medical.pageObj = new PageUtil();
                this.medical.pageObj.getPageControl().init({
                    container: 'page-container-medical',
                    preBtn: 'pre_rec',
                    nextBtn: 'next_rec',
                    totalPage: Manage.vm.medical.totalPage,
                    pageChange: function (page) {
                        Manage.vm.medical.page = page;
                        Manage.vm.listMedicalPageData();
                    }
                });
            }
            this.medical.pageObj.getPageControl().totalPage = this.medical.totalPage;
            this.medical.pageObj.getPageControl().selectPage(this.medical.page, true);

        },
        /**初始化physical分页对象*/
        initPhysicalPage: function () {
            if (this.physical.pageObj == null) {
                this.physical.pageObj = new PageUtil();
                this.physical.pageObj.getPageControl().init({
                    container: 'page-container',
                    preBtn: 'pre_rec',
                    nextBtn: 'next_rec',
                    totalPage: Manage.vm.physical.totalPage,
                    pageChange: function (page) {
                        Manage.vm.physical.page = page;
                        Manage.vm.listPhysicalPageData();
                    }
                });
            }
            this.physical.pageObj.getPageControl().totalPage = this.physical.totalPage;
            this.physical.pageObj.getPageControl().selectPage(this.physical.page, true);
        },
        /**获取会员饮食数据*/
        getDietData: function () {
            var data = {
                'userId': this.currentMember.userId,
                'recordDate': this.date
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'GET',
                url: '/org/memberManage?getMemberDietData',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        Manage.vm.dietData = result.obj;
                    }
                }
            });
        },
        /** 弹出消息对话框*/
        popupChatDialog: function (userId, userCode, photo, userName) {
            /*var currentMember = {
                userCode: userCode,
                type: 'chat',
                head: photo,
                userName: userName
            }*/
            if (this.chatMember == null) {
                this.chatMember = {};
                // this.chatMember[this.orgUser.userCode] = {};
                this.chatMember[userCode] = [];
                // this.currentMember.type = "chat";
            }
            this.currentMember.type = "chat";
            chat.initChatDialog(this.chatMember, this.orgUser, this.currentMember);
        },
        showHreatRate: function (event) {
            if ($('.heart-item').css('display') == 'none') {
                $('.heart-item').show(300);
                $(event.currentTarget).css("border-color", "#10bb71 transparent transparent transparent");
                $(event.currentTarget).css("margin-top", "12px");
            } else {
                $('.heart-item').hide(300);
                $(event.currentTarget).css("border-color", "transparent transparent #10bb71 transparent");
                $(event.currentTarget).css("margin-top", "0px");
            }
        },
        /**初始化日期*/
        initDate: function () {
            var now = (new Date()).Format('yyyy-MM-dd');
            $(".health-date").jeDate({
                isvalid:[Manage.vm.validDate, true],  //设置有效日期（已重写方法）
                minDate:'1900-01-01',
                maxDate:now,
                format:"YYYY-MM-DD",
                zIndex:199999,
                choosefun:function(elem, val) {
                    //val为获取到的时间值
                    Manage.vm.date = val;
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
                        for (var i in Manage.vm.validDate) {
                            arr.push(i);
                        }
                        arr.sort();
                        for (var i in Manage.vm.validDate) {
                            if (arr[0] == date) {
                                break;
                            }
                            if (i == date) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            Manage.vm.listValidDate(date + '-01');
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
                        for (var i in Manage.vm.validDate) {
                            if (i == date) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            Manage.vm.listValidDate(date + '-01');
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
                        for (var i in Manage.vm.validDate) {
                            if (i == date) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            Manage.vm.listValidDate(date + '-01');
                        }
                    });

                }
            })
        },
        listValidDate: function (date, callback) {
            var data = {
                userId: this.currentMember.userId,
                type: this.currentType,
                date: date
            }
            $.ajax({
                async : false,
                cache : false,
                type: 'GET',
                url: '/org/member/list-valid-date',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if(result.success){
                        // Manage.vm.dietData = result.obj;

                        var temp = result.attributes.date;
                        for (var i in temp) {
                            Manage.vm.validDate[i] = temp[i];
                        }
                        var arr = [];
                        for (var i in Manage.vm.validDate) {
                            arr.push(i);
                        }
                        arr = arr.sort();
                        var timeArr = [];
                        var date = null;
                        for (var i = arr.length - 1; i >= 0; i --) {
                            if (Manage.vm.validDate[arr[i]].length > 0) {
                                timeArr = Manage.vm.validDate[arr[i]];
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
                            Manage.vm.date = time;
                        }

                        // Manage.vm.initDate();
                    }
                }
            });
        },
        popurImg: function (event, img) {
            // $('.physical-img img').bigic();
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                // area: '516px',
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: '<div style="max-width: 500px; max-height: 500px"><img src = "'+ img +'"></div>'
            });
        },
        /**修改用户备注*/
        modifyUserRemark: function (value) {
            if (value == '' && !this.currentMember.userRemark) {
                return;
            }
            if (value == this.currentMember.userRemark) {
                return;
            }
            var url = '/org/member/modify-user-remark';
            var data = {
                orderId: this.currentMember.orderId,
                userId: this.currentMember.userId,
                userRemark: value
            }
            http.ajax.post_no_loading(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                Manage.vm.currentMember.userRemark = value == '' ? null : value;
                for (var i in Manage.vm.tempMembers) {
                    if (Manage.vm.tempMembers[i].orderId == Manage.vm.currentMember.orderId) {
                        Manage.vm.tempMembers[i] = Manage.vm.currentMember;
                        break;
                    }

                }
                for (var i in Manage.vm.members) {
                    if (Manage.vm.members[i].orderId == Manage.vm.currentMember.orderId) {
                        Manage.vm.members[i] = Manage.vm.currentMember;
                        break;
                    }

                }

            });
        },
        getMemberName: function (user) {
            var str = '';
            if (user.userRemark != null) {
                str += user.userRemark + '(';
                if (user.realName != null) {
                    str += user.realName;
                } else {
                    str += user.userName;
                }
                str += ')';
            } else {
                if (user.realName != null) {
                    str += user.realName;
                } else {
                    str += user.userName;
                }
            }
            return str;
        },
        /**分组获取会员数据*/
        listMemer: function () {
            var url = '/org/member/list-member';
            var data = null;
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                Manage.vm.v_2.members = [];
                Manage.vm.v_2.group = [];
                Manage.vm.v_2.members = result.obj;
                for (var i in result.obj) {
                    var temp = {};
                    temp.name = i;
                    temp.size = result.obj[i].length;
                    Manage.vm.v_2.group.push(temp);
                }
            })
        },
        /**获取各组会员*/
        listMemberByGroup: function (name) {
            if (Manage.vm.v_2.members.length < 1) {
                return [];
            }
            return Manage.vm.v_2.members[name];
        },
        /**更新用户病种*/
        modifyUserDiseases: function (diseasesId, diseasesName) {
            var url = '/org/member/modify-user-diseases';
            var data = {
                orderId: this.currentMember.orderId,
                userId: this.currentMember.userId,
                userDiseasesId: diseasesId,
                userDiseasesName: diseasesName
            };
            http.ajax.post_no_loading(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if (result.success) {
                    layer.msg('设置成功', {icon: 1});
                    Manage.vm.currentMember.userDiseasesId = diseasesId;
                    for (var i in Manage.vm.tempMembers) {
                        if (Manage.vm.tempMembers[i].orderId == Manage.vm.currentMember.orderId) {
                            Manage.vm.tempMembers[i] = Manage.vm.currentMember;
                            break;
                        }

                    }
                    for (var i in Manage.vm.members) {
                        if (Manage.vm.members[i].orderId == Manage.vm.currentMember.orderId) {
                            Manage.vm.members[i] = Manage.vm.currentMember;
                            break;
                        }

                    }
                    return;
                }
                layer.msg('设置失败', {icon: 5});
            });
        },
        /**获取病种列表*/
        listDiseases: function () {
            if (this.diseases.length != 0) {
                return;
            }
            var url = '/org/member/list-diseases';
            var data = null;
            http.ajax.get_no_loading(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if (result.success) {
                    Manage.vm.diseases = result.obj;
                }
            });
        },
        /**获取各组人数*/
        countGroupMember: function (type) {
            for (var i in this.v_2.group) {
                if (this.v_2.group[i].name == type) {
                    return this.v_2.group[i].size;
                }
            }
            return 0;
        },
        /**点击搜索框*/
        showSearch: function () {
            this.isSearch = true;
            // $('.search-content').removeClass('none');
            // $('.layui-tab-item').removeClass('layui-show');
            // $('.layui-tab-title').addClass('none');
        },
        /**获取设备历史数据*/
        getDeviceHistory: function (type) {
            $('.health-data').addClass('none');
            $('.health-date').addClass('none');
            switch (type) {
                case 'xty':
                    this.history.showDevice = 'xty';
                    this.history.paramType = '1'
                    break;
                case 'xyj':
                    this.history.showDevice = 'xyj';
                    this.history.paramType = 'diastolic';
                    break;
                case 'xyy':
                    this.history.showDevice = 'xyy';
                    this.history.paramType = 'saturation'
                    break;
                case 'fhy':
                    this.history.showDevice = 'fhy';
                    this.history.paramType = 'vitalCapacity';
                    break;
                case 'band':
                    this.history.showDevice = 'band';
                    this.history.paramType = 'steps';
                    break;
                case 'tzc':
                    this.history.showDevice = 'tzc';
                    this.history.paramType = 'weight';
                    break;
                case 'twj':
                    this.history.showDevice = 'twj';
                    this.history.paramType = 'temperature';
                    break;
                case 'xzy':
                    this.history.showDevice = 'xzy';
                    this.history.paramType = 'tC';
                    break;
                case 'ny':
                    this.history.showDevice = 'ny';
                    this.history.paramType = 'sG';
                    break;
                case 'ns':
                    this.history.showDevice = 'ns';
                    this.history.paramType = 'uA';
                    break;
                case 'ecg':
                    this.history.showDevice = 'ecg';
                    this.history.paramType = 'heartRate';
                    break;
                default:
                    $('.health-data').removeClass('none');
                    $('.health-date').removeClass('none');
                    break
            }
            this.history.dateType = 'WEEK';
            Manage.vm.history.pageData = null;
            this.listHistoryPageData();
            setTimeout(function() {
                Manage.vm.createChart();
            }, 1000)

        },
        /**切换参数类型*/
        changeParamType: function (event, type) {
            $(event.currentTarget).addClass('on').siblings().removeClass('on');
            this.history.paramType = type;
            if (this.history.showDevice == 'band') {    //心率手环
                Manage.vm.history.weekData = null;
                Manage.vm.history.monthData = null;
                Manage.vm.history.threeMonthData = null;
                this.listHistoryChartData();
                setTimeout(function () {
                    Manage.vm.createChart();
                }, 300);
                this.history.page = 1;
                this.history.pageData = null;
                this.listHistoryPageData();
                return;
            }
            this.createChart();
        },
        /**切换日期类型*/
        changeDateType: function (event, type) {
            $(event.currentTarget).addClass('on').siblings().removeClass('on');
            this.history.dateType = type;
           /* setTimeout(function () {
                Manage.vm.createChart();
            }, 1000);*/
        },
        /**显示总的健康数据*/
        showHealthData: function () {
            this.history.showDevice = null;
            Manage.vm.history.weekData = null;
            Manage.vm.history.monthData = null;
            Manage.vm.history.threeMonthData = null;
            Manage.vm.history.data = [];
            Manage.vm.history.dateType = null;
            Manage.vm.history.page = 1;
            /*Manage.vm.history.pageData = null;*/
            $('.health-data').removeClass('none');
            $('.health-date').removeClass('none');
        },
        /**获取设备历史图表数据*/
        listHistoryChartData: function () {
            var url = '/org/member/list-member-health-data/' + this.historyDateType;

            var data = {
                type: this.history.showDevice,
                userId: this.currentMember.userId,
            }
            if (this.history.showDevice == 'band') {
                var xlType = this.history.paramType;
                xlType = xlType == 'steps' ? 'step' : xlType;
                xlType = xlType == 'sleepDuration' ? 'sleep' : xlType;
                data.xlType = xlType;
                Manage.vm.history.weekData = null;
                Manage.vm.history.monthData = null;
                Manage.vm.history.threeMonthData = null;
            }
            if (Manage.vm.history.weekData != null && Manage.vm.historyDateType == 'WEEK') {
                Manage.vm.history.data = Manage.vm.history.weekData;
                Manage.vm.createChart();
                return;
            }
            if (Manage.vm.history.monthData != null && Manage.vm.historyDateType == 'MONTH') {
                Manage.vm.history.data = Manage.vm.history.monthData;
                Manage.vm.createChart();
                return;
            }
            if (Manage.vm.history.threeMonthData != null && Manage.vm.historyDateType == 'THREEMONTH') {
                Manage.vm.history.data = Manage.vm.history.threeMonthData;
                Manage.vm.createChart();
                return;
            }
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                switch (Manage.vm.historyDateType) {
                    case 'WEEK':
                        Manage.vm.history.weekData = result.obj;
                        break;
                    case 'MONTH':
                        Manage.vm.history.monthData = result.obj;
                        break;
                    case 'THREEMONTH':
                        Manage.vm.history.threeMonthData = result.obj;
                        break;
                }
                Manage.vm.history.data = result.obj;
                Manage.vm.createChart();
            });
        },
        /**获取设备历史分页数据*/
        listHistoryPageData: function () {
            var url = '/org/member/list-member-health-data-paging/' + this.history.page;
            var data = {
                type: this.history.showDevice,
                userId: this.currentMember.userId,
            }
            if (this.history.showDevice == 'band') {
                var xlType = this.history.paramType;
                xlType = xlType == 'steps' ? 'step' : xlType;
                xlType = xlType == 'sleepDuration' ? 'sleep' : xlType;
                data.xlType = xlType;
            }
            http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if (Manage.vm.history.pageData == null) {
                    Manage.vm.history.pageData = [];
                }
                if (result.obj == null || result.success && result.obj.data.length === 0) {
                    return;
                }
                Manage.vm.history.pageData.push.apply(Manage.vm.history.pageData, result.obj.data);

            });
        },
        /**创建图表*/
        createChart: function () {
            if (Manage.vm.history.data.length < 1) {
                return;
            }
            var series = [];
            var categories = [];
            var tickPositions = [];
            var plotLines = [];
            var param = Manage.vm.history.paramType;
            if (this.history.showDevice == 'xty') {
                for (var i in Manage.vm.history.data) {
                    for (var j in Manage.vm.history.data[i].paramData) {
                            if (Manage.vm.history.data[i].paramData[j][param] != null) {
                                series.push(Number(Manage.vm.history.data[i].paramData[j][param].bloodSugar));
                                categories.push(new Date(Manage.vm.history.data[i].measureDate).Format('MM-dd'))
                                if (plotLines.length < 1) {
                                    var arr = Manage.vm.history.data[i].paramData[j][param]['bloodSugarArea'].split('-');
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
                this.history.xtyHasData = true;
                if (series.length < 1) {
                    this.history.xtyHasData = false;
                }
            } else {
                for (var i in Manage.vm.history.data) {
                    series.push(Manage.vm.history.data[i][param]);
                    Manage.vm.history.data[i].measureDate = Manage.vm.history.data[i].measureDate == null ? Manage.vm.history.data[i].date : Manage.vm.history.data[i].measureDate;
                    categories.push(new Date(Manage.vm.history.data[i].measureDate).Format('MM-dd'))
                }
                if (this.history.showDevice != 'band' && this.history.showDevice != 'ecg') {
                    var arr = Manage.vm.history.data[0][param + 'Area'].split('-');
                    if (this.history.showDevice == 'fhy') {
                        arr[1] = 10000;
                    }
                    var number = ((Number(arr[1]) -  Number(arr[0])) / 4.5);
                    var fix = 1;
                    if (this.history.paramType == 'sG') {   //比重保留三位小数
                        fix = 3;
                    }
                    if (this.history.paramType == 'uA') {   //尿酸保留2位小数
                        fix = 2;
                    }
                    for (var i = -1; i <= 5; i ++) {
                        tickPositions.push((Number(arr[0]) + number * i).toFixed(fix))
                    }
                    plotLines = arr;
                }

                if (this.history.showDevice == 'fhy') {
                    plotLines.splice(1, 1);
                }
                if (this.history.showDevice == 'band' && this.history.showDevice != 'ecg') {
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
                Manage.vm.initChart(data);
            }, 300);

        },
        /**初始化图表*/
        initChart: function (data) {
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
    },
    computed: {
        /**返回current用户信息*/
        // currentMember: function () {
        //     if (this.tempMembers != null) {
        //         return this.tempMembers[this.currentId];
        //     }
        // },
        v_2_members: function () {
            return this.v_2.members;
        },
        historyShowDevice: function() {
            return this.history.showDevice;
        },
        historyDateType: function () {
            return this.history.dateType;
        },
        historyData: function () {
            return this.history.data;
        },
        historyPageData: function () {
            return this.history.pageData;
        },
        /**返回设备名称*/
        deviceName: function () {
            var msg = null;
            switch (this.history.showDevice) {
                case 'xty':
                    msg = '血糖仪';
                    break;
                case 'xyj':
                    msg = '血压计';
                    break;
                case 'xyy':
                    msg = '血氧仪';
                    break;
                case 'fhy':
                    msg = '肺活仪';
                    break;
                case 'band':
                    msg = '心率手环';
                    break;
                case 'tzc':
                    msg = '体脂秤';
                    break;
                case 'twj':
                    msg = '体温计';
                    break;
                case 'xzy':
                    msg = '血脂仪';
                    break;
                case 'ny':
                    msg = '尿液分析仪';
                    break;
                case 'ns':
                    msg = '尿酸分析仪';
                    break;
                case 'ecg':
                    msg = '心电仪';
                    break;
                default:
                    break
            }
            return msg;
        },

    },
    watch: {
        tempMembers: function () {
            //渲染完成后触发事件
            this.$nextTick(function(){
                $('.member-content-lists').find('li').eq(0).click();
                $('.member-list p').eq(0).click();
               /* for (var m in this.tempMembers) {
                    var head = this.tempMembers[m].photo;
                    console.log('head:', head);
                    document.styleSheets[0].addRule('.member-content-lists li:nth-of-type('+ (Number(m) + 1) +')::before','background-image:url('+ head +')');
                }*/

            })
        },
        /**监听date变化*/
        date: function () {
            if (this.date != null) {    //TODO
                this.isChange = {
                    1: true,
                    2: true,
                    3: true,
                    4: true
                }
                this.isChange[this.currentType] = false;
                switch (this.currentType) {
                    case 1:
                        this.getHealthData();
                        break;
                    case 2:
                        this.getPhysicalData();
                        break;
                    case 3:
                        // this.getMedicalData();
                        this.listMedicalPageData();
                        break;
                    case 4:
                        this.getDietData();
                        break;
                    default:
                        break;
                }
            }
           /* this.$nextTick(function () {
                Manage.vm.initDate();
            })*/
        },
        /**搜索框*/
        search: function () {
            if (this.search.length > 0) {
                $('.search-content').removeClass('none');
                $('.layui-tab-item-group-1').removeClass('layui-show');
                $('.layui-tab-item-group-2').removeClass('layui-show');
                $('.layui-tab-title-group').addClass('none');
                $('.member-search i').addClass('none');
            } else {
                $('.search-content').addClass('none');
                $('.layui-tab-title-group').find('li').eq(0).click();
                $('.layui-tab-title-group').removeClass('none');
                $('.member-search i').removeClass('none');
            }
            for (var m in this.v_2.members['全部']) { //只筛选全部
                //依次检测账户名、真实姓名、备注名称
                /*console.log(this.v_2.members['全部'][m].userName + '、' +
                    this.v_2.members['全部'][m].realName + '、' +
                    this.v_2.members['全部'][m].userRemark +
                    this.search)*/
                if (this.v_2.members['全部'][m].userName.indexOf(this.search) != -1) {
                    this.v_2.members['全部'][m].display = true;
                    continue;
                }
                if (this.v_2.members['全部'][m].realName != null && this.v_2.members['全部'][m].realName.indexOf(this.search) != -1) {
                    this.v_2.members['全部'][m].display = true;
                    continue;
                }
                if (this.v_2.members['全部'][m].userRemark != null && this.v_2.members['全部'][m].userRemark.indexOf(this.search) != -1) {
                    this.v_2.members['全部'][m].display = true;
                    continue;
                }
                this.v_2.members['全部'][m].display = false;

            }
        },
        physicalData: function () {
            /*this.$nextTick(function () {
                $('.physical-img img').bigic();
            })*/
        },
        /**修改用户病种*/
        userDiseases: function () {
            if (this.userDiseases == null || this.userDiseases == this.currentMember.userDiseasesId) {
                return;
            }
            var name = $('.user-diseases option:selected').text();
            this.modifyUserDiseases(this.userDiseases, name);
        },
        /**当前用户*/
        currentMember: function () {

            this.currentMember.userDiseasesId = this.currentMember.userDiseasesId == 0 ? -1 : this.currentMember.userDiseasesId;
            this.userDiseases = this.currentMember.userDiseasesId;
        },

        v_2_members: function () {
           this.$nextTick(function () {
               $('.main-content-left-bottom .user-list').on('click', function () {
                   var index = $(this).index();
                   index = index > 0 ? index/2 : index;
                   var $obj = $('.autocompleter-list').eq(index);
                   if ($obj.hasClass('none')) {
                       $obj.show(300);
                       $obj.removeClass('none');
                       $(this).addClass('on');
                       return;
                   }
                   $obj.hide(300);
                   $obj.addClass('none');
                   $(this).removeClass('on')
               });
               /*$('.main-content-left-bottom li').on('click', function () {
                   $(this).addClass('on').siblings().removeClass('on')
               })*/
           })
        },
       /* historyShowDevice: function () {
            this.$nextTick(function () {
                Manage.vm.initChart();
            })
        }*/
        historyDateType: function () {
            if (this.historyDateType == null) {
                return;
            }
            this.listHistoryChartData();

        },
        historyShowDevice: function () {
            this.$nextTick(function () {
                if (Manage.vm.historyShowDevice == null) {
                    return;
                }
                $('#member-dialog-content').scroll(function(){
                    var srollPos = $('#member-dialog-content').scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
                    var totalheight = parseFloat($('#member-dialog-content').height()) + parseFloat(srollPos);
                    if(Manage.vm.history.finished && ($('.member-dialog').height()-50) <= totalheight) {
                        Manage.vm.history.finished = false;
                        Manage.vm.history.page ++;
                        setTimeout(function () {
                            Manage.vm.listHistoryPageData();
                        }, 500);


                    }
                });
            })
        },
        historyPageData: function () {
            Manage.vm.history.finished = true;
        }
    },
    updated: function() {


    },
    filters: {
        gender: function (value) {
            var msg = null;
            switch (value) {
                case true:
                    msg = '男';
                    break;
                case false:
                    msg = '女';
                    break;
            }
            return msg;
        },
        date: function (value, format) {
             // var date = null;
            if (value == '' || value == undefined) {
                return '';
            }
             return new Date(value).Format(format);
        },
        /**睡眠时长转换*/
        sleepTime: function (value) {
            if (value <= 60) {
                return value + ' 分';
            }
            var hour =  Math.floor(value/60);
            var min = value%60;
            return hour + ' 时' + (min == 0 ? '' : min + ' 分');
        },
        /**
         * 测量类型装换
         * 测量类型：早餐前_1,早餐后_2,午餐前_3,午餐后_4,晚餐前_5,晚餐后_6
         */
        measureType: function (value) {
            var msg = null;
            switch (value) {
                case 1:
                    msg = '早餐前';
                    break;
                case 2:
                    msg = '早餐后';
                    break;
                case 3:
                    msg = '午餐前';
                    break;
                case 4:
                    msg = '午餐后';
                    break;
                case 5:
                    msg = '晚餐前';
                    break;
                case 6:
                    msg = '晚餐后';
                    break;
            }
            return msg;
        },
        /**移除小数点*/
        removeDecimalPoint: function (value, type) {
            switch (type) {
                case 1:
                    if (value == null) {
                        return null;
                    }
                    var arr = [];
                    arr = value.split('.');
                    return arr[0];
            }
        },
        /**字符串截取*/
        subStr: function (value, len) {
            if (value == null) {
                return value;
            }
            if (value.length < len) {
                return value;
            }
            return value.substr(0, len) + '...';
        },
        imgIsNone: function (value, type) {
            switch (type) {
                case 1: //头像模板
                    if (value == null || value == undefined) {
                        return '/static/platform/v2.2.0/images/template-head.png';
                    }
                    return value;
            }
        },
        isNone: function (value, type) {
            switch (type) {
                case 1:
                    if (value == null | value == undefined) {
                        return '无';
                    }
                    return value;
                    break;
            }
        }
    }
});

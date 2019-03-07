/**推送消息 */
registerController.controller('customerPushController',['$scope', function ($scope){

    $scope.page = {
        pageIndex: 1,
        pageSize: 15,
        totalSize: 0,//总数

    };
    $scope.appMsg ={
        message: '',
        size: 100,
        openType:'',
        serviceUser:'',
        serviceUserInfo:''
    };
    $scope.smsMsg = {
        message: '',
        size: 70,
    };
    $scope.selectTemplate=null;
    $scope.allUserData = {};
    $scope.selectedUserId =[];
    $scope.results = {};
    $scope.provinces = null;
    $scope.citys =null;
    $scope.reads = [];
    $scope.arr = [];
    $scope.query ={
        province:null,
        cityx : null,
        area : null,
        users : null, //用户会员
        projectCode:null,
        project:null,
        gender: null, // 性别
        startAge: null, //开始
        endAge: null, //结束
        weight:null,
        label :null,
        mobile:null,//输入
    };

    $scope.smsTemplate ={
        data: {
            '服务到期提醒': [
                '尊敬的    ，您的账户余额仅剩    ，为了确保您的正常使用，请及时充值。',
                '尊敬的    ，您的订购的预警短信仅剩*条，为了确保您的正常使用，请及时充值。',
                '尊敬的    ，您的订购的    咨询服务仅剩    天，如需*医生持续为您服务请继续订购服务，服务链接    。',
                '尊敬的    ，您的订购的    医生的    课堂服务仅剩*天，如需    医生持续为您服务请继续订购其课堂服务，服务链接    。',
                '尊敬的    ，您的订购的    套餐服务仅剩*天，如需续购请点击链接    。'
            ],
                '优惠提醒': [
                 '尊敬的    ，现在我平台    套餐做特惠活动，活动从    年    月    日到    年    月    日，活动不限名额，期待您的参与。参与链接    。',
                 '尊敬的    ，现在我平台    套餐做特惠活动，活动从    年    月    日到    年    月    日，活动限    位幸运名额，先到先得，我们期待您的参与。参与链接    。',
                 '尊敬的    ，您有    未领取，仅限*天参与活动才可领取，逾时将无法再享受，请点击链接    。',
                 '【优惠提醒】尊敬的*，从    年    月    日到    年    月    日，您可参与一次    优惠！活动期内在我平台消费满    元，即可赠送    ，本次赠送活动不叠加参与其他优惠，短信转发无效。'
            ],
                '通知提醒': [
                '尊敬的    ，您订购的**套餐已经生效，可在生效后**使用。',
                '尊敬的    ，您已成功充值*元短信，现可用预警短信条数为    条，短信预警功能即刻生效。',
                '尊敬的    ，感谢您使用生命守护，您的账号已设置完毕，祝您使用愉快。',
                '尊敬的    ，截至     年    月    日，您账户上还有    元。'
            ],
                '咨询信息': [
                 '我们给您准备的    的资讯，欢迎阅读。'
            ],
                '节日关怀信息': [
                '尊敬的    ，今天是    节，祝您    。'
            ]
        },
        type: '',
            currentData: null,
            selectedId: [],
            template: null,
    }
    $scope.init = function () {
        
        $('.msgUrl').css('display', 'none');
        $('.service-project').css('display', 'none');
        $('.select-spec-orguser').find('input').removeAttr("readonly");
        
        setTimeout(function () {
            // $('.content-left > ul >li:nth-child(2)').click();
            $scope.initPage();
        }, 300);
        $scope.findBydatalist();  // 普通用户
        $scope.getProvinces();
        for(var i =0 ; i < 100; i ++){
            $scope.arr.push(i);
        }
        layui.use('form',function(){
            var form = layui.form;
            form.on('select(select-template)', function(data){
                $scope.$apply(function(){
                    $scope.smsTemplate.currentData=$scope.smsTemplate.data[data.value];
                })
            });
        });
       // console.log( $scope.selectTemplate)
    }

    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                laypage.render({
                    elem: 'page'
                    ,count: $scope.page.totalSize
                    ,limit: $scope.page.pageSize
                    ,theme: '#00bfff'
                    ,layout: ['count', 'prev', 'page', 'next', 'skip']
                    ,jump: function(obj, first){
                        if(!first){
                            $scope.page.pageIndex = obj.curr;
                            $scope.findBydatalist();
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
                        $scope.findBydatalist();
                    }
                }
            });
        }
    }
    /**
     * 条件查询
     */
    $scope.findBydatalist = function () {
        var url = "/datalist/list-data-statistics/" + $scope.page.pageIndex;
        var areaCode = null;
        if(typeof($scope.query.area)!="undefined" && $scope.query.area != null){
            areaCode = $scope.query.area.name;
        }
        var data = {
                area : areaCode,
                users : $scope.query.users,
                gender : $scope.query.gender,
                startAge : $scope.query.startAge,
                endAge : $scope.query.endAge,
                weight: null,
                label : $scope.query.label,
                mobile : $scope.query.mobile
        };
//        http.ajax.post(true,true,url,$scope.query,http.ajax.CONTENT_TYPE_1,function (result) {
        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.results = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
                for(var i in $scope.results){
                    $scope.allUserData[$scope.results[i].userId] = $scope.results[i];
                }
            })

        })
    }

    $scope.getProvinces = function () {
        var url = "/datalist?getProvince";
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                $scope.provinces =result.obj;
            })
        })
    };
    $scope.gerCity = function () {
      var code = null;
      if(typeof($scope.query.province)!="undefined" && $scope.query.province != null){
          code = $scope.query.province.code;
      }
        
        if (code =="" || code == null){
            return;
        }
        
//        var code = $scope.query.province.code;
        var data = {
            provinceCode :  code.substr(0, 2)
        };
        $.ajax({
            async : true,
            cache : false,
            type : 'GET',
            url: "/datalist?getCity",
            data: data,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            beforeSend:function(){

            },
            complete:function(){
            },
            success: function(result) {
                if(result.success){
                    if($scope.citys != null){
                        $scope.reads = null;
                    }
                    $scope.citys = result.obj;
                    $scope.$apply();
                }
            }
        });
    };
    $scope.gerArea = function () {
        var code = null;
        if(typeof($scope.query.cityx)!="undefined" && $scope.query.cityx != null){
            code = $scope.query.cityx.code;
        }
        
//        var code = $scope.query.cityx.code;
        if (code =="" || code == null){
            return;
        }

        var data = {
            provinceCode :  code.substr(0, 4)
        }
        $.ajax({
            async : true,
            cache : false,
            type : 'GET',
            url: "/datalist?getArea",
            data: data,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            beforeSend:function(){
            },
            complete:function(){
            },
            success: function(result) {
                if(result.success){
                    if($scope.reads != null){
                        $scope.reads = null;
                        $scope.query.area =null;
                    }
                    $scope.reads = result.obj;
                    $scope.$apply();
                }
            }
        });
    };
    /** 弹出推送选择框*/
     $scope.popupSendMsg =function () {
         if ($scope.selectedUserId.length < 1) {
             layer.msg('至少选择一个用户', {icon: 5});
             return;
         }
         this.lay = layer.open({
             type: 1,
             title: false,
             shadeClose: false,
             moveType: 1,
             // area: ["1000px", "800px"],
             offset: ['30%', '51%'],
             closeBtn: false,
             content: $('.push-type')
         });
     }
    /*关闭推送方式选择框*/
    $scope.closeSendMsgDialog = function () {
        layer.close(this.lay);
    };
    $scope.closeLay2 = function () {
        layer.close(this.lay2);
    };
    /*弹出sms模板选择框*/
    $scope.popupSmsTemplateDialog=function () {
        this.lay2 = layer.open({
            type: 1,
            title: false,
            shadeClose: false,
            moveType: 1,
            // area: ["1000px", "800px"],
            offset: ['30%', '51%'],
            closeBtn: false,
            content: $('.push-sms-template')
        });
    };
    /**确认sms模板*/
    $scope.confirmSmsTemplate = function () {
        if (this.smsTemplate.selectedId.length < 1) {
            layer.msg('请选择模板', {icon: 5});
            return;
        }
        this.smsTemplate.template = this.smsTemplate.currentData[this.smsTemplate.selectedId];
        this.smsMsg.message = this.smsTemplate.template;

        this.smsMsg.message = this.smsMsg.message
        $('.sms-message-content').find('input').eq(0).val();

        layer.close(this.lay2);
    };
    /**发送app推送消息*/
    $scope.appSendMsg = function () {
        layer.load(2, {offset: ['55%', '50%']});
        if(typeof(this.appMsg.title)=="undefined" || this.appMsg.title == ""){
            layer.closeAll('loading');
            layer.msg('请填写标题!', {icon: 5});
            $('.message-title').focus();
            return;
        }
        
        var url = '/push/app';
        var arr = [];
        var paramValue = null;
        var serviceUserId = null;
        for (var i in $scope.selectedUserId) {
            arr.push($scope.selectedUserId[i]);
        }
        
        if(typeof(this.appMsg.openType)=="undefined" || this.appMsg.openType==""){
            layer.closeAll('loading');
            layer.msg('请选择消息类型.', {icon: 5});
            $('.openType').focus();
            return;
        }
        var openTypeValue = parseInt(this.appMsg.openType);
        if(3 == openTypeValue){
            if(typeof(this.appMsg.paramUrl)=="undefined" || typeof(this.appMsg.paramUrl)==""){
                layer.closeAll('loading');
                layer.msg('请设置URL.', {icon: 5});
                $('.paramUrl').focus();
                return;
            } else{
                paramValue = this.appMsg.paramUrl;
            }
        }else if(2 == openTypeValue){
            if(typeof(this.appMsg.appType)=="undefined" || typeof(this.appMsg.appType)==""){
                layer.closeAll('loading');
                layer.msg('请选择页面类型.', {icon: 5});
                $('.appType').focus();
                return;
            }else if (typeof(this.appMsg.serviceUserInfo)=="undefined" || this.appMsg.serviceUserInfo=="" 
                || this.appMsg.serviceUserInfo==null){
                layer.closeAll('loading');
                layer.msg('请选择服务师.', {icon: 5});
                $('.serviceUserInfo').focus();
                return;
            }else{
                paramValue = this.appMsg.appType;
                serviceUserId = this.appMsg.serviceUserInfo.id;
            }
        }

        if (this.appMsg.message.length < 1) {
            layer.closeAll('loading');
            layer.msg('请输入消息内容', {icon: 5});
            return;
        }
        if (this.appMsg.message.length > this.appMsg.size) {
            layer.closeAll('loading');
            layer.msg('消息字数超出限制', {icon: 5});
            $('.message-content').focus();
            return;
        }
        
        var data = {};
        if($("#all").is(':checked')==true){
            var codeStr = null;
            var cityxStr = null;
            var areaStr = null;
            if(typeof($scope.query.province)!="undefined" && $scope.query.province != null){
                codeStr = $scope.query.province.code;
            }
            if(typeof($scope.query.cityxStr)!="undefined" && $scope.query.cityxStr != null){
                codeStr = $scope.query.cityxStr.code;
            }
            if(typeof($scope.query.areaStr)!="undefined" && $scope.query.areaStr != null){
                codeStr = $scope.query.areaStr.code;
            }
            
            data = {
                    checkAll:true,
                    ids: arr,
                    province:   codeStr,
                    cityx : cityxStr,
                    area : areaStr,
                    users : $scope.query.users,
                    gender : $scope.query.gender,
                    startAge : $scope.query.startAge,
                    endAge : $scope.query.endAge,
                    label : $scope.query.label,
                    mobile : $scope.query.mobile
            }
        }else{
            data = {
                    checkAll:false,
                    ids: arr
                }
        }
        data["content"] = this.appMsg.message;
        data["openType"] = openTypeValue;
        data["paramUrl"] = paramValue;
        data["title"] = this.appMsg.title;
        data["params"] = serviceUserId;
        
        http.ajax.post(true, true, url, data,http.ajax.CONTENT_TYPE_1,function (result) {
            layer.closeAll('loading');
            layer.msg(result.obj+'条信息'+result.msg, {icon: 1});
            layer.close($scope.lay);
        })
    };
    /**发送sms推送消息*/
    $scope.sendSmsMsg = function () {
        var temp = $scope.smsMsg.message;
        var arr = [];
        var nameArr = [];
        var idArr = [];
        for (var i in $scope.allUserData) {
            for(var j in $scope.selectedUserId)
            if ($scope.allUserData[i].userId == $scope.selectedUserId[j]) {
                arr.push(this.allUserData[i].mobile);
                nameArr.push(this.allUserData[i].realName);
                idArr.push(this.allUserData[i].userId);
            }
        }
        var url = '/push/sms';
        var data = {
            mobiles: arr,
            content: temp,
            names: nameArr,
            ids: idArr
        }
        http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            layer.msg(result.msg, {icon: 1});
            layer.close($scope.lay);
        })
    };

    /*弹出app推送方式框*/
    $scope.popupAppMsgDialog = function () {
        this.closeSendMsgDialog();
        this.lay = layer.open({
            type: 1,
            title: false,
            shadeClose: false,
            moveType: 1,
            // area: ["1000px", "800px"],
            offset: ['30%', '51%'],
            closeBtn: false,
            content: $('.push-app')
        });
    };
    /*弹出sms推送方式框*/
    $scope.popupSmsMsgDialog = function () {
        this.closeSendMsgDialog();
        this.lay = layer.open({
            type: 1,
            title: false,
            shadeClose: false,
            moveType: 1,
            // area: ["1000px", "800px"],
            offset: ['30%', '51%'],
            closeBtn: false,
            content: $('.push-sms')
        });
    };
   $scope.selectAll=function(){
        var checks = $('.userCode');
        $scope.selectedUserId.length=0;
        if($("#all").is(':checked')==true){
            $.each(checks,function(val,index){
//              $(this).attr('checked',true)
                this.checked = true; 
                $scope.selectedUserId.push($(this).val())
            });
        }else if($('#all').is(':checked')==false){
            $.each(checks,function(val,index){
//                $(this).attr('checked',false)
                this.checked = false; 
            })
        }
    };
    $scope.clearUserCode=function(){

        var checks = $('.userCode');
        $scope.selectedUserId.length=0;
        for(var i=0;i<checks.length;i++){
            if(checks[i].checked==true){
                $scope.selectedUserId.push(checks[i].getAttribute('value'))
            }
        }
     // $scope.selectedUserId.length=0;
    }
    /**搜索*/
    $scope.searchStatistics= function (type) {
        
        var query = this.query;
        if ((query.startAge == '' && query.endAge != '') ||
            (query.startAge != '' && query.endAge == '')) {
            layer.msg('请选择年龄段', {icon: 5});
            return;
        }
        
        if((typeof($scope.query.label)!="undefined" && $scope.query.label != null) 
                && (typeof($scope.query.users)=="undefined" || $scope.query.users == null)){
            layer.msg('请选择用户级别!', {icon: 5});
            return;
        }
        
        switch (type) {
            case 1:
                // this.listStatistics();
                break;
            case 2:
                this.findBydatalist();
                break;
        }
        this.selectedUserId = [];
    };
    $scope.smsTemplateType = function () {
        this.smsTemplate.currentData = this.smsTemplate.data[this.smsTemplate.type]
    };
    $scope.smsTemplateSelectedId = function () {
        if (this.smsTemplate.selectedId.length > 1) {
            this.smsTemplate.selectedId.splice(0, 1);
        }
    };
    
    $scope.searchService = function(){
        var url = '/orgUser/listByRealName';
        var data = {
                rName: this.appMsg.serviceUser,
        }
        http.ajax.get_no_loading(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if($scope.orgUserList !=null){
                $scope.orgUserList = null;
                $scope.serviceUserInfo = '';
            }
            $scope.orgUserList = result.obj;
            $scope.$apply();
        });
    };
    
    $scope.openTypeMethod=function(){
        if(this.appMsg.openType == 3){
            $('.service-project').val("");
            $('.service-project').css('display', 'none');
            $('.msgUrl').css('display', 'block');
        }else if(this.appMsg.openType == 2){
            $('.msgUrl').val("");
            $('.msgUrl').css('display', 'none');
            $('.service-project').css('display', 'block');
        }else{
            $('.msgUrl').val("");
            $('.msgUrl').css('display', 'none');
            $('.service-project').val("");
            $('.service-project').css('display', 'none');
        }
    };
    
    
}]);

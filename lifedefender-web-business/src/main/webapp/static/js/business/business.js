/** 申明参数 */
registerController.controller('sellManageController',['$scope', '$rootScope', function ($scope, $rootScope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.ManAgeMent = null; //列表
    $scope.OrgUser = [];
    $scope.serveList =[];
    $scope.modify ={
        id:null,
        userName : null,
        name:null
    }

    $scope.Fields ={
        userName:null,
        name:null,
        address:null,
        phone:null,
        status:0,   // 状态 0 正常
        password:null,
        pwds:null,//重复密码
        mailbox:null//邮箱

    }

    $scope.init = function () {
        $('.layui-nav').find('.layui-nav-item').eq(2).addClass('layui-nav-itemed')
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav-itemed .layui-nav-child').find('dd').eq(2).addClass('layui-this');
        $scope.initPage();
        $scope.findByAllManageMent();
        //$scope.listStore();
    };

    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                console.log('wait for laypage...');
                laypage.render({
                    elem: 'page'
                    , count: $scope.page.totalSize
                    , limit: $scope.page.pageSize
                    , theme: '#00bfff'
                    , layout: ['count', 'prev', 'page', 'next', 'skip']
                    , jump: function (obj, first) {
                        if (!first) {
                            $scope.page.pageIndex = obj.curr;
                            $scope.findByAllManageMent();
                        }

                    }
                });
            }, 300);
        } else {
            laypage.render({
                elem: 'page'
                , count: $scope.page.totalSize
                , limit: $scope.page.pageSize
                , theme: '#00bfff'
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (obj, first) {
                    if (!first) {
                        $scope.page.pageIndex = obj.curr;
                        $scope.findByAllManageMent();
                    }
                }
            });
        }

    }

    $scope.addsell = function(superior){
       if(superior != 0){
            layer.msg("对不起、您无此权限操作！！");
            return;
        }
        var index =layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加用户</p>",
            area:['550px','auto'],
            content:$('#orderCenterPopup')
        })
    }

    $scope.findByAllManageMent = function(){
        var url="/sell/list/" +$scope.page.pageIndex;
        http.ajax.post(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.ManAgeMent = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }

    $scope.openManAgeMent = function(business){
        $scope.modify.id = business.id;
        $scope.modify.userName =business.userName;
        $scope.modify.name =business.name;
        var index =layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>修改推销员</p>",
            area:['550px','auto'],
            content:$('#orderCenterPopup2')
        })
    }

    $scope.listStore = function() {
        var url = '/sell/store/stores';
         http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
             $scope.$apply(function(){
                 $scope.OrgUser = result.obj;
             })
         })
    }


    $scope.verifyEmail=function(str){
        var re = /^([\w\.\-]+)\@(\w+)(\.([\w^\_]+)){1,2}$/;
        if (re.test(str)) {
            return true
        } else{
            return false
        }

    }
    $scope.verifyNumber=function(val){
        var sMobile = val
        if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){
            return false;
        }else {
            return true;
        }
    }
    $scope.modifyBusiness = function(){
        var url ="/sell/update";
        console.log($scope)
        var data ={
            id:$scope.modify.id,
            userName:$scope.modify.userName,
            name:$scope.modify.name,
        }
        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                if(result.success){
                    layer.msg("修改成功！")
                    layer.closeAll('page');
                }
            })
        })
    }

    $scope.addBusiness = function(){
        var url ="/sell/add";
        var data = $scope.Fields;
        if($.trim($scope.Fields.userName)==""){
            layer.msg("请输入用户账号名！！");
            return;
        }
        if($.trim($scope.Fields.name)==""){
            layer.msg("请输入用户名！！");
            return;
        }
        if($.trim($scope.Fields.address) ==""){
            layer.msg("请输入地址！！");
            return;
        }

        if($scope.verifyNumber($.trim($scope.Fields.phone)) ==false){
            layer.msg("请输入正确的手机号！！");
            return;
        }
        if($.trim($scope.Fields.password) == ""){
            layer.msg("请输入密码....！");
            return;
        }
        if($.trim($scope.Fields.password) != $.trim($scope.Fields.pwds)){
            layer.msg("密码重复输入不一致！！");
            return;
        }


        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                if(result.success){
                    layer.msg("添加成功！");
                    location.reload();
                    layer.closeAll('page');
                }
            })
        })
    }
    // TODO 废弃
    $scope.listStoreServe = function(value){
        var url = "/sell/server/"+$scope.Fields.storeId;
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                $scope.serveList = result.obj;
                console.log($scope.serveList)
            })
        })
    }
}]);
/** 申明参数 */
registerController.controller('temporaryController',['$scope', '$rootScope', function ($scope, $rootScope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    };
    $scope.results = null; //列表
    $scope.dateDefault = null;
    $scope.temps ={
        name:null,
        date:"",
        bname:null
    };

    $scope.init = function () {
        $('.layui-nav').find('.layui-nav-item').eq(2).addClass('layui-nav-itemed')
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav-itemed .layui-nav-child').find('dd').eq(2).addClass('layui-this');
        $scope.initPage();
        $scope.dateSelect();
        $scope.findByAllTemporary();
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
                            $scope.findByAllTemporary();
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
                        $scope.findByAllTemporary();
                    }
                }
            });
        }

    }


    $scope.findByAllTemporary = function(){
        var url="/temp/list/" +$scope.page.pageIndex;
        http.ajax.post(true,true,url,$scope.temps,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.results = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }
    $scope.dateSelect=()=>{
        layui.use(['form','laydate'],()=>{
            const laydate = layui.laydate;
            laydate.render({
                elem: '#dateSelect',type: 'month',
                value: new Date()
                ,done: function(value, date, endDate){
                   $scope.temps.date = value;
                }
            })
        })
    }

    $scope.show = function(){
        $scope.findByAllTemporary();
    }
}]);
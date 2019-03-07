/**
 * 分析报告-订单中心
 * author: wenxian.cai
 * date: 2017/10/25 11:15
 */

registerController.controller('reportAnalysisOrderController',['$scope', function ($scope) {
    /** 申明参数 */

    $scope.orders = null;
    $scope.conditions = {
        orderStatus: '',
        keyword: null
    }
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }

    /** 声明函数 */

    /*初始化*/
    $scope.init = function () {
        $('.layui-nav').find('.layui-nav-item').eq(1).addClass('layui-nav-itemed')
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav-itemed .layui-nav-child').find('dd').eq(1).addClass('layui-this');

        $scope.listOrders();
        $scope.initPage();
    }

    /*获取订单列表*/
    $scope.listOrders = function () {
        var data = $scope.conditions;
        var url = '/report-analysis/orders/' + $scope.page.pageIndex;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if ($scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.initPage();
                }
                $scope.orders = result.obj.data;
                $scope.page.pageIndex = result.obj.nowPage;
                $scope.page.totalSize = result.obj.totalSize;

            });
        })
    }

    /*搜索*/
    $scope.search = function () {
        $scope.page.pageIndex = 1;
        $scope.listOrders();
    }

    /** 回车搜索 */
    $scope.enterSearch = function($event) {
        if ($event.keyCode == 13) {
            $scope.search();
        }
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
                            $scope.listOrders();
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
                        $scope.$apply(function () {
                            $scope.page.pageIndex = obj.curr;
                            $scope.listOrders();
                        })

                    }
                }
            });
        }
    }

    $scope.statusClass = function (status) {
         switch (status) {
             case 1:
                 return 'color_red';
             case 3:
                 return 'color_orange';
             case 4:
                 return 'color_10bb71';
         }
    }
    
    /** 参数监听 */

    $scope.$watch('conditions.orderStatus', function (newValue, oldValue, scope) {
        if (newValue == oldValue) {
            return;
        }
        $scope.listOrders();
    })
}]);
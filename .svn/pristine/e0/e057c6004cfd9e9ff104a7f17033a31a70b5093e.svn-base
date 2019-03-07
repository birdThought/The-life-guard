
registerController.controller('vipMemberFinancialController',['$scope', '$rootScope', function ($scope, $rootScope) {
    /** 声明参数*/
    $scope.month = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.orders = null;
    $scope.date = new Date().getMonth() + 1;
    $scope.bill = null;

    /** 声明函数*/
    /*初始化*/
    $scope.init = function () {

        $('.layui-nav').find('.layui-nav-item').eq(0).addClass('layui-nav-itemed')
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav-itemed .layui-nav-child').find('dd').eq(2).addClass('layui-this');

        $scope.listOrders();
        $scope.initPage();
        $scope.listOrdersBill();
    }

    /*获取订单列表*/
    $scope.listOrders = function () {
        var url = '/vip-member/financial/orders/' + $scope.page.pageIndex;
        var date = new Date().getFullYear() + '-' + ($scope.date > 9 ? $scope.date : '0' + $scope.date) + '-01';
        var data = {
            month: date
        }
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.orders = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }

    /*获取总账单*/
    $scope.listOrdersBill = function () {
        var url = '/vip-member/financial/bill';
        var date = new Date().getFullYear() + '-' + ($scope.date > 9 ? $scope.date : '0' + $scope.date) + '-01';
        var data = {
            month: date
        }
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.bill = result.obj;
            })
        })
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

    /** 参数监听 */
    $scope.$watch('date', function (newVal, oldVal, scope) {
        if (newVal == oldVal) {
            return;
        }
        $scope.page.pageIndex = 1;
        $scope.listOrders();
        $scope.listOrdersBill()
    })

}]);
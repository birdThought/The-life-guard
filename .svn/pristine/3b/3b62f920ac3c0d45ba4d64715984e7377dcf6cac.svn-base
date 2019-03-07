/**预约记录*/
registerController.controller('success-customerOrderController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.customerorders = null;
    $scope.conditions =null;
//    $scope.finsh={
//        id:     		null,
//        orgName:        null,
//        sureDate:       null,
//        address:        null,
//        customerRemark: null,
//    }
    currentOrder: null;

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
   /* setTimeout(function () {
            $('.content-left ul li').eq(3).click();
        }, 100)*/
        $scope.listOrder();
        $scope.initPage();
    }

     /*获取工单列表*/
    $scope.listOrder = function () {
        var url = '/combo/member/worklist/data/list/success/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.customerorders = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
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
                            $scope.listOrder();
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
                        $scope.listOrder();
                    }
                }
            });
        }
    }
}]);
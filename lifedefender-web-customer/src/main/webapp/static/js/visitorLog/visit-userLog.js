/**预约记录*/
registerController.controller('visit-userLogController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    };
    $scope.userLog = null;
    $scope.conditions ={
    		userType : null,
    		terminalType : null
    };
    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listUserLog();
        $scope.initPage();
    };

     /*获取工单列表*/
    $scope.listUserLog = function () {
        var url = '/pv/visit/list/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        for (var k in data) {
        	if (!data[k]) {
        		delete data[k];
        	}
        }
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.userLog = result.obj.data;
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
                            $scope.listUserLog();
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
                        $scope.listUserLog();
                    }
                }
            });
        }
    }
    
    $scope.search = function(){
    	$scope.listUserLog();
    }
    /**参数监听 */
    $scope.$watch('conditions.userType + conditions.terminalType', function (newVal, oldVal, scope) {
        if (newVal === oldVal) {
            return;
        }
        $scope.search();
    })
}]);
/** 订单列表*/
angular.module("Controllers",[]).controller('shopOrderController',['$scope','$rootScope', function ($scope,$rootScope) {
	//分页需要的参数
    $scope.page = {
        pageIndex: 1,//要查的当前页
        pageSize: 15,//每页行数
        totalSize: 0,//总数录数
        totalPage: 0 //总页数
    };
    //条件参数
    $scope.conditions = {
    		pageSize: $scope.page.pageSize,
        	shopName: null,
        	userName: null,
        	orderNo: null,
        	mobile:null,
        	status: null
        };
        
       
    //加载初始化
    $scope.init = function () {
    	$scope.userInfo = $rootScope.userInfo;
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.searchOrderList();
    }
    
    
    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                laypage.render({
                    elem: 'page'
                    , count: $scope.page.totalSize
                    , limit: $scope.page.pageSize
                    , theme: '#00bfff'
                    , layout: ['count', 'prev', 'page', 'next', 'skip']
                    , jump: function (obj, first) {
                        if (!first) {
                            $scope.page.pageIndex = obj.curr;
                            $scope.searchOrderList();
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
                        $scope.searchOrderList();
                    }
                }
            });
        }
    }
    
    $scope.searchOrderList = function(){
    	var url = '/commodity/order/list/' + $scope.page.pageIndex;
    	http.ajax.get(true, false, url, $scope.conditions, http.ajax.CONTENT_TYPE_1, function (result) {
        	$scope.$apply(function(){
        		if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.page.pageSize = result.obj.pageSize;
                    $scope.initPage();
                }
                $scope.results = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
                $scope.page.pageSize = result.obj.pageSize;
        	});
        });
    }
    
    /**
     * 清空查询框
     */
    $scope.resetSearch = function(){
    	 $scope.conditions = {
    	        	shopName: null,
    	        	userName: null,
    	        	orderNo: null,
    	        	mobile:null,
    	        	status: null
    	        };
    }
    
    /**
     * 物流信息跳转
     */
    $scope.logistics = function(shoppingNo,com){
    	var url = 'https://www.kuaidi100.com/chaxun?nu=' + shoppingNo;
    	if(typeof com != 'undefined'){
    		url = url + '&com=' + com;
    	}
//    	alert(url)
    	window.open(url,'wuliu');
    }
    
}]);
/** 商铺列表*/
angular.module("Controllers",[]).controller('shopController',['$scope','$rootScope', function ($scope,$rootScope) {
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
        	orgName: null,
        	state: null
        };
        
       
    //加载初始化
    $scope.init = function () {
//    	console.log(111);
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.searchShopList();
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
                            $scope.searchShopList();
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
                        $scope.searchShopList();
                    }
                }
            });
        }
    }
    
    $scope.searchShopList = function(){
    	var url = "/commodity/shop/list/" + $scope.page.pageIndex;
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
     * 解冻
     */
    $scope.unfrozen = function(shop){
    	layer.confirm("确定解冻吗?", {icon: 3, title:'提示'}, function(index) {
    		var url = "/commodity/shop/changeState/unfrozen";
        	http.ajax.post(true, false, url, {id:shop.id}, http.ajax.CONTENT_TYPE_1, function (result) {
        		$scope.$apply(function(){
        			if(result.success){
        				shop.state = result.obj;
        			}
        		});
        	});
        	layer.close(index);
    	});
    	
    }
    
    /**
     * 冻结
     */
    $scope.frozen = function(shop){
    	layer.confirm("确定冻结吗?", {icon: 3, title:'提示'}, function(index) {
	    	var url = "/commodity/shop/changeState/frozen";
	    	http.ajax.post(true, false, url, {id:shop.id}, http.ajax.CONTENT_TYPE_1, function (result) {
	    		$scope.$apply(function(){
	    			if(result.success){
	    				shop.state = result.obj;
	    			}
	    		});
	    	});
	    	layer.close(index);
    	});
    }
    
    $scope.authition = {} // 审核结果对象
    
    $scope.authitShop = null; // 审核商铺
    
    /**
     * 审核
     */
    $scope.authit = function(shop) {
    	$scope.authitShop = shop;
    	$scope.authition.shopId = shop.id;
    	$scope.authition.userId = $rootScope.userInfo.id;
    	layer.open({
            type: 1,
            title: ['商铺审核',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['800px'],
//            offset: 80,
            moveType: 1,
            scrollbar: false,
            zIndex: 9999,
            scrolling: 'no',
            content: $('#authit-content')
        });
    }
    
    /*
     * 提交审核 
     */
    $scope.commitAuthit = function(pass){
//    	console.log($scope.authition);
    	var url = "/commodity/shop/authit";
    	$scope.authition.pass = pass;
    	http.ajax.post(true, false, url, JSON.stringify($scope.authition), http.ajax.CONTENT_TYPE_2, function (result) {
    		$scope.$apply(function(){
    			if(result.success){
    				$scope.authitShop.state = result.obj;
    				$scope.authition.remarks = null;
    				$scope.authition.pass = 2;
    				$scope.authition.shopId = null;
    		    	$scope.authition.userId = null;
    				$scope.authitShop = null;
//    				console.log($scope.authition);/
    				layer.msg('成功!', {
					     time: 1000, //1s后自动关闭
					});
    			}
    		});
    	});
    	layer.closeAll();
    }
}]);
var temp_$scope = null;
/** 商品列表*/
angular.module("Controllers",[]).controller('shopGoodsController',['$scope','$rootScope', function ($scope,$rootScope) {
	 
	//分页需要的参数
    $scope.page = {
        pageIndex: 1,//要查的当前页
        pageSize: 12,//每页行数
        totalSize: 0,//总数录数
        totalPage: 0 //总页数
    };
    //条件参数
    $scope.conditions = {
    		pageSize: $scope.page.pageSize,
    		shopId: null,
    		goodsName: null,
        	shopName: null,
        	status: null
        };
        
    //加载初始化
    $scope.init = function () {
    	$scope.userInfo = $rootScope.userInfo;
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.searchGoodsList();
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
                            $scope.searchGoodsList();
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
                        $scope.searchGoodsList();
                    }
                }
            });
        }

    }

    $scope.searchGoodsList = function () {
        var url = "/commodity/goods/list/" + $scope.page.pageIndex;
        http.ajax.get(true, false, url, $scope.conditions, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
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
            })
        })
    }
    
    $scope.dump = function(url, type, goodsId){
    	var path = url + '/' + type + '/' + goodsId;
    	$scope.go(path);
    }
    
    /**
     * 下架
     */
    $scope.down = function(goods) {
    	layer.confirm("确定下架吗?", {icon: 3, title:'提示'}, function(index) {
    		var url = "/commodity/goods/changeStatus/down";
    		http.ajax.post(true, false, url, {id:goods.id}, http.ajax.CONTENT_TYPE_1, function (result) {
    			$scope.$apply(function(){
    				if(result.success){
    					goods.status = result.obj;
    				}
    			});
    		});
    		layer.close(index);
    	});
    }
    
    /**
     * 上架
     */
    $scope.up = function(goods) {
//    	console.log(goods);
    	layer.confirm("确定上架吗?", {icon: 3, title:'提示'}, function(index) {
    		var url = "/commodity/goods/changeStatus/up";
        	http.ajax.post(true, false, url, {id:goods.id}, http.ajax.CONTENT_TYPE_1, function (result) {
        		$scope.$apply(function(){
        			if(result.success){
    					goods.status = result.obj;
    				}
    			});
    		});
        	layer.close(index);
    	});
    }
    
    $scope.goodsDetails = {}

    $scope.details = function(goods) {
    	var url = "/commodity/goods/details";
    	http.ajax.get(true, false, url, {id:goods.id}, http.ajax.CONTENT_TYPE_1, function (result) {
    		$scope.$apply(function(){
    			if(result.success){
    				$scope.goodsDetails = result.obj;
    				$scope.goodsDetails.shopName = goods.shopName;
				}
			});
		});
    	layer.open({
            type: 1,
            title: ['商品详情',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['610px','750px'],
            scrollbar: false,
            scrolling: 'no',
            content: $('#goodsDetails')
            ,cancel: function(){ 
                //右上角关闭回调
            	$scope.goodsDetails = null;
            }
        });
    	
    }
}]);
(function(){
	angular.module("Controllers", [])
	.controller('smsRecordController',['$scope', '$rootScope', function ($scope, $rootScope) {

		/** 申明参数 */
	    $scope.page = {
	        pageIndex: 1,
	        pageSize: 10,
	        totalSize: 0
	    }

	    /*控制器初始化*/
	    $scope.init = function (){
	    	$scope.smsRecordList();
	        $scope.initPage();
	    }
	    
	    /** 查询条件 */
	    $scope.conditions = {
	            userName: null,
	            receiveMobile: null
	        }

	    $scope.smsRecordList = function() {
	    	var url = '/sms/data/list/' + $scope.page.pageIndex;
	    	var data =  $scope.conditions;
	    	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	            $scope.$apply(function () {
	                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
	                    $scope.page.totalSize = result.obj.totalSize;
	                    $scope.page.pageIndex = result.obj.nowPage;
	                    $scope.initPage();
	                }
	                $scope.smsRecord = result.obj.data;
	                console.log($scope.smsRecord)
	                $scope.page.totalSize = result.obj.totalSize;
	                $scope.page.pageIndex = result.obj.nowPage;
	            })
	        })
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
	                           $scope.smsRecordList();
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
	                       $scope.smsRecordList();
	                   }
	               }
	           });
	       }
	   }
	}]);
}());
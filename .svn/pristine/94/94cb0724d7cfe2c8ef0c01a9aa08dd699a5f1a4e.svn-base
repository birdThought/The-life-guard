/**测量原因管理*/
registerController.controller('reasonController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.reason = null;
    $scope.conditions={
    		healthPackageParamId : null,

    }
    $scope.healthParam=null;
    $scope.edit={
    		id 	 		 : null,
    		reason 		 : null,
    		status		 : null,
    		professional : null,
 
    }
    $scope.delete={
    		id: null,
    }

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listreason();
        $scope.initPage();
        $scope.listHealthParam();

    }

     /*获取测量原因列表*/
    $scope.listreason = function () {
        var url = '/data/measure-reason/list-reason/' + $scope.page.pageIndex;
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
                $scope.reason = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }
    
    /*获取健康参数列表*/
    $scope.listHealthParam=function(){
    	var url='/data/measure-reason/list-healthParam';
    	var data=null;
    	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
            	$scope.healthParam = result.obj;
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
                            $scope.listreason();
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
                        $scope.listreason();
                    }
                }
            });
        }
    }
    
    
    //弹出编辑测量原因
    $scope.EditDialog = function(reason){
    	
    	$scope.edit.id = reason.id;
    	$scope.edit.reason = reason.reason;
    	$scope.edit.status=reason.status;
    	$scope.edit.professional=reason.professional;    	
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>编辑测量原因</p>",
            area:['940px','600px'],
            content:$('#orderCenterPopup2')
    	})
    }
    
    $scope.editreason = function(){
    	var url ="/data/measure-reason/edit-reason";
    	var data=$scope.edit;
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listreason();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出删除框
    $scope.DeleteDialog = function (reason) {
	$scope.delete.id=reason.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
	$scope.deletereason = function () {
   
	       var url = '/data/measure-reason/delete-reason';
	       var data = $scope.delete;
	       
	        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	        	
	           if (result.success) {
	               layer.msg('操作成功!');
	               $scope.listreason();
	               layer.close($scope.lay);
	               return;
	           }
	           
	       })       		  
	}	   
}
    
    $scope.search = function(){
    	$scope.listreason();
    }
    /**参数监听 */
    $scope.$watch('conditions.healthPackageParamId',function (newVal, oldVal, scope){
    	if(newVal === oldVal){
    		return;
    	}
    	$scope.search();
    })
    
}]);
/**测量建议管理*/
registerController.controller('suggestionController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.suggestion = null;
    $scope.conditions={
    		healthPackageParamId : null,

    }
    $scope.healthParam=null;
    $scope.edit={
    		id 	 				 : null,
    		description 		 : null,
    		status				 : null,
    		healthPackageParamId : null,
    		gender				 : null,
    		startAge			 : null,
    		endAge				 : null,
    		display			   	 : null,
    }
    $scope.delete={
    		id: null,
    }

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listsuggestion();
        $scope.initPage();
        $scope.listHealthParam();

    }

     /*获取测量建议列表*/
    $scope.listsuggestion = function () {
        var url = '/data/measure-suggection/list-suggection/' + $scope.page.pageIndex;
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
                $scope.suggestion = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }
    
    /*获取健康参数列表*/
    $scope.listHealthParam=function(){
    	var url='/data/measure-suggection/list-healthParam';
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
                            $scope.listsuggestion();
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
                        $scope.listsuggestion();
                    }
                }
            });
        }
    }
    
    
    //弹出添加测量建议
    $scope.addDialog = function(){
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加测量建议</p>",
            area:['940px','600px'],
            content:$('#orderCenterPopup')
    	})	
    }
    $scope.addsuggestion = function(){
    	var url ="/data/measure-suggection/add-suggestion";
    	var data={
    		healthPackageParamId : $('#suggestion_healthPackageParamId').val(),
    		description			 : $('#suggestion_description').val(),
    		status				 : $('#suggestion_status').val(),
    		gender				 : $('#suggestion_gender').val(),
    		startAge			 : $('#suggestion_startAge').val(),
    		endAge				 : $('#suggestion_endAge').val(),
    		display				 : $('#suggestion_display').val(),
    	};
    	if($.trim(data.description) == '' || $.trim(data.description) == null){
    		layer.msg('请输入描述文字');
    		return false;
    	}
    	if($.trim(data.startAge) == '' || $.trim(data.startAge) == null){
    		layer.msg('请输入开始年龄');
    		return false;
    	}
    	if($.trim(data.startAge) < 0){
    		layer.msg('不能小于0');
    		return false;
    	}
    	if($.trim(data.endAge) == '' || $.trim(data.endAge) == null){
    		layer.msg('请输入结束年龄');
    		return false;
    	}
    	if($.trim(data.endAge) > 120){
    		layer.msg('不能大于120');
    		return false;
    	}
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listsuggestion();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    //弹出编辑测量建议
    $scope.EditDialog = function(suggestion){
    	
    	$scope.edit.id = suggestion.id;
    	$scope.edit.healthPackageParamId = suggestion.healthPackageParamId;
    	$scope.edit.description=suggestion.description;
    	$scope.edit.status=suggestion.status;
    	$scope.edit.gender=suggestion.gender;
    	$scope.edit.startAge = suggestion.startAge;
    	$scope.edit.endAge = suggestion.endAge;
    	$scope.edit.display = suggestion.display;
    	
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>编辑测量建议</p>",
            area:['940px','600px'],
            content:$('#orderCenterPopup2')
    	})
    }
    
    $scope.editsuggestion = function(){
    	var url ="/data/measure-suggection/edit-suggestion";
    	var data=$scope.edit;
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listsuggestion();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出删除框
    $scope.DeleteDialog = function (suggestion) {
	$scope.delete.id=suggestion.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
	$scope.deletesuggestion = function () {
   
	       var url = '/data/measure-suggection/delete-suggestion';
	       var data = $scope.delete;
	       
	        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	        	
	           if (result.success) {
	               layer.msg('操作成功!');
	               $scope.listsuggestion();
	               layer.close($scope.lay);
	               return;
	           }
	           
	       })       		  
	}	   
}
    
    $scope.search = function(){
    	$scope.listsuggestion();
    }
    /**参数监听 */
    $scope.$watch('conditions.healthPackageParamId',function (newVal, oldVal, scope){
    	if(newVal === oldVal){
    		return;
    	}
    	$scope.search();
    })
    
}]);
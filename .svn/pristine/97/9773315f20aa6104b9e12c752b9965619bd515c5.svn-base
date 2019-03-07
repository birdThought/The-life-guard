/**食物管理*/
registerController.controller('foodController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.food = null;
    $scope.conditions={
    		kind: null,
    		name: null,
    }
    $scope.foodKind=null;
    $scope.edit={
    		id 	 		: null,
    		name 		: null,
    		kcal		: null,
    		kind		: null,
    		image		: null,
    }
    $scope.delete={
    		id: null,
    }

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listfood();
        $scope.initPage();
        $scope.listFoodKind();

    }

     /*获取食物列表*/
    $scope.listfood = function () {
        var url = '/data/food/list/' + $scope.page.pageIndex;
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
                $scope.food = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }
    
    /*获取食物种类列表*/
    $scope.listFoodKind=function(){
    	var url='/data/food/kind/list';
    	var data=null;
    	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
            	$scope.foodKind = result.obj;
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
                            $scope.listfood();
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
                        $scope.listfood();
                    }
                }
            });
        }
    }
    
    
    //弹出添加食物种类
    $scope.addDialog = function(){
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加食物</p>",
            area:['940px','500px'],
            content:$('#orderCenterPopup')
    	})	
    }
    $scope.addfood = function(){
    	var url ="/data/food/add";
    	var data={
    		name	: $('#food_name').val(),	
    		kcal	: $('#food_kcal').val(),
    		kind	: $('#food_kind').val(),
    		image	: $('#food_image').val(),
    	};
    	if($.trim(data.name) == '' || $.trim(data.name) == null){
    		layer.msg('请输入食物名称');
    		return false;
    	}
    	if($.trim(data.kcal) == '' || $.trim(data.kcal) == null){
    		layer.msg('请输入卡路里');
    		return false;
    	}
    	if($.trim(data.kind) == '' || $.trim(data.kind) == null){
    		layer.msg('请输入食物种类名称');
    		return false;
    	}
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listfood();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    //弹出编辑食物种类
    $scope.EditDialog = function(food){
    	$scope.edit.id = food.id;
    	$scope.edit.name=food.name;
    	$scope.edit.kcal=food.kcal;
    	$scope.edit.kind=food.kind;
    	$scope.edit.image=food.image;
    	
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>编辑食物</p>",
            area:['940px','500px'],
            content:$('#orderCenterPopup2')
    	})
    }
    
    $scope.editfood = function(){
    	var url ="/data/food/edit";
    	var data=$scope.edit;
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listfood();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出删除框
    $scope.DeleteDialog = function (food) {
	$scope.delete.id=food.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
	$scope.deletefood = function () {
   
	       var url = '/data/food/delete';
	       var data = $scope.delete;
	       
	        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	        	
	           if (result.success) {
	               layer.msg('操作成功!');
	               $scope.listfood();
	               layer.close($scope.lay);
	               return;
	           }
	           
	       })       		  
	}	   
}
    
    $scope.search = function(){
    	$scope.listfood();
    }
    /**参数监听 */
    $scope.$watch('conditions.kind + conditions.name',function (newVal, oldVal, scope){
    	if(newVal === oldVal){
    		return;
    	}
    	$scope.search();
    })
    
}]);

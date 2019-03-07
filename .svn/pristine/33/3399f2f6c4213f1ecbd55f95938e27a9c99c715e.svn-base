/**运动管理*/
registerController.controller('sportController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.sport = null;
    $scope.conditions={
    		kind: null,
    		name: null,
    }
    $scope.sportKind=null;
    $scope.edit={
    		id 	 		: null,
    		name 		: null,
    		kcal		: null,
    		kind		: null,
    }
    $scope.delete={
    		id: null,
    }

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listsport();
        $scope.initPage();
        $scope.listsportKind();

    }

     /*获取运动列表*/
    $scope.listsport = function () {
        var url = '/data/sport/list-sport/' + $scope.page.pageIndex;
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
                $scope.sport = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }
    
    /*获取运动种类列表*/
    $scope.listsportKind=function(){
    	var url='/data/sport/list-sportKind';
    	var data=null;
    	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
            	$scope.sportKind = result.obj;
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
                            $scope.listsport();
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
                        $scope.listsport();
                    }
                }
            });
        }
    }
    
    
    //弹出添加运动种类
    $scope.addDialog = function(){
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加运动</p>",
            area:['940px','500px'],
            content:$('#orderCenterPopup')
    	})	
    }
    $scope.addsport = function(){
    	var url ="/data/sport/add-sport";
    	var data={
    		name	: $('#sport_name').val(),	
    		kcal	: $('#sport_kcal').val(),
    		kind	: $('#sport_kind').val(),
    	};
    	if ($.trim(data.name) == ''||$.trim(data.name) == null){
    		layer.msg('请输入运动名称');
            return false;
    	}
    	if ($.trim(data.kcal) == ''||$.trim(data.kcal) == null){
    		layer.msg('请输入卡路里');
            return false;
    	}
    	
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listsport();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    //弹出编辑运动种类
    $scope.EditDialog = function(sport){
    	$scope.edit.id = sport.id;
    	$scope.edit.name=sport.name;
    	$scope.edit.kcal=sport.kcal;
    	$scope.edit.kind=sport.kind;
    	
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>编辑运动</p>",
            area:['940px','500px'],
            content:$('#orderCenterPopup2')
    	})
    }
    
    $scope.editsport = function(){
    	var url ="/data/sport/edit-sport";
    	var data=$scope.edit;
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listsport();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出删除框
    $scope.DeleteDialog = function (sport) {
	$scope.delete.id=sport.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
	$scope.deletesport = function () {
   
	       var url = '/data/sport/delete-sport';
	       var data = $scope.delete;
	       
	        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	        	
	           if (result.success) {
	               layer.msg('操作成功!');
	               $scope.listsport();
	               layer.close($scope.lay);
	               return;
	           }
	           
	       })       		  
	}	   
}
    
    $scope.search = function(){
    	$scope.listsport();
    }
    /**参数监听 */
    $scope.$watch('conditions.kind + conditions.name',function (newVal, oldVal, scope){
    	if(newVal === oldVal){
    		return;
    	}
    	$scope.search();
    })
    
}]);

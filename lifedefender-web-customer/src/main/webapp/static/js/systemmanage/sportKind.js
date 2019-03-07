/**运动种类*/
registerController.controller('sportKindController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.sportKind = null;
    $scope.conditions=null;
    $scope.edit={
    		id 	 		: null,
    		name 		: null,
    }
    $scope.delete={
    		id: null,
    }

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listsportKind();
        $scope.initPage();

    }

     /*获取种类列表*/
    $scope.listsportKind = function () {
        var url = '/data/sportKind/list-sportKind/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.sportKind = result.obj.data;
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
                            $scope.listsportKind();
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
                        $scope.listsportKind();
                    }
                }
            });
        }
    }
    
    
    //弹出添加运动种类
    $scope.addDialog = function(){
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加种类</p>",
            area:['940px','300px'],
            content:$('#orderCenterPopup')
    	})	
    }
    $scope.addsportKind = function(){
    	var url ="/data/sportKind/add-sportKind";
    	var data={
    		name	 : 	$('#sportKind_name').val(),	
    	};
     	if($.trim(data.name) =='' || $.trim(data.name) == null){
     		layer.msg('请输入运动种类名称');
     		return false;
     	}
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listsportKind();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    //弹出编辑运动种类
    $scope.EditDialog = function(sportKind){
    	$scope.edit.id = sportKind.id;
    	$scope.edit.name=sportKind.name;
    	
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>编辑种类</p>",
            area:['940px','300px'],
            content:$('#orderCenterPopup2')
    	})
    }
    
    $scope.editsportKind = function(){
    	var url ="/data/sportKind/update-sportKind";
    	var data=$scope.edit;
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listsportKind();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出删除框
    $scope.DeleteDialog = function (sportKind) {
	$scope.delete.id=sportKind.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
	$scope.deletesportKind = function () {
   
	       var url = '/data/sportKind/delete-sportKind';
	       var data = $scope.delete;
	       
	        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	        	
	           if (result.success) {
	               layer.msg('操作成功!');
	               $scope.listsportKind();
	               layer.close($scope.lay);
	               return;
	           }
	           
	       })       		  
	}	   
}

    
}]);

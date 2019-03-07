/**预约记录*/
registerController.controller('departmentManageController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 30,
        totalSize: 0
    }
    $scope.Department = null;
    $scope.conditions=null;
    $scope.edit={
    		id 	 		: null,
    		name 		: null,
    		parentId 	: null,
    }
    $scope.delete={
    		id: null,
    }

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listDepartment();
        $scope.initPage();
        $scope.showMenu($('.departmentName'),$('.menuContent'))
    }

     /*获取科室列表*/
    $scope.listDepartment = function () {
        var url = '/data/department/list-department/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.Department = result.obj.data;
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
                            $scope.listDepartment();
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
                        $scope.listDepartment();
                    }
                }
            });
        }
    }
    
    
    //弹出添加科室
    $scope.addDialog = function(){
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加科室</p>",
            area:['940px','300px'],
            content:$('#orderCenterPopup')
    	})	
    }
    $scope.addDepartment = function(){
    	var url ="/data/department/add";
    	var data={
    		parentId :	$('#department_parentId').val(),
    		name	 : 	$('#department_name').val(),
    	};
     	
    	if($.trim(data.name) == '' || $.trim(data.name) == null){
    		layer.msg('请输入科室名称');
    		return false;
    	}
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listDepartment();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    //弹出修改科室
    $scope.EditDialog = function(Department){
    	$scope.edit.id = Department.id;
    	$scope.edit.name=Department.name;
    	$scope.edit.parentId=Department.parentId;
    	
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加科室</p>",
            area:['940px','300px'],
            content:$('#orderCenterPopup2')
    	})
    }
    
    $scope.editDepartment = function(){
    	var url ="/data/department/update";
    	var data=$scope.edit;
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listDepartment();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出删除框
    $scope.DeleteDialog = function (Department) {
	$scope.delete.id=Department.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
	$scope.deleteDepartment = function () {
   
	       var url = '/data/department/delete';
	       var data = $scope.delete;
	       
	        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	        	
	           if (result.success) {
	               layer.msg('操作成功!');
	               $scope.listDepartment();
	               layer.close($scope.lay);
	               return;
	           }
	           
	       })       		  
	}	   
}
    $scope.iconToggle = function($event) {
		var $this = $($event.target);
		$this.prev().children('.menuContent').slideToggle('300');
	}  
}]);

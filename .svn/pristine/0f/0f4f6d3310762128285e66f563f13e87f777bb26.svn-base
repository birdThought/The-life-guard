/** 版本管理 */
registerController.controller('appVersionController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.version = null;
    $scope.conditions=null;
    $scope.edit={
    		id 	 			: null,
    		appname 		: null,
    		version			: null,
    		comment			: null,
    		path			: null,
    		publicVersion	: null,
    }
    $scope.delete={
    		id: null,
    }

    /** 申明函数 */

    /* 控制器初始化 */
    $scope.init = function (){
        $scope.listversion();
        $scope.initPage();

    }

     /* 获取版本列表 */
    $scope.listversion = function () {
        var url = '/app-version/list-version/' + $scope.page.pageIndex;
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
                $scope.version = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }
    
     /* 初始化分页 */
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
                            $scope.listversion();
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
                        $scope.listversion();
                    }
                }
            });
        }
    }
    
    
    // 弹出添加版本种类
    $scope.addDialog = function(){
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加版本</p>",
            area:['940px','500px'],
            content:$('#orderCenterPopup')
    	})	
    }
    $scope.addversion = function(){
    	var url ="/app-version/add-version";
    	var data={
    		appname			: $('#version_appname').val(),	
    		comment			: $('#version_comment').val(),
    		path			: $('#version_path').val(),
    		publicVersion 	: $('#version_publicVersion').val(),
    		version			: $('#version_version').val(),
    	};
    	if($.trim(data.appname) == '' || $.trim(data.appname) == null){
    		layer.msg('请输入应用名称');
    		return false;
    	}
    	if($.trim(data.version) == '' || $.trim(data.version) == null){
    		layer.msg('请输入发布版本号');
    		return false;
    	}
    	if($.trim(data.publicVersion) =='' || $.trim(data.publicVersion) == null){
    		layer.msg('请输入公布版本号');
    		return false;
    	}
    	if($.trim(data.comment) == '' || $.trim(data.comment)== null){
    		layer.msg('请填入版本说明');
    		return false;
    	}

     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listversion();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出编辑版本种类
    $scope.EditDialog = function(version){
    	$scope.edit.id = version.id;
    	$scope.edit.appname=version.appname;
    	$scope.edit.comment=version.comment;
    	$scope.edit.path=version.path;
    	$scope.edit.publicVersion=version.publicVersion;
    	$scope.edit.version=version.version;
    	
    	var index =layer.open({
    		type:1,
    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>发布新版本</p>",
            area:['940px','500px'],
            content:$('#orderCenterPopup2')
    	})
    }
    
    $scope.editversion = function(){
    	var url ="/app-version/edit-version";
    	var data=$scope.edit;
     	
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listversion();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
    }
    
    // 弹出删除框
    $scope.DeleteDialog = function (version) {
	$scope.delete.id=version.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
	$scope.deleteversion = function () {
   
	       var url = '/app-version/delete-version';
	       var data = $scope.delete;
	       
	        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
	        	
	           if (result.success) {
	               layer.msg('操作成功!');
	               $scope.listversion();
	               layer.close($scope.lay);
	               return;
	           }
	           
	       })       		  
	}	   
}
    
}]);

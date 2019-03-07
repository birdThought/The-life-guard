registerController.controller('characterController',['$scope', function ($scope){
	
	$scope.page = {
		    pageIndex: 1,
		    pageSize: 10,
		    totalSize: 0
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
	                        $scope.roleData();
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
	                    $scope.roleData(); 
	                }
	            }
	        });
	    }
	};

	
    $scope.results =null;
    $scope.results2 = null;
    
    
    $scope.init = function () {
        setTimeout(function () {
            $scope.roleData();
        }, 300);
    }

    $scope.updates ={
        ids : -1,
        name :null
    }
    
    //--------------------------------------
    /** 申明参数 */
	
    
	
    /**
     * 获取角色列表
     */
    $scope.roleData = function(){
        var url = "/role/data?page="+$scope.page.pageIndex;
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
        	$scope.$apply(function () {
	            if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
	                $scope.page.totalSize = result.obj.totalSize;
	                $scope.page.pageIndex = result.obj.nowPage;
	                $scope.initPage();
	            }
	            $scope.results = result.obj.data;                                
	            $scope.page.totalSize = result.obj.totalSize;
	            $scope.page.pageIndex = result.obj.nowPage;
	        })
        })
    };

    $scope.openEdit = function(id,name,remark){
        $scope.updates.ids =id;
       /* roleForm.reset();*/
        layer.open({
            type: 1,
            title: ['编辑角色',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['600px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.dialog-content'),
            success: function () {
                $("input[name='name']").val(name);
                 $("input[name='id']").val(id);
                 $("input[name='remark']").val(remark);
             }
            
        });
    }
 
    $scope.deleteRole = function(id){
        layer.confirm("你确定要删除该角色吗？", function (index) {
            var url = "/role/del/"+id;
            http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
                $scope.$apply(function(){
                    if(result.success){
                    	 layer.msg("操作成功", {offset: 450});
                    	setTimeout(function () {                                              	 
                    	 $scope.roleData();                    	
                    	 layer.closeAll();
                    	})
                    
                    }
                })
            })
        })
    };
    $scope.saveOrEdit = function(){
        var name = $("input[name='name']").val();
        var remark = $("input[name='remark']").val();
        $scope.updates.name =name;
        $scope.updates.remark =remark;
        if ($scope.updates.name.length ==0 & $scope.updates.name == "") {
            layer.msg("请务必输入角色名称", {offset: 350});
            return;
        }
        if ($scope.updates.remark.length ==0 & $scope.updates.remark == "") {
            layer.msg("请务必输入角色名称", {offset: 350});
            return;
        }
        var data ={
            id:$scope.updates.ids,
            name:$scope.updates.name,
            remark:$scope.updates.remark
        }
        if($scope.updates.ids == -1){
           var url ="/role/select/save"
            var datas ={
                name:$scope.updates.name,
                remark:$scope.updates.remark
            }
            http.ajax.post(true,true,url,datas,http.ajax.CONTENT_TYPE_1,function(result){
                $scope.$apply(function(){
                    if (result.success) {
                    	 layer.msg("操作成功", {offset: 450});
                         setTimeout(function () {                         	                            
                        	 $scope.roleData();
                        	 layer.closeAll();
                         }, 500);
                    }
                })
            })
            return;
        }else {
            var url = "/role/select/update";
            http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                    console.log(result);
                    if (result.success) {
                    	$("input[name='id']").val("");
                    	$scope.updates.ids = -1;               	
                    	 layer.msg("操作成功", {offset: 450});
                         setTimeout(function () {                   	 
                        	 $scope.roleData();
                        	 layer.closeAll();
                         }, 500);
                    }
                })
            });
        }
    }

    $scope.addNew = function () {
       /* roleForm.reset();*/
        layer.open({
            type: 1,
            title: ['添加角色',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['600px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.dialog-content'),
            success: function () {
                $("input[name='name']").val("");
                 $("input[name='id']").val("");
                 $("input[name='remark']").val("");
             }
        });
    }
    
   // ------------------------------------------------------
    
    
    /** 申明参数 */
	$scope.page1 = {
	    pageIndex1: 1,
	    pageSize: 10,
	    totalSize: 0
	}
	
	 /*$scope.init2 = function () {
        setTimeout(function () {
            $scope.getPermissionList();
        }, 300);
    }*/
	
	
	
	//初始化分页
	$scope.initPage1 = function () {
	    if (typeof laypage == 'undefined') {
	        setTimeout(function () {
	            console.log('wait for laypage...');
	            laypage.render({
	                elem: 'page1'
	                ,curr:$scope.page1.pageIndex1
	                ,count: $scope.page1.totalSize
	                ,limit: $scope.page1.pageSize
	                ,theme: '#00bfff'
	                ,layout: ['count', 'prev', 'page', 'next', 'skip']
	                ,jump: function(obj, first){
	                    if(!first){
	                        $scope.page1.pageIndex1 = obj.curr;
	                        $scope.getPermissionList();
	                    }
	                }
	            });
	        }, 300);
	    } else {
	        laypage.render({
	            elem: 'page1'
	            ,curr:$scope.page1.pageIndex1
	            ,count: $scope.page1.totalSize
	            ,limit: $scope.page1.pageSize
	            ,theme: '#00bfff'
	            ,layout: ['count', 'prev', 'page', 'next', 'skip']
	            ,jump: function(obj, first){
	                if(!first){
	                    $scope.page1.pageIndex1 = obj.curr;
	                    $scope.getPermissionList();
	                }
	            }
	        });
	    }
	};
	
	
	
	
	$scope.roleId = null;
	//添加权限页面
	$scope.openMenu = function (id) {
	    /*permissionForm.reset();*/
		$scope.roleId = id;
	    
	    $scope.getPermissionList();
	    
	    
	    /*$scope.getSelectChecked();*/
	   /* $scope.getSelectChecked();*/
	    
	    layer.open({
	        type: 1,
	        title: ['添加权限',
	            'text-align:center;font-size:16px;background:#fff;'],
	        area: ['75%',"75%"],
	        offset: 150,
	        moveType: 1,
	        scrollbar: false,
	        zIndex: 99,
	        scrolling: 'no',
	        content: $('.openMenu-content')
	    });
	};
	
	
	$scope.getPermissionList = function(){
		
		
			var url = '/permiss/pagePermission?page='+$scope.page1.pageIndex1+'&roleId='+$scope.roleId;
			http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
		        $scope.$apply(function () {
		            if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
		                $scope.page1.totalSize = result.obj.totalSize;
		                $scope.page1.pageIndex1 = result.obj.nowPage;
		                $scope.initPage1();
		            }
		            $scope.results1 = result.obj.data;                                
		            $scope.page1.totalSize = result.obj.totalSize;
		            $scope.page1.pageIndex = result.obj.nowPage;
		           
		        })
		        
		        
		    })
		};
			
		
	
    	 //勾选更改操作
        $scope.updateOperation = function($event,item){
        	 if($event.target.checked){
        		 var url = '/role/updateRolePermission2?item='+item+'&judge='+true+'&roleId='+$scope.roleId;
                 http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_2,function(result){
                	 $scope.$apply(function(){
                         if (result.success) {
                        	
                        	 layer.msg("操作成功", {offset: 450});
                             setTimeout(function () {                         	 
                            	$scope.getPermissionList();                         	                      	                         	 
                             }, 500);	
                         }
                	 })
                 });           
        	 }else{
        		 var url = '/role/updateRolePermission2?item='+item+'&judge='+false+'&roleId='+$scope.roleId;
                 http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_2,function(result){
                	 $scope.$apply(function(){
                         if (result.success) {
                        	
                        	 layer.msg("操作成功", {offset: 450});
                             setTimeout(function () {                   	 
                            	 $scope.getPermissionList();                        	                         	 
                             }, 500);	
                         }
                     })
                 });		 
        	 }
        };
    	
    	
   
       
    
}]);





registerController.controller('adminPermissionController',['$scope','$compile', function ($scope,$compile,$element){

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    
    $scope.results =null;  
    $scope.results2 =null; 
    $scope.getOperation =null;
    $scope.abroad = null;
    $scope.operationList = null;
    $scope.checkAll=null, //全选 

    $scope.init = function () {
        setTimeout(function () {
            $scope.getPermissionList();
        }, 300);
    }

    $scope.updates ={
        ids : -1,
        name :null,
        item:null,
    }
    
    $scope.opration = {
            oName :null,
            operation:null,
    }

    /**
     * 获取权限列表
     */
    $scope.getPermissionList = function(){
        var url = '/permission/get/' + $scope.page.pageIndex;
        /*$scope.selectedPermission();*/
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
                            $scope.getPermissionList();
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
                        $scope.getPermissionList();
                    }
                }
            });
        }
    };
    
  
    
    //删除选中权限
    $scope.delPermission = function(){
    	var data = $scope.itemIds
    	if(data.length == 0){
    		 layer.msg("请选择删除项", {offset: 450});
    		 return;
    	};
    	
        layer.confirm("你确定要删除选中的权限吗？", function (index) {
        	var data = $scope.itemIds
            var url = "/permission/del?itemIds="+data;
            http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
            	debugger;
                $scope.$apply(function(){
                    if(result.success){
                    	 setTimeout(function () {
           	              
                         	layer.close(layer.index)
                         	layer.msg("操作成功！",
                         		{offset:['300px']});
                         	$scope.PermissData(); 
                         	$scope.getPermissionList();
                         	$scope.itemIds = [];
                         }, 500);	
                    }
                })
            })
        })
    };
    
    //添加权限页面
    $scope.addNew = function () {
        permissionForm.reset();
        $scope.operationList = null;
        
        layer.open({
            type: 1,
            title: ['添加权限',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['500px',"300px"],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.dialog-content')
        });
    };
    
   
    $scope.saveOperation = function(){
        var name = $("input[name='oName']").val();
        var operation = $("input[name='operation']").val();
        if (name ==null || name.length ==0 || name == "") {
            layer.msg("请务必输入操作项名称", {offset: 350});
            return;
        }
        if (operation ==null || operation ==0 || operation == "") {
            layer.msg("请务必输入操作项内容", {offset: 350});
            return;
        }
        
        var divContent = $('#permission-operation').html();
        divContent += "<input type='checkbox' id='"+name+"' name='checkboxinput' class='input-checkbox' v-model='checkboxModel' value='"+operation+"'>"+name+" &nbsp; &nbsp; &nbsp;"; 
        
        divContent = "<div>"+divContent+"</div>";
        
        var oper = $('#permission-operation');
        var $e = $compile(divContent)($scope);
        oper.html($e);
        $("input[name='oName']").val("");
        $("input[name='operation']").val("");
       
        
    };
    
   
    
    $scope.saveOrEdit = function(){
    	var id = $("input[name='id']").val();
        var name = $("input[name='name']").val();
        var item = $("input[name='item']").val();
        /*var agent = $("input[name='agent']:checked").val();*/
        $scope.updates.name =name;
        $scope.updates.item =item;
        /*$scope.updates.item =agent;*/
        if ($scope.updates.name ==null || $scope.updates.name.length ==0 || $scope.updates.name == "") {
            layer.msg("请务必输入权限名称", {offset: 350});
            return;
        }
        if ($scope.updates.item==null || $scope.updates.item.length ==0 || $scope.updates.item == "") {
            layer.msg("请务必输入功能项名称", {offset: 350});
            return;
        }
        /*if (agent ==null|| name == "") {
            layer.msg("请务必选择Agent", {offset: 350});
            return;
        }*/
       
        	
        	//新增权限
            var url = '/permission/savePermission?name='+name+'&item='+item;                                  
            http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_2,function(result){
            	 $scope.$apply(function(){
                     if (result.success) {
                    	
                    	 layer.msg("操作成功", {offset: 450});
                         setTimeout(function () {                   	 
                        	                    	
                        	 layer.closeAll();
                         }, 500);	
                           
                     }
                });
            })  
        
                  
    };
    
    //自定义操作
    $scope.customPermission = function(id){
    	$scope.opration.pemissionId = id;
    	layer.open({
            type: 1,
            title: ['添加操作',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['500px','300px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.custom-operation')
        });
    };
    
    //添加操作
    $scope.saveOperation = function(){
    	var name = $("input[name='oName']").val();
        var item = $("input[name='operation']").val();
        
        if (name ==null || name.length ==0 || name == "") {
            layer.msg("请务必输入操作名称", {offset: 350});
            return;
        }
        if (item==null || item.length ==0 || item == "") {
            layer.msg("请务必输入操作项名称", {offset: 350});
            return;
        }
        
        
        $scope.opration.name =name;
        $scope.opration.operation =item;
    	data = $scope.opration;
    	var url = '/permission/addOperation';
        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){
        	 $scope.$apply(function(){
                 if (result.success) {
                	 /*$scope.selectedPermission();*/
                	 layer.msg("操作成功", {offset: 450});
                     setTimeout(function () {                   	 
                    	 $scope.getPermissionList();                    	 
                    	 layer.closeAll();
                     }, 500);	
       
                 }
            });
        })
    
    };
    
    //删除操作页面跳转
    var delOperationId = null;
    $scope.delOperationPage = function(roldId,item,id,operationList){
    	$scope.list = operationList;
    	delOperationId = id;
    	delroleId = roldId;
    	delItem = item;
    	layer.open({
            type: 1,
            title: ['删除操作',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['500px','300px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.del-operation')
        });
	};
	
	var operationArr = [];
	$scope.pushArray = function(operation,$event){
		 if($event.target.checked){
			 operationArr.push(operation);
		 }else{
			 if(operationArr.indexOf(operation) != -1){
				 operationArr.remove(operation);
			 }  
		 }
	};
	
	$scope.delOperation = function(){
		if(operationArr != null){
			
			var url = '/permission/delOperationList?operationArr='+operationArr+'&delOperationId='+delOperationId+'&roleId='+delroleId+'&item='+delItem;
	        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_2,function(result){
	        	
	        	 $scope.$apply(function(){
                     if (result.success) {
                    	/* $scope.selectedPermission();*/
                    	 layer.msg("操作成功", {offset: 450});
                         setTimeout(function () {                   	 
                        	 $scope.getPermissionList();                        	 
                        	 layer.closeAll();
                         }, 500);	
                     }
                 })
	        })			
		}
	};
	
	
	//权限checkbox选择
	$scope.itemIds = [];
	$scope.itemSelected2 = function($event,id,name,item,hasAgent){
		var checked = $event.target;
		var st = id+"/"+name+"/"+item+"/"+hasAgent;
		if(checked.checked){
			$scope.itemIds.push(st);		
		}else{			
			$scope.itemIds.splice($.inArray(st, $scope.itemIds), 1);
		}
	};
	//转页面初始化
	$scope.itemSelected1 = function(id,name,item){
		 for ( var i = 0; i <$scope.itemIds.length; i++){
			 if($scope.itemIds[i].indexOf(item)!= -1 && $scope.itemIds[i].indexOf(id)!=-1 && $scope.itemIds[i].indexOf(name)!=-1){
					return true;
				}
		 }
		
	}
	
	/*//修改权限
	$scope.updatePermission = function(){
		if($scope.itemIds.length > 1){
			 layer.msg("权限修改只能选择一项", {offset: 350});
			 return;
		}
		if($scope.itemIds.length == 0){
			 layer.msg("请选择要修改的权限", {offset: 350});
			 return;
		}	
		var st = $scope.itemIds;
		var arr = st[0].split("/");
		$("input[name='id']").val(arr[0]);
		$("input[name='name']").val(arr[1]);
        $("input[name='item']").val(arr[2]);	
        if(arr[3]==1){		
        	
        	$("#radio1").attr("checked","checked");	
        	$("#radio0").removeAttr("checked");
        
        }else{	
        		$("#radio0").attr("checked","checked");	
        		$("#radio1").removeAttr("checked");
        }
        
        
		layer.open({
            type: 1,
            title: ['修改权限',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['500px','300px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.dialog-content2')
        });		
	}*/
	  
  /*//查询role-permission
    var array = []; 
    $scope.selectedPermission = function(){	
    	var url = '/role/queryRolePermission';
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_2,function(result){
            array.push(result.obj);
        })
    };
    
    
    //查询勾选已有项
    $scope.isSelected = function(operation,roleId,item){
        var arr =  array[array.length-1];
        for ( var i = 0; i <arr.length; i++){
        	if(arr[i].roleId == roleId){
        		
        		var arr2 = arr[i].permission.split(":");     	
                if(arr2[0] == item){
                	if(arr2[1] == '*'){
                		return true;
                	}
                	if(arr2[1].match(operation)){1
                		return true;
                	}
                }
        	}
        	
        }
        
    }*/
	
	$scope.isSelected = function(operation,roleId,item){
		var arr = item.split(":");
		if(arr.length>1 && arr[1] == '*'){
			return true;
		}
		 if(item.match(operation)){
			 return true;
		 }
	 }
    
    //勾选更改操作
    $scope.updateOperation = function($event,item,operation,roleId){
    	 if($event.target.checked){
    		 var url = '/role/updateRolePermission?item='+item+'&operation='+operation+'&judge='+true+'&roleId='+roleId;
             http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_2,function(result){
            	 $scope.$apply(function(){
                     if (result.success) {
                    	 /*$scope.selectedPermission();*/
                    	 layer.msg("操作成功", {offset: 450});
                         setTimeout(function () {                         	 
                        	$scope.getPermissionList();                         	                      	                         	 
                         }, 500);	
                     }
            	 })
             });           
    	 }else{
    		 var url = '/role/updateRolePermission?item='+item+'&operation='+operation+'&judge='+false+'&roleId='+roleId;
             http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_2,function(result){
            	 $scope.$apply(function(){
                     if (result.success) {
                    	/* $scope.selectedPermission();*/
                    	 layer.msg("操作成功", {offset: 450});
                         setTimeout(function () {                   	 
                        	 $scope.getPermissionList();                        	                         	 
                         }, 500);	
                     }
                 })
             });		 
    	 }
    };
    
    
    // ------------------------------------------------------------------------------------------------------------------------
    
    
    /** 申明参数 */

	$scope.page1 = {
		    pageIndex1: 1,
		    pageSize: 10,
		    totalSize: 0
		};
	
	
	
	/*初始化分页*/
	 $scope.initPage1 = function(){
		if (typeof laypage == 'undefined') {
		    setTimeout(function () {
		        console.log('wait for laypage...');
		        laypage.render({
		            elem: 'page1'
		            ,count: $scope.page1.totalSize
		            ,limit: $scope.page1.pageSize
		            ,theme: '#00bfff'
		            ,layout: ['count', 'prev', 'page', 'next', 'skip']
		            ,jump: function(obj, first){
		                if(!first){
		                    $scope.page1.pageIndex1 = obj.curr;
		                    $scope.PermissData();
		                }
		            }
		        });
		    }, 300);
		} else {
		    laypage.render({
		        elem: 'page1'
		        ,count: $scope.page1.totalSize
		        ,limit: $scope.page1.pageSize
		        ,theme: '#00bfff'
		        ,layout: ['count', 'prev', 'page', 'next', 'skip']
		        ,jump: function(obj, first){
		            if(!first){
		                $scope.page1.pageIndex1 = obj.curr;
		                $scope.PermissData();
		            }
		        }
		    });
		}
	};  
	

	/**
     * 获取角色列表
     */
    $scope.PermissData = function(){
      
    	var url = "/permission/delPermiss?page="+$scope.page1.pageIndex1;
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
        	$scope.$apply(function () {
	            if (($scope.page1.totalSize != 0 || result.obj.totalSize > 0) && $scope.page1.totalSize != result.obj.totalSize) {
	                $scope.page1.totalSize = result.obj.totalSize;
	                $scope.page1.pageIndex1 = result.obj.nowPage;
	                $scope.initPage1();
	            }
	            $scope.results1 = result.obj.data;                                
	            $scope.page1.totalSize = result.obj.totalSize;
	            $scope.page1.pageIndex1 = result.obj.nowPage;
	        })
        })
    };

    $scope.openEdit = function(){
    	 $scope.PermissData();
    	 
        layer.open({
            type: 1,
            title: ['权限编辑',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['70%,70%'],
            offset: 120,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.openPermiss-content'),
           
            
        });
        
    }
	
			

	//修改
	$scope.saveOrEdit2 = function(){
		
		var id =  $("input[name='id1']").val();
		var name = $("input[name='name1']").val();
        var item = $("input[name='item1']").val();
        /*var agent = $("input[name='agent1']:checked").val();*/
       
        $.ajax({
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            url: '/permission/updatePermission?id='+id+'&name='+name+'&item='+item+'&oldItem='+oldItem+'&oldName='+oldName,		            
            success: function (result) {
                var b = result.success;
                if (!b) {
                    layer.msg("操作失败，请重试");
                    return;
                }
                
                setTimeout(function () {
                	              
                	layer.close(layer.index)
                	layer.msg("操作成功！",
                		{offset:['300px']});
                	$scope.PermissData(); 
                	$scope.itemIds = [];
                }, 500);
            }, error: function (jqXHR) {
                switch (jqXHR.status) {
                    case 401:
                        layer.msg("连接超时，即将返回登录页面");
                        setTimeout(function () {
                            window.location.href = $.baseUrl + "/loginPage";
                        }, 2000);
                        break;
                }
            }
        	 
                	
        })
	};
	
	
	
	//修改打开页面
	$scope.updatePermission2 = function(){
		if($scope.itemIds.length > 1){
			 layer.msg("权限修改只能选择一项", {offset: 350});
			 return;
		}
		if($scope.itemIds.length == 0){
			 layer.msg("请选择要修改的权限", {offset: 350});
			 return;
		}	
		var st = $scope.itemIds;
		var arr = st[0].split("/");
		$("input[name='id1']").val(arr[0]);
		$("input[name='name1']").val(arr[1]);
        $("input[name='item1']").val(arr[2]);	
      /*if(arr[3]==1){		
      	
      	$("#radio4").attr("checked","checked");	
      	$("#radio3").removeAttr("checked");
      
      }else{	
      		$("#radio3").attr("checked","checked");	
      		$("#radio4").removeAttr("checked");
      }*/
      oldItem = arr[2];
	  oldName = arr[1];	
		layer.open({
           type: 1,
           title: ['修改权限',
               'text-align:center;font-size:16px;background:#fff;'],
           area: ['500px','300px'],
           offset: 150,
           moveType: 1,
           scrollbar: false,
           zIndex: 99,
           scrolling: 'no',
           content: $('.dialog-content2')
       });		
				
	};
	
}]);
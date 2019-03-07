(function() {
    angular.module("Controllers", []).controller('columnManagerController', ['$scope', '$timeout', function ($scope, $timeout) {

    	// 栏目列表
    	$scope.columnList = [];
    	// 栏目对象
    	$scope.column = null;
    	
    	$scope.initColumnObj = function() {
    		$scope.column = {id:null, parentId: null, name: null};
    	}
    	
    	$scope.init = function() {
    		// 初始化栏目对象
    		$scope.initColumnObj();
    		// 获取数据
    		var url = '/news/column/data/list';
    		var data = null;
    		http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result) {
    			$scope.$apply(function(){
        			$scope.columnList = result.obj;
        		})
    		});
        }
    	// 绑定事件
    	$scope.iconToggle = function($event) {
    		var $this = $($event.target);
    		if($this.find('img').attr('src')=='/static/images/open.png'){
    			$this.find('img').attr('src','/static/images/close.png')
	        }else {
	        	$this.find('img').attr('src','/static/images/open.png')
	        }
    		$this.siblings('.dataContent').slideToggle('300');
    	}
    	
    	/* 删除提示框 */
        $scope.delPopout = function(id) {
        	layer.confirm("确定要删除这项栏目吗？", {icon: 3, title:'提示'}, function(index) {
        		var url = '/news/column/del';
            	var data = {
        			id: id
            	}
            	http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result) {
            		$scope.$apply(function () {
                        if (!result.success) {
                            layer.msg(result.msg);
                         } else {
                        	 layer.msg('删除成功');
                        	 $scope.init();
                         }
                    })
            	});
            	
            	layer.close(index);
        	});
        }
        
        $scope.addNew = function (parentId) {
            $("#columnForm")[0].reset();
            if (parentId != undefined) {
                $("select[name='parentId']").val(parentId);
                $("select[name='parentId']").attr("disabled", "disabled");
            } else {
                $("select[name='parentId']").removeAttr("disabled");
            }
            $("select[name='parentId'] option").each(function () {
                $(this).show()
            })
            $scope.openDialog("添加栏目");
            $scope.column.parentId = parentId;
        }
        
        $scope.modify = function (id, name, parentId) {
            $("#columnForm")[0].reset();
            $("input[name='id']").val(id);
            $("input[name='name']").val(name);
            $("select[name='parentId']").val(parentId == undefined ? 0 : parentId);
            $("select[name='parentId']").removeAttr("disabled");
            $("select[name='parentId'] option[value=" + id + "]").siblings().show();
            $("select[name='parentId'] option[value=" + id + "]").hide()
            $scope.openDialog("修改栏目");
        }
        
        var index2 = null;
        
        $scope.openDialog = function (name) {
        	index2 = layer.open({
                type: 1,
                title: [name,
                    'text-align:center;font-size:16px;background:#fff;'],
                area: ['600px'],
                offset: 150,
                moveType: 1,
                scrollbar: false,
                zIndex: 99,
                scrolling: 'no',
                content: $('.dialog-content'),
                end: function() {
                	// 初始化栏目对象
            		$scope.initColumnObj();
                }
            });
        }
        
        $scope.saveOrEdit = function() {
        	var id = $scope.column.id;
        	var data = {
    			id: id,
    			name: $scope.column.name,
    			parentId: $scope.column.parentId
        	}
        	if($.trim(data.name) == '') {
        		layer.msg('请输入栏目名称');
        		return false;
        	}
        	var url = '/news/column/add';
        	if (id != null && id != undefined) {
        		url = '/news/column/update';
        	}
        	// 如果parentId
        	if (data.parentId == null) {
        		data.parentId = 0;
        	}
        	http.ajax.post(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_2, function (result) {
                $scope.$apply(function () {
                	if(!result.success) {
                		layer.msg(result.msg)
                        return;
                	} else {
                		 layer.msg('操作成功');
                    	 layer.close(index2);
                		 $scope.init();
                	}
                })
            })
        }
        
    }]);
}());
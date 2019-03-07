(function() {
    angular.module("Controllers", [])
    .controller('consultManagerController', ['$scope', '$timeout', '$location', function ($scope, $timeout, $location) {
    	$scope.childColumns = [];
    	$scope.hideChildColumn = true;	// 默认隐藏二级栏目框
    	
    	/* 重置参数 */
    	$scope.resetInformationObj = function() {
    		$scope.informationObj = {parentColumnId:null,columnId:null,id:null};
    	}
    	
    	$scope.idObj = {
    			id:null,
    			columnId:null
    	}
    	
    	$scope.page = {
    	        pageIndex: 1,
    	        pageSize: 10,
    	        totalSize: 0
    	    }
    	
        $scope.init = function() {
        	$scope.columnList = [];
        	$scope.resetInformationObj();
        	var url = '/news/column/data/list';
        	http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
        		$scope.$apply(function(){
        			$scope.columnList = result.obj;
        		});

    			var childId = $location.search().childId;
    			if (childId != null) {
    				var $column = $("ul[name='column']").find("li[data-id=" + childId + "]");
    				$column.click();
    			}
        	});
    	    Editor.init();
        }
        
    	/* 获取资讯信息列表 */
        $scope.getInformationList = function(parentId, resetPageIndex) {
        	if (resetPageIndex) {
        		$scope.page.pageIndex = 1;
        	}
        	var url = '/news/data/list/' + $scope.page.pageIndex;
        	$scope.informationList = null;
        	var data = {
        			parentId:parentId
        	}
        	http.ajax.get(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_1, function(result) {
        		$scope.$apply(function() {
        			if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                        $scope.page.totalSize = result.obj.totalSize;
                        $scope.page.pageIndex = result.obj.nowPage;
                        $scope.initPage(parentId);
                    }
        			$scope.informationList = result.obj.data;
        			$scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
        		})
        	})
        }
        
        /* 获取二级栏目资讯信息列表 */
        $scope.getSecondInformationList = function(id) {
        	var url = '/news/data/list/second/' + $scope.page.pageIndex;
        	$scope.secondInformationList = null;
        	var data = {
        			id:id
        	}
        	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result){
        		$scope.$apply(function() {
        			if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                        $scope.page.totalSize = result.obj.totalSize;
                        $scope.page.pageIndex = result.obj.nowPage;
                        $scope.initSecondPage(id);
                    }
        			$scope.informationList = result.obj.data;
        			$scope.page.totalSize = result.obj.totalSize;
        			$scope.page.pageIndex = result.obj.nowPage;
        		})
        	})
        }
        $scope.closeAll=function(){
        	layer.closeAll()
        }
        /* 初始化分页 */
        $scope.initPage = function (parentId) {
            laypage.render({
                elem: 'page'
                ,count: $scope.page.totalSize
                ,limit: $scope.page.pageSize
                ,theme: '#00bfff'
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,jump: function(obj, first){
                    if(!first){
                        $scope.page.pageIndex = obj.curr;
                        $scope.getInformationList(parentId);
                    }
                }
            });
        }
        
        /* 初始化二级栏目分页 */
        $scope.initSecondPage = function (id) {
        	laypage.render({
        		elem: 'page'
        			,count: $scope.page.totalSize
        			,limit: $scope.page.pageSize
        			,theme: '#00bfff'
        				,layout: ['count', 'prev', 'page', 'next', 'skip']
        	,jump: function(obj, first){
        		if(!first){
        			$scope.page.pageIndex = obj.curr;
        		    $scope.getSecondInformationList(id);
        		}
        	}
        	});
        }
        
        var index1 = null;
        
        /* 删除提示框 */
        $scope.delPopout = function(id,columnId){
        	$scope.idObj.id = id;
        	$scope.idObj.columnId = columnId;
        	index1 = layer.open({
        		type:1,
        		area:['260px','150px'],
        		content:$('#deletenews')
        	})
        }
        
        /* 删除资讯 */
        $scope.delInformation = function() {
        	var url = '/news/del';
        	data = {
        			id:$scope.idObj.id,
        			columnId:$scope.idObj.columnId
        	}
        	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result) {
        		$scope.$apply(function () {
                    if (!result.success) {
                        layer.msg(result.msg);
                     } else {
                    	 layer.msg('删除成功');
                    	 layer.close(index1);
                    	 $scope.getSecondInformationList(data.columnId);
                     }
                })
        	})
        }
        
        var index2 = null;
        
        $scope.openDialog = function (name, content) {
            var hb = null;
            index2 = layer.open({
                type: 1,
                title: [name,'text-align:center;font-size:16px;background:#fff;'],
                area: ['850px'],
                offset: 150,
                moveType: 1,
                scrollbar: false,
                zIndex: 99,
                scrolling: 'no',
                content: $('.dialog-content'),
                success: function () {
                	/*function continueHB() {
                        hb = $timeout(function () {
                            consultService.heartBeat(function () {
                                continueHB();
                            })
                        }, 60000)
                    }
                    continueHB();*/
                }, cancel: function () {
                    $timeout.cancel(hb)
                }
            });
        }
        
        /* 添加资讯 */
        $scope.addNew = function () {
        	$scope.resetInformationObj();	// 初始化
            $scope.openDialog("添加资讯");
        }
        
        /* 获取子栏目 */
        $scope.getChildColumns = function(id) {
        	$scope.childColumns = [];
        	var url = '/news/column/getChild';
        	var data = {
        			id:id
        	}
        	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result) {
        		$scope.$apply(function(){
        			$scope.childColumns = result.obj;
        		})
        	})
        }
        
        /* 保存 */
        $scope.saveOrEdit = function() {
        	var id = $scope.informationObj.id;
        	var columnId = $scope.informationObj.columnId;
        	
        	if(columnId == null) {
        		columnId = $scope.informationObj.parentColumnId
        	}
        	
        	var data = {
        			id: id,
        			title: $('#title').val(),
        			columnId: columnId,
        	        source: $('#source').val(),
        	        content: Editor.getContent()
        	}
        	
        	if($.trim(data.title) == '') {
        		layer.msg('请输入资讯标题');
        		return false;
        	}
        	
        	if(data.columnId == 0) {
        		layer.msg('请选择栏目');
        		return false;
        	}
        	
        	if($.trim(data.content) == '') {
        		layer.msg('请填写资讯内容');
        		return false;
        	}
        	
        	var url = '/news/add';
        	if (id != null && id != undefined) {
        		url = '/news/update';
        	}
        	
        	http.ajax.post(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_2, function (result) {
                $scope.$apply(function () {
                	if(!result.success) {
                		layer.msg(result.msg)
                        return;
                	} else {
                		 layer.msg('操作成功');
                    	 layer.close(index2);
                		 $scope.getSecondInformationList(data.columnId);
                	}
                })
            })
        }
        
        /* 修改 */
        $scope.editInformation = function(id) {
        	var url = '/news/get';
    		var data = {
    				id:id
    		}
    		http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
    			$scope.$apply(function () {
    				$scope.informationObj = result.obj;
    				var content = result.obj.content;
    				if (content != undefined) {
    					Editor.setContent(content);
    				}
    	    		$scope.openDialog('编辑资讯');
    			});
    		})
        }
        
        /* 资讯内容 */
        $scope.goContent = function(id) {
        	$scope.go("/news/detail?id=" + id);
        }
        
        $scope.$watch('informationObj.parentColumnId', function(value) {
        	if (value != null && value != 0) {
        		$scope.getChildColumns(value);
        	} else {
        		$scope.childColumns = [];
        	}
        });
        
        $scope.$watch('childColumns', function(array) {
        	if (array.length > 0) {
        		$scope.hideChildColumn = false;
        	} else {
        		$scope.hideChildColumn = true;
        	}
        });
        
     // 绑定事件
    	$scope.iconToggle = function($event) {
    		var $this = $($event.target);
    		var common123 ={};
    		common123.clickChange=function(elem1,elem2,elem3){
    		    elem1.on('click',function(){
    		        if($(this).find(elem2).css('display')=='none'){
    		            $(this).find(elem2) .show('fast');
    		            $(this).siblings().find(elem2).hide('fast')
    		            $(this).find(elem3).attr('src','static/images/close.png')
    		        }else {
    		        	
    		            $(this).find(elem2) .hide('fast');
    		            $(this).find(elem3).attr('src','static/images/open.png')
    		        }
    		        return false;
    		    })
    		};
    		//组织时间冒泡
    		common123.preventBubble=function(elem1){
    		   elem1.bind('click',function($this){
    			   $this.stopPropagation()
    		   })
    		};
    		setTimeout(function () {
    		    common123.preventBubble($('.small-child-menu'));
    		    common123.clickChange($('.small-parent-menu > li'),$('.small-child-menu'),$('.small-parent-menu > li img'));
    		},100);
    	}
        
    }]);
}());

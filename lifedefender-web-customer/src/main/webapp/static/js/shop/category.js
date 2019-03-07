angular.module("Controllers",[]).controller('goodsClassifyController',['$scope','$compile', function ($scope,$compile) {
	
	$scope.root = {id:0,children:[],pid:null,labelId:null}
	
	$scope.labels = [];
	
	$scope.init = function() {
		$scope.findByPid($scope.root);
		$scope.findAllLabels();
	}
	
	$scope.findByPid = function(parent) {
		if(parent.children != null && parent.children.length > 0){
			return;
		}
		var url = '/commodity/classify/list/' +parent.id;
		var data = null;
		http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result) {
			$scope.$apply(function(){
				parent.children = result.obj;
				for(var i = 0; i < parent.children.length;i++) {
					parent.children[i].expland = false;
					parent.children[i].parent = parent;
					parent.children[i].children = [];
				}
    		})
		});
	}
	
	$scope.findAllLabels = function(){
		var url = '/commodity/label/list';
		var data = null;
		http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result) {
			$scope.$apply(function(){
				$scope.labels = result.obj;
    		})
		});
	}
	
	$scope.newCategory = {};
	
	$scope.saveOrEdit = function() {
		var parent = $scope.newCategory.parent;
//		console.log($scope.newCategory);
		if($scope.newCategory.labelId == null){
			layer.msg('标签不能为空!', {
			     time: 1200, //1s后自动关闭
				});
			return;
		}
		if($scope.newCategory.cName == null || $scope.newCategory.cName == ''){
			layer.msg('类目名称不能为空!', {
			     time: 1200, //1s后自动关闭
			});
			return;
		}
//		console.log(parent);
		if($scope.newCategory.id == null || $scope.newCategory.id == undefined) { //添加
			var url = '/commodity/classify/add';
			$scope.newCategory.parent = null;
			http.ajax.post(true,false,url,JSON.stringify($scope.newCategory),http.ajax.CONTENT_TYPE_2,function(result){
				$scope.$apply(function(){
					if(result.success){
						result.obj.children = [];
						parent.children.push(result.obj);
						result.obj.parent = parent;
					}
					$scope.newCategory = {};
				})
			});
		}
		else { // 修改
			var url = '/commodity/classify/edit';
			$scope.newCategory.parent = null;
			http.ajax.post(true,false,url,JSON.stringify($scope.newCategory),http.ajax.CONTENT_TYPE_2,function(result){
				$scope.$apply(function(){
					if(result.success){
						parent.children[$scope.newCategory.k] = $scope.newCategory
						$scope.newCategory.parent = parent;
					}
					$scope.newCategory = {}
				})
			});
		}
		layer.close($scope.layer_index);
	}
	
	$scope.openDialog = function (name) {
		$scope.layer_index = layer.open({
            type: 1,
            title: [name,
                'text-align:center;font-size:16px;background:#6BB1DF;'],
            area: ['600px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.dialog-content'),
            end: function() {
//            	$scope.newCategory = {};
            }
        });
    }
    
    $scope.addnew = function(parent) {
		$scope.newCategory.pid = parent.id;
		$scope.newCategory.cName = null;
		$scope.newCategory.sort = 0;
		if(parent.id==0){
			$scope.newCategory.labelId = null;
			$scope.newCategory.labelName = null;
			$scope.newCategory.idPath = '';
			$scope.newCategory.parentnode = 0;
		} else {
			$scope.newCategory.labelId = parent.labelId;
			$scope.newCategory.labelName = parent.labelName;
			$scope.newCategory.idPath = parent.idPath;
			$scope.newCategory.parentnode = 1;
		}
		$scope.newCategory.parent = parent;
//		console.log($scope.newCategory.parent);
		$scope.openDialog("添加新类目");
	}
	
	$scope.modify = function(category,k){
		$scope.newCategory = {
				id:category.id
				,cName:category.cName
//				,pid:category.pid
				,parentnode:category.parentnode
				,labelId:category.labelId
				,labelName:category.labelName
				,sort:category.sort
				,k:k
			}
		$scope.newCategory.parent = category.parent;
		$scope.openDialog("修改类目");
	}
	
	$scope.del = function(category, k) {
//		console.log(category.parent);
		layer.confirm("确定要删除这项吗？", {icon: 3, title:'提示'}, function(index) {
			var url = "/commodity/classify/delete";
			http.ajax.post(true,false,url,{idPath:category.idPath},http.ajax.CONTENT_TYPE_1,function(result){
				$scope.$apply(function(){
					if(result.success) {
						category.parent.children.splice(k, 1);
//						console.log(category.parent.children);
					}
				})
			})
			layer.close(index);
		});
	}
	
}])
.directive('treeView', function(){
	return {
	    restrict: 'E',
	    templateUrl: 'treeView.html',
	    scope: {
	      treeData: '='
	      ,addnew: '&'
	      ,modify: '&'
	      ,del: '&'
	    },
	    controller: function($scope){
	        $scope.isLeaf = function(item){
	        	return item.children == null || item.children.length == 0;
	        }
	        
	        $scope.findByPid = function(parent) {
	    		if(parent.children != null && parent.children.length > 0){
	    			return;
	    		}
	    		var url = '/commodity/classify/list/' +parent.id;
	    		var data = null;
	    		http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function(result) {
	    			$scope.$apply(function(){
	    				parent.children = result.obj;
	    				for(var i = 0; i < parent.children.length;i++) {
	    					parent.children[i].expland = false;
	    					parent.children[i].parent = parent;
	    					parent.children[i].children = [];
	    				}
	        		})
	    		});
	    	}
	      
	        $scope.expland = function(parent,$event) {
//	        	console.log(parent.parent.expland);
	        	if(parent.expland == false){
	        		for(var i = 0; i < parent.parent.children.length;i++){
	        			parent.parent.children[i].expland = false;
	        		}
	        		parent.expland = true;
	        	} else {
	        		parent.expland = false;
	        	}
	    		$scope.findByPid(parent);
	    	}
	        
	    }
	    
	}
})
;
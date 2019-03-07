/* 
 * 已废弃 
 */
angular.module("Controllers",[]).controller('editGoodsController',['$scope', function ($scope) {
	
	$scope.goods = {
		categoryId:null
		,skuType:1
	}
	
	$scope.layer_index = -1;
	
	$scope.properties = [];
	
	// ②规格
	$scope.specs = [
		{
			k:0 // 生成以后不能改变,唯一,整形,用于修改页面的回显
			,name:'颜色'
			,used:false
			,usedNum:0
			,rowspan:1
			/* 规格值*/,values:[{vk:'生成以后不能改变,唯一,整形,用于修改页面的回显',used:false,value:'白色'},{used:false,value:'黑色'},{used:false,value:'黄色'},{used:false,value:'红色'},{used:false,value:'绿色'},{used:false,value:'棕色'}]
		}
		,{
			k:1
			,name:'尺度'
			,used:false
			,usedNum:0
			,rowspan:1
			,values:[{used:false,value:11},{used:false,value:12},{used:false,value:13},{used:false,value:14},{used:false,value:15},{used:false,value:16},{used:false,value:17}]
		}
		,{
			k:2
			,name:'容量'
			,used:false
			,usedNum:0
			,rowspan:1
			,values:[{used:false,value:100},{used:false,value:200},{used:false,value:300},{used:false,value:400},{used:false,value:500},{used:false,value:600}]
		}
	];
	
	$scope.specTempValue = null;
	
	$scope.skus = [];
	
	$scope.n_ids = [];
	
	$scope.spec = {
		k:null
		,name:null
		,used:false
		,usedNum:0
		,rowspan:1
		,values:[]
	}
	
	$scope.init = function () {
		var url = "/commodity/goods/addInit";
		var condition = {categoryId:$scope.goods.categoryId}
		http.ajax.get(true, true, url, condition, http.ajax.CONTENT_TYPE_1, function (result) {
			 $scope.$apply(function () {
				 $scope.base = result.obj;
				 $scope.base.attrs2 = $scope.toTD($scope.base.attrs, 3);
				 $scope.base.labels2 = $scope.toTD($scope.base.labels, 8);
//				 console.log($scope.base.attrs2);
//				 console.log($scope.base.labels2);
			 });
		});
	}
	
	/**
	 * 一维数组转二维,相当与分页
	 * source 来源数组
	 * size 每个数组长度
	 */
	$scope.toTD = function(source,size) {
		var x = parseInt((source.length - 1) / size + 1);
		 var outer = new Array(x);
		 for(var i = 0;i < x;i++) {
			 var start = i * size;
			 var end = (i + 1) * size;
			 if(i == (x - 1)) {
				 end = source.length;
			 }
			 var arr = new Array(end - start);
			 for(var j = 0;start < end;j++,start++) {
				 arr[j] = source[start];
			 }
			 outer[i] = arr;
		 }
		 return outer;
	}
	
	//弹出框
    $scope.addDialog = function(id,title){
    	console.log("start:" + $scope.layer_index);
    	$scope.layer_index = layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 50px;font-size: 16px; letter-spacing: 1.5px'>" + title + "</p>",
            area:['400px','300px'],
            content:$('#' + id)
        })
        console.log("open:" + $scope.layer_index);
    };
	
	/**
	 * 添加属性
	 */
	$scope.addProperty = function() {
		var property = {
					name:$scope.attribute.name
					,value:$scope.attribute.value
				}
		$scope.properties.push(property);
		$scope.attribute = null;
		console.log($scope.properties);
		$scope.closeLayer();
	}
	
	$scope.closeLayer = function() {
		console.log("close:" + $scope.layer_index);
		layer.close($scope.layer_index);
	}
	
	/**
	 * 编辑规格
	 * k : 下标
	 */
	$scope.editSpecByKey = function(k) {
		
		if(k != null && k != undefined) { // 编辑存在的
			$scope.addDialog('dialog_edit_spec', '编辑规格');
			$scope.spec = angular.copy($scope.specs[k]);
			$scope.spec.k = k;
		} else { //添加
			$scope.spec = {
					k:null
					,name:null
					,used:false
					,usedNum:0
					,rowspan:1
					,values:[]
				}
			$scope.addDialog('dialog_edit_spec', '添加规格');
		}
		console.log("edit:" + $scope.spec);
	}
	
	/**
	 * 取消编辑
	 */
	$scope.cancelEditSpec = function() {
		$scope.closeLayer();
	}
	
	$scope.test = function() {
		var a = [1,2,3,4];
		var b = [];
		$scope.arrayAddAll(b, a);
		b[1] = 10;
		console.log(a);
		console.log(b);
	}
	
	/**
	 * 编辑规格时删除其中一个值
	 * 
	 */
	$scope.removeSpecValueFromList = function(k) {
		$scope.spec.values.splice(k, 1);
	}
	
	/**
	 * 删除规格
	 * k : 下标
	 */
	$scope.removeSpecByKey = function(k) {
		layer.confirm('确认删除吗?', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            //此处请求后台程序，下方是成功后的前台处理……
            $scope.specs.splice(k, 1); // 删除
        }); 
	}
	
	/**
	 * 添加规格值
	 */
	$scope.addSpecValueToList = function() {
		console.log("添加规格值" + $scope.specTempValue);
		if($scope.specTempValue == null){
			return;
		}
		var value = {used:false,value:$scope.specTempValue}
		$scope.spec.values.push(value);
		$scope.specTempValue = null;
		console.log($scope.spec.values);
	}
	
	/**
	 * 确定修改
	 */
	$scope.saveToSpecArray = function(k) {
		if($scope.spec.name == null || $scope.spec.values.length == 0){
			return;
		}
		var s = angular.copy($scope.spec);
		console.log("s:" + s);
		if(k != null && k != undefined) {
			$scope.specs[k] = s;
		} else {
			$scope.specs.push(s);
		}
		$scope.spec = {k:null
				,name:null
				,used:false
				,usedNum:0
				,rowspan:1
				,values:[]};
		console.log("list:" + $scope.specs);
		$scope.closeLayer();
	}
	
	$scope.arrayAddAll = function(result, src) {
		for(let i=0;i < src.length;i++) {
			result.push(src[i]);
	    }
		return result;
	}
	
	/**
	 * √
	 */
	$scope.checked = function(n_k, v_k) {
		//
		if($scope.skus.length < 1) {//第1个选中
			var sku = {n_ids:[n_k], v_ids:[v_k]}
			$scope.skus.push(sku);
		} else { 
			if($scope.specs[n_k].used) { // 存在
				let vk = -1; 
				for(let a = 0; a < $scope.specs[n_k].values.length; a++) {
					if(a != v_k & $scope.specs[n_k].values[a].used == true) {
						vk = a;// 随便找一个
						console.log("a=" + a);
						break;
					}
				}
				var newSkus = [];
				for(let b = 0; b < $scope.skus.length; b++) {
					let sku = $scope.skus[b];
					let n = -1;
					if((n = sku.n_ids.indexOf(n_k)) != -1 && sku.v_ids[n] == vk){
						var copy1 = [], copy2 = [];
						$scope.arrayAddAll(copy1, sku.n_ids);
						$scope.arrayAddAll(copy2, sku.v_ids);
						copy2[n] = v_k;
						var newSku = {n_ids:copy1, v_ids:copy2}
						newSkus.push(newSku);
					}
				}
				console.log("n_k:" + n_k + ";vk:" + vk);
				console.log("newsku:" + newSkus);
				$scope.arrayAddAll($scope.skus, newSkus);
			} else { // 不存在
				for(let b = 0; b < $scope.skus.length; b++) {
					let sku = $scope.skus[b];
					let arr1 = sku.n_ids, arr2 = sku.v_ids;
					let vn = -1;
					for(let i = arr1.length - 1; i >= 0; i--) {
						//TODO
						if(arr1[i] < n_k) {
							arr1.splice(i + 1, 0, n_k);
							vn = i + 1;
							break;
						}
					}
					arr2.splice(vn, 0, v_k);
				}
			}
		}
		$scope.specs[n_k].used = true;
		$scope.specs[n_k].usedNum++;
	}
	
	/**
	 * ×
	 */
	$scope.cross = function(n_k, v_k) {
		$scope.specs[n_k].usedNum--;
		$scope.specs[n_k].used = ($scope.specs[n_k].usedNum > 0);
		
		if($scope.specs[n_k].used) {
			var len = $scope.skus.length;
			while(--len >= 0) {
				let sku = $scope.skus[len];
				let n = sku.n_ids.indexOf(n_k);
				if(n != -1 && sku.v_ids[n] == v_k){
					$scope.skus.splice(len, 1);
				}
			}
		} else { // 缩短
			for(let b = $scope.skus.length - 1; b >= 0; b--) {
				let sku = $scope.skus[b];
				if(sku.n_ids.length == 1) {
					if(n_k == sku.n_ids[0] && v_k == sku.v_ids[0]){
						$scope.skus.splice(b, 1);
					}
				} else {
					let nn = sku.n_ids.indexOf(n_k);
					sku.n_ids.splice(nn, 1);
					sku.v_ids.splice(nn, 1);
				}
			}
		}
	}
	
	/**
	 * 选择|取消选中规格
	 */
	$scope.checkSpecValue = function(n_k, v_k) {
		if($scope.specs[n_k].values[v_k].used == true) { // checkbox选中
			$scope.checked(n_k, v_k);
			
		} else { // checkbox 取消选中
			$scope.cross(n_k, v_k);
		}
		// rowspan
		for(var i = n_k; i >= 0 && $scope.specs[i].used; i--) {
			var rowspan = 1;
			for(var j = i + 1; j < $scope.specs.length && $scope.specs[j].used; j++) {
				rowspan = rowspan * $scope.specs[j].usedNum;
			}
			$scope.specs[i].rowspan = rowspan;
		}
		
		
		console.log($scope.specs);
		console.log($scope.skus);
	}
	
	/**
	 * 最后提交
	 */
	$scope.saveGoods = function() {
		
	}
}]);
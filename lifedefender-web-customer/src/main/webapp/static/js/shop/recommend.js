angular.module("Controllers",[]).controller('recommendController',['$scope', function ($scope) {
	
	$scope.laydate = null;
	
	/**
	 * 初始化
	 */
	$scope.init = function(page){
		layui.use('laydate',function(){
			$scope.laydate = layui.laydate;
			if(typeof page == 'undefined' || page == 'cate'){
				$scope.cate.init();
			}
			else if(page == 'advert'){
				$scope.advert.init();
			}
		});
		
	}
	
	/**
	 * 推荐类目
	 */
	$scope.cate = {
		recommendSize:0,
		allList: [],
		render: function(){
			lay("input[name='datetime']").each(function() {
				var ele = this;
				var nowDate = new Date();
				var $render = $scope.laydate.render({
	                elem: this, //指定元素
	                type: 'datetime',
	                format: "yyyy-MM-dd HH:mm:ss",
	                min: 'nowDate',
	                trigger: 'click',
	                btns: ['now', 'confirm'],
	                done: function(value, date) {
	                	var id = $(ele).attr('data-id');
	                	var index = $(ele).attr('index');
//	                	console.log(value);
//	                	console.log(date);
	                	var url = '/commodity/recommend/editEndTime';
	                	http.ajax.post(true, false, url, {id:id,newTime:value}, http.ajax.CONTENT_TYPE_1, function (result) {
		                    if(result.success){
		                    	$scope.cate.allList[index].endTime = new Date(date.year, date.month, date.date, 
		                    			date.hours, date.minutes, date.seconds).getTime();
		                    	layer.msg('修改成功!', {
		                    		time: 1000, //1s后自动关闭
		                    	});
		                    }
	                	});
	                }
				});
			})
			
		},
		init: function(){
			var url = '/commodity/recommend/getRecommendCategory';
			http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
	        	$scope.$apply(function(){
	        		$scope.cate.allList = result.obj;
	        		for(var i = 0; i < $scope.cate.allList.length; i++){
	    				$scope.cate.allList[i].checked = false;
	    			}
	        		$scope.cate.recommendSize = result.attributes.recommendSize;
//		        		console.log("1:" + $scope.cate.allList);
	        		
	        	});
	        	$scope.cate.render();//  监听时间控件
			});
		},
		move: false,
		down: function() {
			var k = $scope.cate.checkIndex;
			var cur = $scope.cate.allList[k];
			console.log('下移' + cur.categoryName);
			$scope.cate.allList[k] = $scope.cate.allList[k+1];
			$scope.cate.allList[k + 1] = cur;
			$scope.cate.checkIndex = k + 1;
			$scope.cate.move = true;
//			$scope.cate.render();
		},
		up: function() {
			var k = $scope.cate.checkIndex;
			var cur = $scope.cate.allList[k];
			console.log('上移' + cur.categoryName);
			$scope.cate.allList[k] = $scope.cate.allList[k-1];
			$scope.cate.allList[k - 1] = cur;
			$scope.cate.checkIndex = k - 1;
			$scope.cate.move = true;
//			$scope.cate.render();
		},
		checkIndex: -1,
		change: function(k) {
			var tmp = !$scope.cate.allList[k].checked;
			console.log(tmp);
			if(tmp == false) {
				$scope.cate.allList[k].checked = tmp;
				$scope.cate.checkIndex = -1;
				return
			}
			for(var i = 0; i < $scope.cate.allList.length; i++){
				$scope.cate.allList[i].checked = !tmp;
			}
			$scope.cate.allList[k].checked = tmp;
			$scope.cate.checkIndex = k;
		},
		saveToServer: function(){
			for(var i = 0; i < $scope.cate.recommendSize; i++) {
				var now = new Date();
				if($scope.cate.allList[i].endTime <= now.getTime()){
//					console.log("save:" + $scope.cate.allList[i].endTime);
//					console.log("cur:" + now.getTime());
					layer.msg('第' + (i + 1) + '行的[到期时间]小于当前时间,请修改过期时间!',{
						time: 1800,
					});
					return;
				}
			}
//			console.log($scope.cate.allList);
			layer.confirm("确定要保存位置变化吗?", {icon: 3, title:'提示'}, function(index) {
				var pageGoods = [];
				var maxSort = $scope.cate.allList.length;
				for(var p = 0; p < $scope.cate.allList.length; p++){
					var item = {id:$scope.cate.allList[p].id,sort:maxSort--}
					pageGoods.push(item);
				}
				var data = {pageGoods: pageGoods};
				var url = '/commodity/recommend/resetRecommendCategory';
				http.ajax.post(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_2, function (result) {
					$scope.$apply(function(){
						if(result.success){
							for(var p = 0; p < $scope.cate.allList.length; p++){
								$scope.cate.allList[p].sort = pageGoods[p].sort;
							}
							$scope.cate.move = false
							layer.msg('调整位置成功!', {
	                    		time: 1000, //1s后自动关闭
	                    	});
						}
					});
					layer.close(index);
				});
			});
		}
	}
	
	/**
	 * 置顶广告
	 */
	$scope.advert = {
		init: function(){
//			console.log(this);
			//条件参数
			$scope.advert.conditions = {
		    		pageSize: this.page.pageSize,
		        	shopName: null,
		        	userName: null,
		        	goodsName: null,
		        	date:null,
		        	advertType: null,
		        	status: 1
		    }
			setTimeout(function () {
	            $scope.advert.initPage();
	        }, 300);
	        $scope.advert.searchAdvertList();
			// 监听时间控件
	        $scope.laydate.render({
                elem: "input[name='date']", //指定元素
                type: 'date',
                format: "yyyy-MM-dd",
                trigger: 'click',
                btns: ['clear', 'now'],
                done: function(value, date){
//                	console.log(value);
//                	console.log(date);
                	$scope.advert.conditions.date = value;
                	console.log($scope.advert.conditions);
                }
             });
		},
		//分页需要的参数
	    page: {
	        pageIndex: 1,//要查的当前页
	        pageSize: 15,//每页行数
	        totalSize: 0,//总数录数
	        totalPage: 0 //总页数
	    },
	    
	    initPage: function () {
	        if (typeof laypage == 'undefined') {
	            setTimeout(function () {
	                laypage.render({
	                    elem: 'page'
	                    , count: $scope.advert.page.totalSize
	                    , limit: $scope.advert.page.pageSize
	                    , theme: '#00bfff'
	                    , layout: ['count', 'prev', 'page', 'next', 'skip']
	                    , jump: function (obj, first) {
	                        if (!first) {
	                            $scope.advert.page.pageIndex = obj.curr;
	                            $scope.advert.searchAdvertList();
	                        }
	                    }
	                }); 
	            }, 300);
	        } else {
	            laypage.render({
	                elem: 'page'
	                , count: $scope.advert.page.totalSize
	                , limit: $scope.advert.page.pageSize
	                , theme: '#00bfff'
	                , layout: ['count', 'prev', 'page', 'next', 'skip']
	                , jump: function (obj, first) {
	                    if (!first) {
	                        $scope.advert.page.pageIndex = obj.curr;
	                        $scope.advert.searchAdvertList();
	                    }
	                }
	            });
	        }
	    },
	    searchAdvertList: function(){
	    	var url = '/commodity/recommend/getAdverts/' + $scope.advert.page.pageIndex;
	    	http.ajax.get(true, false, url, $scope.advert.conditions, http.ajax.CONTENT_TYPE_1, function (result) {
	        	$scope.$apply(function(){
	        		if (($scope.advert.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.advert.page.totalSize != result.obj.totalSize) {
	                    $scope.advert.page.totalSize = result.obj.totalSize;
	                    $scope.advert.page.pageIndex = result.obj.nowPage;
	                    $scope.advert.page.pageSize = result.obj.pageSize;
	                    $scope.advert.initPage();
	                }
	                $scope.advert.results = result.obj.data;
	                $scope.advert.page.totalSize = result.obj.totalSize;
	                $scope.advert.page.pageIndex = result.obj.nowPage;
	                $scope.advert.page.pageSize = result.obj.pageSize;
	        	});
	        });
	    },
	    remove: function(k,id){//逻辑删除
	    	layer.confirm("确定要删除吗?", {icon: 3, title:'提示'}, function(index) {
		    	var url = '/commodity/recommend/removeAdvert';
		    	http.ajax.post(true, false, url, {id:id}, http.ajax.CONTENT_TYPE_1, function (result) {
		    		$scope.$apply(function(){
		    			if(result.success){
		    				$scope.advert.results.splice(k, 1);
		    				layer.msg('删除成功!', {
	                    		time: 1000, //1s后自动关闭
	                    	});
		    			}
		    		});
		    		layer.close(index);
		    	});
	    	});
	    },
	    addAdvert: function(){
	    	layer.open({
	            type: 1,
	            title: ['添加广告', 'text-align:center;font-size:16px;background:#fff;'],
	            area: ['1000px','750px'],
	            resize: false,
	            offset: 80,
	            moveType: 1,
	            scrollbar: false,
	            zIndex: 9999,
	            content: $('#add-advert-choice'),
	            end:function(index){
	            	$scope.$apply(function(){
	            	});
	            }
	        });
	    },
	    add: {
	    	conditions: {
	    		advertType: '2',
	    		shopName: null,
	    		goodsName: null,
	    		status: 2,
	    		userName: null,
	    		size:20
	    	},
	    	shopList: null,
	    	goodsList: null,
	    	render: function(){
	    		var str = "input[name='choice-date']";
	    		if($scope.advert.add.conditions.advertType == 1){
	    			str = str + "[advert-type='shop']";
	    		} else {
	    			str = str + "[advert-type='goods']";
	    		}
	    		lay(str).each(function() {
					var ele = this;
			    	$scope.laydate.render({
		                elem: ele, //指定元素
		                type: 'date',
		                format: "yyyy-MM-dd",
		                trigger: 'click',
		                btns: ['clear', 'now'],
		                done: function(value, date){
		//                	console.log(value);
		//                	console.log(date);
//		                	var type = $(ele).attr('advert-type');
		                	$scope.$apply(function(){
		                	var index = $(ele).attr('index');
		                	if($scope.advert.add.conditions.advertType == 1){
		    	    			console.log($scope.advert.add.shopList[index].showTime = value);
		    	    		} else {
		    	    			console.log($scope.advert.add.goodsList[index].showTime = value);
		    	    		}
		                	});
		                }
		             });
		    	});
	    	},
	    	searchShop: function(){
	    		var url = '/commodity/shop/list/1';
//	    		$scope.advert.add.conditions.size = 20;
		    	http.ajax.get(true, false, url, $scope.advert.add.conditions, http.ajax.CONTENT_TYPE_1, function (result) {
		        	$scope.$apply(function(){
		        		$scope.advert.add.shopList = result.obj.data;
		        	});
		        	$scope.advert.add.render();
		        });
	    	},
	    	searchGoods: function(){
	    		var url = 'commodity/goods/list/1';
//	    		$scope.advert.add.conditions.size = 20;
		    	http.ajax.get(true, false, url, $scope.advert.add.conditions, http.ajax.CONTENT_TYPE_1, function (result) {
		        	$scope.$apply(function(){
		        		$scope.advert.add.goodsList = result.obj.data;
		        	});
		        	$scope.advert.add.render();
		        });
	    	},
	    	screen: function(){
	    		let type = $scope.advert.add.conditions.advertType;
	    		let shopName = $scope.advert.add.conditions.shopName;
	    		if(1 == type){ // 商铺广告
	    			let userName = $scope.advert.add.conditions.userName;
	    			if((shopName == null || shopName == '') && (userName == null || userName == '')){
	    				layer.msg('输入"商铺名"或"用户名"能帮助你更准确地查找');
	    				return;
	    			}
	    			$scope.advert.add.searchShop();
	    		} else { // 商品广告
	    			let goodsName = $scope.advert.add.conditions.goodsName;
	    			let userName = $scope.advert.add.conditions.userName;
	    			if((shopName == null || shopName == '') && (userName == null || userName == '')){
	    				layer.msg('输入"商铺名"或"用户名"能帮助你更准确地查找');
	    				return;
	    			}
//	    			if((goodsName == null || goodsName == '')){
//	    				layer.alert('请输入商品名');
//	    				return;
//	    			}
	    			$scope.advert.add.searchGoods();
	    		}
	    	},
	    	commit: function(k, type){
	    		layer.confirm("你想好了哦?", {icon: 3, title:'提示'}, function(index) {
	    			var advert = {};
	    			var list = null;
	    			if(type == 'shop'){
	    				advert.advertType = 1;
	    				list = $scope.advert.add.shopList;
	    				advert.shopId = list[k].id;
	    				advert.shopName = list[k].shopName;
	    				advert.userName = list[k].userName;
//	    				console.log($scope.advert.add.shopList[k])
	    			} else if(type == 'goods'){
	    				advert.advertType = 2;
	    				list = $scope.advert.add.goodsList;
	    				advert.goodsId = list[k].id;
	    				advert.goodsName = list[k].goodsName;
	    				advert.shopName = list[k].shopName;
	    				advert.image = list[k].pictureUrl;
//	    				console.log($scope.advert.add.goodsList[k]);
	    			}
    				advert.showTime = list[k].showTime;
	    			var url = '/commodity/recommend/addAdvert';
			    	http.ajax.post(true, false, url, JSON.stringify(advert), http.ajax.CONTENT_TYPE_2, function (result) {
			    		$scope.$apply(function(){
			    			if(result.success){
			    				advert.id = result.obj.id;
			    				$scope.advert.results.splice(0, 0, advert);
			    				list[k].showTime = null;
			    				console.log($scope.advert.results);
			    				layer.msg('添加成功!', {
		                    		time: 1000, //1s后自动关闭
		                    	});
			    			}
			    		});
			    		layer.close(index);
			    	});
	    		});
	    	}
	    	
	    }
	}
}]);
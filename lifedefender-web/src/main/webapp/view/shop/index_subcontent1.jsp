<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">		
		<script src="../../static/plugins/angularJS/angular.min.js"></script>
		<script src="../../static/plugins/layui/layui.js"></script>
		<script src="../../static/plugins/http.js"></script>
		<title></title>
		<style>
			body{
				margin: 0;
				padding: 0;
				background-color: #f5f5f5;
				
			}
		</style>
		<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent_plus.css" />
		<!--自适应js-->
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/js/jq.js" ></script>
		<script type="text/javascript" src="/static/js/vue.js" ></script>
		<script src="../../static/plugins/layui/v2.1.2/layui.all.js""></script>
		
		
		<script type="text/javascript">	
			var app = angular.module('myShop',[]); 
			app.controller('indexSubContentController',function($scope,$element){	
				$scope.filter = null;
				$scope.sort = null;
				
				$scope.goodsList = ${goods}
				
				
				$scope.init = function(){
					if($scope.goodsList ==null ||$scope.goodsList.length==0){
						layer.msg("该分类还没有商品", {offset: 350}); 
                		setTimeout(function () {                                              	 
                			window.history.go(-1);
                       	},1500)	
					}
				}
				
				$scope.sortPrice = function(sort){
					var url = '/shop/showGoods';  
					$scope.sort = sort;
					var data = {
							"filter":$scope.filter,
							"sort":$scope.sort
					};
					http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){		            	
		                $scope.$apply(function(){
		                    if(result.success){
		                    	$scope.goodsList = result.obj;
		                    }
		                })
			        });  
				}
				
				$scope.backOff = function(){
					window.history.go(-1);
				}
				
				
				$scope.details = function(goodsId,skuId){
					window.location = "/shop/detailsPage?goodsId="+goodsId+"&skuId="+skuId;
				}
							
				
			}); 
	</script>	
		
	</head>
	
	<body ng-app="myShop" ng-controller="indexSubContentController" ng-init="init()">
		<!--标题-->
		<div class="mainbg main_title" style="position: relative;">
			<div>
				<div  class="main_title_img">
					<img ng-click="backOff()" src="/static/images/shop/img/icon_return.png" style="width: 0.7rem;height: 1.2rem;"/>					
				</div>
				<div class="main_title_text">{{goodsList[0].category.cName }}</div>
				<div>&nbsp;</div>
			</div>
		</div>
		<!--end 标题-->
		
		<!--选项条-->
		<div class="mainbg main_opselect">
			
			<div>
				<div>默认</div>
				<div><img  src="/static/images/shop/img/up.png"/><img src="/static/images/shop/img/down.png"/></div>
			</div>
			
			<div>
				<div>价格</div>
				<div><img ng-click="sortPrice(1)" src="/static/images/shop/img/up.png"/><img ng-click="sortPrice(0)" src="/static/images/shop/img/down.png"/></div>
			</div>
			
			<div>
				<div>筛选</div>
				<div><img src="/static/images/shop/img/select.png" style="width: 1rem;height: 1rem;"/></div>
				
			</div>
			
		</div>
		<!--end 选项条-->
		
		<!--内容块-->
		<div class="main_subcontent"></div>
		<ul class="product_part_div">
			<li class="product_item" ng-repeat = "goods in goodsList">	
				<div ng-click="details(goods.id,goods.getSku.id)">
					<div class="product_img"><img src={{goods.mainPic}} /></div>
					<label class="product_title">{{goods.goodsName}}&nbsp;</label>
					<div class="product_price" style="color: red;">￥{{goods.favorablePrice}}</div>
				</div>
			</li>			
		</ul>
		<!--end 内容块-->
	</body>
</html>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>		
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<script src="../../static/plugins/angularJS/angular.min.js"></script>
		<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
		<script src="../../static/plugins/http.js"></script>
		
		<title></title>
		<link rel="stylesheet" href="/static/css/shop/css/title_bar.css" />
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/jquery/jquery-3.1.0.min.js"></script>
		<script type="text/javascript">
			var app = angular.module('myShop',[]);
			app.controller('addressController',function($scope,$element){
				$scope.address = ${address}; 
				
				$scope.backOff = function(){
					history.go(-1);
				};
				
				$scope.showaddress =  function(judge,id){
					window.location = "/shop/addressPage?judge="+judge+"&id="+id;
					                                   
							                
		        };
				
		
				$scope.del = function(id){
					var url = "/shop/delAddress?id="+id;
					http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){		            	
		                $scope.$apply(function(){
		                    if(result.success){
		                    	$scope.address = result.obj;
		                    	layer.msg("删除成功", {offset: 350}); 
		                    	
		                    }
		                })
			        });
					
				};
				
				$scope.updateAddressDefault = function(id){
					
					var url = "/shop/updateAddressDefault?id="+id;
					http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){		            	
		                $scope.$apply(function(){
		                    if(result.success){
		                    	$scope.address = result.obj;		                    			                    	
		                    }
		                })
			        });
				}
				
			});
		
		</script>
		
		
	</head>

	<body ng-app="myShop" ng-controller="addressController"  style="background-color: #f5f5f5;">
	
		<ul class="header_shop_bg"></ul>
		
		<!--头选择项-->
		<ul class="header_shop">
			<li class="header_icon" ng-click="backOff()"><img  src="/static/images/shop/img/icon_return.png" style="width: 0.7rem;height: 1.2rem;margin-left: 1rem;" /></li>
			<li class="header_title" style="height: 1.2rem;">
				<label>用户地址</label>
			</li>
			<li class="header_icon" style="text-align: right;"></li>
		</ul>
		<!--end 头选择项-->

		<style>
			.block_address {
				font-size: 1.2rem;
				padding: 1rem 1rem 1rem 1rem;
				background-color: white;
			}
			
			.block_address_user {
				display: flex;
				justify-content: space-between;
				align-items: center;
			}
			
			.block_address_describe {}
			
			.block_address_option {
				display: flex;
				justify-content: space-between;
				align-items: center;
			}
			
			.block_address_option img {
				width: 1.2rem;
				height: 1.2rem;
			}
			
			.block_address_btn {
				text-align: center;
				color: #f6fdf7;
				background-color: #63d07a;
				border: 1px solid #a0cda6;
				padding: 1rem 1rem 1rem 1rem;
				border-radius: 0.5rem;
			}
		</style>
		
		<!--地址管理块-->
		<div class="block_address" ng-repeat="add in address">
			<div class="block_address_user">
				<label>{{add.receiverName}}</label>
				<label>{{add.contactNumber}}</label>
			</div>
			<div class="block_address_describe">
				<h3>{{add.address}}{{add.street}}</h3>
			</div>
			
			<div class="block_address_option">
				<div ng-if="add.selected != 1"><img src="/static/images/shop/img/add_no.png" ng-click="updateAddressDefault(add.id)">设为默认&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div ng-if="add.selected == 1" style="float:left;" ><img src="/static/images/shop/img/add_choice.png" />&nbsp;&nbsp;默认地址</div>
				<div style="display: flex;justify-content: space-between;align-items: center;">
					<img src="/static/images/shop/img/address_icon_edit.png" ng-click="showaddress(true,add.id)" />&nbsp;编辑&nbsp;&nbsp;
					<img src="/static/images/shop/img/address_icon_del.png" ng-click="del(add.id)"/>&nbsp;删除
				</div>
			</div>
		</div>
		<!--end 地址管理块-->

		<div style="padding:2rem 2rem 2rem 2rem;">
			<div class="block_address_btn" ng-click="showaddress(false,0)">添加新地址</div>
		</div>

	</body>

</html>
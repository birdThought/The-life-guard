<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1,maximum-scale=1, minimum-scale=1">
<script type="text/javascript" src="js/vue.js"></script>
<title></title>
<style>
body {
	margin: 0;
	padding: 0;
}
</style>
<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
<link rel="stylesheet" href="/static/css/shop/css/subcontent.css" />
<link rel="stylesheet" href="/static/css/shop/css/subcontent_plus.css" />
<!--自适应js-->
<script type="text/javascript" src="/static/jquery/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/static/js/mode.js"></script>
<script src="../../static/plugins/angularJS/angular.min.js"></script>
<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
<script src="../../static/plugins/http.js"></script>


<style>


[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak.x-ng-cloak{ 
display: none!important; 
} 


/*头部*/
.header_shop {
	position: fixed;
	width: 100%;
	list-style-type: none;
	top: 0;
	margin: 0;
	padding: 0;
	height: 1.5rem;
	color: white;
	padding: 1rem 0 1rem 0;
	border-bottom: 1px solid #f4f4f4;
	background-color: #44c05e;
	z-index: 10;
}

.header_shop_bg {
	position: relative;
	list-style-type: none;
	top: 0;
	margin: 0;
	top: 0;
	height: 1.5rem;
	padding: 1rem 0 1rem 0;
}

.header_shop>li {
	float: left;
}

.header_shop .header_icon {
	width: 20%;
	text-align: left;
}

.header_shop .header_title {
	width: 60%;
	text-align: center;
}
</style>
<style>
*, html {
	font-size: 1rem;
}

body {
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}
/*购物车组件*/
.shopcar_list_div {
	width: 100%;
	margin-bottom: 1rem;
	background-color: white;
	padding-bottom: 0.5rem;
}
/*购物车头*/
.shopcar_list_div_header {
	display: flex;
	font-size: 1.3rem;
	padding: 1rem 1rem 1rem 1rem;
	border-bottom: 1px solid #d2d2d2;
	justify-content: space-between;
}

.shopcar_list_div_header img {
	width: 1.2rem;
	height: 1.2rem;
}
/*购物车子项*/
.shopcar_list_div_item {
	font-size: 1.2rem;
	/*border-bottom: 1px solid #d2d2d2;*/
}

.shopcar_list_div_item img {
	width: 1.2rem;
	height: 1.2rem;
}
/*子项内容块*/
.list_item_div_bar {
	display: flex;
	justify-content: space-between;
	padding: 2rem 2rem 2rem 2rem;
	background-color: #f3f8f4;
	margin-bottom: 0.3rem;
	opacity: 0.8;
}

.list_item_div_bar img {
	width: 5rem;
	height: 5rem;
}

.list_item_bar_left {
	/*width: 15%;*/
	display: flex;
	/*align-items: center;*/
}

.list_item_bar_center {
	padding-left: 0.5rem;
	padding-right: 0.5rem;
}

.list_item_bar_right {
	
}

.tip {
	font-size: 0.9rem;
	text-align: right;
	padding: 0.3rem 0.3rem 0.3rem 0.3rem;
}

.order_button_group {
	font-size: 0.9rem;
	text-align: right;
	padding: 0.5rem 0.5rem 0.5rem 0.5rem;
}

.order_button {
	border-radius: 0.8rem;
	border: 1px solid #d2d2d2;
	margin-left: 0.5rem;
	padding: 0.5rem 0.5rem 0.5rem 0.5rem;
}

.shopcar_list_img {
	display: flex;
	align-items: center;
}
</style>
<style>
body, ul {
	margin: 0px;
	paliing: 0px;
}

body {
	font-size: 12px;
	color: black;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: underline;
	color: black;
}

#position {
	list-style-type: none;
	margin: 0;
	padding: 0;
	height: 4.5rem;
	background: white;
	position: fixed;
	bottom: 0px;
	left: 0px;
	width: 100%;
	z-index: 99;
	overflow: hilien;
	border-top: 2px solid #f1f1f1;
}

#position li {
	width: 25%;
	text-align: center;
	float: left;
	padding-top: 0.2rem;
}

#position li font {
	display: block;
	font-size: 0.9rem;
}

#position li a {
	text-decoration: none;
}

#position li a img {
	width: 1.8rem;
	height: 1.8rem;
}

#blank {
	height: 64px;
}

*{
	font-size:1.2rem;
}
</style>

<script type="text/javascript">
	var app = angular.module('myShop', []);
	app.controller('orderController', function($scope, $element) {
		$scope.list = ${order};

		$scope.init = function() {
			if ($scope.list == null || $scope.list.length == 0) {
				layer.msg("订单列表为空，请前往购买商品", {
					offset : 350
				});
				setTimeout(function() {
					history.go(-1);
				}, 1000);

			}
		};

		$scope.clickDeleteOrder = function(orderNo) {

			var url = '/shop/delOrder?orderNo=' + orderNo;
			http.ajax.get(true, true, url, null, http.ajax.CONTENT_TYPE_1,
					function(result) {
						$scope.$apply(function() {
							if (result.success) {
								$scope.list = result.obj;
								if($scope.list != null || $scope.list.length != 0){
									layer.msg("删除成功", {
										offset : 350
									});
								}
								 
								if ($scope.list == null || $scope.list.length == 0) {
									layer.msg("订单列表为空，请前往购买商品", {
										offset : 350
									});
									setTimeout(function() {
										history.go(-1);
									}, 2000);

								}

							}
						})
					});

		};

		$scope.repl = function(value) {
			if(value.length == 0){
				return "统一规格";
			}; 
			
			var info = "";
			var array = value.split(",");
			for (var i = 0; i < array.length; i++) {
				var array1 = array[i].split(":");
				info += array1[1] + ";";
			}
			return info.substr(0, info.length - 1);
		}
		
		
		$scope.ReceiptConfirm = function(shippingNo,orderNo){
			layer.confirm("是否确认收货", function (index) {
				
				var url = '/shop/ReceiptConfirm?shippingNo=' + shippingNo +'&orderNo=' + orderNo;
				http.ajax.get(true, true, url, null, http.ajax.CONTENT_TYPE_1,function(result) {
						$scope.$apply(function() {
								if (result.success) {
									$scope.list =  result.obj;
									layer.closeAll();
								}
						})
				});
			})
		};
		
		
		$scope.trackingPackage = function(shippingNo){
			
		    var url = "http://m.kuaidi100.com/result.jsp?nu="+shippingNo+"&com=";     //嵌套网址
			
			layer.open({
	            type: 2,
	            title: ['查询物流',
	                'text-align:center;font-size:16px;background:#fff;'],
	            area: ['90%','90%'],	            
	            moveType: 1,
	            scrollbar: false,
	            zIndex: 99,
	            scrolling: 'no',
	            content: url,
	            anim: 'up',
	            style: 'position:fixed; bottom:0; left:0; width: 100%; height: 200px; padding:10px 0; border:none;'
	            
					
	        });
			
		}
		

		$scope.backOff = function() {
			history.go(-1);
		};

		$scope.goOrderDetails = function(orderNo) {

			window.location = "/shop/OrderDetails?orderNo=" + orderNo;

		};
		
		$scope.details = function(goodsId,skuId){
			window.location = "/shop/detailsPage?goodsId="+goodsId+"&skuId="+skuId;
		}

		$scope.payOrder = function(orderNo) {
			window.location = "/shop/payment?orderNo=" + orderNo;
		};
		$scope.order = function() {
			window.location = "/shop/getOrderAll";
		};
		$scope.findCart = function() {
			window.location = "/cart/findCartList";
		}
		$scope.personal = function() {
			window.location = "/shop/personal";
		}
		$scope.index = function() {
			window.location = "/shop/index";
		};

	});
</script>
</head>

<body ng-app="myShop" ng-controller="orderController" ng-init="init()" ng-cloak>
	<!--头选择项-->

	<ul class="header_shop" style="z-index: 20">
		<li class="header_icon" style="z-index: 20;"><img
			ng-click="backOff()" src="/static/images/shop/img/left_white.png"
			style="width: 1.3rem; height: 1.3rem; margin-left: 1rem;" /></li>
		<li class="header_title" style="height: 1.2rem; z-index: 10"><label>订单列表</label>
		</li>

		<li class="header_icon" style="text-align: right; z-index: 10;"></li>
	</ul>
	<!--end 头选择项-->
	<ul class="header_shop_bg" style="z-index: 10"></ul>

	<div>

		<div class="shopcar_list_div" ng-repeat="item in list">
			<!--店名-->
			<div class="shopcar_list_div_header">
				<div class="shopcar_list_img">
					<img src="/static/images/shop/img/shopname.png" /> <label>{{item.orderNo}}&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<img ng-click="goOrderDetails(item.orderNo)"
						src="/static/images/shop/img/right.png"
						style="width: 0.8rem; height: 1rem;" />
				</div>
				<!--点下编辑按钮时，才能点击图片,传入店铺id，进行操作-->
				<div ng-if="item.status == 1">
					<label style="color: #569008;">未支付</label>
				</div>
				<div ng-if="item.status == 2">
					<label style="color: #569008;">等待发货</label>
				</div>
				<div ng-if="item.status == 3">
					<label style="color: #569008;">已完成</label>
				</div>
				<div ng-if="item.status == 4">
					<label style="color: #569008;">配送中</label>
				</div>
			</div>
			<!--end 店名-->

			<!--订单布局块-->
			<div class="shopcar_list_div_item" ng-repeat="shop in item.orderShopList">	 
				<div style="padding: 1rem 1rem 1rem 1rem;">
					
					{{shop.shopName}}
					<span style="text-align: right; float:right;">
						<label  class="order_button" ng-if="shop.status == 4 || shop.status == 3 " ng-click="trackingPackage(shop.shippingNo)">查看物流</label> 
						<label  class="order_button" ng-if="shop.status == 4" ng-click="ReceiptConfirm(shop.shippingNo,item.orderNo)">确认收货</label> 					
					</span>
					
				</div>				
				<!--订单描述块-->
				<div class="list_item_div_bar" ng-click="details(order.goodsId,order.skuId)" ng-repeat="order in shop.orderList">
					<div class="list_item_bar_left" style="text-align: left;">
						<img src="{{order.pictureUrl}}" /> 
						<div class="list_item_bar_center" style="text-align: left;">
							<div>{{order.goodsName}}<p>								
								{{repl(order.attributeValue)}}
							</div>
						</div>
							
					</div>

					<div class="list_item_bar_right" style="text-align: right;">
						<div>￥{{order.price}}</div>
						<div>x{{order.num}}</div>
					</div>
					
					
				</div>
				
				<!--end 订单描述块-->

			</div>
			<!--end 订单布局块-->
			<!--合计描述-->
			<div class="tip">
				合计:￥<label style="font-size: 1.4rem;">{{item.money}}</label>
			</div>
			<!--end 合计描述-->

			<!--操作按钮组-->
			<div class="order_button_group">
				<label class="order_button" ng-if="item.status == 1" ng-click="payOrder(item.orderNo)">支付订单</label> 
				<label class="order_button" ng-if="item.status == 1" ng-click="clickDeleteOrder(item.orderNo)">删除订单</label>
				<!--<label class="order_button">评价</label>-->
			</div>
			<!--end 操作按钮组-->

		</div>

	</div>

	<!--底部导航-->
	<ul style="height: 4.5rem;"></ul>
	<ul id="position">
		<li><a ng-click="index()"><img id="index1"
				onclick="changeImg('index1')"
				src="/static/images/shop//img/index.png"> <font>首頁</font> </a></li>
		<li><a ng-click="order()"><img id="index2"
				onclick="changeImg('index2')"
				src="/static/images/shop//img/order_on.png"> <font>订单</font> </a></li>

		<li><a ng-click="findCart()"><img id="index4"
				onclick="changeImg('index4')"
				src="/static/images/shop//img/shop.png"> <font>购物车</font> </a></li>
		<li><a ng-click="personal()"><img id="index5"
				onclick="changeImg('index5')"
				src="/static/images/shop//img/mine.png"> <font>个人</font> </a></li>
	</ul>
	<script>
		//切换图标
		function changeImg(id) {

			document.getElementById("index1").src = "/static/images/shop/img/index.png";
			document.getElementById("index2").src = "/static/images/shop/img/order.png";
			document.getElementById("index4").src = "/static/images/shop/img/shop.png";
			document.getElementById("index5").src = "/static/images/shop/img/mine.png";
			switch (id) {
			case 'index1':
				document.getElementById("index1").src = "/static/images/shop/img/index_on.png";
				break;
			case 'index2':
				document.getElementById("index2").src = "/static/images/shop/img/order_on.png";
				break;
			case 'index4':
				document.getElementById("index4").src = "/static/images/shop/img/shop_on.png";
				break;
			case 'index5':
				document.getElementById("index5").src = "/static/images/shop/img/mine_on.png";
				break;
			}
		}
	</script>
	<!--end 底部导航-->
	
	<%-- <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px;>
        <iframe name="myiframe" id="mainContent"  width="100%" height="100%"></iframe>
    </div> --%>

</body>

</html>
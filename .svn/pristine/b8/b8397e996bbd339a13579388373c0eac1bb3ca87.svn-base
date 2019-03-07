<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script src="../../static/plugins/angularJS/angular.min.js"></script>
<script src="../../static/plugins/layui/layui.js"></script>
<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
<script src="../../static/plugins/http.js"></script>
<script type="text/javascript" src="/static/js/vue.js" ></script>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title></title>
<!--公共类-->
<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
<!--首页内容-->
<link href="/static/css/shop/css/content.css" rel="stylesheet">
<link href="/static/semantic/semantic.css" rel="stylesheet" />
<link rel="stylesheet" href="/static/css/shop/css/bootstrap.css">
<!--自适应布局-->
<script type="text/javascript" src="/static/js/mode.js"></script>
<style>
body {
	margin: 0;
	padding: 0;
	font-size: 16px;
	background-color: #f5f5f5;
}
</style>
<style>
.el-carousel__item h3 {
	color: #475669;
	font-size: 18px;
	opacity: 0.75;
	line-height: 300px;
	margin: 0;
}

.el-carousel__item:nth-child(2n) {
	background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
	background-color: #d3dce6;
}

#carousel-ad {
	position: absolute;
}

.carousel .item {
	height: 25rem;
	background-color: #777;
}

.carousel-inner {
	overflow: hidden;
}

.carousel-inner>.item>img {
	float: left;
	top: 0;
	bottom: 0;
	min-width: 100%;
	min-height: 100%;
	height: 25rem;
}
</style>

<style>
.option_item {
	position: relative;
	float: left;
	/*display: table-cell;*/
	display: flex;
	justify-content: center;
	align-items: center;
	width: 33.3%;
	height: 14rem;
	text-align: center;
}
</style>

<script src="/static/js/jq.js"></script>
<script src="/static/js/bootstrap.js"></script>

<!--end 图片轮播-->

<!--底部导航-->
<style>
body, ul, li, span, p, b, strong, h1, h2, h3, h4, h5, h6, ul, dt, li,
	form {
	margin: 0px;
	paliing: 0px;
}

[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak.x-ng-cloak{ 
display: none!important; 
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
</style>

<!-- 4.商品样式 -->
<style>
.box {
	position: relative;
	white-space: nowrap;
	overflow-x: auto;
	padding: 1rem 0 1rem 0;
}

.box::-webkit-scrollbar {
	width: 0;
	height: 0;
	display: none;
}

.block_product_item {
	display: inline-block;
	width: 8rem;
	margin: 0 0.5rem 0 0.5rem;
}
/*商品条*/
.product_item_img {
	width: 100%;
}

.product_item_img img {
	width: 100%;
	border: 1px solid #DEDEDE;
	border-radius: 2rem;
}

.product_item_header {
	width: 8rem;
	white-space: normal;
	word-break: break-all;
	max-width: 8rem;
	word-wrap: break-word;
	padding: 0.5rem 0 0.5rem 0;
	font-size:1.2rem;
}

.product_item_price {
	width: 8rem;
}

.option_item {
	position: relative;
	float: left;
	/*display: table-cell;*/
	display: flex;
	justify-content: center;
	align-items: center;
	width: 33.3%;
	height: 6rem;
	text-align: center;
}


/*3.居中块(包含点击及文本)*/

.option_item>div {
	display: inline-block;
	height: 4rem;
	text-align: center;
}


/*4.具体居中块*/

.option_item>div>span {
	display: block;
	border-radius: 3.5rem;
	width: 4rem;
	height: 4rem;
	line-height: 4rem;
	z-index: 10;
	background-size: cover;
	text-align: center;
}
.option_item>div>label{
	font-size: 1rem;
}


</style>

<!--end 底部导航-->
<script>
	//切换图标
	function changeImg(id) {

		document.getElementById("index1").src = "/static/images/shop/img/index.png";
		document.getElementById("index2").src = "/static/images/shop/img/tool.png";
		document.getElementById("index4").src = "/static/images/shop/img/services.png";
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

<script>
	//商品滑动
	var u = navigator.userAgent;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	var isUc = u.indexOf('UCBrowser') > -1; //uc浏览器
	//var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if (isAndroid && isUc) { /*注释5*/
		$('.box').on('touchstart', function() {
			$(document).on('touchmove', function(e) {
				e.preventDefault();
			});
			$(document).on('touchend', function() {
				$(document).unbind();
			});
		});
	}
</script>

<!-- <div class='main'  style='margin-bottom: 2px;margin-top:1 ren;height:210px;' ng-repeat='column in showPreview'>
	<div class='product_header' >
		<div>
			<div class='header_left'>{{column.category_name}}</div>
			<div class='header_right' ng-click='moreGoods($index,column.id)'>更多</div>
		</div>
	</div>
	<div class='scroll_view'>
		<div class='box'>
			<div class='block_product_item' ng-repeat='item in column.goodList' ng-click='details(item.goodsId)'>
				<div class='product_item_img'><img src={{item.pictureUrl}} /></div>
				<div class='product_item_header'>{{item.goodName}}&nbsp;{{item.attrName}}&nbsp;{{item.attrValue}}</div>
				<div class='product_item_price' style='color: red;'>￥{{item.marketPrice}}</div>
			</div>
		</div>
	</div>
</div> -->

<script type="text/javascript">
	var picture = [];
	var app = angular.module('myShop', []);
	app
			.controller(
					'indexController',
					function($scope, $element, $compile) {
						$scope.Label = null;
						$scope.showPreview = null;
						$scope.advertisement = null;
						$scope.searchKeyWord = null;
						
						var html = "<div class='main'  style='margin-bottom: 2px;margin-top:1 ren;height:20rem;' ng-repeat='column in showPreview'><div class='product_header' ><div><div class='header_left'>{{column.category_name}}</div><div class='header_right' ng-click='moreGoods($index,column.id)'>更多</div></div></div><div class='scroll_view'><div class='box'><div class='block_product_item' ng-repeat='item in column.goodList' ng-click='details(item.goodsId,item.id)'><div class='product_item_img' ng-repeat='pic in item.pictureUrls' ng-if='pic.masterGraph==0'><img src={{pic.pictureUrl}} style='width:7rem;height:7rem;' /></div><div class='product_item_header' style='height:4rem;overflow-y:hidden;'>{{item.goodName}}&nbsp;{{item.attrName}}&nbsp;{{item.attrValue}}</div><div class='product_item_price' style='color: red;'>￥{{item.favorablePrice}}</div></div></div></div></div>";
						/* <div class='main'  style='margin-bottom: 2px;margin-top:1 ren;height:20rem;' ng-repeat='column in showPreview'><div class='product_header' ><div><div class='header_left'>{{column.category_name}}</div><div class='header_right' ng-click='moreGoods($index,column.id)'>更多</div></div></div><div class='scroll_view'><div class='box'><div class='block_product_item' ng-repeat='item in column.goodList' ng-click='details(item.goodsId)'><div class='product_item_img'><img src={{item.pictureUrl}} style='width:7rem;height:7rem;' /></div><div class='product_item_header' style='height:4rem;overflow-y:hidden;'>{{item.goodName}}&nbsp;{{item.attrName}}&nbsp;{{item.attrValue}}</div><div class='product_item_price' style='color: red;'>￥{{item.favorablePrice}}</div></div></div></div></div>"; */
						/* 	var html = "<div class='main'  style='margin-bottom: 2px;margin-top:1 ren;height:210px;' ng-repeat='column in showPreview'><div class='product_header' ><div ><div class='header_left'>{{column.category_name}}</div><div class='header_right' ng-click='moreGoods($index,column.id)'>更多</div></div></div><div class='scroll_view'><div class='box'><div class='product_item' ng-repeat='item in column.goodList' ng-click='details(item.goodsId)'><div class='product_img'><img src={{item.pictureUrl}} /></div><div style='display: table-row;'><div class='product_title'><textarea class='product_title_content' rows='2' readonly>{{item.goodName}}&nbsp;{{item.attrName}}&nbsp;{{item.attrValue}}</textarea></div></div><div class='product_price' style='color: red;'>￥{{item.marketPrice}}</div></div></div></div></div>"; */
						var $html = $compile(html)($scope);
						$("#preview").append($html);

						$scope.init = function() {
							$scope.findClassfiyTags();
							$scope.showPreview();
							$scope.getAdvertisement();
						}

						//获取标签
						$scope.findClassfiyTags = function() {
							var url = '/shop/findClassfiyTags';
							http.ajax.get(true, true, url, null,
									http.ajax.CONTENT_TYPE_1, function(result) {
										$scope.$apply(function() {
											if (result.success) {
												$scope.Label = result.obj;
											}
											;
										})
									});
						}
						//获取预览
						$scope.showPreview = function() {
							var url = '/shop/showPreview';
							var data = {
								"oneColumn" : 1,
								"twoColumn" : 2
							};
							http.ajax
									.post(
											true,
											true,
											url,
											data,
											http.ajax.CONTENT_TYPE_1,
											function(result) {
												$scope
														.$apply(function() {
															if (result.success) {
																$scope.showPreview = result.obj;
															}
														})
											});
						}
						//获取广告
						$scope.getAdvertisement = function() {
							var url = '/shop/getAdvertisement';
							http.ajax
									.get(
											true,
											true,
											url,
											null,
											http.ajax.CONTENT_TYPE_1,
											function(result) {
												$scope
														.$apply(function() {
															if (result.success) {
																$scope.advertisement = result.obj;
																$scope.advertisement2 = [];
																for(var i = 0;i< $scope.advertisement.length;i++){
																	$scope.advertisement2.push($scope.advertisement[i].image);
																	/* phone = $scope.advertisement2; */
																}
															}
														})
											});
						}
						
						
						$scope.onBulr = function(){							
							if( $scope.searchKeyWord !=null){
								window.location =  '/shop/shopSearch?keyWord='+$scope.searchKeyWord;								
							}
						}
						
						

						$scope.order = function() {
							window.location = "/shop/getOrderAll";
						};

						$scope.findCatalog = function() {
							window.location = "/shop/findCatalogAll";
						}
						
						$scope.moreGoods = function(index, id) {
							if (typeof (id) == "undefined") {
								layer.msg("查看的商品为空", {offset: 350});
								return;
							}
							window.location = "/shop/moreGoodsPage?id=" + id;
						}

						$scope.details = function(goodsId,skuId) {
							window.location = "/shop/detailsPage?goodsId="+ goodsId +"&skuId="+skuId;
						}

						$scope.findCart = function() {
							window.location = "/cart/findCartList";
						}

						$scope.findCatalogById = function(id) {
							window.location = "/shop/findCatalogById?id=" + id;
						}

						$scope.personal = function() {
							window.location = "/shop/personal";
						}
						
						picture = $scope.advertisement2; 
						
					})
</script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<!-- <script>
			
			
			//注:轮播图图片数据
			var carousel_ad = new Vue({
				el : '#carousel_ad',
				data : {
					dataurl : [
						'/static/images/shop/img/indexbg.png',
						'/static/images/shop/img/productimg.png',
						'/static/images/shop/img/indexbg.png',
						'/static/images/shop/img/productimg.png']
				}
				
			});
</script> -->


</head>

<body ng-app="myShop" ng-controller="indexController" ng-init="init()" ng-cloak>
	
	<!--图片轮播-->
	 	<div id="carousel-ad" class="carousel slide" data-ride="carousel" style="width:100%;height: 20rem;">
			<div class="carousel-inner" role="listbox" style="width:100%;height: 20rem;" ontouchstart='getFlag()' ontouchmove='setFlag()' ontouchend='change()'>				
				<div style="height:20rem;" class="item active" ng-if="index==0" ng-repeat="(index,picture) in advertisement"><img class="img-responsive" style="width:100%;height:100%;" src={{picture.image}}></div> 
				<div style="height:20rem;" class="item" ng-if="index!=0" ng-repeat="(index,picture) in advertisement"><img class="img-responsive" style="width:100%;height:100%;" src={{picture.image}}></div>										
			</div>
		</div> 

	<!--首页图片-->
	<div class="main main_index_img"
		style="position: relative; height: 20rem; background-size: cover; width: 100%; background-color: transparent;">
	<!-- 	<div id="carousel_ad">
			<el-carousel indicator-position="none" height="25rem">
				<el-carousel-item v-for="item,i in dataurl" :key="i">
					<img :src="item" style="width: 100%;height: 100%;" />
				</el-carousel-item>
			</el-carousel>
		</div> -->
		<!--end 轮播图-->
	
		<div
			style="position: absolute; top: 0; width: 100%; text-align: center; top: 1rem; z-index: 100;">
			<div class="ui left icon input" style="width: 80%;">
				<input type="text" placeholder="搜索商品"
					style="border-radius: 10rem; border-color: #d1d1d1;" ng-blur="onBulr()" ng-model="searchKeyWord"/> 
					 <i class="search icon" ></i> 
			</div>
		</div>
		<!--购物车图标-->
		<div
			style="position: absolute; top: 0.8rem; right: 1rem; display: table;">
			<img src="/static/images/back.png" style="width: 1.8rem; height: 1.8rem; margin-top: 1rem; z-index:20;" ng-click="onBulr()"/>
		</div>
		<!--end 购物车图标-->
	</div>
	<!--end 首页图片-->
	<!--选项-->
	<ul class="main option_div" style="height: 14rem;">
		<div class="option_row">
			<li class="option_item" ng-repeat="item in Label">
				<div ng-if="item.labelName == '全部分类'" ng-click="findCatalog()">
					<span style="background-image: url({{item.icon}});"></span><label>{{item.labelName}}</label>
				</div>
				<div ng-if="item.labelName != '全部分类'"
					ng-click="findCatalogById(item.id)">
					<span style="background-image: url({{item.icon}});"></span><label>{{item.labelName}}</label>
				</div>
			</li>
		</div>
	</ul>
	<!--end 选项-->	
	<!--商品条目-->
	<div id="preview">
		<!-- <div class="main"  style="margin-bottom: 2px;margin-top:1 ren;height:210px;" ng-repeat="column in showPreview">
				<div class="product_header" >
					<div >
						<div class="header_left">{{column.category_name}}</div>
						<div class="header_right" ng-click="moreGoods($index,column.id)">更多</div>
					</div>
				</div>
				<div class="scroll_view">
					<div class="box">
						<div class="product_item" ng-repeat="item in column.goodList" ng-click="details(item.goodsId,item.id)">
							<div class="product_img">
								<img src={{item.pictureUrl}} />
							</div>
							<div style="display: table-row;">
								<div class="product_title"><textarea class="product_title_content" rows="2" readonly>{{item.goodName}}&nbsp;{{item.attrName}}&nbsp;{{item.attrValue}}</textarea></div>
							</div>
							<div class="product_price" style="color: red;">￥{{item.marketPrice}}</div>
						</div>					
					</div>
				</div>
			</div> -->
	</div>



	<div style="height: 4.5rem;z-index:-1;"></div>

	<ul id="position" style="z-index:20;">
		<li><a href="#"><img id="index1"
				onclick="changeImg('index1')"
				src="/static/images/shop//img/index_on.png"> <font>首頁</font> </a></li>
		<li ng-click="order()"><a href="#"><img id="index2"
				onclick="changeImg('index2')"
				src="/static/images/shop/img/order.png"> <font>订单</font> </a></li>
		<li ng-click="findCart()"><a href="#"><img id="index4"
				onclick="changeImg('index4')" src="/static/images/shop/img/shop.png">
				<font>购物车</font> </a></li>
		<li ng-click="personal()"><a href="#"><img id="index5"
				onclick="changeImg('index5')" src="/static/images/shop/img/mine.png">
				<font>个人</font> </a></li>
	</ul>
	<!--end 底部导航-->
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
	
	<script>
	$(function () {
	    // 获取手指在轮播图元素上的一个滑动方向（左右）
	
	    // 获取界面上轮播图容器
	    var $carousels = $('.carousel');
	    var startX,endX;
	    // 在滑动的一定范围内，才切换图片
	    var offset = 50;
	    // 注册滑动事件
	    $carousels.on('touchstart',function (e) {
	        // 手指触摸开始时记录一下手指所在的坐标x
	        startX = e.originalEvent.touches[0].clientX;
	
	    });
	    $carousels.on('touchmove',function (e) {
	        // 目的是：记录手指离开屏幕一瞬间的位置 ，用move事件重复赋值
	        endX = e.originalEvent.touches[0].clientX;
	    });
	    $carousels.on('touchend',function (e) {
	        //console.log(endX);
	        //结束触摸一瞬间记录手指最后所在坐标x的位置 endX
	        //比较endX与startX的大小，并获取每次运动的距离，当距离大于一定值时认为是有方向的变化
	        var distance = Math.abs(startX - endX);
	        if (distance > offset){
	            //说明有方向的变化
	            //根据获得的方向 判断是上一张还是下一张出现
	            $(this).carousel(startX >endX ? 'next':'prev');
	        }
	    })
	});
</script>
</body>
</html>
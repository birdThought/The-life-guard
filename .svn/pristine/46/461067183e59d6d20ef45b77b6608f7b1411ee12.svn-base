<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1,maximum-scale=1, minimum-scale=1">
<script type="text/javascript" src="/static/js/jq.js"></script>
<script type="text/javascript" src="/static/js/vue.js"></script>
<script src="../../static/plugins/http.js"></script>
<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
<title></title>
<style>
*{
	font-size:1rem;
}
body {
	margin: 0;
	padding: 0;
}
</style>
<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
<link rel="stylesheet" href="/static/css/shop/css/subcontent.css" />
<link rel="stylesheet" href="/static/css/shop/css/subcontent_plus.css" />
<!--自适应js-->
<script type="text/javascript" src="/static/js/mode.js"></script>
<script type="text/javascript" src="/static/js/vue.js"></script>
<style>

[v-cloak] {
  display: none;
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
body {
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.shopcar_list_img {
	display: flex;
	align-items: center;
}
/*购物车组件*/
.shopcar_list_div {
	margin-bottom: 1rem;
	background-color: white;
}
/*购物车头*/
.shopcar_list_div_header {
	display: flex;
	font-size: 1.3rem;
	padding: 1rem 1rem 1rem 1rem;
	border-bottom: 1px solid #d2d2d2;
}

.shopcar_list_div_header img {
	width: 1.2rem;
	height: 1.2rem;
}

.shopcar_list_div_header>div:nth-of-type(1) {
	width: 8%;
}

.shopcar_list_div_header>div:nth-of-type(2) {
	width: 80%;
}

.shopcar_list_div_header>div:nth-of-type(3) {
	width: 12%;
	text-align: right;
}
/*购物车子项*/
.shopcar_list_div_item {
	display: flex;
	align-items: center;
	font-size: 1.5rem;
	padding: 1.5rem 1rem 1.5rem 1rem;
	border-bottom: 1px solid #d2d2d2;
}

.shopcar_list_div_item img {
	width: 1.2rem;
	height: 1.2rem;
}

.shopcar_list_div_item>div:nth-of-type(1) {
	width: 8%;
}

.shopcar_list_div_item>div:nth-of-type(2) {
	width: 92%;
}
/*子项内容块*/
.list_item_div_bar {
	display: flex;
	padding-right: 1rem;
	/* border-bottom: 1px dashed #d2d2d2; */
}

.list_item_bar_left {
	width: 25%;
}

.list_item_bar_center {
	width: 45%;
}

.list_item_bar_right {
	width: 30%;
	text-align: right;
}
/*提示*/
.shopcar_list_div_tip {
	display: flex;
	color: #fc7b04;
	font-size: 0.9rem;
	padding: 0.2rem 1rem 0.2rem 1rem;
	border-bottom: 1px solid #d2d2d2;
	background-color: #fff4dd;
}

.shopcar_list_div_tip img {
	width: 0.6rem;
	height: 0.8rem;
}

.shopcar_list_div_tip>div:nth-of-type(1) {
	width: 70%;
}

.shopcar_list_div_tip>div:nth-of-type(2) {
	width: 30%;
	text-align: right;
}
</style>
<style>
/*去结算布局*/
.bar_total_shopcar {
	width: 100%;
	position: fixed;
	bottom: 0;
	display: flex;
	align-items: center;
	border-top: 1px solid #d4d4d4;
	padding: 1rem 0 1rem 0;
	background-color: #f4f4f4;
}

.bar_total_shopcar>div:nth-of-type(1) {
	width: 70%;
	padding-left: 1rem;
}

.bar_total_shopcar>div:nth-of-type(2) {
	width: 30%;
	text-align: center;
	color: #ffeede;
	padding: 0.3rem 0 0.3rem 0;
	border-radius: 1rem;
	font-size: 1.2rem;
	background-color: #ff8309;
	margin-right: 1rem;
}
</style>
<style>
body, ul, li {
	margin: 0px;
	padding: 0px;
}

body {
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
</head>

<body>

	<div  id="order_list" v-cloak>
	<!--头选择项-->
	<ul class="header_shop" style="z-index: 15">
		<li class="header_icon" onclick="backOff()"><img
			src="/static/images/shop/img/left_white.png"
			style="width: 1.3rem; height: 1.3rem; margin-left: 1rem;" /></li>
		<li class="header_title" style="height: 1.2rem;"><label>购物车</label>
		</li>
		<li class="header_icon" style="text-align: right;">
			<!--点下编辑按钮时，才能点击图片,传入店铺id，进行操作-->
			<div @click="clickEdit(i)">
				<label v-if="!editStatus">编辑&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label v-if="editStatus" style="color: #569008;">完成&nbsp;&nbsp;&nbsp;&nbsp;</label>
			</div>
		</li>
	</ul>
	<!--end 头选择项-->
	<ul class="header_shop_bg" style="z-index: 10"></ul>
	<div>
		<div class="shopcar_list_div" v-for="shop,i in orderlist">
			<!--店名-->
			<div class="shopcar_list_div_header">
				<div class="shopcar_list_img">
					<!-- <input type="checkbox" id={{shop.seller}}  v-on:change="checked(shop,$event)"> -->
					<img style="width: 1.2rem; height: 1.2rem;"
						:src="clickAll == false?'/static/images/shop/img/add_no.png':'/static/images/shop/img/add_choice.png'"
						@click="clickAllItem(i,shop.orderItemList.length),checked(shop,$event)" />
				</div>
				<div >
					<img src="/static/images/shop/img/shopname.png" /> <label>{{shop.seller}}</label>
					<img src="/static/images/shop/img/right.png"
						style="width: 1rem; height: 1rem;" />
				</div>


			</div>
			<!--end 店名-->
			<!--店名购物商品-->
			<div class="shopcar_list_div_item"v-for="productItem,j in shop.orderItemList" style="height:6rem;">
				<div>
					<!-- <input type="checkbox" id={{productItem.id}}  v-on:change="checked(productItem.id,$event)"> -->
					<img :src="clickArr[i][j]==true?'/static/images/shop/img/add_choice.png':'/static/images/shop/img/add_no.png'"
						@click="clickItem(i,j),checked(productItem.id,$event)" />
				</div>
		
				<!--商品项-->
				<div class="list_item_div_bar" v-if="clickArr[i][j]==false || !editStatus" >
					<div class="list_item_bar_left" v-for="pic in productItem.pictureUrls" v-if="pic.masterGraph==0">	
						<img v-bind:src="pic.pictureUrl" style="width:100%;height:100%;"/>
					</div>
					<div class="list_item_bar_center" style="margin-left: 1.5rem;" @click="details(productItem.goodsId,productItem.id)">
						<div style="font-size:1.2rem;">{{productItem.goodsName}}</div>						
						<div style="font-size: 1.2rem; color: #999999;" >{{productItem.group_spec | repl}}</div>
						<div style="color: red;">
							￥{{productItem.favorablePrice}} 
							<label style="color: #999999; font-size:1rem; text-decoration: line-through;">￥{{productItem.marketPrice}}</label>
						</div>
					</div>
					<div class="list_item_bar_right">
						<span style="color: #9d9d9d; font-size: 1.8rem;" v-on:click="reduce(productItem.id)">-&nbsp;&nbsp;</span>
						<span>x{{productItem.num}}</span>
						<span style="color: #9d9d9d; font-size: 1.8rem;" @click="add(productItem.id)">&nbsp;&nbsp;+</span>
					</div>
				</div> 
				<!--end 商品项-->
				<!--编辑操作-->
				<div class="list_item_div_bar" v-if="clickArr[i][j]==true & editStatus">
					<div class="list_item_bar_left" v-for="pic in productItem.pictureUrls" v-if="pic.masterGraph==0">
						 <img v-bind:src="pic.pictureUrl" style="width:100%;height:100%;"/>
					</div>
					<div class="list_item_bar_center" style="margin-left: 1.5rem;">
						<div style="padding: 1.2rem 0 1.2rem 0;">
							<div style="font-size: 1.2rem;">
								<label style="color: #9d9d9d; font-size: 1.4rem;">{{productItem.goodsName}}&nbsp;&nbsp;</label>
							</div>
						</div>
						<div style="font-size: 0.8rem; color: #999999;">{{productItem.attribute3}}</div>

					</div>
					<div class="list_item_bar_right" style="display: flex; justify-content: center; align-items: center;">
						<img src="/static/images/shop/img/delete.png" style="width: 2.3rem; height: 2.3rem;" @click="deleteItem(i,j)" />
					</div>
				</div>
				<!--end 编辑操作-->

			</div>
			</div>
			<!--end 店名购物商品-->

		</div>

		<div style="height: 4rem; width: 100%; bottom: 0; background-color: transparent; border: 0; z-index: -12;"></div>
		<div class="bar_total_shopcar" style="position: fixed; bottom: 4.5rem;">
			<div style="font-size:1.3rem;">
				合计:<label style="color: red;" id="priceCound">0.00</label>
			</div>
			<div @click="total">
				<div>去结算</div>
			</div>
		</div>
		<!--底部导航-->
		<ul style="height: 4.5rem;"></ul>
		<ul id="position">
			<li><a @click="linkItem('index1')"><img id="index1"
					onclick="changeImg('index1')"
					src="/static/images/shop/img/index.png"> <font>首頁</font> </a></li>
			<li><a @click="linkItem('index2')"><img id="index2"
					onclick="changeImg('index2')"
					src="/static/images/shop/img/order.png"> <font>订单</font> </a></li>
			<li><a @click="linkItem('index4')"><img id="index4"
					onclick="changeImg('index4')"
					src="/static/images/shop/img/shop_on.png"> <font>购物车</font> </a></li>
			<li><a @click="linkItem('index5')"><img id="index5"
					onclick="changeImg('index5')"
					src="/static/images/shop/img/mine.png"> <font>个人</font> </a></li>
		</ul>
		<!--end 底部导航-->
	</div>

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

</body>

<script>
	var order_list = new Vue(
			{
				el : '#order_list',
				data : {
					view:true,
					editStatus:false,
					//注：实质购物车对象，结算时，传递到下一个页面
					orderlist : JSON.parse('${cartList}'),
					//注意:被选中数据的下标集
					clickArr : this.clickArr,
					//初始时编辑数组
					editArr : this.editArr,
					//初始时全选数组
					clickAll : this.clickAll,

					checkedShop : []
				},
				watch : {
					checkedShop : 'changePrice'
				},
				filters : {
					repl : function(value) {
						
						if(value.length == 0 || value == " "){
							return "统一规格";
						}; 
						if(typeof(value)=="undefined"){
							return "统一规格";
						}
						
						var info = "";
						var array = value.split(",");
						for (var i = 0; i < array.length; i++) {
							var array1 = array[i].split(":");
							info += array1[1] + ";";
						}
						return info.substr(0, info.length - 1);
					}
				},
				mounted : function() {
					//记录建立对应的点击数组
					//初始化点击数组
					var clickArr = [];
					for (var i = 0; i < this.orderlist.length; i++) {
						clickArr[i] = [];
						for (var j = 0; j < this.orderlist[i].orderItemList.length; j++) {
							clickArr[i][j] = false;
						}
					}
					this.clickArr = clickArr;

					//初始化编辑数组
					var editArr = [];
					//初始化全选
					var clickAll = false;
					for (var i = 0; i < this.orderlist.length; i++) {
						editArr[i] = false;
						/* clickAll[i] = false; */
					}
					this.editArr = editArr;
					this.clickAll = clickAll;
				},
				methods : {
					//按钮点击数组
					clickItem : function(i, j) {
						Vue.set(order_list.clickArr[i], j,!order_list.clickArr[i][j]);
						if(this.clickAll){
							this.clickAll = !this.clickAll;
						}
						var len = 0;
						for(var a= 0;a<order_list.clickArr[i].length;a++){
							if(this.clickArr[i][a]){
								 len++;
							}
						}
						
						if(order_list.clickArr[i].length==len){
							console.log(len);
							this.clickAll = true;
						}
					},
					//编辑数组
					//每次点击取反，共同影响删除
					clickEdit : function(i) {
						/* Vue.set(order_list.editArr, i, !order_list.editArr[i]); */
						this.editStatus = !this.editStatus;
					},
					//全选
					//设置被选按钮为全true或全false
					clickAllItem : function(i, j) {
						for (var a = 0; a <= j; a++) {
							Vue.set(order_list.clickArr[i], a, !order_list.clickAll);
						}
						order_list.clickAll=!order_list.clickAll;
					},
					//加减，
					add : function(i) {
						var url = '/cart/addItemToCartList';
						var data = {
							"itemId" : i,
							"num" : 1
						};
						$.post(url, data, function(result) {
							if (result.success) {
								order_list.$data.orderlist = result.obj;
							}
							;
						});
					},
					reduce : function(i) {
						var url = '/cart/addItemToCartList';
						var data = {
							"itemId" : i,
							"num" : -1
						};
						$.post(url, data, function(result) {
							if (result.success) {
								order_list.$data.orderlist = result.obj;
							}
							;
						});

					},
					//删除
					deleteItem : function(i, j) {
						
						var vueArray = order_list.$data.checkedShop;
						var result = null;
						var array = [];
						for ( var index in vueArray) {
							var item = vueArray[index];
							item = item.replace('(', '').replace(')', '');
							array.push(item);
						}

						var url = "/cart/delItemToCartList?delItemList="
								+ array;
						http.ajax
								.get(
										true,
										true,
										url,
										null,
										http.ajax.CONTENT_TYPE_1,
										function(result) {
											if (result.success) {
												if (result.obj == null
														|| result.obj.length == 0) {
													window.location = "/cart/findCartList";
												}
												order_list.$data.orderlist = result.obj;
												order_list.$data.checkedShop = [];
											}
										});
						/*  购物车数据删除掉，对应点击的下标也删除掉
						 this.orderlist[i].orderItemList.splice(j, 1);*/
						this.clickArr[i].splice(j, 1);
					},
					//结算
					total : function(e) {
						var vueArray = order_list.$data.checkedShop;
						var result = null;
						var array = [];
						for ( var index in vueArray) {
							var item = vueArray[index];
							item = item.replace('(', '').replace(')', '');
							array.push(item);
						}
						if (array.length == 0) {
							layer.msg("请选择商品", {
								offset : 350
							});
							return;
						}

						var url = "/shop/LoadingList?LoadingList=" + array;
						window.location = url;
					},
					checked : function(obj, e) {
						if (typeof (obj) == 'number') { 
							if (e.target.currentSrc.match("static/images/shop/img/add_no.png")) {

								order_list.$data.checkedShop.push("(" + obj + ")");
							} else {
								order_list.$data.checkedShop.splice(order_list.$data.checkedShop.indexOf("(" + obj + ")"), 1);
							}

						} else {
							if (e.target.currentSrc.match("static/images/shop/img/add_no.png")) {
								for (var x = 0; x < obj.orderItemList.length; x++) {
									if (order_list.$data.checkedShop.indexOf("("+ obj.orderItemList[x].id+ ")") == -1) {
										order_list.$data.checkedShop.push("("+ obj.orderItemList[x].id+ ")");
									}
								}

							} else {
								for (var x = 0; x < obj.orderItemList.length; x++) {
									var id = obj.orderItemList[x].id;
									order_list.$data.checkedShop.splice(
													order_list.$data.checkedShop.indexOf("("+ obj.orderItemList[x].id)+ ")", 1);
								}
							}

						}
						
						

					},
					changePrice : function(curVal, oldVal) {
						
						var prict = 0.00;
						for (var i = 0; i < order_list.$data.orderlist.length; i++) {
							for (var j = 0; j < order_list.$data.orderlist[i].orderItemList.length; j++) {
								if (curVal
										.indexOf("("
												+ order_list.$data.orderlist[i].orderItemList[j].id
												+ ")") != -1) {
									prict += order_list.$data.orderlist[i].orderItemList[j].favorablePrice
											* order_list.$data.orderlist[i].orderItemList[j].num
								}

							}

						}

						$("#priceCound").html("￥" + prict.toFixed(2));
					},
					linkItem:function(text){
						switch(text){
						   case 'index1':
							   window.location = "/shop/index";
							   break;
						   case 'index2':
							   window.location = "/shop/getOrderAll";
							   break;
						   case 'index4':
							   window.location = "/cart/findCartList";
							   break;
						   case 'index5':
							   window.location = "/shop/personal";
							   break;
						}
					},
					details:function(goodsId,skuId){
						window.location = "/shop/detailsPage?goodsId="+goodsId+"&skuId="+skuId;
					}
					

				}
			});

	function backOff() {
		history.go(-1);
	}
</script>

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

</html>
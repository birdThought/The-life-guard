<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title></title>
		<!--首页内容-->
		<link href="/static/css/shop/css/content.css" rel="stylesheet">
		<!--自适应布局-->

		<style>
			body {
				margin: 0;
				padding: 0;
			}
		</style>

		<style>
			body {
				margin: 0;
				padding: 0;
			}
			
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
		</style>
		<style>
			body {
				margin: 0;
				padding: 0;
			}
			/*头部*/
			
			.header_shop {
				position: fixed;
				width: 100%;
				list-style-type: none;
				top: 0;
				z-index: 10;
				background-color: white;
				margin: 0;
				padding: 0;
				height: 1.5rem;
				padding: 0.8rem 0 0.8rem 0;
				border-bottom: 1px solid #f4f4f4;
			}
			
			.header_shop_bg {
				position: relative;
				list-style-type: none;
				top: 0;
				margin: 0;
				padding: 0;
				height: 1.5rem;
				padding: 0.8rem 0 0.8rem 0;
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
			/*选择列表*/
			
			.option_shop {
				position: fixed;
				display: none;
				width: 100%;
				top: 3rem;
				list-style-type: none;
				z-index: 10;
				margin: 0;
				padding: 0.5rem 0 0.5rem 0;
				background-color: white;
				border-bottom: 1px solid #eeeeee;
			}
			
			.option_shop li {
				float: left;
				width: 50%;
				text-align: center;
				padding: 0.5rem 0 0.5rem 0;
			}
			/*a标签属性*/
			
			a {
				text-decoration: none;
				color: black;
			}
			
			.active {
				color: #44c660;
			}
			
			.table_data {
				position: relative;
				width: 100%;
				top: 6rem;
				/*outline: 1px solid red;*/
				display: none;
			}
			
			.table_bar {
				padding: 1rem 0.5rem 1rem 0.5rem;
			}
			
			.table_bar>div {
				float: left;
				/*width: 50%;*/
			}
			
			.table_bar>div:nth-of-type(1) {
				width: 30%;
			}
			
			.table_bar>div:nth-of-type(2) {
				width: 70%;
			}
			/*说明书:表格描述*/
			/*.tab_describe {
				position: relative;
				top: 8rem;
				width: 100%;
				margin: 0;
				padding: 0;
				list-style-type: none;
			}*/
			
			.tab_describe_row {
				/*position: relative;
				top: 8rem;*/
				width: 100%;
				margin: 0;
				padding: 0;
				list-style-type: none;
			}
			
			.tab_describe_tabcell {
				float: left;
				width: 45%;
				/*display: table-cell;*/
				border: 1px solid #dedede;
				padding: 0.5rem 0.5rem 0.5rem 0.5rem;
			}
		</style>

		<style>
			.nav {
				position: fixed;
				display: flex;
				width: 100%;
				padding: 0;
				bottom: 0;
				list-style-type: none;
				/*outline: 1px solid red;*/
				border-top: 1px solid #eeeeee;
				background-color: white;
			}
			
			.nav>li {
				width: 25%;
				color: white;
				text-align: center;
				padding: 0.5rem 0 0.5rem 0;
				/*outline: 1px solid red;*/
			}
			/*前两列占20%*/
			
			.nav_li_icon {
				width: 20%;
			}
			
			.nav_li_icon_div {
				font-size: 0.6rem;
				color: black;
			}
			/*后两列为30%*/
			
			.nav_li_button {
				width: 30%;
				display: flex;
				justify-content: center;
				align-items: center;
				font-size: 1rem;
				background-color: #ffa200;
			}
			
			.nav_li_button_submit {
				background-color: #44c660;
			}
			/*规格*/
			
			.popu_bar_option {
				position: relative;
				padding: 0rem 0 1rem 0;
				border-bottom: 1px solid #e5e5e5;
				margin-left: 0.5rem;
			}
			
			.popu_bar_option_title {
				padding: 0.5rem 0 0.5rem 0;
			}
			
			.popu_bar_option a {
				margin-right: 0.5rem;
				margin-top: 0.5rem;
				margin-bottom: 0.5rem;
				padding: 0.2rem 0.6rem 0.2rem 0.6rem;
				background-color: #dde1e2;
				border-radius: 0.4rem;
			}
			
			.popu_bar_option a:hover {
				color: #f8f0c1;
				background-color: #ff4f00;
			}
			
			.active_label {
				color: #f8f0c1;
				background-color: #ff4f00;
			}
		</style>

		<style>
			/*规格*/
			
			.popu_bar_option {
				position: relative;
				padding: 0rem 0 1rem 0;
				border-bottom: 1px solid #e5e5e5;
				margin-left: 0.5rem;
			}
			
			.popu_bar_option_title {
				padding: 0.5rem 0 0.5rem 0;
			}
			
			.popu_bar_option label {
				margin-right: 0.5rem;
				margin-top: 0.5rem;
				margin-bottom: 0.5rem;
				padding: 0.2rem 0.6rem 0.2rem 0.6rem;
				background-color: #dde1e2;
				border-radius: 0.4rem;
			}
			
			.popu_bar_option label:hover {
				color: #f8f0c1;
				background-color: #ff4f00;
			}
			
			.active_label {
				color: #f8f0c1;
				background-color: #ff4f00;
			}
		</style>

		<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent.css" />
		<link rel="stylesheet" href="/static/css/shop/css/slide.css" />
		<link rel="stylesheet" href="/static/css/shop/css/product_index_option.css" />
		<!-- 引入样式 -->
		<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
		<!--自适应js-->
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script src="../../static/plugins/layui/v2.1.2/layui.all.js""></script>
		<script src="../../static/plugins/http.js"></script>
		<script type="text/javascript" src="/static/js/jq.js"></script>
		<script type="text/javascript" src="/static/js/vue.js"></script>
		<!-- <script type="text/javascript" src="/static/js/data_sku.js"></script>
		<script type="text/javascript" src="/static/js/data_sku_new.js"></script> -->
	</head>

	<body>
		<!--头选择项-->
		<ul class="header_shop">
			<li class="header_icon" onclick="backOff()"><img src="/static/images/shop/img/icon_return.png" style="width: 0.7rem;height: 1.2rem;margin-left: 1rem;" /></li>
			<li class="header_title" style="height: 1.2rem;">
				<label id="button_product">商品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label id="button_describe">详情</label>
				<label id="button_commemt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;评论</label>
			</li>
			<li class="header_icon" style="text-align: right;"><img src="/static/images/shop/img/share.png" style="width: 1.5rem;height: 1.5rem;margin-right: 1rem;" /></li>
		</ul>
		<!--end 头选择项-->
		<ul class="header_shop_bg"></ul>

		<!--二。详情块-->
		<!--列表选择-->
		<ul id="div_describe_block2" class="option_shop">
			<li>
				<a id="li1" href="#" class="active">图文详情</a>
			</li>
			<li>
				<a id="li2" href="#">说明书</a>
			</li>
		</ul>
		<!--end 列表选择-->
		<!--end 二。详情块-->

		<!--一。商品块-->
		<div id="div_product_block">
			<!--轮播图-->
			<div style="height: 17rem;border-bottom: 1px solid #eeeeee;">
				<div id="app">
					<el-carousel indicator-position="none" height="17rem">
						<el-carousel-item v-for="item in dataurl" :key="item">
							<!--<h3>{{ item }}</h3>-->
							<img :src="item" style="width: 100%;height: 100%;" />
						</el-carousel-item>
					</el-carousel>
				</div>
			</div>
			<!--end 轮播图-->
			<div id="format" style="z-index:5">
				<!--1.价格描述-->
				<div class="product_bar_price">
					<div>{{goods.goodName}}</div> 
					<div>
						<label style="color: red;">￥{{obj.favorablePrice}}</label>
						<label style="font-size: 0.8rem;color: #999999;">&nbsp;市场价: <span style="text-decoration: line-through;">￥{{obj.marketPrice}}</span>&nbsp;已省{{obj.marketPrice-obj.favorablePrice}}元</label>
						<label style="font-size: 0.8rem;border:1px dashed red;color: red;">&nbsp;包邮</label>
					</div>
					<div>
						
						<label style="font-size: 0.8rem;color: #999999;">&nbsp;剩余: {{obj.inventory}}			
						<label style="font-size: 0.8rem;color: #999999;">&nbsp;销量：&nbsp;{{obj.salesVolume}}
					</div>
				</div>
				<!--end 1.价格描述-->
			
				<!--2.plus功效-->
				<div class="product_bar_effect">
					<div>功效</div>
					<div id="option_show" style="text-align: right;"><img src="/static/images/shop/img/arrow_up.png" style="width: 1rem;height: 1rem;" /></div>
				</div>
				<!--end 2.plus功效-->
				<!--2.详细描述-->
				<div id="show_div" class="product_bar_effect_describe">
					<div>1.慢性胃炎</div>
					<div>2.与胃酸有关的胃部不适应症，如:胃痛、胃灼烧感(烧心)、酸性暖气、饱胀等。</div>
				</div>				
				<!--end 2.详细描述-->

				<!--规格选择-->
				<!--pointer-events:none;-->
				<!--<div id="format">
				<div class="popu_bar_option" v-for="item,i in formatLable">
					<div class="popu_bar_option_title">{{formatTitle[i]}}</div>
					<div class="popu_bar_option_label">
						<a :style="clickLable[i][j]?'background-color: #ff4f00;color: #f8f0c1;':''" v-for="lable,j in formatLable[i]" @click="clickFormat(item,i,j,$event)">{{lable}}</a>
					</div>
				</div>
			</div>-->
				<!--end 规格选择-->
				<!--规格选择-->
				<!--pointer-events:none;-->
				<div>
					<div v-if="item.usedNum>0" class="popu_bar_option" v-for="item,i in obj_sku_title">
						<div class="popu_bar_option_title">{{item.name}}</div>
						<div class="popu_bar_option_label" style="font-size:1.2rem;">
							<a v-if="lable.used" :style="clickLable[i][j]?'background-color: #ff4f00;color: #f8f0c1;display:inline-block;padding:0.4rem 0.4rem 0.4rem 0.4rem;':'display:inline-block;padding:0.4rem 0.4rem 0.4rem 0.4rem;'" v-for="lable,j in item.specValues" @click="clickFormat(item.name,lable.name,i,j,$event)">{{lable.name}}</a>
						</div>
					</div>
				</div>
				<!--end 规格选择-->

				<!--3.数量选择-->
				<div class="product_bar_sum">
					<label>数量:&nbsp;&nbsp;</label>
					<label class="product_bar_sum_option">
				<span id="reduce">-</span
				><span id="sum" class="sum_option">0</span
				><span id="add">+</span>
			</label>
				</div>
				<!--end 3.数量选择-->

				<!--4.电话留言-->
				<!-- <div class="product_bar_phone">
					<label>咨询电话：</label>
					<label style="color: #49f990;text-decoration: underline;">400-888-8888</label>
				</div> -->
				<!--end 4.电话留言-->

				<!--5.图文描述-->
				<!-- <div class="product_bar_img">
					<div><img src="/static/images/shop/img/productimg.png" style="width: 6rem;height: 6rem;" /></div>
					<div>健康大药房</div>
				</div>
				end 5.图文描述

				<div class="product_bar_ask">
					<div>
						<span>咨询</span>
						<span>进入店铺</span>
					</div>
				</div> -->
			</div>
			<!--商品条目-->

		</div>
		<!--end 一。商品块-->
	
		<!--二.详情块-->
		<div id="div_describe_block">
			<!--图文详情-->
			<div id="div_table" class="table_data">
				<!-- <div class="table_bar">
					
					<img  src=""> 
				</div>	 -->			
				<!--注：待插入图片-->
				<div style="width: 100%;text-align: center;">
					<img src="${goodsSkuPO.details }" style="width: 90%;margin-top: 1rem;" />
				</div>
				<!--end-->

				
			</div>

			<!--end 图文详情-->

			<!--说明书-->
			<div id="div_describe" style="display: none;width: 100%;">
				<div style="width: 100%;height: 7rem;"></div>
				<img src="${goodsSkuPO.Instructions }" style="width: 90%;margin-top: 1rem;" />				
			</div>
			<!--end 说明书-->
		</div>
		<!--end 二.详情块-->

		<style>
			.div_comment_block {
				margin: 0;
				padding: 0;
			}
			
			.comment_block {
				padding: 1rem 1rem 1rem 1rem;
				background-color: white;
				margin-bottom: 1rem;
			}
			
			.comment_title {
				display: flex;
				justify-content: flex-start;
				align-items: center;
				padding-bottom: 0.7rem;
			}
			
			.comment_title img {
				width: 1rem;
				height: 1rem;
			}
			
			.comment_tip {
				padding: 0.3rem 0 0.3rem 0;
			}
			
			.comment_text {}
			
			.comment_img img {
				width: 32%;
				margin-right: 0.2%;
				/*border:1px solid red;*/
			}
		</style>
		<!--三，评论块-->
		<div id="div_comment_block" style="display: none;">

			<!--评论-->
			<c:forEach items="${goodsSkuPO.evaluate}" var="eval">
				<div class="comment_block" >

					<div class="comment_title">
						<img src="/static/images/shop/img/mine_on.png" />
						<label>&nbsp;&nbsp;${eval.userName }&nbsp;&nbsp;</label>
						<img src="/static/images/shop/img/star_none.png" />
					</div>
					<div class="comment_tip">${eval.createTime }</div>
					<div class="comment_text">${eval.content }</div>
					<div class="comment_img">
						${eval.pictures}
					</div>					

				</div>
			</c:forEach>
			
			<!--评论-->
			
		</div>
		<!--end 评论块-->
		
		<!--背景块-->
		<ul style="width: 100%;height: 5rem;z-index: -1;"></ul>
		<!--end 背景块-->
		<ul class="nav" style="z-index:20">
			<li class="nav_li_icon"><img src="/static/images/shop/img/question.png" style="width: 1.5rem;height: 1.5rem;" />
				<div class="nav_li_icon_div">客服</div>
			</li>
			<li class="nav_li_icon" onclick="findCart()"><img src="/static/images/shop/img/shop_submit.png" style="width: 1.5rem;height: 1.5rem;" />
				<div  class="nav_li_icon_div"><a href=#>购物车</a></div>
			</li>
			<li class="nav_li_button" onclick="addCart()" style="z-index:20">加入购物车</li>
			<li class="nav_li_button nav_li_button_submit" onclick="purchase()" style="z-index:20">立即购买</li>
		</ul>

	</body>

	<!-- <script type="text/javascript" src="/staticp/js/data_sku.js"></script> -->
	<!--数组去重-->
	<script>
		function myuniq(array) {
			var temp = []; //一个新的临时数组
			for(var i = 0; i < array.length; i++) {
				if(temp.indexOf(array[i]) == -1) {
					temp.push(array[i]);
				}
			}
			return temp;
		}
		
		var data_new = ${goodsSkuPO};
		
		
		function backOff() {
			history.go(-1);
		}
		
		
		function addCart(){
			var num = $("#sum").html();
			if(num==0){
				layer.msg("请选择购买商品数量", {offset: 350});
	            return;
			}
			var itemId = format.$data.obj.id;
			var url = '/cart/addItemToCartList';   					
			var data = {
				"num":num,
				"itemId":itemId
			};
			http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){		            	               
                   if(result.success){
                   	layer.msg("加入购物车成功", {offset: 350});
                   }
               
	        });
		};
		
		 function purchase(){
			 
			var num = $("#sum").html();
			if(num==0){
				layer.msg("请选择购买商品数量", {offset: 350});
	            return;
			}
			var itemId =  format.$data.obj.id;		
			
			window.location = "/shop/purchasePage?num="+num+"&itemId="+itemId;						
			
		}
		 
		function findCart(){
			window.location = "/cart/findCartList";	
		}
	</script>
	<!--end 数组去重-->
	<script>
		//规格切换
		var format = new Vue({
			el: '#format',
			data: {
				//选中的数据
				//对应的点击数组true。false，提交时，根据true，false查看金额				
				obj: {},
				dataStr: '',
				dataLable: [],
				clickLable: [],
				obj_sku: [],
				obj_sku_title: [],
				goods:{}
			},
			mounted: function() {
				//初始对象，需要修改
				var skuId = ${skuId};
				for(var i = 0; i < data_new.sku.length; i++) {
					if(data_new.sku[i].id == skuId){
						this.obj = data_new.sku[i];
					}
				}
					
				/* this.obj = data_new.sku[0]; */
				this.obj_sku = data_new.sku;
				this.obj_sku_title = JSON.parse(data_new.specsFormat);
				this.goods = data_new;
				var dataLable = [];
				var arrClickLable = [];
				
				var defa = 0;
				
				if(typeof(this.obj.group_spec) != 'undefined' ){
					var spe = this.obj.group_spec.split(":");
					for(var i = 0; i < this.obj_sku_title.length; i++) {
						var temp = spe[0].substr(1);
						if(this.obj_sku_title[i].name==temp){
							for(var j = 0; j < this.obj_sku_title[i].specValues.length; j++) {
								if(this.obj_sku_title[i].specValues[j].name==spe[1]){
									defa = j;
								}
							}
						}
						
					}
					
					//层数
					for(var i = 0; i < this.obj_sku_title.length; i++) {
						var temp = [];
						var str = this.obj_sku_title[i].name + ':';
						var sum = 0;
						//套餐数
						//默认选择第一个
						for(var j = 0; j < this.obj_sku_title[i].specValues.length; j++) {
							temp.push(false);
							//即有显示的首个标签
							if(this.obj_sku_title[i].specValues[j].used && sum==0 && j==defa) {
								str += this.obj_sku_title[i].specValues[j].name;
								dataLable.push(str); 
								temp[j]=true; 
								sum++; 
							}else{
								temp[j] =false;
							}
							
						}
						
						//初始化时，空串判断
						if(this.obj_sku_title[i].usedNum<=0){
							dataLable.push('');
						}
						arrClickLable.push(temp);
						
					}
					//拼接字符串
					var str = '';
					for(var i = 0; i < dataLable.length; i++) {
						
						if(dataLable[i]!=''){
							str += '$' + dataLable[i];
							if(i != dataLable.length - 1) {
								str += ',';
							}
						}
					
					}
					//保存获取的数据和对应的点击下标
					str = str.trim();
					this.dataStr = str;
					this.dataLable = dataLable;
					this.clickLable = arrClickLable;
					
				}
				
				
				
				
				
			},
			methods: {
				clickFormat: function(lableName, lable, index, j, event) {
					var arr = this.formatLable;

					//每点一下，根据点击数据查询商品
					for(var b = 0; b < this.clickLable[index].length; b++) {
						Vue.set(this.clickLable[index], b, false);
					}
					Vue.set(this.clickLable[index], j, true);

					//获取规格和套餐名
					var str = lableName + ':' + lable;
					Vue.set(this.dataLable, index, str);

					//拼接字符串
					var str = '';
					for(var i = 0; i < this.dataLable.length; i++) {
						console.log(this.dataLable[i]);
						if(this.dataLable[i]!=''){
							str += '$' + this.dataLable[i].trim();
							if(i != this.dataLable.length - 1) {
								str += ',';
							}
						}
						
					}
					str = str.trim();
					this.dataStr = str;
					console.log(this.dataStr);
					
					for(var i = 0; i < this.obj_sku.length; i++) {
						if(this.dataStr == this.obj_sku[i].group_spec) {
							this.obj = this.obj_sku[i];
							console.log(this.dataStr);
							console.log(this.obj);
						}
					}
					
					//提取对象
					console.log(this.obj);
					
					if(this.obj.inventory < sum){
						$("#sum").html(this.obj.inventory);
						sum = this.obj.inventory;
					}

				}
				
				
			}
		});
	</script>

	<script>
		//当前数量
		var sum = 0;
		//注:最大数量,需要传入数据
		var limit = format.$data.obj.inventory;
		$('#reduce').click(function() {
			limit = format.$data.obj.inventory;
			if(sum == 0) {
				$("sum").html(0);
			}  else if(sum > limit){
				$("#sum").html(limit);
				sum = limit;
			}	else {			
				$("#sum").html(--sum);
			}

		});
		$('#add').click(function() {
			limit = format.$data.obj.inventory;			
			if(sum == limit || sum>limit) {
				$("#sum").html(limit);
				sum = limit;
			} else {
				$("#sum").html(++sum);
			}

		});
	</script>
	<script>
		//块级显示与隐藏
		var show = true;
		$('#option_show').click(function() {
			if(show == true) {
				$("#show_div").css('display', 'none');
			} else {
				$("#show_div").css('display', 'block');
			}
			show = !show;
		});
	</script>

	<script>
		//点击更换id
		var className = 'active';
		var laseClickId = '#li1';
		$('#li1').click(function() {
			$(laseClickId).removeClass(className);
			$(this).addClass(className);
			$('#div_table').css('display', 'block');
			$('#div_describe').css('display', 'none');
			laseClickId = '#li1';
		});
		$('#li2').click(function() {
			$(laseClickId).removeClass(className);
			$(this).addClass(className);
			$('#div_table').css('display', 'none');
			$('#div_describe').css('display', 'table');
			laseClickId = '#li2';
		});
	</script>

	<script>
		//商品和详情
		$('#button_product').click(function() {
			$('#div_describe_block').css('display', 'none');
			$('#div_describe_block2').css('display', 'none');
			$('#div_comment_block').css('display', 'none');
			$('#div_product_block').css('display', 'block');
		});
		$('#button_describe').click(function() {
			$('#div_product_block').css('display', 'none');
			$('#div_comment_block').css('display', 'none');
			$('#div_describe_block').css('display', 'block');
			$('#div_describe_block2').css('display', 'block');
			//点击了详情页，默认显示详情
			$('#div_table').css('display', 'block');
		});

		$('#button_commemt').click(function() {
			$('#div_product_block').css('display', 'none');
			$('#div_describe_block').css('display', 'none');
			$('#div_describe_block2').css('display', 'none');
			$('#div_comment_block').css('display', 'block');
		});
	</script>

	<script>
		//商品滑动
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isUc = u.indexOf('UCBrowser') > -1; //uc浏览器
		//var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		if(isAndroid && isUc) { /*注释5*/
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

	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script>
	
		var picS = ${goodsSkuPO.pictureUrl};
		//注:轮播图图片数据
		var vue = new Vue({
			el: '#app',
			data: {
				dataurl: picS
				
			}
		});
	</script>

</html>
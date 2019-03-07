<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title></title>

		<style>
			body {
				margin: 0;
				padding: 0;
			}
			
			#index_content {
				z-index: -1;
			}
		</style>
		<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent.css" />
		<link rel="stylesheet" href="/static/css/shop/css/slide.css" />
		<!--自适应js-->
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/js/jq.js"></script>
		<!-- <script type="text/javascript" src="/static/js/data.js"></script> -->
		<script type="text/javascript" src="/static/js/vue.js"></script>
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script src="../../static/plugins/layui/v2.1.2/layui.all.js""></script>
		<style>
			/*头部*/
			
			.header_shop {
				position: fixed;
				width: 100%;
				list-style-type: none;
				margin: 0;
				padding: 0;
				height: 1.5rem;
				padding: 1rem 0 1rem 0;
				border-bottom: 1px solid #dddddd;
				background-color: white;
			}
			
			.header_shop_bg {
				position: relative;
				width: 100%;
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
			/*横边框*/
			
			.content_title_ul {}
			
			.content_title_ul>li {
				float: left;
				width: 20%;
				text-align: center;
				padding: 0.8rem 0 0.8rem 0;
			}
			
			.content_title_ul>li:hover {
				border-bottom: 1px solid #53a260;
			}
			
			.content_div {
				/*position: relative;*/
			}
			/*侧边框*/
			
			.content_slide_ul {
				width: 10%;
			}
			
			.content_slide_ul>li {
				float: left;
				padding: 0.3rem 1.5rem 0.3rem 1.5rem;
			}
			
			.content_content_div {
				background-color: #efeef3;
			}
		</style>
		<script type="text/javascript">
			function backOff() {
				history.go(-1);
			}
		</script>
	</head>

	<body>
		<!--头选择项-->
		<ul class="header_shop" style="z-index: 1000;padding-top:1rem;position: fixed;">
			<li class="header_icon" onclick="backOff()"><img src="/static/images/shop/img/icon_return.png" style="width: 0.8rem;height: 1.3rem;margin-left: 1rem;" /></li>
			<li class="header_title" style="height: 1.2rem;">
				<label>全部分页</label>
			</li>
			<li class="header_icon" style="text-align: right;"></li>
		</ul>
		<!--end 头选择项-->
		<ul class="header_shop_bg"></ul>
		<!--<script>
			//一级:5
			console.log(data.length);
			//二级 8 ，4 ，6 ，6，11
			console.log(data[4].catalogTwos.length);
			//三级8:0-5 1-6....
			console.log(data[0].catalogTwos[0].catalogThree.length);
			//四级
			console.log(data[0].catalogTwos[0].catalogThree[0].catalogFours.length);
		</script>-->
		<div id="index_content">
			<!--横选项-->
			<ul class="mainbg main_nav header_shop" style="float: left;">
				<li v-for="item,cid1 in arr" @click="clickId1(cid1)"><span>{{item.cName}}</span></li>
			</ul>
			<!--end 横选项-->

			<nav style="float: left;z-index: -10;font-size: 1rem;width: 20%;">
				<ul class="li_slide" style="background-color: white; margin-top:37px;">
					<!--传入该li的id-->
					<li v-for="subItem,cid2 in arr[cId1].catalogTwos" @click="clickId2(cid2)" :style="selectId==cid2?'background-color:#efeef3;':''">{{subItem.cName1}}</li>
				</ul>
			</nav>
			<!--內容布局-->
			<section class="flexModal fixedLeft slide_conent" style="top:3rem;z-index: -10;float: left;width: 80%;">

				<article>
					<!--注:可复制的图片列表-->
					<!--json.data[0].catalogThree-->
					<div class="list_conent_div" v-for="sub_subItem in arr[cId1].catalogTwos[cId2].catalogThree">
						<!--标题-->
						<h3 style="text-align: left;padding-left: 2rem;">{{sub_subItem.cName2}}</h3>
						<!--end 标题-->

						<!--图片集合-->
						<ul class="main option_div">
							<li class="option_item" v-for="sub_sub_subItem,cid4 in sub_subItem.catalogFours">
								<div v-if="sub_sub_subItem.length != 0"><span style="background-image: url(/static/images/shop/img/icon_older.png);" @click="clickId3(sub_sub_subItem.id3)"></span><label>{{sub_sub_subItem.cName3}}</label></div>
							</li>
						</ul>
						<!--end 图片集合-->
					</div>
					<!--end 可复制的图片列表-->

				</article>

			</section>
			<!--end 内容布局-->
		</div>

	</body>

	<script>
		var index_content = new Vue({
			el: '#index_content',
			data: {
				arr: JSON.parse('${CatalogList}'),
				cId1: 0,
				cId2: 0,
				cId3: 0,
				cId4: 0,
				selectId:0
			},
			methods: {
				clickId1: function(cid1) {
					index_content.cId1 = cid1;
					console.log("横" + index_content.cId1);
				},
				clickId2: function(cid2) {
					this.selectId = cid2;
					index_content.cId2 = cid2;
					console.log("竖" + index_content.cId2);
				},
				clickId3: function(cid3) {
					index_content.cId3 = cid3;
					/* index_content.cId4 = cid4; */
					//点击图片跳转
					window.location = "/shop/getGoods?cid="+cid3;
				}
				
			}
		});
	</script>

</html>
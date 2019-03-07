<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1,maximum-scale=1, minimum-scale=1">
<title></title>
<script src="../../static/plugins/http.js"></script>
<script type="text/javascript" src="/static/js/mode.js"></script>
<script type="text/javascript" src="/static/js/vue.js"></script>
<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
<script src="../../static/plugins/layui/v2.1.2/layui.js"></script>
<script type="text/javascript" src="/static/js/jq.js"></script>
<link rel="stylesheet" href="../../static/plugins/layui/v2.1.2/css/layui.css" media="all">

<style>
/*头部*/
.header_shop {
	position: fixed;
	width: 100%;
	list-style-type: none;
	margin: 0;
	padding: 0;
	top: 0;
	height: 1.5rem;
	color: white;
	padding: 1rem 0 1rem 0;
	/*border-bottom: 1px solid white;*/
	background-color: #44c05e;
}

.header_shop_bg {
	position: relative;
	width: 100%;
	list-style-type: none;
	margin: 0;
	padding: 0;
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
}

.block_author_describe * {
	/*outline: 1px solid red;*/
	text-align: center;
	background-color: #63be5e;
}

.block_author_img {
	
}

.block_author_img img {
	margin-top:1rem;
	width: 8rem;
	height: 8rem;
	border-radius:4rem;
}
/*账号描述*/
.block_author_describe_bar {
	font-size: 1.2rem;
	text-align: center;
	padding: 0.1rem 1rem 1rem 1rem;
}
/*个人信息表单*/
.block_author_form_bar {
	font-size: 1rem;
	display: flex;
	justify-content: space-between;
	padding: 1rem 1rem 1rem 1rem;
	border-bottom: 1px solid #dddddd;
}

.block_author_form_bar_left {
	display: flex;
	align-items: center;
}

.block_author_form_bar_right {
	width: 0.7rem;
	height: 1.2rem;
}

.block_btn {
	position: fixed;
	bottom: 0.5rem;
	width: 100%;
}

.block_btn_exit {
	color: white;
	text-align: center;
	border-radius: 0.5rem;
	padding: 1rem 0 1rem 0;
	margin-left: 1rem;
	margin-right: 1rem;
	background-color: #f3ad4d;
}
*{
	font-size:1.2rem;
}
</style>

<script>
	$('#mine_describe').click(function() {
		/* window.location = 'mine_desribe.html'; */
	});

	function showAddress() {
		window.location = '/shop/showAddress';
	};

	function backOff() {
		history.go(-1);
	};

	function out() {
		layer.confirm("确定退出？", function(index) {
			window.location = '/shop/userOut';
		});
	};
	
	/*图片上传*/
    var url = '/commonControl/uploadFile/img.do';	
    layui.use('upload', function(){
    	  var upload = layui.upload;
    	   
    	  //执行实例
    	  var uploadInst = upload.render({
    	    elem: '#uploadPhone' //绑定元素
    	    ,url: url
    	    ,field:"path"
    	    ,done: function(res){
    	    	if (res.success) {
					document.getElementById("phont").src = res.obj;	
					
					var url = '/shop/imageChange?img='+res.obj;				
					http.ajax.get(true, true, url, null, http.ajax.CONTENT_TYPE_1,function(result) {})
						
    	    	}
    	    }
    	    ,error: function(){
    	      //请求异常回调
    	    },
    	    accept:'file'
    	  });
    	});
	
	
	
	
</script>
<script>
		function clickImg(){
			$('#uploadPhone').trigger('click');
		}
</script>
</head>

<body>
	<!--头选择项-->
	<ul class="header_shop" style="z-index: 10">
		<li class="header_icon" onclick="backOff()"><img
			src="/static/images/shop/img/left_white.png"
			style="width: 1.3rem; height: 1.3rem; margin-left: 1rem;" /></li>
		<li class="header_title" style="height: 1.2rem;"><label>个人中心</label>
		</li>
		<li class="header_icon" style="text-align: right;"></li>
	</ul>
	<!--end 头选择项-->
	<ul class="header_shop_bg" style="z-index: -1"></ul>
		
	<div class="block_author_describe">
		<div class="block_author_img">
			<c:if test="${not empty userInfo.photo }">
				<img id="phont" onclick="clickImg()" src="${userInfo.photo }" />
			</c:if>
			<c:if test="${empty userInfo.photo }">
				<img id="phont" onclick="clickImg()" src="/static/images/shop/img/author.png" />
			</c:if>	
		</div>
		<div class="set-head">
            <button type="button" class="layui-btn" name="path" id="uploadPhone" style="display:none;">
 				<i class="layui-icon" name="path">&#xe67c;</i>更换头像
			</button>
         </div>
		             
	
		<div class="block_author_describe_bar" style="padding-top:0.8rem;">
			<div>${userInfo.userName }</div>
		</div>
		<!-- <div class="block_author_describe_bar">
				<div>账号:</div>
			</div> -->
	</div>

	<div class="block_author_form" style="z-index: -1">
		<!--个人信息-->
		<div id="mine_describe" class="block_author_form_bar"
			style="z-index: -1">
			<div class="block_author_form_bar_left">
				<img src="/static/images/shop/img/mine_on.png"
					style="width: 1.2rem; height: 1.2rem;" />
				<div style="margin-left: 1rem;">个人信息</div>
			</div>
		</div>
	</div>
	<!--end 个人信息-->
	<!--地址管理-->
	<div onclick="showAddress()" id="address" class="block_author_form_bar">
		<div class="block_author_form_bar_left">
			<img src="/static/images/shop/img/pos.png"
				style="width: 1.2rem; height: 1.2rem;" />
			<div style="margin-left: 1rem;">地址管理</div>
		</div>
		<img class="block_author_form_bar_right"
			src="/static/images/shop/img/right.png" />
	</div>
	<!--end 地址管理-->
	</div>


	<div class="block_btn" style="position: fixed; bottom: 5rem;">
		<div class="block_btn_exit" onclick="out()">退出登录</div>
	</div>

	<!--底部导航-->
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
</style>
	<ul style="height: 4.5rem;"></ul>
	<ul id="position">
		<li><a @click="linkItem('index1')"><img id="index1"
				onclick="changeImg('index1')" src="/static/images/shop/img/index.png"> <font>首頁</font>
		</a></li>
		<li><a @click="linkItem('index2')"><img id="index2"
				onclick="changeImg('index2')" src="/static/images/shop/img/order.png"> <font>订单</font>
		</a></li>
		<li><a @click="linkItem('index4')"><img id="index4"
				onclick="changeImg('index4')" src="/static/images/shop/img/shop.png"> <font>购物车</font>
		</a></li>
		<li><a @click="linkItem('index5')"><img id="index5"
				onclick="changeImg('index5')" src="/static/images/shop/img/mine_on.png"> <font>个人</font>
		</a></li>
	</ul>
	<!--end 底部导航-->
	<script>
		var postion_id = new Vue({
			el:'#position',
			methods:{
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
				}
			}
		});
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
	
</body>


</html>
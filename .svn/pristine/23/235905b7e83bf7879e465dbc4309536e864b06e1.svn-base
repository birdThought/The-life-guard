<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title></title>
		<style>
			body{
				margin: 0;
				padding: 0;
			}
		</style>
		<link rel="stylesheet" href="/static/css/shop/css/comman.css" />
		<link rel="stylesheet" href="/static/css/shop/css/content.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent2.css" />
		<link rel="stylesheet" href="/static/css/shop/css/subcontent_plus.css" />
		<link rel="stylesheet" href="/static/semantic/semantic.css" />
		<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
		<!--自适应js-->
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/js/jq.js" ></script>
		<script type="text/javascript">
				function backOff() {
					history.go(-1);
				};
				
				function ChoiceSkuPage(id){
					window.location = "/shop/detailsPage?goodsId="+ id;
				}
				
				window.onload=function(){
					if(${searchPo.skuList}.length==0){
						
						layer.msg("没搜索到对应的商品", {offset: 350}); 
                		setTimeout(function () {                                              	 
                			history.go(-1);
                       	},1000)	
						
					}
				}
				
				
		</script>
	</head>
	<body>
		
		<!--标题-->
		<div class="mainbg main_title" style="position: relative;border-bottom:1px solid #dedede;">
			<div>
				<div  class="main_title_img" onclick="backOff()"><img src="/static/images/shop/img/icon_return.png" style="width: 0.7rem;height: 1.2rem;"/></div>
				<div class="main_title_text">搜索结果</div>
				<div>&nbsp;</div>
			</div>
		</div>
		<!--end 标题-->
		
		<!--选项条-->
		<div class="mainbg main_opselect">
			
			<!-- <div>
				<div>默认</div>
				<div><img src="./img/up.png"/><img src="./img/down.png"/></div>
			</div>
			
			<div>
				<div>价格</div>
				<div><img src="./img/up.png"/><img src="./img/down.png"/></div>
			</div>
			
			<div>
				<div>筛选</div>
				<div><img src="img/select.png" style="width: 1rem;height: 1rem;"/></div>
			</div> -->
			
		</div>
		<!--end 选项条-->
		
		<!--商品条目-->
		<c:forEach items="${searchPo.skuList }" var="item">
			<div class="product_list_flex" onclick="ChoiceSkuPage(${item.id})">
				<c:forEach items="${item.pictureUrls }" var="pic">			
					<c:if test="${pic.masterGraph == 0 }">
						<div class="product_list_flex_left">
							<img src="${pic.pictureUrl }"/>				
						</div>
					</c:if>
				</c:forEach>
				<div class="product_list_flex_right">
					<h3 class="product_list_flex_title">${item.goodName }<h3>
					<label class="product_list_flex_price">￥${item.favorablePrice }</label>
				</div>
			
			</div>
		</c:forEach>
		
		<!--end 商品条目-->
		
	</body>
</html>

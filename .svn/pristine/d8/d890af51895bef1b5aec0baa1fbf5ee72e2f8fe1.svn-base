<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type = "layer,vue,jquery"></t:base>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/css/paper/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/paper/questionnaire.css">
    <script type="text/javascript" src="/static/plugins/highcharts/highcharts.js"></script>
    <title>调查问卷_中医体质分析</title>
    <style>
    	[v-cloak] {
    		display: none;
    	}
    </style>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<header id="header" class="header">
		<div class="container">
			<nav class="navbar navbar-inverse" role="navigation">
	        	<div class="navbar-header">
		            <button type="button" id="nav-toggle" class="navbar-toggle" data-toggle="collapse" data-target="#main-nav">
			            <span class="sr-only">切换导航</span>
			            <span class="icon-bar"></span>
			            <span class="icon-bar"></span>
			            <span class="icon-bar"></span>
		            </button>
		            <a href="#" class="navbar-brand scroll-top logo">
		            	<img src="/static/images/index/logo.png" class="img-responsive" alt="Responsive image">
		            </a>
	        	</div>
	        	<!--/.navbar-header-->
		        <div id="main-nav" class="collapse navbar-collapse">
		            <ul class="nav navbar-nav" id="mainNav">
			            <li class="active"><a href="#home" class="scroll-link">首页</a></li>
						<li>
							<a href="#" class="scroll-link">APP介绍</a>
							<ul>
							    <li><a href="#">机构端</a></li>
							    <li><a href="#">用户端</a></li>
							</ul>
						</li>
						<li>
							<a href="#" class="scroll-link">产品与服务</a>
							<ul>
							    <li><a href="#">机构服务</a></li>
							    <li><a href="#">健康问诊</a></li>
							    <li><a href="#">智能设备</a></li>
							    <li><a href="#">健康咨询</a></li>
							</ul>
						</li>
						<li><a href="#" class="scroll-link">商家入住</a></li>
						<li>
							<a href="#" class="scroll-link">关于我们</a>
							<ul>
							    <li><a href="#">公司介绍</a></li>
							    <li><a href="#">帮助中心</a></li>
							    <li><a href="#">联系我们</a></li>
							</ul>
						</li>
						<li><a href="#" class="scroll-link">注册</a></li> 
						<li><a href="#" class="scroll-link">登录</a></li> 
		            </ul>
		        </div>
		        <!--/.navbar-collapse-->
		    </nav>
		    <!--/.navbar-->
		</div>
		<!--/.container-->
	</header><!-- /header -->
	<section class="item-content physicalAnalyzeModel">
		<div class="container">
			<div class="item-title">
				<h3>体质分析</h3>
			</div>
			<%@include file="physicalAnalyzeCommon.jsp"%>
			<%-- <div class="item-content-border">
				<div class="comment">
					<h4 style="color:#3db74d;">评论结果:</h4>
					<p style="text-indent: 2em"> 您的体质是{{physicalName}}，
						<template v-if = "physicalNames != null">兼有
							<template v-for = "(p, index) in physicalNames">
								<template v-if = "p != physicalName">{{p}}
									<span v-if = "index < physicalNames.length - 1">、</span>
								</template>
							</template>等多种体质
						</template>
					</p>
					<div id="container" style="width:100%;margin:0 auto;border-bottom: 2px solid #ddd"></div>
				</div>
				<div class="comment-content">
					<h4 style="color:#fe933d;">{{physicalName}}病因</h4>
					<ul>
					    <li>
					    	{{cause}}
					    </li>
					</ul>
				</div>
				<div class="comment-content">
					<h4 style="color:#fe933d;">{{physicalName}}症状</h4>
					<ul>
					    <li>
					    	{{symptoms}}
					    </li>
					</ul>
				</div>
				<div class="comment-content">
					<h4 style="color:#fe933d;">{{physicalName}}临床表现</h4>
					<ul>
					    <li>
					    	{{manifestations}}
					    </li>
					</ul>
				</div>
				<div class="comment-content">
					<h4 style="color:#fe933d;">{{physicalName}}易感疾病</h4>
					<ul>
					    <li>
					    	{{susceptibleDisease}}
					    </li>
					</ul>
				</div>
				<div class="comment-content">
					<h4 style="color:#fe933d;">{{physicalName}}建议</h4>
					<ul>
					    <li>
					    	{{suggestion}}
					    </li>
					</ul>
				</div>
				<p class="text-center"><button class="btn" @click = 'again'>重新评估</button></p>
			</div> --%>		
		</div>
	</section>
	<footer id="footer" class="footer"> 
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-4 col-xs-12">
					<ul>
					    <li><h5>广州通众电气实业有限公司&copy;版权所有</h5></li>
					    <li><a href="#">粤ICP备13019866号-3</a></li>
					    <li>400-026-1003</li>
					</ul>
				</div>
				<div class="col-md-5 col-sm-4">
					<div class="row clearMargin">
						<div class="col-xs-6">
							<h4>关于我们</h4>
							<ul>
							    <li><a href="#">关于健康管理平台</a></li>
							    <li><a href="#">官方网站</a></li>
							    <li><a href="#">加入我们</a></li>
							    <li><a href="#">联系我们</a></li>
							</ul>
						</div>
						<div class="col-xs-6">
							<h4>免费下载APP</h4>
							<ul>
							    <li>
							    	<a href="#">IOS下载</a>
							    </li>
							    <li>
							    	<a href="#">IOS下载</a>
							    </li>
							</ul>
						</div>
					</div>
					
				</div>
				<div class="col-md-3 col-sm-4">
					<a href="#" class="item-code">
						<img src="/static/images/index/QR_Code.png" alt="">
						<h4>生命守护公众号</h4>
					</a>
				</div>
			</div>
		</div>
	</footer>

	<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="/static/com/lifeshs/paper/physicalPaper.js"></script>
	<script>
		vueModel.getPhysicalAnalyze().results = '${physicalStandard}' == '[]' ? '' : JSON.parse('${physicalStandard}');
		vueModel.getPhysicalAnalyze().physicalScore = ${scoreArr};
		vueModel.getPhysicalAnalyze().makeChart();
	</script>

	<script>
		
		
	</script>

</body>
</html>
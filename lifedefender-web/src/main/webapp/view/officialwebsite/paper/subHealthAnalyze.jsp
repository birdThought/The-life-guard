<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type = "layer,vue,jquery"></t:base>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/questionnaire.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <title>调查问卷_亚健康分析</title>
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
<%@include file="/context/mainHeader_1.jsp"%>
	<section class="item-content subHealthAnalyzeModel" >
		<div class="container">
			<div class="item-title">
				<h3>亚健康分析</h3>
			</div>
			<div class="item-content-border">
				<div class="comment">
					<h4 style="color:#3db74d;">评估结果:</h4>
					<input type="hidden" class = "score" :score = "score" points = "${points}">
					<p style="text-indent: 2em;">您此次的亚健康评估得分: <em v-cloak>{{score}}</em>分</p>
					<div class="progress">
						<div class="progress-bar progress-bar-info" style="width: 25%">
							<span class="sr-only">25% Complete (infor)</span>
						</div>
						<div v-if = "(score < 21)" class="progress-bar progress-bar-success" style="width: 25%">
							<span class="sr-only">25% Complete (success)</span>
						</div>
						<div v-if = "(score < 16)" class="progress-bar progress-bar-warning" style="width: 25%">
							<span class="sr-only">25% Complete (warning)</span>
						</div>
						<div v-if = "(score < 10)" class="progress-bar progress-bar-danger" style="width: 25%">
							<span>25% Complete (danger)</span>
						</div>
					</div>
					<p class="text-center" v-cloak>{{basicDes}}</p>
				</div>
				<div class="comment-content" v-if = "suggestion != null" v-cloak>
					<h4 style="color:#fe933d;">健康小贴士</h4>
					<ul v-for = "(s, index) in suggestion">
						<li>
							<template v-if = "suggestion.length > 1">{{index + 1}}.</template>{{s}}
						</li>
					</ul>
				</div>
				<p class="text-center"><button class="btn" @click = "again">重新评估</button></p>
			</div>
		</div>
	</section>
	<%@include file="/context/mainFooter_1.jsp"%>
	<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="/static/officialwebsite/index/paper/subHealth.js"></script>
	<script>
		$(function () {
            vueModel.getSubHealthAnalyze().results = '${subHealthStandard}' == '[]' ? '' : JSON.parse('${subHealthStandard}');
            vueModel.getSubHealthAnalyze().score = ${score};
            if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
                $('#header').css('display', 'none');
                $('#footer').css('display', 'none');
                $('.extension').css('display', 'none');
            }
        })
	</script>
</body>
</html>
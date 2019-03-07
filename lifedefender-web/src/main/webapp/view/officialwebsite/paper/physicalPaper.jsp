<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type="jquery,vue"></t:base>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
	<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/magic-check.css">
	<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/questionnaire.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <title>调查问卷_中医</title>
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
	<section class="item-content physicalModel">
		<div class="container">
			<div>
				<div class="item-title">
					<h3>中医体质评估问卷</h3>
				</div>
				<p class="item-content-border">
					中医体质辩证是根据中华中医药学会标准来通过测试题目来判断体质现象，然后为诊断和治疗疾病提供重要依据。虽然题目比较多，但是准确性高，请耐心完成。
				</p>
			</div>
			<div class="item-quetion">
				<h4 class="item-sub" v-cloak>第{{currentTopicNumber}}题,共{{totalTopic}}道题</h4>
				<form>
					<h4 v-cloak>{{currentTopicNumber}}. {{currentTopicName}}</h4>
					<ul>
						<li v-cloak v-for = "(o, index) in paperOptions">
							<input type="radio" name="10" :id="checkedCss(index)" @click = "nextTopic(o.score, currentTopicType, special)" class="magic-radio">
							<label :for="checkedCss(index)">{{o.name}}</label>
						</li>
					</ul>
				</form>
				<p class="text-center">
					<button v-cloak v-if = "gender" @click = "skipTopic()" class="btn" style = "background-color: chocolate;">跳过</button>
					<button v-cloak v-if = "(currentTopicNumber > 1)" @click = "preTopic()" class="btn">上一题</button>
				</p>
			</div>
		</div>
	</section>
<%@include file="/context/mainFooter_1.jsp"%>
	<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="/static/officialwebsite/index/paper/physicalPaper.js"></script>
	<script>
		$(function () {
			vueModel.getPhysical().paper = '${paper}' == '[]' ? '' : JSON.parse('${paper}');
			vueModel.getPhysical().paperOptions = '${paperOption}' == '[]' ? '' : JSON.parse('${paperOption}');

            if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
                $('#header').css('display', 'none');
                $('#footer').css('display', 'none');
                $('.item-quetion form').css('min-height', '135px');
                $('.item-quetion').css('margin-top', '0px');
                $('.item-sub').css('top', '-10%');
                $('.extension').css('display', 'none');
            }
		});
	</script>

</body>
</html>
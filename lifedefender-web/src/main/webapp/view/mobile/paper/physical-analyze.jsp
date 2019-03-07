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
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css">
    <%--<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/magic-check.css">--%>
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/questionnaire.css">
    <%--<link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">--%>

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
<%--<%@include file="/context/mainHeader_1.jsp"%>--%>
	<section class="item-content vue-content">
		<div class="container">
			<div class="item-title">
				<h3>体质分析</h3>
			</div>
            <div class="item-content-border">
                <div class="comment">
                    <h4 style="color:#3db74d;">评论结果:</h4>
                    <input type="hidden" class = "score" :physicalName = "physicalName" :score = "${scoreArr}">
                    <p style="text-indent: 2em" v-cloak> 您的体质是{{physicalName}}

                    </p>
                    <div v-cloak id="container" style="width:100%;margin:0 auto;border-bottom: 2px solid #ddd"></div>
                </div>
                <div class="comment-content">
                    <h4 style="color:#fe933d;" v-cloak>{{physicalName}}病因</h4>
                    <ul v-cloak>
                        <li>
                            {{cause}}
                        </li>
                    </ul>
                </div>
                <div class="comment-content">
                    <h4 style="color:#fe933d;" v-cloak>{{physicalName}}症状</h4>
                    <ul v-cloak v-for = "(s, index) in symptoms">
                        <li>
                            <template v-if = "symptoms.length > 1">{{index + 1}}.</template>{{s}}
                        </li>
                    </ul>
                </div>
                <div class="comment-content">
                    <h4 style="color:#fe933d;" v-cloak>{{physicalName}}临床表现</h4>
                    <ul v-cloak>
                        <li>
                            {{manifestations}}
                        </li>
                    </ul>
                </div>
                <div class="comment-content">
                    <h4 style="color:#fe933d;" v-cloak>{{physicalName}}易感疾病</h4>
                    <ul v-cloak>
                        <li>
                            {{susceptibleDisease}}
                        </li>
                    </ul>
                </div>
                <div class="comment-content">
                    <h4 style="color:#fe933d;" v-cloak>{{physicalName}}建议</h4>
                    <ul v-cloak>
                        <li>
                            {{suggestion}}
                        </li>
                    </ul>
                </div>
                <p class="text-center"><button class="btn" @click = 'again' v-cloak>重新评估</button></p>
            </div>
		</div>
	</section>
<%--<%@include file="/context/mainFooter_1.jsp"%>--%>
<script type="text/javascript" src="/static/plugins/highcharts/highcharts.js"></script>
<script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src = "/static/mobile/paper/js/physical.js"></script>
<script>
$(function () {
	physical.vm.results = '${physicalStandard}' == '[]' ? '' : JSON.parse('${physicalStandard}');
    physical.vm.physicalScore = ${scoreArr};
    physical.vm.makeChart();
	
	/*if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
		$('#header').css('display', 'none');
		$('#footer').css('display', 'none');
		$('.item-quetion form').css('min-height', '135px');
        $('.extension').css('display', 'none');
	}*/
})
</script>
</body>
</html>
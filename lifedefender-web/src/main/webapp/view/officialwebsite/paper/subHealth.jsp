<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="layer,jquery,vue"></t:base>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/adapter/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/magic-check.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/questionnaire.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <!-- <script src="jquery/jquery-2.1.1.min.js"></script> -->
    <title>调查问卷_亚健康</title>
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
    <section class="item-content subHealthModel">
        <div class="container">
            <div>
                <div class="item-title">
                    <h3>亚健康评估问卷</h3>
                </div>
                <p class="item-content-border" v-cloak>
                    亚健康是由于生活方式不合理而渐渐形成对身体有害无益的一种状态，他介于健康与疾病之间。亚健康会使你在不知不觉中进入疾病状态，直接影响工作效率。你是否正处于亚健康状态呢?根据下面测试问卷的提示，选择适合自己的答案，你就能知道结果了。
                </p>
            </div>
            <div class="item-quetion">
                <h4 class="item-sub" v-cloak>第{{currentTopicNumber}}题,共{{totalTopic}}道题</h4>
                <form>
                    <h4 v-cloak>{{currentTopicNumber}}. {{currentTopicName}}</h4>
                    <ul class = "options">
                        <li v-cloak v-for = "(o, index) in currentOption">
                            <input type="radio" name="2" :id="checkedCss(index)" @click = "nextTopic(o.score, o.topicType)" class="magic-radio">
                            <label :for="checkedCss(index)">{{o.name}}</label>
                        </li>
                    </ul>
                </form>
                <p class="text-center"><button v-if = "(currentTopicNumber > 1)" @click = "preTopic()" class="btn" v-cloak>上一题</button></p>
            </div>
        </div>
    </section>
<%@include file="/context/mainFooter_1.jsp"%>
<script src="/static/officialwebsite/index/paper/subHealth.js"></script>
<script>
    $(function () {
        vueModel.getSubHealth().questionnaire = '${questionnaire}' == '[]' ? '' : JSON.parse('${questionnaire}');
        <%--vueModel.getSubHealth().questionnaireOptions = '${questionnaireOption}' == '[]' ? '' : JSON.parse('${questionnaireOption}');--%>

        //App页面适配
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
<%--
  @Description: app中医体质问卷
  @Author: wenxian.cai
  @Date: 2017/7/18 9:59
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,vue"></t:base>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-title" content="生命守护">
    <meta name="format-detection" content="telephone=yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <link rel="stylesheet" href="/static/mobile/paper/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/mobile/paper/css/health.css">
    <title>中医体质评估</title>
</head>
<body>
<%--<header>
    <div class="container">
        <i></i>
        <p>中医体质评估</p>
    </div>
</header>--%>
<article class="vue-content">
    <div class="progress">
        <div class="step"></div>
    </div>
    <p class="count" >{{currentTopicNumber}}<em>/</em>{{totalTopic}}
        <i v-if = "gender" @click = "skipTopic()">跳过</i></p>
    <div class="subject  clearfix" >
        <p style="height: 40px" v-cloak>{{currentTopicNumber}}. {{currentTopicName}}</p>
        <ul class="answer">
            <li v-cloak v-for = "(o, index) in paperOptions" @click = "nextTopic(o.score, currentTopicType, special)">
                <span >{{o.name}}</span>
                <i>{{getOptionNumber(index)}}</i>
            </li>
        </ul>
    </div>
    <div class="refer-btn" v-cloak v-if = "(currentTopicNumber > 1)" @click = "preTopic()">
        <span class="answer-refer" style="color: white;">上一题</span>
    </div>
</article>
<script src = "/static/mobile/paper/js/physical.js"></script>
<script>
    physical.init();
    physical.vm.paper = '${paper}' == '[]' ? '' : JSON.parse('${paper}');
    physical.vm.paperOptions = '${paperOption}' == '[]' ? '' : JSON.parse('${paperOption}');
</script>
</body>
</html>
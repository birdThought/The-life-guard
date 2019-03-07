<%--
 * 中风风险测试
 * date: 2017/8/7 14:44
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
    <link rel="stylesheet" href="/static/mobile/paper/css/reset.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/mobile/paper/css/health.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/mobile/paper/css/stroke.css?v=2.2.0">
    <title>中风风险测试</title>
</head>
<body>
<%--<header>
    <div class="container">
        <i></i>
        <p>中风风险测试</p>
    </div>
</header>--%>
<article class="vue-content">
    <div class="progress">
        <div class="step"></div>
    </div>
    <p class="count">{{currentTopicNumber}}<em>/</em>{{totalTopic}}</p>
    <div class="subject  clearfix">
        <p>{{currentTopicNumber}}.{{currentTopicName}}</p>
        <ul class="answer">
            <li v-cloak v-for = "(o, index) in paperOptions" @click = "nextTopic(o.score, currentTopicType)">
                <span >{{o.name}}</span>
                <i>{{getOptionNumber(index)}}</i>
            </li>
        </ul>
    </div>
    <div class="refer-btn" v-cloak v-if = "(currentTopicNumber > 1)" @click = "preTopic()">
        <span class="answer-refer" style="color: white">上一题</span>
    </div>
</article>
<script src = "/static/mobile/paper/js/stroke.js?v=2.2.0"></script>
<script>
    stroke.init();
    stroke.vm.paper = '${paper}' == '[]' ? '' : JSON.parse('${paper}');
    stroke.vm.paperOptions = '${paperOption}' == '[]' ? '' : JSON.parse('${paperOption}');
</script>
</body>
</html>
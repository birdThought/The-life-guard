<%--
 * 中风风险测试分析
 * date: 2017/8/7 17:11
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
    <link rel="stylesheet" type="text/css" href="/static/mobile/paper/css/stroke-analyze.css?v=2.2.0">
    <title>中风风险测试</title>
</head>
<body>

<article class="vue-content">
    <div class="gradient"></div>
    <div class="main-content" v-if = "result != null">
        <h3 class="tit">风险提示：<em>{{result.basicDescription}}</em></h3>
        <div class="risk-level">
            <ul class="level-color clearfix">
                <li></li>
                <li></li>
                <li></li>
            </ul>
            <ol class="list-level clearfix">
                <li>低危</li>
                <li>中危</li>
                <li>高危</li>
            </ol>
            <i class="point"></i>
        </div>
        <p class="conclusion">存在{{score}}项上诉危险因素</p>
        <div class="advice">
            <div class="advice-tip clearfix">
                <div class="cut cut-1"><span>///</span></div>
                <div class="text">建议</div>
                <div class="cut cut-2"><span>///</span></div>
            </div>
            <div class="advice-details">
                <%--<h3>恭喜您！请继续保持良好的生活习惯。</h3>--%>
                <p>{{result.suggestion}}</p>
            </div>
        </div>
        <div class="retest">
            <button type="button" @click = "again()">重新评估</button>
        </div>
    </div>
    <input type="hidden" :value = "score" class="score">
    <input v-if = "result != null" type="hidden" :value = "result.basicDescription" class="result">
</article>

<script src = "/static/mobile/paper/js/stroke.js?v=2.2.0"></script>
<script>
    stroke.init();
    stroke.vm.result = '${subHealthStandard}' == '' ? null : JSON.parse('${subHealthStandard}');
    stroke.vm.score = '${score}';
</script>
</body>
</html>

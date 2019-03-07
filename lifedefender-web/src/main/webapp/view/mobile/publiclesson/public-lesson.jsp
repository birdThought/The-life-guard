<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
 	<script type="text/javascript" src="/static/jquery/jquery-2.1.1.min.js"></script>
   	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-title" content="生命守护">
    <meta name="format-detection" content="telephone=yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
	<link rel="stylesheet" href="/static/css/portal/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/css/portal/classroom.css">
    <title>公益课堂</title>
</head>
<body>
	<%--<header>--%>
		<%--<div class="container clearfix">--%>
			<%--<i></i>--%>
			<%--<p>热门公益课堂</p>--%>
		<%--</div>--%>
	<%--</header>--%>
	<article>
		<section class="hot-video">
			<h3>Hot Video</h3>
			<div></div>
			<h1>今日热门课堂</h1>
			<div class="play"></div>
		</section>
		<section class="classes">
			<ul>
			    <li>
			    	<p>
			    		<span>家居慢运动之关氏养生操</span>
			    		<span class="count">1263次播放</span>
			    	</p>
			    	<a href="http://v.youku.com/v_show/id_XMTc4MTk2MzA2OA==.html?from=s1.8-1-1.2&spm=a2h0k.8191407.0.0&qq-pf-to=pcqq.temporaryc2c" target="_blank">
			    		<div class="play"></div>
			    	</a>
			    </li>
			    <li>
			    	<p>
			    		<span>家居慢运动之运动功能检测</span>
			    		<span class="count">153次播放</span>
			    	</p>
			    	<a href="http://v.youku.com/v_show/id_XMTc4MjIyNzc0MA==.html?spm=a2hzp.8244740.userfeed.5!6~5~5~5!2~Ahttp://v.youku.com/v_show/id_XMTc4MjIyNzc0MA==.html?spm=a2hzp.8244740.userfeed.5!6~5~5~5!2~A" target="_blank">
			    		<div class="play"></div>
			    	</a>
			    </li>
			    <li>
			    	<p>
			    		<span>家居慢运动之床上操</span>
			    		<span class="count">153次播放</span>
			    	</p>
			    	<a href="http://v.youku.com/v_show/id_XMTg0NDg2MTk2NA==.html?spm=a2hzp.8244740.userfeed.5!3~5~5~5!2~A" target="_blank">
			    		<div class="play"></div>
			    	</a>
			    </li>
			    <li>
			    	<p>
			    		<span>家居慢运动之单项训练（上）</span>
			    		<span class="count">199次播放</span>
			    	</p>
			    	<a href="http://v.youku.com/v_show/id_XMTc4MTk5MzIwOA==.html?spm=a2hzp.8244740.userfeed.5!7~5~5~5!3~5~A" target="_blank">
			    		<div class="play"></div>
			    	</a>
			    </li>
			    <li>
			    	<p>
			    		<span>家居慢运动之单项训练（下）</span>
			    		<span class="count">235次播放</span>
			    	</p>
			    	<a href="http://v.youku.com/v_show/id_XMTc4MjIyNzc0MA==.html?spm=a2hzp.8244740.userfeed.5!6~5~5~5!2~A" target="_blank">
			    		<div class="play"></div>
			    	</a>
			    </li>
			</ul>
		</section>
	</article>
</body>
</html>
<script>
(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            if(clientWidth>=640){
                docEl.style.fontSize = '100px';
            }else{
                docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
            }
        };

    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);
</script>

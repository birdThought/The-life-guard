<!DOCTYPE html>
<html lang="en">
<head>
	<%@ page contentType="text/html;charset=UTF-8" language="java"%>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-title" content="生命守护">
	<meta name="format-detection" content="telephone=yes">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
	<link rel="stylesheet" href="/static/mobile/publiclesson/v2.4.0/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/mobile/publiclesson/v2.4.0/css/classroom.css">
	<script src="/static/mobile/publiclesson/v2.4.0/js/reset.js"></script>
    <title>公益课堂</title>
</head>
<body ng-app="routingDemoApp">
	<!--header-->
    <%-- 该头部与手机App端产生冲突 已经舍弃
	<header>
		<div class="container clearfix">
			<i></i>
			<p>公益课堂</p>
		</div>
	</header>--%>
	<!-- /header -->
	<article id="article">
		<nav class="nav">
			<a class="btn-one" href="#/computers" class="on">热门课堂</a>
			<a class="btn-two" href="#/printers">热门听单</a>
		</nav>
		<ul ng-view class="hot-class">
			<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
			<script src="http://apps.bdimg.com/libs/angular-route/1.3.13/angular-route.js"></script>
		</ul>
	</article>
</body>
</html>
<!---->
<script>
	/*angular路由层配置*/
    angular.module('routingDemoApp',['ngRoute'])
        .config(['$routeProvider', function($routeProvider){
            $routeProvider
                .when('/computers',{templateUrl:'/app/appweb/public-lesson-2/hot'})
                .when('/printers',{templateUrl:'/app/appweb/public-lesson-2/hot2'})
                .otherwise({redirectTo:'/computers'});
        }]);
</script>
<script>
window.onload=function(){
    var btn_one = document.getElementsByClassName('btn-one')[0]
    var btn_two = document.getElementsByClassName('btn-two')[0]
    btn_one.onclick=function(){
        this.style.backgroundColor='#44c660';
        this.style.color='#fff'
        btn_two.style.backgroundColor='#fff'
        btn_two.style.color='#000'
    }
    btn_two.onclick=function(){
        this.style.backgroundColor='#44c660';
        this.style.color='#fff'
        btn_one.style.backgroundColor='#fff'
        btn_one.style.color='#000'
    }
}
</script>
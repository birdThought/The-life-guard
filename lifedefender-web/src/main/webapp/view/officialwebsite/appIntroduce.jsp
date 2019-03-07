<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11"></t:base>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/banner.css">
    <title>生命守护官网  健康管理系统</title>
</head>
<body>
<%@include file="/context/mainHeader_1.jsp"%>
<section class="item-about-content">
    <div class="banner">
        <img src="/static/images/index/appImg.png" alt="">
    </div>
    <div class="tab_full">
        <div class="tab-item">
            <ul class="tab-nav banxin">
                <li class="current">机构端APP</li>
                <li>用户端APP</li>
            </ul>
            <div class="tab-content">
                <ul class="org">
                    <li>
                        <div class="banxin">
                            <img src="/static/images/index/unusual.png" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="banxin">
                            <img src="/static/images/index/chatTo.png" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="banxin">
                            <img class="app-org-img-3" src="/static/images/index/healthMall.png" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="banxin">
                            <img class="app-org-img-4" src="/static/images/index/userSort.png" alt="">
                        </div>
                    </li>
                </ul>
                <ul class="user">
                    <li>
                        <div class="banxin">
                            <img src="/static/images/index/userUnusual.png" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="banxin">
                            <img src="/static/images/index/dataAnalyse.png" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="banxin">
                            <img class="app-user-img-3" src="/static/images/index/healthConsult.png" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="banxin">
                            <img class="app-user-img-4" src="/static/images/index/userMall.png" alt="">
                        </div>
                    </li>
                </ul>
            </div>
            <script>
            $(".tz-navbar-nav > li:eq(1) a").addClass("on").parent("li").siblings("li").children("a").removeClass("on");
                $(".tab-content ul").not($(".tab-content ul").eq(0)).hide();
                $(".tab-item .tab-nav > li ").click(function(event) {
                    $(this).attr("class","current").siblings().removeClass("current");
                    $(".tab-content > ul").hide().eq($(this).index()).show();
                });

            </script>
        </div>
    </div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
</body>
</html>
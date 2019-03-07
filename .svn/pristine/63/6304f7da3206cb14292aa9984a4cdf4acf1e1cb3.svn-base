<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <img src="/static/images/index/orgService_1.png" alt="">
    </div>
    <div class="banxin">
        <ul  class="service_items clearfix">
            <li>
                <div>
                    <img src="/static/images/index/bodyConsult_gray.png" alt="">
                </div>
                <div>
                    <h4>健康问诊</h4>
                    <p>图片和服务师在线资询</p>
                </div>
            </li>
            <li>
                <a href="indexControl.do?physicalPaper" style = "text-decoration:none; color: #333333;">
                    <div>
                        <img src="/static/images/index/bodyMeasure.png" alt="">
                    </div>
                    <div>
                        <h4>体质评估</h4>
                        <p>自我诊断，健康测试</p>
                    </div>
               </a>
            </li>
            <li >
                <a href="indexControl.do?subHealth" style = "text-decoration:none; color: #333333;">
                    <div>
                        <img src="/static/images/index/subhealthy.png" alt="">
                    </div>
                    <div>
                        <h4>亚健康评估</h4>
                        <p>自我诊断，健康测试</p>
                    </div>
                </a>
            </li>
            <li>
                <div>
                    <img src="/static/images/index/orgService_2_gray.png" alt="">
                </div>
                <div>
                    <h4>机构服务</h4>
                    <p>全方位的服务健康医疗体系</p>
                </div>
            </li>
        </ul>
        <!-- 机构服务与健康问诊/ -->
        <div class="service_cooper">
            <div class="service-title">
                <h3>合作伙伴</h3>
            </div>
            <p>
                <span>宏元堂中医院</span>
                <span>中山市小榄人民医院</span>
                <span>广东省医师协会</span>
                <span>中国电信</span>
            </p>
        </div>
        <!-- 推荐服务机构 -->
        <div class="recommended_service">
            <div class="service-title clearfix">
                <h3 class="fl">推荐服务机构</h3>
                <a class="fr moreBtn" href="indexControl.do?healthServiceOrg">更多服务</a>
            </div>
            <ul class="org_introduce clearfix">
                <c:forEach items="${orgs}" var="o">
                    <li class="col-md-6 col-sm-6 col-xs-12 orginfo-row-wrapper">
                        <div class="row alignFlex clearShadow orginfo-row">
                            <div class="col-md-3 col-sm-3 col-xs-4 org-page-img orginfo-row-img">
                                <img src="${o.logo}" alt="" onerror="this.src='/static/images/index/nopic.jpg'">
                            </div>
                            <div class="col-md-9 col-sm-9 col-xs-8 org-page-text orginfo-row-text">
                                <h4><a href="indexControl.do?healthserviceorgdetails&orgId=${o.id}">${o.name}</a></h4>
                                <p>${o.classfies}</p>
                                <address class="no-bottom">${o.address}</address>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <!-- 推荐服务机构 -->

        <!-- 服务动态 -->
        <div>
            <div class="service-title clearfix">
                <h3 class="fl">服务动态</h3>
                <a class="fr moreBtn" href="informationControl.do?newsIndex&f=6">更多动态</a>
            </div>
            <ul class="org_news">
                <c:choose>
                    <c:when test="${empty news}">
                        <li><p>暂未有动态</p></li>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="nw" items="${news}">
                            <li class="col-md-12 col-sm-12 col-xs-12 orgnews-row-wrapper">
                                <div class="row alignFlex clearShadow orgnews-row">
                                    <div class="col-md-2 col-sm-3 col-xs-4 org-page-img orgnews-row-img">
                                        <img src="${nw.image}" alt="" onerror="this.src='/static/images/index/nopic.jpg'">
                                    </div>
                                    <div class="col-md-10 col-sm-9 col-xs-8  org-page-text orgnews-row-text">
                                        <h4>${nw.title}</h4>
                                        <p>${nw.content}<a href="informationControl.do?inforLook&id=${nw.id}" target="_blank">[详情]</a></p>
                                        <p class="text-right">${nw.date}</p>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <!-- 服务动态 -->
    </div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
</body>
</html>
<script>
$(".tz-navbar-nav > li:eq(2) a").addClass("on").parent("li").siblings("li").children("a").removeClass("on");
</script>
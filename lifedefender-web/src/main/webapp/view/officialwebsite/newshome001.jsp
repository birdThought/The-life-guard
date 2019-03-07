<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>生命守护官网——健康管理/慢病管理云平台</title>
    <meta name="keyword" content="慢病管理,健康管理">
    <meta name="description" content="生命守护专注打造慢病管理、健康管理系统，功能包括健康数据测量与分析、疾病预警、家庭医生、健康问诊、健康课堂、健康档案等，同时为用户提供智能预警检测设备。">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/static/common/css/mainHeader_1.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/news.css">
    <link rel="stylesheet" href="/static/common/css/pageCss.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/banner.css">
    <c:set var="totalPage" value="${data.attr.informations.totalPage}"></c:set>
    <c:set var="nowPage" value="${data.attr.informations.nowPage}"></c:set>
</head>
<body>
<div class="container">
    <%@include file="/context/mainHeader_1.jsp" %>
    <section class="banner" class="banner">
        <div>
            <img src="/static/images/index/aboutUs.png" alt="">
        </div>
    </section>
    <div class="item_content container tz-news-center">
        <div class="healthInfor">
            <h3>动态资讯</h3>
            <div style="display: none;">
                <input type="text" id="searchCondition" placeholder="站内搜索">
                <button id="search">搜索</button>
            </div>
        </div>
        <ul class="news-nav">
            <c:forEach items="${data.attr.columns}" var="item">
                <li data-id="${item.id}"><a href="informationControl.do?newsIndex&f=${item.id}">${item.name}</a></li>
            </c:forEach>
            <c:forEach items="${dataofficial.attr.columns}" var="item">
                <li data-id="${item.id}"><a href="informationControl.do?newsIndex&f=${item.id}">${item.name}</a></li>
            </c:forEach>
        </ul>
        <div class="item-content">
            <ul class="item-content-left">
                <c:if test="${empty data.attr.informations.dataObject}">
                    <li style="border-bottom: none;text-align: center;font-size: 20px">
                        <c:choose>
                            <c:when test="${empty search}">
                                <div>该栏目下还没有资讯</div>
                            </c:when>
                            <c:otherwise>
                                <div>无搜索结果</div>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:if>
                <c:forEach items="${data.attr.informations.dataObject}" var="item">
                    <li onclick="newsControl.lookNews(${item.id})">
                        <div class="item-img">
                            <img src="${item.image}" alt="" onerror="this.src='/static/images/index/nopic.jpg'">
                        </div>
                        <div class="item-news">
                            <h3>${item.title}</h3>
                            <p>
                                <c:choose>
                                    <c:when test="${fn:length(item.content) > 85}">
                                        <c:out value="${fn:substring(item.content, 0, 85)}..."/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${item.content}"/>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <a><img src="/static/images/sADtor.png" alt="" style="margin-right: 4px;" onerror="this.src='/static/images/index/nopic.jpg'">&nbsp;&nbsp;${item.source}</a>
                            <span><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
                        </div>
                        <span class="spread" style="display: none;"><a href="#">推广</a></span>
                    </li>
                </c:forEach>
            </ul>
            <%--<%@include file="/context/page.jsp" %>--%>
            <div id="pageManager" class="page_Container" style="width: 760px">
                <c:choose>
                    <c:when test="${totalPage==1}">
                        <span class="page page_action">1</span>
                    </c:when>
                    <c:when test="${totalPage>1}">
                        <span class="page page_action">1</span>
                        <c:choose>
                            <c:when test="${totalPage>5}">
                                <c:forEach begin="2" end="5" var="p">
                                    <span class="page">${p}</span>
                                </c:forEach>
                                <span class="page_dian">...</span>
                            </c:when>
                            <c:when test="${totalPage<=5}">
                                <c:forEach begin="2" end="${totalPage}" var="p">
                                    <span class="page">${p}</span>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                        <span class="page page_next" id="btn_next">下一页</span>
                        <span style="margin-left:10px">共${totalPage}页，到第</span>
                        <input type="text" class="page_input" id="p_input"/>
                        <span>页</span>
                        <button class="page_input_enter">确定</button>
                    </c:when>
                </c:choose>
            </div>
            <style>

            </style>
            <div class="item-content-right">
                <div class="item-content-top">
                    <div class="ystuijian">
                        <p class="title"><a href="#">养生推荐</a></p>
                    </div>
                    <div class="imgSlider">
                        <c:forEach items="${data.attr.yangsheng}" var="item">
                            <c:choose>
                                <c:when test="${fn:length(item.title) > 10}">
                                    <a href="informationControl.do?inforLook&id=${item.id}" target="_blank"><img src="${item.image}" alt="" title="${fn:substring(item.title, 0, 10)}..." onerror="this.src='/static/images/index/nopic.jpg'"></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="informationControl.do?inforLook&id=${item.id}" target="_blank"><img src="${item.image}" alt="" title="${item.title}" onerror="this.src='/static/images/index/nopic.jpg'"></a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
                <div class="item-content-top">
                    <div class="ystuijian">
                        <p class="title"><a href="#">本日排行</a></p>
                    </div>
                    <ul class="hotNews">
                        <c:forEach items="${data.attr.hot}" var="item" varStatus="status">
                            <c:choose>
                                <c:when test="${status.index==0}">
                                    <li onclick="newsControl.lookNews(${item.id})">
                                        <a href="#" style="display:block;overflow:hidden;">
                                            <div style="padding:10px 0;">
                                                <img src="${item.image}" alt="" onerror="this.src='/static/images/index/nopic.jpg'">
                                            </div>
                                            <p>
                                                <strong>
                                                    <c:choose>
                                                        <c:when test="${fn:length(item.title) > 18}">
                                                            <c:out value="${fn:substring(item.title, 0, 18)}..."/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${item.title}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </strong><br>
                                                <span style="color:#999;">
                                                    <c:choose>
                                                        <c:when test="${fn:length(item.content) > 35}">
                                                            <c:out value="${fn:substring(item.content, 0, 35)}..."/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${item.content}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                            </p>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li onclick="newsControl.lookNews(${item.id})" class="news-title"><a href="#"><span>${item.title}</span></a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/context/mainFooter_1.jsp" %>
</div>

<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
<t:base type="jquery,layer"></t:base>
<script type="text/javascript" src="/static/plugins/slider/js/slider.js"></script>
<script type="text/javascript" src="/static/plugins/img_slide/jquery.imgslider.js"></script>
<script>
    $("div.imgSlider").imgslider();
    newsControl.init({
        f: '${f}',
        search: '${search}'
    })
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: ${totalPage},
        initPage: '${nowPage}',
        pageChange: function (page) {
            newsControl.nextPage(page)
        }
    });
</script>
</body>
</html>
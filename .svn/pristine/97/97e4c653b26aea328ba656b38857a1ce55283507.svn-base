<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${info.title}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keyword" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/mainHeader.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/news.css">

    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">

    <t:base type="jquery,layer"></t:base>
    <style type="text/css">
        html {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .collect {
            border: none;
            padding: 3px 18px;
            background: #ff9838;
            color: white;
            outline: none;
            font-size: 17px;
            border-radius: 5px;
            font-family: 'Microsoft Yahei';
            cursor: pointer;
            float: right;
            margin-right: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <%@include file="/context/mainHeader_1.jsp" %>
    <%--<%@include file="/context/mainBanner.jsp" %>--%>
    <div class="item_content hwrap">
        <div class="healthInfor">
            <h3><a href="informationControl.do?newsIndex">动态资讯</a>&nbsp;>&nbsp;<span
                    style="color:#48c858;">${columnName}</span></h3>
        </div>
        <div class="item-content">
            <div class="item-content-left">
                <section>
                    <h3 style="text-align:center;font-weight:400;font-size:20px;">${info.title}</h3>
                    <p style="text-align: center;margin: 15px 0 0px">
                        <a href="#" style="font-size: 16px;color:#000;margin-right: 50px"><img
                                src="/static/images/sADtor.png" alt="">&nbsp;&nbsp;${info.source}</a>
                        <span style="font-size: 15px;color:#6c6c6c;">发布时间：<fmt:formatDate value="${info.createDate}"
                                                                                          pattern="yyyy-MM-dd HH:mm"/></span>
                        <%--<button class="collect" onclick="addFavorite()">+收藏</button>--%>
                    </p>
                    <div style="line-height: 26px;padding-bottom: 20px ;border-bottom: 1px solid #ddd">　　
                        ${info.content}
                    </div>
                    <p style="padding:20px 0;text-align: right;display:none;">
                        <label>分享：</label>
                        <img src="/static/images/sina.png" alt="">
                        <img src="/static/images/tx.png" alt="">
                        <img src="/static/images/zoom.png" alt="">
                        <img src="/static/images/wechat_2.png" alt="">
                    </p>
                </section>
                <%--<!-- 畅言代码开始 -->--%>
                <%--<!--PC和WAP自适应版-->--%>
                <%--<div id="SOHUCS" sid="${infor.id}"></div>--%>
                <%--<script type="text/javascript">--%>
                    <%--(function () {--%>
                        <%--var appid = 'cysV2QVfr';--%>
                        <%--var conf = 'prod_41a65cfc511c78821fb3b724b16270ff';--%>
                        <%--var width = window.innerWidth || document.documentElement.clientWidth;--%>
                        <%--if (width < 960) {--%>
                            <%--window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>');--%>
                        <%--} else {--%>
                            <%--var loadJs = function (d, a) {--%>
                                <%--var c = document.getElementsByTagName("head")[0] || document.head || document.documentElement;--%>
                                <%--var b = document.createElement("script");--%>
                                <%--b.setAttribute("type", "text/javascript");--%>
                                <%--b.setAttribute("charset", "UTF-8");--%>
                                <%--b.setAttribute("src", d);--%>
                                <%--if (typeof a === "function") {--%>
                                    <%--if (window.attachEvent) {--%>
                                        <%--b.onreadystatechange = function () {--%>
                                            <%--var e = b.readyState;--%>
                                            <%--if (e === "loaded" || e === "complete") {--%>
                                                <%--b.onreadystatechange = null;--%>
                                                <%--a()--%>
                                            <%--}--%>
                                        <%--}--%>
                                    <%--} else {--%>
                                        <%--b.onload = a--%>
                                    <%--}--%>
                                <%--}--%>
                                <%--c.appendChild(b)--%>
                            <%--};--%>
                            <%--loadJs("http://changyan.sohu.com/upload/changyan.js", function () {--%>
                                <%--window.changyan.api.config({appid: appid, conf: conf})--%>
                            <%--});--%>
                        <%--}--%>
                    <%--})();--%>
                <%--</script>--%>
                <%--<!-- 畅言代码结束 -->--%>
                <!--跟帖代码开始 -->
                <div id="cloud-tie-wrapper" class="cloud-tie-wrapper"></div>
                <script src="https://img1.cache.netease.com/f2e/tie/yun/sdk/loader.js"></script>
                <script>
                    var cloudTieConfig = {
                        url: document.location.href,
                        sourceId: "${infor.id}",
                        productKey: "19c1555b3d1f45b49033f370f2a02d14",
                        target: "cloud-tie-wrapper"
                    };
                    var yunManualLoad = true;
                    Tie.loader("aHR0cHM6Ly9hcGkuZ2VudGllLjE2My5jb20vcGMvbGl2ZXNjcmlwdC5odG1s", true);
                </script>
                <!--跟帖代码结束 -->
            </div>
            <div class="item-content-right">
                <div class="item-content-top">
                    <div class="ystuijian">
                        <p class="title"><a href="#">行业资讯</a></p>
                    </div>
                    <div class="imgSlider">
                        <c:forEach items="${hangye}" var="item">
                            <c:choose>
                                <c:when test="${fn:length(item.title) > 10}">
                                    <a href="informationControl.do?inforLook&id=${item.id}" target="_blank"><img
                                            src="${item.image}" alt=""
                                            title="${fn:substring(item.title, 0, 10)}..."></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="informationControl.do?inforLook&id=${item.id}" target="_blank"><img
                                            src="${item.image}" alt="" title="${item.title}"></a>
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
                        <c:forEach items="${hot}" var="item" varStatus="status">
                            <c:choose>
                                <c:when test="${status.index==0}">
                                    <li onclick="newsControl.lookNews(${item.id})">
                                        <a href="#" style="display:block;overflow:hidden;">
                                            <div style="padding:10px 0;">
                                                <img src="${item.image}" alt="">
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
                                                <span style="color:#ccc;">
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
                                    <li onclick="newsControl.lookNews(${item.id})" class="news-title"><a
                                            href="#"><span>${item.title}</span></a></li>
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

<script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
<script type="text/javascript" src="/static/plugins/slider/js/slider.js"></script>
<script type="text/javascript" src="/static/plugins/img_slide/jquery.imgslider.js"></script>
<script>
    $("div.imgSlider").imgslider();
</script>
</body>
</html>
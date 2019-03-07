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
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/iconfont.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/news.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>

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
        @media screen and (max-width: 340px){
            .item-content-left p:nth-of-type(1) a{
                font-size: 1.3rem !important;
            }
            .item-content-left p:nth-of-type(1) span {
                font-size: 1.3rem !important;
            }
        }
        .hwrap {
            width: 1200px;
            margin: 0 auto;
            margin-top: 3rem;
        }
		.imgSlider .imgShow {
			width:100%;
			height:100%;
		}
    </style>
</head>
<body>
<div id="CfloatBtn" class="CfloatBtn">
    <ul>
        <li>
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"  class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/zxzx1.gif" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#fff">在线咨询</b></a>
        </li>
        <li>
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"  class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/qqlite.gif" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#000">QQ咨询</b></a>
        </li>
        <li class="li-list-three">
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"  class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/dhzx.gif" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#000">联系方式</b></a>
            <div id="connact_method"><p>400-026-1003</p></div>
        </li>
        <li class="li-list-four">
            <a rel="nofllow" href="/index/v2.5/help/feedback" class="wjdc advice"><img src="/static/officialwebsite/v2.5.0/images/advice.png" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#000">意见反馈</b></a>
        </li>
        <li>
            <span id="scroll_Top" class="scroll_Top" onclick="$.$w.windowScroll(0);">
                <img src="/static/officialwebsite/v2.5.0/images/returnTop.png" alt="">
                <b style="display: block;color: #fff;padding-bottom: 6px;">返回顶部</b>
            </span>
        </li>
    </ul>
</div>
<%@include file="/view/officialwebsite/v2.5.0/common/header.jsp"%>
<div class="item_content hwrap">
    <div class="healthInfor">
        <h3>
            <a href="informationControl.do?newsIndex">动态资讯</a>&nbsp;>&nbsp;
            <span style="color:#48c858;">${columnName}</span>
        </h3>
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
                <div class="content" style="line-height: 26px;padding-bottom: 20px ;border-bottom: 1px solid #ddd">　　
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
                                <a href="/index/v2.5/home/news-details?id=${item.id}" target="_blank"><img
                                        src="${item.image}" alt=""
                                        title="${fn:substring(item.title, 0, 10)}..."></a>
                            </c:when>
                            <c:otherwise>
                                <a href="/index/v2.5/home/news-details?id=${item.id}" target="_blank"><img
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
                                <li onclick="goDetails(${item.id})">
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
                                <li onclick="goDetails(${item.id})" class="news-title"><a
                                        href="#"><span>${item.title}</span></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
<script type="text/javascript" src="/static/plugins/slider/js/slider.js"></script>
<script type="text/javascript" src="/static/plugins/img_slide/jquery.imgslider.js"></script>
<script>
    common.activityMenu(1, 2);
    $("div.imgSlider").imgslider();
    if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){setTimeout(function(){$(".item-content-right").css({"display":"none"});$(".item_content").css({"width":"100%"});$(".item-content-left").css({"width":"100%"});$(".item-content-left img").addClass("img-responsive center-block");$(".item-content-left  img:nth-of-type(1)").removeClass("img-responsive center-block");$(".item-content-left h3").css({"line-height":"2.4rem"});$(".item-content-left p:nth-of-type(1) a").css({"margin-right":"1rem"})},100)};

    var goDetails = function (id) {
        window.open('/index/v2.5/home/news-details?id=' + id, '_blank');
    }
</script>
</body>
</html>
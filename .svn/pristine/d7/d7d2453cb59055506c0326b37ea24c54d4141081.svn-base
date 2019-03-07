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
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/iconfont.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/common.css">

    <link rel="stylesheet" type="text/css" href="/static/css/portal/news.css">
    <link rel="stylesheet" href="/static/common/css/pageCss.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <c:set var="totalPage" value="${data.attr.informations.totalPage}"></c:set>
    <c:set var="nowPage" value="${data.attr.informations.nowPage}"></c:set>
    <style>
        .page_input,.page_input_enter{line-height:1.2;}
        .healthInfor h3 {
            cursor: pointer;
        }
        .footer{
            position: fixed;
            bottom: 0;
            width: 100%;
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
<div class="item_content container tz-news-center" style="margin-top: 3rem;">
    <div class="healthInfor">
        <h3 style="color: #9a9a9a; " onclick="goNews()">动态资讯</h3>
        <h3 style="border: none;">知识库</h3>
        <div style="display: none;">
            <input type="text" id="searchCondition" placeholder="站内搜索">
            <button id="search">搜索</button>
        </div>
    </div>
    <div class="list-auto" style="overflow: auto;width:100%;">
        <ul class="news-nav">
            <c:forEach items="${data.attr.columns}" var="item">
                <li data-id="${item.id}"><a href="/index/v2.5/home/news?f=${item.id}">${item.name}</a></li>
            </c:forEach>
            <c:forEach items="${dataofficial.attr.columns}" var="item">
                <li data-id="${item.id}"><a href="/index/v2.5/home/news?f=${item.id}">${item.name}</a></li>
            </c:forEach>
        </ul>
    </div>

    <div class="item-content">
        <ul class="item-content-left clearfix">
            <c:if test="${empty data.attr.informations.dataObject}">
                <li style="border-bottom: none;text-align: center;font-size: 20px">
                    <c:choose>
                        <c:when test="${empty search}">
                            <div>该栏目下还没有内容</div>
                        </c:when>
                        <c:otherwise>
                            <div>无搜索结果</div>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:if>
            <c:forEach items="${data.attr.informations.dataObject}" var="item">
                <li onclick="goDetails(${item.id})">
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
        <div id="pageManager" class="page_Container" style="width: 80%">
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

    </div>
</div>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>

<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>

<script type="text/javascript" src="/static/plugins/slider/js/slider.js"></script>
<script type="text/javascript" src="/static/plugins/img_slide/jquery.imgslider.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
<script>
    common.activityMenu(1, 2);
    $("div.imgSlider").imgslider();
    newsControl.init({
        f: '${f}',
        search: '${search}'
    });
    $('.page_input').css({'height':'20px'})
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: '${totalPage}',
        initPage: '${nowPage}',
        pageChange: function (page) {
            newsControl.nextPage(page)
        }
    });
    var goDetails = function (id) {
        window.open('/index/v2.5/home/news-details?id=' + id, '_blank');
    }
    var goNews = function () {
        window.location.href = '/index/v2.5/home/news';
    }


    setTimeout(function(){if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){setTimeout(function(){$(".item-content-left li").addClass("col-xs-12");$(".item_content").css({"width":"100%"});$(".item-content-right").css({"display":"none"});$(".item-content-left").css({"width":"100%"});$(".banner").css({"width":"100%","min-width":"100%"});$(".news-nav").css({"width":"900px"});$(".tz-news-center").css({"width":"100%"});$("#pageManager").css({"width":"100%"});$(".item-news h3").css({"line-height":"2rem"});var newsVal=$(".item-news p").html();$(".item-news h3").css({"overflow":"hidden","text-overflow":"ellipsis","white-space":"nowrap"});$(".item-news p").text(Trim(newsVal).substring(0,26)+"....");$("#pageManager span:eq(4)").after("<br>")},100)}else{}},300);
    function Trim(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
</script>
</body>
</html>
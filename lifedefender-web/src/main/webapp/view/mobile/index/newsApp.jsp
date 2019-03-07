<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>生命守护</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keyword" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link type="image/x-icon" rel="shortcut icon" href="../favicon.ico"
          mce_href="../favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="../static/common/css/common.css">
    <link rel="stylesheet" type="text/css" href="../static/common/css/mainHeader.css">
    <link rel="stylesheet" type="text/css" href="../static/css/portal/news.css">
    <link rel="stylesheet" type="text/css" href="../static/plugins/swiper/css/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/portal/newsApp.css">
    <script type="text/javascript" src="../static/officialwebsite/js/dateFormat.js"></script>
    <script type="text/javascript" src="../static/officialwebsite/index/js/news_index.js"></script>

    <%-- <t:base type="jquery,layer"></t:base> --%>
    <!-- 暂时不引入标签，直接插入js -->
    <script type="text/javascript" src="../static/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../static/common/js/common.js"></script>
    <script type="text/javascript" src="../static/plugins/layer/layer.js"></script>

    <script type="text/javascript" src="../static/plugins/img_slide/jquery.imgslider.js"></script>
    <script src="../static/plugins/swiper/js/swiper.min.js"></script>
    <script src="../static/mobile/index/js/information.js"></script>
    <style type="text/css">
        .swiper-container {
            width: 100%;
            height: 40px;
           line-height:30px;
            position:fixed;
            left:0;
            top:0;
            background-color:#fff;
            border-bottom:1px solid #eee;
        }
        
        .swiper-slide {
            text-align: center;
            font-size: 16px;
            color:#666;
            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }

        html {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .fAction span{
            color: #48c858
        }
        
    </style>
</head>
<body>
<div class="swiper-container">
    <ul id="col_list" class="swiper-wrapper">
        <c:forEach items="${columns}" var="item">
            <li data-id="${item.id}" class="swiper-slide"><span>${item.name}</span></li>
        </c:forEach>
    </ul>
    <!-- Add Pagination -->
    <div class="swiper-pagination"></div>
</div>
<div class="item-content">
    <ul id="info_list" class="item-content-left">
        <%--<li>
            <div class="item-img">
                <img src="/static/images/coffee.png" alt="">
            </div>
            <div class="item-news">
                <h3>早餐的五种吃法才是最要命的</h3>
                <p>
                    俗话说:"一日之计在于晨。"可很多人宁愿花时间赖会儿床，也懒得拿出20分钟认认真真地吃个早餐。不吃早餐，惹来···
                </p>
                <a href="#"><img src="/static/images/sADtor.png" alt="">求医网</a>
                <span>2016-09-12</span>
            </div>
            <span class="spread"><a href="#">推广</a></span>
        </li>--%>
    </ul>
    <%--<div class="item-content-right">
        <div class="item-content-top">
            <div class="ystuijian">
                <p class="title"><a href="#">养生推荐</a></p>
            </div>
            <div class="slider">
                <ul class="slide-main">
                    <li><a href="#"><img src="/static/images/extendBanner.png" alt=""></a></li>
                </ul>
            </div>
        </div>
        <div class="item-content-top">
            <div class="ystuijian">
                <p class="title"><a href="#">本月排行</a></p>
            </div>
            <ul class="hotNews">
                <li>
                    <a href="#" style="display:block;overflow:hidden;">
                        <div style="padding:10px 0;">
                            <img src="/static/images/coffee.png" alt="">
                        </div>
                        <p>
                            <strong>NO.1</strong><br>
                            养脾就是养身材，脾是人体中消化排毒的重要器官，无论是胖子还是... <strong>[详细]</strong>
                        </p>
                    </a>
                </li>
                <li class="news-title"><a href="app.do?informationIndex"><span>四叶参的食用禁忌 药用特性介绍</span></a></li>
                <li class="news-title"><a href="#"><span>人中白的治病验方有哪些？</span></a></li>
                <li class="news-title"><a href="#"><span>人中白的治病验方有哪些？</span></a></li>
                <li class="news-title"><a href="#"><span>人中白的治病验方有哪些？</span></a></li>
            </ul>
        </div>
    </div>--%>
     <p id="goTop" class="goTop">顶部</p>
</div>
</body>

<script>
    $(function () {
        var swiper = new Swiper('.swiper-container', {
            // pagination: '.swiper-pagination',
            slidesPerView: 4,
            paginationClickable: true,
            spaceBetween: 10,
            freeMode: true
        });
        infomationControl.init();
    })
     $(window).scroll(function(){
        var sc=$(window).scrollTop();
        
        var rwidth=$(window).width()+$(document).scrollLeft();
        var rheight=$(window).height()+$(document).scrollTop();
        console.log($(document).scrollLeft());
        if(sc>400){
            $("#goTop").css("display","block");
            $("#goTop").css("left",(rwidth-50)+"px");
            $("#goTop").css("top",(rheight-90)+"px");
        }else{
            $("#goTop").css("display","none");
        }
    });
    $("#goTop").click(function(){
        $('body,html').animate({scrollTop:0},300);
    });
</script>
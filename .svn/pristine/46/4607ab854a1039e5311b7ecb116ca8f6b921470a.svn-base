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
    <link rel="stylesheet" type="text/css" href="/static/plugins/layui/v2.1.2/css/layui.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/iconfont.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/news.css">
    <c:set var="totalPage" value="${data.attr.informations.totalPage}"></c:set>
    <c:set var="nowPage" value="${data.attr.informations.nowPage}"></c:set>
    <style>
        .page_input,.page_input_enter{line-height:1.2;}
        .healthInfor h3 {
            cursor: pointer;
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
        <h3>动态资讯</h3>
        <h3 style="color: #9a9a9a; border: none;" onclick="goRepository()">知识库</h3>
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
                            <div>该栏目下还没有资讯</div>
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
        <div id="pageManager" class="page_Container" style="width: 80%; text-align: center;">
        </div>
        <div class="item-content-right">
            <div class="item-content-top">
                <div class="ystuijian">
                    <p class="title"><a href="#">养生推荐</a></p>
                </div>
                <%-- <div class="imgSlider">
                      <c:forEach items="${data.attr.yangsheng}" var="item">
                        <c:choose>
                            <c:when test="${fn:length(item.title) > 5}">
                                <a href="/index/v2.5/home/news-details?id=${item.id}" target="_blank"><img src="${item.image}" alt="" title="${fn:substring(item.title, 0, 10)}..." onerror="this.src='/static/images/index/nopic.jpg'"></a>
                            </c:when>
                            <c:otherwise> 
                                <a href="/index/v2.5/home/news-details?id=${item.id}" target="_blank"><img src="${item.image}" alt="" title="${item.title}" onerror="this.src='/static/images/index/nopic.jpg'"></a>
                            </c:otherwise>
                        </c:choose> 
                    </c:forEach>  
                    <div class="imgSlider"><div class="imgShow">
                                <a href="/index/v2.5/home/news-details?id=163" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: none;"></a>
                                <a href="/index/v2.5/home/news-details?id=171" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: none;"></a>
                                <a href="/index/v2.5/home/news-details?id=173" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: none;"></a>
                                <a href="/index/v2.5/home/news-details?id=181" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: none;"></a>
                                <a href="/index/v2.5/home/news-details?id=162" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/11/8aad3dcb5e564f9e015e6f9556fd000f.png" alt="" title="肾病知多点，教你看懂..." style="display: block;"></a>
                </div><div class="footer"></div><div class="titleShow">肾病知多点，教你看懂...</div><ul class="imgIndex"><li class="">1</li><li class="">2</li><li class="">3</li><li class="">4</li><li class="selected">5</li></ul></div>
                </div> --%>
                <div class="imgSlider"><div class="imgShow">
                    
                        
                            
                                <a href="/index/v2.5/home/news-details?id=163" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: none;"></a>
                            
                            
                        
                    
                        
                            
                                <a href="/index/v2.5/home/news-details?id=171" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: none;"></a>
                            
                            
                        
                    
                        
                            
                                <a href="/index/v2.5/home/news-details?id=173" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: none;"></a>
                            
                            
                        
                    
                        
                            
                                <a href="/index/v2.5/home/news-details?id=181" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/13/8aad3dcb5e564f9e015e7a5b6cf80011.jpg" alt="" title="看懂血常规化验单先记..." style="display: block;"></a>
                            
                            
                        
                    
                        
                            
                                <a href="/index/v2.5/home/news-details?id=162" target="_blank"><img src="http://www.lifekeepers.cn/lifekeepers_files/upload/html/2017/09/11/8aad3dcb5e564f9e015e6f9556fd000f.png" alt="" title="肾病知多点，教你看懂..." style="display: none;"></a>
                            
                            
                        
                    
                </div><div class="footer"></div>
                <div class="titleShow">看懂血常规化验单先记...</div>
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
                                <li onclick="goDetails(${item.id})">
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
                                <li onclick="goDetails(${item.id})" class="news-title"><a href="#"><span>${item.title}</span></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="cooperation" style="margin-top: 50px;">
        <h2>合作伙伴</h2>
        <p>加入生命守护合作伙伴计划，开创新业务，获取资源，快速成长</p>
        <section class="linkfriends container">
            <ul class="row">
                <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/chinaLife.png" alt=""></li>
                <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/chinaMobile.png" alt=""></li>
                <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/beadhouse.png" alt=""></li>
                <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/oldRogue.png" alt=""></li>
            </ul>
        </section>
    </div>
</div>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
<script type="text/javascript" src="/static/plugins/slider/js/slider.js"></script>
<script type="text/javascript" src="/static/plugins/img_slide/jquery.imgslider.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="/static/plugins/layui/v2.1.2/layui.js"></script>
<script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
<script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
<script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
<script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
<script>
    common.activityMenu(1, 2);
    $("div.imgSlider").imgslider();
    newsControl.init({
        f: '${f}',
        search: '${search}'
    });
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        laypage.render({
            elem: 'pageManager'
            ,theme: '#337ab7'
            ,count: '${data.attr.informations.totalSize}'
            ,curr: '${data.attr.informations.nowPage}'
            ,limit: 10
            ,jump: function(obj, first){
                if(!first){
                    var f = '${f}';
                    window.location.href = '/index/v2.5/home/news?f=${f}&page=' + obj.curr;
                }
            }
        });
    });
    var goDetails = function (id) {
        window.open('/index/v2.5/home/news-details?id=' + id, '_blank');
    };
    var goRepository = function () {
        window.location.href = '/index/v2.5/home/repository';
    };
    setTimeout(
        function(){
            if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
                setTimeout(function(){
                    $(".item-content-left li").addClass("col-xs-12");
                    $(".item_content").css({"width":"100%"});
                    $(".item-content-right").css({"display":"none"});
                    $(".item-content-left").css({"width":"100%"});
                    $(".banner").css({"width":"100%","min-width":"100%"});
                    $(".news-nav").css({"width":"900px"});
                    $(".tz-news-center").css({"width":"100%"});
                    $("#pageManager").css({"width":"100%"});
                    $(".item-news h3").css({"line-height":"2rem"});
                    var newsVal=$(".item-news p").html();
                    $(".item-news h3").css({"overflow":"hidden","text-overflow":"ellipsis","white-space":"nowrap"});
                    $(".item-news p").text(Trim(newsVal).substring(0,26)+"....");
                    $("#pageManager span:eq(4)").after("<br>")},100)
            }else{}
        },300);
    function Trim(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
</script>
</body>
</html>
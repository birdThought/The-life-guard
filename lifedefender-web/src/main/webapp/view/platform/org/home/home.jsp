
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-home.css?v=2.2.0">
    <title>服务师主页</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content">
            <ul class="member-task clearfix">
                <li>
                    <dl>
                        <dt>会员数量</dt>
                        <dd class="count"><em class="member-count">0</em>个</dd>
                        <dd class="manage"><a href="/org/memberManage">会员管理 ></a></dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dt>待办任务</dt>
                        <dd class="count"><em class="task-count">0</em>个</dd>
                        <dd class="manage"><a href="/orderManage/ordertodo">任务管理 ></a></dd>
                    </dl>
                </li>
            </ul>
            <div class="org-part2 clearfix">
                <ul>
                    <li>
                        <span class="cursor-pointer" onclick = "org.goTodayProfit()">今日收入</span>
                        <p><em class="week-earning">0</em>元</p>
                    </li>
                    <li>
                        <span class="cursor-pointer" onclick = "org.goTodayOrder()">今日订单</span>
                        <p><em class="week-order">0</em>单</p>
                    </li>
                    <li>
                        <span>本周访客</span>
                        <p><em class="week-visitor">0</em>人</p>
                    </li>
                </ul>
                <div>
                    <div id="container"></div>
                </div>
            </div>
            <div class="org-items-note">
                <div class="org-title clearfix">
                    <h3 class="fl">公告</h3>
                    <a href="#" class="fr">更多 ></a>
                </div>
                <ul class="clearfix">
                    <li class="fl">
                        <div class="note-bg">
                            <img src="/static/images/index/nopic.jpg" alt="">
                            <span>全面招商</span>
                        </div>
                    </li>
                    <li class="fl">
                        <dl>
                            <dd>
                                <a href="#">-【公告】生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。</a>
                            </dd>
                            <dd>
                                <a href="#">-【公告】生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。</a>
                            </dd>
                            <dd>
                                <a href="#">-【公告】生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。</a>
                            </dd>
                            <dd>
                                <a href="#">-【公告】生命守护全面招商-one，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。</a>
                            </dd>              
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="spread-service">
                <div class="org-title clearfix">
                    <h3 class="fl">推送服务</h3>
                    <a href="#" class="fr">更多 ></a>
                </div>
                <div class="spread-service-lists clearfix">
                    <ul class="fl">
                        <li><a href="#">今日头条大揭秘！10w+推荐，算个pi？</a></li>
                        <li><a href="#">微信运营人必备的四大装逼神器</a></li>
                        <li><a href="#">运营内行人才知道的“微信群搜索大法”...</a></li>
                        <li><a href="#">做活动，到底能不能袭来高质用户</a></li>
                    </ul>
                    <ul class="fl">
                        <li><a href="#">今日头条大揭秘！10w+推荐，算个pi？</a></li>
                        <li><a href="#">微信运营人必备的四大装逼神器</a></li>
                        <li><a href="#">运营内行人才知道的“微信群搜索大法”...</a></li>
                        <li><a href="#">做活动，到底能不能袭来高质用户</a></li>
                    </ul>
                </div>

            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>

<script src="/static/plugins/highcharts/highcharts.js"></script>
<script src="/static/platform/v2.2.0/js/org/home/org-home.js"></script>
<script>
    layui.use('layer');
    $('.main-nav li').eq(0).click();
</script>
<script>
    var height=$(".main-content").height();
    $(".main-nav").css({
        borderRight:'1px solid #ddd',
        height:height
    });
    $(".container").css({
        borderLeft:'1px solid #ddd',
        borderRight:'1px solid #ddd',
        borderBottom:'1px solid #ddd',
        height:height
    });

</script>

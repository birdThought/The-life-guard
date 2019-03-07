<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<t:base type="jquery2.11,layui,vue"></t:base>
<meta charset=utf-8>
<meta name=description content="">
<meta name=viewport content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
<link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
<%--<link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/common-store.css?v=2.2.0">--%>
<link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/store-home.css?v=2.2.0">
<title>门店主页</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" v-cloak>
           <ul class="member-task clearfix">
               <c:if test="${data.orgType == 1}">   <%--门店时显示--%>
                   <li>
                       <dl>
                           <dt>员工数量</dt>
                           <dd class="count"><em>${data.userCount}个</em></dd>
                       <dd class="manage"><a href="/org/employee">员工管理 ></a></dd>
                       </dl>
                   </li>
               </c:if>
               <li>
                   <dl>
                       <dt>会员数量</dt>
                       <dd class="count"><em>${data.memberCount}个</em></dd>
                       <dd class="manage"><a href="/org/memberManage">会员管理 ></a></dd>
                   </dl>
               </li>
               <li>
                   <dl>
                       <dt>服务项目</dt>
                       <dd  class="count"><em>${data.serviceCount}个</em></dd>
                        <dd class="manage"><a href="/org/service">服务项目管理 ></a></dd>
                    </dl>
                </li>
               <c:if test="${data.orgType == 2}">   <%--个体门店时显示--%>
                   <li>
                       <dl>
                           <dt>待办任务</dt>
                           <dd  class="count"><em>${data.orderTodoCount}个</em></dd>
                           <dd class="manage"><a href="/orderManage/ordertodo">任务管理 ></a></dd>
                       </dl>
                   </li>
               </c:if>
            </ul>
        <div class="org-part2 clearfix">
            <ul>
                <li>
                    <span class="cursor-pointer" @click = "goTodayProfit">今日收入</span>
                    <p><em>${data.todayProfit}</em>元</p>
               </li>
               <li>
                   <span class="cursor-pointer" @click = "goTodayOrder">今日订单</span>
                   <p><em>${data.todayOrder}</em>单</p>
               </li>
               <li>
                   <span>本周访客</span>
                   <p><em>${data.todayVisit}</em>人</p>
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
                           <li v-if="announcement.length == 0">暂未有数据</li>
                           <li v-if="announcement.length > 0" class="fl">
                               <div class="note-bg">
                                   <img src="/static/platform/v2.2.0/images/org/store.png" alt="">
                                   <span>全面招商</span>
                               </div>
                           </li>
                           <li class="fl">
                               <dl>
                                   <dd v-for="a in announcement">
                                       <a href="#">{{a}}</a>
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
                               <li v-if="pushService.length == 0">暂未有数据</li>
                               <li v-for="p in pushService"><a href="#">{{p}}</a></li>
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
<script src="/static/platform/v2.2.0/js/org/home/store-home.js"></script>
<script>
    layui.use('layer');
    $('.main-nav li').eq(0).click();
    $(".main-content").css("minHeight",1000);
    $(function() {
        home.vm.announcement = JSON.parse('${data.gongao}');
        home.vm.pushService = JSON.parse('${data.tuisong}');
        home.vm.flowRate = JSON.parse('${data.flowRate}');

        $(".items-content table").not("table:first").css("display","none");
        $(".items-nav li").on("click",function(){
            $(this).addClass('current').siblings().removeClass('current');
            $(".items-content table").css("display","none").eq($(this).index()).css("display","block");
        });
    });
</script>
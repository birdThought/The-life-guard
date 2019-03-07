<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<html>
<head>
    <t:base type="jquery2.11,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-task-center.css?v=2.2.0">
    <title>员工管理</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article >
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-task">
            <div class="items-link">
                <a href="#">任务中心</a><em>&gt;</em>
                <a href="#">任务查看</a>
            </div>
            <div class="items-nav">
                <ul>
                    <li class="current cursor-pointer" @click = "currentStatus = 3">
                        未完成<%--<i>8</i>--%>
                    </li>
                    <li class="cursor-pointer" @click = "currentStatus = 4">已完成</li>
                </ul>
                <div class="search">
                    <input type="text" placeholder="搜索相关用户内容" v-model = "search">
                </div>
            </div>
            <ol class="task-content">
                <li>
                    <div class="service-items-lists">
                        <div class="service-type">
                            <label>服务类型</label>
                            <select v-model = "currentType">
                                <option value='all'>全部服务</option>
                                <option value="online">线上服务</option>
                                <option value="offline">线下服务</option>
                                <option value="visit">上门服务</option>
                            </select>
                        </div>
                        <dl class="service-details" v-for = "t in taskData" v-cloak>
                            <dt>
                                <img v-if = "t.service.image != null || t.service.image != ''" :src = "t.service.image" alt="">
                                <img v-else src = "/static/images/index/nopic.jpg" alt="">
                            </dt>
                            <dd class="items-details">

                                <p>项目：{{t.service.name}}</p>
                                <p>服务师：{{t.orgRealName}}</p>
                                <p>用户：{{t.userName}}</p>
                                <p>价格：{{t.price}}元</p>
                                <input type="hidden" :value = "t.orgUserId">
                            </dd>
                            <dd class="items-btn">
                                <p>订单时间：{{t.createDate | date('yyyy-MM-dd hh:mm')}}</p>
                                <span class="cursor-pointer" @click = "popupRemindDialog(t.orderNumber, t.orgUserId)">提醒</span>
                                <a href="#" @click = "goTaskDetails(t.orderId)">查看详情</a>
                            </dd>
                        </dl>
                        <dl style = "margin-top: 100px; text-align: center;"
                            v-if = "taskData != null && taskData.length < 1" v-cloak>
                            <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                            <br/><br/>
                            <p style="font-size: 18px; color: gray;">暂无任务</p>
                        </dl>
                    </div>
                    <div id="task-todo-page-manager" class="page_Container" style = "background-color: white; margin-top: 0px; margin-bottom: 0px;"></div>
                </li>

            </ol>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/common/js/dateFormat.js"></script>
<script src = "/static/common/js/pageUtils.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/ordermanage/task-center.js?v=2.2.0"></script>

<script>
    layui.use('layer');
    $('.main-nav').find('li').eq(1).click();
    task.init();
    task.vm.results = '${order}' == '' ? '' : JSON.parse('${order}'.replace(/\\/g, '/'));
</script>

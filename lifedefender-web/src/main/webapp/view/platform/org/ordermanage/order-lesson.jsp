<%--
 * 课堂服务
 * date: 2017/8/1 10:07
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-order.css?v=2.2.0">
    <title>服务管理</title>
</head>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content">
            <ul class="service-items clearfix">
                <li>
                    <a href="/orderManage/ordertodo" >
                        <div></div>
                        <p >待办服务</p>
                    </a>
                </li>
                <li>
                    <a href="/orderManage/orderdone" >
                        <div></div>
                        <p >已完成服务</p>
                    </a>
                </li>
                <li>
                    <a href="/orderManage/order-lesson" class="on">
                        <div></div>
                        <p class="on">课堂服务</p>
                    </a>
                </li>
            </ul>
            <div class="service-type">
                <label>服务类型</label>
                <select v-model = "projectType">
                    <option value="all">全部服务</option>
                    <option value="online">线上服务</option>
                    <%--<option value="offline">线下服务</option>
                    <option value="visit">上门服务</option>--%>
                </select>
            </div>
            <div class="service-search">
                <input type="text" class="search" placeholder="搜索相关课堂信息" v-model = "search">
            </div>
            <div class="service-items-lists">

                <dl class="service-details" v-for = "(o, index) in orderData">
                    <dt>
                        <img v-if = "o.image != null" :src = "o.image" alt="">
                        <img v-else src = "/static/images/index/nopic.jpg" alt="">
                    </dt>
                    <dd class="items-details" v-cloak>
                        <p>项目：{{o.name}}</p>
                        <p>价格：{{o.price == '0' ? '免费' : o.price + '元'}}</p>
                    </dd>
                    <dd class="items-btn">
                        <span @click = "popupChatDialog(4, null,
                         o.huanxinId, null, o.name, null, o.image, o.code)">去讲课</span>
                        <%--<a @click = "goServeDetails(o.orderId)" class="cursor-pointer">查看详情</a>--%>
                    </dd>
                </dl>
                <dl style = "margin-top: 100px; text-align: center;"
                    v-if = "(orderData != null && orderData.length < 1) || orderData == undefined">
                    <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                    <br/><br/>
                    <p style="font-size: 18px; color: gray;">暂无任务</p>
                </dl>
            </div>
            <div id="pageManager" class="page_Container" style = "background-color: white; margin-top: 0px; margin-bottom: 0px;"></div>

        </div>
    </section>
</article>
<!-- /article -->
</body>
<%@include file="/view/platform/org/message/chat-dialog.jsp"%>
</html>
<script src = "/static/platform/v2.2.0/js/org/ordermanage/order.js?v=2.2.0"></script>
<script src="/static/platform/v2.2.0/js/org/message/chat-dialog.js?v=2.2.0"></script>
<script src = "/static/common/js/pageUtils.js?v=2.2.0"></script>
<script type="text/javascript" src="/static/common/js/dateFormat.js?v=2.2.0"></script>

<script>
    layui.use('layer');
    orderTodo.vm.orderData = '${order.data}' == '' ? '' : JSON.parse('${order.data}');
    orderTodo.vm.orderTotalPage = '${order.totalPage}' == '' ? '1' : '${order.totalPage}';
    orderTodo.vm.currentPage = '${order.nowPage}' == '' ? '1' : '${order.nowPage}';
    pageUtil.getPageUtil();
    orderTodo.vm.orderType = 'lesson';
    orderTodo.vm.orgUser = ${user};

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(3).click();
    } else {
        $('.main-nav li').eq(4).click();
    }
    common.addBorder();
</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-order.css?v=2.2.0">
    <title>订单详情</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-task">
            <div class="org-service-details" v-if = "taskDetails != null">
                <div class="look-for">
                    <a href="/orderManage/order">任务中心></a><a href="#">订单详情</a>
                </div>
                <div class="clearfix status">
                    <p class="fl">当前订单状态：<em>{{taskDetails.status | orderStatus}}</em></p>
                    <span class="fr"><a href="#" class="go-pay" @click = "popupRemindDialog()">提醒</a></span>
                </div>
                <div class="order-details">
                    <div><img :src = "taskDetails.service.image" alt=""></div>
                    <div>
                        <p>订单时间：{{taskDetails.createDate | date('yyyy-MM-dd hh:mm')}}</p>
                        <p>项目：{{taskDetails.service.name}}</p>
                        <p>用户：{{taskDetails.userName}}</p>
                        <p>价格：{{taskDetails.price == '0' ? '免费' : taskDetails.price + '元'}}</p>
                    </div>
                </div>
                <div>
                    <p>订单详情</p>
                    <div class="order-common">
                        <div>
                            <span>套餐信息</span>
                            <span>订单编号</span>
                            <span>下单时间</span>
                            <span>购买手机号</span>
                            <span>数量</span>
                            <span >总价</span>
                        </div>
                        <dl>
                            <dd>{{taskDetails.service.name}}</dd>
                            <dd style="word-break: break-all; line-height: 2;">{{taskDetails.orderNumber}}</dd>
                            <dd >{{taskDetails.createDate | date('yyyy-MM-dd')}}</dd>
                            <dd>{{taskDetails.mobile}}</dd>
                            <dd>{{taskDetails.number}}</dd>
                            <dd>{{taskDetails.price * taskDetails.number}}</dd>
                        </dl>
                    </div>
                </div>
            </div>

        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src = "/static/platform/v2.2.0/js/org/ordermanage/task-center.js?v=2.2.0"></script>
<script type="text/javascript" src="/static/common/js/dateFormat.js?v=2.2.0"></script>

<script>
    $('.main-nav li').eq(1).click();
    task.vm.taskDetails = '${order}' == '' ? '' : JSON.parse('${order}'.replace(/\\/g, '/'));
</script>
<script>
    $(".main-content").css("minHeight",1000);
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

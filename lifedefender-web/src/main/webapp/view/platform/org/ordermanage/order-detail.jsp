
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
    <title>服务详情</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content">
            <div class="org-service-details" v-if = "orderDetail != null">
                <div class="look-for">
                    <a href="#">服务管理></a><span>订单详情</span>
                </div>
                <div class="clearfix status" v-cloak>
                    <p class="fl">当前订单状态：<em>{{orderDetail.status | orderStatus}}</em></p>
                    <span class="fr"><a href="#" class="go-pay" @click = "popupChatDialog()">去指导</a></span>
                </div>
                <div class="order-details" v-cloak>
                    <div>
                        <img v-if = "orderDetail.service.image != null" :src = "orderDetail.service.image" alt="">
                        <img v-else src = "/static/images/index/nopic.jpg" alt="">
                    </div>
                    <div>
                        <p>订单时间：{{orderDetail.createDate | date('yyyy-MM-dd hh:mm')}}</p>
                        <p>项目：{{orderDetail.service.name}}</p>
                        <p>用户：{{orderDetail.userName}}</p>
                        <p>价格：{{orderDetail.price == '0' ? '免费' : orderDetail.price + '元'}}</p>
                    </div>
                </div>
                <div v-cloak>
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
                                <dd>{{orderDetail.service.name}}</dd>
                            <dd style="word-break: break-all; line-height: 2;">{{orderDetail.orderNumber}}</dd>
                            <dd >{{orderDetail.createDate | date('yyyy-MM-dd')}}</dd>
                            <dd>{{orderDetail.mobile}}</dd>
                            <dd>{{orderDetail.number}}</dd>
                            <dd>{{orderDetail.price == '0' ? '免费' : '￥' + orderDetail.price * orderDetail.number}}</dd>
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
<script src = "/static/platform/v2.2.0/js/org/ordermanage/order.js?v=2.2.0"></script>
<script type="text/javascript" src="/static/common/js/dateFormat.js?v=2.2.0"></script>

<script>
    $('.main-nav li').eq(3).click();
    orderTodo.vm.orderDetail = '${order}' == '' ? '' : ${order};
</script>
<script>
    common.addBorder();
    if ('${orgType}' == 1) {
        $('.main-nav li').eq(3).click();
    } else {
        $('.main-nav li').eq(4).click();
    }

</script>

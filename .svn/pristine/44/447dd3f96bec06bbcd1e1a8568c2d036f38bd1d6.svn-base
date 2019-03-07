<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,vue,layui"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css?v=2.2.0">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/org-order.css?v=2.2.0">
    <title>服务评价</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content  vue-content">
            <div class="org-service-details">
                <div class="look-for">
                    <a href="#">服务管理></a><span>评价</span>
                </div>
                <div class="clearfix order-degree">
                    <h5 class="fl">满意度：</h5>
                    <ul class="star fl">
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
                <div class="commend-area clearfix">
                    <h5 class="fl">评价：</h5>
                    <div class="fl">
                        <p>{{comment}}</p>
                        <div class="commend-img">
                            <span v-for="i in image">
                                <img alt="" v-bind:src="i" />
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="reply layui-form">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">回复：</label>
                    <div class="layui-input-block">
                        <textarea name="reply" v-model="reply" class="layui-textarea" lay-verify="reply" disabled></textarea>
                        <span id="spanBtn" lay-filter="*" lay-submit>已回复</span>
                    </div>
                </div>
            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
<script type="text/javascript" src="/static/platform/v2.2.0/js/org/ordermanage/order-comment-detail.js"></script>
<script type="text/javascript">
    common.addBorder();
    if ('${orgType}' == 1) {
        $('.main-nav li').eq(3).click();
    } else {
        $('.main-nav li').eq(4).click();
    }

    $(function() {
        OrderComment.init();
        OrderComment.vm.orderId = ${comment.orderId};
        OrderComment.vm.comment = '${comment.comment}';
        OrderComment.vm.reply = '${comment.reply}';
        OrderComment.vm.score = ${comment.score};
        OrderComment.vm.replied = ${comment.status};
        OrderComment.vm.image = JSON.parse('${comment.image}');

    });

</script>
</html>
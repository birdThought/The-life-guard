
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%--<meta name="flexible" content="initial-dpr=1" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <link rel="apple-touch-icon" href="favicon.png">
    <link rel="Shortcut Icon" href="favicon.png" type="image/x-icon">
    <title>病历记录</title>
    <style>
        [v-cloak] {
            display: none;
        }
        .physical-list li{
            height: 1.6rem;
            border-bottom: 1px solid #F5F5F5;
        }
        .physical-list span {
            display: block;
            line-height: 0.5rem;
            margin-top: 0.2rem;
            color: #8191a3;
        }
        .physical-list img.logo {
            width: 1.2rem;
            height: 1.2rem;
            border-radius: 1rem;
            float: left;
            margin-right: 0.3rem;
            margin-left: 0.3rem;
            margin-top: 0.1rem;
        }
        .physical-list img.more {
            float: right;
            margin-top: -0.9rem;
            width: 0.7rem;
            height: 0.7rem;
        }
        .pull {
            text-align: center;
            padding: 1.5rem;
            font-weight: 600;
            color: #57565b;
        }
    </style>
</head>
<body>
<div class="vue-content" style="font-size: 14px">
    <ul class="physical-list" v-cloak>
        <li v-for="i in list" @click="goDetails(i.id)">
            <img class="logo" src="/static/mobile/wechat/images/we-physical-1.jpg">
            <span>{{i.title}}</span>
            <span>{{i.basicCondition}}</span>
            <img class="more" src="/static/mobile/wechat/images/we-more.png">
        </li>
    </ul>
    <div class="pull">
        <p>点击或上拉加载更多</p>
    </div>
</div>

</body>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/medical/medical-record.js?v=1.10"></script>
<script>
    medical.init();
</script>
</html>

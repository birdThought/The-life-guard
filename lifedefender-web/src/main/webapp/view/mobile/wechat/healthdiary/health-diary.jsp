
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
    <title>饮食记录</title>
    <style>
        .vue-content {
            background-color: rgb(245, 245, 245);
            font-size: 14px;
        }
        .item {
            margin-bottom: 0.2rem;
            background-color: white;
            padding: 0.3rem;
        }
        .item img {
            width: 1rem;
            height: 1rem;
            border-radius: 0.2rem;
            margin-right: 0.1rem;
        }
        .times {
            background-color: #f0eff5;
            font-size: 14px;
            text-align: center;
            padding: 0.2rem;
            color: #9a9a9a;
        }
        .energy {
            background-color: #f08836;
            padding: 0.08rem 0.3rem 0.08rem 0.3rem;
            border-radius: 0.1rem;
            color: white;
        }
    </style>
</head>
<body>
<div class="vue-content">
    <p class="times">总共记录了{{list.length}}次饮食</p>
    <div v-for="i in list" class="item">
        <p style="color: #73787c;">{{i.recordDate | date('yyyy-MM-dd')}}</p>
        <p style="line-height: 40px;">
            <span style="color: #64CC77;">{{i.dietType}}</span>
            <span style="float: right; margin-right: 0.5rem;">{{i.dietTime | date('hh:ss:mm')}}</span>
        </p>
        <p style="line-height: 30px; color: #9a9a9a;">
            <template v-for="(m, index) in i.foods">
                <span>{{m.foodName}}</span>
                <span>{{m.foodWeight}}g</span>
                <span v-if="index < i.foods.length - 1">、</span>
            </template>

        </p>
        <p style="line-height: 30px; color: #9a9a9a;">
            <span class="energy">能量</span>
            <span>{{i.energy}}kCal</span>
        </p>
        <div style="margin-top: 0.1rem; ">
            <img v-for="m in i.foods" :src="m.image">
        </div>
    </div>
</div>
</body>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/mobile/wechat/js/dateFormat.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/healthdiary/health-diary.js?v=1.10"></script>
<script>
    diary.init();
</script>
</html>

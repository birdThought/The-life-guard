
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
    <title>体检报告查看</title>
    <style>
        [v-cloak] {
            display: none;
        }
        body {
            /*background-color: #f5f5f5;*/
        }
        .vue-content {
            background-color: rgb(245, 245, 245);
            font-size: 14px;
            height: 100%;
            padding-top: 0.15rem;
        }
        .content-1 {
            height: 1.5rem;
            background-color: white;
        }
        .content-1 span:nth-of-type(1) {
            margin-left: 0.3rem;
            color: #b1b1b1;
            line-height: 50px;
        }
        .content-1 span:nth-of-type(2) {
            margin-left: 0.5rem;
        }
        .content-2 {
            height: 1.5rem;
            background-color: white;
            margin-top: 0.15rem;
        }
        .content-2 span:nth-of-type(1) {
            margin-left: 0.3rem;
            color: #b1b1b1;
            line-height: 50px;
        }
        .content-2 span:nth-of-type(2) {
            margin-left: 0.5rem;
        }

        .content-3 {
            min-height: 1.5rem;
            background-color: white;
            margin-top: 0.15rem;
        }
        .content-3 span:nth-of-type(1) {
            margin-left: 0.3rem;
            color: #b1b1b1;
            line-height: 50px;
        }
        .content-3 textarea {
            margin-top: -0.8rem;
            display: block;
            margin-left: 1.7rem;
            width: 80%;
            height: 1.5rem;
            border: none;
            resize: none;
        }
        .content-3 img {
            width: 2rem;
            height: 2rem;
            border-radius: 0.1rem;
            margin-right: 0.2rem;
        }

    </style>
</head>
<body>
<div class="vue-content" style="background-color: #f5f5f5;" v-cloak>
    <div class="content-1">
        <span>体检机构</span>
        <span>{{details.physicalsOrg}}</span>
    </div>
    <div class="content-2">
        <span>体检时间</span>
        <span>{{details.physicalsDate}}</span>
    </div>
    <div class="content-3">
        <span>描述</span>
        <textarea readonly="readonly">{{details.description}}</textarea>
        <div style="padding-left: 1rem;">
            <img v-for="i in details.imgList" :src="i.img">
        </div>
    </div>
    <div></div>
    <div></div>
</div>
</body>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/physical/physical-record.js?v=1.10"></script>
<script>
    physical.vm.physicalId = '${physicalId}';
    physical.initDetails();
</script>
</html>

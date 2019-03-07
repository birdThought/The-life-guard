
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
    <title>浏览病历</title>
    <style>
        [v-cloak] {
            display: none;
        }
        body {
            /*background-color: #f5f5f5;*/
        }
        .vue-content {
            background-color: rgb(245, 245, 245);
            font-size: 16px;
            height: 100%;
            padding-top: 0.15rem;
        }
        .content-1 {
            height: 2.5rem;
            background-color: white;
            padding: 0.2rem;
        }
        .content-1 p {
            color: #9a9a9a;
            font-size: 14px;
            line-height: 22px;
        }
        .content-2 {
            height: 1.5rem;
            background-color: white;
            margin-top: 0.15rem;
            padding: 0.2rem;
        }
        .content-2 p {
            color: #9a9a9a;
            font-size: 14px;
            line-height: 22px;
        }
        .content-2 textarea {
            display: block;
            width: 80%;
            /*height: 1.2rem;*/
            border: none;
            resize: none;
            color: #9a9a9a;
            font-size: 14px;
        }

        .content-3 {
            min-height: 1.5rem;
            background-color: white;
            margin-top: 0.15rem;
            padding: 0.2rem;
        }
        .content-3 span:nth-of-type(1) {
            color: #b1b1b1;
            line-height: 50px;
        }
        .content-3 textarea {
            display: block;
            width: 80%;
            height: 1.2rem;
            border: none;
            resize: none;
            color: #9a9a9a;
            font-size: 14px;
        }
        .content-3 img {
            width: 2rem;
            height: 2rem;
            border-radius: 0.1rem;
            margin-right: 0.2rem;
        }
        .course {
            width: 30%;
            margin-left: 0.3rem;
            background: white;
            border-radius: 0.3rem;
            text-align: center;
            padding-top: -0.7rem;
            margin-top: 0.3rem;
            line-height: 26px;
            color: #44cc61;
        }
        .item {
            margin-top: 0.2rem;
            background-color: white;
            border-radius: 0.1rem;
            margin-right: 0.5rem;
            padding: 0.2rem;
        }
        .item textarea {
            display: block;
            width: 100%;
            height: 1.2rem;
            border: none;
            resize: none;
            color: #9a9a9a;
            font-size: 14px;
        }

    </style>
</head>
<body>
<div class="vue-content" style="background-color: #f5f5f5;" v-cloak>
    <div class="content-1">
        <span style="color: #44cc61;">患者信息</span><br>
        <p>{{user.realName || user.userName}} &nbsp;&nbsp;
            {{user.gender}} &nbsp;&nbsp;
            {{user.age}}岁 &nbsp;&nbsp;
        </p>
        <p>{{details.hospital}}&nbsp;&nbsp;{{details.departments}}</p>
        <p>{{details.visitingDate | date('yyyy-MM-dd')}}&nbsp;&nbsp;就诊</p>
    </div>
    <div class="content-2">
        <span style="color: #44cc61;">医生诊断</span>
        <textarea readonly="readonly">{{details.doctorDiagnosis}}</textarea>
    </div>
    <div class="content-3">
        <span style="color: #44cc61;">病情信息</span>
        <textarea readonly="readonly">{{details.basicCondition}}</textarea>
    </div>
    <div>
        <p class="course">我的病程</p>
        <div v-for="i in details.courseList" style="margin-left: 1rem; margin-top: 0.8rem;">
            <p style="color: #44cc61;">{{i.courseType}}</p>
            <div class="item">
                <div>
                    <img v-for="m in i.imgList" :src="m.img" style="width: 1.2rem; height: 1.2rem; margin-right: 0.2rem">
                </div>
                <textarea readonly="readonly" style="margin-top: 0.1rem" :value="i.remark">
                </textarea>
            </div>
        </div>
    </div>
    <div></div>
</div>
</body>
<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/mobile/wechat/js/dateFormat.js"></script>
<script src="/static/mobile/wechat/js/http.js"></script>
<script src="/static/mobile/wechat/js/medical/medical-record.js?v=1.10"></script>
<script>
    medical.vm.medicalId = '${medicalId}';
    medical.initDetails();
</script>
</html>

<%--
  @Description: 线下与上门服务预览
  @Author: wenxian.cai
  @Date: 2017/7/17 9:58
--%>

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
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/preview-service.css?v=2.2.0">
    <title>发布项目</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article>

    <section class="main-top">
        <%@include file="/view/platform/org/context/admin.jsp"%>
    </section>
    <section class="banxin container vue-preview">
        <div class="items-link">
            <a href="#">服务管理</a><em>></em><span>预览</span>
        </div>
        <div v-cloak class="order-item">
            <p class="tit" v-if = "service != null">{{service.name}}</p>
            <div class="order-item-content clearfix">
                <div class="item-img fl">
                    <div class="fl lg-img">
                        <img v-if = "media.pictureOne != ''" :src="media.pictureOne" alt="">
                        <img v-else src = "/static/images/index/nopic.jpg">
                        <img v-if = "media.pictureTwo != ''" class="none" :src="media.pictureTwo" alt="">
                        <img v-else src = "/static/images/index/nopic.jpg">
                        <img v-if = "media.pictureThree != ''" class="none" :src="media.pictureThree" alt="">
                        <img v-else src = "/static/images/index/nopic.jpg">
                        <img v-if = "media.pictureFour != ''" class="none" :src="media.pictureFour" alt="">
                        <img v-else src = "/static/images/index/nopic.jpg">
                    </div>
                    <ul class="sm-btn">
                        <li class="on">
                            <img v-if = "media.pictureOne != ''" class="serve-image-show-1" :src="media.pictureOne" alt="">
                            <img v-else src = "/static/images/index/nopic.jpg">
                        </li>
                        <li>
                            <img v-if = "media.pictureTwo != ''" class="serve-image-show-1" :src="media.pictureTwo" alt="">
                            <img v-else src = "/static/images/index/nopic.jpg">
                        </li>
                        <li>
                            <img v-if = "media.pictureThree != ''" class="serve-image-show-1" :src="media.pictureThree" alt="">
                            <img v-else src = "/static/images/index/nopic.jpg">
                        </li>
                        <li>
                            <img v-if = "media.pictureFour != ''" class="serve-image-show-1" :src="media.pictureFour" alt="">
                            <img v-else src = "/static/images/index/nopic.jpg">
                        </li>
                    </ul>
                </div>
                <dl class="fl handle">
                    <dd class="cost">
                        <em>现价</em>
                        <span>￥<i v-if = "currentCombo != null">{{currentCombo.price}}</i></span>
                    </dd>
                    <dd class="add-border">
                        <span><em>已售</em>0</span>
                        <span>0 <em>分</em></span>
                        <span>0 <em>人评价</em></span>
                    </dd>
                    <dd class="meal">
                        <em>套餐</em>
                        <span v-for = "(c, index) in combo">{{'套餐' + (index + 1)}}</span>
                    </dd>
                    <dd class="count clearfix">
                        <em class="fl">数量</em>
                        <div class="clearfix fl">
                            <a class="combo-number-sub" href="javascript:void(0)">-</a>
                            <span>{{comboNumber | filterAmount}}</span>
                            <a class="combo-number-add" href="javascript:void(0)">+</a>
                        </div>
                    </dd>
                    <dd>
                        <span class="pay-now">立即购买</span>
                    </dd>
                </dl>
            </div>
        </div v-cloak>
        <div class="this-item-content" v-cloak>
            <p class="tit">本单详情</p>
            <table>
                <thead>
                <tr>
                    <th style="width: 40%;">套餐内容</th>
                    <th>单价</th>
                    <th>数量/规格</th>
                    <th>小计</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <template v-for = "(d, index) in currentCombo.description">{{d.name}}
                            <template v-if = "index < currentCombo.description.length - 1">+</template>
                        </template>
                    </td>
                    <td>{{currentCombo.price}}</td>
                    <td>{{comboNumber}}份</td>
                    <td>{{currentCombo.price * comboNumber}}</td>
                </tr>
                </tbody>
            </table>
            <ul>
                <li>
                    <a onclick="script:window.close();" class="cursor-pointer">退出预览</a>
                </li>
                <%--<li>
                    <a href="#" @click = "changeProjectStatus(service.code)">确认上线</a>
                    &lt;%&ndash;<input type="hidden" v-if = "service != null" :value = "service.code">&ndash;%&gt;
                </li>--%>
            </ul>
        </div>
        <div class="this-item-info" v-cloak>
            <ul class="href-nav">
                <li class="on"><a href="#">商家位置</a></li>
                <li><a href="#pay">购买须知</a></li>
                <li><a href="#service">服务介绍</a></li>
                <li><a href="#org">商家介绍</a></li>
            </ul>
            <p class="tit">商家位置</p>
            <div class="clearfix">
                <div class="fl map" id = "map" style="width: 250px; height: 200px;">

                </div>
                <div class="fl business">
                    <dl v-if = "org != null">
                        <dt>{{org.orgName}}</dt>
                        <dd class="clearfix">
                            <label class="fl">地址：</label>
                            <p class="fl">{{org.street}}</p>
                        </dd>
                        <%--<dd class="look-map">查看地图公交/驾校去这里</dd>
                        <dd>地铁：距夏滘站约592米</dd>--%>
                        <dd>电话：{{org.contactInformation}}</dd>
                    </dl>
                </div>
            </div>
            <p class="tit"><a name = "pay">购买须知</a></p>
            <table class="pay-note" v-if = "service != null">
                <tbody>
                <tr>
                    <td>温馨提示</td>
                    <td>
                        <ul>
                            <li>如发现有问题，请你在消费时向商户咨询</li>
                            <li>为了保障您的权益，建议使用线上支付。若使用其他支付方式导致纠纷，</li>
                            <li>本商家不承担任何责任，感谢您的理解和支持</li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>适用人群</td>
                    <td>{{service.userType}}</td>
                </tr>
                <tr>
                    <td>预约信息</td>
                    <td>{{service.appointment}}</td>
                </tr>
                <%--<tr>
                    <td>商家服务</td>
                    <td>
                        <ul>
                            <li>百草园美容面部深层排螨清洁</li>
                            <li>百草园美容单人背部经络排毒</li>
                            <li>免费提供WiFi</li>
                            <li></li>
                        </ul>
                    </td>
                </tr>--%>
                </tbody>
            </table>
            <div class="business-info">
                <div class="business-tit">
                    <h3><a name = "service">服务介绍</a></h3>
                </div>
                <div class="business-det" v-if = "service != null" v-html = "service.description">
                </div>
            </div>
        </div>
        <div class="business-info" v-cloak>
            <div class="business-tit">
                <h3><a name = "org">商家介绍</a></h3>
            </div>
            <div class="business-det" v-if = "org != null" v-html = "org.detail">

            </div>
        </div>
    </section>
</article>
</body>

<script src="/static/platform/v2.2.0/js/org/servicemanage/preview-service.js?v=2.2.0"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj&callback=initMap"></script>
<script>
    layui.use('layer');
    preview.init();
    preview.vm.service = '${service}' == '' ? '' : ${service};
    preview.vm.combo = '${combo}';
    preview.vm.org = '${org}' == '' ? '' : ${org};
    preview.vm.service.description = '${serviceDescription}';

    var initMap = function () {
        var map = new BMap.Map("map");
        preview.vm.initMap(map);
    }

</script>
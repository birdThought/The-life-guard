<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11"></t:base>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/banner.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/about.css">
    <title>生命守护官网  健康管理系统</title>
</head>
<body>
<%@include file="/context/mainHeader_1.jsp"%>
<section class="item-about-content">
    <div class="banner">
        <img src="/static/images/index/aboutUs.png" alt="">
    </div>
    <div class="banxin">
        <div class="about-title main-title">
            <div class="clearfix">
                <h3 class="fl">关于我们<em>></em><a href="#">帮助中心</a></h3>
                <%--<div class="clearfix searchFor fr">--%>
                    <%--<input type="text" id="searchCondition" placeholder="输入搜索内容" class=fl>--%>
                    <%--<span id="search" class="fl"></span>--%>
                <%--</div>--%>
            </div>
        </div>
        <div class="item-nav-container clearfix">
            <ul class="item-nav fl">
                <li class="current">
                    <img src="/static/images/index/help.png"/>
                    <a href="javascript:void(0)" class="class_name">公司介绍</a>
                </li>
                <li class="helpCenter">
                    <img src="/static/images/index/help_1.png"/>
                    <a href="javascript:void(0)">帮助中心</a>
                </li>
                <li>
                    <img src="/static/images/index/help_2.png"/>
                    <a href="javascript:void(0)">联系我们</a>
                </li>
            </ul>
            <ol class="item-nav-content fl">
                <li>
                    <h4 class="content-current">公司介绍</h4>
                    <p class="puretext">
                                                  生命守护是广州通众电气实业有限公司旗下品牌，是便携式智能预警健康检测设备、APP、平台三者合一的项目。 通过健康检测设备收集健康数据上传至云平台，生命守护用户端APP能对用户实现健康数据分析、疾病预警、健康 咨询、健康课堂等功能，机构客户使用机构端APP同步实现对用户的远程健康监测，为健康用户及健康机构提供专 业的健康/慢病管理云平台服务。目前已获得国家高新技术企业认证。     
2013年公司开始开发生命体征监测系统和慢病集成监测模块；2015年底推出生命守护云平台和便携式智能预警健 康检测设备产品；2016年将便携式智能预警健康检测设备与慢病管理系统结合一起，推出互动式慢病康复管理云平 台；2017年公司获得国家高新技术企业认证；2017年与中国电信达成战略合作伙伴协议，共同推进“智慧家庭”项 目。

                    </p>
                </li>
                <li>
                    <h4>帮助中心 <em>></em> <a href="#" class="content-current">问题分类</a></h4>
                    <ul class="helplist"></ul>
                    <a target="_blank" href="informationControl.do?helpCenterIndex" class="knowMore">了解更多>> </a>
                </li>
                <li>
                    <h4 class="content-current">联系我们</h4>
                    <address class="puretext">
                        <div>联系电话：400-0261-003</div>
                        <div>公司座机：020-61067620</div>
                        <div>传真：020-87237650</div>
                        <div>地址：广州市天河区柯木塱园区10栋302</div>
                    </address>
                    <h4  class="content-current mt25">地图坐标</h4>
                    <div class="bdMap">
                       <div style="width:100%;height: 300px" >
                           <%@include file="/context/aboutUsMap.jsp"%>
                       </div>
                    </div>
                </li>
            </ol>
        </div>
    </div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
</body>
<script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
<script type="text/javascript" src="/static/officialwebsite/index/js/helpCenter.js"></script>
<script>
    $(function(){
        $('.item-nav-content > li:gt(0)').css('display','none');
        $('.item-nav li').click(function(){
            $(this).addClass('current').siblings('li').removeClass('current');
            $(this).children("a").addClass('class_name').parent('li').siblings('li').children('a').removeClass('class_name');
            $('.item-nav-content > li').hide().eq($(this).index()).show();
        });
    });
    
    helpControl.init({
        f: null,
        search: null
    });
    $(".tz-navbar-nav > li:eq(4) a").addClass("on").parent("li").siblings("li").children("a").removeClass("on");
</script>
</html>



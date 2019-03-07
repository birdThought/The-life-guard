<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" ng-app="myApp" ng-controller="indexController">
<!--<html lang="en" ng-app="myApp">-->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--第三方css文件-->
    <link rel="stylesheet" href="/static/css/iconfont.css">
    <link rel="stylesheet" href="/static/css/base.css">
    <link rel="stylesheet" href="/static/css/index.css">
    <link async rel="stylesheet" href="https://cdn.materialdesignicons.com/1.8.36/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="/static/css/pretty.min.css">
    <link rel="stylesheet" href="/static/plugin/layui/v2.1.5/css/layui.css">

    <link rel="stylesheet" type="text/css" href="/static/plugin/jeDate/css/jedate.css?v=1.0.0">
    <title>生命守护-渠道商</title>

</head>
<body ng-cloak>

<header class="header container">
    <section class="header-left"><i></i></section>
    <section class="header-middle"><i></i><p>渠道商后台</p></section>
    <section class="header-right"><p onclick="common.logout();">退出登陆</p></section>
</header>
<nav class="nav">
    <section class="nav-content container clearfix">
        <section class="nav-left" ng-cloak>
            <img ng-if="userInfo.photo == '' || userInfo.photo == null" src="/static/images/head-icon.png">
            <img ng-if="userInfo.photo != '' && userInfo.photo != null" ng-src="{{userInfo.photo}}">
            <p>{{userInfo.name}}</p></section>
        <%--<section class="nav-right"><i></i><span>0</span><p>消息中心</p></section>--%>
    </section>
</nav>
<article class="content clearfix container">
    <section class="content-left">
        <div>
        <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;">
            <li class="layui-nav-item">
                <a href="javascript:;"><span class="nav-member" style="" ></span>套餐销售</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" ng-click = "go('/')"><span>会员列表</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/vip-card')"><span>邀请码管理</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/vip-member/financial')"><span>结算管理</span></a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><span class="nav-security" style="" ></span>套餐推广</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" ng-click = "go('/code')"><span>二维码列表</span></a></dd>
                    <dd><a href="javascript:;" ng-if="userInfo.type == 0 ? true:false" ng-click = "go('/sell')"><span>用户管理</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/temp')"><span>推广数据</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/spread')"><span>结算记录</span></a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><span class="nav-report-analysis" style="" ></span>报告分析服务</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" ng-click = "go('/search-member')"><span>用户搜索</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/report-analysis-order')"><span>订单中心</span></a></dd>
                    <%--<dd><a href="javascript:;" ng-click = "go('/report-analysis-financial')"><span>结算管理</span></a></dd>--%>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><span class="nav-member"></span>用户推广</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" ng-click = "go('/user-manager')"><span>用户管理</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/user-statistic')"><span>用户统计</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/month-cost')"><span>本月费用</span></a></dd>
                    <dd><a href="javascript:;" ng-click = "go('/account-list')"><span>结算记录</span></a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;" <%--ng-click = "go('/message')"--%>><span class="nav-message" style="" ></span>消息中心</a></li>
            <li class="layui-nav-item"><a href="javascript:;" ng-click = "go('/security')"><span class="nav-security" style="" ></span>账号安全</a></li>
        </ul>
        </div>
       <%-- <div style="border:none;opacity:0.7;right: 100px;" id="CfloatBtn" class="CfloatBtn">
            <a rel="nofllow" href="tencent://Message/?Uin=805673949&websiteName=sc.chinaz.com=&Menu=yes"  class="wjdc"><img style="opacity:0.6" src="/static/officialwebsite/v2.5.0/images/customer.png" alt=""><b style="font-size:1.2rem;display: block;font-weight:normal;color:#44c660">在线资讯</b></a>
        </div>--%>
    </section>
    <section ng-view  class="content-right clearfix">

    </section>
</article>
<script src="/static/plugin/jquery/jquery.min.js"></script>
<script src="/static/plugin/angular/angular.min.js"></script>
<script src="/static/plugin/angular-route/angular-route.min.js"></script>
<script src="/static/plugin/angular/ocLazyLoad.min.js"></script>
<script src="/static/plugin/layui/v2.1.5/layui.js"></script>
<script src="/static/plugin/highchart/highcharts.js"></script>
<script src="/static/plugin/jeDate/jeDate.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/webim.config.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/strophe-1.2.8.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/websdk-1.4.13.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/websdk.shim.js"></script>

<script src="/static/js/common/dateFormat.js"></script>
<script src="/static/js/common/app.js"></script>
<script src="/static/js/common/angular-filters.js"></script>
<script src="/static/js/common/controller.js"></script>
<script>
    /*layui应用各个模块*/
    layui.use(['layer', 'laypage', 'element'], function () {
        laypage = layui.laypage;
    });
</script>


<script src="/static/js/common/common.js"></script>
<script src="/static/js/common/http.js"></script>
<script src="/static/js/common/index.js"></script>
<script src="/static/plugin/date/DateUtils.js"></script>
<script src="/static/js/business/package.js"></script>
<script src="/static/plugin/qrcode/qrcode.min.js"></script>
<script src="/static/js/business/temporary.js"></script>
<script src="/static/js/vipmember/vip-member.js?v=1.0.0"></script>
<script src="/static/js/vipmember/vip-card.js?v=1.0.0"></script>

<script src="/static/js/reportanalysis/search-member.js?v=1.0.0"></script>
<script src="/static/js/reportanalysis/order.js?v=1.0.0"></script>
<script src="/static/js/accountsecurity/account-security.js?v=1.0.0"></script>
<script src="/static/js/vipmember/financial.js?v=1.0.0"></script>
<script src="/static/js/business/user-manager.js?v=1.0.0"></script>
<script src="/static/js/business/check-detail.js?v=1.0.0"></script>
<script src="/static/js/business/user-statistic.js?v=1.0.0"></script>
<script src="/static/js/business/month-cost.js?v=1.0.0"></script>
<script src="/static/js/business/detail-list.js?v=1.0.0"></script>
<script src="/static/js/business/account-list.js?v=1.0.0"></script>
<script src="/static/js/business/check-account.js?v=1.0.0"></script>
<script src="/static/js/business/business.js?v=1.0.0"></script>
<script src="/static/js/settle/settle-list.js?v=1.0.0"></script>

</body>
</html>
<script>
    /*菜单事件*/

</script>

<style>
    .layui-nav {
        background-color: #4c5f70 !important;
    }
    .layui-nav-child dd {
        background-color: #3a4957 !important;
    }
    .layui-nav-itemed, .layui-nav-itemed>a{
        background-color: #3a4957 !important;
    }
    .layui-nav-child .layui-this, .layui-nav-child .layui-this a{
        background-color: #5f7487 !important;
    }
    .layui-nav-bar {
        background-color: #00bfff !important;
    }
    .layui-nav-tree .layui-this, .layui-nav-tree .layui-this a {
        background-color: #5f7487 !important;
    }
    .layui-nav-tree {
        width: 100% !important;
    }
    .layui-nav-itemed .layui-nav-more {
        top: 25px;
    }
    .layui-nav-tree .layui-nav-more {
        top: 25px;
    }
    .layui-nav-tree .layui-nav-item {
        line-height: 70px;
    }
    .layui-nav-tree .layui-nav-item a {
        height: 70px;
        font-size: 16px;
    }
    .layui-nav-tree .layui-nav-child a {
        height: 55px;
        line-height: 55px;
        font-size: 16px;
    }
    .layui-nav-tree .layui-nav-child a span {
        margin-left: 30px;
        font-size: 16px;
    }

    .nav-member {
        background: url(/static/images/menu-sprite.png);
        background-position: 0px -0px;
        width: 20px;
        height: 18px;
        display: inline-block;
        margin-right: 10px
    }
    .nav-report-analysis {
        background-image: url(/static/images/report-icon.png);;
        background-position: -10px -46px;
        width: 20px;
        height: 20px;
        display: inline-block;
        margin-right: 10px
    }
    .nav-message {
        background: url(/static/images/menu-sprite.png);
        background-position: 0 -80px;
        width: 20px;
        height: 18px;
        display: inline-block;
        margin-right: 10px
    }
    .nav-security {
        background: url(/static/images/menu-sprite.png);
        background-position: 0px -120px;
        width: 20px;
        height: 18px;
        display: inline-block;
        margin-right: 10px
    }
</style>




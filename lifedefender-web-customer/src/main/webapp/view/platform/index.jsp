<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en" ng-app="myApp" ng-controller="indexController" ng-init="init()">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/css/base.css">
    <link rel="stylesheet" href="/static/css/index.css">
    <link rel="stylesheet" href="/static/css/iconfont.css">
    <link async rel="stylesheet" href="https://cdn.materialdesignicons.com/1.8.36/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="/static/css/pretty.min.css">
    <link rel="stylesheet" href="/static/css/iconfont1.css">
    <link rel="stylesheet" href="/static/css/member/healthAsk.css">
    <link rel="stylesheet" href="/static/css/member/shenhe.css">
    <link rel="stylesheet" href="/static/css/member/paramSubmit.css">
    <link rel="stylesheet" href="/static/css/member/tableLayout.css  ">
    <link rel="stylesheet" href="/static/plugin/layui/v2.1.5/css/layui.css">
    <script src="/static/plugin/jquery/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/plugin/jeDate/css/jedate.css?v=1.0.0">
    <title>客服端</title>
<style type="text/css">
.hidden {
    display: none !important;
}

.layui-nav-tree .layui-nav-item a:hover{background:none}
.layui-nav-itemed .layui-nav-child{background:none;}
.layui-nav .layui-nav-item a{padding-left:0;}
.layui-nav-child {width:208px;left:-62px;
    background:#3a4957!important;}
.layui-nav-child a{display:block;width:200px;text-align: center;width:100%;border-bottom:1px solid #444}
.layui-nav-item a {display:block;line-height:60px;font-size:14px;}
.layui-nav-more{ top: 26px!important;right: 64px !important;transition: all .8s!important; -webkit-transition: all .8s;border-width:5px!important;}
</style>
</head>
<body ng-cloak>
<header class="header">
    <section class="header-left"><i></i></section>
    <section class="header-middle"><i></i><p>客服端</p></section>
    <section class="header-right"><i style="position: absolute;"></i><p onclick="common.logout();">退出</p></section>
    <section class="nav-content clearfix">
        <section class="nav-left" ng-cloak>
            <img ng-if="userInfo.photo == '' || userInfo.photo == null" src="/static/images/head-icon.png">
            <img ng-if="userInfo.photo != '' && userInfo.photo != null" ng-src="{{userInfo.photo}}">
            <p>{{userInfo.name}}  [{{userInfo.userNo}}] </p>
        </section>
        <%--<section class="nav-middle"><ul  class="nav-middle-ul"><li>自营会员</li><li>渠道商会员</li></ul></section>--%>
    </section>
    <section class="nav-right" ng-click = "showChatDialog()"><i></i><span class="news " value="0">0</span><p>消息中心</p></section>
</header>

<article style="margin-left: 0;" class="content  clearfix container">
    <section  class="content-left">
        <ul  class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;width: 100%;background: none;">
            <shiro:hasPermission name="member:get"><li>
                <i class="iconfont icon-guanliyuan"></i>
                <div class="layui-nav-item deal-methods" <%--ng-if="userInfo.authority == 0 ? true:false"--%>>
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">用户管理</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="member:get"><dd><a href="" ng-click = "go('/member')">用户查看</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="member_offline:get"><dd><a href="" ng-click = "go('/member/offline')">业务员查看</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="member:count"><dd><a href="" ng-click="go('/member/count')">用户统计</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="member_c3:get"><dd><a href="" ng-click="go('/member/c3')">C3用户</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="member_hx:get"><dd><a href="" ng-click="go('/member/hx')">环信统计</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="member_report:get"><dd><a href="" ng-click="go('/member/report')">用户反馈</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="org:get"><li>
                <i class="iconfont icon-changqudemendian" style="font-size: 26px"></i>
                <div class="layui-nav-item deal-methods" <%--ng-if="userInfo.authority == 0 ? true:false"--%>>
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">门店管理</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="org:get"><dd><a href="" ng-click="go('/org')">门店查看</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="org:check"><dd><a href="" ng-click="go('/org/check')">门店审核</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="org:recommend"><dd><a href="" ng-click="go('/org/recommend')">推荐管理</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="serve:get"><li>
                <i class="iconfont icon-fuwu"></i>
                <div class="layui-nav-item deal-methods" <%--ng-if="userInfo.authority == 0 ? true:false"--%>>
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">服务管理</a>
                    <dl class="layui-nav-child">
                        <%--<dd><a href="" ng-click="go('/serve')">服务管理</a></dd>--%>
                        <shiro:hasPermission name="serve:get"><dd><a href="" ng-click="go('/serve/list')">服务查看</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="serve:count"><dd><a href="" ng-click="go('/serve/count')">服务统计</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="order:get"><li>
                <i class="iconfont icon-dingdan"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">订单管理</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="order:get"><dd><a href="" ng-click="go('/order')">订单查看</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="order:count"><dd><a href="" ng-click="go('/order/count')">订单统计</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="order_flow:get"><dd><a href="" ng-click="go('/order/flow')">交易流水</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="order_flow:count"><dd><a href="" ng-click="go('/order/flow/count')">流水统计</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="order:statement"><dd><a href="" ng-click="go('/order/statement')">结算记录</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="news:get"><li>
                <i class="iconfont icon-zixundianji"></i>
                <div class="layui-nav-item deal-methods" <%--ng-if="userInfo.authority == 0 ? true:false"--%>>
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">资讯管理</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="news:get"><dd><a href="" ng-click = "go('/news')">资讯管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="news_column:get"><dd><a href="" ng-click = "go('/news/column')">栏目管理</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="combo:get"><li>
                <i class="iconfont icon-huiyuan2"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">自营会员</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="combo:get"><dd><a href="" ng-click = "go('/combo')">套餐管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="news:get"><dd><a href="" ng-click = "go('/news?childId=31')">套餐内容</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="combo_item:get"><dd><a href="" ng-click = "go('/combo/item')">套餐项管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="news:get"><dd><a href="" ng-click = "go('/news?childId=32')">套餐项内容</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="combo:code"><dd><a href="" ng-click = "go('/combo/invite-code')">会员邀请码</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="combo_member:get"><dd><a href="" ng-click = "go('/combo/member')">会员管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="combo_member_worklist:get"><dd><a href="" ng-click = "go('/combo/member/worklist')">服务预约</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="drugs:get"><li>
                <i class="iconfont icon-fenxibaogao"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">自营服务</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="analysis:get"><dd><a href="" ng-click = "go('/report-analysis')">分析报告</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="drugs:get"><dd><a href="" ng-click = "go('/drugs')">药品管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="drugs:order"><dd><a href="" ng-click = "go('/drugs/order')">订单管理</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="push:get"><li>
                <i class="iconfont icon-xiaoxizhongxin"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">消息中心</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="push:im"><dd><a href="" ng-click = "showChatDialog()">对话交流</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="push:get"><dd><a href="" ng-click = "go('/sms')">短信记录</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="push:sms"><dd><a href="" ng-click = "go('/sms/send')">发送短信</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="push:get"><dd><a href="" ng-click = "go('/push')">消息推送</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="coupon:get"><li>
                <i class="iconfont icon-msnui-market"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">推广运营</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="coupon:get"><dd><a href="" ng-click="go('coupon')">优惠券管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="coupon_pkg:get"><dd><a href="" ng-click="go('coupon/package')">优惠券礼包</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="business:get"><dd><a href="" ng-click = "go('business')">渠道商管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="agent:get"><dd><a href="" ng-click = "go('agent')">代理商管理</a></dd></shiro:hasPermission>
                        <%--<shiro:hasPermission name="drugs:*"><dd><a href="">广告设置</a></dd></shiro:hasPermission>--%>
                    </dl>
                </div>
            </li></shiro:hasPermission>
<shiro:hasPermission name="visit:get"><li>
                <i class="iconfont icon-tongji"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">访问量数据</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="visit:get"><dd><a href="" ng-click = "go('/visit')">用户登录记录</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="visit:get"><dd><a href="">用户登录统计</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="back_visit:login"><dd><a href="" ng-click="go('/back/login')">后台登录记录</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="back_visit:visit"><dd><a href="" ng-click="go('/back/visit')">后台操作日志</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
            
            
            <!--  商城管理     static/css/reportanalysis/report-analysis.css -->
<shiro:hasPermission name="commodity:get"><li>
                <i class="iconfont icon-tongji"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">商城管理</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="commodity:get"><dd><a href="" ng-click = "go('/commodity')">商品列表</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="commodity_cif:get"><dd><a href="" ng-click="go('/commodity/category/tree')">商品分类</a></dd></shiro:hasPermission>
                    	<shiro:hasPermission name="commodity_shop:get"><dd><a href="" ng-click="go('/commodity/shop/list')">商铺列表</a></dd></shiro:hasPermission>
                    	<shiro:hasPermission name="commodity_order:get"><dd><a href="" ng-click="go('/commodity/order/list')">订单列表</a></dd></shiro:hasPermission>
         				<shiro:hasPermission name="commodity_tuijian:category"><dd><a href="" ng-click="go('/commodity/recommend/category')">类目推荐</a></dd></shiro:hasPermission>
         				<shiro:hasPermission name="commodity_tuijian:advert"><dd><a href="" ng-click="go('/commodity/recommend/advert')">商品广告</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>    
            
<shiro:hasPermission name="user:get"><li>
                <i class="iconfont icon-xitongguanli"></i>
                <div class="layui-nav-item deal-methods">
                    <a class="layui-nav-item-first" href="javascript:;" style="padding-left: 0;color: #fff;">系统管理</a>
                    <dl class="layui-nav-child">
                        <shiro:hasPermission name="user_manage:get"><dd><a href=""  ng-click = "go('/user')">用户管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="user:get"><dd><a href="" ng-click = "go('/account')">用户安全</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="role:get"><dd><a href=""  ng-click="go('/role')">角色权限</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="permiss:get"><dd><a href="" ng-click="go('/permission')">权限管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:department"><dd><a href="" ng-click = "go('/data/department')">科室管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:foodKind"><dd><a href="" ng-click = "go('/data/foodKind')">食物种类</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:food"><dd><a href="" ng-click ="go('/data/food')">食物管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:sportKind"><dd><a href="" ng-click ="go('/data/sportKind')">运动种类</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:sport"><dd><a href="" ng-click ="go('/data/sport')">运动管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:suggection"><dd><a href="" ng-click ="go('/data/measure-suggection')">测量建议</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:reason"><dd><a href="" ng-click ="go('/data/measure-reason')">测量结果原因</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:app"><dd><a href="" ng-click ="go('/app-version')">APP版本管理</a></dd></shiro:hasPermission>
                        <shiro:hasPermission name="data:weixin"><dd><a href="" ng-click = "go('/weixin')">微信菜单</a></dd></shiro:hasPermission>
                    </dl>
                </div>
            </li></shiro:hasPermission>
        </ul>
    </section>
    <section ng-view  class="content-right" timeout ng-cloak></section>
    <%@include file="/view/platform/message/chat-dialog.jsp"%>
</article>

<script src="/static/plugin/angular/angular.min.js"></script>
<script src="/static/js/file/fileUpload.js"></script>
<script src="/static/plugin/angular-route/angular-route.min.js"></script>
<script src="/static/plugin/angular/ocLazyLoad.min.js"></script>
<script src="/static/plugin/layui/v2.1.5/layui.js"></script>
<script src="/static/plugin/highchart/highcharts.js"></script>
<script src="/static/plugin/jeDate/jeDate.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/webim.config.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/strophe-1.2.8.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/websdk-1.4.13.js"></script>
<script type="text/javascript" src="/static/plugin/hx/v1.4.13/websdk.shim.js"></script>
<script src="/static/plugin/vue/vue.min.js"></script>
<script src="/static/js/common/dateFormat.js"></script>
<script src="/static/js/common/app.js"></script>
<script>
    (function ($) {
        $.extend({
            baseUrl: '${ctx}'
        })
    })($)
</script>
<script src="/static/js/common/angular-filters.js"></script>
<script src="/static/js/common/controller.js"></script>
<script src="/static/js/member/member.js?v=1.0.0"></script>
<script src="/static/js/shop/goodsClassify.js?v=1.0.0"></script>
<script src="/static/js/member/membercount.js?v=1.0.0"></script>
<script src="/static/js/member/memberC3.js?v=1.0.0"></script>
<script src="/static/js/member/memberHx.js?v=1.0.0"></script>
<script src="/static/js/member/memberreport.js?v=1.0.0"></script>
<script src="/static/js/org/orgList.js?v=1.0.0"></script>
<script src="/static/js/Character/roles.js?v=1.0.0"></script>
<script src="/static/js/org/addorg.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/department-manage.js?v=1.0.0"></script>
<script src="/static/js/business/addbusiness.js?v=1.0.0"></script>

<script>
    /*layui应用各个模块*/
    layui.use(['layer', 'laypage', 'element'], function () {
        laypage = layui.laypage;
    });
</script>

<script src="/static/js/common/index.js"></script>
<script src="/static/js/common/common.js"></script>
<script src="/static/js/common/http.js"></script>
<script src="/static/js/message/chat-dialog.js?v=1.0.0"></script>
<script src="/static/js/vipmember/vip-member.js?v=1.0.0"></script>
<script src="/static/js/reportanalysis/reportanalysis.js?v=1.0.0"></script>
<script src="/static/js/accountsecurity/account-security.js?v=1.0.0"></script>
<script src="/static/js/customerorder/customerorder.js?v=1.0.0"></script>
<script src="/static/js/customerorder/success-customerorder.js?v=1.0.0"></script>
<script src="/static/js/push/push-manage.js?v=1.0.0"></script>
<script src="/static/js/business/business.js?v=1.0.0"></script>
<script src="/static/js/combomanage/combo-manage.js?v=1.0.0"></script>
<script src="/static/js/combomanage/combo-item-manage.js?v=1.0.0"></script>
<script src="/static/js/drugs/drugs-manage.js?v=1.0.0"></script>
<!-- <script src="static/js/stores/goodsListOf.js?v=1.0.0"></script> -->
<script src="/static/js/member/memberOffline.js?v=1.0.0"></script>
<script src="/static/js/drugs/drugs-order-manage.js?v=1.0.0"></script>
<script src="/static/js/permission/permission-manage.js?v=1.0.0"></script>
<script src="/static/js/customer/customer-manager.js?v=1.0.0"></script>
<script src="/static/js/visitorLog/visit-userLog.js?v=1.0.0"></script>
<script type="text/javascript" src="/static/plugin/uedit/ueditor.config.js"></script>
<script type="text/javascript" src="/static/plugin/uedit/editor_api.js"></script>
<script src="/static/js/agent/agent.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/foodKind.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/food.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/sportKind.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/sport.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/measure-suggection.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/measure-reason.js?v=1.0.0"></script>
<script src="/static/js/systemmanage/app-version.js?v=1.0.0"></script>
<script src="/static/js/visitorLog/backLog.js?v=1.0.0"></script>
<script src='/static/js/shop/area_data.js'></script>
</body>
</html>
<script src="/static/js/common/directive.js"></script>
<script>
    /*菜单事件*/
    $(document).ready(function () {
        //indexJs.addLine($('.content-left>ul>li'),$('<u style="width: 5px;height: 60px;background: #eee;position: absolute;left: 0"></u>'));
        var lis = $('.content-left > ul > li');
         var SecondMenu = $('.layui-nav-child > dd');
         SecondMenu.on('click',function(){
             var MenuIndex=$(this).index();
           $.cookie('MenuCurrent',MenuIndex)
         });
        lis.on('mouseover',function () {
            $(this).children('i').addClass('i-spec');
        });
        lis.on('mouseout',function () {
           if (!$(this).hasClass('on')) {
               $(this).children('i').removeClass('i-spec');
           }
        });
        (function(){lis.on("click",function(){$(this).addClass("on").siblings("li").removeClass("on").find("a").css({"color":"#fff"});var Index=$(this).index();$.cookie("Current",Index);if($(this).hasClass("on")){$(this).children("i").addClass("i-spec")}else{$(this).children("i").removeClass("i-spec")}})})();
        if($.cookie('Current')==null||$.cookie('MenuCurrent')==null){return false;}else{   var flag=true;var Number=$.cookie("Current");var num=$.cookie("MenuCurrent");$(".content-left>ul>li:eq("+Number+")").css({"background":"#3a4957"}).find(".layui-nav-item-first").css({"color":"deepskyblue"});$(".content-left>ul>li:eq("+Number+")").find(".layui-nav-item").addClass("layui-nav-itemed");$(".content-left>ul>li:eq("+Number+")").siblings("li").removeClass("on").find(".layui-nav-item-first").css({"color":"#fff"});$(".content-left>ul>li:eq("+Number+")").find(".layui-nav-item").find(".layui-nav-child>dd:eq("+num+")").addClass("layui-this");}
        // indexJs.getHref();
        $(".content-left>ul>li:eq("+Number+")").find(".layui-nav-item").find(".layui-nav-child>dd:eq("+num+")").click();
    })
</script>





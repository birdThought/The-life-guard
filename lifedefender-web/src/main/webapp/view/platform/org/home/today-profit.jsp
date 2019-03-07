<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <%--<link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/common-store.css?v=2.2.0">--%>
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/today-profit.css?v=2.2.0">
    <title>今日收入</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" v-cloak>
            <div class="store-href">
                <a href="#">个体门店主页</a><em>&gt;</em><span>今日收入</span>
            </div>
            <ul class="member-task clearfix">
                <li class="shouyi">
                    <dl>
                        <dt>今日收入</dt>
                        <dd class="count"><em>{{todayProfit}}</em>元</dd>

                    </dl>
                </li>
                <li class="shouyi-7">
                    <dl>
                        <dt>本月收入</dt>
                        <dd class="count"><em>{{weekProfit}}</em>元</dd>

                    </dl>
                </li>
            </ul>
            <div class="income-top">
                <div>
                    <div class="data-show clearfix">
                        <ul>
                            <li class="on" @click = "listProfit(1, 'profit')">近7天</li>
                            <li @click = "listProfit(2, 'profit')">14天</li>
                            <li @click = "listProfit(3, 'profit')">30天</li>
                        </ul>
                        <div>
                            <span style="margin-right: 5px; color: gray;">按时间段</span>
                            <input type="text" id="s" >
                            <em>至</em>
                            <input type="text" id="e" >
                        </div>
                    </div>
                    <div id="container" class="profit-chart"></div>
                </div>
            </div>
            <div class="income-notice" >
                <div class="order-lists">
                    <span>日期</span>
                    <span style="margin-left: 28px;">订单号</span>
                    <span style="margin-left: 60px;">服务项目</span>
                    <span>用户名称</span>
                    <span>消费</span>
                </div>
                <ul class="order-content">
                    <li v-for = "order in orders" v-cloak>
                        <span>{{order.createDate | date('yyyy-MM-dd')}}</span>
                        <span style="margin-left: 28px;">{{order.orderNumber}}</span>
                        <span style="margin-left: 60px;">{{order.projectName}}</span>
                        <span>{{order.userName}}</span>
                        <span>￥{{order.charge}}</span>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/plugins/highcharts/highcharts.js"></script>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script type="text/javascript" src="/static/common/js/dateFormat.js"></script>
<script src="/static/platform/v2.2.0/js/org/home/today-profit.js?v=2.2.0"></script>
<script>
    layui.use('layer');
    $('.main-nav li').eq(0).click();
    common.addBorder();
    home.vm.type = 'profit';
    home.vm.profit = '${data}' == '' ? null : ${data};
    home.vm.startDate = new Date().Format("yyyy-MM-dd");
    home.vm.endDate = new Date().Format("yyyy-MM-dd");
    home.init();

    home.vm.userType = '${userType}' == '1' ? 'orgUser' : 'org';
    home.vm.listProfit(1, 'profit');

</script>
<script>
    //这里是日期联动的关键
    function endDates() {
        //将结束日期的事件改成 false 即可
        end.trigger = false;
        jeDate(end);
    }

    var start = {
        dateCell : "#s",
        format:"YYYY-MM-DD",
        isinitVal:true,
        initAddVal:[0],
        startMin:"1900-01-01",
        startMax: jeDate.now(0),
        minDate:"1900-01-01",
        maxDate: jeDate.now(0),
        zindex: 999,
        choosefun: function(elem, val){
            home.vm.startDate = val;
            end.minDate = val;
            endDates();
        }
    }

    var end = {
        dateCell : "#e",
        format:"YYYY-MM-DD",
        isinitVal:true,
        initAddVal:[0],
        startMin:"1900-01-01",
        startMax:jeDate.now(0),
        minDate: jeDate.now(0),
        maxDate: jeDate.now(0),
        zindex: 999,
        choosefun: function(elem, val){
            home.vm.endDate = val;
            start.maxDate = val; //将结束日的初始值设定为开始日的最大日期
        }
    }

    jeDate(start);
    jeDate(end);
</script>
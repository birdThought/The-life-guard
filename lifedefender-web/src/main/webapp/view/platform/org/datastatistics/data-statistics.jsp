<%--
 * 数据统计
 * date: 2017/8/16 11:18
--%>
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
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-data-statistics.css?v=2.4.0">
    <script src="http://files.cnblogs.com/files/xiaohuochai/velocity.min.js"></script>
    <title>数据统计报表</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white">
            <div class="server-items">
                <p class="server-items_p">
                    <span>服务项目&nbsp; : &nbsp;<%--<i></i>--%></span>
                    <select style="width: 130px" v-model = "search.projectCode">
                        <option value="-1">全部</option>
                        <option v-for = "p in project" :value = "p.projectCode">{{p.name | subStr(1)}}</option>
                    </select>
                    <span>病种 &nbsp;:&nbsp; <%--<i></i>--%></span>
                    <select style="width: 98px" v-model = "search.diseasesId">
                        <option value="-1">不限</option>
                        <option v-for = "d in diseases" :value = "d.id">{{d.name}}</option>
                    </select>
                    <span>性别 &nbsp;:&nbsp; <%--<i></i>--%></span>
                    <select v-model = "search.gender">
                        <option value="-1">不限</option>
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                    <span>年龄段 &nbsp;:&nbsp;<%-- <i></i>--%></span>
                    <select class="select-spec" v-model = "search.startAge">
                        <option value="-1">不限</option>
                        <option v-for = "i in 100" :value = "i">{{i}}</option>
                    </select>
                    <span style="font-weight: 500">&nbsp;&nbsp;至&nbsp;&nbsp;<%--<i></i>--%></span>
                    <select  id="select_spec" class="select-spec" v-model = "search.endAge">
                        <option value="-1">不限</option>
                        <option v-for = "i in 100" :value = "i">{{i}}</option>
                    </select>
                    <button class="search cursor-pointer" @click = "searchStatistics(1)">查询</button>
                </p>
                <button type="button" class="excel fr cursor-pointer" @click = "exportStatistics(1)">
                    <a id="dlink" href="" style="display: none;"></a>导出Excel
                </button>
            </div>
            <!--表格区域开始-->

            <table class="hovertable" id="table-id">
                <thead>
                    <th style="width: 30%">服务项目</th>
                    <th style="width: 10%">病种</th>
                    <th style="width: 10%">年龄段</th>
                    <th>性别</th>
                    <th>总人数</th>
                    <th>总消费(元)</th>
                    <th>总消费次数</th>
                    <th>平均消费(元)</th>
                    <th>统计详情</th>
                </thead>
                <tbody is="transition-group"
                                  name="staggered-fade"
                                  v-bind:css="false"
                                  v-on:before-enter="beforeEnter"
                                  v-on:enter="enter"
                                  v-on:leave="leave"
                                  v-cloak
                >
                    <tr v-for = "(r, index) in results.data" v-bind:key = "index" v-bind:data-index="index">
                        <td style="width: 30%">{{r.projectName}}</td>
                        <td style="width: 10%">{{r.diseasesName | isNone(1)}}</td>
                        <td style="width: 10%">{{ageSegment}}</td>
                        <td>{{r.gender | gender(1)}}</td>
                        <td>{{r.totalUser}}</td>
                        <td>{{r.totalCharge}}</td>
                        <td>{{r.totalOrder}}</td>
                        <td>{{r.averageCharge}}</td>
                        <td><a href="#" class="details"
                               @click = "goStatisticsDetails(r.projectCode, r.diseasesId, r.gender, ageSegment)">详情</a></td>
                    </tr>
                </tbody>
            </table>

            <div id="page-container" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
            <!--表格区域结束-->
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/platform/v2.2.0/js/org/datastatistics/data-statistics.js?v=2.4.0"></script>
<script src = "/static/common/js/pageUtils.js?v=2.2.0"></script>
<script src = "/static/plugins/layer/layer.js"></script>
<script src = "/static/plugins/excel/excel.js?v=2.4.0"></script>
<script>
    layui.use('layer');
    if (window.jQuery) {
        var Velocity = $.Velocity;
    }

    statistics.vm.project = '${project}' == '' ? [] : JSON.parse('${project}'.replace(/\\/g, '/'));
    statistics.vm.diseases = '${diseases}' == '' ? [] : JSON.parse('${diseases}');

    statistics.init();
    common.addBorder();

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(6).addClass('menu-current');
    } else {
        $('.main-nav li').eq(5).addClass('menu-current');
    }
</script>
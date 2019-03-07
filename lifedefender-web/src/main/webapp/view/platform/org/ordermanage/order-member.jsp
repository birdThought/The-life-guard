<%--
 * 订单列表
 * date: 2018/12/20 19:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui2.1.2,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-push-manage.css?v=2.4.0">
    <link rel="stylesheet" href="/static/plugins/layui/v2.1.2/css/layui.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/velocity/1.2.3/velocity.min.js"></script>
    <title>订单列表</title>
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
                    <span>用户名 &nbsp;:&nbsp; <%--<i></i>--%></span>
                    <input type="text" placeholder="用户名" style="width:200px;height:30px;border: 1px solid #10BA71;" v-model = "search.userName">
                    
                    <span>真实姓名&nbsp; : &nbsp;<%--<i></i>--%></span>
                    <input type="text" placeholder="真实姓名" style="width:200px;height:30px;border: 1px solid #10BA71;" v-model = "search.realName">
                    
                    <span>门店名称&nbsp; : &nbsp;<%--<i></i>--%></span>
                    <input type="text" placeholder="门店名称" style="width:200px;height:30px;border: 1px solid #10BA71;" v-model = "search.orgName">
                    
                    </br></br>
                    <span>订单类型 &nbsp;:&nbsp; <%--<i></i>--%></span>
                    <select v-model = "search.orderType" name="oType" id="oType">
                        <option value="">选择订单类型</option>
                        <option value="Vip">Vip套餐</option>
                        <option value="Service">服务</option>
                    </select>
                    
                    <button class="search search-btn cursor-pointer" @click = "searchStatistics()">查询</button>
                </p>
            </div>
            <!--表格区域开始-->
            <table class="hovertable" id="table-id">
                <thead>
                  <th style="width: 5%">序号</th>
                  <th>订单编号</th>
                  <th>用户名</th>
                  <th>用户姓名</th>
                  <th>门店</th>
                 <!--  <th>服务类型</th> -->
                  <th>订单类型</th>
                  <th>状态</th>
                  <th>消费金额</th>
                  <th>引入机构编号</th>
                  <th>引入机构获利(元)</th> 
                  <th>服务机构编号</th>
                  <th>服务机构获利(元)</th> 
                  <th>下单时间</th>
                </thead>
                <tbody is="transition-group" name="staggered-fade" v-bind:css="false" >
                 
                <tr v-for = "(r, index) in results.data" v-bind:key = "index" v-bind:data-index="index" v-cloak>
                    <td style="width: 5%">{{index + 1 + (results.nowPage - 1) * pageSize}}</td>
                    <td>{{r.orderNumber}}</td>
                    <td>{{r.userName}}</td>
                    <td>{{r.realName}}</td>
                    <td>{{r.orgName}}</td>
                    <!-- <td>{{r.serveName}}</td> -->
                    <td>{{r.orderType}}</td>
                    <td>{{r.status | orderStatus}}</td>
                    <td>{{r.charge}}</td>
                    <td>{{r.introduceOrgUserNo}}</td>
                    <td>
                        <div v-if="userNo != r.introduceOrgUserNo">{{r.introduceOrgIncome}}</div>
                        <div v-if="userNo == r.introduceOrgUserNo"><span style="color: red">{{r.introduceOrgIncome}}</span></div>
                    </td>
                    <td>{{r.serviceOrgUserNo}}</td>
                    <td>
                        <div v-if="userNo != r.serviceOrgUserNo">{{r.serviceOrgIncome}}</div>
                        <div v-if="userNo == r.serviceOrgUserNo"><span style="color: red">{{r.serviceOrgIncome}}</span></div>
                   </td> 
                   <td>{{r.createDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
                </tr>
                </tbody>
            </table>
            <div > <font style="margin-left: 40%">引入机构:{{introduceTotal}} 元</font> <font style="margin-left: 10%">服务机构：{{serviceTotal}} 元</font> <font style="margin-left: 10%">合计: {{total}} 元</font></div>
        </div>
        <div id="page-container" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
    </section>
</article>
<!-- /article -->
</body>
</html>

<script src = "/static/common/js/pageUtils.js?v=2.2.0"></script>
<script src = "/static/common/js/dateFormat.js"></script> 
<script src = "/static/plugins/layer/layer.js"></script>
<script src = "/static/platform/v2.2.0/js/org/ordermanage/order-member.js?v=2.4.0"></script>
<script>
layui.use(['element', 'layer']);
       order.init();
</script> 
<%--
 * 用户管理
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
    <title>用户管理</title>
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
                    <span>真实姓名&nbsp; : &nbsp;<%--<i></i>--%></span>
                    <input type="text" placeholder="用户名" style="width:200px;height:30px;border: 1px solid #10BA71;" v-model = "search.userName">
                    
                    <span>真实姓名&nbsp; : &nbsp;<%--<i></i>--%></span>
                    <input type="text" placeholder="真实姓名" style="width:200px;height:30px;border: 1px solid #10BA71;" v-model = "search.realName">
                           
                    <span>手机号码 &nbsp;:&nbsp; <%--<i></i>--%></span>
                    <input type="text" placeholder="手机号码" style="width:200px;height:30px;border: 1px solid #10BA71;" v-model = "search.mobile">
                    <button class="search search-btn cursor-pointer" @click = "searchStatistics()">查询</button>
                </p>
            </div>
            <!--表格区域开始-->
            <table class="hovertable" id="table-id">
                <thead>
                 <th style="width: 5%">序号</th>
                 <th>用户名</th>
                 <th>真实姓名</th>
                 <th width="60px">性别</th>
                 <th>手机号码</th>
                 <th>年龄</th>
                 <th>注册时间</th>
                </thead>
                <tbody is="transition-group" name="staggered-fade" v-bind:css="false" >
                <tr v-for = "(r, index) in results.data" v-bind:key = "index" v-bind:data-index="index" v-cloak>
                    <td style="width: 5%">{{1 + index + (results.nowPage - 1) * pageSize}}</td>
                    <td>{{r.userName}}</td>
                    <td>{{r.realName}}</td>
                    <td>{{r.gender | sex}}</td>
                    <td>{{r.mobile}}</td>
                    <td>{{r.age}}</td>
                    <td>{{r.createDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
                </tr>
                </tbody>
            </table>
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
<script src = "/static/platform/v2.2.0/js/org/offline/offline-manage.js?v=2.4.0"></script>
<script>
layui.use(['element', 'layer']);
   offline.init();
</script> 
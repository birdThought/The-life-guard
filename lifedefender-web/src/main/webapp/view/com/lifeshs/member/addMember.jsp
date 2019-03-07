<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>添加成员</title>
    <title>用户主页</title>
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
          mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" type="text/css" href="/static/css/familyInfor.css">
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl">
                <a href="#" class="top_cite">主页</a> &nbsp;&nbsp;>&nbsp;&nbsp; <a
                    href="#" class="top_cite">家庭组</a> &nbsp;&nbsp;>&nbsp;&nbsp;家庭成员
            </div>
            <div class="container fr">
                <div class="family_member" style="border-bottom: none;">
                    <h3>家庭成员</h3>
                    <div class="search">
                        <form id="searchForm">
                            <input type="search" id="searchCondition" autocomplete="off">
                            <button id="searchUser">搜索</button>
                        </form>
                    </div>
                </div>
                <!-- <p>
                    <em><span id="showUserName"></span></em>&nbsp;&nbsp;<span
                        id="searchResult"></span>
                </p> -->
                <table class="addMember">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>年龄</th>
                        <th>手机号码</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <div class="layUp">
                    <form id="addForm" autocomplete="off">
                        <input style="display: none;">
                        <p>
                            添加&nbsp;&nbsp;"<em><span id="userName"></span></em>"&nbsp;&nbsp;到家庭组
                        </p>
                        <label for="">请输入新成员的登录密码</label><br> <input type="password"
                                                                     placeholder="请输入密码" id="password">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        menuSetting({
            parent: 4,
            child: 1
        });
    });
</script>
<script type="text/javascript" src="/static/officialwebsite/js/family.js"></script>
</html>
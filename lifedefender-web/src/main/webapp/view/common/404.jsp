<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>404错误页面</title>
    <t:base type="jquery,layer"></t:base>
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <link rel="stylesheet" href="/static/officialwebsite/css/error.css">
    <t:base type="jquery,layer"></t:base>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/mainHeader_1.jsp" %>
    <div class="wrapper error-wrap">
        <p>很抱歉，您所请求的内容不存在！</p>
        <p>您可以<a class="error-btn" href="/">返回主页>></a></p>
    </div>
    <%@include file="/context/mainFooter_1.jsp"%>
</div>
</body>
</html>
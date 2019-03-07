<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <t:base type="jquery,layer"></t:base>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/common/css/error.css">
    <t:base type="jquery,layer"></t:base>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <div class="right-wrap">
            <div class="title fl">主页>C</div>
            <div class="container fr">
                <h3>信息提示</h3>
                <p>${error}</p>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#wearableDevice").prev().trigger("click");
        $("#wearableDevice").children("li:eq(1)").children().addClass("active");
    });
</script>
</html>